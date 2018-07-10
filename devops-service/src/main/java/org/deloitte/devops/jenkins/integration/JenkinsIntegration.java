package org.deloitte.devops.jenkins.integration;

import java.util.Arrays;
import java.util.List;

import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.deloitte.devops.jira.integration.JiraEndPoint;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.Transitions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

@Component
public class JenkinsIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(JenkinsIntegration.class);
	private static final String JENKINS_JOB_URI = "/job/";
	private static final String QUERY_STRING = "?";

	@Autowired
	private JenkinsJiraHelper helper;

	public void setBuildStatus(List<Issue> issues) {
		for (Issue issue : issues) {
			if (null != issue.getFields().getJobName() && issue.getFields().getStatus().getName()
					.equals(JenkinsEndPoint.ISSUE_STATUS_IN_RELEASE_TO_TEST)) {

				checkExistingBuildForIssue(issue, issues);
				if (null == issue.getFields().getBuildStatus()) {

					String result = helper.getFromJenkins(null, JENKINS_JOB_URI, issue.getFields().getJobName(),
							JenkinsEndPoint.JENKIS_BUILD_STATUS_API_URL);

					LOG.info("Jenkins status - [{}]", result);
					setBuildStatus(issue, result);
				}

			}

		}

	}

	public void setBuildStatusForIssue(Issue issue) {
		String queryString = JenkinsEndPoint.JENKIS_BUILD_STATUS_API_URL;// /lastBuild/api/json
		String result = null;

		String jobName = null != issue.getFields() && !StringUtils.isEmpty(issue.getFields().getJobName())
				? issue.getFields().getJobName()
				: null;
		String status = null != issue.getFields() && null != issue.getFields().getStatus()
				&& StringUtils.isEmpty(issue.getFields().getStatus().getName())
						? issue.getFields().getStatus().getName()
						: null;

		if (null != jobName && JenkinsEndPoint.ISSUE_STATUS_IN_RELEASE_TO_TEST.equals(status)) {

			try {
				result = helper.getFromJenkins(null, JENKINS_JOB_URI, jobName, queryString);
				setBuildStatus(issue, result);
				LOG.info("Build status was fetched successfully - [{}]", result);
			} catch (Exception e) {
				LOG.error("Unable to fetch build status - [{}]", e.getMessage());
			}

		}
		if (null != jobName && JenkinsEndPoint.ISSUES_IN_DEV.equals(status)) {
			try {
				result = helper.getFromJenkins(null, JENKINS_JOB_URI, jobName, queryString);
				setBuildStatusDev(issue, result);
				LOG.info("Build status was fetched successfully - [{}]", result);
			} catch (Exception e) {
				LOG.error("Unable to fetch build status - [{}]", e.getMessage());
			}
		}

	}

	private void checkExistingBuildForIssue(Issue issue, List<Issue> issues) {
		Issue tempIssue = issues.contains(issue) ? issues.get(issues.indexOf(issue)) : null;

		if (tempIssue == null || tempIssue.getFields() == null) {
			LOG.info("Either issue or Fields is null");
			return;
		}

		String buildStatus = tempIssue.getFields().getBuildStatus();
		LOG.info("Current build status - [{}]", buildStatus);

		if (!StringUtils.isEmpty(buildStatus)) {
			issue.getFields().setBuildStatus(buildStatus);
		}
	}

	private void setBuildStatus(Issue issue, String result) {
		JSONObject responseJson = new JSONObject(result);
		Gson gson = new Gson();
		String buildStatus = gson.fromJson(responseJson.get("result").toString(), String.class);
		issue.getFields().setBuildStatus(buildStatus);

		if (issue.getFields().getBuildStatus().equals(JenkinsEndPoint.SUCCESS_BUILD_STATUS))// Change to success for
																							// testing
		{
			issue.getFields().getStatus().setName(JiraEndPoint.INTEST);
			issue.getFields().getStatus().setDescription(JiraEndPoint.TESTING);
			changeStatusInJira(issue);
		} else if (issue.getFields().getBuildStatus().equals(JenkinsEndPoint.FAILURE_BUILD_STATUS)) {
			issue.getFields().getStatus().setName(JiraEndPoint.IN_DEVELOPMENT);
			issue.getFields().getStatus().setDescription(JiraEndPoint.DEVELOPMENT_OF_STORY);
			changeStatusInJira(issue);
		}
	}

	private void changeStatusInJira(Issue issue) {
		List<Transitions> transitionList = getTransitions(issue);
		String transitionId = getTransitionId(transitionList, issue);

		String body = formulateBody(transitionId);

		String status = helper.exchangeWithJira(HttpMethod.POST, null, body,
				JiraEndPoint.UPDATE_CURRENT_ISSUE_TO_TEST_OR_IN_DEVLOPMENT, issue.getId(),
				JiraEndPoint.UPDATE_ST_STRING, QUERY_STRING, JiraEndPoint.TRANSITION_QUERY_STRING);

		LOG.info("Status -- [{}]", status);

	}

	private String formulateBody(String transitionId) {
		return "{\"transition\":{\"id\":" + transitionId + "}}";
	}

	private String getTransitionId(List<Transitions> transitionList, Issue issue) {
		for (Transitions transitions : transitionList) {
			if (issue.getFields().getStatus().getName().equals(transitions.getName())) {
				return transitions.getId();
			}
		}
		return null;
	}

	private List<Transitions> getTransitions(Issue issue) {
		String result = helper.exchangeWithJira(HttpMethod.GET, null, null, JiraEndPoint.GET_TRANSITIONS, issue.getId(),
				JiraEndPoint.TRANSITION);

		if (!StringUtils.isEmpty(result)) {
			JSONObject responseJson = new JSONObject(result);
			Gson gson = new Gson();

			List<Transitions> list = Arrays
					.asList(gson.fromJson(responseJson.get("transitions").toString(), Transitions[].class));
			return list;
		}

		return null;
	}

	private void setBuildStatusDev(Issue issue, String result) {
		JSONObject responseJson = new JSONObject(result);
		Gson gson = new Gson();
		String buildStatus = gson.fromJson(responseJson.get("result").toString(), String.class);
		issue.getFields().setBuildStatus(buildStatus);
	}

}
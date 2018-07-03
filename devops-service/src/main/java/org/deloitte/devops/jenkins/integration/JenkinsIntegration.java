package org.deloitte.devops.jenkins.integration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.deloitte.devops.config.EnivironmentConfig;
import org.deloitte.devops.jira.integration.JiraEndPoint;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.Transitions;
import org.deloitte.devops.repository.DevopsRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

import junit.swingui.StatusLine;

@Component
public class JenkinsIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(JenkinsIntegration.class);
	@Autowired
	private EnivironmentConfig env;

	@Autowired
	private DevopsRepository repository;

	public void setBuildStatus(List<Issue> issues) {
		for (Issue issue : issues) {
			if (null != issue.getFields().getJobName()
					&& issue.getFields().getStatus().getName().equals(JenkinsEndPoint.ISSUESTATUSINRELEASETOTEST)) {

				checkExistingBuildForIssue(issue, issues);
				if (null == issue.getFields().getBuildStatus()) {
					String jenkinsURL = env.getJenkinsUrl() + "/job/" + issue.getFields().getJobName()
							+ JenkinsEndPoint.jenkisBuildStatusApiUrl;
					String authHeader = env.getJenkinsUserName() + ":" + env.getJenkinsPassword();
					String result = repository.get(jenkinsURL, String.class, null, authHeader);
					LOG.info("Jenkins status - [{}]", result);
					setBuildStatus(issue, result);
				}

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

		if (issue.getFields().getBuildStatus().equals(JenkinsEndPoint.SUCCESSBUILDSTATUS))// Change to success for
																							// testing
		{
			issue.getFields().getStatus().setName(JiraEndPoint.INTEST);
			issue.getFields().getStatus().setDescription(JiraEndPoint.TESTING);
			changeStatusInJira(issue);
		} else if (issue.getFields().getBuildStatus().equals(JenkinsEndPoint.FAILUREBUILDSTATUS)) {
			issue.getFields().getStatus().setName(JiraEndPoint.INDEVELOPMENT);
			issue.getFields().getStatus().setDescription(JiraEndPoint.DEVELOPMENTOFSTORY);
			changeStatusInJira(issue);
		}
	}

	private void changeStatusInJira(Issue issue) {
		// TODO Auto-generated method stub
		List<Transitions> transitionList = getTransitions(issue);
		String transitionId = getTransitionId(transitionList, issue);
		String cred = env.getUserName() + ":" + env.getPassword();
		String str = env.getURL();
		URI uri;
		String result = "";
		try {
			uri = new URI("https", null, env.getURL(), -1,
					JiraEndPoint.UPDATECURRENTISSUETOTESTORINDEVLOPMENT + issue.getId() + JiraEndPoint.UPDATESTSTRING,
					JiraEndPoint.TRANSITIONQUERYSTRING, null);

			HttpClient authClientProj = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(uri);

			String body = formulateBody(transitionId);
			StringEntity stringEntity = new StringEntity(body);
			post.getRequestLine();
			post.setEntity(stringEntity);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");

			post.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode(cred.getBytes())));

			HttpResponse response = authClientProj.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println("HTTP STATUS" + statusLine.getStatusCode());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String formulateBody(String transitionId) {
		// TODO Auto-generated method stub
		return "{\"transition\":{\"id\":" + transitionId + "}}";
	}

	private String getTransitionId(List<Transitions> transitionList, Issue issue) {
		// TODO Auto-generated method stub

		for (Transitions transitions : transitionList) {
			if (issue.getFields().getStatus().getName().equals(transitions.getName())) {
				return transitions.getId();
			}
		}
		return null;
	}

	private List<Transitions> getTransitions(Issue issue) {
		// TODO Auto-generated method stub

		String cred = env.getUserName() + ":" + env.getPassword();
		String str = env.getURL();
		URI uri;
		String result = "";
		try {
			uri = new URI("https", null, env.getURL(), -1,
					JiraEndPoint.GETTRANSITIONS + issue.getId() + JiraEndPoint.TRANSITION, null, null);

			HttpClient authClientProj = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			get.setHeader("Content-type", "application/json");
			get.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode(cred.getBytes())));
			HttpResponse response = authClientProj.execute(get);
			result = EntityUtils.toString(response.getEntity());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!result.equals("")) {
			JSONObject responseJson = new JSONObject(result);
			Gson gson = new Gson();

			List<Transitions> list = Arrays
					.asList(gson.fromJson(responseJson.get("transitions").toString(), Transitions[].class));

			return list;
		}

		return null;
	}

	public void setBuildStatusForIssue(Issue issue) {
		// TODO Auto-generated method stub

		String queryString = JenkinsEndPoint.jenkisBuildStatusApiUrl;// /lastBuild/api/json
		String jenkinsCred = env.getJenkinsUserName() + ":" + env.getJenkinsPassword();
		String str = env.getJenkinsUrl(); // 10.14.224.229:8080/job
		URI uri;
		String result = "";

		try {

			if (null != issue.getFields().getJobName() && issue.getFields().getJobName().length() > 0
					&& issue.getFields().getStatus().getName().equals(JenkinsEndPoint.ISSUESTATUSINRELEASETOTEST)) {

				uri = new URI(env.getJenkinsUrl() + "/job/" + issue.getFields().getJobName() + queryString);

				HttpClient authClientProj = HttpClientBuilder.create().build();
				HttpGet get = new HttpGet(uri);
				get.setHeader("Accept", "application/json");
				get.setHeader("Content-type", "application/json");

				get.setHeader("Authorization",
						"Basic " + new String(Base64.getEncoder().encode(jenkinsCred.getBytes())));
				HttpResponse response = authClientProj.execute(get);
				result = EntityUtils.toString(response.getEntity());
				setBuildStatus(issue, result);

			}
			if (null != issue.getFields().getJobName() && issue.getFields().getJobName().length() > 0
					&& issue.getFields().getStatus().getName().equals(JenkinsEndPoint.ISSUESINDEV)) {

				uri = new URI(env.getJenkinsUrl() + "/job/" + issue.getFields().getJobName() + queryString);

				HttpClient authClientProj = HttpClientBuilder.create().build();
				HttpGet get = new HttpGet(uri);
				get.setHeader("Accept", "application/json");
				get.setHeader("Content-type", "application/json");

				get.setHeader("Authorization",
						"Basic " + new String(Base64.getEncoder().encode(jenkinsCred.getBytes())));
				HttpResponse response = authClientProj.execute(get);
				result = EntityUtils.toString(response.getEntity());
				setBuildStatusDev(issue, result);

			}

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setBuildStatusDev(Issue issue, String result) {
		// TODO Auto-generated method stub
		JSONObject responseJson = new JSONObject(result);
		Gson gson = new Gson();
		String buildStatus = gson.fromJson(responseJson.get("result").toString(), String.class);
		issue.getFields().setBuildStatus(buildStatus);
	}

}
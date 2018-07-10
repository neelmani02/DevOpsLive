package org.deloitte.devops.jira.integration;

import java.util.HashMap;
import java.util.Map;

import org.deloitte.devops.helper.JenkinsJiraHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class JiraIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(JiraIntegration.class);

	@Autowired
	private JenkinsJiraHelper helper;

	public String getAllIssues() {
		Map<String, Object> allIssuesParamMap = new HashMap<>();
		allIssuesParamMap.put("jql", "project=\"DevOps Portal Project\"");
		allIssuesParamMap.put("fields", "id,key,description,summary,creator,status,issuetype,customfield_10201");
		allIssuesParamMap.put("startAt", 0);
		allIssuesParamMap.put("maxResults", 50);
		
		String result = helper.exchangeWithJira(HttpMethod.GET, allIssuesParamMap, null, JiraEndPoint.GENERAL_SEARCH);

		LOG.info("Result - - [{}]", result);
		return result;
	}

	public String getIssue(String id) {
		String result = helper.exchangeWithJira(HttpMethod.GET, null, null, JiraEndPoint.GET_ISSUE, id);
		LOG.info("Result - - [{}]", result);
		return result;

	}
}

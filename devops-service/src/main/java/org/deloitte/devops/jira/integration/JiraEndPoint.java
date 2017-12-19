package org.deloitte.devops.jira.integration;

public interface JiraEndPoint {

	public static final String generalSearch = "/rest/api/2/search";
	public static final String allIssues= "jql=project=\"DevOps Portal Project\"&fields=id,key,description,summary,creator,status,issuetype&startAt=0&maxResults=50";
	public static final String getIssue = "/rest/api/2/issue";
	
}

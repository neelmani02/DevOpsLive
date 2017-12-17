package org.deloitte.devops.jira.integration;

public interface JiraEndPoint {

	public static final String generalSearch = "/rest/api/2/search";
	public static final String allIssues= "jql=project=\"DevOps Portal Project\"&fields=id,key,description,summary,creator,status&startAt=1&maxResults=50";
	
}

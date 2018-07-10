package org.deloitte.devops.jira.integration;

public interface JiraEndPoint {

	public static final String GENERAL_SEARCH = "/rest/api/2/search";
	public static final String allIssues= "jql=project=\"DevOps Portal Project\"&fields=id,key,description,summary,creator,status,issuetype,customfield_10201&startAt=0&maxResults=50";
	public static final String GET_ISSUE = "/rest/api/2/issue/";
	public static final String GET_TRANSITIONS="/rest/api/2/issue/";
	public static final String TRANSITION="/transitions";
	public static final String UPDATE_CURRENT_ISSUE_TO_TEST_OR_IN_DEVLOPMENT="/rest/api/2/issue/";
	public static final String UPDATE_ST_STRING="/transitions";
	public static final String TRANSITION_QUERY_STRING="expand=transitions.fields";
	public static final String INTEST="In Test";
	public static final String TESTING="Testing";
	public static final String IN_DEVELOPMENT="In Development";
	public static final String DEVELOPMENT_OF_STORY="Development of story";
	
}

package org.deloitte.devops.jira.integration;

public interface JiraEndPoint {

	public static final String generalSearch = "/rest/api/2/search";
	public static final String allIssues= "jql=project=\"DevOps Portal Project\"&fields=id,key,description,summary,creator,status,issuetype,customfield_10201&startAt=0&maxResults=50";
	public static final String getIssue = "/rest/api/2/issue";
	public static final String GETTRANSITIONS="/rest/api/2/issue/";
	public static final String TRANSITION="/transitions";
	public static final String UPDATECURRENTISSUETOTESTORINDEVLOPMENT="/rest/api/2/issue/";
	public static final String UPDATESTSTRING="/transitions";
	public static final String TRANSITIONQUERYSTRING="expand=transitions.fields";
	public static final String INTEST="In Test";
	public static final String TESTING="Testing";
	public static final String INDEVELOPMENT="In Development";
	public static final String DEVELOPMENTOFSTORY="Development of story";
}

package org.deloitte.devops.jenkins.integration;

public interface JenkinsEndPoint {

	
	public static final String jenkisBuildStatusApiUrl = "/lastBuild/api/json";
	public static final String ISSUESTATUSINRELEASETOTEST="Release to Test";
	public static final String SUCCESSBUILDSTATUS="SUCCESS";
	public static final String FAILUREBUILDSTATUS="FAILURE";
	public static final String ISSUESINDEV="In Development";
}

package org.deloitte.devops.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnivironmentConfig {
	
	@Value("${jira.url}")
	private String jiraURL;
	@Value("${jira.username}")
	private String jiraUserName;
	@Value("${jira.password}")
	private String jiraPassword;
	
	@Value("${application.userName}")
	private String appUserName;
	@Value("${application.password}")
	private String appPassword;
	

	@Value("${jenkins.url}")
	private String jenkinsUrl;
	@Value("${jenkins.username}")
	private String jenkinsUserName;
	@Value("${jenkins.password}")
	private String jenkinsPassword;
	
	public String getJiraURL() {
		return jiraURL;
	}
	public String getJiraUserName() {
		return jiraUserName;
	}
	public String getJiraPassword() {
		return jiraPassword;
	}
	public String getAppUserName() {
		return appUserName;
	}
	public String getAppPassword() {
		return appPassword;
	}
	public String getJenkinsUrl() {
		return jenkinsUrl;
	}
	public String getJenkinsUserName() {
		return jenkinsUserName;
	}
	public String getJenkinsPassword() {
		return jenkinsPassword;
	}
}

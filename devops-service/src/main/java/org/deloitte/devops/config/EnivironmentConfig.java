package org.deloitte.devops.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnivironmentConfig {
	
	@Value("${jira.url}")
	private String url;
	@Value("${jira.username}")
	private String userName;
	@Value("${jira.password}")
	private String password;
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
	
	public String getURL() {
		return url;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
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

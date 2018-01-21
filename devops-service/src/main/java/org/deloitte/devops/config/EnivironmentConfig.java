package org.deloitte.devops.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnivironmentConfig {
	
	@Value("${jira.url}")
	private String URL;
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
	
	public String getJenkinsUrl() {
		return jenkinsUrl;
	}
	public void setJenkinsUrl(String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}
	public String getJenkinsUserName() {
		return jenkinsUserName;
	}
	public void setJenkinsUserName(String jenkinsUserName) {
		this.jenkinsUserName = jenkinsUserName;
	}
	public String getJenkinsPassword() {
		return jenkinsPassword;
	}
	public void setJenkinsPassword(String jenkinsPassword) {
		this.jenkinsPassword = jenkinsPassword;
	}
	public String getAppUserName() {
		return appUserName;
	}
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	public String getAppPassword() {
		return appPassword;
	}
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}

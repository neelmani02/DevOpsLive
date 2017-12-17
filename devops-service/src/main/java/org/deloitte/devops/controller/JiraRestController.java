package org.deloitte.devops.controller;

import org.deloitte.devops.jira.service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;

public class JiraRestController {
	
	@Autowired
	private JiraService jiraService;

}

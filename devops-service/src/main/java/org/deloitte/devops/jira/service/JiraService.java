package org.deloitte.devops.jira.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.deloitte.devops.jenkins.integration.JenkinsIntegration;
import org.deloitte.devops.jira.integration.JiraIntegration;
import org.deloitte.devops.jira.model.Fields;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.IssueType;
import org.deloitte.devops.jira.model.Status;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class JiraService {

	@Autowired
	private JiraIntegration jiraIntgration;

	@Autowired
	private JenkinsIntegration jenkinsIntegration;
	public List<Issue> getAllIssues() {
		// TODO Auto-generated method stub
		
			String issuesString = jiraIntgration.getAllIssues();
			if(!issuesString.equals("")) {
				JSONObject responseJson = new JSONObject(issuesString);
                Gson gson = new Gson();
                
                List<Issue> list =
                        Arrays.asList(gson.fromJson(responseJson.get("issues").toString(), Issue[].class));
                List<Issue> mainList = new ArrayList<>();
                for (Issue issue : list) {
                	 issue.getFields().setJobName(issue.getFields().getCustomfield_10101());
                	if(issue.getFields().getIssuetype().getName().equals("Story")) {
                		mainList.add(issue);
                	}
                	
					
				}
                jenkinsIntegration.setBuildStatus(mainList);
                return mainList;
			}
		
			return null;
	}
	
	
	public Issue getIssue(String id) {
		// TODO Auto-generated method stub
		    Issue issue =null;
			String issuesString = jiraIntgration.getIssue(id);
			
			
			if(!issuesString.equals("")) {
				JSONObject responseJson = new JSONObject(issuesString);
               
                issue = new Issue();
                issue.setId((String)responseJson.get("id"));
                issue.setKey((String)responseJson.get("key"));
                responseJson=(JSONObject)responseJson.get("fields");
                Fields fields =new Fields();
                fields.setSummary(responseJson.getString("summary"));
                fields.setDescription(responseJson.getString("description"));
              fields.setCustomfield_10101(responseJson.getString("customfield_10101"));
              fields.setJobName(fields.getCustomfield_10101());
                Gson gson = new Gson();
                List<Issue> list =
                        Arrays.asList(gson.fromJson(responseJson.get("subtasks").toString(), Issue[].class));
                fields.setSubtasks(list);
                Status list1 =
                		(Status)(gson.fromJson(responseJson.get("status").toString(), Status.class));
                fields.setStatus(list1);
                IssueType list2 =
                		(IssueType)(gson.fromJson(responseJson.get("issuetype").toString(), IssueType.class));
              
                fields.setIssuetype(list2);
                issue.setFields(fields);
                jenkinsIntegration.setBuildStatusForIssue(issue);
                return issue;
				}
                
                return issue;
		
	}
	
}

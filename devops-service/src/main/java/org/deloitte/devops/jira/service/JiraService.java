package org.deloitte.devops.jira.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.deloitte.devops.jira.integration.JiraIntegration;
import org.deloitte.devops.jira.model.Issue;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class JiraService {

	@Autowired
	private JiraIntegration jiraIntgration;

	public List<Issue> getAllIssues() {
		// TODO Auto-generated method stub
		
			String issuesString = jiraIntgration.getAllIssues();
			if(!issuesString.equals("")) {
				JSONObject responseJson = new JSONObject(issuesString);
                Gson gson = new Gson();
                
                List<Issue> list =
                        Arrays.asList(gson.fromJson(responseJson.get("issues").toString(), Issue[].class));
                return list;
			}
		
			return null;
	}
	
}

/*package org.deloitte.devops.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

@Controller
public class JIRAController {
	
	@RequestMapping(value = "/jira", method = RequestMethod.GET)
	public Issue getIssue(String issueKey) throws Exception
    {
        final URI jiraServerUri = new URI("https://trackotrack.atlassian.net");
        final JiraRestClient restClient = new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(jiraServerUri, "user@domain.com", "password");
        Promise issuePromise = restClient.getIssueClient().getIssue(issueKey);
        return Optional.ofNullable((Issue) issuePromise.claim()).orElseThrow(() -> new Exception("No such issue"));
    }

}*/

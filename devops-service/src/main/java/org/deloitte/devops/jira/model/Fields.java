package org.deloitte.devops.jira.model;

import java.util.List;

public class Fields {
	
	 private String summary;
	 private String description;
	 private User creator;
	 private Status status;
	 private IssueType issuetype;
	 private List<Issue> subtasks;
	public List<Issue> getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(List<Issue> subtasks) {
		this.subtasks = subtasks;
	}
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	 
	
}
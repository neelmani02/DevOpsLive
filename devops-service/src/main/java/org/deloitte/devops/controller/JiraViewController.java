package org.deloitte.devops.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.deloitte.devops.jira.service.JiraService;
import org.deloitte.devops.jira.model.Issue;
import org.deloitte.devops.jira.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JiraViewController{
	
	@Autowired
	private JiraService jiraService;
	
	
	@Value("${welcome.message:test}")
	private String message;

	@RequestMapping("/welcome")
	public String welcome(ModelMap model) {
		model.addAttribute("message", this.message);
		//return new ModelAndView("welcome", "message", this.message);
		List<User> user =new ArrayList<>();
		User us =new User();
		us.setDisplayName("Neel");
		us.setEmailAddress("abc@abc.com");
		user.add(us);
		model.addAttribute("list",user);
          return "welcome";	
	}
	
	@RequestMapping("/getAllIssues")
	public String getAllIssues(ModelMap model) {
		List<Issue> list = jiraService.getAllIssues();
		model.addAttribute("list", list);
		return "list";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
	    return "index";
	}
}

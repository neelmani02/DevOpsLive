package org.deloitte.devops.controller;

import java.util.HashMap;
import java.util.Map;

import org.deloitte.devops.bo.DevopsServiceBO;
import org.deloitte.devops.config.EnivironmentConfig;
import org.deloitte.devops.jira.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	private EnivironmentConfig env;
	private AuthService authService;
	private DevopsServiceBO bo;

	public AuthController(EnivironmentConfig env, AuthService authService, DevopsServiceBO bo) {
		this.env = env;
		this.authService = authService;
		this.bo = bo;
	}

	@GetMapping("/auth")
	public String authUser() {

		String authURL = env.getURL() + "/rest/api/2/search";

		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("jql", "project=\"DevOps Portal Project\"");
		uriVariables.put("fields", "id,key,description,summary,creator,status");
		uriVariables.put("startAt", "1");
		uriVariables.put("maxResults", "50");
		
		LOG.info("Query strings - [{}]", uriVariables);
		
		String result = bo.getAuthResponse(authURL, uriVariables);
		LOG.info("Returned by getAuthResponse - [{}]", result);
		return result;
	}

	@GetMapping("/login")
	public String login(ModelMap model) {
		model.addAttribute("subHeader", "PROJECT LEAD / PROJECT MANAGER LOGIN");
		return "login";
	}

	@GetMapping("/team")
	public String team(ModelMap model) {
		return "team";
	}

	@PostMapping("/doLogin")
	public String doLogin(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password, ModelMap model) {

		boolean validUser = authService.checkApplicationAccess(userName, password);

		if (validUser) {
			model.addAttribute("name", "Monika Goyal");
			model.addAttribute("subHeader", "My Projects");

			return "welcome";
		} else {
			model.addAttribute("subHeader", "Access Denied");
			return "error";

		}
	}

}

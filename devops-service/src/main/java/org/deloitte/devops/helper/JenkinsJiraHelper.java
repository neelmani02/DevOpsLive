package org.deloitte.devops.helper;

import java.util.Base64;
import java.util.Map;

import org.deloitte.devops.config.EnivironmentConfig;
import org.deloitte.devops.repository.DevopsRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class JenkinsJiraHelper {
	private static final Object HTTPS = "https://";
	
	private DevopsRepository repository;
	private EnivironmentConfig env;
	
	public JenkinsJiraHelper(DevopsRepository repository, EnivironmentConfig env) {
		this.repository = repository;
		this.env = env;
	}

	public String getFromJenkins(Map<String, Object> uriVariables, String... urlComponents) {
		String authHeader = new String(
				Base64.getEncoder().encode((env.getJenkinsUserName() + ":" + env.getJenkinsPassword()).getBytes()));
		StringBuilder sb = new StringBuilder();
		sb.append(env.getJenkinsUrl());
		for (String s : urlComponents) {
			sb.append(s);
		}
		return repository.get(sb.toString(), String.class, uriVariables, authHeader);
	}

	public String exchangeWithJira(HttpMethod method, Map<String, Object> uriVariables, Object requestBody,
			String... urlComponents) {
		StringBuilder sb = new StringBuilder();
		sb.append(HTTPS);
		sb.append(env.getJiraURL());
		for (String s : urlComponents) {
			sb.append(s);
		}
		String cred = env.getJiraUserName() + ":" + env.getJiraPassword();
		String authHeader = Base64.getEncoder().encodeToString(cred.getBytes());

		if (HttpMethod.GET.equals(method)) {
			return repository.get(sb.toString(), String.class, uriVariables, authHeader);
		} else if (HttpMethod.POST.equals(method)) {
			return repository.post(sb.toString(), String.class, requestBody, authHeader);
		}
		return null;
	}
}

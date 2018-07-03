package org.deloitte.devops.bo;

import java.util.Map;

import org.deloitte.devops.repository.DevopsRepository;
import org.springframework.stereotype.Component;

@Component
public class DevopsServiceBO {

	private DevopsRepository repository;
	
	public DevopsServiceBO(DevopsRepository repository) {
		this.repository = repository;
	}

	public String getAuthResponse(String url, Map<String, ?> uriVariables) {
		return repository.get(url, String.class, uriVariables);
	}
}

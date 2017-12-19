package org.deloitte.devops.jira.integration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.deloitte.devops.config.EnivironmentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JiraIntegration {

	
	@Autowired
	private EnivironmentConfig env;
	
	public String getAllIssues()  {
		// TODO Auto-generated method stub
		
		String queryString =JiraEndPoint.allIssues;
        String cred = env.getUserName()+":"+env.getPassword();
        String str = env.getURL();
        URI uri;
        String result = "";
		try {
			uri = new URI("https", null, env.getURL(), -1, JiraEndPoint.generalSearch , queryString, null);
		
        HttpClient authClientProj = HttpClientBuilder.create().build(); 
        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-type", "application/json");
        //get.setHeader("-u", "neelsindwani@gmail.com:Nov@2017ns");
        get.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode(cred.getBytes())));
        HttpResponse response = authClientProj.execute(get);
        result = EntityUtils.toString(response.getEntity());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
	
	}

	public String getIssue(String id) {
		// TODO Auto-generated method stub
		
		//String queryString =JiraEndPoint.getIssue +"/"+id;
        String cred = env.getUserName()+":"+env.getPassword();
        String str = env.getURL();
        URI uri;
        String result = "";
		try {
			uri = new URI("https", null, env.getURL(), -1, JiraEndPoint.getIssue +"/"+id,"", null);
		
        HttpClient authClientProj = HttpClientBuilder.create().build(); 
        HttpGet get = new HttpGet(uri);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-type", "application/json");
        //get.setHeader("-u", "neelsindwani@gmail.com:Nov@2017ns");
        get.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode(cred.getBytes())));
        HttpResponse response = authClientProj.execute(get);
        result = EntityUtils.toString(response.getEntity());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
	
	}
}

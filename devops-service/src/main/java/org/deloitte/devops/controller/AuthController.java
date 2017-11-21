package org.deloitte.devops.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@RestController
public class AuthController {
	
/*static final Properties properties = new Properties();
	
	static
	{
		try 
		{
			properties.load(AuthController.class.getResourceAsStream("/WEB-INF/custom.properties"));
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}*/
	

	@RequestMapping(value = "/auth", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String authUser() throws ClientProtocolException, IOException, ParserConfigurationException,
			SAXException, XPathExpressionException, JSONException {
		
		
		
		String authUrl = "https://trackotrack.atlassian.net/rest/auth/1/session";

		HttpClient authClient = HttpClientBuilder.create().build(); 
		HttpPost post = new HttpPost(authUrl);
		
		JSONObject jObject = new JSONObject();
		jObject.put("username", "neelsindwani@gmail.com");
		jObject.put("password", "Nov@2017ns");
		
		String userandpass = jObject.toString();

		HttpEntity entity = new ByteArrayEntity(userandpass.getBytes());
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		HttpResponse response = authClient.execute(post);
		String result = EntityUtils.toString(response.getEntity());
System.out.println("rest.."+result);

		return result;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
	    return "index";
	}
	

}

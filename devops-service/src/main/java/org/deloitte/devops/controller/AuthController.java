package org.deloitte.devops.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import javax.ws.rs.Produces;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.deloitte.devops.config.EnivironmentConfig;
import org.deloitte.devops.jira.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@Controller
public class AuthController {
	
@Autowired
private EnivironmentConfig env;
@Autowired	
private AuthService authService;

@RequestMapping(method = RequestMethod.GET,value = "/auth")
public @ResponseBody String authUser() throws ClientProtocolException, IOException, ParserConfigurationException,
                           SAXException, XPathExpressionException, JSONException, URISyntaxException {
             
             
             
             String authUrl = "https://trackcumtrack.atlassian.net/rest/auth/1/session";

             HttpClient authClient = HttpClientBuilder.create().build(); 
             HttpPost post = new HttpPost(authUrl);
             
             JSONObject jObject = new JSONObject();
             jObject.put("username", "neelmani02@gmail.com");
             jObject.put("password", "Dec@2017ns");
             
             String userandpass = jObject.toString();

             HttpEntity entity = new ByteArrayEntity(userandpass.getBytes());
             /*post.setHeader("Accept", "application/json");
             post.setHeader("Content-type", "application/json");
             post.setEntity(entity);
             HttpResponse response = authClient.execute(post);
             String result = EntityUtils.toString(response.getEntity());
             System.out.println("rest.."+result);       */   
             
             String projsURL = "https://devopslive.atlassian.net/rest/api/2/search?"
             		+ "jql=project=\"DevOps+%20+Portal+%20+Project\"+%26+fields=id,key,description,summary,creator,status+%26+startAt=1+%26+maxResults=50";
             String queryString ="jql=project=\"DevOps Portal Project\"&fields=id,key,description,summary,creator,status&startAt=1&maxResults=50";
             String cred = env.getUserName()+":"+env.getPassword();
             String str = env.getURL();
             URI uri = new URI("https", null, env.getURL(), -1, "/rest/api/2/search", queryString, null);
             HttpClient authClientProj = HttpClientBuilder.create().build(); 
             HttpGet get = new HttpGet(uri);
            
             
             
             get.setHeader("Accept", "application/json");
             get.setHeader("Content-type", "application/json");
             //get.setHeader("-u", "neelsindwani@gmail.com:Nov@2017ns");
             get.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode(cred.getBytes())));
             HttpResponse responseProjs = authClientProj.execute(get);
             String projsResult = EntityUtils.toString(responseProjs.getEntity());
              System.out.println("projsResult.."+projsResult);

             return projsResult;
}

@RequestMapping("/login")
public  String login()  {
             
  return "login";        
}
@RequestMapping(value= "/doLogin", method = RequestMethod.POST)
public  String doLogin(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password,ModelMap model)  {
         
	
	boolean validUser= authService.checkApplicationAccess(userName,password);

	if(validUser) {
		model.addAttribute("name", "Jon Doe");
				return "welcome";
	}
	else
				return "error";        
}

}


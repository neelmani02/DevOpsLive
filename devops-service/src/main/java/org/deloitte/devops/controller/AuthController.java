package org.deloitte.devops.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@RestController
public class AuthController {
	

@RequestMapping(method = RequestMethod.GET,value = "/auth")
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
             /*post.setHeader("Accept", "application/json");
             post.setHeader("Content-type", "application/json");
             post.setEntity(entity);
             HttpResponse response = authClient.execute(post);
             String result = EntityUtils.toString(response.getEntity());
             System.out.println("rest.."+result);       */   
             
             String projsURL = "https://trackotrack.atlassian.net/rest/api/2/issue/DL-10";
             
             HttpClient authClientProj = HttpClientBuilder.create().build(); 
             HttpGet get = new HttpGet(projsURL);
             
             
             get.setHeader("Accept", "application/json");
             get.setHeader("Content-type", "application/json");
             //get.setHeader("-u", "neelsindwani@gmail.com:Nov@2017ns");
             get.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode("neelsindwani@gmail.com:Nov@2017ns".getBytes())));
             HttpResponse responseProjs = authClientProj.execute(get);
             String projsResult = EntityUtils.toString(responseProjs.getEntity());
              System.out.println("projsResult.."+projsResult);

             return projsResult;
}

@RequestMapping(value = "/index", method = RequestMethod.GET)
public String index() {
    return "index";
}


}


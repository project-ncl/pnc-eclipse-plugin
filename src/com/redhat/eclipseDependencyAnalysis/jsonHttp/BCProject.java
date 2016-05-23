package com.redhat.eclipseDependencyAnalysis.jsonHttp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The Class BCProject handles creating of a new project for Build Configuration. Also gets an id of an existing project.
 */
public class BCProject {

	public static String url = "http://ncl-demo.stage.engineering.redhat.com/pnc-rest/rest/projects";
	
	public static String id;
	
	/**
	 * Json.
	 *
	 * @param fieldName the field name
	 * @param projectName the project name
	 * @return the JSON object
	 */
	public static JSONObject json(String fieldName, String projectName){
		JSONObject projectJSON = new JSONObject();
		projectJSON.put(fieldName, projectName);
		System.out.println("String of project name jarray to be sent: " + projectJSON);
		return projectJSON;
	}

	/**
	 * Http method tries to create a new project. 
	 *
	 * @param body the body
	 * @param token the token
	 */
	public static void http(String body, String token) {
		System.out.println("crete project http method called");
    	
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.addHeader("accept", "application/json");
            request.addHeader("authorization", "Bearer " + token);
            request.addHeader("cache-control", "no-cache");
            request.setEntity(params);
            HttpResponse result = httpClient.execute(request);
           
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            System.out.println(json);
            System.out.println("lets try to parse");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        System.out.println("PNC response: " + obj.toString());
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println(obj.toString());
                }
            } catch (Exception e) {
            	result.toString();
            	System.out.println("probem with parsing json project name post");
                e.printStackTrace();
            }

        } catch (IOException ex) {
        	System.out.println("project Name exists");
        	ex.printStackTrace();
        }
    }
	
	/**
	 * Gets the id of an existing project.
	 *
	 * @param body the body
	 * @param token the token
	 * @return the id
	 */
	public static String getId(String body, String token) {
		System.out.println("crete project http method called");
		String secondURL = url + "?q=name%3D%3D" + body;
    	
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(secondURL);
            StringEntity params = new StringEntity(body);
            request.addHeader("accept", "application/json");
            request.addHeader("authorization", "Bearer " + token);
            request.addHeader("cache-control", "no-cache");
            HttpResponse result = httpClient.execute(request);
           
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            System.out.println(json);
            System.out.println("lets try to parse");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        System.out.println("Project info: " + obj.toString());
                        id = obj.get("id").toString();
                        return id;
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println(obj.toString());
                    System.out.println("Project info: " + obj.toString());
                    id = ((JSONObject) ((org.json.simple.JSONArray)obj.get("content")).get(0)).get("id").toString();
                    return id;
                }
            } catch (Exception e) {
            	result.toString();
            	System.out.println("probem with parsing json project name post");
                e.printStackTrace();
                return null;
            }
            

        } catch (IOException ex) {
        	System.out.println("project Name exists");
        	ex.printStackTrace();
        	return null;
        }
		return null;
        
	}
	
}

package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Project {

	private static String URL_GET_PROJECTS = "{SERVER}/rest/api/2/issue/createmeta";
	private static String TEST_ISSSUETYPE_NAME = "Test";

	
	
	public static Long getProjectIdByName(String projectName, String hostNameWithProtocol, String userName, String password) {

		Long projectId = 0L;

		HttpClientContext context = getClientContext(hostNameWithProtocol, userName, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(new HttpGet(hostNameWithProtocol + "/flex/services/rest/latest/project?name=" + URLEncoder.encode(projectName, "utf-8")), context);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONArray projArray = new JSONArray(string);
				List<Long> projectIdList = new ArrayList<Long>();
				for(int i = 0; i < projArray.length(); i++) {
					Long id = projArray.getJSONObject(i).getLong("id");
					projectIdList.add(id);
				}
				
				Collections.sort(projectIdList);
				projectId = projectIdList.get(0);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return projectId;
	}
	
	public static Map<Long, String> getAllProjects(String hostNameWithProtocol, String userName, String password) {


		Map<Long, String> projects = new TreeMap<Long, String>();
		
		HttpClientContext context = getClientContext(hostNameWithProtocol, userName, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		
		final String url = URL_GET_PROJECTS.replace("{SERVER}", hostNameWithProtocol);
		try {
			response = client.execute(new HttpGet(url), context);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONObject projObject = new JSONObject(string);
				JSONArray projArray = projObject.getJSONArray("projects");
				for(int i = 0; i < projArray.length(); i++) {
					JSONObject project = projArray.getJSONObject(i);
					Long id = project.getLong("id");
					String projName = project.getString("name");
					JSONArray issueTypes = project.getJSONArray("issuetypes");
					
					boolean issueTypeTesstExists = false;
					for (int j = 0; j < issueTypes.length(); j++) {
						JSONObject issueType = issueTypes.getJSONObject(j);
						String issueTypeName = issueType.getString("name");
						
						if (issueTypeName.trim().equalsIgnoreCase(TEST_ISSSUETYPE_NAME)) {
							issueTypeTesstExists = true;
							break;
						}
					}
					
					if(!issueTypeTesstExists) {
						continue;
					}
					projects.put(id, projName);
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		} else {
			
			projects.put(0L, "No Project");
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return projects;
	}
	
	
	private static HttpClientContext getClientContext(String hostAddressWithProtocol, String userName, String password) {
		URL url;
		HttpClientContext context = null;
		try {
			url = new URL(hostAddressWithProtocol);
			HttpHost targetHost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(userName, password));
			
			AuthCache authCache = new BasicAuthCache();
			authCache.put(targetHost, new BasicScheme());
			
			context = HttpClientContext.create();
			context.setCredentialsProvider(credsProvider);
			context.setAuthCache(authCache);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return context;
	}
	
}

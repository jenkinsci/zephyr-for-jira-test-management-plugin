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
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class Release {

	private static String URL_GET_RELEASES = "{SERVER}/rest/api/2/project/{PROJ_ID}/versions?expand";

	
	public static Long getReleaseIdByNameProjectId(String releaseName, Long projectId, String hostAddressWithProtocol, String userName, String password) {

		Long releaseId = 0L;

		HttpClientContext context = getClientContext(hostAddressWithProtocol, userName, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(new HttpGet(hostAddressWithProtocol + "/flex/services/rest/latest/release?name=" + URLEncoder.encode(releaseName, "utf-8") + "&project.id=" + projectId), context);
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
				JSONArray releaseArray = new JSONArray(string);
				List<Long> releaseIdList = new ArrayList<Long>();
				for(int i = 0; i < releaseArray.length(); i++) {
					Long id = releaseArray.getJSONObject(i).getLong("id");
					releaseIdList.add(id);
				}
				
				Collections.sort(releaseIdList);
				releaseId = releaseIdList.get(0);
				
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
	
		return releaseId;
	}
	
	public static Map<Long, String> getAllReleasesByProjectID(Long projectID, String hostAddressWithProtocol, String userName, String password) {


		Map<Long, String> releases = new TreeMap<Long, String>();
		
		HttpClientContext context = getClientContext(hostAddressWithProtocol, userName, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		
		final String url = URL_GET_RELEASES.replace("{SERVER}", hostAddressWithProtocol).replace("{PROJ_ID}", projectID+"");
		try {
			response = client.execute(new HttpGet(url), context);
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
				for(int i = 0; i < projArray.length(); i++) {
					Long id = projArray.getJSONObject(i).getLong("id");
					String projName = projArray.getJSONObject(i).getString("name");
					releases.put(id, projName);
				}
				
				
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
		releases.put(-1L, "Unscheduled");
		return releases;
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

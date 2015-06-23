package com.getzephyr.jenkins.zfj.utils.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.getzephyr.jenkins.zfj.model.ZephyrConfigModel;

public class Cycle {

    public static final long NEW_CYCLE_KEY_IDENTIFIER = 1000000000L;

	private static String URL_GET_CYCLES = "{SERVER}/rest/zapi/latest/cycle?versionId=";
	private static String URL_CREATE_CYCLES = "{SERVER}/rest/zapi/latest/cycle?versionId={versionId}&projectId={projectId}";
	private static String URL_DELETE_CYCLE = "{SERVER}/rest/zapi/latest/cycle/{id}";

	
	public static Long getCycleIdByCycleNameAndReleaseId(String cycleName, Long releaseId, String hostAddressWithProtocol, String userName, String password) {

		Long cycleId = 0L;

		HttpClientContext context = getClientContext(hostAddressWithProtocol, userName, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(new HttpGet(hostAddressWithProtocol + "/flex/services/rest/latest/cycle?name=" + URLEncoder.encode(cycleName, "utf-8") + "&releaseId=" + releaseId), context);
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
				JSONArray cycleArray = new JSONArray(string);
				List<Long> cycleIdList = new ArrayList<Long>();
				for(int i = 0; i < cycleArray.length(); i++) {
					Long id = cycleArray.getJSONObject(i).getLong("id");
					cycleIdList.add(id);
				}
				
				Collections.sort(cycleIdList);
				cycleId = cycleIdList.get(0);
				
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
	
		return cycleId;
	}
	
	public static Map<Long, String> getAllCyclesByReleaseID(Long releaseID, String hostAddressWithProtocol, String userName, String password) {


		Map<Long, String> cycles = new TreeMap<Long, String>();
		
		HttpClientContext context = getClientContext(hostAddressWithProtocol, userName, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		
		final String url = URL_GET_CYCLES.replace("{SERVER}", hostAddressWithProtocol) + releaseID;
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
				JSONObject projObj = new JSONObject(string);
				for(int i = 0; i < projObj.length(); i++) {
					Iterator<String> keys = projObj.keys();
					
					while (keys.hasNext()) {
						String key = (String) keys.next();
						if (!key.trim().equals("-1") && !key.trim().equals("recordsCount")) {
							JSONObject cycleObject = projObj.getJSONObject(key);
							String cycleName = cycleObject.getString("name");
							long id = Long.parseLong(key);
							cycles.put(id, cycleName);
						}
					}
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		} else {
			
			cycles.put(0L, "No Cycle");
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return cycles;
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
			
			// Add AuthCache to the execution context
			context = HttpClientContext.create();
			context.setCredentialsProvider(credsProvider);
			context.setAuthCache(authCache);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return context;
	}
	
	
	
	public static Long createCycle(ZephyrConfigModel zephyrData) {

		Long cycleId = 0L;

		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			String createCycleURL = URL_CREATE_CYCLES.replace("{SERVER}", hostAddressWithProtocol).replace("{versionId}", zephyrData.getReleaseId()+"").replace("{projectId}", zephyrData.getZephyrProjectId()+"");
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E dd, yyyy hh:mm a");
			String dateFormatForCycleCreation = sdf.format(date);

			JSONObject jObject = new JSONObject();
			String cycleName = "Cycle_" + dateFormatForCycleCreation;
			
			if(zephyrData.getCycleId() != NEW_CYCLE_KEY_IDENTIFIER) {
				cycleName = zephyrData.getCycleName();
			}
			
			jObject.put("name", cycleName);
			jObject.put("projectId", zephyrData.getZephyrProjectId());
			jObject.put("versionId", zephyrData.getReleaseId());
			
			StringEntity se = new StringEntity(jObject.toString());
			
			HttpPost createCycleRequest = new HttpPost(createCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = client.execute(createCycleRequest, context);
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
				JSONObject cycleObj = new JSONObject(string);
				cycleId = cycleObj.getLong("id");
				
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
	
		return cycleId;
	}
	
	public static Long deleteCycle(ZephyrConfigModel zephyrData) {

		Long cycleId = 0L;

		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			String deleteCycleURL = URL_DELETE_CYCLE.replace("{SERVER}", hostAddressWithProtocol).replace("{id}", zephyrData.getCycleId()+"");
			
			HttpDelete createCycleRequest = new HttpDelete(deleteCycleURL);
			
			response = client.execute(createCycleRequest, context);
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

			
		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return cycleId;
	}
}

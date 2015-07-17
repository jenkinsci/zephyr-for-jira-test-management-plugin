package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.propertyeditors.URLEditor;

import com.thed.zephyr.jenkins.model.ZephyrConfigModel;

public class Cycle {

    public static final long NEW_CYCLE_KEY_IDENTIFIER = 1000000000L;

	private static String URL_GET_CYCLES = "{SERVER}/rest/zapi/latest/cycle?projectId={projectId}&versionId={versionId}";
	private static String URL_CREATE_CYCLES = "{SERVER}/rest/zapi/latest/cycle";
	private static String URL_DELETE_CYCLE = "{SERVER}/rest/zapi/latest/cycle/{id}";

	public static Long createCycle(ZephyrConfigModel zephyrData) {

		Long cycleId = 0L;

		HttpResponse response = null;
		try {
			String createCycleURL = URL_CREATE_CYCLES.replace("{SERVER}", zephyrData.getRestClient().getUrl());
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E dd, yyyy hh:mm a");
			String dateFormatForCycleCreation = sdf.format(date);

			JSONObject jObject = new JSONObject();
			String cycleName = zephyrData.getCyclePrefix() + dateFormatForCycleCreation;
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yy");
			String startDate = sdf1.format(date);
			
			GregorianCalendar gCal = new GregorianCalendar();

			if (zephyrData.getCycleDuration().trim().equalsIgnoreCase("30 days")) {
				gCal.add(Calendar.DAY_OF_MONTH, +29);
			} else if (zephyrData.getCycleDuration().trim().equalsIgnoreCase("7 days")) {
				gCal.add(Calendar.DAY_OF_MONTH, +6);
			}

			String endDate = sdf1.format(gCal.getTime());
			
			jObject.put("name", cycleName);
			jObject.put("projectId", zephyrData.getZephyrProjectId());
			jObject.put("versionId", zephyrData.getVersionId());
			jObject.put("startDate", startDate);
			jObject.put("endDate", endDate);
			
			StringEntity se = new StringEntity(jObject.toString(), "utf-8");
			
			HttpPost createCycleRequest = new HttpPost(createCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = zephyrData.getRestClient().getHttpclient().execute(createCycleRequest, zephyrData.getRestClient().getContext());
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
			
			
		} else if(statusCode == 405) {

			try {
				throw new ClientProtocolException("ZAPI plugin license is invalid"
						+ statusCode);
			} catch (ClientProtocolException e) {
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

		HttpResponse response = null;
		try {
			String deleteCycleURL = URL_DELETE_CYCLE.replace("{SERVER}", zephyrData.getRestClient().getUrl()).replace("{id}", zephyrData.getCycleId()+"");
			
			HttpDelete createCycleRequest = new HttpDelete(deleteCycleURL);
			
			response = zephyrData.getRestClient().getHttpclient().execute(createCycleRequest, zephyrData.getRestClient().getContext());
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
	
	public static Map<Long, String> getAllCyclesByVersionId(long versionId, RestClient restClient, String projectId) {


		Map<Long, String> cycles = new TreeMap<Long, String>();
		
		HttpResponse response = null;
		
		final String url = URL_GET_CYCLES.replace("{SERVER}", restClient.getUrl()).replace("{projectId}", projectId).replace("{versionId}", versionId+"");
		try {
			response = restClient.getHttpclient().execute(new HttpGet(url), restClient.getContext());
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
						if (/*!key.trim().equals("-1") && */!key.trim().equals("recordsCount")) {
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
			
			
		}
	
		return cycles;
	}
}

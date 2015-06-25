package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerInfo {
	
	private static String URL_GET_PROJECTS = "{SERVER}/rest/api/2/project?expand";
	private static String USER_NOT_FOUND_MSG = "username and password are incorrect";
	private static String URL_GET_ISSUETYPES = "{SERVER}/rest/api/2/issuetype";
	private static String TEST_ISSSUETYPE_NAME = "Test";
	private static long ISSUE_TYPE_ID = 10100L;
	
	private static String URL_GET_USERS = "{SERVER}/rest/api/2/user?username=";
	private static String INVALID_USER_CREDENTIALS = "Invalid user credentials";
	private static String INVALID_USER_ROLE = "User role should be manager or lead";
	private static String VALID_USER_ROLE = "User authentication is successful";



	public static boolean findServerAddressIsValidZephyrURL(String hostNameWithProtocol) {
		boolean status = false;
		CloseableHttpClient httpclient = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			        builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(
			        sslsf).build();
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		if(httpclient == null) {
			return status;
		}
		HttpClientContext context = getClientContext(hostNameWithProtocol, "test.manage", "test.ma");
		HttpResponse response = null;
		try {
			String constructedURL = URL_GET_PROJECTS.replace("{SERVER}", hostNameWithProtocol);
			
			response = httpclient.execute(new HttpGet(constructedURL));
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
				if (string.contains("[]")) return true;
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (statusCode == 401) {
			return false;
		}  else if (statusCode == 404) {
			return false;
		} else{
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return status;
	
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
	
	public static long findTestIssueTypeId(String hostNameWithProtocol, String userName, String password) {
		long status = ISSUE_TYPE_ID;
		CloseableHttpClient httpclient = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			        builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(
			        sslsf).build();
			
			
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		if(httpclient == null) {
			return status;
		}
		HttpClientContext context = getClientContext(hostNameWithProtocol, userName, password);
		HttpResponse response = null;
		try {
			String constructedURL = URL_GET_ISSUETYPES.replace("{SERVER}", hostNameWithProtocol);
			
			HttpGet getRequest = new HttpGet(constructedURL);
			getRequest.addHeader("Content-Type", "application/json");
			getRequest.addHeader("Accept-Encoding", "gzip, deflate, sdch");

			response = httpclient.execute(getRequest, context);
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
				string = EntityUtils.toString(entity, "utf-8");
				
				JSONArray jArr = new JSONArray(string);
				
				for (int i = 0; i < jArr.length(); i++) {
					JSONObject jObj = (JSONObject) jArr.getJSONObject(i);
					if (jObj.getString("name").trim().equals(TEST_ISSSUETYPE_NAME)) {
						long testIssueTypeId = jObj.getLong("id");
						return testIssueTypeId;
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (statusCode == 401 || statusCode == 404) {
			return status;
		}  else{
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return status;
	
	}
	
	
	public static boolean validateCredentials(String zephyrURL,
			String username, String password) {
		

		boolean status = true;
		
		CloseableHttpClient httpclient = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			        builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(
			        sslsf).build();
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		HttpClientContext context = getClientContext(zephyrURL, username, password);
		HttpResponse response = null;
		try {
			String constructedURL = URL_GET_USERS.replace("{SERVER}", zephyrURL) + username;
			response = httpclient.execute(new HttpGet(constructedURL), context);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode == 401) {
			status = false;
		}
		return status;
	
	
	}
	
}

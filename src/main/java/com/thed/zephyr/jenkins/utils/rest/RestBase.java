package com.thed.zephyr.jenkins.utils.rest;

public interface RestBase {

	static final String ZFJC_SERVER = "{ZFJC_SERVER}";
	static final String VERSIONID = "{VERSIONID}";
	static final String PROJECTID = "{PROJECTID}";
	static final String CYCLEID = "{CYCLEID}";
	static final String PAGINATION_OFFSET = "{PAGINATION_OFFSET}";
	
	static final String ATLASSIAN_NET = "atlassian.net";
	static final String JSON_ARRAY_START_CHAR = "[";
	static final String JSON_ARRAY_END_CHAR = "]";
	static final String JSON_OBJECT_START_CHAR = "{";
	static final String JSON_OBJECT_END_CHAR = "}";
	static final String HEADER_ZFJC_ACCESS_KEY = "zapiAccessKey";
	static final String HEADER_AUTHORIZATION = "Authorization";
	static final String HEADER_CONTENT_TYPE = "Content-Type";
	static final String CONTENT_TYPE_JSON = "application/json";
	static final String CONTENT_TYPE_TEXT = "text/plain";
	
	static final String HTTP_REQUEST_METHOD_GET = "GET";
	static final String HTTP_REQUEST_METHOD_POST = "POST";
	
	static String URL_ZFJC_GET_CYCLES_BY_PROJECT_AND_VERSION = ZFJC_SERVER + "/public/rest/api/1.0/cycles/search?versionId=" + VERSIONID + "&projectId=" + PROJECTID;
	static String URL_ZFJC_CREATE_CYCLE = ZFJC_SERVER + "/public/rest/api/1.0/cycle";
	static String URL_ZFJC_DELETE_CYCLE = ZFJC_SERVER + "/public/rest/api/1.0/cycle/" + CYCLEID + "?versionId=" + VERSIONID + "&projectId=" + PROJECTID;
	
	static final String URL_ZFJC_ASSIGN_TESTS = ZFJC_SERVER + "/public/rest/api/1.0/executions/add/cycle/" + CYCLEID;
	static final String URL_ZFJC_CREATE_EXECUTIONS_URL = ZFJC_SERVER + "/public/rest/api/1.0/executions/search/cycle/" + CYCLEID + "?projectId=" + PROJECTID + "&versionId=" + VERSIONID + "&offset=" + PAGINATION_OFFSET + "&action=expand";
	static final String URL_ZFJC_EXECUTE_TEST = ZFJC_SERVER + "/public/rest/api/1.0/executions";


}

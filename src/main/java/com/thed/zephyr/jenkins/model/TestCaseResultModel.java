package com.thed.zephyr.jenkins.model;

import com.thed.service.soap.RemoteTestcase;

public class TestCaseResultModel {

	private RemoteTestcase remoteTestcase;
	private Boolean passed;
	private String testCase;
	private String testCaseName;

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getTestCase() {
		return testCase;
	}

	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}

	public RemoteTestcase getRemoteTestcase() {
		return remoteTestcase;
	}

	public void setRemoteTestcase(RemoteTestcase remoteTestcase) {
		this.remoteTestcase = remoteTestcase;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

}

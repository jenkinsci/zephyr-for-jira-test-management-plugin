package com.thed.zephyr.jenkins.model;

public class TestCaseResultModel {

	private Boolean passed;
	private String testCase;
	private String testCaseName;
	private String testCaseError;

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

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}
	
	public String getError(){
		return testCaseError;
	}
	
	public void setError(String error){
		this.testCaseError = error;
	}
}

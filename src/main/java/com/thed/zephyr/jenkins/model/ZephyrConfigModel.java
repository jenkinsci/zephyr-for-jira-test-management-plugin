package com.thed.zephyr.jenkins.model;

import java.util.List;

import com.thed.zephyr.jenkins.utils.rest.RestClient;

public class ZephyrConfigModel {

	private List<TestCaseResultModel> testcases;
	private Long zephyrProjectId;
	private Long versionId;
	private Long cycleId;
	private String cyclePrefix;
	private String cycleSuffix;
	private long testIssueTypeId;
	private String cycleName;
	private String cycleDuration;
	private RestClient restClient;
	private boolean zfjClud;
	private String cycleIdZfjCloud;
	private int builNumber;


	public long getTestIssueTypeId() {
		return testIssueTypeId;
	}

	public void setTestIssueTypeId(long testIssueTypeId) {
		this.testIssueTypeId = testIssueTypeId;
	}

	public String getCyclePrefix() {
		return cyclePrefix;
	}
	public String getCycleSuffix() {
		return cycleSuffix;
	}
	
	public void setCyclePrefix(String cyclePrefix) {
		this.cyclePrefix = cyclePrefix;
	}
	
	public void setCycleSuffix(String cycleSuffix) {
		this.cycleSuffix = cycleSuffix;
	}

	public List<TestCaseResultModel> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<TestCaseResultModel> testcases) {
		this.testcases = testcases;
	}

	public Long getZephyrProjectId() {
		return zephyrProjectId;
	}

	public void setZephyrProjectId(Long zephyrProjectId) {
		this.zephyrProjectId = zephyrProjectId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getCycleName() {
		return cycleName;
	}

	public String getCycleDuration() {
		return cycleDuration;
	}

	public void setCycleDuration(String cycleDuration) {
		this.cycleDuration = cycleDuration;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public RestClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public boolean isZfjClud() {
		return zfjClud;
	}

	public void setZfjClud(boolean zfjClud) {
		this.zfjClud = zfjClud;
	}

	public String getCycleIdZfjCloud() {
		return cycleIdZfjCloud;
	}

	public void setCycleIdZfjCloud(String cycleIdZfjCloud) {
		this.cycleIdZfjCloud = cycleIdZfjCloud;
	}

	public int getBuilNumber() {
		return builNumber;
	}

	public void setBuilNumber(int builNumber) {
		this.builNumber = builNumber;
	}

}
package com.thed.zephyr.jenkins.model;

import java.util.List;
import java.util.Set;

import com.thed.zephyr.jenkins.utils.rest.RestClient;

public class ZephyrConfigModel {

	private List<TestCaseResultModel> testcases;
	private String zephyrURLString;
	private Long zephyrProjectId = 1L;
	private Long versionId = 1L;
	private Long cycleId = 1L;
	private Set<String> packageNames;
	private String cyclePrefix;
	private boolean createPackage;
	private String zephyrURL;
	private long testIssueTypeId;
	private String cycleName;
	private String cycleDuration;
	private RestClient restClient;


	public long getTestIssueTypeId() {
		return testIssueTypeId;
	}

	public void setTestIssueTypeId(long testIssueTypeId) {
		this.testIssueTypeId = testIssueTypeId;
	}

	public String getZephyrURL() {
		return zephyrURL;
	}

	public void setZephyrURL(String zephyrURL) {
		this.zephyrURL = zephyrURL;
	}

	public boolean isCreatePackage() {
		return createPackage;
	}

	public void setCreatePackage(boolean createPackage) {
		this.createPackage = createPackage;
	}

	public String getCyclePrefix() {
		return cyclePrefix;
	}

	public void setCyclePrefix(String cyclePrefix) {
		this.cyclePrefix = cyclePrefix;
	}

	public List<TestCaseResultModel> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<TestCaseResultModel> testcases) {
		this.testcases = testcases;
	}

	public String getZephyrURLString() {
		return zephyrURLString;
	}

	public void setZephyrURLString(String zephyrURLString) {
		this.zephyrURLString = zephyrURLString;
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

	public Set<String> getPackageNames() {
		return packageNames;
	}

	public void setPackageNames(Set<String> packageNames) {
		this.packageNames = packageNames;
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

}
package com.thed.zephyr.jenkins.model;

import java.util.List;
import java.util.Set;

public class ZephyrConfigModel {

	private List<TestCaseResultModel> testcases;
	private String zephyrURLString;
	private Long zephyrProjectId = 1L;
	private Long releaseId = 1L;
	private Long cycleId = 1L;
	private String userName = "test.manager";
	private String password = "test.manager";
	private String zephyrServerIPAddress = "127.0.0.1";
	private int zephyrServerPort = 80;
	private Set<String> packageNames;
	private String cyclePrefix;
	private boolean createPackage;
	private String zephyrURL;
	private long testIssueTypeId;
	private String cycleName;
	private String cycleDuration;

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

	public Long getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZephyrServerIPAddress() {
		return zephyrServerIPAddress;
	}

	public void setZephyrServerIPAddress(String zephyrServerIPAddress) {
		this.zephyrServerIPAddress = zephyrServerIPAddress;
	}

	public int getZephyrServerPort() {
		return zephyrServerPort;
	}

	public void setZephyrServerPort(int zephyrServerPort) {
		this.zephyrServerPort = zephyrServerPort;
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

}
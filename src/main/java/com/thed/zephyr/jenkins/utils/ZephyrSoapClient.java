package com.thed.zephyr.jenkins.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.thed.zephyr.jenkins.model.TestCaseResultModel;
import com.thed.zephyr.jenkins.model.ZephyrConfigModel;
import com.thed.zephyr.jenkins.reporter.ZfjReporter;
import com.thed.service.soap.RemoteCriteria;
import com.thed.service.soap.RemoteCycle;
import com.thed.service.soap.RemoteData;
import com.thed.service.soap.RemoteFieldValue;
import com.thed.service.soap.RemoteNameValue;
import com.thed.service.soap.RemotePhase;
import com.thed.service.soap.RemoteProject;
import com.thed.service.soap.RemoteRelease;
import com.thed.service.soap.RemoteReleaseTestSchedule;
import com.thed.service.soap.RemoteRepositoryTree;
import com.thed.service.soap.RemoteRepositoryTreeTestcase;
import com.thed.service.soap.RemoteTestResult;
import com.thed.service.soap.RemoteTestcase;
import com.thed.service.soap.SearchOperation;
import com.thed.service.soap.ZephyrServiceException;
import com.thed.service.soap.ZephyrSoapService;
import com.thed.service.soap.ZephyrSoapService_Service;

public class ZephyrSoapClient {

	private static final String PACKAGE_FALSE_COMMENT = "Created via Jenkins";
	private static final String PACKAGE_TRUE_COMMENT = "Created by Jenkins";
	private static final QName SERVICE_NAME = new QName(
			"http://soap.service.thed.com/", "ZephyrSoapService");
	private static String ZEPHYR_URL = "{SERVER}/flex/services/soap/zephyrsoapservice-v1?wsdl";
	private static ZephyrSoapService client;
	private static String token;

	private static void updateTestCaseExecution(Long cyclePhaseId,
			long testCaseId2, Boolean testStatus) {


		long testCaseId = testCaseId2;
		List<RemoteCriteria> searchCriterias = new ArrayList<RemoteCriteria>();
		RemoteCriteria rc = new RemoteCriteria();
		rc.setSearchName("cyclePhaseId");
		rc.setSearchValue(Long.toString(cyclePhaseId));
		rc.setSearchOperation(SearchOperation.EQUALS);

		searchCriterias.add(rc);
		try {
			List<RemoteReleaseTestSchedule> testSchedulesByCriteria = client
					.getTestSchedulesByCriteria(searchCriterias, false, token);
			client.assignTestSchedules(testSchedulesByCriteria, token);
			for (Iterator<RemoteReleaseTestSchedule> iterator = testSchedulesByCriteria
					.iterator(); iterator.hasNext();) {
				RemoteReleaseTestSchedule remoteReleaseTestSchedule = iterator
						.next();

				if (remoteReleaseTestSchedule.getRemoteTestcaseId() == testCaseId) {
					long testScheduleId = remoteReleaseTestSchedule
							.getTestScheduleId();

					List<RemoteTestResult> testResults = new ArrayList<RemoteTestResult>();
					RemoteTestResult remoteTestResult = new RemoteTestResult();
					remoteTestResult.setReleaseTestScheduleId(Long
							.toString(testScheduleId));
					remoteTestResult.setExecutionNotes("Update from Jenkins");

					if (testStatus) {
						remoteTestResult.setExecutionStatus("1");
					} else {
						remoteTestResult.setExecutionStatus("2");
					}

					testResults.add(remoteTestResult);
					List<RemoteFieldValue> updateTestStatus = client
							.updateTestStatus(testResults, token);

				}

			}
		} catch (ZephyrServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the Zephyr Service Client and returns the access token
	 */
	private static String initializeClient(ZephyrConfigModel zephyrData) {

		String token = null;
		URL wsdlURL = ZephyrSoapService_Service.WSDL_LOCATION;
		String zephyrURL = zephyrData.getZephyrURL().trim();
		if (zephyrData != null && zephyrData.getZephyrServerIPAddress() != null
				&& !zephyrURL.equals("")) {

			File wsdlFile = new File(ZEPHYR_URL.replace("{SERVER}", zephyrURL));
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(
							(ZEPHYR_URL.replace("{SERVER}", zephyrURL)));
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		ZephyrSoapService_Service ss = new ZephyrSoapService_Service(wsdlURL,
				SERVICE_NAME);
		client = ss.getZephyrSoapServiceImplPort();
		try {
			token = client.login(zephyrData.getUserName(),
					zephyrData.getPassword());
		} catch (ZephyrServiceException e) {
			e.printStackTrace();
		}
		return token;
	}

	static public long createTestCase(RemoteRepositoryTree tree,
			RemoteTestcase testcase, String token)
			throws ZephyrServiceException {
		RemoteRepositoryTreeTestcase treeTestcase = new RemoteRepositoryTreeTestcase();
		testcase.setReleaseId(tree.getReleaseId());
		treeTestcase.setRemoteRepositoryId(tree.getId());
		treeTestcase.setTestcase(testcase);

		List<RemoteFieldValue> response = client.createNewTestcase(
				treeTestcase, token);

		long testcaseId = Long.parseLong(response.get(1).getValue().toString());
		return testcaseId;

	}

	public static String findTestCaseName(String testCaseNameWithPackage) {
		String testCaseName = null;
		if (StringUtils.isBlank(testCaseNameWithPackage)) {
			return testCaseName;
		}

		String[] split = testCaseNameWithPackage.split("\\.");

		int splitCount = split.length;

		if (splitCount == 1) {
			testCaseName = split[0].trim();
		}
		if (splitCount == 2) {
			testCaseName = split[0] + "." + split[1];
		}
		if (splitCount > 2) {
			testCaseName = split[(splitCount - 2)] + "."
					+ split[(splitCount - 1)];
		}

		return testCaseName;
	}

	public static String findPackageName(String testCaseNameWithPackage) {
		String packageName = "";

		if (StringUtils.isBlank(testCaseNameWithPackage)) {
			return packageName;
		}

		String[] split = testCaseNameWithPackage.split("\\.");

		int splitCount = split.length;

		for (int i = 0; i < split.length; i++) {
			if (splitCount > 2) {

				if (i == splitCount - 2)
					break;

				packageName += split[i];
				packageName += ".";
			}
		}

		String removeEnd = StringUtils.removeEnd(packageName, ".");
		return removeEnd;
	}

	public void manageTestResults1(ZephyrConfigModel zephyrData)
			throws DatatypeConfigurationException {

		token = initializeClient(zephyrData);
		Map<String, RemoteRepositoryTree> packageRepositoryStructureMap = null;

		createNewCycle(zephyrData);
		packageRepositoryStructureMap = createPackageRepositoryStructure(zephyrData);

		try {
			long testcaseTreeId = 0L;
			RemoteRepositoryTree tree = null;

			Map<Long, Boolean> testCaseIdResultMap = new HashMap<Long, Boolean>();
			List<TestCaseResultModel> testcases = zephyrData.getTestcases();

			for (Iterator<TestCaseResultModel> iterator = testcases.iterator(); iterator
					.hasNext();) {
				TestCaseResultModel testCaseWithStatus = iterator.next();
				RemoteTestcase remoteTestcase = testCaseWithStatus
						.getRemoteTestcase();

				if (zephyrData.isCreatePackage()) {
					tree = packageRepositoryStructureMap
							.get(findPackageName(remoteTestcase.getName()));
				} else {
					tree = packageRepositoryStructureMap.get("parentPhase");
				}
				testcaseTreeId = tree.getId();

				RemoteCriteria rm11 = new RemoteCriteria();
				rm11.setSearchName("testcase.name");
				rm11.setSearchOperation(SearchOperation.EQUALS);
				rm11.setSearchValue(remoteTestcase.getName());

				RemoteCriteria rm22 = new RemoteCriteria();
				rm22.setSearchName("testcase.externalId");
				rm22.setSearchOperation(SearchOperation.EQUALS);
				rm22.setSearchValue(tree.getId() + "");

				List<RemoteCriteria> searchCriterias1 = new ArrayList<RemoteCriteria>();
				searchCriterias1.add(rm11);
				searchCriterias1.add(rm22);
				List<RemoteRepositoryTreeTestcase> testCasesByCriteria11 = null;
				try {
					testCasesByCriteria11 = client.getTestcasesByCriteria(
							searchCriterias1, false, token);
				} catch (ZephyrServiceException e1) {
					ZfjReporter.logger.print("Error in getting phase name");
					e1.printStackTrace();
				}

				long testCaseId = 0L;

				try {
					if (testCasesByCriteria11 != null) {

						for (Iterator iterator2 = testCasesByCriteria11
								.iterator(); iterator2.hasNext();) {
							RemoteRepositoryTreeTestcase remoteRepositoryTreeTestcase = (RemoteRepositoryTreeTestcase) iterator2
									.next();
							Long releaseId = remoteRepositoryTreeTestcase
									.getTestcase().getReleaseId();
							Long id = remoteRepositoryTreeTestcase
									.getTestcase().getId();

							if (releaseId == zephyrData.getReleaseId()) {
								testCaseId = id;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (testCaseId == 0L) {
					remoteTestcase.setExternalId(tree.getId() + "");
					testCaseId = createTestCase(tree, remoteTestcase, token);
				}

				testCaseIdResultMap.put(testCaseId,
						testCaseWithStatus.getPassed());

			}

			RemotePhase remotePhase = new RemotePhase();

			RemoteData rd = new RemoteData();
			rd.setId(packageRepositoryStructureMap.get("parentPhase").getId());
			RemoteNameValue remoteRepository = new RemoteNameValue();
			remoteRepository.setRemoteData(rd);

			RemoteData rd1 = new RemoteData();
			rd1.setId(zephyrData.getCycleId());
			RemoteNameValue remoteCycle = new RemoteNameValue();
			remoteCycle.setRemoteData(rd1);

			remotePhase.setRemoteRepository(remoteRepository);
			remotePhase.setRemoteCycle(remoteCycle);

			RemoteCycle cycleById = client.getCycleById(
					zephyrData.getCycleId(), token);
			XMLGregorianCalendar startDate1 = cycleById.getStartDate();
			XMLGregorianCalendar endDate1 = cycleById.getEndDate();
			// XMLGregorianCalendar startDate =
			// DatatypeFactory.newInstance().newXMLGregorianCalendar("2015-05-01T00:00:00-07:00");
			// XMLGregorianCalendar endDate =
			// DatatypeFactory.newInstance().newXMLGregorianCalendar("2015-05-21T00:00:00-07:00");

			remotePhase.setStartDate(startDate1);
			remotePhase.setEndDate(endDate1);
			Long cyclePhaseID = client.addPhaseToCycle(remotePhase, 1, token);


			Set<Long> testCaseIds = testCaseIdResultMap.keySet();
			for (Iterator<Long> iterator = testCaseIds.iterator(); iterator
					.hasNext();) {
				Long testCaseId = iterator.next();
				Boolean testStatus = testCaseIdResultMap.get(testCaseId);

				updateTestCaseExecution(cyclePhaseID, testCaseId, testStatus);
			}

		} catch (ZephyrServiceException e) {
			e.printStackTrace();
		}
	}

	private void createNewCycle(ZephyrConfigModel zephyrData)
			throws DatatypeConfigurationException {
		if (zephyrData.getCycleId() == ZfjReporter.NEW_CYCLE_KEY_IDENTIFIER) {
			RemoteProject projectById = null;
			try {
				projectById = client.getProjectById(
						zephyrData.getZephyrProjectId(), token);
			} catch (ZephyrServiceException e) {
				ZfjReporter.logger.print("Problem Getting Project ID");
				return;
			}
			RemoteRelease releaseById = null;
			try {
				releaseById = client.getReleaseById(zephyrData.getReleaseId(),
						token);
			} catch (ZephyrServiceException e) {
				ZfjReporter.logger.print("Problem Getting Release ID");

				return;
			}

			RemoteCycle rCycle = new RemoteCycle();
			XMLGregorianCalendar releaseStartDate = releaseById.getStartDate();
			rCycle.setStartDate(releaseStartDate);

			if (projectById.getEndDate() != null) {
				rCycle.setEndDate(projectById.getEndDate());
			} else {
				GregorianCalendar gregorianCalendar = releaseStartDate
						.toGregorianCalendar();
				gregorianCalendar.add(Calendar.DAY_OF_MONTH, +30);
				XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(gregorianCalendar);
				rCycle.setEndDate(xgcal);
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E dd, yyyy hh:mm a");
			String dateFormatForCycleCreation = sdf.format(date);

			rCycle.setName(zephyrData.getCyclePrefix()
					+ dateFormatForCycleCreation);
			rCycle.setReleaseId(zephyrData.getReleaseId());
			try {
				Long createNewCycleID = client.createNewCycle(rCycle, token);
				zephyrData.setCycleId(createNewCycleID);
			} catch (ZephyrServiceException e) {
				ZfjReporter.logger.print("Problem Creating new cycle");
				e.printStackTrace();
				return;
			}
		}
	}

	private Map<String, RemoteRepositoryTree> createPackageRepositoryStructure(
			ZephyrConfigModel zephyrData) {

		Map<String, RemoteRepositoryTree> RemoteRepositoryTreeMap = new HashMap<String, RemoteRepositoryTree>();
		Map<String, RemoteRepositoryTree> tempRemoteRepositoryTreeMap = new HashMap<String, RemoteRepositoryTree>();
		RemoteRepositoryTree tree = null;

		tree = fetchPhase(zephyrData);

		RemoteRepositoryTreeMap.put("parentPhase", tree);

		if (!zephyrData.isCreatePackage())
			return RemoteRepositoryTreeMap;

		Set<String> packageNames = zephyrData.getPackageNames();

		for (Iterator iterator = packageNames.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();

			String[] packageNameArray = string.split("\\.");
			RemoteRepositoryTree previousRepository = tree;
			for (int i = 0; i < packageNameArray.length; i++) {

				try {
					if (tempRemoteRepositoryTreeMap
							.containsKey(packageNameArray[i])) {
						previousRepository = tempRemoteRepositoryTreeMap
								.get(packageNameArray[i]);
						continue;
					}

					RemoteRepositoryTree remoteRepositoryTree1 = null;

					RemoteCriteria rm11 = new RemoteCriteria();
					rm11.setSearchName("name");
					rm11.setSearchOperation(SearchOperation.EQUALS);
					rm11.setSearchValue(packageNameArray[i]);

					RemoteCriteria rm22 = new RemoteCriteria();
					rm22.setSearchName("releaseId");
					rm22.setSearchOperation(SearchOperation.EQUALS);
					rm22.setSearchValue(zephyrData.getReleaseId() + "");

					RemoteCriteria rm33 = new RemoteCriteria();
					rm33.setSearchName("parent.id");
					rm33.setSearchOperation(SearchOperation.EQUALS);
					rm33.setSearchValue(previousRepository.getId() + "");

					List<RemoteCriteria> searchCriterias1 = new ArrayList<RemoteCriteria>();
					searchCriterias1.add(rm11);
					searchCriterias1.add(rm22);
					searchCriterias1.add(rm33);
					try {
						List<RemoteRepositoryTree> testCaseTreesByCriteria11 = client
								.getTestCaseTreesByCriteria(searchCriterias1,
										false, token);

						if (testCaseTreesByCriteria11 != null
								&& testCaseTreesByCriteria11.size() > 0) {
							remoteRepositoryTree1 = testCaseTreesByCriteria11
									.get(0);
						}
					} catch (ZephyrServiceException e1) {
						ZfjReporter.logger
								.print("Error in getting phase name");
						e1.printStackTrace();
					}

					long previousRepositoryId;
					RemoteRepositoryTree tree1 = remoteRepositoryTree1;

					if (remoteRepositoryTree1 == null) {
						remoteRepositoryTree1 = new RemoteRepositoryTree();
						remoteRepositoryTree1.setDescription("something");
						remoteRepositoryTree1.setReleaseId(zephyrData
								.getReleaseId());
						remoteRepositoryTree1.setName(packageNameArray[i]);
						remoteRepositoryTree1.setParent(previousRepository);
						previousRepositoryId = client.createNewTestcaseTree(
								remoteRepositoryTree1, token);
						tree1 = client.getTestcaseTreeById(
								previousRepositoryId, token);
					}

					previousRepository = tree1;

					tempRemoteRepositoryTreeMap.put(packageNameArray[i], tree1);

				} catch (ZephyrServiceException e) {
					e.printStackTrace();
				}

			}

			RemoteRepositoryTreeMap.put(string, previousRepository);
		}

		return RemoteRepositoryTreeMap;

	}

	/**
	 * @param zephyrData
	 * @return
	 */
	private RemoteRepositoryTree fetchPhase(ZephyrConfigModel zephyrData) {

		RemoteRepositoryTree tree = null;

		RemoteCriteria rm1 = new RemoteCriteria();
		rm1.setSearchName("name");
		rm1.setSearchOperation(SearchOperation.EQUALS);
		rm1.setSearchValue("Automation");

		RemoteCriteria rm2 = new RemoteCriteria();
		rm2.setSearchName("releaseId");
		rm2.setSearchOperation(SearchOperation.EQUALS);
		rm2.setSearchValue(zephyrData.getReleaseId() + "");

		List<RemoteCriteria> searchCriterias = new ArrayList<RemoteCriteria>();
		searchCriterias.add(rm1);
		searchCriterias.add(rm2);
		try {
			List<RemoteRepositoryTree> testCaseTreesByCriteria = client
					.getTestCaseTreesByCriteria(searchCriterias, false, token);

			if (testCaseTreesByCriteria != null
					&& testCaseTreesByCriteria.size() > 0) {
				
				for (Iterator<RemoteRepositoryTree> iterator = testCaseTreesByCriteria.iterator(); iterator
						.hasNext();) {
					RemoteRepositoryTree remoteRepositoryTree = iterator.next();
					
					if (zephyrData.isCreatePackage() && remoteRepositoryTree.getDescription()
						.equalsIgnoreCase(PACKAGE_TRUE_COMMENT)) {
						tree = remoteRepositoryTree;
						break;
					} else if (!zephyrData.isCreatePackage() && remoteRepositoryTree.getDescription()
							.equalsIgnoreCase(PACKAGE_FALSE_COMMENT)) {
						tree = remoteRepositoryTree;
						break;
					}
				}
			}
		} catch (ZephyrServiceException e1) {
			ZfjReporter.logger.print("Error in getting phase name");
			e1.printStackTrace();
		}

		if (tree == null) {

			RemoteRepositoryTree remoteRepositoryTree = new RemoteRepositoryTree();

			if (zephyrData.isCreatePackage()) {
				remoteRepositoryTree.setDescription(PACKAGE_TRUE_COMMENT);
			} else {
				remoteRepositoryTree.setDescription(PACKAGE_FALSE_COMMENT);
			}
			remoteRepositoryTree.setReleaseId(zephyrData.getReleaseId());
			remoteRepositoryTree.setName("Automation");
			Long testcaseTreeId = 0L;
			try {
				testcaseTreeId = client.createNewTestcaseTree(
						remoteRepositoryTree, token);
				tree = client.getTestcaseTreeById(testcaseTreeId, token);
			} catch (ZephyrServiceException e) {
				e.printStackTrace();
			}
		}
		return tree;
	}
}

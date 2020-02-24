package com.automation.integrations;

import java.io.IOException;
import java.net.MalformedURLException;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;

import partner.utils.ApiPartnerConfigDetails;
import sanctuary.utils.ApiConfigDetails;

public class TestRailTestRunStatus {
	public static TestRailAPIClient testrailObj = null;

	public static void TestRailTestInstance(String project) throws MalformedURLException, IOException, APIException {

		String TestRailSuiteName = TestUtilities.fnGetTestRailSuiteName(project);
		String strTestRailRunName = TestRailTestRunStatus.fnGetTestRailRunInstance();
		String strTestPlanName = getTestPlanName();
		if (Boolean.parseBoolean(ConfigDetails.strTestRail)) {
			TestRailAPIClient.setTestRail(strTestPlanName, TestRailSuiteName, strTestRailRunName);
		}
	}

	public static void fnReportStatus(int scriptID, int result, String comment) throws IOException, APIException {

		int intStatus = 0;
		if (result == 1) {
			intStatus = 1;
		} else if (result == 2) {
			intStatus = 5;
		} else if (result == 3) {
			intStatus = 2;
		}
		TestRailAPIClient.addResultForTestCase(testrailObj, scriptID, intStatus, comment);
	}

	public static String fnGetTestRailRunInstance() {
		String strTestRunInstance = "";
		try {
			String strCountry = ConfigDetails.country;
			String strEnvironment = ConfigDetails.environment;
			String strOS = ConfigDetails.strPlatformName.toUpperCase();
			String ExecutionType = ConfigDetails.suiteType.toUpperCase();
			String strName = FileUtilities.GetCurrentDateStamp();
			strName = strName.replaceAll(":", "_").replaceAll("-", "_").replaceAll(" ", "_");

			String[] buildNumber = null;

			if (ConfigDetails.project.equalsIgnoreCase("Sanctuary")
					|| ConfigDetails.project.equalsIgnoreCase(GlobalConstant.PROJECT_PARTNERAPI)) {
				buildNumber = ConfigDetails.jenkinsBuildName.split("-");
				String environment = ConfigDetails.environment;
				String country = ConfigDetails.project.equalsIgnoreCase("Sanctuary") ? ApiConfigDetails.country : ApiPartnerConfigDetails.country;
				String platform_Partner = ConfigDetails.project.equalsIgnoreCase("Sanctuary")? ApiConfigDetails.platform : ApiPartnerConfigDetails.partner;

				strTestRunInstance = buildNumber[0] + "_" + country + "_" + environment + "_" + platform_Partner + "_"
						+ ExecutionType;

			} else {

				if (ConfigDetails.targetExecution.equalsIgnoreCase(GlobalConstant.ENVIRONMENT_BROWSERSTACK)) {
					buildNumber = ConfigDetails.jenkinsBuildName.split("-");
					strTestRunInstance = buildNumber[0] + "_" + strCountry + "_" + strEnvironment + "_" + strOS + "_"
							+ ConfigDetails.browser + "_" + ExecutionType;
				} else {
					strTestRunInstance = strCountry + "_" + strEnvironment + "_" + strOS + "_" + ConfigDetails.browser
							+ "_" + ExecutionType + "_" + strName;
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return strTestRunInstance.toUpperCase();
	}

	public static String getTestPlanName() {
		String buildNumber = "";
		if (ConfigDetails.jenkinsBuildName.toLowerCase().startsWith("local run")) {
			buildNumber = ConfigDetails.jenkinsBuildName.split("-")[0] + "_" + FileUtilities.GetCurrentTimeStamp();
		} else {
			buildNumber = ConfigDetails.jenkinsBuildName.split("-")[0];
		}

		String strEnvironment = ConfigDetails.environment;
		return (buildNumber + "_Env-" + strEnvironment + "_Application-" + ConfigDetails.project + "_SuiteType-"
				+ ConfigDetails.suiteType).toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println("Value : " + getTestPlanName());
	}

}

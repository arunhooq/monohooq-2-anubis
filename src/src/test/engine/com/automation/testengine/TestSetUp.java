package com.automation.testengine;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.automation.integrations.ReportStatus;
import com.automation.integrations.TestRailTestRunStatus;
import com.automation.reports.ReporterLog;
import com.automation.reports.ReportsExtent;
import com.automation.utilities.GmailAPI;
import com.automation.utilities.ReadTestData;

public class TestSetUp {
	public static TestRailTestRunStatus testRailStatus = null;
	public static String strTestName = null;

	public static void initializeSetUp(String project, String suiteType,String country) {
		try {
			ConfigDetails.country = country ;
			Logger.getRootLogger().setLevel(Level.OFF);
			TestUtilities.cleanUpFilesFolders();
			ReporterLog.initilizeSoftAssert();
			ConfigDetails.environment = ConfigDetails.fnGetPropertiesFileDetails("environment");
			GmailAPI.getGmailService();
			ReportsExtent.fnCreateExtentReport(project,ConfigDetails.country);
			ReportsExtent.fnStartTest("BeforeSuite", project, suiteType);
			ConfigDetails.setConfigDetails();
			ConfigDetails.country = country ;
			ConfigDetails.project = project ;
			ConfigDetails.suiteType = suiteType;
			TestRailTestRunStatus.TestRailTestInstance(project);
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		
		}
	}
	
	public static void endSetUp() {
		try {
			
			ReportsExtent.fnEndTest("fnBeforeSuite");
			ReporterLog.softAssert.assertAll();
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ e.getStackTrace()[1].getLineNumber() + " And Exception is"+e.getStackTrace().toString(), e);
		
		}
	}

	public static void endSuite() {
		try {
			
			ReportsExtent.fnCloseExtentReports();
			ReportsExtent.fnCopyReportToHistory();
	
			if (ActionEngine.driver != null)
				ActionEngine.driver.quit();	
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		
		}
	}

	public static void initializeBeforeTest(String testName, String project, String suiteType) {
		try {
			
			ReporterLog.softAssert = new SoftAssert();
			TestSetUp.strTestName = testName;
			
			System.out.println("\n*******	START OF SCRIPT: "+TestSetUp.strTestName+"  *********");	
	
			ReportsExtent.fnStartTest(TestSetUp.strTestName, project, suiteType);
			
			TestUtilities.fnGetGoogleSheetDeviceDetails(TestSetUp.strTestName, suiteType,ReadTestData.testData);
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		
		}
	}
	
	public static void initializeBeforeTest_NEW(String testName, String project, String suiteType) throws Exception{
		try {
			
			ReporterLog.softAssert = new SoftAssert();
			TestSetUp.strTestName = testName;
			
			System.out.println("\n*******	START OF SCRIPT: "+TestSetUp.strTestName+"  *********");	
	
			ReportsExtent.fnStartTest(TestSetUp.strTestName, project, suiteType);
			
			TestUtilities.fnGetGoogleSheetDeviceDetails(TestSetUp.strTestName, suiteType,ReadTestData.testData);
				
		}
		catch(Exception e) {
			//Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
			//			+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
			throw new Exception("Exception encountered in initializeBeforeTest");		
		}
	}

	public static void endAfterMethod(int result,String testName) {
		try {
		
			if( result == ITestResult.SKIP)
				ReporterLog.skip(TestSetUp.strTestName, "Test is skipped as the dependency test failed");
			
			ReportsExtent.fnEndTest(TestSetUp.strTestName);
	
			if (Boolean.parseBoolean(ConfigDetails.strGoogleSheet))
				ReportStatus.fnUpdateDashboard();
			if (Boolean.parseBoolean(ConfigDetails.strTestRail))
				TestRailTestRunStatus.fnReportStatus(ReadTestData.TestRailID ,result,testName);
			ReadTestData.TestRailID = 0;
			
			System.out.println("\n\n*******  			END OF SCRIPT			  **********\n\n");	
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		
		}
	}

}

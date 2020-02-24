package com.automation.reports;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.automation.integrations.ReportStatus;
import com.automation.integrations.TestRailAPIClient;
import com.automation.integrations.TestRailTestRunStatus;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;
import com.relevantcodes.extentreports.LogStatus;

import sanctuary.utils.ApiConfigDetails;

public class ReporterLog {

	public static SoftAssert softAssert = null;

	public static void initilizeSoftAssert() {
		softAssert = new SoftAssert();

	}
	
	/***
	 * Name of Function :- pass Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log pass in Extent Report
	 * 
	 * @param Exception
	 */
	public static void pass(String stepName, String description) {
		if(Boolean.parseBoolean(ConfigDetails.enableScreenShotForAllStep)) 
			logScreenshot(stepName);
		ReportsExtent.testReporter.log(LogStatus.PASS, stepName, description);
		System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : PASS");
		Reporter.log("Test Step : "+stepName +" Description : "+ description +" Status : PASS");
		TestRailAPIClient.addResultForTestCase(TestRailTestRunStatus.testrailObj, ReadTestData.TestRailID, 1, "Step - "+stepName +" - Description - "+ description +"  -Status : ");
	}

	/***
	 * Name of Function :- info Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log info in Extent Report
	 * 
	 * @param Exception
	 */
	public static void info(String stepName, String description) {
		
		if(Boolean.parseBoolean(ConfigDetails.enableScreenShotForAllStep)) 
			logScreenshot(stepName);
		ReportsExtent.testReporter.log(LogStatus.INFO, stepName, description);
		System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : INFO");

	}

	/***
	 * Name of Function :- info Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log info in Extent Report
	 * 
	 * @param Exception
	 */
	public static void infoWithScreenshot(String stepName, String description) {
		try {
			ReportsExtent.testReporter.log(LogStatus.INFO, stepName, description);
			System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : INFO");
			logScreenshot(stepName);

		} catch (Exception e) {
			softAssert.fail(Thread.currentThread().getStackTrace()[2].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	/***
	 * Name of Function :- skip Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log skip in Extent Report
	 * 
	 * @param Exception
	 */
	public static void skip(String stepName, String description) {

		ReportsExtent.testReporter.log(LogStatus.SKIP, stepName, description);
		if(Boolean.parseBoolean(ConfigDetails.enableScreenShotForAllStep)) 
			logScreenshot(stepName);
		System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : SKIP");
		Reporter.log("Test Step : "+stepName +" Description : "+ description +" Status : SKIP");
		TestRailAPIClient.addResultForTestCase(TestRailTestRunStatus.testrailObj, ReadTestData.TestRailID, 2, "Step - "+stepName +" - Description - "+ description +"  -Status : Skip");
	}

	/***
	 * Name of Function :- fail Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log fail in Extent Report
	 * 
	 * @param Exception
	 */
	public static void fail(String stepName, String description) {

		ReportsExtent.testReporter.log(LogStatus.FAIL, stepName, description);
		TestRailAPIClient.addResultForTestCase(TestRailTestRunStatus.testrailObj, ReadTestData.TestRailID, 5, "Step - "+stepName +" - Description - "+ description +"  -Status : ");
		logScreenshot(stepName);
		System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : FAIL");
		Reporter.log("Test Step : "+stepName +" Description : "+ description +" Status : FAIL");  
		softAssert.assertAll();
		Assert.fail(Thread.currentThread().getStackTrace()[3].getMethodName() + "StepName : "+stepName+" Description : "+description+"  Status : ");	
	}

	public static void failAndContinue(String stepName, String description) {

		ReportsExtent.testReporter.log(LogStatus.FAIL, stepName, description);
		logScreenshot(stepName);
		System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : FAIL");
		Reporter.log("Test Step : "+stepName +" Description : "+ description +" Status : FAIL");  
		softAssert.assertFalse(true, "stepName : "+stepName+" and Description "+description);

	}

	/***
	 * Name of Function :- fail Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log fail in Extent Report
	 * 
	 * @param Exception
	 */
	public static void fatal(String stepName, String description) {

		ReportsExtent.testReporter.log(LogStatus.FATAL, stepName, description);
		TestRailAPIClient.addResultForTestCase(TestRailTestRunStatus.testrailObj, ReadTestData.TestRailID, 5, "Step - "+stepName +" - Description - "+ description);
		System.out.println("Test Step : "+stepName +" Description : "+ description +" Status : FAIL");
		Reporter.log("Test Step : "+stepName +" Description : "+ description +" Status : FAIL");
		logScreenshot(stepName); 
		softAssert.assertAll();
		Assert.fail(Thread.currentThread().getStackTrace()[3].getMethodName() + "StepName : "+stepName+" Description : "+description+"  Status : FAILED");	
	}


	/***
	 * Name of Function :- error Developed By :- Pankaj Kumar (Cigniti Technologies)
	 * Date :- 15-May-2019 Function Description :- To Log error in Extent Report
	 * 
	 * @param Exception
	 */
	public static void error(String stepName, String description) {

		if(Boolean.parseBoolean(ConfigDetails.enableScreenShotForAllStep)) 
			logScreenshot(stepName);
		ReportsExtent.testReporter.log(LogStatus.ERROR, stepName, description);
		ReportStatus.blnStatus = false;
		Assert.fail(Thread.currentThread().getStackTrace()[3].getMethodName() + "StepName : "+stepName+" Description : "+description+"  Status : FAILED");	

	}

	/***
	 * Name of Function :- warning Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Log warning in
	 * Extent Report
	 * 
	 * @param Exception
	 */
	public static void warning(String stepName, String description) {
		try {
			if(Boolean.parseBoolean(ConfigDetails.enableScreenShotForAllStep)) 
				logScreenshot(stepName);
			ReportsExtent.testReporter.log(LogStatus.WARNING, stepName, description);
			Reporter.log("Step Name : "+stepName +"  Description : "+description +"  Status : Warning");

		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[3].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	public static void logScreenshot(String strName) {
		try {
			String screenshotPath = null;

			//TODO - Temporary Disable of AndroidTv ScreenShots due to appium Issue with AndroidTV screen capturing functionality
			//https://github.com/appium/appium-uiautomator2-server/issues/275
			if(!(ActionEngine.driver == null || ConfigDetails.project.equalsIgnoreCase(GlobalConstant.PROJECT_ANDROIDTVV1) || ConfigDetails.project.equalsIgnoreCase(GlobalConstant.PROJECT_ANDROIDTVV2))) {
				
				screenshotPath = FileUtilities.getScreenshot(strName);
				String reportBase64 ="data:image/jpg;base64 "+FileUtilities.embedImageToTestNGReport(screenshotPath); //Adding base 64 Image to TestNG report

				ReportsExtent.testReporter.log(LogStatus.INFO,"Screenshot: ", ReportsExtent.testReporter.addScreenCapture(reportBase64));
			}
		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[3].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}

	}
	/***
	 * Name of Function :- unknown Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Log warning in
	 * Extent Report
	 * 
	 * @param Exception
	 */
	public static void unknown(String stepName, String description) {
		try {
			if(Boolean.parseBoolean(ConfigDetails.enableScreenShotForAllStep)) 
				logScreenshot(stepName);
			ReportsExtent.testReporter.log(LogStatus.UNKNOWN, stepName, description);
			Reporter.log("Step Name : "+stepName +"  Description : "+description +"  Status : UNKNOWN");
		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[3].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	public static void skipTest()
	{
		try
		{
			throw new SkipException("Not applicable for Other than IOS "); 
		}
		catch(Exception e)
		{

			ReporterLog.skip(Thread.currentThread().getStackTrace()[1].getMethodName(),

					"Test Result SKIPPED, as it is applicable to device:"+ApiConfigDetails.platform);
			softAssert.assertAll();

		}
	}

}

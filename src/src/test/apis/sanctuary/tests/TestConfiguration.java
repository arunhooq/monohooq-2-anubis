package sanctuary.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.automation.integrations.TestRailAPIClient;
import com.automation.integrations.TestRailTestRunStatus;
import com.automation.reports.ReporterLog;
import com.automation.reports.ReportsExtent;
import com.automation.testengine.ConfigDetails;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.ReadTestData;

import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ReusableMethods;

public class TestConfiguration {

	public static String email;
	public static String deviceid;
	public static String testName = null;
	public static String strcountry;

	public static TestRailTestRunStatus testRailStatus = null;

	@BeforeSuite(alwaysRun = true)
	public void fnBeforeSuite() {
		try {
			Logger.getRootLogger().setLevel(Level.OFF);
			TestUtilities.cleanUpFilesFolders();
			ReporterLog.initilizeSoftAssert();
			ReportsExtent.fnCreateExtentReport("API", "All_Countries");
			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest("fnBeforeSuite",
					"API Tests on " + ApiConfigDetails.environment);
			ConfigDetails.setConfigDetails();
			ConfigDetails.project = "Sanctuary";
			ConfigDetails.suiteType = "Sanity";
			ReportsExtent.fnEndTest("fnBeforeSuite");
			ReporterLog.softAssert.assertAll();
			ReporterLog.info("fnBeforeSuite", "Before Suite completed");
			System.out.println("Before Suite End");

		} catch (Exception e) {
			ReporterLog.info("fnBeforeSuite", "Before Suite failed");
			TestUtilities.logReportFatal(e, "Before Suite failed");
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);

		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "country", "platform" })
	public void fnBeforeTest(@Optional("SG") String country, @Optional("android") String platform) {
		try {

			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest("fnBeforeTest_" + country,
					"API Tests on " + ApiConfigDetails.environment);
			ApiConfigDetails.country = country;
			ApiConfigDetails.platform = platform;
			ConfigDetails.country = country; // not needed if not used
			ApiConfigDetails.setConfigDetails();

			// TestRail instantiation
			TestRailTestRunStatus.TestRailTestInstance("Sanctuary");

			FileUtilities.writeToFile("fnBeforeTest_" + country, "Environment: " + ApiConfigDetails.environment,
					"purple");

			ReportsExtent.fnEndTest("fnBeforeTest_" + country);

			ReporterLog.info("fnBeforeTest", "Before Test completed");

			ReporterLog.softAssert.assertAll();

			System.out.println("Before Test End");

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.info("fnBeforeTest", "Before Test failed");
			TestUtilities.logReportFatal(e, "Before Test failed");
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "country" })
	public void fnBeforeMethod(Method method, @Optional("SG") String country) throws IOException {
		try {

			System.out.println("*****Country changed to: " + ApiConfigDetails.country);

			ReporterLog.softAssert = new SoftAssert();
			testName = method.getName() + "_" + ApiConfigDetails.country;

			// email and deviceid configuration for each test
			email = "hooqtest" + ReusableMethods.getTimeStamp() + "@yopmail.com";
			deviceid = "deviceid" + ReusableMethods.getTimeStamp();

			System.out.println("\n*******	START OF SCRIPT: " + testName + "  *********");
			System.out.println("The email is set to: " + email);
			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest(testName,
					"API Tests on " + ApiConfigDetails.environment);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e, "Before Method failed");
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);

		}
	}

	@AfterMethod(alwaysRun = true)
	public void fnAfterMethod(ITestResult result, Method method) {
		try {
			System.out.println("result status" + result.getStatus());
			if (result.getStatus() == ITestResult.SKIP) {
				email = null;
				deviceid = null;
				ReporterLog.skip(testName, "Test is skipped as the dependency test failed");
			}

			if (Boolean.parseBoolean(ConfigDetails.strTestRail))
				TestRailTestRunStatus.fnReportStatus(ReadTestData.TestRailID, result.getStatus(), testName);
			ReadTestData.TestRailID = 0;
			ReportsExtent.fnEndTest(testName);
			System.out.println("\n*******  			END OF SCRIPT			  **********\n");

		} catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFatal(e, "After Method failed");
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	@AfterTest(alwaysRun = true)
	public void fnAfterTest() {
		try {
			
			System.out.println("\n==================================================================\n"
					+ "================== Executing AfterTest =========================\n"
					+ "==================================================================");
			
			if (Boolean.parseBoolean(ConfigDetails.strTestRail)) {
				TestRailAPIClient.updateTestRun(TestRailTestRunStatus.testrailObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFailure(e, "After Test failed");
		}
	}

	@AfterSuite(alwaysRun = true)
	@Parameters({ "country" })
	public void fnAfterSuite(@Optional("SG") String country) {
		try {

			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest("fnAfterSuite_" + country,
					"API Tests on " + ApiConfigDetails.environment);
			FileUtilities.writeToFile("fnAfterSuite_" + country, "Environment: " + ApiConfigDetails.environment,
					"purple");

			ReportsExtent.fnEndTest("fnAfterSuite_" + country);

			ReporterLog.info("fnAfterSuite", "After Suite completed");
			ReportsExtent.fnCloseExtentReports();
			ReportsExtent.fnCopyReportToHistory();

		} catch (Exception e) {
			ReporterLog.info("fnAfterSuite", "After Suite failed");
			TestUtilities.logReportFatal(e, "After Suite failed");
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}

}

package partner.tests;

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
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import partner.utils.ApiPartnerConfigDetails;

public class TestConfiguration
{
	public static String testName = null;

	@BeforeSuite(alwaysRun = true)
	public void fnBeforeSuite()
	{
		try
		{
			Logger.getRootLogger().setLevel(Level.OFF);
			TestUtilities.cleanUpFilesFolders();
			ReporterLog.initilizeSoftAssert();
			ConfigDetails.setConfigDetails();
			ConfigDetails.project = GlobalConstant.PROJECT_PARTNERAPI ;
			ConfigDetails.suiteType="Sanity";
			ReportsExtent.fnCreateExtentReport("APIPartner","All_Countries");
			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest("fnBeforeSuite","API Tests on "+ApiPartnerConfigDetails.environment);
			ReporterLog.info("fnBeforeSuite", "initialization is done");
			ReportsExtent.fnEndTest("fnBeforeSuite");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"country", "partner"})
	public void fnBeforeTest(@Optional("ID") String country, @Optional("HOOQ") String partner)
	{
		try
		{
			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest("fnBeforeTest_"+country+"_"+partner,"API Partner Tests on "+ApiPartnerConfigDetails.environment);

			ApiPartnerConfigDetails.country = country;
			ApiPartnerConfigDetails.partner = partner;
			ConfigDetails.country = country;
			ApiPartnerConfigDetails.setConfigDetails();

			//TestRail instantiation
			TestRailTestRunStatus.TestRailTestInstance(GlobalConstant.PROJECT_PARTNERAPI);

			FileUtilities.writeToFile("fnBeforeTest_"+country+"_"+ApiPartnerConfigDetails.partner, "Environment: "+ApiPartnerConfigDetails.environment, "purple");
			ReportsExtent.fnEndTest("fnBeforeTest_"+country+"_"+ApiPartnerConfigDetails.partner);
			ReporterLog.softAssert.assertAll();
			System.out.println("Before Test End");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"country", "partner"})
	public void fnBeforeMethod(Method method, @Optional("ID") String country, @Optional("HOOQ") String partner) throws IOException
	{
		try
		{
			ReporterLog.softAssert = new SoftAssert();
			testName = method.getName()+"_" + ApiPartnerConfigDetails.country+"_"+ApiPartnerConfigDetails.partner;
			System.out.println("\n========== START OF SCRIPT: "+ testName +" ==========");
			ReportsExtent.testReporter = ReportsExtent.extentReport.startTest(testName,"API Tests on "+ApiPartnerConfigDetails.environment);

			// Set RequestID
			ApiPartnerConfigDetails.request_id = method.getName();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void fnAfterMethod(ITestResult result, Method method)
	{
		try
		{
			if(result.getStatus() == ITestResult.SKIP)
			{
				ReporterLog.skip(testName, "Test is skipped as the dependency test failed");
			}
			ReportsExtent.fnEndTest(testName);
			System.out.println("========== END OF SCRIPT: "+ testName + " ============");

			if (Boolean.parseBoolean(ConfigDetails.strTestRail))
				TestRailTestRunStatus.fnReportStatus(ReadTestData.TestRailID ,result.getStatus(),testName);
			ReadTestData.TestRailID = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
	public void fnAfterSuite()
	{
		try
		{
			ReportsExtent.fnCloseExtentReports();
			ReportsExtent.fnCopyReportToHistory();
		}
		catch(Exception e)
		{
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}
}

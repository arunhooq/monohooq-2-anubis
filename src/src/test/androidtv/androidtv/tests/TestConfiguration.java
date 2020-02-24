package androidtv.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automation.integrations.TestRailAPIClient;
import com.automation.integrations.TestRailTestRunStatus;
import com.automation.reports.ReporterLog;
import com.automation.reports.ReportsExtent;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestDriver;
import com.automation.testengine.TestSetUp;
import com.automation.utilities.APIUtils;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import androidtv.pages.ATVDiscoverPage;
import androidtv.pages.ATVSigninPage;
import androidtv.utils.AndroidTVConstants;

public class TestConfiguration {
	
	public static androidtv.pages.ATVWelcomePage atvWelcomePage = new androidtv.pages.ATVWelcomePage();
	public static androidtv.pages.ATVSigninPage atvSigninPage = new androidtv.pages.ATVSigninPage();
	public static androidtv.pages.ATVDiscoverPage atvDiscoverPage = new androidtv.pages.ATVDiscoverPage();
	public static androidtv.pages.ATVMePage atvMePage = new androidtv.pages.ATVMePage();
	public static androidtv.pages.ATVPlayerPage atvPlayerPage = new androidtv.pages.ATVPlayerPage();
	public static androidtv.pages.ATVChannelsPage atvChannelsPage = new androidtv.pages.ATVChannelsPage();
	public static androidtv.pages.ATVSearchPage atvSearchPage = new androidtv.pages.ATVSearchPage();
	public static androidtv.pages.ATVTVODPage atvTVODPage = new androidtv.pages.ATVTVODPage();
	public static androidtv.pages.ATVContentDetailsPage atvContentDetailsPage = new androidtv.pages.ATVContentDetailsPage();
	
	public static androidtv.pages.BasePage basePage = new androidtv.pages.BasePage();
	public static androidtv.pages.DiscoverPage discoverPage = new androidtv.pages.DiscoverPage();
	
	public static androidtv.pages.PlayerSDKBasePage playerSDKBasePage = new androidtv.pages.PlayerSDKBasePage();
	public static androidtv.pages.PlayerSDKPlayerPage playerSDKPlayerPage = new androidtv.pages.PlayerSDKPlayerPage();
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({"Project", "SuiteType","country"})
	public void fnBeforeSuite(String project, String suiteType ,String country) {
		try {
			
			TestSetUp.initializeSetUp(project, suiteType,country);			
			//ReadTestData.getTestData(project);
			TestDriver.initiateDriverSession();
			
			TestSetUp.endSetUp();

		}
		catch(Exception e) {
			e.printStackTrace();
			ReporterLog.fail("BeforeSuite", "Configuration at BeforeSuite Failed with exception : "+e.getMessage());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
	
		}
	}
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"userType","Project","SuiteType","country"})
	public void fnBeforeTest(String userType, String project, String suiteType, String country) {
		try {

			ConfigDetails.userType = userType;
			ConfigDetails.country = country;

			ReportsExtent.fnStartTest("fnBeforeTest-"+ConfigDetails.userType, project, suiteType);

			Thread.sleep(10000);
				
			if (ConfigDetails.userType.equalsIgnoreCase("Active")) {
				ReadTestData.fnAddTestRailScriptID(22189);
				atvWelcomePage.clickSigninButton().verifyLoginPage();
				ATVSigninPage.signinUsingLinkTV(userType, country, AndroidTVConstants.PROD_ENV, AndroidTVConstants.PROD_ENV);
				ATVDiscoverPage.verifyToastMessage(userType, country);
			} else if (ConfigDetails.userType.equalsIgnoreCase("Lapsed")) {
				ReadTestData.fnAddTestRailScriptID(22189);
				atvWelcomePage.clickSigninButton().verifyLoginPage();
				ATVSigninPage.signinUsingLinkTV(userType, country, AndroidTVConstants.PROD_ENV, AndroidTVConstants.PROD_ENV);
				ATVDiscoverPage.verifyToastMessage(userType, country);
			}

			ReportsExtent.fnEndTest("fnBeforeTest-"+ConfigDetails.userType);
			//ReporterLog.softAssert.assertAll();

		}
		catch(Exception e) {
			ReporterLog.fail("beforeTest", "Configuration failed at BeforeTest with Exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);

		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"userType", "Project", "SuiteType" })
	public void fnBeforeMethod(String userType, String project, String suiteType, Method method) throws IOException {
		try {
			ConfigDetails.userType = userType;
			TestSetUp.initializeBeforeTest(method.getName()+"_"+userType,project,suiteType);
							
		}
		catch(Exception e) {
			e.printStackTrace();
			ReporterLog.fail(method.getName(), "Test Failed  at BeforeMethod with Exception "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is"+e.getStackTrace().toString(), e);
			
		}
		
	}

	@AfterMethod(alwaysRun = true)
	@Parameters({"userType"})
	public void fnAfterMethod(ITestResult result, Method method, String userType) {	
		try {	
			
			TestSetUp.endAfterMethod(result.getStatus(),method.getName()+"_"+userType);
		
		}
		catch(Exception e) {
			e.printStackTrace();
			ReporterLog.fail(method.getName(), "Test Failed with exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	@AfterSuite(alwaysRun = true)
	public void fnAfterSuite() {
		try {
			
			TestSetUp.endSuite();
			if (Boolean.parseBoolean(ConfigDetails.strTestRail))
			TestRailAPIClient.updateTestRun(TestRailTestRunStatus.testrailObj);
		}
		catch(Exception e) {
			e.printStackTrace();
			ReporterLog.fail("AfterSuite", "Configuration failed at Aftersuite with exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}

}

package android.tests;


import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
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
import android.pages.ContentDetailsPage;
import android.pages.DownloadsPage;


public class TestConfiguration {

	public static android.pages.BasePage basePage = new android.pages.BasePage();
	public static android.pages.LoginPage loginPage = new android.pages.LoginPage();
	public static android.pages.DiscoverPage discoverPage = new android.pages.DiscoverPage();
	public static android.pages.ContentDetailsPage contentPage = new android.pages.ContentDetailsPage();
	public static android.pages.PlayerPage playerPage = new android.pages.PlayerPage();
	public static android.pages.SearchPage searchPage = new android.pages.SearchPage();
	public static DownloadsPage downloadPage = new DownloadsPage();
	public static ContentDetailsPage contentdetailspage = new ContentDetailsPage();
	public static android.pages.WatchListPage watchlistPage = new android.pages.WatchListPage();
	private int count= 0;
	public static By MeProf = By.id("tv.hooq.android:id/ivMeBtn");

	@BeforeSuite(alwaysRun = true)
	@Parameters({"Project","SuiteType","country"})
	public void fnBeforeSuite(String project, String suiteType ,String country) {
		try {
			
			TestSetUp.initializeSetUp(project, suiteType,country);	
			
			ReporterLog.info("BeforeSuite",
					"\n==================================================================\n"
							+ "================== Executing BeforeSuite =========================\n"
							+ "==================================================================");
			
			ReadTestData.getTestData(project);					
			TestSetUp.endSetUp();
		}
		catch(Exception e) {
			ReporterLog.fail("beforeSuite", "Configuration failed at BeforeSuite with Exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);

		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"userType","Project","SuiteType"})
	public void fnBeforeTest(String userType,String project, String suiteType) {
		try {

			ConfigDetails.userType = userType;

			ReportsExtent.fnStartTest("fnBeforeTest-"+ConfigDetails.userType, project, suiteType);
			
			ReporterLog.info("BeforeTest",
					"\n==================================================================\n"
							+ "================== Executing BeforeTest ==========================\n"
							+ "==================================================================\n");
			ios.tests.TestConfiguration.isCountryIndia();
			TestDriver.initiateDriverSession();	
//			if(count > 0) {
//				ActionEngine.getAndroidDriver().launchApp();
//			}

			Thread.sleep(10000);

			if(ConfigDetails.targetExecution.equalsIgnoreCase(GlobalConstant.ENVIRONMENT_BROWSERSTACK))
			{
				if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE))
					ReadTestData.ACTIVE_USER_ID=APIUtils.getActiveUserEmail();

				if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED))
					ReadTestData.LAPSED_USER_ID=APIUtils.getLapsedUserEmail();
			}
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE))
				basePage.getLoginPage().performLogin(ReadTestData.ACTIVE_USER_ID);

			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED))
				basePage.getLoginPage().performLogin(ReadTestData.LAPSED_USER_ID);

			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
				//BasePage.fnHandleGetStartedButton();
				basePage.logoutIfAlreadyLoggedIn();



			ReportsExtent.fnEndTest("fnBeforeTest-"+ConfigDetails.userType);
			//ReporterLog.softAssert.assertAll();

		}
		catch(Exception e) {
			if (e.getClass().getSimpleName().contentEquals("SkipException")) {
				ReporterLog.skip("fnBeforeTest",
						"Intentionaly Skiping all tests due to Skip exception in @BeforeTest Method For UserType :"
								+ ConfigDetails.userType + " , Country : " + ConfigDetails.country);
				throw new SkipException("Before Test Failed As this test suite is not valid for this country ");
			}
			ReporterLog.fail("beforeTest", "Configuration failed at BeforeTest with Exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);

		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"userType", "Project", "SuiteType" })
	public void fnBeforeMethod(String userType, String project, String suiteType, Method method) throws IOException {
		try {

			TestSetUp.initializeBeforeTest(method.getName()+"_"+userType,project,suiteType);				

			ReporterLog.info("BeforeMethod",
					"\n==================================================================\n"
							+ "================== Executing BeforeMethod. =========================\n"
							+ "==================================================================");
			
			//Relaunching Android App before each Test case
			ActionEngine.getAndroidDriver().launchApp();
			//FileUtilities.startRecordingScreen();
			count ++;
			ActionEngine.closeInterstitialAd();
//			if (ActionEngine.driver.findElements(MeProf).size() == 0) 
//			{
//				if(ConfigDetails.userType.equalsIgnoreCase("Active"))
//					basePage.getLoginPage().performLogin(ReadTestData.ACTIVE_USER_ID);
//
//				else if(ConfigDetails.userType.equalsIgnoreCase("Lapsed"))
//					basePage.getLoginPage().performLogin(ReadTestData.LAPSED_USER_ID);
//			}

		}
		catch(Exception e) {
			ReporterLog.fail(method.getName(), "Configuration failed at BeforeMethod with Exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is"+e.getStackTrace().toString(), e);

		}		
	}

	@AfterMethod(alwaysRun = true)
	@Parameters({"userType"})
	public void fnAfterMethod(ITestResult result, Method method, String userType) {	
		try {	

			TestSetUp.endAfterMethod(result.getStatus(),method.getName()+"_"+userType);
			
//			String base64Video = FileUtilities.stopRecordingScreen();
//			 Thread t1 = new Thread(new Runnable() {
//				    public void run()
//				    {
//				    	FileUtilities.base64toVideoFile(base64Video, "Android_" + method.getName() + "_" + userType); 
//				    }});  
//				    t1.start();
		}
		catch(Exception e) {
			ReporterLog.fail(method.getName(), "Configuration failed at AfterMethod with Exception : "+e.getStackTrace());
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
			ReporterLog.fail("AfterSuite", "Configuration failed at AfterSuite with Exception : "+e.getStackTrace());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}
}
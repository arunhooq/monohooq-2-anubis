package ios.tests;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.IResultMap;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import io.appium.java_client.ios.IOSDriver;
import ios.pages.BasePage;
import ios.pages.ContentDetailsPage;
import ios.pages.DiscoverPage;
import ios.pages.DownloadsPage;
import ios.pages.HistoryPage;
import ios.pages.LinkTVPage;
import ios.pages.LiveChannelsPage;
import ios.pages.LoginPage;
import ios.pages.MePage;
import ios.pages.MoviesPage;
import ios.pages.MyRentalsPage;
import ios.pages.PlayerPage;
import ios.pages.SearchPage;
import ios.pages.SettingsPage;
import ios.pages.SubscriptionPage;
import ios.pages.SupportPage;
import ios.pages.TVShowsPage;
import ios.pages.TransactionHistoryPage;
import ios.pages.WatchListPage;
import ios.utils.IOSConstants;

public class TestConfiguration {

	public static BasePage basePage = new BasePage();
	public static DiscoverPage discoverPage = new DiscoverPage();
	public static ContentDetailsPage contentDetailsPage = new ContentDetailsPage();
	public static DownloadsPage downloadsPage = new DownloadsPage();
	public static HistoryPage historyPage = new HistoryPage();
	public static LinkTVPage linkTVPage = new LinkTVPage();
	public static LoginPage loginPage = new LoginPage();
	public static MePage mePage = new MePage();
	public static MoviesPage moviesPage = new MoviesPage();
	public static MyRentalsPage myRentalsPage = new MyRentalsPage();
	public static PlayerPage playerPage = new PlayerPage();
	public static SearchPage searchPage = new SearchPage();
	public static SettingsPage settingsPage = new SettingsPage();
	public static SubscriptionPage subscriptionPage = new SubscriptionPage();
	public static SupportPage supportPage = new SupportPage();
	public static TransactionHistoryPage transactionHistoryPage = new TransactionHistoryPage();
	public static TVShowsPage tvShowsPage = new TVShowsPage();
	public static WatchListPage watchListPage = new WatchListPage();
	public static LiveChannelsPage liveChannelPage = new LiveChannelsPage();
	private static int count;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "Project", "SuiteType", "country" })
	public void fnBeforeSuite(String project, String suiteType, String country) {
		try {
			TestSetUp.initializeSetUp(project, suiteType, country);
			ReporterLog.info("BeforeSuite",
					"\n==================================================================\n"
							+ "================== Executing BeforeSuite =========================\n"
							+ "==================================================================");
			ReadTestData.getTestData(project);
			TestSetUp.endSetUp();
		} catch (Exception e) {
			ReporterLog.fail("BeforeSuite", "TestConfiguration Failed at Before Suite");
			e.printStackTrace();
		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "userType", "Project", "SuiteType" })
	public void fnBeforeTest(String userType, String project, String suiteType) {
		try {
			ReportsExtent.fnStartTest("fnBeforeTest-" + ConfigDetails.userType, project, suiteType);
			ReporterLog.info("BeforeTest",
					"\n==================================================================\n"
							+ "================== Executing BeforeTest ==========================\n"
							+ "==================================================================\n");
			ConfigDetails.userType = userType;
			//isCountryIndia();
			TestDriver.initiateDriverSession();
			count = 0;
			ActionEngine.fnHandleNotificationPopup();
			LoginPage.fnHandleBrazePage();
			LoginPage.fnHandleAdvertisement();

			if (ConfigDetails.targetExecution.equalsIgnoreCase(IOSConstants.BROWSERSTACK)) {
				if (ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE))
					ReadTestData.ACTIVE_USER_ID = APIUtils.getActiveUserEmail();

				if (ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED))
					ReadTestData.LAPSED_USER_ID = APIUtils.getLapsedUserEmail();
			}
			if (ConfigDetails.userType.equals(GlobalConstant.USERTYPE_ACTIVE)) {
				basePage.getLoginPage().performEmailLogin(ReadTestData.ACTIVE_USER_ID, ReadTestData.PASSWORD);
			} else if (ConfigDetails.userType.equals(GlobalConstant.USERTYPE_LAPSED))
				basePage.getLoginPage().performEmailLogin(ReadTestData.LAPSED_USER_ID, ReadTestData.PASSWORD);
			ReporterLog.info("BeforeTest", "Before Test Method");
			ReportsExtent.fnEndTest("fnBeforeTest-" + ConfigDetails.userType);

		} catch (Exception e) {
			if (e.getClass().getSimpleName().contentEquals("SkipException")) {
				ReporterLog.skip("fnBeforeTest",
						"Intentionaly Skiping all tests due to Skip exception in @BeforeTest Method For UserType :"
								+ ConfigDetails.userType + " , Country : " + ConfigDetails.country);
				throw new SkipException("Before Test Failed As this test suite is not valid for this country ");
			} else {
				e.printStackTrace();
				ReporterLog.fail("fnBeforeTest", "Configuration Fail at Before Test");
			}

		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "userType", "Project", "SuiteType" })
	public void fnBeforeMethod(String userType, String project, String suiteType, Method method) {

		TestSetUp.initializeBeforeTest(method.getName() + "_" + userType, project, suiteType);
		ReporterLog.info("BeforeMethod",
				"\n==================================================================\n"
						+ "================== Executing BeforeMethod. =========================\n"
						+ "==================================================================");
		ActionEngine.getIOSDriver().launchApp();
		FileUtilities.startRecordingScreen();
		LoginPage.fnHandleBrazePage();
		LoginPage.fnHandleAdvertisement();
	}

	@AfterMethod(alwaysRun = true)
	@Parameters({ "userType" })
	public void fnAfterMethod(ITestResult result, Method method, String userType) {
		try {
			TestSetUp.endAfterMethod(result.getStatus(), method.getName() + "_" + userType);
			
			String base64Video = FileUtilities.stopRecordingScreen();
			 Thread t1 = new Thread(new Runnable() {
				    public void run()
				    {
				    	FileUtilities.base64toVideoFile(base64Video, "IOS_" + method.getName() + "_" + userType); 
				    }});  
				    t1.start();
			if (result.getStatus() == ITestResult.FAILURE) {
				count++;
			}

		} catch (Exception e) {
			count++;
			ReporterLog.fail(method.getName(), "Test Failed with Exception : " + e.getCause());
			e.printStackTrace();
		}
	}

	@AfterTest(alwaysRun = true)
	public void fnAfterTest() {
		System.out.println("\n==================================================================\n"
				+ "================== Executing AfterTest =========================\n"
				+ "==================================================================");	
		try {
			if (!(count >= 0)) {
				APIUtils.setBrowserStackSessionStatus(GlobalConstant.BROWSERSTACK_SESSION_ID, "passed");
			} else {
				APIUtils.setBrowserStackSessionStatus(GlobalConstant.BROWSERSTACK_SESSION_ID, "failed");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ActionEngine.driver != null)
			ActionEngine.driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void fnAfterSuite() {
		try {
			TestSetUp.endSuite();
			if (Boolean.parseBoolean(ConfigDetails.strTestRail))
				TestRailAPIClient.updateTestRun(TestRailTestRunStatus.testrailObj);
		} catch (Exception e) {

			ReporterLog.fail("AfterSuite", "AfterSuite Failed with Expception : " + e.getStackTrace());
			e.printStackTrace();
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}

	public static void isCountryIndia() {
		if (ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN)
				&& ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)) {
			throw new SkipException(
					"Skipping this Test as Visitor are not allowed to view Discover Page for India Country");
		}
	}

}

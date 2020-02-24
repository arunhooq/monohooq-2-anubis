package web.tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
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

import web.utils.webConstants;


public class TestConfiguration {
	public static web.pages.BasePage basePage = new web.pages.BasePage();

	public static int reTryCount;
	private static int count;

	private static Boolean success = false;

	@BeforeSuite(alwaysRun = true)
	@Parameters({"Project", "SuiteType","country" })
	public void fnBeforeSuite(String project, String suiteType ,String country) {


		try {
			TestSetUp.initializeSetUp(project, suiteType,country);
			ReporterLog.info("BeforeSuite", "===========================> Executing the 'fnBeforeSuite' block");
			switch(ConfigDetails.environment){
			case "prod": ConfigDetails.strURL = "https://www.hooq.tv"; break;
			case "preprod": ConfigDetails.strURL = "https://web-preprod.hooq.tv"; break;
			case "nightly": ConfigDetails.strURL = "https://web-nightly.hooq.tv"; break;
			case "nightly1": ConfigDetails.strURL = "https://web-nightly1.hooq.tv"; break;
			case "nightly2": ConfigDetails.strURL = "https://web-nightly2.hooq.tv"; break;
			case "nightly3": ConfigDetails.strURL = "https://web-nightly3.hooq.tv"; break;
			case "nightly4": ConfigDetails.strURL = "https://web-nightly4.hooq.tv"; break;
			default: ConfigDetails.strURL = "https://www.hooq.tv"; break;
			}

			if(ConfigDetails.environment.equalsIgnoreCase("preprod"))
				ConfigDetails.environment = "prod"; //After the URL allocation is done, setting the environment value for preprod as per evergent to call API with prod
			else if (ConfigDetails.environment.equalsIgnoreCase("nightly")
					|| ConfigDetails.environment.equalsIgnoreCase("nightly1")
					|| ConfigDetails.environment.equalsIgnoreCase("nightly2")
					|| ConfigDetails.environment.equalsIgnoreCase("nightly3")
					|| ConfigDetails.environment.equalsIgnoreCase("nightly4"))
				ConfigDetails.environment = "stag"; 

			ReadTestData.getTestData(project);
			

			TestSetUp.endSetUp();
			ReporterLog.info("BeforeSuite", "===========================> Finished executing the 'fnBeforeSuite' block");
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);

		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"userType","Project", "SuiteType"})
	public void fnBeforeTest(String userType,String project, String suiteType) throws Exception {

		ConfigDetails.userType = userType;
		TestDriver.initiateDriverSession();
		success = false;			
		reTryCount = 0;
		count=0;
		FileUtilities.startRecordingScreen();
		while (success == false && (reTryCount < Integer.parseInt(ConfigDetails.strMaxRetryCount))) {
			ReporterLog.info( "BeforeTest","===========================> Executing the 'fnBeforeTest' block");	
			try {	
				beforeTestGuts_NEW(userType,project,suiteType);
			}
			catch (Exception e) {

				//e.printStackTrace();
				System.out.println("*************** EXCEPTION CAUGHT ****************");
				if (reTryCount < Integer.parseInt(ConfigDetails.strMaxRetryCount)) {
					reTryCount ++;
					ReporterLog.info("BeforeTest", "Exception occured in BeforeTest.class Retrying. Attempt "+reTryCount +"/"+ConfigDetails.strMaxRetryCount);
					ActionEngine.driver.navigate().refresh();
					//beforeTestGuts_NEW(userType,project,suiteType);		

				} else {
					ReporterLog.fail("Beforetest", "Configuration failed at BeforeTest with Exception : "+e.getStackTrace());
					Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
							+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
				}	
			}
		}
		
		String base64Video = FileUtilities.stopRecordingScreen();
		 Thread t1 = new Thread(new Runnable() {
			    public void run()
			    {
			    	FileUtilities.base64toVideoFile(base64Video, "PWA_"+userType+"_BeforeTest"); 
			    }});  
			    t1.start();
	}

/*
	public void  beforeTestGuts(String userType,String project, String suiteType) {
		ConfigDetails.userType = userType;
		webConstants.geoLocationFailed = false;
		ReportsExtent.fnStartTest("fnBeforeTest-"+ConfigDetails.userType, project, suiteType);

		ReporterLog.info("Attempting to launch url: "+ConfigDetails.strURL, "");
		ActionEngine.launchUrl(ConfigDetails.strURL);
		By remindMeButton = By.cssSelector(".ab-message-buttons button:first-of-type");

		if(ActionEngine.isNumOf_Elements_Greater_Than_Zero(remindMeButton))
			ActionEngine.click(remindMeButton, "remindMeButton");

		if(ConfigDetails.userType.equalsIgnoreCase("Active")) {
			basePage.getLoginPage().enterUserDetails(ReadTestData.ACTIVE_USER_ID).enterPassword(ReadTestData.PASSWORD).checkIfUserLoggedIn();
		}
		else if(ConfigDetails.userType.equalsIgnoreCase("Lapsed")) {
			basePage.getLoginPage().enterUserDetails(ReadTestData.LAPSED_USER_ID).enterPassword(ReadTestData.PASSWORD).checkIfUserLoggedIn();
		} else if(ConfigDetails.userType.equalsIgnoreCase("Visitor")) {
			basePage.performLogout_ifLoggedIn();
		}


		ReporterLog.info("BeforeTest","===========================> Finished executing the 'fnBeforeTest' block");

		ReportsExtent.fnEndTest("fnBeforeTest-"+ConfigDetails.userType);
		ReporterLog.softAssert.assertAll();		
	}
*/
	public void  beforeTestGuts_NEW(String userType,String project, String suiteType) throws Exception {
		ConfigDetails.userType = userType;
		webConstants.geoLocationFailed = false;
		ReportsExtent.fnStartTest("fnBeforeTest-"+ConfigDetails.userType, project, suiteType);

		ReporterLog.info("Attempting to launch url: "+ConfigDetails.strURL, "");
		ActionEngine.launchUrl_NEW(ConfigDetails.strURL);
		By remindMeButton = By.cssSelector(".ab-message-buttons button:first-of-type");

		if(ActionEngine.isNumOf_Elements_Greater_Than_Zero(remindMeButton))
			ActionEngine.click_NEW(remindMeButton, "remindMeButton");

		//Logging into the application
		loginScenario();
		success = true;


		ReporterLog.info("BeforeTest","===========================> Finished executing the 'fnBeforeTest' block");

		ReportsExtent.fnEndTest("fnBeforeTest-"+ConfigDetails.userType);
		//ReporterLog.softAssert.assertAll();		
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"userType","Project", "SuiteType"})
	public void fnBeforeMethod(String userType, String project, String suiteType, Method method) throws Exception {
		ReporterLog.info( "BeforeMethod","===========================> Executing the 'fnBeforeMethod' block");	
		success = false;			
		reTryCount = 0;
		
		createNewSessionIfNotAvailable();
		
		//FileUtilities.startRecordingScreen();
		while (success == false && (reTryCount < Integer.parseInt(ConfigDetails.strMaxRetryCount))) {
			try {	
				beforeMethodGuts(userType,project,suiteType,method);
			}
			catch (Exception e) {

				//e.printStackTrace();
				System.out.println("*************** EXCEPTION CAUGHT ****************");
				if (reTryCount < Integer.parseInt(ConfigDetails.strMaxRetryCount)) { 
					reTryCount ++;
					ReporterLog.info("BeforeTest", "Exception occured in BeforeMethod class - Retrying. Attempt "+reTryCount +"/"+ConfigDetails.strMaxRetryCount);
					ActionEngine.driver.navigate().refresh();	

				} else {
					ReporterLog.fail("Beforetest", "Configuration failed at BeforeTest with Exception : "+e.getStackTrace());
					Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
							+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
				}	
			}
		}			
	}

	public void beforeMethodGuts (String userType, String project, String suiteType, Method method) throws Exception {
		ReporterLog.info("BeforeMethod", "===========================> Executing the 'fnBeforeMethod' block");
		
		TestSetUp.initializeBeforeTest_NEW(method.getName().toString()+"_"+userType,project,suiteType);
		//if (webConstants.geoLocationFailed == false) {
		ActionEngine.launchUrl_NEW(ConfigDetails.strURL);
		By remindMeButton = By.cssSelector(".ab-message-buttons button:first-of-type");

		if(ActionEngine.isNumOf_Elements_Greater_Than_Zero(remindMeButton))
			ActionEngine.click_NEW(remindMeButton, "remindMeButton");
		//} else {
		//	ReporterLog.info("BeforeMethod", "Skipping execution of BeforeMethod due to GeoLocation Validation failure");
		//}
		success = true;
		ReporterLog.info("BeforeTest", "===========================> Finished executing the 'fnBeforeMethod' block");


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
//				    	FileUtilities.base64toVideoFile(base64Video, "PWA_"+method.getName()+ "_" + userType); 
//				    	System.out.println("Video Saved successfully ....");
//				    }});  
//				    t1.start();

		}
		catch(Exception e) {
			count++;
			e.printStackTrace();
			ReporterLog.fail("AfterMethod", "Configuration failed at AfterMethod with Exception : "+e.getStackTrace().toString());	
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	@AfterTest(alwaysRun = true)
	public void fnAfterTest() {
		System.out.println("\n==================================================================\n" + 
				"================== Executing AfterTest =========================\n" + 
				"==================================================================");
		if (ActionEngine.driver != null)
			ActionEngine.driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void fnAfterSuite() {
		try {

			TestSetUp.endSuite();
			if (Boolean.parseBoolean(ConfigDetails.strTestRail))
				TestRailAPIClient.updateTestRun(TestRailTestRunStatus.testrailObj);

		}
		catch(Exception e) {
			ReporterLog.fail("AfterSuite", "Configuration failed at AfterSuite with Exception : "+e.getLocalizedMessage());
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}
	
	public static void createNewSessionIfNotAvailable() {
		try {
			if(ActionEngine.driver == null) {
				ReporterLog.info("validate Session", "Session is not available hence creating new session ..");
				TestDriver.initiateDriverSession();
				System.out.println("A new Session Created hence Loging into the application again ...");
				
				ActionEngine.launchUrl_NEW(ConfigDetails.strURL);
				//Logging into the application
				loginScenario();
			} else {
				//ReporterLog.info("validate Session", "Session is available : "+ActionEngine.driver.getSessionId());
			}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	private static void loginScenario() throws Exception {
		try {
		if(ConfigDetails.userType.equalsIgnoreCase("Active")) {
			basePage.getLoginPage_NEW().enterUserDetails_NEW(ReadTestData.ACTIVE_USER_ID).enterPassword_NEW(ReadTestData.PASSWORD).checkIfUserLoggedIn();
		}
		else if(ConfigDetails.userType.equalsIgnoreCase("Lapsed")) {
			basePage.getLoginPage_NEW().enterUserDetails_NEW(ReadTestData.LAPSED_USER_ID).enterPassword_NEW(ReadTestData.PASSWORD).checkIfUserLoggedIn();
		} else if(ConfigDetails.userType.equalsIgnoreCase("Visitor")) {
			basePage.performLogout_ifLoggedIn_NEW();
		}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in Login scenario");
		}
	}

}

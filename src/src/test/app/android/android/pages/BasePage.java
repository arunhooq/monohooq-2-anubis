package android.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import io.appium.java_client.android.AndroidDriver;

public class BasePage extends ActionEngine{

	public static By MeProf = By.id("tv.hooq.android:id/ivMeBtn");
	public static By logOut = By.xpath("//android.widget.TextView[@text='Log out']");
	public static By confirmlogout = By.xpath("//android.widget.TextView[@text='Confirm']");
	public static By LoginForIN = By.id("tv.hooq.android:id/accountLogin"); 
	public static By LoginForOtherRegions = By.id("tv.hooq.android:id/loginButton");
	public static By dontHavAccount = By.id("tv.hooq.android:id/signup");
	public static By skipMobileSignup = By.id("tv.hooq.android:id/skip");
	public static By signUp = By.id("tv.hooq.android:id/joinFree");
	public static By getStarted = By.id("tv.hooq.android:id/get_started");
	public static By gmailInfo = By.xpath("//android.view.View[@resource-id='headingText']");


	public static String strBuildNo = "";
	public enum DIRECTION {
		DOWN, UP, LEFT, RIGHT;
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date dt = null;
	String beforelogin = null;
	String afterLogin = null;
	public static boolean FirstLogin = true;

	public  LoginPage getLoginPage() {

		//ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {   

			logoutIfAlreadyLoggedIn();

			if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN)) 
			{
				closeInterstitialAd();
				click(LoginForOtherRegions, "Login button on Freemium page");
				Thread.sleep(5000);
				
				if(isNumOf_Elements_Greater_Than_Zero(gmailInfo))
				{
					ReporterLog.info("Google Login info", "Google Login info page is displayed");
					ActionEngine.driver.navigate().back(); // For other countries, the gmail pop up is appearing at this stage.
				}			
				
//				else
//				{
//					ReporterLog.info("Google Login info", "Google Login info page is not displayed");
//					List<WebElement> eleList1 = driver.findElements(By.xpath(
//							"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));
//					if (eleList1.size() > 0) {
//						eleList1.get(0).click();
//					}	
//				}
			}
			else {
				click(LoginForIN, "Login option");
				if(isElementPresentInDom(gmailInfo))
					ActionEngine.driver.navigate().back();
			}
		}
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public  void getSignUpPage() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {    		
			logoutIfAlreadyLoggedIn();
			if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN)) 
			{
				click(LoginForOtherRegions, "Login option");
				// To remove the default email popup
				List<WebElement> eleList1 = driver.findElements(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));

				if (eleList1.size() > 0) {
					eleList1.get(0).click();
				}
				click(dontHavAccount, "Don't have an account? Sign up!");	
				click(skipMobileSignup, "Skip mobile Sign up");

				// To remove the default email popup
				List<WebElement> eleList2 = driver.findElements(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));

				if (eleList2.size() > 0) {
					eleList2
					.get(0).click();
				}
			}
			else{
				click(signUp, "Sign Up");	
				click(skipMobileSignup, "Skip mobile Sign up");
				List<WebElement> eleList2 = driver.findElements(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));
				
				if (eleList2.size() > 0) {
					eleList2
					.get(0).click();
				}
			}


		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	public void logoutIfAlreadyLoggedIn() {
		try {

			((AndroidDriver<?>)ActionEngine.driver).launchApp();
			closeInterstitialAd();
			/*if(!ConfigDetails.country.equalsIgnoreCase("IN")) {
				if (ActionEngine.driver.findElements(getStarted).size() > 0) 
					click(getStarted, "Get Started");
				closeInterstitialAd();
			}*/
			//waitForVisibilityOfElement(MeProf, "Me Profile");
			Thread.sleep(5000);
			if (ActionEngine.driver.findElements(MeProf).size() > 0) {
				click(MeProf, "Me Profile");
				for (int i = 1; i <= 2; i++) {
					swipeUpOrBottom(true);    				
				}
				click(logOut, "ME icon");
				click(confirmlogout, "Confirm Logout");
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

	}

	public static void fnHandleGetStartedButton() {
		try {
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(getStarted)) {
				click(getStarted, "Get Started Button");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}






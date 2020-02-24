package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class ATVWelcomePage extends ActionEngine {
	
	public static By logo = By.id("tv.hooq.android:id/logo");
	public static By signinButton = By.id("tv.hooq.android:id/signin");
	public static By signupnButton = By.id("tv.hooq.android:id/signup");

	public void verifyLogo() {
		try {
			isElementDisplayed(logo);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public ATVSigninPage clickSigninButton() {
		try {
			click(signinButton, "signinButton");
			ReporterLog.pass("clickLoginButton", "Successfully clicked on Sign In Button");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ATVSigninPage();
	}
	
	public ATVSigninPage clickSignupButton() {
		try {
			click(signupnButton, "signupnButton");
			ReporterLog.pass("clickSignupButton", "Successfully clicked on Sign Up Button");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ATVSigninPage();
	}
}
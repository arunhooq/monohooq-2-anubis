package androidtv2.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class WelcomePage extends ActionEngine {
	
	public static By signinButton = By.id("tv.hooq.android:id/loginButton");
	public static By signupnButton = By.id("tv.hooq.android:id/signUpButton");
	
	public SigninPage clickSigninButton() {
		try {
			click(signinButton, "signinButton");
			ReporterLog.pass("clickLoginButton", "Successfully clicked on Sign In Button");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SigninPage();
	}
	
	public SigninPage clickSignupButton() {
		try {
			click(signupnButton, "signupnButton");
			ReporterLog.pass("clickSignupButton", "Successfully clicked on Sign Up Button");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SigninPage();
	}
}

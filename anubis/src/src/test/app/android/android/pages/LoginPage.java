package android.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class LoginPage extends ActionEngine {
	public static By addnewemail1 = By.id("tv.hooq.android:id/emailInput");
	public static By emailDone = By.id("tv.hooq.android:id/emailDone");
	public static By HOOQLogo = By.xpath("//*[@resource-id='tv.hooq.android:id/headerLogo']");
	public static By iHaveVerifiedBtn = By.xpath("//android.widget.Button[@text='I have verified']");
	public static By lnkpassword = By.id("tv.hooq.android:id/password");
	public static By pwdPageHeader = By
			.xpath("//*[@text='ENTER HOOQ PASSWORD'][@resource-id='tv.hooq.android:id/title']");
	public static By txtPassword = By.id("tv.hooq.android:id/input");
	public static By btnPwdOkay = By.id("tv.hooq.android:id/confirm");
	public static By btnSingtelCastLogin = By.id("tv.hooq.android:id/singtelCast");
	public static By wiFiPopUp = By.className("android.app.Dialog");
	public static By btnStayOnWifi = By.xpath("//android.widget.Button[@text='No, stay on WiFi']");
	public static By btnLogin = By.xpath("//android.widget.Button[@resource-id='loginButton']");
	public static By gmailInfo = By.xpath("//android.view.View[@resource-id='headingText']");
	public static By imgMeIcon = By.id("tv.hooq.android:id/ivMeBtn");
	public static By txtLOGIN = By.id("tv.hooq.android:id/loginButton");
	public DiscoverPage performLogin(String email) {
		try {

			try {
				// Wait for Login Button visibility
				waitTillElementPresent_HardWait_Polling(addnewemail1, GlobalConstant.WAIT_SHORT_5_SEC);

				// Click on the Login button
				click(addnewemail1, "Email Textbox");
				Thread.sleep(3000);
				if (isNumOf_Elements_Greater_Than_Zero(gmailInfo)) {
					ReporterLog.info("Google Login info", "Google Login info page is displayed");
					ActionEngine.driver.navigate().back(); // For other countries, the gmail pop up is appearing at this
															// stage.
					waitTillElementPresent_HardWait_Polling(addnewemail1, GlobalConstant.WAIT_SHORT_5_SEC);
					click(addnewemail1, "Email Textbox");
				}
				
				int flag = 0;
				while (!isNumOf_Elements_Greater_Than_Zero(addnewemail1)) {
					driver.navigate().back();
					if (flag == 3) {
						break;
					}
					flag++;
				}

				type(addnewemail1, email, "Email Address");
				Thread.sleep(3000);
				click(emailDone, "Done button");

				if (ConfigDetails.targetExecution.equalsIgnoreCase(GlobalConstant.ENVIRONMENT_BROWSERSTACK)) {
					waitTillElementPresent_HardWait_Polling(lnkpassword, GlobalConstant.WAIT_SHORT_5_SEC);
					click(lnkpassword, "Enter Password link");
					type(txtPassword, "123456", "Password");
					click(btnPwdOkay, "Okay");
				} else {

					if (ActionEngine.driver.findElements(lnkpassword).size() > 0) {
						click(lnkpassword, "Enter Password link");
						type(txtPassword, "123456", "Password");
						click(btnPwdOkay, "Okay");
					}
				}
			} catch (Exception e) {e.printStackTrace();}

			waitTillElementPresent_HardWait_Polling(imgMeIcon, GlobalConstant.WAIT_SHORT_5_SEC);
			// Check if Me Icon is not visible then retry login
			if (!isElementPresentInDom(imgMeIcon)) {
				ReporterLog.infoWithScreenshot("Login Validation", "Login was unSuccessful hence Retrying ...");
				int count = 1;
				while (!isLoginSuccess(email)) {
					count++;
					ReporterLog.info("Login Auto Recovery Method Validation", "Retry Login Attempt : " + count + "/5");
					if (count == 5) {
						break;
					}
				}

			} else {
				ReporterLog.pass("Login Validation", "Login was Successful..");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DiscoverPage();
	}

	public DiscoverPage performSingtelCastLogin(String email, String pwd) {
		try {

			click(btnSingtelCastLogin, "SingtelCast login");
			Thread.sleep(3000);
			if (isElementPresent(wiFiPopUp)) {
				click(btnStayOnWifi, "Stay on WiFi");
			}
			List<WebElement> singtelLoginPwd = driver.findElements(By.className("android.widget.EditText"));
			if (singtelLoginPwd.size() > 0) {
				singtelLoginPwd.get(0).sendKeys(email);
				singtelLoginPwd.get(1).sendKeys(pwd);
				click(btnLogin, "Login");
			}
			waitForVisibilityOfElement(HOOQLogo, "HOOQ Logo");
			// closeInterstitialAd();

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DiscoverPage();
	}

	// This method will be worked as AutoRecovery of login
	public static boolean isLoginSuccess(String email) throws InterruptedException {
		try {
			ActionEngine.getAndroidDriver().launchApp();
			if (!isElementPresentInDom(imgMeIcon)) {

				if (ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN)) {
					click(BasePage.LoginForIN, "Login option");
				} else {
					waitTillElementPresent_HardWait_Polling(txtLOGIN, GlobalConstant.WAIT_SHORT_10_SEC);
					click(txtLOGIN, "LOGIN Label");
				}

				waitTillElementPresent_HardWait_Polling(addnewemail1, GlobalConstant.WAIT_SHORT_5_SEC);
				// Click on the Login button
				click(addnewemail1, "Email Textbox");
				Thread.sleep(3000);
				if (isNumOf_Elements_Greater_Than_Zero(gmailInfo)) {
					ReporterLog.info("Google Login info", "Google Login info page is displayed");
					ActionEngine.driver.navigate().back(); // For other countries, the gmail pop up is appearing at this
															// stage.
					waitTillElementPresent_HardWait_Polling(addnewemail1, GlobalConstant.WAIT_SHORT_5_SEC);
					click(addnewemail1, "Email Textbox");
				}

				type(addnewemail1, email, "Email Address");
				Thread.sleep(3000);
				click(emailDone, "Done button");

				// For Browserstack we dont need to skip Password functionality because we use
				// New email each time
				if (ConfigDetails.targetExecution.equalsIgnoreCase(GlobalConstant.ENVIRONMENT_BROWSERSTACK)) {
					waitTillElementPresent_HardWait_Polling(lnkpassword, GlobalConstant.WAIT_SHORT_5_SEC);
					click(lnkpassword, "Enter Password link");
					type(txtPassword, "123456", "Password");
					click(btnPwdOkay, "Okay");
				} else {

					if (ActionEngine.driver.findElements(lnkpassword).size() > 0) {
						click(lnkpassword, "Enter Password link");
						type(txtPassword, "123456", "Password");
						click(btnPwdOkay, "Okay");
					}
				}
				waitTillElementPresent_HardWait_Polling(imgMeIcon, GlobalConstant.WAIT_SHORT_5_SEC);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementPresentInDom(imgMeIcon);
	}
}

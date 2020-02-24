package ios.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import ios.utils.IOSConstants;

public class LoginPage extends ActionEngine {

	public static By Login = By.name("Login");
	public static By txtLoginTitle = By.name("LOGIN");
	public static By tabMobile = By.xpath("//XCUIElementTypeButton[@name='Mobile']");
	public static By addnewemail = By.xpath("//XCUIElementTypeTextField");
	public static By emailDone = By.name("Done");
	public static By btnIhaveVerified = By.id("I have verified");
	public static By subPaymentPopUpEnterPassword = By.id("Enter Password");
	public static By subPaymentPopUpCancel = By.id("Cancel");
	public static By loginOKay = By.id("Okay");
	public static By SignInPassword = By.xpath(".//XCUIElementTypeSecureTextField");
	public static By meLabel = By.id("icon me");
	public static By logoutButton = By.name("Logout");
	public static By confirm = By.name("Confirm");
	public static By log_in = By.name("LOG IN");
	public static By linkResendEmail = By.id("Resend Email");
	public static By inputMobileNumber = By.xpath(".//XCUIElementTypeOther/XCUIElementTypeTextField[2]");
	public static By btnYes = By.name("YES");
	public static By txtOTPVerificationTitle = By.id("OTP VERIFICATION");
	public static By listInputOTP = By.xpath(
			"//XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By btnNext = By.name("Next");
	public static By btnSkip = By.id("Skip");
	// public static By btnSignUp =
	// ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG) ?
	// By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/following::XCUIElementTypeButton[3]"):By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/following::XCUIElementTypeButton[2]");

	public static By getSignUpButton() {
		By btnSignUp;
		if (ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG)) {
			btnSignUp = By.xpath(
					"//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/following::XCUIElementTypeButton[3]");

		} else {
			btnSignUp = By.xpath(
					"//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/following::XCUIElementTypeButton[2]");
		}
		return btnSignUp;
	}

	public DiscoverPage performEmailLogin(String strUserID, String strPassword) {
		try {
			type(addnewemail, strUserID, "emailField");
			click(emailDone, "Done");

			// No need to fail Test if Password field is not visible for already logged In
			// users
			try {
				Thread.sleep(3000);
				// explicitWaitForVisibilityOfElement(subPaymentPopUpEnterPassword,
				// GlobalConstant.WAIT_MEDIUM_20_SEC);
				waitTillElementPresent_HardWait_Polling(subPaymentPopUpEnterPassword, GlobalConstant.WAIT_SHORT_10_SEC,
						"linkEnterPassword");
			} catch (Exception e) {
				ReporterLog.info("Check for Password field",
						"Password Field is not visible Hence Continue Execution for already Logged in User");
			}
			// Need to login with password for new user
			if (isNumOf_Elements_Greater_Than_Zero(subPaymentPopUpEnterPassword)) {
				click(subPaymentPopUpEnterPassword, "Enter Password link");
				waitTillElementPresent_HardWait_Polling(SignInPassword, GlobalConstant.WAIT_SHORT_4_SEC,
						"Login Password inputbox");
				click(SignInPassword, "Login Password inputbox");
				typeNoWait(SignInPassword, "123456", "Login Password inputbox");
				click(loginOKay, "Okay Button");

				fnHandleBrazePage();
				waitTillElementPresent_HardWait_Polling(meLabel, GlobalConstant.WAIT_SHORT_10_SEC);

				if(isElementPresentInDom(subPaymentPopUpEnterPassword)) { //Handle double Click on the enter password link if one time login fails
					click(subPaymentPopUpEnterPassword, "Enter Password link");
					waitTillElementPresent_HardWait_Polling(SignInPassword, GlobalConstant.WAIT_SHORT_4_SEC,
							"Login Password inputbox");
					click(SignInPassword, "Login Password inputbox");
					typeNoWait(SignInPassword, "123456", "Login Password inputbox");
					click(loginOKay, "Okay Button");
					waitTillElementPresent_HardWait_Polling(meLabel, GlobalConstant.WAIT_SHORT_4_SEC,"Me Label");	
					}
				
				if (!isElementPresentInDom(meLabel, "Me Label")) {
					if (!isLoginSuccessfull(strUserID)) {
						isLoginSuccessfull(strUserID);
					}
				}
			} else {
				if (!isLoginSuccessfull(strUserID)) {
					isLoginSuccessfull(strUserID);
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DiscoverPage();
	}

	public static boolean isLoginSuccessfull(String strUserID) throws InterruptedException {

		((IOSDriver<?>) ActionEngine.driver).launchApp();
		ReporterLog.info("Relaunching App", "Application relaunch success ... ");
		if (!ConfigDetails.country.equalsIgnoreCase("IN")) {
			Thread.sleep(10000);
			LoginPage.fnHandleBrazePage();
			if (isNumOf_Elements_Greater_Than_Zero(BasePage.freemiumLogin)) {
				LoginPage.fnHandleBrazePage();
				click(BasePage.freemiumLogin, "Login");
			}
		} else {
			click(Login, "logInButton");
		}
		type(addnewemail, strUserID, "emailField");
		click(emailDone, "Done");
		waitTillElementPresent_HardWait_Polling(subPaymentPopUpEnterPassword, GlobalConstant.WAIT_SHORT_5_SEC,
				"Password Pop up");
		if (isNumOf_Elements_Greater_Than_Zero(subPaymentPopUpEnterPassword)) {
			click(subPaymentPopUpEnterPassword, "Enter Password link");
			type(SignInPassword, "123456", "Login Password");
			click(loginOKay, "Enter Password");

			fnHandleBrazePage();
			waitTillElementPresent_HardWait_Polling(meLabel, GlobalConstant.WAIT_SHORT_10_SEC, "Me Icon");
		}
		return isElementPresent(meLabel);
	}

	public LoginPage UnsuccessfulLoginWithIncorrectUserName(String strUserID) {
		try {
			type(addnewemail, strUserID, "emailField");
			click(emailDone, "Done");
			if (isNumOf_Elements_Greater_Than_Zero(subPaymentPopUpEnterPassword)) {
				ReporterLog.fail("IncorrectUserNameForUnsuccessfulLogin", "Validation Failed for Incorrect UserName");
				throw new Exception("Validation failed for Inccorect UserName");
			} else {
				ReporterLog.pass("IncorrectUserNameForUnsuccessfulLogin", "Validation Passed for Incorrect UserName");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public LoginPage UnsuccessfulLoginWithIncorrectPassword(String strUserID, String strPassword) {
		try {
			type(addnewemail, strUserID, "emailField");
			click(emailDone, "Done");
			if (isNumOf_Elements_Greater_Than_Zero(subPaymentPopUpEnterPassword)) {
				click(subPaymentPopUpEnterPassword, "Enter Password");
				type(SignInPassword, strPassword, "Login Password");
				click(loginOKay, "Enter Password");
			} else {
				throw new Exception("Email Verification page did not appeared .");
			}
			if (isNumOf_Elements_Greater_Than_Zero(meLabel)) {

				ReporterLog.fail("IncorrectPasswordForUnsuccessfulLogin", "Validation Failed for Incorrect Password");
				throw new Exception("Validation failed for Inccorect Password");
			} else {
				ReporterLog.pass("IncorrectPasswordForUnsuccessfulLogin", "Validation Passed for Incorrect Password");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public LoginPage UnsuccessfulLoginWithIncorrectOTP(String strUserID) {
		try {
			LoginPage.fnHandleBrazePage();
			type(addnewemail, strUserID, "emailField");
			click(emailDone, "Done");
			if (isNumOf_Elements_Greater_Than_Zero(subPaymentPopUpEnterPassword)) {
				click(btnIhaveVerified, "I have Verified button");
			} else {
				throw new Exception("Password Pop up did not appeared .");
			}
			if (isNumOf_Elements_Greater_Than_Zero(meLabel)) {

				ReporterLog.fail("IncorrectEmailVerificationForUnsuccessfulLogin",
						"Validation Failed for Incorrect Email Verification");
				throw new Exception("Validation failed for Inccorect Password");
			} else {
				ReporterLog.pass("IncorrectEmailVerificationForUnsuccessfulLogin",
						"Validation Passed for Incorrect Email Verification");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public LoginPage UnsuccessfulLoginWithIncorrectMobile(String strMobileNumber) {
		try {
			LoginPage.fnHandleBrazePage();
			click(tabMobile, "Click on the Mobiletab");
			if (isNumOf_Elements_Greater_Than_Zero(inputMobileNumber)) {
				type(inputMobileNumber, strMobileNumber, "Mobile Number");
				click(emailDone, "Done");
			}
			if (isNumOf_Elements_Greater_Than_Zero(meLabel)) {

				ReporterLog.fail("IncorrectMobileNumberForUnsuccessfulLogin",
						"Validation Failed for Incorrect Mobile Number");
				throw new Exception("Validation failed for Inccorect Mobile Number");
			} else {
				ReporterLog.pass("IncorrectMobileNumberForUnsuccessfulLogin",
						"Validation Passed for Incorrect Mobile Number");
			}

		} catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public LoginPage UnsuccessfulLoginWithIncorrectMobileOTP(String strMobileNumber) {
		try {
			LoginPage.fnHandleBrazePage();
			click(tabMobile, "Click on the Mobiletab");
			if (isNumOf_Elements_Greater_Than_Zero(inputMobileNumber)) {
				type(inputMobileNumber, strMobileNumber, "Mobile Number");
				click(emailDone, "Done");
				waitForVisibilityOfElement(btnYes, "PopUp");
				click(btnYes, "YES");
			}

			// Validate Incorrect OTP
			List<MobileElement> ele = getMobileDriver().findElements(listInputOTP);

			for (MobileElement e : ele) {

				int i = 1;
				System.out.println("Inside For loop");
				e.sendKeys(String.valueOf(i));
				i++;
			}

			click(btnNext, "Click Next button");

			// Validate Login not successful
			if (isNumOf_Elements_Greater_Than_Zero(meLabel)) {

				ReporterLog.fail("IncorrectMobileOTPForUnsuccessfulLogin",
						"Validation Failed for Incorrect Mobile OTP");
				throw new Exception("Validation failed for Inccorect Mobile OTP");
			} else {
				ReporterLog.pass("IncorrectMobileOTPForUnsuccessfulLogin",
						"Validation Passed for Incorrect Mobile OTP");
			}

		} catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public LoginPage logout() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(meLabel)) {
				click(meLabel, "Me Icon");

				if (isNumOf_Elements_Greater_Than_Zero(logoutButton)) {
					click(logoutButton, "Logout Button");
					click(confirm, "Confirm");
				}
			}

			waitForVisibilityOfElement(log_in, "Login button");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	public static void fnHandleBrazePage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		if (isNumOf_Elements_Greater_Than_Zero(
				By.xpath("//*[@name = '" + FileUtilities.strGetLocaleText(IOSConstants.BRAIZE_SIGNUP_TEXT) + "']"))) {
			isElementDisplayed(
					By.xpath("//*[@name = '" + FileUtilities.strGetLocaleText(IOSConstants.BRAIZE_SIGNUP_TEXT) + "']"));
			click(By.xpath("//*[@name = '" + FileUtilities.strGetLocaleText(IOSConstants.BRAIZE_SIGNUP_TEXT) + "']"),
					"Close button Braze");
			click(BasePage.handlePopUp, "Cancel pop up");
		}
	}

	public static void fnHandleAdvertisement() {
		try {
			int count = 0;
			while (isNumOf_Elements_Greater_Than_Zero(BasePage.btnCloseAdvertisement)) {

				clickNoWait(BasePage.btnCloseAdvertisement, "Closing Advertisements");
				
				getMobileDriver().context("NATIVE_APP");

				if (isNumOf_Elements_Greater_Than_Zero(BasePage.btnCloseAdvertisement)) {
					Point offset = getMobileElement(BasePage.btnCloseAdvertisement).getCenter();
					new TouchAction(ActionEngine.getIOSDriver()).tap(new PointOption().withCoordinates(offset))
							.perform();
					ReporterLog.info("Tap on Close button", "Single Tap performed on Close button");
				} else {
					break;
				}
				if (isNumOf_Elements_Greater_Than_Zero(BasePage.btnCloseAdvertisement)) {
					new TouchAction(ActionEngine.getIOSDriver()).tap(new TapOptions()
							.withElement(ElementOption.element(getMobileElement(BasePage.btnCloseAdvertisement), 1, 1))
							.withTapsCount(2)).perform();
					ReporterLog.info("Tap on Close button", "Double Tap performed on Close button");
				} else {
					break;
				}
				
				if (isNumOf_Elements_Greater_Than_Zero(BasePage.btnCloseAdvertisement))
					clickNoWait(BasePage.btnCloseAdvertisement, "Closing Advertisements");
	
				if (count == 5) {
					ReporterLog.info("Exiting Loop", "Exiting Loop after 6 attempts ...");
					break;
				}

				if (count == 3 && isNumOf_Elements_Greater_Than_Zero(BasePage.btnCloseAdvertisement)) {
					ReporterLog.info("Launching Application",
							"Relaunching App After 3 attempt to close Interstitial Ads");
					ActionEngine.getIOSDriver().launchApp();
					continue;
				}

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SignUpPage getSignUpPage() {
		try {
			Thread.sleep(5000);
			//isElementDisplayed(getSignUpButton(), "Sign Up button");
			clickNoWait(getSignUpButton(), "Sign Up link");

		} catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFailure(e);
		}
		return new SignUpPage();

	}
}

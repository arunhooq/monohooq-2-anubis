package web.pages;

import org.openqa.selenium.By;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GmailAPI;
import com.automation.utilities.ReadTestData;


public class LoginPage extends ActionEngine {
	public static By evNextBtn = By.cssSelector(".md-button");
	// Login
	public static By btnPassword = By.xpath(".//*[@id='password-button']");
	public static By edtPassword = By.xpath(".//*[@id='password']");
	public static By btnPasswordOK = By.xpath(".//*[@id='submit-button']");
	public static By lblEmail = By.xpath("//a[contains(@class,'redirect_url emailormobile email ')]");
	public static By txtLoginMobile = By.xpath("//*[@id='mobile']");

	public static By lblPWDForm = By.xpath(".//*[@id='loginpwd_form']/label");
	public static By txtPWD = By.xpath("//input[@name='password']");
	public static By btnNextAuth = By.cssSelector(".md-button");
	public static By lblOTPForm = By.xpath(".//*[@id='otp_form']/label");
	public static By btnOTPNxt = By.xpath(".//*[@id='otp_form']/button");
	public static By lblCreatePWDForm = By.xpath(".//*[@id='logincreatepwd_form']/label");
	public static By btnCreatePWDLogin = By.xpath(".//*[@id='logincreatepwd_form']/div[2]/button");
	public static By emailIDTxt=By.xpath("//input[@id='email']");
	public static By forgotPassword = By.cssSelector(".text-right a");
	public static By nextButtonResetPassword = By.cssSelector("button:not([disabled])");
	public static By resetLinkSentOkay = By.cssSelector(".form-group button");
	public static By createPassword = By.cssSelector("#password");
	public static By createPasswordDone = By.cssSelector(".form-group button");
	public static By passwordUpdatedLable = By.cssSelector("label");
	public static By loginToAccount = By.xpath("/html/body/div[2]/div[1]/div[1]/div/div/div/button");
	//public static By passwordLabel = By.cssSelector("label[class='labelHead']");
	public static By passwordLabel = By.id("password");
	
	public LoginPage enterUserDetails_old(String user) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			if (user.contains("@")) {
				waitForElementClickable(lblEmail, "lblEmail");
				click(lblEmail, "Click on Email button");

				waitForElementClickable(emailIDTxt, "emailIDTxt");
				click(emailIDTxt, "Click on Email text");
				type(emailIDTxt, user, "Entering Valid Email ID");
			}
			else {
				type(txtLoginMobile, user, "Mobile number");
			}
			click(evNextBtn, "Click on login button");
			TestUtilities.logReportPass("Entered user: "+user+" details successfully in login form and clicked on Next");
	
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}
	
	public LoginPage enterUserDetails(String user) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			if (user.contains("@")) {
				ReporterLog.info("Enter User Details","looking for element to become Clickable: Email label (lblEmail)");
				
				waitForElementClickable(lblEmail, "lblEmail");
				click(lblEmail, "Click on Email button");

				ReporterLog.info("Enter User Details","looking for element to become Clickable: Enter Email Address (emailIDtxt)");
				waitForElementClickable(emailIDTxt, "emailIDTxt");
				click(emailIDTxt, "Click on Email text");
				type(emailIDTxt, user, "Entering Valid Email ID");
			}
			else {
				type(txtLoginMobile, user, "Mobile number");
			}
			ReporterLog.info("Enter User Details","looking for element to become Clickable: Next button (evNextBtn)");
			waitForElementClickable(emailIDTxt, "emailIDTxt");	
			click(evNextBtn, "Click on login button");
			TestUtilities.logReportPass("Entered user: "+user+" details successfully in login form and clicked on Next");
	
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}
	
	public LoginPage enterUserDetails_NEW(String user) throws Exception{
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			if (user.contains("@")) {
				ReporterLog.info("Enter User Details","looking for element to become Clickable: Email label (lblEmail)");
				
				waitForElementClickable_NEW(lblEmail, "lblEmail");
				click_NEW(lblEmail, "Click on Email button");

				ReporterLog.info("Enter User Details","looking for element to become Clickable: Enter Email Address (emailIDtxt)");
				waitForElementClickable_NEW(emailIDTxt, "emailIDTxt");
				click_NEW(emailIDTxt, "Click on Email text");
				type_NEW(emailIDTxt, user, "Entering Valid Email ID");
			}
			else {
				type_NEW(txtLoginMobile, user, "Mobile number");
			}
			ReporterLog.info("Enter User Details","looking for element to become Clickable: Next button (evNextBtn)");
			waitForElementClickable_NEW(emailIDTxt, "emailIDTxt");	
			click_NEW(evNextBtn, "Click on login button");
			TestUtilities.logReportPass("Entered user: "+user+" details successfully in login form and clicked on Next");
	
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e);
			throw new Exception("Exception encountered at enterUserDetails");
		}
		return new LoginPage();
	}
	
	public BasePage enterPassword(String password) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
			
		//String pwdLabel = ActionEngine.driver.findElement(passwordLabel).getText();
		//ReporterLog.info("Checking for label 'Enter your password' presence", "");
		//Assert.assertEquals(pwdLabel, "Enter your password");
		
		try {
				Thread.sleep(15000);
				
				//String desired = "password";
				//String actual = ActionEngine.driver.findElement(passwordLabel).getText();
				
				boolean displayed = isElementPresentInDom(passwordLabel);
				
				ReporterLog.info("Checking password is visible on page: "+isElementPresentInDom(passwordLabel),"");
				
				//if (isNumOf_Elements_Greater_Than_Zero(lblPWDForm)) {
				if (displayed) {
					type(txtPWD, "123456", "Password");
					click(btnNextAuth, "Login");
					TestUtilities.logReportPass("Entered password: "+password+" and clicked on Next");
					Thread.sleep(5000);
					ActionEngine.waitForVisibilityOfElement(BasePage.hamburgerMenu, "Hamburger yumm");
				} else {
					// We may not need enter a password if password already in session data
					ReporterLog.info("Password screen not available, checking if already logged in", "");
					ActionEngine.waitForVisibilityOfElement(BasePage.hamburgerMenu, "Hamburger yumm");
					click(BasePage.hamburgerMenu,"Hamburger, yumm");
					Boolean isUserNameVisible = isElementPresentInDom(BasePage.userNameInSideMenu);
					if (isUserNameVisible) {
						
						if(exactTextMatch(getText(BasePage.userNameInSideMenu, "username string"),ReadTestData.ACTIVE_USER_ID)) {
							ReporterLog.info("Password entry not required, user is logged in successfully","" );
						} else {
							//We do NOT want to throw a fail here as error handling will be conducted in the next step (Verify user logged in)
							ReporterLog.warning("Unable to enter password, and different user logged in ("+getText(BasePage.userNameInSideMenu, "username string")+")","");
						}
						
					} else {
						//We do NOT want to throw a fail here as error handling will be conducted in the next step (Verify user logged in)
						ReporterLog.warning("Unable to enter password, and no user is logged in.","");
					}
				}
			} catch (Exception e) {
				TestUtilities.logReportFailure(e);
			}
		return new BasePage();
	}
	
	public BasePage enterPassword_NEW(String password) throws Exception {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
			
		//String pwdLabel = ActionEngine.driver.findElement(passwordLabel).getText();
		//ReporterLog.info("Checking for label 'Enter your password' presence", "");
		//Assert.assertEquals(pwdLabel, "Enter your password");
		
		try {
				Thread.sleep(15000);
				
				//String desired = "password";
				//String actual = ActionEngine.driver.findElement(passwordLabel).getText();
				
				boolean displayed = isElementPresentInDom(passwordLabel);
				
				ReporterLog.info("Checking password is visible on page: "+isElementPresentInDom(passwordLabel),"");
				
				//if (isNumOf_Elements_Greater_Than_Zero(lblPWDForm)) {
				if (displayed) {
					type_NEW(txtPWD, "123456", "Password");
					click_NEW(btnNextAuth, "Login");
					TestUtilities.logReportPass("Entered password: "+password+" and clicked on Next");
					Thread.sleep(5000);
					ActionEngine.waitForVisibilityOfElement_NEW(BasePage.hamburgerMenu, "Hamburger yumm");
				} else {
					// We may not need enter a password if password already in session data
					ReporterLog.info("Password screen not available, checking if already logged in", "");
					ActionEngine.waitForVisibilityOfElement_NEW(BasePage.hamburgerMenu, "Hamburger yumm");
					click_NEW(BasePage.hamburgerMenu,"Hamburger, yumm");
					Boolean isUserNameVisible = isElementPresentInDom(BasePage.userNameInSideMenu);
					if (isUserNameVisible) {
						
						if(exactTextMatch(getText_NEW(BasePage.userNameInSideMenu, "username string"),ReadTestData.ACTIVE_USER_ID)) {
							ReporterLog.info("Password entry not required, user is logged in successfully","" );
						} else {
							//We do NOT want to throw a fail here as error handling will be conducted in the next step (Verify user logged in)
							ReporterLog.warning("Unable to enter password, and different user logged in ("+getText(BasePage.userNameInSideMenu, "username string")+")","");
						}
						
					} else {
						//We do NOT want to throw a fail here as error handling will be conducted in the next step (Verify user logged in)
						ReporterLog.warning("Unable to enter password, and no user is logged in.","");
					}
				}
			} catch (Exception e) {
				//TestUtilities.logReportFailure(e);
				throw new Exception("Exception encountered when entering password");
			}
		return new BasePage();
	}
	
	
	public LoginPage clickForgotPassword() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
				click(forgotPassword, "forgotPassword");
				TestUtilities.logReportPass("Clicked on forgotpassword link.");
			} catch (Exception e) {
				TestUtilities.logReportFailure(e);
			}
		return new LoginPage();
	}
	
	public LoginPage forgotPassword_SendResetLink() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
				click(nextButtonResetPassword, "nextButtonResetPassword");
				click(resetLinkSentOkay, "resetLinkSentOkay");
				TestUtilities.logReportPass("Clicked on Next button on reset password Page and Clicked on Okay on the Next Page");
			} catch (Exception e) {
				TestUtilities.logReportFailure(e);
			}
		return new LoginPage();
	}
	
	public LoginPage forgotPassword_ResetPassword(String email) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
				ReporterLog.info("getLinkFromEmail","Waiting for 10 seconds to allow for email delivery");
				Thread.sleep(10000);
				String linkFromEmail = GmailAPI.fetchLinkFromEmail(email, "authenticate.hooq.tv/createnewpassword");
				System.out.println("linkFromEmail: "+linkFromEmail);
				openUrl(linkFromEmail);
				//Thread.sleep(5000);
				click(createPassword,"click into create password field");
				type(createPassword, "123456", "createPassword");
				click(createPasswordDone, "createPasswordDone");
				waitForVisibilityOfElement(passwordUpdatedLable, "passwordUpdatedLable");
				click(loginToAccount, "loginToAccount");
				TestUtilities.logReportPass("Clicked on the link from gmail, typed new password in the form and click on Next, then clicked on LoginToAccount button");
			} catch (Exception e) {
				TestUtilities.logReportFailure(e);
			}
		return new LoginPage();
	}
	
	public  LoginPage securePassword(String password) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			type(txtPWD, "123456", "Password");
			click(btnCreatePWDLogin, "Done");	
			TestUtilities.logReportPass("Entered password in Secure Password page and clicked on CreatePassword button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}
	
	public  LoginPage enterOTP() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			waitForVisibilityOfElement(By.xpath("//*[@id='otp-input']"), "otp-input");
			for (int i = 1; i <= 6; i++) {
				driver.findElement(By.xpath("//*[@id='otp-input']/input[" + i + "]"))
						.sendKeys(String.valueOf(i));
			}
			click(btnOTPNxt, "Next");
			TestUtilities.logReportPass("Entered OTP and clicked on Next button");
	
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}
	
	

}

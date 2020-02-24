package ios.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.ClickAction;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.APIUtils;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.GmailAPI;
import com.automation.utilities.ReadTestData;


public class SignUpPage extends ActionEngine {

	public static By txtSignUp = By.name("SIGN UP");
	public static By btnClose = By.name("ic close white");
	public static By btnSkip = By.id("Skip");
	public static By btnLogin = ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG) ? By.xpath("//XCUIElementTypeOther/following::XCUIElementTypeButton[4]"):By.xpath("//XCUIElementTypeOther/following::XCUIElementTypeButton[3]");
	public static By btnSingtelCast = By.id("singtelCastLogo");
	public static By inputMobile_Email=By.xpath("//XCUIElementTypeTextField[2]");
	public static By btnDone = By.id("Done");
	public static By btnYes = By.id("YES");
	public static By txtOTPVerificationTitle = By.id("OTP VERIFICATION");
	public static By inputOTPBox = By.xpath("//XCUIElementTypeOther/*/*/*/*/*/*/*/XCUIElementTypeOther/XCUIElementTypeButton");
	public static By btnNext = By.id("next");
	public static By inputEmailAdress_signUp = By.xpath("//XCUIElementTypeTextField");
	public static By btnStartTrialNow = By.id("Start your trial now");
	//Email Verification Page
	public static By txtTitle_EmailVerification = By.id("Email verification");
	public static By btnIhaveVerified = By.id("I have verified");
	
	
	
	private static By getOTPInputBox(String index){
		return By.xpath("//XCUIElementTypeOther/*/*/*/*/*/*/*/XCUIElementTypeOther/XCUIElementTypeStaticText["+index+"]");
	}
	public SignUpPage isSignUpPageDisplayed() {
		try {
			if(!isElementPresentInDom(btnSkip, "Sign Up Title")) {
				click(LoginPage.getSignUpButton(), "Sign Up link");
			}
			waitForVisibilityOfElement(txtSignUp, "Sign Up Title");
			isElementDisplayed(btnLogin ,"Login button");
			isElementDisplayed(btnSkip,"Skip Button");
			if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG))
				isElementDisplayed(btnSingtelCast,"Singtel Cast Logo");

		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new SignUpPage();
	}


	public SignUpPage clickCloseButton() {

		click(btnClose, "Close button Clicked");
		return new SignUpPage();
	}
	
	public static void enterOTP(String otp) {
		char[] arr = otp.toCharArray();
		clickNoWait(inputOTPBox, "OTP INPUT BOX");
		typeNoWait(getOTPInputBox("1"), Character.toString(arr[0]), "First OTP box");
		typeNoWait(getOTPInputBox("2"), Character.toString(arr[1]), "Second OTP box");
		typeNoWait(getOTPInputBox("3"), Character.toString(arr[2]), "Third OTP box");
		typeNoWait(getOTPInputBox("4"), Character.toString(arr[3]), "Fourth OTP box");
		typeNoWait(getOTPInputBox("5"), Character.toString(arr[4]), "Fifth OTP box");
		typeNoWait(getOTPInputBox("6"), Character.toString(arr[5]), "Sixth OTP box");
		ReporterLog.info("OTP Entered", "OTP entered successfully as : "+otp);
	}
	
	
	public void verifySignUpWithMobile() {
		isSignUpPageDisplayed();
		click(inputMobile_Email, "Mobile/email input box");
		type(inputMobile_Email, "8938393030", "Incorrect Mobile Number");
		click(btnDone, "Done button"); //Pop up accept
		
		//Verify OTP verification page
		if(!isElementPresentInDom(btnYes,"Yes button on the pop up")) {
			click(btnDone, "Done button");
		}
		if(!isElementPresentInDom(btnYes,"YES button on Pop up")) {
			tapOnElement(btnDone, "Done button");
		}
		
		waitTillElementPresent_HardWait_Polling(btnYes, GlobalConstant.WAIT_SHORT_5_SEC,"Message Pop up");
		click(btnYes, "YES pop up ");
		
		isElementDisplayed(txtOTPVerificationTitle,"OTP Page Title");
		
		//Enter Wrong OTP
		enterOTP("134564"); 
		click(btnNext, "Next button");
		
		//Verify Login is not successful
		VerifyThatIsFalse(isElementPresentInDom(MePage.meLabel,"Me Icon"), "Login was not successfull with incorrect Mobile OTP , as expected- Pass");;
	}
	
	public void verifySignUpWithEmail() throws IOException {
		try {
		isSignUpPageDisplayed();
		
		//Click on the Skip button
		click(btnSkip, "Skip button");
		
		isElementDisplayed(btnStartTrialNow, "Start Trial Now button");
		
		//************** Validate with Invalid Email address ********************
		ReadTestData.fnAddTestRailScriptID(22408);
		
		String invalidEmail ="email.com";
		type(inputEmailAdress_signUp, invalidEmail, "inavalid inputEmailAdress_signUp");
		
		click(btnStartTrialNow, "Start Trial Now button");
		
		if(!isElementPresentInDom(txtTitle_EmailVerification, "Email Verification Page Title"))
			ReporterLog.pass("Verify invalid email sign up ", "Invalid email sign up validation successfull..");
		
		//***************** Validate SignUp with Valid Email address ************
		
		ReadTestData.fnAddTestRailScriptID(22407);
		//Enter New Email to the input box
		String userName = "hooqtestuser+"+FileUtilities.strGetRandomString(5)+"@gmail.com";
		type(inputEmailAdress_signUp, userName, "inputEmailAdress_signUp");
		//Click on the Start your trial now button
		
		click(btnStartTrialNow, "Start Trial Now button");
		
		//Email verification page validate 
		waitTillElementPresent_HardWait_Polling(txtTitle_EmailVerification, GlobalConstant.WAIT_SHORT_4_SEC, "Email Verification page Title");
		
		
		//***************** Validate SignUp without verifying email (click on  I have verified button) ************
		ReadTestData.fnAddTestRailScriptID(22409);
		//Click on the I have Verified Button
		 click(btnIhaveVerified, "I have verified button on Sign Up Page");
		 waitTillElementPresent_HardWait_Polling(MePage.meLabel, GlobalConstant.WAIT_SHORT_1_SEC, "Me Icon");
		 if(!isElementPresentInDom(MePage.meLabel, "Me Icon"))
				ReporterLog.pass("Validation of email without clicking on the email deep link ", "Verify successfully for 'I have verified button' without click on the Email deep link");
			
		 
		// *************************  Valid Email verification  Continues.. *************
		 
		 ReadTestData.fnAddTestRailScriptID(22407);
		 //Verify the link in the email sent to gmail account .
		String tokenURI = GmailAPI.getRedirectURLforNewUserLogin(userName);
		
		String command = "curl " + tokenURI;

		Process process = Runtime.getRuntime().exec(command);

		System.out.println(APIUtils.convertInputStreamToString(process.getInputStream()));
		
		//Click on the I have Verified Button
		click(btnIhaveVerified, "I have verified button on Sign Up Page");
		
		//Verify Me Icon is visible
		waitTillElementPresent_HardWait_Polling(MePage.meLabel, GlobalConstant.WAIT_SHORT_3_SEC, "Me Icon");
		
		isElementDisplayed(MePage.meLabel,"Me Icon");
		}catch(Exception e) {
			e.printStackTrace();
			ReporterLog.fail("verifySignUpWithEmail", "Sign Up with email failed with cause : "+e.getMessage());
		}
	}
	
	
}

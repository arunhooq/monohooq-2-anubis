package ios.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.GmailAPI;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class ParentalControlPage extends ActionEngine {
	
	public static By txt_PageTitle_ParentalControl = By.id("Parental Controls");
	public static By txt_Heading_VerifyYourAccount = By.id("Verify your account");
	public static By txt_email = By.xpath("//XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeStaticText[4]");
	public static By link_ResendEmail = By.id("Resend Email");
	public static By btn_Next = By.id("Next");
	public static By error_message_incorrectOTP = By.id("Incorrect code.Please try again or resend email.");
	public static By input_OTP = By.xpath(".//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By btn_keyboard_delete = By.xpath(".//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeKey[12]");
	
	public static By btn_NotOver21Year = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton");
	public static By btn_Confirm = By.id("Confirm");
	
	public static By btn_YesOver21Year = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton");
	public static By txt_dateOfBirth = By.id("Date of birth:");
	public static By input_DateOfBirth = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
	public static By datePicker_DOB_Month= By.xpath(".//XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[1]");
	public static By datePicker_DOB_Date= By.xpath(".//XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[2]");
	public static By datePicker_DOB_Year= By.xpath(".//XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[3]");
	public static By btn_DatePicker_Done = By.id("Done");
	
	public static By txt_errorMessage_wrongDOB = By.id("You must be 21 or over in order to proceed.");
	public static By input_NewPIN = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By btn_ManageSettings = By.id("Manage Settings");
	public static By btnConfirm = By.id("Confirm");

	public static By getOTPInputBox(String index){
		return By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText["+index+"]");
	}
	
	
	public  ContentDetailsPage verifyParentalControlPage() throws InterruptedException, IOException {
		
		//Parental Control Page Validation
		waitTillElementPresent_HardWait_Polling(txt_PageTitle_ParentalControl, GlobalConstant.WAIT_SHORT_10_SEC);
		isElementDisplayed(txt_PageTitle_ParentalControl);
		isElementDisplayed(txt_Heading_VerifyYourAccount);
		isElementDisplayed(link_ResendEmail);
		verifyText(txt_email, ReadTestData.ACTIVE_USER_ID.toLowerCase());
		
		//Enter Invalid OTP and verify error messages
		verifyInvalidOTP();
		
		//Gmail Authentication for Valid OTP
		verifyValidOTP();
		
		//No ,I am not over 21 flow > validate user shall not be able to play video
		verifyIfNotOver21();
		
		//Re-checking for Over 21 Scenario with Correct OTP from Gmail
		verifyValidOTP();
		
		//Yes I am over 21 flow >DOB > set incorrect PIN and validate error scenario		
		verifyIfOver21();
		
		
		return new ContentDetailsPage();
	}
	
	
	public void verifyInvalidOTP() throws InterruptedException {
		
		//Enter Invalid OTP and verify error messages
		enterOTP("123456");		
		Thread.sleep(20000);
		waitTillElementPresent_HardWait_Polling(error_message_incorrectOTP, GlobalConstant.WAIT_SHORT_5_SEC);
		//click(btn_Next, "Parental Control Next button");
		isElementDisplayed(error_message_incorrectOTP);
	}
	
	public void verifyValidOTP() throws IOException, InterruptedException {
		
		String strCorrectOTP = GmailAPI.getOTPForParentalControl(ReadTestData.ACTIVE_USER_ID.toLowerCase()).trim();
		clickNoWait(input_OTP, "OTP INPUT BOX");
		for(int i =0 ;i<6 ;i++) {
			driver.findElement(btn_keyboard_delete).click();	
		}	
		//Enter Correct OTP 
		enterOTP(strCorrectOTP);
		Thread.sleep(10000);
	}
	
	public void verifyIfNotOver21() throws InterruptedException {
		Thread.sleep(10000);
		waitTillElementPresent_HardWait_Polling(btn_NotOver21Year, GlobalConstant.WAIT_SHORT_10_SEC);
		isElementDisplayed(btn_NotOver21Year);
		clickNoWait(btn_NotOver21Year, "Not Over 21 Year button");
		click(btn_Confirm, "Confirm Button at Not over 21 year");
		Thread.sleep(5000);
		clickNoWait(btn_ManageSettings, "Manage Settings button");
		Thread.sleep(5000);
	}
	
	public void verifyIfOver21() throws InterruptedException {
		
		//Yes I am over 21 flow >DOB > set incorrect PIN and validate error scenario
		Thread.sleep(10000);
		waitTillElementPresent_HardWait_Polling(btn_YesOver21Year, GlobalConstant.WAIT_SHORT_10_SEC);
		waitForVisibilityOfElement(btn_YesOver21Year, "Yes,Over 21 year");
		clickNoWait(btn_YesOver21Year, "Yes,Over 21 year");
		
		isElementDisplayed(txt_dateOfBirth);
		isElementDisplayed(input_DateOfBirth);
		clickNoWait(input_DateOfBirth, "Date Of Birth Input box");
	
		//Enter Wrong DOB
		setDateOfBirth("15","August","2015");
		
		//Validate Error Message
		waitTillElementPresent_HardWait_Polling(txt_errorMessage_wrongDOB,GlobalConstant.WAIT_SHORT_1_SEC);
		isElementDisplayed(txt_errorMessage_wrongDOB);
		
		clickNoWait(input_DateOfBirth, "Date Of Birth Input box");
		//Enter Correct DOB
		setDateOfBirth("12","March","1992");
				
		click(input_NewPIN, "New OTP input");
		setNewPIN(input_NewPIN);
		
		//Click Done Button-Keyboard
		click(By.id("Done"), "KeyBoard Done button");
		click(btnConfirm, "Confirm button");	
	}
	
	public static void setNewPIN(By by) {
		List<WebElement> pin = driver.findElements(by);
		char[] arr = IOSConstants.validR21MoviePIN.toCharArray();
		int i=0 ;
		for(WebElement ele : pin) {
			ele.sendKeys(Character.toString(arr[i]));
			i++;
		}
	}
	
	public static void enterOTP(String otp) {
		char[] arr = otp.toCharArray();
		clickNoWait(input_OTP, "OTP INPUT BOX");
		typeNoWait(getOTPInputBox("1"), Character.toString(arr[0]), "First OTP box");
		typeNoWait(getOTPInputBox("2"), Character.toString(arr[1]), "Second OTP box");
		typeNoWait(getOTPInputBox("3"), Character.toString(arr[2]), "Third OTP box");
		typeNoWait(getOTPInputBox("4"), Character.toString(arr[3]), "Fourth OTP box");
		typeNoWait(getOTPInputBox("5"), Character.toString(arr[4]), "Fifth OTP box");
		typeNoWait(getOTPInputBox("6"), Character.toString(arr[5]), "Sixth OTP box");
		ReporterLog.info("OTP Entered", "OTP entered successfully as : "+otp);
	}
	
	private static void setDateOfBirth(String intDate ,String strMonth ,String strYear ) {
		try {
		ReporterLog.info("Entering DOB ", "Date of Birth Entered as :  Date : "+intDate +" month : "+strMonth +" Year: "+strYear);
		getIOSDriver().findElement(datePicker_DOB_Month).sendKeys(strMonth);
		getIOSDriver().findElement(datePicker_DOB_Date).sendKeys(intDate);
		getIOSDriver().findElement(datePicker_DOB_Year).sendKeys(strYear);		
		click(btn_DatePicker_Done, "Date Picker Done.");
		ReporterLog.info("Date of Birth Set", "DOB set successfully ...");
		}catch(Exception e) {e.printStackTrace();}
	}
	

}

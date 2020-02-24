package web.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class SignUpPageTests extends TestConfiguration{

	//@Test(groups = { "Regression", "HOOQ_PWA_SignIN_SingtelCast_ExistsInEV" })
	public void HOOQ_PWA_SignIN_SingtelCast_ExistsInEV() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {
			basePage.getSignUpPage().
			verifySignInWithSingtelCAST("cignititest@singtel.com", "hooqtest", "Valid");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignIN_SingtelCast_IncorrectDetails" })
	public void HOOQ_PWA_SignIN_SingtelCast_IncorrectDetails() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignInWithSingtelCAST("cignititest@singtel.com", "hooqtest", "Invalid");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignIN_SingtelCast_NotExistsInEV" })
	public void HOOQ_PWA_SignIN_SingtelCast_NotExistsInEV() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignInWithSingtelCAST("cignititest13@singtel.com", "hooqtest", "Valid");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_OTTEmail_CancelPayment" })
	public void HOOQ_PWA_SignUP_OTTEmail_CancelPayment() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignUpWithOTTEmail("cignitiinsignupcancel6@yopmail.com", "OTTEmail", "Cancel", "Valid",
					"IN");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_OTTEmail_CreditCard" })
	public void HOOQ_PWA_SignUP_OTTEmail_CreditCard() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignUpWithOTTEmail("cignitiinsignup@yopmail.com", "OTTEmail", "CreditCard", "Valid", "IN");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_OTTMobile_AccountExistsInEV" })
	public void HOOQ_PWA_SignUP_OTTMobile_AccountExistsInEV() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignUpWithOTTMobile("91231231231", "OTTMobile", "Cash Card", "Invalid", "Skip", "", "IN");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_OTTMobile_CreditCard" })
	public void HOOQ_PWA_SignUP_OTTMobile_CreditCard() throws Throwable {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignUpWithOTTMobile("92465438689", "OTTMobile", "CreditCard", "Valid", "SecureEmail",
					"cignitiottmobilesignup@yopmail.com", "IN");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_OTTMobile_ResendOTP" })
	public void HOOQ_PWA_SignUP_OTTMobile_ResendOTP() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifyResendOTP("92465432155", "OTTMobile", "Skip", "IN");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_OTTMobile_SkipNova" })
	public void HOOQ_PWA_SignUP_OTTMobile_SkipNova() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignUpWithOTTMobileSkipNova("9343543523", "OTTMobile", "Skip",
					"Accept without verification", "IN");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@DataProvider(name = "preProdTestData")
	public Object[][] preProdTestData() {
	 return new Object[][] {
	   { "98545645654", "PreProv", "Invalid", "", ""  },
	   { "98545645654", "PreProv", "Valid", "Skip", ""},
	   { "98545645654", "PreProv", "Valid", "ExistingSecureEmail", ""},
	   { "98545645654", "PreProv", "Valid", "SecureEmail", ""},
	 };
	}
	//@Test(dataProvider = "preProdTestData", groups = { "Regression", "HOOQ_PWA_SignUP_PreProvisioned_AccountExistsInEV" })
	public void HOOQ_PWA_SignUP_PreProvisioned_AccountExistsInEV(String loginID, String strUserType, String strValid, String strSecure,
			String strSecureEmail) {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {

			basePage.getSignUpPage().
			verifySignUpWithPreProvMobile(loginID, strUserType, strValid, strSecure,strSecureEmail);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	//@Test(groups = { "Regression", "HOOQ_PWA_SignUP_SingtelCast" })
	public void HOOQ_PWA_SignUP_SingtelCast() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {
			basePage.getSignUpPage().
			verifySignUPWithSingtelCAST();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(groups = {"xxSanity_Visitor", "Successful_Register_Email"})
	public void Successful_Register_Email() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {
			basePage.getSignUpPage()
			.enterUserDetails("hooqtester+"+RandomStringUtils.random(5, "123456789")+"@gmail.com")
			.clickNextButton()
			.clickSkipLink()
			.clickGetStartedButton()
			.checkIfUserLoggedIn();
			ReporterLog.softAssert.assertAll();

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
}

package ios.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class SignUpPageTest extends TestConfiguration {

	/*****************************************************
	 * 
	 * 1.VerifySignUpWith Valid MobileNumber 2.VerifySignUpwith Invalid Mobile
	 * Number 3.SignUp with Valid Email 4.SignUp with Invalid Email
	 * 5.SignUpEmailVerificationwithInvalidToken<Click on I have verified button>
	 * 
	 *******************************************************/

	@Test(priority = 1, groups = { GlobalConstant.GROUP_REGRESSION_Visitor,
			"verifyUnsuccessfullSignUpForIncorrectMobileNumberOrOTP" })

	public void verifyUnsuccessfullSignUpForIncorrectMobileNumberOrOTP() {

		try {
			ReadTestData.fnAddTestRailScriptID(22405);

			basePage.getLoginPage().getSignUpPage()
					.verifySignUpWithMobile();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getStackTrace());
		}
	}
	
	@Test(priority = 2, groups = { GlobalConstant.GROUP_SANITY_Visitor,"verifySignUpWithValidEmail"})

	public void verifySignUpWithValidEmail() {

		try {
			ReadTestData.fnAddTestRailScriptID(22407);

			basePage.getLoginPage().getSignUpPage().verifySignUpWithEmail();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getStackTrace());
		}
	}

}

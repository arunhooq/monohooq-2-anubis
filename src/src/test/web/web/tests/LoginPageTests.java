package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class LoginPageTests extends TestConfiguration{
	
	@Test(groups = { "Sanity_Visitor", "SuccessfulLogin_Email"})
	public void SuccessfulLogin_Email() {
		ReadTestData.fnAddTestRailScriptID(22376);
		try 
		{					
			basePage.getLoginPage()
				.enterUserDetails(ReadTestData.ACTIVE_USER_ID)
				.enterPassword(ReadTestData.PASSWORD)
				.checkIfUserLoggedIn()
				.performLogout_ifLoggedIn();
		
				ReporterLog.softAssert.assertAll();
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(groups = { "Sanity_Visitor", "ForgotPassword_EmailUser"}, enabled = true)
	public void ForgotPassword_EmailUser() {
		ReadTestData.fnAddTestRailScriptID(22375);
		try 
		{					
			basePage.getLoginPage()
					.enterUserDetails("hooqtestuser+vv1dp_sg@gmail.com")
					.clickForgotPassword()
					.forgotPassword_SendResetLink()
					.forgotPassword_ResetPassword("hooqtestuser+vv1dp_sg@gmail.com");
			basePage.performLogout_ifLoggedIn();
				
			ReporterLog.softAssert.assertAll();
       } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}

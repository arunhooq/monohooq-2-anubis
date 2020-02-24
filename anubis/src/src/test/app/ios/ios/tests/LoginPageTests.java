package ios.tests;

import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.utilities.APIUtils;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class LoginPageTests extends TestConfiguration {

	private static  final String INCORRECT_USERNAME1 = "abcd1298XZZZZZ";
	private static  final String INCORRECT_MobileNumber = "83783278347";



	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_Login Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */
	

	@Test(priority = 1, groups = {GlobalConstant.GROUP_REGRESSION_Visitor,"verifySuccessfulLoginWithEmail" })

	public void verifySuccessfulLoginWithEmail()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22395);
			ReadTestData.ACTIVE_USER_ID = APIUtils.getActiveUserEmail();

			basePage.getLoginPage()
			.performEmailLogin(ReadTestData.ACTIVE_USER_ID,ReadTestData.PASSWORD)
			.verifyMeIcon();
			ReporterLog.pass("Verify ME Section", "Validatiobn of ME section is successfull");
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getMessage());
		}
	}
	
	/**
	 * @author mdafsarali
	 * @date 8 October 2019
	 * @Description verifyUnsuccessfulLoginWithIcorrectUserName
	 */

	@Test(priority = 2, groups = {GlobalConstant.GROUP_REGRESSION_Visitor,"verifyUnsuccessfulLoginWithIncorrectUserName" })

	public void verifyUnsuccessfulLoginWithIncorrectUserName()  {
		
		try {
			ReadTestData.fnAddTestRailScriptID(22396);

			basePage.getLoginPage()
			.UnsuccessfulLoginWithIncorrectUserName(IOSConstants.USERNAME_INCORRECT)
			.UnsuccessfulLoginWithIncorrectUserName(INCORRECT_USERNAME1);

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace());
		}
	}
	
	/**
	 * @author mdafsarali
	 * @date 8 October 2019
	 * @Description verifyUnsuccessfulLoginWithIcorrectPassword
	 */

	@Test(priority = 3, groups = {GlobalConstant.GROUP_REGRESSION_Visitor,"verifyUnsuccessfulLoginWithIncorrectPassword" })

	public void verifyUnsuccessfulLoginWithIncorrectPassword()  {
		
		try {
			ReadTestData.fnAddTestRailScriptID(22397);
			ReadTestData.ACTIVE_USER_ID = APIUtils.getActiveUserEmail();

			basePage.getLoginPage()
			.UnsuccessfulLoginWithIncorrectPassword(ReadTestData.ACTIVE_USER_ID ,IOSConstants.PASSWORD_INCORRECT);

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace());
		}
	}

	@Test(priority = 4, groups = {GlobalConstant.GROUP_REGRESSION_Visitor,"verifyUnsuccessfulLoginWithIncorrectOTP" })

	public void verifyUnsuccessfulLoginWithIncorrectOTP()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22398);
			ReadTestData.ACTIVE_USER_ID = APIUtils.getActiveUserEmail();

			basePage.getLoginPage()
			.UnsuccessfulLoginWithIncorrectOTP(ReadTestData.ACTIVE_USER_ID);

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace());
		}
	}

	@Test(priority = 5,enabled=true, groups = {GlobalConstant.GROUP_REGRESSION_Visitor,"verifyUnsuccessfulLoginWithIncorrectMobileNumber" })

	public void verifyUnsuccessfulLoginWithIncorrectMobileNumber()  {
		
		try {
			ReadTestData.fnAddTestRailScriptID(23027);


			basePage.getLoginPage()
			.UnsuccessfulLoginWithIncorrectMobile(INCORRECT_MobileNumber);

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace());
		}
	}
	 
	
	@Test(priority = 6,enabled=false, groups = {GlobalConstant.GROUP_REGRESSION_Visitor,"verifyUnsuccessfulLoginWithIncorrectMobileOTP" })
	public void verifyUnsuccessfulLoginWithIncorrectMobileOTP()  {
		
		try {
			ReadTestData.fnAddTestRailScriptID(22399);
	
			basePage.getLoginPage()
			.UnsuccessfulLoginWithIncorrectMobileOTP(APIUtils.signUpUsingMobileNumber());

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace());
		}
	}
}

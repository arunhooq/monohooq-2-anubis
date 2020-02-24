package partner.tests;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import io.restassured.response.Response;
import partner.modules.UserController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class UserTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST})
	public void createUserByEmail() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(20373);
		String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
		try {
			// Create User By Email
			Response res = UserController.createUser(Constants.ACCOUNT_TYPE_EMAIL, random_email);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateUserByEmail", "CreateUserByEmail executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateUserByEmail	failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createUserByPhoneNumber() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22555);
		String random_phone_number = Constants.ZERO + Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create User By Phone Number
			Response res = UserController.createUser(Constants.ACCOUNT_TYPE_PHONENUMBER, random_phone_number);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateUserByPhoneNumber", "CreateUserByPhoneNumber executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateUserByPhoneNumber failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createUserByCpCustomerId() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22556);
		String random_cp_id = Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create User CP Customer ID
			Response res = UserController.createUser(Constants.ACCOUNT_TYPE_CP_ID, random_cp_id);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateUserByCpCustomerId", "CreateUserByCpCustomerId executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreaCreateUserByCpCustomerIdteUserByPhoneNumber failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createAndSignInUserByEmail() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22563);
		String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
		try {
			// Create And SignIn User By Email
			Response res = UserController.createAndSignInUser(Constants.ACCOUNT_TYPE_EMAIL, random_email);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateAndSignInUserByEmail", "CreateAndSignInUserByEmail executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateAndSignInUserByEmail failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createAndSignInUserByPhoneNumber() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22564);
		String random_phone_number = Constants.ZERO + Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create And SignIn User By Phone Number
			Response res = UserController.createAndSignInUser(Constants.ACCOUNT_TYPE_PHONENUMBER, random_phone_number);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateAndSignInUserByPhoneNumber", "CreateAndSignInUserByPhoneNumber executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateAndSignInUserByPhoneNumber failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createAndSignInUserByCpCustomerId() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22565);
		String random_cp_id = Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create And SignIn User By CP Customer ID
			Response res = UserController.createAndSignInUser(Constants.ACCOUNT_TYPE_CP_ID, random_cp_id);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateAndSignInUserByCpCustomerId", "CreateAndSignInUserByCpCustomerId executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateAndSignInUserByCpCustomerId failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createUserWithActiveSubscriptionByEmail() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22557);
		String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
		try {
			// Create User With Active Subscription By Email
			Response res = UserController.createUserWithActiveSubscription(Constants.ACCOUNT_TYPE_EMAIL, random_email, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateUserWithActiveSubscriptionByEmail", "CreateUserWithActiveSubscriptionByEmail executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateUserWithActiveSubscriptionByEmail failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createUserWithActiveSubscriptionByPhoneNumber() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22558);
		String random_phone_number = Constants.ZERO + Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create User With Active Subscription By Phone Number
			Response res = UserController.createUserWithActiveSubscription(Constants.ACCOUNT_TYPE_PHONENUMBER, random_phone_number, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateUserWithActiveSubscriptionByPhoneNumber", "CreateUserWithActiveSubscriptionByPhoneNumber executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateUserWithActiveSubscriptionByPhoneNumber failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createUserWithActiveSubscriptionByCpCustomerId() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22559);
		String random_cp_id = Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create And SignIn User With Active Subscription By CP Customer ID
			Response res = UserController.createUserWithActiveSubscription(Constants.ACCOUNT_TYPE_CP_ID, random_cp_id, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateUserWithActiveSubscriptionByCpCustomerId", "CreateUserWithActiveSubscriptionByCpCustomerId executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateUserWithActiveSubscriptionByCpCustomerId failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createAndSignInUserWithActiveSubscriptionByEmail() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22560);
		String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
		try {
			// Create And SignIn  User With Active Subscription By Email
			Response res = UserController.createAndSignInUserWithActiveSubscription(Constants.ACCOUNT_TYPE_EMAIL, random_email, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateAndSignInUserWithActiveSubscriptionByEmail", "CreateAndSignInUserWithActiveSubscriptionByEmail executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateAndSignInUserWithActiveSubscriptionByEmail failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createAndSignInUserWithActiveSubscriptionByPhoneNumber() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22561);
		String random_phone_number = Constants.ZERO + Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create And SignIn  User With Active Subscription By Phone Number
			Response res = UserController.createAndSignInUserWithActiveSubscription(Constants.ACCOUNT_TYPE_PHONENUMBER, random_phone_number, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateAndSignInUserWithActiveSubscriptionByPhoneNumber", "CreateAndSignInUserWithActiveSubscriptionByPhoneNumber executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateAndSignInUserWithActiveSubscriptionByPhoneNumber failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void createAndSignInUserWithActiveSubscriptionByCpCustomerId() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22562);
		String random_cp_id = Long.toString(ReusableMethods.getTimeStamp());
		try {
			// Create And SignIn  User With Active Subscription By CP Customer ID
			Response res = UserController.createAndSignInUserWithActiveSubscription(Constants.ACCOUNT_TYPE_CP_ID, random_cp_id, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyRequestSucceed(res);
			ReporterLog.pass("CreateAndSignInUserWithActiveSubscriptionByCpCustomerId", "CreateAndSignInUserWithActiveSubscriptionByCpCustomerId executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"CreateAndSignInUserWithActiveSubscriptionByCpCustomerId failed");
		}
	}
}

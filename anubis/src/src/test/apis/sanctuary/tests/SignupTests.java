package sanctuary.tests;


import java.util.Map;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import io.restassured.response.Response;

import sanctuary.modules.ActivateControllers;
import sanctuary.modules.CatalogControllers;
import sanctuary.modules.EvergentControllers;
import sanctuary.modules.SigninControllers;
import sanctuary.modules.SignupControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.DBhelpers;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class SignupTests extends TestConfiguration  {
	

	@Test(priority = 10, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN,"Testi"})
	public void signupEmailTest()
	{
		try
		{	
			// Get the device signature 
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Perform sign up email
			Response signupemail_res = SignupControllers.SignupEmail(devicesignature,email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
			
			// Verify the sign up email response			
			ApiVerifications.verifyStatusCode(signupemail_res.getStatusCode(), 201);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("signupEmail", "sign up api successfull "+signupemail_res.getStatusCode());	
	
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void signupVerifyEmailTest()
	{
		try
		{		
			// Perform activate client and get device signature
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
		    JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			System.out.println(email);
			// Perform sign up email 
			SignupControllers.SignupEmail(devicesignature,email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.REGISTERED_USER);
			
			// Perform sign up verify email 
			Response signupverify_res = SignupControllers.SignupverifyEmail(devicesignature,email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			// Verify the sign up verify email	
			ApiVerifications.verifyStatusCode(signupverify_res.getStatusCode(), 200);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("signupVerifyEmail", "sign up api successfull "+signupverify_res.getStatusCode());	
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	
	@Test(priority = 3, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void signupPhoneTest()
	{
		try
		{	
			// Get the device signature 
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Perform sign up email
			Response signupphone_res = SignupControllers.SignupPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
			
			// Verify the sign up email response			
			ApiVerifications.verifyStatusCode(signupphone_res.getStatusCode(), 201);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("signupPhone", "sign up phone api successfull "+signupphone_res.getStatusCode());	
	
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	
	@Test(priority = 4, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void signupVerifyPhoneTest()
	{
		try
		{		
			// Perform activate client and get device signature
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
		
			// Perform sign up phone
			SignupControllers.SignupPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.REGISTERED_USER);
			
			// Perform sign up verify phone
			Response signupverify_res = SignupControllers.SignupverifyPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			// Verify the sign up verify phone
			ApiVerifications.verifyStatusCode(signupverify_res.getStatusCode(), 200);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("signupVerifyPhone", "sign up Phone api successfull "+signupverify_res.getStatusCode());	
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	@Test(priority = 5, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void accountMe()
	{
		try
		{	
									
			Map<String,String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
			
			Response accountMe = SigninControllers.accountMe(devicesignature, authorization);
			// Verify the sign up verify email	
			ApiVerifications.verifyStatusCode(accountMe.getStatusCode(), 200);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("accountMe", "accountMe api successfull "+accountMe.getStatusCode());	
						
		}
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	
	@Test(priority = 6, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void accountMeandSigninUserLevelCheck()
	{
		try
		{					
							
			Map<String,String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			
			// sign in
			JSONObject signin = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email));
			String authorization = signin.get(SanctuaryConstants.TOKEN).toString();			
			int signin_userLevel = Integer.parseInt(signin.get(SanctuaryConstants.USERLEVEL).toString());
			
			// accountMe
			Response accountMe = SigninControllers.accountMe(devicesignature, authorization);
			int accountMe_userLevel = Integer.parseInt(ReusableMethods.rawtoJsonObject(accountMe).get(SanctuaryConstants.USERLEVEL).toString());
			
			// Verify the account me and sign in user level are same
			ApiVerifications.verifyStatusCode(signin_userLevel,accountMe_userLevel);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("accountMeandSigninUserLevelCheck", "accountMe and sign in has the same user Level successfully "+accountMe.getStatusCode());	
						
		}
		catch (Exception e) {
			
			TestUtilities.logReportFailure(e);
		
			
		}
		
	}
	
	@Test(priority = 7, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void signupUpdateEmail()
	{
		
		String newEmail = null;	
		try
		{
			newEmail = "hooq_Newemail"+ReusableMethods.getTimeStamp()+"@yopmail.com";	
			// Perform activate client and get device signature
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Perform sign up phone
			SignupControllers.SignupPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.REGISTERED_USER);
			
			Thread.sleep(200);
			// Perform sign up verify phone
			Response signupverify_res = SignupControllers.SignupverifyPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			Thread.sleep(200);
			
			JSONObject signup_json = (JSONObject)ReusableMethods.rawtoJsonObject(signupverify_res);
			// Verify the sign up verify phone
			Thread.sleep(200);
			ApiVerifications.verifyStatusCode(signupverify_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
			
			Thread.sleep(200);
			// sign up update
			Response signupUpdate_res = SignupControllers.SignupUpdate(devicesignature, signup_json.get(SanctuaryConstants.ID).toString(), newEmail, ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			Thread.sleep(200);
			// Verify the sign up verify phone
		    ApiVerifications.verifyStatusCode(signupUpdate_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
		    
		    // Connect to back stage DB	
		    JSONObject dbresults = DBhelpers.dbconnect("select * from accounts where email = "+"'"+ newEmail.toLowerCase()+"'" +" ", ApiConfigDetails.country);
			
		        
		    ApiVerifications.verifyStringMatching(newEmail, dbresults.get(SanctuaryConstants.EMAIL.toLowerCase()).toString());
		    
		    ApiVerifications.verifyStringMatching(signup_json.get(SanctuaryConstants.PHONE_NUMBER).toString(),dbresults.get(SanctuaryConstants.PHONE_NUMBER).toString());
		    
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("signupUpdateemail", "sign up Update api successfully "+signupUpdate_res.getStatusCode());	
			
		}
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	
	@Test(priority = 8,groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_IN},dataProviderClass=ApiConfigDetails.class,dataProvider = SanctuaryConstants.SKU)
	public void planObjectCheck(String data)
	{
		try
		{
				
			Map<String,String> userDetails = EvergentControllers.createSubsribedUser(email.toLowerCase(),deviceid,data);
									
			// hmac
			String hmac = ReusableMethods.generateHMACforPhantomLogin(email.toLowerCase(),deviceid);
			
			// phantom login
			JSONObject phantom_res = (JSONObject) EvergentControllers.phantomLogin(email.toLowerCase(),deviceid, hmac);	
			JSONObject phantom = (JSONObject) phantom_res.get(SanctuaryConstants.CUSTOMER_LOGIN_RES);
		
			// plan current 
			Response plan_Current = SigninControllers.plan_Current(userDetails.get(SanctuaryConstants.DEVICESIGNATURE), userDetails.get(ApiConfigDetails.AUTHORIZATION));
			JSONObject planCurrent_Json = ReusableMethods.rawtoJsonObject(plan_Current);
			JSONArray plandetails = (JSONArray) planCurrent_Json.get(SanctuaryConstants.PLAN);			
			JSONObject plan = (JSONObject) plandetails.get(0);	
								
			// Api verifications
			ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.SKU).toString(), plan.get(SanctuaryConstants.SKU).toString());	
			ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.SUBSCRIPTIONEXPIRED).toString(),plan.get(SanctuaryConstants.EXPIRED).toString());
			ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.SUBSCRIPTIONNAME).toString(),plan.get(SanctuaryConstants.NAME).toString());
			ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.SUBSCRIPTIONSTATUS).toString(),plan.get(SanctuaryConstants.STATUS).toString());
			ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.ACCOUNTACTIVATIONDATE).toString(), plan.get(SanctuaryConstants.STARTDATE).toString());
			ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.SUBSCRIPTIONEXPDATE).toString(), plan.get(SanctuaryConstants.EXPIREDON).toString());
			
						
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("planObjectCheck", "plan Object Check api successfully");	
		}
		catch (Exception e) {
			TestUtilities.logReportFailure(e, "plan Object Check api Unsuccessfull");
		}
		
	}
	
	@Test(priority = 9, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_PH})
	public void accountMapping_Check()
	{
		
		try
		{
				
			EvergentControllers.createSubsribedUser(email.toLowerCase(),deviceid,ApiConfigDetails.sku);
									
			// hmac
			String hmac = ReusableMethods.generateHMACforPhantomLogin(email.toLowerCase(),deviceid);
			
			// phantom login
			JSONObject phantom_res = (JSONObject) EvergentControllers.phantomLogin(email.toLowerCase(),deviceid, hmac);	
			JSONObject phantom = (JSONObject) phantom_res.get(SanctuaryConstants.CUSTOMER_LOGIN_RES);
						
		    // Connect to back stage DB	
		    JSONObject dbresults = DBhelpers.dbconnect("select id from accounts where email = "+"'"+ email +"'" +" ", ApiConfigDetails.country);
		    		  		    
		    JSONObject account_mapping = DBhelpers.dbconnect("select account_id,sp_account_id from account_id_mapping where account_id = "+"'"+ dbresults.get(SanctuaryConstants.ID).toString() +"'" +" ", ApiConfigDetails.country);
		    				    
		    ApiVerifications.verifyStringMatching(phantom.get(SanctuaryConstants.SP_ACCOUNT_ID).toString(),account_mapping.get(SanctuaryConstants.SP_ACCOUNT_MAPPING).toString());
		    
		    ApiVerifications.verifyStringMatching(dbresults.get(SanctuaryConstants.ID).toString(),account_mapping.get(SanctuaryConstants.ACCOUNT_MAPPING_ID).toString());
					
		}
		catch(Exception e) 			
		{
			TestUtilities.logReportFailure(e, "account Mapping Check api Unsuccessfull");
		}
		
	}
	
	@Test(priority = 11, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_PH})
	public void deviceLimitCheck()
	{
		try
		{
			String deviceSignature 	= ActivateControllers.generate_deviceSignature(deviceid);
			ActivateControllers.ActivateVisitor(deviceSignature);
			Response res = ActivateControllers.deviceLimitCheck(deviceSignature);
			Map<String,String> errorMap = CatalogControllers.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(SanctuaryConstants.ERROR_STATUS).toString()), SanctuaryConstants.ERRSTATUS_DEVICELIMIT);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(SanctuaryConstants.ERROR_CODE).toString()), SanctuaryConstants.ERRCODE_DEVICELIMIT);	
			ApiVerifications.verifyStringMatching(errorMap.get(SanctuaryConstants.ERROR_DETAIL.toString()), SanctuaryConstants.ERRORDETAIL_DEVICELIMIT);
			
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("deviceLimitCheck", "device Limit Check executed successfully");
		}
		catch (Exception e) {
			TestUtilities.logReportFailure(e, "device Limit Check Unsuccessfull");
		}
		
	}
	
	
	@Test(priority = 12, groups = {SanctuaryConstants.GROUP_ID})
	public void verificationstableCheck()
	{
		try
		{						
			// Perform activate client and get device signature
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Perform sign up phone
			SignupControllers.SignupPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
			
			// Perform sign up verify phone
			Response signupPhone_Res = SignupControllers.SignupverifyPhone(devicesignature,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			JSONObject signup_Json = ReusableMethods.rawtoJsonObject(signupPhone_Res);
			
			Thread.sleep(5000);
			// Connect to back stage DB	
		    JSONObject dbresults = ApiConfigDetails.getRegistrationstatus(signup_Json,SanctuaryConstants.PHONE_NUMBER);
		    
		    
		    ApiVerifications.verifyStatusCode(Integer.parseInt(dbresults.get(SanctuaryConstants.REGISTRATION_STATUS).toString()), 1);
		    
		    SignupControllers.SignupverifyEmail(devicesignature,email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
		    
			// sign up update
		    SignupControllers.SignupUpdate(devicesignature, signup_Json.get(SanctuaryConstants.ID).toString(), email, ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
		    Thread.sleep(5000);
		    // Connect to back stage DB	
		    JSONObject emailUpdate = ApiConfigDetails.getRegistrationstatus(signup_Json,SanctuaryConstants.PHONE_NUMBER);
		    		
			ApiVerifications.verifyStatusCode(Integer.parseInt(emailUpdate.get(SanctuaryConstants.REGISTRATION_STATUS).toString()), 3);

		    ReporterLog.softAssert.assertAll();
			ReporterLog.pass("RegistrationstatusinverificationsentityCheck", "Registration status in verifications entity Check executed successfully");
		   			
		}
		catch(Exception e) {
			
			
			TestUtilities.logReportFailure(e, "Registration status in verifications table Check failed");

		}
		
	}
	
	@Test(priority = 13, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void signup_Trustedemail()
	{
		try
		{	
					
			// Perform activate client and get device signature
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Perform sign up with trusted email
			SignupControllers.signupwith_Trustedemail(devicesignature,email,true);
							
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature, email)).get(SanctuaryConstants.TOKEN).toString());
			
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("sign up Trusted email", "sign up Trusted email executed successfully");
						   			
		}
		catch(Exception e) {
			
			
			TestUtilities.logReportFailure(e, "sign up Trusted email Check failed");

		}
	
	}
	
	@Test(priority = 14, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void signup_NonTrustedemail()
	{
		try
		{	
			
					
			// Perform activate client and get device signature
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Perform sign up with Non trusted email
			Response r = SignupControllers.signupwith_Trustedemail(devicesignature,email,false);
			
			JSONObject signup_Json = ReusableMethods.rawtoJsonObject(r);
			
			
			// Connect to back stage DB	
		    JSONObject dbresults = ApiConfigDetails.getRegistrationstatus(signup_Json,SanctuaryConstants.EMAIL);
			
			ApiVerifications.verifyStatusCode(Integer.parseInt(dbresults.get(SanctuaryConstants.REGISTRATION_STATUS).toString()), 0);
			
			
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("sign up Trusted email", "sign up Trusted email executed successfully");
						   			
		}
		catch(Exception e) {
			
			
			TestUtilities.logReportFailure(e, "sign up Trusted email Check failed");

		}
	
	}
			
}

package sanctuary.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import io.restassured.response.Response;
import sanctuary.modules.ActivateControllers;
import sanctuary.modules.CatalogControllers;
import sanctuary.modules.EveControllers;
import sanctuary.modules.EvergentControllers;
import sanctuary.modules.SigninControllers;
import sanctuary.modules.SignupControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class CatalogTests extends TestConfiguration 
{
	ArrayList<String> Concurrent_device;
	
	
	public CatalogTests()
	{
		Concurrent_device = new ArrayList<String>();
		Concurrent_device.add("deviceid1"+ReusableMethods.getTimeStamp());
		Concurrent_device.add("deviceid2"+ReusableMethods.getTimeStamp());
	}
	
	@Test(priority = 12, groups = {SanctuaryConstants.ANDROID})
	public void getCatalogPlay_Android()
	{
		
			try
			{
										
				// Activate client			
				Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
				String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
				
				// Set up
				ActivateControllers.SetUp(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT), deviceid, devicesignature);
							
				// Activate visitor
				ActivateControllers.ActivateVisitor(devicesignature);
				
				// Sign up
				SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
				
				// Sign up verify email
				SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
				
				// Sign in
				Response signin_res = SigninControllers.SigninEmail(devicesignature, email);
				String authorization = ReusableMethods.rawtoJsonObject((signin_res)).get(SanctuaryConstants.TOKEN).toString();
					
				
				// get catalog play for Android
				Response getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString(),devicesignature,authorization);
										
				// verify catalog play status code for Android
				ApiVerifications.verifyStatusCode(getcatalog_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
				ReporterLog.softAssert.assertAll();
				ReporterLog.pass("getCatalogPlayforAndroid", "get catalog successfull for Android "+getcatalog_res.getStatusCode());	
				
				
				// verify the content and license url for Android
				ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString());				
				ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());
				ReporterLog.softAssert.assertAll();	
				ReporterLog.pass("getCatalogPlayforAndroid", "get catalog retrived contentUrl and licenseUrl for Android"+ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());	
										
			}
			catch(Exception e) {			
				TestUtilities.logReportFailure(e,"The get catalog play for Android is not working as expected");			
			}
		}
		
	
	@Test(priority = 2, groups = {SanctuaryConstants.IOS})
	public void getCatalogPlay_IOS()
	{
		
			try
			{						
				// Activate client			
				Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
				String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
				
				// Set up
				ActivateControllers.SetUp(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT), deviceid, devicesignature);
							
				// Activate visitor
				ActivateControllers.ActivateVisitor(devicesignature);
				
				// Sign up
				SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
				
				// Sign up verify email
				SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
				
				// Sign in
				Response signin_res = SigninControllers.SigninEmail(devicesignature, email);
				String authorization = ReusableMethods.rawtoJsonObject((signin_res)).get(SanctuaryConstants.TOKEN).toString();
							
				// get catalog play for IOS
				Response getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString(),devicesignature,authorization);
				
				// verify catalog play status code for IOS
				ApiVerifications.verifyStatusCode(getcatalog_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
				ReporterLog.softAssert.assertAll();
				ReporterLog.pass("getCatalogPlayforIOS", "get catalog successfull for IOS "+getcatalog_res.getStatusCode());	
				
				// Navigate to the specific key
				JSONObject  raw = (JSONObject) ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.RAW);			
				JSONArray sources = (JSONArray)  raw.get(SanctuaryConstants.SOURCES);			
				JSONObject sources_list  = (JSONObject) sources.get(0);
				JSONObject key_systems = (JSONObject) sources_list.get(SanctuaryConstants.KEYSYSTEMS);			
				JSONObject com_apple = (JSONObject) key_systems.get(SanctuaryConstants.COMAPPLE);
				
				// Verify the certificate and key request url
				ApiVerifications.verifyNotNull(com_apple.get(SanctuaryConstants.CERTIFICATE_URL).toString());
				ApiVerifications.verifyNotNull(com_apple.get(SanctuaryConstants.REQUEST_URL).toString());
				ReporterLog.softAssert.assertAll();	
				ReporterLog.pass("getCatalogPlayforIOS", "get catalog retrived contentUrl and licenseUrl for IOS "+com_apple.get(SanctuaryConstants.CERTIFICATE_URL).toString());
				
				
			}							
			catch(Exception e) {			
				TestUtilities.logReportFailure(e,"The get catalog play for IOS is not working as expected");			
			}
			
		}
				
	
	
	
	@Test(priority = 3, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforSubscribedUser_Contentlevel30()
	{
		try
		{
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.SUBSCRIBED_USER, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_30").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("ContentEligibilityTestforSubscribedContenntlevel30", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
			
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforSubscribedContenntlevel30 is not working as expected");	
		}
		
	}


	@Test(priority = 4, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforSubscribedUser_Contentlevel10()
	{
		try
		{
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.SUBSCRIBED_USER, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_10").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	 
			ReporterLog.pass("ContentEligibilityTestforSubscribedContenntlevel10", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
			
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforSubscribedContenntlevel10 is not working as expected");	
		}
		
	}
	
	@Test(priority = 5, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforRegisteredUser_Contentlevel30()
	{	
		try
		{	
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.REGISTERED_USER, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_30").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("ContentEligibilityTestforRegisteredContentlevel30", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
			
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforRegisteredContentlevel30 is not working as expected");	
		}
	}
	
	@Test(priority = 6, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforRegisteredUser_Contentlevel10()
	{
		try
		{
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.REGISTERED_USER, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_10").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("ContentEligibilityTestforRegisteredContenntlevel10", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
		
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforRegisteredContenntlevel10 is not working as expected");	
		}

	}
	
	@Test(priority = 7, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforVisitor_Contentlevel30()
	{
		try
		{		
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.VISITOR, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_30").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("ContentEligibilityTestforVisitorContenntlevel30", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
			System.out.println(e);
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforVisitorContenntlevel30 is not working as expected");	
		}
		
	}
	
	@Test(priority = 8, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforVisitor_Contentlevel20()
	{
		try
		{		
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.VISITOR, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_20").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("ContentEligibilityTestforVisitorContenntlevel20", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
			
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforVisitorContenntlevel20 is not working as expected");	
		}
	}
	
	@Test(priority = 9, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void validate_ContentEligibilityTestforVisitor_Contentlevel10()
	{
		try
		{
				
			CatalogControllers.verify_Contenteligibility(SanctuaryConstants.VISITOR, deviceid, email, ApiConfigDetails.get_titles.get("Contentlevel_10").toString(), ApiConfigDetails.country);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("ContentEligibilityTestforVisitorContenntlevel10", "Content eligibility test is working as expected");
		}
		catch(Exception e)
		{
		
			TestUtilities.logReportFailure(e,"ContentEligibilityTestforVisitorContenntlevel10 is not working as expected");	
		}
		
	}
	
	@Test(priority = 10, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void concurrent_Streaming() throws ParseException 
	{
		Response first_Android;
		Map<String,String> errorCode;
		Response third_Android;
		try
		{
					
			// Activate client for First device
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature_1 = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization_1 = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature_1,email)).get(SanctuaryConstants.TOKEN).toString();
			
			// catalog play for first device
			first_Android = CatalogControllers.getCataloglicense_Android(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL30).toString(),devicesignature_1,authorization_1);
			ApiVerifications.verifyStatusCode(first_Android.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
						
			// Activate client for second device		
			Response client_res_2 = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),Concurrent_device.get(0).toString());		
			String devicesignature_2 = ReusableMethods.rawtoJsonObject((client_res_2)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			SigninControllers.SigninEmail(devicesignature_2,email);
			SigninControllers.signinVerifyemail(devicesignature_2,email);
			String authorization_2 = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature_2,email)).get(SanctuaryConstants.TOKEN).toString();
							
			// catalog play for second device
			Response second_Android =  CatalogControllers.getCataloglicense_Android(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL30).toString(),devicesignature_2,authorization_2);
			ApiVerifications.verifyStatusCode(second_Android.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
						
			// Activate client	for third device		
			Response client_res_3 = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),Concurrent_device.get(1).toString());		
			String devicesignature_3 = ReusableMethods.rawtoJsonObject((client_res_3)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			SigninControllers.SigninEmail(devicesignature_3,email);
			SigninControllers.signinVerifyemail(devicesignature_3,email);
			String authorization_3 = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature_3,email)).get(SanctuaryConstants.TOKEN).toString();			
						
			// catalog play for third device
			third_Android = CatalogControllers.getCataloglicense_Android(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL30).toString(),devicesignature_3,authorization_3);					
			errorCode = CatalogControllers.getErrorcode(third_Android);
			
			// Error message : You cannot have more than 2 active streams at a time
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorCode.get(SanctuaryConstants.ERROR_CODE).toString()), SanctuaryConstants.ERRCODE_CONCURRENT);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorCode.get(SanctuaryConstants.ERROR_STATUS).toString()), SanctuaryConstants.ERRSTATUS_CONCURRENT);
			
			
			Thread.sleep(40000);
												
			// catalog play for first device after 100 seconds
			first_Android = CatalogControllers.getCataloglicense_Android(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL30).toString(),devicesignature_1,authorization_1);
			errorCode = CatalogControllers.getErrorcode(first_Android);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorCode.get(SanctuaryConstants.ERROR_CODE).toString()), 8015);
			
			
			// catalog play for third device after 100 seconds
			Response Android = CatalogControllers.getCataloglicense_Android(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL30).toString(),devicesignature_3,authorization_3);	
			System.out.println(ReusableMethods.rawtoJsonObject(Android));
			ApiVerifications.verifyStatusCode(Android.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("Concurrent streaming", "Concurrent streaming is working as expected");
						
		
		}
		catch(Exception e)
		{
			System.out.println(e);
			TestUtilities.logReportFailure(e,"The concurrent streaming is not working as expected");	
		}
		
		
	}
	
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG})
	public void validate_r21parentalcode_SG()
	{
		try
		{
			// Activate client 
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
			
			// catalog play 
			Response catalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get(SanctuaryConstants.R21_TITLE_SG).toString(), devicesignature, authorization);
			System.out.println(ReusableMethods.rawtoJsonObject(catalog_res));
			
			Map<String,String> errorCode = CatalogControllers.getErrorcode(catalog_res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorCode.get(SanctuaryConstants.ERROR_CODE).toString()), 7078);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("R21 Parental code", "The R21 Parental code is displayed successfully ");
		}
		catch(Exception e)
		{		
			TestUtilities.logReportFailure(e,"The R21 Parental code is not displayed ");	
		}
	}
	
	@Test(priority = 11, groups = {SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void verify_RegionbasedPlayback()
	{
		try
			{
				// Activate client 
				Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
				String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
				String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
				
				// catalog play 
				Response catalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get("Regionbased_title_SG").toString(), devicesignature, authorization);
				Map<String,String> errorCode = CatalogControllers.getErrorcode(catalog_res);
				ApiVerifications.verifyStatusCode(Integer.parseInt(errorCode.get(SanctuaryConstants.ERROR_CODE).toString()), 4002);
				ApiVerifications.verifyStringMatching(errorCode.get("detail").toString(),SanctuaryConstants.REGION_ERRORMESSAGE);
				
				ReporterLog.softAssert.assertAll();	
				ReporterLog.pass("Region based Playback", "Region based Playback is working as expected");
				
			}
			catch(Exception e)
			{
				
				TestUtilities.logReportFailure(e,"Region based Playback is working as expected");	
			}
		}
		
		
	@Test(priority = 13, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void getCatalogplay_Roku()
	{
		try
		{
									
			// user creation, device signature and authorization
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
				
			Map<String,String> roku_map = ApiConfigDetails.getAllPlatformDetails(SanctuaryConstants.PLATFORM_ROKU);
			
			// get catalog play for Android
			Response getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString(),devicesignature,
					authorization,roku_map.get(SanctuaryConstants.PLATFORM_APIKEY),roku_map.get(SanctuaryConstants.PLATFORM_DRMFORMAT),roku_map.get(SanctuaryConstants.PLATFORM_USERAGENT));
									
			// verify catalog play status code for Android
			ApiVerifications.verifyStatusCode(getcatalog_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("getCatalogPlay", "get catalog successfull "+getcatalog_res.getStatusCode());	
			
			
			// verify the content and license url for Android
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString());				
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());
			
			
			// verify playready exists in manifest and license url
			ApiVerifications.verifyStringContains(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString(),SanctuaryConstants.DRMFORMAT_PLAYREADY);
			ApiVerifications.verifyStringContains(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString(),SanctuaryConstants.DRMFORMAT_PLAYREADY);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("getCatalogPlay", "get catalog retrived contentUrl and licenseUrl for roku successfully "+ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());	
									
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"The get catalog play for Roku is not working as expected");			
		}
		
	}
		
	@Test(priority = 14, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void getCatalogplay_Androidtv()
	{
		try
		{
									
			// user creation, device signature and authorization
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
				
			Map<String,String> androidtv_map = ApiConfigDetails.getAllPlatformDetails(SanctuaryConstants.PLATFORM_ANDROIDTV);
			
			// get catalog play for Android
			Response getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString(),devicesignature,authorization,androidtv_map.get(SanctuaryConstants.PLATFORM_APIKEY),androidtv_map.get(SanctuaryConstants.PLATFORM_DRMFORMAT),androidtv_map.get(SanctuaryConstants.PLATFORM_USERAGENT));
									
			// verify catalog play status code for Android
			ApiVerifications.verifyStatusCode(getcatalog_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("getCatalogPlay", "get catalog successfull "+getcatalog_res.getStatusCode());	
						
			// verify the content and license url for Android
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString());				
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());
				
			// verify dash exists in the content url
			ApiVerifications.verifyStringContains(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString(),SanctuaryConstants.DRMFORMAT_DASH);
						
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("getCatalogPlay", "get catalog retrived contentUrl and licenseUrl for androidtv successfully"+ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());	
									
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"The get catalog play for androidtv is not working as expected");			
		}
		
	}
		
	@Test(priority = 15, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void getCatalogplay_Webclient()
	{
		try
		{
			
		
		Response client_res = ActivateControllers.ActivateClient_Webclient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
		String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
		
		EvergentControllers.createSubsribedUser(email,deviceid);		
		Response res = SigninControllers.getOAuthToken_WebClient(email,deviceid);
		JSONObject outh_data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(SanctuaryConstants.DATA);
		String authorization = (String) outh_data.get(SanctuaryConstants.ACCESS_TOKEN);
		
		
		// get account details(contact id and sp account id) from eve
		Response getContact_Res = EveControllers.getContact(authorization);
		JSONObject account_data = (JSONObject) ReusableMethods.rawtoJsonObject(getContact_Res).get(SanctuaryConstants.DATA);
		JSONArray contactMessageList = (JSONArray) account_data.get(SanctuaryConstants.CONTACT_MESSAGE);
		JSONObject contactMessage = (JSONObject) contactMessageList.get(0);
		String contactID = contactMessage.get(SanctuaryConstants.CONTACT_ID).toString();
		String spAccountID = account_data.get(SanctuaryConstants.SP_ACCOUNT_ID).toString();
		
		// webhook call to Sanctuary
		@SuppressWarnings("serial")
		HashMap<String,String> accountMap = new HashMap<String,String>() {{ put(SanctuaryConstants.CONTACT_ID , contactID); 
																			put(SanctuaryConstants.SP_ACCOUNT_ID,spAccountID);
																			put(SanctuaryConstants.EMAIL,email);}};
																			
		Response webHook = EveControllers.webHookCall(authorization, accountMap);
		System.out.println((JSONObject) ReusableMethods.rawtoJsonObject(webHook)) ;
											
		Map<String,String> webclient_map = ApiConfigDetails.getAllPlatformDetails(SanctuaryConstants.PLATFORM_WEBCLIENT);
		
		// get catalog play for webclient
		Response getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString(),devicesignature,authorization,webclient_map.get(SanctuaryConstants.PLATFORM_APIKEY),webclient_map.get(SanctuaryConstants.PLATFORM_DRMFORMAT),webclient_map.get(SanctuaryConstants.PLATFORM_USERAGENT));
								
		// verify catalog play status code for webclient
		ApiVerifications.verifyStatusCode(getcatalog_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
		
		ReporterLog.pass("getCatalogPlay", "get catalog successfull "+getcatalog_res.getStatusCode());	
					
		// verify the content and license url for webclient
		ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString());				
		ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());
			
		// verify dash exists in the content url
		ApiVerifications.verifyStringContains(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString(),SanctuaryConstants.DRMFORMAT_DASH);
					
		ReporterLog.softAssert.assertAll();	
		ReporterLog.pass("getCatalogPlay", "get catalog retrived contentUrl and licenseUrl for webclient successfully"+ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());	
		
	}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"The get catalog play for webclient is not working as expected");			
			}
	}
	
	
	
	@Test(priority = 16, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void getCatalogplay_Singtelstb()
	{
		try
		{
									
			// user creation, device signature and authorization
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
				
			String accountId = ReusableMethods.rawtoJsonObject(SigninControllers.accountMe(devicesignature, authorization)).get(SanctuaryConstants.ID).toString();
						
					
			Map<String,String> singtelstb_map = ApiConfigDetails.getAllPlatformDetails(SanctuaryConstants.PLATFORM_SINGTELSTB);
			
			String titleid = ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString();
						
			// get hmac for singtelstb
			String hmac = ReusableMethods.generateHMACforSingtel(titleid,devicesignature,accountId);
			
			// get catalog play for Singtelstb		
			Response getcatalog_res = CatalogControllers.getCatalogPlayforSingtelstb(titleid, devicesignature, authorization, hmac, 
					 singtelstb_map.get(SanctuaryConstants.PLATFORM_DRMFORMAT), singtelstb_map.get(SanctuaryConstants.PLATFORM_USERAGENT), singtelstb_map.get(SanctuaryConstants.PLATFORM_CONSUMER));
			
			// verify catalog play status code for Singtelstb
			ApiVerifications.verifyStatusCode(getcatalog_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_200);
							
			// verify the content and license url for Singtelstb
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString());				
			ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());
			
			
			// verify playready exists in manifest and license url for Singtelstb
			ApiVerifications.verifyStringContains(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.CONTENT).toString(),SanctuaryConstants.DRMFORMAT_PLAYREADY);
			ApiVerifications.verifyStringContains(ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString(),SanctuaryConstants.DRMFORMAT_PLAYREADY);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("getCatalogPlay-Singtelstb", "get catalog retrived contentUrl and licenseUrl for singtelstb successfully "+ReusableMethods.rawtoJsonObject(getcatalog_res).get(SanctuaryConstants.LICENSE).toString());	
									
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"The get catalog play for singtelstb is not working as expected");			
		}
		
	}

	
		
}

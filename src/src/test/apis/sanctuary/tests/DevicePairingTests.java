package sanctuary.tests;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import sanctuary.modules.EvergentControllers;
import sanctuary.modules.SigninControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;
import sanctuary.modules.ActivateControllers;
import sanctuary.modules.DevicePairingControllers;
import sanctuary.modules.EveControllers;
import web.data.WebSocketListener;


import io.restassured.response.Response;

public class DevicePairingTests extends TestConfiguration{
	
	
	@Test(priority = 1, groups = { SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void device_PairingTest_JWT1() 
	{	
		String tvCode = "";
		String primaryDevice = SanctuaryConstants.RANDOM_DEVICE_ID;
		try {
			
			// Secondary device is required to get the randomly generated key from the service.
			String input = DevicePairingControllers.construct_WebSocketRequest(SanctuaryConstants.JWT1_VERSION,primaryDevice,SanctuaryConstants.PLATFORM_ROKU);
			JSONObject devicePairResponse = WebSocketListener.getResponseFromWebSocket(ApiConfigDetails.websocket_Url, input);
			JSONObject devicePairResponseData = (JSONObject) devicePairResponse.get(SanctuaryConstants.DATA);			
			tvCode = (String) devicePairResponseData.get(SanctuaryConstants.KEY);
			
			if(tvCode.equals(""))
				throw new Exception();
			
			TestUtilities.logReportPass("TVCode generated successfully: "+tvCode);
					
			// user creation, device signature and authorization
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String token = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
					
			//Primary device is required to validate key as shown in the secondary device. To validate a key, send a message with this info:			
			String validate_tvCode = DevicePairingControllers.construct_WebSocketValidate(tvCode,email,ApiConfigDetails.country,token);
									
			JSONObject device_Validate = WebSocketListener.getResponseFromWebSocket(ApiConfigDetails.websocket_Url, validate_tvCode);		
			
			JSONObject deviceValidateResponseData = (JSONObject) device_Validate.get(SanctuaryConstants.DATA);
			
			ApiVerifications.verifyiftrue(((boolean)deviceValidateResponseData.get(SanctuaryConstants.MATCH)));
		
			// To check the status of the primary device
			Response device_status = DevicePairingControllers.deviceStatus(primaryDevice);
			
			org.json.simple.JSONObject devicestatus_json = (org.json.simple.JSONObject) ReusableMethods.rawtoJsonObject(device_status);
						
			String SecondarydeviceJWT= ReusableMethods.decode_JWT(devicestatus_json.get(SanctuaryConstants.TOKEN).toString());
			String PrimarydeviceJWT= ReusableMethods.decode_JWT(token);
			
			// Verify if jti (jti is a unique identifier) contains in the JWT (for PWA, bigTV --> jti contains(JWT2), for Android,ios,roku,singtel --> no jti(JWT1) )
			// Verify jti is not present in secondary device Roku JWT
			ApiVerifications.verifyStringdoesnotContain(SecondarydeviceJWT,SanctuaryConstants.JTI);
			
			// Verify jti is not present in primary device Android JWT
			ApiVerifications.verifyStringdoesnotContain(PrimarydeviceJWT,SanctuaryConstants.JTI);
			
			ApiVerifications.verifyStringMatching(devicestatus_json.get(SanctuaryConstants.STATUS).toString(), SanctuaryConstants.LINKED);
						
			ReporterLog.softAssert.assertAll();				
			ReporterLog.pass("Signed In", "Successfully Signed In using Link TV");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	
	@Test(priority = 2, groups = { SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID})
	public void device_PairingTest_JWT2()
	{
		String tvCode = "";
		String primaryDevice = SanctuaryConstants.RANDOM_DEVICE_ID;
		try
		{	
			// Secondary device is required to get the randomly generated key from the service.
			String input = DevicePairingControllers.construct_WebSocketRequest(SanctuaryConstants.JWT2_VERSION,primaryDevice,SanctuaryConstants.PLATFORM_BIGTV);
			JSONObject devicePairResponse = WebSocketListener.getResponseFromWebSocket(ApiConfigDetails.websocket_Url, input);
			JSONObject devicePairResponseData = (JSONObject) devicePairResponse.get(SanctuaryConstants.DATA);			
			tvCode = (String) devicePairResponseData.get(SanctuaryConstants.KEY);
			
			if(tvCode.equals(""))
				throw new Exception();
			
			TestUtilities.logReportPass("TVCode generated successfully: "+tvCode);

			// Secondary device
			Response client_res = ActivateControllers.ActivateClient_Webclient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			EvergentControllers.createSubsribedUser(email,deviceid);		
			Response res = SigninControllers.getOAuthToken_WebClient(email,deviceid);
			org.json.simple.JSONObject outh_data = (org.json.simple.JSONObject) ReusableMethods.rawtoJsonObject(res).get(SanctuaryConstants.DATA);
			String token = (String) outh_data.get(SanctuaryConstants.ACCESS_TOKEN);
			
			
			// get account details(contact id and sp account id) from eve
			Response getContact_Res = EveControllers.getContact(token);
			org.json.simple.JSONObject account_data = (org.json.simple.JSONObject) ReusableMethods.rawtoJsonObject(getContact_Res).get(SanctuaryConstants.DATA);
			JSONArray contactMessageList = (JSONArray) account_data.get(SanctuaryConstants.CONTACT_MESSAGE);
			org.json.simple.JSONObject contactMessage = (org.json.simple.JSONObject) contactMessageList.get(0);
			String contactID = contactMessage.get(SanctuaryConstants.CONTACT_ID).toString();
			String spAccountID = account_data.get(SanctuaryConstants.SP_ACCOUNT_ID).toString();
			
			// webhook call to Sanctuary
			@SuppressWarnings("serial")
			HashMap<String,String> accountMap = new HashMap<String,String>() {{ put(SanctuaryConstants.CONTACT_ID , contactID); 
																				put(SanctuaryConstants.SP_ACCOUNT_ID,spAccountID);
																				put(SanctuaryConstants.EMAIL,email);}};
																				
			Response webHook = EveControllers.webHookCall(token, accountMap);
			org.json.simple.JSONObject webHook_json = (org.json.simple.JSONObject) ReusableMethods.rawtoJsonObject(webHook) ;
			
			System.out.println("webhook: "+webHook_json);
			
			//Primary device is required to validate key as shown in the secondary device. To validate a key, send a message with this info:			
			String validate_tvCode = DevicePairingControllers.construct_WebSocketValidate(tvCode,email,ApiConfigDetails.country,token);
										
			JSONObject device_Validate = WebSocketListener.getResponseFromWebSocket(ApiConfigDetails.websocket_Url, validate_tvCode);		
			
			JSONObject deviceValidateResponseData = (JSONObject) device_Validate.get(SanctuaryConstants.DATA);
			
			ApiVerifications.verifyiftrue(((boolean)deviceValidateResponseData.get(SanctuaryConstants.MATCH)));
		
			// To check the status of the secondary device
			Response device_status = DevicePairingControllers.deviceStatus(primaryDevice);
			
			org.json.simple.JSONObject devicestatus_json = (org.json.simple.JSONObject) ReusableMethods.rawtoJsonObject(device_status);
			
			String SecondarydeviceJWT= ReusableMethods.decode_JWT(devicestatus_json.get(SanctuaryConstants.TOKEN).toString());
			
			String PrimarydeviceJWT= ReusableMethods.decode_JWT(token);
			
			// Verify if jti (jti is a unique identifier) contains in the JWT2 (for PWA, bigTV --> jti contains(JWT2), and no jti for JWT1 Android,ios,roku,singtel --> no jti(JWT1) )
			// Verify jti is present in secondary device Roku JWT
			ApiVerifications.verifyStringContains(SecondarydeviceJWT,SanctuaryConstants.JTI);
			
			// Verify jti is present in primary device Android JWT
			ApiVerifications.verifyStringContains(PrimarydeviceJWT,SanctuaryConstants.JTI);
			
			ApiVerifications.verifyStringMatching(devicestatus_json.get(SanctuaryConstants.STATUS).toString(), SanctuaryConstants.LINKED);
						
			ReporterLog.softAssert.assertAll();				
			ReporterLog.pass("Signed In", "Successfully Signed In using Link TV");
			
		}
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		
	}

}

package sanctuary.modules;

import static io.restassured.RestAssured.given;


import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.DBhelpers;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class CatalogControllers 
{
	public static Response getCatalogPlay(String titleid,String devicesignature,String auth_Bearer)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.catalog_Base_Url;
			RestAssured.basePath = "/catalog/play/"+titleid;
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-dhs-drm",ApiConfigDetails.client_drm);
			header.put("User-Agent",ApiConfigDetails.platform);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			System.out.println(ApiConfigDetails.client_apikey);
			System.out.println(ApiConfigDetails.client_drm);
			
			// Consume the api of Http get method
			res=
					given().
					headers(header).
					when().
					get().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;	
	 } 
	
	
	public static Response putCatalogPlay(String titleid,String devicesignature,String auth_Bearer,int position,int length)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.catalog_Base_Url;
			RestAssured.basePath = "/catalog/play/"+titleid;
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("Content-Type","application/json");
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-dhs-drm",ApiConfigDetails.client_drm);
			header.put("User-Agent",ApiConfigDetails.platform);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			System.out.println(ApiConfigDetails.client_apikey);
			System.out.println(ApiConfigDetails.client_drm);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("position",position);
			payLoad.put("length", length);

			
			// Consume the api of http put method
			res=
					given().
					headers(header).
					body(payLoad).
					when().
					put().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;	
	 } 	
	
	
	public static Response getTVODCatalogPlay(String titleid,String devicesignature,String auth_Bearer)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.catalog_Base_Url;
			RestAssured.basePath = "/tvod/play/"+titleid;
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-dhs-drm",ApiConfigDetails.client_drm);
			header.put("User-Agent",ApiConfigDetails.platform);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			System.out.println(ApiConfigDetails.client_apikey);
			System.out.println(ApiConfigDetails.client_drm);
			
			// Consume the api of Http get method
			res=
					given().
					headers(header).
					when().
					get().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;	
	 } 
	
	
	
	public static void verify_Contenteligibility(String userType,String deviceid,String email,String titleid,String country)
	{
		try
		{
				
			Map<String,String> userDetails = null;
			
			String devicesignature = null;
			String authorization = null;
			Response getcatalog_res = null;	
			
			if(userType.equalsIgnoreCase(SanctuaryConstants.SUBSCRIBED_USER))
			{
				
				JSONObject emailexists = DBhelpers.dbconnect("select email from accounts where email = "+"'"+ email +"'" +" ", country);
				if (emailexists.isEmpty())
				{				
					userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
					devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
					authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get("token").toString();
					
				}
				else
				{
					String email_Subscribed = ApiConfigDetails.get_titles.get("email_Subscribed").toString();
					email = email_Subscribed;
					Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
					devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();				
					SigninControllers.SigninEmail(devicesignature,email);
					SigninControllers.signinVerifyemail(devicesignature,email);
					authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();					
				}
			}
			else if(userType.equalsIgnoreCase(SanctuaryConstants.REGISTERED_USER))
			{
				
				JSONObject emailexists = DBhelpers.dbconnect("select email from accounts where email = "+"'"+ email +"'" +" ", country);
				if (emailexists.isEmpty())
				{					
					userDetails = EvergentControllers.createLapsedUser(email,deviceid);
					devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
					authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
				}
				else
				{
					String email_Registered = ApiConfigDetails.get_titles.get("email_Registered").toString();
					email = email_Registered;
					Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
					devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();				
					SigninControllers.SigninEmail(devicesignature,email);
					SigninControllers.signinVerifyemail(devicesignature,email);
					authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
				}
			}
			else if(userType.equalsIgnoreCase(SanctuaryConstants.VISITOR))
			{
				Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
				devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
				
				System.out.println(email);
				// Activate visitor
				Response visitor = ActivateControllers.ActivateVisitor(devicesignature);
				authorization = ReusableMethods.rawtoJsonObject(visitor).get(SanctuaryConstants.TOKEN).toString();
			}
							 					
			//account Me
			Response account_Me = SigninControllers.accountMe(devicesignature,authorization);
			
			// User level
			int userLevel =  Integer.parseInt(ReusableMethods.rawtoJsonObject(account_Me).get("userLevel").toString());
			
			// Content level
			JSONObject titleJSON = DBhelpers.dbconnect("select content_level from titles where id = "+"'"+ titleid +"'" +" ", country);
			
			int contentLevel = Integer.parseInt(titleJSON.get("content_level").toString());
			
			if(contentLevel == 20)
			{
				// get catalog play for TVOD as the content level is 20
				getcatalog_res = CatalogControllers.getTVODCatalogPlay(titleid,devicesignature,authorization);
			}
			else if(contentLevel == 30 || contentLevel == 10)
			{
				// get catalog play for SVOD as the content level is 30 or 10
				getcatalog_res = CatalogControllers.getCatalogPlay(titleid,devicesignature,authorization);
			}
					
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("getCatalogPlay", "get catalog accurate accurate response "+getcatalog_res.getStatusCode());	
			
			
			if((userLevel == 30) && (contentLevel == 30 || contentLevel == 20 || contentLevel == 10))
			{
				if(ApiConfigDetails.platform.equalsIgnoreCase("Android"))
				{			
					ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get("content").toString());				
					ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get("license").toString());
					ReporterLog.pass("ContentEligibilityPassforuserLevel30inAndroid","The user is allowed to playback and the title is valid");
				}
				else if (ApiConfigDetails.platform.equalsIgnoreCase("ios"))
				{							
					JSONObject com_apple = fetchIOSlicense(getcatalog_res);					
					// Verify the certificate and key request url
					ApiVerifications.verifyNotNull(com_apple.get("certificate_url").toString());
					ApiVerifications.verifyNotNull(com_apple.get("key_request_url").toString());
					ReporterLog.pass("ContentEligibilityPassforuserLevel30inIOS","The user is allowed to playback and the title is valid");					
				}
				
			}
			
			if((userLevel == 20) && (contentLevel == 10 || contentLevel == 20))
			{
				if(ApiConfigDetails.platform.equalsIgnoreCase("Android"))
				{			
					ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get("content").toString());				
					ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get("license").toString());
					ReporterLog.pass("ContentEligibilityPassforuserLevel20inAndroid","The user is allowed to playback and the title is valid");	
				}
				else if (ApiConfigDetails.platform.equalsIgnoreCase("ios"))
				{					
					JSONObject com_apple = fetchIOSlicense(getcatalog_res);				
					// Verify the certificate and key request url
					ApiVerifications.verifyNotNull(com_apple.get("certificate_url").toString());
					ApiVerifications.verifyNotNull(com_apple.get("key_request_url").toString());
					ReporterLog.pass("ContentEligibilityPassforuserLevel20inIOS","The user is allowed to playback and the title is valid");						
				}				
			}
			
			Map<String,String> errorMap = null;						
			if(((userLevel == 20) || (userLevel == 10)) && (contentLevel == 30))
			{
				errorMap = getErrorcode(getcatalog_res);
				if (errorMap.get("status").equalsIgnoreCase("403") && errorMap.get("code").equalsIgnoreCase("5072"))
				{
					ReporterLog.pass("ContentEligibilityPassretreived403status","Hey There, please purchase a plan to enjoy HOOQ!");
				}				
			}
			
			if((userLevel == 10) && ((contentLevel == 30) || contentLevel == 20))
			{
				errorMap = getErrorcode(getcatalog_res);
				if (errorMap.get("status").equalsIgnoreCase("403") && errorMap.get("code").equalsIgnoreCase("5072"))
				{
					ReporterLog.pass("ContentEligibilityPassretreived403status","Hey There, please purchase a plan to enjoy HOOQ!");
				}				
			}
			
			if((userLevel == 10) && (contentLevel == 10))
			{
				if(ApiConfigDetails.platform.equalsIgnoreCase("Android"))
				{			
					ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get("content").toString());				
					ApiVerifications.verifyNotNull(ReusableMethods.rawtoJsonObject(getcatalog_res).get("license").toString());
					ReporterLog.pass("ContentEligibilityPassforuserLevel20inAndroid","The user is allowed to playback and the title is valid");	
				}
				else if (ApiConfigDetails.platform.equalsIgnoreCase("ios"))
				{					
					JSONObject com_apple = fetchIOSlicense(getcatalog_res);				
					// Verify the certificate and key request url
					ApiVerifications.verifyNotNull(com_apple.get("certificate_url").toString());
					ApiVerifications.verifyNotNull(com_apple.get("key_request_url").toString());
					ReporterLog.pass("ContentEligibilityPassforuserLevel20inIOS","The user is allowed to playback and the title is valid");	
					
				}				
			}					
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		
	}
	
	
	public static JSONObject fetchIOSlicense(Response getcatalog)
	{
		JSONObject com_apple = null;
		try {
			
		
			JSONObject  raw = (JSONObject) ReusableMethods.rawtoJsonObject(getcatalog).get("raw");			
			JSONArray sources = (JSONArray)  raw.get("sources");			
			JSONObject sources_list  = (JSONObject) sources.get(0);
			JSONObject key_systems = (JSONObject) sources_list.get("key_systems");			
			com_apple = (JSONObject) key_systems.get("com.apple.fps.1_0");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		
		return com_apple;
	}
	

	public static Map<String,String> getErrorcode(Response rawResponse)
	{
		Map<String,String> errorMap = new HashMap<String,String>();
		try {
			
			JSONArray error = (JSONArray) ReusableMethods.rawtoJsonObject(rawResponse).get("errors");
			JSONObject errorlist = (JSONObject) error.get(0);
			errorMap.put("status", errorlist.get("status").toString());
			errorMap.put("code", errorlist.get("code").toString());
			errorMap.put("detail", errorlist.get("detail").toString());
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		
		return errorMap;
	}
	
	
	
	public static Response getCataloglicense_Android(String title,String devicesignature,String authorization)
	{
		Response getcatalog_res = null;
		try
		{							
			// get catalog play for Android
			 getcatalog_res = CatalogControllers.getCatalogPlay(title,devicesignature,authorization);
																					
		}
		catch(Exception e){
			TestUtilities.logReportFailure(e,"The get catalog play for Android is not working as expected");
		}
		return getcatalog_res;	
	}
	
	public static Response getCataloglicense_IOS(String email,String deviceid)
	{
		Response getcatalog_res = null;
		try
		{
			
			Map<String,String>  userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			String devicesignature = userDetails.get("devicesignature");
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get("token").toString();
									
			// get catalog play for IOS
			getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get("Contentlevel_30").toString(),devicesignature,authorization);
																	
		}							
	catch(Exception e) {			
		ReusableMethods.logReportFailure(e,"The get catalog play for IOS is not working as expected");	
	}
	return getcatalog_res;
	
	}
	
	
	public static Response getCatalogPlay(String titleid,String devicesignature,String auth_Bearer,String apiKey,String drmformat,String userAgent)
	{
		Response res = null;
		
		String base_Url = null;
		String bearer = null;
		if(userAgent.equalsIgnoreCase("webclient")){
			base_Url = ApiConfigDetails.catalog_webclient_Url;
			bearer = "Bearer "+auth_Bearer;
		} else {
			base_Url = "https://api-"+ApiConfigDetails.sanctuary_Env+".hooq.tv/"+1.2;
		    bearer   = auth_Bearer; }
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = base_Url;
			RestAssured.basePath = "/catalog/play/"+titleid;
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", bearer);
			header.put("apiKey",apiKey);
			header.put("x-dhs-drm",drmformat);
			header.put("User-Agent",userAgent);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			System.out.println(apiKey);
			System.out.println(drmformat);
			System.out.println(userAgent);
			
			// Consume the api of Http get method
			res=
					given().
					headers(header).
					when().
					get().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;	
	 } 
	
	
	public static Response putCatalogPlay(String titleid,String devicesignature,String auth_Bearer,int position,int length,String apiKey,String drmformat,String userAgent)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.catalog_Base_Url;
			RestAssured.basePath = "/catalog/play/"+titleid;
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("Content-Type","application/json");
			header.put("apiKey",apiKey);
			header.put("x-dhs-drm",drmformat);
			header.put("User-Agent",userAgent);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			System.out.println(ApiConfigDetails.client_apikey);
			System.out.println(ApiConfigDetails.client_drm);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("position",position);
			payLoad.put("length", length);

			
			// Consume the api of http put method
			res=
					given().
					headers(header).
					body(payLoad).
					when().
					put().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;	
	 } 	
	
	public static Response getCatalogPlayforSingtelstb(String titleid,String devicesignature,String auth_Bearer,String hmac,String drmformat,
			String userAgent,String consumerUserName)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = "https://api-"+ApiConfigDetails.sanctuary_Env+".hooq.tv/"+1.0;
			RestAssured.basePath = "/catalog/play/"+titleid;
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("playback-hmac","Key4|"+hmac);
			header.put("x-dhs-drm",drmformat);
			header.put("User-Agent",userAgent);
			header.put("x-consumer-userName",consumerUserName);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			System.out.println(header.get("playback-hmac"));
			System.out.println(header.get("x-dhs-drm"));
			System.out.println(header.get("User-Agent"));
			
			// Consume the api of Http get method
			res=
					given().
					headers(header).
					when().
					get().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;	
	 } 
	
}

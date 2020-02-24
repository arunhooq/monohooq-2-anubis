package sanctuary.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;

import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;


public class SigninControllers 
{
	
	public static Response SigninEmail(String devicesignature,String email)
	{
		Response res = null;
		try
		{								
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signin";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("email",email);

			
			// Consume the api
			res=
					given().
					headers(header).
					body(payLoad).
					when().					
					post().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;
		
	}
	
	
	public static Response signinVerifyemail(String devicesignature,String email)
	{
		Response res = null;
		try
		{								
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signin/verify/email";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("email",email);
			payLoad.put("country",ApiConfigDetails.country);
			payLoad.put("otp","123456");

			
			// Consume the api
			res=
					given().
					headers(header).
					body(payLoad).
					when().
					post().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;
		
	}
	
	public static Response accountMe(String devicesignature,String auth_Bearer)
	{
		Response res = null;
		try
		{								
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/Account/Me";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("apiKey",ApiConfigDetails.client_apikey);			
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			
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
	
	@SuppressWarnings("unchecked")
	public static Response getOAuthToken_WebClient(String email,String deviceid)
	{
		
		Response res = null;
		try
		{								
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.eve_Base_Url;
			RestAssured.basePath = "/private/getOAuthAccessTokenv2";
			
			System.out.println(RestAssured.baseURI+RestAssured.basePath);
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			
			@SuppressWarnings("rawtypes")
			HashMap deviceMessage = new HashMap();
			deviceMessage.put("deviceType", "webClient");
			deviceMessage.put("deviceName", "webclient-aurora/production-2.15.0");
			deviceMessage.put("modelNo","webclient-aurora");
			deviceMessage.put("serialNo",deviceid);
					
			// payload or body details	
			@SuppressWarnings("rawtypes")
			HashMap  payLoad = new HashMap();
			payLoad.put("email",email);
			payLoad.put("contactUserName",email);
			payLoad.put("channelPartnerID",ApiConfigDetails.channelPartnerId);
			payLoad.put("ipAddress",ApiConfigDetails.ip_address);
			payLoad.put("deviceMessage",deviceMessage);
			
			
						
			// Consume the api
			res=
					given().
					headers(header).
					body(payLoad).
					when().
					post().
					then().log().ifError().extract().response();
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;
		
	}
	
	public static Response plan_Current(String devicesignature,String auth_Bearer)
	{
		Response res = null;
		try
		{								
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/plan/current";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("apiKey",ApiConfigDetails.client_apikey);			
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			
			// Consume the api of Http get method
			res=
					given().
					queryParam("invalidate",true).
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

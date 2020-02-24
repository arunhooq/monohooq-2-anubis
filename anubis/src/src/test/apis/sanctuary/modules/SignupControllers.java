package sanctuary.modules;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.SanctuaryConstants;


public class SignupControllers {
		
	public static Response SignupEmail(String devicesignature,String email,Map<String,Object> signup_map,String user)
	{
		Response res = null;
		boolean nova_flag = false;
		
		if(user.equalsIgnoreCase(SanctuaryConstants.SUBSCRIBED_USER) || ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_IN)) {
			nova_flag = (boolean) signup_map.get("nova");
		}
		
		try
		{		
			
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signup";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("email",email);
			payLoad.put("country",ApiConfigDetails.country);
			payLoad.put("nova",nova_flag);
			
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
	
		
	public static Response SignupverifyEmail(String devicesignature,String email,Map<String,Object> signup_map)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signup/verify/email";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("email",email);
			payLoad.put("country",ApiConfigDetails.country);
			payLoad.put("otp",signup_map.get("otp"));
			
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
	
	public static Response signupwith_Trustedemail(String devicesignature,String email,boolean trusted)
	{
		Response res = null;
		

		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signup";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("email",email);
			payLoad.put("country",ApiConfigDetails.country);
			payLoad.put("trusted",trusted);
			
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
	
	
	public static Response SignupPhone(String devicesignature,Map<String,Object> signup_map,String user)
	{
		Response res = null;
		boolean nova_flag = false;
		
		if(user.equalsIgnoreCase(SanctuaryConstants.SUBSCRIBED_USER)|| ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_IN)) {
			nova_flag = (boolean) signup_map.get("nova");
		}
		
		try
		{					
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signup";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("countryCode",ApiConfigDetails.countryCode);
			payLoad.put("phoneNumber",ApiConfigDetails.randomNumber);
			payLoad.put("password",signup_map.get("ph_Pwd"));
			payLoad.put("nova",nova_flag);
			
			System.out.println("phone number :"+ApiConfigDetails.randomNumber);
			
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
	
	public static Response SignupverifyPhone(String devicesignature,Map<String,Object> signup_map)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signup/verify/phone";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("countryCode",ApiConfigDetails.countryCode);
			payLoad.put("phoneNumber",ApiConfigDetails.randomNumber);
			payLoad.put("token",signup_map.get("otp"));
							
			
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
	
	
	public static Response SignupUpdate(String devicesignature,String accountId,String email,Map<String,Object> signup_map)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/signup/update";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("country",ApiConfigDetails.country);
			payLoad.put("email",email);
			payLoad.put("id",accountId);
			payLoad.put("trusted",signup_map.get("trusted"));
			
									
			// Consume the api
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
	
	
	
	
}

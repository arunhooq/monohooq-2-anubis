package sanctuary.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;

public class ActivateControllers
{
	
	public static Response ActivateClient(Map<String,Object> activateclient_map,String deviceid)
	{
		Response res = null;
		try
		{	
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/client/activate";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// pay load details
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("device_id", deviceid);
			payLoad.put("push_id", activateclient_map.get("push_id"));
			payLoad.put("useragent",activateclient_map.get("useragent"));
			payLoad.put("os", activateclient_map.get("os"));
			payLoad.put("version", activateclient_map.get("version"));
			payLoad.put("model",activateclient_map.get("model"));
			payLoad.put("tester", activateclient_map.get("tester"));
			
			// Consume the api of type method: post
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
	
	
	public static Response ActivateVisitor(String devicesignature)
	{
		Response res = null;
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/visitor/activate";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			
			// Consume the api
			res=
					given().
					headers(header).
					body(payLoad).
					when().
					post().
					then().log().ifError().extract().response();
		}
		catch(Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;
		
	}
	
	public static Response SetUp(Map<String,Object> activateclient_map,String deviceid,String devicesignature)
	{
		Response res = null;
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/setup";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("device-signature",devicesignature);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("device_id", deviceid);
			payLoad.put("push_id", activateclient_map.get("push_id"));
			payLoad.put("useragent",activateclient_map.get("useragent"));
			payLoad.put("os", activateclient_map.get("os"));
			payLoad.put("version", activateclient_map.get("version"));
			payLoad.put("model",activateclient_map.get("model"));
			payLoad.put("tester", activateclient_map.get("tester"));
			
			// Consume the api
			res=
					given().
					headers(header).
					body(payLoad).
					when().
					get().
					then().log().ifError().extract().response();
		}
		catch(Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		return res;
		
	}
	
	
	public static Response ActivateClient_Webclient(Map<String,Object> activateclient_map,String deviceid)
	{
		Response res = null;
		try
		{	
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/client/activate";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
			// pay load details
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("device_id", deviceid);
			payLoad.put("push_id", activateclient_map.get("push_id"));
			payLoad.put("useragent",activateclient_map.get("useragent"));
			payLoad.put("os", "webclient");
			payLoad.put("version", activateclient_map.get("version"));
			payLoad.put("model",activateclient_map.get("model"));
			payLoad.put("tester", activateclient_map.get("tester"));
			
			// Consume the api of type method: post
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
	
	public static String generate_deviceSignature(String deviceid)
	{
		String devicesignature = null;
		try
		{
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			return devicesignature;
		}
	    catch (Exception e) 
	    {
	    	TestUtilities.logReportFailure(e);
	    }
		return devicesignature;
		
	}
	
	
    public static String generate_JWT1Token(String deviceSignature,String email)
    {
    	String JWT1 = null;
    	try
    	{
    		    		
			// Sign in		
    		SigninControllers.SigninEmail(deviceSignature,email);
    		SigninControllers.signinVerifyemail(deviceSignature, email);
    		JWT1 = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(deviceSignature,email)).get("token").toString();
    				
    	}
		catch (Exception e) 
	    {
			TestUtilities.logReportFailure(e);
	    }
		return JWT1;
		
    }
    
    
    @SuppressWarnings("unchecked")
	public static Response deviceLimitCheck(String deviceSignature) throws ParseException
    {
    	Response res = null;
    	for(int i=0;i<=4;i++)
    	{
    		res = ActivateControllers.ActivateVisitor(deviceSignature);
    		
    	}
		return res;
    }
	

}




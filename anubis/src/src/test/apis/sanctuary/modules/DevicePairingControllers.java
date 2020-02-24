package sanctuary.modules;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DevicePairingControllers 
{
	
	public static String construct_WebSocketRequest(String apiVersion,String deviceid,String deviceModel)
	{
		
		
		String input = "{\"action\":\"request-key\",\"apiVersion\":\""+apiVersion+"\",\"device\":{\"deviceId\":\""+deviceid+"\",\"deviceModel\":\""+deviceModel+"\"}}";
		
		return input;
		
	}
	
	public static String construct_WebSocketValidate(String key,String email,String country,String token)
	{
		
		String validate_tvCode = "{\"action\":\"validate-key\",\"key\":\""+key+"\",\"credentials\":{\"email\":\""+email+"\",\"country\":\""+country+"\",\"token\":\""+token+"\"}}";
		
		return validate_tvCode;
		
	}
	

	public static Response deviceStatus(String deviceid)
	{
		Response res = null;
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = "http://pair-nightly.hooq.tv/";
			RestAssured.basePath = "1.1-alpha/key/status";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
						
			
			// Consume the api
			res=
					given().
					queryParam("deviceId",deviceid).					
					headers(header).
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
	
	
}

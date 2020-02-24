package sanctuary.modules;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;

public class EveControllers 
{
	
	public static Response getContact(String auth_Bearer)
	{
		Response res = null;
		try
		{	
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.eve_Base_Url;
			RestAssured.basePath = "/proxy/getContact";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("Authorization","Bearer "+auth_Bearer);
			
			
			// pay load details
			HashMap<String, Object>  payLoad = new HashMap<>();
			
			
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
	
	public static Response webHookCall(String auth_Bearer,HashMap<String, String> evecontactMap)
	{
		Response res = null;
		try
		{	
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.eve_Base_Url;
			RestAssured.basePath = "/webhook/contact";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("apiKey","GXH7CVfetyfA7QoMhCa9W7BvBfr4kxjc");
			
			// pay load details
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("spAccountID", evecontactMap.get("spAccountID"));
			payLoad.put("email", evecontactMap.get("email"));
			payLoad.put("contactID", evecontactMap.get("contactID"));
			payLoad.put("country", ApiConfigDetails.country);
			
			// Consume the api of type method: post
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

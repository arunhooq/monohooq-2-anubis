package sanctuary.modules;

import static io.restassured.RestAssured.given;


import java.util.HashMap;

import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;

public class DownloadControllers {
	

	public static Response download_Title(String devicesignature,String auth_Bearer,String titleid)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Download_Url;
			RestAssured.basePath = "/download/title";
			
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("Content-Type","application/json");	
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
		
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("title_id",titleid);
			

						
			// Consume the api of http post method
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

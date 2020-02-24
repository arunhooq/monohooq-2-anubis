package sanctuary.modules;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
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
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class HeartbeatControllers {
	
	
	
	public static Response postplaylist_list(String devicesignature,String auth_Bearer,String label)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/playlist/list";
			
			
			ArrayList<String> items = new ArrayList<String>();
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("Content-Type","application/json");	
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
		
			// payload or body details	
			HashMap<String, Object>  payLoad = new HashMap<>();
			payLoad.put("label",label);
			payLoad.put("items", items);

						
			// Consume the api of http post method
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
	
	
	
	public static Response getLastwatched(String devicesignature,String auth_Bearer)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/playlist/watched?invalidate=true&size=10&scope=detail";
			
					
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("Content-Type","application/json");	
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
							
			// http get method
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
	
	
	public static Response getHistory(String devicesignature,String auth_Bearer)
	{
		Response res = null;
		
		try
		{					
			// baseUrl and controller details
			RestAssured.baseURI  = ApiConfigDetails.sanctuary_Base_Url;
			RestAssured.basePath = "/playlist/history?invalidate=true&size=10&scope=detail";
			
					
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("device-signature",devicesignature);
			header.put("Authorization", auth_Bearer);
			header.put("Content-Type","application/json");	
			header.put("apiKey",ApiConfigDetails.client_apikey);
			header.put("x-forwarded-for",ApiConfigDetails.ip_address);
			
							
			// http get method
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
	
	
	public static void heartbeat_Validate(String userType,String deviceid,String email,int position,int length)
	{
		try
		{
			
			Map<String,String> userDetails = null;
			
			if(userType.equalsIgnoreCase(SanctuaryConstants.SUBSCRIBED_USER))
			{
				userDetails = EvergentControllers.createSubsribedUser(email,deviceid);
			}
			else if(userType.equalsIgnoreCase(SanctuaryConstants.REGISTERED_USER))
			{
				userDetails = EvergentControllers.createLapsedUser(email,deviceid);
			}
				
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
							
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
			
			//account Me
			Response account_Me = SigninControllers.accountMe(devicesignature,authorization);
		
			int userLevel =  Integer.parseInt(ReusableMethods.rawtoJsonObject(account_Me).get("userLevel").toString());
				
			// $lastwatched put call and verify status code
			ApiVerifications.verifyStatusCode(postplaylist_list(devicesignature,authorization,"$LASTWATCHED").statusCode(), 201);
			
			// $history put call and verify status code
			ApiVerifications.verifyStatusCode(postplaylist_list(devicesignature,authorization,"$HISTORY").statusCode(), 201);
			
			// get catalog play 
			Response getcatalog_res = CatalogControllers.getCatalogPlay(ApiConfigDetails.get_titles.get("Contentlevel_10").toString(),devicesignature,authorization);
					
			// verify get catalog play status code
			ApiVerifications.verifyStatusCode(getcatalog_res.statusCode(),200);
										
			// put catalog play 
			Response putcatalog_res = CatalogControllers.putCatalogPlay(ApiConfigDetails.get_titles.get("Contentlevel_10").toString(),devicesignature,authorization,position,length);
			
			// verify put catalog play status code
		    ApiVerifications.verifyStatusCode(putcatalog_res.statusCode(),204);
							
			// get play list watched
			ApiVerifications.verifyStatusCode(getLastwatched(devicesignature,authorization).statusCode(),200);
						
			JSONObject playlistWatched = ReusableMethods.rawtoJsonObject(getLastwatched(devicesignature,authorization));
			
			System.out.println("playlistwatched"+playlistWatched.get("data"));
			
				
			
			// get play list watched 
			ApiVerifications.verifyStatusCode(getHistory(devicesignature,authorization).statusCode(),200);
			
			JSONObject playlistHistory = ReusableMethods.rawtoJsonObject(getHistory(devicesignature,authorization));
			
			JSONArray playArrayList = new JSONArray();
						
						
			if((userLevel == 30 || userLevel == 20) && (position > 3 && position < 50))
			{
				if ((playlistWatched.get("data") != playArrayList) && (playlistHistory.get("data").equals(playArrayList)))
				{
					JSONArray playlistWatched_Array =  (JSONArray) playlistWatched.get("data");		
					JSONObject playlistWatched_data = (JSONObject) playlistWatched_Array.get(0);		
					JSONObject playlistWatched_Attributes = (JSONObject) playlistWatched_data.get("attributes");
					ApiVerifications.verifyStringhasdecimal(playlistWatched_Attributes.get("position").toString());
					ApiVerifications.verifyStringhasdecimal(playlistWatched_Attributes.get("duration").toString());
					ReporterLog.pass("HeartbeatValidate","The heartbeat is captured accurately for subscribed user when position is greater than 3 and less than 50");
				}
			}
			
			if((userLevel == 30 || userLevel == 20) && (position > 50 && position < 95))
			{
				if ((playlistWatched.get("data") != playArrayList) && (playlistHistory.get("data") != playArrayList))
				{
					JSONArray playlistWatched_Array =  (JSONArray) playlistWatched.get("data");		
					JSONObject playlistWatched_data = (JSONObject) playlistWatched_Array.get(0);		
					JSONObject playlistWatched_Attributes = (JSONObject) playlistWatched_data.get("attributes");
					ApiVerifications.verifyStringhasdecimal(playlistWatched_Attributes.get("position").toString());
					ApiVerifications.verifyStringhasdecimal(playlistWatched_Attributes.get("duration").toString());
					ReporterLog.pass("HeartbeatValidate","The heartbeat is captured accurately for subscribed or registered user when position is greater than 50 and less than 95");
				}
			}
			
			if((userLevel == 30 || userLevel == 20) && (position > 95 && position < 100))
			{
				if ((playlistWatched.get("data").equals(playArrayList) && (playlistHistory.get("data") != playArrayList)))
				{
					ReporterLog.pass("HeartbeatValidate","The heartbeat is captured accurately for subscribed or registered user when position is greater than 95 and less than 100");
				}
			}
			
			if((userLevel == 30 || userLevel == 20) && (position < 3 ))
			{
				if ((playlistWatched.get("data").equals(playArrayList) && (playlistHistory.get("data").equals(playArrayList))))
				{
					ReporterLog.pass("HeartbeatValidate","The heartbeat is captured accurately for subscribed or registered user when position is greater than 95 and less than 100");
				}
			}
			
			ReporterLog.softAssert.assertAll();											
		}
		catch(Exception e) {	
			
			TestUtilities.logReportFailure(e,"The heartbeat is not working as expected");			
		}
	}
	
	public static Response hearbeatcheck_forvisitor(String titleid,String device_id,int length,int position)
	{
		Response putcatalog_res = null;
		try
		{
			
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),device_id);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
								
			// Activate visitor
			Response visitor = ActivateControllers.ActivateVisitor(devicesignature);
			
			String authorization = ReusableMethods.rawtoJsonObject(visitor).get(SanctuaryConstants.TOKEN).toString();
			
			CatalogControllers.getCatalogPlay(titleid,devicesignature,authorization);
			
			putcatalog_res = CatalogControllers.putCatalogPlay(titleid,devicesignature,authorization,length,position);
		
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"The heartbeat endpoint is not deprecated for visitor");			
		}
		
		return putcatalog_res;
		
	}
	
	public static boolean isNumeric(String strNum) {
	    return strNum.matches("-?\\d+(\\.\\d+)?");
	}

}

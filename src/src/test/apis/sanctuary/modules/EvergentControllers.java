package sanctuary.modules;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.JSONUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class EvergentControllers 
{
	
	
	public static JSONObject evergentSearchaccount(String email)
	{
		Response res = null;
		JSONObject jsonObject = null;
		
		try
		{
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.evegent_Base_Url;
			RestAssured.basePath = "/qp/searchAccount";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
						
			// Consume the api of method type : post
			res=
					given().
					queryParam("email",email).
					queryParam("channelPartnerID",ApiConfigDetails.channelPartnerId).
					queryParam("apiuser",ApiConfigDetails.eve_Searchaccount_User).
					queryParam("apipassword",ApiConfigDetails.eve_Searchaccount_Pwd).
					headers(header).
					when().
					post().
					then().log().ifError().extract().response();
			jsonObject = ReusableMethods.rawtoJsonObject(res);
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
		
		
		return  jsonObject;
	}
	
	public static JSONObject evergentActivatecustomer(String cpCustomerID,String user) throws ParseException
	{
	    Response res = null;
		JSONObject jsonObject = null;
		String method,sku = null;
		int endDate = 0;
		
		if(user.equalsIgnoreCase("lapsed")) 
		{
			if(!ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_IN))
			{				
				method = "deactivate";
				sku = ApiConfigDetails.country+"-OTT-FREE07";
				endDate = 1;
			}
			else
			{
				method = "deactivate";
				sku = null;
				endDate = 1;
				
			}
		}		
		else 
		{	
			method = "activate";
			sku = ApiConfigDetails.sku;
			
		}
			
		try
		{					
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.evegent_Base_Url;
			RestAssured.basePath = "/qp/hooq/activateCustomerV3";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
						
			// Consume the api of method type : post
			res=
					given().
					queryParam("method",method).
					queryParam("sku",sku).
					queryParam("cpCustomerID",cpCustomerID).
					queryParam("endDate",endDate).
					queryParam("apiKey",ApiConfigDetails.evergent_apikey).
					queryParam("channelPartnerID",ApiConfigDetails.channelPartnerId).
					queryParam("secret",ApiConfigDetails.evergent_secret).
					headers(header).
					when().
					post().
					then().log().ifError().extract().response();
			jsonObject = ReusableMethods.rawtoJsonObject(res);
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
				
		return  jsonObject;				
	}
	
	
	public static Map<String,String> createSubsribedUser(String email,String device_id)
	{
		Map<String,String> Userdetails = new HashMap<String,String>();
		
		try
		{				
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails("activateclient"),device_id);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get("device_signature").toString();
					
			// Activate visitor
			ActivateControllers.ActivateVisitor(devicesignature);
			
			// Sign up
			SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails("signup"),"subscribed");
			
			// Sign up verify email
			SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails("signup"));
			
			// Sign in
			Response signin_res = SigninControllers.SigninEmail(devicesignature, email);
			String authorization = ReusableMethods.rawtoJsonObject((signin_res)).get("token").toString();
			
			// Perform evergent search account to get the cpcustomerid
			
			JSONObject evergent_res = EvergentControllers.evergentSearchaccount(email);							
			JSONObject cpCustomerID = (JSONObject) evergent_res.get("SearchAccountResponseMessage");	
						
			// Perform activate customer in order to create subscribed user		
	        EvergentControllers.evergentActivatecustomer(cpCustomerID.get("cpCustomerID").toString(),"subscribed");
	        
	        Userdetails.put("device_signature", devicesignature);
	        Userdetails.put("authorization",authorization);
	        	        
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	 
		return Userdetails;
	}
	
	public static Map<String,String> createLapsedUser(String email,String deviceid)
	{
		Map<String,String> Userdetails = new HashMap<String,String>();
		try
		{
			
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails("activateclient"),deviceid);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get("device_signature").toString();
			
			System.out.println(email);
			// Activate visitor
			ActivateControllers.ActivateVisitor(devicesignature);
			
			// Sign up
			SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails("signup"),"lapsed");
			
			// Sign up verify email
			SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails("signup"));
			
			Response signin = SigninControllers.SigninEmail(devicesignature, email);
			
			System.out.println(signin.getStatusCode());
			
			// Perform evergent search account to get the cpcustomerid
			
			JSONObject evergent_res = EvergentControllers.evergentSearchaccount(email);							
			JSONObject cpCustomerID = (JSONObject) evergent_res.get("SearchAccountResponseMessage");
			
			// Perform activate customer in order to create lapsed user			
			JSONObject user = EvergentControllers.evergentActivatecustomer(cpCustomerID.get("cpCustomerID").toString(),"lapsed");
	        
	        if(JSONUtilities.getJsonValueUsingPath(user.toString(),
            		"$['ActivateCustomerV3RespMessage'].message").equals(SanctuaryConstants.DEACTIVATE_MESSAGE))
            {
            	ReporterLog.pass("Create lapsed user", "The lapsed user is created successfully");
            }
	        
	        Userdetails.put("device_signature", devicesignature);  
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		
		return Userdetails;
		
	}
	
	
	public static JSONObject evergentActivatecustomerbasedonSKU(String cpCustomerID,String sku) throws ParseException
	{
	    Response res = null;
		JSONObject jsonObject = null;	
		int endDate = 1;
					
		try
		{					
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.evegent_Base_Url;
			RestAssured.basePath = "/qp/hooq/activateCustomerV3";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
						
			// Consume the api of method type : post
			res=
					given().
					queryParam("method","activate").
					queryParam("sku",sku).
					queryParam("cpCustomerID",cpCustomerID).
					queryParam("endDate",endDate).
					queryParam("apiKey",ApiConfigDetails.evergent_apikey).
					queryParam("channelPartnerID",ApiConfigDetails.channelPartnerId).
					queryParam("secret",ApiConfigDetails.evergent_secret).
					headers(header).
					when().
					post().
					then().log().ifError().extract().response();
			jsonObject = ReusableMethods.rawtoJsonObject(res);
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
				
		return  jsonObject;				
	}
	
	
	public static Map<String,String> createSubsribedUser(String email,String device_id,String sku)
	{
		Map<String,String> Userdetails = new HashMap<String,String>();
		
		try
		{				
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails("activateclient"),device_id);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get("device_signature").toString();
					
			// Activate visitor
			ActivateControllers.ActivateVisitor(devicesignature);
			
			// Sign up
			SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails("signup"),"subscribed");
			
			// Sign up verify email
			SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails("signup"));
			
			// Sign in
			Response signin_res = SigninControllers.SigninEmail(devicesignature, email);
			String authorization = ReusableMethods.rawtoJsonObject((signin_res)).get("token").toString();
			
			// Perform evergent search account to get the cpcustomerid
			
			JSONObject evergent_res = EvergentControllers.evergentSearchaccount(email);							
			JSONObject cpCustomerID = (JSONObject) evergent_res.get("SearchAccountResponseMessage");	
						
			// Perform activate customer in order to create subscribed user		
	        EvergentControllers.evergentActivatecustomerbasedonSKU(cpCustomerID.get("cpCustomerID").toString(),sku);
	        
	        Userdetails.put("device_signature", devicesignature);   
	        Userdetails.put("authorization",authorization);
	        
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	 
		return Userdetails;
	}
	
	
	@SuppressWarnings("unchecked")
	public static JSONObject phantomLogin(String email,String deviceid,String hmac)
	{
		Response res = null;
		JSONObject jsonObject = null;
		
		try
		{					
			// baseUrl and controller/resource details
			RestAssured.baseURI  = ApiConfigDetails.evegent_Base_Url;
			RestAssured.basePath = "/qp/phantomLogin";
			
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			header.put("x-auth-hooq","Key8|"+hmac);
			
			
			
			// Consume the api of method type : post
			res=
					given().
					queryParam("invalidateOldestSession",true).
					queryParam("contactUserName",email).
					queryParam("deviceType",ApiConfigDetails.getOSDetails(ApiConfigDetails.platform)).
					queryParam("deviceName","Redmi Note 5 Pro/8.1.0").
					queryParam("deviceId",deviceid).
					queryParam("apiUser",ApiConfigDetails.eve_Searchaccount_User).
					queryParam("apiPassword",ApiConfigDetails.eve_Searchaccount_Pwd).					
					queryParam("locale","eng_SG").
					queryParam("channelPartnerID","HAWK").
					queryParam("apiuser",ApiConfigDetails.eve_Searchaccount_User).
					queryParam("apipassword",ApiConfigDetails.eve_Searchaccount_Pwd).
					headers(header).
					when().
					post().
					then().log().ifError().extract().response();
			
			jsonObject = ReusableMethods.rawtoJsonObject(res);
		}
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
			
		return  jsonObject;		
			
	}
}

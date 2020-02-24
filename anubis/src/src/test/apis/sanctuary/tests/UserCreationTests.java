package sanctuary.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.JSONUtilities;
import io.restassured.response.Response;
import sanctuary.modules.*;
import sanctuary.utils.SanctuaryConstants;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ReusableMethods;


public class UserCreationTests extends TestConfiguration 
{
			
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void createSubscribedUserTest()
	{
		try
		{
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
					
			// Activate visitor
			ActivateControllers.ActivateVisitor(devicesignature);
			
			// Sign up
			SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
			
			// Sign up verify email
			SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			Response signin = SigninControllers.SigninEmail(devicesignature, email);
			
			System.out.println(signin.getStatusCode());
			
			System.out.println(ReusableMethods.rawtoJsonObject(signin).get(SanctuaryConstants.TOKEN).toString());
			
			// Perform evergent search account to get the cpcustomerid
			
			JSONObject evergent_res = EvergentControllers.evergentSearchaccount(email);							
			JSONObject cpCustomerID = (JSONObject) evergent_res.get(SanctuaryConstants.SEARCH_ACCOUNT_RES);	
						
			// Perform activate customer in order to create subscribed user		
            JSONObject user = EvergentControllers.evergentActivatecustomer(cpCustomerID.get(SanctuaryConstants.CP_CUSTOMER_ID).toString(),SanctuaryConstants.SUBSCRIBED_USER);
            
            System.out.println(user);
            //Validating the Customer activation
                    
            if(JSONUtilities.getJsonValueUsingPath(user.toString(),
            		"$['ActivateCustomerV3RespMessage'].message").equals(SanctuaryConstants.ACTIVATE_MESSAGE))
            {
            	ReporterLog.pass("Create Subscribed user", "The subscribed user is created successfully with the sku");
            }
            else 
            	throw new Exception();
            						
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"The subcribed user is not created");
		}
	}
	
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void createLapsedUserTest()
	{
		try
		{
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			System.out.println(email);
			// Activate visitor
			ActivateControllers.ActivateVisitor(devicesignature);
			
			// Sign up
			SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.LAPSED_USER);
			
			// Sign up verify email
			SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			Response signin = SigninControllers.SigninEmail(devicesignature, email);
			
			System.out.println(signin.getStatusCode());
			
			// Perform evergent search account to get the cpcustomerid
			
			JSONObject evergent_res = EvergentControllers.evergentSearchaccount(email);							
			JSONObject cpCustomerID = (JSONObject) evergent_res.get(SanctuaryConstants.SEARCH_ACCOUNT_RES);
			
			// Perform activate customer in order to create lapsed user			
            JSONObject user = EvergentControllers.evergentActivatecustomer(cpCustomerID.get(SanctuaryConstants.CP_CUSTOMER_ID).toString(),SanctuaryConstants.LAPSED_USER);
           
            
            if(JSONUtilities.getJsonValueUsingPath(user.toString(),
            		"$['ActivateCustomerV3RespMessage'].message").equals(SanctuaryConstants.DEACTIVATE_MESSAGE))
            {
            	ReporterLog.pass("Create lapsed user", "The lapsed user is created successfully");
            }
            else 
            	throw new Exception();
            						
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"The lapsed user is not created");
		}
	}

		
}

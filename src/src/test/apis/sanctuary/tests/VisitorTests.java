package sanctuary.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import io.restassured.response.Response;
import sanctuary.modules.ActivateControllers;
import sanctuary.modules.EvergentControllers;
import sanctuary.modules.SigninControllers;
import sanctuary.modules.SignupControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.DBhelpers;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class VisitorTests extends TestConfiguration 
{
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void Visitorupgrade_ToSubscriber()
	{
		try
		{
					
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			// Set up
			ActivateControllers.SetUp(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT), deviceid, devicesignature);
						
			// Activate visitor
			Response visitor = ActivateControllers.ActivateVisitor(devicesignature);
			
			JSONObject visitor_json = ReusableMethods.rawtoJsonObject(visitor);
			
			
			// Sign up
			SignupControllers.SignupEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP),SanctuaryConstants.SUBSCRIBED_USER);
			
			// Sign up verify email
			SignupControllers.SignupverifyEmail(devicesignature, email,ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP));
			
			// Sign in
			Response signin_res = SigninControllers.SigninEmail(devicesignature, email);
			ReusableMethods.rawtoJsonObject((signin_res)).get(SanctuaryConstants.TOKEN).toString();
			
			// Verify the visitor subscribed flag is set to true
			JSONObject visitor_record = DBhelpers.dbconnect("select subscribed from visitors where id = "+"'"+ visitor_json.get(SanctuaryConstants.ID).toString() +"'" +" ", ApiConfigDetails.country);
			ApiVerifications.verifyiftrue((boolean) visitor_record.get(SanctuaryConstants.SUBSCRIBED_USER));
			
			
			JSONObject evergent_res = EvergentControllers.evergentSearchaccount(email);	
			JSONObject cpCustomerID = (JSONObject) evergent_res.get(SanctuaryConstants.SEARCH_ACCOUNT_RES);		
			String accountID = cpCustomerID.get(SanctuaryConstants.ACCOUNT_ID).toString();
			
			// Verify the visitor id is mapped to account ID in evergent
			ApiVerifications.verifyStringMatching(accountID, visitor_json.get(SanctuaryConstants.ID).toString());
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("Visitorupgrade_ToSubscriber", "Visitor is upgraded to subscriber successfully");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"Vistor is not upgraded to subscriber");			
		}
	}
			
}

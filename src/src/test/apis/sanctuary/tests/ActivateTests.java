package sanctuary.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import io.restassured.response.Response;
import sanctuary.modules.ActivateControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


public class ActivateTests extends TestConfiguration {

	@Test(priority = 1, groups = { SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void activateClientTest() 
	{
		try 
		{
			ReadTestData.fnAddTestRailScriptID(17924);
			// Activate client
			Response activate_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);	
			ApiVerifications.verifyStatusCode(activate_res.getStatusCode(), 201);
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("ActivateClientTest", "Activate client api successfull"+activate_res.getStatusCode());	

		} 
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 2, groups = { SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH,SanctuaryConstants.GROUP_IN})
	public void setupTests() 
	{
		try 
		{	ReadTestData.fnAddTestRailScriptID(18920);		
			// Activate client
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();
				
			// Set up
			Response setup_res = ActivateControllers.SetUp(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT), deviceid, devicesignature);						
			ApiVerifications.verifyStatusCode(setup_res.getStatusCode(), 200);	
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("setupTests", "set up api successfull"+setup_res.getStatusCode());	
			
		} 
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(priority = 3, groups = { SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH })
	public void activateVisitorTest()
	{	String userLevel = null;
		try 
		{
			ReadTestData.fnAddTestRailScriptID(22346);
			// Activate client
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);
			JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
			String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();

			// Activate visitor
			Response visitor_res = ActivateControllers.ActivateVisitor(devicesignature);
			
			// Verify Activate visitor status code
			ApiVerifications.verifyStatusCode(visitor_res.getStatusCode(), 200);			
			ReporterLog.pass("activate Visitor", "activate visitor api successfull"+visitor_res.getStatusCode());
			
			// Verify Activate visitor user level
			JSONObject visitor = ReusableMethods.rawtoJsonObject(visitor_res);			
			ApiVerifications.verifyStatusCode(Integer.parseInt((String) visitor.get(SanctuaryConstants.USERLEVEL)), 10);
			ReporterLog.softAssert.assertAll();			
			ReporterLog.pass("ActivateVisitorTest", "For Visitor userLevel is shown 10 as expected");
		
		} 
		catch (Exception e) 
		{
			TestUtilities.logReportFailure(e,"The visitor user level is not 10","UserLevel shown is: "+userLevel);
		}

	}
		
}

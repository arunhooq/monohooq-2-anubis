package sanctuary.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import io.restassured.response.Response;
import sanctuary.modules.AVODControllers;
import sanctuary.modules.ActivateControllers;
import sanctuary.modules.EvergentControllers;
import sanctuary.modules.SigninControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;

public class AVODTests extends TestConfiguration
{
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG})
	public void verify_OTT_RCU()
	{
				
		try
		{
					
			Map<String, String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid,SanctuaryConstants.SKU_OTT_RCU_SG);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
								
			// accountMe
			Response account_res = SigninControllers.accountMe(devicesignature,authorization);
			
			AVODControllers.validate_AVODbyCustomerType(account_res);
			 
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("AVODTestsOTTRCU", "AVOD Tests for OTT RCU is working as expected");
		
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"The AVOD for OTT customer type is not working as expected");
		}
		
	}
	
	 
	
	
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_SG}, enabled = false)
	public void verify_AVODforOTT_OneTime7days()
	{
		
		try
		{
					
			Map<String, String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid,SanctuaryConstants.SKU_OTT_ONETIME7DAYS_SG);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
								
			// accountMe
			Response account_res = SigninControllers.accountMe(devicesignature,authorization);
			
			AVODControllers.validate_AVODbyCustomerType(account_res);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("AVODTestsOTT_OneTime7days", "AVOD Tests for OTT One Time 7 days is working as expected");
		
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"The AVOD for One time > 7 days customer type is not working as expected");
		}
			
	}
		
			
	@Test(priority = 3, groups = {SanctuaryConstants.GROUP_ID})
	public void verify_AVODforOTT_1or7dayssachet()
	{
		try
			{
						
				Map<String, String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid,SanctuaryConstants.SKU_OTT_1OR7DAYSSACHET_ID);
				String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
				String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
									
				// accountMe
				Response account_res = SigninControllers.accountMe(devicesignature,authorization);
				
				AVODControllers.validate_AVODbyCustomerType(account_res);
				
				ReporterLog.softAssert.assertAll();	
				ReporterLog.pass("AVODTestsfor1or7days", "AVOD Tests for 1 or 7 days is working as expected");
			
			}
			catch(Exception e) {
				TestUtilities.logReportFailure(e,"The AVOD for 1 or 7 Sachet customer type is not working as expected");
			}
		
	 }
		
	 	
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_ID})
	public void verify_AVODforPrepaidhardbundles()
	{
		try
		{
					
			Map<String, String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid,SanctuaryConstants.SKU_PREPAIDHARDBUNDLE_ID);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
								
			// accountMe
			Response account_res = SigninControllers.accountMe(devicesignature,authorization);
			
			AVODControllers.validate_AVODbyCustomerType(account_res);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("AVODTestsPrepaidHardbundles", "AVOD Tests for Prepaid Hard Bundles is working as expected");
		
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"The AVOD for Prepaid hard bundles customer type is not working as expected");
		}
		
	}
		
		 	
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_ID})
	public void verify_AVODforhardbundles()
	{
			
		try
		{
					
			Map<String, String> userDetails = EvergentControllers.createSubsribedUser(email,deviceid,SanctuaryConstants.SKU_HARDBUNDLE_ID);
			String devicesignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			String authorization = ReusableMethods.rawtoJsonObject(SigninControllers.SigninEmail(devicesignature,email)).get(SanctuaryConstants.TOKEN).toString();
								
			// accountMe
			Response account_res = SigninControllers.accountMe(devicesignature,authorization);
			
			AVODControllers.validate_AVODbyCustomerType(account_res);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("AVODTestsHardbundles", "AVOD Tests for hard bundles is working as expected");
		
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"The AVOD for hard bundles customer type is not working as expected");
		}
			
	}
		
		
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_ID, SanctuaryConstants.GROUP_SG})
	public void verify_AVODforFreeUsers()
	{
		try
		{
					
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			String devicesignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
			
			Response visitor_res = ActivateControllers.ActivateVisitor(devicesignature);
			String authorization = ReusableMethods.rawtoJsonObject(visitor_res).get(SanctuaryConstants.TOKEN).toString();
										
			// accountMe
			Response account_res = SigninControllers.accountMe(devicesignature,authorization);
			
			AVODControllers.validate_AVODbyCustomerType(account_res);
			
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("AVODTestsVisitor", "AVOD Tests for Visitor is working as expected");
		
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"The AVOD for Visitor is not working as expected");
		}
		
	}

}

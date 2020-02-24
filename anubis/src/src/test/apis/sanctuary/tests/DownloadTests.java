package sanctuary.tests;

import sanctuary.modules.ActivateControllers;
import sanctuary.modules.DownloadControllers;
import sanctuary.modules.EvergentControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;


import java.util.Map;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import io.restassured.response.Response;

public class DownloadTests extends TestConfiguration 
{
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG})
	public void verify_TitleDownload_SubscribedUser()
	{
		try
		{
					
			Map<String,String> userDetails = EvergentControllers.createSubsribedUser(email.toLowerCase(),deviceid);
			String deviceSignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			
			String authorization = ActivateControllers.generate_JWT1Token(deviceSignature, email);
			
			Response download_res = DownloadControllers.download_Title(deviceSignature, authorization, ApiConfigDetails.getTitles(ApiConfigDetails.country).get(SanctuaryConstants.CONTENTLEVEL10).toString());	
			ApiVerifications.verifyStatusCode(download_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_204);
			
			Response download_SameTitle = DownloadControllers.download_Title(deviceSignature, authorization, ApiConfigDetails.getTitles(ApiConfigDetails.country).get(SanctuaryConstants.CONTENTLEVEL10).toString());
			ApiVerifications.verifyStatusCode(download_SameTitle.getStatusCode(), SanctuaryConstants.ERRCODE_DOWNLOAD);
			
			
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("Title Download", "Title already downloaded executed successfully "+download_SameTitle.getStatusCode());	
		}
		catch(Exception e) 
		{
			TestUtilities.logReportFailure(e,"Title already downloaded executed for subscribed user");
		}
				
	}
	
	
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG})
	public void verify_TitleDownload_RegisteredUser()
	{
		try
		{
					
			Map<String,String> userDetails = EvergentControllers.createLapsedUser(email.toLowerCase(),deviceid);
			String deviceSignature = userDetails.get(SanctuaryConstants.DEVICESIGNATURE);
			
			String authorization = ActivateControllers.generate_JWT1Token(deviceSignature, email);
			
			Response download_res = DownloadControllers.download_Title(deviceSignature, authorization, ApiConfigDetails.getTitles(ApiConfigDetails.country).get(SanctuaryConstants.CONTENTLEVEL10).toString());	
			ApiVerifications.verifyStatusCode(download_res.getStatusCode(), SanctuaryConstants.SUCCESSCODE_204);
			
			Response download_SameTitle = DownloadControllers.download_Title(deviceSignature, authorization, ApiConfigDetails.getTitles(ApiConfigDetails.country).get(SanctuaryConstants.CONTENTLEVEL10).toString());
			ApiVerifications.verifyStatusCode(download_SameTitle.getStatusCode(), SanctuaryConstants.ERRCODE_DOWNLOAD);
			
			
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("Title Download", "Title already downloaded executed successfully "+download_SameTitle.getStatusCode());	
		}
		catch(Exception e) 
		{
			TestUtilities.logReportFailure(e,"Title already downloaded executed for Registered user");
		}
				
	}
	
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG})
	public void verify_TitleDownload_Visitor()
	{
		try
		{					
			// Activate client			
			Response client_res = ActivateControllers.ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT),deviceid);		
			String deviceSignature = ReusableMethods.rawtoJsonObject((client_res)).get(SanctuaryConstants.DEVICESIGNATURE).toString();
					
			// Activate visitor
			Response activate_Visitor = ActivateControllers.ActivateVisitor(deviceSignature);
			String token = ReusableMethods.rawtoJsonObject((activate_Visitor)).get(SanctuaryConstants.TOKEN).toString();
			Response download_res = DownloadControllers.download_Title(deviceSignature, token, ApiConfigDetails.getTitles(ApiConfigDetails.country).get(SanctuaryConstants.CONTENTLEVEL10).toString());	
			System.out.println(ReusableMethods.rawtoJsonObject(download_res));
			ApiVerifications.verifyStatusCode(download_res.getStatusCode(), SanctuaryConstants.ERRSTATUS_VISITOR_DEPRECATE);
		}
		catch(Exception e) 
		{
			TestUtilities.logReportFailure(e,"The visitor is able to download the title");
		}
	}
	
}

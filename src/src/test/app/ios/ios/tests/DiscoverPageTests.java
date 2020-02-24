package ios.tests;

import java.util.List;

import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;
import ios.utils.IOSConstants;

public class DiscoverPageTests extends TestConfiguration  {
	
	@Test(priority = 1, groups = {GlobalConstant.GROUP_SANITY_ACTIVE,"verifyDiscoverPage" })
	public void verifyDiscoverPage()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22412);
			discoverPage.verifyHOOQLogo()
						.verifyDownloadIcon()
						.verifySearchButton()
						.verifyMeIcon();
			
	
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() 
																														   + " And Exception is "+e.getStackTrace());
		}
	}
	
	
	@Test(priority = 2, groups = {GlobalConstant.GROUP_SANITY_ACTIVE,GlobalConstant.GROUP_SANITY_LAPSED, "verifyQuickLinkNavigation" })
	public void verifyQuickLinkNavigation()  {
		try {
			ReadTestData.getTestRailID("22411,22421,");
			if(!(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_TH)))
				discoverPage.verifyQuickLinkPage();
			
		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "+e.getLocalizedMessage());
		}
	}
	
	
	//Spotlight 
	//1.Validate all spotlight title 
	//2.Validate that all Spotlight content is playable ...
	
	@Test(priority = 3, groups = {GlobalConstant.GROUP_SANITY_ACTIVE,GlobalConstant.GROUP_SANITY_LAPSED,GlobalConstant.GROUP_SANITY_Visitor, "verifySpotlightNavigation" })
	public void verifySpotlightNavigation()  {
		try {
			ReadTestData.getTestRailID("22410,22420,22417");					
			discoverPage.verifySpotLightNavigation();
			
		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "+e.getLocalizedMessage());
		}
	}
	
	
	
	
	//Test 2 -Quick Link Navigation for Lapsed
	
	//Test 3 -Quick Link Navigation for Visitors 
}

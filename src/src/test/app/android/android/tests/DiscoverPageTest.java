package android.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import android.tests.TestConfiguration;

public class DiscoverPageTest extends TestConfiguration {

	@Test(priority = 1, enabled = true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			"verify_Spotlight" })
	public void verify_Spotlight(){

		ReadTestData.fnAddTestRailScriptID(22624);
		try {
			discoverPage
			.swipeSpotlightFromRightToLeft()
			.swipeSpotlightFromLeftToRight()
			.clickOnSpotlight();
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	@Test(priority = 1, enabled = true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			"verify_QuickLinks_Navigation" })
	public void verify_QuickLinks_Navigation(){

		ReadTestData.fnAddTestRailScriptID(22625);
		try {
			discoverPage.quickLinks();
			ReporterLog.pass("verify_QuickLinks_Navigation", "Quick links are working fine");
		
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			"verify_ContinueWatching" })
	public void verify_ContinueWatching(){

		ReadTestData.fnAddTestRailScriptID(22629);
		try {
			searchPage.verifyContentSearchFunctionality(ReadTestData.FREE_CONTENT);
			
			contentPage.clickPlayButton();
			ReporterLog.pass("", "");
		
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}

package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class DiscoverPageTests extends TestConfiguration{

	@Test(groups = { "Sanity_Active", "Verify_QuickLinks_Navigation" })
	public void Verify_QuickLinks_Navigation() {

		ReadTestData.fnAddTestRailScriptID(22377);
		try {
			
			basePage.getDiscoverPage().verifyQuickLinks();			
			ReporterLog.softAssert.assertAll();
			ReporterLog.pass("Verify_QuickLinks_Navigation", "QuickLinks Navigation successful");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(groups = { "Sanity_Lapsed", "Verify_SeeAllLink" })
	public void Verify_SeeAllLink() {

		ReadTestData.fnAddTestRailScriptID(22385);
		try {
						
			basePage.getDiscoverPage().verifySeeAll();
			ReporterLog.softAssert.assertAll();
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(groups = { "Sanity_Active", "Verify_ShowmoreLink"}, enabled = false )
	public void Verify_ShowmoreLink() {

		ReadTestData.fnAddTestRailScriptID(22378);
		try {
			
			basePage.getDiscoverPage().verifyShowMore();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(groups = { "Sanity_Visitor", "Verify_Spotlight" }, enabled = true)
	public void Verify_Spotlight() {

		ReadTestData.fnAddTestRailScriptID(22370);
		try {
			
			basePage.getDiscoverPage().verifySpotlight_NEW(ConfigDetails.userType);

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(groups = { "Sanity_Lapsed", "Verify_PerformingSearch_FromDiscover" })
	public void Verify_PerformingSearch_FromDiscover() {

		ReadTestData.fnAddTestRailScriptID(22384);
		try {
			
			basePage.clickSearch()
			.typeSearch(ReadTestData.TVOD_CONTENT)
			.verifySearchResults(ReadTestData.TVOD_CONTENT);
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}

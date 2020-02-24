package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class ContentDetailsPageTests extends TestConfiguration{

	@Test(groups = { "Sanity_Visitor", "Verify_ContentDetails_TVShow"})
	public void Verify_ContentDetails_TVShow() {

		ReadTestData.fnAddTestRailScriptID(22373);
		try {
			basePage.getTVShowsPage()
					.getContentDetailsPage()
					.verifyContentDetailsCommon()
					.verifyContentDetailsForTVShow()
					.verify_Audio_Subtitle_ContentDetails();
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(groups = { "Sanity_Active", "Verify_ContentDetails_Movie_TVOD"})
	public void Verify_ContentDetails_Movie_TVOD() {

		ReadTestData.fnAddTestRailScriptID(22381);
		try {
			basePage.getMoviesPage()
					.getContentDetailsPage()
					.verifyContentDetailsCommon()
					.verify_Audio_Subtitle_ContentDetails();
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(groups = { "Sanity_Active", "Verify_ContentDetails_TVShow_Subscribed"})
	public void Verify_ContentDetails_TVShow_Subscribed() {

		ReadTestData.fnAddTestRailScriptID(22566);
		try {
			basePage.getTVShowsPage()
					.getContentDetailsPage()
					.verifyContentDetailsCommon()
					.verifyContentDetailsForTVShow()
					.verify_Audio_Subtitle_ContentDetails();
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	

}

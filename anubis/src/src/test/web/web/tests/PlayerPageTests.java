package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class PlayerPageTests extends TestConfiguration{


	@Test(groups = { "Sanity_Active", "Verify_Playback_PlayerWindowSettings_Movie" })
	public void Verify_Playback_PlayerWindowSettings_Movie() {

		ReadTestData.fnAddTestRailScriptID(22379);
		try {
			
			basePage.clickSearch()
			.typeSearch(ReadTestData.FREE_CONTENT)
			.verifySearchResults(ReadTestData.FREE_CONTENT)
			.clickOnSearchResult(ReadTestData.FREE_CONTENT)
			.verify_Audio_Subtitle_ContentDetails()
			.clickOnWatchNow()
			.verifyMoviePlayback()
			.verify_Audio_Subtitle_PlayerPage();
			ReporterLog.pass("Verify_Playback_PlayerWindowSettingsMovie", "Verify_Playback_PlayerWindowSettingsMovie executed succesfully");
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}


	@Test(groups = { "Sanity_Active", "Verify_Playback_PlayerWindowSettings_TVShow" })
	public void Verify_Playback_PlayerWindowSettings_TVShow() {

		ReadTestData.fnAddTestRailScriptID(22380);
		try {
			
			basePage.clickSearch()
			.typeSearch(ReadTestData.TV_SERIES)
			.verifySearchResults(ReadTestData.TV_SERIES)						
			.clickOnSearchResult(ReadTestData.TV_SERIES)
			.verify_Audio_Subtitle_ContentDetails()
			.clickOnWatchNow().verifyTVShowPlayback()
			.verify_Audio_Subtitle_PlayerPage();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 1, groups = {"Sanity_Visitor", "verify_Trailer_TVOD" })
	public void verify_Trailer_TVOD(){

		ReadTestData.fnAddTestRailScriptID(22372);
		try {
			basePage.clickSearch()
			.typeSearch(ReadTestData.TVOD_CONTENT)
			.verifySearchResults(ReadTestData.TVOD_CONTENT)
			.clickOnSearchResult(ReadTestData.TVOD_CONTENT)
			.clickOnWatchTrailer()
			.verifyTrailerPlayback();
						
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

}

package android.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import android.tests.TestConfiguration;

public class PlayerPageTest extends TestConfiguration {
	
	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			"verify_MoviePlayback" })
	public void verify_MoviePlayback() {

		ReadTestData.fnAddTestRailScriptID(22695);
		try {			
			searchPage.verifyContentSearchFunctionality(ReadTestData.FREE_CONTENT);
			
			contentPage.clickPlayButton().
			clickPause().clickPlay().
			clickSettings().verifySettings().closeSettingsQuality().
			clickQuality().verifyQuality().closeSettingsQuality().
			clickForward().clickRewind().getContentTitle(ReadTestData.FREE_CONTENT).
			verify_Playback();
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
			"verify_TVShowPlayback" })
	public void verify_TVShowPlayback(){

		ReadTestData.fnAddTestRailScriptID(22694);
		try {

			searchPage.verifyContentSearchFunctionality(ReadTestData.TV_SERIES);
			contentPage.clickPlayButton().			
			clickPause().clickPlay().
			clickSettings().verifySettings().closeSettingsQuality().
			clickQuality().verifyQuality().closeSettingsQuality().
			clickFindEpisodes().verifyFindEpisodes().closeFindEpisodes().
			clickForward().clickRewind().getContentTitle(ReadTestData.TV_SERIES).getEpisodeTitle().
			verify_Playback();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}	
	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_LAPSED,
			"verify_TVShow2ndEpisodePlayback" })
	public void verify_TVShow2ndEpisodePlayback(){

		ReadTestData.fnAddTestRailScriptID(29588);
		try {

			searchPage.verifyContentSearchFunctionality(ReadTestData.TV_SERIES);
			contentPage.play2ndEpisodeFromContentPage();			

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}	

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
			"verify_TVShowPlayerControls" })
	public void verify_TVShowPlayerControls(){

		ReadTestData.fnAddTestRailScriptID(29589);
		try {			
			searchPage.verifyContentSearchFunctionality(ReadTestData.TV_SERIES);
			contentPage.clickPlayButton().clickPause().clickPlay().
			clickSettings().verifySettings().closeSettingsQuality().
			clickQuality().verifyQuality().closeSettingsQuality().
			clickFindEpisodes().verifyFindEpisodes().closeFindEpisodes().
			clickForward().clickRewind().getContentTitle(ReadTestData.TV_SERIES).getEpisodeTitle();
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,			
			"verify_MoviePlayerControls" })
	public void verify_MoviePlayerControls(){

		ReadTestData.fnAddTestRailScriptID(29590);
		try {
			
			searchPage.verifyContentSearchFunctionality(ReadTestData.FREE_CONTENT);
			contentPage.clickPlayButton().clickPause().clickPlay().
			clickSettings().verifySettings().closeSettingsQuality().
			clickQuality().verifyQuality().closeSettingsQuality().
			clickForward().clickRewind().getContentTitle(ReadTestData.FREE_CONTENT);
		
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}		
	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,			
			"verify_TVODPlaybackByRedeemingTicket" })
	public void verify_TVODPlaybackByRedeemingTicket(){

		ReadTestData.fnAddTestRailScriptID(22687);
		try {
			
			searchPage.verifyContentSearchFunctionality(ReadTestData.TVOD_CONTENT);
			
			contentPage.clickRedeemTicket()
			.clickConfirmOnTVODPopup()
			.clickWatchNowOnTicketRedeemedPopup()
			.getContentTitle(ReadTestData.TVOD_CONTENT)
			.verify_Playback();
			playerPage.closePlayer().verify_TVODValidity("2 days left");
		
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
		
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			"verify_PlaybackOfR21_CorrectPIN" })
	public void verify_PlaybackOfR21_CorrectPIN() {

		ReadTestData.fnAddTestRailScriptID(29591);
		try {			
			searchPage.verifyContentSearchFunctionality(ReadTestData.R21_MOVIES);
			
			contentPage.clickPlayButton().verify_R21PINPopUP().type_R21PIN("1234").isPlayerWindowDisplayed();
			
			} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			"verify_PlaybackOfR21_IncorrectPIN" })
	public void verify_PlaybackOfR21_IncorrectPIN() {

		ReadTestData.fnAddTestRailScriptID(29592);
		try {			
			searchPage.verifyContentSearchFunctionality(ReadTestData.R21_MOVIES);
			
			contentPage.clickPlayButton().verify_R21PINPopUP().type_R21PIN("1111").verifyIncorrectPinErrorMsg();
			
			} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

}

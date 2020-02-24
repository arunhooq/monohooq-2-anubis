package android.tests;

import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;
import api.pojo.DiscoveryFeed.DiscoverFeedController;

public class ContentDetailsPageTest extends TestConfiguration {


	@Test(priority = 1, enabled = true, groups = { 			
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_ContentDetailsPage_Movies" })
	public void verify_ContentDetailsPage_Movies() {

		ReadTestData.fnAddTestRailScriptID(22634);
		try {

			//String svodMovie = TestUtilities.getRandomItem(DiscoverFeedController.getSVODNonR21MovieList(false));
	        //discoverPage.clickSearch().typeSearch(svodMovie).verifySearchResults().clickOnSearchResult(svodMovie);
			discoverPage.clickSearch().typeSearch(ReadTestData.FREE_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.FREE_CONTENT);

			contentPage.verify_Poster()
			.verify_Title()
			.verify_PlayButtonForMovie()
			.verify_MovieDuration()
			.verify_ContentAudio()
			.verify_ContentSubtitles()
			.verify_WatchList()
			.verify_Share()
			.verify_MovieDownload()
			.verify_ContentDescription()
			.verify_LabelCategory()
			.verify_CategoryValues()
			.verify_LabelDirector()
			.verify_LabelActors()			
			.verify_SimilarTitleLabel();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(priority = 1, enabled = true, groups = { 
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_ContentDetailsPage_TVShows" })
	public void verify_ContentDetailsPage_TVShows() {

		ReadTestData.fnAddTestRailScriptID(22631);
		try {
			//String svodTVseries = TestUtilities.getRandomItem(DiscoverFeedController.getTVShowList());
	        //discoverPage.clickSearch().typeSearch(svodTVseries).verifySearchResults().clickOnSearchResult(svodTVseries);
			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);

			contentPage.verify_Poster()
			.verify_Title()
			.verify_PlayButtonForTVShow()			
			.verify_ContentAudio()
			.verify_ContentSubtitles()
			.verify_WatchList()
			.verify_Share()
			.verify_ContentDescription()
			.verify_LabelCategory()
			.verify_CategoryValues()
			.verify_LabelDirector()
			.verify_LabelActors()			
			.verify_SeasonDropdown()
			.verify_EpisodeList()
			.verify_TVShowDownload()
			.verify_TVShowEpisodePlayIcon()
			.verify_EpisodeDuration()
			.verify_SimilarTitleLabel();


		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=true, priority = 1, groups = { 
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_ContentDetailsPage_Premium" })
	public void verify_ContentDetailsPage_Premium() {

		ReadTestData.fnAddTestRailScriptID(22637);
		try {
			
			discoverPage.clickSearch().typeSearch(ReadTestData.TVOD_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.TVOD_CONTENT);
			contentPage.verify_Poster()
			.verify_Title()
			.verify_PlayButtonForTVOD()
			.verify_MovieDuration()
			.verify_ContentAudio()
			.verify_ContentSubtitles()
			.verify_ContentDescription()
			.verify_LabelCategory()
			.verify_CategoryValues()
			.verify_LabelDirector()
			.verify_LabelActors()
			.verify_WatchList()
			.verify_Share()
			.verify_MovieDownload()
			.verify_SimilarTitleLabel();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	@Test(enabled=false,priority = 1, groups = { 
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_ContentDetailsPage_R21Movies" })
	public void verify_ContentDetailsPage_R21Movies() {

		ReadTestData.fnAddTestRailScriptID(29565);
		try {
			
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_MOVIES).verifySearchResults().clickOnSearchResult(ReadTestData.R21_MOVIES);
			contentPage.verify_Poster()
			.verify_Title()
			.verify_PlayButtonForMovie()
			.verify_MovieDuration()
			.verify_ContentAudio()
			.verify_ContentSubtitles()
			.verify_ContentDescription()
			.verify_LabelCategory()
			.verify_CategoryValues()
			.verify_LabelDirector()
			.verify_LabelActors()
			.verify_WatchList()
			.verify_Share()
			.verify_MovieDownload()
			.verify_SimilarTitleLabel();


		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false,priority = 1, groups = { 
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_ContentDetailsPage_R21TVShows" })
	public void verify_ContentDetailsPage_R21TVShows() {

		ReadTestData.fnAddTestRailScriptID(29566);
		try {
			
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_TVSHOWS).verifySearchResults().clickOnSearchResult(ReadTestData.R21_TVSHOWS);
			contentPage.verify_Poster()
			.verify_Title()
			.verify_PlayButtonForTVShow()			
			.verify_ContentAudio()
			.verify_ContentSubtitles()
			.verify_ContentDescription()
			.verify_LabelCategory()
			.verify_CategoryValues()
			.verify_LabelDirector()
			.verify_LabelActors()
			.verify_WatchList()
			.verify_Share()
			.verify_SeasonDropdown()
			.verify_EpisodeList()
			.verify_TVShowDownload()
			.verify_TVShowEpisodePlayIcon()
			.verify_EpisodeDuration()
			.verify_SimilarTitleLabel();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}


	@Test(enabled=false, priority = 1, groups = { 
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_ContentDetailsPage_R21Premium" })
	public void verify_ContentDetailsPage_R21Premium() {

		ReadTestData.fnAddTestRailScriptID(29567);
		try {
			
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_PREMIUM).verifySearchResults().clickOnSearchResult(ReadTestData.R21_PREMIUM);
			contentPage.verify_Poster()
			.verify_Title()
			.verify_PlayButtonForTVOD()
			.verify_MovieDuration()
			.verify_ContentAudio()
			.verify_ContentSubtitles()
			.verify_ContentDescription()
			.verify_LabelCategory()
			.verify_CategoryValues()
			.verify_LabelDirector()
			.verify_LabelActors()
			.verify_WatchList()
			.verify_Share()
			.verify_MovieDownload()
			.verify_SimilarTitleLabel();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_LikeFeature_ContentDetailsPage_TVShows" })
	public void verify_LikeFeature_ContentDetailsPage_TVShows() {

		ReadTestData.fnAddTestRailScriptID(29568);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);
			contentPage.verify_Like().clickLike().verify_Like_AfterClick().clickLike().verify_Liked_AfterClick();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_LikeFeature_ContentDetailsPage_Movies" })
	public void verify_LikeFeature_ContentDetailsPage_Movies() {

		ReadTestData.fnAddTestRailScriptID(29569);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.FREE_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.FREE_CONTENT);
			contentPage.verify_Like().clickLike().verify_Like_AfterClick().clickLike().verify_Liked_AfterClick();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_LikeFeature_ContentDetailsPage_Premium" })
	public void verify_LikeFeature_ContentDetailsPage_Premium() {

		ReadTestData.fnAddTestRailScriptID(29570);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.TVOD_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.TVOD_CONTENT);
			contentPage.verify_Like().clickLike().verify_Like_AfterClick().clickLike().verify_Liked_AfterClick();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_LikeFeature_ContentDetailsPage_R21TVShows" })
	public void verify_LikeFeature_ContentDetailsPage_R21TVShows() {

		ReadTestData.fnAddTestRailScriptID(29571);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_TVSHOWS).verifySearchResults().clickOnSearchResult(ReadTestData.R21_TVSHOWS);
			contentPage.verify_Like().clickLike().verify_Like_AfterClick().clickLike().verify_Liked_AfterClick();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_LikeFeature_ContentDetailsPage_R21Movies" })
	public void verify_LikeFeature_ContentDetailsPage_R21Movies() {

		ReadTestData.fnAddTestRailScriptID(29572);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_MOVIES).verifySearchResults().clickOnSearchResult(ReadTestData.R21_MOVIES);
			contentPage.verify_Like().clickLike().verify_Like_AfterClick().clickLike().verify_Liked_AfterClick();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_LikeFeature_ContentDetailsPage_R21Premium" })
	public void verify_LikeFeature_ContentDetailsPage_R21Premium() {

		ReadTestData.fnAddTestRailScriptID(29573);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_PREMIUM).verifySearchResults().clickOnSearchResult(ReadTestData.R21_PREMIUM);
			contentPage.verify_Like().clickLike().verify_Like_AfterClick().clickLike().verify_Liked_AfterClick();

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_Trailer_Premium" })
	public void verify_Trailer_Premium() {

		ReadTestData.fnAddTestRailScriptID(29574);
		try {
			discoverPage.clickSearch()
			.typeSearch(ReadTestData.TVOD_CONTENT)
			.verifySearchResults()
			.clickOnSearchResult(ReadTestData.TVOD_CONTENT)
			.clickPlayTrailerButton()
			.verify_Playback();		

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 					
	"verify_TVODCancelWhileRedeemingTicket" })
	public void verify_TVODCancelWhileRedeemingTicket() {

		ReadTestData.fnAddTestRailScriptID(29584);
		try {

			searchPage.verifyContentSearchFunctionality(ReadTestData.TVOD_CONTENT);
			contentPage.clickRedeemTicket().clickCancelOnTVODPopup();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,			
	"verify_TVODWatchLaterWhileRedeemingTicket" })
	public void verify_TVODWatchLaterWhileRedeemingTicket() {

		ReadTestData.fnAddTestRailScriptID(29585);
		try {

			searchPage.verifyContentSearchFunctionality(ReadTestData.TVOD_CONTENT);
			contentPage.clickRedeemTicket().clickConfirmOnTVODPopup().clickWatchLaterOnTicketRedeemedPopup();


		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,			
	"verify_ClickRentForButton" })
	public void verify_ClickRentForButton() {

		ReadTestData.fnAddTestRailScriptID(29602);
		try {

			searchPage.verifyContentSearchFunctionality(ReadTestData.TVOD_CONTENT);
			contentPage.verify_PlayButtonForTVOD_WithoutBTVOD().clickRentForButton();


		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
	@Test(priority = 1, enabled = true, groups = { 
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_SeasonsDropdown_TVShows" })
	public void verify_SeasonsDropdown_TVShows() {

		ReadTestData.fnAddTestRailScriptID(29603);
		try {

			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);

			contentPage.verify_SeasonDropdown()
			.click_SeasonDropdown().click_SeasonFromTheList()
			.verify_EpisodeList();


		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

}
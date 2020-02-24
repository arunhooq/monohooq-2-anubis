package ios.tests;

import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;
import ios.utils.IOSConstants;

public class PlayerPageTests extends TestConfiguration {
	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */

	@Test(priority = 1, groups = { GlobalConstant.GROUP_SANITY_ACTIVE, "verifyMoviesPlayback" })
	public void verifyMoviesPlayback() {
		try {
			ReadTestData.fnAddTestRailScriptID(22494);

			ReadTestData.SVOD_CONTENT = TestUtilities.getRandomItem(DiscoverFeedController.getSVODNonR21MovieList());
			searchPage.validateSearch(ReadTestData.SVOD_CONTENT).clickWatchNow().clickPause().verifyVideoTitleName()
					.verifyVideoTimeCounter().verifyRewind().verifyForward().verifySeekBar().clickSubtitle()
					.verifyAudioLabel().verifySubTitleLabel().clickDone().verifyQualitySelectedMovies().clickQuality()
					.verifyVideoQualityLabel().verifyQualityAuto().verifyQualityHigh().verifyQualityMedium()
					.verifyQualityLow().clickDone().clickPlay().waitforPlay(10000).verifyVideoTimeCounter()
					.navigateBack();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}

	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */
	@Test(priority = 2, groups = { GlobalConstant.GROUP_SANITY_ACTIVE, "verifyTVshowsPlayback" })
	public void verifyTVshowsPlayback() {
		try {
			ReadTestData.fnAddTestRailScriptID(22492);

			tvShowsPage.clickTvshowsTab().selectContentFromMovies().clickWatchNow().waitforPlay(10000).clickPause()
					.verifyVideoTitleName().verifyVideoTimeCounter().verifyRewind().verifyForward().verifySeekBar()
					.clickSubtitle().verifyAudioLabel().verifySubTitleLabel().clickDone().verifyQualitySelectedTVShows()
					.clickQuality().verifyVideoQualityLabel().verifyQualityAuto().verifyQualityHigh()
					.verifyQualityMedium().verifyQualityLow().clickDone().clickEpisode()
					// .verifySeasonDetails().verifyEpisodeDetails()
					.clickSeasonClose().clickPlay().waitforPlay(10000).verifyVideoTimeCounter().navigateBack();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getStackTrace());
		}
	}

	@Test(priority = 3, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE, GlobalConstant.GROUP_REGRESSION_LAPSED,
			GlobalConstant.GROUP_REGRESSION_Visitor, "verifyMoviesTrailer" })
	public void verifyMoviesTrailer() {
		try {
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22476,22477,22478"));

			searchPage.validateSearch(ReadTestData.TRAILER_MOVIES);

			contentDetailsPage.verifyMoviesTrailer().waitforPlay(10000).clickPause().verifyVideoTitleName()
					.verifyVideoTimeCounter().verifyRewind().verifyForward().verifySeekBar().clickPlay()
					.waitforPlay(10000).navigateBack();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getStackTrace());
		}
	}

	@Test(priority = 4, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE, GlobalConstant.GROUP_REGRESSION_LAPSED,
			GlobalConstant.GROUP_REGRESSION_Visitor, "verifyTVShowTrailer" })
	public void verifyTVShowTrailer() {
		try {
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22479,22480,22481"));
			System.out.println("TVShow trailer returned as : "+ ReadTestData.TRAILER_TVSHOWS);
			
			searchPage.validateSearch(ReadTestData.TRAILER_TVSHOWS);

			contentDetailsPage.verifyMoviesTrailer().waitforPlay(10000).clickPause().verifyVideoTitleName()
					.verifyVideoTimeCounter().verifyRewind().verifyForward().verifySeekBar().clickPlay()
					.waitforPlay(10000).navigateBack();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getStackTrace());
		}
	}

}

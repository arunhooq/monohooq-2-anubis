package ios.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;
import ios.pages.SearchPage;

public class TVShowsPageTests extends TestConfiguration{


	//TODO - Need to write Content Details for Lapsed users w.r.t API response .

	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-
	 */
	@Test(priority = 1, enabled = false ,groups = {	GlobalConstant.GROUP_REGRESSION_LAPSED,"verifyTvshowContentDetailsForLapsedUser" })

	public void verifyTvshowContentDetailsForLapsedUser()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22438);
			searchPage.validateSearch("Arrow")
			.validateLapsed2ndContent()
			.verifylapseduser();
			ReporterLog.softAssert.assertAll();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getMessage());
		}
	}


	@Test(priority = 2,description="Validating TVShows Seasons and Episodes contentDetailsPage w.r.t API response", 
			groups = {GlobalConstant.GROUP_SANITY_ACTIVE, "validateTVShow_Season_and_Episodes_ContainDetails"})
	public void validateTVShow_Season_and_Episodes_ContainDetails()  {
		try {

			ReadTestData.fnAddTestRailScriptID(22423);
			ReadTestData.TV_SERIES = TestUtilities.getRandomItem(DiscoverFeedController.getTVShowList());

			searchPage.validateSearch(ReadTestData.TV_SERIES)
			.verifyTVShowContentDetailsWithAPI()
			.verifySeasonsAndEpisodeDetails();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getLocalizedMessage());
		}
	}

	@Test(priority = 3,dataProvider = "seeAllLink",description="Validating TVShows See All button from TVShow ", 
			groups = {GlobalConstant.GROUP_REGRESSION_ACTIVE , GlobalConstant.GROUP_REGRESSION_LAPSED, "validateTVShow_See_All_Button"})
	public void validateTVShow_See_All_Button(String data) {
		try {

			ReadTestData.fnAddTestRailScriptID(22965);
			
			discoverPage.fnSelectDiscover(data);
			tvShowsPage.verifyTVShowSeeAllLinks();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getLocalizedMessage());
		}
	}
	
	 @DataProvider(name = "seeAllLink")
	    public Object[][] getData() {
	        return new Object[][] { { "RentPage" }, { "TVShows" },{ "MoviesPage" } };
	    }


	@Test(priority = 4,description="Validate TVShow auto navigation to next episodes", 
			groups = {GlobalConstant.GROUP_SANITY_ACTIVE ,"validate_autoplay_of_episodes"})
	public void validate_autoplay_of_episodes() {
		try {
			ReadTestData.fnAddTestRailScriptID(22945);

			//ReadTestData.TV_SERIES = TestUtilities.getRandomItem(DiscoverFeedController.getTVShowList());
			ReadTestData.TV_SERIES = "Drop Dead Diva";
			searchPage.validateSearch(ReadTestData.TV_SERIES)
			.clickWatchNow()
			.verifyNextEpisodeAutoPlay();		  

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getLocalizedMessage());
		}
	}

	@Test(priority = 5,description="validation of continue watching section for tvshow ", 
			groups = {GlobalConstant.GROUP_REGRESSION_ACTIVE ,"validate_continue_watching_section_for_tvshow"},dependsOnMethods = {"validate_autoplay_of_episodes"})
	public void validate_continue_watching_section_for_tvshow() {
		try {
			ReadTestData.fnAddTestRailScriptID(22415);
			discoverPage.verifyContinueWatchingSection();			

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getLocalizedMessage());
		}
	}

	@Test(priority = 6,enabled=false,description="validation of history section for tvshow ", 
			groups = {GlobalConstant.GROUP_REGRESSION_ACTIVE ,"validate_history_section_for_tvshow"},dependsOnMethods = {"validate_autoplay_of_episodes"})
	public void validate_history_section_for_tvshow() {
		try {
			ReadTestData.fnAddTestRailScriptID(22453);
			discoverPage.getMePage().getHistoryPage().validateHistoryPage();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getLocalizedMessage());
		}
	}


	@Test(priority = 7,enabled=false,description="Validation of Because you have watched for TVShow",dependsOnMethods = {"validate_autoplay_of_episodes"}, 
			groups = {GlobalConstant.GROUP_REGRESSION_ACTIVE ,"validate_because_you_have_watched_section"})
	public void validate_because_you_have_watched_section() {
		try {
			ReadTestData.fnAddTestRailScriptID(22416);
			//TODO - ----

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getLocalizedMessage());
		}
	}

}

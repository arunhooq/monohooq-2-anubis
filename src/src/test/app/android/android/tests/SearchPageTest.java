package android.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;


public class SearchPageTest extends TestConfiguration {

	/***
	 * Test Script Name :- HOOQ_AndroidTab_Search_Premium Developed By :- Jagadish
	 * Date :- 17-June-2019 Test Description :- Verification of Search functionality
	 * in Android Tablet Test Rail ID :-
	 */
	@Test(priority = 1, enabled=true, groups = {GlobalConstant.GROUP_SANITY_ACTIVE, "verify_Search_Premium"})
	public void verify_Search_Premium(){
		
		ReadTestData.fnAddTestRailScriptID(29594);
		try {
			ReadTestData.TVOD_CONTENT = TestUtilities.getRandomItem(DiscoverFeedController.getTVODNonR21MovieList());
			discoverPage.clickSearch().typeSearch(ReadTestData.TVOD_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.TVOD_CONTENT);
		
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	/***
	 * Test Script Name :- HOOQ_AndroidTab_Search_R21Premium Developed By :-
	 * Jagadish Date :- 17-June-2019 Test Description :- Verification of Search
	 * functionality in Android Tablet Test Rail ID :-
	 */
	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			"verify_Search_R21Premium" })
	public void verify_Search_R21Premium(){

		ReadTestData.fnAddTestRailScriptID(29595);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_PREMIUM).verifySearchResults().clickOnSearchResult(ReadTestData.R21_PREMIUM);
			
		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	/***
	 * Test Script Name :- HOOQ_AndroidTab_Search_TVShows Developed By :- Jagadish
	 * Date :- 17-June-2019 Test Description :- Verification of Search functionality
	 * in Android Tablet Test Rail ID :-
	 */
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			"verify_Search_TVShows"})
	public void verify_Search_TVShows(){
		ReadTestData.fnAddTestRailScriptID(22700);
		try {
			ReadTestData.TV_SERIES = TestUtilities.getRandomItem(DiscoverFeedController.getTVShowList());
			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	/***
	 * Test Script Name :- HOOQ_AndroidTab_Search_Movies Developed By :- Jagadish
	 * Date :- 17-June-2019 Test Description :- Verification of Search functionality
	 * in Android Tablet Test Rail ID :-
	 */
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
			"verify_Search_Movies" })
	public void verify_Search_Movies(){

		ReadTestData.fnAddTestRailScriptID(22697);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.FREE_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.FREE_CONTENT);
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	/***
	 * Test Script Name :- HOOQ_AndroidTab_Search_R21Movies Developed By :- Jagadish
	 * Date :- 17-June-2019 Test Description :- Verification of Search functionality
	 * in Android Tablet Test Rail ID :-
	 */
	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			"verify_Search_R21Movies" })
	public void verify_Search_R21Movies(){

		ReadTestData.fnAddTestRailScriptID(22698);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_MOVIES).verifySearchResults().clickOnSearchResult(ReadTestData.R21_MOVIES);
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	/***
	 * Test Script Name :- HOOQ_AndroidTab_Search_R21TVShows Developed By :-
	 * Jagadish Date :- 17-June-2019 Test Description :- Verification of Search
	 * functionality in Android Tablet Test Rail ID :-
	 */
	@Test(enabled=false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			"verify_Search_R21TVShows" })
	public void verify_Search_R21TVShows(){

		ReadTestData.fnAddTestRailScriptID(29596);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.R21_TVSHOWS).verifySearchResults().clickOnSearchResult(ReadTestData.R21_TVSHOWS);
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
	
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_Visitor, "verify_Search_FTAChannel"})
	public void verify_Search_FTAChannel(){

		ReadTestData.fnAddTestRailScriptID(29580);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {			
				discoverPage.clickSearch().typeSearch(ReadTestData.LIVETV_CHANNEL).verifyTVChannelSearchResults().clickOnTVChannelSearchResult().verifyPlaybackChannelNameFromSearch(ReadTestData.LIVETV_CHANNEL);

			} catch (Exception e) {
				ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for Country : "+ConfigDetails.country);
	}
	
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_LAPSED, "verify_Search_UCastChannel"})
	public void verify_Search_UCastChannel(){

		ReadTestData.fnAddTestRailScriptID(29581);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {			
				discoverPage.clickSearch().typeSearch(ReadTestData.UCAST_CHANNEL).verifyTVChannelSearchResults().clickOnTVChannelSearchResult().verifyPlaybackChannelNameFromSearch(ReadTestData.UCAST_CHANNEL);

			} catch (Exception e) {
				ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable Country : "+ConfigDetails.country);
	}
	
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, "verify_Search_PayTVChannel"})
	public void verify_Search_PayTVChannel(){

		ReadTestData.fnAddTestRailScriptID(29582);
		if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_ID))
		{
			try {			
				discoverPage.clickSearch().typeSearch(ReadTestData.PAYTV_CHANNEL).verifyTVChannelSearchResults().clickOnTVChannelSearchResult().verifyPlaybackChannelNameFromSearch(ReadTestData.PAYTV_CHANNEL);

			} catch (Exception e) {
				ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for regions other than : "+ConfigDetails.country);
	}

	@Test(priority = 1, enabled = true, groups = {
			GlobalConstant.GROUP_SANITY_Visitor, 
			"verify_Search_NoResults" })
	public void verify_Search_NoResults(){

		ReadTestData.fnAddTestRailScriptID(29598);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.NO_SEARCH_RESULT).verifyNoSearchResults();
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
			"verify_RecentSearches" })
	public void verify_RecentSearches(){

		ReadTestData.fnAddTestRailScriptID(22702);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.FREE_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.FREE_CONTENT).clickCloseButton().ClickBack();
			
			discoverPage.clickSearch().verifyRecentSearchesLabel().verifyContentInRecentSearches(ReadTestData.FREE_CONTENT);
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
	@Test(priority = 1, enabled=true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
			"verify_ClearRecentSearches" })
	public void verify_ClearRecentSearches(){

		ReadTestData.fnAddTestRailScriptID(29597);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.FREE_CONTENT).verifySearchResults().clickOnSearchResult(ReadTestData.FREE_CONTENT).clickCloseButton().ClickBack();
			
			discoverPage.clickSearch().verifyRecentSearchesLabel().verifySizeOfRecentSearchResultsBeforeClear().clearRecentSearches().verifySizeOfRecentSearchResultsAfterClear();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

}

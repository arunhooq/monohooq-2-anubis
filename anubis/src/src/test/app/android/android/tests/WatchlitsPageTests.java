package android.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;


public class WatchlitsPageTests extends TestConfiguration {	
	
	
	@Test(enabled=true, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_AddingToWatchlist_TVShows" })
	public void verify_AddingToWatchlist_TVShows() {

		ReadTestData.fnAddTestRailScriptID(29599);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);
			contentPage.verify_WatchList().clickWatchlist().verify_Watchlist_AfterClick();
			if(!ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				contentPage.clickCloseButton().ClickBack().clickMeProfile().getWatchlistPage().verifyContentDisplayedInWatchlist(ReadTestData.TV_SERIES);			
			}

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	@Test(enabled=true, priority = 2, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
	"verify_RemovingFromWatchlist_TVShows" })
	public void verify_RemovingFromWatchlist_TVShows() {

		ReadTestData.fnAddTestRailScriptID(29600);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);
			contentPage.verify_WatchList().clickWatchlist();
			if(!ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				contentPage.clickCloseButton().ClickBack().clickMeProfile().getWatchlistPage().verifyContentNotDisplayedInWatchlist(ReadTestData.TV_SERIES);			
			}

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	@Test(enabled=true, priority = 3, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
	"verify_EditAndRemoveFromWatchlist_TVShows" })
	public void verify_EditAndRemoveFromWatchlist_TVShows() {

		ReadTestData.fnAddTestRailScriptID(29601);
		try {
			discoverPage.clickSearch().typeSearch(ReadTestData.TV_SERIES).verifySearchResults().clickOnSearchResult(ReadTestData.TV_SERIES);
			contentPage.verify_WatchList().clickWatchlist().verify_Watchlist_AfterClick();
			if(!ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				contentPage.clickCloseButton().ClickBack().clickMeProfile().getWatchlistPage().verifyContentDisplayedInWatchlist(ReadTestData.TV_SERIES);			
			}
			contentPage.clickClose().clickEditWatchlist().clickOnAContentInWatchlist().clickRemoveFromWatchlist().verifyContentNotDisplayedInWatchlist(ReadTestData.TV_SERIES);
						

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}

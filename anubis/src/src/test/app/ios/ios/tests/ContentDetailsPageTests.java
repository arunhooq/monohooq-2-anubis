package ios.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;
import ios.utils.IOSConstants;

public class ContentDetailsPageTests extends TestConfiguration {

	@Test(priority = 1, enabled = true, groups = { GlobalConstant.GROUP_SANITY_ACTIVE,
												   GlobalConstant.GROUP_SANITY_LAPSED, 
												   GlobalConstant.GROUP_SANITY_Visitor, "verifySVODMovieContentDetails" })

	public void verifySVODMovieContentDetails() {
		try {
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22426,22433,22440"));
			
			moviesPage.clickMoviesTab().selectContentFromMovies();
			contentDetailsPage.verifyMovieContentDetailsElementVisibility().verifyMovieDetailsWithAPI();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}

	@Test(priority = 2, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE,
			   					   GlobalConstant.GROUP_REGRESSION_LAPSED, 
			   					   GlobalConstant.GROUP_REGRESSION_Visitor, "verifyTVODMovieContentDetails" })

	public void verifyTVODMovieContentDetails() {

		try {
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22429,22436,22443"));
			ReadTestData.TVOD_CONTENT = TestUtilities.getRandomItem(DiscoverFeedController.getTVODNonR21MovieList());
			searchPage.validateSearch(ReadTestData.TVOD_CONTENT).verifyTVODMovieContentDetailsElementVisibility()
					.verifyMovieDetailsWithAPI();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}



	@Test(priority = 4, enabled = false, groups = { GlobalConstant.GROUP_SANITY_ACTIVE,
													GlobalConstant.GROUP_SANITY_LAPSED, "verifyTVShowContentDetailsForActiveUser" })

	public void verifyTVShowContentDetailsForActiveUser() {
		try {
			ReadTestData.fnAddTestRailScriptID(20399);
			
			searchPage.validateSearch(ReadTestData.TV_SERIES).verifyTVShowElementsVisibility();
			// .verifySeasonSelection()
			// .verifyEpisodeDetails();
			// .verifySimilartitles();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}

	@Test(priority = 5, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_Visitor,"verifySignUpToWatchForMovie" })

	public void verifySignUpToWatchForMovie() {
		try {
			
			ReadTestData.fnAddTestRailScriptID(22886);
			searchPage.validateSearch(ReadTestData.SVOD_CONTENT);

			contentDetailsPage.verifySignUpButton().isSignUpPageDisplayed();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}

	@Test(priority = 6, enabled = true, groups = { GlobalConstant.GROUP_SANITY_Visitor,"verifySignUpToWatchForRentalMovies" })

	public void verifySignUpToWatchForRentalMovies() {
		
		try {
			loginPage.logout();
			ReadTestData.fnAddTestRailScriptID(22887);
			
			ReadTestData.PREMIUM_CONTENT = TestUtilities.getRandomItem(DiscoverFeedController.getTVODNonR21MovieList());
			searchPage.validateSearch(ReadTestData.PREMIUM_CONTENT);

			contentDetailsPage.verifySignUpButtonForRentalMovie().isSignUpPageDisplayed();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}

	@Test(priority = 7, enabled = true, groups = { GlobalConstant.GROUP_SANITY_LAPSED,"verifySubscribeToWatchMovies" })

	public void verifySubscribeToWatchMovies() {
		try {

			ReadTestData.fnAddTestRailScriptID(22885);

			searchPage.validateSearch(ReadTestData.SVOD_CONTENT);

			contentDetailsPage.verifySignUpButton();

			//subscriptionPage.verifySubscriptionPage();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}

	@Test(priority = 8, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE,"verifyPaymentNavigationForRentalMovies" })

	public void verifyPaymentNavigationForRentalMovies() {
		try {
			ReadTestData.fnAddTestRailScriptID(22888);
			
			ReadTestData.PREMIUM_CONTENT = TestUtilities.getRandomItem(DiscoverFeedController.getTVODNonR21MovieList());
			searchPage.validateSearch(ReadTestData.PREMIUM_CONTENT);

			contentDetailsPage.verifySignUpButtonForRentalMovieActiveUser();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "
							+ e.getStackTrace());
		}
	}
	
	@Test(priority = 9, dataProvider = "similarTitles",enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE,GlobalConstant.GROUP_REGRESSION_LAPSED,GlobalConstant.GROUP_REGRESSION_Visitor,"verifySimilarContents_Movie_TvShow_RentMovie" })

	public void verifySimilarContents_Movie_TvShow_RentMovie(String contentType) {
		try {
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22427,22434,22441"));
			String content ;
			if(contentType.toLowerCase().startsWith("tvshow")) {				
				content = TestUtilities.getRandomItem(DiscoverFeedController.getTVShowList());
			}else if(contentType.toLowerCase().startsWith("movie")) {
				content = TestUtilities.getRandomItem(DiscoverFeedController.getSVODNonR21MovieList(false));
			}else {
				content = TestUtilities.getRandomItem(DiscoverFeedController.getTVODNonR21MovieList());	
			}
			
			searchPage.validateSearch(content);

			//Validate Similar Titles 
			contentDetailsPage.verifySimilarTitles(contentType,content);

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "+ e.getStackTrace());
		}
	}

	@DataProvider(name="similarTitles")
    public Object[][] getDataForSimilarTitles(){
    return new Object[][] 
    	{
            { "TVShow" },
            { "Movie" },
            { "RentMovie"}
        };

    }
	

}

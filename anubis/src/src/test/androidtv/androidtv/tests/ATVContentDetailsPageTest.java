package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVContentDetailsPageTest extends TestConfiguration {
	
	@Test(priority = 5, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "contentDetails"
    })
	public void contentDetails() {
		ReadTestData.fnAddTestRailScriptID(22196);
		try {
			atvDiscoverPage.chooseSVODContent().verifyContentDetails();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 7, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
			GlobalConstant.GROUP_REGRESSION_LAPSED,
			"verifyTrailer"
	})
	public void verifyTrailer() {
		ReadTestData.fnAddTestRailScriptID(22954);
		try {
			atvContentDetailsPage.verifyTrailer();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 8, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
			GlobalConstant.GROUP_REGRESSION_LAPSED,
			"addToFavorite"
	})
	public void addToFavorite() {
		ReadTestData.fnAddTestRailScriptID(22955);
		try {
			atvContentDetailsPage.addToFavorite();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 9, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
			GlobalConstant.GROUP_REGRESSION_LAPSED,
			"addToWatchlist"
	})
	public void addToWatchlist() {
		ReadTestData.fnAddTestRailScriptID(22957);
		try {
			atvContentDetailsPage.addToWatchlist();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 10, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
			GlobalConstant.GROUP_REGRESSION_LAPSED,
			"verifySimilarTitles"
	})
	public void verifySimilarTitles() {
		ReadTestData.fnAddTestRailScriptID(22970);
		try {
			atvContentDetailsPage.verifySimilarTitles();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
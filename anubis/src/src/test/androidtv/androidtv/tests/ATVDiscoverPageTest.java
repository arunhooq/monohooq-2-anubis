package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVDiscoverPageTest extends TestConfiguration {
	
	@Test(priority = 1, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifySpotlight"
    })
	public void verifySpotlight() {
		ReadTestData.fnAddTestRailScriptID(22210);
		try {
			atvDiscoverPage.verifySpotlight();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 2, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifySpotlightTitle"
    })
	public void verifySpotlightTitle() {
		ReadTestData.fnAddTestRailScriptID(22851);
		try {
			atvDiscoverPage.verifySpotlightTitle();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 3, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyQuicklinks"
    })
	public void verifyQuicklinks() {
		ReadTestData.fnAddTestRailScriptID(22211);
		try {
			atvDiscoverPage.verifyQuicklinks();;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 4, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyContinueWatching"
    })
	public void verifyContinueWatching() {
		ReadTestData.fnAddTestRailScriptID(22236);
		try {
			atvDiscoverPage.verifyContinueWatching();;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVSearchPageTest extends TestConfiguration {
	
	@Test(priority = 12, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "searchSVOD"
    })
	public void searchSVOD() {
		ReadTestData.fnAddTestRailScriptID(22212);
		try {
			atvSearchPage.searchSVOD();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 13, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "searchTVOD"
    })
	public void searchTVOD() {
		ReadTestData.fnAddTestRailScriptID(22212);
		try {
			atvSearchPage.searchTVOD();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 14, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "searchNoResult"
    })
	public void searchNoResult() {
		ReadTestData.fnAddTestRailScriptID(22213);
		try {
			atvSearchPage.searchNoResult();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVTVODPageTest extends TestConfiguration {
	
	@Test(priority = 15, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyTVODCollection"
    })
	public void verifyTVODCollection() {
		ReadTestData.fnAddTestRailScriptID(22238);
		try {
			atvTVODPage.goToTVODPage();
			atvTVODPage.verifyTVODCollection();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 16, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyRedeemTVOD"
    })
	public void verifyRedeemTVOD() {
		ReadTestData.fnAddTestRailScriptID(22204);
		try {
			atvTVODPage.chooseTVODContent();
			atvTVODPage.verifyRedeemTVOD();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
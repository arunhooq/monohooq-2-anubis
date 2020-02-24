package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVPlayerPageTest extends TestConfiguration {
	
	@Test(priority = 6, dependsOnGroups = "contentDetails", groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            "playContent"
    })
	public void playContent() {
		ReadTestData.fnAddTestRailScriptID(22194);
		try {
			atvPlayerPage.playContent();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 6, dependsOnGroups = "contentDetails", groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_LAPSED,
            "playContent, "
    })
	public void playContentLapsed() {
		ReadTestData.fnAddTestRailScriptID(22206);
		try {
			atvPlayerPage.playContentLapsed();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
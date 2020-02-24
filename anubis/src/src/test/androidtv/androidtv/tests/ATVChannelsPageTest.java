package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVChannelsPageTest extends TestConfiguration {
	
	@Test(priority = 11, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_REGRESSION_ACTIVE,
            "channels"
    })
	public void channelsPlayback() {
		ReadTestData.fnAddTestRailScriptID(22191);
		try {
			atvChannelsPage.goToChannelsPage();
			atvChannelsPage.chooseChannel();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 11, groups = {
			GlobalConstant.ANDROIDTVV1,
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_REGRESSION_LAPSED,
            "channels"
    })
	public void channelsPlaybackLapsed() {
		ReadTestData.fnAddTestRailScriptID(22237);
		try {
			atvChannelsPage.goToChannelsPage();
			atvChannelsPage.chooseChannelLapsed();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
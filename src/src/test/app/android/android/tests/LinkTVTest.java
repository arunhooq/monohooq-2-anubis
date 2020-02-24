package android.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class LinkTVTest extends TestConfiguration {

	@Test(enabled = true, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			"verify_LinkTVFailureMessage" })
	public void verify_LinkTVFailureMessage() {

		ReadTestData.fnAddTestRailScriptID(22672);
		try {			
				discoverPage.
				clickMeProfile().
				getLinkTVPage().
				getLinkTVTitle().
				clickLinkTV().
				clickEnterTVCode().
				typeTVCode("123456").
				clickLinkButton().
				verifyInvalidTVCode();
						
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(enabled = false, priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			"verify_LinkTVSuccessMessage" })
	public void verify_LinkTVSuccessMessage() {

		ReadTestData.fnAddTestRailScriptID(22671);
		try {			
				discoverPage.
				clickMeProfile().
				getLinkTVPage().
				getLinkTVTitle().
				clickLinkTV().
				clickEnterTVCode().
				typeTVCode("").
				clickLinkButton().
				verifyValidTVCode();
						
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}

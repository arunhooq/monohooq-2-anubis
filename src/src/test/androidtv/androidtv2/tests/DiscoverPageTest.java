package androidtv2.tests;

import org.testng.annotations.Test;

import com.automation.utilities.GlobalConstant;

import androidtv.utils.AndroidTVConstants;

public class DiscoverPageTest extends TestConfiguration {

	@Test(priority = 1, groups = {
			GlobalConstant.ANDROIDTVV2,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            "verifySpotlightContent" })
	public void verifySpotlightContent() {
		discoverPage.verifySpotlightContent();
	}

}

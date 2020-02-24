package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.utilities.GlobalConstant;

public class DiscoverPageTest extends TestConfiguration {

	@Test(priority = 1, groups = {
            GlobalConstant.GROUP_SANITY_Visitor,
            "verifySpotlightContent" })
	public void verifySpotlightContent() {
		discoverPage.verifySpotlightContent();
	}

}
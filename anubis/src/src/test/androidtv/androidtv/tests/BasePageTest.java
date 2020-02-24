package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.utilities.GlobalConstant;

public class BasePageTest extends TestConfiguration {

	@Test(priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_Visitor,
            "verifyDiscoverPage" })
	public void verifyDiscoverPage() {
		basePage.clickDiscoverMenu().verifyDiscoverPage();
	}

}
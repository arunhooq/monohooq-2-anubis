package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class SearchPageTests extends TestConfiguration{

	@Test(groups = { "Sanity_Visitor", "Verify_No_Search_Results_Page" })
	public void Verify_No_Search_Results_Page() {

		ReadTestData.fnAddTestRailScriptID(22374);
		try {
			basePage.clickSearch()
			.typeSearch("sdfc")
			.verifySearchResults("sdfc");
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}

package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import web.pages.LinkTVPage;

public class LinkTVPageTests extends TestConfiguration {
	
	@Test(groups = { "Sanity_Lapsed", "Verify_LinkTV_SuccessMessage" })
	public void Verify_LinkTV_SuccessMessage() {

		ReadTestData.fnAddTestRailScriptID(22382);
		try {
			basePage.getLinkTVPage().enterTVCode(LinkTVPage.getValidTVCode()).clickLinkNow().verifySuccessMessage();
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(groups = { "Sanity_Lapsed", "Verify_LinkTV_FailureMessage" })
	public void Verify_LinkTV_FailureMessage() {

		ReadTestData.fnAddTestRailScriptID(22383);
		try {
			
			basePage.getLinkTVPage().enterTVCode("123456").clickLinkNow().verifyFailureMessage();
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}

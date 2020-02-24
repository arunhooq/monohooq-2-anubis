package ios.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class LinkTVPageTests extends TestConfiguration {
	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */
	@Test(priority = 1,enabled=false, groups = {GlobalConstant.GROUP_SANITY_ACTIVE, "verifyLinkTVPage" })
	
	public void verifyLinkTVPage()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22466);
			discoverPage.getMePage().getLinkTVPage()
			.verifLinkTVLandingpage().clickLinkTVButtonInLandingPage()
			.verifLinkTVSvanQRCode().gotoEnterTVCode()
			.verifLinkTVEnterTVCode();
			
			ReporterLog.softAssert.assertAll();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
}

package ios.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class MePageTests extends TestConfiguration {
	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */

	@Test(priority = 1, groups = {GlobalConstant.GROUP_SANITY_ACTIVE, "verifyMePage" })
	public void verifyMePage()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22880);
			discoverPage.getMePage().verifyMePage()
			.getLinkTVPage().navigateBack().getMyRentalsPage().navigateBack().getWatchListPage().navigateBack()
			.getHistoryPage().navigateBack().getSettingsPage().navigateBack().getSubscriptionPage().navigateBack()
			.getTransactionHistoryPage().navigateBack().getSupportPage().navigateBack().getLinkTVPage().navigateBack();
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace());
		}
	}
	
}

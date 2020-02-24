package ios.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class TransactionHistoryPageTests extends TestConfiguration {
	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_Transaction_History Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */

	@Test(priority = 1, groups = {GlobalConstant.GROUP_REGRESSION_ACTIVE,"verifyTransactionHistory" })
	public void verifyTransactionHistory()  {
		
		try {
			ReadTestData.fnAddTestRailScriptID(22452);
			discoverPage.getMePage().
			getTransactionHistoryPage().fnVerifyTransactionHistory();
			ReporterLog.softAssert.assertAll();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
}

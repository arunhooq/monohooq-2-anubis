package ios.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class RentPageTest extends TestConfiguration{
	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */
	@Test(priority = 1,enabled=false ,groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE, 
			   					   GlobalConstant.GROUP_REGRESSION_LAPSED,
			   					   GlobalConstant.GROUP_REGRESSION_Visitor,"verifyTVODLapsedUser" })
	public void verifyTVODLapsedUser()  {
		try {
			//ReadTestData.fnAddTestRailScriptID(22494);
			searchPage.validateSearch("The Nun").validateLapsedTVODRent();
			ReporterLog.softAssert.assertAll();

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
}

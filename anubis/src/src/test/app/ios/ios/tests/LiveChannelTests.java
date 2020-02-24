package ios.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import ios.utils.IOSConstants;

public class LiveChannelTests extends TestConfiguration{
	
	/***
	 * @author mdafsarali
	 * @since 7th Aug 2019
	 * @description Live Channel Test for Visitor User
	 * @TestName Validate LiveChannel For Visitors
	 */
	
	@Test(priority = 1, enabled=false ,groups = {GlobalConstant.GROUP_SANITY_Visitor, "verifyLiveChannelPage" })
	public void verifyLiveChannelPage()  {
		
		try {
			ReadTestData.fnAddTestRailScriptID(22447);
			loginPage.logout();
			
			liveChannelPage.			
			verifyNavigationToLiveChannel();
			//.verifyLiveChannelPage();
			//.verifyChannelsSignUpForVisitors()
			//.verifyChannelsPlayBackForVisitors();
						
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "+e.getStackTrace());
		}
	}
	
	@Test(priority = 2, enabled=false ,groups = {GlobalConstant.GROUP_SANITY_ACTIVE,"verifyLiveChannelPageActiveUser" })
	public void verifyLiveChannelPageActiveUser()  {
		try {
			ReadTestData.fnAddTestRailScriptID(22446);
			liveChannelPage.			
			verifyNavigationToLiveChannel()
			//.verifyLiveChannelPage()
			.verifyChannelsPlayBackForActiveUser();
						
			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is "+e.getStackTrace());
		}
	}

}

package web.tests;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class ChannelsPageTests extends TestConfiguration {
	
	@Test(groups = { "Sanity_Visitor", "Verify_LiveTV_Channels" }, enabled = true)
	public void Verify_LiveTV_Channels() {
		
		ReadTestData.fnAddTestRailScriptID(22971);
		try {
			
			basePage.getChannelsPage().verifyChannels();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
/*	@Test(groups = { "Sanity_Visitor", "Verify_EPG" }, enabled = false)
	public void Verify_EPG() {

		ReadTestData.fnAddTestRailScriptID(22971);
		try {
			
			basePage.getChannelsPage().verifyEPG();

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}*/

}

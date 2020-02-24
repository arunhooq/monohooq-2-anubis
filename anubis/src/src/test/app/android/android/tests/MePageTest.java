package android.tests;

import org.testng.annotations.Test;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class MePageTest extends TestConfiguration {

	@Test(priority = 1, enabled = true, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
	"verify_MeSection" })
	public void verify_MeSection() {

		ReadTestData.fnAddTestRailScriptID(29583);
		try {
			String userName = null;
			if(ConfigDetails.userType.equalsIgnoreCase("Active"))
				userName=ReadTestData.ACTIVE_USER_ID;
			else
				userName=ReadTestData.LAPSED_USER_ID;

			if(!ConfigDetails.userType.equalsIgnoreCase("visitor"))
				discoverPage.clickMeProfile()
				.getSubscriptionLabel().getSubscriptionDays()
				.getTicketLabel().getNoOfBTVODs()				
				.verify_MeProfileLabels()
				.verify_MeProfileImages()
				.verify_LoggedInUser(userName);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 1, enabled = true, groups = {
            GlobalConstant.GROUP_SANITY_ACTIVE, 
            GlobalConstant.GROUP_SANITY_LAPSED,
    "verify_MeSection_Settings_MobileDataUsage" })
    public void verify_MeSection_Settings_MobileDataUsage() {
        ReadTestData.fnAddTestRailScriptID(1234);
        try {
            String userName = null;
            if(ConfigDetails.userType.equalsIgnoreCase("Active"))
                userName=ReadTestData.ACTIVE_USER_ID;
            else
                userName=ReadTestData.LAPSED_USER_ID;
            if(!ConfigDetails.userType.equalsIgnoreCase("visitor"))
            {
                discoverPage.clickMeProfile().getSettingsPage().verifyMobileDataUsage();
            }
                
        } catch (Exception e) {
            TestUtilities.logReportFailure(e);
        }
    }

}

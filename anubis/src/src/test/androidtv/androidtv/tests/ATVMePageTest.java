package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class ATVMePageTest extends TestConfiguration {
	
	@Test(priority = 17, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "signedInAccount"
    })
	public void signedInAccount() {
		ReadTestData.fnAddTestRailScriptID(22239);
		try {
			atvMePage.goToMePage();
			atvMePage.signedInAccountDetails();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 18, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "appVersion"
    })
	public void appVersion() {
		ReadTestData.fnAddTestRailScriptID(22567);
		try {
			atvMePage.appVersion();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 19, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyRentalInfo"
    })
	public void verifyRentalInfo() {
		ReadTestData.fnAddTestRailScriptID(22568);
		try {
			atvMePage.verifyMyRentalInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 20, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyWatchLaterInfo"
    })
	public void verifyWatchLaterInfo() {
		ReadTestData.fnAddTestRailScriptID(22569);
		try {
			atvMePage.verifyWatchLaterInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 21, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyFavouriteInfo"
    })
	public void verifyFavouriteInfo() {
		ReadTestData.fnAddTestRailScriptID(22570);
		try {
			atvMePage.verifyFavouriteInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 22, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyHistoryInfo"
    })
	public void verifyHistoryInfo() {
		ReadTestData.fnAddTestRailScriptID(22571);
		try {
			atvMePage.verifyHistoryInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 23, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifySettingsInfo"
    })
	public void verifySettingsInfo() {
		ReadTestData.fnAddTestRailScriptID(22604);
		try {
			atvMePage.verifySettingsInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 24, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifySubscriptionInfo"
    })
	public void verifySubscriptionInfo() {
		ReadTestData.fnAddTestRailScriptID(22605);
		try {
			atvMePage.verifySubscriptionInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 25, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifyTransactionInfo"
    })
	public void verifyTransactionInfo() {
		ReadTestData.fnAddTestRailScriptID(22606);
		try {
			atvMePage.verifyTransactionInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 26, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "verifySupportInfo"
    })
	public void verifySupportInfo() {
		ReadTestData.fnAddTestRailScriptID(22607);
		try {
			atvMePage.verifySupportInfo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 27, groups = {
			GlobalConstant.ANDROIDTVV1,
            GlobalConstant.GROUP_SANITY_ACTIVE,
            GlobalConstant.GROUP_SANITY_LAPSED,
            GlobalConstant.GROUP_REGRESSION_ACTIVE,
            GlobalConstant.GROUP_REGRESSION_LAPSED,
            "logout"
    })
	public void logout() {
		ReadTestData.fnAddTestRailScriptID(22240);
		try {
			atvMePage.logout();
			atvWelcomePage.verifyLogo();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

}
package androidtv.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import androidtv.pages.ATVDiscoverPage;
import androidtv.pages.ATVSigninPage;
import androidtv.utils.AndroidTVConstants;

public class ATVSigninPageTest extends TestConfiguration {
	
	/**@Test(priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
            "signInUsingLinkTV"
    })
	public void signInUsingLinkTV() {
		ReadTestData.fnAddTestRailScriptID(22189);
		try {
			atvWelcomePage.clickSigninButton().verifyLoginPage();
			ATVSigninPage.signinUsingLinkTV(GlobalConstant.USERTYPE_ACTIVE, GlobalConstant.COUNTRY_SG, AndroidTVConstants.PROD_ENV, AndroidTVConstants.PROD_ENV);
			ATVDiscoverPage.verifyToastMessage(GlobalConstant.USERTYPE_ACTIVE);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 1, groups = {
			GlobalConstant.GROUP_SANITY_LAPSED,
            "signInUsingLinkTVLapsed"
    })
	public void signInUsingLinkTVLapsed() {
		ReadTestData.fnAddTestRailScriptID(22189);
		try {
			atvWelcomePage.clickSigninButton().verifyLoginPage();
			ATVSigninPage.signinUsingLinkTV(GlobalConstant.USERTYPE_LAPSED, GlobalConstant.COUNTRY_SG, AndroidTVConstants.PROD_ENV, AndroidTVConstants.PROD_ENV);
			ATVDiscoverPage.verifyToastMessage(GlobalConstant.USERTYPE_LAPSED);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}**/

}
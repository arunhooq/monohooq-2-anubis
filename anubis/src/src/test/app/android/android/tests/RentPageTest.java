package android.tests;


import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;


public class RentPageTest extends TestConfiguration {

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,			
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_TVODPlaybackByPurchasingWithCC" })
	public void verify_TVODPlaybackByPurchasingWithCC(){

		ReadTestData.fnAddTestRailScriptID(22689);
		try {

			//searchPage.verifyContentSearchFunctionality("Big Buck Bunny");
			discoverPage.clickRentTab().clickOnATVODContent();
			contentPage.clickRentForButton();

			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE) || ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				contentPage.clickCreditCardPaymentMethod()
				.fillCardDetails()
				.clickMakePayment()
				.verifyTVODVictoryMessage()
				.verify_TVODValidity("1 month left")
				.clickPlayButton()
				.verify_Playback();

				//playerPage.closePlayer().verify_TVODValidity("2 days left");
			}
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,			
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_TVODPlaybackByPurchasingWithDC" })
	public void verify_TVODPlaybackByPurchasingWithDC(){

		ReadTestData.fnAddTestRailScriptID(29593);
		try {

			//searchPage.verifyContentSearchFunctionality("Big Buck Bunny");
			discoverPage.clickRentTab().clickOnATVODContent();
			contentPage.clickRentForButton();

			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE) || ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				contentPage.clickDebitCardPaymentMethod()
				.fillCardDetails()
				.clickMakePayment()
				.verifyTVODVictoryMessage()
				.verify_TVODValidity("1 month left")
				.clickPlayButton()
				.verify_Playback();

				//playerPage.closePlayer().verify_TVODValidity("2 days left");
			}
			
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}}

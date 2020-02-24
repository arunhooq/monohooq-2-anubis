package android.tests;

import org.testng.annotations.Test;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class NovaPlanSelectorTest extends TestConfiguration {

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			"verify_Subscription_CreditCard" })
	public void verify_Subscription_CreditCard() {

		ReadTestData.fnAddTestRailScriptID(29586);
		try {			
				discoverPage.
				clickMeProfile().
				getSubscriptionPage().
				clickSubscribeNow().
				selectAPlanWhichHasCCPayment().
				clickOnCreditCardPaymentMethod().
				fillCardDetails().
				clickMakePayment().
				verifyRecurringVictoryMessage();
						
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
			GlobalConstant.GROUP_SANITY_LAPSED,
			"verify_Subscription_DebitCard" })
	public void verify_Subscription_DebitCard() {

		ReadTestData.fnAddTestRailScriptID(29587);
		try {			
				discoverPage.
				clickMeProfile().
				getSubscriptionPage().
				clickSubscribeNow().
				selectAPlanWhichHasDCPayment().
				clickOnDebitCardPaymentMethod().
				fillCardDetails().
				clickMakePayment().
				verifyNonRecurringVictoryMessage();
						
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
}

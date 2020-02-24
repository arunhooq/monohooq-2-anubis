package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class ATVMePage extends ActionEngine {
	
	public static By meButton = By.id("tv.hooq.android:id/me");
	
	public static By signedInAccount = By.id("tv.hooq.android:id/email");
	public static By appVersion = By.id("tv.hooq.android:id/app_version");
	
	public static By rentSection = By.id("tv.hooq.android:id/rentals");
	public static By watchlaterSection = By.id("tv.hooq.android:id/watch_later");
	public static By favoriteSection = By.id("tv.hooq.android:id/favorite");
	public static By historySection = By.id("tv.hooq.android:id/history");
	
	public static By settingsSection = By.id("tv.hooq.android:id/settings");
	public static By subscriptionSection = By.id("tv.hooq.android:id/subscription");
	public static By transactionSection = By.id("tv.hooq.android:id/transaction_history");
	public static By supportSection = By.id("tv.hooq.android:id/support");
	
	public static By title = By.id("tv.hooq.android:id/title");
	public static By counter = By.id("tv.hooq.android:id/counter");
	
	public static By logoutButton = By.id("tv.hooq.android:id/logout");
	public static By logoutPopup = By.id("tv.hooq.android:id/custom_btn");
	
	
	public void goToMePage() {
		try {
			pressKeyRemote("back", 1);
			waitTillElementPresent_HardWait_Polling(meButton, GlobalConstant.WAIT_SHORT_10_SEC);
			click(meButton, "meButton");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void signedInAccountDetails() {
		try {
			getText(signedInAccount, "signedInAccount");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void appVersion() {
		try {
			getText(appVersion, "appVersion");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyMyRentalInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(rentSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(rentSection, "rentSection");
			getText(title, "title");
			getText(counter, "counter");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyWatchLaterInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(watchlaterSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(watchlaterSection, "watchlaterSection");
			getText(title, "title");
			getText(counter, "counter");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyFavouriteInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(favoriteSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(favoriteSection, "favoriteSection");
			getText(title, "title");
			getText(counter, "counter");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyHistoryInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(historySection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(historySection, "historySection");
			getText(title, "title");
			getText(counter, "counter");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifySettingsInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(settingsSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(settingsSection, "settingsSection");
			getText(title, "title");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifySubscriptionInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(subscriptionSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(subscriptionSection, "subscriptionSection");
			getText(title, "title");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyTransactionInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(transactionSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(transactionSection, "transactionSection");
			getText(title, "title");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifySupportInfo() {
		try {
			waitTillElementPresent_HardWait_Polling(supportSection, GlobalConstant.WAIT_SHORT_5_SEC);
			click(supportSection, "supportSection");
			getText(title, "title");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void logout() {
		try {
			waitTillElementPresent_HardWait_Polling(logoutButton, GlobalConstant.WAIT_SHORT_10_SEC);
			click(logoutButton, "logoutButton");
			waitTillElementPresent_HardWait_Polling(logoutPopup, GlobalConstant.WAIT_SHORT_10_SEC);
			click(logoutPopup, "logoutPopup");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}
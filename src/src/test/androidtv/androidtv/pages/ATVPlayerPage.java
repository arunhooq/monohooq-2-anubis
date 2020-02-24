package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class ATVPlayerPage extends ActionEngine {
	
	public static By message = By.id("tv.hooq.android:id/message");
	public static By playerControl = By.id("tv.hooq.android:id/player_control");
	
	public ATVContentDetailsPage playContent() {
		try {
			pressKeyRemote("center", 1);
			//will be commented until test suite running on non rooted device
			//waitTillElementPresent_HardWait_Polling(playerControl, GlobalConstant.WAIT_SHORT_10_SEC);
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ATVContentDetailsPage();
	}
	
	public ATVContentDetailsPage playContentLapsed() {
		try {
			pressKeyRemote("center", 1);
			getText(message, "messageInfo");
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ATVContentDetailsPage();
	}
	
}
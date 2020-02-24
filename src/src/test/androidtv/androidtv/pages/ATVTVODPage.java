package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class ATVTVODPage extends ActionEngine {
	
	public static By rentButton = By.id("tv.hooq.android:id/rent");
	public static By firstCollection = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView[1]");
	public static By redeemButton = By.id("tv.hooq.android:id/redeem");
	
	public void goToTVODPage() {
		try {
			pressKeyRemote("back", 1);
			waitTillElementPresent_HardWait_Polling(rentButton, GlobalConstant.WAIT_SHORT_10_SEC);
			click(rentButton, "rentButton");
			waitTillElementPresent_HardWait_Polling(firstCollection, GlobalConstant.WAIT_SHORT_10_SEC);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyTVODCollection() {
		try {
			isElementDisplayed(firstCollection);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void chooseTVODContent() {
		try {
			int randOne = randomNumber(4, 9);
			int randTwo = randomNumber(3, 6);
			pressKeyRemote("down", randOne);
			pressKeyRemote("right", randTwo);
			pressKeyRemote("center", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyRedeemTVOD() {
		try {
			isElementDisplayed(redeemButton);
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
}
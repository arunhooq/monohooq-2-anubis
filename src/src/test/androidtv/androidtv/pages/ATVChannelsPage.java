package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class ATVChannelsPage extends ActionEngine {
	
	public static By channelsButton = By.id("tv.hooq.android:id/channels");
	public static By lapsedInfo = By.id("tv.hooq.android:id/error_message");
	public static By lockIcon = By.id("tv.hooq.android:id/premium_lock");
	public static By fifthChannel = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[5]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ImageView");
	
	public void goToChannelsPage() {
		try {
			pressKeyRemote("back", 2);
			click(channelsButton, "channelsButton");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void chooseChannel() {
		try {
			Thread.sleep(1000);
			ReporterLog.pass("Channels page", "Playing first TV channel");
			pressKeyRemote("down", 2);
			int randOne = randomNumber(8, 10);
			int randTwo = randomNumber(1, 5);
			pressKeyRemote("right", randOne);
			pressKeyRemote("left", randTwo);
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void chooseChannelLapsed() {
		try {
			Thread.sleep(1000);
			pressKeyRemote("down", 2);
			int randOne = randomNumber(8, 10);
			int randTwo = randomNumber(1, 5);
			pressKeyRemote("right", randOne);
			pressKeyRemote("left", randTwo);
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
}
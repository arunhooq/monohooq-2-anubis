package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class PlayerSDKPlayerPage extends ActionEngine {
	
	public static By settingButton = By.id("tv.hooq.android:id/settings");
	public static By qualityButton = By.id("tv.hooq.android:id/variant");
	public static By audioButton = By.id("tv.hooq.android:id/audios");
	public static By subtitleButton = By.id("tv.hooq.android:id/subtitles");
	
	public static By playButton = By.id("tv.hooq.android:id/play_pause_btn");
	
	public void playContent() {
		try {
			click(playButton, "playButton");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void assertionSettingButton() {
		try {
			isElementDisplayed(settingButton);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}

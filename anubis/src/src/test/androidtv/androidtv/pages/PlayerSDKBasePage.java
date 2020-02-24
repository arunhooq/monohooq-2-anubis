package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class PlayerSDKBasePage extends ActionEngine {
	
	public static By title = By.xpath("//android.widget.TextView[@index='0' and @text='AndroidPlayerSDK']");
	
	public static By configButton = By.id("tv.hooq.android:id/file_picker_btn");
	public static By permissionButton = By.id("com.android.packageinstaller:id/permission_allow_button");
	
	public static By downloadDirectory = By.xpath("//android.widget.TextView[@index='0' and @text='Download']");
	public static By configFile = By.xpath("//android.widget.RelativeLayout[@index='1']");
	
	public static By playbackStartPosition = By.id("tv.hooq.android:id/init_playback_startpos");
	public static By drmContent = By.id("tv.hooq.android:id/radio_drm");
	public static By trailerContent = By.id("tv.hooq.android:id/radio_trailer");
	public static By livetvContent = By.id("tv.hooq.android:id/radio_livetv");
	
	public static By nativeDRMToggle = By.id("tv.hooq.android:id/prefer_native_drm");
	public static By saveButton = By.id("tv.hooq.android:id/save_btn");

	public void verifyTitle() {
		try {
			isElementDisplayed(title);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void configureFile() {
		try {
			click(configButton, "configButton");
			click(permissionButton, "permissionButton");
			click(downloadDirectory, "downloadDirectory");
			click(configFile, "configFile");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void chooseDRMContent() {
		try {
			click(drmContent, "drmContent");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void chooseTrailerContent() {
		try {
			click(trailerContent, "trailerContent");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void chooseLiveTVContent() {
		try {
			click(livetvContent, "livetvContent");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void saveConfiguration() {
		try {
			click(saveButton, "saveButton");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

}

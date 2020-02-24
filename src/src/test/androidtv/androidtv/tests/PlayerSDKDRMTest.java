package androidtv.tests;

import org.testng.annotations.Test;

import androidtv.utils.AndroidTVConstants;

public class PlayerSDKDRMTest extends TestConfiguration {
	
	@Test(priority = 1, groups = {
            AndroidTVConstants.GROUP_PLAYERSDK,
            "verifyTitle" })
	public void verifyTitle() {
		playerSDKBasePage.verifyTitle();
	}
	
	@Test(priority = 2, groups = {
            AndroidTVConstants.GROUP_PLAYERSDK,
            "configureDRMContent" })
	public void configureDRMContent() {
		playerSDKBasePage.configureFile();
		playerSDKBasePage.chooseDRMContent();
		playerSDKBasePage.saveConfiguration();
	}
	
	@Test(priority = 3, groups = {
            AndroidTVConstants.GROUP_PLAYERSDK,
            "playingContent" })
	public void playContent() {
		playerSDKPlayerPage.playContent();
		playerSDKPlayerPage.assertionSettingButton();
	}
	
}

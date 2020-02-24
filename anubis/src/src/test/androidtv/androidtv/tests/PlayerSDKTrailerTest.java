package androidtv.tests;

import org.testng.annotations.Test;

import androidtv.utils.AndroidTVConstants;

public class PlayerSDKTrailerTest extends TestConfiguration {
	
	@Test(priority = 1, groups = {
            AndroidTVConstants.GROUP_PLAYERSDK,
            "verifyTitle" })
	public void verifyTitle() {
		playerSDKBasePage.verifyTitle();
	}
	
	@Test(priority = 2, groups = {
            AndroidTVConstants.GROUP_PLAYERSDK,
            "configureTrailerContent" })
	public void configureTrailerContent() {
		playerSDKBasePage.configureFile();
		playerSDKBasePage.chooseTrailerContent();
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

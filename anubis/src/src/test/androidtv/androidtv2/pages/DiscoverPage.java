package androidtv2.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class DiscoverPage extends ActionEngine {

	public static By discoverActive = By.xpath("//android.widget.TextView[@text='Discover' and @focused='true']");
	public static By spotlight = By.id("tv.hooq.android:id/spotlight");
	public static By spotlightFocused = By.id("tv.hooq.android:id/detailContainer");

	public void verifyDiscoverPage() {
		try {
			isElementDisplayed(spotlight);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	public void verifySpotlightContent() {
		try {
			pressKeyRemote("down", 1);
			isElementDisplayed(spotlightFocused);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}
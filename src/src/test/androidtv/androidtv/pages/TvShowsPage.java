package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class TvShowsPage extends ActionEngine {

	public static By tvShowsMenu = By.id("tv.hooq.android:id/tvShowsMenu");

	public void verifyTvShowsPage() {
		try {
			isElementPresent(tvShowsMenu);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}
package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class BasePage extends ActionEngine {

	public static By logo = By.id("tv.hooq.android:id/hooqLogo");
	public static By discoverMenu = By.id("tv.hooq.android:id/discoverMenu");
	public static By moviesMenu = By.id("tv.hooq.android:id/moviesMenu");
	public static By tvShowsMenu = By.id("tv.hooq.android:id/tvShowsMenu");
	public static By rentMenu = By.id("tv.hooq.android:id/rentMenu");
	public static By liveTvMenu = By.id("tv.hooq.android:id/liveTvMenu");
	public static By searchMenu = By.id("tv.hooq.android:id/searchMenu");
	public static By meMenu = By.id("tv.hooq.android:id/meMenu");

	public DiscoverPage clickDiscoverMenu() {
		try {
			click(discoverMenu, "discoverMenu");
			ReporterLog.pass("clickDiscoverMenu", "Successfully clicked on Discover menu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}

	public TvShowsPage clickTvShowsPage() {
		try {
			click(tvShowsMenu, "tvShowsMenu");
			ReporterLog.pass("clickTvShowsPage", "Successfully clicked on TVShows menu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new TvShowsPage();
	}
}
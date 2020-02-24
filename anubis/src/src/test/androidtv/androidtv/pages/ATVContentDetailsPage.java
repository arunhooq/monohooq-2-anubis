package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class ATVContentDetailsPage extends ActionEngine {
	
	public static By title = By.id("tv.hooq.android:id/title");
	public static By movieInfo = By.id("tv.hooq.android:id/movie_info");
	public static By subtitle = By.id("tv.hooq.android:id/subtitle");
	public static By cast = By.id("tv.hooq.android:id/cast");
	public static By synopsis = By.id("tv.hooq.android:id/synopsis");
	public static By genres = By.id("tv.hooq.android:id/genres");
	public static By favorite = By.id("tv.hooq.android:id/favorite");
	public static By watchlist = By.id("tv.hooq.android:id/watchlist");
	public static By trailer = By.id("tv.hooq.android:id/trailer");
	public static By similarTitles = By.id("tv.hooq.android:id/bucket_title");
	
	public void verifyContentDetails() {
		try {
			getText(title, "titleName");
			getText(movieInfo, "movieInfo");
			getText(subtitle, "subtitleInfo");
			getText(cast, "castInfo");
			getText(synopsis, "synopsysInfo");
			getText(genres, "genresInfo");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void addToFavorite() {
		try {
			click(favorite, "favoriteButton");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void addToWatchlist() {
		try {
			click(watchlist, "watchlistButton");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyTrailer() {
		try {
			if (isElementVisible(trailer)) {
				ReporterLog.pass("Trailer", "there's Trailer on this title");
			} else {
				ReporterLog.pass("Trailer", "there's no Trailer on this title");
			}
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifySimilarTitles() {
		try {
			isElementDisplayed(similarTitles);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
}
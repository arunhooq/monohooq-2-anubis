package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class TVShowsPage extends ActionEngine {

	// Locators
	public static By btntvshows = By.id("TV SHOWS");
	public static By navBackBtn = By.name("navbar back btn");
	public static By tblMovies = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By txtTVShowTitle = By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText");
	public static By listSeeAllLink = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeButton");
	public static By listContents = By.xpath("//*[@XCElementType='XCUIElementTypeTable']/*/*[@XCElementType='XCUIElementTypeCollectionView' and (./preceding-sibling::* | ./following-sibling::*)[@XCElementType='XCUIElementTypeButton']]");

	// Movies/TVShow List page
	public static By listMovies =By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell");
	public static By txtMoviePageTitle =By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell");



	/***
	 * Function Name :- naviagtetoBackFromSettings Developed By :- Pankaj Kumar Date
	 * :- 4-July-2019
	 */
	public MoviesPage navigateBack() {
		try {
			click(navBackBtn, "Back From Me page");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MoviesPage();
	}

	public TVShowsPage clickTvshowsTab() {
		try {
			waitForElementClickable(btntvshows, "TVShows tab");
			click(btntvshows, "Click on TVShows Button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TVShowsPage();
	}

	public ContentDetailsPage selectContentFromMovies() {
		try {
			click(getElements(tblMovies).get(0),"First Movie From Movie Page");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}


	public TVShowsPage verifyTVShowPage() {
		try {
			//Validate Heading
			isElementDisplayed(txtTVShowTitle); //TVShow Page Title Displayed ?
			//List Of TVshows Present
			//waitTillElementPresent_HardWait_Polling(listContents,GlobalConstant.WAIT_SHORT_5_SEC);
			//isElementDisplayed(listContents);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TVShowsPage();
	}

	public TVShowsPage verifyTVShowSeeAllLinks() {
		try {
			waitTillElementPresent_HardWait_Polling(listSeeAllLink,GlobalConstant.WAIT_SHORT_5_SEC);
			isElementDisplayed(listSeeAllLink); //See All Buttons
			click(getElements(listSeeAllLink).get(0), "See All button"); // Click on the First See All button
			waitTillElementPresent_HardWait_Polling(txtMoviePageTitle, GlobalConstant.WAIT_SHORT_3_SEC);
			if(getElements(txtMoviePageTitle).size() > 0) {
				ReporterLog.pass("See All button Validation", "See All button working expected .");
			}			
			isElementDisplayed(navBackBtn,"Navigation back");
			click(navBackBtn, "Click Nav back button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TVShowsPage();
	}
}

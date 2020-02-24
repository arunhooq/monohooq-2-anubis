package ios.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class SearchPage extends ActionEngine {
	// Locators
	public static By navBackBtn = By.name("navbar back btn");
	public static By btnsearch = By.id("icon search");
	public static By searchTextField = By.xpath("//XCUIElementTypeNavigationBar[@name=\"HQSearchSuggestionStandaloneView\"]/XCUIElementTypeSearchField");
	public static By searchResultFirst = By.xpath("//XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[1]");
	public static By txtNoResultFound = By.xpath(".//XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By btnRefresh = By.id("Refresh");
	public static By btnCancel = By.name("Cancel");
	public static By btnClearText = By.id("Clear text");
	public static By labelSearchChannels = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeOther/XCUIElementTypeStaticText");

	public MoviesPage navigateBack() {
		try {
			click(navBackBtn, "Back From History page");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MoviesPage();
	}

	public SearchPage clicksearch() {
		try {
			waitTillElementPresent_HardWait_Polling(btnsearch, GlobalConstant.WAIT_SHORT_5_SEC ,"Search button");
			waitForVisibilityOfElement(btnsearch, "Search button ");
			click(btnsearch, "Search Button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SearchPage();
	}

	public SearchPage typesearch(String strText) {
		try {
			waitForVisibilityOfElement(searchTextField, "Search Input box");
			type(searchTextField, strText, "Type the Content : "+strText);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SearchPage();
	}

	public ContentDetailsPage validateSearch(String strEnterContentName) {
		try {
			LoginPage.fnHandleBrazePage();
			clicksearch();
			ReporterLog.info("Search content text", "Searching for content : " + strEnterContentName);
			typesearch(strEnterContentName);
			waitForVisibilityOfElement(searchResultFirst, "First Search result");
			click(searchResultFirst, "First Search Results");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public SearchPage verifySearchForEmptyResult() {

		try {
			Thread.sleep(2000);
			isElementDisplayed(btnRefresh);
			verifyTextContains(txtNoResultFound, "Sorry, we could not find what you were looking for. Try again?",
					"No Result found Texts");
			click(btnClearText, "Click on ClearText");
			Thread.sleep(3000);
			VerifyThatIsFalse(isElementPresentInDom(btnRefresh), "Refresh Button is not displayed");
			click(btnCancel, "Cancel button");

			// Handle flakiness of Cancel button
			int flag = 0;
			while (isNumOf_Elements_Greater_Than_Zero(btnCancel)) {
				clickNoWait(btnCancel, "Cancel Button");

				if (flag == 2) {
					break;
				}
				flag++;
			}
			// After Click on the Cancel button in the search box ,validate LOGIN or Me Icon
			// visible
			if (ConfigDetails.userType.equalsIgnoreCase("Visitor")) {

				waitTillElementPresent_HardWait_Polling(LoginPage.log_in, GlobalConstant.WAIT_SHORT_5_SEC);

				if(isElementPresentInDom(LoginPage.log_in) || isElementPresentInDom(DiscoverPage.meLabel)){
					ReporterLog.pass("Cancel Button validation", "Cancel button for Search working as expected");
				}
			} else {
				isElementDisplayed(DiscoverPage.meLabel);
				ReporterLog.pass("Cancel Button validation", "Cancel button for Search working as expected");
			}
			ReporterLog.pass("Empty Search Result Page Validation", "Empty Search result page validated");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new SearchPage();
	}
	
	public LiveChannelsPage validateSearchForChannels(String strEnterContentName) {
		try {
			clicksearch();
			typesearch(strEnterContentName);
			waitForVisibilityOfElement(searchResultFirst, "First Search result");
			verifyTextContains(labelSearchChannels, "CHANNELS", "Channels Label");
			click(searchResultFirst, "First Search Results");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LiveChannelsPage();
	}
}

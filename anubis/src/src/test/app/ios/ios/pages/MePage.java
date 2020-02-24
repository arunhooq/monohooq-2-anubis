package ios.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class MePage extends ActionEngine {

	// Lcators
	public static By meLabel = By.id("icon me");
	public static By navBackBtn = By.name("navbar back btn");
	public static By downloadSection = By.name("Downloads");
	public static By rentalsSection = By.name("My Rentals");
	public static By watchLaterModule = By.name("Watchlist");
	public static By HistorySection = By.name("History");
	public static By langSettings = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[5]/XCUIElementTypeStaticText");
	public static By subscriptionSection = By.name("Subscription");
	public static By transactionHistory = By.name("Transaction History");
	public static By supportSection = By.name("Support");
	public static By LinkTV = By.name("Link TV");
	public static By logoutButton = By.name("Logout");
	public static By confirm = By.name("Confirm");
	public static By meHeading = By.name("ME");
	public static By subscriptionHeading = By.id("SUBSCRIPTION");
	public static By ticketHeading = By.id("TICKETS");
	public static By loginInfo = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[10]/XCUIElementTypeStaticText[2]");
	public static By subsInfo = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeImage/XCUIElementTypeStaticText[3]");
	public static By ticketInfo = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeImage/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By ticketImage = By.id("bigticket_image");

	/***
	 * Function Name :- selectDiscover Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DiscoverPage getDiscoverPage() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(navBackBtn))
				click(navBackBtn, "Back From ME page");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DiscoverPage();
	}

	/***
	 * Function Name :- getDownloadPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DownloadsPage getDownloadPage() {
		try {
			click(downloadSection, "Downloads");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	/***
	 * Function Name :- getMyRentalsPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MyRentalsPage getMyRentalsPage() {
		try {
			click(rentalsSection, "My Rentals");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MyRentalsPage();
	}

	/***
	 * Function Name :- getWatchListPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public WatchListPage getWatchListPage() {
		try {
			click(watchLaterModule, "WatchList");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}

	/***
	 * Function Name :- getHistoryPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public HistoryPage getHistoryPage() {
		try {
			click(HistorySection, "History");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new HistoryPage();
	}

	/***
	 * Function Name :- getSettingsPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public SettingsPage getSettingsPage() {
		try {
			click(langSettings, "Settings");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}

	/***
	 * Function Name :- getSubscriptionPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public SubscriptionPage getSubscriptionPage() {
		try {
			waitForVisibilityOfElement(subscriptionSection, "Subscription");
			click(subscriptionSection, "Subscription");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SubscriptionPage();
	}

	/***
	 * Function Name :- getTransactionHistoryPage Developed By :- Pankaj Kumar Date
	 * :- 4-July-2019
	 */
	public TransactionHistoryPage getTransactionHistoryPage() {
		try {
			waitForVisibilityOfElement(transactionHistory, "Transaction History");
			click(transactionHistory, "Transaction History");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TransactionHistoryPage();
	}

	/***
	 * Function Name :- getSupportPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public SupportPage getSupportPage() {
		try {
			waitForVisibilityOfElement(supportSection, "Support");
			click(supportSection, "Support");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SupportPage();
	}

	/***
	 * Function Name :- getLinkTVPage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public LinkTVPage getLinkTVPage() {
		try {
			click(LinkTV, "Link TV");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}

	/***
	 * Function Name :- logOut Developed By :- Pankaj Kumar Date :- 4-July-2019
	 */
	public LoginPage logOut() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(meLabel))
				click(meLabel, "Me Icon");
			click(logoutButton, "Logout Button");
			click(confirm, "Confirm");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LoginPage();
	}

	/***
	 * Function Name :- verifyingMePage Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public MePage verifyMePage() {
		try {
			verifyTextContains(meHeading, "ME", "Me Page Title");
			verifyTextContains(subscriptionHeading, "SUBSCRIPTION", "SUBSCRIPTION Title");
			// verifyTextContains(subsInfo, "DAY", "Subscription Info");
			verifyTextContains(ticketHeading, "TICKETS", "TICKETS Title");
			getText(ticketInfo, "Ticket Info");
			isElementDisplayed(ticketImage);
			verifyTextContains(loginInfo, ReadTestData.ACTIVE_USER_ID.toLowerCase(), "Login Info");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}

}

package ios.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class TransactionHistoryPage  extends ActionEngine {
	public static By TransationHistoryTitle = By.name("TRANSACTION HISTORY");
	public static By transactionHistoryTable = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]");
	public static By navBackBtn = By.name("navbar back btn");
	public static By transactionHistoryLogo = By.id("empState_TransactionHistory");
	public static By Message = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	
	/***
	 * Function Name :- navigatebacktoTransactionHistory Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			click(navBackBtn, "Back From Transaction History page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- fnVerifyTransactionHistory Developed By :- Pankaj
	 * Kumar Date :- 23-May-2019
	 */
	public TransactionHistoryPage fnVerifyTransactionHistory()  {
		try {
				isElementDisplayed(TransationHistoryTitle);
				List<WebElement> eleTable = driver.findElements(transactionHistoryTable);
				List<WebElement> eleTableHistory = eleTable.get(0).findElements(By.xpath("XCUIElementTypeStaticText"));
				for (int j = 0; j < eleTableHistory.size(); j++) 
				{
					ReporterLog.pass("Transaction History Detail for Transaction : " + (1),"Text Displayed : " + eleTableHistory.get(j).getText());
				}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TransactionHistoryPage();
	}
	
	
	/***
	 * Function Name :- fnVerifyTransactionHistoryEmpty Developed By :- Pankaj Kumar
	 * Date :- 23-May-2019
	 */
	public TransactionHistoryPage fnVerifyTransactionHistoryEmpty()  {
		try {
			isElementDisplayed(transactionHistoryLogo);
			
			verifyTextContains(Message,"Receipts from your transactions appear here","Transaction History Message");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TransactionHistoryPage();
	}
	
	
	/***
	 * Function Name :- fnVerifyTransactionHistorytitle Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public TransactionHistoryPage fnVerifyTransactionHistorytitle()  {
		try {
			isElementDisplayed(TransationHistoryTitle);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TransactionHistoryPage();

	}
}

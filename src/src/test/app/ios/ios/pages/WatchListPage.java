package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class WatchListPage  extends ActionEngine {
	public static By navBackBtn = By.name("navbar back btn");
	public static By editButton = By.name("Edit");
	public static By cancel = By.name("Cancel");
	public static By removeButton = By
			.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton");
	
	public static By watchListLogo = By.id("empState_History");
	public static By lstwatchList = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView");
	public static By Message = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By lnkBrowse = By.name("Browse");
	public static By watchListTitle = By.name("WATCHLIST");
	
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			click(navBackBtn, "Back From WatchList page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public WatchListPage clickonWatchListEdit()
	{
		try {
			click(editButton, "Edit Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new WatchListPage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public WatchListPage clickonWatchListCancel()
	{
		try {
			click(cancel, "Cancel Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new WatchListPage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public WatchListPage clickonWatchListRemove()
	{
		try {
			click(removeButton, "Remove Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new WatchListPage();
	}
	
	/***
	 * Function Name :- fnVerifyHistoryScreen Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public WatchListPage fnVerifyWatchList()
	{
		try {
			if (isNumOf_Elements_Greater_Than_Zero(watchListLogo)) 
			{
				verifyEmptyWatchList();
			} 
			else
			{
				getCountofWatchListContent();
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}
	
	/***
	 * Function Name :- fnVerifyHistoryScreen Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public int getCountofWatchListContent()
	{
		try {
				WebElement eleCom = driver.findElement(lstwatchList);
				List<WebElement> eleComList = eleCom.findElements(By.xpath("XCUIElementTypeCell"));
				if (eleComList.size() > 0) 
				{
					ReporterLog.pass("WatchList Page Screen","WatchList Page is displayed with " + eleComList.size() + " Contents");
				} 
				return eleComList.size();
			
			} catch (Exception e) {
				TestUtilities.logReportFailure(e);
		}
		return 0;
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public WatchListPage verifyEmptyWatchList()
	{
		try {
			verifyTextContains(Message, "Movies and TV Shows that you watch appear here", "WatchList Message");
			isElementDisplayed(lnkBrowse);
			isElementDisplayed(watchListTitle);
			
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new WatchListPage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public WatchListPage selectContentFromWatchList()
	{
		try {
			if(getCountofWatchListContent()>0)
			{
				WebElement eleCom = driver.findElement(lstwatchList);
				List<WebElement> eleComList = eleCom.findElements(By.xpath("XCUIElementTypeCell"));
				eleComList.get(0).click();
			}
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new WatchListPage();
	}
	
}

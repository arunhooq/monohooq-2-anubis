package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class DownloadsPage extends ActionEngine {

	public static By editButton = By.name("Edit");
	public static By cancel = By.name("Cancel");
	public static By removeButton = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton");
	public static By Message = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By lnkDiscover = By.name("Discover");
	public static By navBackBtn = By.name("navbar back btn");
	public static By downloadTitle = By.id("DOWNLOADS");
	public static By lstdownload = By.xpath("//XCUIElementTypeTable");
	
	/***
	 * Function Name :- navigateBackFromDownloads Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			click(navBackBtn, "Back From Download page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- fnVerifyDownloads Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public DownloadsPage fnVerifyDownloads()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();

	}
	
	/***
	 * Function Name :- clickonDownlaodEdit Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DownloadsPage clickonDownlaodEdit()
	{
		try {
			click(editButton, "Edit Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new DownloadsPage();
	}
	
	/***
	 * Function Name :- clickonDownlaodCancel Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DownloadsPage clickonDownlaodCancel()
	{
		try {
			click(cancel, "Cancel Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new DownloadsPage();
	}
	
	/***
	 * Function Name :- clickonDownloadRemove Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DownloadsPage clickonDownloadRemove()
	{
		try {
			click(removeButton, "Remove Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new DownloadsPage();
	}
	
	/***
	 * Function Name :- getCountofDownloadContent Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public int getCountofDownloadContent()
	{
		try {
				WebElement eleCom = driver.findElement(lstdownload);
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
	 * Function Name :- verifyEmptyDownloads Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DownloadsPage verifyEmptyDownloads()
	{
		try {
			verifyTextContains(Message, "Movies and TV Shows that you watch appear here", "WatchList Message");
			isElementDisplayed(lnkDiscover);
			isElementDisplayed(downloadTitle);
			
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new DownloadsPage();
	}
	
	/***
	 * Function Name :- selectContentFromDownloads Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public DownloadsPage selectContentFromDownloads()
	{
		try {
			if(getCountofDownloadContent()>0)
			{
				WebElement eleCom = driver.findElement(lstdownload);
				List<WebElement> eleComList = eleCom.findElements(By.xpath("XCUIElementTypeCell"));
				eleComList.get(0).click();
			}
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new DownloadsPage();
	}
	
	
	
	
	
	
	
	
}

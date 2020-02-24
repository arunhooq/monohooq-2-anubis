package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import io.appium.java_client.MobileElement;

public class HistoryPage extends ActionEngine{

//	Locators
	public static By navBackBtn = By.name("navbar back btn");
	public static By editButton = By.name("Edit");
	public static By cancel = By.name("Cancel");
	public static By btnConfirm = By.name("Confirm");
	public static By imgSelectedTick = By.id("History_Selected_Icon");
	public static By btnRemove = By.name("Remove");
	//public static By removeButton = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton");
	public static By historyLogo = By.id("empState_History");
	public static By lstHistory = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView");
	public static By Message = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By lnkBrowse = By.name("Browse");
	public static By HistoryTitle = By.name("HISTORY");
	public static By tableContentHistory = By.xpath("XCUIElementTypeCell");
	
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			click(navBackBtn, "Back From History page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public HistoryPage clickonHistoryEdit()
	{
		try {
			click(editButton, "Edit Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new HistoryPage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public HistoryPage verifyHistoryRemove()
	{
		try {
			isElementDisplayed(btnRemove);
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new HistoryPage();
	}
	
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public HistoryPage clickonHistoryCancel()
	{
		try {
			click(cancel, "Cancel Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new HistoryPage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public HistoryPage clickonHistoryRemove()
	{
		try {
			click(btnRemove, "Remove Button");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new HistoryPage();
	}
	
	/***
	 * Function Name :- fnVerifyHistoryScreen Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public HistoryPage fnVerifyHistory()
	{
		try {
			if (isNumOf_Elements_Greater_Than_Zero(historyLogo)) 
			{
				verifyEmptyHistory();
			} 
			else
			{
				getCountofHistoryContent();
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new HistoryPage();
	}
	
	/***
	 * Function Name :- fnVerifyHistoryScreen Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public int getCountofHistoryContent()
	{
		try {
				WebElement eleCom = driver.findElement(lstHistory);
				List<WebElement> eleComList = eleCom.findElements(By.xpath("XCUIElementTypeCell"));
				if (eleComList.size() > 0) 
				{
					ReporterLog.pass("History Page Screen","History Page is displayed with " + eleComList.size() + " Contents");
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
	public HistoryPage verifyEmptyHistory()
	{
		try {
			verifyTextContains(Message, "Movies and TV Shows that you watch appear here", "History Message");
			isElementDisplayed(HistoryTitle);
			isElementDisplayed(lnkBrowse);
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new HistoryPage();
	}
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public ContentDetailsPage selectContentFromHistory()
	{
		try {
			waitTillElementPresent_HardWait_Polling(editButton, GlobalConstant.WAIT_SHORT_5_SEC);
			if(getCountofHistoryContent()>0)
			{
				WebElement eleCom = driver.findElement(lstHistory);
				List<WebElement> eleComList = eleCom.findElements(By.xpath("XCUIElementTypeCell"));
				VerifyThatIsTrue(eleComList.size()>0, "History Section Have atleast 1 Content ");
				eleComList.get(0).click();
			}
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new ContentDetailsPage();
	}
	
	
	public void validateHistoryPage() throws InterruptedException{
		
		//Validate that Last episode Shall be Present 
		selectContentFromHistory()
		.verifyTVShowElementsVisibility(); //Verify Content Details Page
		click(ContentDetailsPage.btnClose, "Close Conetnt Button");  //Click on the Close Button
		
		//Navigate back to History page and Validate Edit Functionality 
		waitForVisibilityOfElement(HistoryTitle, "History Title");
		isElementDisplayed(editButton);
		click(editButton, "History Edit button");
		isElementDisplayed(btnRemove);
		isElementDisplayed(cancel);
		tapOnElement((MobileElement) getElements(lstHistory).get(0), "Select Content History");
		//click(getElements(lstHistory).get(0),"Select Content History"); //Select Content To be removed
		Thread.sleep(2000);
		isElementDisplayed(imgSelectedTick);
		
		//Remove Content from History Page 
		click(btnRemove, "Remove button _ Hiostory");
		waitTillElementPresent_HardWait_Polling(btnConfirm, GlobalConstant.WAIT_SHORT_3_SEC);
		click(btnConfirm, "Confirm Pop Up ");
		
		if(!(getElements(lstHistory).size() >0) ) {
			ReporterLog.pass("Validate History After Remove Content", "Content removed Successfully ");	
		}else {
			ReporterLog.fail("Validate History After Remove Content", "Content remove didn't  worked ");	
		}
	}
	
	
}

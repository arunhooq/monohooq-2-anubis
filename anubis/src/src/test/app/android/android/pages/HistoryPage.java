package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class HistoryPage extends ActionEngine{
	
	public static By historyTitle = By.xpath("//android.widget.TextView[@text='History']"); //public static By historyTitle = By.xpath("tv.hooq.android:id/title");
	public static By btnHistoryEdit = By.id("tv.hooq.android:id/btnCancel");
	public static By btnHistoryRemove = By.id("tv.hooq.android:id/btnRemove");
	public static By btnHistoryCancel = By.id("tv.hooq.android:id/btnCancel");
	public static By btnHistoryBack = By.id("tv.hooq.android:id/btnUp");
	
	public HistoryPage getHistoryTitle() {
		try 
		{
			String strHistoryTitle = getText(historyTitle, "History title");
			verifyText(strHistoryTitle, "History");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new HistoryPage();
	}
	
	public MePage clickHistoryBack() {
		try 
		{
			click(btnHistoryBack, "History Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	

}

package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class TransactionHistoryPage extends ActionEngine {
	
	public static By transactionHistoryTitle = By.xpath("//android.widget.TextView[@text='Transaction History']"); //public static By transactionHistoryTitle = By.id("tv.hooq.android:id/title");
	public static By btnTransactionHistoryBack = By.id("tv.hooq.android:id/btnUp");
	
	public TransactionHistoryPage getTransactionHistoryTitle() {
		try 
		{
			String strTransactionHistoryTitle = getText(transactionHistoryTitle, "Transaction History title");
			verifyText(strTransactionHistoryTitle, "Transaction History");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TransactionHistoryPage();
	}
	
	public MePage clickTransactionHistoryBack() {
		try 
		{
			click(btnTransactionHistoryBack, "Transaction History Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	
}

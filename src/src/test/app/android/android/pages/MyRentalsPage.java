package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class MyRentalsPage extends ActionEngine {
	
	public static By myRentalsTitle = By.id("tv.hooq.android:id/toolbarTitle");
	public static By btnMyRentalsBack = By.id("tv.hooq.android:id/backInMainActivity");
	
	public MyRentalsPage getMyRentalsTitle() {
		try 
		{
			String strMyRentalsTitle = getText(myRentalsTitle, "My Rentals title");  
			verifyText(strMyRentalsTitle, "My Rentals");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MyRentalsPage();
	}
	
	public MePage clickMyRentalsBack() {
		try 
		{
			click(btnMyRentalsBack, "My Rentals Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	
	
	
}

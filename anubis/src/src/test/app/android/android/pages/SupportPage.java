package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class SupportPage extends ActionEngine {
	
	public static By supportTitle = By.id("tv.hooq.android:id/toolbarTitle");
	public static By btnSupportBack = By.id("tv.hooq.android:id/backInMainActivity");
	
	public SupportPage getSupportTitle() {
		try 
		{
			String strSupportTitle = getText(supportTitle, "Support title"); 
			verifyText(strSupportTitle, "Support");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SupportPage();
	}
	
	public MePage clickSupportBack() {
		try 
		{
			click(btnSupportBack, "Support Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	
}

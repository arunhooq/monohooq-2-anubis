package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class SubscriptionPage extends ActionEngine {
	
	public static By subscriptionTitle = By.id("tv.hooq.android:id/toolbarTitle");
	public static By btnSubscriptionBack = By.id("tv.hooq.android:id/backInMainActivity");
	public static By btnSubscribeNow = By.id("tv.hooq.android:id/manage");
	
	
	public SubscriptionPage getSubscriptionTitle() {
		try 
		{
			String strSubscriptionTitle = getText(subscriptionTitle, "Subscription title"); 
			verifyText(strSubscriptionTitle, "Subscription");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SubscriptionPage();
	}
	
	public MePage clickSubscriptionBack() {
		try 
		{
			click(btnSubscriptionBack, "Subscription Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public NovaPlanSelectorPage clickSubscribeNow() {
		try 
		{
			click(btnSubscribeNow, "Subscribe now");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
		
	

}

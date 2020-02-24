package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class SettingsPage extends ActionEngine {
	
	public static By settingsTitle = By.id("tv.hooq.android:id/toolbarTitle");
	public static By btnSettingsBack = By.id("tv.hooq.android:id/backInMainActivity");
	public static By mobileDataUsage=By.xpath("//android.widget.TextView[@text='Mobile Data Usage']");
	public static By switchWidgetON=By.xpath("//android.widget.Switch[@text='ON']");
	public static By switchWidgetOFF=By.xpath("//android.widget.Switch[@text='OFF']");
	
	public SettingsPage getSettingsTitle() {
		try 
		{
			String strSettingsTitle = getText(settingsTitle, "Settings title"); 
			verifyText(strSettingsTitle, "Settings");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}
	
	public MePage clickSettingsBack() {
		try 
		{
			click(btnSettingsBack, "Settings Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public SettingsPage verifyMobileDataUsage() {
        try 
        {
             
            isElementPresent(mobileDataUsage);
            if(isElementPresent(switchWidgetON))
            {
                click(switchWidgetON,"Mobile Data Usage is switched OFF");
            }
            if(isElementPresent(switchWidgetOFF))
            {
                click(switchWidgetOFF,"Mobile Data Usage is switched ON");
            }
            
            
        } 
        catch (Exception e) {
            TestUtilities.logReportFailure(e);
        }
        return new SettingsPage();
    }

}

package android.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class LinkTVPage extends ActionEngine{
	
	public static By linkTVTitle = By.id("tv.hooq.android:id/toolbarTitle");
	public static By btnLinkTVBack = By.id("tv.hooq.android:id/backInMainActivity");
	public static By btnLinkTV = By.id("tv.hooq.android:id/btn_link");
	public static By allowAccessToCam = By.id("com.android.packageinstaller:id/permission_allow_button");
	public static By btnEnterTVCode = By.id("tv.hooq.android:id/btnEnterTvCode");
	public static By PinTVCode = By.id("tv.hooq.android:id/pincode");
	public static By btnLink= By.id("tv.hooq.android:id/btn_link");
	public static By invalidTVCode= By.xpath("//*[@class='android.widget.Toast']");
	public static By validTVCode= By.id("tv.hooq.android:id/title");
	
	public LinkTVPage getLinkTVTitle() {
		try 
		{
			String strLinkTVTitle = getText(linkTVTitle, "LinkTV title");
			verifyText(strLinkTVTitle, "Link TV");			
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	public MePage clickLinkTVBack() {
		try 
		{
			click(btnLinkTVBack, "LinkTV Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public LinkTVPage clickLinkTV() {
		try 
		{
			click(btnLinkTV, "LinkTV");
			clickOnElementInAList(allowAccessToCam, 0);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	public LinkTVPage clickEnterTVCode() {
		try 
		{
			click(btnEnterTVCode, "Enter TV Code");
			isElementDisplayed(PinTVCode);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	public LinkTVPage typeTVCode(String tvCode) {
		try 
		{
			List<WebElement> pin = driver.findElement(By.id("tv.hooq.android:id/pincode")).findElements(By.className("android.widget.EditText"));
			System.out.println(pin.size());
			click(pin.get(0), "TV Code");
			pin.get(0).sendKeys(tvCode);			
			
			//isElementDisplayed(PinTVCode);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	
	public LinkTVPage clickLinkButton() {
		try 
		{					
			click(btnLink, "Link");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	public LinkTVPage verifyInvalidTVCode() {
		try 
		{			
			isElementDisplayed(invalidTVCode);
			String strInvalidCode = getText(invalidTVCode, "Invalid TV Code");
			//verifyTextContains(invalidTVCode, "Invalid TV Code", "Invalid TV Code message");
			verifyTextContains(strInvalidCode, "Invalid TV code");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	public LinkTVPage verifyValidTVCode() {
		try 
		{			
			isElementDisplayed(validTVCode);
			String strTVCodeLinked = getText(validTVCode, "TV Linked");
			//verifyTextContains(invalidTVCode, "Invalid TV Code", "Invalid TV Code message");
			verifyTextContains(strTVCodeLinked, "TV LINKED");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	

}

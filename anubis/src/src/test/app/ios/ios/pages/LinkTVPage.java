package ios.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;


public class LinkTVPage extends ActionEngine {
	//Locators
	public static By navBackBtn = By.name("navbar back btn");
	public static By LinkTV = By.name("Link TV");
	public static By linkTVMessage1 = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]");
	public static By linkTVMessage2 = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	public static By ScanQRCode = By.id("Scan QR Code");
	public static By EnterTVCode = By.id("Enter TV Code");
	public static By qrcode_box_mobile = By.id("qrcode_box_mobile");
	public static By ScanMessage = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By TVCodeMessage = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By TVCodeEntryBox = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText");
	public static By LinkButton = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton");
	public static By LinkTVButton = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton");
	public static By linkTVHeaderTitle = By.name("Link TV");
	public static By scanPopupAllowOkButton=By.name("OK");
	public static By TVCodeError=By.id("Unable to Link TV");
	public static By TVCodeerrorok=By.id("Okay");
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			
			click(navBackBtn, "Back From LinkTV page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- fnSelectMESection Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public LinkTVPage verifLinkTVLandingpage()
	{
		try 
		{		
			verifyTextContains(linkTVMessage1,"Launch","Link TV Message 1");
			verifyTextContains(linkTVMessage2,"Using your mobile ","Link TV Message 2");
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
	
	public LinkTVPage clickLinkTVButtonInLandingPage()
	{
		try 
		{		
		click(LinkTVButton, "LinkTV Button");
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
	
	public LinkTVPage gotoEnterTVCode()
	{
		try 
		{		
			click(EnterTVCode, "Enter TV Code");
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
	
	/***
	 * Function Name :- fnSelectMESection Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public LinkTVPage verifLinkTVSvanQRCode()
	{
		try 
		{		
			
			fnHandleScanNotificationPopup();
			isElementDisplayed(linkTVHeaderTitle);
			click(ScanQRCode, "Scan QR Code");
			verifyTextContains(ScanMessage, "Scan the QR code", "Scan QR Code Info");
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
	
	/***
	 * Function Name :- fnSelectMESection Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public LinkTVPage verifLinkTVEnterTVCode()
	{
		try 
		{		
			gotoEnterTVCode();
			verifyTextContains(TVCodeMessage,"Please enter the 6","Enter TV Code Info");
			
			if(!ConfigDetails.environment.equalsIgnoreCase(GlobalConstant.ENVIRONMENT_BROWSERSTACK)) {
				isElementDisplayed(TVCodeEntryBox);
				type(TVCodeEntryBox, "agfvrs", "TV Code");
				isElementDisplayed(LinkButton);
				click(LinkButton,"LinkTV");
				verifyTextContains(TVCodeError,"Unable to Link TV","TV Code Error Info");
				click(TVCodeerrorok,"TV Code Error OK");
			}
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
	
	public static void fnHandleScanNotificationPopup()
	{
	    if(isNumOf_Elements_Greater_Than_Zero(scanPopupAllowOkButton))
	    {
	        click(scanPopupAllowOkButton,"Scan Code Allow OK");
	    }
	}
	
	/***
	 * Function Name :- fnSelectMESection Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public LinkTVPage enterTVCode()
	{
		try 
		{		
			
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
	
	/***
	 * Function Name :- fnSelectMESection Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public LinkTVPage clickLinkTV_EnterTVCode()
	{
		try 
		{		
			
		}
		catch (Exception e){TestUtilities.logReportFailure(e);}
		return new LinkTVPage();
	}
}

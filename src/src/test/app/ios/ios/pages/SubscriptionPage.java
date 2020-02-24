package ios.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class SubscriptionPage  extends ActionEngine {
	
	public static By navBackBtn = By.name("navbar back btn");
	public static By sublpasetext = By.id("Enjoying HOOQ?");
	public static By Subscriptiontext = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	public static By btnSubscribenow = By.id("Subscribe now");
	public static By SUBSCRIBE=By.id("SUBSCRIBE");
	public static By Selectplan=By.id("Select plan:");
	public static By PlanValue=By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText");
	public static By txtSubscriptionTitle = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By txtEnjoyingHooq = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]");
	public static By btnSubscribeNow = By.id("Subscribe now");
	
	//Plan Selection page
	public static By txtTitlePlanSelection = By.id("SUBSCRIBE");
	public static By btnNavigationBack = By.xpath("//XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By imgHooqLogo = By.id("HOOQ logo");
	public static By txtSelectPlan = By.id("Select plan:");
	public static By txtPlanPrice = By.xpath("//XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText");
	
	public static By btnCancel = By.xpath(".//*[@text='Cancel']");
	public static By btnChangeStore = By.xpath(".//*[@text='Change Store']");
	public static By txtTitleChooseAPlan = By.xpath(".//*[@text='Choose a plan']");
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			
			click(navBackBtn, "Back From Subscription page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	public SubscriptionPage verifySubscriptionPage() {
		try {
		waitForVisibilityOfElement(txtSubscriptionTitle, "SUBSCRIPTION Page title");
		getText(txtSubscriptionTitle, "Subscription title text");
		isElementDisplayed(btnSubscribeNow);
		click(btnSubscribeNow, "SubscribeNow button");
		
		
		//TODO - As browserstack doesnot support apple pay , we will disable below functionality for now
		//waitForVisibilityOfElement(btnCancel ,"Cancel button for AppStore Change");
		
		//click(btnCancel, "Cancel button for AppStore Change");
	
		/*
		waitForVisibilityOfElement(txtTitlePlanSelection, "Subscribe page");
		isElementDisplayed(imgHooqLogo);
		isElementDisplayed(txtSelectPlan);
		isElementDisplayed(txtPlanPrice);
		
		click(btnNavigationBack, "Navigation Back");
		waitForVisibilityOfElement(txtSubscriptionTitle, "SUBSCRIPTION Page title");
		isElementDisplayed(btnSubscribeNow);
		*/
		
		ReporterLog.pass("Subscription page Validation", "Subscription page validated for Lapsed User Movie selection");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new SubscriptionPage();
	}
	
	

	/***
	 * Function Name :- fnVerifySubscriptionDays Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SubscriptionPage verifylapseduser()  {
		try {
			verifyTextContains(sublpasetext, "Enjoying HOOQ?", "Subscription Message 1");
			verifyTextContains(Subscriptiontext, "Subscribe to watch all 10,000 of our Hollywood and Local favorites now!", "Subscription Message 2");
			isElementDisplayed(btnSubscribenow);
			click(btnSubscribenow,"Subscribe Now");
			verifyTextContains(SUBSCRIBE, "SUBSCRIBE", "SUBSCRIBE Title");
			verifyTextContains(Selectplan, "Select plan:", "Select plan: Label");
			verifyTextContains(PlanValue, "1 Year VIP Subscription", "1 Year VIP Subscription Label");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SubscriptionPage();
	}
	
}

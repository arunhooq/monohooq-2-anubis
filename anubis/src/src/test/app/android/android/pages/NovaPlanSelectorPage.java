package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class NovaPlanSelectorPage extends ActionEngine {
	
	public static By creditCardPaymentMethod = By.xpath("//android.widget.Image[@text='logo_credit']");
	public static By debitCardPaymentMethod = By.xpath("//android.widget.Image[@text='logo_debit']");
	public static By cardNumber = By.xpath("//android.widget.EditText[@resource-id='cardnumber']");
	public static By nameOnCard = By.xpath("//android.widget.EditText[@resource-id='nameoncard']");
	public static By expiryDate = By.xpath("//android.widget.EditText[@resource-id='expiry_date']");
	public static By cvv = By.xpath("//android.widget.EditText[@resource-id='cvv']");
	public static By makePayment = By.xpath("//android.widget.Button[@text='Make Payment']"); //("//android.widget.Button[@resource-id='creditcard_btn']");
	public static By cancelOrMakePayment = By.xpath("//android.widget.Button"); 
	public static By seeMorePlans = By.xpath("//android.view.View[@text='See More Plans']");
	public static By recurringVictoryMessage = By.xpath("//android.view.View[contains(@text , 'Congratulations')]");
	public static By tvodVictoryMessage = By.xpath("//android.view.View[contains(@text , 'YOUR RENTAL PURCHASE IS SUCCESSFUL')]");
	public static By nonRecurringVictoryMessage = By.xpath("//android.view.View[contains(@text , 'Your subscription starts now!')]");
	public static By btnDone = By.xpath("//android.view.View[@text = 'Done']");
	public static By returnToMerchantSite = By.xpath("//android.widget.Button[@text='Return To the Merchant Site']");
	
	public NovaPlanSelectorPage selectAPlanWhichHasCCPayment() {
		try 
		{
			clickOnElementInAList(creditCardPaymentMethod, 0);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage selectAPlanWhichHasDCPayment() {
		try 
		{
			if(isNumOf_Elements_Greater_Than_Zero(seeMorePlans))
				click(seeMorePlans, "See More Plans");
			clickOnElementInAList(debitCardPaymentMethod, 0);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage clickOnCreditCardPaymentMethod() {
		try 
		{
			waitForVisibilityOfElement(creditCardPaymentMethod, "Credit Card payment method");
			clickOnElementInAList(creditCardPaymentMethod, 0);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage clickOnDebitCardPaymentMethod() {
		try 
		{
			waitForVisibilityOfElement(debitCardPaymentMethod, "Debit Card payment method");
			clickOnElementInAList(debitCardPaymentMethod, 0);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage fillCardDetails() {
		try 
		{
			type(cardNumber, ReadTestData.CARD_NUMBER, "Card Number");
            type(nameOnCard, ReadTestData.NAME, "Card User");
            click(expiryDate, "Expiry Date");
            driver.findElement(expiryDate).sendKeys(ReadTestData.EXPIRY);
            type(cvv, ReadTestData.CVV, "Card CVV Number");
            
           // click(makePayment, "Make Payment");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage clickMakePayment() {
		try 
		{
           // click(makePayment, "Make Payment");
			clickOnElementInAList(cancelOrMakePayment, 1);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}	
	
	public NovaPlanSelectorPage verifyNonRecurringVictoryMessage() {
		try 
		{
			if(ConfigDetails.country.equalsIgnoreCase("IN"))			
				click(returnToMerchantSite, "Return to Merchant site");

			String strVictoryMessage = getText(nonRecurringVictoryMessage, "Victory message");
			verifyTextContains(strVictoryMessage, "Your subscription starts now!");
			click(btnDone, "Done");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage verifyRecurringVictoryMessage() {
		try 
		{
			if(ConfigDetails.country.equalsIgnoreCase("IN"))			
				click(returnToMerchantSite, "Return to Merchant site");

			String strVictoryMessage = getText(recurringVictoryMessage, "Victory message");
			verifyTextContains(strVictoryMessage, "Congratulations!");
			click(btnDone, "Done");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public ContentDetailsPage verifyTVODVictoryMessage() {
		try 
		{	
			String strVictoryMessage = getText(tvodVictoryMessage, "Victory message");
			verifyTextContains(strVictoryMessage, "YOUR RENTAL PURCHASE IS SUCCESSFUL!");
			click(btnDone, "Done");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

}

package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;


public class MePage extends ActionEngine{
	
	public static By meDownloads = By.id("tv.hooq.android:id/profileBtnDownload");	
	public static By meMyRentals = By.id("tv.hooq.android:id/profileBtnMyRentals");
	public static By meWatchlist = By.id("tv.hooq.android:id/profileBtnWatchLater");
	public static By meHistory = By.id("tv.hooq.android:id/profileBtnHistory");
	public static By meSettings = By.id("tv.hooq.android:id/profileBtnSettings");
	public static By meSubsciption = By.id("tv.hooq.android:id/profileBtnSubscription");
	public static By meTransactionHistory = By.id("tv.hooq.android:id/profileBtnTrasactionHistory");
	public static By meSupport = By.id("tv.hooq.android:id/profileBtnSupport");
	public static By meLinkTV = By.id("tv.hooq.android:id/profileBtnLinkTV");
	public static By meLogout = By.id("tv.hooq.android:id/profileBtnLoginOut");
	public static By exploreRentalPopUp = By.id("tv.hooq.android:id/okay");
	public static By subscriptionField = By.xpath("//android.widget.TextView[@text='Subscription']");
	public static By noOfSubscriptionDays = By.id("tv.hooq.android:id/subscription_days");
	public static By ticketsField = By.xpath("//android.widget.TextView[@text='Tickets']");
	public static By noOfTickets = By.id("tv.hooq.android:id/tickets");
	public static By loggedInUser = By.id("tv.hooq.android:id/subtitle");
	public static By imgIcon = By.id("tv.hooq.android:id/icon");
	
	
	public DownloadsPage getDownloadsPage() {
		try 
		{
			click(meDownloads, "Downloads");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}
	
	
	public MyRentalsPage getMyRentalsPage() {
		try 
		{
			click(meMyRentals, "My Rentals");
			if(isNumOf_Elements_Greater_Than_Zero(exploreRentalPopUp))
				click(exploreRentalPopUp, "Explore Rental PopUp");
       } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MyRentalsPage();
	}
	
	public WatchListPage getWatchlistPage() {
		try 
		{
			click(meWatchlist, "Watchlist");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}
	
	public HistoryPage getHistoryPage() {
		try 
		{
			click(meHistory, "History");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new HistoryPage();
	}
	
	public SettingsPage getSettingsPage() {
		try 
		{
			click(meSettings, "Settings");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}
	
	public SubscriptionPage getSubscriptionPage() {
		try 
		{
			click(meSubsciption, "Subscription");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SubscriptionPage();
	}
	
	public TransactionHistoryPage getTransactionHistoryPage() {
		try 
		{
			click(meTransactionHistory, "Transaction History");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new TransactionHistoryPage();
	}
	
	public SupportPage getSupportPage() {
		try 
		{
			click(meSupport, "Support");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SupportPage();
	}
	
	public LinkTVPage getLinkTVPage() {
		try 
		{
			click(meLinkTV, "Link TV");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LinkTVPage();
	}
	
	public BasePage clickLogout() {
		try 
		{
			click(meLogout, "Logout");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new BasePage();
	}
	
	public MePage getSubscriptionDays() {
		try 
		{
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED))
				verifyText(noOfSubscriptionDays, "0 DAY");
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE))
				verifyText(noOfSubscriptionDays, "ACTIVE");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public MePage getSubscriptionLabel() {
		try 
		{
			isElementDisplayed(subscriptionField);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	public MePage getTicketLabel() {
		try 
		{
			isElementDisplayed(ticketsField);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public MePage getNoOfBTVODs() {
		try 
		{	
			String strNoOfTicketsAvailable=getText(noOfTickets, "No of tickets Available");
			try
			{
				int intTicketCount=Integer.parseInt(strNoOfTicketsAvailable);
				if(intTicketCount<0)
					strNoOfTicketsAvailable="No Ticket Available";
			}
			catch(Exception e) {}
			verifyText(noOfTickets, strNoOfTicketsAvailable);
						
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	public MePage verify_LoggedInUser(String strUsername) {
		try 
		{		
			verifyText(loggedInUser, strUsername);
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public MePage verify_MeProfileImages() {
		try 
		{				
			verifyElementsDisplayedInAnElement(meDownloads, imgIcon);
			verifyElementsDisplayedInAnElement(meMyRentals, imgIcon);
			verifyElementsDisplayedInAnElement(meWatchlist, imgIcon);
			verifyElementsDisplayedInAnElement(meHistory, imgIcon);
			verifyElementsDisplayedInAnElement(meSettings, imgIcon);
			verifyElementsDisplayedInAnElement(meSubsciption, imgIcon);
			verifyElementsDisplayedInAnElement(meTransactionHistory, imgIcon);
			verifyElementsDisplayedInAnElement(meSupport, imgIcon);
			verifyElementsDisplayedInAnElement(meLinkTV, imgIcon);
							
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	public MePage verify_MeProfileLabels() {
		try 
		{				
			isElementDisplayed(meDownloads);
			isElementDisplayed(meMyRentals);
			isElementDisplayed(meWatchlist);
			isElementDisplayed(meHistory);
			isElementDisplayed(meSettings);
			isElementDisplayed(meSubsciption);
			isElementDisplayed(meTransactionHistory);
			isElementDisplayed(meSupport);
			isElementDisplayed(meLinkTV);
							
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
		
	
}

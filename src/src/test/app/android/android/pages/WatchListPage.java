package android.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class WatchListPage extends ActionEngine {
	
	public static By watchListTitle = By.xpath("//android.widget.TextView[@text='Watchlist']"); //By.id("tv.hooq.android:id/header");
	public static By btnWatchListEdit = By.id("tv.hooq.android:id/btnCancel");
	public static By btnWatchListRemove = By.id("tv.hooq.android:id/btnRemove");
	public static By btnWatchListCancel = By.id("tv.hooq.android:id/btnCancel");
	public static By btnWatchListBack = By.id("tv.hooq.android:id/btnUp");	
	public static By watchlistContainer = By.id("tv.hooq.android:id/listView");
	public static By contentInWatchlist = By.id("tv.hooq.android:id/imagePortrait");
	public static By contentTitle = By.id("tv.hooq.android:id/txtAssetTitle");
	public static By edit = By.id("tv.hooq.android:id/btnCancel");
	public static By cancelEdit = By.id("tv.hooq.android:id/btnCancel");
	public static By remove = By.xpath("//android.widget.TextView[@text='Remove']");
	public static By confirm = By.id("tv.hooq.android:id/okay");
	
	public WatchListPage getWatchlistTitle() {
		try 
		{
			String strWatchListTitle = getText(watchListTitle, "Watchlist title"); 
			verifyText(strWatchListTitle, "WatchList");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}
	
	public MePage clickWatchlistBack() {
		try 
		{
			click(btnWatchListBack, "WatchList Back");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public WatchListPage clickEditWatchlist() {
		try 
		{
			click(edit, "Edit");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}
	
	public MePage clickCancelEditWatchlist() {
		try 
		{
			click(edit, "Edit");
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}
	
	public WatchListPage clickRemoveFromWatchlist() {
		try 
		{
			click(btnWatchListRemove, "Remove");
			click(confirm, "Confirm");
			
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}
	
	public WatchListPage clickOnAContentInWatchlist() {
		try 
		{
			clickOnElementInListOfAList(watchlistContainer, contentInWatchlist, 0);			
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new WatchListPage();
	}

	public WatchListPage verifyContentDisplayedInWatchlist(String strContentTitle) {
		try 
		{
			clickOnElementInListOfAList(watchlistContainer, contentInWatchlist, 0);	
			verifyText(strContentTitle, getText(contentTitle, "Title"));
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new WatchListPage();
	}
	
	public WatchListPage verifyContentNotDisplayedInWatchlist(String strContentTitle) {
		try 
		{
			boolean blnStatus = true;
			if(!isNumOf_Elements_InAnElement_Greater_Than_Zero(watchlistContainer, contentInWatchlist))
			{
				ReporterLog.pass("Remove content from Watchlist", "Content is removed from Watchlist successfully");
			}
			else
			{
				int watchlistCount = NumOfEleInAnElement(watchlistContainer,contentInWatchlist);
				for(int i=0; i < watchlistCount ; i++)
				{
					clickOnElementInListOfAList(watchlistContainer, contentInWatchlist, 0);	
					//verifyText(strContentTitle, getText(contentTitle, "Title"));
					if(strContentTitle.equalsIgnoreCase(getText(contentTitle, "Title")))
					{
						ReporterLog.fail("Remove content from Watchlist", "Content is not removed from Watchlist");
						blnStatus = false;
						break;
					}
				}	
				if(blnStatus)
				{
					ReporterLog.pass("Remove content from Watchlist", "Content is removed from Watchlist successfully");
				}
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new WatchListPage();
	}

}

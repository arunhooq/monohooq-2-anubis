package android.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import android.testobjects.TabletLocators;

public class SearchPage extends ActionEngine {
	
	
	public static By edtSearch = By.id("tv.hooq.android:id/inputSearch");
	public static By searchResultList = By.id("tv.hooq.android:id/matchedTitlesListView");
	public static By tvChannelSearchResultList = By.id("tv.hooq.android:id/matchedTvChannelsListView");
	public static By noSearchResult = By.id("tv.hooq.android:id/lblNoResult");
	public static By backFromSearchPage = By.id("tv.hooq.android:id/btnUp");
	public static By txtContentTitle = By.id("tv.hooq.android:id/txtAssetTitle");
	public static By recentSearchesSection = By.id("tv.hooq.android:id/recentSearchesAnchor");
	public static By lblRecentSearches = By.xpath("//android.widget.TextView[@text='RECENT SEARCHES']");
	public static By recentSearchesList = By.id("tv.hooq.android:id/recentSearchesListView");
	public static By recentSearchesContentName = By.id("tv.hooq.android:id/text_search_term");
	public static By btnClearResentSearches = By.id("tv.hooq.android:id/recentSearchesClearButton");
	public static By popUpToClearResentSearches = By.id("tv.hooq.android:id/title");
	public static By btnConfirm = By.id("tv.hooq.android:id/okay");
	
	
	public static void SearchContent(String contentName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, contentName, "Enter Movie Details");
			Thread.sleep(4000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchResultList)) {
				ReporterLog.pass("Search functionality", "Search results are displayed");
				List<WebElement> searchResults = driver.findElements(By.id("tv.hooq.android:id/matchedTitlesListView")); // tv.hooq.android:id/imgPoster
				System.out.println(searchResults.size());
				List<WebElement> actualResults = searchResults.get(0)
						.findElements(By.className("android.widget.FrameLayout")); // android.widget.ImageView
				if (actualResults.size() > 0) {
					actualResults.get(0).click();
					Thread.sleep(4000);
					String title = getText(By.id("tv.hooq.android:id/txtAssetTitle"), "title"); // tv.hooq.android:id/tv_spotlight_title
					if (title.equalsIgnoreCase(contentName)) {
						ReporterLog.pass("Search functionality",
								"Search results are matching with the searched content");
					} else {
						ReporterLog.fail("Search functionality",
								"Search results are not matching with the searched content");
					}
				}
			} else {
				ReporterLog.fail("Search functionality", "Search results are not displayed");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	public  SearchPage verifyContentSearchFunctionality(String strEnterContent) {
		
		new DiscoverPage().clickSearch();		
		this.typeSearch(strEnterContent)
		.verifySearchResults()
		.clickOnSearchResult(strEnterContent);
		return new SearchPage();
	}

		
	public SearchPage typeSearch(String contentName) {
		try 
		{
			type(edtSearch, contentName, "Content name");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public DiscoverPage ClickBack() {
		try 
		{
			click(backFromSearchPage, "Back button");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}
	
	public SearchPage verifySearchResults() {
		try 
		{
			isElementDisplayed(searchResultList);	
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public SearchPage verifyTVChannelSearchResults() {
		try 
		{
			swipeUpOrBottom(true);
			isElementDisplayed(tvChannelSearchResultList);	
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public SearchPage verifyNoSearchResults() {
		try 
		{
			isElementDisplayed(noSearchResult);	
			verifyText(noSearchResult, "No search result found.");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public ContentDetailsPage clickOnSearchResult(String contentName) {
		try 
		{	
			List<WebElement> searchResults = driver.findElements(searchResultList); // tv.hooq.android:id/imgPoster
			List<WebElement> actualResults = searchResults.get(0).findElements(By.className("android.widget.FrameLayout"));
			actualResults.get(0).click();	
		//	Thread.sleep(4000);
		//	if(isElementPresentInDom(searchResultList)) {
		//		click(driver.findElement(By.xpath("//*[@resource-id='tv.hooq.android:id/matchedTitlesListView']/child::*[1]/*/*[1]")),"First result");
		//	}
		
			waitTillElementPresent_HardWait_Polling(txtContentTitle, GlobalConstant.WAIT_SHORT_5_SEC);
			String title = getText(txtContentTitle, "title"); 
			verifyTextContains(title, contentName);				
					
		} 
		catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public ChannelsPage clickOnTVChannelSearchResult() {
		try 
		{	
			List<WebElement> searchResults = driver.findElements(tvChannelSearchResultList); // tv.hooq.android:id/imgPoster
			List<WebElement> actualResults = searchResults.get(0).findElements(By.id("tv.hooq.android:id/cv_channel"));
			actualResults.get(0).click();				
					
		} 
		catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}

	public SearchPage verifyRecentSearchesLabel() {
		try 
		{
			isElementDisplayed(recentSearchesSection);	
			verifyText(lblRecentSearches, "Recent Searches");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}

	public SearchPage verifyContentInRecentSearches(String strContentName) {
		try 
		{
			isElementDisplayed(recentSearchesList);	
			String recentContentName = getTextOfAnElementInAListOfList(recentSearchesList, recentSearchesContentName, 0);
			verifyTextContains(recentContentName, strContentName);
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	
	public SearchPage verifySizeOfRecentSearchResultsBeforeClear() {
		try 
		{
			if(isNumOf_Elements_InAnElement_Greater_Than_Zero(recentSearchesList, recentSearchesContentName))
				ReporterLog.pass("Size of Search results", "Size of Search results is greater than 0");
			else
				ReporterLog.fail("Size of Search results", "Size of Search results is 0");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public SearchPage clearRecentSearches() {
		try 
		{
			isElementDisplayed(btnClearResentSearches);	
			click(btnClearResentSearches, "Clear");	
			isElementDisplayed(popUpToClearResentSearches);	
			verifyTextContains(popUpToClearResentSearches, "Are you sure you want to clear your Recent Searches?", "Popup title to clear Resent searches");
			click(btnConfirm, "Confirm");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public SearchPage verifySizeOfRecentSearchResultsAfterClear() {
		try 
		{
			if(isNumOf_Elements_Greater_Than_Zero(recentSearchesList))
				ReporterLog.fail("Size of Search results after clearing", "Search results are not cleared and the Size of Search results is greater than 0");
			else				
				ReporterLog.pass("Size of Search results after clearing", "Search results are cleared and the Size of Search results is 0");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
}

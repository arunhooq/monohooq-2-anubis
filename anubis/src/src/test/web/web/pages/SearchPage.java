package web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class SearchPage extends ActionEngine {
	
	//public static By edtSearch=By.cssSelector("[class^='mobile'] [class^='SearchInput'] input");
	public static By edtSearch=By.cssSelector("[class^='SearchInput'] input");
	public static By searchResults=By.cssSelector(".collectionRow a img");
	//public static By searchResults=By.xpath("//*[@id=\"mount\"]/div[1]/div/div[1]");
	
	public static By noSearchResultsImg = By.cssSelector("[src$='assets/img/img-search-no-results.png']");
    public static By noSearchResultsHeading = By.cssSelector("[class^='PageContentCenter__Title2Heading']");
    public int targetElement;
    

    
	public SearchPage verifySearchResults(String searchString) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");

		try {
			Thread.sleep(10000);
			ReporterLog.info("verifySearchResults", "Number of search Results: "+driver.findElements(searchResults).size());
			if (isNumOf_Elements_Greater_Than_Zero(searchResults)) {
				targetElement = verifyAttributeOfEachElementContainsText1(searchResults, "title", searchString);
				TestUtilities.logReportPass("Search Results displayed and result movies contain the search string");
			}
			else {
								
				isElementDisplayed(noSearchResultsImg);
				isElementDisplayed(noSearchResultsHeading);
				verifyTextContains(noSearchResultsHeading,searchString,"noSearchResultsHeading");
				
				TestUtilities.logReportPass("No Search Results displayed for the given search string");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return this;
	}

	
	public SearchPage typeSearch(String contentName) {
		try 
		{
			type(edtSearch, contentName, "Content name");
			Thread.sleep(5000);
			String currentURL = getCurrentUrl();
			System.out.println("currentURL after typing search string: "+currentURL);
			if(currentURL.contains("/search")) 
				openUrl(currentURL+"?q="+contentName);
			else
				throw new Exception();
			TestUtilities.logReportPass("Able to type in Search field");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}
	
	public ContentDetailsPage clickOnSearchResult(String searchString) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		
		List<WebElement> elements = driver.findElements(searchResults);
		ReporterLog.info("Click on Search Results", "Quantity of search results: "+elements.size());
		
		try {
			if(!isNumOf_Elements_Greater_Than_Zero(searchResults)) {
				throw new Exception("No search results returned (# Elements: 0)");
			}
		 }
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		
		targetElement = verifyAttributeOfEachElementContainsText1(searchResults, "title", searchString);
		
		//if exactMatch was not previously found, then select first element instead
		if (targetElement == -1) {
			ReporterLog.info("Click on Search Results", "As exact match not found, select the first element of the results");
			targetElement = 0;
		} else {
			ReporterLog.info("Click on Search Results", "Since Exact match found, select that  element of the results, being element #:"+targetElement);
		}
		
		try {
				ReporterLog.info("Click on Search Results", "Attempting to Click on Element #: "+targetElement);
				clickOnElementInAList(searchResults, targetElement);
				TestUtilities.logReportPass("Clicked on result #: "+targetElement+" of the Search Results");
				System.out.println("Clicked on result #: "+targetElement+" of the Search Results");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		return new ContentDetailsPage();
	}

	
}

package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

public class MoviesPage  extends ActionEngine {

	//Locators
	public static By btnMovies=By.id("MOVIES");
	public static By navBackBtn = By.name("navbar back btn");
	public static By tblMovies = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By txtMovieType = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/child::*[2]");
	public static By btnClose = By.id("btn close details");

	
	/***
	 * Function Name :- naviagtetoBackFromSettings Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MoviesPage navigateBack()
	{
		try {
			click(navBackBtn, "Back From History page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MoviesPage();
	}
	
	public MoviesPage clickMoviesTab()
	{
		try
		{
			explicitWaitForVisibilityOfElement(btnMovies, GlobalConstant.WAIT_MAXTIMEOUT_40_SEC).click();
			//click(btnMovies, "Movies tab");	
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MoviesPage();
	}	
	
	
	public ContentDetailsPage selectContentFromMovies()
	{
		try
		{
			waitTillElementPresent_HardWait_Polling(tblMovies, GlobalConstant.WAIT_SHORT_10_SEC);
			//WebElement eleTbl = driver.findElement(tblMovies);
			List<WebElement> eleCat = getElements(tblMovies);
			
			if(eleCat.size()>0) {
				eleCat.get(0).click();
			}else {
				Thread.sleep(10000);
				click(getElements(tblMovies).get(0),"Select Movie From List");
			}
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage selectNonRestrictedMovie() {
		
		List<WebElement> eleCat = driver.findElements(tblMovies);
		
		for(WebElement e : eleCat) {
			e.click();
			
			waitForVisibilityOfElement(txtMovieType, "Movie details type and duration");
			String txt = getText(txtMovieType, "Movie details type and duration");
			
			if(!txt.contains("R21")) {
				break;
			}else {
			click(btnClose, "Close button");
			}
		}
		
		return new ContentDetailsPage();
	}
	
	
}

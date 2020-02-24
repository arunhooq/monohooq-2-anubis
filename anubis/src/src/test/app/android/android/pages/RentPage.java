package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;


public class RentPage extends ActionEngine{
	
	public static By rentPageHeader = By.id("tv.hooq.android:id/toolbarTitle"); 
	public static By seeAll = By.id("tv.hooq.android:id/txt_see_all");	
	public static By seeAllContainer = By.id("tv.hooq.android:id/rvSeeAllContents");	
	public static By contents = By.id("tv.hooq.android:id/image_container");
	
	
	public ContentDetailsPage clickOnATVODContent() {
		try 
		{
			isElementDisplayed(rentPageHeader);
			click(seeAll, "See All");
			clickOnElementInListOfAList(seeAllContainer, contents, 1);			
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}

	
}

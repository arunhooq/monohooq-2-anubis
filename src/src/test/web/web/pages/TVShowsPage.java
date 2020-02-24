package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class TVShowsPage extends ActionEngine{
	
	public static By movieTitlefstTVShow=By.cssSelector("[class*='PortraitTitleCard__TitleCardContainer'] img");
	
	public ContentDetailsPage getContentDetailsPage() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {

			waitForElementClickable(movieTitlefstTVShow, "movieTitlefstTVShow");
			WebElement title = driver.findElement(movieTitlefstTVShow);
			String fstTitle = title.getAttribute("title");
			ReporterLog.pass("Verify Content Title on Detail page", fstTitle + " is displayed successfully");
			click(movieTitlefstTVShow, "Click on content");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}

}

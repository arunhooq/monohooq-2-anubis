package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class MoviesPage extends ActionEngine{
	public static By movieTitlefst=By.cssSelector("[class*='PortraitTitleCard__TitleCardContainer'] img");

	public ContentDetailsPage getContentDetailsPage() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			waitForElementClickable(movieTitlefst, "movieTitlefstTVShow");
			WebElement title = driver.findElement(movieTitlefst);
			String fstTitle = title.getAttribute("title");
			ReporterLog.pass("Verify Content Title on TV Shows page", fstTitle + " is dispalyed successfully");
			click(movieTitlefst, "Click on content");

			TestUtilities.logReportPass("");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}

}

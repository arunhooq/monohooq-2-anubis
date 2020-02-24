package web.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class NovaPage extends ActionEngine{
	public static By skipLink = By.cssSelector("[class*='navbarWeb__button-skip']");
	public static By getStarted =  By.cssSelector(".get-started-button");
	
	public NovaPage clickSkipLink() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			click(skipLink, "skipLink");
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPage();
	}

	
	public BasePage clickGetStartedButton() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			click(getStarted, "getStarted");
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new BasePage();
	}
}

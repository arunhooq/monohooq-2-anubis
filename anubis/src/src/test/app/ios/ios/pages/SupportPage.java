package ios.pages;

import java.time.LocalDateTime;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class SupportPage  extends ActionEngine {
	
	//locators
	public static By navBackBtn = By.name("navbar back btn");
	public static By buildNo = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[6]/XCUIElementTypeStaticText");

	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			
			click(navBackBtn, "Back From Support page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- getBuildNo Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public SupportPage getBuildNo()
	{
		try
		{
			if (ReadTestData.strBuildNo == null) 
			{
				ReadTestData.strBuildNo = getText(buildNo, "HOOQ Build No").replaceAll("\n", " - ");				
			} 
			else 
			{
				ReadTestData.strBuildNo = LocalDateTime.now().toString();
			}
			ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Build No : "+ ReadTestData.strBuildNo);
		}
		catch(Exception e){TestUtilities.logReportFailure(e);}
		return new SupportPage();
	}
	
			
}

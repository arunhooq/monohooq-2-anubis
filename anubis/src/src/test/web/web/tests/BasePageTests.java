package web.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import web.tests.TestConfiguration;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import web.pages.BasePage;

public class BasePageTests extends TestConfiguration{
	
	@Test(groups = { "Sanity_Visitor",
					 "performLinksChecking"})
	public void performLinksCheckingBasePage() {
		ReadTestData.fnAddTestRailScriptID(22371);
		try 
		{
			BasePage.performLinksCheckBasePage(ConfigDetails.strURL);
			ReporterLog.softAssert.assertAll();
        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	@Test(groups = { "Sanity_Active",
	 				"checkLocalisation"})
	public void checkLocalisation() {
		
		
		if (ConfigDetails.country.toUpperCase().equalsIgnoreCase("TH")) {
			ReadTestData.fnAddTestRailScriptID(22790);
			try 
			{
				BasePage.checkLocalisation();
				ReporterLog.softAssert.assertAll();
			} 
			catch (Exception e) {
			TestUtilities.logReportFailure(e);
			}
		} else {
			try {
				throw new SkipException("Skipping test as region != TH");
			} catch (Exception e) {
				
			}
		}
	}

}

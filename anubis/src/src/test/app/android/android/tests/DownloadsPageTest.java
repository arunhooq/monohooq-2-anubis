package android.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;
import android.tests.TestConfiguration;

public class DownloadsPageTest extends TestConfiguration {

	@Test(priority = 1,enabled=false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE, 
			GlobalConstant.GROUP_SANITY_LAPSED,
			GlobalConstant.GROUP_SANITY_Visitor,
			"Android_Verify_Download_FreeContent" })
	public  void Android_Verify_Download_FreeContent(){

		ReadTestData.fnAddTestRailScriptID(22674);
		try {
			if(!ConfigDetails.userType.equalsIgnoreCase("visitor")) {
			
			discoverPage.clickSearch()
			.typeSearch(ReadTestData.FREE_CONTENT)
			.verifySearchResults()
			.clickOnSearchResult(ReadTestData.FREE_CONTENT);
			
			downloadPage.validateDownloadIconPresent()
			.clickDownloadsTitle()
			.validateDownloadDialogueBox()
			.validateVideoQuality()
			.UnCheckDownloadPreferences()
			.clickDownloadButtonInDialogueBox()
			.validateDownloadTitleWhenDownloadStarted()
			.clickDownloadsTitle()
			.validateResumeFunctionality()
			.validateDeleteFunctionality();
			
		}else {
			
			throw new SkipException("Not applicable for Visitors Users Type.");
		}
		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"+e.getStackTrace().toString());
		}
	}





}

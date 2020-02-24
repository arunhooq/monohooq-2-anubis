package ios.tests;

import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class SearchPageTests extends TestConfiguration {
	/***
	 * Test Script Name :-HOOQ_IOS_MOBILE_ME Developed By :-Pankaj Kumar Date
	 * :-20_may-2019 Test Description :- Test Rail ID :-14082
	 */
	@Test(priority = 1, groups = {	GlobalConstant.GROUP_REGRESSION_ACTIVE, 
									GlobalConstant.GROUP_REGRESSION_LAPSED,
									GlobalConstant.GROUP_REGRESSION_Visitor,"verifySearch" })
	public void verifySearch()  {
		try {
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22506,22512,22518"));
			searchPage.validateSearch(ReadTestData.FREE_CONTENT);

		} catch (Exception e) {
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	/**@author mdafsarali
	 * @data 27 Aug 2019
	 * @category Test
	 * @implNote This to validate search result page if no result returned
	 */
	@Test(priority = 2, groups = { GlobalConstant.GROUP_SANITY_ACTIVE, 
								   GlobalConstant.GROUP_SANITY_LAPSED,
								   GlobalConstant.GROUP_SANITY_Visitor,"verifySearchForEmptyResult" })
	
	public void verifySearchForEmptyResult()  {
		try {
			
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22510,22516,22522"));
			searchPage.clicksearch()
				      .typesearch("##zyx")
				      .verifySearchForEmptyResult();
		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
	
	/**@author mdafsarali
	 * @data 27 Aug 2019
	 * @category Test
	 * @implNote This to validate search result page if no result returned
	 */
	@Test(priority = 3,enabled=true, groups = { GlobalConstant.GROUP_SANITY_ACTIVE, 
								   GlobalConstant.GROUP_SANITY_LAPSED,
								   GlobalConstant.GROUP_SANITY_Visitor,"verifySearchForChannels" })
	
	public void verifySearchForChannels()  {
		try {
			
			ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22508,22514,22520"));
			String strChannel = "Al Jazeera";
			searchPage.validateSearchForChannels(strChannel).verifyNavigationToLiveChannel();
				      
		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is");
		}
	}
}

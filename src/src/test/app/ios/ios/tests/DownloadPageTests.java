package ios.tests;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;
import api.pojo.DiscoveryFeed.DiscoverFeedController;


public class DownloadPageTests extends TestConfiguration {

	/**
	 *7.  Relaunch app then validate the downlaoded item in the Me Icon page and its playable 
	 * Then Relaunch app and delete downloads 
	 * 
	 * 2.Validate Download of TVShow Episodes - Active User
	 * 3. Validate Download of TVShow for Lapsed/Visitors 
	 * 4.Validate downloads of SVOD for -Lapsed/Visitors -Done
	 * 5.Validate downloads of TVOD for -Lapsed/Visitor user -Done
	 * 6.Download of r21 movies - Done
	 * 6.Navigate to Download from download icon and then From Me Icon
	 * 7.Edit and Delete Download from Me Icon
	 * 
	 */

	@Test(priority = 1, enabled = true, groups = { GlobalConstant.GROUP_SANITY_ACTIVE,
			"verifyDownloadButtonInContentDetailsPage" })

	public void verifyDownloadButtonInContentDetailsPage() {

		try {
			ReadTestData.fnAddTestRailScriptID(24718);
			searchPage.validateSearch(TestUtilities.getRandomItem(DiscoverFeedController.getSVODNonR21MovieList(false)))
					.verifyDownloadInContentDetailsPage();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}

	@Test(priority = 2, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE,
			"verifyPlaybackOfDownloadedSVOD" })

	public void verifyPlaybackOfDownloadedSVOD() {

		try {
			 ReadTestData.fnAddTestRailScriptID(24717);
			searchPage.validateSearch(TestUtilities.getRandomItem(DiscoverFeedController.getSVODNonR21MovieList(true)))
					.verifyPlayBackForDownloadedContent().verifySeekBar().verifyVideoTitleName();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}
	
	@Test(priority = 3, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_LAPSED,GlobalConstant.GROUP_REGRESSION_Visitor,
	"verifySVODDownloadForLapsedAndVisitors" })

	public void verifySVODDownloadForLapsedAndVisitors() {

		try {
			ReadTestData.fnAddTestRailScriptID(22435);
			searchPage.validateSearch(TestUtilities.getRandomItem(DiscoverFeedController.getSVODNonR21MovieList(true)))
			.verifySVODDownloadForRegisterAndVisitors();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}
	
	@Test(priority = 4, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_LAPSED,GlobalConstant.GROUP_REGRESSION_Visitor,
	"verifyTVODDownloadForLapsedAndVisitors" })

	public void verifyTVODDownloadForLapsedAndVisitors() {

		try {
			
			ReadTestData.getTestRailID(",29608,29609");
			searchPage.validateSearch(TestUtilities.getRandomItem(DiscoverFeedController.getTVODNonR21MovieList()))
			.verifyTVODDownloadForRegisterAndVisitors();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}
	
	@Test(priority = 5, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE,"verifyR21SVODDownload" })

	public void verifyR21SVODDownload() {

		try {
			ReadTestData.fnAddTestRailScriptID(29610);
			searchPage.validateSearch(TestUtilities.getRandomItem(DiscoverFeedController.getSVODR21MovieList()))
			.verifyDownloadForR21();

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}
	//TODO --
	@Test(priority = 5, enabled = false, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE,"verifyTVShowEpisodsDownload" })
	public void verifyTVShowEpisodsDownload() {

		try {
			ReadTestData.fnAddTestRailScriptID(29610);
			searchPage.validateSearch(TestUtilities.getRandomItem(DiscoverFeedController.getTVShowList()));
			

		} catch (Exception e) {
			e.printStackTrace();
			ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber() + " And Exception is"
							+ e.getMessage());
		}
	}



}

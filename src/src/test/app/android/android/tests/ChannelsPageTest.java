package android.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;


public class ChannelsPageTest extends TestConfiguration {

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_ACTIVE,
	"verify_ChannelsSection" })
	public void verify_ChannelsSection() {

		ReadTestData.fnAddTestRailScriptID(22648);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {
				discoverPage.clickChannelsTab().verify_ChannelsSectionDetails();

			} catch (Exception e) {
				TestUtilities.logReportFatal(e);
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for UserType :"+ConfigDetails.userType+" , Country : "+ConfigDetails.country);
	}

	@Test(priority = 1,enabled = false, groups = { 
			GlobalConstant.GROUP_SANITY_Visitor, 			
	"verify_LiveTVChannelPlayBackFromDiscoverPage" })
	public void verify_LiveTVChannelPlayBackFromDiscoverPage()  {

		ReadTestData.fnAddTestRailScriptID(29576);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {			
				discoverPage.playChannelFromLiveTVRow("Live TV").verifyPlaybackChannelName();					

			} catch (Exception e) {
				TestUtilities.logReportFatal(e);
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for UserType :"+ConfigDetails.userType+" , Country : "+ConfigDetails.country);
	}

	@Test(priority = 1,enabled = false, groups = { 
			GlobalConstant.GROUP_SANITY_LAPSED, 
	"verify_uCastChannelPlayBackFromDiscoverPage" })
	public void verify_uCastChannelPlayBackFromDiscoverPage()  {

		ReadTestData.fnAddTestRailScriptID(29577);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {
				discoverPage.playChannelFromLiveTVRow("uCast").verifyPlaybackChannelName();

			} catch (Exception e) {
				TestUtilities.logReportFatal(e);
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for UserType :"+ConfigDetails.userType+" , Country : "+ConfigDetails.country);
	}

	@Test(priority = 1,enabled = false, groups = { 
			GlobalConstant.GROUP_SANITY_ACTIVE, 
	"verify_PayTVChannelPlayBackFromDiscoverPage" })
	public void verify_PayTVChannelPlayBackFromDiscoverPage()  {

		ReadTestData.fnAddTestRailScriptID(29578);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {
				discoverPage.playChannelFromLiveTVRow("Pay TV").verifyPlaybackForPayTV();

			} catch (Exception e) {
				TestUtilities.logReportFatal(e);
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for UserType :"+ConfigDetails.userType+" , Country : "+ConfigDetails.country);
	}

	@Test(priority = 1,enabled = false, groups = { 
			GlobalConstant.GROUP_SANITY_ACTIVE, 
	"verify_ChannelChangeFromFullScreen" })
	public void verify_ChannelChangeFromFullScreen()  {

		ReadTestData.fnAddTestRailScriptID(29579);
		if(!ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
		{
			try {
				discoverPage.clickChannelsTab()
				.clickMaximizePlayer()
				.clickChannelsMenuInFullScreen()
				.changeChannelFromFullScreen()
				.clickCloseFullScreen();

			} catch (Exception e) {
				TestUtilities.logReportFatal(e);
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for UserType :"+ConfigDetails.userType+" , Country : "+ConfigDetails.country);
	}


}

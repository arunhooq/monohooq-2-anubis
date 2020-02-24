package sanctuary.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import sanctuary.modules.HeartbeatControllers;
import io.restassured.response.Response;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.SanctuaryConstants;

public class HeartbeatTests extends TestConfiguration{
	
	@Test(priority = 10, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_SubscribedUser_Pos_greaterthan3andlessthan50()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.SUBSCRIBED_USER,deviceid, email, 10, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_SubscribedUser_Pos_greaterthan3and50");
		}
		catch(Exception e) {

			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for subscribed user when greater than 3 and 50");
			
		}
	}
	
	@Test(priority = 2, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_SubscribedUser_Pos_greaterthan50andlessthan95()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.SUBSCRIBED_USER,deviceid, email, 55, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_SubscribedUser_Pos_greaterthan50andlessthan95");
		}
		catch(Exception e) {	
			ReporterLog.softAssert.assertAll();
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for subscribed user when greater than 50 and less than 95");			
		}
	}
	
	@Test(priority = 3, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_SubscribedUser_Pos_greaterthan95andlessthan100()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.SUBSCRIBED_USER,deviceid, email, 96, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_SubscribedUser_Pos_greaterthan95andlessthan100");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for subscribed user when greater than 95 and less than 100");			
		}
	}
	
	@Test(priority = 4, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_SubscribedUser_Pos_lessthan3()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.SUBSCRIBED_USER,deviceid, email, 2, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_SubscribedUser_Pos_lessthan3");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for subscribed user when less than 3");			
		}
	}
	
	@Test(priority = 5, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_RegisteredUser_Pos_greaterthan3andlessthan50()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.REGISTERED_USER,deviceid, email, 10, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_RegisteredUser_Pos_greaterthan3and50");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for registered user when greater than 3 and less than 50");			
		}
	}
	
	@Test(priority = 6, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_RegisteredUser_Pos_greaterthan50andlessthan95()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.REGISTERED_USER,deviceid, email, 55, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_RegisteredUser_Pos_greaterthan50andlessthan95");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for registered user when greater than 50 and less than 95");			
		}
	}
	
	@Test(priority = 7, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_RegisteredUser_Pos_greaterthan95andlessthan100()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.REGISTERED_USER,deviceid, email, 96, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_RegisteredUser_Pos_greaterthan95andlessthan100");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for Registered userwhen greater than 95 and less than 100");			
		}
	}
	
	@Test(priority = 8, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void HeartBeat_RegisteredUser_Pos_lessthan3()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.REGISTERED_USER,deviceid, email, 2, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_RegisteredUser_Pos_lessthan3");
		}
		catch(Exception e) {			
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for registered user when less than 3");			
		}
	}
	
	@Test(priority = 9, groups = {SanctuaryConstants.GROUP_SG,SanctuaryConstants.GROUP_ID,SanctuaryConstants.GROUP_PH,SanctuaryConstants.GROUP_TH})
	public void vistor_Heartbeat_greaterthan0()
	{
		try
		{
			Response visitor_res = HeartbeatControllers.hearbeatcheck_forvisitor(ApiConfigDetails.get_titles.get(SanctuaryConstants.CONTENTLEVEL10).toString(),deviceid,100,10);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(visitor_res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(SanctuaryConstants.ERROR_CODE)), 1030);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(SanctuaryConstants.ERROR_STATUS)), 404);
			ApiVerifications.verifyStringMatching(errorMap.get(SanctuaryConstants.ERROR_DETAIL).toString(), SanctuaryConstants.VISITOR_ERRORMESSAGE);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate_Visitor", "HeartBeat_Vistor>0");			
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for visitor when greater than 0");
		}
	}
	
	@Test(priority = 1, groups = {SanctuaryConstants.GROUP_SG})
	public void verify_Duration_PlatlistWatched()
	{
		try
		{
			HeartbeatControllers.heartbeat_Validate(SanctuaryConstants.SUBSCRIBED_USER,deviceid, email, 55, 100);
			ReporterLog.softAssert.assertAll();	
			ReporterLog.pass("HeartbeatValidate", "HeartBeat_SubscribedUser_Pos_greaterthan50andlessthan95");
		}
		catch(Exception e) {	
			ReporterLog.softAssert.assertAll();
			TestUtilities.logReportFailure(e,"heartbeat validate is not working as expected for subscribed user when greater than 50 and less than 95");			
		}
	}

}

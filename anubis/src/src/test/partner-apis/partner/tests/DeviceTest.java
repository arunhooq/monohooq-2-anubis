package partner.tests;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import io.restassured.response.Response;
import partner.modules.AuthorizationController;
import partner.modules.DeviceController;
import partner.utils.Constants;
import sanctuary.utils.ApiVerifications;

public class DeviceTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST})
	public void deleteDevice() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22796);
		try
		{
			// Login With Existing Email
			String auth = AuthorizationController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL);

			// Delete Device by Serial Number
			Response res = DeviceController.deleteDevices(auth, Constants.AUTOMATION);
			ApiVerifications.verifyRequestSucceed(res);
			
			ReporterLog.pass("DeleteDevice", "DeleteDevice executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"DeleteDevice failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void deleteAllDevices() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22797);
		try
		{
			// Login With Existing Email
			String auth = AuthorizationController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL);

			// Delete All Devices
			Response res = DeviceController.deleteDevices(auth);
			ApiVerifications.verifyRequestSucceed(res);
			
			ReporterLog.pass("DeleteAllDevices", "DeleteAllDevices executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"DeleteAllDevices failed");
		}
	}
}

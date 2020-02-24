package partner.tests;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import io.restassured.response.Response;
import partner.modules.HealthCheckController;
import partner.utils.Constants;
import sanctuary.utils.ReusableMethods;

public class HealthCheckTests extends TestConfiguration{

	@Test(groups= {Constants.HEALTH_CHECK_ID})
	public void healtCheckTest() throws ParseException
	{
		try {
			Response res = HealthCheckController.getHealthCheck();

			if(res.getStatusCode() == Constants.STATUS_CODE_200)
			{
				JSONObject json = ReusableMethods.rawtoJsonObject(res);
				JSONObject data = (JSONObject) json.get(Constants.DATA);
				String apiVersion = (String) data.get(Constants.VERSION);
				String apiRevision = (String) data.get(Constants.REVISION);

				ReporterLog.pass("HealtCheckTest", "This API is Healthy(API Version : " + apiVersion +
						"& API Revision : " + apiRevision);
			}
			else
			{
				Exception exception = null;
				TestUtilities.logReportFailure(exception, Constants.UN_HEALTHY_API);
			}
			ReporterLog.pass("HealtCheckTest", "HealtCheckTest executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"HealtCheckTest failed");
		}
	}
}

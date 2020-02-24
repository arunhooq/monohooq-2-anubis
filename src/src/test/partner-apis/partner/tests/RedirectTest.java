package partner.tests;

import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import io.restassured.response.Response;
import partner.modules.RedirectionController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class RedirectTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST})
	public void getRedirectPlayWithVisitorToken() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22800);
		try
		{
			// Check Get Redirect Play Endpoint
			Response res = RedirectionController.getRedirectPlayAutoLoginURL(ApiPartnerConfigDetails.titleMovieLvl10, Constants.EMPTY_STRING, 0, ApiPartnerConfigDetails.visitorUserToken);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2511);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.MISSING_REQUIRED_PARAMETER);

			ReporterLog.pass("getRedirectPlayWithVisitorToken", "getRedirectPlayWithVisitorToken executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"getRedirectPlayWithVisitorToken failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void getRedirectPlayWithoutTitleID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22801);
		try
		{
			// Check Get Redirect Play Endpoint
			Response res = RedirectionController.getRedirectPlayAutoLoginURL(Constants.EMPTY_STRING, Constants.EMPTY_STRING, 0, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_404);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1234);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_404);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.ROUTE_NOT_FOUND);

			ReporterLog.pass("getRedirectPlayWithoutTitleID", "getRedirectPlayWithoutTitleID executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"getRedirectPlayWithoutTitleID failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void getRedirectPlay() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22804);
		try
		{
			// Check Get Redirect Play Endpoint
			Response res = RedirectionController.getRedirectPlayAutoLoginURL(ApiPartnerConfigDetails.titleMovieLvl10, Constants.EMPTY_STRING, 0, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			String url = (String) data.get(Constants.URL);
			ApiVerifications.verifyStringStartWith(url,ApiPartnerConfigDetails.hooqBaseUrl + Constants.REDIRECT_URL);
			ApiVerifications.verifyStringEndWith(url,"&targetPath=/play/" + ApiPartnerConfigDetails.titleMovieLvl10 + "&status=success");

			ReporterLog.pass("getRedirectPlay", "getRedirectPlay executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"getRedirectPlay failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void getRedirectPlayWithNonExistingTitleID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22805);
		try
		{
			// Check Get Redirect Play Endpoint
			Response res = RedirectionController.getRedirectPlayAutoLoginURL(Constants.INVALID, Constants.EMPTY_STRING, 0, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			String url = (String) data.get(Constants.URL);
			ApiVerifications.verifyStringStartWith(url,ApiPartnerConfigDetails.hooqBaseUrl + Constants.REDIRECT_URL);
			ApiVerifications.verifyStringEndWith(url,"&targetPath=/play/" + Constants.INVALID + "&status=success");

			ReporterLog.pass("getRedirectPlayWithNonExistingTitleID", "getRedirectPlayWithNonExistingTitleID executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"getRedirectPlayWithNonExistingTitleID failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void getRedirectPlayWithReturnURL() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22802);
		try
		{
			// Check Get Redirect Play Endpoint
			Response res = RedirectionController.getRedirectPlayAutoLoginURL(ApiPartnerConfigDetails.titleMovieLvl10, "/rent", 0, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			String url = (String) data.get(Constants.URL);
			ApiVerifications.verifyStringStartWith(url,ApiPartnerConfigDetails.hooqBaseUrl + Constants.REDIRECT_URL);
			ApiVerifications.verifyStringEndWith(url,"&targetPath=/play/" + ApiPartnerConfigDetails.titleMovieLvl10 + "&status=success&returnUrl=/rent");

			ReporterLog.pass("getRedirectPlayWithReturnURL", "getRedirectPlayWithReturnURL executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"getRedirectPlayWithReturnURL failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST}, enabled=false)
	public void getRedirectPlayWithExpiry() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22803);
		try
		{
			// Check Get Redirect Play Endpoint
			Response res = RedirectionController.getRedirectPlayAutoLoginURL(ApiPartnerConfigDetails.titleMovieLvl10, "/rent", 1, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);

			// Get Redirect URL
//			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
//			JSONObject data =  (JSONObject) json.get(Constants.DATA);

		    Thread.sleep(1500);
		    // Access Redirect URL using HttpURLConnection
		    System.out.println("Need to add assertion for accessing the RedirectURL and Expiry URL");

			ReporterLog.pass("getRedirectPlayWithExpiry", "getRedirectPlayWithExpiry executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"getRedirectPlayWithExpiry failed");
		}
	}
}

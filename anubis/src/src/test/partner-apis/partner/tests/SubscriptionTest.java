package partner.tests;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import io.restassured.response.Response;
import partner.modules.SubscriptionController;
import partner.modules.UserController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class SubscriptionTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST}, priority = 1)
	public void activateSubscription() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22610,22856,22824"));

		try
		{
			// Create and SignIn User with Email
			String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
			String auth = UserController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, random_email);

			// Set auth value into ApiPartnerConfigDetails.auth
			ApiPartnerConfigDetails.auth = auth;

			// Check Update Subscription Endpoint
			Response res = SubscriptionController.updateSubscription(ApiPartnerConfigDetails.auth, ApiPartnerConfigDetails.skuSVOD, Constants.ACTIVATE);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data = (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyStringMatching(data.get(Constants.MESSAGE).toString(), Constants.SUCCESS_ACTIVATE_SUBSCRIPTION);
			ApiVerifications.verifyStringMatching(data.get(Constants.RESPONSE_CODE).toString(), Constants.ONE);

			// Check Update Subscription Endpoint without set Method and Same SKU
			HashMap<String, Object> payload = new HashMap<>();
			payload.put(Constants.SKU, ApiPartnerConfigDetails.skuSVOD);

			res = SubscriptionController.updateSubscriptionEndpoint(ApiPartnerConfigDetails.auth, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);
			Map<String, String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2356);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SKU_ALREADY_ACTIVATED);

			ReporterLog.pass("activateSubscription", "activateSubscription executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "activateSubscription failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void getSubscriptionWithNoPackage() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22612);

		try
		{
			// Check Get Subscription Status Endpoint
			Response res = SubscriptionController.getSubscriptionStatus(ApiPartnerConfigDetails.lapsedUserToken);

			// Verify Response Body
			ApiVerifications.verifyRequestSucceed(res);
			JSONArray data = (JSONArray) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyEmptyArray(data);

			ReporterLog.pass("getSubscriptionWithNoPackage", "getSubscriptionWithNoPackage executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "getSubscriptionWithNoPackage failed");
		}
	}

	@Test(groups = { Constants.SMOKE_TEST })
	public void getSubscriptionWithActivePackage() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(22613);

		try {
			// Check Get Subscription Status Endpoint
			Response res = SubscriptionController.getSubscriptionStatus(ApiPartnerConfigDetails.premiumUserToken);
			JSONArray data = (JSONArray) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject subscription = (JSONObject) data.get(0);
			String packageName = (String) subscription.get(Constants.SERVICE_ID);
			String packageStatus = (String) subscription.get(Constants.STATUS);

			// Verify Response Body
			ApiVerifications.verifyRequestSucceed(res);
			ApiVerifications.verifyStringMatching(packageName, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyStringMatching(packageStatus, Constants.ACTIVE);

			ReporterLog.pass("getSubscriptionWithActivePackage", "getSubscriptionWithActivePackage executed successfully");
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "getSubscriptionWithActivePackage failed");
		}
	}

	@Test(groups = { Constants.SMOKE_TEST })
	public void activateSubscriptionWithUndefinedSKU() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(22825);

		try {
			// Check Update Subscription Endpoint without set SKU
			HashMap<String, Object> payload = new HashMap<>();
			payload.put(Constants.METHOD, Constants.ACTIVATE);

			Response res = SubscriptionController.updateSubscriptionEndpoint(ApiPartnerConfigDetails.lapsedUserToken, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);
			Map<String, String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1001);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SKU_IS_REQUIRED);

			ReporterLog.pass("activateSubscriptionWithUndefinedSKU", "activateSubscriptionWithUndefinedSKU executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "activateSubscriptionWithUndefinedSKU failed");
		}
	}

	@Test(groups = { Constants.SMOKE_TEST })
	public void activateSubscriptionWithInvalidSKU() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(22826);

		try {
			// Check Update Subscription Endpoint with Invalid SKU
			HashMap<String, Object> payload = new HashMap<>();
			payload.put(Constants.SKU, Constants.INVALID);
			payload.put(Constants.METHOD, Constants.ACTIVATE);

			Response res = SubscriptionController.updateSubscriptionEndpoint(ApiPartnerConfigDetails.lapsedUserToken,payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);
			Map<String, String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2355);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_SKU);

			ReporterLog.pass("activateSubscriptionWithInvalidSKU", "activateSubscriptionWithInvalidSKU executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "activateSubscriptionWithInvalidSKU failed");
		}
	}

	@Test(groups = { Constants.SMOKE_TEST }, priority = 3)
	public void deactivateSubscription() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(ReadTestData.getTestRailID("22611,22829"));

		try {
			// Activate User Subscription
			SubscriptionController.succeedUpdateSubscription(ApiPartnerConfigDetails.auth, ApiPartnerConfigDetails.skuSVOD);

			// Check Deactivate Subscription
			Response res = SubscriptionController.updateSubscription(ApiPartnerConfigDetails.auth, ApiPartnerConfigDetails.skuSVOD, Constants.DEACTIVATE);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data = (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyStringMatching(data.get(Constants.MESSAGE).toString(), Constants.SUCCESS_DEACTIVATE_SUBSCRIPTION);
			ApiVerifications.verifyStringMatching(data.get(Constants.RESPONSE_CODE).toString(), Constants.ONE);

			// Check Deactivate Already Inactive Subscription
			res = SubscriptionController.updateSubscription(ApiPartnerConfigDetails.auth,ApiPartnerConfigDetails.skuSVOD, Constants.DEACTIVATE);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);
			Map<String, String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2358);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.USER_NOT_HAVE_THIS_SKU);

			ReporterLog.pass("deactivateSubscription", "deactivateSubscription executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "deactivateSubscription failed");
		}
	}

	@Test(groups = { Constants.SMOKE_TEST }, priority = 4)
	public void getSubscriptionWithInactivePackage() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(22614);

		try {
			// Check Get Subscription Status Endpoint
			Response res = SubscriptionController.getSubscriptionStatus(ApiPartnerConfigDetails.auth);
			JSONArray data = (JSONArray) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject subscription = (JSONObject) data.get(0);
			String packageName = (String) subscription.get(Constants.SERVICE_ID);
			String packageStatus = (String) subscription.get(Constants.STATUS);

			// Verify Response Body
			ApiVerifications.verifyRequestSucceed(res);
			ApiVerifications.verifyStringMatching(packageName, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyStringMatching(packageStatus, Constants.FINAL_BILL);

			ReporterLog.pass("getSubscriptionWithInactivePackage", "getSubscriptionWithInactivePackage executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "getSubscriptionWithInactivePackage failed");
		}
	}

	@Test(groups = { Constants.SMOKE_TEST }, priority = 2)
	public void removeUserSubscription() throws ParseException {
		ReadTestData.fnAddTestRailScriptID(22615);
		int endDate = 1554431216;

		try {
			// Remove User Subscription
			HashMap<String, Object> payload = new HashMap<>();
			payload.put(Constants.SKU, ApiPartnerConfigDetails.skuSVOD);
			payload.put(Constants.METHOD, Constants.DEACTIVATE);
			payload.put(Constants.END_DATE, endDate);

			Response res = SubscriptionController.updateSubscriptionEndpoint(ApiPartnerConfigDetails.auth, payload);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data = (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyStringMatching(data.get(Constants.MESSAGE).toString(), Constants.SUCCESS_DEACTIVATE_SUBSCRIPTION);
			ApiVerifications.verifyStringMatching(data.get(Constants.RESPONSE_CODE).toString(), Constants.ONE);

			// Check Get Subscription Status Endpoint
			res = SubscriptionController.getSubscriptionStatus(ApiPartnerConfigDetails.auth);

			// Verify Response Body
			ApiVerifications.verifyRequestSucceed(res);
			JSONArray subsicriptions = (JSONArray) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyEmptyArray(subsicriptions);

			ReporterLog.pass("removeUserSubscription", "removeUserSubscription executed successfully");
		}
			catch (Exception e)
		{
			TestUtilities.logReportFailure(e, "removeUserSubscription failed");
		}
	}
}

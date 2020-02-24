package partner.tests;

import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import partner.modules.PlayGoController;
import partner.modules.TVODController;
import partner.modules.UserController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import io.restassured.response.Response;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class TVODTest  extends TestConfiguration
{
	String invalid_title		= Constants.INVALID;
	String non_exsiting_title	= Constants.NON_EXISTING_TITLE;

	@Test(groups= {Constants.REGRESSION_TEST})
	public void getTVODStatusOfUnPurchasedTVOD() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(20444);

		try
		{
			// Check Get TVOD Status Endpoint
			Response res = TVODController.getTVODStatus(ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyiffalse((boolean) data.get(Constants.PURCHASED));
			ApiVerifications.verifyStringisNull((String) data.get(Constants.PURCHASED_EXPIRED_AT));
			ApiVerifications.verifyStringisNull((String) data.get(Constants.PLAYBACK_EXPIRED_AT));

			ReporterLog.pass("GetTVODStatusOfUnPurchasedTVOD", "GetTVODStatusOfUnPurchasedTVOD executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"GetTVODStatusOfUnPurchasedTVOD failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST}, priority = 2)
	public void getTVODStatusOfNewPurchasedTVOD() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(20445);

		try
		{
			// Check Get TVOD Status Endpoint
			Response res = TVODController.getTVODStatus(ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.auth);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyiftrue((boolean) data.get(Constants.PURCHASED));
			ApiVerifications.verifyNotNull((String) data.get(Constants.PURCHASED_EXPIRED_AT));
			ApiVerifications.verifyStringisNull((String) data.get(Constants.PLAYBACK_EXPIRED_AT));

			ReporterLog.pass("GetTVODStatusOfNewPurchasedTVOD", "GetTVODStatusOfNewPurchasedTVOD executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"GetTVODStatusOfNewPurchasedTVOD failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST}, enabled=false)
	public void getTVODStatusOfPlayedTVOD() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(20446);
		String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;

		try
		{
			// Create and SignIn User with Email
			String auth = UserController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, random_email);

			// Purchase TVOD
			TVODController.succeedPurchaseTVOD(ApiPartnerConfigDetails.titleTVOD, auth);

			// Play TVOD
			PlayGoController.succeedGetVODManifest(ApiPartnerConfigDetails.titleTVOD, auth);

			// Check Get TVOD Status Endpoint
			Response res = TVODController.getTVODStatus(ApiPartnerConfigDetails.titleTVOD, auth);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyiftrue((boolean) data.get(Constants.PURCHASED));
			ApiVerifications.verifyNotNull((String) data.get(Constants.PURCHASED_EXPIRED_AT));
			if(ApiPartnerConfigDetails.enableEligibility)
			{
				ApiVerifications.verifyNotNull((String) data.get(Constants.PLAYBACK_EXPIRED_AT));
			}
			else
			{
				ApiVerifications.verifyStringisNull((String) data.get(Constants.PLAYBACK_EXPIRED_AT));
			}

			ReporterLog.pass("GetTVODStatusOfPlayedTVOD", "GetTVODStatusOfPlayedTVOD executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"GetTVODStatusOfPlayedTVOD failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void getTVODStatusOfNonTVODTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(20448);

		try
		{
			// Check Get TVOD Status Endpoint
			Response res = TVODController.getTVODStatus(ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyiffalse((boolean) data.get(Constants.PURCHASED));
			ApiVerifications.verifyStringisNull((String) data.get(Constants.PURCHASED_EXPIRED_AT));
			ApiVerifications.verifyStringisNull((String) data.get(Constants.PLAYBACK_EXPIRED_AT));

			ReporterLog.pass("GetTVODStatusOfNonTVODTitle", "GetTVODStatusOfNonTVODTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"GetTVODStatusOfNonTVODTitle	failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void getTVODStatusOfInvalidTitleUUID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22116);

		try
		{
			// Check Get Invalid TVOD Status Endpoint
			Response res = TVODController.getTVODStatus(invalid_title, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_500);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_9999);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_500);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_UUID_ERROR_MESSAGE);

			ReporterLog.pass("GetTVODStatusOfInvalidTitleUUID", "GetTVODStatusOfInvalidTitleUUID executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"GetTVODStatusOfInvalidTitleUUID failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void purchaseInvalidTitleUUID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22113);

		try
		{
			// Purchase Invalid TVOD
			Response res = TVODController.purchaseTVOD(invalid_title, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_3003);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.IDENTIFIER_MUST_BE_UUID);

			ReporterLog.pass("PurchaseInvalidTitleUUID", "PurchaseInvalidTitleUUID executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"PurchaseInvalidTitleUUID failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void purchaseNonTVODTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22114);

		try
		{
			// Purchase TVOD
			Response res = TVODController.purchaseTVOD(ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_3002);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.TITLE_IS_NOT_TVOD);

			ReporterLog.pass("PurchaseNonTVODTitle", "PurchaseNonTVODTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"PurchaseNonTVODTitle failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void purchaseNonExistingTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22115);

		try
		{
			// Purchase TVOD
			Response res = TVODController.purchaseTVOD(non_exsiting_title, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_3003);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.TITLE_NOT_FOUND);

			ReporterLog.pass("PurchaseNonExistingTitle", "PurchaseNonExistingTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"PurchaseNonExistingTitle failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void purchaseWithNonTVODSKU() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22117);

		try
		{
			// Purchase TVOD
			Response res = TVODController.purchaseTVOD(ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.lapsedUserToken, ApiPartnerConfigDetails.skuSVOD);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV1415);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.NON_TVOD_SKU);

			ReporterLog.pass("PurchaseWithNonTVODSKU", "PurchaseWithNonTVODSKU executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"PurchaseWithNonTVODSKU failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST}, priority = 1)
	public void purchaseTVODTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22120);

		try
		{
			// Create and SignIn User with Email
			String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
			String auth = UserController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, random_email);

			// Set auth value into ApiPartnerConfigDetails.auth
			ApiPartnerConfigDetails.auth = auth;

			// Purchase TVOD
			Response res = TVODController.purchaseTVOD(ApiPartnerConfigDetails.titleTVOD, auth);
			ApiVerifications.verifyRequestSucceed(res);

			// Verify Response Body
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);

			ApiVerifications.verifyNotNull((String) data.get(Constants.TRANSACTION_ID).toString());
			ApiVerifications.verifyNotNull((String) data.get(Constants.ORDER_ID));
			ApiVerifications.verifyNotNull((String) data.get(Constants.PURCHASED_EXPIRED_AT).toString());
			ApiVerifications.verifyiftrue((boolean) data.get(Constants.SUCCESS));

			ReporterLog.pass("PurchaseTVODTitle", "PurchaseTVODTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"PurchaseTVODTitle failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST}, priority = 3)
	public void rePurchasePurchasedTVODTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22118);

		try
		{
			// Re-Purchase TVOD
			Response res = TVODController.purchaseTVOD(ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.auth);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_3001);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.PURCHASED_TVOD);

			ReporterLog.pass("RePurchasePurchasedTVODTitle", "RePurchasePurchasedTVODTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"RePurchasePurchasedTVODTitle failed");
		}
	}
}

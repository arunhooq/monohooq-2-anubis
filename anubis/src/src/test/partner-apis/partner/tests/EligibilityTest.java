package partner.tests;

import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import io.restassured.response.Response;
import partner.modules.AuthorizationController;
import partner.modules.EligibilityController;
import partner.modules.TVODController;
import partner.modules.UserController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

@Test(groups= {Constants.ELIGIBILITY_TEST})
public class EligibilityTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST})
	public void checkAdsEligibility() throws ParseException
	{
		// Check Partner Config for Enabled SSAI
		if(ApiPartnerConfigDetails.enableSSAI == null)
		{
			throw new SkipException("SSAI is disabled for this partner : (" + ApiPartnerConfigDetails.partner + ")");
		}

		ReadTestData.fnAddTestRailScriptID(22873);
		try
		{
			// Check Eligibility Endpoint for Non AVOD Title
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleSeriesLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiffalse((boolean) eligible.get(Constants.ADS));

			// Check Eligibility Endpoint for AVOD Title vs Lapsed User
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleAVOD, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.ADS));

			// Check Eligibility Endpoint for AVOD Title vs Premium User
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleAVOD, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiffalse((boolean) eligible.get(Constants.ADS));

			ReporterLog.pass("checkAdsEligibility", "checkAdsEligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkAdsEligibility failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void checkDownloadableEligibility() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22876);
		try
		{
			// Check Eligibility Endpoint for Non Downloadable Title
			Response res = EligibilityController.getEligibility(Constants.DOWNLOADS, ApiPartnerConfigDetails.titleNonDownloadable, ApiPartnerConfigDetails.lapsedUserToken);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2109);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.CONTENT_NOT_DOWNLOADABLE);

			// Check Eligibility Endpoint for Downloadable Title
			res = EligibilityController.getEligibility(Constants.DOWNLOADS, ApiPartnerConfigDetails.titleDownloadable, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			ReporterLog.pass("checkDownloadableEligibility", "checkDownloadableEligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkDownloadableEligibility failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST}, enabled=false)
	public void checkR21Eligibility() throws ParseException
	{
		// Because R21 Data Limitation, this test only can be run if R21 content is available
		if(ApiPartnerConfigDetails.titleR21.isEmpty())
		{
			throw new SkipException("Data R21 Title is not available for this region (" + ApiPartnerConfigDetails.country + ")");
		}

		ReadTestData.fnAddTestRailScriptID(22871);
		try
		{
			// Check Eligibility Endpoint
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleR21, ApiPartnerConfigDetails.premiumUserToken);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2106);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.NEED_PARENTAL_PIN);

			// Check Eligibility Endpoint
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleR21, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			ReporterLog.pass("checkR21Eligibility", "checkR21Eligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkR21Eligibility failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void checkEligibilityVisitorToken() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22878);
		try
		{
			// Check Eligibility Endpoint for Content Level 30
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.visitorUserToken);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2107);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SIGNIN_TO_WATCH);

			// Check Eligibility Endpoint for Content Level 10
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.visitorUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			ReporterLog.pass("checkEligibilityVisitorToken", "checkEligibilityVisitorToken executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkEligibilityVisitorToken failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void checkStreamableEligibility() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22875);
		try
		{
			// Check Eligibility Endpoint for Non Streamable Title
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleNonStreamable, ApiPartnerConfigDetails.lapsedUserToken);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2110);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.CONTENT_NOT_STREAMABLE);

			// Check Eligibility Endpoint for Streamable Title
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			ReporterLog.pass("checkStreamableEligibility", "checkStreamableEligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkStreamableEligibility failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	@Parameters({"country"})
	public void checkRegionEligibility(String country) throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22867);
		try
		{
			// Signin with Foreign IP
			String ip = ApiPartnerConfigDetails.getIPAddress("ID");
			if(country.toLowerCase().equals("id"))
				{
				ip = ApiPartnerConfigDetails.getIPAddress("SG");
				}
			String auth = AuthorizationController.generateAccessTokenWithIP(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL, ip);

			// Check Eligibility Endpoint
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, auth);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2100);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.REGION_ELIGIBILITY);

			ReporterLog.pass("checkRegionEligibility", "checkRegionEligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkRegionEligibility failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void checkSubscriptionEligibility() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22868);
		try
		{
			// Check Eligibility Endpoint for Content Level 10
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			// Check Eligibility Endpoint for Content Level 30 without Subscription
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.lapsedUserToken);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2101);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_TO_WATCH);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

			// Check Eligibility Endpoint for Content Level 30 with Subscription
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			// Check Eligibility Endpoint for TVOD Before Rent
			Response response = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.premiumUserToken);
			errorMap = ApiVerifications.getErrorcode(response);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2102);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.RENT_TO_WATCH);
			ApiVerifications.verifyStatusCode(response.getStatusCode(), Constants.STATUS_CODE_403);

			ReporterLog.pass("checkSubscriptionEligibility", "checkSubscriptionEligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkSubscriptionEligibility failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void checkVideoQualityEligibility() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22872);
		try
		{
			// Check Eligibility Endpoint for AVOD Title
			Response res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleAVOD, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			String streamQuality = (String) eligible.get(Constants.STREAM_QUALITY);
			ApiVerifications.verifyStringMatching(streamQuality,Constants.QHD);

			// Check Eligibility Endpoint for SVOD Title vs Premium User
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			streamQuality = (String) eligible.get(Constants.STREAM_QUALITY);
			ApiVerifications.verifyStringMatching(streamQuality,Constants.FHD);

			// Check Eligibility Endpoint for Disney Content
			res = EligibilityController.getEligibility(Constants.STREAMS, ApiPartnerConfigDetails.titleDisneyContent, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			streamQuality = (String) eligible.get(Constants.STREAM_QUALITY);
			ApiVerifications.verifyStringMatching(streamQuality,Constants.HD);

			ReporterLog.pass("checkVideoQualityEligibility", "checkVideoQualityEligibility executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkVideoQualityEligibility failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void checkEligibilityWithSubscriptionKey() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22977);
		try
		{
			// Check Eligibility Lapsed Subs vs Content Lvl 10
			Response res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken, Constants.LAPSED, Constants.ACTIVE);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			JSONObject eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			// Check Eligibility Lapsed Subs vs TVOD
			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.lapsedUserToken, Constants.LAPSED, Constants.ACTIVE);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2102);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.RENT_TO_WATCH);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

			// Check Eligibility Lapsed Subs vs Content Lvl 30
			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.lapsedUserToken, Constants.LAPSED, Constants.ACTIVE);
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2101);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_TO_WATCH);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

			// Check Eligibility Premium Subs vs Content Lvl 10
			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken, Constants.SVOD, Constants.ACTIVE);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			// Check Eligibility Premium Subs vs TVOD

			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleTVOD, ApiPartnerConfigDetails.premiumUserToken, Constants.SVOD, Constants.ACTIVE);
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2102);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.RENT_TO_WATCH);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

			// Check Eligibility Premium Subs vs Content Lvl 30
			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.lapsedUserToken, Constants.SVOD, Constants.ACTIVE);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			// Check Eligibility TVOD Subs vs Content Lvl 10
			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken, Constants.TVOD, Constants.ACTIVE, ApiPartnerConfigDetails.titleTVOD);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			String random_email = Long.toString(ReusableMethods.getTimeStamp()) + Constants.YOPMAIL_DOMAIN;
			// Create and SignIn User with Email
			String auth = UserController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, random_email);

			// Purchase TVOD
			TVODController.succeedPurchaseTVOD(ApiPartnerConfigDetails.titleTVOD, auth);

			// Check Eligibility TVOD Subs vs TVOD

			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleTVOD, auth, Constants.TVOD, Constants.ACTIVE, ApiPartnerConfigDetails.titleTVOD);
			ApiVerifications.verifyRequestSucceed(res);
			data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			eligible = (JSONObject) data.get(Constants.ELIGIBILE);
			ApiVerifications.verifyiftrue((boolean) eligible.get(Constants.PLAYBACK));

			// Check Eligibility TVOD Subs vs Content Lvl 30
			res = EligibilityController.getEligibilityWithSubscription(Constants.STREAMS, ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.lapsedUserToken, Constants.TVOD, Constants.ACTIVE, ApiPartnerConfigDetails.titleTVOD);
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_403);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2101);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_TO_WATCH);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

			ReporterLog.pass("checkEligibilityWithSubscriptionKey", "checkEligibilityWithSubscriptionKey executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"checkEligibilityWithSubscriptionKey failed");
		}
	}
}

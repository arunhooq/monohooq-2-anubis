package partner.tests;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import io.restassured.response.Response;
import partner.modules.PlayGoController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.TestData;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

@Test(groups= {Constants.PLAYGO_TEST})
public class PlayTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST})
	public void lapsedUserPlayMovieLvl10() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22844);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.SSAI_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.VMAP).toString(), Constants.VMAP_URL);
			
			ReporterLog.pass("lapsedUserPlayMovieLvl10", "lapsedUserPlayMovieLvl10 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlayMovieLvl10 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void lapsedUserPlayMovieLvl30() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22845);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.lapsedUserToken);

			if(ApiPartnerConfigDetails.enableEligibility)
			{
				ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

				// Verify Response Body
				Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

				ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2101);
				ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_TO_WATCH);
			}
			else
			{
				ApiVerifications.verifyRequestSucceed(res);
				JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
				ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.SSAI_PLAYBACK_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.VMAP).toString(), Constants.VMAP_URL);
			}

			ReporterLog.pass("lapsedUserPlayMovieLvl30", "lapsedUserPlayMovieLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlayMovieLvl30 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void lapsedUserPlaySeriesLvl10() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22846);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleSeriesLvl10, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			
			ReporterLog.pass("lapsedUserPlaySeriesLvl10", "lapsedUserPlaySeriesLvl10 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlaySeriesLvl10 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void lapsedUserPlaySeriesLvl30() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22847);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleSeriesLvl30, ApiPartnerConfigDetails.lapsedUserToken);

			if(ApiPartnerConfigDetails.enableEligibility)
			{
				ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

				// Verify Response Body
				Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
				ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2101);
				ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_TO_WATCH);
			}
			else
			{
				ApiVerifications.verifyRequestSucceed(res);
				JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
				ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			}

			ReporterLog.pass("lapsedUserPlaySeriesLvl30", "lapsedUserPlaySeriesLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlaySeriesLvl30 failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void lapsedUserPlayFreeTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22836);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getLiveTVManifest(ApiPartnerConfigDetails.titleFreeTV, ApiPartnerConfigDetails.lapsedUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res);		
			
			Map<String, Object> tvStreamDetails = TestData.getTVStreamDetail(ApiPartnerConfigDetails.titleFreeTV);
			ApiVerifications.verifyStringMatching(data.get(Constants.ID).toString(), tvStreamDetails.get(Constants.ID).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.LOGO_URL).toString(), tvStreamDetails.get(Constants.LOGO_URL).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.TITLE).toString(), tvStreamDetails.get(Constants.TITLE).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.DESCRIPTION).toString(), tvStreamDetails.get(Constants.DESCRIPTION).toString());
			ApiVerifications.verifyboolMatching((Boolean) data.get(Constants.IS_PREMIUM), (Boolean) tvStreamDetails.get(Constants.IS_PREMIUM));
			
			JSONArray streamsList = (JSONArray) data.get(Constants.STREAMS);
			JSONObject stream = (JSONObject) streamsList.get(0);
			ApiVerifications.verifyStringMatching(stream.get(Constants.LABEL).toString(), stream.get(Constants.LABEL).toString());
			ApiVerifications.verifyStringMatching(stream.get(Constants.STREAM_URL).toString(), stream.get(Constants.STREAM_URL).toString());
			
			ReporterLog.pass("lapsedUserPlayFreeTV", "lapsedUserPlayFreeTV executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlayFreeTV failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void lapsedUserPlayPayTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22848);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getLiveTVManifest(ApiPartnerConfigDetails.titlePayTV, ApiPartnerConfigDetails.lapsedUserToken);
			
			// Verify Response Body
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2101);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_TO_WATCH);
			
			ReporterLog.pass("lapsedUserPlayPayTV", "lapsedUserPlayPayTV executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlayPayTV failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void visitorPlayMovieLvl10() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22616);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.visitorUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.SSAI_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.VMAP).toString(), Constants.VMAP_URL);
			
			ReporterLog.pass("visitorPlayMovieLvl10", "visitorPlayMovieLvl10 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"visitorPlayMovieLvl10 failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void visitorPlayMovieLvl30() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22617);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.visitorUserToken);

			if(ApiPartnerConfigDetails.enableEligibility)
			{
				ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

				// Verify Response Body
				Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

				ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2107);
				ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SIGNIN_TO_WATCH);
			}
			else
			{
				ApiVerifications.verifyRequestSucceed(res);
				JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
				ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.SSAI_PLAYBACK_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.VMAP).toString(), Constants.VMAP_URL);
			}

			ReporterLog.pass("visitorPlayMovieLvl30", "visitorPlayMovieLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"visitorPlayMovieLvl30 failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void visitorPlaySeriesLvl10() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22618);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleSeriesLvl10, ApiPartnerConfigDetails.visitorUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			
			ReporterLog.pass("visitorPlaySeriesLvl10", "visitorPlaySeriesLvl10 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"visitorPlaySeriesLvl10 failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void visitorPlaySeriesLvl30() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22619);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleSeriesLvl30, ApiPartnerConfigDetails.visitorUserToken);

			if(ApiPartnerConfigDetails.enableEligibility)
			{
				ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

				// Verify Response Body
				Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

				ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_2107);
				ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SIGNIN_TO_WATCH);
			}
			else
			{
				ApiVerifications.verifyRequestSucceed(res);
				JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
				ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
				ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			}

			ReporterLog.pass("visitorPlaySeriesLvl30", "visitorPlaySeriesLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"visitorPlaySeriesLvl30 failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void visitorPlayFreeTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22620);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getLiveTVManifest(ApiPartnerConfigDetails.titleFreeTV, ApiPartnerConfigDetails.visitorUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res);		
			
			Map<String, Object> tvStreamDetails = TestData.getTVStreamDetail(ApiPartnerConfigDetails.titleFreeTV);
			ApiVerifications.verifyStringMatching(data.get(Constants.ID).toString(), tvStreamDetails.get(Constants.ID).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.LOGO_URL).toString(), tvStreamDetails.get(Constants.LOGO_URL).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.TITLE).toString(), tvStreamDetails.get(Constants.TITLE).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.DESCRIPTION).toString(), tvStreamDetails.get(Constants.DESCRIPTION).toString());
			ApiVerifications.verifyboolMatching((Boolean) data.get(Constants.IS_PREMIUM), (Boolean) tvStreamDetails.get(Constants.IS_PREMIUM));
			
			JSONArray streamsList = (JSONArray) data.get(Constants.STREAMS);
			JSONObject stream = (JSONObject) streamsList.get(0);
			ApiVerifications.verifyStringMatching(stream.get(Constants.LABEL).toString(), stream.get(Constants.LABEL).toString());
			ApiVerifications.verifyStringMatching(stream.get(Constants.STREAM_URL).toString(), stream.get(Constants.STREAM_URL).toString());
			
			ReporterLog.pass("visitorPlayFreeTV", "visitorPlayFreeTV executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"visitorPlayFreeTV failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void visitorPlayPayTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22621);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getLiveTVManifest(ApiPartnerConfigDetails.titlePayTV, ApiPartnerConfigDetails.visitorUserToken);

			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_403);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.PS_4002);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.SUBSCRIBE_FOR_PLAY_PAY_TV);

			ReporterLog.pass("visitorPlaySeriesLvl30", "visitorPlaySeriesLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"visitorPlaySeriesLvl30 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void premiumUserPlayMovieLvl10() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22835);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleMovieLvl10, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			
			ReporterLog.pass("premiumUserPlayMovieLvl10", "premiumUserPlayMovieLvl10 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"premiumUserPlayMovieLvl10 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void premiumUserPlayMovieLvl30() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22841);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleMovieLvl30, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);

			ReporterLog.pass("premiumUserPlayMovieLvl30", "premiumUserPlayMovieLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"lapsedUserPlayMovieLvl30 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void premiumUserPlaySeriesLvl10() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22842);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleSeriesLvl10, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			
			ReporterLog.pass("premiumUserPlaySeriesLvl10", "premiumUserPlaySeriesLvl10 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"premiumUserPlaySeriesLvl10 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void premiumUserPlaySeriesLvl30() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22843);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(ApiPartnerConfigDetails.titleSeriesLvl30, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res).get(Constants.DATA);
			ApiVerifications.verifyStringStartWith(data.get(Constants.CONTENT).toString(), Constants.CONTENT_PLAYBACK_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.LICENSE).toString(), Constants.LICENSE_URL);
			ApiVerifications.verifyStringStartWith(data.get(Constants.PRE_ROLL).toString(), Constants.PRE_ROLL_URL);
			
			ReporterLog.pass("premiumUserPlaySeriesLvl30", "premiumUserPlaySeriesLvl30 executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"premiumUserPlaySeriesLvl30 failed");
		}
	}
	
	@Test(groups= {Constants.SMOKE_TEST})
	public void premiumUserPlayFreeTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22849);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getLiveTVManifest(ApiPartnerConfigDetails.titleFreeTV, ApiPartnerConfigDetails.premiumUserToken);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject data = (JSONObject) ReusableMethods.rawtoJsonObject(res);		
			
			Map<String, Object> tvStreamDetails = TestData.getTVStreamDetail(ApiPartnerConfigDetails.titleFreeTV);
			ApiVerifications.verifyStringMatching(data.get(Constants.ID).toString(), tvStreamDetails.get(Constants.ID).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.LOGO_URL).toString(), tvStreamDetails.get(Constants.LOGO_URL).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.TITLE).toString(), tvStreamDetails.get(Constants.TITLE).toString());
			ApiVerifications.verifyStringMatching(data.get(Constants.DESCRIPTION).toString(), tvStreamDetails.get(Constants.DESCRIPTION).toString());
			ApiVerifications.verifyboolMatching((Boolean) data.get(Constants.IS_PREMIUM), (Boolean) tvStreamDetails.get(Constants.IS_PREMIUM));
			
			JSONArray streamsList = (JSONArray) data.get(Constants.STREAMS);
			JSONObject stream = (JSONObject) streamsList.get(0);
			ApiVerifications.verifyStringMatching(stream.get(Constants.LABEL).toString(), stream.get(Constants.LABEL).toString());
			ApiVerifications.verifyStringMatching(stream.get(Constants.STREAM_URL).toString(), stream.get(Constants.STREAM_URL).toString());
			
			ReporterLog.pass("premiumUserPlayFreeTV", "premiumUserPlayFreeTV executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"premiumUserPlayFreeTV failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void userPlayUnexistingLiveTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22798);
		try
		{
			// Check Get Playback TV Endpoint
			Response res = PlayGoController.getLiveTVManifest(Constants.NON_EXISTING_TITLE, ApiPartnerConfigDetails.visitorUserToken);

			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_404);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.PS_2000);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.CHANNEL_NOT_FOUND);

			ReporterLog.pass("userPlayUnexistingLiveTV", "userPlayUnexistingLiveTV executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"userPlayUnexistingLiveTV failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void userPlayInvalidLiveTV() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22799);
		try
		{
			// Check Get Playback TV Endpoint
			Response res = PlayGoController.getLiveTVManifest(Constants.INVALID, ApiPartnerConfigDetails.visitorUserToken);

			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.PS_1100);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_TV_CHANNEL);

			ReporterLog.pass("userPlayInvalidLiveTV", "userPlayInvalidLiveTV executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"userPlayInvalidLiveTV failed");
		}
	}
	
	@Test(groups= {Constants.REGRESSION_TEST})
	public void userPlayInvalidVODTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(29605);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(Constants.INVALID, ApiPartnerConfigDetails.visitorUserToken);
			
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.PS_1101);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_VOD_TITLE_ID);
			
			ReporterLog.pass("userPlayInvalidVODTitle", "userPlayInvalidVODTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"userPlayInvalidVODTitle failed");
		}
	}
	
	@Test(groups= {Constants.REGRESSION_TEST})
	public void userPlayUnexistingVODTitle() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(29604);
		try
		{
			// Check Get Playback VOD Endpoint
			Response res = PlayGoController.getVODManifest(Constants.NON_EXISTING_TITLE, ApiPartnerConfigDetails.visitorUserToken);
			
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_404);
			
			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);

			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.PS_2100);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.CATALOG_NOT_FOUND);
			
			ReporterLog.pass("userPlayUnexistingVODTitle", "userPlayUnexistingVODTitle executed successfully");
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e,"userPlayUnexistingVODTitle failed");
		}
	}
}

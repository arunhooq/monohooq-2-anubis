package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.json.simple.JSONObject;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class PlayGoController
{
	private static HashMap<String, Object> setHeader(String authorization)
	{
		HashMap<String, Object> header = new HashMap<>();

		try
		{
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(authorization.replaceFirst(Constants.BEARER, ""));

			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);
			header.put(Constants.X_DEVICE_ID, jwt.get(Constants.SERIAL_NUMBER).toString());
			header.put(Constants.X_DEVICE_MODEL, jwt.get(Constants.DEVICE_MODEL_NUMBER).toString());
			header.put(Constants.X_DEVICE_OS, Constants.DEVICE_OS);
			header.put(Constants.X_DEVICE_OS_VERSION, Constants.DEVICE_OS_VERSION);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return header;
	}

	public static Response getVODManifest(String titleID, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.PLAY + titleID;

			res=
					given().
					headers(setHeader(authorization)).
					when().
					get().
					then().extract().response();
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}

	public static void succeedGetVODManifest(String titleID, String authorization)
	{

		try
		{
			if(ApiPartnerConfigDetails.enableEligibility)
			{
				EligibilityController.succeedGetStreamEligibility(titleID, authorization);
			}

			Response res = getVODManifest(titleID, authorization);
			ApiVerifications.verifyRequestSucceed(res);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static Response getLiveTVManifest(String channelID, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.LIVE_TV + channelID;

			res=
					given().
					headers(setHeader(authorization)).
					when().
					get().
					then().extract().response();
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}
}

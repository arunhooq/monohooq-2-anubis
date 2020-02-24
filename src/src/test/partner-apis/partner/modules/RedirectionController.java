package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;

public class RedirectionController
{
	public static Response getRedirectAutoLoginURL(String targetPath, String returnUrl, String expiry, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.REDIRECT_AUTO_LOGIN;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			res=
				given().
				headers(header).
				queryParam(Constants.TARGET_PATH, targetPath).
				queryParam(Constants.RETURN_URL, returnUrl).
				queryParam(Constants.EXPIRY, expiry).
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

	public static Response getRedirectPlayAutoLoginURL(String titleID, String returnUrl, int expiry, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.REDIRECT_PLAYBACK + titleID;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			res=
				given().
				headers(header).
				queryParam(Constants.RETURN_URL, returnUrl).
				queryParam(Constants.EXPIRY, expiry).
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

	public static Response getRedirectNonLoginURL(String path, String titleID, String returnUrl, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.REDIRECT + path + "/" + titleID;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			res=
				given().
				headers(header).
				queryParam(Constants.RETURN_URL, returnUrl).
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

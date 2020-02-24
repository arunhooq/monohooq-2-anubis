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

public class SanctuaryController
{
	private static Response activateVisitor(String devicesignature)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.VISITOR_ACTIVATE;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.DEVICE_SIGNATURE,devicesignature);

			res=
					given().
					headers(header).
					when().
					post().
					then().extract().response();
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}

	public static String getVisitorToken(String devicesignature)
	{
		String authorization = "";

		try
		{
			Response res = activateVisitor(devicesignature);

			// Verify Response Body
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data = (JSONObject) json.get(Constants.DATA);
			String accessToken = (String) data.get(Constants.ACCESS_TOKEN);
			authorization = Constants.BEARER + accessToken;
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return authorization;
	}
}

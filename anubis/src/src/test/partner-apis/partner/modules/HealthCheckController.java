package partner.modules;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;

public class HealthCheckController
{
	public static Response getHealthCheck()
	{
		Response res = null;
		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER_HEALTH_CHECK;

			HashMap<String, Object>  header = new HashMap<>();

			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);

			res=
					given().
					headers(header).
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

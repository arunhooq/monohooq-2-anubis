package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;

public class PartnerConfigController
{
	public static Response invalidatePartnerConfigCache()
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.INVALIDATE_PARTNER_CONFIG;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

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

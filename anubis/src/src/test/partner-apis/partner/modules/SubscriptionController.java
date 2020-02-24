package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;
import sanctuary.utils.ApiVerifications;

public class SubscriptionController
{
	public static Response getSubscriptionStatus(String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.SUBSCRIPTION_STATUS;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
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

	public static Response updateSubscription(String authorization, String sku, String status)
	{
		Response res = null;

		try
		{
			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.SKU, sku);
			payload.put(Constants.METHOD, status);

			res = updateSubscriptionEndpoint(authorization, payload);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}

	public static Response updateSubscriptionEndpoint(String authorization, HashMap<String, Object> payload)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.UPDATE_SUBSCRIPTION;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			res=
				given().
				headers(header).
				body(payload).
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

	public static void succeedUpdateSubscription(String authorization, String sku)
	{
		try
		{
			Response res = updateSubscription(authorization, sku, Constants.ACTIVATE);
			ApiVerifications.verifyRequestSucceed(res);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}
}

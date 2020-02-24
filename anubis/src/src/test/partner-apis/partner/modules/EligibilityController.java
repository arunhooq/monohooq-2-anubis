package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class EligibilityController
{
	private static HashMap<String, String> setEligibilityHeader(String authorization)
	{
		HashMap<String, String>  header = new HashMap<>();

		try
		{
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(authorization.replaceFirst(Constants.BEARER, ""));
			String loginType = jwt.get(Constants.LOGIN_TYPE).toString();

			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);
			header.put(Constants.X_LOC,jwt.get(Constants.LOC).toString());
			header.put(Constants.X_LOGIN_TYPE,loginType);
			if(!loginType.equals(Constants.VISITOR))
			{
				header.put(Constants.X_CID,jwt.get(Constants.CID).toString());
				header.put(Constants.X_SID,jwt.get(Constants.SID).toString());
			}
			header.put(Constants.X_DEVICE_ID,jwt.get(Constants.SERIAL_NUMBER).toString());
			header.put(Constants.X_CONSUMER_USERNAME,ApiPartnerConfigDetails.partner.toLowerCase() + ":"
			+ ApiPartnerConfigDetails.partnerConfig.get(Constants.APPLICATION_NAME));

			if ((ApiPartnerConfigDetails.eligibilityService).toLowerCase().equals("mjolnir")) {
				header.put(Constants.HOST, "mjolnir-" + ApiPartnerConfigDetails.apiEnv + ".hooq.vpc");
			}
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return header;
	}

	private static List<Object> setSubscription(String skuType, String status, String... tvodTitle)
	{
		List<Object> subscriptionsList = new ArrayList<>();
		String sku = skuType.toLowerCase();
		try
		{
			// Set skuType as 'Lapsed' for return Empty List
			if (sku.equals("svod") || sku.equals("tvod"))
			{
				HashMap<String, Object>  subscriptionObject = new HashMap<>();
				switch (sku)
				{
				case "svod":
					subscriptionObject.put(Constants.SERVICE_ID, ApiPartnerConfigDetails.skuSVOD);
					break;
				case "tvod":
					subscriptionObject.put(Constants.SERVICE_ID, ApiPartnerConfigDetails.skuTVOD);

					HashMap<String, String>  tvodObject = new HashMap<>();
					tvodObject.put(Constants.ASSET_ID, tvodTitle[0]);

					List<Object> vodItemsList = new ArrayList<>();
					vodItemsList.add(tvodObject);

					subscriptionObject.put(Constants.VOD_ITEMS, vodItemsList);
					break;
				}
				// Valid status are Active, Final Bill and Add
				subscriptionObject.put(Constants.STATUS, status);

				// Store Subscription on List
				subscriptionsList.add(subscriptionObject);
			}
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return subscriptionsList;
	}

	private static void getEligibilityEndpoint(String eligibilityService)
	{
		try
		{
			if (eligibilityService.toLowerCase().equals("ums")) {
				RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
				RestAssured.basePath = Resources.ELIGIBILITY;
			} else if (eligibilityService.toLowerCase().equals("mjolnir")) {
				RestAssured.baseURI  = ApiPartnerConfigDetails.albBaseUrl;
				RestAssured.basePath = Resources.MJOLNIR;
			}
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static Response getEligibility(String action, String titleID, String authorization)
	{
		Response res = null;

		try
		{
			HashMap<String, Object> content = DiscoverController.titleDetailsObject(titleID);

			getEligibilityEndpoint(ApiPartnerConfigDetails.eligibilityService);

			HashMap<String, String> header = setEligibilityHeader(authorization);

			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACTION, action);
			data.put(Constants.CONTENT, content);

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);

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

	public static Response getEligibilityWithSubscription(String action, String titleID, String authorization, String skuType, String status, String... tvodTitle)
	{
		Response res = null;

		try
		{
			HashMap<String, Object> content = DiscoverController.titleDetailsObject(titleID);

			getEligibilityEndpoint(ApiPartnerConfigDetails.eligibilityService);

			HashMap<String, String> header = setEligibilityHeader(authorization);

			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACTION, action);
			data.put(Constants.CONTENT, content);
			// Set skuType as 'Lapsed' for return Empty List
			if (tvodTitle.length > 0)
			{
				data.put(Constants.SUBSCRIPTIONS, setSubscription(skuType, status, tvodTitle));
			} else data.put(Constants.SUBSCRIPTIONS, setSubscription(skuType, status));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);

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

	public static void succeedGetStreamEligibility(String titleID, String authorization)
	{
		try
		{
			Response res = getEligibility(Constants.STREAMS, titleID, authorization);
			ApiVerifications.verifyRequestSucceed(res);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}
}

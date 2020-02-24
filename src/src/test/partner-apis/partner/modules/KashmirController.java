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
import sanctuary.utils.ReusableMethods;

public class KashmirController
{
	private static Response getPartnerConfig(String partner)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.albBaseUrl;
			RestAssured.basePath = Resources.KASHMIR;
						
			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.HOST, "kashmir-" + ApiPartnerConfigDetails.apiEnv + ".hooq.vpc");
			
			res=
				given().
				headers(header).
				queryParam(Constants.PARTNER_ID, partner).
				queryParam(Constants.APP_ID, ApiPartnerConfigDetails.appID).
				queryParam(Constants.PARTNER_FIELDS, Constants.PARTNER_FIELDS_DATA).
				queryParam(Constants.APP_FIELDS, Constants.APP_FIELDS_DATA).
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

	public static HashMap<String, Object> generatePartnerConfigData(String partner)
	{
		HashMap<String, Object>  partnerConfig = new HashMap<>();

		try
		{
			Response res = getPartnerConfig(partner);
			JSONObject jsonObject = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject dataObject =  (JSONObject) jsonObject.get(Constants.DATA);
			JSONObject partnerObject =  (JSONObject) dataObject.get(Constants.PARTNER);
			JSONObject hmacKeyObject =  (JSONObject) partnerObject.get(Constants.HMAC_KEYS);
			JSONObject eligibilityObject =  (JSONObject) partnerObject.get(Constants.ELIGIBILITY);
			JSONObject appObject =  (JSONObject) dataObject.get(Constants.APPLICATION);

			String hmac = hmacKeyObject.toJSONString().substring(2, 40).replace("\":\"", "|");

			partnerConfig.put(Constants.CHANNEL_PARTNER_ID, partnerObject.get(Constants.CHANNEL_PARTNER_ID));
			partnerConfig.put(Constants.ENABLE_WEBHOOK, partnerObject.get(Constants.ENABLE_WEBHOOK));
			partnerConfig.put(Constants.HMAC_KEYS, hmac);
			partnerConfig.put(Constants.ELIGIBILITY, eligibilityObject.get(Constants.ENABLE));
			partnerConfig.put(Constants.SSAI, partnerObject.get(Constants.SSAI));
			partnerConfig.put(Constants.APPLICATION_NAME, appObject.get(Constants.APPLICATION_NAME));
			partnerConfig.put(Constants.APPLICATION_SECRET, appObject.get(Constants.APPLICATION_SECRET));
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return partnerConfig;
	}
}

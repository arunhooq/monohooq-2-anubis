package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.LinkedHashMap;
import com.automation.testengine.TestUtilities;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;
import sanctuary.utils.ApiVerifications;

public class TVODController
{

	public static Response getTVODStatus(String titleID, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.TVOD_STATUS + titleID;

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

	public static Response purchaseTVOD(String titleID, String authorization, String... d)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.PURCHASE_TVOD;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			LinkedHashMap<String, Object>  transactionDetail = new LinkedHashMap<>();
			transactionDetail.put(Constants.AMOUNT, Constants.TVOD_AMOUNT);
			transactionDetail.put(Constants.TRANSACTION_ID, Constants.TVOD_TRANSACTION_ID);
			transactionDetail.put(Constants.TRANSACTION_MESSAGE, Constants.SUCCESS);

			LinkedHashMap<Object, Object>  data = new LinkedHashMap<>();

			if (d.length > 0) {
				data.put(Constants.SKU, d[0]);
			}
			else
			{
				data.put(Constants.SKU, ApiPartnerConfigDetails.skuTVOD);
			}

			data.put(Constants.TITLE_ID, titleID);
			data.put(Constants.TRANSACTION_DETAIL, transactionDetail);

			// Convert LinkedHashMap into JSON using GSON
			Gson gson = new Gson();
			String hmac = gson.toJson(data, LinkedHashMap.class);

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(hmac));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

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

	public static void succeedPurchaseTVOD(String titleID, String authorization, String... d)
	{
		Response res = null;

		try
		{
			if (d.length > 0) {
				res = purchaseTVOD(titleID, authorization, d);
			}
			else
			{
				res = purchaseTVOD(titleID, authorization);
			}

			ApiVerifications.verifyRequestSucceed(res);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}
}

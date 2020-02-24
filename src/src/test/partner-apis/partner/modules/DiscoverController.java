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

public class DiscoverController
{
	public static Response getTitleDetail(String titleID)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.discoverBaseUrl;
			RestAssured.basePath = Resources.DISCOVER_TITLE_DETAILS + titleID;

			res=
				given().
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

	public static HashMap<String, Object> titleDetailsObject(String titleID)
	{
		HashMap<String, Object> titleDetails = new HashMap<>();

		try
		{
			Response res = DiscoverController.getTitleDetail(titleID);
			ApiVerifications.verifyRequestSucceed(res);

			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);

			titleDetails.put(Constants.ID, data.get(Constants.ID));
			titleDetails.put(Constants.AVAILABILITY, data.get(Constants.AVAILABILITY));
			titleDetails.put(Constants.STREAMABLE, data.get(Constants.STREAMABLE));
			titleDetails.put(Constants.REGION, data.get(Constants.REGION));
			titleDetails.put(Constants.DOWNLOADABLE, data.get(Constants.DOWNLOADABLE));
			titleDetails.put(Constants.PILOT, data.get(Constants.PILOT));
			titleDetails.put(Constants.AVOD, data.get(Constants.AVOD));
			titleDetails.put(Constants.LICENSOR_ID, data.get(Constants.LICENSOR_ID));
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return titleDetails;
	}
}

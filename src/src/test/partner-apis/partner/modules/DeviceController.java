package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;

public class DeviceController
{
	public static Response deleteDevices(String authorization, String... d)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.DELETE_DEVICES;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (d.length > 0) {
				HashMap<String, Object>  device = new HashMap<>();
				device.put(Constants.SERIAL_NUMBER, d[0]);

				data.put(Constants.DEVICE, device);
			}

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);

			res=
				given().
				headers(header).
				body(payload).
				when().
				delete().
				then().extract().response();
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}
}

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

public class UserController
{
	private static HashMap<String, Object> enableWithSiginIn(boolean enabled, String value)
	{
		HashMap<String, Object> withSignIn = new HashMap<>();

		try
		{
			HashMap<String, Object>  device = new HashMap<>();
			device.put(Constants.SERIAL_NUMBER, Constants.AUTOMATION);
			device.put(Constants.NAME, Constants.AUTOMATION);
			device.put(Constants.TYPE, Constants.DEFAULT);
			device.put(Constants.DEVICE_MODEL_NUMBER, Constants.DEFAULT);

			withSignIn.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(value));
			withSignIn.put(Constants.ENABLED,enabled);
			withSignIn.put(Constants.DEVICE, device);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return withSignIn;
	}

	private static HashMap<String, Object> enableWithActivate(String sku)
	{
		HashMap<String, Object> withActivate = new HashMap<>();

		try
		{
			HashMap<String, Object>  activation = new HashMap<>();
			activation.put(Constants.SKU, sku);

			withActivate.put(Constants.ENABLED,true);
			withActivate.put(Constants.DEVICE, activation);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return withActivate;
	}

	public static Response createUser(String type, String value)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (Constants.ACCOUNT_TYPE_LIST.contains(type))
			{
				data.put(type,value);
			}
			else
			{
				Exception exception = null;
				TestUtilities.logReportFailure(exception, Constants.INPUT_VALID_TYPE);
			}

			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.COUNTRY, ApiPartnerConfigDetails.country);

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.WITH_SIGNIN, enableWithSiginIn(false, value));

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

	public static Response createAndSignInUser(String type, String value)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (Constants.ACCOUNT_TYPE_LIST.contains(type))
			{
				data.put(type,value);
			}
			else
			{
				Exception exception = null;
				TestUtilities.logReportFailure(exception, Constants.INPUT_VALID_TYPE);
			}

			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.COUNTRY, ApiPartnerConfigDetails.country);

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.WITH_SIGNIN, enableWithSiginIn(true, value));

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

	public static String generateAccessToken(String type, String value)
	{
		String authorization = null;

		try
		{
			Response res = createAndSignInUser(type, value);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data = (JSONObject) json.get(Constants.DATA);
			JSONObject session = (JSONObject) data.get(Constants.SESSION);
			String accessToken = (String) session.get(Constants.ACCESS_TOKEN);
			authorization = Constants.BEARER + accessToken;
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return authorization;
	}

	public static Response createUserWithActiveSubscription(String type, String value, String sku)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (Constants.ACCOUNT_TYPE_LIST.contains(type))
			{
				data.put(type,value);
			}
			else
			{
				Exception exception = null;
				TestUtilities.logReportFailure(exception, Constants.INPUT_VALID_TYPE);
			}

			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.COUNTRY, ApiPartnerConfigDetails.country);

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.WITH_SIGNIN, enableWithSiginIn(false, value));
			meta.put(Constants.WITH_ACTIVATE, enableWithActivate(sku));

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

	public static Response createAndSignInUserWithActiveSubscription(String type, String value, String sku)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (Constants.ACCOUNT_TYPE_LIST.contains(type))
			{
				data.put(type,value);
			}
			else
			{
				Exception exception = null;
				TestUtilities.logReportFailure(exception, Constants.INPUT_VALID_TYPE);
			}

			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.COUNTRY, ApiPartnerConfigDetails.country);

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.WITH_SIGNIN, enableWithSiginIn(true, value));
			meta.put(Constants.WITH_ACTIVATE, enableWithActivate(sku));

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

	public static Response getUserDetails(String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

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

	public static Response updateUserData(HashMap<String, Object> object, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (object.containsKey(Constants.ACCOUNT_TYPE_EMAIL))
			{
				data.put(Constants.ACCOUNT_TYPE_EMAIL,object.get(Constants.ACCOUNT_TYPE_EMAIL));
			}

			if (object.containsKey(Constants.ACCOUNT_TYPE_PHONENUMBER))
			{
				data.put(Constants.ACCOUNT_TYPE_PHONENUMBER,object.get(Constants.ACCOUNT_TYPE_PHONENUMBER));
			}

			if (object.containsKey(Constants.PASSWORD))
			{
				data.put(Constants.PASSWORD,object.get(Constants.PASSWORD));
			}

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);

			res=
				given().
				headers(header).
				body(payload).
				when().
				patch().
				then().extract().response();
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}

	public static Response updateUserEmail(HashMap<String, Object> object, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.USER;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.UPDATE_EMAIL_ONLY,true);
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();

			if (object.containsKey(Constants.ACCOUNT_TYPE_EMAIL))
			{
				data.put(Constants.ACCOUNT_TYPE_EMAIL,object.get(Constants.ACCOUNT_TYPE_EMAIL));
			}

			if (object.containsKey(Constants.ACCOUNT_TYPE_PHONENUMBER))
			{
				data.put(Constants.ACCOUNT_TYPE_PHONENUMBER,object.get(Constants.ACCOUNT_TYPE_PHONENUMBER));
			}

			if (object.containsKey(Constants.PASSWORD))
			{
				data.put(Constants.PASSWORD,object.get(Constants.PASSWORD));
			}

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);

			res=
				given().
				headers(header).
				body(payload).
				when().
				patch().
				then().extract().response();
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}
}

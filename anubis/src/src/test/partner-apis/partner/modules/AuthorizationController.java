package partner.modules;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import com.automation.testengine.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import partner.utils.Resources;
import partner.utils.TestData;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class AuthorizationController
{
	public static HashMap<String, String> setDeviceObject()
	{
		HashMap<String, String>  device = new HashMap<>();

		try
		{
			device.put(Constants.SERIAL_NUMBER, Constants.AUTOMATION);
			device.put(Constants.NAME, Constants.AUTOMATION);
			device.put(Constants.TYPE, Constants.DEFAULT);
			device.put(Constants.DEVICE_MODEL_NUMBER, Constants.DEFAULT);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return device;
	}

	public static HashMap<String, String> setStartSessionHeader()
	{
		HashMap<String, String>  header = new HashMap<>();

		try
		{
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return header;
	}

	public static Response startSessionEndpoint(HashMap<String, String> header, HashMap<String, Object>  payload)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.SIGNIN;

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

	public static Response startSession(String type, String value, String... d)
	{
		Response res = null;

		try
		{
			HashMap<String, String> header = setStartSessionHeader();
			HashMap<String, String>  device = setDeviceObject();

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

			// Update IP_ADDRESS if Set
			if (d.length > 0) {
				data.put(Constants.IP_ADDRESS, d[0]);
			}

			data.put(Constants.DEVICE, device);

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(value));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			res = startSessionEndpoint(header, payload);
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return res;
	}

	public static Response stopSession(String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.SIGNOUT;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

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

	public static Response getAuthCode(String tokenExpiry, String authorization)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.AUTHCODE;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.AUTHORIZATION,authorization);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  device = new HashMap<>();
			device.put(Constants.SERIAL_NUMBER, Constants.AUTOMATION);
			device.put(Constants.NAME, Constants.AUTOMATION);
			device.put(Constants.TYPE, Constants.DEFAULT);
			device.put(Constants.DEVICE_MODEL_NUMBER, Constants.DEFAULT);

			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.TOKEN_EXPIRY, tokenExpiry);
			data.put(Constants.DEVICE, device);

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

	public static Response refreshToken(String refreshToken)
	{
		Response res = null;

		try
		{
			RestAssured.baseURI  = ApiPartnerConfigDetails.apiBaseUrl;
			RestAssured.basePath = Resources.REFRESH_TOKEN;

			HashMap<String, Object>  header = new HashMap<>();
			header.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
			header.put(Constants.API_KEY, ApiPartnerConfigDetails.apiKey);
			header.put(Constants.X_REQUEST_ID, ApiPartnerConfigDetails.request_id);

			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.REFRESH_TOKEN,refreshToken);

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

	public static String generateAccessToken(String type, String value)
	{
		String authorization = null;

		try
		{
			Response res = startSession(type, value);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			String accessToken =  (String) data.get(Constants.ACCESS_TOKEN);
			authorization = Constants.BEARER + accessToken;
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return authorization;
	}

	public static String generateAccessTokenWithIP(String type, String value, String ip)
	{
		String authorization = null;

		try
		{
			Response res = startSession(type, value, ip);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			String accessToken =  (String) data.get(Constants.ACCESS_TOKEN);
			authorization = Constants.BEARER + accessToken;
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return authorization;
	}

	public static void verifyJWTToken(JSONObject jwt, String user, String... d)
	{
		try
		{
			String channelPartnerID = ApiPartnerConfigDetails.partnerConfig.get(Constants.CHANNEL_PARTNER_ID).toString();
			if (d.length > 0) {
				channelPartnerID = d[0];
			}

			Map<String, String> userDetails = TestData.getUserDetails(user);

			ApiVerifications.verifyStringMatching(jwt.get(Constants.DEVICE_TYPE).toString(), Constants.DEFAULT);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.LOC).toString(), ApiPartnerConfigDetails.country.toUpperCase());
			ApiVerifications.verifyStringMatching(jwt.get(Constants.LOGIN_TYPE).toString(), userDetails.get(Constants.LOGIN_TYPE));
			ApiVerifications.verifyStringMatching(jwt.get(Constants.DEVICE_MODEL_NUMBER).toString(), Constants.DEFAULT);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.CP).toString(), channelPartnerID);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.DEVICE_NAME).toString(), Constants.AUTOMATION);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.SID).toString(), userDetails.get(Constants.SID));
			ApiVerifications.verifyStringMatching(jwt.get(Constants.SERIAL_NUMBER).toString(), Constants.AUTOMATION);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.UID).toString(), userDetails.get(Constants.UID));
			ApiVerifications.verifyStringMatching(jwt.get(Constants.CID).toString(), userDetails.get(Constants.CID));
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static void verifyEvlessJWT(JSONObject jwt, String user)
	{
		try
		{
			Map<String, String> userDetails = TestData.getUserDetails(user);

			ApiVerifications.verifyiftrue((boolean) jwt.get(Constants.EVLESS));
			ApiVerifications.verifyStringMatching(jwt.get(Constants.UID).toString(), userDetails.get(Constants.UID));
			ApiVerifications.verifyStringMatching(jwt.get(Constants.SID).toString(), userDetails.get(Constants.SID));
			ApiVerifications.verifyStringMatching(jwt.get(Constants.LOC).toString(), ApiPartnerConfigDetails.country.toUpperCase());
			ApiVerifications.verifyStringMatching(jwt.get(Constants.SERIAL_NUMBER).toString(), Constants.AUTOMATION);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.CP).toString(), ApiPartnerConfigDetails.partnerConfig.get(Constants.CHANNEL_PARTNER_ID).toString());
			ApiVerifications.verifyStringMatching(jwt.get(Constants.DEVICE_NAME).toString(), Constants.AUTOMATION);
			ApiVerifications.verifyStringMatching(jwt.get(Constants.DEVICE_TYPE).toString(), Constants.DEFAULT.toLowerCase());
			ApiVerifications.verifyStringMatching(jwt.get(Constants.LOGIN_TYPE).toString(), userDetails.get(Constants.LOGIN_TYPE));
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}
}

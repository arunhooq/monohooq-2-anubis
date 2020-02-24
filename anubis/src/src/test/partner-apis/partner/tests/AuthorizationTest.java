package partner.tests;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;
import partner.modules.AuthorizationController;
import partner.utils.ApiPartnerConfigDetails;
import partner.utils.Constants;
import sanctuary.utils.ApiVerifications;
import sanctuary.utils.ReusableMethods;

public class AuthorizationTest extends TestConfiguration
{
	@Test(groups= {Constants.SMOKE_TEST})
	public void signInByEmail() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22791);
		String email = Constants.EXISTING_EMAIL;
		try {
			// Login With Existing Email
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_EMAIL, email);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyNotNull(data.get(Constants.ACCESS_TOKEN).toString());
			ApiVerifications.verifyNotNull(data.get(Constants.REFRESH_TOKEN).toString());

			String accessToken = (String) data.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,email);

			ReporterLog.pass("SignInByEmail", "SignInByEmail executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"SignInByEmail failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInByPhoneNumber() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22792);
		String phoneNumber = Constants.EXISTING_PHONENUMBER;
		try {
			// Login With Existing PhoneNumber
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_PHONENUMBER, phoneNumber);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyNotNull(data.get(Constants.ACCESS_TOKEN).toString());
			ApiVerifications.verifyNotNull(data.get(Constants.REFRESH_TOKEN).toString());

			String accessToken = (String) data.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,phoneNumber);

			ReporterLog.pass("SignInByPhoneNumber", "SignInByPhoneNumber executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"SignInByPhoneNumber failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInByCpCustomerID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22793);
		String cpCustomerID = Constants.EXISTING_CP_ID;
		try {
			// Login With Existing cpCustomerID Account
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyNotNull(data.get(Constants.ACCESS_TOKEN).toString());
			ApiVerifications.verifyNotNull(data.get(Constants.REFRESH_TOKEN).toString());

			String accessToken = (String) data.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,cpCustomerID);

			ReporterLog.pass("SignInByCpCustomerID", "SignInByCpCustomerID executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"SignInByCpCustomerID failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInWithInvalidPrimaryID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22853);
		String invalid_email = Constants.INVALID + "@com";
		try {
			// Login With Invalid Format Email
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_EMAIL, Constants.INVALID);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1001);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_EMAIL_FORMAT);

			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_EMAIL, invalid_email);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1001);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_EMAIL_FORMAT);

			// Login With Invalid Format Phone Number
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_PHONENUMBER, Constants.EXISTING_EMAIL);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2783);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_PHONENUMBER_OR_EMAIL);

			// Login With Invalid Format CP Customer ID
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_CP_ID, Constants.EXISTING_EMAIL);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2327);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.ACCOUNT_NOT_FOUND);

			ReporterLog.pass("signInWithInvalidPrimaryID", "signInWithInvalidPrimaryID executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithInvalidPrimaryID failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInWithUnExistingAccount() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22821);
		String unexisting_email = "unexisting@yopmail.com";
		String unexisting_phone = "081234567899";
		String unexisting_cp_id = "12345678901234567890";
		try {
			// Login With Unexisting Email
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_EMAIL, unexisting_email);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2783);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_PHONENUMBER_OR_EMAIL);

			// Login With Unexisting Phone Number
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_PHONENUMBER, unexisting_phone);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2783);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_PHONENUMBER_OR_EMAIL);

			// Login With Unexisting CP Customer ID
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_CP_ID, unexisting_cp_id);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_EV2327);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.ACCOUNT_NOT_FOUND);

			ReporterLog.pass("signInWithUnExistingAccount", "signInWithUnExistingAccount executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithUnExistingAccount failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInWithoutPrimaryID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22812);
		try {
			// Login Without Email
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_EMAIL, Constants.EMPTY_STRING);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1003);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.MISSING_PRIMARY_ID);

			// Login Without Phone Number
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_PHONENUMBER, Constants.EMPTY_STRING);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1003);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.MISSING_PRIMARY_ID);

			// Login Without CP Customer ID
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_CP_ID, Constants.EMPTY_STRING);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1003);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.MISSING_PRIMARY_ID);

			ReporterLog.pass("signInWithoutPrimaryID", "signInWithoutPrimaryID executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithoutPrimaryID failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInWithoutHMAC() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22820);
		try {
			// Set Request Header
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();

			// Set Request Payload
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL);
			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			// Set HMAC as Empty
			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,Constants.EMPTY_STRING);

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1001);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_HMAC_FORMAT);

			ReporterLog.pass("signInWithoutHMAC", "signInWithoutHMAC executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithoutHMAC failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signInWithInvalidHMAC() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22817);
		try {
			// Set Request Header
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();

			// Set Request Payload
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL);
			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			// Set HMAC as Empty
			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,Constants.INVALID);

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_1004);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_HMAC);

			ReporterLog.pass("signInWithInvalidHMAC", "signInWithInvalidHMAC executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithInvalidHMAC failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void signInWithXForceCPID() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22816);
		String cpCustomerID = Constants.ONE;
		try {
			// Set Request Header x-force-cpid is true
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();
			header.put(Constants.X_FORCE_CPID, "true");

			// Set Request Payload
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(cpCustomerID));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint with x-force-cpid is true
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject responseData =  (JSONObject) json.get(Constants.DATA);

			// Verify Response JWT
			String accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,Constants.HOOQINDO1);

			// Set Request Header x-force-cpid is false
			header.put(Constants.X_FORCE_CPID, "false");

			// Call Start Session Endpoint with x-force-cpid is false
			res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyRequestSucceed(res);
			json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			responseData =  (JSONObject) json.get(Constants.DATA);

			// Verify Response JWT
			accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,Constants.HOOQIND1,Constants.HOOQIND);

			// Call Start Session Endpoint without x-force-cpid
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			ApiVerifications.verifyRequestSucceed(res);
			json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			responseData =  (JSONObject) json.get(Constants.DATA);

			// Verify Response JWT
			accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,Constants.HOOQIND1,Constants.HOOQIND);

			ReporterLog.pass("signInWithXForceCPID", "signInWithXForceCPID executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithXForceCPID failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void signInWithXVisitorHeader() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(29611);
		String cpCustomerID = Constants.EXISTING_CP_ID;
		try {
			// Set Request Header x-visitor is true
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();
			header.put(Constants.X_VISITOR, "true");

			// Set Request Payload
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(cpCustomerID));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint with x-visitor is true
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject responseData =  (JSONObject) json.get(Constants.DATA);

			// Verify Response JWT
			String accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyEvlessJWT(jwt, ApiPartnerConfigDetails.apiEnv + Constants.VISITOR);

			// Set Request Header x-visitor is false
			header.put(Constants.X_VISITOR, "false");

			// Call Start Session Endpoint with x-visitor is false
			res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyRequestSucceed(res);
			json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			responseData =  (JSONObject) json.get(Constants.DATA);

			// Verify Response JWT
			accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,Constants.EXISTING_CP_ID);

			// Call Start Session Endpoint without x-visitor
			res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			ApiVerifications.verifyRequestSucceed(res);
			json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			responseData =  (JSONObject) json.get(Constants.DATA);

			// Verify Response JWT
			accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,Constants.EXISTING_CP_ID);

			ReporterLog.pass("signInWithXVisitorHeader", "signInWithXVisitorHeader executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithXVisitorHeader failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void signInWithInvalidIPAddress() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22809);
		String cpCustomerID = Constants.EXISTING_CP_ID;
		try {
			// Set Request Header x-forwarded-for as Invalid
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();
			header.put(Constants.X_FORWARDED_FOR, Constants.INVALID);

			// Set Request Payload Without IP Address
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(cpCustomerID));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint with Invalid IP Address
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_111111111);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_IP_ADDRESS);

			// Set Request Payload IP Address With Invalid
			data.put(Constants.IP_ADDRESS, Constants.INVALID);

			// Set Request Header x-forwarded-for as Valid IP Address
			header.put(Constants.X_FORWARDED_FOR, ApiPartnerConfigDetails.ip_address);

			// Call Start Session Endpoint with Invalid IP Address
			res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_111111111);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.INVALID_IP_ADDRESS);

			ReporterLog.pass("signInWithInvalidIPAddress", "signInWithInvalidIPAddress executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithInvalidIPAddress failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void signInWithoutIPAddressInHeaderOrBody() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22806);
		String cpCustomerID = Constants.EXISTING_CP_ID;
		try {
			// Set Request Header x-forwarded-for as Empty
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();
			header.put(Constants.X_FORWARDED_FOR, Constants.EMPTY_STRING);

			// Set Request Payload Without IP Address
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(cpCustomerID));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint without IP Address
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyStatusCode(res.getStatusCode(), Constants.STATUS_CODE_400);

			// Verify Response Body
			Map<String,String> errorMap = ApiVerifications.getErrorcode(res);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.CODE), Constants.USR_AU_1006);
			ApiVerifications.verifyStatusCode(Integer.parseInt(errorMap.get(Constants.STATUS)), Constants.STATUS_CODE_400);
			ApiVerifications.verifyStringMatching(errorMap.get(Constants.DETAIL).toString(), Constants.MISSING_IP_ADDRESS);


			ReporterLog.pass("signInWithoutIPAddressInHeaderOrBody", "signInWithoutIPAddressInHeaderOrBody executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithoutIPAddressInHeaderOrBody failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void signInWithIPAddressInHeaderOrBody() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22811);
		String cpCustomerID = Constants.EXISTING_CP_ID;
		try {
			String ipAddress = ApiPartnerConfigDetails.getIPAddress("ID");
			if ((ApiPartnerConfigDetails.country).toLowerCase().equals("id")){
				ipAddress = ApiPartnerConfigDetails.getIPAddress("SG");
				}

			// Set Request Header x-forwarded-for as Foreign IP Address
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();
			header.put(Constants.X_FORWARDED_FOR, ipAddress);

			// Set Request Payload With Local IP Address
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			data.put(Constants.IP_ADDRESS, ApiPartnerConfigDetails.ip_address);

			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(cpCustomerID));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject responseData =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyNotNull(responseData.get(Constants.ACCESS_TOKEN).toString());
			ApiVerifications.verifyNotNull(responseData.get(Constants.REFRESH_TOKEN).toString());

			String accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,cpCustomerID);

			ReporterLog.pass("signInWithoutIPAddressInHeaderOrBody", "signInWithoutIPAddressInHeaderOrBody executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithoutIPAddressInHeaderOrBody failed");
		}
	}

	@Test(groups= {Constants.REGRESSION_TEST})
	public void signInWithXForwardedFor() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22807);
		String cpCustomerID = Constants.EXISTING_CP_ID;
		try {
			// Set Request Header x-forwarded-for as Empty
			HashMap<String, String> header = AuthorizationController.setStartSessionHeader();
			header.put(Constants.X_FORWARDED_FOR, ApiPartnerConfigDetails.ip_address);

			// Set Request Payload Without IP Address
			HashMap<String, Object>  data = new HashMap<>();
			data.put(Constants.ACCOUNT_TYPE_CP_ID, cpCustomerID);
			data.put(Constants.DEVICE, AuthorizationController.setDeviceObject());

			HashMap<String, Object>  meta = new HashMap<>();
			meta.put(Constants.HMAC,ApiPartnerConfigDetails.generateHMAC(cpCustomerID));

			HashMap<String, Object>  payload = new HashMap<>();
			payload.put(Constants.DATA, data);
			payload.put(Constants.META, meta);

			// Call Start Session Endpoint
			Response res = AuthorizationController.startSessionEndpoint(header, payload);
			ApiVerifications.verifyRequestSucceed(res);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject responseData =  (JSONObject) json.get(Constants.DATA);
			ApiVerifications.verifyNotNull(responseData.get(Constants.ACCESS_TOKEN).toString());
			ApiVerifications.verifyNotNull(responseData.get(Constants.REFRESH_TOKEN).toString());

			String accessToken = (String) responseData.get(Constants.ACCESS_TOKEN);
			JSONObject jwt = ReusableMethods.extractJWTBodytoJSON(accessToken);
			AuthorizationController.verifyJWTToken(jwt,cpCustomerID);

			ReporterLog.pass("signInWithXForwardedFor", "signInWithXForwardedFor executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"signInWithXForwardedFor failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void signOutUser() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22794);
		try {
			// SignIn With Existing Email
			String auth = AuthorizationController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL);

			// SignOut User
			Response res = AuthorizationController.stopSession(auth);
			ApiVerifications.verifyRequestSucceed(res);
			
			ReporterLog.pass("SignOutUser", "SignOutUser executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"SignOutUser failed");
		}
	}

	@Test(groups= {Constants.SMOKE_TEST})
	public void refreshToken() throws ParseException
	{
		ReadTestData.fnAddTestRailScriptID(22795);
		try {
			// SignIn With Existing Email
			Response res = AuthorizationController.startSession(Constants.ACCOUNT_TYPE_EMAIL, Constants.EXISTING_EMAIL);
			JSONObject json = (JSONObject) ReusableMethods.rawtoJsonObject(res);
			JSONObject data =  (JSONObject) json.get(Constants.DATA);
			String refreshToken =  (String) data.get(Constants.REFRESH_TOKEN);

			// Refresh User Token
			res = AuthorizationController.refreshToken(refreshToken);
			ApiVerifications.verifyRequestSucceed(res);
			
			ReporterLog.pass("RefreshToken", "RefreshToken executed successfully");
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"RefreshToken failed");
		}
	}
}

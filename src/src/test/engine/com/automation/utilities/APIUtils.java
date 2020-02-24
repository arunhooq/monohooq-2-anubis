package com.automation.utilities;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.modules.SigninControllers;
import sanctuary.utils.ApiConfigDetails;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.SanctuaryConstants;

public class APIUtils {
    public static ConfigDetails objConfig = null;
    static String sanctuary_Base_Url = "https://api-" + ConfigDetails.apiEnv + ".hooq.tv/1.0";
    ;


    public static void WritePropertiesFile(String key, String data) throws IOException {

        Properties configProperty = new Properties();
        configProperty.setProperty(key, data);
        File file = new File(System.getProperty("user.dir") + "/browserstackAppUpload.properties");
        FileOutputStream fileOut = new FileOutputStream(file);
        configProperty.store(fileOut, "BrowserstackAppId");
        fileOut.close();
    }

    public static String getBrowserstackAppID(String appFilePath) {

        try {
            if (ConfigDetails.browserstackAppId.isEmpty() || ConfigDetails.browserstackAppId == null) {

                System.out.println("Uploading file " + appFilePath
                        + " to Browserstack, the execution will continue after upload is successful...");
                String terminal = "cmd";

                if (System.getProperty("os.name").toLowerCase().startsWith("mac"))
                    terminal = "/bin/bash";

                String[] command = {terminal, "-c",
                        "curl -u \"qabot1:TRu8npqFqye6Df2AAX3p\" -X POST \"https://api-cloud.browserstack.com/app-automate/upload\" -F \"file=@"
                                + appFilePath + "\""};

                Process proc = Runtime.getRuntime().exec(command);

                proc.waitFor();

                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

                String output = "";
                while ((output = reader.readLine()) != null) {
                    System.out.print(output + "\n");
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(output);
                    ConfigDetails.browserstackAppId = (String) json.get("app_url");
                }

            }
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }

        return ConfigDetails.browserstackAppId;

    }


    /**
     * @return This will return newly created account ina String format
     * @throws Exception
     * @author Afsar Ali
     * @description This Method is used for craeting a new User in prod/Stagging
     * ConfigDetails.environment
     */

    private static String createNewUserwithAPI() throws Exception {
        String strCreatedAccount = null;

        try {

            String apiuser = FileUtilities.strReadIniFile("searchaccount", "apiuser-" + ConfigDetails.environment);
            String apipassword = FileUtilities.strReadIniFile("searchaccount",
                    "apipassword-" + ConfigDetails.environment);

            UUID uuid = UUID.randomUUID();
            String spAccountID = uuid.toString();
            String channelPartnerID = FileUtilities.getChannelPartnerID(ConfigDetails.country.toLowerCase());
            String contactUserName = "hooqtestuser%2B" + FileUtilities.strGetRandomString(6) + "_"
                    + ConfigDetails.country + "@gmail.com".toLowerCase();
            String ipAddress = FileUtilities.getIPAddress(ConfigDetails.country.toLowerCase());

            String base_url = "https://rest-" + ConfigDetails.environment
                    + ".evergent.com/qp/hooq/partnersCreateCustomer?";

            String urlParameters = "apiuser=" + apiuser + "&apipassword=" + apipassword + "&contactPassword=123456"
                    + "&spAccountID=" + spAccountID + "&channelPartnerID=" + channelPartnerID + "&contactUserName="
                    + contactUserName + "&ipAddress=" + ipAddress;

            JSONObject jsonobj = JSONUtilities.executePOST(base_url + urlParameters);
            System.out.println("===== " + jsonobj.toJSONString());
            String status = JSONUtilities.getJsonValueUsingPath(jsonobj.toString(),
                    "$['createCustomerRespMessage'].message");

            if (status.equalsIgnoreCase("SUCCESS")) {
                strCreatedAccount = java.net.URLDecoder.decode(contactUserName, "UTF-8");
                System.out.println("New Account created successfully . ==>  " + strCreatedAccount);

            } else {
                throw new Exception("Account creation was not successful: " + status.toString());
            }

        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return strCreatedAccount;

    }

    /**
     * @param strEmailtoSearch
     * @return This will return cpCustomerId of the account searched
     * @throws Exception
     * @author Afsar Ali
     * @description This method will be used for searching of newly created account
     */
    public static String strSearchCreatedAccount(String strEmailtoSearch) throws Exception {
        String cpCustomerId = null;
        try {
            String emailtosearch = JSONUtilities.strURLEncode(strEmailtoSearch);

            String apiuser = FileUtilities.strReadIniFile("searchaccount", "apiuser-" + ConfigDetails.environment);
            String apipassword = FileUtilities.strReadIniFile("searchaccount",
                    "apipassword-" + ConfigDetails.environment);
            String channelPartnerID = FileUtilities.getChannelPartnerID(ConfigDetails.country.toLowerCase());

            String base_url = "https://rest-" + ConfigDetails.environment + ".evergent.com/qp/searchAccount?";

            String urlParameters = "apiuser=" + apiuser + "&apipassword=" + apipassword + "&channelPartnerID="
                    + channelPartnerID + "&email=" + emailtosearch;

            JSONObject jsonobj = JSONUtilities.executePOST(base_url + urlParameters);
            System.out.println("JSON result : " + jsonobj.toString());

            cpCustomerId = com.automation.utilities.JSONUtilities.getJsonValueUsingPath(jsonobj.toString(),
                    "$['SearchAccountResponseMessage'].cpCustomerID");

            System.out.println("cpCustomerId : " + cpCustomerId);
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return cpCustomerId;

    }

    /**
     * @param strCpCustomerID
     * @return This method will return success message after account is being
     * activated .
     * @throws Exception
     * @author Afsar Ali
     * @date 12 july 2019
     */

    public static String strActivateCreatedAccount(String strCpCustomerID) throws Exception {
        String response_message = null;
        String sku = null;

        try {

            String apikey = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(),
                    "apikey-" + ConfigDetails.environment);
            String secret = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(),
                    "secret-" + ConfigDetails.environment);

            if (ConfigDetails.userType.equalsIgnoreCase("Active")
                    || (ConfigDetails.userType.equalsIgnoreCase("Lapsed")))
                sku = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(),
                        "sku-active");

            else if (ConfigDetails.userType.equalsIgnoreCase("Free")
                    || ConfigDetails.userType.equalsIgnoreCase("Visitor"))
                sku = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(), "sku-free");

            System.out.println("sku: " + sku);

            String channelPartnerID = FileUtilities.getChannelPartnerID(ConfigDetails.country.toLowerCase());

            String base_url = "https://rest-" + ConfigDetails.environment + ".evergent.com/qp/hooq/activateCustomerV3?";

            String urlParameters = "method=activate" + "&sku=" + sku + "&cpCustomerID=" + strCpCustomerID + "&apiKey="
                    + apikey + "&channelPartnerID=" + channelPartnerID + "&secret=" + secret;

            JSONObject jsonobj = JSONUtilities.executePOST(base_url + urlParameters);

            System.out.println("jsonobj: " + jsonobj);
            response_message = JSONUtilities.getJsonValueUsingPath(jsonobj.toString(),
                    "$['ActivateCustomerV3RespMessage'].message");

            System.out.println("message after success  : " + response_message);
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return response_message;

    }

    /**
     * @param strCpCustomerID
     * @return This method will return success message after account is
     * deactivated/lapsed.
     * @throws Exception
     * @author Soujanya Jaggu
     * @date 14 july 2019
     */

    public static String strDeactivateCreatedAccount(String strCpCustomerID) throws Exception {
        String response_message = null;
        String sku = null;

        try {

            String apikey = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(),
                    "apikey-" + ConfigDetails.environment);
            String secret = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(),
                    "secret-" + ConfigDetails.environment);

            if (ConfigDetails.userType.equalsIgnoreCase("Lapsed"))
                sku = FileUtilities.strReadIniFile("apikey-secret-" + ConfigDetails.country.toLowerCase(),
                        "sku-active");

            String channelPartnerID = FileUtilities.getChannelPartnerID(ConfigDetails.country.toLowerCase());

            String base_url = "https://rest-" + ConfigDetails.environment + ".evergent.com/qp/hooq/activateCustomerV3?";

            String urlParameters = "method=deactivate" + "&sku=" + sku + "&cpCustomerID=" + strCpCustomerID + "&apiKey="
                    + apikey + "&channelPartnerID=" + channelPartnerID + "&secret=" + secret + "&endDate=1";

            JSONObject jsonobj = JSONUtilities.executePOST(base_url + urlParameters);
            System.out.println("JSON result : " + jsonobj.toString());

            response_message = JSONUtilities.getJsonValueUsingPath(jsonobj.toString(),
                    "$['ActivateCustomerV3RespMessage'].message");

            System.out.println("message after success  : " + response_message);
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return response_message;

    }

    public static String getActiveUserEmail() throws Exception {
        String strUserCreated = null;
        try {
            strUserCreated = createNewUserwithAPI();
            String strCpCustomerId = strSearchCreatedAccount(strUserCreated);
            strActivateCreatedAccount(strCpCustomerId);
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return strUserCreated;
    }

    public static String getLapsedUserEmail() throws Exception {
        String strUserCreated = null;
        try {
            strUserCreated = createNewUserwithAPI();
            String strCpCustomerId = strSearchCreatedAccount(strUserCreated);
            strActivateCreatedAccount(strCpCustomerId);
            strDeactivateCreatedAccount(strCpCustomerId);
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return strUserCreated;
    }

    public static String getFreeUserEmail() throws Exception {
        String strUserCreated = null;
        try {
            strUserCreated = createNewUserwithAPI();
            String strCpCustomerId = strSearchCreatedAccount(strUserCreated);
            strActivateCreatedAccount(strCpCustomerId);
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }
        return strUserCreated;
    }

    public static String signUpUsingMobileNumber() throws ParseException {

        String deviceid = "Deviceid" + ReusableMethods.getTimeStamp();
        // Get the device signature
        Response client_res = ActivateClient(ApiConfigDetails.getDetails(SanctuaryConstants.ACTIVATECLIENT), deviceid);
        JSONObject devicesignature_json = ReusableMethods.rawtoJsonObject(client_res);
        String devicesignature = devicesignature_json.get(SanctuaryConstants.DEVICESIGNATURE).toString();

        // Perform sign up email
        String mobileNumberRegistered = SignupPhone(devicesignature,
                ApiConfigDetails.getDetails(SanctuaryConstants.SIGNUP), SanctuaryConstants.SUBSCRIBED_USER);

        return mobileNumberRegistered;
    }

    public static Response ActivateClient(Map<String, Object> activateclient_map, String deviceid) {
        Response res = null;
        try {

            String sanctuary_Base_Url = "https://api-" + ConfigDetails.apiEnv + ".hooq.tv/1.0";
            // baseUrl and controller/resource details
            RestAssured.baseURI = sanctuary_Base_Url;
            RestAssured.basePath = "/client/activate";

            // header details
            HashMap<String, Object> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("x-forwarded-for", FileUtilities.getIPAddress(ConfigDetails.country.toLowerCase()));

            // pay load details
            HashMap<String, Object> payLoad = new HashMap<>();
            payLoad.put("device_id", deviceid);
            payLoad.put("push_id", activateclient_map.get("push_id"));
            payLoad.put("useragent", activateclient_map.get("useragent"));
            payLoad.put("os", activateclient_map.get("os"));
            payLoad.put("version", activateclient_map.get("version"));
            payLoad.put("model", activateclient_map.get("model"));
            payLoad.put("tester", activateclient_map.get("tester"));

            // Consume the api of type method: post
            res = given().headers(header).body(payLoad).when().post().then().log().ifError().extract().response();
        } catch (Exception e) {
            ReusableMethods.logReportFailure(e);
        }

        return res;

    }

    public static String SignupPhone(String devicesignature, Map<String, Object> signup_map, String user) {
        Response res = null;
        boolean nova_flag = false;
        String sanctuary_Base_Url = "https://api-" + ConfigDetails.apiEnv + ".hooq.tv/1.0";
        String countryCode = ApiConfigDetails.getCountryCode(ConfigDetails.country);
        String phoneNumber = Long.toString(ReusableMethods.getTimeStamp());

        if (user.equalsIgnoreCase(SanctuaryConstants.SUBSCRIBED_USER)
                || ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_IN)) {
            nova_flag = (boolean) signup_map.get("nova");
        }

        try {
            // baseUrl and controller/resource details
            RestAssured.baseURI = sanctuary_Base_Url;
            RestAssured.basePath = "/signup";

            // header details
            HashMap<String, Object> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("device-signature", devicesignature);
            header.put("x-forwarded-for", FileUtilities.getIPAddress(ConfigDetails.country.toLowerCase()));

            // payload or body details
            HashMap<String, Object> payLoad = new HashMap<>();
            payLoad.put("countryCode", countryCode);
            payLoad.put("phoneNumber", phoneNumber);
            payLoad.put("password", signup_map.get("ph_Pwd"));
            payLoad.put("nova", nova_flag);

            System.out.println("phone number :" + phoneNumber);

            // Consume the api
            res = given().headers(header).body(payLoad).when().post().then().log().ifError().extract().response();
        } catch (Exception e) {
            TestUtilities.logReportFatal(e);
        }

        return phoneNumber;

    }

    public static void setBrowserStackSessionStatus(String strSessionId, String status)
            throws URISyntaxException, UnsupportedEncodingException, IOException {

        String baseUrl = "";
        if (ConfigDetails.project.equalsIgnoreCase(GlobalConstant.PROJECT_WEB)) {
            baseUrl = "api.browserstack.com/automate";
        } else {
            baseUrl = "api.browserstack.com/app-automate";
        }

        URI uri = new URI("https://" + ConfigDetails.strBS_Username + ":" + ConfigDetails.strBS_AccessKey + "@"
                + baseUrl + "/sessions/" + strSessionId + ".json");
        HttpPut putRequest = new HttpPut(uri);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add((new BasicNameValuePair("status", status))); // passed or failed
        nameValuePairs.add((new BasicNameValuePair("reason", "")));
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpClientBuilder.create().build().execute(putRequest);
    }

    public static Response getLastWatchedContents(String userEmail)
            throws ParseException, IOException, InterruptedException {

        getJWTTokenForEmail(userEmail);

        RestAssured.baseURI = sanctuary_Base_Url;
        RestAssured.basePath = "/playlist/watched?invalidate=true&size=10&scope=detail";

        // header details
        HashMap<String, Object> header = new HashMap<>();
        header.put("device-signature", GlobalConstant.DEVICE_SIGNATURE);
        header.put("Authorization", GlobalConstant.JWT_TOKEN);
        header.put("Content-Type", "application/json");
        header.put("apiKey", FileUtilities.strReadIniFile(ConfigDetails.project.toLowerCase(),
                "apikey-" + ConfigDetails.environment));
        header.put("x-forwarded-for", FileUtilities.getIPAddress(ConfigDetails.country.toLowerCase()));

        // http get method
        Response res = given().headers(header).when().get().then().log().ifError().extract().response();

        return res;

    }

    public static String getDeviceSignature() {
        Response res = null;
        String devicesignature = null;
        try {
            // Activate Client
            RestAssured.baseURI = sanctuary_Base_Url;
            RestAssured.basePath = "/client/activate";

            // header details
            HashMap<String, Object> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("x-forwarded-for", FileUtilities.getIPAddress(ConfigDetails.country.toLowerCase()));

            // pay load details
            HashMap<String, Object> payLoad = new HashMap<>();
            payLoad.put("device_id", "devicid-" + FileUtilities.strGetRandomString(5));
            payLoad.put("push_id", "32G-chet3asd646434455444");
            payLoad.put("useragent", "ali-automation-test");
            payLoad.put("os", "automation");
            payLoad.put("version", "5.0.1");
            payLoad.put("model", "MacBook Pro");
            payLoad.put("tester", true);

            // Consume the api of type method: post
            res = given().headers(header).body(payLoad).when().post().then().log().ifError().extract().response();

            JSONObject client_obj = ReusableMethods.rawtoJsonObject(res);
            devicesignature = client_obj.get(SanctuaryConstants.DEVICESIGNATURE).toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return devicesignature;
    }

    public static void getJWTTokenForEmail(String userEmail) throws InterruptedException, IOException, ParseException {

        // APIUtils.Authorization obj = null;

        if ((GlobalConstant.DEVICE_SIGNATURE == null) || GlobalConstant.DEVICE_SIGNATURE == "") {

            String devicesignature = getDeviceSignature();

            SigninEmail(userEmail, devicesignature);

            // Verify using Gmail

            Thread.sleep(5000);

            String tokenURI = GmailAPI.getRedirectURLforNewUserLogin(userEmail);
            String command = "curl " + tokenURI;

            Process process = Runtime.getRuntime().exec(command);

            System.out.println(convertInputStreamToString(process.getInputStream()));

            Response signInafterVerify = SigninEmail(userEmail, devicesignature);

            // Get Token
            String authorization = ReusableMethods.rawtoJsonObject(signInafterVerify).get(SanctuaryConstants.TOKEN)
                    .toString();

            System.out.println("Authorization   " + authorization);
            GlobalConstant.DEVICE_SIGNATURE = devicesignature;
            GlobalConstant.JWT_TOKEN = authorization;
        }

    }

    public static Response SigninEmail(String email, String deviceSig) {
        Response res = null;
        try {
            // baseUrl and controller details
            RestAssured.baseURI = sanctuary_Base_Url;
            RestAssured.basePath = "/signin";

            String apikey = FileUtilities.strReadIniFile(ConfigDetails.project.toLowerCase(),
                    "apikey-" + ConfigDetails.environment);

            // header details
            HashMap<String, Object> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("apiKey", apikey);
            header.put("device-signature", deviceSig);
            header.put("x-forwarded-for", FileUtilities.getIPAddress(ConfigDetails.country.toLowerCase()));

            // payload or body details
            HashMap<String, String> payLoad = new HashMap<>();
            payLoad.put("email", email);

            // Consume the api
            res = given().headers(header).body(payLoad).when().post().then().log().ifError().extract().response();
        } catch (Exception e) {
            TestUtilities.logReportFailure(e);
        }

        return res;

    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.name());

    }

    public static String getLatestBrowserstackApps() throws IOException {
        String bs_app_url = "";
        try {
            if (!ConfigDetails.browserstackAppId.isEmpty()) {
                return bs_app_url = ConfigDetails.browserstackAppId;
            }
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://api-cloud.browserstack.com/app-automate/recent_apps");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic cWFib3QxOlRSdThucHFGcXllNkRmMkFBWDNw");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(result.toString());
            for (int i = 0; i < json.size(); i++) {
                JSONObject obj = (JSONObject) json.get(i);
                String name = obj.get("app_name").toString();
                String builNumber = obj.get("app_version").toString();
                if (ConfigDetails.project.equalsIgnoreCase(GlobalConstant.PROJECT_IOS)) {
                    if (name.endsWith(".ipa")) {
                        System.out.println(
                                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                        System.out.println(" IPA file to be used for Browserstack is : ||  " + name
                                + " ||  Build Number : " + builNumber);
                        System.out.println("\n" + obj.toJSONString());
                        System.out.println(
                                "\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                        bs_app_url = obj.get("app_url").toString();
                        break;
                    }
                } else if (ConfigDetails.project.equalsIgnoreCase(GlobalConstant.PROJECT_ANDROID)) {
                    if (name.endsWith(".apk")) {
                        System.out.println(
                                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                        System.out.println(" IPA file to be used for Browserstack is : ||  " + name
                                + " ||  Build Number : " + builNumber);
                        System.out.println("\n" + obj.toJSONString());
                        System.out.println(
                                "\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                        bs_app_url = obj.get("app_url").toString();
                        break;
                    }
                } else {
                    throw new Exception(
                            "Please check project selected / ios,android app is not available please upload to browserstack  ...browserstack app can only be fetched with android and ios projects ..");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(
                    "Something went wrong while fetching latest .ipa/.apk file from browserstack .. Please try again...");
        }
        return bs_app_url;
    }

    public static void main(String a[]) throws Exception {


    }

}

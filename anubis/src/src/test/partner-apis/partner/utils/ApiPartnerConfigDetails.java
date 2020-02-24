package partner.utils;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.DBHelpers;
import partner.modules.AuthorizationController;
import partner.modules.KashmirController;
import partner.modules.SanctuaryController;
import partner.modules.SubscriptionController;
import sanctuary.utils.ReusableMethods;

public class ApiPartnerConfigDetails
{
	// Based on the below three variables the test is determined
	public static String environment = "stag";
	public static String country;
	public static String partner;
	public static String eligibilityService = "ums";

	// Property file initialization
	public static Properties prop = ReusableMethods.fnGetPropertiesDetail(ReusableMethods.fnGetConfigFileName());

	public static String ip_address;
	public static String auth;
	public static String request_id= Constants.AUTOMATION;

	public static String titleDownloadable;
	public static String titleNonDownloadable;
	public static String titleNonStreamable;
	public static String titleAVOD;
	public static String titleTVOD;
	public static String titleMovieLvl10;
	public static String titleMovieLvl30;
	public static String titleSeriesLvl10;
	public static String titleSeriesLvl30;
	public static String titleR21;
	public static String titleDisneyContent;
	public static String titleFreeTV;
	public static String titlePayTV;

	public static String lapsedUserToken;
	public static String premiumUserToken;
	public static String visitorUserToken;

	public static String skuTVOD;
	public static String skuSVOD;

	public static HashMap<String, Object> partnerConfig;
	public static String appID;
	public static String apiKey;
	public static boolean enableEligibility;
	public static Object enableSSAI;
	public static String hmacKey;
	public static String hmacValue;

	public static String apiEnv;
	public static String apiBaseUrl;
	public static String albBaseUrl;
	public static String discoverBaseUrl;
	public static String hooqBaseUrl;

	public static String db_hostname;
	public static String db_port;
	public static String db_username;
	public static String db_password;
	public static String db_name;

	public static void setConfigDetails()
	{
		try
		{
			System.out.println("################# BELOW PARAMETERS ARE SET FOR EXECUTION ############");
			environment 		= fnGetPropertiesFileDetails("environment");
			eligibilityService	= fnGetPropertiesFileDetails("eligibilityService");

			// API Partner Config Initialization
			apiEnv				= getApiEnv(environment.toLowerCase());
			apiBaseUrl			= "https://api-" + apiEnv + ".hooq.tv";
			albBaseUrl			= ReusableMethods.strReadIniFile("alb-url", "partner-" + apiEnv);
			discoverBaseUrl		= "https://cdn-discover-" + apiEnv + ".hooq.tv";
			hooqBaseUrl			= getHOOQUrl(apiEnv);
			appID				= ReusableMethods.strReadIniFile("partner", ApiPartnerConfigDetails.partner.toLowerCase()
					+ "-appid-"+ apiEnv );
			partnerConfig		= KashmirController.generatePartnerConfigData(ApiPartnerConfigDetails.partner.toLowerCase());
			apiKey				= partnerConfig.get(Constants.APPLICATION_SECRET).toString();
			enableEligibility	= (boolean) partnerConfig.get(Constants.ELIGIBILITY);
			enableSSAI			= partnerConfig.get(Constants.SSAI);

			// database config details
			db_hostname	= ReusableMethods.strReadIniFile("BACKSTAGE-" +apiEnv+"-DB-DETAILS", "DATABASE_HOST");
			db_port		= ReusableMethods.strReadIniFile("BACKSTAGE-" +apiEnv+"-DB-DETAILS", "DATABASE_PORT");
			db_username	= ReusableMethods.strReadIniFile("BACKSTAGE-" +apiEnv+"-DB-DETAILS", "DATABASE_USER");
			db_password	= ReusableMethods.strReadIniFile("BACKSTAGE-" +apiEnv+"-DB-DETAILS", "DATABASE_PASS");
			db_name		= ReusableMethods.strReadIniFile("BACKSTAGE-" +apiEnv+"-DB-DETAILS", "DATABASE_DB");

			// Set Partner HMAC
			String hmac = getHMACKeys(partnerConfig);
			int i = hmac.indexOf("|");
			hmacKey = hmac.substring(0,i);
			hmacValue = hmac.substring(i+1);

			// Set SKU Data
			HashMap<String, String> skuObject = getSKUObject(partnerConfig.get(Constants.CHANNEL_PARTNER_ID).toString(), country);
			skuTVOD 	= skuObject.get(Constants.TVOD).toString();
			skuSVOD 	= skuObject.get(Constants.SVOD).toString();

			// Set Dynamic Test Data
			ip_address 		= getIPAddress(country);

			// Set Titles Test Data
			Map<String, Object> getTitleObject = TestData.getTitles(apiEnv, country);
			titleDownloadable = getTitleObject.get(Constants.DOWNLOADABLE).toString();
			titleNonDownloadable = getTitleObject.get(Constants.NON_DOWNLOADABLE).toString();
			titleNonStreamable = getTitleObject.get(Constants.NON_STREAMABLE).toString();
			titleAVOD = getTitleObject.get(Constants.AVOD).toString();
			titleTVOD = getTitleObject.get(Constants.TVOD).toString();
			titleMovieLvl10	= getTitleObject.get(Constants.MOVIE_LVL_10).toString();
			titleMovieLvl30	= getTitleObject.get(Constants.MOVIE_LVL_30).toString();
			titleSeriesLvl10 = getTitleObject.get(Constants.SERIES_LVL_10).toString();
			titleSeriesLvl30 = getTitleObject.get(Constants.SERIES_LVL_30).toString();
			titleR21 = getTitleObject.get(Constants.R21).toString();
			titleDisneyContent	= getTitleObject.get(Constants.DISNEY).toString();
			titleFreeTV	= getTitleObject.get(Constants.FREE_TV).toString();
			titlePayTV	= getTitleObject.get(Constants.PAY_TV).toString();

			// Set UserAuthToken Data
			Map<String, String> userTokens = getUserTokens(partner);
			lapsedUserToken = userTokens.get(Constants.LAPSED).toString();
			premiumUserToken = userTokens.get(Constants.PREMIUM).toString();
			visitorUserToken 	= userTokens.get(Constants.VISITOR).toString();
		}
		catch (Exception e)
		{
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
	}

	public static String fnGetPropertiesFileDetails(String strPropertyName)
	{
		String strData = null;
		try
		{
			// First Priority to Maven values
			if (System.getProperty(strPropertyName) != null)
			{
				strData = System.getProperty(strPropertyName);
				System.out.println("property set from Maven call: " + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
						"property set from Maven call: " + strPropertyName + " is :" + strData, "purple");
			}
			// Second Priority to system Environment variables
			else if (System.getenv(strPropertyName) != null)
			{
				strData = System.getenv(strPropertyName);
				System.out.println("property set as environment variable: " + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
						"property set as environment variable: " + strPropertyName + " is :" + strData, "purple");
			}
			// Third Priority to Default properties
			else if(prop.getProperty(strPropertyName) != null)
			{
				strData = prop.getProperty(strPropertyName);
				System.out.println("property set in Default.properties file: " + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
					"property set in Default.properties file: " + strPropertyName + " is :" + strData, "purple");
			}
			// Last Priority to ApiConfig values
			else
			{
			    ApiPartnerConfigDetails configs = new ApiPartnerConfigDetails();
				Field field = ApiPartnerConfigDetails.class.getField(strPropertyName);
				if(field.getType().toString().equals("class java.lang.String"))
					strData = (String) field.get(configs);
				System.out.println("default value set ConfigDetails class: "
						+ "" + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
						"default value set ApiConfigDetails class: " + strPropertyName + " is :" + strData, "purple");
			}
		}
		catch (Exception e)
		{
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
		return strData;
	}

	public static String getApiEnv(String environment)
	{
		String apiEnv = environment;
		try
		{
			switch (environment.toLowerCase())
			{
			case "stag":
				apiEnv = "nightly";
				break;
			}
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return apiEnv;
	}

	public static String getIPAddress(String strCountry)
	{
		String ipAddress = "";
		try
		{
			switch (strCountry.toLowerCase())
			{
			case "in":
				ipAddress = "13.32.32.0";
				break;
			case "sg":
				ipAddress = "118.200.236.168";
				break;
			case "ph":
				ipAddress = "14.102.168.0";
				break;
			case "id":
				ipAddress = "14.102.152.0";
				break;
			case "th":
				ipAddress = "14.128.8.0";
				break;
			}
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return ipAddress;
	}

	public static Map<String, Object> getTitleObject(String country)
	{
		try
		{
			return Stream.of(
    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,DBHelpers.getQueryResult(apiEnv,QueryCollection.getDownloadableTitle(country, true)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,DBHelpers.getQueryResult(apiEnv,QueryCollection.getDownloadableTitle(country, false)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,DBHelpers.getQueryResult(apiEnv,QueryCollection.getNonStreamableTitle(country)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.AVOD,DBHelpers.getQueryResult(apiEnv,QueryCollection.getAVODTitle(country)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.TVOD,DBHelpers.getQueryResult(apiEnv,QueryCollection.getTVODTitle(country)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,DBHelpers.getQueryResult(apiEnv,QueryCollection.getSVODTitle(country, "MOVIE", "10")).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,DBHelpers.getQueryResult(apiEnv,QueryCollection.getSVODTitle(country, "MOVIE", "30")).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,DBHelpers.getQueryResult(apiEnv,QueryCollection.getSVODTitle(country, "EPISODE", "10")).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,DBHelpers.getQueryResult(apiEnv,QueryCollection.getSVODTitle(country, "EPISODE", "30")).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.R21,DBHelpers.getQueryResult(apiEnv,QueryCollection.getR21Title(country)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,DBHelpers.getQueryResult(apiEnv,QueryCollection.getDisneyTitle(country)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,DBHelpers.getQueryResult(apiEnv,QueryCollection.getTVTitle(country, false)).get("title_id")),
    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,DBHelpers.getQueryResult(apiEnv,QueryCollection.getTVTitle(country, true)).get("title_id"))
	    	)
	    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return null;
	}

	public static HashMap<String, String> getSKUObject(String channelPartnerID, String country)
	{
		HashMap<String, String> sku = new HashMap<>();
		try
		{
			if (channelPartnerID.equals(Constants.TELKOMSEL))
			{
				sku.put(Constants.SVOD, Constants.SVOD_TELKOMSEL_SKU);
				sku.put(Constants.TVOD,Constants.NOT_AVAILABLE);
			}
			else
				sku = getHOOQSKUObject(country);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return sku;
	}

	public static HashMap<String, String> getHOOQSKUObject(String country)
	{
		HashMap<String, String> sku = new HashMap<>();
		try
		{
			if (country.equals("ID"))
			{
				sku.put(Constants.TVOD,"INDO" + Constants.TVOD_SKU);
			}
			else sku.put(Constants.TVOD,country + Constants.TVOD_SKU);

			sku.put(Constants.SVOD,country + Constants.SVOD_SKU);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return sku;
	}

	public static String generateHMAC(String msg)
	{
		String hmac = "";
		try
		{
			// Get an algorithm instance
			Mac sha256_HMAC = Mac.getInstance(Constants.HMAC_SHA256);

			// Create secret key
			SecretKeySpec secret_key = new SecretKeySpec(hmacValue.getBytes(), Constants.HMAC_SHA256);

			// Assign secret key algorithm
			sha256_HMAC.init(secret_key);

		    // Generate Base64 encoded cipher string
			String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(msg.getBytes()));

			hmac = hmacKey + "|" + hash;
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return hmac;
	}

	public static String getHMACKeys(HashMap<String, Object> object)
	{
		String hmacKey = "";
		try
		{
			hmacKey = (String) object.get(Constants.HMAC_KEYS);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return hmacKey.replace("\"", "");
	}

	public static String getHOOQUrl(String environment)
	{
		String url = "";
		try
		{
			switch (environment.toLowerCase())
			{
			case "nightly":
				url = "https://web-nightly2.hooq.tv";
				break;

			case "sandbox":
				url = "https://web-sandbox.hooq.tv";
				break;
			}
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return url;
	}

	public static Map<String, String> getUserTokens(String partner)
	{
		try
		{
			// Get User Accounts
			Map<String, Object> getUserAccounts = TestData.getUserAccounts(partner);
			String lapsedUser = getUserAccounts.get(Constants.LAPSED).toString();
			String premiumUser = getUserAccounts.get(Constants.PREMIUM).toString();

			// SignIn Lapsed User
			String lapsedUserToken = AuthorizationController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, lapsedUser);

			// SignIn Premium User and Activate Subscription
			String premiumUserToken = AuthorizationController.generateAccessToken(Constants.ACCOUNT_TYPE_EMAIL, premiumUser);
			SubscriptionController.updateSubscription(premiumUserToken, ApiPartnerConfigDetails.skuSVOD, Constants.ACTIVATE);

			// Generate Visitor Token
			String visitorToken 	= SanctuaryController.getVisitorToken(Constants.EXISTING_DEVICE_SIGNATURE);

			return Stream.of(
	    			new AbstractMap.SimpleEntry<>(Constants.LAPSED,lapsedUserToken),
	    			new AbstractMap.SimpleEntry<>(Constants.PREMIUM,premiumUserToken),
	    			new AbstractMap.SimpleEntry<>(Constants.VISITOR,visitorToken)
		    	)
		    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		}catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}
}

package sanctuary.utils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.automation.testengine.TestUtilities;




public class ApiConfigDetails extends SanctuaryConstants {
	
	public static String strURL = "http://www.hooq.tv";
	
	//Based on the below three variables the test is determined
	public static String environment = "stag";
	public static String country = null;
	public static String platform = "Android";
	
	public static String strUDID = "none";
	public static String strGoogleSheet = "false";
	public static String strTestRail = "false";
	public static String strTestRailProject = "Automation_Cigniti";
	public static String targetExecution = "local";

	public static String jenkinsScreenShotsPath = null;	
	public static String randomNumber;
	
	// Property file initialization 
	public static Properties prop = ReusableMethods.fnGetPropertiesDetail(ReusableMethods.fnGetConfigFileName());
	public static String evegent_Base_Url = "https://rest-" + ApiConfigDetails.environment+".evergent.com";
	public static String catalog_Base_Url;
	public static String catalog_webclient_Url;
	public static String version;
	
	public static String eve_Searchaccount_User;
	public static String eve_Searchaccount_Pwd;
	
	public static String sku;
	public static String channelPartnerId;
	public static String locale;
	public static String evergent_apikey;
	public static String evergent_secret;	
	public static String sanctuary_Env;
	public static String sanctuary_Base_Url;
	public static String sanctuary_Download_Url;
	public static String sanctuary_webclient_url;
	public static String websocket_Url;
	public static String eve_Base_Url;
	
	public static String countryCode;
	
	public static String client_apikey;
	public static String client_drm;
	public static String ip_address;
	public static String free_sku;
	
	public static String db_hostname;
	public static String db_port;
	public static String db_username;
	public static String db_password;
	public static String db_name;
	
	public static String project = "Sanctuary";
	
	
	public static Map<String, Object> get_titles;
		
	
	public static ApiConfigDetails configDetails = new ApiConfigDetails();


	public static void setConfigDetails() 
	{

		try {
			
			System.out.println("################# BELOW PARAMETERS ARE SET FOR EXECUTION ############");
			
			// Generic Test execution and environment initialization 
			targetExecution 		= fnGetPropertiesFileDetails("targetExecution");
			environment 			= fnGetPropertiesFileDetails("environment");				
			strURL 					= fnGetPropertiesFileDetails("strURL");	
			//country 				= fnGetPropertiesFileDetails("country");
			
			
			strTestRailProject  	= fnGetPropertiesFileDetails("strTestRailProject");
			strGoogleSheet 			= fnGetPropertiesFileDetails("strGoogleSheet");	
			jenkinsScreenShotsPath 	= fnGetPropertiesFileDetails("jenkinsScreenShotsPath");
			
			//platform             	= fnGetPropertiesFileDetails("platform");
			
			strTestRail 			= fnGetPropertiesFileDetails("strTestRail");
			
			randomNumber 			= Long.toString(ReusableMethods.getTimeStamp());
			
			ip_address 				= getIPAddress(country.toLowerCase());
			
			countryCode			    = getCountryCode(country.toLowerCase());
			
			// evergent config initialization
			eve_Searchaccount_User  = ReusableMethods.strReadIniFile("searchaccount", "apiuser-" + ApiConfigDetails.environment);
			eve_Searchaccount_Pwd   = ReusableMethods.strReadIniFile("searchaccount", "apipassword-" + ApiConfigDetails.environment);
			
			sku      				= getSku(country.toLowerCase());
			channelPartnerId 		= getChannelPartnerID(country.toLowerCase());
			locale 					= getEvergentlocale(country.toLowerCase());
			evergent_apikey 		= ReusableMethods.strReadIniFile("apikey-secret-" + country.toLowerCase(), "apikey-" + ApiConfigDetails.environment);
			evergent_secret 		= ReusableMethods.strReadIniFile("apikey-secret-" + country.toLowerCase(), "secret-" + ApiConfigDetails.environment);
			
			// sanctuary config initialization
			sanctuary_Env 			= getSanctuaryEnv(environment.toLowerCase());
			sanctuary_Base_Url  	= "https://api-"+sanctuary_Env+".hooq.tv/1.0";
			sanctuary_webclient_url = "https://api-"+sanctuary_Env+".hooq.tv/2.0";
			catalog_webclient_Url   = "https://api-"+sanctuary_Env+".hooq.tv/2.0";
			sanctuary_Download_Url  = "https://api-"+sanctuary_Env+".hooq.tv/1.3";
			websocket_Url			= "wss://pair-"+sanctuary_Env+".hooq.tv/1.1-alpha/device-pairing";
			
			// eve config(proxt to evergent) initialization
			eve_Base_Url			= "http://eve-"+sanctuary_Env+".hooq.tv/1.0";	
			
			// catalog play
			version 			    = ReusableMethods.strReadIniFile(ApiConfigDetails.platform.toLowerCase(), "version");
			catalog_Base_Url        = "https://api-"+sanctuary_Env+".hooq.tv/"+version;
			client_apikey 			= ReusableMethods.strReadIniFile(ApiConfigDetails.platform.toLowerCase(), "apikey-" + ApiConfigDetails.environment);
			client_drm    			= ReusableMethods.strReadIniFile(ApiConfigDetails.platform.toLowerCase(), "drm-format");
			
			// get titles			
			get_titles 				= getTitles(country.toLowerCase());
			
			
			// database config details
			db_hostname 			= ReusableMethods.strReadIniFile("BACKSTAGE-" +sanctuary_Env+"-DB-DETAILS", "DATABASE_HOST");
			db_port					= ReusableMethods.strReadIniFile("BACKSTAGE-" +sanctuary_Env+"-DB-DETAILS", "DATABASE_PORT");
			db_username				= ReusableMethods.strReadIniFile("BACKSTAGE-" +sanctuary_Env+"-DB-DETAILS", "DATABASE_USER");
			db_password				= ReusableMethods.strReadIniFile("BACKSTAGE-" +sanctuary_Env+"-DB-DETAILS", "DATABASE_PASS");
			db_name					= ReusableMethods.strReadIniFile("BACKSTAGE-" +sanctuary_Env+"-DB-DETAILS", "DATABASE_DB");
		
		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);		}
	}

	public static String fnGetPropertiesFileDetails(String strPropertyName) {
		String strData = null;
		try {
			// First Priority to Maven values
			if (System.getProperty(strPropertyName) != null) {
				strData = System.getProperty(strPropertyName);			
				System.out.println("property set from Maven call: " + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
						"property set from Maven call: " + strPropertyName + " is :" + strData, "purple");
			}
			// Second Priority to system Environment variables
			else if (System.getenv(strPropertyName) != null){
				strData = System.getenv(strPropertyName);	
				System.out.println("property set as environment variable: " + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
						"property set as environment variable: " + strPropertyName + " is :" + strData, "purple");
			}
			// Third Priority to Default properties
			else if(prop.getProperty(strPropertyName) != null){
				strData = prop.getProperty(strPropertyName);		
				System.out.println("property set in Default.properties file: " + strPropertyName + " is :" + strData);
				ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
					"property set in Default.properties file: " + strPropertyName + " is :" + strData, "purple");
			}
			// Last Priority to ApiConfig values
			else {
				    ApiConfigDetails configs = new ApiConfigDetails();
					Field field = ApiConfigDetails.class.getField(strPropertyName);
					if(field.getType().toString().equals("class java.lang.String"))
						strData = (String) field.get(configs);
					System.out.println("default value set ApiConfigDetails class: "
							+ "" + strPropertyName + " is :" + strData);	
					ReusableMethods.writeToFile(Thread.currentThread().getStackTrace()[3].getMethodName().toString(),
							"default value set ApiConfigDetails class: " + strPropertyName + " is :" + strData, "purple");
			}

		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);
		}
		return strData;
	}
	
	
	public static String getIPAddress(String strCountry) {

		String ipAddress = "";
		try {
			switch (strCountry.toLowerCase()) {

			case COUNTRY_IN:
				ipAddress = "13.32.32.0";
				break;
			case COUNTRY_SG:
				ipAddress = "118.200.236.168";
				break;
			case COUNTRY_PH:
				ipAddress = "14.102.168.0";
				break;
			case COUNTRY_ID:
				ipAddress = "14.102.152.0";
				break;
			case COUNTRY_TH:
				ipAddress = "14.128.8.0";
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return ipAddress;
	}
	
	
	public static String getChannelPartnerID(String strCountry) {

		String channelPartnerID = "";
		try {
			switch (strCountry.toLowerCase()) {

			case COUNTRY_IN:
				channelPartnerID = "HOOQIND";
				break;
			case COUNTRY_SG:
				channelPartnerID = "HOOQSGP";
				break;
			case COUNTRY_PH:
				channelPartnerID = "HOOQPH";
				break;
			case COUNTRY_ID:
				channelPartnerID = "HOOQINDO";
				break;
			case COUNTRY_TH:
				channelPartnerID = "AIS";
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return channelPartnerID;
	}
	
	
	public static String getSku(String strCountry) {

		String sku = "";
		try {
			switch (strCountry.toLowerCase()) {

			case COUNTRY_IN:
				sku = SanctuaryConstants.IN_PAID30_RECUR_OTT_CC_PROMO90D;
				break;
			case COUNTRY_SG:
				sku = SanctuaryConstants.SG_PAID30_RECUR_OTT_CC;
				break;
			case COUNTRY_PH:
				sku = SanctuaryConstants.PH_PAID30_RECUR_OTT_CC;
				break;
			case COUNTRY_ID:
				sku = SanctuaryConstants.ID_PAID30_ONETIME_OTT_OVO;
				break;
			case COUNTRY_TH:
				sku = SanctuaryConstants.TH_PAID30_RECUR_OTT_CC;
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return sku;
	}
	
	public static String getSanctuaryEnv(String environment) {
		
		String sanctuaryEnv = "";
		try {
			switch (environment.toLowerCase()) {

			case STAGING:
				sanctuaryEnv = "nightly";
				break;
			case PROD:
				sanctuaryEnv = "prod";
				break;
		
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return sanctuaryEnv;
		
	}
	
	
	public static Map<String, Object> getDetails(String feature) {
		try {
			switch (feature.toLowerCase()) {

			case SIGNUP:
				return Stream
						.of(new AbstractMap.SimpleEntry<>(NOVA, true), new AbstractMap.SimpleEntry<>(OTP, "123456"),
							new AbstractMap.SimpleEntry<>(PH_PWD, "123"),new AbstractMap.SimpleEntry<>(TRUSTED, true) )
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			case ACTIVATECLIENT:
				return Stream.of(new AbstractMap.SimpleEntry<>(PUSH_ID, "push test"),
						new AbstractMap.SimpleEntry<>(USERAGENT, "Insomnia"),
						new AbstractMap.SimpleEntry<>(OS, getOSDetails(platform)),
						new AbstractMap.SimpleEntry<>(VERSION, "5.0.1"),
						new AbstractMap.SimpleEntry<>(MODEL, "model"), new AbstractMap.SimpleEntry<>(TESTER, true))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}
	
	public static Map<String, Object> getTitles(String strCountry)
	{
		try {
			switch(strCountry.toLowerCase()){	
				
			    case COUNTRY_SG:
			    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL10, "6a76734f-9480-4e64-9c6c-0a30ccaa4271"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL20, "B34174E7-2B3A-4C07-B21E-B2F2B295E13A"),
			    			new AbstractMap.SimpleEntry<>("email_Registered","sgfranecki85@yopmail.com"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL30, "14858f7d-81ef-4271-83aa-fc2187d4f7ce"),
			    			new AbstractMap.SimpleEntry<>("email_Subscribed", "sgdibbert12@yopmail.com"),
			    			new AbstractMap.SimpleEntry<>(R21_TITLE_SG,"C99D7BAF-3E98-4949-A8E7-48C9048ACB9C")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));	
			    case COUNTRY_ID:
			    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL10, "0fb729bb-12f1-4a01-98da-f21f980d0dc4"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL20, "87A02478-6045-416E-8614-51DBD9736F3E"),
			    			new AbstractMap.SimpleEntry<>("email_Registered","idrolfson40@yopmail.com"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL30, "28DC71EE-2C68-4167-9D6D-5BA9FA21E799"),
			    			new AbstractMap.SimpleEntry<>("email_Subscribed", "idfisher59@yopmail.com"),
			    			new AbstractMap.SimpleEntry<>(REGIONBASED_TITLE, "14858f7d-81ef-4271-83aa-fc2187d4f7ce")
			    	)
			        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));	
			    case COUNTRY_PH:
			    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL10, "9D8C2D75-FB06-4562-A488-61535F543077"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL20, "1397CDE5-E047-4D13-969D-ECDE76B37785"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL30, "91AE076B-B906-4E96-94DF-2A4A12B76BD9"),
			    			new AbstractMap.SimpleEntry<>(REGIONBASED_TITLE, "14858f7d-81ef-4271-83aa-fc2187d4f7ce")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));	
			    case COUNTRY_IN:
			    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL10, "67D7AE8B-42AC-429C-A2DE-1F58C2061E2F"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL20, "873B2A80-E003-49C4-9C1E-97655EDF0603"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL30, "6A1AF0E5-EE72-4422-BFF1-57092A477511"),
			    			new AbstractMap.SimpleEntry<>(REGIONBASED_TITLE, "14858f7d-81ef-4271-83aa-fc2187d4f7ce")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));	
			    case COUNTRY_TH:
			    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL10, "E60A0E16-462E-4BDD-A581-DEFCCED7D845"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL20, "F312B64C-835E-4285-8734-CB032A48BA71"),
			    			new AbstractMap.SimpleEntry<>(CONTENTLEVEL30, "91EF611B-F3C6-41EB-ACA9-6BD4618450AE"),
			    			new AbstractMap.SimpleEntry<>(REGIONBASED_TITLE, "14858f7d-81ef-4271-83aa-fc2187d4f7ce")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));	
			}
			
		}catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}
	
	public static JSONObject getRegistrationstatus(JSONObject signup_Json,String loggedinType)
	{
		JSONObject dbresults = null;
		try {
			dbresults = DBhelpers.dbconnect("select registration_status from verifications  WHERE account_id IN (\n" + 
					"SELECT id FROM accounts where "+loggedinType+" = "+"'"+ signup_Json.get(loggedinType) +"'" +" )", ApiConfigDetails.country);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbresults;
		
	}
	
	public static String getCountryCode(String strCountry) {

		String code = "";
		try {
			switch (strCountry.toLowerCase()) {

			case COUNTRY_IN:
				code = "+91";
				break;
			case COUNTRY_SG:
				code = "+65";
				break;
			case COUNTRY_PH:
				code = "+63";
				break;
			case COUNTRY_ID:
				code = "+62";
				break;
			case COUNTRY_TH:
				code = "+66";
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return code;
	}
	
	
	
	public static String getOSDetails(String platform) {

		String os = "";
		try {
			switch (platform.toLowerCase()) {

			case ANDROID:
				os = "androidmobile";
				break;
			case IOS:
				os = "iosmobile";
				break;
			case PLATFORM_WEBCLIENT:
				os = "webclient";
				break;			
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return os;
	}
	
	public static String getEvergentlocale(String strCountry) {

		String channelPartnerID = "";
		try {
			switch (strCountry.toLowerCase()) {

			case COUNTRY_IN:
				channelPartnerID = "eng_IN";
				break;
			case COUNTRY_SG:
				channelPartnerID = "eng_SG";
				break;
			case COUNTRY_PH:
				channelPartnerID = "eng_PH";
				break;
			case COUNTRY_ID:
				channelPartnerID = "eng_ID";
				break;
			case COUNTRY_TH:
				channelPartnerID = "eng_TH";
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return channelPartnerID;
	}
	
	

	@DataProvider(name = "sku")
    public static Object[] dataProviderMethod() 
	{	
		if(ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_SG)) {
			return new Object[] {SanctuaryConstants.SG_PAID30_RECUR_OTT_CC,SanctuaryConstants.SG_PAID30_RECUR_OTT_CC_PROMO30D};	
		}
		else if(ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_ID)){
			return new Object[] {SanctuaryConstants.ID_PAID30_ONETIME_OTT_OVO,SanctuaryConstants.ID_PAID30_RECUR_OTT_CC_PROMO30D};	
		}
		else if(ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_TH)){
			return new Object[] {SanctuaryConstants.TH_PAID30_RECUR_OTT_CC,SanctuaryConstants.TH_PAID30_RECUR_OTT_MOBILE_PROMO30D};
		}
		else if(ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_IN)){
			return new Object[] {SanctuaryConstants.IN_PAID180_RECUR_OTT_CC,SanctuaryConstants.IN_PAID30_RECUR_OTT_CC_PROMO90D};
		}
		else if(ApiConfigDetails.country.equalsIgnoreCase(SanctuaryConstants.COUNTRY_PH)){
			return new Object[] {SanctuaryConstants.PH_PAID30_RECUR_OTT_CC,SanctuaryConstants.PH_PAID1M_RECUR_OTT_IOS_PROMO30D};
		}
		return null;
    }
	
	
	public static Map<String,String> getAllPlatformDetails(String platform)
	{
		try
		{
			switch(platform.toLowerCase()){
			
			case PLATFORM_ANDROIDTV:
				return Stream
						.of(new AbstractMap.SimpleEntry<>(PLATFORM_USERAGENT,"hooqandroidtv/1.0.6.2.3"), new AbstractMap.SimpleEntry<>(PLATFORM_APIKEY,"KQAgsMRhe6W3TlT6fq87mVGn8EFA8e8a")
						,new AbstractMap.SimpleEntry<>(PLATFORM_DRMFORMAT,"DASH/WIDEVINE"))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			case PLATFORM_ROKU:
				return Stream
						.of(new AbstractMap.SimpleEntry<>(PLATFORM_USERAGENT,"Roku/1.0.0"), new AbstractMap.SimpleEntry<>(PLATFORM_APIKEY,"EQa7QksP1A8V2GQF8ZU60Ia25MH4mBgX")
						   ,new AbstractMap.SimpleEntry<>(PLATFORM_DRMFORMAT,"SS/PR"))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			case PLATFORM_WEBCLIENT:
				return Stream
						.of(new AbstractMap.SimpleEntry<>(PLATFORM_USERAGENT,"webclient"), new AbstractMap.SimpleEntry<>(PLATFORM_APIKEY,"W5OwQ1MEq63ic4tdKTbtYSfsXCfiCgcN")
					    ,new AbstractMap.SimpleEntry<>(PLATFORM_DRMFORMAT,"DASH/WIDEVINE"))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			case PLATFORM_SINGTELSTB:
				return Stream
						.of(new AbstractMap.SimpleEntry<>(PLATFORM_USERAGENT,"singtelstb"),
						    new AbstractMap.SimpleEntry<>(PLATFORM_DRMFORMAT,"MSSS/PLAYREADY"),
						    new AbstractMap.SimpleEntry<>(PLATFORM_CONSUMER,"singtel:default"),
						    new AbstractMap.SimpleEntry<>(PLATFORM_APIKEY,"t0jE1csSvAI4iNDAwnwKxMObqhuGsBFn"))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
	 }
		return null;
	}
	

}



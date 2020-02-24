package com.automation.testengine;

import java.lang.reflect.Field;
import java.util.Properties;

import org.testng.Assert;

import com.automation.utilities.FileUtilities;

public class ConfigDetails {
	public static String strURL = "http://test.hooq.tv";

	public static String environment = "prod";
	public static String apiEnv = "prod";
	
	public static boolean startAppium = false;
	public static String strPlatformName = "";
	public static String strPlatformVersion = "8.0.0";
	public static String strNewCommandTimeOut = "30";
	public static String strUDID = "none";
	public static String strFullReset = "false";
	public static String strNoReset = "true";
	public static String strAppPackage = "none";
	public static String strAppActivity = "none";
	public static String strBundleID = "none";
	public static String strPrintExceptionLog = "none";
	public static String strGlobalTimeOut = "180";

	public static String strGoogleSheet = "false";
	public static String strTestRail = "false";
	public static String strTestRailProject = "New World";
	public static String strTestRailSuiteName ="";

	public static String strBS_Username = "qabot1";
	public static String strBS_AccessKey = "TRu8npqFqye6Df2AAX3p";

	public static boolean appium = false;
	public static String browser = "none";
	public static String deviceName = "none";
	public static String targetExecution = "local";
	public static String country = "SG";
	public static String project = "ios";
	public static String isEmail;
	public static String strDriverType = "none";
	public static String strAppPath = "none";
	
	public static String jenkinsScreenShotsPath = null;
	public static String jenkinsBuildName = "AWS Run - "+FileUtilities.GetCurrentTimeStamp();
  
	public static ConfigDetails configDetails = new ConfigDetails();
	public static Properties prop = FileUtilities.fnGetPropertiesDetail(TestUtilities.fnGetConfigFileName());

	public static String userType = "Active";
	public static String suiteType = "";
	public static String browserstackAppId = "";
	
	public static String EVERGENT_USERNAME="YWZzYXJhbGk=";
	public static String EVERGENT_PASSWORD="cGFzc3dvcmQx";
	public static String strMaxRetryCount = "1";
	public static String isVideoRecord = "false";
	public static String enableScreenShotForAllStep = "false";
	public static String isSlack="false";
	
	public static int callerNumber = 3;
	public static void setConfigDetails() {

		try {
			
			callerNumber = 4;
			System.out.println("################# BELOW PARAMETERS ARE SET FOR EXECUTION ############");

			targetExecution = fnGetPropertiesFileDetails("targetExecution");
			environment = fnGetPropertiesFileDetails("environment");
			apiEnv = fnGetPropertiesFileDetails("apiEnv");
			
			if(targetExecution.equals("browserstack")) {
				
				strBS_Username = fnGetPropertiesFileDetails("strBS_Username");
				strBS_AccessKey = fnGetPropertiesFileDetails("strBS_AccessKey");
			}
			
			
			else if(targetExecution.equals("local")) {
				
				browser = fnGetPropertiesFileDetails("browser");
				appium = Boolean.parseBoolean(fnGetPropertiesFileDetails("appium"));
				startAppium = Boolean.parseBoolean(fnGetPropertiesFileDetails("startAppium"));
				deviceName = fnGetPropertiesFileDetails("deviceName");

				strPlatformVersion = fnGetPropertiesFileDetails("strPlatformVersion");

				strAppPackage = fnGetPropertiesFileDetails("strAppPackage");
				strAppActivity = fnGetPropertiesFileDetails("strAppActivity");
				
				strUDID = fnGetPropertiesFileDetails("strUDID");
				strFullReset = fnGetPropertiesFileDetails("strFullReset");
				strNoReset = fnGetPropertiesFileDetails("strNoReset");
				strAppPath = fnGetPropertiesFileDetails("strAppPath");
				strBundleID = fnGetPropertiesFileDetails("strBundleID");
			}
				
		
			strPlatformName = fnGetPropertiesFileDetails("strPlatformName");
			strAppPath = fnGetPropertiesFileDetails("strAppPath");
			strURL = fnGetPropertiesFileDetails("strURL");
			strDriverType = fnGetPropertiesFileDetails("strDriverType");
			jenkinsBuildName = fnGetPropertiesFileDetails("jenkinsBuildName");

			strGlobalTimeOut = fnGetPropertiesFileDetails("strGlobalTimeOut");
			strNewCommandTimeOut = fnGetPropertiesFileDetails("strNewCommandTimeOut");

			strTestRail = fnGetPropertiesFileDetails("strTestRail");
			strTestRailProject = fnGetPropertiesFileDetails("strTestRailProject");
			strGoogleSheet = fnGetPropertiesFileDetails("strGoogleSheet");
			strMaxRetryCount = fnGetPropertiesFileDetails("strMaxRetryCount");
			strTestRailSuiteName = fnGetPropertiesFileDetails("strTestRailSuiteName");
		
			strPrintExceptionLog = fnGetPropertiesFileDetails("strPrintExceptionLog");
			jenkinsScreenShotsPath = fnGetPropertiesFileDetails("jenkinsScreenShotsPath");
			userType = fnGetPropertiesFileDetails("userType");
			browserstackAppId = fnGetPropertiesFileDetails("browserstackAppId");
			isVideoRecord = fnGetPropertiesFileDetails("isVideoRecord");
			enableScreenShotForAllStep = fnGetPropertiesFileDetails("enableScreenShotForAllStep");
			isEmail = fnGetPropertiesFileDetails("isEmail");
			isSlack = fnGetPropertiesFileDetails("isSlack");
		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[1].getLineNumber() + " And Exception is", e);		}
	}

	public static String fnGetPropertiesFileDetails(String strPropertyName) {
		String strData = null;
		try {
			
			if (System.getProperty(strPropertyName) != null) {
				strData = System.getProperty(strPropertyName);
				if(!strPropertyName.equals("strBS_Username") && !strPropertyName.equals("strBS_AccessKey"))
					System.out.println("property set from Maven call: " + strPropertyName + " is :" + strData);
					FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[callerNumber].getMethodName().toString(),
							"property set from Maven call: " + strPropertyName + " is :" + strData, "purple");
			}
			else if (System.getenv(strPropertyName) != null){
				strData = System.getenv(strPropertyName);
				if(!strPropertyName.equals("strBS_Username") && !strPropertyName.equals("strBS_AccessKey"))
					System.out.println("property set as environment variable: " + strPropertyName + " is :" + strData);
				FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[callerNumber].getMethodName().toString(),
						"property set as environment variable: " + strPropertyName + " is :" + strData, "purple");
			}
			else if(prop.getProperty(strPropertyName) != null){
				strData = prop.getProperty(strPropertyName);
				if(!strPropertyName.equals("strBS_Username") && !strPropertyName.equals("strBS_AccessKey"))
					System.out.println("property set in Default.properties file: " + strPropertyName + " is :" + strData);
					FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[callerNumber].getMethodName().toString(),
						"property set in Default.properties file: " + strPropertyName + " is :" + strData, "purple");
			}
			else {
					ConfigDetails configs = new ConfigDetails();
					Field field = ConfigDetails.class.getField(strPropertyName);
					if(field.getType().toString().equals("class java.lang.String"))
						strData = (String) field.get(configs);
					System.out.println("default value set ConfigDetails class: "
							+ "" + strPropertyName + " is :" + strData);	
					FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[callerNumber].getMethodName().toString(),
							"default value set ConfigDetails class: " + strPropertyName + " is :" + strData, "purple");
			}

		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[callerNumber].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[callerNumber].getLineNumber() + " And Exception is", e);
		}
		return strData;
	}

}
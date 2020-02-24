package com.automation.testengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.automation.integrations.ReportStatus;
import com.automation.reports.ReporterLog;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.JSONUtilities;
import com.automation.utilities.ReadTestData;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import sanctuary.utils.ReusableMethods;

public class TestUtilities {

	public static String setExecutionType(String suiteType) {
		String executionType = "";

		if (suiteType.equals("Sanity"))
			executionType = "Sanity";

		else if (suiteType.equals("Regression"))
			executionType = "Regresson";
		return executionType;
	}

	public static String setSheetName(String project) {
		String strTestRailSuiteName = "";
		try {	
			if (project.equalsIgnoreCase("WEB"))
				strTestRailSuiteName = "HOOQ_WEB";

			else if (project.equalsIgnoreCase("ANDROID"))
				strTestRailSuiteName = "HOOQ_Android";

			else if (project.equalsIgnoreCase("IOS"))
				strTestRailSuiteName = "HOOQ_IOS";
			else if (project.equalsIgnoreCase("Sanctuary"))
				strTestRailSuiteName = "Sanctuary";
			else if (project.equalsIgnoreCase("ANDROIDTVV1"))
				strTestRailSuiteName = "HOOQ_ANDROIDTV_v1";
			else if (project.equalsIgnoreCase("ANDROIDTVV2"))
				strTestRailSuiteName = "HOOQ_ANDROIDTV_v2";
			else if (project.equalsIgnoreCase("APIPARTNER"))
				strTestRailSuiteName = "HOOQ_PARTNER_API";
			else
				throw new Exception("Test Rail Suite Name is Not correct ..Please Create Correct Test Suite in Test Rail");

		}catch(Exception e) {
			e.printStackTrace();
		}
		return strTestRailSuiteName;
	}

	public static String fnGetConfigFileName() {
		String strConfigFile = "Default";
		try {
			String strTest = System.getProperty("configfile");
			if (strTest.equals("null") == false) {
				strConfigFile = strTest;
			}
		} catch (Exception e) {
		}
		return strConfigFile;
	}

	public static String fnGetTestDescription(String project, String suiteType) {
		String TestDescription = "";
		try {

			String sheetName = TestUtilities.setSheetName(project);

			String strCountry = ConfigDetails.country;
			String strEnvironment = ConfigDetails.environment;
			String strPlatformName = ConfigDetails.strPlatformName.toUpperCase();

			String Browser = "";

			if (ConfigDetails.targetExecution.equals("local") && (!ConfigDetails.browser.toLowerCase().equals("none")))
				//Browser = " ** Browser : " + ConfigDetails.browser.toUpperCase();

				TestDescription = "Country : " + strCountry + " ** Environment : " + strEnvironment + " ** Application : "
						+ sheetName + " ** OS : " + strPlatformName + Browser + " ** Execution Type : ";

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return TestDescription;
	}

	public static ReadTestData fnGetGoogleSheetDeviceDetails(String testName, String suiteType, ReadTestData objData) {
		try {

			ReadTestData.strScriptName = testName;

			String strGoogleSheet = "";
			if (TestUtilities.setExecutionType(suiteType).toUpperCase().equals("REGRESSION")) {
				strGoogleSheet = ReadTestData.SHEET_NAME_REG;
			} else {
				strGoogleSheet = ReadTestData.SHEET_NAME_SANITY;
			}
			ReadTestData.strGoogleSheet = strGoogleSheet;
			ReadTestData.strPlatform = ConfigDetails.strPlatformName.toUpperCase();
			ReadTestData.strDevice = ConfigDetails.browser;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return objData;
	}

	public static String fnGetTestRailSuiteName(String project) {
		String strTestRailSuiteName = "";
		try {
			if(ConfigDetails.strTestRailSuiteName.isEmpty() || ConfigDetails.strTestRail == null) {

				if (TestUtilities.setExecutionType(ConfigDetails.suiteType).toLowerCase().equals("sanity")) {
					strTestRailSuiteName = TestUtilities.setSheetName(project) + "_Sanity";
				} else {
					strTestRailSuiteName = TestUtilities.setSheetName(project) + "_Regression";
				}	
			}else {
				strTestRailSuiteName = ConfigDetails.strTestRailSuiteName ;
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return strTestRailSuiteName;
	}

	public static String getMileStoneName() {
		String strMileStoneName =null;
		try {
			switch(ConfigDetails.project.toUpperCase()) {

			case GlobalConstant.PROJECT_ANDROID :
				strMileStoneName = "Android_All_Region";
				break;
			case GlobalConstant.PROJECT_IOS :
				strMileStoneName = "IOS_All_Region";
				break;
			case GlobalConstant.PROJECT_WEB :
				strMileStoneName = "PWA_All_Region";
				break;
			case GlobalConstant.PROJECT_ANDROIDTVV1 :
				strMileStoneName = "AndroidTV_All_Region";
				break;
			case GlobalConstant.PROJECT_ANDROIDTVV2 :
				strMileStoneName = "AndroidTV_All_Region";
				break;
			case GlobalConstant.PROJECT_API :
			case "SANCTUARY":
				strMileStoneName = "Sanctuary_All_Region";
				break;	
			case GlobalConstant.PROJECT_PARTNERAPI :
				strMileStoneName = "PartnerAPI_All_Region";
				break;	
			}
		}catch(Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return strMileStoneName;
	}

	public static void stopServer() {
		String[] command = { "/usr/bin/killall", "-KILL", "node" };
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void startServer() {
		try {
			stopServer();
			Thread.sleep(10000);
			Map<String, String> env = new HashMap<>(System.getenv());
			env.put("PATH", "/usr/local/bin:" + env.get("PATH"));

			AppiumServiceBuilder builder = new AppiumServiceBuilder()
					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
					.usingDriverExecutable(new File("/usr/local/bin/node"))
					.withArgument(GeneralServerFlag.LOG_LEVEL, "info").withIPAddress("127.0.0.1").withEnvironment(env)
					.usingPort(4723);
			AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
			service.start();
			System.out.println("**************************************************");
			System.out.println("			Appium is Started Successfully");
			System.out.println("**************************************************");
		} catch (Exception e) {
			System.out.println("**************************************************");
			System.out.println("			Appium is NOT Started Successfully");
			System.out.println("**************************************************");
			TestUtilities.logReportFailure(e);
		}
	}

	public void fnCloseTest() {
		if (ReportStatus.blnStatus == false) {
			AssertJUnit.fail();
		}
	}

	public static String getFullAppPath(String appPath) {
		String appFilePath = "none";
		try {
			if (!appPath.toLowerCase().equals("none")) {
				File app = new File(System.getProperty("user.dir") + appPath);
				if (app.listFiles() != null)
					appFilePath = app.getCanonicalPath() + "/" + app.listFiles()[0].getName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appFilePath;
	}

	public static String getWebDriverServerURL() {
		String ServerURL = "http://localhost:4444/wd/hub"; // Selenium Server URL
		try {
			if (ConfigDetails.appium) {
				if (ConfigDetails.startAppium)
					startServer();
				ServerURL = "http://localhost:4723/wd/hub"; // Appium Server URL
			}
			else if(ConfigDetails.targetExecution.equals("browserstack"))
				ServerURL = "http://" + ConfigDetails.strBS_Username + ":"+ ConfigDetails.strBS_AccessKey + "@hub.browserstack.com:80/wd/hub"; // Browserstack Server URL
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ServerURL;
	}


	public static void logReportFailure(Exception e, String... data) {
		String locatorName = "NA";
		String locator = "NA";
		if (data.length > 0) {
			locatorName = data[0];
			if (data.length > 1)
				locator = data[1];
		}
		ReporterLog.softAssert.fail();
		e.printStackTrace();
		ReporterLog.fail(Thread.currentThread().getStackTrace()[2].getMethodName(),
				"LocatorName: " + locatorName + "\n" + "Locator: " + locator + "\n" + "MethodName: "
						+ Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + "Line No: "
						+ e.getStackTrace()[0].getLineNumber() + "\n" + "And Exception is: " + e.toString());
	}

	public static void logReportFatal(Exception e, String... data) {
		String locatorName = "NA";
		String locator = "NA";
		if (data.length > 0) {
			locatorName = data[0];
			if (data[1] != null)
				locator = data[1];
		}
		e.printStackTrace();
		ReporterLog.fatal(Thread.currentThread().getStackTrace()[2].getMethodName(),
				"LocatorName: " + locatorName + "\n" + "Locator: " + locator + "\n" + "MethodName: "
						+ Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + "Line No: "
						+ e.getStackTrace()[0].getLineNumber() + "\n" + "And Exception is: " + e.toString());

	}

	public static void logReportPass(String description) {

		ReporterLog.pass(Thread.currentThread().getStackTrace()[2].getMethodName(),description);

	}

	/**
	 * Return file content as string.
	 *
	 * @param fileName the file name
	 * @return the string
	 */
	public static String returnFileContentAsString(String fileName) {
		InputStream is = null;
		StringBuilder sb = null;
		try {
			is = new FileInputStream(fileName);
			@SuppressWarnings("resource")
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line = null;
			line = buf.readLine();
			sb = new StringBuilder();
			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
		} catch (IOException io) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ io.getStackTrace()[0].getLineNumber() + " And Exception is", io);
		}
		return sb.toString();
	}

	public static void cleanUpFilesFolders() {
		try {
			for (String folder : new String[] { "./Reports", }) {
				File theDir = new File(folder);
				if (!theDir.exists())
					theDir.mkdir();

				for (File file : theDir.listFiles()) {
					if (file.isDirectory()) {
						for (File c : file.listFiles())
							c.delete();
					}
					if (!file.isDirectory())
						file.delete();
				}

			}
		} catch (Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}

	/**
	 * @author mdafsarali
	 * @param project
	 * @param strJSONKey
	 * @return JSON value for testdata
	 */
	public static String getJSONValue(String project,String strJSONKey) {

		String strEnvironment = ConfigDetails.environment.toLowerCase();
		String strCountry = ConfigDetails.country.toLowerCase();
		String jsonFileName = strEnvironment+"-"+project.toLowerCase().trim()+".json";

		String strFilePath = ReusableMethods.fnGetCurrentUserDir()+"/TestData/"+jsonFileName;

		JSONObject obj =JSONUtilities.readJSONFile(strFilePath);

		return JSONUtilities.getJsonValueUsingPath(obj.toString(), ""+strCountry+"."+strJSONKey).trim();
	}

	/**
	 * @author mdafsarali
	 * @description This method will select a random item from the List of Elements
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> T getRandomItem(List<T> list)
	{
		Random random = new Random();
		int listSize = list.size();
		int randomIndex = random.nextInt(listSize);
		return list.get(randomIndex);
	}

}

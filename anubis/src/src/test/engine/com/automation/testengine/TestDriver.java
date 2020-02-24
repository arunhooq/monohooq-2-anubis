package com.automation.testengine;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.automation.reports.ReporterLog;
import com.automation.utilities.APIUtils;
import com.automation.utilities.GlobalConstant;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDriver {

	@SuppressWarnings("rawtypes")
	public static void initiateDriverSession() {
		try {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			String appFilePath = TestUtilities.getFullAppPath(ConfigDetails.strAppPath);

			// Setting up capabilities
			switch (ConfigDetails.targetExecution.toLowerCase().trim()) {
			
				case "local":
					
						if(ConfigDetails.strPlatformName.equalsIgnoreCase("android") 
								|| ConfigDetails.strPlatformName.equalsIgnoreCase("androidtv")
								|| ConfigDetails.strPlatformName.equalsIgnoreCase("ios"))
							
							capabilities = setDriverCapabilities_ForLocal_MobileApps(capabilities, appFilePath);
						
						else {
							capabilities = setDriverCapabilities_ForLocal_Browsers(capabilities);
							
						}
						break;
				
				case "browserstack":
					
						if(ConfigDetails.strPlatformName.equalsIgnoreCase("android")|| ConfigDetails.strPlatformName.equalsIgnoreCase("iphone")
								|| ConfigDetails.strPlatformName.toLowerCase().equals("tablet")
								|| ConfigDetails.strPlatformName.toLowerCase().equals("ipad"))
							
							capabilities = setDriverCapabilities_ForBrowserStack_MobileApps(capabilities, appFilePath);
						else
							capabilities = setDriverCapabilities_ForBrowserStack_Browsers(capabilities);
					
						break;
				default:
					break;
			
			}
							
			// Driver initialization
			String serverURL = TestUtilities.getWebDriverServerURL();
			
			switch (ConfigDetails.strPlatformName.toLowerCase().trim()) {
				
			case "android": 
			case "androidtv": 
			case "tablet": 

					ActionEngine.driver = new AndroidDriver(new URL(serverURL), capabilities);
					break;
			
			case "ios": 
			case "iphone":
			case "ipad":			
					try {
						ActionEngine.driver = new IOSDriver(new URL(serverURL), setDriverCapabilities_ForBrowserStack_MobileApps(capabilities, appFilePath));
						
					if(((RemoteWebDriver) ActionEngine.driver).getSessionId() == null || ((RemoteWebDriver) ActionEngine.driver).getSessionId().toString().isEmpty()) {
						Thread.sleep(10000);//Wait for 10 sec
						ActionEngine.driver = new IOSDriver(new URL(serverURL), setDriverCapabilities_ForBrowserStack_MobileApps(capabilities, appFilePath));
					}
					}catch(Exception e) {
						e.printStackTrace();
						ReporterLog.warning("IOS Session Creation Check", "Session Terminated Hence Retrying .....");
						ActionEngine.driver = new IOSDriver(new URL(serverURL), setDriverCapabilities_ForBrowserStack_MobileApps(capabilities, appFilePath));
					}
					break;
			
			case "osx-safari":
			case "windows-chrome":
			case "windows-firefox":
				
					ActionEngine.driver = new RemoteWebDriver(new URL(serverURL), capabilities);
					ActionEngine.driver.manage().timeouts().pageLoadTimeout(Long.valueOf(ConfigDetails.strGlobalTimeOut), TimeUnit.SECONDS);
					ActionEngine.driver.manage().window().fullscreen();
					break;
					
			case "iphone-safari":
			case "ipad-safari":
				capabilities.setCapability("webkitResponseTimeout", "180000");
				
				try {
					ActionEngine.driver = new RemoteWebDriver(new URL(serverURL), capabilities);
					ActionEngine.driver.manage().timeouts().pageLoadTimeout(Long.valueOf(ConfigDetails.strGlobalTimeOut), TimeUnit.SECONDS);
					ActionEngine.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
				if(((RemoteWebDriver) ActionEngine.driver).getSessionId() == null || ((RemoteWebDriver) ActionEngine.driver).getSessionId().toString().isEmpty()) {
					Thread.sleep(10000);//Wait for 10 sec
					ActionEngine.driver = new RemoteWebDriver(new URL(serverURL), capabilities);
				}
				}catch(Exception e) {
					e.printStackTrace();
					ReporterLog.warning("WEB Session Creation Check", "Session Terminated Hence Retrying .....");
					ActionEngine.driver = new RemoteWebDriver(new URL(serverURL), capabilities);
				}
							
				break;	
			case "android-chrome":
			case "tablet-chrome":
				
					ActionEngine.driver = new RemoteWebDriver(new URL(serverURL), capabilities);
					ActionEngine.driver.manage().timeouts().pageLoadTimeout(Long.valueOf(ConfigDetails.strGlobalTimeOut), TimeUnit.SECONDS);
					ActionEngine.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					break;	
			
			default:
				
					ActionEngine.driver = new RemoteWebDriver(new URL(serverURL), capabilities);
					ActionEngine.driver.manage().timeouts().pageLoadTimeout(Long.valueOf(ConfigDetails.strGlobalTimeOut), TimeUnit.SECONDS);
					if(ConfigDetails.deviceName.equalsIgnoreCase("none")) // Means it is Desktop browser execution
						ActionEngine.driver.manage().window().fullscreen();
					break;
			}
		
			
			//CreateSession(serverURL, capabilities);
			ActionEngine.driver.manage().timeouts().implicitlyWait(GlobalConstant.WAIT_SHORT_10_SEC, TimeUnit.SECONDS);

			GlobalConstant.BROWSERSTACK_SESSION_ID = ActionEngine.driver.getSessionId().toString().trim();
			System.out.println("########## DRIVER SESSION: " + ActionEngine.driver.getSessionId().toString().trim());
			ReporterLog.info("BeforeSuite", "Driver initialization successful: "+ ActionEngine.driver.toString());
		}	
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
	}
		
	public static DesiredCapabilities setDriverCapabilities_ForLocal_MobileApps(DesiredCapabilities capabilities, String appFilePath){
		try {
			
			if(ConfigDetails.strPlatformName.equalsIgnoreCase("androidtv"))
				capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
			else
				capabilities.setCapability(MobileCapabilityType.PLATFORM, ConfigDetails.strPlatformName);
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigDetails.strPlatformVersion);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigDetails.deviceName);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,ConfigDetails.strNewCommandTimeOut);
			if (!appFilePath.toLowerCase().equalsIgnoreCase("none"))
				capabilities.setCapability(MobileCapabilityType.APP, appFilePath);
			
			switch (ConfigDetails.strPlatformName.toLowerCase().trim()) {
				case "android":
					capabilities.setCapability("automationName","UiAutomator2");
					capabilities.setCapability("appPackage", ConfigDetails.strAppPackage);
					capabilities.setCapability("appActivity", ConfigDetails.strAppActivity);
					capabilities.setCapability("noReset", Boolean.parseBoolean(ConfigDetails.strNoReset));
					capabilities.setCapability("fullReset", Boolean.parseBoolean(ConfigDetails.strFullReset));
					//capabilities.setCapability("optionalIntentArguments","--ez inAppMessageEnabled false"); 					
					//capabilities.setCapability("optionalIntentArguments","--es apiEnv nightly --es discoverEnv nightly --ez inAppMessageEnabled false");
					//capabilities.setCapability("optionalIntentArguments","--es apiEnv "+ConfigDetails.environment+" --es discoverEnv "+ConfigDetails.environment+" --ez inAppMessageEnabled false");
					if(ConfigDetails.environment.equalsIgnoreCase("stag"))
						capabilities.setCapability("optionalIntentArguments","--es apiEnv nightly --es discoverEnv nightly --ez inAppMessageEnabled false");
					else
						capabilities.setCapability("optionalIntentArguments","--es apiEnv prod --es discoverEnv prod --ez inAppMessageEnabled false");
					
					break;
				case "androidtv":
					capabilities.setCapability("automationName","UiAutomator2");
					capabilities.setCapability("appPackage", ConfigDetails.strAppPackage);
					capabilities.setCapability("appActivity", ConfigDetails.strAppActivity);
					capabilities.setCapability("noReset", Boolean.parseBoolean(ConfigDetails.strNoReset));
					capabilities.setCapability("fullReset", Boolean.parseBoolean(ConfigDetails.strFullReset));
					
					break;
		
				case "ios":
					capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 10000);
					capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
					capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, ConfigDetails.strBundleID);
					capabilities.setCapability(MobileCapabilityType.UDID, ConfigDetails.strUDID);
					capabilities.setCapability("useNewWDA", "true");
					capabilities.setCapability("disableWindowAnimation", "true");
					capabilities.setCapability("debug", "false");
					capabilities.setCapability("log_level", "info");
					capabilities.setCapability("xcodeOrgId", "Q378LCLGKT");
					capabilities.setCapability("xcodeSigningId", "iPhone Developer");
					
					break;
			default:
				break;
			}
			
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
		return capabilities;
	}
	public static DesiredCapabilities setDriverCapabilities_ForLocal_Browsers(DesiredCapabilities capabilities){
		
		System.out.println("inside setDriverCapabilities_ForLocal_Browsers");
		try {
			switch (ConfigDetails.browser.toLowerCase().trim()) {
	
			case "chrome":
				
				if (ConfigDetails.appium) {
					capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigDetails.strPlatformVersion);
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigDetails.deviceName);
					capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, ConfigDetails.browser);

				} else {
					WebDriverManager.chromedriver().setup();
					capabilities = DesiredCapabilities.chrome();
					ChromeOptions chromeoptions = new ChromeOptions();
					chromeoptions.addArguments("test-type");
					chromeoptions.addArguments("disable-session-crashed-bubble");
					chromeoptions.addArguments("disable-popup-blocking");
	
					if (!ConfigDetails.deviceName.equalsIgnoreCase("none")) {
						Map<String, String> mobileEmulation = new HashMap<>();
						mobileEmulation.put("deviceName", ConfigDetails.deviceName);
						chromeoptions.setExperimentalOption("mobileEmulation", mobileEmulation);
					}
					capabilities.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
					capabilities.setCapability("browserName", ConfigDetails.browser);
				}
	
				break;
			case "iphone-safari":
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigDetails.strPlatformVersion);
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigDetails.deviceName);	
				break;
				
			case "iphone-chrome":
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigDetails.strPlatformVersion);
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigDetails.deviceName);	
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("browserName", ConfigDetails.browser);
				break;
	
			case "safari":
				WebDriverManager.firefoxdriver().setup();
				capabilities = DesiredCapabilities.safari();
				capabilities.setCapability("browserName", ConfigDetails.browser);
				break;
	
			case "edge":
				WebDriverManager.edgedriver().setup();
				capabilities = DesiredCapabilities.edge();
				capabilities.setCapability("browserName", ConfigDetails.browser);
				break;
	
			case "ie":
				WebDriverManager.iedriver().setup();
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("browserName", ConfigDetails.browser);
				break;
				
			default:
				break;
			}
	
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("acceptInsecureCerts", true);
		}
			catch(Exception e) {
				Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
			}
		return capabilities;

	}
	
	public static DesiredCapabilities setDriverCapabilities_ForBrowserStack_Browsers(DesiredCapabilities capabilities) {
		try {
			switch (ConfigDetails.strPlatformName.toLowerCase().trim()) {

			case "osx-safari":
				capabilities.setCapability("browserName", "Safari");
				capabilities.setCapability("browser", "Safari");
				capabilities.setCapability("browser_version", "12.0");
				capabilities.setCapability("resolution", "1920x1080");
				capabilities.setCapability("os", "OS X");
				capabilities.setCapability("os_version", "Mojave");
				capabilities.setCapability("project", "Web Automation - OSX");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			case "windows-chrome":
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("browser", "Chrome");
				capabilities.setCapability("browser_version", "73.0");
				capabilities.setCapability("resolution", "2048x1536");
				capabilities.setCapability("os", "Windows");
				capabilities.setCapability("os_version", "10");
				capabilities.setCapability("project", "Web Automation - Windows");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			case "windows-firefox":
				capabilities.setCapability("browserName", "Firefox");
				capabilities.setCapability("browser", "Firefox");
				capabilities.setCapability("browser_version", "65.0 beta");
				capabilities.setCapability("resolution", "2048x1536");
				capabilities.setCapability("os", "Windows");
				capabilities.setCapability("os_version", "10");
				capabilities.setCapability("project", "Web Automation - Windows");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			case "iphone-safari":
				capabilities.setCapability("browserName", "iPhone");
				//capabilities.setCapability("device", "iPhone XR");
				capabilities.setCapability("device", "iPhone XR");
				capabilities.setCapability("os", "iOS");
				capabilities.setCapability("os_version", "12");
				capabilities.setCapability("webkitResponseTimeout", "180000");
				capabilities.setCapability("project", "Web Automation - iPhone");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			case "ipad-safari":
				capabilities.setCapability("browserName", "iPhone");
				capabilities.setCapability("device", "iPad Air 2019");
				capabilities.setCapability("os", "iOS");
				capabilities.setCapability("os_version", "12");
				capabilities.setCapability("project", "Web Automation - iPad");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			case "android-chrome":
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("device", "Google Pixel 3 XL");
				capabilities.setCapability("os", "android");
				capabilities.setCapability("os_version", "9.0");
				capabilities.setCapability("project", "Web Automation - Android");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			case "tablet-chrome":
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("device", "Samsung Galaxy Tab S4");
				capabilities.setCapability("os", "android");
				capabilities.setCapability("os_version", "8.1");
				capabilities.setCapability("project", "Web Automation - Tablet");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				break;
			default:
				break;
			}
			
			capabilities.setCapability("newCommandTimeout", 300);
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("acceptInsecureCerts", true);
			
			capabilities.setCapability("realMobile", true);
			capabilities.setCapability("browserstack.debug", true);
			capabilities.setCapability("browserstack.console", "verbose");
			capabilities.setCapability("browserstack.networkLogs", true);
			
			System.out.println("BROWSERSTACK ConfigDetails.country: "+ConfigDetails.country);
			capabilities.setCapability("browserstack.geoLocation", ConfigDetails.country);

		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
		return capabilities;

	}
	
	public static DesiredCapabilities setDriverCapabilities_ForBrowserStack_MobileApps(DesiredCapabilities capabilities, String appFilePath) {
		try {
			
			switch (ConfigDetails.strPlatformName.toLowerCase().trim()) {

			case "iphone":
				capabilities.setCapability("device", "iPhone XR");
				capabilities.setCapability("os", "iOS");
				capabilities.setCapability("os_version", "12");
				capabilities.setCapability("project", "iOS App Automation");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				//capabilities.setCapability("app", APIUtils.uploadFileToBrowserStackCloud(appFilePath));
				capabilities.setCapability("app", APIUtils.getLatestBrowserstackApps());
				
				
				break;
			case "ipad":
				capabilities.setCapability("device", "iPad Air 2019");
				capabilities.setCapability("os", "iOS");
				capabilities.setCapability("os_version", "12");
				capabilities.setCapability("project", "iOS App Automation");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				//capabilities.setCapability("app", APIUtils.uploadFileToBrowserStackCloud(appFilePath));
				capabilities.setCapability("app", APIUtils.getLatestBrowserstackApps());
				break;
			case "android":
				capabilities.setCapability("device", "Samsung Galaxy S8 Plus");				
				capabilities.setCapability("os", "android");
				capabilities.setCapability("os_version", "9.0");
				//capabilities.setCapability("optionalIntentArguments","--es apiEnv nightly --es discoverEnv nightly --ez inAppMessageEnabled false");
				if(ConfigDetails.environment.equalsIgnoreCase("stag"))
					capabilities.setCapability("optionalIntentArguments","--es apiEnv nightly --es discoverEnv nightly --ez inAppMessageEnabled false");
				else
					capabilities.setCapability("optionalIntentArguments","--es apiEnv prod --es discoverEnv prod --ez inAppMessageEnabled false");
				capabilities.setCapability("project", "Android App Automation");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);

				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				//capabilities.setCapability("app", APIUtils.uploadFileToBrowserStackCloud(appFilePath));
				capabilities.setCapability("app", APIUtils.getLatestBrowserstackApps());

				
				break;
			case "tablet":
				capabilities.setCapability("device", "Samsung Galaxy Tab S4");
				capabilities.setCapability("os", "android");
				capabilities.setCapability("os_version", "8.1");
				capabilities.setCapability("project", "Android App Automation");
				capabilities.setCapability("build", ConfigDetails.jenkinsBuildName);
				capabilities.setCapability("name", ConfigDetails.project+"-"+ConfigDetails.country+"-"+ConfigDetails.userType);
				//capabilities.setCapability("app", APIUtils.uploadFileToBrowserStackCloud(appFilePath));
				capabilities.setCapability("app", APIUtils.getLatestBrowserstackApps());
				break;
			default:
				break;
			}
			capabilities.setCapability("newCommandTimeout", 300);
			capabilities.setCapability("realMobile", true);
			capabilities.setCapability("browserstack.debug", true);
			capabilities.setCapability("browserstack.console", "verbose");
			capabilities.setCapability("browserstack.networkLogs", true);
			capabilities.setCapability("browserstack.geoLocation", ConfigDetails.country);
		}
		catch(Exception e) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ e.getStackTrace()[0].getLineNumber() + " And Exception is", e);
		}
		return capabilities;

	}
	//This Method will provide random available browserstack iphone devices.
	public static String getBrowserstack_iPhonedevice() {
		String[] lisOfAvailableDevices_OS12 = {"iPhone XS","iPhone 8 Plus","","iPhone 7","iPhone 6S","iPhone XR","iPhone XS Max"};
		Random r=new Random();
        int randomNumber=r.nextInt(lisOfAvailableDevices_OS12.length);
       String deviceName = lisOfAvailableDevices_OS12[randomNumber];
       ReporterLog.info("Device Selection", "Selecting Browserstack device as : "+deviceName);
       return deviceName;		
	}
	
}

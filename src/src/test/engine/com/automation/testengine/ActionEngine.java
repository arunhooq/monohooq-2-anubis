package com.automation.testengine;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.reports.ReporterLog;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import android.tests.TestConfiguration;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import web.pages.DiscoverPage;
import web.utils.webConstants;

public class ActionEngine {

	public static WebDriverWait wait;
	public static RemoteWebDriver driver = null;
	public static web.data.AudioSubTitlesBean audioSubTitlesBean = new web.data.AudioSubTitlesBean();

	public static String getScreenshot(WebDriver driver, String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "/Reports/" + screenshotName + dateName + ".png";
		try {

			WebDriver driverForScreenshot = driver;

			TakesScreenshot ts = (TakesScreenshot) driverForScreenshot;

			File source = ts.getScreenshotAs(OutputType.FILE);

			File finalDestination = new File(destination);

			FileUtils.copyFile(source, finalDestination);
			ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Screenshot is captured successfully");

		} catch (IOException e) {
			TestUtilities.logReportFailure(e, driver.toString());
			e.printStackTrace();
		}
		return destination;
	}

	public static void performLinkCheck(String startUrl) {
		try {
			HttpURLConnection connecton = null;
			int respCode = 200;
			ActionEngine.launchUrl(startUrl);

			List<WebElement> links = driver.findElements(By.tagName("a"));

			Iterator<WebElement> elements = links.iterator();

			ReporterLog.info("linkCheck on " + startUrl,
					"Fetched links by tagName <a> and total " + links.size() + " number Of Links found in page");

			Integer maxTries = 5;

			while (elements.hasNext()) {

				Boolean linkOK = false;

				while (!linkOK) {

					Integer currTry = 1;

					WebElement element = elements.next();
					String url = element.getAttribute("href");
					if (url == null || url.isEmpty()) {

						ReporterLog.warning("linkCheck",
								"Anchor tag is empty: outerHTML: " + element.getAttribute("outerHTML").toString()
								+ "\n innerHTML: " + element.getAttribute("innerHTML").toString());
						continue;
					}

					ReporterLog.info("linkCheck","Attempting to follow link: '"+url+"'");
					connecton = (HttpURLConnection) (new URL(url).openConnection());

					connecton.setRequestMethod("HEAD");

					connecton.connect();

					respCode = connecton.getResponseCode();

					if (respCode >= 400) {
						ReporterLog.warning("linkCheck", url + "is a BROKEN LINK and status code is: " + respCode);
						currTry += 1;
						if (currTry <= maxTries) {
							ReporterLog.info("linkCheck", "Retrying link, attempt "+currTry.toString()+"/"+maxTries.toString());
						} else {
							ReporterLog.fail("linkCheck", url + "is a BROKEN LINK and status code is: " + respCode);
						}
					} else {
						linkOK = true;
						ReporterLog.pass("linkCheck", url + " " + element.getText());
					}
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}

	@Deprecated
	public static void mobileSwipeByElement(String swipeType, double swipeLocation) {
		try {
			Dimension size = driver.manage().window().getSize();
			if (swipeType.equalsIgnoreCase("lefttoright") || swipeType.equalsIgnoreCase("righttoleft")) {
				int startx = (int) (size.width * 0.70);
				int endx = (int) (size.width * 0.30);
				int starty = (int) (size.height / swipeLocation);

				if (swipeType.equalsIgnoreCase("lefttoright"))
					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(endx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,starty )).release().perform();

				if (swipeType.equalsIgnoreCase("righttoleft"))
					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx,starty )).release().perform();
			}
			if (swipeType.equalsIgnoreCase("toptobottom") || swipeType.equalsIgnoreCase("bottomtotop")) {
				int starty = (int) (size.height * 0.60);
				int endy = (int) (size.height * 0.40);
				int startx = size.width / 2;
				if (swipeType.equalsIgnoreCase("bottomToTop"))		
					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,endy )).release().perform();
				if (swipeType.equalsIgnoreCase("toptobottom"))

					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,starty )).release().perform();
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}

	@Deprecated
	public static void swipeTill(String strtext) {
		try {
			while (true) {
				String header = null;
				List<WebElement> railHeaderList = driver.findElements(By.id("tv.hooq.android:id/title_text"));
				for (int i = 0; i <= railHeaderList.size() - 1; i++) {
					header = railHeaderList.get(i).getText();
					if (header.equalsIgnoreCase(strtext))
						break;
				}
				if (header.equalsIgnoreCase(strtext))
					break;
				swipeUpOrBottom(true);
			}
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
			e.printStackTrace();
		}
	}

	/**
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:Login Button, SignIn
	 *                    Link etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */

	public static void clickNoWait(By locator, String locatorName) {

		try {
			driver.findElement(locator).click();
			ReporterLog.info("Click On the Element", locatorName +" is Clicked .");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}

	public static String getPageSource() {
		String pageSource = "";
		try {
			pageSource = driver.getPageSource();
			if (pageSource == null)
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

		return pageSource;
	}

	/**
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:Login Button, SignIn
	 *                    Link etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */

	public static void click(By locator, String locatorName) {

		try {			
			explicitWaitForClickableOfElement(locator, Integer.valueOf(ConfigDetails.strGlobalTimeOut)).click();
			ReporterLog.info("Click On the Element", locatorName +" is Clicked .");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}


	public static void click_NEW(By locator, String locatorName) throws Exception {

		try {			
			explicitWaitForClickableOfElement(locator, Integer.valueOf(ConfigDetails.strGlobalTimeOut)).click();
			ReporterLog.info("Click On the Element", locatorName +" is Clicked .");
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			throw new Exception("Exception when clicking on: "+locatorName);
		}
	}

	public static void clickAllowOnAlert() {

		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param testdata    : Value wish to type in text box / text area
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Textbox,Text Area
	 *                    etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public static void type(By locator, String testdata, String locatorName) {
		try {
			waitForVisibilityOfElement(locator, locatorName);
			WebElement we = driver.findElement(locator);
			we.clear();
			we.sendKeys(testdata);
			ReporterLog.info("Type Data ", "Entering Data : "+testdata);
			if (ConfigDetails.strPlatformName.toLowerCase().equalsIgnoreCase(GlobalConstant.PROJECT_ANDROID)) {
				getAndroidDriver().hideKeyboard();
				ReporterLog.info("Hide Keyboard ", "Hiding Android Keyboard while typing..");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			System.out.println("Unable to type on the input box : "+locator.toString() +" Staus : FAIL");
			e.printStackTrace();
		}
	}

	//TODO - Need to reuse existing functions
	public static void type_NEW(By locator, String testdata, String locatorName) throws Exception{
		try {
			waitForVisibilityOfElement(locator, locatorName);
			WebElement we = driver.findElement(locator);
			we.clear();
			we.sendKeys(testdata);
			if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {
				AndroidDriver<?> AndroidDriver2 = (AndroidDriver<?>) driver;
				(AndroidDriver2).hideKeyboard();
			}

		} catch (Exception e) 
		{
			//TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			System.out.println("Unable to type on the input box : "+locator.toString() +" Staus : FAIL");
			e.printStackTrace();
			throw new Exception("Exception encountered whilst attempting to type into: "+ locatorName);
		}
	}
	public static void typeNoWait(By locator, String testdata, String locatorName) {
		try {
			WebElement we = driver.findElement(locator);
			we.clear();
			we.sendKeys(testdata);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			System.out.println("Unable to type on the input box : "+locator.toString() +" Staus : FAIL");
			e.printStackTrace();

		}
	}

	/**
	 * To launch URL
	 * 
	 * @param url : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public static void launchUrl(String url) {
		try {
			By hamburgerMenu = By.xpath("//*[@id=\"mount\"]/header[1]/div/a[2]/img");
			driver.get(url);
			Thread.sleep(1000);
			checkValidGeoLocation();
			waitForElementClickable(hamburgerMenu, "hamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, url.toString());
			System.out.println("Launch URL Failed .");
			e.printStackTrace();
		}
	}

	//TODO -Need to use existing functions
	public static void launchUrl_NEW(String url) throws Exception {
		try {
			By hamburgerMenu = By.xpath("//*[@id=\"mount\"]/header[1]/div/a[2]/img");
			driver.get(url);
			Thread.sleep(5000);
			checkValidGeoLocation_NEW(url);
			waitForElementClickable_NEW(hamburgerMenu, "hamburgerMenu");
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e, url.toString());
			ReporterLog.info("Launch URL","Launch URL Failed .");
			e.printStackTrace();
			driver=null;
			web.tests.TestConfiguration.createNewSessionIfNotAvailable();
			//throw new Exception("Failed in launchURL");
		}
	}

	/**
	 * To Open URL
	 * 
	 * @param url : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public static void openUrl(String url) {
		try {
			driver.get(url);
			Thread.sleep(1000);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, url.toString());
			System.out.println("Open URL Failed .");
			e.printStackTrace();
		}
	}

	/**
	 * To Open URL
	 * 
	 * @param url : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public static String getCurrentUrl() {
		String url = "";
		try {
			url = driver.getCurrentUrl();
			if (url == null)
				throw new Exception();
			Thread.sleep(1000);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
		return url;
	}

	public static void checkValidGeoLocation() {
		//test for VPN failure on browserstack only
		if(ConfigDetails.targetExecution.equalsIgnoreCase("browserstack")) 
		{
			if (! ActionEngine.getCurrentUrl().contains("hooq.tv/"+ConfigDetails.country.toLowerCase())) 
			{
				try {
					webConstants.geoLocationFailed = true;
					ReporterLog.info("Launch URL:", "Instead of hooq.tv/"+ConfigDetails.country.toLowerCase()+" we arrived at "+ActionEngine.getCurrentUrl());

					//open new site to obtain ip address
					ReporterLog.info("GeoLocation Check", "Verifying current VPN i.p, in use...");

					String originalURL = driver.getCurrentUrl();


					driver.get("https://www.expressvpn.com/what-is-my-ip");
					Thread.sleep(1000);

					String myip=ActionEngine.getText(By.cssSelector("[class*='ip-address']"), null);
					String myLocation=ActionEngine.getText(By.cssSelector("h4:nth-child(2)"), null);

					ReporterLog.info("GeoLocation Check", "VPN i.p. address in use is: "+myip);
					ReporterLog.info("GeoLocation Check", "VPN i.p. location is: "+myLocation);

					DiscoverPage.getUrlErrorDetails();

					driver.get(originalURL);

					throw new Exception();

				} catch (Exception e) {
					TestUtilities.logReportFailure(e,"Failed GeoLocation Check");
					e.printStackTrace();
				}
			} else {
				ReporterLog.info("GeoLocationCheck", "Successfully landed at: "+"hooq.tv/"+ConfigDetails.country.toLowerCase());
			}
			//Alternatively are we seeing any error message?
			try {
				if (ActionEngine.isElementPresentInDom(By.cssSelector("div[class*='GeoLock__ErrorText']"))){
					String tempText=ActionEngine.getText(By.cssSelector("div[class*='GeoLock__ErrorText']"), "Error Text");
					if (tempText.equalsIgnoreCase("Sorry, we’re currently not available in your region.")) {
						webConstants.geoLocationFailed = false;
						ReporterLog.fail("GeoLocation Check", "GeoLocation Failure: Detected text 'Sorry, we’re currently not available in your region.'");
						Assert.fail();
					}
				} else {
					ReporterLog.info("GeoLocationCheck", "HOOQ is available in this region");
				}
			}
			catch(Exception e) {
				ReporterLog.info("GeoLocation Check", "GeoLocation confirmed as being in "+ConfigDetails.country.toUpperCase());
			}		
		}
	}

	//TODO - Need to use existing functions
	public static void checkValidGeoLocation_NEW(String url) throws Exception {
		//test for VPN failure on browserstack only

		if(ConfigDetails.targetExecution.equalsIgnoreCase("browserstack")) 
		{
			if (! ActionEngine.getCurrentUrl().contains("hooq.tv/"+ConfigDetails.country.toLowerCase())) 
			{
				try {
					webConstants.geoLocationFailed = true;
					String arrivedAt = ActionEngine.getCurrentUrl();
					ReporterLog.info("Launch URL:", "Instead of hooq.tv/"+ConfigDetails.country.toLowerCase()+" we arrived at "+arrivedAt);
					ReporterLog.info("Geolocation Failure", "Time error occured: "+FileUtilities.GetCurrentTimeStamp());
					
					String originalURL = url;
					Boolean stillFailed = true;
					
					if(arrivedAt.contains("mobile-internet-check")) {
						ReporterLog.info("GeoLocation Error", "Detected we arrived at Browserstack;'s mobile internet check page");
						ReporterLog.info("GeoLocation Error", "Wait 30 seconds to see if we get redirected");
						Thread.sleep(30000);
						arrivedAt = ActionEngine.getCurrentUrl();
						if (arrivedAt.contains("hooq.tv/"+ConfigDetails.country.toLowerCase())) {
							stillFailed = false;
						} else {
							ReporterLog.info("Launch URL:", "Instead of hooq.tv/"+ConfigDetails.country.toLowerCase()+" we arrived at "+arrivedAt);
							ReporterLog.info("Geolocation Failure", "Time error occured: "+FileUtilities.GetCurrentTimeStamp());
						}
					}
					
					if (stillFailed) {
					
						//open new site to obtain ip address
						ReporterLog.info("GeoLocation Check", "Verifying current VPN i.p, in use...");
	
	/*					driver.get("https://www.expressvpn.com/what-is-my-ip");
						Thread.sleep(1000);
	
			    	  		String myip=ActionEngine.getText(By.cssSelector("[class*='ip-address']"), null);
			    	  		String myLocation=ActionEngine.getText(By.cssSelector("h4:nth-child(2)"), null);*/
			    	  		
						driver.get("https://mylocation.org/");
						Thread.sleep(10000);
						
						String nowURL = ActionEngine.getCurrentUrl();
						
						if (nowURL.contains("mylocation.org")){
						
							String myip=ActionEngine.getText(By.xpath("//*[@id='ui-accordion-accordion-panel-0']/div/div[1]/table/tbody/tr[1]/td[2]/b"),"ip address");
							String myLocation=ActionEngine.getText(By.xpath("//*[@id='ui-accordion-accordion-panel-0']/div/div[1]/table/tbody/tr[4]/td[2]"),"ip address");
							
							ActionEngine.getScreenshot(driver, "GeoLocation_Error");
							
				    	  		ReporterLog.info("GeoLocation Check", "VPN i.p. address in use is: "+myip);
				    	  		ReporterLog.info("GeoLocation Check", "VPN i.p. location is: "+myLocation);
			    	  		
				    	  		DiscoverPage.getUrlErrorDetails();
						} else {
							ReporterLog.info("GeoLocationCheck","Unable to launch https://mylocation.org");
						}
			    	  		
			    	  		driver.get(originalURL);
					    	  					
						throw new Exception("GeoLocation check failure.");
					}
						
			} catch (Exception e) {
				//TestUtilities.logReportFailure(e,"Failed GeoLocation Check");
				e.printStackTrace();
				throw new Exception("Failed in GeoLocationCheck");
			}
				
			} else {
				ReporterLog.info("GeoLocationCheck", "Successfully landed at: "+"hooq.tv/"+ConfigDetails.country.toLowerCase());
			}
			//Alternatively are we seeing any error message?
			try {
				if (ActionEngine.isElementPresentInDom(By.cssSelector("div[class*='GeoLock__ErrorText']"))){
					String tempText=ActionEngine.getText(By.cssSelector("div[class*='GeoLock__ErrorText']"), "Error Text");
					if (tempText.equalsIgnoreCase("Sorry, we’re currently not available in your region.")) {
						webConstants.geoLocationFailed = false;
						ReporterLog.fail("GeoLocation Check", "GeoLocation Failure: Detected text 'Sorry, we’re currently not available in your region.'");
						Assert.fail();
					}
				} else {
					ReporterLog.info("GeoLocationCheck", "HOOQ is available in this region");
				}
			}
			catch(Exception e) {
				ReporterLog.info("GeoLocation Check", "GeoLocation confirmed as being in "+ConfigDetails.country.toUpperCase());
				throw new Exception("GeoLocation Error thrown");
			}		
		}
	}

	/**
	 * Assert text on element
	 * 
	 * @param by   : Action to be performed on element (Get it from Object
	 *             repository)
	 * 
	 * @param text : expected text to assert on the element
	 * 
	 */
	
	public static void pollForText(By locator, String text) {
		int count = 0;
		Boolean success = false;
		String myText = "";
		try {
			while(!success && count <  (Integer.valueOf(ConfigDetails.strGlobalTimeOut)*2)) {
				myText=driver.findElement(locator).getText();
				if (myText.equalsIgnoreCase(text)) {
					ReporterLog.info("Waiting for Text", "Text Matched, exiting");
					success = true;
				} else {
					ReporterLog.info("Waiting for Text", "Text did not match. Currently '"+myText+"' - waiting for another 500ms");
					count++;
					Thread.sleep(500);
				}
			}
			if (!success) {
				ReporterLog.info("pollForText", "Failed after a "+ConfigDetails.strGlobalTimeOut+" second poll.");
				throw new Exception();				
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), text.toString());
			e.printStackTrace();
		}
	}

	public static void verifyText(By locator, String text) {

		try {
			String locText=getText(locator,"locator");

			if (!locText.trim().equalsIgnoreCase(text.trim())) {
				ReporterLog.info("verify Element text", "Actual text : "+locText+" and Expected text : "+text + " did not Match ");
				throw new Exception();
			}else {
				ReporterLog.pass("verify Element text", "Actual and Expected text : "+text + " Matched ");				
			}

		} catch (Exception e) {
			System.out.println("Verify Text Failed . Expected text is : '"+text.trim()+"' and Actual text was : '"+getText(locator, "").trim()+"'");
			TestUtilities.logReportFailure(e, locator.toString(), text.toString());
			e.printStackTrace();
		}
	}
	
	public static void verifyText(By locator, String expectedText ,String locatorName) {

		String locText = "";
		try {
			 locText=getText(locator,locatorName);

			if (!locText.trim().equalsIgnoreCase(expectedText.trim())) {
				ReporterLog.info("verify Element text", "Actual text : "+locText+" and Expected text : "+expectedText + " did not Match ");
				throw new Exception("Actual text : "+locText+" and Expected text : "+expectedText + " did not Match ");
			}else {
				ReporterLog.pass("verify Element text", "Actual and Expected text : "+expectedText + " Matched ");				
			}

		} catch (Exception e) {
			System.out.println("Verify Text Failed . Expected text is : '"+expectedText.trim()+"' and Actual text was : '"+locText+"'");
			TestUtilities.logReportFailure(e, locatorName, expectedText);
			e.printStackTrace();
		}
	}
	
	public static void verifyText(WebElement element, String text) {

		try {		
			if (!element.getText().trim().equalsIgnoreCase(text.trim())) {
				ReporterLog.failAndContinue("verify Element text", "Actual text : "+element.getText()+" and Expected text : "+text + " did not Matched ");
				throw new Exception();
			}else {
				ReporterLog.pass("verify Element text", "Actual and Expected text : "+text + " Matched ");				
			}

		} catch (Exception e) {
			System.out.println("Verify Text Failed . Expected text is : '"+text.trim()+"' and Actual text was : '"+element.getText()+"'");
			TestUtilities.logReportFailure(e, element.getText(), text.toString());
			e.printStackTrace();
		}

	}

	public static void verifyTextNotNull(String text) {

		try {
			if (text.trim() == null)
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, "text is null: " + text);
			System.out.println("text is null not as expected : "+text );
			e.printStackTrace();
		}

	}


	public static void verifyText(String actualtext, String expectedText) {
		try {
			if (!actualtext.trim().equalsIgnoreCase(expectedText.trim())) {
				ReporterLog.failAndContinue("verify Element text", "Actual text : "+actualtext+" and Expected text : "+expectedText + " did not Matched ");
				throw new Exception("Actual text : "+actualtext+" and Expected text : "+expectedText + " did not Matched ");
			}else {
				ReporterLog.pass("verify Element text", "Actual and Expected text : "+expectedText + " Matched ");	
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, "The Actual text" +actualtext );
			System.out.println("Actual text and expected text did not match");
			e.printStackTrace();
		}
	}
	
	public static void verifyText_NEW(String text1, String text2) throws Exception{

		try {
			if (!text1.trim().equalsIgnoreCase(text2.trim())) {
				//ReporterLog.failAndContinue("verify Element text", "Actual text : "+text1+" and Expected text : "+text2 + " did not Match ");
				throw new Exception();
			}else {
				ReporterLog.pass("verify Element text", "Actual and Expected text : "+text2 + " Matched ");	
			}
		} catch (Exception e) {
			System.out.println("Actual text and expected text did not match");
			e.printStackTrace();
			throw new Exception("verifyText Error thrown");
		}
	}

	public static Boolean exactTextMatch(String text1, String text2) {

		if (text1.trim().equalsIgnoreCase(text2.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean partialTextMatch(String text1, String text2) {

		if (text1.trim().contains(text2.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static void verifyArrayDeepEquals(Object[] arr1, Object[] arr2) {

		try {
			if (!Arrays.deepEquals(arr1, arr2))
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, Arrays.deepToString(arr1), Arrays.deepToString(arr2));
			e.printStackTrace();
		}

	}

	public static void verifyTextContains(String text1, String text2) {

		String trimmedText ="";
		if (text1.length() > 1024) {
			trimmedText = text1.substring(0, 1024)+" .........";
		}else {
			trimmedText =text1;
		}

		try {
			if (!text1.trim().contains(text2.trim())) {
				throw new Exception();
			}else {
				ReporterLog.pass("Text Validation", "The text : "+ trimmedText +" contains text  "+text2);
			}	
		} catch (Exception e) {
			ReporterLog.fail("Text Validation", "The text : "+ trimmedText+" does not contain text  "+text2);
			TestUtilities.logReportFailure(e, text1, text2);
			e.printStackTrace();
		}
	}

	public static void verifyTextContains(String text1, String text2 ,String message) {

		String trimmedText ="";
		if (text1.length() > 1024) {
			trimmedText = text1.substring(0, 1024)+" .........";
		}else {
			trimmedText =text1;
		}

		try {
			if (!text1.trim().contains(text2.trim())) {
				throw new Exception("Expected and Actual Text did not matched.");
			}else {

				ReporterLog.pass("Text Validation for  "+message, "The text : "+ trimmedText +" contains text  "+text2);
			}	
		} catch (Exception e) {

			ReporterLog.fail("Text Validation for "+message, "The text : "+ trimmedText +" does not contain text  "+text2);
			TestUtilities.logReportFailure(e, text1, text2);
			e.printStackTrace();
		}
	}

	public static void verifyTextNotEquals(String text1, String text2) {

		try {
			if (text1.trim().equalsIgnoreCase(text2.trim()))
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, text1, text2);
			e.printStackTrace();
		}
	}

	public static void getTextAndVerify(By locator, String locatorName, By locator2, String locator2Name) {

		try {
			if (!getText(locator, locatorName).trim().equalsIgnoreCase(getText(locator2, locator2Name).trim()))
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locatorName.toString(), locator2Name.toString());
			e.printStackTrace();
		}

	}

	/**
	 * Assert text on element
	 * 
	 * @param by          : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param text        : expected text to assert on the element
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:link text, label text
	 *                    etc..)
	 * 
	 */
	public static void verifyTextContains(By locator, String text, String locatorName) {
		try {
			if (!getText(locator, text).trim().contains(text.trim())) {
				ReporterLog.fail("Element Text Validation", "Element Text doesnot match with Expected , Actual Text is : "+getText(locator, text) +" While Expected Text was : "+text);
				throw new Exception();
			}else {
				ReporterLog.pass("Element Text Validation", "Element Text  matched with Expected , Actual Text is : "+getText(locator, text) +" and Expected Text was : "+text);	
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}


	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by          : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param attribute   : Attribute name wish to assert the value.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:label, SignIn Link
	 *                    etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public static String getAttributeValue(By locator, String attribute, String locatorName) {

		String value = "";
		try {
			value = driver.findElement(locator).getAttribute(attribute);
			if (value == "" || value == null)
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
		return value;
	}
	
	
	public static String getAttributeValue(WebElement element, String attribute, String locatorName) {

		String value = "";
		try {
			value = element.getAttribute(attribute);
			if (value == "" || value == null)
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, element.toString(), locatorName.toString());
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * The innerText of this element.
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:label text, SignIn
	 *                    Link etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public static String getText(By locator, String locatorName) {
		//ReporterLog.info("getText", "==============> entered getText section");
		String text = "";
		try {
			text = driver.findElement(locator).getText();
			//ReporterLog.info("WITHIN GETTEXT", "FOUND TEXT: '"+text+"'");
			if (text == "" || text == null)
				throw new Exception("Null or blank string detected");
			ReporterLog.info("Text from locatorName: "+locatorName +" is : " , text);
		} catch (Exception e) {
			ReporterLog.info("getText", "Null or blank string detected");
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			
		}
		return text;
	}
	
	public static String getText(WebElement element, String locatorName) {
		String text = "";
		try {
			text = element.getText();
			if (text == "" || text == null)
				throw new Exception();
			ReporterLog.info("Text from locatorName: "+locatorName +" is : " , text);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, text, locatorName.toString());
			e.printStackTrace();
		}
		return text;
	}

	//TODO - Need to use existing functions
	public static String getText_NEW(By locator, String locatorName) throws Exception {
		String text = "";
		try {
			text = driver.findElement(locator).getText();
			if (text == "" || text == null)
				throw new Exception();
			ReporterLog.pass("Text from locatorName: "+locatorName +" is : " , text);
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			throw new Exception("Exception in getText");
		}
		return text;
	}

	public static void JSClick(By locator, String locatorName) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}

	public static void JSClick(WebElement element, String elementName) {

		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, element.toString(), elementName.toString());
			e.printStackTrace();
		}
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by : Action to be performed on element (Get it from Object repository)
	 * @throws Throwable
	 * 
	 */

	public static void waitForVisibilityOfElement(By locator, String locatorName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			ReporterLog.pass("Validate Element Visibility",
					locator.toString() + " : element is visible on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}

	public static void waitForVisibilityOfElement_NEW(By locator, String locatorName) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			ReporterLog.pass("Validate Element Visibility",
					locator.toString() + " : element is visible on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			throw new Exception("Exception when waiting for visibility of element: "+locatorName);
		}
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by : Action to be performed on element (Get it from Object repository)
	 * @throws Throwable
	 * 
	 */

	public static void waitForElementClickable(By locator, String locatorName) {
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			ReporterLog.pass("Validate Element Clickable",
					locator.toString() + " : element is clickable on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}

	public static void waitForElementClickable_NEW(By locator, String locatorName) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			ReporterLog.pass("Validate Element Clickable",
					locator.toString() + " : element is clickable on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			throw new Exception("Exception waiting to click "+locatorName);
		}
	}

	/**
	 * This method wait driver until Invisibility of Elements on WebPage.
	 * 
	 * @param by : Action to be performed on element (Get it from Object repository)
	 * 
	 * @param by : Action to be performed on element (Get it from Object repository)
	 * 
	 */
	public static void waitForInVisibilityOfElement(By locator, String locatorName) {

		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
		try {
			wait.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}

	}

	public static void waitForInVisibilityOfElement_NEW(By locator, String locatorName) throws Exception{

		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
		try {
			wait.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			throw new Exception("Exception when waiting for invisibility of element: "+locatorName);
		}

	}
	
	public static WebElement explicitWaitForVisibilityOfElement(By element , int timeOutinSec) {
		WebDriverWait wait = new WebDriverWait(driver,timeOutinSec);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static WebElement explicitWaitForClickableOfElement(By element , int timeOutinSec) {
		WebDriverWait wait = new WebDriverWait(driver,timeOutinSec);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static Boolean explicitWaitForElementToBeSelected(By element , int timeOutinSec) {
		WebDriverWait wait = new WebDriverWait(driver,timeOutinSec);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	public static Boolean explicitWaitForElementTextToBePresent(By element ,String text, int timeOutinSec) {
		WebDriverWait wait = new WebDriverWait(driver,timeOutinSec);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(element,text));
	}

	public static void isElementDisplayed(By locator) {

		try {
			WebDriverWait newWait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			ReporterLog.pass("Validate Element Display",locator.toString() + " :  element is displayed on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
			System.out.println("Element : "+ locator.toString() +"  is present/Displayed with max wait of "+ConfigDetails.strGlobalTimeOut+" sec --PASS");
		} catch (Exception e) {
			System.out.println("Element : "+ locator.toString() +"  is not present/Displayed with max wait of "+ConfigDetails.strGlobalTimeOut+" sec : -- FAIL");
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();
		}
	}
	
	public static void isElementDisplayed(By locator ,String elementName) {

		try {
			WebDriverWait newWait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			ReporterLog.pass("Validate Element Display",elementName + " :  element is displayed on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
			System.out.println("Element : "+ elementName +"  is present/Displayed with max wait of "+ConfigDetails.strGlobalTimeOut+" sec --PASS");
		} catch (Exception e) {
			System.out.println("Element : "+ elementName +"  is not present/Displayed with max wait of "+ConfigDetails.strGlobalTimeOut+" sec : -- FAIL");
			TestUtilities.logReportFailure(e, elementName);
			e.printStackTrace();
		}
	}

	public static void isElementDisplayed(WebElement element) {

		try {
			if( element.isDisplayed()) {
			ReporterLog.pass("Validate Element Display" ," element is displayed on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
			System.out.println("Element :   is present/Displayed with max wait of "+ConfigDetails.strGlobalTimeOut+" sec --PASS");
			}else {
				ReporterLog.failAndContinue("Validate Element Display" ," element is not displayed on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");	
			}
			} catch (Exception e) {
			System.out.println("Element :  is not present/Displayed with max wait of "+ConfigDetails.strGlobalTimeOut+" sec : -- FAIL");
			TestUtilities.logReportFailure(e, element.toString());
			e.printStackTrace();
		}

	}
	public static boolean isElementPresent(By locator) {

		boolean bool = false;
		try {

			WebDriverWait newWait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			bool = driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();
		}

		return bool;

	}
	
	public static boolean isElementPresent(By locator,String elementName) {

		boolean bool = false;
		try {

			WebDriverWait newWait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			bool = driver.findElement(locator).isDisplayed();
			ReporterLog.pass("Validate Element Display" ," "+elementName+"  element is displayed on the page within : "+ConfigDetails.strGlobalTimeOut+" sec ");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, elementName);
			e.printStackTrace();
		}

		return bool;

	}

	public static boolean isElementPresentInDom(By locator) {

		boolean bool = false;
		try {
			if(driver.findElements(locator).size() > 0) {
				bool =true;
				ReporterLog.pass("Element isDisplayed", locator.toString() +"Element Displayed");

			}
		} catch (Exception e) {
			ReporterLog.fail("Element isDisplayed", locator.toString() +"Element is not Displayed");
			e.printStackTrace();
		}

		return bool;
	}
	
	public static boolean isElementPresentInDom(By locator,String elementName) {

		boolean bool = false;
		try {
			if(driver.findElements(locator).size() > 0) {
				bool =true;
				ReporterLog.pass("Element isDisplayed", elementName +"Element Displayed");

			}
		} catch (Exception e) {
			ReporterLog.info("Element isDisplayed", elementName +"Element is not Displayed");
			e.printStackTrace();
		}

		return bool;
	}
	/**
	 * @author mdafsarali
	 * @Date 30 October 2019
	 * @description This method will work as Hard wait on a element and polling every 1 sec till max timeout 
	 * @param locator
	 * @param maxtimeoutInSec
	 */

	public static void waitTillElementPresent_HardWait_Polling(By locator, int maxtimeoutInSec) { 

		for (int i = 0; i < maxtimeoutInSec; i++) {
			try {
				if (!isNumOf_Elements_Greater_Than_Zero(locator)) {

					Thread.sleep(1000); // wait for 1 sec
					ReporterLog.info("Hard wait For Element", "Element is not Visible ,Hence Retrying for Element : "+locator.toString()+" after "+ (i+1)+" sec " );
				} else {
					ReporterLog.info("Hard wait For Element", "Element is  Visible ,for Element : "+locator.toString()+" after "+ (i+1)+" sec " );
					break;
				}
			} catch (Exception e) {
				//Handling No Such Element Exception here.
			}
		}
	}
	
	public static void waitTillElementPresent_HardWait_Polling(By locator, int maxtimeoutInSec,String elementName) { 

		for (int i = 0; i < maxtimeoutInSec; i++) {
			try {
				if (!isNumOf_Elements_Greater_Than_Zero(locator)) {

					Thread.sleep(1000); // wait for 1 sec
					ReporterLog.info("Hard wait For Element", "Element is not Visible ,Hence Retrying for Element : "+elementName+" after "+ (i+1)+" sec " );
				} else {
					ReporterLog.info("Hard wait For Element", "Element is  Visible ,for Element : "+elementName+" after "+ (i+1)+" sec " );
					break;
				}
			} catch (Exception e) {
				//Handling No Such Element Exception here.
			}
		}
	}
	
	public static void waitTillElementPresent_HardWait_Polling(WebElement element, int maxtimeoutInSec) { 

		for (int i = 0; i < maxtimeoutInSec; i++) {
			try {
				if (!isElementVisible(element)) {

					Thread.sleep(1000); // wait for 1 sec
					ReporterLog.info("Hard wait For Element", "Element is not Visible ,Hence Retrying for Element : "+element.toString()+" after "+ (i+1)+" sec " );
				} else {
					ReporterLog.info("Hard wait For Element", "Element is  Visible ,for Element : "+element.toString()+" after "+ (i+1)+" sec " );
					break;
				}
			} catch (Exception e) {
				//Handling No Such Element Exception here.
			}
		}
	}

	public static String getAttribute(By locator, String attributename) {

		String attribute = "";
		try {

			WebDriverWait newWait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			attribute = driver.findElement(locator).getAttribute(attributename).toString();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();
		}

		return attribute;

	}

	/*
	 * Touch Action Usage:
	 * -------------------- 
	 * Tap on Element:
		new TouchAction(localdriver).tap(tapOptions().withElement(element(myElement))).perform();
	Tap on Coordinates:
		new TouchAction(localdriver).tap(point(xPoint, yPoint)).perform();
	Tap on Element using coordinates relative to element:
		new TouchAction(localdriver).tap(tapOptions().withElement(element(myElement, xPoint, yPoint))).perform();
	LongPress on Element:
		new TouchAction(localdriver).longPress(longPressOptions().withElement(element(myElement))).release().perform();
	LongPress on Element with Duration:
		new TouchAction(localdriver).longPress(longPressOptions().withElement(element(myElement)).withDuration(Duration.ofMillis(duration))).release().perform();
	LongPress on coordinates:
		new TouchAction(localdriver).longPress(point(xPoint, yPoint)).release().perform();
	 */
	@Deprecated
	public static void tapOn(String locator) {
		try {
			MobileElement swt = (MobileElement) driver.findElementById("title");
			//new TouchAction((MobileDriver<?>) driver).tap(swt).tap(swt).tap(swt).tap(swt).tap(swt).perform();

			new TouchAction((PerformsTouchActions) getMobileDriver()).tap(TapOptions.tapOptions().withTapsCount(5).withElement(ElementOption.element(swt)))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();


			MobileElement swt1 = getMobileDriver().findElementById("title");
			//new TouchAction((MobileDriver<?>) driver).press(swt1).press(swt1).press(swt1).press(swt1).press(swt1)
			//.tap(swt1).perform();
			new TouchAction((PerformsTouchActions) getMobileDriver()).tap(TapOptions.tapOptions().withTapsCount(5).withElement(ElementOption.element(swt)))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();

			MobileElement el = getMobileDriver()
					.findElement(By.xpath("//*[@text='Log in'][@resource-id='tv.hooq.android:id/title']"));

			Point p =el.getLocation();
			//TODO -Need to refactor this ..
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
			new TouchAction(getMobileDriver()).press(PointOption.point(p.getX(), p.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();

			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
			//			((TouchShortcuts) driver).tap(1, p.getX(), p.getY(), 1);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();
		}
	}


	@Deprecated
	public static void swipeUpOrBottom(boolean bottomToTop) {
		try {
			org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			int starty = (int) (size.height * 0.60);
			int endy = (int) (size.height * 0.40);
			int startx = size.width / 2;

			if (bottomToTop) {
				if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {
					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,endy )).release().perform();

					//((AndroidDriver<?>) driver).swipe(startx, starty, startx, endy, 3000);
				} else {
					//((IOSDriver<?>) driver).swipe(startx, starty, startx, endy, 3000);
					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,endy )).release().perform();
				}
			} else {
				if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {

					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,starty)).release().perform();

					//((AndroidDriver<?>) driver).swipe(startx, endy, startx, starty, 3000);
				} else {
					new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx,starty)).release().perform();
					//((IOSDriver<?>) driver).swipe(startx, endy, startx, starty, 3000);
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	} 

	@Deprecated
	public static void swipeUPorDownTillElementPresent(By locator, boolean bottomToTop) {
		try {
			org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			int starty = (int) (size.height * 0.60);
			int endy = (int) (size.height * 0.50);
			int startx = size.width / 2;
			int count = 1;

			while (!driver.findElement(locator).isDisplayed()) {
				if (bottomToTop) {
					if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,endy)).release().perform();

						//((AndroidDriver<?>) driver).swipe(startx, starty, startx, endy, 3000);
					} else {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,endy)).release().perform();

						//((IOSDriver<?>) driver).swipe(startx, starty, startx, endy, 3000);
					}
				} else {
					if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,starty)).release().perform();
						//((AndroidDriver<?>) driver).swipe(startx, endy, startx, starty, 3000);
					} else {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,starty)).release().perform();
						//((IOSDriver<?>) driver).swipe(startx, endy, startx, starty, 3000);
					}
				}
				try {
					if (count > 10 || ((MobileDriver<?>) driver).findElement(locator).isDisplayed()) {
						break;
					}
					count++;
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}
	@Deprecated
	public static void swipeUPorDownTillElementPresent(MobileElement element, boolean bottomToTop) {
		try {
			org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			int starty = (int) (size.height * 0.60);
			int endy = (int) (size.height * 0.50);
			int startx = size.width / 2;
			int count = 1;

			while (!element.isDisplayed()) {
				if (bottomToTop) {
					if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {

						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,endy)).release().perform();
						//((AndroidDriver<?>) driver).swipe(startx, starty, startx, endy, 3000);
					} else {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,endy)).release().perform();

						//((IOSDriver<?>) driver).swipe(startx, starty, startx, endy, 3000);
					}
				} else {
					if (ConfigDetails.strPlatformName.toLowerCase().contains("android")) {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,starty)).release().perform();

						//((AndroidDriver<?>) driver).swipe(startx, endy, startx, starty, 3000);
					} else {
						new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx,starty)).release().perform();
						//((IOSDriver<?>) driver).swipe(startx, endy, startx, starty, 3000);
					}
				}
				try {
					if (count > 10 || element.isDisplayed()) {
						break;
					}
					count++;
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}

	public static void scrollTillElementPresent(MobileElement element, boolean isDown) {
		try {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", element.getId());
			//scrollObject.put("predicateString", "label == '"+element.getAttribute("value")+"'");
			if(isDown) {
				scrollObject.put("direction", "down");
			}else {
				scrollObject.put("direction", "up");
			}
			driver.executeScript("mobile:scroll",scrollObject);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}

	public static MobileElement getMobileElement(By locator) {
		MobileElement element = null;
		try {
			element = (MobileElement) driver.findElement(locator);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();

		}
		return element;
	}
	/**
	 * @author mdafsarali
	 * @description This method will return the WebElement Lists 
	 * @param locator
	 * @return
	 */
	
	public static List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element =  driver.findElements(locator);
			ReporterLog.info("Find Multiple Elements ", "Total Elements Returned as : "+element.size());
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();

		}
		return element;
	}

	public static boolean isElementPresentEvenNotVisibleOnScreen(By locator) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString());
			e.printStackTrace();
			return false;
		}

	}

	public static void isElementPresentEvenNotVisibleOnScreen(By locator, String locatorName) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}

	public static boolean click(WebElement locator, String locatorName) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigDetails.strGlobalTimeOut));
			//ReporterLog.info("click", "Waiting for  "+ConfigDetails.strGlobalTimeOut+" element to be clickable");
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			ReporterLog.info("click", "Proceeding to click element "+locatorName);
			element.click();
			return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
			return false;
		}

	}

	@Deprecated
	public static void doubleTap(By locator, String locatorName) {

		try {

			MobileElement element= (MobileElement) driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//			HashMap<String, String> scrollObject = new HashMap<String, String>();
			//			scrollObject.put("x",""+element.getLocation().getX()+10);
			//			scrollObject.put("y",""+(element.getLocation().getY()-10));
			//			scrollObject.put("element",element.getId());		
			//			js.executeScript("mobile: tap", scrollObject);

			IOSTouchAction action = new IOSTouchAction(getIOSDriver());
			action.doubleTap(new PointOption().withCoordinates(element.getCoordinates().onPage().x,element.getCoordinates().onPage().y));

		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}
	}
	
	public static void tapOnElement(MobileElement element ,String elementName) {
		try {
		new TouchAction(getMobileDriver()).tap(PointOption.point(element.getCenter().getX(), element.getCenter().getY())).perform();
		ReporterLog.info("Tap on the Element", "Tapped on the Element : "+elementName);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void tapOnElement(By element ,String elementName) {
		try {
			
		new TouchAction(getMobileDriver()).tap(PointOption.point(getMobileElement(element).getCenter().getX(), getMobileElement(element).getCenter().getY())).perform();
		ReporterLog.info("Tap on the Element", "Tapped on the Element : "+elementName);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void tapOnCoOrdinates(int x , int y ,String elementName) {
		try {
		new TouchAction(getMobileDriver()).tap(PointOption.point(x,y)).perform();
		ReporterLog.info("Tap on the Co-Ordinates", "Tapped on the Co-oridnates : "+elementName);
		}catch(Exception e) {e.printStackTrace();}
	}

	public static void JS_DoubleTap(By locator) {
		MobileElement element= (MobileElement) driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("x","1");
		scrollObject.put("y","1");
		scrollObject.put("element",element.getId());		
		js.executeScript("mobile: tap", scrollObject);
	}

	@Deprecated
	public static void doubleTapNoWait(By locator, String locatorName) {

		try {
			MobileElement element = getMobileElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", element.getId());
			js.executeScript("mobile: doubleTap", scrollObject);
			//new TouchAction((MobileDriver) driver).tap(driver.findElement(locator)).tap(driver.findElement(locator)).perform();			
			//	new TouchAction((PerformsTouchActions) driver).tap(TapOptions.tapOptions().withElement((ElementOption) driver.findElement(locator))).tap(TapOptions.tapOptions().withTapsCount(2));


		} catch (Exception e) {
			TestUtilities.logReportFailure(e, locator.toString(), locatorName.toString());
			e.printStackTrace();
		}

	}

	public static boolean scrollJs(String upOrDown) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", upOrDown);
			js.executeScript("mobile: scroll", scrollObject);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Boolean webScrollTillElementPresent(By locator, Boolean isDown) {

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Boolean found = false;
		int scrollMovements = 0;
		int distance;
		try {
			if(!isNumOf_Elements_Greater_Than_Zero(locator)) {
				ReporterLog.info("Scroll","Scrolling to find element");
				while (!found && scrollMovements < 10) {
					if(isDown) {
						distance = 1000;
					} else {
						distance = -1000;
					}	
					jse.executeScript("scroll(0, "+distance+");");
					scrollMovements ++;
					if (isNumOf_Elements_Greater_Than_Zero(locator)) {
						found = true;
					}
				}
				return found;
			} else {
				ReporterLog.info("Scroll","No need to scroll as element already present");
				found = true;
				return found;				
			}
		} catch (Exception e) {
			ReporterLog.info("scroll", "Unable to scroll");
			found = false;
			return found;
		}
	}
	
	
	public static boolean isNumOf_Elements_Greater_Than_Zero(By locator) {
		boolean flag =false;
		try {
		flag = driver.findElements(locator).size() > 0;
		ReporterLog.info("CheckElements", "# elements for "+locator+": "+driver.findElements(locator).size());
		}catch(Exception ex) {
			ReporterLog.info("Check Elements > 0","DOING NOTHING AS ELEMENT ("+locator+") DOES NOT EXIST");
			/* do nothing, element does not exist */
		}
		return flag;
	}

	public static boolean isNumOf_Elements_InAnElement_Greater_Than_Zero(By locator1, By locator2) {
		return driver.findElement(locator1).findElements(locator2).size() > 0;
	}

	public static int NumOfEleInAList(By locator) {
		return driver.findElements(locator).size();
	}
	public static int NumOfEleInAnElement(By locator1, By locator2) {
		return driver.findElement(locator1).findElements(locator2).size();
	}

	public static void verify_ListSize_GreaterThanZero(By locator) {

		try {
			List<WebElement> eleList = driver.findElements(locator);
			if (eleList.size() <= 0)
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void verifyElementsDisplayedInAnElement(By locator1, By locator2) {

		try {
			List<MobileElement> eleList = getMobileDriver().findElement(locator1).findElements(locator2);
			if (eleList.size() <= 0)
				throw new Exception();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void clickOnElementInListOfAList(By locator1, By locator2, int index) {

		try {
			List<MobileElement> eleList = getMobileDriver().findElement(locator1).findElements(locator2);
			ReporterLog.info("clickOnElementInAList", "clickOnElementInAList confirms there are "+eleList.size()+" elements in the list");
			if(eleList.size()>0)
				click(eleList.get(index), "Clicking on "+locator1.toString()+" element #:" + index);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void clickOnElementInAList(By locator1, int index) {

		try {
			List<WebElement> eleList = driver.findElements(locator1);
			ReporterLog.info("clickOnElementInAList", "clickOnElementInAList confirms there are "+eleList.size()+" elements in the list");
			if(eleList.size()>0)
				click(eleList.get(index), "Clicking on "+locator1.toString()+" element #:" + index);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void typeOnElementInAList(By locator1, int index, String strText) {

		try {
			List<WebElement> searchResults = driver.findElements(locator1);
			ReporterLog.info("typeOnElementInAList", "Entered " +strText+" in "+searchResults.get(index).getText());
			if(searchResults.size()>0)
				searchResults.get(0).sendKeys(strText);	
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void typeOnElementInAnotherElement(By locator1, By locator2, int index, String strText) {

		try {
			List<MobileElement> eleList = getMobileDriver().findElement(locator1).findElements(locator2);
			ReporterLog.info("typeOnElementInAnotherElement", "Entered " +strText+" in "+eleList.get(index).getText());
			if(eleList.size()>0)
				eleList.get(0).sendKeys(strText);	
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static String getTextOfAnElementInAList(By locator, int index) {
		String strText = null;
		try {
			List<WebElement> elements = driver.findElements(locator);
			strText = elements.get(index).getText();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
		return strText;

	}

	public static String getTextOfAnElementInAList(List<WebElement> element, int index) {
		String strText = null;
		try {			
			strText = element.get(index).getText();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
		return strText;

	}
	
	public static String getTextOfAnElementInAListOfList(By locator1, By locator2, int index) {
		String strText = null;
		try {
			List<WebElement> elements = driver.findElement(locator1).findElements(locator2);
			strText = elements.get(index).getText();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
		return strText;

	}

	public static void clickElementInAList(By locator, int index) {

		try {
			driver.findElements(locator).get(index).click();
			ReporterLog.pass("Click in Elements List", "Element is Clicked in a list of : "+ locator.toString() +" with a index of : # "+index);

		} catch (Exception e) {
			
			ReporterLog.fail("Click in Elements List", "Element is not Clicked in a list of : "+ locator.toString() +" with a index of : # "+index);
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void clickElementInAList(List<WebElement> element, int index) {

		try {
			click(element.get(index), "NA");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static String getTextInAListOfElementAndVerifyNotNull(By locator) {
		String strText = null;
		try {
			List<WebElement> elements = driver.findElements(locator);

			for (int i = 0; i < elements.size(); i++) {
				String contentTxt = elements.get(i).getText();
				verifyTextNotNull(contentTxt);
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
		return strText;

	}
	public static void seekThroughDRMPlayerWindow(String seconds) {
		try {

			driver.executeScript("document.body.querySelector('video').pause()", "");
			driver.executeScript("document.body.querySelector('video').currentTime "
					+ "= document.body.querySelector('video').currentTime + " + seconds + "; return true;", "");
			driver.executeScript("document.body.querySelector('video').play()", "");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void seekThroughPlayerWindow(String seconds) {
		try {
			driver.executeScript("document.body.querySelector('video').currentTime "
					+ "= document.body.querySelector('video').duration - " + seconds + "; return true;", "");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void endPlayerVideo() {
		try {
			driver.executeScript("document.body.querySelector('video').currentTime "
					+ "= document.body.querySelector('video').duration; return true;", "");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void verifyAttributeOfEachElementContainsText(By locator, String attributeName, String expectedText) {
		try {
			List<WebElement> elements = driver.findElements(locator);

			for (WebElement element : elements) {
				String actualText = element.getAttribute(attributeName);

				verifyTextContains(actualText.toLowerCase(), expectedText.toLowerCase());
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void fnHandleNotificationPopup() {
		
		if (isNumOf_Elements_Greater_Than_Zero(By.name("OK"))) {
			driver.findElement(By.name("OK")).click();	//For iOS 13 need to handle Use Bluetooth notification
		}
		if (isNumOf_Elements_Greater_Than_Zero(By.name("Allow"))) {
			driver.findElement(By.name("Allow")).click();	
		}	
		
	}

	@SuppressWarnings("unchecked")
	public static MobileDriver<MobileElement> getMobileDriver() {

		return (MobileDriver<MobileElement>)driver;
	}

	@SuppressWarnings("unchecked")
	public static AndroidDriver<AndroidElement> getAndroidDriver() {

		return (AndroidDriver<AndroidElement>)driver;
	}

	@SuppressWarnings("unchecked")
	public static IOSDriver<IOSElement> getIOSDriver() {
		return (IOSDriver<IOSElement>)driver;
	}

	public static IOSElement getIOSElement(By by) {
		return getIOSDriver().findElement(by);
	}

	@SuppressWarnings("rawtypes")
	public static void swipeHorizontalByElementWeb (By element, Boolean rightToLeft, int offsetPercent) {

		int startX = driver.findElement(element).getRect().getX();
		int startY = driver.findElement(element).getRect().getY();
		float width = driver.findElement(element).getRect().getWidth();
		float height = driver.findElement(element).getRect().getHeight();
		//int centreX = startX+(int) (width/2);
		int centreY = startY+(int) (height/2);
		
		int swipeStart;
		int swipeEnd;
		
		if(offsetPercent > 50) {
			offsetPercent = 40;			
		}
		
		if (rightToLeft) {
			swipeEnd = (startX + (int) ((width/100) * (float) ( offsetPercent)));
			swipeStart = ((startX + (int) width) - (int) ((width/100) * (float) ( offsetPercent)));
		} else {
			swipeStart = (startX + (int) ((width/100) * (float) ( offsetPercent)));
			swipeEnd = ((startX + (int) width) - (int) ((width/100) * (float) ( offsetPercent)));
		}
		
		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(swipeStart, centreY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(swipeEnd,centreY)).release().perform();

		//new TouchAction(getMobileDriver()).press(startX,startY).waitAction(1000).moveTo(endX, endY).release().perform();
	}
	
	/**
	 * @author mdafsarali
	 * @date 29 Aug 2019
	 * @Description This method will scroll from one element to another 
	 * @param By startElement
	 * @param By endElement
	 */
	public static void swipeHorizontalByElements (By startElement, By endElement) {

		int startX =getMobileElement(startElement).getLocation().getX() +(getMobileElement(startElement).getSize().getWidth())/2;
		int startY = getMobileElement(startElement).getLocation().getY() + (getMobileElement(startElement).getSize().getHeight())/2;

		int endX = getMobileElement(endElement).getLocation().getX() +(getMobileElement(endElement).getSize().getWidth())/2;
		int endY = getMobileElement(endElement).getLocation().getY() + (getMobileElement(endElement).getSize().getHeight())/2;

		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(endX,endY)).release().perform();

		//new TouchAction(getMobileDriver()).press(startX,startY).waitAction(1000).moveTo(endX, endY).release().perform();
	}

	/**
	 * @author mdafsarali
	 * @date 29 Aug 2019
	 * @Description This method will scroll from one element to another 
	 * @param MobileElement startElement
	 * @param MobileElement endElement
	 */
	public static void swipeHorizontalByElements (MobileElement startElement, MobileElement endElement) {

		int startX =startElement.getLocation().getX() +(startElement).getSize().getWidth()/2;
		int startY = startElement.getLocation().getY() + (startElement).getSize().getHeight()/2;

		int endX = endElement.getLocation().getX() +(endElement).getSize().getWidth()/2;
		int endY = endElement.getLocation().getY() + (endElement).getSize().getHeight()/2;

		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startX, startY))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(endX,endY)).release().perform();

		//new TouchAction(getMobileDriver()).press(startX,startY).waitAction(1000).moveTo(endX, endY).release().perform();
	}


	public static Dimension getWindowsSize() {
		Dimension windowSize = driver.manage().window().getSize();
		return windowSize;

	}

	public static void VerifyThatIsTrue(boolean validation, String message) {

		try {
			if (validation) {
				ReporterLog.pass("Test Validation",
						"PASS , as expected result is true : UserDefined Message : " + message);
			} else {
				ReporterLog.fail("Test Validation", "FAIL , as expected result is not true");
				throw new Exception("Test Validation failed  as expected result is not true :Expected was : "+message);
				
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void VerifyThatIsFalse(boolean validation, String message) {

		try {
			if (validation) {
				ReporterLog.fail("Test Validation", "Expected result is False but found True - Status : FAIL");
				throw new Exception("Expected result is False but found True");
			} else {
				ReporterLog.pass("Test Validation",
						"PASS , Expected result and Actual result found False  : UserDefined Message : " + message);
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void closeInterstitialAd() {

		try {
			List<WebElement> lstInterstitialAd=driver.findElements(By.xpath("//android.view.View[@resource-id='abgcp']"));
			List<WebElement> lstBannerAd=driver.findElements(By.id("tv.hooq.android:id/adViewContainer"));
			if(ConfigDetails.environment.equalsIgnoreCase("stag"))
			{
				List<WebElement> lstStagInterstitialAd=driver.findElements(By.xpath("//android.widget.ImageButton[@content-desc='Interstitial close button']"));
				if (lstStagInterstitialAd.size()>0)
				{
					ReporterLog.pass("Interstitial Ad",	"Interstitial Ad is displayed");
					driver.navigate().back();				
				} else {
					ReporterLog.pass("Interstitial Ad",	"Interstitial Ad is not displayed");
				}
			}				
			else
			{
				if(!(lstBannerAd.size()>0))
				{
					if (lstInterstitialAd.size()>0)
					{
						ReporterLog.pass("Interstitial Ad",	"Interstitial Ad is displayed");
						driver.navigate().back();				
					} else {
						ReporterLog.pass("Interstitial Ad",	"Interstitial Ad is not displayed");
					}
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}

	}

	public static void pressKeyRemote(String direction, int times) {
		try {
			Thread.sleep(1000);
			switch (direction) {
			case "up":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 19");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			case "down":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			case "left":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 21");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			case "right":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			case "center":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			case "back":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 4");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			case "delete":
				for (int i=1; i <= times; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 28");
					Thread.sleep(1000);
				}
				ReporterLog.info("Press Remote button", direction +" "+times+" times");
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}

	public static int randomNumber(int rangeOne, int rangeTwo) {
		int randomInt = ThreadLocalRandom.current().nextInt(rangeOne, rangeTwo);
		return randomInt;
	}

	public static int verifyAttributeOfEachElementContainsText1(By locator, String attributeName, String expectedText) {
		int exactMatchLocation = -1;
		try {
			List<WebElement> elements = driver.findElements(locator);

			int count = 0;

			for (WebElement element : elements) 
			{
				String actualText = element.getAttribute(attributeName);
				if(actualText.contains(expectedText)){
					//count represents number of partial matches 
					count++;	
					if (actualText.equals(expectedText)) {
						// Only if string matches fully will exactMatchLocation be > -1
						exactMatchLocation = (count - 1);
					}
				}							
			}
			if(count>0){
				if(exactMatchLocation >= 0) {
					ReporterLog.info("verifyAttributeOfEachElementContainsText1", "exact match found for '"+expectedText+"' at element #: "+exactMatchLocation);	
				} else {
					ReporterLog.info("verifyAttributeOfEachElementContainsText1", "No exact match found when searching for '"+expectedText+"'");
				}
			}
			else{
				ReporterLog.fail("verify ElementContainsText failed ", "actual string is not matching with expected string");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return exactMatchLocation;
	}

	/**
	 * @author mdafsarali
	 * @param sliderElement - This is the top Parent Element row which is to be swiped
	 * @param targetElement  - The Element till which swipe to be done (till visibility )
	 * @param isRightToLeft  - if true then Swipe from right to left otherwise it will be left to right
	 * @param percentageScroll - it is the percentage of swipe to be done ( percentage of the Screen Width ) Max percentage is 0.5 ( need to use 0 to 0.5 only )
	 * @return isElementVisible
	 */

	public static boolean swipeTillElementVisible_Horizontal(By sliderElement ,By targetElement ,Boolean isRightToLeft ,double percentageScroll ) {

		Dimension dim = driver.manage().window().getSize();

		MobileElement element = (MobileElement) driver.findElement(sliderElement);

		int startX = element.getCenter().getX();
		int startY = element.getCenter().getY();

		int endX ;
		if(isRightToLeft) {
			endX = (int) (element.getCenter().getX() - (dim.getWidth() * percentageScroll)); //Right to Left Swipe
			ReporterLog.info("Swipe Action", "Swipping Element from Right to Left of the Screen ");
		}else {
			endX = (int) (element.getCenter().getX() + (dim.getWidth() * percentageScroll)); //Left to Right Swipe 
			ReporterLog.info("Swipe Action", "Swipping Element from Left to Right of the Screen ");
		}
		int endY = element.getCenter().getY();

		int count =1;
		boolean isElementVisible = false;

		while(!isElementVisible(targetElement)) {
			ReporterLog.info("Swipe Action", "Element is Not Visible , Hence Swipping  : count : "+count);

			new TouchAction(getMobileDriver()).press(PointOption.point(startX, startY))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
			.moveTo(PointOption.point(endX, endY)).perform();			

			if(isElementVisible(targetElement)) {
				isElementVisible=true;
				ReporterLog.info("Swipe Action", "Element is Visible , After Swip : count : "+count);
				break;
			}
			count++;
			if(count == 20) {
				isElementVisible=false;
				ReporterLog.info("Swipe Action", "Element is not Visible , After Swip : count : "+count+"  - FAIL");
				break;
			}
		}
		return isElementVisible;
	}

	/**
	 * @author mdafsarali
	 * @param targetElement - Element till which scroll to be Done  (till visible on the screen)
	 * @param percentageScroll - it is the percentage of Scroll to be done ( percentage of the Screen Height ) Max percentage is 0.5 ( need to use 0 to 0.5 only )
	 * @param isScrollDown - if true then it will scroll from bottom to Top otherwise it will be Top to bottom
	 * @return isElementVisibleOnScreen
	 */
	//Scroll down or Scroll Up till the Element Visible
	public static boolean scrollTillElementVisible_Vertical(By targetElement,double percentageScroll ,Boolean isScrollDown) {
		Dimension dim = driver.manage().window().getSize();
		int startX = dim.getWidth()/2;
		int startY = dim.getHeight()/2;

		int endX = dim.getWidth()/2;
		int endY;

		if (isScrollDown) {
			// For scroll down
			endY = (int) ((dim.getHeight() / 2) - (dim.getHeight() * percentageScroll)); // For Scroll down of 10% of Height of the screen
			ReporterLog.info("Scroll Action", "Scroll Element from bottom to Top of the Screen ");
		} else {
			endY = (int) ((dim.getHeight() / 2) + (dim.getHeight() * percentageScroll));
			ReporterLog.info("Scroll Action", "Scroll Element from Top to Bottom of the Screen ");
		}
		int count =1;
		boolean isElementVisible = false;

		while(!isElementVisible(targetElement)) {
			ReporterLog.info("Scroll Action", "Element is Not Visible , Hence Scrolling  : count : "+count);

			new TouchAction(getMobileDriver()).press(PointOption.point(startX, startY))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
			.moveTo(PointOption.point(endX, endY)).perform();			

			if(isElementVisible(targetElement)) {
				isElementVisible=true;
				ReporterLog.info("Scroll Action", "Element is  Visible , After scroll : count : "+count);
				break;
			}
			if(count == 20) {
				isElementVisible=false;
				ReporterLog.info("Scroll Action", "Element is not Visible , After scroll : count : "+count +"  - FAIL");
				break;
			}
			count++;
		}
		return isElementVisible;
	}
	
	//Scroll down or Scroll Up till the Element Visible
		public static boolean scrollTillElementVisible_Vertical(MobileElement targetElement,double percentageScroll ,Boolean isScrollDown) {
			Dimension dim = driver.manage().window().getSize();
			int startX = dim.getWidth()/2;
			int startY = dim.getHeight()/2;

			int endX = dim.getWidth()/2;
			int endY;

			if (isScrollDown) {
				// For scroll down
				endY = (int) ((dim.getHeight() / 2) - (dim.getHeight() * percentageScroll)); // For Scroll down of 10% of Height of the screen
				ReporterLog.info("Scroll Action", "Scroll Element from bottom to Top of the Screen ");
			} else {
				endY = (int) ((dim.getHeight() / 2) + (dim.getHeight() * percentageScroll));
				ReporterLog.info("Scroll Action", "Scroll Element from Top to Bottom of the Screen ");
			}
			int count =1;
			boolean isElementVisible = false;

			while(!isElementVisible(targetElement)) {
				ReporterLog.info("Scroll Action", "Element is Not Visible , Hence Scrolling  : count : "+count);

				new TouchAction(getMobileDriver()).press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
				.moveTo(PointOption.point(endX, endY)).perform();			

				if(isElementVisible(targetElement)) {
					isElementVisible=true;
					ReporterLog.info("Scroll Action", "Element is  Visible , After scroll : count : "+count);
					break;
				}
				if(count == 20) {
					isElementVisible=false;
					ReporterLog.info("Scroll Action", "Element is not Visible , After scroll : count : "+count +"  - FAIL");
					break;
				}
				count++;
			}
			return isElementVisible;
		}

	public static Boolean isElementVisible(By by) {
		Boolean isVisible = false;
		try {
			isVisible = driver.findElement(by).isDisplayed();

		}catch(Exception e) {}
		return isVisible;
	}
	
	public static Boolean isElementVisible(WebElement element) {
		Boolean isVisible = false;
		try {
			isVisible = element.isDisplayed();

		}catch(Exception e) {}
		return isVisible;
	}


}

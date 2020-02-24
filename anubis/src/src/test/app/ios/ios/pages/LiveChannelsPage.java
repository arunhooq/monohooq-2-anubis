package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class LiveChannelsPage extends ActionEngine {

	public static By tabDiscover = By.name("DISCOVER");

	public static By tabChannels = By.name("CHANNELS");

	public static By txtChannelTitle = By.xpath("//XCUIElementTypeStaticText[@name=\"CHANNELS\"]");

	public static By tabMovies = By.name("MOVIES");

	public static By tabChannels_VideoSection = By.xpath("//XCUIElementTypeStaticText[@name=\"Channels\"]");

	public static By tabCatchUp = By.name("Catch Up");

	public static By labelWatchList = By.name("Watchlist");

	public static By labelyouarewatching = By.name("You're watching");

	public static By sectionLifeStyle = By.name("LifeStyle");

	public static By buttonExpandVideo = By.name("ic expand video");

	public static By buttonPlayerClose = By.id("icPlayerClose");

	public static By playerButtonOverlayImage = By.xpath(
			"//XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage");

	public static By buttonOverlayImageClose = By.id("ic close white");

	public static By tabAllChannels = By.id("All Channels");

	public static By buttonSignUp = By.name("Sign up");

	public static By buttonLogin = By.name("Have an account? Log In");

	public static By buttonSkip = By.name("Skip");

	public static By category = By.xpath(".//*[@class='UIACollectionView']/UIAView/UIAView");

	public static By channelList = By
			.xpath("//XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeCollectionView/XCUIElementTypeCell");

	public static By linkMaybeLater = By.name("Maybe Later");
	public static By btnVideo = By.name("Video");
	public static By btnInProgress = By.name("In progress");
	public static By btnTVGuide = By.id("TV Guide");
	public static By txtAllChannels = By.id("All Channels");

	public LiveChannelsPage verifyNavigationToLiveChannel() {
		try {
			
			//TODO --
			click(tabChannels, "Channels tab");
			 isElementDisplayed(btnTVGuide);
			 isElementDisplayed(txtAllChannels); 
			 
			 //need to replace with Playing now and
			
			 // Next
			ReporterLog.pass("Live Channel Navigation", "Live Channel Navigation succesfull");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LiveChannelsPage();
	}

	public LiveChannelsPage verifyLiveChannelPage() {
		try {
			if(!isElementPresentInDom(labelWatchList,"Watchlist in Channel Video")) {
				tapOnElement(btnInProgress, "Video Player");
			}
			
			if(!isElementPresentInDom(labelWatchList,"Watchlist in Channel Video")) {
				tapOnElement(btnInProgress, "Video Player");
			}
			
			//isElementDisplayed(labelWatchList);
			clickNoWait(labelWatchList, "watchlist");
			isElementDisplayed(buttonSignUp);
			click(linkMaybeLater, "Maybe Later Button");

			ReporterLog.pass("Live ChannelPage Validation", "Live Channelpage validation succesfull");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LiveChannelsPage();
	}

	public LiveChannelsPage verifyChannelsSignUpForVisitors() {
		try {
			isElementDisplayed(labelWatchList);
			Thread.sleep(10000);
			List<MobileElement> list = getMobileElement(channelList)
					.findElements(By.xpath(".//XCUIElementTypeOther[2]"));
			System.out.println("Elements list : " + list.size());
			list.get(1).isDisplayed();
			click(list.get(1), "Channel 2");
			click(list.get(2), "Channel 3");
			// click(list.get(3),"Channel 4");

			isElementDisplayed(buttonSignUp);
			isElementDisplayed(buttonSkip);
			isElementDisplayed(buttonLogin);
			click(buttonSkip, "Skip Button");

			ReporterLog.pass("Live Channel Sign up for Visitors", "Live Channelpage Sign up validation succesfull");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LiveChannelsPage();
	}

	public LiveChannelsPage verifyChannelsPlayBackForVisitors() {
		try {
			waitForVisibilityOfElement(buttonExpandVideo, "Expand/Maximize Video");
			click(buttonExpandVideo, "Maximize Channel Player");
			// waitForVisibilityOfElement(playerButtonOverlayImage, "Player button
			// overlay");
			// click(playerButtonOverlayImage, "Player Button Overlay");
			// Thread.sleep(10000);
			// tapCenterOfScreen();

			ReporterLog.pass("Live Channel Playback", "Live Channelpage validation succesfull");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LiveChannelsPage();
	}

	public LiveChannelsPage verifyChannelsPlayBackForActiveUser() {
		try {
			waitForVisibilityOfElement(buttonExpandVideo, "Expand/Maximize Video");
			click(buttonExpandVideo, "Maximize Channel Player");
			// tapCenterOfScreen();
			waitForVisibilityOfElement(playerButtonOverlayImage, "Player button overlay");
			click(playerButtonOverlayImage, "Player Button Overlay");
			isElementDisplayed(tabAllChannels);
			Thread.sleep(10000);
			// tapCenterOfScreen();
			click(buttonOverlayImageClose, "Button Overlay image Close");
			// tapCenterOfScreen();
			click(buttonPlayerClose, "Player Close");
			isElementDisplayed(tabChannels);
			ReporterLog.pass("Live Channel Playback", "Live Channelpage validation succesfull");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new LiveChannelsPage();
	}

}

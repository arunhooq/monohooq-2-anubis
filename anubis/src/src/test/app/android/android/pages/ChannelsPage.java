package android.pages;

import java.time.Duration;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class ChannelsPage extends ActionEngine{
	
	public static By channelsHeaderLOGO = By.id("tv.hooq.android:id/headerLogo"); 
	public static By channelsPlayerWindow = By.id("tv.hooq.android:id/brightcove_video_view");
	public static By channelsHeader = By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc='Channels']"); 
	public static By catchUpHeader = By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc='Catch-Up']");
	public static By channelsGroupTitle = By.id("tv.hooq.android:id/channels_title");
	public static By selectedChannel = By.id("tv.hooq.android:id/view_selected_state");
	public static By channelsCount = By.id("tv.hooq.android:id/cv_channel");	
	public static By youAreWatchingHeader = By.id("tv.hooq.android:id/tvYoureWatching");
	public static By youAreWatchingChannelTitle = By.id("tv.hooq.android:id/currentChannelName");
	public static By addToWatchlistLogo = By.id("tv.hooq.android:id/ivAddOrRemoveToFromWatchlist");
	public static By addToWatchlistText = By.id("tv.hooq.android:id/tvAddOrRemoveToFromWatchlist");
	public static By promtDescForPayTV = By.id("tv.hooq.android:id/promptOkDesc");
	public static By promtButtonForPayTV = By.id("tv.hooq.android:id/promptOkButtonText");
	public static By btnFullScreen = By.id("tv.hooq.android:id/btnFullscreen");
	public static By currentChannelNameInFullScreen = By.id("tv.hooq.android:id/currentChannelName");
	public static By currentChannelLogoInFullScreen = By.id("tv.hooq.android:id/currentChannelLogo");
	public static By channelGroupsInFullScreen = By.className("androidx.appcompat.app.ActionBar$Tab");
	public static By channelListInAGroupInFullScreen = By.id("tv.hooq.android:id/name");
	public static By closeFullScreen = By.id("tv.hooq.android:id/close");
	public static By channelport = By.id("tv.hooq.android:id/playerLayout");
	public static By channelfullscreenbtn = By.id("tv.hooq.android:id/btnFullscreen");
	public static By channelfullscreenplayer = By.id("tv.hooq.android:id/playerLandscapeOverlay");
	
	public ChannelsPage verify_ChannelsSectionDetails() {
		try 
		{
			isElementDisplayed(channelsHeaderLOGO);
			isElementDisplayed(channelsPlayerWindow);
			isElementDisplayed(channelsHeader);
			isElementDisplayed(catchUpHeader);
			isElementDisplayed(channelsGroupTitle);
			isElementDisplayed(selectedChannel);
			verify_ListSize_GreaterThanZero(channelsCount);
			verify_YouAreWatching();
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage verify_YouAreWatching() {
		try 
		{
			isElementDisplayed(youAreWatchingHeader);
			isElementDisplayed(youAreWatchingChannelTitle);
			isElementDisplayed(addToWatchlistLogo);
			isElementDisplayed(addToWatchlistText);	
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage verifyPlaybackChannelNameFromSearch(String channelName) {
		try 
		{			
			click(channelport, "Click on Channel window");
			click(channelfullscreenbtn, "Click on Channel window");
			Thread.sleep(10000);
			click(channelfullscreenplayer,"click on player window");		
			String youWereWatchingChannelName = getText(youAreWatchingChannelTitle, "Channel name");	
			click(channelfullscreenplayer,"click on player window");
			verifyText(youWereWatchingChannelName.toLowerCase(), channelName.toLowerCase());
			Thread.sleep(10000);
			click(channelfullscreenplayer,"click on player window");
			click(closeFullScreen, "Close Full screen");
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage verifyPlaybackChannelName() {
		try 
		{			
			//click(channelsCount, "Channel");
			isElementDisplayed(channelsPlayerWindow);
			String youWereWatchingChannelName = getText(youAreWatchingChannelTitle, "Channel name");
			verifyText(youWereWatchingChannelName, DiscoverPage.channelName);
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage verifyPlaybackForPayTV() {
		try 
		{	
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				String strPromptDescForPayTV = getText(promtDescForPayTV, "Channel name");
				String strPromptButtonForPayTV = getText(promtButtonForPayTV, "Channel name");
				verifyTextContains(strPromptDescForPayTV, "premium channel");
				verifyText(strPromptButtonForPayTV, "Subscribe to watch");
			}
			else
			{
				verifyPlaybackChannelName();
			}
						
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage clickMaximizePlayer() {
		try 
		{			
			click(btnFullScreen, "Full screen");
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage clickCloseFullScreen() {
		try 
		{	
			waitForInVisibilityOfElement(closeFullScreen, "Close Full screen");
			//TouchAction action = new TouchAction((MobileDriver<?>) driver);
			//action.tap(1000, 500).perform();
			
			new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(1000, 500))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			.release().perform();
			
			click(closeFullScreen, "Close Full screen");
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage verify_FullScreen() {
		try 
		{			
			isElementDisplayed(currentChannelNameInFullScreen);
			isElementDisplayed(currentChannelLogoInFullScreen);
			isElementDisplayed(closeFullScreen);
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage clickChannelsMenuInFullScreen() {
		try 
		{	
			waitForInVisibilityOfElement(currentChannelLogoInFullScreen, "Channels Menu");
			//TouchAction action = new TouchAction((MobileDriver<?>) driver);
			//action.tap(1000, 500).perform();
			new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(1000, 500))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			.release().perform();
			
			click(currentChannelLogoInFullScreen, "Channels Menu");
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
	public ChannelsPage changeChannelFromFullScreen() {
		try 
		{			
			verify_ListSize_GreaterThanZero(channelGroupsInFullScreen);
			clickOnElementInAList(channelGroupsInFullScreen, 2);
			String channelName = getTextOfAnElementInAList(channelListInAGroupInFullScreen, 2);
			clickOnElementInAList(channelListInAGroupInFullScreen, 2);
			//TouchAction action = new TouchAction((MobileDriver<?>) driver);
			//action.tap(1000, 500).perform();
			
			new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(1000, 500))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			.release().perform();
			
			String CurrentChannelName = getText(currentChannelNameInFullScreen, "Current Channel Name");
			verifyText(CurrentChannelName, channelName);
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	
}

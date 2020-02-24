package android.pages;


import org.openqa.selenium.By;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class PlayerPage extends ActionEngine {

	public static By forward = By.id("tv.hooq.android:id/iconForward");
	public static By rewind = By.id("tv.hooq.android:id/iconRewind");
	public static By playPause = By.id("tv.hooq.android:id/iconPlayPause");
	public static By nextEpisode = By.id("tv.hooq.android:id/iconNextEpisode");
	public static By playTime = By.id("tv.hooq.android:id/playTime");
	public static By seekbar = By.id("tv.hooq.android:id/seek_bar");
	public static By findEpisode = By.id("tv.hooq.android:id/iconPlaylist");
	public static By settings = By.id("tv.hooq.android:id/iconSettings");
	public static By quality = By.id("tv.hooq.android:id/iconQuality");
	public static By labelQuality = By.id("tv.hooq.android:id/lblQuality");
	public static By labelSubtitles = By.id("tv.hooq.android:id/lblSubtitles");
	public static By labelAudio = By.id("tv.hooq.android:id/lblAudioTrack");
	public static By contentTitle = By.id("tv.hooq.android:id/videoTitle");
	public static By episodeSeasonAndTitle = By.id("tv.hooq.android:id/episodeTitle");
	public static By closePlayer = By.id("tv.hooq.android:id/btnClose");
	public static By closeQualitySettings = By.id("tv.hooq.android:id/close");
	public static By closeFindEpisodes = By.id("tv.hooq.android:id/episodeListCloseButton");
	public static By labelSeasonsCount = By.id("tv.hooq.android:id/seasonCount");
	public static By r21PinInput = By.id("tv.hooq.android:id/pinInput");
	public static By r21PinInputEditbox = By.className("android.widget.EditText");
	public static By incorrectPIN = By.id("tv.hooq.android:id/incorrectPin");
	public static By playerWindow = By.id("tv.hooq.android:id/brightcoveVideoView");
	//public static By activePlayTime = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/playTime']");
	//public static By contentTitle = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/videoTitle']");
	//public static By episodeSeasonAndTitle = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/episodeTitle']");

	public PlayerPage verify_R21PINPopUP() {
		try 
		{
			isElementDisplayed(r21PinInput);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	public PlayerPage type_R21PIN(String strPIN) {
		try 
		{
			typeOnElementInAnotherElement(r21PinInput, r21PinInputEditbox, 0, strPIN);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	public PlayerPage verifyIncorrectPinErrorMsg() {
		try 
		{
			isElementDisplayed(incorrectPIN);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	public PlayerPage isPlayerWindowDisplayed() {
		try 
		{
			isElementDisplayed(playerWindow);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	
	public PlayerPage clickForward() {
		try 
		{
			fnClickOnPlayerOption("Forward");

		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickRewind() {
		try 
		{
			fnClickOnPlayerOption("Rewind");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickPause() {
		try 
		{
			
			fnClickOnPlayerOption("PlayPause");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickPlay() {
		try 
		{
			fnClickOnPlayerOption("PlayPause");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickNextEpisode() {
		try 
		{
			fnClickOnPlayerOption("NextEpisode");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickFindEpisodes() {
		try 
		{
			fnClickOnPlayerOption("FindEpisodes");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickSettings() {
		try 
		{			
			fnClickOnPlayerOption("Settings");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}
	public PlayerPage verifySettings() {
		try 
		{	
			String strSubtitle = getText(labelSubtitles, "Subtitle header");
			String strAudio = getText(labelAudio, "Audio header");
			verifyText(strSubtitle, "Subtitles");
			verifyText(strAudio, "Audio");
		/*	List<MobileElement> SubtitlesList=driver.findElement(By.id("tv.hooq.android:id/spinnerSubtitles")).findElements(By.id("tv.hooq.android:id/title"));
			List<MobileElement> AudioList=driver.findElement(By.id("tv.hooq.android:id/spinnerAudioTrack")).findElements(By.id("tv.hooq.android:id/title"));
			
			if(ContentDetailsPage.subtitles.length +1 == SubtitlesList.size())
			{
				for(int j=0; j<ContentDetailsPage.subtitles.length; j++)
				{
					for (int i=1; i<SubtitlesList.size(); i++)
					{
						//System.out.println(SubtitlesList.get(i).getText());
						if(ContentDetailsPage.subtitles[j].trim().contains(SubtitlesList.get(i).getText()))
						{
							System.out.println(SubtitlesList.get(i).getText()+" is displayed in both content details page and player settings");
							break;
						}
					}
				}
			}
			
			for (int i=0; i<AudioList.size(); i++)
			{
				System.out.println(AudioList.get(i).getText());
			}*/
			//System.out.println(strSubtitle);
			//System.out.println(strAudio);	
			//TestUtilities.logReportPass("Verified Audio and Subtitle headers in Player settings");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyQuality() {
		try 
		{			
			String strQuality = getText(labelQuality, "Quality header");
			//System.out.println(strQuality);	
			verifyText(strQuality, "Quality");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyFindEpisodes() {
		try 
		{			
			String strSeasons = getText(labelSeasonsCount, "Seasons header");
			//System.out.println(strSeasons);		
			verifyTextContains(strSeasons, "Seasons");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickQuality() {
		try 
		{
			fnClickOnPlayerOption("Quality");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage getContentTitle(String strContentTitle) {
		try 
		{
			clickPause();
			/*	TouchAction action = new TouchAction((MobileDriver<?>) driver);
			action.tap(1000, 500).perform();*/
			String title = getText(contentTitle, "Content Title");
			verifyText(title, strContentTitle);
			//System.out.println(title);
			clickPlay();
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage getEpisodeTitle() {
		try 
		{
			clickPause();
			getText(episodeSeasonAndTitle, "Episode Title");
			clickPlay();
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public String getPlayTime() {
		String strPlayTime = null;
		try 
		{
			clickPause();
			strPlayTime = getText(playTime, "Play time");
			clickPlay();
			return strPlayTime;
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return strPlayTime;
	}

	public ContentDetailsPage closePlayer() {
		try 
		{
			fnClickOnPlayerOption("Close");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}

	public PlayerPage closeSettingsQuality() {
		try 
		{
			fnClickOnPlayerOption("Settings");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}

	public PlayerPage closeFindEpisodes() {
		try 
		{
			fnClickOnPlayerOption("CloseFindEpisode");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}


	public static void fnClickOnPlayerOption(String loc)
	{
		try
		{
			Thread.sleep(5000);
		
			TouchAction action = new TouchAction((MobileDriver<?>) driver);
			//action.tap(1000, 500).perform();
			action.tap(PointOption.point(1000, 500));
			
			
			if(loc.equalsIgnoreCase("PlayPause") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{		
				/*action.tap((ht*25)/100,(int)(wd*43.1)/100).perform(); 
				action.tap((ht*25)/100,(int)(wd*43.1)/100).perform(); */
				//action.tap(270, 888).perform(); 
				//action.tap(270, 888).perform();
				
				action.tap(PointOption.point(270, 888));
				action.tap(PointOption.point(270, 888));
				
			}

			else if(loc.equalsIgnoreCase("Settings") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				//action.tap(1917, 13).perform();
				//action.tap(1917, 13).perform();
				action.tap(PointOption.point(1917, 13));
				action.tap(PointOption.point(1917, 13));
			}
			else if(loc.equalsIgnoreCase("Quality") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				/*action.tap(2061, 12).perform();
					action.tap(2061, 12).perform();*/
				//action.tap(270, 888).perform(); 
				//action.tap(270, 888).perform();
				action.tap(PointOption.point(270, 888));
				action.tap(PointOption.point(270, 888));
				
				click(quality, "Quality");
			}
			else if(loc.equalsIgnoreCase("FindEpisodes") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				//action.tap(1773, 13).perform();
				//action.tap(1773, 13).perform();
				action.tap(PointOption.point(1917, 13));
				action.tap(PointOption.point(1917, 13));
			}
			else if(loc.equalsIgnoreCase("Forward") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				//action.tap(414, 888).perform();
				//action.tap(414, 888).perform();
				action.tap(PointOption.point(414, 888));
				action.tap(PointOption.point(414, 888));
			}
			else if(loc.equalsIgnoreCase("Rewind") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				//action.tap(126, 888).perform();
				//action.tap(126, 888).perform();
				action.tap(PointOption.point(126, 888));
				action.tap(PointOption.point(126, 888));
			}
			else if(loc.equalsIgnoreCase("NextEpisode") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				//action.tap(558, 888).perform();
				//action.tap(558, 888).perform();
				action.tap(PointOption.point(558, 888));
				action.tap(PointOption.point(558, 888));
			}				
			else if(loc.equalsIgnoreCase("ClosePlayer") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				//action.tap(126, 16).perform();
				//action.tap(126, 16).perform();
				action.tap(PointOption.point(126, 16));
				action.tap(PointOption.point(126,16));
			}
			else if(loc.equalsIgnoreCase("Close") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				click(closeQualitySettings, "Close Setting/Quality");
			}
			else if(loc.equalsIgnoreCase("CloseFindEpisode") && ConfigDetails.strPlatformName.equalsIgnoreCase("android"))
			{	
				click(closeFindEpisodes, "Close Find Episodes");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void verify_Playback() {

		ReadTestData.fnAddTestRailScriptID(1234);
		try {				

			String playStartTime = getPlayTime();
			ReporterLog.warning("Verify Playback", "PlayStartTime: "+playStartTime+" and please review screenshot");

			Thread.sleep(10000);
			String playEndTime = getPlayTime();					
			ReporterLog.warning("Verify Playback", "PlayEndTime: "+playEndTime+" and please review screenshot");

			ReporterLog.softAssert.assertAll();
		} catch (Exception e) {

			TestUtilities.logReportFailure(e, "verify_Playback");
		}
	}

}

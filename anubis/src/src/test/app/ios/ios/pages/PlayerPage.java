package ios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.ClickAction;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;

public class PlayerPage extends ActionEngine {

	// locators
	public static By navBackBtn = By.name("navbar back btn");
	public static By playerWindow = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By btnPause = By.id("HQPauseIcon");
	public static By btnPlay = By.id("HQPlayIcon");
	public static By btnRewind = By.id("HQRewind15Icon");
	public static By SubtitlesIcon = By.id("HQSubtitlesIcon");
	public static By timeCounter = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText");
	public static By playSlider = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeSlider");
	public static By playerBack = By.id("Icon back");
	public static By btnForward = By.id("HQForward15Icon");
	public static By seekbar = By.xpath("//XCUIElementTypeSlider");
	public static By btnQuality = By.id("HQQuality");
	public static By qualitySelectedMovies = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	public static By qualitySelectedTvshows = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[3]");
	public static By lblVideoQuality = By.id("VIDEO QUALITY");
	public static By qualityAuto = By.id("Auto");
	public static By qualityLow = By.id("Low");
	public static By qualityMedium = By.id("Medium");
	public static By qualityHigh = By.id("High");
	public static By Done = By.id("Done");
	public static By lblAudioTrack = By.id("AUDIO");
	public static By lblsubtitle = By.id("SUBTITLES");
	public static By videoTitle = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]");
	public static By videoTitleSeasonDetails = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]");
	public static By episodeBtn = By.id("HQEpisodeIcon");
	public static By seasonText = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By seasonClose = By.id("Icon Close");
	public static By episodeText = By.xpath(
			"//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]");

	public static String strSecondEpisodeTitle ;
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :- 4-July-2019
	 */
	public MePage navigateBack() {
		try {
			Thread.sleep(10000);
			clickNoWait(playerWindow, "Player Window");
			clickNoWait(playerBack, "Back From Player page");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}

	public PlayerPage clickPause() {
		try {			
			Thread.sleep(15000);			
			if(!isElementPresentInDom(btnPause))
				clickNoWait(playerWindow, "Player Window");
			driver.findElement(btnPause).click();
		} catch (Exception e) {
			driver.findElement(playerWindow).click();
			driver.findElement(btnPause).click();
			e.printStackTrace();
			//TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickSeasonClose() {
		try {
			waitForVisibilityOfElement(seasonClose, "season close button");
			click(seasonClose, "season close button");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickEpisode() {
		try {
			
			if(!isElementPresentInDom(episodeBtn))
				clickNoWait(playerWindow, "Player Window");
			click(episodeBtn, "Episode Button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickQuality() {
		try {
			click(btnQuality, "Quality Button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickSubtitle() {
		try {
			Thread.sleep(15000);
			driver.findElement(playerWindow).click();
			driver.findElement(SubtitlesIcon).click();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickDone() {
		try {
			click(Done, "Done button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage clickPlay() {
		try {
			if(!isElementPresentInDom(btnPlay))
				clickNoWait(playerWindow, "Player Window");
			clickNoWait(btnPlay, "Play Button");
		} catch (Exception e) {
			clickNoWait(playerWindow, "Player Window");
			click(btnPlay, "Play Button");
			//TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyQualityAuto() {
		try {
			isElementDisplayedPlayerControl(qualityAuto);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	private PlayerPage isElementDisplayedPlayerControl(By loc) {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(loc))
				System.out.println("Element " + loc.toString() + " is displayed");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();

	}

	public PlayerPage verifyQualityHigh() {
		try {
			isElementDisplayedPlayerControl(qualityHigh);
		} catch (Exception e) {     
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyQualityMedium() {
		try {
			isElementDisplayedPlayerControl(qualityMedium);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyQualityLow() {
		try {
			isElementDisplayedPlayerControl(qualityLow);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyRewind() {
		try {
			if(!isElementPresentInDom(btnRewind))
				clickNoWait(playerWindow, "Player Window");
			isElementDisplayedPlayerControl(btnRewind);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyForward() {
		try {
			if(!isElementPresentInDom(btnForward))
				click(playerWindow, "Player Window");
			isElementDisplayedPlayerControl(btnForward);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifySeekBar() {
		try {
			Thread.sleep(5000);
			driver.findElement(playerWindow).click();
			isElementDisplayedPlayerControl(seekbar);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyVideoTimeCounter() {
		try {
			//if(!isElementPresentInDom(timeCounter))
			Thread.sleep(5000);
			clickNoWait(playerWindow, "Player Window");
			getText(timeCounter, "Video Playing Time");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyVideoTitleName() {
		try {
			
			if(!isElementPresentInDom(videoTitle))
				clickNoWait(playerWindow, "Player Window");
			driver.findElement(videoTitle).getText();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifySubTitleLabel() {
		try {
			getTextPlayerControl(lblsubtitle, "Subtitle Label");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyAudioLabel() {
		try {
			getTextPlayerControl(lblAudioTrack, "Audio Track Label");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	private void getTextPlayerControl(By loc, String string) {
		System.out.println(string + " ==> " + driver.findElement(loc).getText());

	}

	public PlayerPage verifyQualitySelectedMovies() {
		try {
			Thread.sleep(500 * GlobalConstant.WAIT_MEDIUM_20_SEC);
			clickNoWait(playerWindow, "Player Window");
			getTextPlayerControl(qualitySelectedMovies, "Selected Quality");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyQualitySelectedTVShows() {
		try {
			if(!isElementPresentInDom(qualitySelectedTvshows))
				clickNoWait(playerWindow, "Player Window");
			getTextPlayerControl(qualitySelectedTvshows, "Selected Quality");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyVideoQualityLabel() {
		try {
			getTextPlayerControl(lblVideoQuality, "VIDEO QUALITY");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifySeasonDetails() {
		try {
			waitForVisibilityOfElement(seasonText, "season text");
			getTextPlayerControl(seasonText, "seasonText");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage verifyEpisodeDetails() {
		try {
			waitForVisibilityOfElement(episodeText, "Episode details");
			getTextPlayerControl(episodeText, "Episode Text");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public PlayerPage waitforPlay(int intCounter) {
		try {
			Thread.sleep(intCounter);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	public void verifyVideoPlayingSuccess() {
		try {
		clickPause();	
		
		//Verify Movie Title from Player Window
		if(!isElementPresentInDom(videoTitle))
			clickNoWait(playerWindow, "Player Window");
		
		String movieTitle = driver.findElement(videoTitle).getText();
		verifyText(movieTitle, ReadTestData.R21_MOVIES);
		verifySeekBar();
		navigateBack();
		}catch(Exception e) {
		 TestUtilities.logReportFailure(e);
		}
	}
	
	public void verifySlider(String sliderPercentage) {
		try {
		driver.findElement(playSlider).sendKeys(sliderPercentage);
		ReporterLog.info("Player Slider Validation", "Sliding Player Seek bar to % : "+sliderPercentage);
		}catch(Exception e) {
			if(!isElementPresentInDom(videoTitle))
				clickNoWait(playerWindow, "Player Window");	
			driver.findElement(playSlider).sendKeys(sliderPercentage);
			ReporterLog.info("Player Slider Validation", "Sliding Player Seek bar to % : "+sliderPercentage);
		}
	}
	
	/**
	 * @author mdafsarali
	 * @throws Exception 
	 * @date : 10 December 2019
	 * @Description This Method will test TVShow Episodes Auto play and auto navigation to next episode
	 */
	public void verifyNextEpisodeAutoPlay() throws Exception {
		try {
		List<String> ExpectedEpisodeLists = DiscoverFeedController.getListOfEpisodes(ReadTestData.TV_SERIES, "1");
		System.out.println("All episodes "+ExpectedEpisodeLists);
		//clickPause();
		
		//Validate Title of the Episode 
		
		if(!isElementPresentInDom(videoTitle))
			clickNoWait(playerWindow, "Player Window");
		String strFirstEpisodeTitle = driver.findElement(videoTitleSeasonDetails).getText();
		
		verifyTextContains(strFirstEpisodeTitle,ExpectedEpisodeLists.get(0) ); // validate First episode w.r.t API title
		
		if(!isElementPresentInDom(videoTitle))
			clickNoWait(playerWindow, "Player Window");
		
		verifySlider("0.40");
		
		//Click on the Play button then wait till one episode completes and then move to another 
		//clickPlay(); 
		
		Thread.sleep(10000);
		if(!isElementPresentInDom(videoTitle))
			clickNoWait(playerWindow, "Player Window");
		
		verifySlider("0.60");
		//Waiting for Next episode to play
		Thread.sleep(20000); 
		
		clickPause();
		
		//verifySlider("0.90"); 
		
		verifySlider("0.99");
		
		Thread.sleep(20000); 
		
		//Now get the Next episode Title 
		
		if(!isElementPresentInDom(videoTitle))
			clickNoWait(playerWindow, "Player Window");
		clickPlay();
		
		 strSecondEpisodeTitle = driver.findElement(videoTitleSeasonDetails).getText();
	
		int c=0;
		while(!ExpectedEpisodeLists.get(1).contains(strSecondEpisodeTitle)) {
		
			Thread.sleep(20000);
			if(!isElementPresentInDom(videoTitle))
				clickNoWait(playerWindow, "Player Window");
			 strSecondEpisodeTitle = driver.findElement(videoTitleSeasonDetails).getText();
			 
			 if(c==2) {
				 break;
			 }
			c++;
		}
		
		verifyTextContains(strSecondEpisodeTitle,ExpectedEpisodeLists.get(1)); //Validate Second episode playing successfully 
		
		//Move Slider to 60% for Continue watching section Test
		
		if(!isElementPresentInDom(videoTitle))
			clickNoWait(playerWindow, "Player Window");
		verifySlider("0.60");
		Thread.sleep(20000);  //Playing second season for 20sec
	}catch(Exception e) {e.printStackTrace();}
	}	
}

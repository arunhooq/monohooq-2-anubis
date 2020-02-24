package web.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.ReadTestData;


public class PlayerPage extends ActionEngine {

	public By movieTitlefstTVShow=By.cssSelector("[class*='PortraitTitleCard__TitleCardContainer'] img");
	public By headerMovies = By.cssSelector(".mobile .e2e-tab-movies");
	public By headerTVShows = By.cssSelector(".mobile .e2e-tab-tvshows");
	public By headerRent = By.cssSelector(".mobile .e2e-tab-rent");
	public static By signupBtn = By.tagName("label");
	public static By footerSignUP = By.xpath("(//*[@href='/auth/signup'])[2]");
    public static By closeaudioSubtitleBtn = By.xpath("//div[contains(@class,'MobileModal__TopRightPosition')]/div");

    public static By contentTitleTxt = By.cssSelector("[class*='e2e-titleHeader TitleBasicInfo__TitleHeader']");
    public static By contentPageAudio = By.xpath("//div[contains(@class,'TitleBasicInfo__Container')]/div[2]");
    public static By contentPageSubtitle = By.xpath("//div[contains(@class,'TitleBasicInfo__Container')]/div[3]");
    public static By restrictedContent = By.xpath("//*[contains(@class,'PromptModalBase__ModalHeader')]");
    public static By playerTitleName = By.xpath("//*[contains(@class,'e2e-player-title')]");
    public static By playerSeasonEpisodeNo = By.xpath("//*[contains(@class,'e2e-player-title')]/following-sibling::div");
    public static By playerwindow = By.xpath("//*[contains(@class,'VideoComponent__VideoWrapper-adotv8-0')]//div/div");
 
	public static By novaPageValidation = By.xpath("//div[@class='paymentPartnerInfo__payment-wrapper___2FUDu']/img");

 // BROWSE
 	public static By allMovies = By.xpath("//h3[text()='ALL MOVIES']");
 	public static By allPlaybutton = By.xpath("//*[@class='play']");
 	public static By searchText = By.xpath("//span[text()='Search']");
 	public static By searchBox = By.xpath("//input[@id='search-panel-input']");
 	public static By searchedItem = By.xpath("//*[@id='search']/a[1]/div[1]/img");
 	public static By watchNowbtn = By.xpath("//*[contains(@class,'TitleActionButtons__DesktopButtonContainer')]/a");// a[contains(text(),'Watch')]");
 	public static By MoviesName = By.xpath("//*[@id='card-description']/div/h4");
 	public static By allMoviesList = By.xpath("//div[@class='content-title']");
 	public static By lblBrowseContent = By.xpath("(//div[@class='overlay'])[2]");
 	public static By lblBrowseContentTitle = By.xpath("((//div[@class='overlay'])[2]/../following-sibling::div[4])");
	
 	 public static By btnWatchNow = By.xpath("//*[contains(@class,'StickyFooterToast__StickyFooterContent-nj')]//a");
     public static By btnPlay= By.xpath("//button[@class='PlayButton__Button-ly9s2o-0 kKWwlP']");
     public static By playerPlayBtn=By.xpath("//*[contains(@icon,'play.svg')]");
     public static By playerPauseBtn=By.xpath("//*[contains(@class,'PlayerControlsBottomContainer')]/div[2]");
     public static By playerForwardBtn=By.xpath("//*[contains(@icon,'forward-15.svg')]");
     public static By playerRewindBtn=By.xpath("//*[contains(@icon,'rewind-15.svg')]");	   
     public static By playersettingReamingTime =By.xpath("//*[contains(@class,'RemainingTimeDisplay')]");
     public static By playersettingMaximizeBtn=By.xpath("//*[contains(@icon,'maximize.svg')]");
     public static By paymentHooqLogo=By.xpath("//a[@class='navbarWeb__logo___2ephX']");
     public static By playerMouseover=By.xpath("PlayerControls__PlayerControlsWrapper-y5byqc-0 dVUsQw");
     public static By player_BackArrow = By.xpath("//a[@class='e2e-iconButton IconButton__WrappedLink-s1hapitg-0 kfYEjC']/img");
     public static By findEpisodeBtn = By.xpath("//*[contains(@class,'e2e-player-episodes')]");
     public static By audioSubtitleBtn = By.xpath("(//div[contains(@class,'VideoTitle__VideoTitleWrapper')]//following-sibling::div[contains(@class,'e2e-player-subtitle-audio')])[2]");
     public static By durationContentdetailapge = By.xpath("//div[@class='TitleBasicInfo__Container-kdqnsj-0 kiJNYZ']//div[2]");
     public static By seasonDropdown = By.xpath("//div[@class='tab-label']/span");
     public static By seasonSelectorLst = By.cssSelector("ul.SeasonModalSelector__List-h7ngy2-0 li:nth-of-type(2)");
     public static By playRndmEpisode = By.cssSelector("(//*[contains(@class, 'EpisodeList__Episode')]//button[contains(@class,'PlayButton__Button')])[6]");
     public static By loginTitleVerification = By.tagName("label");
     public static By nextBtn = By.xpath("//button[text()='Next']");
     public static By skipBtn = By.xpath("//*[contains(@class,'navbarWeb__button-skip')]");
     public static By Restrictedcontent = By.xpath("h2[contains(@class,'PromptModalBase__ModalHeader')]");
     public static By okayBtn = By.xpath("//button[text()='Okay, got it!!']");
     public static By closeBtn = By.xpath("//button[text()='Close']");
     public static By tryagainPayment = By.xpath("//button[text()='Try again']");
     public static By videoPlayer = By.xpath("//video[contains(@class,'VideoComponent__VideoWrapper-adotv8-0')]");
     public static By drmOverlay= By.cssSelector("[class*='vjs-tech']");
     public static By drmVideoPlayer = By.cssSelector("[class*='vjs-has-started']");
     
 	 public static By watchTrailer = By.xpath("//*[contains(@class,'WatchTrailerButton')]");

     public static By episodeDuration=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>h3+div");

     public static By backArrowPlayback = By.cssSelector("[class*='BackButton__BackButtonElement']");
 	 //public static By trailerPlaybackTitle = By.xpath("//div[text()='Trailer: "+ReadTestData.TVOD_CONTENT+"']");
 	 
 	 //public static By moviePlaybackTitle = By.xpath("//div[text()='"+ReadTestData.FREE_CONTENT+"']");
     public static By moviePlaybackTitle = By.cssSelector("[class*='e2e-player-title']");
 	 public static By tvShowPlaybackTitle = By.xpath("//div[text()='"+ReadTestData.TV_SERIES+"']");
 	

     
     public PlayerPage verifyTrailerPlayback() {

 		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
 		try {
 			
 			waitForVisibilityOfElement(backArrowPlayback, "backArrowTrailerPlayback");

 			//locator text varies dependent on region			
 			String searchString = ReadTestData.TRAILER_PREFIX + " " + ReadTestData.TVOD_CONTENT;
 			
 			System.out.println("Looking for element on screen that contains text: '"+searchString+"'");			
 			waitForVisibilityOfElement(By.xpath("//div[text()='"+searchString+"']"), "trailerPlaybackTitle");

 			waitForVisibilityOfElement(videoPlayer, "videoPlayer");
 			
 			seekThroughPlayerWindow("30");
 			click(videoPlayer, "videoPlayer");
		 	//ReporterLog.infoWithScreenshot("PlayerWindow", "Screenshot After seek through 30 seconds");
 			click(videoPlayer, "videoPlayer");
 			TestUtilities.logReportPass("Verified the Player window of Trailer for Title and BackArrow");
 		
 		} catch (Exception e) {
 			TestUtilities.logReportFatal(e);
 		}
 		return new PlayerPage();
 	}
     
     public PlayerPage verifyMoviePlayback() {

  		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
  		try {
  			
  			if(ConfigDetails.strPlatformName.toLowerCase().equals("android-chrome") 
  					|| ConfigDetails.strPlatformName.toLowerCase().equals("tablet-chrome")) {
  				clickAllowOnAlert(); //First time when opening player window on Android phones/tablet native alert appears to allow secured content
  			}
  			
  			Thread.sleep(30000);

			waitForVisibilityOfElement(drmVideoPlayer, "Waiting for player window");
			seekThroughDRMPlayerWindow("30");

			JSClick(drmVideoPlayer, "Click on player window");
			waitForElementClickable(backArrowPlayback, "backArrowTrailerPlayback");
			waitForVisibilityOfElement(moviePlaybackTitle, "moviePlaybackTitle");
			//ReporterLog.infoWithScreenshot("PlayerWindow", "Screenshot After seek through 30 seconds");
			
			TestUtilities.logReportPass("Verified the Player window of Movie for Title and BackArrow");
  		
  		} catch (Exception e) {
  			TestUtilities.logReportFatal(e);
  		}
  		return new PlayerPage();
  	}
     
     public PlayerPage verifyTVShowPlayback() {

   		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
   		try {
   			
   			if(ConfigDetails.strPlatformName.toLowerCase().equals("android-chrome") 
   					|| ConfigDetails.strPlatformName.toLowerCase().equals("tablet-chrome")) {
   				clickAllowOnAlert(); //First time when opening player window on Android phones/tablet native alert appears to allow secured content
   			}
   			
   			Thread.sleep(10000);
 			waitForVisibilityOfElement(drmVideoPlayer, "Waiting for player window");
 			
 			seekThroughDRMPlayerWindow("30");
 
 			JSClick(drmVideoPlayer, "Click on player window");
 			
 			waitForElementClickable(backArrowPlayback, "backArrowTrailerPlayback");
 			waitForVisibilityOfElement(tvShowPlaybackTitle, "tvShowPlaybackTitle");
 			//ReporterLog.infoWithScreenshot("PlayerWindow", "Screenshot After seek through 30 seconds");
 			
 			TestUtilities.logReportPass("Verified the Player window of TV Show for Title and BackArrow");
   		
   		} catch (Exception e) {
   			TestUtilities.logReportFatal(e);
   		}
   		return new PlayerPage();
   	}
     
 	
	/***
	 * Function Name :- verifyPlayBackTVShows Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifyPlayBackTVShows(String userType) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			//click(headerTVShows, "TV SHOWS");
			waitForVisibilityOfElement(movieTitlefstTVShow, "");
			WebElement title = driver.findElement(movieTitlefstTVShow);
			String fstTitle = title.getAttribute("title");
			ReporterLog.pass("Verify Content Title on TV Shows page", fstTitle + " is dispalyed successfully");
			click(movieTitlefstTVShow, "Click on content");
			String contentName = getText(contentTitleTxt, "");
			if (fstTitle.equalsIgnoreCase(contentName)) {
				ReporterLog.pass("Verify content title in content details page",
						"" + fstTitle + "" + "" + contentName + "" + " is Matched successfully");
				

			} else {
				ReporterLog.fail("Verify content title in content details page",
						"" + fstTitle + "" + "" + contentName + "" + " contents are not Matched");
			}

			if (isNumOf_Elements_Greater_Than_Zero(watchNowbtn)) {
				ReporterLog.pass("Verify content details Play Btn ", "Play button is displayed in content details page");
			}
			
			waitForElementClickable(contentTitleTxt, "contentTitleTxt");
			String titleName = getText(contentTitleTxt, "Get title");
			ReporterLog.pass("Verify content title: ", titleName + "is displayed");
			String episodeDurationTxt = getText(episodeDuration, "Episode Duration");
			String[] arrOfEpisodetime = episodeDurationTxt.split("m");

			if (userType.equalsIgnoreCase("Anonymous")) {
				click(watchNowbtn, "Click on Watch now");
				if (isNumOf_Elements_Greater_Than_Zero(signupBtn)) {
					String signupPage = getText(signupBtn, "Sign UP Page");
					ReporterLog.pass("Verify Sign up",
							"Redirected to '" + signupPage + "' and is displayed successfully for Anonymous user");
					driver.navigate().to(ConfigDetails.strURL);
					driver.navigate().to(ConfigDetails.strURL);
					
				} else if (isNumOf_Elements_Greater_Than_Zero(
						By.xpath("//div[contains(@class,'PromptModalBase__ModalButtonGroup-sc-1')]/a[1]"))) {
					ReporterLog.pass("Verify movie player for Anonymous user",
							"Signup page is displayed successfully while playing the Movie for the Anonymous user ");
					click(By.xpath("//div[contains(@class,'PromptModalBase__ModalButtonGroup-sc-1')]/a[2]"),
							"Click on continue watching");
					driver.navigate().to(ConfigDetails.strURL);
					
				} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
					ReporterLog.pass("Verify TVShow player for Lapsed user pilot episodes",
							"Player window is displayed successfully while playing the pilot episodes");
					driver.navigate().to(ConfigDetails.strURL);
					driver.navigate().to(ConfigDetails.strURL);
					driver.navigate().to(ConfigDetails.strURL);
					
				} else {
					ReporterLog.fail("Verify trailer for Anonymous user",
							"Signup page is not displayed while playing the Movie");
					
				}
			}

			else if (userType.equalsIgnoreCase("Active")) {
				click(watchNowbtn, "Click on Watch now");
				if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
					click(playerwindow, "Click on player window");
					String remainingtime = getText(playersettingReamingTime,
							"get test of Reaming Time in player window");
					if (!arrOfEpisodetime[0].equals(remainingtime)) {
						ReporterLog.pass("Verify duration of content in player window and content deatils page",
								"duration " + episodeDuration + " +" + remainingtime + " is matching");
						
					} else {
						ReporterLog.fail("Verify duration of content in player window and content deatils page",
								"duration " + episodeDuration + " +" + remainingtime + " is not matching");
						
					}
					click(playerwindow, "Click on player window");
					click(playerwindow, "Click on player window");
					String seasonEpisodeNo = getText(playerSeasonEpisodeNo, "season episode Name");
					String[] arrOfEpisodelst = seasonEpisodeNo.split(":");
					String[] arrOfEpisode = arrOfEpisodelst[1].split(" ");
					ReporterLog.pass("Verify movie player for Active user",
							"Player window is displayed successfully while playing the Movie");

					click(playerwindow, "Click on player window");
					click(playerPauseBtn, "Click on pause button");
					click(playerwindow, "Click on player window");
					click(playerPauseBtn, "Click on play button");
					click(playerwindow, "Click on player window");
					click(findEpisodeBtn, "Click on Find episode list button");
					click(By.xpath("(//button[contains(@class,'PlayButton__Button')])[3]"),
							"Click on 3rd episode from find episode");
					click(playerwindow, "Click on player window");
					String seasonEpisodeNo2 = getText(playerSeasonEpisodeNo, "Content Name");
					String[] arrOfEpisodelst2 = seasonEpisodeNo2.split(":");
					String[] arrOfEpisode2 = arrOfEpisodelst2[1].split(" ");

					if (!arrOfEpisode[0].equalsIgnoreCase(arrOfEpisode2[0])) {
						ReporterLog.pass("Verify episode changing",
								"" + arrOfEpisode[0] + " +" + arrOfEpisode2[0] + " is matching");
						
					} else {
						ReporterLog.fail("Verify episode changing",
								"duration " + arrOfEpisode[0] + " +" + arrOfEpisode2[0] + " is not matching");
						
					}
					click(playerwindow, "Click on player window");
					click(audioSubtitleBtn, "Click on Audio and subtitle button");
					click(closeaudioSubtitleBtn, "Click on close button in player window");
					ReporterLog.pass("Verify movie player for Active user",
							"Player window is displayed successfully while playing the Movie");
					driver.navigate().to(ConfigDetails.strURL);
				} else {
					ReporterLog.fail("Verify movie player for Active user",
							"Player window is not displayed while playing the Movie");
					
				}
				if (isNumOf_Elements_Greater_Than_Zero(watchNowbtn)) {
					ReporterLog.pass("Verify content deatails",
							"content deatils page is displayed after clicking on back button");
					driver.navigate().to(ConfigDetails.strURL);
				}

			} else if (userType.equalsIgnoreCase("Lapsed")) {
				click(watchNowbtn, "Click on Watch now");
				if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
					ReporterLog.pass("Verify movie player for Lapsed user",
							"Nova planselector page is displayed successfully while playing the Movie");
					driver.navigate().to(ConfigDetails.strURL);
					
				} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
					ReporterLog.pass("Verify TVShow player for Lapsed user pilot episodes",
							"Player window is displayed successfully while playing the pilot episodes");
					driver.navigate().to(ConfigDetails.strURL);
					driver.navigate().to(ConfigDetails.strURL);
					driver.navigate().to(ConfigDetails.strURL);
					
				} else {
					ReporterLog.fail("Verify movie player for Lapsed user",
							"Nova planselector page is not displayed while playing the Movie");
					
				}

			}

			BasePage basePage = new BasePage();
			basePage.getDiscoverPage();

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	public PlayerPage verify_Audio_Subtitle_PlayerPage() { 
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
				if(!ConfigDetails.strPlatformName.toLowerCase().contains("safari")) {
					waitForElementClickable(playerwindow, "Waiting for player window");
					JSClick(playerwindow, "Click on player window");
					clickNoWait(audioSubtitleBtn, "Click on Audio & Subtitle Button");
					
					List<WebElement> playerAudioList = driver.findElements(
							By.xpath("(//div[contains(@class,'SubtitleAudioSelector')]//ul)[1]/li"));
					
					String[] audioText = new String[playerAudioList.size()];
					
					for (int j = 0; j < playerAudioList.size(); j++) 
						
						audioText[j] = playerAudioList.get(j).getText();
					
					ArrayList<String> audioList = new ArrayList<String>(Arrays.asList(audioText));
					Collections.sort(audioList);
					
					FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[2].getMethodName(),
							"Player Page: <br>  audioText: "+audioList.toString(), "purple" );
					
					verifyText(Integer.toString(audioSubTitlesBean.getAudio().size()), Integer.toString(playerAudioList.size()));
					verifyArrayDeepEquals(audioSubTitlesBean.getAudio().toArray(), audioList.toArray());
						
					ReporterLog.pass("Verification of Audio in Content details page and Player", Arrays.deepToString(audioList.toArray()) + " is displayed in both the pages");				
					
					List<WebElement> playerSubtitleList = driver.findElements(
							By.xpath("(//div[contains(@class,'SubtitleAudioSelector')]//ul)[2]/li"));
		
					String[] subtitleText = new String[playerSubtitleList.size()];
					
					for (int j = 0; j < playerSubtitleList.size(); j++) 
						
						subtitleText[j] = playerSubtitleList.get(j).getText().trim();
					
					ArrayList<String> subtitleListPlayer = new ArrayList<String>(Arrays.asList(subtitleText));
					Collections.sort(subtitleListPlayer);
				    
					FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[2].getMethodName(),
							"subtitleListPlayer: "+subtitleListPlayer.toString(), "purple" );
				
					verifyText(Integer.toString(audioSubTitlesBean.getsubTitle().size()), Integer.toString(subtitleListPlayer.size()));
					verifyArrayDeepEquals(audioSubTitlesBean.getsubTitle().toArray(), subtitleListPlayer.toArray());
					
					ReporterLog.pass("Verification of Subtitle in Content details page and Player", Arrays.deepToString(subtitleListPlayer.toArray()) + " is displayed in both the pages");
									
					click(closeaudioSubtitleBtn, "Click on close button in player window");
				}
								
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		return new PlayerPage();
	}

	public static void sendActionToElement(String elementName, String action) {
		try {
			driver.executeScript("document.body.querySelector('"+elementName+"')."+action+"()");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			e.printStackTrace();
		}
	}
	
}
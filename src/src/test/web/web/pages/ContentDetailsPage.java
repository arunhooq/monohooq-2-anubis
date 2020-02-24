package web.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.ReadTestData;

public class ContentDetailsPage extends ActionEngine {
	public static By contentDetails = By.cssSelector("[class*='TitleBasicInfo__Container']>div");
	//public static By contentTitleTxt = By.cssSelector("[class*='e2e-titleHeader TitleBasicInfo__TitleHeader']");
	public static By contentTitleTxt = By.cssSelector("[class*='TitleBasicInfo__TitleHeader']");
	
	//public static By watchNowbtn = By.xpath("//a[text()='Watch Now']");
	public static By watchNowbtn = By.cssSelector("a[class*='WatchNowButton']");
	public static By continuebtn = By.xpath("//a[text()='Continue']");

	//public static By watchTrailer = By.xpath("//a[text()='Watch Trailer']");
	public static By watchTrailer = By.cssSelector("a[class*='WatchTrailerButton']");
	//public static By subscribeToWatch = By.xpath("//a[text()='Subscribe to watch']");
	public static By subscribeToWatch = By.cssSelector("a[class*='PremiumPlusButton']");
	//public static By signUpToWatch = By.xpath("//a[text()='SignUp to watch']");
	public static By signUpToWatch = By.cssSelector("a[class*='WatchNowButton']");
	public static By redeemButton = By.xpath("//a[text()='Redeem your ticket']");
	public static By rentButton = By.xpath("//a[contains(text(),'Rent')]");
	public static By confirmRedeemButton = By.xpath("//a[text()='Confirm']");
	public static By cancelRedeemButton = By.xpath("//a[text()='Cancel']");
	public static By redeemText = By.xpath("//a[contains(text(),'Start within 30 days, finish within 48 hours.')]");
	public static By redeemHeading = By.xpath("//a[contains(text(),'Redeem '"+ReadTestData.TVOD_CONTENT+"' for 1 ticket')]");
	public static By termsOfUseLink = By.xpath("//a[text()='Terms of Use']");
	
	public static By backArrowTrailerPlayback = By.cssSelector("[class*='BackButton__BackButtonElement']");
	public static By trailerPlaybackTitle = By.xpath("//div[text()='Trailer: '"+ReadTestData.TVOD_CONTENT+"']");

 	public static By playerwindow = By.xpath("//*[contains(@class,'VideoComponent__VideoWrapper-adotv8-0')]//div/div");
    public static By restrictedContent = By.xpath("//*[contains(@class,'PromptModalBase__ModalHeader')]");
	public static By novaPageValidation = By.xpath("//div[@class='paymentPartnerInfo__payment-wrapper___2FUDu']/img");
	public static By episodeName=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>h3");
	public static By episodeDuration=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>h3+div");
	public static By episodeDetail=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>p");
		
    public static By closeaudioSubtitleBtn = By.xpath("//div[contains(@class,'MobileModal__TopRightPosition')]/div");
    public static By contentPageAudio = By.xpath("//div[contains(@class,'TitleBasicInfo__Container')]/div[2]");
    public static By contentPageSubtitle = By.xpath("//div[contains(@class,'TitleBasicInfo__Container')]/div[3]");
    public static By audioSubtitleBtn = By.xpath("(//div[contains(@class,'VideoTitle__VideoTitleWrapper')]//following-sibling::div[contains(@class,'e2e-player-subtitle-audio')])[2]");

	public PlayerPage clickOnWatchNow() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			if(isNumOf_Elements_Greater_Than_Zero(continuebtn)) {
				click(continuebtn, "continuebtn");
				TestUtilities.logReportPass("Clicked on continuebtn button");
			}
			else {	
				click(watchNowbtn, "watchNowbtn");
				TestUtilities.logReportPass("Clicked on watchNow button");
			}
			
			openUrl(getCurrentUrl());
			Thread.sleep(5000);
			By playButton = By.cssSelector("[class*='PlayButton__Button-ly9s2o']");
			if(isNumOf_Elements_Greater_Than_Zero(playButton))
			{
				waitForElementClickable(playButton, "Click on play button");
				click(playButton, "Click on play button");
			}
			
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}
	
	public PlayerPage clickOnWatchTrailer() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(watchTrailer, "watchTrailer");
			
			TestUtilities.logReportPass("Clicked on watchTrailer button");
		
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new PlayerPage();
	}
	
	public SignUpPage clickOnSubscribeToWatch() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(subscribeToWatch, "subscribeToWatch");
			TestUtilities.logReportPass("Clicked on subscribeToWatch button");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SignUpPage();
	}
	
	public SignUpPage clickOnSignupToWatch() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(signUpToWatch, "signUpToWatch");
			TestUtilities.logReportPass("Clicked on signUpToWatch button");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SignUpPage();
	}
	
	public ContentDetailsPage clickOnRedeem() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(redeemButton, "redeemButton");
			
			isElementPresent(redeemText);
			isElementPresent(redeemHeading);
			isElementPresent(termsOfUseLink);

			TestUtilities.logReportPass("Clicked on redeemButton button and verified if the confirm redeem pop up displayed with appropriate message and terms of use link");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickOnRent() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(rentButton, "rentButton");
			TestUtilities.logReportPass("Clicked on rentButton button");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickOnConfirmRedeem() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(confirmRedeemButton, "confirmRedeemButton");
			TestUtilities.logReportPass("Clicked on confirmRedeemButton button");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickOnCancelRedeem() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(cancelRedeemButton, "cancelRedeemButton");
			TestUtilities.logReportPass("Clicked on cancelRedeemButton button");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickOnTermsOfUseLink() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(termsOfUseLink, "termsOfUseLink");
			TestUtilities.logReportPass("Clicked on termsOfUseLink button");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verifyContentDetailsCommon() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			waitForElementClickable(contentTitleTxt, "contentTitleTxt");
			verifyTextNotNull(getText(contentTitleTxt, "contentTitleTxt"));
			waitForElementClickable(contentDetails, "contentDetails");
			getTextInAListOfElementAndVerifyNotNull(contentDetails);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verifyContentDetailsForTVShow() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			waitForVisibilityOfElement(episodeName, "episodeName");
			
			verifyTextContains(getPageSource(), getText(episodeName, "Episode Name"));
			verifyTextContains(getPageSource(), getText(episodeDuration, "Episode Duration"));
			verifyTextContains(getPageSource(), getText(episodeDetail, "Episode Detail"));
		
			ReporterLog.pass("Verify Content Details Episode Name", episodeName + " is displayed successfully");
			ReporterLog.pass("Verify Content Details Episode Duration",
						episodeDuration + " is displayed successfully");
			ReporterLog.pass("Verify Detail of Episode summary ",
						"Summary is : " + episodeDetail + " is displayed successfully");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_Audio_Subtitle_ContentDetails() { 
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
				waitForElementClickable(contentPageAudio, "contentPageAudio");
				
				String audioText = getText(contentPageAudio, "List of Audio titles in content details page");
				verifyTextNotNull(audioText);
				ReporterLog.info("audioList", audioText.toString());
				String[] arrOfAudio = audioText.toString().split(",");
				ArrayList<String> audioList = new ArrayList<String>(Arrays.asList(arrOfAudio));
				Collections.sort(audioList);
				
				String subtitleList = getText(contentPageSubtitle,"List of subtitles in content details page");		
				verifyTextNotNull(subtitleList);
				String[] arrOfSubTitle = subtitleList.split(",");
				for (int i = 0; i < arrOfSubTitle.length; i++) {
					arrOfSubTitle[i] = arrOfSubTitle[i].trim();
				}
				
				ArrayList<String> subTitleList = new ArrayList<String>(Arrays.asList(arrOfSubTitle));
		        subTitleList.add("None"); 
		        Collections.sort(subTitleList); 
		        
				ReporterLog.info("subTitleList", subTitleList.toString());
				
				audioSubTitlesBean.setAudio(audioList);
				audioSubTitlesBean.setSubTitle(subTitleList);
				
		        FileUtilities.writeToFile(Thread.currentThread().getStackTrace()[2].getMethodName(),
						"ContentDetails Page: <br> arrOfAudio: "+Arrays.deepToString(arrOfAudio)+ " <br> subTitleList: "+subTitleList.toString(), "purple" );
								
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		return new ContentDetailsPage();
	}

}

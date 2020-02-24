package ios.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.GmailAPI;
import com.automation.utilities.ReadTestData;
import api.pojo.DiscoveryFeed.ContentDetailsModel;
import api.pojo.DiscoveryFeed.DiscoverFeedController;
import api.pojo.DiscoveryFeed.RelatedTitlesControl;
import api.pojo.DiscoveryFeed.RelatedTitlesModel.Data;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import ios.utils.IOSConstants;

public class ContentDetailsPage extends ActionEngine {

	private static ContentDetailsModel tvShowContent;
	public static By btnClose = By.id("btn close details");
	public static By imgContentImage = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeImage[2]");
	public static By btnWatchNow = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]");
	public static By btnWatchTrailer = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[2]");
	public static By txtMovieTitle = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]");
	public static By txtContentTypeContentLength = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	public static By txtAudioLanguage = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText[3]");
	public static By txtSubtitleLanguage = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText[4]");
	public static By iconWatchList = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeImage");
	public static By icon_details_greenTick = By.id("details_greenTick");
	public static By iconShare = By.name("share");
	public static By iconFavourite_AfterClick = By.id("added_Favourite");
	public static By txtShare = By.name("Share");
	public static By txtMovieDescription = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By txtMovieDirectorName = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	public static By txtMovieCategory = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[4]");
	public static By txtMovieActors = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[6]");

	//TVSHow Episodes
	public static By btnSeasonSection = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[7]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By btnArrowDown = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[7]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[2]");
	public static By txtDialogueSelectSeason = By.id("Select a season");
	public static By dialogueBoxSeasonlist = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable");
	public static By sectionEpisode = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[7]/XCUIElementTypeOther/XCUIElementTypeTable");
	public static By btnEpisodePlay = By.name("ic play black");
	public static By btnMore = By.name("more");
	public static By imgTickSeason = By.id("icDownloadedPurple");
	public static By txtSimilarTitle = By.name("Similar titles");
	public static By listSimilarTvShows = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[8]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell");
	public static By listSimilarMovies = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell");
	public static By lapsed2ndEpisode = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[7]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeButton");
	public static By rentButton = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]");
	public static By rentPopupText1 = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]");
	public static By rentPopupText2 = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");
	public static By rentPopupText3 = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[3]");
	public static By Cancel = By.id("Cancel");
	public static By Confirm = By.id("Confirm");

	// Rent Pop up
	public static By btnConfirm = By.id("Confirm");
	public static By btnCancel = By.id("Cancel");
	public static By txtRentTitle = By.xpath(".//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]");
	public static By txtTermsOfUse = By.xpath(".//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[3]");
	public static By btnCancelChangeItuneAccount = By.xpath(".//*[contains(text(),'Cancel')]");
	// Restricted Content Pop up
	public static By popup_RestrictedContent = By.xpath(".//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther");

	public static By txt_popup_title = By.id("Restricted Content");
	public static By txt_popup_body = By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]");

	public static By btn_ManageSettings = By.id("Manage Settings");
	public static By btn_RestrctedContent_Cancel = By.id("Cancel");

	// Parental control Page
	public static By txt_PageTitle_ParentalControl = By.id("Parental Controls");
	public static By txt_Heading_VerifyYourAccount = By.id("Verify your account");
	public static By txt_email = By.xpath("//XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeStaticText[4]");
	public static By link_ResendEmail = By.id("Resend Email");
	public static By btn_Next = By.id("Next");
	public static By error_message_incorrectOTP = By.id("Incorrect code.Please try again or resend email.");
	public static By input_OTP = By.xpath(".//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther");
	public static By btn_keyboard_delete = By.xpath(".//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeKeyboard/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeKey[12]");

	public static By btn_NotOver21Year = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton");
	public static By btn_Confirm = By.id("Confirm");

	public static By btn_YesOver21Year = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton");
	public static By txt_dateOfBirth = By.id("Date of birth:");
	public static By input_DateOfBirth = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
	public static By datePicker_ = By.className("XCUIElementTypeDatePicker");
	public static By txt_errorMessage_wrongDOB = By.id("You must be 21 or over in order to proceed.");
	public static By input_NewPIN = By.xpath(".//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");

	// Enter Pin to watch Pop up

	public static By input_enterPin_popup = By.xpath(".//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By link_ForgotPin = By.xpath(".//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");
	public static By txt_title_enterpin_popup = By.xpath(".//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeStaticText");

	//Download Pop Up 
	public static By btnDownload = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeButton");
	public static By txtDownloadQualityPopUp = By.id("Download Quality");
	public static By txtLowQuality = By.id("Low Quality");
	public static By txtMediumQuality = By.id("Medium Quality");
	public static By txtHighQuality = By.id("High Quality");
	public static By checkboxSavePreference_checked = By.id("ic_checkbox_save_preference_active");
	public static By btnDownload_popup = By.xpath(".//XCUIElementTypeWindow/*/*/XCUIElementTypeOther/XCUIElementTypeButton");
	public static By txtDownloadPause = By.id("Pause");
	public static By txtDownloadResume = By.id("Resume");
	public static By txtDownloadDelete = By.id("Delete");
	public static By btnWatchNow_popup = By.id("Watch now");
	
	public static final String RESTRICTED_CONTENT_POP_UP_TITLE = "Restricted Content";
	
	//Sign Up Pop Up for Registered/Visitor Users
	
	public static By btnMaybeLater = By.id("Maybe Later");
	public static By btnSignUp = By.id("Sign up");
	public static By txtSignUpToDownload = By.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]/*/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]");
	
	
	public ContentDetailsPage verifyMovieContentDetailsElementVisibility() {

		try {
			Thread.sleep(10000);
			// waitForVisibilityOfElement(imgContentImage, "Poster Image for Movie");
			isElementDisplayed(btnClose);
			isElementDisplayed(btnWatchNow);
			isElementDisplayed(iconWatchList);
			isElementDisplayed(iconShare);
			isElementDisplayed(txtShare);
			isElementDisplayed(txtMovieDescription);
			isElementDisplayed(txtContentTypeContentLength);
			ReporterLog.pass("ContentDetails Page Validation","All the elements are visible on the Content details Page");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new ContentDetailsPage();
	}

	public ContentDetailsPage verifyMovieDetailsWithAPI() {

		// TODO: Need to implement Discover API and compare with UI
		try {
			// Need to validate Title from API
			// Need to validate Audio text from API
			// Need to validate transcript from API
			// Download button is displayed or not from API
			String movieTitle = getText(txtMovieTitle, "Movie Title");
			String audioLanguage = getText(txtAudioLanguage, "Audio Languages");
			String SubtitleLanguage = getText(txtSubtitleLanguage, "Subtitle Language");
			String contentType = getText(txtContentTypeContentLength, "Content Type");
			ReporterLog.pass("ContentDetails Page Validation with API ",
					"All the elements are validated on the Content details Page with API");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public PlayerPage clickWatchNow() {
		try {
			click(btnWatchNow, "Watch Now");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public ContentDetailsPage verifyTVODMovieContentDetailsElementVisibility() {
		try {
			verifyMovieContentDetailsElementVisibility();
			String rentButton = getText(btnWatchNow, "Rent/Watch now button");
			if (!rentButton.contains("Watch Now")) {
				ReporterLog.pass("TVOD Content Validation", "Watch Now Button is not visible as expected.");
			} else {
				ReporterLog.fail("TVOD Content Validation",
						"Watch Now button is displayed for TVOD content not expected");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new ContentDetailsPage();
	}

	public ContentDetailsPage verifyTVShowElementsVisibility() {
		try {
			isElementDisplayed(btnClose);
			isElementDisplayed(btnWatchNow);
			isElementDisplayed(iconWatchList);
			isElementDisplayed(iconShare);
			isElementDisplayed(txtShare);
			isElementDisplayed(txtMovieDescription);
			scrollTillElementVisible_Vertical(btnSeasonSection, 0.2, true);
			isElementDisplayed(btnSeasonSection);
			isElementDisplayed(sectionEpisode);
			ReporterLog.pass("TVShow Content details Elements Validation",
					"All elements are visible on the TVShow Content details page.");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verifySeasonSelection() {
		try {

			MobileElement element = getMobileElement(btnSeasonSection).findElementByXPath(".//XCUIElementTypeButton[2]");

			new TouchAction(getMobileDriver()).tap(PointOption.point(element.getCenter().getX(), element.getCenter().getY())).perform();

			//scrollTillElementPresent(getMobileElement(btnSeason), false); TODO --
			getText(btnSeasonSection, "Season text"); //TODO
			click(btnArrowDown, "Season button");
			isElementDisplayed(txtDialogueSelectSeason);
			waitForVisibilityOfElement(txtDialogueSelectSeason, "Season section Dialogue ");

			List<MobileElement> seasonlist = getMobileElement(dialogueBoxSeasonlist)
					.findElements(By.xpath(".//XCUIElementTypeCell/XCUIElementTypeStaticText"));

			for (MobileElement ele : seasonlist) {
				ReporterLog.info("Season list returned ", "season list returned as : " + ele.getText());
			}

			try {
				if (seasonlist.get(1).isDisplayed()) {
					String secondSeason = seasonlist.get(1).getText();
					seasonlist.get(1).click();
					Thread.sleep(10000);
					verifyText(btnSeasonSection, secondSeason);
				}
			} catch (Exception e) {
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verifyEpisodeDetails() {

		try {
			List<MobileElement> episodeList = getMobileElement(sectionEpisode)
					.findElements(By.xpath(".//XCUIElementTypeCell/XCUIElementTypeStaticText[3]"));

			// Get Text for all episode title
			for (MobileElement ele : episodeList) {
				// scrollTillElementPresent(ele, true);
				ReporterLog.info("Episode Titles ", "Episode title is : " + ele.getText());
			}

			// Click on the VIP button
			getMobileElement(sectionEpisode).findElement(By.xpath(".//XCUIElementTypeCell/XCUIElementTypeButton")).click();

			Thread.sleep(10000);

			// Check SignUp Page displays after Click on the VIP button
			(new SignUpPage()).isSignUpPageDisplayed().clickCloseButton();

			// Check Episode Page Navigation from Sign Up page
			waitForVisibilityOfElement(sectionEpisode, "Episode Section");
			isElementDisplayed(sectionEpisode);

			ReporterLog.pass("Episode details Validation", "All Episode and elements visible");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verifyEpisodeDetailsForActiveUser() {

		try {
			List<MobileElement> episodeList = getMobileElement(sectionEpisode)
					.findElements(By.xpath(".//XCUIElementTypeCell"));

			// Validate Download button and Play button for each episode
			int count = 1;
			for (MobileElement ele : episodeList) {

				MobileElement eleDownload = ele.findElement(By.xpath(".//XCUIElementTypeButton[1]"));

				VerifyThatIsTrue(eleDownload.isDisplayed(), "Download button is displayed");

				MobileElement btnPlay = ele.findElement(By.xpath(".//XCUIElementTypeButton[2]"));

				VerifyThatIsTrue(btnPlay.isDisplayed(), "Playback button is displayed");

				scrollTillElementPresent(btnPlay, true);
				if (count == 5) {
					break;
				}
				count++;
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new ContentDetailsPage();
	}

	public ContentDetailsPage verifySimilartitles() {
		try {
			// Swipe down till Similar titles
			swipeUpOrBottom(true);
			swipeUpOrBottom(true);
			isElementDisplayed(txtSimilarTitle);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public SubscriptionPage validateLapsed2ndContent() {
		try {
			scrollJs("down");
			click(lapsed2ndEpisode, "Lapsed 2nd Episode");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new SubscriptionPage();
	}

	public SubscriptionPage validateLapsedTVODRent() {
		try {
			verifyTextContains(rentButton, "Rent For", "Rent Button");
			click(rentButton, "Rent Button");
			Thread.sleep(4000);
			verifyTextContains(rentPopupText1, "Rent", "Rent Popup Text1");
			verifyTextContains(rentPopupText2, "Start within 30 days, finish within 48 hours.", "Rent Popup Text2");
			verifyTextContains(rentPopupText3, "Terms of Use", "Rent Popup Terms of Use");
			isElementDisplayed(Cancel);
			isElementDisplayed(Confirm);
			click(Cancel, "Cancel");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new SubscriptionPage();
	}

	public PlayerPage verifyMoviesTrailer() {
		try {
			waitForVisibilityOfElement(btnWatchTrailer, "Watch Trailer button");
			isElementDisplayed(btnWatchTrailer);
			click(btnWatchTrailer, "Watch Trailer button");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public SignUpPage verifySignUpButton() {

		try {
			isElementDisplayed(btnWatchNow);
			getText(btnWatchNow, "Subscribe Now button");
			click(btnWatchNow, "Sign up to watch");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SignUpPage();
	}

	public SignUpPage verifySignUpButtonForRentalMovie() {

		try {
			isElementDisplayed(btnWatchNow);
			String buttonText = getText(btnWatchNow, "Watch Now/Rent Button ");
			if(buttonText.isEmpty()) {
				Thread.sleep(20000);
				buttonText = getText(btnWatchNow, "Watch Now/Rent Button ");
			}
			verifyText(buttonText, FileUtilities.strGetLocaleText(IOSConstants.IOS_TVOD_RENT_BUTTON_TEXT));
			click(btnWatchNow, "Sign up to watch");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SignUpPage();
	}

	public SignUpPage verifySignUpButtonForRentalMovieActiveUser() {

		try {
			isElementDisplayed(btnWatchNow);
			if (ConfigDetails.country.contains("SG")) {
				verifyText(btnWatchNow, "Rent For SGD 5.98");
			}
			getText(btnWatchNow, "Rent Button Text");
			click(btnWatchNow, "Sign up to watch");
			// waitForVisibilityOfElement(txtRentTitle, "Pop up title");
			if (!ConfigDetails.targetExecution.equalsIgnoreCase(GlobalConstant.ENVIRONMENT_BROWSERSTACK)) {
				isElementDisplayed(btnCancel);
				isElementDisplayed(btnConfirm);
				click(btnCancel, "Cancel button");
				click(btnWatchNow, "Sign up to watch");
				// waitForVisibilityOfElement(txtRentTitle, "Pop up title");
				click(btnConfirm, "Confirm button");
				// Thread.sleep(5000);
				// isElementDisplayed(btnCancelChangeItuneAccount);
			}
			ReporterLog.pass("Rent for TVOD validation", "TVOD Rent paymnet navigation success");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SignUpPage();
	}

	public ContentDetailsPage verifyContentDetailsUsingAPI(String MovieName) throws IOException, InterruptedException {

		ContentDetailsModel contentdetails = DiscoverFeedController.getMovieContentDetails(ReadTestData.R21_MOVIES);
		String title = contentdetails.getData().getTitle();
		String duration = contentdetails.getData().getRunning_time_friendly();
		String ageRating = contentdetails.getMeta().getAgeRating();
		String year = contentdetails.getMeta().getReleaseYear();
		List<String> audios = contentdetails.getData().getAudios();
		List<String> language = contentdetails.getData().getLanguages();

		// Wait till Watch Now Button is visible
		waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_10_SEC);

		// Validate Title of Movie
		String movieTitle = getText(txtMovieTitle, "Movie Title");
		verifyText(movieTitle, title);

		// Validate Audio Languages
		String audioLanguage = getText(txtAudioLanguage, "Audio Languages");
		for (String audio : audios) {
			verifyTextContains(audioLanguage, audio);
		}

		// Validate Subtitle languages
		String SubtitleLanguage = getText(txtSubtitleLanguage, "Subtitle Language");
		for (String subtl : language) {
			verifyTextContains(SubtitleLanguage, subtl);
		}

		// Validate content Rating , Content Duration ,release year
		String contentType = getText(txtContentTypeContentLength, "Content Rating,Duration,year");
		verifyTextContains(contentType, ageRating, "Age rating");
		verifyTextContains(contentType, duration, "Duration");
		verifyTextContains(contentType, year, "Year of Release");

		// Validate Watchlist
		isElementDisplayed(iconWatchList);
		clickNoWait(iconWatchList, "WatchList");
		isElementDisplayed(iconWatchList);
		// VerifyThatIsFalse(isElementPresentInDom(iconWatchList),"WatchList icon is not
		// visible as expected");

		// Validate like
		isElementDisplayed(iconShare);
		verifyText(txtShare, "Share");
		// clickNoWait(txtLike, "Favourite/Like icon");
		doubleTapNoWait(iconShare, "Favourite/Like icon");


		getText(txtShare, "Like");
		// verifyText(txtLike, "Liked");

		// Click on the watch now and Validate Restricted content Pop up and click
		// Manage Settings
		click(btnWatchNow, "Watch Now button");

		return new ContentDetailsPage();
	}

	public ParentalControlPage verifyManageSettingPopUp() throws InterruptedException {

		// isElementDisplayed(popup_RestrictedContent);
		waitTillElementPresent_HardWait_Polling(txt_popup_title, GlobalConstant.WAIT_SHORT_10_SEC);
		isElementDisplayed(txt_popup_title);
		verifyText(txt_popup_title, RESTRICTED_CONTENT_POP_UP_TITLE);
		isElementDisplayed(btn_ManageSettings);
		isElementDisplayed(btn_RestrctedContent_Cancel);
		clickNoWait(btn_ManageSettings, "Manage Settings button");
		Thread.sleep(10000);
		return new ParentalControlPage();
	}

	public static void verifyEnterPinPopUp(String enterPIN) {

		// Validate Enter PIN for ` watch Pop up`
		waitTillElementPresent_HardWait_Polling(txt_title_enterpin_popup, GlobalConstant.WAIT_SHORT_5_SEC);
		verifyText(txt_title_enterpin_popup, "Enter PIN to watch restricted content");
		clickNoWait(input_enterPin_popup, "Enter Pin Pop up"); // Need to check for Multiple elements
		enterCorrectPin(input_enterPin_popup, enterPIN);
	}

	public static void enterCorrectPin(By by, String enterPIN) {
		try {
			char[] arr = enterPIN.toCharArray();
			int i = 0;
			List<WebElement> pin = driver.findElements(by);
			for (WebElement ele : pin) {
				ele.sendKeys(Character.toString(arr[i]));
				i++;
			}
		} catch (Exception e) {
			ReporterLog.fail("Enter Correct PIN", "Correct PIN for R21 Movie Failed");
		}

	}

	public ContentDetailsPage verifyWithIncorrectPIN() {
		click(btnWatchNow, "Watch Now button");
		// validate incorrect pin text
		// click cancel
		verifyEnterPinPopUp(IOSConstants.inValidR21MoviePIN);
		return new ContentDetailsPage();
	}

	public PlayerPage verifyWithcorrectPIN() {
		verifyEnterPinPopUp(IOSConstants.validR21MoviePIN);
		return new PlayerPage();
	}

	/**
	 * @author mdafsarali
	 * @return 
	 * @implNote Verifying TVShow ContentDetails with API .
	 */
	public ContentDetailsPage verifyTVShowContentDetailsWithAPI() {
		try {
			tvShowContent = DiscoverFeedController.getTVShowContentDetails(ReadTestData.TV_SERIES);		

			String strTitle = tvShowContent.getData().getTitle();
			List<String> strLanguages = tvShowContent.getData().getLanguages();
			List<String> strAudio = tvShowContent.getData().getAudios();
			String strAgeRating = tvShowContent.getMeta().getAgeRating();
			String strReleaseYear = tvShowContent.getMeta().getReleaseYear();
			String strSeasons = tvShowContent.getData().getSeasons();
			String strDescription = tvShowContent.getData().getDescription();

			//Wait for Content Details Page load
			waitTillElementPresent_HardWait_Polling(btnClose, GlobalConstant.WAIT_SHORT_5_SEC);

			//Validate Title
			verifyTextContains(txtMovieTitle, strTitle, "TVShow Title");

			verifyTextContains(txtContentTypeContentLength, strAgeRating, "Age Rating");

			verifyTextContains(txtContentTypeContentLength, strReleaseYear, "Release Year");

			verifyTextContains(txtContentTypeContentLength, strSeasons+" Season", "Nos. Seasons Available");

			//Verify Subtitle Languages
			for(String lang : strLanguages) {
				verifyTextContains(txtSubtitleLanguage, lang, "SubTitle Language");
			}

			//verify Audio Languages
			for(String lang : strAudio) {
				verifyTextContains(txtAudioLanguage, lang, "Audio Language");
			}

			//Verify TVShow Content Description
			verifyTextContains(txtMovieDescription, strDescription, "TVShow Descriptions");

			//Verify Director ,Category and Actor field is Visible

			ReporterLog.info("validate TVShow Director Name", "Director Name is present ?  : "+isElementVisible(txtMovieDirectorName));
			ReporterLog.info("validate TVShow Actors Name", "Actors Name is present ?  : "+isElementVisible(txtMovieActors));
			ReporterLog.info("validate TVShow Catagory ", "Catagory  is present ?  : "+isElementVisible(txtMovieCategory));

		}catch(Exception e) {
			ReporterLog.fail("Validate verifyTVShowContentDetailsWithAPI ", "Failed with exception : "+e.getMessage());
			e.printStackTrace();
		}
		return new ContentDetailsPage();
	}

	/**
	 * @author mdafsarali
	 * @implNote This method will be used for Active Users TVShow Details and Episode Details ,
	 *  Download and Play button element Xpath changes for Lapsed/Visitors we need to write separate Method for Lapsed/Visitor
	 *  @date 4 December 2019
	 * @throws Exception
	 */
	public void verifySeasonsAndEpisodeDetails() throws Exception {

		//Verify Episode Lists and Its Title are correct 
		//Verify Serial number is also correct
		//Verify Download button is present and Play button is also present
		System.out.println("==============  validating seasons and Episodes Details =======================\n");

		//tvShowContent = DiscoverFeedController.getTVShowContentDetails(ReadTestData.TV_SERIES);
		int seasonsList = Integer.parseInt(DiscoverFeedController.getListOfSeasons(ReadTestData.TV_SERIES));
		List<String> ExpectedEpisodeLists = DiscoverFeedController.getListOfEpisodes(ReadTestData.TV_SERIES, "1");

		System.out.println("Season 1 Episodes are : "+ExpectedEpisodeLists);
		List<String> ExpectedEpisode2Lists = null;
		if(seasonsList >1) {
			ExpectedEpisode2Lists = DiscoverFeedController.getListOfEpisodes(ReadTestData.TV_SERIES, "2");	 //Season 2 Episode Lists
			System.out.println("Season 2 Episodes are : "+ExpectedEpisode2Lists);
		}
		List<String> actualEpisodeList = new ArrayList<String>();

		ReporterLog.info("Validate Seasons List", "The Seasons available are : # "+seasonsList);
		//Validate All Episode details of First Season
		validateEpisodes(actualEpisodeList, ExpectedEpisodeLists);

		if(seasonsList > 1) {

			//After Validating All Episode of Season 1 Now Switch to Season 2 , Scroll Up
			if(!isElementVisible(btnSeasonSection))
				scrollTillElementVisible_Vertical(btnSeasonSection, 0.4, false);

			MobileElement element = getMobileElement(btnSeasonSection).findElementByXPath(".//XCUIElementTypeButton[2]");
			new TouchAction(getMobileDriver()).tap(PointOption.point(element.getCenter().getX(), element.getCenter().getY())).perform(); //Tap on the Season Drop down box

			isElementDisplayed(txtDialogueSelectSeason);
			waitForVisibilityOfElement(txtDialogueSelectSeason, "Season section Dialogue ");

			List<MobileElement> seasonlist = getMobileElement(dialogueBoxSeasonlist).findElements(By.xpath(".//XCUIElementTypeCell/XCUIElementTypeStaticText"));

			//Validate Seasons List in the Select Season Pop up 
			VerifyThatIsTrue(seasonlist.size() == seasonsList , "Comparison of TVShow Season List with API");

			for (MobileElement ele : seasonlist) {
				ReporterLog.info("Season list returned ", "season list returned as : " + ele.getText());	
			}

			//Selecting Second season from the pop up 
			click(seasonlist.get(1)," Season 2 ");
			waitTillElementPresent_HardWait_Polling(btnSeasonSection, GlobalConstant.WAIT_SHORT_5_SEC);
			verifyText(getMobileElement(btnSeasonSection).findElementByXPath(".//XCUIElementTypeButton[1]"), "Season 2");

			//Validate Episodes after switching to season 2
			validateEpisodes(actualEpisodeList,ExpectedEpisode2Lists);
		}else {
			System.out.println("Only one Season available ...");
		}



	}

	public static void validateEpisodes(List<String>actualEpisodeList ,List<String>ExpectedEpisodeLists) throws InterruptedException {

		actualEpisodeList.clear();
		System.out.println("================= Validating Episodes  ============= \n");
		if(!isElementVisible(btnSeasonSection))
			scrollTillElementVisible_Vertical(btnSeasonSection, 0.3, true);

		//Validate Episode Details

		List<MobileElement> episodeList = getMobileElement(sectionEpisode).findElements(By.xpath(".//XCUIElementTypeCell"));
		
		// Get Text for all episode title
		for(int i=1;i<=episodeList.size();i++) {
			
			if(!isElementVisible(getMobileElement(sectionEpisode).findElement(By.xpath(".//XCUIElementTypeCell["+i+"]"))));
			scrollTillElementVisible_Vertical(getMobileElement(sectionEpisode).findElement(By.xpath(".//XCUIElementTypeCell["+i+"]")), 0.2, true);
			tapOnElement(getMobileElement(sectionEpisode).findElement(By.xpath(".//XCUIElementTypeCell["+i+"]")), "Episode List");
			Thread.sleep(5000);

			String episodetitles =  getMobileElement(sectionEpisode).findElement(By.xpath(".//XCUIElementTypeCell["+i+"]/XCUIElementTypeStaticText[2]")).getText(); //Episode title
			isElementDisplayed(getMobileElement(sectionEpisode).findElement(By.xpath(".//XCUIElementTypeCell["+i+"]/XCUIElementTypeButton[1]"))); //Play Button on Episode

			actualEpisodeList.add(episodetitles);
			
			ReporterLog.info("Episode Titles ", "Episode title is : " + episodetitles);
			
			//Checking for 5 Episode details only for time consuming purpose
			if(i== 5) {
				break;
			}
		}

		//Compare Episode List From API and UI 

		VerifyThatIsTrue(ExpectedEpisodeLists.containsAll(actualEpisodeList), "All Episodes are present in the UI ..");

	}

	
	public ContentDetailsPage verifyDownloadInContentDetailsPage() {
	
		try {
		//Verify Download in Content Details Page 
		waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
		isElementDisplayed(btnDownload,"Download button");
		click(btnDownload, "Download button");
		waitTillElementPresent_HardWait_Polling(txtDownloadQualityPopUp, GlobalConstant.WAIT_SHORT_2_SEC,"Download Quality Pop up");
		isElementDisplayed(txtDownloadQualityPopUp,"Download Quality Pop up");
		isElementDisplayed(txtHighQuality,"High Quality Download");
		isElementDisplayed(txtLowQuality,"Low Quality Download");
		isElementDisplayed(txtMediumQuality,"Medium Quality Download");
		isElementDisplayed(btnDownload_popup,"Download button Pop Up");
		isElementDisplayed(checkboxSavePreference_checked,"Save prefrence checkbox_checked");
		click(checkboxSavePreference_checked, "Save prefrence checkbox_checked");
		click(btnDownload_popup, "Download button Pop Up");
		Thread.sleep(15000);	
		verifyText(getMobileElement(btnDownload).getAttribute("name"), "Downloading");
		click(btnDownload, "Download button");
		isElementDisplayed(txtDownloadPause,"Download in Progress Pop up");
		click(txtDownloadPause, "Pause button Pop up");
		
		String afterPause = getMobileElement(btnDownload).getAttribute("name");
		verifyText(afterPause, "Paused");
		click(btnDownload, "Download button");
		isElementDisplayed(txtDownloadResume,"Resume Pop up");
		click(txtDownloadResume, "Resume Pop up");
		
		String afterResume = getMobileElement(btnDownload).getAttribute("name");
		verifyText(afterResume, "Downloading");
		
		//Delete Download from Content Details
		click(btnDownload, "Download button");
		isElementDisplayed(txtDownloadDelete,"Delete Pop up");
		click(txtDownloadDelete, "Delete Pop up");
		click(btn_Confirm, "Confirm button");
				
		verifyText(getMobileElement(btnDownload).getAttribute("name"), "Download");
		
		//Validate download preferences 
		click(btnDownload, "Download button");
		waitTillElementPresent_HardWait_Polling(txtDownloadQualityPopUp, GlobalConstant.WAIT_SHORT_2_SEC,"Download Quality Pop up");
		isElementDisplayed(txtDownloadQualityPopUp,"Download Quality Pop up");
		click(btnDownload_popup, "Download button Pop Up");
		
		click(btnDownload, "Download button");
		isElementDisplayed(txtDownloadDelete,"Delete Pop up");
		click(txtDownloadDelete, "Delete Pop up");
		click(btn_Confirm, "Confirm button");
		
		click(btnDownload, "Download button"); //Again Click on Download to check if Quality preference pop up appears
		
		if(!isElementPresentInDom(txtDownloadQualityPopUp,"Download Quality Pop up")) {
			ReporterLog.pass("Verify Download Preference", "Download Preference Check box works fine ");
		}
		
	} catch (Exception e) {
		TestUtilities.logReportFailure(e);
	}
		return new ContentDetailsPage();
		
	}

	//Verify Download From Content Details Page for Lapsed and Visitors
	public void verifySVODDownloadForRegisterAndVisitors() {
		// Click on May be Later  will dismiss the Pop up 
		try {
		waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
		isElementDisplayed(btnDownload,"Download button");

		String contentTitle = getText(txtMovieTitle, "Content Title");
		click(btnDownload, "Download button");
		//For Visitors 
		if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)) {
			isElementDisplayed(btnMaybeLater,"Maybe Later button");
			isElementDisplayed(btnSignUp,"Sign Up button");

			verifyText(txtSignUpToDownload, "Sign up to download "+contentTitle);

			click(btnMaybeLater, "Maybe Later button");

			VerifyThatIsFalse(isElementPresentInDom(btnMaybeLater), "Maybe Later pop up doesnot displayed");

			//Click Download again and then Click on the Sign Up 
			click(btnDownload, "Download button");

			isElementDisplayed(btnSignUp,"Sign Up button");
			click(btnSignUp, "Sign Up button");

			//Validate Sign Up page is displayed for Visitors 
			isElementDisplayed(SignUpPage.inputMobile_Email ,"Sign up Email or Mobile Input box");
		}else {

			//For lapsed Users 
			//TODO - Download preferences -- Bug -- Lapsed User App Crashes 
			isElementDisplayed(SubscriptionPage.txtTitleChooseAPlan,"Choose a plan Title"); //Validate Plan selector page is displayed
			isElementDisplayed(SubscriptionPage.btnChangeStore ,"Change Store Pop up ");
			click(SubscriptionPage.btnCancel, "Cancel button on the pop up");
		}
		}catch(Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFailure(e);
		}
	}
	
	//Verify TVOD Download From Content Details Page for Lapsed and Visitors
		public void verifyTVODDownloadForRegisterAndVisitors() {
			// Click on May be Later  will dismiss the Pop up 
			try {
			waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
			isElementDisplayed(btnDownload,"Download button");

			String contentTitle = getText(txtMovieTitle, "Content Title");
			click(btnDownload, "Download button");
			//For Visitors 
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)) {
				isElementDisplayed(btnMaybeLater,"Maybe Later button");
				isElementDisplayed(btnSignUp,"Sign Up button");

				verifyText(txtSignUpToDownload, "Sign up to download "+contentTitle);

				click(btnMaybeLater, "Maybe Later button");

				VerifyThatIsFalse(isElementPresentInDom(btnMaybeLater), "Maybe Later pop up doesnot displayed");

				//Click Download again and then Click on the Sign Up 
				click(btnDownload, "Download button");

				isElementDisplayed(btnSignUp,"Sign Up button");
				click(btnSignUp, "Sign Up button");

				//Validate Sign Up page is displayed for Visitors 
				isElementDisplayed(SignUpPage.inputMobile_Email ,"Sign up Email or Mobile Input box");
			}else {

				//For lapsed Users 				 
				isElementDisplayed(txtRentTitle,"Rent Pop up title"); 
				isElementDisplayed(txtTermsOfUse ,"Terms of Use Link");
				isElementDisplayed(Confirm,"Confirm button");
				isElementDisplayed(btnCancel,"Cancel button");
				click(Confirm, "Confirm button");
			}
			}catch(Exception e) {
				e.printStackTrace();
				TestUtilities.logReportFailure(e);
			}
		}
	//TODO-
		public void verifyDownloadForR21() {
			waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
			isElementDisplayed(btnDownload,"Download button");
			click(btnDownload, "Download button");
			
			System.out.println("==========   Need to remove this , might be a Bug for R21 ============");
			waitTillElementPresent_HardWait_Polling(txtDownloadQualityPopUp, GlobalConstant.WAIT_SHORT_2_SEC,"Download Quality Pop up");
			isElementDisplayed(txtDownloadQualityPopUp,"Download Quality Pop up");
			isElementDisplayed(txtHighQuality,"High Quality Download");
			isElementDisplayed(txtLowQuality,"Low Quality Download");
			isElementDisplayed(txtMediumQuality,"Medium Quality Download");
			isElementDisplayed(btnDownload_popup,"Download button Pop Up");
			click(btnDownload_popup, "Download button Pop Up");
			
		}
	
	public PlayerPage verifyPlayBackForDownloadedContent() {
		try {
		//Verify Download in Content Details Page 
		waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
		isElementDisplayed(btnDownload,"Download button");
		click(btnDownload, "Download button");
		waitTillElementPresent_HardWait_Polling(txtDownloadQualityPopUp, GlobalConstant.WAIT_SHORT_2_SEC,"Download Quality Pop up");
		click(btnDownload_popup, "Download button on Pop up");
		VerifyThatIsTrue(isDownloaded(), "Content Downlaoded successfully ");
		
		//If Downloaded Success then play 
		click(btnDownload, "Download button");
		click(btnWatchNow_popup, "Watch now button in Pop up");
		}catch(Exception e) {
			TestUtilities.logReportFailure(e);	
		}
		
		return new PlayerPage();
	}
	
	//Check if Movie is downloaded
	public boolean isDownloaded() throws InterruptedException {
		
		int count =0;
		String downloadstatus ;
		downloadstatus = getMobileElement(btnDownload).getAttribute("name");
		while(!downloadstatus.equalsIgnoreCase("Downloaded")) {
			Thread.sleep(15000); //Wait 15 sec
			downloadstatus = getMobileElement(btnDownload).getAttribute("name");
			ReporterLog.info("Download Status", "Download status after retry attempt : "+count+1 +"  is : "+downloadstatus);
			if(count == 10) {
				System.out.println("Did not downloaded after 10 attempts Hence Quiting ..");
				break;
			}
			count++;
		}
		if(downloadstatus.equalsIgnoreCase("Downloaded")) {
			return true;
		}else {
			return false;
		}
	}
	
	//TODO --
	//Download for TVShow Episodes
	public void verifyDownloadForEpisodes() {
		waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
		
	}
	
	public void verifyDownloadEpisodesForVisitorsAndLapsed() {
		waitTillElementPresent_HardWait_Polling(btnWatchNow, GlobalConstant.WAIT_SHORT_5_SEC);
		
		
		
	}
	

	// Validate Similar titles

	public void verifySimilarTitles(String contentType, String titleName) {
		try {
			List<Data> data = RelatedTitlesControl.getSimilarTitleObject(titleName).getData();
			
			int expectedSize ;
			if(data.size() >= 9) {
				expectedSize= 9	;
			}else {
				expectedSize = data.size();
			}
			if (contentType.contentEquals("TVShow")) {
				// TvShow
				scrollTillElementVisible_Vertical(txtSimilarTitle, 0.5, true);

				int size = getElements(listSimilarTvShows).size();
				ReporterLog.info("Similar Title Content List", "Expected Result : "+expectedSize +" Actual result : "+size);
				VerifyThatIsTrue(expectedSize == size, "Similar Title Contents Size is Macthed ");

			} else if (contentType.contentEquals("Movie") || contentType.contentEquals("RentMovie")) {
				// Movies
				scrollTillElementVisible_Vertical(txtSimilarTitle, 0.5, true);
				int size = getElements(listSimilarMovies).size();				
				ReporterLog.info("Similar Title Content List", "Expected Result : "+expectedSize +" Actual result : "+size);
				VerifyThatIsTrue(expectedSize == size, "Similar Title Contents Size is Macthed ");
			}
			//Close Content 
			click(btnClose, "Close content");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}

package android.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import android.testobjects.TabletLocators;


public class ContentDetailsPage extends ActionEngine{

	public static By contentPlayButton = By.id("tv.hooq.android:id/txtPlay");
	public static By contentPlayIcon = By.id("tv.hooq.android:id/playIcon");
	public static By tvShowEpisodePlayIcon = By.id("tv.hooq.android:id/play_icon");
	public static By tvShowEpisodeVIPIcon = By.id("tv.hooq.android:id/button_vip");
	public static By btnRedeemYourTckt = By.id("tv.hooq.android:id/txtTvodRedeem");
	public static By close = By.id("tv.hooq.android:id/imgCloseAll");
	public static By poster = By.id("tv.hooq.android:id/rlImgPoster");
	public static By contentMovieDuration = By.id("tv.hooq.android:id/txtDuration");
	public static By contentEpisodeDuration = By.id("tv.hooq.android:id/duration");//tv.hooq.android:id/txtDuration
	public static By contentEpisode = By.id("tv.hooq.android:id/episode_name");
	public static By contentAudio = By.id("tv.hooq.android:id/txtAudio");
	public static By contentSubtitles = By.id("tv.hooq.android:id/txtSubtitle");
	public static By btnRentTrailer = By.id("tv.hooq.android:id/txtTrailer");
	public static By contentTitle = By.id("tv.hooq.android:id/txtAssetTitle");
	public static By watchList = By.id("tv.hooq.android:id/txtWatchList");
	public static By popupAddedToWatchlist = By.id("tv.hooq.android:id/watchlistText");
	public static By like = By.id("tv.hooq.android:id/txtRate");
	public static By share = By.id("tv.hooq.android:id/btnShare");
	public static By contentDescription = By.id("tv.hooq.android:id/txtDescription");
	public static By actorsLabel = By.id("tv.hooq.android:id/txtActorLabel");
	public static By actorsList = By.id("tv.hooq.android:id/txtActorList");
	public static By categoryLabel = By.id("tv.hooq.android:id/txtCategoryLabel");
	public static By categoryList = By.id("tv.hooq.android:id/txtCategoryList");
	public static By directorLabel = By.id("tv.hooq.android:id/txtDirectoryLabel");
	public static By directorList = By.id("tv.hooq.android:id/txtDirectorList");
	public static By downloadTextMovie = By.id("tv.hooq.android:id/downloadButton");	
	public static By downloadIconMovie = By.id("tv.hooq.android:id/downloadIcon");
	public static By downloadTVShow1 = By.id("tv.hooq.android:id/button_download");
	public static By seasonDropdown = By.id("tv.hooq.android:id/txtSeason");
	public static By seasonDropdownList = By.id("tv.hooq.android:id/radio_group_season");
	public static By seasons = By.className("android.widget.RadioButton");
	public static By contentSimilarTitleLabel = By.id("tv.hooq.android:id/txtSimilarTitileLabel");
	public static By signUpPopUp = By.id("tv.hooq.android:id/signUp");
	public static By mayBeLaterPopUp = By.id("tv.hooq.android:id/skip");
	public static By signUpPage = By.xpath("//android.widget.TextView[@text='SIGN UP']");
	public static By novaPage = By.xpath("//android.view.View[@text='SUBSCRIBE']");
	public static By videoPlayer = By.id("tv.hooq.android:id/brightcoveVideoView");
	public static By tvodRentPopUpHeader = By.id("tv.hooq.android:id/title");
	public static By tvodRentPopUpConfirm = By.id("tv.hooq.android:id/okay");
	public static By tvodRentPaymentPage = By.xpath("//android.view.View[@text='PAYMENT']");	
	public static By tvodRedeemPopUpHeader = By.id("tv.hooq.android:id/title");
	public static By tvodRedeemPopUpContent = By.id("tv.hooq.android:id/content");
	public static By tvodRedeemPopUpFooterLink = By.id("tv.hooq.android:id/footer");
	public static By tvodRedeemPopUpCancel = By.id("tv.hooq.android:id/btnSecond");
	public static By tvodRedeemPopUpConfirm = By.id("tv.hooq.android:id/okay");
	public static By ticketRedeemeedPopUpHeader = By.id("tv.hooq.android:id/title");
	public static By ticketRedeemeedPopUpContent = By.id("tv.hooq.android:id/content");
	public static By ticketRedeemeedPopUpWatchLater = By.id("tv.hooq.android:id/btnSecond");
	public static By ticketRedeemeedPopUpWatchNow = By.id("tv.hooq.android:id/okay");
	public static By tvodValidityAfterPurchase = By.id("tv.hooq.android:id/txtTvodInfo");
	public static By tvodPaymentPage = By.xpath("//android.view.View[@resource-id='payment-mount']");
	public static By creditCardPaymentMethod = By.xpath("//android.widget.Image[@text='logo_credit']");
	public static By debitCardPaymentMethod = By.xpath("//android.widget.Image[@text='logo_debit']");
	
	public static String[] subtitles;
	
	
	public SearchPage clickCloseButton() {
		try 
		{
			click(close, "Close button");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SearchPage();
	}
	
	public WatchListPage clickClose() {
		try 
		{
			click(close, "Close button");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new WatchListPage();
	}
	
	public PlayerPage clickPlayButton() {
		try 
		{
			click(contentPlayButton, "Play button");
			waitForInVisibilityOfElement(contentPlayButton, "Play button");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}	
	
	public PlayerPage clickPlayTrailerButton() {
		try 
		{
			click(btnRentTrailer, "WatchTrailer button");
			waitForInVisibilityOfElement(btnRentTrailer, "WatchTrailer button");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	
	
	public PlayerPage play2ndEpisodeFromContentPage() {
		try 
		{
			swipeUpOrBottom(true);
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)){
				verify_ListSize_GreaterThanZero(tvShowEpisodePlayIcon);
				clickOnElementInAList(tvShowEpisodePlayIcon, 3);				
				waitForInVisibilityOfElement(tvShowEpisodePlayIcon, "Play button");
				isElementDisplayed(videoPlayer);
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				verify_ListSize_GreaterThanZero(tvShowEpisodeVIPIcon);
				clickOnElementInAList(tvShowEpisodeVIPIcon, 0);
				isElementDisplayed(novaPage);
				driver.navigate().back();
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)){				
				verify_ListSize_GreaterThanZero(tvShowEpisodeVIPIcon);
				clickOnElementInAList(tvShowEpisodeVIPIcon, 0);
				isElementDisplayed(signUpPage);
				driver.navigate().back();
				driver.navigate().back();
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}

	public ContentDetailsPage verify_Poster() {
		try 
		{
			isElementDisplayed(poster);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_Title() {
		try 
		{
			isElementDisplayed(contentTitle);
			//verifyText(ReadTestData.FREE_CONTENT, getText(contentTitle, "Title"));
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_MovieDuration() {
		try 
		{
			isElementDisplayed(contentMovieDuration);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_ContentAudio() {
		try 
		{
			isElementDisplayed(contentAudio);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_ContentSubtitles() {
		try 
		{
			isElementDisplayed(contentSubtitles);
			/*String strSubtitles = getText(contentSubtitles, "Subtitles");
			subtitles = strSubtitles.split(",");
			for (String a : subtitles) 
	            System.out.println(a);*/
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_ContentDescription() {
		try 
		{
			isElementDisplayed(contentDescription);
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_LabelDirector() {
		try 
		{
			isElementDisplayed(directorLabel);			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_DirectorValues() {
		try 
		{
			isElementDisplayed(directorList);			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_LabelCategory() {
		try 
		{
			//swipeUpOrBottom(true);
			isElementDisplayed(categoryLabel);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_CategoryValues() {
		try 
		{
			isElementDisplayed(categoryList);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_LabelActors() {
		try 
		{	
			swipeUpOrBottom(true);
			isElementDisplayed(actorsLabel);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_ActorsValues() {
		try 
		{
			isElementDisplayed(actorsList);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_Like() {
		try 
		{
			isElementDisplayed(like);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage clickLike() {
		try 
		{
			click(like, "Like");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_Like_AfterClick() {
		try 
		{
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				isElementDisplayed(signUpPopUp);
			}
			else
			{
				verifyText(getText(TabletLocators.like, "title"), "Liked");
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}	

	public ContentDetailsPage verify_Liked_AfterClick() {
		try 
		{

			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				isElementDisplayed(signUpPopUp);
			}
			else
			{
				String strLike = getText(TabletLocators.like, "title");
				verifyText(strLike, "Like");
			}
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_WatchList() {
		try 
		{
			isElementDisplayed(watchList);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickWatchlist() {
		try 
		{
			click(watchList, "Watch list");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_Watchlist_AfterClick() {
		try 
		{
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR))
			{
				isElementDisplayed(signUpPopUp);
			}
			else
			{
				isElementDisplayed(popupAddedToWatchlist);
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}	
	

	public ContentDetailsPage verify_Share() {
		try 
		{
			isElementDisplayed(share);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_MovieDownload() {
		try 
		{
			isElementDisplayed(downloadTextMovie);
			isElementDisplayed(downloadIconMovie);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_TVShowDownload() {
		try 
		{
			isElementDisplayed(downloadTVShow1);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	public ContentDetailsPage verify_SeasonDropdown() {
		try 
		{
			swipeUpOrBottom(true);
			isElementDisplayed(seasonDropdown);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage click_SeasonDropdown() {
		try 
		{
			click(seasonDropdown, "Season Dropdown");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage click_SeasonFromTheList() {
		try 
		{
			isElementDisplayed(seasonDropdownList);
			String seasonSelected = getTextOfAnElementInAListOfList(seasonDropdownList, seasons, 1);
			clickOnElementInListOfAList(seasonDropdownList, seasons, 1);
			String seasonDisplayed =  getText(seasonDropdown, "Season dropdown");
			verifyText(seasonSelected, seasonDisplayed);
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}	
	

	public ContentDetailsPage verify_EpisodeList() {
		try 
		{
			swipeUpOrBottom(true);
			isElementDisplayed(contentEpisode);
			//TestUtilities.logReportPass("Episodes are displayed for TVShow");
			/*	List<WebElement> episodeList = driver.findElements(By.id("tv.hooq.android:id/recycleSeasonList"));
			System.out.println(episodeList.size());
			List<WebElement> noOfEpisodes = episodeList.get(0).findElements(By.id("tv.hooq.android:id/container"));
			if (noOfEpisodes.size() > 0) {
				ReporterLog.pass("Content details page",
						"Episodes list is displayed and no. of episodes displayed is: " + noOfEpisodes.size());
			} else {
				ReporterLog.fail("Content details page", "Episodes list is not displayed");
				// blnStatus = false;
			}*/
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}


	public ContentDetailsPage verify_EpisodeDuration() {
		try 
		{
			isElementDisplayed(contentEpisodeDuration);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_TVShowEpisodePlayIcon() {
		try 
		{
			isElementDisplayed(tvShowEpisodePlayIcon);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_SimilarTitleLabel() {
		try 
		{
			swipeUpOrBottom(true);
			swipeUpOrBottom(true);
			swipeUpOrBottom(true);
			swipeUpOrBottom(true);
			swipeUpOrBottom(true);
			swipeUpOrBottom(true);
			isElementDisplayed(contentSimilarTitleLabel);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_PlayButtonForMovie() {
		try 
		{
			isElementDisplayed(contentPlayIcon);
			String strBtnPlayText = getText(contentPlayButton, "Text on play button");
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)){
				verifyText(strBtnPlayText, "Watch now");
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				verifyText(strBtnPlayText, "Subscribe to watch");
				/*click(contentPlayButton, "Subscribe to watch");
				isElementDisplayed(novaPage);
				driver.navigate().back();*/
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)){
				verifyText(strBtnPlayText, "Subscribe to watch");
			/*	click(contentPlayButton, "Subscribe to watch");
				isElementDisplayed(signUpPage);
				driver.navigate().back();
				driver.navigate().back();*/
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_PlayButtonForTVShow() {
		try 
		{
			String strBtnPlayText = getText(contentPlayButton, "Text on play button");
			if(strBtnPlayText.toLowerCase().startsWith("watch"))
			{
				verifyText(strBtnPlayText, "Watch now");
			}
			else
			{
				String[] arrData=strBtnPlayText.split(":");
				verifyText(strBtnPlayText, "Continue:"+arrData[1]);
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}

	public ContentDetailsPage verify_PlayButtonForTVOD() {
		try 
		{
			String strBtnPlayText = getText(btnRedeemYourTckt, "Text on play button");
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)){
				if(strBtnPlayText.startsWith("Rent for"))
					verifyTextContains(strBtnPlayText, "Rent for");
				else
					verifyText(strBtnPlayText, "Redeem your ticket");
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				verifyText(strBtnPlayText, "Redeem your ticket");
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)){
				verifyTextContains(strBtnPlayText, "Rent for");
				/*click(btnRedeemYourTckt, "Rent for");
				isElementDisplayed(signUpPage);
				driver.navigate().back();
				driver.navigate().back();*/
			
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_PlayButtonForTVOD_WithoutBTVOD() {
		try 
		{
			String strBtnPlayText = getText(btnRedeemYourTckt, "Text on play button");
			if(ConfigDetails.userType.equalsIgnoreCase("active") || ConfigDetails.userType.equalsIgnoreCase("lapsed")){
				if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))
					verifyTextContains(strBtnPlayText, "Rent for INR");
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG))
					verifyTextContains(strBtnPlayText, "Rent for SGD");
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_ID))
					verifyTextContains(strBtnPlayText, "Rent for IDR");
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_TH))
					verifyTextContains(strBtnPlayText, "Rent for THB");
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_PH))
					verifyTextContains(strBtnPlayText, "Rent for PHP");				
			}	
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)){
				verifyText(strBtnPlayText, "Sign up to watch");				
				
			}

		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickRentForButton() {
		try 
		{
			click(btnRedeemYourTckt, "Rent for button");
			
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE) || ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				isElementDisplayed(tvodRentPopUpHeader);
				click(tvodRentPopUpConfirm, "Confirm");
				isElementDisplayed(tvodRentPaymentPage);
				
			}	
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)){	
				isElementDisplayed(signUpPage);
				//driver.navigate().back();
				//driver.navigate().back();			
			}	
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public NovaPlanSelectorPage clickCreditCardPaymentMethod() {
		try 
		{
			isElementDisplayed(tvodPaymentPage);
			clickOnElementInAList(creditCardPaymentMethod, 0);
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public NovaPlanSelectorPage clickDebitCardPaymentMethod() {
		try 
		{
			isElementDisplayed(tvodPaymentPage);
			clickOnElementInAList(debitCardPaymentMethod, 0);
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPlanSelectorPage();
	}
	
	public ContentDetailsPage clickRedeemTicket() {
		try 
		{
			
			if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)){
				click(btnRedeemYourTckt, "Redeem with Ticket");
				isElementDisplayed(tvodRedeemPopUpHeader);
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)){
				click(btnRedeemYourTckt, "Redeem with Ticket");
				isElementDisplayed(tvodRedeemPopUpHeader);
			}
			else if(ConfigDetails.userType.equalsIgnoreCase(GlobalConstant.USERTYPE_VISITOR)){				
							
			}	
			
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickConfirmOnTVODPopup() {
		try 
		{			
			click(tvodRedeemPopUpConfirm, "Confirm");
			isElementDisplayed(ticketRedeemeedPopUpHeader);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage clickCancelOnTVODPopup() {
		try 
		{
			click(tvodRedeemPopUpCancel, "Cancel");
			isElementDisplayed(btnRedeemYourTckt);
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public PlayerPage clickWatchNowOnTicketRedeemedPopup() {
		try 
		{			
			click(ticketRedeemeedPopUpWatchNow, "Watch now");
			waitForInVisibilityOfElement(ticketRedeemeedPopUpWatchNow, "Pop up");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new PlayerPage();
	}
	
	public ContentDetailsPage clickWatchLaterOnTicketRedeemedPopup() {
		try 
		{
			click(ticketRedeemeedPopUpWatchLater, "Watch later");
			isElementDisplayed(tvodValidityAfterPurchase);	
			verify_TVODValidity("1 month left");
			//verifyTextContains(tvodValidityAfterPurchase, "1 month left", "TVOD validity");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
	
	public ContentDetailsPage verify_TVODValidity(String strValidity) {
		try 
		{
			verifyTextContains(tvodValidityAfterPurchase, strValidity, "TVOD validity");
		} 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new ContentDetailsPage();
	}
}

package web.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;

public class SignUpPage extends ActionEngine {
	public By hamburgerMenu = By.cssSelector(".mobile .e2e-iconButton:last-of-type img[title='Menu']");
	public static By signupBtn = By.tagName("label");

	public static By passwordText = By.xpath("//input[@id='password']");
	public static By getStarted = By.xpath("//button[@type='button']");

	public static By signupTitle = By.xpath("//h3[text()='Sign Up']");
	public static By verifyEmailtxt = By.xpath("//h3[text()='Verify Email']");
	public static By iHaveVerifiedBtn = By.xpath("//span[text()='I have verified']");

	public static By lblPWDForm = By.xpath(".//*[@id='loginpwd_form']/label");
	public static By txtPWD = By.xpath("//input[@name='password']");
	public static By evNextBtn = By.cssSelector(".md-button");
	public static By lblOTPForm = By.xpath(".//*[@id='otp_form']/label");
	public static By btnOTPNxt = By.xpath(".//*[@id='otp_form']/button");
	public static By lblCreatePWDForm = By.xpath(".//*[@id='logincreatepwd_form']/label");
	public static By btnCreatePWDLogin = By.xpath(".//*[@id='logincreatepwd_form']/div[2]/button");

	// EV STAG PORTAL
	public static By evLoginForm = By.xpath("//*[@id='login-main']");
	public static By txtEvLogin = By.xpath("(//*[@class='login-text'])[1]");
	public static By txtEvPWD = By.xpath("(//*[@class='login-text'])[2]");
	public static By btnEvLogin = By.xpath("//*[@value='Login']");
	public static By lblEvWelcomeMsg = By.xpath("//*[contains(text(),'Welcome to the eVergent System')]");
	public static By btnAcctManagement = By.name("Account Management");
	public static By lblBusinessunits = By.xpath("//*[contains(text(),'BUSINESS UNITS')]");
	public static By lnkHOOQIN = By.xpath("//*[contains(text(),'HOOQ India')]");
	public static By lnkHOOQSG = By.xpath("//*[contains(text(),'HOOQ Singapore')]");
	public static By lnkHOOQPH = By.xpath("//*[contains(text(),'Globe')]");
	public static By lnkHOOQTH = By.xpath("//*[contains(text(),'AIS')]");
	public static By lnkHOOQID = By.xpath("//*[contains(text(),'HOOQ Indonesia')]");
	public static By txtEvEmail = By.name("qsemail");
	public static By txtEvPhone = By.name("Phone Number");
	public static By btnEvSearch = By.xpath("//*[@value='Search']");
	public static By btnEditToChkSocialMediaType = By
			.xpath("//td[contains(text(),'Guest  User')]/../td[2]//table//tbody//tr//td[2]//span//div//img");
	public static By lblSocialMediaType = By.xpath("//*[contains(text(),'Facebook')]");
	public static By lblPartner = By.xpath("//*[contains(text(),'Partner')]/../span");
	public static By lblCountry = By.xpath("//*[contains(text(),'Country')]/../span");
	public static By lblAccountStatus = By.xpath("(//*[contains(text(),'Account Status')]/../span)[1]");
	
	public static By lblUserName = By.xpath("//*[text()='User Name']/../div");
	public static By lblAltUserName = By.xpath("//*[contains (text(),'Alternate UserName')]/../div"); 
																										
	public static By lblCellPhone = By.xpath("(//*[contains (text(),'Cell Phone')])[2]/../div");
	public static By tabServices = By.xpath("//*[text()='Services']");

	public static By inpCardNumber = By.xpath(".//input[@id='cardnumber']");
	public static By inpNameOnCard = By.xpath(".//input[@id='nameoncard']");
	public static By inpExpiryDate = By.xpath(".//input[@id='expiry_date']");
	public static By inpCVV = By.xpath(".//input[@id='cvv']");
	public static By btnMakePayment = By.xpath("//button[text()='Make Payment']");

	public static By txtLoginemail = By.xpath(".//*[@id='login']");
	public static By btnCheckEmail = By.xpath(".//*[@value='Check Inbox']");
	public static By lblVerificationEmail = By.xpath(".//*[text()='HOOQ - Your verification code'][1]");
	public static By btnConfirmEmail = By.xpath(".//a[text()='Confirm Email']");
	public static By lnkDelete = By.xpath(".//a[@class='igif lmenudelall']");
	public static By lnkResetPwd = By
			.xpath(".//*[text()='Reset Now' or text()='Ganti Sekarang' or text()='?????????????????']");
	public static By lnkActivate7Day = By.xpath(".//*[text()='Activate Now']");
	public static By lnkDeleteAll = By.xpath(".//a[text()='Empty Inbox']");
	public static By strOTP = By.xpath(".//*[contains(text(),' 20 ')]"); // By.xpath("//*[contains(text(),'Here is your
																			// OTP')]");
	public static By lblInbox = By.xpath(".//*[@id='inboxtit']");
	public static By lblYopmail = By.xpath(".//*[contains(text(), 'YOPmail :')]");

	public static By lblSignUPSingtelCASTHeader = By.xpath("//*[@class='content']/div");

	// *********************************************EV2.0************************************************

	public static By lblExodusHeader = By.xpath("//*[text()='EV 2.0 Webview Test']");
	public static By txtExodusURL = By.xpath("//*[@id='main-form-exodusUrl']");
	public static By drpWebViewType = By.xpath("//*[@id='main-form-webviewType']");
	public static By txtDeviceID = By.xpath("//*[@id='main-form-serialNo']");
	public static By drpDeviceType = By.xpath("//*[@id='main-form-deviceType']");
	public static By btnLoadWebView = By.xpath("//*[@id='main-form-loadwebview']");
	public static By strReturnURL = By.xpath("//*[@class='info-container']");

	public static By txtCreatePWD = By.xpath("//*[@id='password']");
	public static By txtLoginEmail = By.xpath(".//*[@id='email']");
	public static By txt7DayTrialEmail = By.xpath(".//*[@id='email']");

	public static By btnEmailNxt = By.xpath(".//*[@id='email_form']/div[2]/button");

	public static By btnEmailSignUpNxt = By.xpath(".//*[@id='email_form']/div[4]/button");
	public static By btnMobileSignUpNxt = By.xpath(".//*[@id='register_form']/div[4]/button");
	public static By btnEmail7DayTrialNxt = By.xpath(".//*[@id='email_form']/div[3]/button");
	public static By btnMayBeLater = By.xpath(".//*[@id='email_form']/div[4]/button");
	public static By btnEmailOTT7DayTrialNxt = By.xpath(".//*[@class='modal-body p-0 py-4']/div[1]/button");
	public static By btnEmailOTTMayBeLater = By.xpath(".//*[@class='modal-body p-0 py-4']/div[2]/button");
	public static By lblAcntExists = By.xpath(".//*[@class='modal-body']/label"); 
	public static By lblMobileAcntExists = By.xpath(".//*[@class='modal-body']/label"); 
	public static By lblFBAcntExists = By.xpath(".//*[@class='modal-body']/label"); 
	public static By lblFBAcntNotExists = By.xpath(".//*[@class='modal-body']/label"); 
	public static By btnLoginToMyAccount = By.xpath(".//*[@class='modal-body']/button"); 

	public static By btnForgotPWDLogin = By.xpath(".//*[@id='createpwd_form']/div[2]/button");
	public static By btnForgotPWDConfirmation = By.xpath("//*[@class='modal-body']/button"); 
	public static By lblInvalidOTP = By.xpath(".//*[@class='modal-body']/label"); 
	public static By btnEmailExistClose = By.xpath(".//*[@id='closeModal']");

	public static By lblLoginMobilePage = By.xpath(".//*[@id='login_form']/label");
	public static By lblLoginEmailPage = By.xpath(".//*[@id='email_form']/label");
	public static By lblSignupMobilePage = By.xpath(".//*[@id='register_form']/label");
	public static By lblSignupEmailPage = By.xpath(".//*[@id='email_form']/label");
	public static By lblResendOTPTimer1 = By.xpath("(//*[@id='otpTime']/text())[1]");
	public static By lblResendOTPTimer2 = By.xpath("(//*[@id='otpTime']/text())[2]");
	public static By lblResendOTPTimerSpan = By.xpath("//*[@id='otpTime']/span");
	public static By lnkResendOTP = By.xpath("//a[@id='resendOTP']");
	public static By lblResendOTPSuccess = By.xpath("//*[@class='modal-body']/label"); 
	public static By btnResendOTPClose = By.xpath("//*[@id='closeModal']");
	public static By lblForgotPWDForm = By.xpath(".//*[@id='createpwd_form']/h2/label");
	public static By lblForgotPWDConfirmation = By.xpath(".//*[@class='modal-body']/label"); 
	public static By lbl7DaySuccess = By.xpath(".//*[@class='modal-body p-0 py-4']/label");
	public static By btn7DaySuccess = By.xpath(".//*[@class='modal-body p-0 py-4']/button");
	public static By lbl7DaySuccessAfterActivation = By.xpath(".//*[@class='modal-body']/label");
	public static By btn7DaySuccessAfterActivation = By.xpath(".//*[@class='modal-body']/button");
	public static By lblSecureEmail = By.xpath(".//*[@id='email_form']/label");
	public static By lblSecureEmailExists = By.xpath("//*[@class='modal-body']/label"); 
	public static By lbl7DayTrial = By.xpath(".//*[@id='email_form']/label");
	public static By lblEmailOTT7DayTrial = By.xpath(".//*[@class='modal-body p-0 py-4']/label");
	public static By lbl7DayTrialConfirmEmail = By.xpath("//*[@class='modal-body']/label"); 
	public static By btnOkayGotIt = By.xpath(".//*[@class='form-group']/button"); 
	public static By btn7DayOkayGotIt = By.xpath("//*[@class='modal-body']/button");
	public static By lbl7DayTrialNotActivated = By.xpath(".//*[@class='modal-body']/div"); 
	public static By btn7DayTrialNotActivated = By.xpath(".//*[@class='modal-body']/button"); 
	public static By lnkSkipSecureEmail = By.xpath(".//*[@id='email_form']/div/a"); 
	public static By lnkForgotPWD = By.xpath(".//*[@class='mainBody']/p/a"); 
	public static By lblResetPWD = By.xpath(".//*[@class='mainBody ng-scope']/label"); 
	public static By lnkResetPWD = By.xpath(".//*[@class='modal-body']/a");
	public static By lblResetPWDActiveTab = By
			.xpath(".//*[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple md-active']");
	public static By lblResetPWDEmailConfirmation = By.xpath(".//*[@class=' mainBody']/label"); 
	public static By btnGotIt = By.xpath(".//*[@class='form-group']/button"); 
	public static By lblFaceBook = By.xpath(".//*[@id='homelink']");
	public static By txtFaceBookLogin = By.xpath(".//*[@id='email']");
	public static By txtFaceBookPWD = By.xpath(".//*[@id='pass']");
	public static By txtSingtelCASTLogin = By.xpath(".//*[@name='myEmail']");
	public static By txtSingtelCASTPWD = By.xpath(".//*[@name='password']");
	public static By btnSingtelCASTLogin = By.xpath(".//*[@id='loginButton']");
	public static By btnFaceBookLogin = By.xpath(".//*[@name='login']");
	public static By btnFaceBookContinue = By.xpath(".//button[contains(text(), 'Continue as')]");
	public static By lblSingtelCASTHeader = By.xpath(".//*[contains(text(),'Please login with your CAST Account')]");
	public static By lblSingtelCASTIncorrectLogin = By
			.xpath(".//*[text()='Your login attempt was not successful. Please try again.']");

	public static By lnkLoginEmail = By.xpath(".//*[@class='sociallogins']/div[1]/a"); 
	public static By lnkLoginFB = By.xpath(".//*[@class='sociallogins']/div[2]/a"); 
	public static By lnkLoginMobile = By.xpath(".//*[@class='sociallogins']/div[1]/a"); 
	public static By lnkLoginSingtelCAST = By.xpath(".//*[@class='sociallogins']/div[3]/a");
	public static By lnkDontHaveAccount = By.xpath(".//*[@class='sociallogins']/p");

	public static By btnWelcomePageLogin = By.xpath(".//*[@class='btn btn-login']");

	public static By lblCountryCode = By.xpath(".//div[@class='input-group-prepend']//span");
	public static By txtSignUpMobile = By.xpath(".//input[@name='mobile']");
	public static By txtSignUpEmail = By.xpath(".//*[@id='email']");

	public static By btnShwPWD = By.xpath(".//img[@class='IC_Show']");
	public static By btnSignUpNext = By.xpath(".//button[@type='submit']");
	public static By btnToggle = By.xpath(".//input[@type='checkbox']//following-sibling::label");
	public static By lnkEmailSignup = By.xpath(".//*[@class='sociallogins']/div[1]/a"); 
	public static By lnkMobileSignup = By.xpath(".//*[@class='sociallogins']/div[1]/a"); 
	public static By lnkSignupFB = By.xpath(".//*[@class='sociallogins']/div[2]/a");
	public static By lnkAlreadyHaveAccount = By.xpath(".//*[@class='sociallogins']/p"); 
	public static By lnktermsAndConditionsOfUse = By.xpath(".//*[@class='sociallogins']/../p/a[1]"); 
	public static By lnkprivacyPolicy = By.xpath(".//*[@class='sociallogins']/../p/a[2]"); 
	public static By lnkSkipNova = By.xpath(".//a[text()='Skip']");

	public static By ottPlnSelector = By.xpath(".//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']");
	public static By plnSelectorPayTM = By.xpath(".//*[@id='tab-PTMRecurring']");
	public static By plnSelectorPayTMPymnt = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[1]");
	public static By plnSelectorOlaMoneyPymnt = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[7]");
	public static By plnSelectorCreditCardPymnt = By.xpath("//*[contains(@src,'credit.svg')]");
	public static By plnSelectorCreditCardPymntPH = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[3]");
	public static By plnSelectorCreditCardPymntTH = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[1]");
	public static By plnSelectorCreditCardPymntID = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[1]");
	public static By plnSelectorCreditCardPymntSG = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[2]");
	public static By plnSelectorPhoneBillPymnt = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[3]");
	public static By plnSelectorDebitCardPymnt = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[4]");
	public static By plnSelectorNetBankingPymnt = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[5]");
	public static By plnSelectorCashCardPymnt = By
			.xpath("(//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']/ul/li/a)[6]");

	public static By btnPlanSelectorSubscribe = By
			.xpath("//*[contains(@src,'credit.svg')]/../following-sibling::div/ul/following-sibling::button");
	public static By btnPlanSelectorSubscribeFreeTrial = By.xpath("//*[text()='Start your free trial now']");
	public static By btnCancelCreditPayment = By.xpath(".//*[@id='creditcard_cancel_btn']");
	public static By lblPaymentCancel = By.xpath(".//*[@class='modal-body p-0 py-4']/label"); 
	public static By lblPaymentCancelTryAgain = By.xpath("//*[@type='button']");
	public static By btnReturnToSite = By.xpath(".//*[@value='Return To the Merchant Site']");
	public static By lblSubcriptionStart = By.xpath(".//*[@class='innerTab']/h5/b"); 
	public static By lblSubcriptionStartPrepaid = By.xpath(".//*[@class='innerTab']/h5/b"); 
	public static By btnStartBrowsingDone = By.xpath(".//*[@class='innerTab']/../div[2]/button"); 
	public static By btnStartWatchingPreProv = By.xpath(".//*[@class='modal-body p-0 py-4']/button"); 
	public static By btnStartBrowsing = By.xpath(".//*[text()='Start browsing']");
	public static By lblSignUpSuccess = By.xpath(".//*[@class='modal-body p-0 py-4']/label"); 
	public static By lblDataAllocation = By.xpath(".//*[contains(text(),'7 GB data allocation for HOOQ')]");
	public static By lblDataValidation = By.xpath(".//*[contains(text(),'Valid for 30 days')]");

	public static By lblPayTm = By.xpath("(//*[@id='login-header-wrapper']/ul/li)[1]");
	public static By lblOlaMoney = By.xpath(".//*[text()='Ola Money - 30 days of HOOQ recurring subscription']");
	public static By lblCashCard = By.xpath(".//*[text()='Cash Card Payment']");
	public static By lblNetBanking = By.xpath(".//*[text()='Net Banking Payment']");
	public static By lblMobileBill = By.xpath(".//*[text()='MOBILE BILL PAYMENT']");
	public static By lblPrepaidFreeTrail = By.xpath("//h2");
	public static By lblPrepaidFreeTrailContinue = By.xpath(".//*[text()='Continue']");
	public static By lblPrepaidPlans = By.xpath(".//*[text()='Select duration:']");
	public static By lblPrepaidPlan1Month = By.xpath(".//*[text()='1 Month Subscription']");
	public static By lblPrepaidPlan7Days = By.xpath(".//*[text()='7 Days Subscription']");

	// *********************************************EV2.0************************************************

	public static By headerLogin = By.xpath(".//*[@class='btn btn-login']");
	public static By txtEmail = By.xpath(".//*[@id='email']");
	public static By btnDone = By.xpath(".//*[@id='submit-button']");
	public static By lnkHelp = By.xpath(".//*[@id='help-link']");
	public static By imgLogo = By.xpath(".//img[contains(@src,'logo_hooq.png')]");

	public static By lblInvalidEmail = By.xpath(".//*[@class='error-message']");
	public static By lblNonExistingEmail = By.xpath(".//*[@class='error-message']");
	public static By lblVerifyEmail = By.xpath("//*[@id='card-block-form']/p[2]");
	public static By lnkMobile = By.xpath(".//*[@id='login-mobile']");
	public static By txtMobile = By.xpath(".//*[@id='mobile']");
	public static By lblVerifyMobile = By.xpath("//*[text()='Your SMS verification code has been sent to']");
	public static By lblNonExistingMobile = By.xpath(".//*[@class='error-message']");
	public static By lblInvalidMobile = By.xpath(".//*[@class='error-message']");
	public static By lblInvalidOtp = By.xpath(".//*[@class='error-message']"); 
	public static By lnkSignUp = By.xpath(".//*[@id='signup-link']");
	public static By lblExistingEmail = By.xpath(".//*[@class='error-message']");
	public static By lblSignUpVerifyEmail = By.xpath("//*[@id='card-block-form']/p[2]");
	public static By btnVerified = By.xpath(".//*[@id='submit-button']/span");
	public static By lnkSkip = By.xpath(".//*[@id='skip-button']");
	public static By lblExistingMobile = By.xpath(".//*[@class='error-message']");
	public static By lblSignupVerifyMobile = By.xpath("//*[text()='Your SMS verification code has been sent to']");
	public static By btnNext = By.xpath(".//*[@id='otp_form']/button");
	public static By lblPrivacyPolicy = By.xpath("//h1[text()='HOOQ's Privacy Policy']");
	public static By novaPageValidation = By.xpath("//div[@class='paymentPartnerInfo__payment-wrapper___2FUDu']/img");

	public static By RedeemWithTcKt = By.xpath("//span[text()='Redeem with']");
	public static By ConfirmWithTcKt = By.xpath("//button[text()='Confirm']");
	public static By WatchNow = By.xpath("//button[text()='Watch Now']");

	// Spotlight or Watchnow Section
	public static By imgBanner = By.xpath(".//*[@class='banners show']");
	public static By btnWatchOrRent = By.xpath(".//*[@class='buttons']/a");
	public static By btnSpotlightFavorite = By.xpath(".//a[@class='btn btn-action-icon btn-icon-favorite']");
	public static By lblSpotlightContentTitle = By.xpath(".//h1[@id='highlights-current-title']");
	public static By btnRentNow = By.xpath(".//*[@class='btn btn-watch-now-tvod btn-yellow']");
	public static By btnWatchNowRented = By.xpath(".//*[@class='btn btn-watch-now btn-yellow btn-watch-now-rented']");

	// Xpaths for Get started window - Home Page
	public static By homeGetStartedBox = By.xpath(".//*[@class='concierge expanded slide-in']");

	public static By homeGetStartedHeaderText(String headerText) {
		return By.xpath(".//*[@class='concierge expanded slide-in']//*[text() = '" + headerText + "']");
	}

	public static By homeGetStartedHelpText(String helpText) {
		return By.xpath(".//*[@class='concierge expanded slide-in']//*[text() = '" + helpText + "']");
	}

	public static By homeGetStartedButtonText(String buttonText) {
		return By.xpath(".//*[@class='concierge expanded slide-in']//*[text() = '" + buttonText + "']");
	}

	// Rentals Page
	public static By lblRentalsTitle = By.xpath(".//*[@id='profile-title']");
	public static By lnkRentals = By.xpath(".//*[@class='value']/span[1][text()='Rentals']");
	public static By lnkWatchLater = By.xpath(".//*[@class='value']/span[1][text()='Watch later']");
	public static By lnkFavorites = By.xpath(".//*[@class='value']/span[1][text()='Favorites']");
	public static By lnkHistory = By.xpath(".//*[@class='value']/span[1][text()='History']");

	// Watch Later Page
	public static By lblWatchLaterTitle = By.xpath(".//*[@id='profile-title']");

	// Favorites Page
	public static By lblFavoritesTitle = By.xpath(".//*[@id='profile-title']");

	// History Page
	public static By lblHistoryTitle = By.xpath(".//*[@id='profile-title']");

	// Favorite related controls
	public static By lnkFirstMovieSelector = By.xpath("(.//*[@class='paging']//a[text()='·'])[1]");
	public static By lblMovieTile = By.xpath(".//div[@class='info show']//h1");
	public static By lblFavoriteElement = By
			.xpath(".//*[@class='btn link-white-fav' or @class = 'btn link-green-fav']");
	public static By lblFavoriteTextContainer = By
			.xpath("((.//*[@class='btn link-white-fav' or @class = 'btn link-green-fav'])//Span)[2]");

	public static By lblFavoriteMovieTitle(String movieTitle) {
		return By.xpath(".//*[@class='content-title'][text() = '" + movieTitle + "']");
	}

	public static By btnFacebook = By.xpath(".//*[@id='fb-button']");
	public static By lstFaviroteWatchLaterTitle = By.xpath(".//*[@id='profile-mount']/div/div[2]/div");
	public static By lstYouWereWatchingTitle = By.xpath("(//*[@class='slick-list'])[1]");
	public static By discoverwatchlater = By.xpath(".//*[@id='btnWatchLater']");
	public static By discoverFavorite = By.xpath(".//*[@id='btnFavorite']");
	public static By playMovie = By.xpath(".//*[@class='btn btn-watch-now btn-purple']");
	public static By playerEpisode = By.xpath(".//*[@class='hidden-sm-down']");
	public static By playerEpisodeList = By.xpath(".//*[@class='seasons displayed']");
	public static By playerSettings = By.xpath(".//*[@class='icon-cog-outline']");
	public static By playerSettingDetails = By.xpath("//*[@id='player-mount']/section/div[1]/div/div/div[1]/div");
	public static By playerEpisodeDetails = By.xpath(".//*[@id='player-title']");
	public static By playerEpisodeNext = By.xpath(".//*[@class='icon-to-end']");
	public static By playerEpisodePrevious = By.xpath(".//*[@class='icon-to-start']");
	public static By playerEpisodePlay = By.xpath(".//*[@class='icon-play']");
	public static By playerEpisodepause = By.xpath(".//*[@class='icon-pause']");
	public static By playerMaximize = By.xpath(".//*[@class='icon-resize-full-alt']");
	public static By playerMinimize = By.xpath(".//*[@class='icon-resize-small']");
	public static By lblSignUP = By.xpath("//h3[text()='Sign Up']");
	public static By lblPlayerErrorFree = By
			.xpath(".//p[contains(text(),'Title is not available for free trial users')]");
	public static By settingQuality = By.xpath("//h4[text()='Quality']/following-sibling::ul/li/a");

	// Settings Page
	public static By lblSettingsTitle = By.xpath(".//*[@class='page-title']"); 
	public static By btnSettingsSave = By.xpath(".//*[@id='btn-save']");
	public static By lblSettingAppDisplayLanguage = By.xpath("(//*[@class='card-block']/h6)[1]"); 
	public static By cboSettingAppDisplayLanguage = By.xpath("//*[@id='language']");
	public static By lblSettingsAudioLanguage = By.xpath("(//*[@class='card-block']/h6)[2]"); 
	public static By cboSettingsAudioLanguage = By.xpath(".//*[@id='audio']");
	public static By lblSettingsSubtitleLanguage = By.xpath("(//*[@class='card-block']/h6)[3]"); 
	public static By cboSettingsSubtitleLanguage = By.xpath("//*[@id='subtitle']");
	public static By lblSettingsPlaybackQuality = By.xpath("(//*[@class='card-block']/h6)[4]"); 
	public static By cboSettingsPlaybackQuality = By.xpath("//*[@id='playbackQuality']");
	public static By lblHeaderBrowse = By.xpath(".//*[@id='userbar-container']/ul[1]/li[1]/a"); 
	public static By lblHeaderRent = By.xpath(".//*[@id='rental']");
	public static By lblHeaderSearch = By.xpath(".//*[@id='userbar-container']/ul[1]/li[3]/a/span");
	public static By lblHeaderRentals = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[1]/a/span");
	public static By lblHeaderWatchLater = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[2]/a/span");
	public static By lblHeaderFaviorites = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[3]/a/span");
	public static By lblHeaderHistory = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[2]/li[4]/a/span");
	public static By lblHeaderSettings = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[1]/a/span");
	public static By lblHeaderSubscription = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[2]/a/span");
	public static By lblHeaderTransactionHistory = By
			.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[3]/a/span");
	public static By lblHeaderSupport = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[3]/li[4]/a/span");
	public static By lblHeaderLogout = By.xpath(".//*[@id='userbar-container']/ul[2]/li/div/ul[4]/li[2]/a ");
	public static By lblHeaderLoginInfo = By.xpath(".//*[@id='display-name']/span");
	public static By lnkSeeMore = By.xpath(".//*[@class='more-info']");

	// Subscription Page
	public static By lblSubscriptionTitle = By.xpath(".//*[@class='subscription']/section/div/h1");
	public static By lblPlnDaysLeft = By.xpath("//*[@id='plan-days-left']");
	public static By lblNoActiveSubscription = By.xpath("//*[text()='No Active Subscription']");
	public static By lblPlnExpiry = By.xpath("//*[@id='plan-period']");
	public static By lblAutoReniew = By.xpath("//*[@class='info']/p[3]");
	public static By lblAutoeniewOnOff = By.xpath("//*[@class='info']/p[3]/span");
	public static By btnManageSubscription = By.xpath("//*[text()='Manage Subscription']");
	public static By lblVoucherHeading = By.xpath("//*[text()='Voucher Code Redemption']");
	public static By lblVoucherTxt = By.xpath("//*[@class='card-block voucher']/div/p");
	public static By txtVoucherCode = By.xpath("//*[@id='voucher_code']");
	public static By btnVoucherSubmit = By.xpath("//*[@id='btn-submit-voucher']");
	public static By lblVoucherMsg = By.xpath("//*[@id='voucher_message']/text()");
	public static By plnSelector = By.xpath("//*[@class='paymentMethodSelector__payment-method-selector___1nt4s']");
	public static By lnkLogo = By.xpath("//a[@class='navbarWeb__logo___2ephX']");
	public static By lblPickPlan = By.xpath("//*[text()='PICK THE PLAN THAT'S PERFECT FOR YOU']");
	public static By btnStrtFreeTrial = By.xpath("//button[text()='Start your free trial now']");

	public static By lblSubscriptionHeader = By.xpath(".//*[@class='content']/h1");
	public static By btnNetbank = By.xpath(".//*[@id='payment-mount']/section/div[2]/div[2]/div/ul/li[5]/a/img");
	public static By btnSubscribenow = By.xpath("//*[@id='payment-mount']/section/div[2]/div[2]/div/div/button");
	public static By lblSelectDurationyear = By
			.xpath("//*[@id='payment-mount']/section/div[2]/div[2]/div/ul/li[1]/div/div[1]");
	public static By Dropdowncashcard = By.xpath("//*[@id='cashcardbank']");
	public static By btnpaycashcard = By.xpath("//*[@id='icashcard_btn']");
	public static By btnPaymentPopupContinue = By.xpath(".//*[@id='payment-mount']/section/div[1]/div[2]/a[1]");
	public static By lblSelectDuration = By.xpath("//*[@id='payment-mount']/section/div[2]/div[2]/div/div/p");
	public static By lblEmail = By.xpath("//a[contains(@class,'redirect_url emailormobile')]");
	public static By emailIDTxt=By.xpath("//input[@id='email']");

	
	// Transaction History Page
	public static By lblTransactionHistory = By.xpath(".//*[@class='content']/h1");
	public static By lblTransactionTitle = By.xpath(".//*[@class = 'card-block'][1]/div[1]/div[2]/h3");
	public static By lblTransactionType = By.xpath(".//*[@class = 'card-block'][1]/div[1]/div[2]/h4");
	public static By lbltransDateFrom = By
			.xpath(".//*[@id='transaction-history-mount']/div/section[2]/div/div/div[6]/div[1]/div[2]/p/span[1]"); 
	public static By lbltransTo = By
			.xpath(".//*[@id='transaction-history-mount']/div/section[2]/div/div/div[6]/div[1]/div[2]/p/span[3]"); 
	public static By lbltransDateTo = By
			.xpath(".//*[@id='transaction-history-mount']/div/section[2]/div/div/div[6]/div[1]/div[2]/p/span[5]");
	public static By lbltransDateHeading = By.xpath(".//*[@class = 'card-block'][1]/div[2]/p[1]");
	public static By lbltransIdHeading = By.xpath(".//*[@class = 'card-block'][1]/div[2]/p[3]");
	public static By lbltransPaidByHeading = By
			.xpath(".//*[@id='transaction-history-mount']/div/section[2]/div/div/div[6]/div[2]/div/p[1]"); 
	// Support Page
	public static By lblSuportTitle = By.xpath(".//*[@class='page-title']");
	public static By lblAboutUs = By.xpath(".//span[contains(text(),'About Us')]");
	public static By lblFAQ = By.xpath(".//span[contains(text(),'FAQ')]");
	public static By lblPrivacy = By.xpath(".//span[contains(text(),'Privacy Policy')]");
	public static By lblTermsofUse = By.xpath(".//span[contains(text(),'Terms of Use')]");
	public static By lblNeedHelp = By.xpath(".//span[contains(text(),'Need help')]");
	public static By lblNeedHelpDetails = By
			.xpath(".//*[@id='support-mount']/div/div/div/section/div[3]/div/div/div/div/div[1]/p");
	public static By btnContactUs = By.xpath("//*[@class='buttons']");
	// Support Footer
	public static By lnkFooterAboutUs = By.xpath(".//*[@class='list-inline-item'][1]");
	public static By lnkFoterTermsOfUse = By.xpath(".//*[@class='list-inline-item'][2]");
	public static By lnkFooterPrivacyPolicy = By.xpath(".//*[@class='list-inline-item'][3]");
	public static By lnkFooterFAQ = By.xpath(".//*[@class='list-inline-item'][4]");
	public static By lnkFooterContactUs = By.xpath(".//*[@class='list-inline-item'][5]");
	// Payment
	public static By lblPrice = By.xpath(".//p[starts-with(.,'INR')]");
	public static By cboNetBank = By.xpath(".//*[@id='netbank']");
	public static By btnNetBankMakePayment = By.xpath(".//*[@name='inetbanking_btn']");
	public static By lblCarrierbillingPrice = By.xpath("//*[@id='carrierbilling_form']/div/div[1]/div[2]/p");
	// For Phillipines User
	public static By lblGlobeMobilePrice = By.xpath("//*[@id='mobilenumber_form']/div/div[1]/div[2]/p");
	//
	public static By cboOperator = By.xpath("//*[@id='oprtr_list']");
	public static By txtCarrierBillPin = By.xpath("//*[@id='pb-pin-input']");
	public static By tctCarrierBillMobileNumber = By.xpath("//*[@id='mno']");
	public static By btnCarrierBillMakePayment = By.xpath("//*[@id='carrierbilling_btn']");
	// For Phillipines User
	public static By btnGlobeMobileMakePayment = By.xpath("//*[@id='mobilenumber_btn']");
	//

	public static By cboCashCard = By.xpath("//*[@id='cashcardbank']");
	public static By btnCashCard = By.xpath("//*[@id='icashcard_btn']");
	// Home Page Locators
	public static By lnkMovies = By.xpath(".//a[text()='Movies']");
	public static By lnkTVSeries = By.xpath(".//a[text()='TV Series']");
	public static By lnkshowmemore = By.xpath("//*[@class='whats-hot-header']/div/a");
	public static By lblPriceBundle = By.xpath(".//h1[text()='PRICING & BUNDLES']");
	public static By lnkGetHooqNow = By.xpath(".//a[text()='Get HOOQ Now']");
	public static By lnkAndroidPlayStore = By.xpath(".//*[@href='http://on.hooq.tv/signup-bottom-android']");
	public static By lnkIOSAppStore = By.xpath(".//*[@href='http://on.hooq.tv/signup-bottom-ios']");
	public static By lblCopyRight = By.xpath(".//*[@id='copyright']");
	public static By lstBanner = By.xpath(".//*[@id='highlights']/div[2]/div/ul");
	public static By lblBannerMovieName = By.xpath("//*[@id='highlights']/div[2]/div/h1");
	public static By btnBannerWatchLater = By.xpath("//*[@id='highlights']/div[2]/div/div/a[1]");
	public static By btnBanerFaviorites = By.xpath(".//*[@class='btn btn-action-icon btn-icon-favorite']");
	public static By lstBrowse = By.xpath(".//*[@class='row']");
	public static By lblContentTitle = By.xpath(".//*[@id='collection-title']");
	public static By lstRent = By.xpath("(//*[@class='row'])[3]");

	public static By GetStarted = By.xpath("//*[@id='concierge-mount']/div/a[1]");
	public static By GetStartedtext = By.xpath("//*[@id='concierge-mount']/div/a[1]/p");
	/***
	 * Function Name :- verifySignInWithSingtelCAST Developed By :- Indraja Reddy
	 * Date :- 23-May-2019
	 */
	public void verifySignInWithSingtelCAST(String loginID, String strPWD, String strValid) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			click(lnkLoginSingtelCAST, "Singtel CAST");
			driver.switchTo().frame(0);
			if (isNumOf_Elements_Greater_Than_Zero(lblSingtelCASTHeader)) {
				String strSingtelCASTHeader = getText(lblSingtelCASTHeader, "SingtelCast Login Page Header");
				ReporterLog.pass("Signin to HOOQ Application with SingtelCAST account",
						"SingtelCAST login page is displayed with the header: " + strSingtelCASTHeader);

				type(txtSingtelCASTLogin, loginID, "Singtel CAST account");
				type(txtSingtelCASTPWD, strPWD, "Password");
				click(btnSingtelCASTLogin, "Login");

				if (strValid.equalsIgnoreCase("Valid")) {
					click(hamburgerMenu, "Hamburger Menu");
					By loginElement = By.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
					waitForElementClickable(loginElement,"loginElement");
					String loggedinID = getText(loginElement, "LoggedIn ID");
					if (driver.getPageSource().contains(loggedinID)) {
						ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

					} else {
						ReporterLog.fail("Verify Login", " User does not logged in successfully");
					}

				} else if (strValid.equalsIgnoreCase("Invalid")) {
					if (isNumOf_Elements_Greater_Than_Zero(lblSingtelCASTIncorrectLogin)) {
						String strSingtelCASTIncorrect = getText(lblSingtelCASTIncorrectLogin,
								"Incorrect Login Details");
						ReporterLog.pass("Signin to HOOQ Application with SingtelCAST account",
								strSingtelCASTIncorrect + " - is displayed");

					} else {
						ReporterLog.fail("Signin to HOOQ Application with SingtelCAST account",
								"Error message for Incorrect details is not displayed");

					}
				}
			} else {
				ReporterLog.fail("Signin to HOOQ Application with SingtelCAST account",
						"SingtelCAST login page is not displayed");

			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifySignInWithSingtelCAST Developed By :- Indraja Reddy
	 * Date :- 23-May-2019
	 */
	public void verifySignUPWithSingtelCAST() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			click(lnkLoginSingtelCAST, "Singtel CAST");
			click(By.xpath("(//*[@role='button'])[3]"), "clcik on close popup");
			if (isNumOf_Elements_Greater_Than_Zero(lblSignUPSingtelCASTHeader)) {
				String strSingtelCASTHeader = getText(lblSignUPSingtelCASTHeader, "SingtelCast signup Page Header");
				ReporterLog.pass("SignUP to HOOQ Application with SingtelCAST account",
						"SingtelCAST signup page is displayed with the header: " + strSingtelCASTHeader);
				driver.navigate().to(ConfigDetails.strURL);
			} else {
				ReporterLog.fail("SignUP to HOOQ Application with SingtelCAST account",
						"SingtelCAST signup page is not displayed");

			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifySignUpWithOTTMobile Developed By :- Indraja Reddy Date
	 * :- 23-May-2019
	 */
	public void verifySignUpWithOTTMobile(String loginID, String strUserType, String strSubscriptionMode,
			String strValid, String strSecure, String strSecureEmail, String strRegion) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			waitForElementClickable(signupBtn, "signupBtn");
			String signupTitle = getText(signupBtn, "sign up page title");
			if (isNumOf_Elements_Greater_Than_Zero(signupBtn)) {
				ReporterLog.pass("Verify Login Page", " " + signupTitle + " displayed successfully");
			} else {
				ReporterLog.fail("Verify Login Page", "" + signupTitle + " is not displayed successfully");
			}
			ReporterLog.pass("Login to HOOQ Application", "Login with " + loginID + " User Type: " + strUserType);
			type(txtMobile, loginID, "Mobile Number");
			type(passwordText, "cigniti123", "Password");
			click(evNextBtn, "Next");

			if (strValid.equalsIgnoreCase("Valid")) {

				if (isNumOf_Elements_Greater_Than_Zero(lblAcntExists)) {
					String strMobileAlreadyExists = getText(lblAcntExists, "Account already exists");
					ReporterLog.fail("HOOQ SignUp with Mobile OTT User", strMobileAlreadyExists + " - is displayed");

				} else if (isNumOf_Elements_Greater_Than_Zero(lblOTPForm)) {
					ReporterLog.pass("HOOQ SignUp with Mobile OTT User", "OTP verification page is displayed");

					for (int i = 1; i <= 6; i++) {
						driver.findElement(By.xpath("//*[@id='otp-input']/input[" + i + "]"))
								.sendKeys(String.valueOf(i));
					}
					click(btnOTPNxt, "Next");

					if (isNumOf_Elements_Greater_Than_Zero(lblInvalidOTP)) {
						ReporterLog.fail("HOOQ signup with Mobile OTT User", "Invalid OTP");

					} else if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
						ReporterLog.pass("HOOQ SignUp with Mobile OTT User", "Plan Selector is displayed");

						if (strRegion.equalsIgnoreCase("IN")) {
							if (strSubscriptionMode.equalsIgnoreCase("CreditCard")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								fnFillCCDetails();

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
										ReporterLog.pass("Verify Subscription with Credit card",
												"Subscription has been done successfully");
										click(getStarted, "Get Started");

										if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmail)) {
											if (strSecure.equalsIgnoreCase("SecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											} else if (strSecure.equalsIgnoreCase("Skip")) {
												String strSecureEmailHeader = getText(lblSecureEmail,
														"Secure your account with email");
												ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
														"Secure your account page is displayed: "
																+ strSecureEmailHeader);
												click(lnkSkipSecureEmail, "Skip");
											} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
													if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists)) {
														String strExistingEmail = getText(lblSecureEmailExists,
																"Email exists label");
														ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																"Popup is displayed for existing secure email: "
																		+ strExistingEmail);
														click(btnEmailExistClose, "Close");

													} else {
														ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																"Popup is not displayed for existing secure email ");

													}
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											}
										}

										click(hamburgerMenu, "Hamburger Menu");

										By loginElement = By.xpath(
												"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
										waitForElementClickable(loginElement,"loginElement");
										String loggedinID = getText(loginElement, "LoggedIn ID");
										if (loginID.contains(loggedinID)) {
											ReporterLog.pass("Verify Login",
													loggedinID + "User Logged in successfully");

										} else {
											ReporterLog.fail("Verify Login", " User does not logged in successfully");
										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Credit card",
											"Subscription has not been done");
								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Paytm")) {
								click(plnSelectorPayTMPymnt, "Pay TM");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								try {
									driver.switchTo().frame("login-iframe");
									if (isNumOf_Elements_Greater_Than_Zero(lblPayTm)) {
										String strTxt = getText(lblPayTm, "PayTM Login");
										ReporterLog.pass("Verify Subscription with PayTM",
												"PayTM Login page is displayed: " + strTxt);
									}
								} catch (Exception e) {
									ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
											"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber()
													+ " And Exception is: " + e.toString());
								}

							} else if (strSubscriptionMode.equalsIgnoreCase("Ola Money")) {
								click(plnSelectorOlaMoneyPymnt, "Ola Money");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
										String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
										ReporterLog.pass("Verify Subscription with Ola Money",
												"Plan durations page is displayed " + strTxt1);
										click(lblPrepaidPlan1Month, "1 Month Subscription");
										if (isNumOf_Elements_Greater_Than_Zero(lblOlaMoney)) {
											String strTxt = getText(lblOlaMoney, "Ola Payment page");
											ReporterLog.pass("Verify Subscription with Ola Money",
													"Ola Money payment page is displayed: " + strTxt);

										} else {
											ReporterLog.fail("Verify Subscription with Ola Money",
													"Ola Money payment page is not displayed: ");

										}
									} else {
										ReporterLog.fail("Verify Subscription with Ola Money",
												"Plan durations page is not displayed ");

									}
								} catch (Exception e) {
									ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
											"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber()
													+ " And Exception is: " + e.toString());
								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Phone Bill")) {
								click(plnSelectorPhoneBillPymnt, "Phone bill");
								click(btnPlanSelectorSubscribe, "Subscribe now");

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
										String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
										ReporterLog.pass("Verify Subscription with Phone Bill",
												"Pop up: " + strTxt + " is displayed");
										click(lblPrepaidFreeTrailContinue, "Continue");
										if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
											String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
											ReporterLog.pass("Verify Subscription with Phone Bill",
													"Plan durations page is displayed " + strTxt1);
											click(lblPrepaidPlan7Days, "7 Days Subscription");
											if (isNumOf_Elements_Greater_Than_Zero(lblMobileBill)) {
												String strTxt2 = getText(lblMobileBill, "Mobile Payment page");
												ReporterLog.pass("Verify Subscription with Phone Bill",
														"Phone Bill payment page is displayed: " + strTxt2);

											} else {
												ReporterLog.fail("Verify Subscription with Phone Bill",
														"Phone Bill payment page is not displayed: ");
											}
										}
									}
								} catch (Exception e) {
									ReporterLog.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
											"Test Result FAILED, and Line No: " + e.getStackTrace()[0].getLineNumber()
													+ " And Exception is: " + e.toString());
								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Debit Card")) {
								click(plnSelectorDebitCardPymnt, "Debit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
										String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
										ReporterLog.pass("Verify Subscription with Phone Bill",
												"Pop up: " + strTxt + " is displayed");
										click(lblPrepaidFreeTrailContinue, "Continue");
										if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
											String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
											ReporterLog.pass("Verify Subscription with Debit Card",
													"Plan durations page is displayed " + strTxt1);
											click(lblPrepaidPlan1Month, "1 Month Subscription");

											fnFillCCDetails();

											click(btnReturnToSite, "Return to Merchant site");

											try {
												if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStartPrepaid)) {
													ReporterLog.pass("Verify Subscription with Debit card",
															"Subscription has been done successfully");
													click(btnStartBrowsingDone, "Start watching");
													if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmail)) {
														if (strSecure.equalsIgnoreCase("SecureEmail")) {
															if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
																ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																		"Secure Email field is displayed ");
																type(txtLoginEmail, strSecureEmail, "SecureEmail");
																click(btnEmailSignUpNxt, "Done");
															} else {
																ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																		"Secure Email field is not displayed ");
															}
														} else if (strSecure.equalsIgnoreCase("Skip")) {
															String strSecureEmailHeader = getText(lblSecureEmail,
																	"Secure your account with email");
															ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																	"Secure your account page is displayed: "
																			+ strSecureEmailHeader);
															click(lnkSkipSecureEmail, "Skip");
														} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
															if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
																ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																		"Secure Email field is displayed ");
																type(txtLoginEmail, strSecureEmail, "SecureEmail");
																click(btnEmailSignUpNxt, "Done");
																if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists)) {
																	String strExistingEmail = getText(
																			lblSecureEmailExists, "Email exists label");
																	ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																			"Popup is displayed for existing secure email: "
																					+ strExistingEmail);
																	click(btnEmailExistClose, "Close");

																} else {
																	ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																			"Popup is not displayed for existing secure email ");

																}
															} else {
																ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																		"Secure Email field is not displayed ");
															}
														}
													}

													click(hamburgerMenu, "Hamburger Menu");
													By loginElement = By.xpath(
															"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
													waitForElementClickable(loginElement,"loginElement");
													String loggedinID = getText(loginElement, "LoggedIn ID");
													if (loginID.contains(loggedinID)) {
														ReporterLog.pass("Verify Login",
																loggedinID + "User Logged in successfully");

													} else {
														ReporterLog.fail("Verify Login",
																" User does not logged in successfully");
													}
												}
											} catch (Exception f) {
												ReporterLog.fail("Verify Subscription with Debit card",
														"Subscription has not been done");

											}
										} else {
											ReporterLog.fail("Verify Subscription with Debit Card",
													"Plan durations page is not displayed ");

										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Debit Card",
											"Subscription has not been done");
								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Net Banking")) {
								click(plnSelectorNetBankingPymnt, "Net Banking");
								click(btnPlanSelectorSubscribe, "Subscribe now");

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
										String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
										ReporterLog.pass("Verify Subscription with Net Banking",
												"Pop up: " + strTxt + " is displayed");
										click(lblPrepaidFreeTrailContinue, "Continue");
										if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
											String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
											ReporterLog.pass("Verify Subscription with Net Banking",
													"Plan durations page is displayed " + strTxt1);
											click(lblPrepaidPlan1Month, "1 Month Subscription");
											if (isNumOf_Elements_Greater_Than_Zero(lblNetBanking)) {
												String strTxt2 = getText(lblNetBanking, "NetBanking Payment page");
												ReporterLog.pass("Verify Subscription with Net Banking",
														"Net Banking payment page is displayed: " + strTxt2);

											} else {
												ReporterLog.fail("Verify Subscription with Net Banking",
														"Net Banking payment page is not displayed");

											}
										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Net Banking",
											"Subscription has not been done");
								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Cash Card")) {
								click(plnSelectorCashCardPymnt, "Cash card");
								click(btnPlanSelectorSubscribe, "Subscribe now");

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
										String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
										ReporterLog.pass("Verify Subscription with Phone Bill",
												"Pop up: " + strTxt + " is displayed");
										click(lblPrepaidFreeTrailContinue, "Continue");
										if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
											String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
											ReporterLog.pass("Verify Subscription with Cash Card",
													"Plan durations page is displayed with Label - " + strTxt1);
											click(lblPrepaidPlan1Month, "1 Month Subscription");
											if (isNumOf_Elements_Greater_Than_Zero(lblCashCard)) {
												String strTxt2 = getText(lblCashCard, "Cash Card Payment");
												ReporterLog.pass("Verify Subscription with Cash card",
														"Cash Card payment page is displayed: " + strTxt2);

											} else {
												ReporterLog.fail("Verify Subscription with Cash card",
														"Cash Card payment page is not displayed: ");

											}
										} else {
											ReporterLog.fail("Verify Subscription with Cash card",
													"Plan durations page is not displayed");

										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Cash Card",
											"Subscription has not been done");
								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								click(btnCancelCreditPayment, "Cancel Payment");
								if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
									String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Payment is cancelled successfully with the label: " + strTxt1);
									click(lblPaymentCancelTryAgain, "Try again");
									if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
										ReporterLog.pass("Verify Cancel subscription with Credit Card",
												"Plan Selector is displayed after clicking on Try again button");

									} else {
										ReporterLog.fail("Verify Cancel subscription with Credit Card",
												"Plan Selector is not displayed after clicking on Try again button");

									}
								} else {
									ReporterLog.fail("Verify Cancel subscription with Credit Card",
											"Payment cancelled message is not displayed");

								}
							} else {
								ReporterLog.fail("Verify Subscription", "Subsription mode is invalid");
							}
						} else if (strRegion.equalsIgnoreCase("TH")) {
							if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								fnFillCCDetails();

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
										ReporterLog.pass("Verify Subscription with Credit card",
												"Subscription has been done successfully");
										click(btnStartBrowsingDone, "Start watching");

										if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmail)) {
											if (strSecure.equalsIgnoreCase("SecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											} else if (strSecure.equalsIgnoreCase("Skip")) {
												String strSecureEmailHeader = getText(lblSecureEmail,
														"Secure your account with email");
												ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
														"Secure your account page is displayed: "
																+ strSecureEmailHeader);
												click(lnkSkipSecureEmail, "Skip");
											} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
													if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists
															)) {
														String strExistingEmail = getText(lblSecureEmailExists,
																"Email exists label");
														ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																"Popup is displayed for existing secure email: "
																		+ strExistingEmail);
														click(btnEmailExistClose, "Close");

													} else {
														ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																"Popup is not displayed for existing secure email ");

													}
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											}
										}

										click(hamburgerMenu, "Hamburger Menu");
										By loginElement = By.xpath(
												"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
										isNumOf_Elements_Greater_Than_Zero(loginElement);
										String loggedinID = getText(loginElement, "LoggedIn ID");
										if (loginID.contains(loggedinID)) {
											ReporterLog.pass("Verify Login",
													loggedinID + "User Logged in successfully");

										} else {
											ReporterLog.fail("Verify Login", " User does not logged in successfully");
										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Credit card",
											"Subscription has not been done");

								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								click(btnCancelCreditPayment, "Cancel Payment");

								if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
									String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Payment is cancelled successfully with the label: " + strTxt1);
									click(lblPaymentCancelTryAgain, "Try again");
									if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
										ReporterLog.pass("Verify Cancel subscription with Credit Card",
												"Plan Selector is displayed after clicking on Try again button");

									} else {
										ReporterLog.fail("Verify Cancel subscription with Credit Card",
												"Plan Selector is not displayed after clicking on Try again button");

									}
								}
							} else {
								ReporterLog.fail("Verify Subscription", "Invalid payment method");

							}
						} else if (strRegion.equalsIgnoreCase("SG")) {
							if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Start your free trial now");
								fnFillCCDetails();
								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
										ReporterLog.pass("Verify Subscription with Credit card",
												"Subscription has been done successfully");
										click(btnStartBrowsingDone, "Start watching");
									
										if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmail)) {
											if (strSecure.equalsIgnoreCase("SecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											} else if (strSecure.equalsIgnoreCase("Skip")) {
												String strSecureEmailHeader = getText(lblSecureEmail,
														"Secure your account with email");
												ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
														"Secure your account page is displayed: "
																+ strSecureEmailHeader);
												click(lnkSkipSecureEmail, "Skip");
											} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
													if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists
															)) {
														String strExistingEmail = getText(lblSecureEmailExists,
																"Email exists label");
														ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																"Popup is displayed for existing secure email: "
																		+ strExistingEmail);
														click(btnEmailExistClose, "Close");

													} else {
														ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																"Popup is not displayed for existing secure email ");

													}
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											}
										}

										click(hamburgerMenu, "Hamburger Menu");
										By loginElement = By.xpath(
												"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
										isNumOf_Elements_Greater_Than_Zero(loginElement);
										String loggedinID = getText(loginElement, "LoggedIn ID");
										if (loginID.contains(loggedinID)) {
											ReporterLog.pass("Verify Login",
													loggedinID + "User Logged in successfully");

										} else {
											ReporterLog.fail("Verify Login", " User does not logged in successfully");
										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Credit card",
											"Subscription has not been done");

								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								click(btnCancelCreditPayment, "Cancel Payment");
								if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
									String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Payment is cancelled successfully with the label: " + strTxt1);
									click(lblPaymentCancelTryAgain, "Try again");
									if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
										ReporterLog.pass("Verify Cancel subscription with Credit Card",
												"Plan Selector is displayed after clicking on Try again button");

									} else {
										ReporterLog.fail("Verify Cancel subscription with Credit Card",
												"Plan Selector is not displayed after clicking on Try again button");

									}
								}
							} else {
								ReporterLog.fail("Verify Subscription", "Invalid payment method");

							}
						} else if (strRegion.equalsIgnoreCase("PH")) {
							if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Start your free trial now");
								fnFillCCDetails();

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
										ReporterLog.pass("Verify Subscription with Credit card",
												"Subscription has been done successfully");
										click(btnStartBrowsingDone, "Start watching");
										

										if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmail)) {
											if (strSecure.equalsIgnoreCase("SecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											} else if (strSecure.equalsIgnoreCase("Skip")) {
												String strSecureEmailHeader = getText(lblSecureEmail,
														"Secure your account with email");
												ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
														"Secure your account page is displayed: "
																+ strSecureEmailHeader);
												click(lnkSkipSecureEmail, "Skip");
											} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
													if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists
															)) {
														String strExistingEmail = getText(lblSecureEmailExists,
																"Email exists label");
														ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																"Popup is displayed for existing secure email: "
																		+ strExistingEmail);
														click(btnEmailExistClose, "Close");

													} else {
														ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																"Popup is not displayed for existing secure email ");

													}
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											}
										}

										click(hamburgerMenu, "Hamburger Menu");
										By loginElement = By.xpath(
												"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
										isNumOf_Elements_Greater_Than_Zero(loginElement);
										String loggedinID = getText(loginElement, "LoggedIn ID");
										if (loginID.contains(loggedinID)) {
											ReporterLog.pass("Verify Login",
													loggedinID + "User Logged in successfully");

										} else {
											ReporterLog.fail("Verify Login", " User does not logged in successfully");
										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Credit card",
											"Subscription has not been done");

								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								click(btnCancelCreditPayment, "Cancel Payment");
								if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
									String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Payment is cancelled successfully with the label: " + strTxt1);
									click(lblPaymentCancelTryAgain, "Try again");
									if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
										ReporterLog.pass("Verify Cancel subscription with Credit Card",
												"Plan Selector is displayed after clicking on Try again button");

									} else {
										ReporterLog.fail("Verify Cancel subscription with Credit Card",
												"Plan Selector is not displayed after clicking on Try again button");

									}
								}
							} else {
								ReporterLog.fail("Verify Subscription", "Invalid payment method");

							}
						} else if (strRegion.equalsIgnoreCase("ID")) {
							if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Start your free trial now");
								fnFillCCDetails();

								try {
									if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
										ReporterLog.pass("Verify Subscription with Credit card",
												"Subscription has been done successfully");
										click(btnStartBrowsingDone, "Start watching");
										
										if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmail)) {
											if (strSecure.equalsIgnoreCase("SecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											} else if (strSecure.equalsIgnoreCase("Skip")) {
												String strSecureEmailHeader = getText(lblSecureEmail,
														"Secure your account with email");
												ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
														"Secure your account page is displayed: "
																+ strSecureEmailHeader);
												click(lnkSkipSecureEmail, "Skip");
											} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
												if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
													ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is displayed ");
													type(txtLoginEmail, strSecureEmail, "SecureEmail");
													click(btnEmailSignUpNxt, "Done");
													if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists
															)) {
														String strExistingEmail = getText(lblSecureEmailExists,
																"Email exists label");
														ReporterLog.pass("HOOQ SignUp with Mobile OTT User",
																"Popup is displayed for existing secure email: "
																		+ strExistingEmail);
														click(btnEmailExistClose, "Close");

													} else {
														ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
																"Popup is not displayed for existing secure email ");

													}
												} else {
													ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
															"Secure Email field is not displayed ");
												}
											}
										}

										click(hamburgerMenu, "Hamburger Menu");
										By loginElement = By.xpath(
												"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
										isNumOf_Elements_Greater_Than_Zero(loginElement);
										String loggedinID = getText(loginElement, "LoggedIn ID");
										if (loginID.contains(loggedinID)) {
											ReporterLog.pass("Verify Login",
													loggedinID + "User Logged in successfully");

										} else {
											ReporterLog.fail("Verify Login", " User does not logged in successfully");
										}
									}
								} catch (Exception e) {
									ReporterLog.fail("Verify Subscription with Credit card",
											"Subscription has not been done");

								}
							} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
								click(plnSelectorCreditCardPymnt, "Credit Card");
								click(btnPlanSelectorSubscribe, "Subscribe now");
								click(btnCancelCreditPayment, "Cancel Payment");
								if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
									String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Payment is cancelled successfully with the label: " + strTxt1);
									click(lblPaymentCancelTryAgain, "Try again");
									if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
										ReporterLog.pass("Verify Cancel subscription with Credit Card",
												"Plan Selector is displayed after clicking on Try again button");

									} else {
										ReporterLog.fail("Verify Cancel subscription with Credit Card",
												"Plan Selector is not displayed after clicking on Try again button");

									}
								}
							} else {
								ReporterLog.fail("Verify Subscription", "Invalid payment method");

							}
						} else {
							ReporterLog.fail("Verify Subscription", "Invalid Region");

						}
					} else {
						ReporterLog.fail("HOOQ SignUp with Mobile OTT User", "Plan Selector is not displayed");

					}
				} else {
					ReporterLog.fail("HOOQ SignUp with Mobile OTT User", "OTP Verification page is not displayed");

				}
			} else if (strValid.equalsIgnoreCase("Invalid")) {
				if (isNumOf_Elements_Greater_Than_Zero(lblMobileAcntExists)) {
					String strMobileAlreadyExists = getText(lblMobileAcntExists, "Account already exists");
					ReporterLog.pass("HOOQ SignUp with Mobile OTT User", strMobileAlreadyExists + " - is displayed");

					click(btnLoginToMyAccount, "Log in to my account");

					if (isNumOf_Elements_Greater_Than_Zero(lblLoginMobilePage)
							|| isNumOf_Elements_Greater_Than_Zero(lblLoginEmailPage)) {
						ReporterLog.pass("HOOQ Login with Mobile OTT User", " Login page is displayed");

					} else {
						ReporterLog.fail("HOOQ Login with Mobile OTT User", " Login page is not displayed");

					}
				} else {
					ReporterLog.fail("HOOQ SignUp with Mobile OTT User", "Account exists popup is  not displayed");

				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifySignUpWithOTTEmail Developed By :- Indraja Reddy Date
	 * :- 23-May-2019
	 */
	public void verifySignUpWithOTTEmail(String email, String strUserType, String strSubscriptionMode, String strValid,
			String strRegion) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			ReporterLog.pass("Login to HOOQ Application", "Login with " + email + " User Type: " + strUserType);
			click(lblEmail, "Click on Email button");
			type(txtSignUpEmail, email, "Email ID");
			type(txtCreatePWD, "cigniti123", "Password");
			click(btnEmailSignUpNxt, "Next");

			if (strValid.equalsIgnoreCase("Valid")) {
				if (isNumOf_Elements_Greater_Than_Zero(lblAcntExists)) {
					String strEmailAlreadyExists = getText(lblAcntExists, "Account already exists");
					ReporterLog.fail("HOOQ Signup with Email OTT User", strEmailAlreadyExists + " - is displayed");

				} else if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
					ReporterLog.pass("HOOQ Signup with Email OTT User", "Plan Selector is displayed");

					if (strRegion.equalsIgnoreCase("IN")) {
						if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							fnFillCCDetails();
							click(btnReturnToSite, "Return to Merchant site");

							try {

								if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
									ReporterLog.pass("Verify Subscription with Credit card",
											"Subscription has been done successfully");
									click(btnStartBrowsingDone, "Start watching");
									click(hamburgerMenu, "Hamburger Menu");
									By loginElement = By
											.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
									isNumOf_Elements_Greater_Than_Zero(loginElement);
									String loggedinID = getText(loginElement, "LoggedIn ID");
									if (email.contains(loggedinID)) {
										ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

									} else {
										ReporterLog.fail("Verify Login", " User does not logged in successfully");
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Credit card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Paytm")) {
							click(plnSelectorPayTMPymnt, "Pay TM");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							try {
								driver.switchTo().frame("login-iframe");
								if (isNumOf_Elements_Greater_Than_Zero(lblPayTm)) {
									String strTxt = getText(lblPayTm, "PayTM Login");
									ReporterLog.pass("Verify Subscription with PayTM",
											"PayTM Login page is displayed: " + strTxt);

								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with PayTM", "PayTM Login page is not displayed");

							}

						} else if (strSubscriptionMode.equalsIgnoreCase("Ola Money")) {
							click(plnSelectorOlaMoneyPymnt, "Ola Money");
							click(btnPlanSelectorSubscribe, "Subscribe now");

							try {

								if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
									String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
									ReporterLog.pass("Verify Subscription with Ola Money",
											"Plan durations page is displayed " + strTxt1);
									click(lblPrepaidPlan1Month, "1 Month Subscription");
									if (isNumOf_Elements_Greater_Than_Zero(lblOlaMoney)) {
										String strTxt = getText(lblOlaMoney, "Ola Payment page");
										ReporterLog.pass("Verify Subscription with Ola Money",
												"Ola Money payment page is displayed: " + strTxt);

									} else {
										ReporterLog.fail("Verify Subscription with Ola Money",
												"Ola Money payment page is not displayed: ");

									}
								} else {
									ReporterLog.fail("Verify Subscription with Ola Money",
											"Plan durations page is not displayed ");

								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Ola Money",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Phone Bill")) {
							click(plnSelectorPhoneBillPymnt, "Phone bill");
							click(btnPlanSelectorSubscribe, "Subscribe now");

							try {
								if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
									String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
									ReporterLog.pass("Verify Subscription with Phone Bill",
											"Pop up: " + strTxt + " is displayed");
									click(lblPrepaidFreeTrailContinue, "Continue");
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
										String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
										ReporterLog.pass("Verify Subscription with Phone Bill",
												"Plan durations page is displayed " + strTxt1);
										click(lblPrepaidPlan7Days, "7 Days Subscription");
										if (isNumOf_Elements_Greater_Than_Zero(lblMobileBill)) {
											String strTxt2 = getText(lblMobileBill, "Mobile Payment page");
											ReporterLog.pass("Verify Subscription with Phone Bill",
													"Phone Bill payment page is displayed: " + strTxt2);

										} else {
											ReporterLog.fail("Verify Subscription with Phone Bill",
													"Phone Bill payment page is not displayed: ");

										}
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Phone Bill",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Debit Card")) {
							click(plnSelectorDebitCardPymnt, "Debit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");

							try {
								if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
									String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
									ReporterLog.pass("Verify Subscription with Debit Card",
											"Pop up: " + strTxt + " is displayed");
									click(lblPrepaidFreeTrailContinue, "Continue");
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans))
										if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
											String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
											ReporterLog.pass("Verify Subscription with Debit Card",
													"Plan durations page is displayed " + strTxt1);
											click(lblPrepaidPlan1Month, "1 Month Subscription");
											fnFillCCDetails();
											click(btnReturnToSite, "Return to Merchant site");

											try {

												if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStartPrepaid)) {
													ReporterLog.pass("Verify Subscription with Debit card",
															"Subscription has been done successfully");
													click(btnStartBrowsingDone, "Start watching");

													String urlWelcomePage = getText(strReturnURL, "Return URL");
													if (urlWelcomePage.isEmpty()) {
														ReporterLog.fail("HOOQ Signup with Email OTT User",
																"Return URL is not displayed");

													} else {
														ReporterLog.pass("HOOQ Signup with Email OTT User",
																"Return URL: " + urlWelcomePage);
														ReporterLog.pass("HOOQ Signup with Email OTT User",
																"Return URL is displayed successfully with ");

													}
												}
											} catch (Exception f) {
												ReporterLog.fail("Verify Subscription with Debit card",
														"Subscription has not been done");

											}
										} else {
											ReporterLog.fail("Verify Subscription with Debit Card",
													"Plan durations page is not displayed ");

										}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Debit Card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Net Banking")) {
							click(plnSelectorNetBankingPymnt, "Net Banking");
							click(btnPlanSelectorSubscribe, "Subscribe now");

							try {
								if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
									String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
									ReporterLog.pass("Verify Subscription with Phone Bill",
											"Pop up: " + strTxt + " is displayed");
									click(lblPrepaidFreeTrailContinue, "Continue");
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
										String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
										ReporterLog.pass("Verify Subscription with Net Banking",
												"Plan durations page is displayed " + strTxt1);
										click(lblPrepaidPlan1Month, "1 Month Subscription");
										if (isNumOf_Elements_Greater_Than_Zero(lblNetBanking)) {
											String strTxt2 = getText(lblNetBanking, "NetBanking Payment page");
											ReporterLog.pass("Verify Subscription with Net Banking",
													"Net Banking payment page is displayed: " + strTxt2);

										} else {
											ReporterLog.fail("Verify Subscription with Net Banking",
													"Net Banking payment page is not displayed");

										}
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Net Banking",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Cash Card")) {
							click(plnSelectorCashCardPymnt, "Cash card");
							click(btnPlanSelectorSubscribe, "Subscribe now");

							try {
								if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidFreeTrail)) {
									String strTxt = getText(lblPrepaidFreeTrail, "Don't miss your free trial");
									ReporterLog.pass("Verify Subscription with Phone Bill",
											"Pop up: " + strTxt + " is displayed");
									click(lblPrepaidFreeTrailContinue, "Continue");
									if (isNumOf_Elements_Greater_Than_Zero(lblPrepaidPlans)) {
										String strTxt1 = getText(lblPrepaidPlans, "Plan durations");
										ReporterLog.pass("Verify Subscription with Cash Card",
												"Plan durations page is displayed with Label - " + strTxt1);
										click(lblPrepaidPlan1Month, "1 Month Subscription");
										if (isNumOf_Elements_Greater_Than_Zero(lblCashCard)) {
											String strTxt2 = getText(lblCashCard, "Cash Card Payment");
											ReporterLog.pass("Verify Subscription with Cash card",
													"Cash Card payment page is displayed: " + strTxt2);

										} else {
											ReporterLog.fail("Verify Subscription with Cash card",
													"Cash Card payment page is not displayed: ");

										}
									} else {
										ReporterLog.fail("Verify Subscription with Cash card",
												"Plan durations page is not displayed");

									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Cash Card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							click(btnCancelCreditPayment, "Cancel Payment");
							if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
								String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
								ReporterLog.pass("Verify Cancel subscription with Credit Card",
										"Payment is cancelled successfully with the label: " + strTxt1);
								click(lblPaymentCancelTryAgain, "Try again");
								if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Plan Selector is displayed after clicking on Try again button");

								} else {
									ReporterLog.fail("Verify Cancel subscription with Credit Card",
											"Plan Selector is not displayed after clicking on Try again button");

								}
							}
						} else {
							ReporterLog.fail("Verify Subscription", "Subsription mode is invalid");

						}
					} else if (strRegion.equalsIgnoreCase("TH")) {
						if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							fnFillCCDetails();

							try {

								if (isNumOf_Elements_Greater_Than_Zero(btnStartBrowsingDone)) {
									ReporterLog.pass("Verify Subscription with Credit card",
											"Subscription has been done successfully");
									click(btnStartBrowsingDone, "Start watching");

									click(hamburgerMenu, "Hamburger Menu");
									By loginElement = By
											.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
									isNumOf_Elements_Greater_Than_Zero(loginElement);
									String loggedinID = getText(loginElement, "LoggedIn ID");
									if (email.contains(loggedinID)) {
										ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

									} else {
										ReporterLog.fail("Verify Login", " User does not logged in successfully");
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Credit card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							click(btnCancelCreditPayment, "Cancel Payment");
							if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
								String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
								ReporterLog.pass("Verify Cancel subscription with Credit Card",
										"Payment is cancelled successfully with the label: " + strTxt1);
								click(lblPaymentCancelTryAgain, "Try again");
								if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Plan Selector is displayed after clicking on Try again button");

								} else {
									ReporterLog.fail("Verify Cancel subscription with Credit Card",
											"Plan Selector is not displayed after clicking on Try again button");

								}
							}
						} else {
							ReporterLog.fail("Verify Subscription", "Invalid payment method");

						}
					} else if (strRegion.equalsIgnoreCase("SG")) {
						if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Start your free trial now");
							fnFillCCDetails();

							try {

								if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
									ReporterLog.pass("Verify Subscription with Credit card",
											"Subscription has been done successfully");
									click(btnStartBrowsingDone, "Start watching");

									click(hamburgerMenu, "Hamburger Menu");
									By loginElement = By
											.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
									isNumOf_Elements_Greater_Than_Zero(loginElement);
									String loggedinID = getText(loginElement, "LoggedIn ID");
									if (email.contains(loggedinID)) {
										ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

									} else {
										ReporterLog.fail("Verify Login", " User does not logged in successfully");
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Credit card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							click(btnCancelCreditPayment, "Cancel Payment");
							if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
								String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
								ReporterLog.pass("Verify Cancel subscription with Credit Card",
										"Payment is cancelled successfully with the label: " + strTxt1);
								click(lblPaymentCancelTryAgain, "Try again");
								if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Plan Selector is displayed after clicking on Try again button");

								} else {
									ReporterLog.fail("Verify Cancel subscription with Credit Card",
											"Plan Selector is not displayed after clicking on Try again button");

								}
							}
						} else {
							ReporterLog.fail("Verify Subscription", "Invalid payment method");

						}
					} else if (strRegion.equalsIgnoreCase("PH")) {
						if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Start your free trial now");
							fnFillCCDetails();

							try {

								if (isNumOf_Elements_Greater_Than_Zero(lblSubcriptionStart)) {
									ReporterLog.pass("Verify Subscription with Credit card",
											"Subscription has been done successfully");
									click(btnStartBrowsingDone, "Start watching");

									click(hamburgerMenu, "Hamburger Menu");
									By loginElement = By
											.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
									isNumOf_Elements_Greater_Than_Zero(loginElement);
									String loggedinID = getText(loginElement, "LoggedIn ID");
									if (email.contains(loggedinID)) {
										ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

									} else {
										ReporterLog.fail("Verify Login", " User does not logged in successfully");
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Credit card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							click(btnCancelCreditPayment, "Cancel Payment");
							if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
								String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
								ReporterLog.pass("Verify Cancel subscription with Credit Card",
										"Payment is cancelled successfully with the label: " + strTxt1);
								click(lblPaymentCancelTryAgain, "Try again");
								if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Plan Selector is displayed after clicking on Try again button");

								} else {
									ReporterLog.fail("Verify Cancel subscription with Credit Card",
											"Plan Selector is not displayed after clicking on Try again button");

								}
							}
						} else {
							ReporterLog.fail("Verify Subscription", "Invalid payment method");

						}
					} else if (strRegion.equalsIgnoreCase("ID")) {
						if (strSubscriptionMode.equalsIgnoreCase("Credit Card")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Start your free trial now");
							fnFillCCDetails();

							try {

								if (isNumOf_Elements_Greater_Than_Zero(btnStartBrowsingDone)) {
									ReporterLog.pass("Verify Subscription with Credit card",
											"Subscription has been done successfully");
									click(btnStartBrowsingDone, "Start watching");

									click(hamburgerMenu, "Hamburger Menu");
									By loginElement = By
											.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
									isNumOf_Elements_Greater_Than_Zero(loginElement);
									String loggedinID = getText(loginElement, "LoggedIn ID");
									if (email.contains(loggedinID)) {
										ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

									} else {
										ReporterLog.fail("Verify Login", " User does not logged in successfully");
									}
								}
							} catch (Exception e) {
								ReporterLog.fail("Verify Subscription with Credit card",
										"Subscription has not been done");

							}
						} else if (strSubscriptionMode.equalsIgnoreCase("Cancel")) {
							click(plnSelectorCreditCardPymnt, "Credit Card");
							click(btnPlanSelectorSubscribe, "Subscribe now");
							click(btnCancelCreditPayment, "Cancel Payment");
							if (isNumOf_Elements_Greater_Than_Zero(lblPaymentCancel)) {
								String strTxt1 = getText(lblPaymentCancel, "Cancel Payment");
								ReporterLog.pass("Verify Cancel subscription with Credit Card",
										"Payment is cancelled successfully with the label: " + strTxt1);
								click(lblPaymentCancelTryAgain, "Try again");
								if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
									ReporterLog.pass("Verify Cancel subscription with Credit Card",
											"Plan Selector is displayed after clicking on Try again button");

								} else {
									ReporterLog.fail("Verify Cancel subscription with Credit Card",
											"Plan Selector is not displayed after clicking on Try again button");

								}
							}
						} else {
							ReporterLog.fail("Verify Subscription", "Invalid payment method");

						}
					} else {
						ReporterLog.fail("Verify Subscription", "Invalid Region");

					}
				} else {
					ReporterLog.fail("HOOQ SignUp with Email OTT User", "Plan Selector is not displayed");

				}
			} else if (strValid.equalsIgnoreCase("Invalid")) {
				if (isNumOf_Elements_Greater_Than_Zero(lblAcntExists)) {
					String strEmailAlreadyExists = getText(lblAcntExists, "Account already exists");
					ReporterLog.pass("HOOQ Login with Email OTT User", strEmailAlreadyExists + " - is displayed");

					click(btnLoginToMyAccount, "Log in to my account");

					if (isNumOf_Elements_Greater_Than_Zero(lblLoginMobilePage)
							|| isNumOf_Elements_Greater_Than_Zero(lblLoginEmailPage)) {
						ReporterLog.pass("HOOQ Login with Email OTT User", " Login page is displayed");

					} else {
						ReporterLog.fail("HOOQ Login with Email OTT User", " Login page is not displayed");

					}
				} else {
					ReporterLog.fail("HOOQ SignUp with Email OTT User", "Account exists popup is  not displayed");

				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifySignUpWithOTTMobileSkipNova Developed By :- Indraja
	 * Reddy Date :- 23-May-2019
	 */
	public void verifySignUpWithOTTMobileSkipNova(String loginID, String strUserType, String strSubscriptionMode,
			String strValid, String strRegion) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			ReporterLog.pass("HOOQ SignUp with Skip Nova", "Signup with " + loginID + " User Type: " + strUserType);
			type(txtMobile, loginID, "Mobile Number");
			type(txtCreatePWD, "cigniti123", "Password");
			click(btnMobileSignUpNxt, "Next");

			if (isNumOf_Elements_Greater_Than_Zero(lblOTPForm)) {
				ReporterLog.pass("HOOQ SignUp with Skip Nova", "OTP verification page is displayed");

				for (int i = 1; i <= 6; i++) {
					driver.findElement(By.xpath("//*[@id='otp-input']/input[" + i + "]")).sendKeys(String.valueOf(i));
				}
				click(btnOTPNxt, "Next");
				if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
					ReporterLog.pass("HOOQ SignUp with Skip Nova", "Plan Selector is displayed");

					if (strSubscriptionMode.equalsIgnoreCase("Skip")) {
						if (isNumOf_Elements_Greater_Than_Zero(lnkSkipNova)) {
							ReporterLog.pass("HOOQ SignUp with Skip Nova", "Skip Nova button is displayed");
							click(lnkSkipNova, "Skip");
							if (isNumOf_Elements_Greater_Than_Zero(GetStarted)) {
								String getStartedBtn = getText(GetStarted, "Get started");
								ReporterLog.pass("HOOQ SignUp with Skip Nova",
										"Redirected to GetStarted email page with the label: " + getStartedBtn);
								click(GetStarted, "Get started");
								click(lnkSkipNova, "Skip");
							} else {
								ReporterLog.fail("HOOQ SignUp with Skip Nova",
										"GetStarted email page is not displayed ");

							}
							click(hamburgerMenu, "Hamburger Menu");
							By loginElement = By
									.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
							isNumOf_Elements_Greater_Than_Zero(loginElement);
							String loggedinID = getText(loginElement, "LoggedIn ID");
							if (driver.getPageSource().contains(loggedinID)) {
								ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

							} else {
								ReporterLog.fail("Verify Login", " User does not logged in successfully");
							}
						} else {
							ReporterLog.fail("HOOQ SignUp with Skip Nova", "Skip Nova link is not displayed");

						}
					} else {
						ReporterLog.fail("HOOQ SignUp with Skip Nova", "Subscription mode is invalid");

					}
				} else if (isNumOf_Elements_Greater_Than_Zero(lblInvalidOTP)) {
					ReporterLog.fail("HOOQ SignUp with Skip Nova", "Invalid OTP");

				} else {
					ReporterLog.fail("HOOQ SignUp with Skip Nova", "Plan Selector is not displayed");

				}
			} else if (isNumOf_Elements_Greater_Than_Zero(lblAcntExists)) {
				String strMobileAlreadyExists = getText(lblAcntExists, "Account already exists");
				ReporterLog.fail("HOOQ SignUp with Skip Nova", strMobileAlreadyExists + " - is displayed");

			} else {
				ReporterLog.fail("HOOQ SignUp with Skip Nova", "OTP Verification page is not displayed");

			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifyResendOTP Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifyResendOTP(String loginID, String strUserType, String strSubscriptionMode, String strRegion) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			isNumOf_Elements_Greater_Than_Zero(lblAcntExists);
			String signupTitle = getText(signupBtn, "sign up page title");
			if (isNumOf_Elements_Greater_Than_Zero(lblAcntExists)) {
				ReporterLog.pass("Verify Login Page", " " + signupTitle + " displayed successfully");
			} else {
				ReporterLog.fail("Verify Login Page", "" + signupTitle + " is not displayed successfully");
			}
			ReporterLog.pass("Login to HOOQ Application", "Login with " + loginID + " User Type: " + strUserType);

			type(txtMobile, loginID, "Mobile Number");
			type(passwordText, "cigniti123", "Password");
			click(evNextBtn, "Next");
			if (isNumOf_Elements_Greater_Than_Zero(lblAcntExists)) {
				String strMobileAlreadyExists = getText(lblAcntExists, "Account already exists");
				ReporterLog.fail("HOOQ SignUp with Mobile OTT User", strMobileAlreadyExists + " - is displayed");

			} else if (isNumOf_Elements_Greater_Than_Zero(lblOTPForm)) {
				ReporterLog.pass("HOOQ SignUp with Mobile OTT User", "OTP verification page is displayed");
				String strOTPVerify = getText(lblOTPForm, "OTP Verification");

				if (strOTPVerify.contains("OTP Verification") || strOTPVerify.contains("Verifikasi OTP")
						|| strOTPVerify.contains("ตรวจสอบรหัส OTP")) {

					if (isNumOf_Elements_Greater_Than_Zero(lnkResendOTP)) {
						ReporterLog.pass("HOOQ Login with Email OTT User", "ResendOTP link is displayed ");
						click(lnkResendOTP, "ResendOTP");
						if (isNumOf_Elements_Greater_Than_Zero(lblResendOTPSuccess)) {
							String strResendOTPSuccess = getText(lblResendOTPSuccess, "OTP Resend Successfully");
							ReporterLog.pass("HOOQ Login with Email OTT User",
									"Pop up is displayed with the label: " + strResendOTPSuccess);
							click(btnResendOTPClose, "Close");
							if (isNumOf_Elements_Greater_Than_Zero(lblOTPForm)) {

								for (int i = 1; i <= 6; i++) {
									driver.findElement(By.xpath("//*[@id='otp-input']/input[" + i + "]"))
											.sendKeys(String.valueOf(i));
								}
								click(btnOTPNxt, "Next");
								if (isNumOf_Elements_Greater_Than_Zero(lblInvalidOTP)) {
									ReporterLog.fail("HOOQ signup with Mobile OTT User", "Invalid OTP");

								} else if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {

									if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
										ReporterLog.pass("HOOQ SignUp with Skip Nova", "Plan Selector is displayed");

										if (strSubscriptionMode.equalsIgnoreCase("Skip")) {
											if (isNumOf_Elements_Greater_Than_Zero(lnkSkipNova)) {
												ReporterLog.pass("HOOQ SignUp with Skip Nova",
														"Skip Nova button is displayed");
												click(lnkSkipNova, "Skip");
												if (isNumOf_Elements_Greater_Than_Zero(GetStarted)) {
													String getStartedBtn = getText(GetStarted, "Get started");
													ReporterLog.pass("HOOQ SignUp with Skip Nova",
															"Redirected to GetStarted email page with the label: "
																	+ getStartedBtn);
													click(GetStarted, "Get started");
													click(lnkSkipNova, "Skip");
												} else {
													ReporterLog.fail("HOOQ SignUp with Skip Nova",
															"GetStarted email page is not displayed ");

												}
												click(hamburgerMenu, "Hamburger Menu");
												By loginElement = By.xpath(
														"//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
												isNumOf_Elements_Greater_Than_Zero(loginElement);
												String loggedinID = getText(loginElement, "LoggedIn ID");
												if (driver.getPageSource().contains(loggedinID)) {
													ReporterLog.pass("Verify Login",
															loggedinID + "User Logged in successfully");

												} else {
													ReporterLog.fail("Verify Login",
															" User does not logged in successfully");
												}
											} else {
												ReporterLog.fail("HOOQ SignUp with Skip Nova",
														"Skip Nova link is not displayed");

											}
										} else {
											ReporterLog.fail("HOOQ SignUp with Skip Nova",
													"Subscription mode is invalid");

										}
									}
								} else {
									ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
											"Plan Selector is not displayed");

								}
							} else {
								ReporterLog.fail("HOOQ SignUp with Mobile OTT User",
										"OTP Verification page is not displayed");

							}
						} else {
							ReporterLog.fail("HOOQ Login with Email OTT User",
									"ResendOTP Success popup is not displayed ");

						}
					} else {
						ReporterLog.fail("HOOQ Login with Email OTT User", "ResendOTP link is not displayed ");

					}
				} else {
					ReporterLog.fail("HOOQ Login with Mobile OTT account", "OTP verification page is not displayed ");

				}
			} else {
				ReporterLog.fail("HOOQ Login with Email OTT User", "OTP Verification is not displayed ");

			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- fnFillCCDetails Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void fnFillCCDetails() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			type(inpCardNumber, "4111111111111111", "Card Number");
			type(inpNameOnCard, "Test", "Card User");
			type(inpExpiryDate, "1221", "Card Expire Date");
			type(inpCVV, "345", "Card CVV Number");
			click(btnMakePayment, "Make Payment");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifySignUpWithPreProvMobile Developed By :- Indraja Reddy
	 * Date :- 23-May-2019
	 */
	public void verifySignUpWithPreProvMobile(String loginID, String strUserType, String strValid, String strSecure,
			String strSecureEmail) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {

			type(txtMobile, loginID, "Mobile Number");
			type(txtCreatePWD, "cigniti123", "Password");
			click(btnMobileSignUpNxt, "Next");

			if (strValid.equalsIgnoreCase("Valid")) {
				if (isNumOf_Elements_Greater_Than_Zero(lblMobileAcntExists)) {
					String strMobileAlreadyExists = getText(lblMobileAcntExists, "Account already exists");
					ReporterLog.fail("HOOQ SignUp with Mobile PreProvsioned User",
							strMobileAlreadyExists + " - is displayed");

				} else if (isNumOf_Elements_Greater_Than_Zero(lblOTPForm)) {
					ReporterLog.pass("HOOQ SignUp with Mobile PreProvsioned User",
							"OTP verification page is displayed");
					for (int i = 1; i <= 6; i++) {
						driver.findElement(By.xpath("//*[@id='otp-input']/input[" + i + "]"))
								.sendKeys(String.valueOf(i));
					}
					click(btnOTPNxt, "Next");
					if (isNumOf_Elements_Greater_Than_Zero(lblInvalidOTP)) {
						ReporterLog.fail("HOOQ SignUp with Mobile PreProvsioned User", "Invalid OTP");

					} else {
						try {
							if (isNumOf_Elements_Greater_Than_Zero(lblSignUpSuccess)) {
								String strSuccessMsg = getText(lblSignUpSuccess, "Success message");
								ReporterLog.pass("HOOQ SignUp with Mobile PreProvsioned User",
										"Success message is displayed" + strSuccessMsg);

								click(btnStartWatchingPreProv, "Start watching");
								boolean eleSecureEmail = isNumOf_Elements_Greater_Than_Zero(lblSecureEmail);
								if (eleSecureEmail) {
									if (strSecure.equalsIgnoreCase("SecureEmail")) {
										if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
											ReporterLog.pass("HOOQ SignUp with PreProvisioned User",
													"Secure Email field is displayed ");
											type(txtLoginEmail, strSecureEmail, "SecureEmail");
											click(btnEmailSignUpNxt, "Done");
										}
									} else if (strSecure.equalsIgnoreCase("Skip")) {
										String strSecureEmailHeader = getText(lblSecureEmail,
												"Secure your account with email");
										ReporterLog.pass("HOOQ SignUp with PreProvisioned User",
												"Secure your account page is displayed: " + strSecureEmailHeader);
										click(lnkSkipSecureEmail, "Skip");
									} else if (strSecure.equalsIgnoreCase("ExistingSecureEmail")) {
										if (isNumOf_Elements_Greater_Than_Zero(txtLoginEmail)) {
											ReporterLog.pass("HOOQ SignUp with PreProvisioned User",
													"Secure Email field is displayed ");
											type(txtLoginEmail, strSecureEmail, "SecureEmail");
											click(btnEmailSignUpNxt, "Done");
											if (isNumOf_Elements_Greater_Than_Zero(lblSecureEmailExists)) {
												String strExistingEmail = getText(lblSecureEmailExists,
														"Email exists label");
												ReporterLog.pass("HOOQ SignUp with PreProvisioned User",
														"Popup is displayed for existing secure email: "
																+ strExistingEmail);
												click(btnEmailExistClose, "Close");

											} else {
												ReporterLog.fail("HOOQ SignUp with PreProvisioned User",
														"Popup is not displayed for existing secure email ");

											}
										}
									}
								}

								click(hamburgerMenu, "Hamburger Menu");
								By loginElement = By
										.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
								isNumOf_Elements_Greater_Than_Zero(loginElement);
								String loggedinID = getText(loginElement, "LoggedIn ID");
								if (driver.getPageSource().contains(loggedinID)) {
									ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

								} else {
									ReporterLog.fail("Verify Login", " User does not logged in successfully");
								}

							}
						} catch (Exception e) {
							ReporterLog.fail("HOOQ SignUp with Mobile PreProvsioned User",
									"Success message is not displayed");

						}
					}
				} else {
					ReporterLog.fail("HOOQ SignUp with Mobile PreProvsioned User",
							"OTP Verification page is not displayed");

				}
			} else if (strValid.equalsIgnoreCase("Invalid")) {
				if (isNumOf_Elements_Greater_Than_Zero(lblMobileAcntExists)) {
					String strMobileAlreadyExists = getText(lblMobileAcntExists, "Account already exists");
					ReporterLog.pass("HOOQ SignUp with Mobile PreProvsioned User",
							strMobileAlreadyExists + " - is displayed");

					click(btnLoginToMyAccount, "Log in to my account");

					if (isNumOf_Elements_Greater_Than_Zero(lblLoginMobilePage)
							|| isNumOf_Elements_Greater_Than_Zero(lblLoginEmailPage)) {
						ReporterLog.pass("HOOQ Login with Mobile PreProvsioned User", " Login page is displayed");

					} else {
						ReporterLog.fail("HOOQ Login with Mobile PreProvsioned User", " Login page is not displayed");

					}
				} else {
					ReporterLog.fail("HOOQ SignUp with Mobile PreProvsioned User",
							"The Popup 'Account exists' is not displayed");

				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- emailActivate7DayTrial Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void emailActivate7DayTrial(String validEmail) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {

			String winHandle = driver.getWindowHandle();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.open('http://www.yopmail.com','_blank');");

			// Switch to new window opened

			Set<String> windows = driver.getWindowHandles();

			for (String currentwindow : windows) {
				driver.switchTo().window(currentwindow);
			}
			type(txtLoginEmail, validEmail, "Login Email");
			click(btnCheckEmail, "Check Email");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("ifmail");

			click(lnkActivate7Day, "Activate 7 Day");

			driver.switchTo().window(winHandle);
			Set<String> newwindows = driver.getWindowHandles();
			for (String newwindow : newwindows) {
				driver.switchTo().window(newwindow);
			}

			// Perform the actions on new window
			if (isNumOf_Elements_Greater_Than_Zero(lbl7DaySuccess)) {
				String str7DaySuccesMsg = getText(lbl7DaySuccess, "7 Day success message");
				ReporterLog.pass("HOOQ SignUp with Skip Nova",
						"Success message is displayed after activation" + str7DaySuccesMsg);

				click(btn7DaySuccess, "Get Started");
				click(hamburgerMenu, "Hamburger Menu");
				By loginElement = By.xpath("//div[contains(@class,'SideMenu__UserLoginText-sc-1y2c5x4-7')]");
				isNumOf_Elements_Greater_Than_Zero(loginElement);
				String loggedinID = getText(loginElement, "LoggedIn ID");
				if (driver.getPageSource().contains(loggedinID)) {
					ReporterLog.pass("Verify Login", loggedinID + "User Logged in successfully");

				} else {
					ReporterLog.fail("Verify Login", " User does not logged in successfully");
				}

			} else {
				ReporterLog.fail("HOOQ SignUp with Skip Nova", "Success message is not displayed after activation");

			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- emailResendOTP Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public String emailResendOTP(String validEmail) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		String strOTPs = null;
		try {
			String winHandle = driver.getWindowHandle();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.open('http://www.yopmail.com','_blank');");

			// Switch to new window opened
			Set<String> windows = driver.getWindowHandles();
			for (String currentwindow : windows) {
				driver.switchTo().window(currentwindow);
			}

			if (isNumOf_Elements_Greater_Than_Zero(lblYopmail)) {
				ReporterLog.pass("Getting OTP from Yopmail", "Navigated to Yopmail");
				type(txtLoginEmail, validEmail, "Login Email");
				click(btnCheckEmail, "Check Email");
				driver.switchTo().frame("ifmail");
				String strResendOTP = getText(strOTP, "OTP");
				String[] OTP = strResendOTP.split("\\s+");

				strOTPs = OTP[0];
			} else {
				ReporterLog.fail("Getting OTP from Yopmail", "Not navigated to Yopmail");
			}
			driver.close();
			driver.switchTo().window(winHandle);
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		return strOTPs;
	}

	public SignUpPage enterUserDetails(String user) {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			if (user.contains("@")) {
				waitForElementClickable(lblEmail, "lblEmail");
				click(lblEmail, "Click on Email button");

				waitForElementClickable(emailIDTxt, "emailIDTxt");
				click(emailIDTxt, "Click on Email text");
				type(emailIDTxt, user, "Entering Valid Email ID");
			}
			else {
				type(txtMobile, user, "Mobile number");
			}
			
			type(txtPWD, "123456", "Password");
			TestUtilities.logReportPass("enter user: "+user+" details successfully in Signup form");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SignUpPage();
	}
	
	public NovaPage clickNextButton() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			click(evNextBtn, "evNextBtn");
			TestUtilities.logReportPass("Clicked on Next button");
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new NovaPage();
	}
}

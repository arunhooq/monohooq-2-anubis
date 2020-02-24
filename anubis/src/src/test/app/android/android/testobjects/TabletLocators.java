package android.testobjects;

import org.openqa.selenium.By;

public class TabletLocators {
	
	//Content details page	
	public static By contentTitle = By.id("tv.hooq.android:id/txtAssetTitle");
	public static By watchList = By.id("tv.hooq.android:id/txtWatchList");
	public static By like = By.id("tv.hooq.android:id/txtRate");
	public static By signUpPopUp = By.id("tv.hooq.android:id/signUp");
	public static By mayBeLaterPopUp = By.id("tv.hooq.android:id/skip");
	public static By downloadMovie = By.id("tv.hooq.android:id/txtDownload");
	public static By downloadMovie1 = By.id("tv.hooq.android:id/downloadButton");
	public static By downloadMovie2 = By.id("tv.hooq.android:id/downloadIcon");
	public static By downloadTVShow1 = By.id("tv.hooq.android:id/button_download");
	public static By downloadTVShow2 = By.id("tv.hooq.android:id/download_icon");
	public static By actorsLabel = By.id("tv.hooq.android:id/txtActorLabel");
	public static By actorsList = By.id("tv.hooq.android:id/txtActorList");
	public static By categoryLabel = By.id("tv.hooq.android:id/txtCategoryLabel");
	public static By categoryList = By.id("tv.hooq.android:id/txtCategoryList");
	public static By directorLabel = By.id("tv.hooq.android:id/txtDirectoryLabel");
	public static By directorList = By.id("tv.hooq.android:id/txtDirectorList");
	public static By contentDescription = By.id("tv.hooq.android:id/txtDescription");
	public static By poster = By.id("tv.hooq.android:id/rlImgPoster");
	//public static By posterRibbon = By.id("tv.hooq.android:id/ribbon");
	public static By contentMovieDuration = By.id("tv.hooq.android:id/txtDuration");
	public static By contentEpisodeDuration = By.id("tv.hooq.android:id/duration");//tv.hooq.android:id/txtDuration
	public static By contentEpisode = By.id("tv.hooq.android:id/episode_name");
	public static By contentAudio = By.id("tv.hooq.android:id/txtAudio");
	public static By contentSubtitles = By.id("tv.hooq.android:id/txtSubtitle");
	public static By contentPlayButton = By.id("tv.hooq.android:id/txtPlay");
	public static By btnRedeemYourTckt = By.id("tv.hooq.android:id/txtTvodRedeem");
	public static By btnRentTrailer = By.id("tv.hooq.android:id/txtTrailer");
	public static By contentClose = By.id("tv.hooq.android:id/imgCloseAll");
	public static By contentSimilarTitleLabel = By.id("tv.hooq.android:id/txtSimilarTitileLabel");
	public static By contentEpisodeVIPButton = By.id("tv.hooq.android:id/button_vip");
	public static By seasonDropdown = By.id("tv.hooq.android:id/txtSeason");
	public static By tvShowEpisodePlayIcon = By.id("tv.hooq.android:id/play_icon");
	
	
	
	
	
	
	//ME Page Locators
	public static By downloads = By.xpath("//android.widget.TextView[@text='Downloads']");
	public static By watchLater = By.xpath("//android.widget.TextView[@text='Watch Later']");
	public static By favorites = By.xpath("//android.widget.TextView[@text='Favorites']");
	public static By favoritestab = By.xpath("//android.widget.TextView[@text='Favorite']");
	public static By favoriteswatchnow = By.xpath("//android.widget.TextView[@text='Add to favorites']");
	public static By favoriteswatchnowfavorited = By.xpath("//android.widget.TextView[@text='Favorited']");
	public static By favoritesme = By.xpath("//android.widget.TextView[@text='Favorites']");
	public static By history = By.xpath("//android.widget.TextView[@text='History']");
	public static By settings = By.xpath("//android.widget.TextView[@text='Settings']");
	public static By settingsthai = By.xpath("//android.widget.TextView[@text='ตั้งค่า']");
	public static By appdisplaylanguage = By.xpath("//android.widget.TextView[@text='App Display Language']");
	public static By appdisplaylanguagethai = By.xpath("//android.widget.TextView[@text='ตั้งค่า']");
	public static By audiolanguage = By.xpath("//android.widget.TextView[@text='Audio Language']");
	public static By subtitlelang = By.xpath("//android.widget.TextView[@text='Subtitle Language']");
	public static By playback = By.xpath("//android.widget.TextView[@text='Playback Quality']");
	public static By Downloadquality = By.xpath("//android.widget.TextView[@text='Download Quality']");
	public static By mobiledata = By.xpath("//android.widget.TextView[@text='Mobile Data Usage']");
	public static By DownloadqualityMed = By.xpath("//android.widget.CheckedTextView[@text='Medium']");
	public static By playbackauto = By.xpath("//android.widget.CheckedTextView[@text='Auto']");
	public static By playbackpopup = By.xpath("//android.widget.TextView[@text='Playback Quality']");
	public static By subtitleeng = By.xpath("//android.widget.CheckedTextView[@text='English']");
	public static By subtitlelang1 = By.xpath("//android.widget.TextView[@text='Subtitle Language']");
	public static By subscription = By.xpath("//android.widget.TextView[@text='Subscription']");
	public static By support = By.xpath("//android.widget.TextView[@text='Support']");
	public static By Rentalsme = By.xpath("//android.widget.TextView[@text='Rentals']");
	public static By premiumme = By.xpath("//android.widget.TextView[@text='Premium+']");
	public static By Rentalsmetitle = By.xpath("//android.widget.TextView[@text='PREMIUM+']");
	public static By Linktvheader = By.xpath("//android.widget.TextView[@text='LINK TV']");
	public static By bahasalanguage = By.xpath("//android.widget.CheckedTextView[@text='Bahasa Indonesia']");
	public static By Linktvtext = By.xpath("//android.widget.TextView[@text='Launch the HOOQ app on your TV.']");
	public static By indo = By.xpath("//android.widget.TextView[@text='Bahasa Indonesia']");
	public static By Linktvscantext = By.xpath("//android.widget.TextView[@text='Scan the QR code displayed on your TV to link it with your account.']");
	public static By settingsindo = By.xpath("//android.widget.TextView[@text='Pengaturan']");
	public static By Default = By.xpath("//android.widget.CheckedTextView[@text='Default']");
	public static By Linktvcodetext = By.xpath("//android.widget.TextView[@text='Please enter the 6 digit code displayed on your TV to link it with your account.']");
	public static By Rentalsticketsdesc = By.id("tv.hooq.android:id/ticketText");
	public static By logout = By.xpath("//android.widget.TextView[@text='Logout']");
	public static By loggedInAccount = By.id("tv.hooq.android:id/subtitle");
	
	public static By conformlogout = By.xpath("//android.widget.Button[@text='Logout']");
	public static By conformlogout1 = By.xpath("//android.widget.TextView[@text='Confirm']");
	public static By watchnowbutton = By.xpath("//android.widget.Button[@text='Watch now']");
	public static By videoTitlerem = By.id("tv.hooq.android:id/imagePortrait");
	public static By watchnowfav = By.id("tv.hooq.android:id/btnFavorite");
	public static By watchnowtitle = By.id("tv.hooq.android:id/spotLightTitle");
	public static By favclick = By.id("tv.hooq.android:id/imagePortrait");
	public static By favclickplay = By.id("tv.hooq.android:id/fabbutton_ring");
	public static By rentplay = By.id("tv.hooq.android:id/playButton");
	public static By favclickplay1 = By.id("tv.hooq.android:id/watchContainer");
	public static By Rentalsemptyicon = By.id("tv.hooq.android:id/emptyStateIcon");
	public static By SettingsBlock = By.id("tv.hooq.android:id/list");
	public static By Rentalsemptydesc = By.xpath("//android.widget.Button[@text='Explore rental movies']");
	public static By Cancel = By.id("android:id/button2");
	public static By Watchnowtitle = By.id("tv.hooq.android:id/pageMakerContainerSpotLight");
	public static By Support = By.xpath("//android.widget.TextView[@text='Support']");
	public static By aboutus = By.xpath("//android.widget.TextView[@text='About Us']");
	public static By aboutuscont = By.xpath("//android.widget.TextView[@content-desc='Just press Play.']");
	public static By englishlanguage = By.xpath("//android.widget.CheckedTextView[@text='English (USA)']");
	public static By aboutus1 = By.xpath("//android.widget.TextView[@text='ABOUT US']");
	public static By aboutusback = By.id("tv.hooq.android:id/btnUp");
	public static By opensrc = By.xpath("//android.widget.TextView[@text='Open Source Licences']");
	public static By aboutusback1 = By.id("tv.hooq.android:id/backInMainActivity");
	public static By Debitcarrier = By.xpath("//android.widget.TextView[@text='DebitCardPaid - 7 Days of HOOQ']");
	public static By Debitcarrier30 = By.xpath("//android.widget.TextView[@text='DebitCardPaid - 30 Days of HOOQ']");
	public static By Debitcarrier90 = By.xpath("//android.widget.TextView[@text='DebitCardPaid - 90 Days of HOOQ']");
	public static By Debitcarrier180 = By.xpath("//android.widget.TextView[@text='DebitCardPaid - 180 Days of HOOQ']");
	public static By Debitcarrier360 = By.xpath("//android.widget.TextView[@text='DebitCardPaid - 360 Days of HOOQ']");
	public static By Netbanking = By.xpath("//android.widget.TextView[@text='NetbankingPaid - 7 Days of HOOQ']");
	public static By Netbanking30 = By.xpath("//android.widget.TextView[@text='NetbankingPaid - 30 Days of HOOQ']");
	public static By Netbanking90 = By.xpath("//android.widget.TextView[@text='NetbankingPaid - 90 Days of HOOQ']");
	public static By Netbanking180 = By.xpath("//android.widget.TextView[@text='NetbankingPaid - 180 Days of HOOQ']");
	public static By Netbanking360 = By.xpath("//android.widget.TextView[@text='NetbankingPaid - 360 Days of HOOQ']");
	public static By Paytm = By.xpath("//android.widget.TextView[@text='PayTM - 7 Days of HOOQ']");
	public static By Paytm30 = By.xpath("//android.widget.TextView[@text='PayTM - 30 Days of HOOQ']");
	public static By Paytm90 = By.xpath("//android.widget.TextView[@text='PayTM - 90 Days of HOOQ']");
	public static By Paytm180 = By.xpath("//android.widget.TextView[@text='PayTM - 180 Days of HOOQ']");
	public static By Paytm360 = By.xpath("//android.widget.TextView[@text='PayTM - 360 Days of HOOQ']");
	public static By Paytm1 = By.xpath("//android.widget.TextView[@text='PayTM - 30 Days of HOOQ (recurring)']");
	public static By Paytm2 = By.xpath("//android.widget.TextView[@text='PayTM - 90 Days of HOOQ (recurring)']");
	public static By Paytm3 = By.xpath("//android.widget.TextView[@text='PayTM - 180 Days of HOOQ (recurring)']");
	public static By Paytm4 = By.xpath("//android.widget.TextView[@text='PayTM - 360 Days of HOOQ (recurring)']");
	public static By creditcard = By.xpath("//android.widget.TextView[@text='CreditCardPaid - 30 Days of HOOQ (recurring)']");
	public static By creditcard1 = By.xpath("//android.widget.TextView[@text='CreditCardPaid - 90 Days of HOOQ (recurring)']");
	public static By creditcard2 = By.xpath("//android.widget.TextView[@text='CreditCardPaid - 180 Days of HOOQ (recurring)']");
	public static By creditcard3 = By.xpath("//android.widget.TextView[@text='CreditCardPaid - 360 Days of HOOQ (recurring)']");
	public static By paymentback = By.id("tv.hooq.android:id/backInMainActivity");
	public static By faq = By.xpath("//android.widget.TextView[@text='FAQ']");
	public static By startyourtrial = By.xpath("//android.widget.Button[@text='Start your free trial now']");
	public static By thailanguage = By.xpath("//android.widget.CheckedTextView[@text='Thai']");
	public static By thaisetting = By.id("tv.hooq.android:id/profileBtnSettings");
	public static By CCrec = By.xpath("//android.widget.Image[@text='logo_credit']");
	public static By subsrec = By.xpath("//android.widget.TextView[@text='Your subscription renews in']");
	public static By subsrecautorenew = By.xpath("//android.widget.TextView[@text='Auto-renew:']");
	public static By subsrecautorenew1 = By.xpath("//android.widget.TextView[@text='ON']");
	public static By subsrecsubmit = By.xpath("//android.widget.Button[@text='Submit']");
	public static By vouchercode = By.id("tv.hooq.android:id/voucherCode");
	public static By DebitINR = By.xpath("//android.view.View[@text='From INR89']");
	public static By startsubscribe = By.xpath("//android.widget.Button[@text='Subscribe now']");
	public static By subscribeMOL = By.xpath("//android.widget.Image[@text='logo_zgold']");
	public static By molprepaid = By.xpath("//android.view.View[@text='Prepaid']");
	public static By mol1month = By.xpath("//android.view.View[@text='1 Month Subscription']");
	public static By molsubs = By.xpath("//android.widget.TextView[@text='Downloads']");
	public static By subscribeSG = By.xpath("//android.widget.Button[@text='Start your free trial now']");
	public static By subscriptionheader = By.xpath("//android.widget.TextView[@text='SUBSCRIPTION']");
	public static By Debitmonthsubs = By.xpath("//android.view.View[@text='1 Month Subscription']");
	public static By netbankingselect = By.xpath("//android.widget.Spinner[@text='Please select your Bank']");
	public static By Debitmonthinr = By.xpath("//android.view.View[@text='INR89']");
	public static By Debitmonthyear = By.xpath("//android.view.View[@text='1 Year Subscription']");
	public static By selectbank = By.xpath("//android.view.View[@text='Select your bank']");
	public static By Debityearinr = By.xpath("//android.view.View[@text='INR450']");
	public static By netbanktext = By.xpath("//android.view.View[@text='NET BANKING PAYMENT']");
	public static By Phonebil = By.xpath("//android.widget.Image[@text='logo_phonebill']");
	public static By Phonebillpre = By.xpath("//android.view.View[@text='Prepaid']");
	public static By Phonebillsubs = By.xpath("//android.view.View[@text='PREPAID SUBSCRIPTION']");
	public static By Phonesubs = By.xpath("//android.widget.Button[@text='Subscribe now']");
	public static By subsdebit = By.xpath("//android.widget.Image[@text='logo_debit']");
	public static By subscancel = By.id("tv.hooq.android:id/terminationContainer");
	public static By netbanking = By.xpath("//android.widget.Image[@text='logo_netbanking']");
	public static By Phonepayment = By.xpath("//android.view.View[@text='PAYMENT']");
	public static By Phonesubcont = By.xpath("//android.view.View[@text='Continue']");
	public static By low = By.xpath("//android.widget.CheckedTextView[@text='Low']");
	public static By medium = By.xpath("//android.widget.CheckedTextView[@text='Medium']");
	public static By high = By.xpath("//android.widget.CheckedTextView[@text='High']");
	public static By paytmback = By.id("tv.hooq.android:id/btnUp");
	public static By auto = By.xpath("//android.widget.CheckedTextView[@text='Auto']");
	public static By Cashcard = By.xpath("//android.widget.TextView[@text='CashCardPaid - 7 Days of HOOQ']");
	public static By Cashcard30 = By.xpath("//android.widget.TextView[@text='CashCardPaid - 30 Days of HOOQ']");
	public static By Cashcard90 = By.xpath("//android.widget.TextView[@text='CashCardPaid - 90 Days of HOOQ']");
	public static By Cashcard180 = By.xpath("//android.widget.TextView[@text='CashCardPaid - 180 Days of HOOQ']");
	public static By Cashcard360 = By.xpath("//android.widget.TextView[@text='CashCardPaid - 360 Days of HOOQ']");
	public static By tos = By.xpath("//android.widget.TextView[@text='Terms of Use']");
	public static By tos1 = By.xpath("//android.widget.TextView[@text='TERMS OF USE']");
	public static By contactus = By.xpath("//android.widget.TextView[@text='Contact Us']");
	public static By Opensrclicense = By.xpath("//android.widget.TextView[@text='Open source licences']");
	public static By Opensrclicense1 = By.xpath("//android.widget.TextView[@text='LICENSE DETAILS']");
	public static By Appv = By.id("tv.hooq.android:id/detail");
	public static By Autorenewon = By.id("tv.hooq.android:id/autoRenew");
	public static By Privacypolicy = By.xpath("//android.widget.TextView[@text='Privacy Policy']");
	public static By downcancel = By.id("tv.hooq.android:id/btnDelete");
	public static By Privacypolicy1 = By.xpath("//android.widget.TextView[@text='PRIVACY POLICY']");
	public static By Bill180desc = By.xpath("//android.widget.TextView[@text='Get billed once every 180 days and cancel at any time.']");
	public static By Bill360desc = By.xpath("//android.widget.TextView[@text='Get billed once every 360 days and cancel at any time.']");
	public static By meticket = By.id("tv.hooq.android:id/tickets");
	public static By Selectfav = By.id("tv.hooq.android:id/imagePortrait"); 
	public static By Autorenewoff = By.id("tv.hooq.android:id/firstRow");
	public static By mesect = By.id("tv.hooq.android:id/iv_me_btn");
	public static By addwatchlater = By.xpath("//android.widget.TextView[@text='Watch Later']");
	public static By addfav = By.xpath("//android.widget.TextView[@text='Favorites']");
	public static By Downloadoption =  By.xpath("//android.widget.TextView[@text='Downloads']");
	public static By videoTitle(String title){
		return By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/assetTitle'][@text='"+title+"']");
	}
	public static By editButton = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/btnCancel']");
	//public static By settings = By.xpath("//android.widget.TextView[@text='Settings']");
	public static By removeButton = By.xpath("//android.widget.TextView[@text='Remove']");
	public static By deleteButton = By.xpath("//android.widget.Button[@resource-id='tv.hooq.android:id/confirm']");
	public static By deleteButton1 = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/okay']");
	public static By emptyStateIcon = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/emptyStateIcon']");
	public static By emptyStateTitle =By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/emptyStateTitle']");
	public static By emptySateDesc = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/emptyStateDesc']");
	public static By emptyStateButton =By.xpath("//android.widget.Button[@resource-id='tv.hooq.android:id/btnAction'][@text='Browse']");
	public static By favoritePageLoader = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/listview']/android.widget.RelativeLayout");
	public static By expiryDate = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/expires']"); 
	public static By terminationDesc = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/terminateDesc']");
	public static By browseBtnInDownLoadSection = By.xpath("//android.widget.Button[@text='Browse']");
	public static By firstShowInDownloadSection = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/imagePortrait']");
	public static By selectplan = By.xpath("//android.widget.Button[@resource-id='tv.hooq.android:id/selectPlan']");
	public static By PaymentTitle = By.xpath("//android.widget.TextView[@text='PAYMENT METHOD']");
	public static By Paymentmtddesc = By.xpath("//android.widget.TextView[@text='7 Days - Unlimited Movies and TV Shows']");
	public static By Paymentmtddesc30 = By.xpath("//android.widget.TextView[@text='30 Days - Unlimited Movies and TV Shows']");
	public static By Paymentmtddesc90 = By.xpath("//android.widget.TextView[@text='90 Days - Unlimited Movies and TV Shows']");
	public static By Paymentmtddesc180 = By.xpath("//android.widget.TextView[@text='180 Days - Unlimited Movies and TV Shows']");
	public static By Paymentmtddesc360 = By.xpath("//android.widget.TextView[@text='360 Days - Unlimited Movies and TV Shows']");
	public static By PaymentINR = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/price']");
	public static By PaymentINR1 = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/price']");
	public static By PaymentINR2 = By.xpath("//android.widget.TextView[@text='INR 1700']");
	public static By CarrierRadiobtn = By.xpath("//android.widget.RadioButton[@resource-id='tv.hooq.android:id/radioButton']");
	public static By Carrierbilling = By.xpath("//android.widget.TextView[@text='Carrier Billing - 30 Days of HOOQ']");
	public static By CarrierRadiobtntab = By.xpath("//android.widget.RadioButton[@resource-id='tv.hooq.android:id/radioButton']");
	public static By findShows = By.xpath("//android.widget.TextView[@text='TVSHOW']");
	public static By Makepaymentbtn = By.xpath("//android.widget.Button[@resource-id='tv.hooq.android:id/submitPayment']");
	public static By Carrierbillmthd = By.xpath("//android.widget.EditText[@resource-id='mno']");
	public static By Paymentmthd = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/toolbarTitle']");
	public static By lapsesubs = By.xpath("//android.widget.TextView[@text='Enjoying HOOQ?']");
	public static By setcancel = By.xpath("//android.widget.Button[@text='Cancel']");
	public static By managedown = By.xpath("//android.widget.TextView[@text='Manage Downloads']");
	public static By WatchList = By.xpath("//android.widget.TextView[@text='Watchlist']");
	public static By WatchList1 = By.id("tv.hooq.android:id/profileBtnWatchLater");
	public static By editButton1 = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/btnEdit']");
	public static By rentPlay1=By.id("tv.hooq.android:id/txtPlay");
	public static By editdel = By.id("tv.hooq.android:id/portrait");
	
	//Login Page Locators
	public static By Login = By.id("tv.hooq.android:id/accountLogin");   //xpath("//*[@resource-id,'tv.hooq.android:id/accountLogin']");
	public static By emailTab = By.id("tv.hooq.android:id/btnEmail");
	public static By cancelEmailSelection = By.id("tv.hooq.android:id/button1");
	public static By email = By.id("text1");
	public static By email2 = By.id("tv.hooq.android:id/emailInputLayout");
	public static By emailnight = By.id("android:id/text1");
	public static By email1 = By.id("tv.hooq.android:id/mobileInput");
	public static By mobile = By.id("tv.hooq.android:id/mobileText");
	public static By lnkpassword = By.id("tv.hooq.android:id/password");
	public static By pwdPageHeader = By.xpath("//*[@text='ENTER HOOQ PASSWORD'][@resource-id='tv.hooq.android:id/title']");
	public static By txtPassword = By.id("tv.hooq.android:id/input");
	public static By btnPwdOkay = By.id("tv.hooq.android:id/confirm");
	public static By mobiledrop = By.id("android:id/text1");
	public static By emailDone = By.id("tv.hooq.android:id/emailDone");
	public static By LoginLogo = By.name("Login"); 
	public static By mobalert = By.xpath("//android.widget.Button[@text='Yes']");
	public static By preprod =  By.xpath("//android.widget.TextView[@text='preprod']");
	public static By mobalert1 = By.xpath("//android.widget.TextView[@text='+91']");
	
	public static By Region = By.xpath("//*[@resource-id='tv.hooq.android:id/regionSpinner']");  
	public static By NameOfRegion(String region)
	{
		return 	By.xpath("//*[@text='"+region+"'][@resource-id='android:id/text1'][@class='android.widget.TextView']");
	}
	public static By logOut = By.xpath("//android.widget.TextView[@text='Log out']");
	public static By logOutConfirm = By.xpath("//android.widget.Button[@resource-id='tv.hooq.android:id/confirm']");
	public static By Api1 = By.xpath("//*[@resource-id='tv.hooq.android:id/apiSpinner']");
	public static By NameofAPI(String api)
	{
		
		return By.xpath("//*[@text='"+api+"'][@resource-id='android:id/text1'][@class='android.widget.TextView']");
	}
	
	public static By nightenvi =  By.xpath("//android.widget.TextView[@text='nightly']");
	public static By Api2 = By.xpath("//*[@resource-id='tv.hooq.android:id/apiSpinner']");
	public static By NameofAPI1(String api1)
	{
		
		return By.xpath("//*[@text='"+api1+"'][@resource-id='android:id/text1'][@class='android.widget.TextView']");
	}
	
	public static By buildNo = By.id("tv.hooq.android:id/detail");
	public static By codePopMsg = By.id("tv.hooq.android:id/content");
	public static By CodeRedemSuccess = By.id("tv.hooq.android:id/content");
	public static By okPopMsg = By.xpath("//android.widget.Button[@text='Okay']");
	public static By addnewEmailButton = By.xpath("//*[@text='+ add new email'][@resource-id='android:id/text1']");
	public static By addnewemail = By.id("emailInput");
	public static By addnewemail1 = By.id("tv.hooq.android:id/emailInput");
	public static By addnewmobile = By.id("mobileInput");
	public static By ccdetailnumber = By.id("cardnumber");
	public static By addnewemailtvod = By.id("tv.hooq.android:id/emailInput");
	public static By MeProf = By.id("tv.hooq.android:id/iv_me_btn");
	public static By MeProft = By.id("tv.hooq.android:id/imgProfile");
	public static By Subscrip = By.xpath("//android.widget.TextView[@text='Subscription']");
	public static By Subscrip1 = By.xpath("//android.widget.TextView[@text='Subscription']");
	public static By vocherTextBox = By.id("tv.hooq.android:id/voucherCode");
	public static By signup = By.id("tv.hooq.android:id/joinFree");
	public static By vocherSubmit = By.id("tv.hooq.android:id/redeem");
	public static By ManageS = By.xpath("//android.widget.Button[@text='Manage subscription']");
	public static By SubscribeS = By.xpath("//android.widget.Button[@text='Subscribe Now']");
	public static By Sts = By.xpath("//android.widget.Button[@text='Skip this step']");
	public static By Skp = By.xpath("//android.widget.Button[@text='Skip']");
	public static By Newtohooq = By.xpath("//android.widget.Button[@text='New to HOOQ? Sign up here']");
	public static By Settings = By.xpath("//android.widget.TextView[@text='Settings']");
	public static By Applang = By.xpath("//android.widget.TextView[@text='App Display Language']");
	public static By Search = By.xpath("//android.widget.TextView[@text='BROWSE']");
	public static By SearchContainer = By.id("tv.hooq.android:id/searchContainer");
	public static By SuggestedText = By.xpath("//android.widget.TextView[@text='The Big C']");
	public static By Suggesteditem = By.xpath("//android.widget.TextView[@text='The Big C']");
	public static By btnPlay = By.id("tv.hooq.android:id/btnPlay");
	//public static By emptyStateIcon = By.id("tv.hooq.android:id/emptyStateIcon"); 
	public static By AnonUser = By.xpath("//android.widget.TextView[@text='HOWDY STRANGER!']");
	public static By stateDescrip = By.id("tv.hooq.android:id/emptyStateDesc");
	public static By StartBtn = By.xpath("//android.widget.Button[@text='Get started']");
	public static By btnUp = By.id("tv.hooq.android:id/btnUp");
	public static By SubscripLgdone = By.id("tv.hooq.android:id/emailDone");
	public static By SubscripLgemail = By.id("tv.hooq.android:id/emailSpinner");
	public static By SubscripLgLogo = By.id("tv.hooq.android:id/fragmentTitle");
	public static By Watchnw = By.id("tv.hooq.android:id/spotLightWatchNowBtn");
	public static By Days(String plan)
	{
		return By.xpath("//android.widget.TextView[@text='"+plan+"']");
	}
	
	//plan selector locators
	public static By planTitle = By.xpath("//android.support.v4.view.ViewPager//android.widget.TextView[@resource-id='tv.hooq.android:id/planTitle']");
	public static By planTitle30 = By.xpath("//android.support.v4.view.ViewPager//android.widget.TextView[@text='30 Days']");
	public static By planTitle90 = By.xpath("//android.support.v4.view.ViewPager//android.widget.TextView[@text='90 Days']");
	public static By planTitle180 = By.xpath("//android.support.v4.view.ViewPager//android.widget.TextView[@text='180 Days']");
	public static By planSubtitle = By.id("tv.hooq.android:id/planSubtitle");
	public static By spotlight = By.id("tv.hooq.android:id/textTitleType");
	public static By planTitlewatch = By.xpath("//android.support.v4.view.ViewPager//android.support.v4.view.ViewPager[@resource-id='tv.hooq.android:id/spotLightImage']");
	public static By autoRenewDesc = By.id("tv.hooq.android:id/lblAutoRenewDesc");
	public static By autoRenewDesc(String price){
		return By.xpath("//android.widget.TextView[@text='THB 589']/following-sibling::android.widget.RelativeLayout//android.widget.TextView[@resource-id='tv.hooq.android:id/lblAutoRenewDesc']");
	}
	public static By planPrice(String price)
	{
		return  By.xpath("//android.widget.TextView[@text='"+price+"']");
	}
	
	public static By autoRenew = By.id("tv.hooq.android:id/autoRenew");
	public static By autoRenew(String price){
		return By.xpath("//*[@text = '"+price+"']/following-sibling::android.widget.RelativeLayout/android.widget.CheckBox[@resource-id='tv.hooq.android:id/autoRenew']");
	}
	public static By SelectPlan = By.id("tv.hooq.android:id/selectPlan");
	public static By planDescription = By.id("tv.hooq.android:id/planDesc");
	
	
	//makepayment
	public static By paymentMethodMakePayment = By.xpath("//android.widget.Button[@text='Make Payment']");
	public static By paymentSelector(String payment)
	{
		return By.xpath("//android.widget.TextView[@text='"+payment+"']");
	}
	public static By globeAISMobileNumber = By.xpath("//android.widget.EditText[@resource-id='mobilenumber']");
	public static By mobileNumber = By.xpath("//*[@resource-id='mno']");
	public static By carrierSelector = By.xpath("//*[@resource-id='oprtr_list']");
	public static By carrierOption(String carrier)
	{
		 return By.xpath("//android.widget.CheckedTextView[@text='"+carrier+"']");
	}
	public static By makePayment = By.xpath("//*[@content-desc='Make Payment']");
	public static By cardNumber = By.xpath("//*[@resource-id='cardnumber']");
	public static By nameOfCard = By.xpath("//*[@resource-id='nameoncard']");
	//public static By expiryDate = By.xpath("//*[@resource-id='expiry_date']");
	public static By cvv = By.xpath("//*[@resource-id='cvv']");
	public static By cashCard = By.xpath("//*[@resource-id='cashcardbank']");
	public static By cashCardOption(String cashCard){
		return By.xpath("//android.widget.CheckedTextView[@text='"+cashCard+"']");
	}
	public static By netBanking = By.xpath("//*[@resource-id='netbank']");
	public static By netBankingOption(String bank){
		return By.xpath("//android.widget.CheckedTextView[@text='"+bank+"']");
	}
	public static By errorMessage(String error){
		return By.xpath("//android.view.View[@content-desc='"+error+"']");
	}
	
	public static By inpPaymentMethod(String billing)
	{
		return By.xpath("//android.widget.TextView[@text='"+billing+"']");
	}
	//public static By Submit = By.id("tv.hooq.android:id/paywallSubscribe");
	 public static By Close = By.id("tv.hooq.android:id/btnClose");
	 public static By  anonbtnSearch = By.id("tv.hooq.android:id/txtSearch");
	 
	 public static By  anonbtnSearchnew = By.id("tv.hooq.android:id/btnSearch");
	 //public static By  Getstr = By.id("tv.hooq.android:id/title");
	 public static By  Lapsetrial = By.id("tv.hooq.android:id/ottTopBanner");
	 public static By  Lapsecw = By.xpath("//android.widget.TextView[@text='Continue Watching']");
	 public static By  PaytmMob = By.xpath("//android.view.View[@text='Registered Paytm Mobile Number']");
	 public static By  settingsdatausage = By.xpath("//android.widget.TextView[@text='Mobile Data Usage']");
	 public static By  CCDesc = By.xpath("//android.view.View[@text='30 Days HOOQ All Access']");
	 public static By  settingssubtitle = By.xpath("//android.widget.TextView[@text='Subtitle Language']");
	 public static By  settingssubtitleheader = By.xpath("//android.widget.TextView[@text='Subtitle Language']");
	 public static By Paytmlogo = By.xpath("//android.widget.Image[@text='logo_paytm']");
	 public static By  CCRecon = By.xpath("//android.view.View[@text='Auto-renew: ON']");
	 public static By  Cardno = By.xpath("//android.view.View[@text='Card Number']");
	 public static By  Cardname = By.xpath("//android.view.View[@text='Name on Card']");
	 public static By  Debitcancel = By.xpath("//android.widget.Button[@text='Cancel']");
	 public static By PaytmDesc = By.xpath("//android.view.View[@text='HOOQ SPECIAL OFFER']");
	 public static By  Debitpayment = By.xpath("//android.widget.Button[@text='Make Payment']");
	 public static By logininfo = By.id("tv.hooq.android:id/subtitle");
	 public static By Paytmsubscribe = By.xpath("//android.widget.Button[@text='Subscribe now']");
	 public static By  Cardexpiry = By.xpath("//android.view.View[@text='Expiry Date']");
	 public static By  Cardcvv = By.xpath("//android.view.View[@text='CVV']");
	 public static By  Lapsetrialgo = By.xpath("//android.widget.TextView[@text='Let's Go!']");
	 public static By  Signuptitle = By.id("tv.hooq.android:id/title");
	 public static By anonhooqClick = By.id("tv.hooq.android:id/collectionPoster");
	 public static By anonassetTitle = By.id("tv.hooq.android:id/imagePortrait");
	 public static By anonhooqtabletClick = By.id("tv.hooq.android:id/collectionPosterLeft");
	 public static By anonassetTitletablet = By.id("tv.hooq.android:id/imageLandscape");
	 public static By playButton = By.id("tv.hooq.android:id/fabbutton_ring");
	 public static By playButtonv = By.id("tv.hooq.android:id/btn_play");
	 public static By playButton1 = By.id("tv.hooq.android:id/txtPlay");
	 public static By playtrailer = By.id("tv.hooq.android:id/txtTrailer");
	 public static By favtvod = By.id("tv.hooq.android:id/rental");
	 public static By phonebillmonth = By.xpath("//android.view.View[@text='1 Month Subscription']");
	 public static By phonebillautooff = By.xpath("//android.view.View[@text='Auto-renew: OFF']");
	 public static By phonebillprice = By.xpath("//android.view.View[@text='INR 89.00']");
	 public static By lapseott = By.id("tv.hooq.android:id/ottTopBannerButton");
	 public static By phonebillmeth = By.xpath("//android.view.View[@text='MOBILE BILL PAYMENT']");
	 public static By phonemob = By.xpath("//android.view.View[@text='Mobile Number']");
	 public static By lapseclaim = By.xpath("//android.widget.TextView[@text='Claim Now']");
	 public static By lapseott1 = By.id("tv.hooq.android:id/title");
	 public static By downloadbtn = By.id("tv.hooq.android:id/btn_download");
	 public static By downloadbtntv = By.id("tv.hooq.android:id/secondaryProgress");
	 public static By playButtonTab = By.id("tv.hooq.android:id/watchTxt");
	 public static By continuewat = By.id("tv.hooq.android:id/imagePortrait");
	 public static By playButtonep = By.id("tv.hooq.android:id/play");
	 //public static By Close = (MobileElement) driver.findElementById("tv.hooq.android:id/close");
	 public static By anonImage = By.id("tv.hooq.android:id/image");
	 public static By signupmobdesc = By.id("tv.hooq.android:id/mobileText");
	 public static By signupskip = By.id("tv.hooq.android:id/skip");
	 public static By signupconformemail = By.xpath("//android.widget.TextView[@text='Confirm your email']");
	 public static By signupconformdesc = By.xpath("//android.widget.TextView[@text='You're almost done! One last step.']");
	 public static By signupalreadylogin = By.id("tv.hooq.android:id/login");
	 public static By signupemailtext = By.id("android:id/text1");
	 public static By starttrial = By.xpath("//android.widget.Button[@text='Start your trial now']");
		public static By anonGstarted = By.xpath("//android.widget.TextView[@text='Get Started']");
		public static By Mobilecountrycode = By.xpath("//android.widget.TextView[@text='+91']");
		public static By Signuptoc = By.xpath("//android.widget.TextView[@text='By signing up, I agree to HOOQ's']");
		public static By signupmobileinput = By.id("tv.hooq.android:id/mobileInput");
		 public static By signupTCPP = By.id("tv.hooq.android:id/tos");
		public static By anonDescription = By.id("tv.hooq.android:id/description");
		public static By anonDescription1 = By.id("tv.hooq.android:id/content");
		public static By anonDescription2 = By.id("tv.hooq.android:id/title");
		public static By signupdone = By.id("tv.hooq.android:id/done");
		public static By anonpaywallBtn = By.id("tv.hooq.android:id/paywallButton");
		public static By signuppage = By.id("tv.hooq.android:id/title");
		public static By content_poster = By.id("tv.hooq.android:id/imgPoster");
		public static By searchresult = By.id("tv.hooq.android:id/matchedTitlesSection");
		public static By content_detail = By.id("tv.hooq.android:id/txtDescription");
		public static By searchresult1 = By.id("tv.hooq.android:id/matchedTitlesLabel");
		public static By searchresult2 = By.id("tv.hooq.android:id/seeAllButton");
		public static By content_duration = By.id("tv.hooq.android:id/txtDuration");
		public static By browsedetail = By.xpath("//android.widget.TextView[@text='Action & Adventure']");
		public static By getstartednew = By.id("tv.hooq.android:id/okay");
		public static By getStartedClose = By.id("tv.hooq.android:id/close");
		public static By anonskip = By.xpath("//android.widget.Button[@resource-id='tv.hooq.android:id/skipLogin']");
		public static By  anonFabButton1 = By.id("tv.hooq.android:id/fabbutton_circle");
		public static By btnBrowse = By.id("tv.hooq.android:id/txtSearch");
		public static By btnBrowsenew = By.id("tv.hooq.android:id/btnSearch");
		public static By btnmovies = By.id("tv.hooq.android:id/btnMovies");
		public static By btntvshows = By.id("tv.hooq.android:id/btnTvShows");
		public static By btnBrowsedesc = By.id("tv.hooq.android:id/description");
		public static By continuewatchposter = By.id("tv.hooq.android:id/imageContinueWatchingPoster");
		public static By continuewatchindicator = By.id("tv.hooq.android:id/pager_indicator_container");
		public static By pickurplan = By.id("tv.hooq.android:id/cardContainer");
		public static By btnDetails = By.xpath("//android.widget.TextView[@text='DETAILS']");
	    public static By lapsedhooqClick = By.id("tv.hooq.android:id/collectionPoster");
	    public static By lapsedhooqClickTab = By.id("tv.hooq.android:id/collectionPosterLeft");
		public static By lapsedAssetTitle = By.id("tv.hooq.android:id/imagePortrait");
		public static By lapsedAssetTitleTab = By.id("tv.hooq.android:id/imagePortrait");
		public static By lapsedClose = By.id("tv.hooq.android:id/close");
		public static By lapsedImage = By.id("tv.hooq.android:id/image");
		public static By lapsedGstarted = By.xpath("//android.widget.TextView[@text='Subscribe to HOOQ']");
		public static By Othersalsowatched = By.xpath("//android.widget.TextView[@text='OTHERS ALSO WATCHED']");
		public static By youmayalso = By.xpath("//android.widget.TextView[@text='Similar titles']");
		public static By lapsedDescription = By.id("tv.hooq.android:id/description");
		public static By lapsedDescription1 = By.id("tv.hooq.android:id/content");
		public static By lapsedpaywallBtn = By.xpath("//android.widget.Button[@text='Subscribe']");
		public static By lapsedpaywallBtn1 = By.xpath("//android.widget.TextView[@text='Subscribe']");
		public static By lapseddays7 = By.id("tv.hooq.android:id/planTitle");
		public static By lapsedbtnSelect = By.xpath("//android.widget.Button[@text='Select Plan']");
		public static By  lapsedFabButton1 = By.id("tv.hooq.android:id/fabbutton_circle");
		
		public static By videoView = By.xpath("//android.widget.VideoView[@resource-id='tv.hooq.android:id/videoView']");
		
		public static By activebtnSearch = By.id("tv.hooq.android:id/txtSearch");
		
		public static By activehooqClick = By.id("tv.hooq.android:id/collectionPoster");
		public static By activehooqClick1 = By.id("tv.hooq.android:id/imagePortrait");
		public static By activehooqClickTab = By.id("tv.hooq.android:id/collectionPosterLeft");
		//public static By genreList = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/genreList']");
		public static By genreListView = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/browseGenreListView']");
		public static By genreListView1 = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/browseTitlesListView']");
		
		public static By activeAssetTitle = By.xpath("//android.widget.TextView[@index='1' and @resource-id = 'tv.hooq.android:id/assetTitle']");
		
		
		public static By playerLoadingIcon = By.xpath("//android.widget.ProgressBar[@resource-id='tv.hooq.android:id/loading']");
		public static By activeClose = By.id("tv.hooq.android:id/btnClose");
		public static By activebandWidthIndi = By.id("tv.hooq.android:id/bandwidthIndicator");
		public static By activePlayTime = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/playTime']");
		//String plt = PlayTime.getText();
		//System.out.println("Start Time:"+plt);
		public static By activePlayPause = By.id("tv.hooq.android:id/iconPlayPause");
//		Thread
		//public static By activescreenview = By.className("android.view.View");

		public static By activePlayTime1 = By.id("tv.hooq.android:id/playTime");
		public static By freebtnSearch = By.id("tv.hooq.android:id/txtSearch");
		
		public static By freehooqClick = By.id("tv.hooq.android:id/collectionPoster");
		//public static By continuewatching = By.id("tv.hooq.android:id/imageContinueWatchingPoster");
		public static By freeAssetTitle = By.id("tv.hooq.android:id/imagePortrait");
		
		
		
		public static By freeClose = By.id("tv.hooq.android:id/btnClose");
		public static By freebandWidthIndi = By.id("tv.hooq.android:id/bandwidthIndicator");
		
		public static By freePlayTime = By.id("tv.hooq.android:id/playTime");
		
		public static By freePlayPause = By.id("tv.hooq.android:id/iconPlayPause");
		
		public static By freescreenview = By.className("android.view.View");
		

		public static By freePlayTime1 = By.id("tv.hooq.android:id/playTime");
		
		//public static By lapsedSubScripText = By.xpath("//*[@resource-id='tv.hooq.android:id/title']");
		public static By lapsedSubScripText = By.xpath("//*[@resource-id='tv.hooq.android:id/subscribeMsg']");
		public static By lapsedDaysLeft = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/daysLeft']");
		public static By lapsedDescp = By.id("tv.hooq.android:id/expires");
		public static By lapsedSubScripBtn = By.xpath("//android.widget.Button[@text='Subscribe Now']");
		public static By lapsedVoucherCodeBox = By.id("tv.hooq.android:id/voucherCode");
		public static By lapsedRedeemBtn = By.id("tv.hooq.android:id/redeem");
		//public static By lapsedbtnUp = By.id("tv.hooq.android:id/btnUp");
		public static By lapsedbtnUp = By.id("tv.hooq.android:id/backInMainActivity");
		public static By anonemptyStateIcon = By.id("tv.hooq.android:id/emptyStateIcon"); 
		 
		public static By anAnonUser = By.xpath("//android.widget.TextView[@text='HOWDY STRANGER!']");
		public static By anonstateDescrip = By.id("tv.hooq.android:id/emptyStateDesc");
		public static By anonStartBtn = By.xpath("//android.widget.Button[@text='Get started']");
		public static By anonbtnUp = By.id("tv.hooq.android:id/backInMainActivity");
		                                     
        public static By anonMeProf1=By.id("tv.hooq.android:id/btnProfile");

        public static By anonSubscripLgdone = By.id("tv.hooq.android:id/emailDone");
		public static By anonSubscripLgemail = By.id("tv.hooq.android:id/emailSpinner");
		public static By anonSubscripLgLogo = By.id("tv.hooq.android:id/fragmentTitle");
		public static By activeSubScripText2 = By.id("tv.hooq.android:id/startingSentence");
		public static By activeSubScripText = By.id("tv.hooq.android:id/expires");
		////*[@resourceid='tv.hooq.android:id/daysLeft']
		public static By activeDaysLeft = By.id("tv.hooq.android:id/daysLeft");
		public static By activeDescp = By.id("tv.hooq.android:id/expires");
		public static By activeAutoRenew = By.id("tv.hooq.android:id/lblAutoRenew");
		public static By activeRenewState = By.xpath("//android.widget.TextView[@text='OFF']");
		public static By activeSubScripBtn = By.id("tv.hooq.android:id/manage");
		public static By activeVoucherCodeBox = By.id("tv.hooq.android:id/voucherCode");
		public static By activeRedeemBtn = By.id("tv.hooq.android:id/redeem");
		public static By activebtnUp = By.id("tv.hooq.android:id/backInMainActivity");
		public static By freeSubScripText = By.id("tv.hooq.android:id/title");
		public static By freeDaysLeft = By.id("tv.hooq.android:id/daysLeft");
		public static By freeDescp = By.id("tv.hooq.android:id/expires");
		public static By freeAutoRenew = By.id("tv.hooq.android:id/lblAutoRenew");
		public static By freeRenewState = By.xpath("//android.widget.TextView[@text='OFF']");
		public static By freeSubScripBtn = By.id("tv.hooq.android:id/manage");
		public static By freeVoucherCodeBox = By.id("tv.hooq.android:id/voucherCode");
		public static By freeRedeemBtn = By.id("tv.hooq.android:id/redeem");
		public static By freebtnUp = By.id("tv.hooq.android:id/backInMainActivity");
		public static By freeVCR= By.xpath("//android.widget.TextView[@text='Voucher Code Redemption']");
		public static By freeVCRDesc = By.xpath("//android.widget.TextView[@text='If you have received a voucher code, please enter it below to add on to your subscription.']");
		public static By AnonSubscripLgdone = By.id("tv.hooq.android:id/emailDone");
		public static By AnonSubscripLgemail = By.id("tv.hooq.android:id/emailSpinner");
		public static By AnonSubscripLgLogo = By.id("tv.hooq.android:id/fragmentTitle");
		public static By AnonSignUp = By.xpath("//android.widget.TextView[@text='Sign up']");
		public static By AnonSignUpTxt = By.xpath("//android.widget.TextView[@text='Sign up with your mobile number']");
		public static By AnonInputLayout = By.id("tv.hooq.android:id/inputLayout");
		public static By AnonSignUpDoneBtn = By.xpath("//android.widget.Button[@text='Done']");
		public static By AnonTos = By.id("tv.hooq.android:id/tos");
		public static By AnonSignUpLogin = By.xpath("//android.widget.Button[@text='Already a user? Login. ']");
		public static By AnonVCR1= By.xpath("//android.widget.TextView[@text='Redeem Voucher Codes']");                                                                           
		public static By AnonSignUpLoginTab = By.xpath("//android.widget.Button[@text='Already a user?Login. ']");
		public static By AnonVCR= By.xpath("//android.widget.TextView[@text='Voucher Code Redemption']");
		public static By AnonVCRDesc = By.xpath("//android.widget.TextView[@text='If you have received a voucher code, please enter it below to add on to your subscription.']");
		public static By ContinueWatchingVid = By.xpath("//android.widget.TextView[@text='Continue watching']");
		public static By seekbar = By.id("tv.hooq.android:id/seekBar");
		public static By Cancelsubs = By.xpath("//android.widget.TextView[@text='Cancel Subscription']");
		
		public static By AnonVCRDesc1 = By.xpath("//android.widget.TextView[@text='If you have received a voucher code, enter it here!']");
		public static By FbLoginLaunchBtn = By.id("tv.hooq.android:id/loginEmailFacebook");
		public static By FbLogo = By.id("com.facebook.katana:id/login_fb_logo");
		public static By Fblogin = By.id("com.facebook.katana:id/login_username");
		public static By FbloginPwd = By.id("com.facebook.katana:id/login_password");
		public static By FbloginPwd1 = By.xpath("//android.widget.EditText[@resource-id='u_0_2']");
		public static By FbloginBtn = By.id("com.facebook.katana:id/login_login");
		public static By FbloginBtn1 = By.id("//android.widget.Button[@content-desc='Log In']");
		public static By FbErrorTitle = By.xpath("//android.view.View[@content-desc='Error']");
		public static By FberrorMsg = By.xpath("//android.view.View[@content-desc='App Not Setup: This app is still in development mode, and you don't have access to it. Switch to a registered test user or ask an app admin for permissions.']");
		public static By FberrorMsgBtn = By.xpath("//android.widget.Button[@content-desc='Okay']");
		public static By DiscimgPlay = By.id("tv.hooq.android:id/btnPlayImg");
		public static By discoverPageFirstShowMovieTablet = By.id("tv.hooq.android:id/imagePortrait");
		public static By discoverPageFirstShowMovie = By.id("tv.hooq.android:id/imgLeftPoster");
		public static By discoverPageFirstShowMovienew = By.id("tv.hooq.android:id/imgLeftPoster");
		//public static By WatchList = By.xpath("//android.widget.TextView[@text='Watchlist']");
		public static By discoverPageFirstShowMovie2 = By.id("tv.hooq.android:id/imgPoster");
		public static By discoverPageFirstShowMovie1 = By.id("tv.hooq.android:id/collectionPoster");
		public static By WatchLater = By.xpath("//android.widget.TextView[@text='Watch Later']");
		public static By WatchLaterTitle = By.xpath("//android.widget.TextView[@text='Watchlist']");
		public static By WatchLaterBackbtn = By.id("tv.hooq.android:id/btnUp");
		//public static By cancelBtn= By.id("tv.hooq.android:id/btnCancel");
		public static By historyimage= By.id("tv.hooq.android:id/imagePortrait");
		public static By historyremove= By.id("tv.hooq.android:id/btnRemove");
		public static By historydelete= By.id("tv.hooq.android:id/confirm");
		public static By WatchLaterimg = By.id("tv.hooq.android:id/imagePortrait");
		public static By FavSection = By.xpath("//android.widget.TextView[@text='Favorites']");
		
		public static By WLSection = By.xpath("//android.widget.TextView[@text='Watch Later']");
		
		public static By FavTitle = By.xpath("//android.widget.TextView[@text='Favorites']");
		public static By FavBackbtn = By.id("tv.hooq.android:id/btnUp");
		public static By SetBackbtn1 = By.id("tv.hooq.android:id/backInMainActivity");
		public static By setBackbtn = By.id("tv.hooq.android:id/btnUp");
		public static By Favimg = By.id("tv.hooq.android:id/imagePortrait");
		public static By HistoryTitle = By.xpath("//android.widget.TextView[@text='History']");
		public static By SettingsTitle = By.xpath("//android.widget.TextView[@text='SETTINGS']");
		public static By SubmitTitle = By.xpath("//android.widget.Button[@text='Submit']");
		public static By Substitle = By.id("tv.hooq.android:id/startingSentence");
		
		public static By Substitle1 = By.id("tv.hooq.android:id/manage");
		public static By TransactionTitle = By.xpath("//android.widget.TextView[@text='TRANSACTION HISTORY']");
		public static By HistoryBackbtn = By.id("tv.hooq.android:id/btnUp");
		public static By rentflag = By.id("tv.hooq.android:id/img_flag_rental");
		public static By HistorySection = By.xpath("//android.widget.TextView[@text='History']");
		public static By settingsSection = By.xpath("//android.widget.TextView[@text='Settings']");
		public static By TransactionSection = By.xpath("//android.widget.TextView[@text='Transaction History']");
		public static By SupportSection = By.xpath("//android.widget.TextView[@text='Support']");
		
		
		public static By SupportSectionheader = By.xpath("//android.widget.TextView[@text='SUPPORT']");
		public static By SupportSection1 = By.xpath("//android.widget.TextView[@text='Support']");
		public static By DownloadsTitle = By.xpath("//android.widget.TextView[@text='DOWNLOADS']");
		public static By AudiolanTitle = By.xpath("//android.widget.TextView[@text='Audio Language']");
		public static By Bahasalang = By.xpath("//android.widget.TextView[@text='Paket Berlangganan']");
		public static By AudiolanTitlecancel = By.xpath("//android.widget.Button[@text='Cancel']");
		//public static By thailang = By.xpath("//android.widget.TextView[@text='รายการของฉัน']");
		public static By englang = By.xpath("//android.widget.TextView[@text='ME']");
		//public static By Appdisplaylangbahasa = By.xpath("//android.widget.TextView[@text='SAYA']");
		public static By DownloadsBackbtn = By.id("tv.hooq.android:id/btnUp");
		public static By rentalsBackbtn = By.id("tv.hooq.android:id/backInMainActivity");
		public static By DownloadSection = By.xpath("//android.widget.TextView[@text='Downloads']");
		public static By Seasonlist1 = By.xpath("//android.widget.RadioButton[@text='Season 1']");
		public static By Audiolan = By.xpath("//android.widget.TextView[@text='Audio Language']");
		public static By Appdisplay = By.xpath("//android.widget.TextView[@text='App Display Language']");
		public static By Appdisplaybahasa = By.xpath("//android.widget.CheckedTextView[@text='Bahasa Indonesia']");
		public static By linktv = By.xpath("//android.widget.TextView[@text='Link TV']");
		public static By Downloadqual = By.xpath("//android.widget.TextView[@text='Download Quality']");
		public static By playbackquality = By.xpath("//android.widget.TextView[@text='Playback Quality']");
		public static By mobdatausage = By.xpath("//android.widget.TextView[@text='Mobile Data Usage']");
		public static By transactionhistoryimage = By.id("tv.hooq.android:id/emptyStateIcon");
		public static By transactionhistorydesc = By.id("tv.hooq.android:id/emptyStateDesc");
		public static By transactionhistoryheader = By.xpath("//android.widget.TextView[@text='TRANSACTION HISTORY']");
		public static By subtitle = By.xpath("//android.widget.TextView[@text='Subtitle Language']");
		//public static By playbackauto = By.xpath("//android.widget.CheckedTextView[@text='Auto']");
		public static By downloadlow1 = By.xpath("//android.widget.CheckedTextView[@text='Low']");
		public static By downloadmed = By.xpath("//android.widget.CheckedTextView[@text='Medium']");
		public static By downloadhigh = By.xpath("//android.widget.CheckedTextView[@text='High']");
		public static By subtitlelan = By.xpath("//android.widget.TextView[@text='Subtitle Language']");
		public static By subtitlelan1 = By.xpath("//android.widget.CheckedTextView[@text='English']");
		public static By link = By.id("tv.hooq.android:id/btn_link");
		public static By season = By.id("tv.hooq.android:id/txtSeason");
		public static By linktvcode = By.id("tv.hooq.android:id/btnEnterTvCode");
		
		public static By linktvbckbtn = By.id("tv.hooq.android:id/backInMainActivity");
		
		
		public static By linkscan = By.id("tv.hooq.android:id/btnScanQRCode");
		public static By subtitlecancel = By.xpath("//android.widget.Button[@text='Cancel']");
		public static By Downloadqual1 = By.id("tv.hooq.android:id/alertTitle");
		public static By playbackqual = By.id("tv.hooq.android:id/alertTitle");
		//public static By Appdisplaylangthai = By.xpath("//android.widget.TextView[@text='ตั้งค่า']");
		public static By Appdisplaythai = By.xpath("//android.widget.CheckedTextView[@text='Thai']");
		public static By Appdisplayeng = By.xpath("//android.widget.CheckedTextView[@text='English (USA)']");
		public static By Anonsettings = By.xpath("//android.widget.TextView[@text='Settings']");
		public static By Applanguagethai = By.xpath("//android.widget.CheckedTextView[@text='Thai']");
		public static By FindSeasonEpisode = By.xpath("//android.widget.TextView[@text='Find Episode']");
		public static By FindSeasonEpisod = By.id("tv.hooq.android:id/imgEpisodeList");
		public static By FindSeasonEpisodt = By.id("tv.hooq.android:id/seasons_button_right");
		public static By Season1 = By.xpath("//android.widget.TextView[@text='Season 1']");
		public static By FindSeason = By.xpath("//android.widget.TextView[@text='SEASON 1']");
		public static By Appokay = By.xpath("//android.widget.TextView[@text='Okay']");
		
		public static By Anontransaction = By.xpath("//android.widget.TextView[@text='Transaction History']");
		
		
		public static By Episode6 = By.xpath("//android.widget.TextView[@text='The Middle Earth Paradigm']");
		public static By Tv50Shows = By.xpath("//android.widget.TextView[@text='TOP 50 TV SHOWS']");
		public static By emailVerify = By.xpath("//android.widget.TextView[@text='Email verification']");
		 public static By firstCollection = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/collectionPoster']");
			public static By firstShowFromCollection = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/imagePortrait']");
			public static By favorite= By.id("tv.hooq.android:id/btnFavorite");
		//	public static By watchLater  = By.id("tv.hooq.android:id/btnAdd");
			//public static By loadingImage = By.id("android:id/body");
		//	public static By videoTitle = By.id("tv.hooq.android:id/assetTitle");
		
		//facebook login
		public static By fbUserName = By.xpath("//android.widget.EditText[@resource-id='com.facebook.katana:id/login_username']");
		public static By fbPassword = By.xpath("//android.widget.EditText[@resource-id='com.facebook.katana:id/login_password']");
		public static By fbLogin = By.xpath("//android.widget.Button[@resource-id='com.facebook.katana:id/login_login']");
		public static By fbLink	 = By.xpath("//android.widget.TextView[@text='Login with Facebook']");

		//Search
		public static By btnSearch = By.id("tv.hooq.android:id/search");
		public static By btnSearchcc = By.id("cardnumber");
		public static By searchmov = By.id("tv.hooq.android:id/imagePortrait");
		public static By edtSearch = By.id("tv.hooq.android:id/inputSearch");
		public static By downloadlow = By.id("tv.hooq.android:id/quality_low");
		public static By downloaddone = By.id("tv.hooq.android:id/selection_done");
		public static By downloadsavepref = By.id("tv.hooq.android:id/pref_checkBox");
		public static By txtSuggestedText = By.id("tv.hooq.android:id/suggestText");
		public static By txtSuggestedText1 = By.id("tv.hooq.android:id/imagePortrait");
		
		public static By searchResultList = By.id("tv.hooq.android:id/matchedTitlesListView");
		//public static By addwatchlater = By.id("tv.hooq.android:id/btnAdd");
		public static By addfavorite = By.id("tv.hooq.android:id/btnFavorite");
		public static By Downloadvideo = By.xpath("//android.widget.TextView[@text='Download']");
		//public static By Downloadquality = By.xpath("//android.widget.TextView[@text='Download Quality']");
		public static By Downloadpreference = By.xpath("//android.widget.TextView[@text='Save my preferences']");
		public static By Downloadpreferencedone = By.xpath("//android.widget.TextView[@text='Downloading']");
		public static By downloadmeload = By.id("tv.hooq.android:id/primaryProgress");
		public static By downloadmplay = By.id("tv.hooq.android:id/imagePortrait");
		public static By downloadmplay1 = By.id("tv.hooq.android:id/okay");
		public static By downloadbutton = By.id("tv.hooq.android:id/downloadButton");
		public static By Downloadoption1 = By.xpath("//android.widget.TextView[@text='Downloaded']");
		public static By Downloadlimit = By.xpath("//android.widget.TextView[@text='Download Limit Reached']");
		public static By Downloadlimit1 = By.xpath("//android.widget.TextView[@text='You can only have 5 downloads at any time.']");
		public static By pendingdownload = By.id("tv.hooq.android:id/pending_count");
		
		public static By genreList1 = By.id("tv.hooq.android:id/genreList");
		
		//Browse Page Locators
		//public static By firstCollection = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/collectionPoster']");
		public static By firstCollectiont = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/collectionPosterLeft']");
		//public static By firstShowFromCollection = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/imagePortrait']");
		//public static By favorite= By.id("tv.hooq.android:id/btnFavorite");
		public static By Filtertype2 = By.xpath("//android.widget.TextView[@text='TYPE']");
		public static By firstCollection1 = By.xpath("//android.widget.RelativeLayout[@resource-id='tv.hooq.android:id/collectionItem']");
		public static By favoritetab1= By.id("tv.hooq.android:id/title");
		//public static By watchLater  = By.id("tv.hooq.android:id/btnAdd");
		public static By selectplantoolbar  = By.id("tv.hooq.android:id/toolbarTitle");
		public static By Filtericon  = By.id("tv.hooq.android:id/btnFilter");
		public static By Filterheader = By.xpath("//android.widget.TextView[@text='FILTER']");
		public static By Filterreset = By.xpath("//android.widget.TextView[@text='Reset']");
		public static By Applyfilter = By.xpath("//android.widget.Button[@text='Apply Filters']");
		public static By Filtertype= By.id("tv.hooq.android:id/Type_tv_shows");
		public static By Filtertype1= By.id("tv.hooq.android:id/Type_movies_only");
		public static By Filtergenre= By.xpath("//android.widget.ToggleButton[@text='Comedy']");
		public static By watchLatertab  = By.id("tv.hooq.android:id/btnWatchLater");
		public static By loadingImage = By.id("android:id/body");
		//public static By videoTitle = By.id("tv.hooq.android:id/assetTitle");
		public static By genreList = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/genreList']");
		public static By seasonList = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/seasonTitle']");
		public static By findEpisodeBtn = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/imgEpisodeList']");
		public static By findEpisodeBtnTablet = By.xpath("//android.widget.ImageButton[@resource-id='tv.hooq.android:id/seasons_button_right']");
		public static By episodeList = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/mSeasonRecyclerListView']//android.widget.ImageView[@resource-id='tv.hooq.android:id/play']");
		public static By Filterclose  = By.id("tv.hooq.android:id/btnUp");
		public static By Restritedpin  = By.id("tv.hooq.android:id/pinInput");
		public static By TVODB  = By.id("tv.hooq.android:id/txtTvodRedeem");
		public static By lstSeason =By.id("tv.hooq.android:id/txtSeason");
		public static By txtFirstColl=By.id("tv.hooq.android:id/tvTitle");
		public static By linkseeAll=By.id("tv.hooq.android:id/txt_see_all");
		public static By SeeAllfirstCollHeader=By.id("tv.hooq.android:id/tvLabel");
		
		//SubScription
		//public static By emptyStateIcon = By.id("tv.hooq.android:id/emptyStateIcon"); 
		//public static By AnonUser = By.xpath("//android.widget.TextView[@text='HOWDY STRANGER!']");
		//public static By stateDescrip = By.id("tv.hooq.android:id/emptyStateDesc");
		//public static By StartBtn = By.xpath("//android.widget.Button[@text='Get started']");
		//public static By btnUp = By.id("tv.hooq.android:id/btnUp");
		
		
		//HomePage Locators
		public static By HOOQLogo = By.xpath("//*[@resource-id='tv.hooq.android:id/headerLogo']"); 
        
		public static By fbprofile = By.id("com.facebook.katana:id/secondary_action_button");
		public static By Submit = By.id("tv.hooq.android:id/redeem");
		public static By YouwwTVOD = By.id("tv.hooq.android:id/rental");
		public static By tabclose = By.id("tv.hooq.android:id/btnClose");
		public static By CWTVOD = By.id("tv.hooq.android:id/goldLabel");
		public static By INR79 = By.xpath("//android.widget.TextView[@text='INR 79']");
		public static By GetStarted = By.xpath("//android.widget.ImageView[@index='1']");
		public static By Anonyplaybtn = By.id("tv.hooq.android:id/btn_play");
		public static By lapplaybtn = By.id("tv.hooq.android:id/playButton");
		public static By playersub = By.id("tv.hooq.android:id/iconSettings");
		public static By meProfile = By.id("btnProfile");
		public static By meProfile1 = By.id("tv.hooq.android:id/iv_me_btn");
		//public static By iHaveVerifiedBtn = By.xpath("//android.widget.Button[@text='I have verified']");
		public static By videoOptions = By.xpath("//android.support.v7.widget.RecyclerView//android.widget.RelativeLayout[@resource-id='tv.hooq.android:id/btnOverflow']");
		public static By videoOptionst = By.xpath("//android.support.v7.widget.RecyclerView//android.widget.RelativeLayout[@resource-id='tv.hooq.android:id/collectionList']");
		public static By videoTitle = By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='tv.hooq.android:id/discover_feed_list']//android.widget.TextView[@resource-id='tv.hooq.android:id/assetTitle']");
		public static By Getstr = By.xpath("////android.widget.TextView[@resource-id='tv.hooq.android:id/title']");
		public static By favoriteOption = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/lblFav']");
		public static By favoriteOptiont = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/fav_text']");
		//public static By watchLater = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/lblWatchLater']");
		public static By cancelVideOption = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/lblCancel']");
		public static By cancelBtn = By.xpath("//android.widget.ImageView[@resource-id='tv.hooq.android:id/btnClose']");
		public static By videoLoader = By.xpath("//android.support.v7.widget.RecyclerView");
		public static By youwerewatching = By.xpath("//android.widget.TextView[@text='Continue Watching']");
		public static By continuewatching = By.xpath("//android.widget.TextView[@text='CONTINUE WATCHING?']");
		public static By iHaveVerifiedBtn = By.xpath("//android.widget.Button[@text='I have verified']");
		public static By discoverBtn = By.xpath("//android.widget.RelativeLayout[@resource-id='tv.hooq.android:id/btnDiscover']");	
		public static By browseBtn = By.xpath("//android.widget.TextView[@text='Browse']");
		public static By browseBtnnew =  By.id("tv.hooq.android:id/imgSearch");
		public static By closebtnnew =  By.id("tv.hooq.android:id/btnUp");
		public static By cwplay =  By.id("tv.hooq.android:id/play_icon");
		public static By favoriteOption1 = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/btnFavorite']");
		public static By watchlater1 = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/btnAdd']");
		public static By watchlist = By.xpath("//android.widget.TextView[@resource-id='tv.hooq.android:id/txtWatchList']");
		public static By playerclose = By.id("tv.hooq.android:id/iconCloseSettings");
		public static By playerepisode = By.id("tv.hooq.android:id/iconPlaylist");
		public static By playernextepisode = By.id("tv.hooq.android:id/iconNextEpisode");
		public static By playerpause = By.id("tv.hooq.android:id/iconPlayPause");
		public static By playerplay = By.id("tv.hooq.android:id/iconPlayPause");
		public static By cwcontent = By.id("tv.hooq.android:id/txtAssetTitle");
		//public static By subtitleeng = By.xpath("//android.widget.TextView[@text='English']");
		
		public static By watchlist2 = By.xpath("//android.widget.TextView[@text='Watchlist']");

		public static By likeOption=By.xpath("//android.widget.TextView[@text='Like']");
		public static By unlike=By.xpath("//android.widget.TextView[@text='Liked']");
		
		public static By btnMovies=By.id("tv.hooq.android:id/btnMovies");
		public static By headerMovies=By.xpath("//android.widget.TextView[@text='MOVIES']");
		public static By btnTVShows=By.id("tv.hooq.android:id/btnTvShows");
		public static By headerTVShows=By.xpath("//android.widget.TextView[@text='TV SHOWS']");
		public static By watchlist1=By.id("tv.hooq.android:id/txtAssetTitle");
		public static By Like = By.xpath("//android.widget.TextView[@text='Like']");
		public static By Liked = By.xpath("//android.widget.TextView[@text='Liked']");
	    public static By Download = By.id("tv.hooq.android:id/txtDownload");
		
		//public static By Login = By.id("tv.hooq.android:id/title");
	    public static By rentbtn = By.xpath("//android.widget.RelativeLayout[@resource-id='tv.hooq.android:id/btnRent']");	
		public static By rent = By.id("tv.hooq.android:id/btnRent");
		public static By premium = By.id("tv.hooq.android:id/btnPremium");
		public static By rentwhatsnew = By.xpath("//android.widget.TextView[@text='WHAT'S NEW ON HOOQ']");
		public static By explorebtn = By.xpath("//android.widget.Button[@text='Explore Rental Movies']");
		public static By premiumbtn = By.xpath("//android.widget.Button[@text='Explore Premium+ Movies']");
		public static By subscriptionr = By.xpath("//android.widget.TextView[@text='Subscription']");
		public static By ticketscnt = By.xpath("//android.widget.TextView[@text='Tickets']");
		public static By renttc = By.xpath("//android.widget.TextView[@text='Terms of Use']");
		public static By rentpayment = By.xpath("//android.widget.TextView[@text='PAYMENT METHOD']");
		public static By phpprice = By.xpath("//android.widget.TextView[@text='PHP 125']");
		public static By latestmvtext = By.xpath("//android.widget.TextView[@text='Latest movies now available for rent!']");
		public static By ticketdesc = By.xpath("//android.widget.TextView[@text='Already on a monthly HOOQ subscription? Congratulations! Each month’s subscription comes with 1 ticket for a movie rental on us!']");
		public static By exploremovie = By.id("tv.hooq.android:id/okay");
		public static By ccmovie = By.id("tv.hooq.android:id/desc");
		public static By rentoptn = By.id("tv.hooq.android:id/fabbutton_ring");
		public static By renttvod = By.id("tv.hooq.android:id/rental");
		public static By rentoptn1 = By.id("tv.hooq.android:id/lbl_rent");
		public static By rentcollheader = By.id("tv.hooq.android:id/searchHeader");
		public static By anonrent = By.id("tv.hooq.android:id/btnRent");
		public static By tktredeem = By.id("tv.hooq.android:id/btnRedeem");
		public static By rentoptntab = By.id("tv.hooq.android:id/watchContainer");
		public static By tktpurchase = By.id("tv.hooq.android:id/btnSecond");
		public static By tktpurchase1 = By.id("tv.hooq.android:id/lblRedeem");
		public static By tktconfirm = By.id("tv.hooq.android:id/okay");
		public static By ticketconform = By.xpath("//android.widget.Button[@text='Confirm']");
		public static By tktwatchltr = By.xpath("//android.widget.Button[@text='Confirm']");
		public static By rentmakepayment = By.xpath("//android.widget.Button[@text='Make Payment']");
		public static By ccradio = By.id("tv.hooq.android:id/radioButton");
		public static By tvodcc = By.xpath("//android.widget.Button[@text='Rent for PHP 125']");
		public static By tvodConform = By.xpath("//android.widget.TextView[@text='Confirm']");
		public static By rentSubScripText = By.xpath("//android.widget.TextView[@text='Subscription']");
		public static By subscriptiondays = By.id("tv.hooq.android:id/subscription_days");
		public static By ticketredeem = By.xpath("//android.widget.TextView[@text='TICKET REDEEMED']");
		public static By rentticketsText = By.xpath("//android.widget.TextView[@text='Tickets']");
		public static By nooftickets = By.id("tv.hooq.android:id/tickets");
		public static By lapsedesc = By.id("tv.hooq.android:id/content");
		public static By lapsedsubs = By.id("tv.hooq.android:id/toolbarTitle");
		public static By ticketredeeminfo = By.id("tv.hooq.android:id/content");
	//	public static By lapsesubs = By.xpath("//android.widget.TextView[@text='Subscribe']");
		public static By watchnow = By.id("tv.hooq.android:id/okay");
		public static By rentheader = By.id("tv.hooq.android:id/toolbarTitle");
		public static By premiumheader = By.xpath("//android.widget.TextView[@text='PREMIUM+']");
		public static By rentticketdesc = By.id("tv.hooq.android:id/ticketText");
		 public static By playButtonrent = By.id("tv.hooq.android:id/fabbutton_ring");
		 public static By Trailer = By.id("tv.hooq.android:id/trailerButton"); 
		 public static By Trailer1 = By.id("tv.hooq.android:id/watchTrailer");                                   
		 public static By merentals = By.xpath("//android.widget.TextView[@text='Rentals']");
		 public static By rentpurchasedes = By.xpath("//android.widget.TextView[@text='Start within 30 days, finish within 48 hours.']");
		   
		 public static By rentpurchasedestc = By.xpath("//android.widget.TextView[@text='Terms of Use']");
		 public static By myrentalsplay = By.id("tv.hooq.android:id/btnPlay");
		 public static By rentimg = By.id("tv.hooq.android:id/imgRent");
		 public static By renttabtext = By.id("tv.hooq.android:id/txtRent");
		 public static By rentcollectionimg = By.id("tv.hooq.android:id/rental");
		 public static By rentcollectionimgtab = By.id("tv.hooq.android:id/rent_flag");
		// public static By rentflag = By.id("tv.hooq.android:id/rent_flag_portrait");
		 public static By trailer = By.id("tv.hooq.android:id/btnTrailer");
		 public static By Rentposter = By.id("tv.hooq.android:id/imgLeftPoster");
		 public static By myrentalstitle = By.id("tv.hooq.android:id/title");
		 public static By discrent = By.id("tv.hooq.android:id/btnRent");
		 
		 public static By rentsect = By.id("tv.hooq.android:id/rental");

		 public static By tabPremium=By.id("tv.hooq.android:id/btnPremium");
		 public static By ticketconfirm = By.id("tv.hooq.android:id/okay");
	    
}

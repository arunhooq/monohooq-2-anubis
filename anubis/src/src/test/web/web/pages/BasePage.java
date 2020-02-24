package web.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.JSONUtilities;
import com.automation.utilities.ReadTestData;
import com.codepine.api.testrail.model.Result.List;

public class BasePage extends ActionEngine {
	
	public  By hooqLogo=By.cssSelector("[class*='mobile Header__HeaderContainer'] a");

//    public  By hamburgerMenu = By.cssSelector("img[class*='IconButton']");
	public static  By hamburgerMenu = By.xpath("//*[@id='mount']/header[1]/div/a[2]/img");
	public  By hamburgerMenu_Discover = By.cssSelector(".e2e-sideMenu-discover a");
	public  By hamburgerMenu_Channels = By.cssSelector(".e2e-sideMenu-channels a");
	public  By hamburgerMenu_Movies = By.cssSelector(".e2e-sideMenu-movies a");
	public  By hamburgerMenu_TVShows = By.cssSelector(".e2e-sideMenu-tvshows a");
	public  By hamburgerMenu_Rent = By.cssSelector(".e2e-sideMenu-rent a");
	public  By hamburgerMenu_MyRentals = By.cssSelector(".e2e-sideMenu-myrentals a");
	public static  By hamburgerMenu_DownloadApp = By.cssSelector(".e2e-sideMenu-downloadapp a");
	public static  By hamburgerMenu_AccountSettings = By.cssSelector(".e2e-sideMenu-accountsettings a");
	public  By hamburgerMenu_LinkTV = By.cssSelector(".e2e-sideMenu-linktv a");
	public  By hamburgerMenu_Pricing = By.cssSelector(".e2e-sideMenu-pricing a");
	public  By hamburgerMenu_SignUp = By.cssSelector(".e2e-sideMenu-signup a");
	public  By hamburgerMenu_Login = By.cssSelector(".e2e-sideMenu-login a");
	public  By hamburgerMenu_Logout = By.cssSelector(".e2e-sideMenu-logout a");
	public static  By hamburgerMenu_Close = By.xpath("//img[@title='Close']");

	public static  By headerDiscover = By.cssSelector("mobile .e2e-tab-discover");
	public static  By headerDiscover1 = By.cssSelector("[class*='e2e-tab-discover']");
	public  By headerChannels = By.cssSelector(".mobile .e2e-tab-channels");
	public  By headerMovies = By.cssSelector(".mobile .e2e-tab-movies");
	public  By headerTVShows = By.cssSelector(".mobile .e2e-tab-tvshows");
	public  By headerRent = By.cssSelector(".mobile .e2e-tab-rent");
	
	public  static By languageButton = By.cssSelector("[class*='LanguageSelectorButton']");
	public  static By languageModal = By.cssSelector("[class*='Content--after-open']");
	public  static String languageSelector = "//div[contains(@class, 'Content--after-open')]/div/div/div[";
	public  By languageRows = By.xpath("//div[contains(@class,'ReactModal__Overlay ReactModal__Overlay--after-open')]/div/div[1]/div[2]/div");
	public  static By footerContactUs = By.xpath("//div[contains(@class,'PageFooter__FooterLinks')]/a[5]");

	public static  By userNameInSideMenu = By.xpath("//div[contains(@class,'SideMenu__UserLoginText')]");
	public  By closehamburgerMenu = By.xpath("//img[@title='Close']");

	public static By spotLightTab = By.cssSelector(".e2e-spotlight");

 	public static By btnSearch = By.cssSelector("[title='Search']");
	
	public static BasePage performLinksCheckBasePage(String url) {

		try {
	
			performLinkCheck(url);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new BasePage();
	}

	public  DiscoverPage clickDiscoverTab() {
		
		try {
			click(headerDiscover, "headerDiscover");
			waitForVisibilityOfElement(spotLightTab, "spotLightTab");
			TestUtilities.logReportPass("Clicked on DiscoverTab and Verified if Spotlight appeared");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}

	public  void clickChannelsTab() {

		
		try {
			click(headerChannels, "headerChannels");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("channels"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Channels tab");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public  MoviesPage clickMoviesTab() {

		
		try {

			click(headerMovies, "headerMovies");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("movies"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Movies tab");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new MoviesPage();
	}

	public  TVShowsPage clickTVShowsTab() {

		
		try {

			click(headerTVShows, "headerTVShows");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("tv-shows"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on TVShows tab");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new TVShowsPage();
	}

	public void clickRentTab() {

		
		try {

			click(headerRent, "headerRent");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("rent"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Rental tab");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public static BasePage checkLocalisation() {	
		
		try {
			//Create Array of possible language choices
			ArrayList<String> arr = new ArrayList<String>();
			arr.add("EN");
			arr.add("ID");
			arr.add("TH");
			
			//TC1 - Check Current Language set to TH (default for TH)
			if (selectedLanguage() != "TH") {
				ReporterLog.warning("localisation", "Expected language 'TH' is not selected - instead '"+selectedLanguage()+"' is selected.");
			} else {
				ReporterLog.pass("Initial Language Check", "Initial language is correctly set to 'TH'");
			}
			
			//Cycle through each language and validate text is correct localized string
			for (int i = 0; i < 3; i++) {
				changeLanguage(arr.get(i));
				//Thread.sleep(15000);
				String thisLanguage = selectedLanguage();
				if (!(thisLanguage.equals(arr.get(i)))) {
					throw new Exception("Expected language '"+arr.get(i)+"' is not selected - instead '"+selectedLanguage()+"' is selected."); 
				} else {
					ReporterLog.pass("Check Language", "Selected Language is: "+thisLanguage);
				}
				verifyLanguage(arr.get(i));
			}
			
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new BasePage();
	}
	
	public static void changeLanguage(String language) {
		ReporterLog.info("Change Language","Changing language to: "+language);
		
		//Create Array of possible language choices
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("EN");
		arr.add("ID");
		arr.add("TH");
		
		//find desired language
		int myPos = arr.indexOf(language.toUpperCase())+1;		
		By eleToClick = By.xpath(languageSelector+myPos+"]");
		
		//and click it
		waitForElementClickable(languageButton, "LanguageButton");
		click(languageButton, "Clicking on language button");

		waitForVisibilityOfElement(languageModal, "waiting for language Modal to appear");
		
		click(eleToClick,"clicking on language '"+language.toUpperCase()+"' which is line # "+(myPos));
		waitForInVisibilityOfElement(languageModal, "language Modal");
	}
	
	public static String selectedLanguage() {
		ReporterLog.info("Check Selected Language","Checking Selected Language");
		
		//Create Array of possible language choices
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("EN");
		arr.add("ID");
		arr.add("TH");

		waitForElementClickable(headerDiscover1,"headerDiscover");
		
		waitForElementClickable(languageButton, "languageButton");
		clickNoWait(languageButton,"Clicking on language button");
		
		waitForVisibilityOfElement(languageModal, "waiting for language Modal to appear");
		
		int found = -1;
		
		for (int i = 0; i < 3; i++) {
			By eleChecked = By.xpath(languageSelector+(i+1)+"]/img");
			if (ActionEngine.isNumOf_Elements_Greater_Than_Zero(eleChecked)){
				found = i;
			}			
		}
		
		if (found == -1) {
			ReporterLog.fail("Verify selected Language", "No language indicated as being selected");
		}
		
		ActionEngine.driver.findElement(languageModal).sendKeys(Keys.ESCAPE);
		waitForInVisibilityOfElement(languageModal, "language Modal");
		waitForElementClickable(headerDiscover1,"headerDiscover");
		
		if (found >=0) {
			return arr.get(found);
		} else {
			return "";
		}
	}
	
	public static void verifyLanguage(String language) {
		
		verifyText(headerDiscover1, JSONUtilities.getLocalizedString(language, "TopMenu1"));
		verifyText(footerContactUs, JSONUtilities.getLocalizedString(language, "FooterMenu5"));
		clickHamburgerMenu();
		verifyText(hamburgerMenu_DownloadApp, JSONUtilities.getLocalizedString(language, "HamburgerMenu6"));
		closeHamburgerMenu();
	}

	public static BasePage clickHamburgerMenu() {

		
		try {
			//clickOnElementInAList(hamburgerMenu,1);
			//System.out.println("Entered the basepage func and about to click");
			click(hamburgerMenu, "hamburgerMenu");
			TestUtilities.logReportPass("Clicked on HamburgerMenu");
			//System.out.println("Entered the basepage func and clicked");
			waitForVisibilityOfElement(hamburgerMenu_DownloadApp, "hamburgerMenu_DownloadApp");
			TestUtilities.logReportPass("Verified HamburgerMenu is open");
			//System.out.println("completed waitForVisibilityOfElement func ");
			


		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new BasePage();
	}
	
	public static BasePage clickHamburgerMenu_NEW() throws Exception{

		
		try {
			//clickOnElementInAList(hamburgerMenu,1);
			//System.out.println("Entered the basepage func and about to click");
			click_NEW(hamburgerMenu, "hamburgerMenu");
			TestUtilities.logReportPass("Clicked on HamburgerMenu");
			//System.out.println("Entered the basepage func and clicked");
			waitForVisibilityOfElement_NEW(hamburgerMenu_DownloadApp, "hamburgerMenu_DownloadApp");
			TestUtilities.logReportPass("Verified HamburgerMenu is open");
			//System.out.println("completed waitForVisibilityOfElement func ");
			


		} catch (Exception e) {
			//TestUtilities.logReportFatal(e);
			throw new Exception("Error encountered clicking Hamburger Menu");
		}
		return new BasePage();
	}
	
	public  LoginPage getLoginPage() {

		try {

			performLogout_ifLoggedIn();
			clickHamburgerMenu();
			click(hamburgerMenu_Login, "hamburgerMenu_Login");
			TestUtilities.logReportPass("Clicked on Login option from HamburgerMenu");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new LoginPage();
	}
	
	public  LoginPage getLoginPage_NEW() throws Exception {
		
		try {

			performLogout_ifLoggedIn_NEW();
			clickHamburgerMenu_NEW();
			click_NEW(hamburgerMenu_Login, "hamburgerMenu_Login");
			TestUtilities.logReportPass("Clicked on Login option from HamburgerMenu");

		} catch (Exception e) {
			//TestUtilities.logReportFatal(e);
			throw new Exception("Exception getting Login page");
		}
		return new LoginPage();
	}

	public  DiscoverPage getDiscoverPage() {

		try {

			clickHamburgerMenu();
			click(hamburgerMenu_Discover, "hamburgerMenu_Discover");
			waitForVisibilityOfElement(spotLightTab, "spotLightTab");
			TestUtilities.logReportPass("Clicked on Discover option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}

	public  ChannelsPage getChannelsPage() {	
		try {

			clickHamburgerMenu();
			click(hamburgerMenu_Channels, "hamburgerMenu_Channels");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("channels"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Channels option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}

	public  MoviesPage getMoviesPage() {

		try {

			clickHamburgerMenu();
			click(hamburgerMenu_Movies, "hamburgerMenu_Movies");

			String currentURL = getCurrentUrl();
			if (!currentURL.contains("movies"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Movies option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new MoviesPage();
	}

	public TVShowsPage getTVShowsPage() {

		
		try {
			clickHamburgerMenu();
			click(hamburgerMenu_TVShows, "hamburgerMenu_TVShows");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("tv-shows"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on TVShows option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new TVShowsPage();
	}

	public  RentPage getRentPage() {
		
		try {

			clickHamburgerMenu();
			click(hamburgerMenu_Rent, "hamburgerMenu_Rent");

			String currentURL = getCurrentUrl();
			if (!currentURL.contains("rent"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Rent option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		
		return new RentPage();
	}

	public  void getAccountSettingsPage() {

		try {

			clickHamburgerMenu();
			click(hamburgerMenu_AccountSettings, "hamburgerMenu_AccountSettings");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("account-settings"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Accounts & Settings option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		
	}
	
	public  LinkTVPage getLinkTVPage() {

		try {

			clickHamburgerMenu();
			click(hamburgerMenu_LinkTV, "hamburgerMenu_LinkTV");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("mytv"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Link TV option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		
		return new LinkTVPage();
	}

	public  SignUpPage getSignUpPage() {

		
		try {

			performLogout_ifLoggedIn();
			clickHamburgerMenu();
			click(hamburgerMenu_SignUp, "hamburgerMenu_SignUp");
			TestUtilities.logReportPass("Clicked on SignUp option from HamburgerMenu");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SignUpPage();
	}

	public  void getMyRentalsPage() {
		
		try {

			clickHamburgerMenu();
			click(hamburgerMenu_MyRentals, "hamburgerMenu_MyRentals");
			String currentURL = getCurrentUrl();
			if (!currentURL.contains("my-rentals"))
				throw new Exception();
			TestUtilities.logReportPass("Clicked on Rentals option from HamburgerMenu");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}

	public static  BasePage closeHamburgerMenu() {

		
		try {

			clickHamburgerMenu();
			click(hamburgerMenu_Close, "hamburgerMenu_Close");
			TestUtilities.logReportPass("Closed HamburgerMenu");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new BasePage();
	}

	public  BasePage performLogout_ifLoggedIn() {

		try {
			clickHamburgerMenu();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Integer logoutCount = driver.findElements(hamburgerMenu_Logout).size();
			ReporterLog.info("PERFORM_LOGOUT_IF_LOGGED_IN - If logout is present in menu, logoutCount should be >0 - logoutCount is: "+logoutCount,"");

			if (driver.findElements(hamburgerMenu_Logout).size() > 0) {
				ReporterLog.info("Log out existing user","User shown as being logged in, logging them out so we can become a visitor or log in as another user");
				click(hamburgerMenu_Logout, "Click on logoutLink");
				TestUtilities.logReportPass("Clicked on Logout button");
				Thread.sleep(20000);
			}
			else {
				
				click(closehamburgerMenu, "closehamburgerMenu");
				TestUtilities.logReportPass("Clicked on Close for Hamburger Menu as user not logged in");
				Thread.sleep(20000);
			}			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new BasePage();
	}
	
	public  BasePage performLogout_ifLoggedIn_NEW() throws Exception{

		try {
			clickHamburgerMenu_NEW();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Integer logoutCount = driver.findElements(hamburgerMenu_Logout).size();
			ReporterLog.info("PERFORM_LOGOUT_IF_LOGGED_IN - If logout is present in menu, logoutCount should be >0 - logoutCount is: "+logoutCount,"");

			if (driver.findElements(hamburgerMenu_Logout).size() > 0) {
				ReporterLog.info("Log out existing user","User shown as being logged in, logging them out so we can become a visitor or log in as another user");
				click_NEW(hamburgerMenu_Logout, "Click on logoutLink");
				TestUtilities.logReportPass("Clicked on Logout button");
				Thread.sleep(20000);
			}
			else {
				
				click_NEW(closehamburgerMenu, "closehamburgerMenu");
				TestUtilities.logReportPass("Clicked on Close for Hamburger Menu as user not logged in");
				Thread.sleep(20000);
			}			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			//TestUtilities.logReportFailure(e);
			throw new Exception("Exception encountered when attempting to log out user");
		}
		return new BasePage();
	}
	
	public  BasePage performLinksCheck() {
		try 
		{
			performLinkCheck(ConfigDetails.strURL);

        } 
		catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new BasePage();
	}
	
	public  BasePage checkIfUserLoggedIn() {
		ReporterLog.info("BasePage", "Check if user Logged In");
		try {
			
			Integer numErrors = 0;
			//This delay required to allow the login info to be populated in the hamburger menu
			Thread.sleep(20000);
			//clickOnElementInAList(hamburgerMenu,1);
			click(hamburgerMenu,"Hamburger Menu");
			waitForElementClickable(hamburgerMenu_DownloadApp,"");
			
			ReporterLog.info("BasePage", "Looking for 'LogIn' option in menu");
			Boolean isLogInVisible = isElementPresentInDom(hamburgerMenu_Login);

			ReporterLog.info("BasePage", "Looking for 'LogOut' option in menu");
			Boolean isLogOutVisible = isElementPresentInDom(hamburgerMenu_Logout);
			
			ReporterLog.info("BasePage", "Looking for userName option in menu");
			Boolean isUserNameVisible = isElementPresentInDom(userNameInSideMenu);
			
			ReporterLog.info("Logout option visible = "+isLogOutVisible+", Login option visible = "+isLogInVisible+", userName visible = "+isUserNameVisible, "");

			Integer logoutCount = driver.findElements(hamburgerMenu_Logout).size();
			ReporterLog.info("CHECK_IF_USER_LOGGED_IN - If logout is present in menu, logoutCount should be >0 - logoutCount is: "+logoutCount,"");

			int numAttempts = 0;
			int maxAttempts = 5;
			Boolean success = isLogOutVisible;
			
			
			
			while (!success && (numAttempts < maxAttempts)) {
				ReporterLog.info("User login not reflected in menu - Retry Attempt: "+(numAttempts+1)+"/"+maxAttempts, "");
				driver.navigate().refresh();
				Thread.sleep(10000);
				//ReporterLog.info("Closing Hamburger Menu", "");
				//click(closehamburgerMenu, "closehamburgerMenu");
				ReporterLog.info("Opening Hamburger Menu", "");
				clickHamburgerMenu();
				isLogInVisible = isElementPresentInDom(hamburgerMenu_Login);
				isLogOutVisible = isElementPresentInDom(hamburgerMenu_Logout);
				isUserNameVisible = isElementPresentInDom(userNameInSideMenu);
				ReporterLog.info("BasePage", "'Logout' now visible in menu? "+isLogOutVisible);
				logoutCount = driver.findElements(hamburgerMenu_Logout).size();
				ReporterLog.info("Basepage - If logout is present in menu, logoutCount should be >0 - logoutCount is: "+logoutCount,"");
				
				success = isLogOutVisible;
				numAttempts ++;
			}
			
			if (isLogInVisible) {
				numErrors += 1;
				ReporterLog.info("Login", "Login Failed! (Log In option still present in Hamburger Menu - will retry in 10 seconds)");
				driver.navigate().refresh();
				Thread.sleep(10000);
				
				if(ConfigDetails.userType.equalsIgnoreCase("Active")) {
					//no need to close hamburger menu after a driver refresh
					//closeHamburgerMenu();
					getLoginPage().enterUserDetails(ReadTestData.ACTIVE_USER_ID).enterPassword(ReadTestData.PASSWORD);
					click(hamburgerMenu,"");
					waitForElementClickable(hamburgerMenu_DownloadApp,"");
				}
				else if(ConfigDetails.userType.equalsIgnoreCase("Lapsed")) {
					//no need to close hamburger menu after a driver refresh
					//closeHamburgerMenu();
					getLoginPage().enterUserDetails(ReadTestData.LAPSED_USER_ID).enterPassword(ReadTestData.PASSWORD);
					click(hamburgerMenu,"");
					waitForElementClickable(hamburgerMenu_DownloadApp,"");
				}
			} else {
				ReporterLog.info("Logout option visible = "+isLogOutVisible+", login option visible = "+isLogInVisible+", userName visible = "+isUserNameVisible, "");
			}
			
			if (isLogOutVisible && (!isUserNameVisible)) {
				ReporterLog.warning("Login", "Logged In successfully but username not displayed");
			}
			
			
			
			Boolean isCloseVisible = isElementPresentInDom(closehamburgerMenu);
			ReporterLog.info("Is close button visible: "+isCloseVisible, "");
			if (isCloseVisible) {
				ReporterLog.info("Closing Hamburger Menu", "");
				click(closehamburgerMenu, "closehamburgerMenu");
			}
			if (numErrors == 0) {
				TestUtilities.logReportPass("Clicked on HamburgerMenu and verified that user logged in, closed HamburgerMenu");
			}
				
		} catch (Exception e) {
				ActionEngine.launchUrl(ConfigDetails.strURL);
				TestUtilities.logReportFailure(e);
		}
		return new BasePage();
	}
	
	public SearchPage clickSearch() {
		try 
		{
			click(btnSearch, "Search button");
			TestUtilities.logReportPass("Able to click on Search button");
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}

}

package web.pages;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.core.pattern.EqualsIgnoreCaseReplacementConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;

import api.pojo.DiscoveryFeed.ContentDetailsController;
import api.pojo.DiscoveryFeed.DiscoverFeedController;
import web.tests.TestConfiguration;

public class DiscoverPage extends ActionEngine {
	public static By quickLinksTab = By.cssSelector("a[class*=QuickLinkButton]");
	public static By collectionName=By.cssSelector("[class*='typo__Title3Heading']");
	public static By collectionBackArrow =By.cssSelector("[class^='mobile'] [class^='HeaderBackButton'] a");
	public static By spotLightTab = By.cssSelector(".e2e-spotlight");
    public static By spotlight = By.cssSelector("[class*='swipeable-view-container']");
    public static By spotlightMobileTitle = By.cssSelector("[class*='SpotlightMobileTitle']");
    public static By spotlightDiscoverTitle = By.xpath("//*[@id='mount']/div[1]/div/div[1]/div/div[1]/div[1]/div/div[6]/div/a/div[2]");
    public static By spotlightText = By.xpath("//*[contains(@class,\"e2e-spotlight Spotlight__StyledSwipeableViews\")]/div/div[contains(@aria-hidden,'false')]/div/a/div[2]");
    public static By spotLightArrowFwd = By.xpath("//div[contains(@class,\"SpotlightArrowNavigation__ShowNavOnHoverContainer\")]/div[2]");
    public static By spotLightArrowBk = By.xpath("//div[contains(@class,\"SpotlightArrowNavigation__ShowNavOnHoverContainer\")]/div[1]");
    
    public static By signupBtn = By.cssSelector("[class*='label']");
    public static By footerSignUP = By.xpath("(//*[@href='/auth/signup'])[2]");
    //public static By seeAllLnkFstCollection = By.cssSelector("[class*='e2e-seeAll']");
    public static By seeAllLnkFstCollection = By.cssSelector("[class*='SeeAllLink']");
    public static By showMoreLnkFstCollection = By.xpath("//*[@id='mount']/div[1]/div/div[1]/div/a[contains(@class,'e2e-showMore')]");

    public static By titlePageTitle = By.xpath("//header[1]/div/div/div/a/span");
    //public static By BackArrow=By.cssSelector("[class^=\"mobile\"] [class^=\"HeaderBackButton\"] a");
    public static By BackArrow=By.cssSelector("[class^=\"HeaderBackButton\"] a");
	public static By showMoreFstCollection=By.xpath("(//div[contains(@class, 'e2e-collectionGrid Card__Group-sc-1lby5b9-3')])[3]/a");
	public static By movieTitlefstDisc=By.xpath("(//img[@class='Card__CardImg-s1lby5b9-1 clppcs'])[1]");
	public static By episodeName=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>h3");
	public static By episodeDuration=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>h3+div");
	public static By episodeDetail=By.cssSelector("[class*='EpisodeList__Container']>li:nth-of-type(1)>div>p");
	public static By tabIndicator=By.xpath("//div[contains(@class, 'Tab__Indicator-augbew-3 fWqQBx')]");
	public static By loginTitleEV=By.xpath("//h3[text()='Login']");
	public static By pricingtitle=By.xpath("//h1[text()='PRICING']");
	public static By pricingLink=By.xpath("//a[text()='Pricing']");
	public static By passwordLnk=By.xpath("//a[text()='Enter Password']");
	public static By doneButton=By.xpath("//button[@id='submit-button']");
	public static By okButton=By.xpath("//button[@id='submit-button']");
	public static By btnContinue=By.xpath("//a[@id='submit-button']");
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
	public static By novaPageValidation = By.xpath("//div[@class='paymentPartnerInfo__payment-wrapper___2FUDu']/img");

	// Discover
	public static By DiscoverCollection = By.xpath(".//*[@class='galleria-section-header']");
	public static By DiscoverCollectionTitle = By.xpath(
			"//*[contains(@class,'ScrollRestorer__VisibilityContainer')]/div[6]//span[contains(@class,'e2e-gridTitle')]");

	public static By DiscoverCollectionTitleList = By.xpath(".//*[@class='titles-list']");
	public static By lnkSingleTitle = By.xpath(".(//div[text()='MOVIE'])[1]");
	public static By lnkSingleTitleSeeMore = By.xpath("(//a[text()='Popular Films']/../div/div/div[4])[1]"); 
	public static By lblSingleTitleHeader = By.xpath("//*[@class='content-wrapper']/div[2]/h2");
	public static By btnFavorite = By.xpath("//button[@class='btn btn-action-icon btn-icon-favorite']");
	public static By btnRemoveFavorite = By
			.xpath("//*[@class='btn btn-action-icon btn-action-selected btn-icon-favorite-selected']");
	public static By btnWatchLater = By.xpath("//button[@class='btn btn-action-icon btn-icon-watch-later']");
	public static By btnRemoveWatchLater = By
			.xpath("//button[@class='btn btn-action-icon btn-action-selected btn-icon-watch-later-selected']");
	public static By lnkViewDetails = By.xpath("//a[text()='View Details']"); 

	public static By imgContent = By.xpath("(//div[text()='MOVIE'])[1]/../div[1]");
	public static By btnAddToFav = By.xpath("//*[@id='btnAddToFavs']");
    public static By sellAllFrstCollection= By.cssSelector("[class*='Collection__CollectionContent']>div:nth-of-type(1)>a:nth-of-type(1)>div>div>img");

    public static By contentTitleTxt = By.cssSelector("[class*='e2e-titleHeader']");
   
    public static By playerwindow = By.xpath("//*[contains(@class,'VideoComponent__VideoWrapper-adotv8-0')]//div/div");
    public static By playerwindow1 = By.xpath("//*[contains(@class,'VideoComponent__VideoWrapper-adotv8-0')]");
    
    // epg
    public static By currentTitle = By.cssSelector(".epg-program-now .epg-program-name");
    public static By nextTitle = By.cssSelector(".epg-program-next .epg-program-name");
    
    // Oops something went wrong
    public static By refreshButton = By.cssSelector("[class*='ErrorState__ActionButton']");

    /***
	 * Function Name :- verifyQuickLinks Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifyQuickLinks() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			waitForElementClickable(quickLinksTab, "quickLinksTab");			
			List<WebElement> lst = driver.findElements(quickLinksTab);
					
			for (int i = 0; i < (lst.size()); i++) {
				
				//Thread.sleep(5000);
				lst = driver.findElements(quickLinksTab);
				if (lst.size() == 0) {
					int lstRetry = 0;
					while (lst.size() == 0 && lstRetry < 5) {
						ReporterLog.info("quicklinkstab","LIST size == 0, refreshing page to recover. Recovery Attempt "+(lstRetry+1)+"/5");
						driver.navigate().to(ConfigDetails.strURL);
						waitForElementClickable_NEW(quickLinksTab, "quickLinksTab");	
						//Thread.sleep(2000);
						lst = driver.findElements(quickLinksTab);
						lstRetry ++;
					}
				}
				
				String menuTabsTxt = "";
				String destURL = "";
				
				Boolean success = false;
				Integer maxAttempts = 5;
				Integer tryNum = 0;
				
				while (success == false && (tryNum < maxAttempts)) {
					try {
						//ReporterLog.info("QuickLinks", "Entered try block");
						ReporterLog.info("Quicklinks", "Looking for link "+(i+1)+"/"+lst.size());
						menuTabsTxt  = driver.findElements(quickLinksTab).get(i).getText();
						ReporterLog.info("Quicklinks", "link "+(i+1)+"/"+lst.size()+" collection name is: '"+menuTabsTxt+"'");
						destURL = driver.findElements(quickLinksTab).get(i).getAttribute("href");
/*						//Thread.sleep(5000);						
						ReporterLog.info("Quicklinks", "About to click link "+(i+1)+"/"+lst.size()+" to go to URL: "+destURL);
						driver.findElements(quickLinksTab).get(i).click();
						//Thread.sleep(5000);
						ReporterLog.info("Quicklinks", "Clicked link "+(i+1)+"/"+lst.size());*/
						ReporterLog.info("Quicklinks", "About to follow link "+(i+1)+"/"+lst.size()+" to go to URL: "+destURL);
						driver.navigate().to(destURL);
						success = true;
					} catch (Exception e) {
						e.printStackTrace();
						tryNum ++;
						//ReporterLog.info("Quick Link Error","trynum == "+tryNum+", maxAttempts == "+maxAttempts);
						if (tryNum < maxAttempts){
							ReporterLog.info("Quick Link Error","Error accessing Quicklink - Retrying: attempt "+tryNum+"/"+maxAttempts);
							driver.navigate().refresh();
							Thread.sleep(3000);
							success = false;
							continue;
						} else {
							ReporterLog.fail("Quick Link Error","Error accessing Quicklink - FAILING After "+maxAttempts+" unsuccessful attempts.");
						}
					}
				}

				//ReporterLog.info("QuickLinks", "Exited try block: "+"trynum == "+tryNum+", maxAttempts == "+maxAttempts);
				
				tryNum = 0;
				success = false;
				
				while (success == false && (tryNum < maxAttempts)) {
					try {
						Thread.sleep(1000);
						if (isNumOf_Elements_Greater_Than_Zero(refreshButton)) {
							//internet connection issue
							ReporterLog.info("check Collection", "'Oops, check your internet connection message' is displayed.");
							ReporterLog.info("check Collection", "proceeding to run mitigation");
							Boolean mitSuccess = false;
							int mitTries = 0;
							
							//from observation it appears that refreshing multiple times will 
							// eventually resolve the issue 
							while (!mitSuccess && (mitTries < 15)) {
								//ReporterLog.info("check Collection", "Refreshing driver");
								//driver.navigate().refresh();
								ReporterLog.info("check Collection", "Refreshing URL");
								driver.navigate().to(destURL);
								Thread.sleep(3000);
								mitTries ++;
								if (!isNumOf_Elements_Greater_Than_Zero(refreshButton)) {
									ReporterLog.info("check Collection", "driver Refresh Successful!");
									mitSuccess = true;
								} else {
									ReporterLog.info("check Collection", "Error Message still displayed after "+mitTries+" refresh attempts.");
								}
							}
							
							// if refreshing does not resolve the issue, attempt to explicitly load 
							// the target URL
							if (!mitSuccess) {
								ReporterLog.info("check Collection", "driver refresh STILL unsuccessful, trying to explicitly load url");
								driver.navigate().to(destURL);
								Thread.sleep(5000);
							}
							
							// if all else fails, then throw error and retry script
							if (isNumOf_Elements_Greater_Than_Zero(refreshButton)) {
								ReporterLog.info("check Collection", "mitigation still unsuccessful - throwing exception");
								throw new Exception("Internet Broke");
							}
							
						} else {
							waitForElementClickable_NEW(collectionName, "collectionName");
							String collectionNme = getText_NEW(collectionName, "Collection name is displayed");
							ReporterLog.pass("Verify Quick links tabs on Discover page",
									menuTabsTxt + " is displayed successfully and clicked ");
							success = true;
							if (menuTabsTxt.contains(collectionNme)) {
								ReporterLog.pass("Verify Quick links Collection name",
										collectionNme + " is displayed successfully");
								driver.navigate().to(ConfigDetails.strURL);
								ReporterLog.info("Collection Name Get URL", driver.getCurrentUrl());
								if (partialTextMatch(driver.getCurrentUrl(), "error")){
									getUrlErrorDetails();
								}
							} else {
								ReporterLog.warning("Verify Quick links Collection name", collectionNme + " is not displayed");
								driver.navigate().to(ConfigDetails.strURL);
								ReporterLog.info("Collection Name Get URL", driver.getCurrentUrl());
								if (partialTextMatch(driver.getCurrentUrl(), "error")){
									getUrlErrorDetails();
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						tryNum ++;
						//ReporterLog.info("Quick Link Error","trynum == "+tryNum+", maxAttempts == "+maxAttempts);
						if (tryNum < maxAttempts){
							ReporterLog.info("Quick Link Error","Error accessing Quicklink COLLECTIONS - Retrying: attempt "+tryNum+"/"+maxAttempts);
							driver.navigate().refresh();
							Thread.sleep(3000);
							success = false;
							continue;
						} else {
							ReporterLog.fail("Quick Link Error","Error accessing Quicklink COLLECTIONS - FAILING After "+maxAttempts+" unsuccessful attempts.");
						}						
					}
				}
			}
				
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	public static void getUrlErrorDetails() {
		//ReporterLog.info("Get User Agent Details", "Error encountered launching URL - Expected: "+ConfigDetails.strURL+" - Actual: "+driver.getCurrentUrl());
		ReporterLog.info("Get User Agent Details", "Navigating to https://www.hooq.tv/api/user-store to get error details");
		
		try {
			driver.navigate().to("https://www.hooq.tv/api/user-store?r="+FileUtilities.GetCurrentTimeStamp());
			Thread.sleep(5000);
		} catch (Exception e) {
			ReporterLog.info("User Details", "Failed to get user info");
		}
		
		String json = getPageSource();
		ReporterLog.info("User Info", "JSON body of GET request is: "+json);
		driver.navigate().to(ConfigDetails.strURL);
	}
	
	
	/***
	 * Function Name :- verifySpotLight Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifySpotLight() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			TestConfiguration.basePage.getDiscoverPage();
			List<WebElement> lst = driver.findElements(spotLightTab);
			for (int i = 1; i <= lst.size(); i++) {
				By strtElement = By.xpath(
						"(//div[@class='HorizontalCard__ImageContainer-sc-1lyllnp-2 kmxnSw']/following-sibling::div[1])["
								+ i + "]");
				waitForElementClickable(strtElement, "strtElement");
				WebElement strt = driver.findElement(strtElement);
				strt.click();
				By connectionNameElement = By.xpath("(//span[contains(@class,'typo__Title3Heading-tc92ae-4')])[1]");
				waitForElementClickable(connectionNameElement, "connectionNameElement");
				String collectionName = getText(connectionNameElement, "Spotlight name is displayed");
				ReporterLog.pass("Verify Spotlight links Collection name", collectionName + " is dispalyed successfully");
				driver.navigate().to(ConfigDetails.strURL);
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifySpotlight Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifySpotlight(String userType) { 
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			//String spotlightStr = getText(spotlight, "getext of first spolight");
			String spotlightStr = getAttributeValue(spotlight, "title","getext of first spotlight");
			ReporterLog.info("Discover Page", "Spotlight Titlename is: "+spotlightStr);
			click(spotlight, "Click on spotlight"); 
			String contentName = getText(contentTitleTxt, "gettext of content title");
			if (spotlightStr.equalsIgnoreCase(contentName)) {
				ReporterLog.pass("Verify content title in content details page",
						"" + spotlightStr + "" + "" + contentName + "" + " is Matched successfully");
				
				if (isNumOf_Elements_Greater_Than_Zero(watchNowbtn)) {
						click(watchNowbtn, "Clicked on Play button");
					
					if (userType.equalsIgnoreCase("Anonymous")) {
						
						ReporterLog.info("spotlight", "User type = "+userType);
						if (isNumOf_Elements_Greater_Than_Zero(signupBtn)) {
							String signupPage = getText(signupBtn, "Sign UP Page");
							ReporterLog.pass("Verify Sign up", "Redirected to '" + signupPage
									+ "' and is displayed successfully for Anonymous user");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(
								By.xpath("//div[contains(@class,'PromptModalBase__ModalButtonGroup-sc-1')]/a[1]"))) {
							ReporterLog.pass("Verify movie player for Anonymous user",
									"Signup page is displayed successfully while playing the Movie for the Anonymous user ");
							click(By.xpath("//div[contains(@class,'PromptModalBase__ModalButtonGroup-sc-1')]/a[2]"),
									"Click on continue watching");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(By.xpath("//div[@id='trailerPlayer']"))) {
							ReporterLog.pass("Verify Movie for Anonymous user",
									"Signup page is not displayed while playing the trailer");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
							click(playerwindow, "Click on player window");
							ReporterLog.pass("Verify movie player for Active user",
									"Player window is displayed successfully while playing the Movie");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else {
							ReporterLog.fail("Verify Movie for Anonymous user",
									"Signup page is not displayed while playing the content with anonymous user");
							
						}
					} else if (userType.equalsIgnoreCase("Active")) {
						if (isNumOf_Elements_Greater_Than_Zero(By.xpath("//div[@id='trailerPlayer']"))) {
							ReporterLog.pass("Verify video player", "Able to play the trailer");
							ReporterLog.pass("Verify Movie for Active user",
									"Trailer window is displayed for Active user from discover page");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
							click(playerwindow, "Click on player window");
							ReporterLog.pass("Verify movie player for Active user",
									"Player window is displayed successfully while playing the Movie");
							driver.navigate().to(ConfigDetails.strURL);
							
							if (isNumOf_Elements_Greater_Than_Zero(watchNowbtn)) {
								ReporterLog.pass("Verify content deatails",
										"content deatils page is displayed after clicking on back button");
								driver.navigate().to(ConfigDetails.strURL);
							} else {
								ReporterLog.fail("Verify movie player for Active user",
										"Player window is not displayed while playing the Movie");
								
							}
						} else {
							ReporterLog.fail("Verify player for Active user",
									"Player window is not displayed for Active user");
							
						}

					} else if (userType.equalsIgnoreCase("Lapsed")) {
						if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
							ReporterLog.pass("Verify movie player for Lapsed user",
									"Nova planselector page is displayed successfully while playing the Movie");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
							ReporterLog.pass("Verify video player", "Able to play the trailer");
							ReporterLog.pass("Verify trailer for Lapsed user",
									"Signup page is not displayed while playing the trailer");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(footerSignUP)) {
							driver.navigate().to(ConfigDetails.strURL);
							String url = driver.getCurrentUrl();
							ReporterLog.pass("Verify content details URL", url + " displayed successfully");
							
						} else {
							ReporterLog.fail("Verify movie player for Lapsed user",
									"Nova planselector page is not displayed while playing the Movie");
							
						}
					}
				}
			} else {
				ReporterLog.fail("Verify content title in content details page",
						"" + spotlight + "" + "" + contentName + "" + " are not Matching");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}
	
	public void verifySpotlight_NEW(String userType) { 
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {

			String spotlightStr=getText(spotlightDiscoverTitle, "Spotlight Discover Text");	

			ReporterLog.info("Name from Discover page", "is: "+spotlightStr);
			ReporterLog.info("spotlight", "Clicking on Spotlight element");
			
			ActionEngine.JSClick(driver.findElement(spotlightDiscoverTitle), "JS click on spotlight");
			
			//now we should be on the title page but wait for page load
			Thread.sleep(3000);

			String contentName = getText(contentTitleTxt, "gettext of content title");
			ReporterLog.info("Name from title page", "is: "+contentName);
			
			//testcase, make sure we are on same title's title page
			if (spotlightStr.equalsIgnoreCase(contentName)) {
				ReporterLog.pass("Verify content title in content details page",
						"" + spotlightStr + "" + "" + contentName + "" + " is Matched successfully");
				
				String titleContentLevel = ContentDetailsController.getContentdetailsObject(DiscoverFeedController.getContentId(contentName).get(0)).getData().getContent_level();
				ReporterLog.info("Spotlight title page", "Title Content Level is: "+titleContentLevel);
				
				
				if (isNumOf_Elements_Greater_Than_Zero(watchNowbtn)) {
					
					//Since we don;t know if it is a 'subscribe to watch' or 'watch now' at this time, click and see where we go
					click(watchNowbtn, "Clicked on Play button");
					Thread.sleep(5000);
					waitForInVisibilityOfElement_NEW(watchNowbtn, "watchNowbtn");
					
					if (userType.equalsIgnoreCase("Visitor")) {
						
						//String titleContentLevel = ContentDetailsController.getContentdetailsObject(DiscoverFeedController.getContentId(contentName).get(0)).getData().getContent_level();
							
						if (isNumOf_Elements_Greater_Than_Zero(signupBtn)) {
							if (titleContentLevel.equalsIgnoreCase("30")){
								String signupPage = getText(signupBtn, "Sign UP Page");
								ReporterLog.pass("Verify Sign up", "Redirected to '" + signupPage
										+ "' and is displayed successfully for Anonymous user");
							} else {
								String signupPage = getCurrentUrl();
								ReporterLog.fail("Verify Sign up", "Expected to be redirected to signupPage for Anonymous user but reached "+signupPage+" instead.");
							}
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(
								By.xpath("//div[contains(@class,'PromptModalBase__ModalButtonGroup-sc-1')]/a[1]"))) {
							if (titleContentLevel.equalsIgnoreCase("30")) {
								ReporterLog.pass("Verify movie player for Anonymous user",
										"Signup page is displayed successfully while playing the Movie for the Anonymous user ");
								click(By.xpath("//div[contains(@class,'PromptModalBase__ModalButtonGroup-sc-1')]/a[2]"),
										"Click on continue watching");
							} else {
								String whereamI = getCurrentUrl();
								ReporterLog.fail("Verify title plays", "Expected should be able to play title but instead was directed to: "+whereamI);
							}
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(By.xpath("//div[@id='trailerPlayer']"))) {
							ReporterLog.pass("Verify Movie for Anonymous user",
									"Signup page is not displayed while playing the trailer");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
							if (titleContentLevel.equalsIgnoreCase("10")) {
								click(playerwindow1, "Click on playerwindow1");
								ReporterLog.pass("Verify movie player for Anonymous",
										"Player window is displayed successfully while playing the Movie");
							} else {
								ReporterLog.fail("Verify movie player for Anonymous user",
										"Expected should not be able to play the title, yet can.");
							}
							driver.navigate().to(ConfigDetails.strURL);
							
						} else {
							if (titleContentLevel.equalsIgnoreCase("30")) {
								ReporterLog.fail("Verify Movie for Anonymous user",
										"Signup page is not displayed while playing the content with anonymous user");
							} else {
								ReporterLog.pass("Verify Movie for Anonymous user",
										"Visitor is able to play a title of content level "+titleContentLevel+" successfully.");
							}
								
						}
					} else if (userType.equalsIgnoreCase("Active")) {
						if (isNumOf_Elements_Greater_Than_Zero(By.xpath("//div[@id='trailerPlayer']"))) {
							ReporterLog.pass("Verify video player", "Able to play the trailer");
							ReporterLog.pass("Verify Movie for Active user",
									"Trailer window is displayed for Active user from discover page");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
							click(playerwindow, "Click on player window");
							ReporterLog.pass("Verify movie player for Active user",
									"Player window is displayed successfully while playing the Movie");
							driver.navigate().to(ConfigDetails.strURL);
							
							if (isNumOf_Elements_Greater_Than_Zero(watchNowbtn)) {
								ReporterLog.pass("Verify content deatails",
										"content deatils page is displayed after clicking on back button");
								driver.navigate().to(ConfigDetails.strURL);
							} else {
								ReporterLog.fail("Verify movie player for Active user",
										"Player window is not displayed while playing the Movie");
								
							}
						} else {
							ReporterLog.fail("Verify player for Active user",
									"Player window is not displayed for Active user");
							
						}

					} else if (userType.equalsIgnoreCase("Lapsed")) {
						if (isNumOf_Elements_Greater_Than_Zero(novaPageValidation)) {
							ReporterLog.pass("Verify movie player for Lapsed user",
									"Nova planselector page is displayed successfully while playing the Movie");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(playerwindow)) {
							ReporterLog.pass("Verify video player", "Able to play the trailer");
							ReporterLog.pass("Verify trailer for Lapsed user",
									"Signup page is not displayed while playing the trailer");
							driver.navigate().to(ConfigDetails.strURL);
							
						} else if (isNumOf_Elements_Greater_Than_Zero(footerSignUP)) {
							driver.navigate().to(ConfigDetails.strURL);
							String url = driver.getCurrentUrl();
							ReporterLog.pass("Verify content details URL", url + " displayed successfully");
							
						} else {
							ReporterLog.fail("Verify movie player for Lapsed user",
									"Nova planselector page is not displayed while playing the Movie");
							
						}
					}
				}
			} else {
				ReporterLog.fail("Verify content title in content details page",
						"spotlightText: '" + spotlightStr + "' and " + "contentTitleTxt: '" + contentName + "'" + " are not Matching");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifyBackArrow Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifySeeAllBackNavigation() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {

			if (isNumOf_Elements_Greater_Than_Zero(seeAllLnkFstCollection)) {
				
				String seeAllText = getText(seeAllLnkFstCollection, "seeAllLnkFstCollection");
				
				if(seeAllText.toString().toLowerCase().contains("see all"))
					ReporterLog.pass("Verify See All link ", "See All link is displayed successfully");
				else
					ReporterLog.fail("Verify See All link ", "See All link is NOT displayed successfully");
				
				String discoverCollectionTitle = getText(DiscoverCollectionTitle, "DiscoverCollectionTitle");
								
				click(seeAllLnkFstCollection, "seeAllLnkFstCollection");	
					
				waitForElementClickable(collectionName,"collectionName");
				String collectionNameCDP = getText(collectionName,
						"Heading of collection is displayed successfully");
								
				if (discoverCollectionTitle.contains(collectionNameCDP)) {
					ReporterLog.pass("Verify content title in content details page",
							"" + discoverCollectionTitle + "" + "" + collectionNameCDP + "" + " is Matched successfully");

				} else {
					ReporterLog.fail("Verify content title in content details page",
							"" + discoverCollectionTitle + "" + "" + collectionNameCDP + "" + " contents are not Matched");
				}

				waitForElementClickable(sellAllFrstCollection,"sellAllFrstCollection");
				WebElement title = driver.findElement(sellAllFrstCollection);
				String fstTitle = title.getAttribute("title");

				click(sellAllFrstCollection, "First title in " + collectionNameCDP);
				
				waitForElementClickable(contentTitleTxt,"contentTitleTxt");
				String titlename = getText(contentTitleTxt,
						"Heading of collection is displayed successfully");
				if (fstTitle.contains(titlename)) {
					ReporterLog.pass("Verify content title in content details page",
							"" + fstTitle + "" + "" + titlename + "" + " is Matched successfully");
				} else {
					ReporterLog.fail("Verify content title in content details page",
							"" + fstTitle + "" + "" + titlename + "" + " contents are not Matched");
				}
				
				
				if (isNumOf_Elements_Greater_Than_Zero(BackArrow)) {
						ReporterLog.pass("Verify Back Arrow", "Back Arrow is displayed successfully");
						click(BackArrow, "Back Arrow");

						if (isNumOf_Elements_Greater_Than_Zero(collectionName)) {
							ReporterLog.pass("Verify collection name ", " collection name is displayed successfully");
						} else {
							ReporterLog.fail("Verify collection name", "collection name is not displayed successfully");
						}
					} else {
						ReporterLog.fail("Click See All", "Unable to click See All");
						ReporterLog.fail("Verify Back Arrow", "Back Arrow is not displayed");
					}
					click(BackArrow, "Back Arrow");
					
				}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	
	/***
	 * Function Name :- verifySeeAll Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifySeeAll() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		
		try {
			ReporterLog.info("scroll into view", "attempting to scroll into view first");
			webScrollTillElementPresent(seeAllLnkFstCollection, true);
			//ReporterLog.info("sleepBaby", "Pausing for 15 seconds");
			//Thread.sleep(15000);
			if (isNumOf_Elements_Greater_Than_Zero(seeAllLnkFstCollection)) {
				ReporterLog.pass("Verify See All link ", "See All link is displayed successfully");
				
				int numElements = getElements(seeAllLnkFstCollection).size();

				ReporterLog.info("verifySeeAll", "Qty of 'SeeAll' links: "+numElements);
				
				// if more than 1 see all, the first is usually for live tv which isn't a collection per se
				// so skip first 'SeeAll' if more than 1 exists
				if (numElements > 1) {
					ReporterLog.info("seeAll", "Clicking on 2nd 'See All' instance (element 1 in list)");
					driver.findElements(seeAllLnkFstCollection).get(1).click();
				} else {
					ReporterLog.info("seeAll", "Clicking on 1st 'See All' instance (element 0 in list)");
					driver.findElements(seeAllLnkFstCollection).get(0).click();
				}
				
				
				waitForVisibilityOfElement(titlePageTitle, "Collection Page title");
				String collectionName = getText(titlePageTitle,
						"Heading of collection is displayed successfully");
				if (isNumOf_Elements_Greater_Than_Zero(BackArrow)) {
					ReporterLog.pass("See all collection Title is ",
							" '' " + collectionName + " '' " + " collection page is displayed successfully");
					ReporterLog.pass("Verify Back Arrow", "Back Arrow is displayed successfully");
					click(BackArrow, "Back Arrow");
					
					
				} else {
					ReporterLog.fail("Verify Back Arrow", "Back Arrow is not displayed");
				}
			} else {
				ReporterLog.fail("Verify See All link ", "See All link is not displayed successfully");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
	}

	/***
	 * Function Name :- verifyQuickLinks_Details Developed By :- Indraja Reddy Date
	 * :- 23-May-2019
	 */
	public void verifyQuickLinks_Details() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			if (isNumOf_Elements_Greater_Than_Zero(quickLinksTab)) {
				List<WebElement> lst = driver.findElements(quickLinksTab);
				for (int i = 1; i <= lst.size(); i++) {
					WebElement strt = driver.findElement(By.xpath(
							"//a[contains(@class,'QuickLinkButton__QuickLinkButtonContainer-dvnzd2-0')][" + i + "]"));
					String menuTabsTxt = getText(By.xpath(
							"//a[contains(@class,'QuickLinkButton__QuickLinkButtonContainer-dvnzd2-0')][" + i + "]"),
							"");
					strt.click();
					String collectionName = getText(
							By.xpath("(//span[contains(@class,'typo__Title3Heading-tc92ae-4')])[1]"),
							"Collection name is displayed");
					if (menuTabsTxt.contains(collectionName)) {
						ReporterLog.pass("Verify Quick links Collection name",
								collectionName + " is displayed successfully");
						driver.navigate().to(ConfigDetails.strURL);
					} else {
						ReporterLog.fail("Verify Quick links Collection name", collectionName + " is not displayed");
						driver.navigate().to(ConfigDetails.strURL);
					}
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");

	}

	/***
	 * Function Name :- verifyShowMore Developed By :- Indraja Reddy Date :-
	 * 23-May-2019
	 */
	public void verifyShowMore() {
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {

			waitTillElementPresent_HardWait_Polling(showMoreLnkFstCollection, 5);
			WebElement element = driver.findElements(showMoreLnkFstCollection).get(0);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
			WebElement parent = element.findElement(By.xpath(".//parent::div"));
			
			List<WebElement> movieSize = parent.findElements(By.xpath(".//div[2]/a"));
						
				System.out.println("Movies Size in the Collection before click on Show More .. "+movieSize.size());

				click(element, "Show More Link");
				
				movieSize = parent.findElements(By.xpath(".//div[2]/a"));
				
				System.out.println("Movies present after Click : "+ movieSize.size());

				if (movieSize.size() >= 7) {
					ReporterLog.pass("Verify Show More Collection Title","7 or More Than 7 collection titles are displayed ");
				} else {
					ReporterLog.fail("Verify Show More Collection Title","Less than 7 collection titles are displayed ");
				}
		}
			catch (Exception e) {
				TestUtilities.logReportFailure(e);
			}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");
		
	}

	public ContentDetailsPage getContentDetailsPage() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			
			TestUtilities.logReportPass("");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}


}

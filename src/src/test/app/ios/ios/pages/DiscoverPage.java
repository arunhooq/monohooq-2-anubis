package ios.pages;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class DiscoverPage extends ActionEngine {
	//Locators
	public static By meLabel = By.id("icon me");
	public static By hooqLogo = By.id("logo");
	public static By universalSearch = By.id("icon search");
	public static By download = By.name("icon download");
	public static By listBtnQuickLink = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[2]");

	// Quick link page
	public static By btnNavigationBack = By.xpath("//XCUIElementTypeOther/XCUIElementTypeNavigationBar/XCUIElementTypeButton");
	public static By listTitle = By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText/XCUIElementTypeStaticText");
	//public static By listMovies = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[1]");
	public static By listMovies = By.xpath("//XCUIElementTypeCollectionView");
	
	//Continue Watching
	public static By txtContinueWatchingContentLabel = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeStaticText[1]");
	public static By btnContinueWatchingContentNav = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeButton[1]");
	
	//Spotlight
	public static By btnSpotlightArrow_Right =By.id("arrow Spotlight Right");
	public static By btnSpotlightArrow_Left =By.id("arrow Spotlight Left");
	public static By spotlightImage = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[1]");
	
	
	public MePage getMePage() {
		try {
			waitForElementClickable(meLabel, "Me Icon");
			click(meLabel, "Me Icon");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return new MePage();
	}

	/***
	 * Function Name :- fnSelectDiscover Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public void fnSelectDiscover(String strTab) {
		try {
			List<WebElement> eleList = driver.findElement(By.xpath("//XCUIElementTypeTabBar"))
					.findElements(By.xpath("XCUIElementTypeButton"));
			if (eleList.size() == 5) {
				if (strTab.toLowerCase().startsWith("disc")) {
					eleList.get(0).click();
				} else if (strTab.toLowerCase().startsWith("livetv")) {
					eleList.get(1).click();
				} else if (strTab.toLowerCase().startsWith("movies")) {
					eleList.get(2).click();
				} else if (strTab.toLowerCase().startsWith("tvshows")) {
					eleList.get(3).click();
				} else if (strTab.toLowerCase().startsWith("rent")) {
					eleList.get(4).click();
				}
			} else {
				if (strTab.toLowerCase().startsWith("disc")) {
					eleList.get(0).click();
				} else if (strTab.toLowerCase().startsWith("movies")) {
					eleList.get(1).click();
				} else if (strTab.toLowerCase().startsWith("tvshows")) {
					eleList.get(2).click();
				} else if (strTab.toLowerCase().startsWith("rent")) {
					eleList.get(3).click();
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	public DiscoverPage verifyHOOQLogo() {
		isElementDisplayed(hooqLogo);
		return new DiscoverPage();
	}

	public DiscoverPage verifyDownloadIcon() {
		isElementDisplayed(download);
		return new DiscoverPage();
	}

	public DiscoverPage verifySearchButton() {
		isElementDisplayed(universalSearch);
		return new DiscoverPage();
	}

	public DiscoverPage verifyMeIcon() {
		isElementDisplayed(meLabel);
		return new DiscoverPage();
	}


	public DiscoverPage verifyQuickLinkPage() throws InterruptedException {

		Thread.sleep(10000);

		List<MobileElement> ele = getMobileElement(listBtnQuickLink).findElements(By.xpath(".//XCUIElementTypeButton"));

		//TODO - For Now validating for only 3 quick Links ,Once Appium Version upgraded to latest then we can perform on all quick links 
		for (int i = 0; i <= 3;i++) {
			
			ReporterLog.info("Quick Link validation ", "Executing quickLink # "+i);
			Thread.sleep(10000);
			
			String quickLinktext = ele.get(i).getText();
			// Click on the QuickLink button
			//touch.tap(ele.get(i));
			
			ele.get(i).click();
			
			ReporterLog.info("QuickLinkText","Text is : "+ quickLinktext);
			
			// Waiting for Quick link page to load
			waitTillElementPresent_HardWait_Polling(listTitle, GlobalConstant.WAIT_SHORT_5_SEC);
			
			//Validate QuickLink Movies Page visible
			isElementDisplayed(listTitle);

			// Validating QuickLink text with Group title
			verifyText(quickLinktext, getText(listTitle, "Group title"));

			// Validate Videos present
			List<MobileElement> movies = getMobileElement(listMovies).findElements(By.xpath(".//XCUIElementTypeCell/XCUIElementTypeOther[1]"));
			ReporterLog.info("Numbers of Movies : ", String.valueOf(movies.size()));

			// Navigating to the Movies Content details page
			click(movies.get(0), "Click First movie");

			// Validate Content details Page
			(new ContentDetailsPage()).verifyMovieContentDetailsElementVisibility();

			// Close Content details page
			click(ContentDetailsPage.btnClose, "Close Content details page");

			// Waiting for Quick link page to load
			waitForVisibilityOfElement(listTitle, "Group Title");

			// Click on the Back Navigation button
			click(btnNavigationBack, "Back button");

			// Validate Discover page is displayed
			isElementDisplayed(universalSearch);
			ReporterLog.pass("Verify QuickLink Navigation", "Quick link navigation successfull");
			break; //For now checking only one QuickLink Tab

		}
		return new DiscoverPage();
	}
	
	public ContentDetailsPage verifyContinueWatchingSection() {
		try {
		waitTillElementPresent_HardWait_Polling(MePage.meLabel,GlobalConstant.WAIT_SHORT_5_SEC);
		//Verify Me Icon Present
		isElementDisplayed(MePage.meLabel);
		ReporterLog.logScreenshot("ContinueWatchingsection");
		
		//Get Title Text and Validate		
		waitTillElementPresent_HardWait_Polling(txtContinueWatchingContentLabel, GlobalConstant.WAIT_SHORT_5_SEC);	
		String contentLabel = getText(txtContinueWatchingContentLabel, "Continue watching Content Label");
		ReporterLog.info("Validate Label ", "Continue watching watching content Label is : "+contentLabel);
		
		//Click on the Continue Button to navigate to Content details
		click(btnContinueWatchingContentNav, "Continue watch Nav Button");	
		
		verifyTextContains(ContentDetailsPage.txtMovieTitle, ReadTestData.TV_SERIES, "TVShow Title");
		//Click to run the Video and Validate The title
		new ContentDetailsPage().clickWatchNow().verifyVideoTitleName();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ContentDetailsPage();
	}
	
	
	public  void verifySpotLightNavigation() {
		try {
		List<String> spotlightTitles = DiscoverFeedController.getSpotlightContentList();
			System.out.println("Returned expected Spotlight titles are  :  "+spotlightTitles);
		for(String title :spotlightTitles) {
		
		System.out.println("***********  Checking for the title ********** : "+title);
		//click on the Spotlight
		click(spotlightImage, "Spotlight Image");
		 isElementDisplayed(ContentDetailsPage.btnWatchNow,"WatchNow button");
		 String ContentTitle = getText(ContentDetailsPage.txtMovieTitle, "Content Title");
		 
		 VerifyThatIsTrue(spotlightTitles.contains(ContentTitle), "Spotlight title matched successfully ..======   "+ContentTitle);
		
		 //Need to check playback as well
		 click(ContentDetailsPage.btnClose, "Content Close");
		 
		 isElementDisplayed(btnSpotlightArrow_Right,"btnSpotlightArrow_Right");
		 click(btnSpotlightArrow_Right, "btnSpotlightArrow_Right");
		}
		 
		}catch(Exception e) {
		e.printStackTrace();
		ReporterLog.fail("verifySpotLightNavigation", "Spotlight Verification failed  : "+e.getMessage());
	}
	}

}

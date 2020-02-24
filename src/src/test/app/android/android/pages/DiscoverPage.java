package android.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;


public class DiscoverPage extends ActionEngine{
	
	public static By meProfile = By.id("tv.hooq.android:id/ivMeBtn");	
	public static By spotlight = By.id("tv.hooq.android:id/iv_spotlight_image");	
	public static By spotlightContentTitle = By.id("tv.hooq.android:id/tv_spotlight_title");	
	public static By spotlightContentType = By.id("tv.hooq.android:id/tv_spotlight_title_type");	
	public static By contentTitle = By.id("tv.hooq.android:id/txtAssetTitle");
	public static By pageTitle = By.id("tv.hooq.android:id/tvLabel");
	public static By closeQuicklinkPage = By.id("tv.hooq.android:id/ivBack");
	public static By btnSearch = By.id("tv.hooq.android:id/search");
	public static By closeContentPage = By.id("tv.hooq.android:id/imgCloseAll");
	public static By channelsTab = By.id("tv.hooq.android:id/imgChannels");
	public static By rentTab = By.id("tv.hooq.android:id/imgPremium");
	public static By liveTVChannel = By.id("tv.hooq.android:id/name");	
	public static String channelName = null;
	public static String ribbon = null;
	
	public MePage clickMeProfile() {
		try 
		{
			click(meProfile, "Me profile");
			closeInterstitialAd();
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new MePage();
	}
	
	public DiscoverPage getSpotlightContentTitle() {
		try 
		{
			//String strContentTitle = null;
			if(isNumOf_Elements_Greater_Than_Zero(spotlight))
				getText(spotlightContentTitle, "Spotlight Content title");			
			else
				throw new Exception();
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}
	
	public DiscoverPage getSpotlightContentType() {
		try 
		{
			if(isNumOf_Elements_Greater_Than_Zero(spotlight))
				getText(spotlightContentType, "Spotlight Content type");
			else
				throw new Exception();
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}
	
	public ContentDetailsPage clickOnSpotlight() {
		try 
		{
			String strSpotlightContentTitle = null;
			strSpotlightContentTitle = getText(spotlightContentTitle, "Spotlight Content title");
			click(spotlight, "Spotlight Content");
			String strContentTitle = getText(contentTitle, "title");
			verifyText(strContentTitle,strSpotlightContentTitle);
			click(closeContentPage, "Close");
        } 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ContentDetailsPage();
	}
	
	public DiscoverPage swipeSpotlightFromLeftToRight() {
		try 
		{
			String strFirstSpotlightContentTitle = null;
			String strSpotlightContentTitle = null;
			isElementDisplayed(spotlight);
			
				strFirstSpotlightContentTitle = getText(spotlightContentTitle, "Spotlight Content title");
				
				while(true)
				{
					strSpotlightContentTitle = getText(spotlightContentTitle, "Spotlight Content title");
					clickOnSpotlight();
					Thread.sleep(5000);
					String strSpotlightContentTitleAfterCmgBack= getText(spotlightContentTitle, "Spotlight Content title");
					verifyText(strSpotlightContentTitle, strSpotlightContentTitleAfterCmgBack);
					mobileSwipeByElement("lefttoright", 5);
					String strSpotlightContentTitleAfterSwipe = getText(spotlightContentTitle, "Spotlight Content title");
					verifyTextNotEquals(strSpotlightContentTitle, strSpotlightContentTitleAfterSwipe);
					if(strSpotlightContentTitleAfterSwipe.equalsIgnoreCase(strFirstSpotlightContentTitle))
						break;
				}
								
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}
	
	public DiscoverPage swipeSpotlightFromRightToLeft() {
		try 
		{
			String strFirstSpotlightContentTitle = null;
			String strSpotlightContentTitle = null;

			isElementDisplayed(spotlight);			
			strFirstSpotlightContentTitle = getText(spotlightContentTitle, "Spotlight Content title");
			while(true)
			{				
				strSpotlightContentTitle = getText(spotlightContentTitle, "Spotlight Content title");
				clickOnSpotlight();
				String strSpotlightContentTitleAfterCmgBack= getText(spotlightContentTitle, "Spotlight Content title");
				verifyText(strSpotlightContentTitle, strSpotlightContentTitleAfterCmgBack);
				mobileSwipeByElement("righttoleft", 5);							
				String strSpotlightContentTitleAfterSwipe = getText(spotlightContentTitle, "Spotlight Content title");				
				verifyTextNotEquals(strSpotlightContentTitle, strSpotlightContentTitleAfterSwipe);

				if(strSpotlightContentTitleAfterSwipe.equalsIgnoreCase(strFirstSpotlightContentTitle))
					break;
			}
					
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}
	
	public DiscoverPage quickLinks() {
		try 
		{
			while(true)
			{
				List<WebElement> quickLinks = driver.findElements(By.id("tv.hooq.android:id/title"));
				String lastQuickLink = quickLinks.get(quickLinks.size()-1).getText();
				for (int i=0; i<quickLinks.size(); i++)
				{
					String strQuickLink = quickLinks.get(i).getText();
					click(quickLinks.get(i), "Quick link");
					closeInterstitialAd();
					String strTitle = getText(pageTitle, "Page Title");
					
					verifyText(strQuickLink, strTitle);
					click(closeQuicklinkPage, "Close");
				}
				mobileSwipeByElement("righttoleft", 2.45);	
				mobileSwipeByElement("righttoleft", 2.45);
				mobileSwipeByElement("righttoleft", 2.45);
				/*	Dimension size = driver.manage().window().getSize();				
			int startx = (int) (size.width * 0.70);
			int endx = (int) (size.width * 0.30);
			int starty = (int) (size.height / 2.45);

			//Swipe from Right to Left.
			((AndroidDriver<?>) driver).swipe(startx, starty, endx, starty, 3000);
			((AndroidDriver<?>) driver).swipe(startx, starty, endx, starty, 3000);
			((AndroidDriver<?>) driver).swipe(startx, starty, endx, starty, 3000);*/

				List<WebElement> toFindLastQuicklink = driver.findElements(By.id("tv.hooq.android:id/title"));
				if(lastQuickLink.equalsIgnoreCase(toFindLastQuicklink.get(toFindLastQuicklink.size()-1).getText()))
					break;
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new DiscoverPage();
	}	

	public SearchPage clickSearch() {
		try 
		{
			click(btnSearch, "Search button");
			closeInterstitialAd();
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new SearchPage();
	}

	public ChannelsPage clickChannelsTab() {
		try 
		{
			click(channelsTab, "Channels Tab");
			closeInterstitialAd();
		} 
		catch (Exception e) {;
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}	
	
	public RentPage clickRentTab() {
		try 
		{
			click(rentTab, "Rent Tab");
		} 
		catch (Exception e) {;
			TestUtilities.logReportFatal(e);
		}
		return new RentPage();
	}	
	
	public ChannelsPage playChannelFromLiveTVRow(String strTVChannelType) {
		try 
		{
			String strChannelType = getChannelType(strTVChannelType);
			swipeTill(strChannelType);
			mobileSwipeByElement("bottomtotop", 2);
			channelName = getTextOfAnElementInAList(liveTVChannel, 0);
			clickOnElementInAList(liveTVChannel, 0);
			closeInterstitialAd();
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ChannelsPage();
	}
	

	public String getChannelType(String strTVChannelType) {
		String strChannelType = null;
		try 
		{	
			if(strTVChannelType.equalsIgnoreCase("Live TV"))
			{
				if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_ID))
					strChannelType="Live TV";
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG))
					strChannelType="TV Channels";
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_PH))
					strChannelType="Quick TV";
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_TH))
					strChannelType="ดูสดทีวีออนไลน์";
			}
			else if(strTVChannelType.equalsIgnoreCase("uCast"))
			{
				if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_ID))
					strChannelType="Ragam Kanal Pilihan";
				else if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_TH))
					strChannelType="Non-stop ดูทีวี";
			}
			else
			{
				strChannelType = "Tonton TV Kabel";
			}
		} 
		catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return strChannelType;
	}
	
	
}

package web.pages;

import java.util.List;

import org.apache.logging.log4j.core.pattern.EqualsIgnoreCaseReplacementConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.ContentDetailsModel;
import sanctuary.utils.ReusableMethods;

public class ChannelsPage extends ActionEngine{
	
	public static By channelsPlayer = By.id("live-tv-player_html5_api");
	//public static By channelsPlayer = By.cssSelector("[class='vjs-live-control']");
	public static By channelsPlayerMuteButton = By.cssSelector("[class*='vjs-mute-control']");
	public static By channelsPlayerPlayOrPauseButton = By.cssSelector("[class*='vjs-play-control']");
	public static By channelsPlayerFullscreenButton = By.cssSelector("[class*='vjs-fullscreen-control']");
	public static By channelsPlayerLoadingSpinner = By.cssSelector("[class*='vjs-loading-spinner']");
		
	public static By channelsPlayerTopMuteButton = By.cssSelector("[class*='TapToUnmuteButton']");

	public static By channelsSignUpOverlay = By.cssSelector("[class*='BackgroundOverlay']");
	public static By channelsSignUpButton = By.cssSelector("[class*='PayWallcontent__SignUp']");
	public static By channelsLogInButton = By.cssSelector("[class*='PayWallcontent__Login']");
	//use select elements and element #1 for Skip
	public static By channelsSkipLink = By.cssSelector("[class*='PayWallcontent__PayWallLink']");
	
	public static By channelsPlayingNowTitle = By.cssSelector("[class*='LiveTvHeaderLayoutDisplay__LiveHeader']");
	public static By channelsPlayingNowImage = By.cssSelector("[class*='ChannelButtonImages']");
	public static By channelsSocialShareButton = By.cssSelector("[class*='SocialShareButton']");
	
	public static By channelsGroupTitle = By.cssSelector("[class*='ChannelGroupTitle']");
	public static By channelsGroupContainer = By.cssSelector("[class*='ChannelsContainer']");
	
	// epg
    public static By playerCurrentTitle = By.cssSelector(".epg-program-now .epg-program-name");
    public static By playerNextTitle = By.cssSelector(".epg-program-next .epg-program-name");
    //public static By pageCurrentTitle = By.cssSelector("[class*='EmbossedContainerBase'");
    public static By pageCurrentTitle = By.xpath("//*[@id=\"mount\"]/div[1]/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div[2]/div[1]/div[2]/div[1]");
    //public static By pageNextTitle = By.cssSelector("[class*='LiveTvChannelSchedule'] div:nth-of-type(3) div div");
    public static By pageNextTitle = By.xpath("//*[@id=\"mount\"]/div[1]/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div[2]/div[2]/div/div");
    //public static By pageEPG = By.cssSelector("[class*='EmbossedContainerBase']");
    //public static By pageEPG = By.xpath("//*[@id=\"mount\"]/div[1]/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div[2]/div[1]/div[2]");
    public static By pageEPG = By.xpath(".//img[contains(@class,'collapsible-arrow-icon')]/parent::div/following-sibling::div/div");
    public static By collapsibleArrow = By.xpath(".//img[contains(@class,'collapsible-arrow-icon')]");
    public static By playerEPGtitles = By.cssSelector(".epg-program-name");
    
    public static By now = By.cssSelector(".epg-program-now");
    public static By next = By.cssSelector("[class*='epg-program-next']");
    
    public static final String ANSI_RED = "\u001B[31m";
    
	public void verifyChannelsOLD() {
		try {
			String thisPage=driver.getCurrentUrl();
			waitForVisibilityOfElement(channelsGroupTitle, "Channels Group Titles");
			List<WebElement> channelGroupTitles = driver.findElements(channelsGroupTitle);
			ReporterLog.info("Get Channel Groups", "Qty of Channel Groups is: "+channelGroupTitles.size());
			
			for (int loopCount = 0; loopCount < channelGroupTitles.size(); loopCount ++) {
			
				String myTitle=getTextOfAnElementInAList(channelGroupTitles, loopCount);
				//String myTitle = "undefined";
				List<WebElement> els = driver.findElements(channelsGroupContainer);
				//ReporterLog.info("Get Channel Groups", "Qty of channelsGroupContainer is: "+els.size());
				
				List<WebElement> imgs = els.get(loopCount).findElements(By.tagName("img"));
				//ReporterLog.info("Get Channel Groups", "Qty of channels is: "+imgs.size());
				
				ReporterLog.info("verifyChannels", "Selecting each channel in channel Group: "+myTitle);
				
				for (int i=1; i< imgs.size(); i=i+2) {
					
					imgs = els.get(loopCount).findElements(By.tagName("img"));
					String channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
					ReporterLog.info("verify channels", "Verifying channel '"+channelName+"'");
					
					// testcase #1 - Assume if loading spinner appears and disappears that channel 
					// has started playing successfully
					click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
				
					int retryAttempt = 0;
					Boolean hallelujah = false;
					
					while (!hallelujah && retryAttempt < 5) {
						try {
							int delayCount = 0;
							Boolean spinnerPresent = false;
							Boolean overlayPresent = false;
							
							while (delayCount < 10 && ((spinnerPresent == false) && (overlayPresent == false))){
								spinnerPresent = isNumOf_Elements_Greater_Than_Zero(channelsPlayerLoadingSpinner);
								overlayPresent = isNumOf_Elements_Greater_Than_Zero(channelsSignUpButton);
								ReporterLog.info("verify channels","waiting for loading spinner or signup overlay to appear");
								delayCount++;
								if (!spinnerPresent && !overlayPresent && delayCount < 10) {
									Thread.sleep(500);
								}
							}
							
							ReporterLog.info("Play Channel", "spinnerPresent === "+spinnerPresent+", overlayPresent == "+overlayPresent);
							
							if (overlayPresent) {
								ReporterLog.info("verify channels", "Signup overlay detected - clicking 'skip'");
								driver.findElements(channelsSkipLink).get(1).click();
								ReporterLog.info("verify channels", "'skip' clicked.");
							}
							if (!spinnerPresent) {
								waitForVisibilityOfElement_NEW(channelsPlayerLoadingSpinner, "Loading Spinner");
							}
							waitForInVisibilityOfElement_NEW(channelsPlayerLoadingSpinner, "Loading Spinner");
							hallelujah = true;
						} catch (Exception e) {
							ReporterLog.info("verify channels","Spinner/Signup visibility check failed - retrying attempt "+(retryAttempt+1)+"/5");
							ReporterLog.info("Retry","Failure that invoked this retry attempt was: "+e);
							retryAttempt++;
							if(retryAttempt < 5) {
								ActionEngine.launchUrl_NEW(thisPage);
								waitForVisibilityOfElement(channelsGroupTitle, "Channels Group Titles");
								channelGroupTitles = driver.findElements(channelsGroupTitle);
								els = driver.findElements(channelsGroupContainer);
								imgs = els.get(loopCount).findElements(By.tagName("img"));
								click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
								channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
								continue;
							} else {
								ReporterLog.info("verify channels","Spinner/Signup visibility check failed - aborting since 5 unsuccessful attempts");
								ReporterLog.failAndContinue("", ""+e.getLocalizedMessage());
								throw new Exception(e);
							}			
						}
					}

					ReporterLog.pass("Play Channel", "Channel Group '"+channelName+"' - Channel '"+channelName+"' appears to have started successfully");
					
					// testcase #2 - Verify that channel title is displayed correctly
					channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
					verifyText(channelsPlayingNowTitle, channelName);
					ReporterLog.pass("verifychannels", "Page Title matches selected channel: "+channelName);
					
					click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");	
	}

	public void verifyChannels() {
		try {
			
			String thisPage=driver.getCurrentUrl();
			waitForVisibilityOfElement(channelsGroupTitle, "Channels Group Titles");
			List<WebElement> channelGroupTitles = driver.findElements(channelsGroupTitle);
			ReporterLog.info("Get Channel Groups", "Qty of Channel Groups is: "+channelGroupTitles.size());
			
			for (int loopCount = 0; loopCount < channelGroupTitles.size(); loopCount ++) {
				ReporterLog.info("Debug","=========================================== Start Initial Loop through channelgroups ========================");
				
				//testcase for verify channels
				ReadTestData.fnAddTestRailScriptID(22971);		
			
				String myTitle=getTextOfAnElementInAList(channelGroupTitles, loopCount);

				List<WebElement> els = driver.findElements(channelsGroupContainer);

				
				List<WebElement> imgs = els.get(loopCount).findElements(By.tagName("img"));
				
				ReporterLog.info("verifyChannels", "Selecting each channel in channel Group: "+myTitle);
				
				for (int i=1; i< imgs.size(); i=i+2) {
					
					//testcase for verify channels
					ReadTestData.fnAddTestRailScriptID(22971);
					
					ReporterLog.info("Debug","=========================================== Start Loop each channel within group ========================");
					
					imgs = els.get(loopCount).findElements(By.tagName("img"));
					String channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
					ReporterLog.info("verify channels", "Verifying channel '"+channelName+"'");
					
					// testcase #1A - Assume if loading spinner appears and disappears that channel 
					// has started playing successfully
					click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
				
					int retryAttempt = 0;
					Boolean hallelujah = false;
					
					while (!hallelujah && retryAttempt < 5) {
						
						ReporterLog.info("Debug","=========================================== Check Playing Starts ========================");
						try {
							int delayCount = 0;
							Boolean spinnerPresent = false;
							Boolean overlayPresent = false;
							
							while (delayCount < 10 && ((spinnerPresent == false) && (overlayPresent == false))){
								spinnerPresent = isNumOf_Elements_Greater_Than_Zero(channelsPlayerLoadingSpinner);
								overlayPresent = isNumOf_Elements_Greater_Than_Zero(channelsSignUpButton);
								ReporterLog.info("verify channels","waiting for loading spinner or signup overlay to appear");
								delayCount++;
								if (!spinnerPresent && !overlayPresent && delayCount < 10) {
									Thread.sleep(500);
								}
							}
							
							ReporterLog.info("Play Channel", "spinnerPresent === "+spinnerPresent+", overlayPresent == "+overlayPresent);
							
							if (overlayPresent) {
								ReporterLog.info("verify channels", "#################################### SKIP HANDLING #1 ############################");
								ReporterLog.info("verify channels", "Signup overlay detected - clicking 'skip'");
								driver.findElements(channelsSkipLink).get(1).click();
								ReporterLog.info("verify channels", "'skip' clicked.");
							}
							if (!spinnerPresent) {
								waitForVisibilityOfElement_NEW(channelsPlayerLoadingSpinner, "Loading Spinner");
							}
							waitForInVisibilityOfElement_NEW(channelsPlayerLoadingSpinner, "Loading Spinner");
							hallelujah = true;
						} catch (Exception e) {
							ReporterLog.info("verify channels","Spinner/Signup visibility check failed - retrying attempt "+(retryAttempt+1)+"/5");
							ReporterLog.info("Retry","Failure that invoked this retry attempt was: "+e);
							retryAttempt++;
							if(retryAttempt < 5) {
								ActionEngine.launchUrl_NEW(thisPage);
								waitForVisibilityOfElement(channelsGroupTitle, "Channels Group Titles");
								channelGroupTitles = driver.findElements(channelsGroupTitle);
								els = driver.findElements(channelsGroupContainer);
								imgs = els.get(loopCount).findElements(By.tagName("img"));
								click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
								channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
								continue;
							} else {
								ReporterLog.info("verify channels","Spinner/Signup visibility check failed - aborting since 5 unsuccessful attempts");
								ReporterLog.failAndContinue("", ""+e.getLocalizedMessage());
								throw new Exception(e);
							}			
						}
					}

					ReporterLog.pass("Play Channel", "Channel Group '"+channelName+"' - Channel '"+channelName+"' appears to have started successfully");
					
					// testcase #1B - Verify that channel title is displayed correctly
					channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
					verifyText(channelsPlayingNowTitle, channelName);
					ReporterLog.pass("verifychannels", "Page Title matches selected channel: "+channelName);
					
					// testcase #2A - Verify if EPG available
					
					//testcase for EPG
					ReadTestData.fnAddTestRailScriptID(29607);
					
					String thisURL=driver.getCurrentUrl();
					String channelID=getTVchannelIDfromURL(thisURL);
					String programs=getEPGschedule(channelID);
					Boolean EPGexpected = false;
					Boolean canSeeEPG = false;
					
					if(programs.equalsIgnoreCase("[]")) {
						EPGexpected = false;
					} else {
						EPGexpected = true;
					}
					
					ReporterLog.info("verifyEPG", "EPG Availability for this channel: "+EPGexpected);
					
					if (EPGexpected) {
						
						ReporterLog.info("Debug","=========================================== If EPG Expected ========================");
						
						retryAttempt = 0;
						hallelujah = false;
						
						while (!hallelujah && retryAttempt < 5) {
							try {
						
								String playerCurrent = "";
								String playerNext = "";
								String pageCurrentTitle = "";
								String pageNextTitle = "";
								
								// check that the 'current' and 'next' programs match between page and player
								
								//Scroll up to EPG section
								ReporterLog.info("getEPGlist", "scroll till EPG becomes visible");
								canSeeEPG = webScrollTillElementPresent(collapsibleArrow, false);
								if(!canSeeEPG) {
									throw new Exception("Cannot see collapsibleArrow");
								}
								ReporterLog.info("getEPGboxes", "Gettring 0th element from list");
								String pageNowPlaying = getTextOfAnElementInAList(pageEPG, 0);
								ReporterLog.info("pageNowPlaying", pageNowPlaying);
								//need to extract just the title
								ReporterLog.info("epgstuff", "Extract the lines");
								String [] lines = pageNowPlaying.split("\\r?\\n");
								pageCurrentTitle = lines[2];
								
								String pageNextPlaying = getTextOfAnElementInAList(pageEPG, 1);
								//need to extract just the title
								lines = pageNextPlaying.split("\\r?\\n");
								pageNextTitle = lines[1];
								
								//check skip overlay is not present
								Boolean overlayPresent = false;
								overlayPresent = isNumOf_Elements_Greater_Than_Zero(channelsSignUpButton);
								
								if (overlayPresent) {
									ReporterLog.info("verify channels", "#################################### SKIP HANDLING #2 ############################");
									ReporterLog.info("verify channels", "Signup overlay detected - clicking 'skip'");
									driver.findElements(channelsSkipLink).get(1).click();
									ReporterLog.info("verify channels", "'skip' clicked.");
									//Thread.sleep(5000);
									waitForVisibilityOfElement_NEW(channelsPlayerLoadingSpinner, "Loading Spinner");
									waitForInVisibilityOfElement_NEW(channelsPlayerLoadingSpinner, "Loading Spinner");
								}			
								
								ReporterLog.info("EPG", "Clicking on pause button to display elements");
								waitForElementClickable_NEW(channelsPlayerPlayOrPauseButton, "channelsPlayerPlayOrPauseButton");
								JSClick(channelsPlayerPlayOrPauseButton, "channelsPlayerPlayOrPauseButton");
								Thread.sleep(500);
								
								waitForVisibilityOfElement(playerCurrentTitle, "playerCurrentTitle");
								
								//get epg values from player
								playerCurrent=getTextOfAnElementInAList(playerEPGtitles,0);
								playerNext=getTextOfAnElementInAList(playerEPGtitles,1);
		
								ReporterLog.info("EPG Guide", "Page  'Playing Now': "+pageCurrentTitle+" || Player 'Playing Now': "+playerCurrent);
								ReporterLog.info("EPG Guide", "Page  'Playing Next': "+pageNextTitle+" || Player 'Playing Next': "+playerNext);
								
								ReporterLog.info("EPG Guide", "Verifying Page 'Playing Now' and Player 'Playing Now' match");
								verifyText_NEW(pageCurrentTitle, playerCurrent);
								ReporterLog.info("EPG Guide", "Verifying Page 'Playing Next' and Player 'Playing Next' match");
								verifyText_NEW(pageNextTitle, playerNext);
								
								ReporterLog.pass("EPG", "EPG entries for current and next playing titles match for: "+channelName);
								
								JSClick(channelsPlayerPlayOrPauseButton, "channelsPlayerPlayOrPauseButton");
								
								hallelujah = true;
							} catch (Exception e) {
								ReporterLog.info("EPG Failure","Error occured whilst verifying EPG data - retrying attempt "+(retryAttempt+1)+"/5");
								ReporterLog.info("Retry","Failure that invoked this retry attempt was: "+e);
								retryAttempt++;
								if(retryAttempt < 5) {
									ActionEngine.launchUrl_NEW(thisPage);
									waitForVisibilityOfElement(channelsGroupTitle, "Channels Group Titles");
									channelGroupTitles = driver.findElements(channelsGroupTitle);
									els = driver.findElements(channelsGroupContainer);
									imgs = els.get(loopCount).findElements(By.tagName("img"));
									click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
									channelName =  getAttributeValue(imgs.get(i), "title", "Channel #: "+ (int)((i/2)+1));
									continue;
								} else {
									ReporterLog.info("EPG Failure","aborting since 5 unsuccessful attempts");
									ReporterLog.failAndContinue("", ""+e.getLocalizedMessage());
									throw new Exception(e);
								}								
							}
						}						
					} else {
						ReporterLog.info("verifyEPG","Verifying no EPG information appears.");
						
						int noEPGretryAttempt = 0;
						boolean noEPG_hallelujah = false;
						

							
						while (!noEPG_hallelujah && noEPGretryAttempt < 5)
						{	
							
							ReporterLog.info("Debug","=========================================== NO EPG Expected ========================");
							
							try {							
					
								//no EPG expected. No elements should be present on screen or player
/*								waitForInVisibilityOfElement_NEW(pageCurrentTitle, "pageCurrentTitle");
								waitForInVisibilityOfElement_NEW(pageNextTitle, "pageNextTitle");
								waitForInVisibilityOfElement_NEW(playerCurrentTitle, "playerCurrentTitle");
								waitForInVisibilityOfElement_NEW(playerNextTitle, "playerNextTitle");*/
								
								if (		
/*										!(
												isNumOf_Elements_Greater_Than_Zero(pageCurrentTitle) &&
												isNumOf_Elements_Greater_Than_Zero(pageNextTitle) &&
												isNumOf_Elements_Greater_Than_Zero(playerCurrentTitle) &&
												isNumOf_Elements_Greater_Than_Zero(playerNextTitle)
										) */
										!(
												isNumOf_Elements_Greater_Than_Zero(pageEPG) &&
												isNumOf_Elements_Greater_Than_Zero(playerCurrentTitle) &&
												isNumOf_Elements_Greater_Than_Zero(playerNextTitle)
										) 
									){
										ReporterLog.pass("EPG", "EPG entries are correctly not visible for: "+channelName);
										noEPG_hallelujah = true;
									} else {
										ReporterLog.info("EPG", "EPG are incorrectly visible for: "+channelName);
										throw new Exception("EPG info visible when should be blank");
									}		
				
							} catch (Exception e) {
									
								ReporterLog.info("EPG Failure","Error occured whilst verifying no EPG data appears for this channel - retrying attempt "+(retryAttempt+1)+"/5");
								ReporterLog.info("Retry","Failure that invoked this retry attempt was: "+e);
								
								noEPGretryAttempt ++;
								if (noEPGretryAttempt < 5) {
									ActionEngine.driver.navigate().refresh();
									continue;								
								} else {
									ReporterLog.info("EPG Failure","aborting since 5 unsuccessful attempts");
									ReporterLog.failAndContinue("", ""+e.getLocalizedMessage());
									throw new Exception(e);
								}
							}
						}
					}
					
					//click(imgs.get(i), "Channel #: "+ (int)((i/2)+1)+"/"+(int)(imgs.size()/2));
				}
			}
		} catch (Exception e) {
			//testcase for verify channels
			ReadTestData.fnAddTestRailScriptID(22971);
			TestUtilities.logReportFailure(e);
			//testcase for EPG
			ReadTestData.fnAddTestRailScriptID(29607);
			TestUtilities.logReportFailure(e);
		}
		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "End of Workflow");	
	}

	@SuppressWarnings("unchecked")
	public static String getEPGschedule(String channelID) {
		
		String programsString="";
		
		try {
			String baseurl = "https://cdn-discover.hooq.tv/v1.7/discover/";
			String endpoint = "tv/channels/";

			String response = ReusableMethods.getAPI(baseurl + endpoint + channelID);

			JSONParser parser = new JSONParser();
			JSONObject parentObj = (JSONObject) parser.parse(response);

			JSONObject dataObj = (JSONObject) parentObj.get("data");
			
			JSONArray programs = (JSONArray) dataObj.get("programs");

			//System.out.println("Programs "+programs.toString());
			programsString = programs.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return programsString;		
	}

	private static Object getJSONValueIfKeyExists(JSONObject childObj, String key) {
		Object obj = "";
		try {

			if (childObj.containsKey(key)) {
				obj = childObj.get(key);
				if (obj == null) {
					obj = "";
				}
			}
		} catch (Exception e) {

		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static String getTVchannelIDfromURL (String url) {
		
		String id = "";
		
		try {
			String [] parts=url.split("-");
			int count = parts.length;
			if (count > 4) {
				for (int which = count-5; which < count; which ++) {
					id += parts[which]+"-";
				}
				id = id.substring(0,id.length() - 1);
			} else {
				System.out.println("No id found within supplied string: "+url);
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(id);
		return id;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		getTVchannelIDfromURL("https://www.hooq.tv/th/channels/%E0%B9%84%E0%B8%97%E0%B8%A2%E0%B8%A3%E0%B8%B1%E0%B8%90%E0%B8%97%E0%B8%B5%E0%B8%A7%E0%B8%B5-1c10db00-5d4b-48d7-aa53-2b701794a158");
			
		}
	
}





package androidworkflows;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.integrations.GoogleDriveAPI;
import com.automation.integrations.ReportStatus;
import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.automation.utilities.ReadTestData;

import android.testobjects.TabletLocators;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class TabletWorkflows extends ActionEngine {
	public static boolean blnFirstLogin = true;
	public static String strUserID = null;

	/***
	 * Function Name :- browseSpecificEpisodeanony Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void browseSpecificEpisodeanony(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			Thread.sleep(4000);
			click(TabletLocators.searchmov, "Search Content");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- browseSpecificEpisodeActive Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void browseSpecificEpisodeActive(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			Thread.sleep(4000);
			// click(TabletLocators.searchmov, "Search Content");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode.size());
			eleEpisode.get(0).click();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- browseNightly Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void browseNightly(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			Thread.sleep(4000);
			// click(TabletLocators.searchmov, "Search Content");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode.size());
			eleEpisode.get(0).click();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Creditcardnumber Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void Creditcardnumber(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearchcc, "CC enter option");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			Thread.sleep(4000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- SearchActive Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void SearchActive(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			Thread.sleep(4000);
			// click(TabletLocators.searchmov, "Search Content");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- SearchContent Developed By :- Jagadish Date :- 17-June-2019
	 */

	public static void SearchContent(String contentName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, contentName, "Enter Movie Details");
			Thread.sleep(4000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchResultList)) {
				ReporterLog.pass("Search functionality", "Search results are displayed");
				List<WebElement> searchResults = driver.findElements(By.id("tv.hooq.android:id/matchedTitlesListView")); // tv.hooq.android:id/imgPoster
				System.out.println(searchResults.size());
				List<WebElement> actualResults = searchResults.get(0)
						.findElements(By.className("android.widget.FrameLayout")); // android.widget.ImageView
				if (actualResults.size() > 0) {
					actualResults.get(0).click();
					Thread.sleep(4000);
					String title = getText(By.id("tv.hooq.android:id/txtAssetTitle"), "title"); // tv.hooq.android:id/tv_spotlight_title
					if (title.equalsIgnoreCase(contentName)) {
						ReporterLog.pass("Search functionality",
								"Search results are matching with the searched content");
					} else {
						ReporterLog.fail("Search functionality",
								"Search results are not matching with the searched content");
					}
				}
			} else {
				ReporterLog.fail("Search functionality", "Search results are not displayed");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	/***
	 * Function Name :- Like Developed By :- Jagadish Date :- 18-June-2019
	 */

	public static void fnContentDetails(String contentName, String strusrType, String strContentType) {
		// boolean blnStatus=true;
		try {
			ReporterLog.info("Verification of Content details page",
					"Verification of Content details page of: " + contentName);
			SearchContent(contentName);
			
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.poster)) {
				ReporterLog.pass("Content details page", "Poster is displayed");
			} else {
				ReporterLog.fail("Content details page", "Poster is not displayed");
				// blnStatus = false;
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentTitle)) {
				String strTitle = getText(TabletLocators.contentTitle, "title");
				ReporterLog.pass("Content details page", "Title is displayed: " + strTitle);
			} else {
				ReporterLog.fail("Content details page", "Title is not displayed");
				// blnStatus = false;
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentMovieDuration)) {
				String strDuration = getText(TabletLocators.contentMovieDuration, "title");
				ReporterLog.pass("Content details page", "Duration and Genre details are displayed: " + strDuration);
			} else {
				ReporterLog.fail("Content details page", "Duration and Genre details are not displayed");
				// blnStatus = false;
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentAudio)) {
				String strAudio = getText(TabletLocators.contentAudio, "Audio");
				ReporterLog.pass("Content details page", "Audio details are displayed: " + strAudio);
			} else {
				ReporterLog.fail("Content details page", "Audio details are not displayed");
				// blnStatus = false;
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentSubtitles)) {
				String strSubtitles = getText(TabletLocators.contentSubtitles, "Subtitles");
				ReporterLog.pass("Content details page", "Subtitle details are displayed: " + strSubtitles);
				FileUtilities.writeToFile("NA", "Subtitles: "+strSubtitles, "Purple");
			} else {
				ReporterLog.fail("Content details page", "Subtitle details are not displayed");
				// blnStatus = false;
			}
			
			
			/*
			 * if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentPlayButton)) {
			 */
			// String strBtnPlayText = getText(TabletLocators.contentPlayButton, "Text on
			// play button");
			if (strContentType.equalsIgnoreCase("Movie")) {
				String strBtnPlayText = getText(TabletLocators.contentPlayButton, "Text on play button");
				if (strusrType.equalsIgnoreCase("Active")) {
					if (strBtnPlayText.equalsIgnoreCase("Watch now") || strBtnPlayText.contains("Continue")) {
						ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					} else {
						ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
						// blnStatus = false;
					}
				} else if (strusrType.equalsIgnoreCase("Lapsed")) {
					if (strBtnPlayText.equalsIgnoreCase("Subscribe to watch")) {
						ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					} else {
						ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
						// blnStatus = false;
					}
				} else {
					if (strBtnPlayText.equalsIgnoreCase("Sign up to watch")) {
						ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					} else {
						ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
						// blnStatus = false;
					}
				}
			} else if (strContentType.equalsIgnoreCase("Rent")) {
				String strBtnPlayText = null;
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.btnRedeemYourTckt)) {
					strBtnPlayText = getText(TabletLocators.btnRedeemYourTckt, "Text on play button");
				} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentPlayButton)) {
					strBtnPlayText = getText(TabletLocators.contentPlayButton, "Text on play button");
				} else {
					ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					// blnStatus = false;
				}
				if (strusrType.equalsIgnoreCase("Active") || strusrType.equalsIgnoreCase("Free")) {
					if (strBtnPlayText.equalsIgnoreCase("Redeem your ticket") || strBtnPlayText.contains("Rent for INR")
							|| strBtnPlayText.equalsIgnoreCase("Watch now") || strBtnPlayText.contains("Continue") 
							|| strBtnPlayText.contains("Rent for SGD") || strBtnPlayText.contains("Rent for IDR")
							|| strBtnPlayText.contains("Rent for THB") || strBtnPlayText.contains("Rent for PHP")) {
						ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					} else {
						ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
						// blnStatus = false;
					}
				} else if (strusrType.equalsIgnoreCase("Lapsed")) {
					if (strBtnPlayText.equalsIgnoreCase("Redeem your ticket") || strBtnPlayText.contains("Rent for INR")
							|| strBtnPlayText.equalsIgnoreCase("Watch now") || strBtnPlayText.contains("Continue") 
							|| strBtnPlayText.contains("Rent for SGD") || strBtnPlayText.contains("Rent for IDR")
							|| strBtnPlayText.contains("Rent for THB") || strBtnPlayText.contains("Rent for PHP")) {
						ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					} else {
						ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
						// blnStatus = false;
					}
				} else {
					if (strBtnPlayText.equalsIgnoreCase("Sign up to watch")) {
						ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					} else {
						ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
						// blnStatus = false;
					}
				}

			} else {
				String strBtnPlayText = getText(TabletLocators.contentPlayButton, "Text on play button");
				if (strBtnPlayText.equalsIgnoreCase("Watch now") || strBtnPlayText.contains("Continue")) {
					ReporterLog.pass("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
				} else {
					ReporterLog.fail("Content details page", "Text displayed on Play button is: " + strBtnPlayText);
					// blnStatus = false;
				}
			}

			/*
			 * } else { Reporter.fail("Content details page",
			 * "Play button is not displayed"); blnStatus = false; }
			 */
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentDescription)) {
				ReporterLog.pass("Content details page", "Content Description is displayed");
			} else {
				ReporterLog.fail("Content details page", "Content Description is not displayed");
				// blnStatus = false;
			}

	/*		if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.directorLabel) && isNumOf_Elements_Greater_Than_Zero(TabletLocators.directorList)) {
				String strDirectors = getText(TabletLocators.directorList, "Director");
				if (strDirectors == null || strDirectors == "") {
					ReporterLog.fail("Content details page", "Director details are not displayed");
					// blnStatus = false;
				} else {
					ReporterLog.pass("Content details page", "Director details are displayed: " + strDirectors);
				}

			} else {
				ReporterLog.fail("Content details page", "Director details are not displayed");
				// blnStatus = false;
			}*/

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.categoryLabel) && isNumOf_Elements_Greater_Than_Zero(TabletLocators.categoryList)) {
				String strCategory = getText(TabletLocators.categoryList, "Category");
				if (strCategory == null || strCategory == "") {
					ReporterLog.fail("Content details page", "Category details are not displayed");
					// blnStatus = false;
				} else {
					ReporterLog.pass("Content details page", "Category details are displayed: " + strCategory);
				}

			} else {
				ReporterLog.fail("Content details page", "Category details are not displayed");
				// blnStatus = false;
			}

		/*	if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.actorsLabel) && isNumOf_Elements_Greater_Than_Zero(TabletLocators.actorsList)) {
				String strActors = getText(TabletLocators.actorsList, "Actors");
				if (strActors == null || strActors == "") {
					ReporterLog.fail("Content details page", "Actors details are not displayed");
					// blnStatus = false;
				} else {
					ReporterLog.pass("Content details page", "Actors details are displayed: " + strActors);
				}
			} else {
				ReporterLog.fail("Content details page", "Actors details are not displayed");
				// blnStatus = false;
			}*/

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.watchList)) {
				ReporterLog.pass("Content details page", "Watchlist icon is displayed");
			} else {
				ReporterLog.fail("Content details page", "Watchlist icon is not displayed");
				// blnStatus = false;
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.like)) {
				ReporterLog.pass("Content details page", "Like icon is displayed");
			} else {
				ReporterLog.fail("Content details page", "Like icon is not displayed");
				// blnStatus = false;
			}

			if (strContentType.equalsIgnoreCase("Movie") || strContentType.equalsIgnoreCase("Rent")) {

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadMovie1)) {
					ReporterLog.pass("Content details page", "Download button is displayed");
				} else {
					ReporterLog.fail("Content details page", "Download button is not displayed");
					// blnStatus = false;
				}
			} else {
				for (int i = 1; i <= 1; i++) {
					swipeUpOrBottom(true);
				}
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadTVShow1)) {
					ReporterLog.pass("Content details page", "Download button is displayed");
				} else {
					ReporterLog.fail("Content details page", "Download button is not displayed");
					// blnStatus = false;
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.seasonDropdown)) {
					ReporterLog.pass("Content details page", "Seasons dropdown is displayed");
				} else {
					ReporterLog.fail("Content details page", "Seasons dropdown is not displayed");
					// blnStatus = false;
				}

				List<WebElement> episodeList = driver.findElements(By.id("tv.hooq.android:id/recycleSeasonList"));
				System.out.println(episodeList.size());
				List<WebElement> noOfEpisodes = episodeList.get(0).findElements(By.id("tv.hooq.android:id/container"));
				if (noOfEpisodes.size() > 0) {
					ReporterLog.pass("Content details page",
							"Episodes list is displayed and no. of episodes displayed is: " + noOfEpisodes.size());
				} else {
					ReporterLog.fail("Content details page", "Episodes list is not displayed");
					// blnStatus = false;
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentEpisodeDuration)) {
					String strEpisodeDuration = getText(TabletLocators.contentEpisodeDuration, "Duration");
					ReporterLog.pass("Content details page", "Episode duration is displayed: " + strEpisodeDuration);
				} else {
					ReporterLog.fail("Content details page", "Episode duration is not displayed");
					// blnStatus = false;
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.tvShowEpisodePlayIcon)) {
					ReporterLog.pass("Content details page", "Play icon for 1st episode is displayed");
				} else {
					ReporterLog.fail("Content details page", "Play icon for 1st episode is not displayed");
					// blnStatus = false;
				}

			}
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			// if(isNumOf_Elements_Greater_Than_Zero())
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contentSimilarTitleLabel)) {
				ReporterLog.pass("Content details page", "Similar titles section is displayed");
			} else {
				ReporterLog.fail("Content details page", "Similar titles section is not displayed");
				// blnStatus = false;
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Like Developed By :- Jagadish Date :- 18-June-2019
	 */

	public static void fnLike(String contentName, String strUserType) {
		// boolean blnStatus=true;
		try {
			SearchContent(contentName);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.like)) {
				ReporterLog.pass("Content details page", "Like icon is displayed");
				if(strUserType.equalsIgnoreCase("Active") || strUserType.equalsIgnoreCase("Lapsed") || strUserType.equalsIgnoreCase("Free"))
				{
					String strLike = getText(TabletLocators.like, "title");
					if (strLike.equalsIgnoreCase("Like")) {
						click(TabletLocators.like, "Like");
						String strLiked = getText(TabletLocators.like, "title");
						if (strLiked.equalsIgnoreCase("Liked")) {
							ReporterLog.pass("Content details page", "Able to Like the content successfully");
							click(TabletLocators.like, "Like");
							String strRemoveLike = getText(TabletLocators.like, "title");
							if (strRemoveLike.equalsIgnoreCase("Like")) {
								ReporterLog.pass("Content details page", "Able to click and remove the Like");
							} else {
								ReporterLog.fail("Content details page", "Not able to remove the Like");
							}

						} else {
							ReporterLog.fail("Content details page", "Not able to Like the content");
						}

					} else {
						ReporterLog.pass("Content details page", "This content is already Liked");
						click(TabletLocators.like, "Like");
						String strRemoveLike = getText(TabletLocators.like, "title");
						if (strRemoveLike.equalsIgnoreCase("Like")) {
							ReporterLog.pass("Content details page", "Able to remove the Like");
							click(TabletLocators.like, "Like");
							String strLikeAgain = getText(TabletLocators.like, "title");
							if (strLikeAgain.equalsIgnoreCase("Liked")) {
								ReporterLog.pass("Content details page", "Able to Like the content");
							} else {
								ReporterLog.pass("Content details page", "Not able to Like the content");
							}
						} else {
							ReporterLog.fail("Content details page", "Not able to remove the Like");
						}
					}
				}
				else
				{
					click(TabletLocators.like, "Like");
					if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.signUpPopUp))
					{
						ReporterLog.pass("Content details page", "Pop up to Signup is displayed when visitor clicked on Like icon");
						click(TabletLocators.mayBeLaterPopUp, "May be later");
					}
					else
					{
						ReporterLog.pass("Content details page", "Pop up to Signup is not displayed when visitor clicked on Like icon");
					}

				}
			} else {
				ReporterLog.fail("Content details page", "Like icon is not displayed");
				// blnStatus = false;
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		// return blnStatus;
	}

	/***
	 * Function Name :- nightlyplay Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	/*
	 * public static void nightlyplay(String description,String strEpisodeName) {
	 * ReporterLog.info("HOOQ Android","Search Movie / TV Episode");
	 * click(TabletLocators.btnSearch, "Search button");
	 * type(TabletLocators.edtSearch, description, "Enter Movie Details");
	 * Thread.sleep(4000); click(TabletLocators.txtSuggestedText1,"Suggested Text");
	 * click(TabletLocators.playButton,"Play button"); Thread.sleep(4000); {
	 * verifyVideoPlayback(); driver.navigate().back(); driver.navigate().back();
	 * driver.navigate().back(); Thread.sleep(2000); click(TabletLocators.meProfile,
	 * "Me"); for(int i=1;i<=4;i++) { swipeUpOrBottom(true); }
	 * click(TabletLocators.logOut,"ME icon");
	 * click(TabletLocators.conformlogout,"ME"); return true; } }
	 */

	/***
	 * Function Name :- browseSpecificEpisode1Tab Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void browseSpecificEpisode1Tab(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			Thread.sleep(3000);
			click(TabletLocators.txtSuggestedText, "Suggested Text");
			Thread.sleep(9000);
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.FindSeasonEpisodt, "Find Episode");
			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/seasonTitle"));
			eleEpisode.get(0).click();
			List<MobileElement> eleList = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			if (eleList.size() > 0) {
				eleList.get(0).click();
			}
			{
				verifyVideoPlayback();
				// return true;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBack(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowsenew, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.lapsedhooqClick, "First collection in  browse page");
			waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content");
			click(TabletLocators.lapsedAssetTitle, "first episode/movie from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			Thread.sleep(25000);
			verifyPaymentlapse();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackfree Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackfree(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowsenew, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.lapsedhooqClick, "First collection in  browse page");
			waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content");
			click(TabletLocators.lapsedAssetTitle, "first episode/movie from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			Thread.sleep(25000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackmobile Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackmobile(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btntvshows, "TV Shows button");
			List<WebElement> eleHeader = driver.findElements(By.id("tv.hooq.android:id/recycler_view"));
			System.out.println(eleHeader.size());
			List<WebElement> eleSubscription = eleHeader.get(0).findElements(By.id("tv.hooq.android:id/imgPoster"));
			System.out.println(eleSubscription.size());
			eleSubscription.get(0).click();
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			Thread.sleep(2000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentbrowse Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void contentbrowse(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			// click(TabletLocators.btnBrowsenew, "Browse button");
			ActiveSearch_movie();
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			/*
			 * List<MobileElement>
			 * eleList=eleMobile.findElements(By.className("android.widget.RelativeLayout"))
			 * ; if(eleList.size()>0) { eleList.get(1).click(); }
			 */
			// click(TabletLocators.lapsedAssetTitle,"first episode/movie from selected
			// collection");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackTabletPickurplan Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static void contentPlayBackTabletPickurplan(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.pickurplan, "Clicked on pick ur Plan");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.selectplantoolbar)) {
				ReporterLog.pass("Select plan header", "Select plan header is Visible");
			} else {
				ReporterLog.fail("Select plan header", "Select plan header is not visible");
			}
			driver.navigate().back();
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClickTab, "First collection in  browse page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.activeAssetTitle, title + " is from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButtonTab)) {
				click(TabletLocators.playButtonTab, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for " + title);
			}
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackFilter Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackFilter(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnmovies, "Movies button");
			List<WebElement> eleHeader = driver.findElements(By.id("tv.hooq.android:id/recycler_view"));
			System.out.println(eleHeader.size());
			List<WebElement> eleSubscription = eleHeader.get(0).findElements(By.id("tv.hooq.android:id/imgPoster"));
			// click(TabletLocators.Moviebtn,"Movie Poster");
			System.out.println(eleSubscription.size());
			eleSubscription.get(0).click();
			click(TabletLocators.playButton1, "Play button");
			Thread.sleep(20000);
			fnVerifyBrightCovePlayer();
			/*
			 * if(fnVerifyBrightCovePlayer()) {
			 * System.out.println("Video is Playing successfully"); } else {
			 * System.out.println("Video is not Playing"); }
			 */

			// MePageHelper.verifyVideoPlaybackB();
			// waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			// click(TabletLocators.activehooqClick,"First collection in browse page");
			/*
			 * click(TabletLocators.Filtericon,"Filter icon in  browse page");
			 * //if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)){
			 * //Reporter.pass("Filter header", "Filter header is not Visible"); //}else{ //
			 * Reporter.fail("Filter header", "Filter header is not visible"); //}
			 * 
			 * //if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)){ //
			 * Reporter.pass("Filter Reset", "Filter Reset is not Visible"); //}else{
			 * //Reporter.fail("Filter Reset", "Filter Reset is not visible"); //}
			 * //if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filtertype2)){ //
			 * Reporter.pass("Filter Type", "Filter Type is Visible"); //}else{
			 * Reporter.fail("Filter Type", "Filter Type is not visible"); }
			 * 
			 * if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.Applyfilter)){
			 * Reporter.pass("Apply Filter Type", "Apply Filter Type is Visible"); }else{
			 * Reporter.fail("Apply Filter Type", "Filter Type is not visible"); }
			 * click(TabletLocators.Filterclose,"Filter close is clicked");
			 * //click(TabletLocators.Filtergenre,"Filter genre in  browse page");
			 * //click(TabletLocators.Applyfilter,"Apply Filter is clicked");
			 * Thread.sleep(3000); for(int i=1;i<=4;i++) { swipeUpOrBottom(true); }
			 */
			driver.navigate().back();
			driver.navigate().back();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- SupportValidationsanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void SupportValidationsanity(String strColumn) {
		try {
			click(TabletLocators.aboutus, "About Us option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutus1)) {
				ReporterLog.pass("About Us", "About Us Option is visible");
			} else {
				ReporterLog.fail("About Us", "About Us Option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("About Us Back Button", "About Us Back Button is displayed");
			} else {
				ReporterLog.fail("About Us Back Button", "About Us Back Button is not displayed");
			}
			/*
			 * String strSource=driver.getPageSource(); String strText="ABOUT US";
			 * if(strSource.contains("ABOUT US")) { Reporter.pass("About us context",
			 * "About us context is displayed"); } else { Reporter.fail("About us context",
			 * "About us context is not displayed successfully"); } String
			 * strText1="Just press Play."; if(strSource.contains("Just press Play.") &&
			 * strSource.contains(strText1)) { Reporter.pass("About us desc",
			 * "About us desc is displayed successfully"); } else {
			 * Reporter.fail("About us desc",
			 * "About us desc is not displayed successfully"); }
			 */

			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// FAQ
			click(TabletLocators.faq, "FAQ option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.faq)) {
				ReporterLog.pass("FAQ", "FAQ Option is visible");
			} else {
				ReporterLog.fail("FAQ", "FAQ is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("FAQ Back Button", "FAQ Back Button is displayed");
			} else {
				ReporterLog.fail("FAQ Back Button", "FAQ Back Button is not displayed");
			}
			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// Terms of Use

			click(TabletLocators.tos, "Terms of Use option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.tos1)) {
				ReporterLog.pass("Terms of Use", "Terms of Use is visible");
			} else {
				ReporterLog.fail("Terms of Use", "Terms of Use is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("Terms of Use Back Button", "Terms of Use Back Button is displayed");
			} else {
				ReporterLog.fail("Terms of Use Back Button", "Terms of Use Back Button is not displayed");
			}

			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// Privacy Policy
			click(TabletLocators.Privacypolicy, "Privacy policy option is clicked");
			Thread.sleep(13000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Privacypolicy1)) {
				ReporterLog.pass("Privacy policy", "Privacy policy is visible");
			} else {
				ReporterLog.fail("Privacy policy", "Privacy policy is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("Privacy policy Back Button", "Privacy policy Back Button is displayed");
			} else {
				ReporterLog.fail("Privacy policy Back Button", "Privacy policy Back Button is not displayed");
			}

			click(TabletLocators.aboutusback1, "Back Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contactus)) {
				ReporterLog.pass("Contact us", "Contact us button is visible");

			} else {
				ReporterLog.fail("Contact Us", "Contact Us is not visible");
			}

			click(TabletLocators.opensrc, "open source Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Opensrclicense1)) {
				ReporterLog.pass("Open Source License", "Open Source License button is visible");

			} else {
				ReporterLog.fail("Open Source License", "Open Source License is not visible");
			}

			click(TabletLocators.aboutusback1, "Back Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Appv)) {
				ReporterLog.pass("App Version", "App Version button is visible");

			} else {
				ReporterLog.fail("App Version", "App Version button is not visible");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyBrightCovePlayer Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void fnVerifyBrightCovePlayer() {
		ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
		boolean blnStatus = true;
		try {
			driver.findElement(By.id("tv.hooq.android:id/brightcoveVideoView")).click();
			String strSource = driver.getPageSource();
			if (strSource.contains("tv.hooq.android:id/btnClose") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "Button Close is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "Button Close is visible");
			}
			if (strSource.contains("tv.hooq.android:id/videoTitle") == false) {
				blnStatus = false;
				ReporterLog.fail("videoTitle", "videoTitle is not visible");
			} else {
				ReporterLog.pass("videoTitle", "videoTitle is visible");
			}
			if (strSource.contains("tv.hooq.android:id/iconSettings") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "iconSettings is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "iconSettings is visible");
			}
			if (strSource.contains("tv.hooq.android:id/iconQuality") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "iconQuality is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "iconQuality is visible");
			}
			if (strSource.contains("tv.hooq.android:id/iconRewind") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "iconRewind is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "iconRewind is visible");
			}
			if (strSource.contains("tv.hooq.android:id/iconPlayPause") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "iconPlayPause is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "iconPlayPause is visible");
			}
			if (strSource.contains("tv.hooq.android:id/iconForward") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "iconForward is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "iconForward is visible");
			}
			if (strSource.contains("tv.hooq.android:id/seek_bar") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "seek_bar is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "seek_bar is visible");
			}
			if (strSource.contains("tv.hooq.android:id/playTime") == false) {
				blnStatus = false;
				ReporterLog.fail("Video Player Controls", "playTime is not visible");
			} else {
				ReporterLog.pass("Video Player Controls", "playTime is visible");
			}
			Thread.sleep(20000);
			driver.findElement(By.id("tv.hooq.android:id/brightcoveVideoView")).click();
			String strPageSource1 = driver.getPageSource();
			System.out.println(strPageSource1);
			String strData = StringUtils.difference(strSource, strPageSource1);
			System.out.println(strData);
			System.out.println(strData.length());
			if (strData.length() == 0) {
				blnStatus = false;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			blnStatus = false;
		}
		if (blnStatus) {
			ReporterLog.pass("Video Playback", "Video is playing successfully");
		} else {
			ReporterLog.fail("Video Playback", "Video is not playing");
		}
		// return blnStatus;
	}

	/***
	 * Function Name :- contentPlayBackFilterTab Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackFilterTab(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClickTab, "First collection in  browse page");
			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is not Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is not Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}

			click(TabletLocators.Filtertype1, "Flter type in  browse page");
			click(TabletLocators.Applyfilter, "Apply Filter is clicked");
			Thread.sleep(3000);
			for (int i = 1; i <= 12; i++) {
				swipeUpOrBottom(true);
			}
			driver.navigate().back();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackFiltercontent Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void contentPlayBackFiltercontent(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowsenew, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClick, "First collection in  browse page");
			// String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}
			click(TabletLocators.Filterclose, "Filter close is clicked");
			Thread.sleep(3000);
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/browseTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(1).click();
			List<MobileElement> eleList = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			if (eleList.size() > 0) {
				eleList.get(1).click();
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			verifyVideoPlayback();
			driver.navigate().back();
			driver.navigate().back();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackFiltercontentlap Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static void contentPlayBackFiltercontentlap(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowsenew, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClick, "First collection in  browse page");
			// String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}

			click(TabletLocators.Filtergenre, "Flter genre in  browse page");
			click(TabletLocators.Applyfilter, "Apply Filter is clicked");
			Thread.sleep(3000);
			click(TabletLocators.lapsedAssetTitle, " is from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
				ReporterLog.pass("play Button", "Play button is Visible");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			Thread.sleep(25000);
			verifyPaymentlapse();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackFiltercontenttab Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static void contentPlayBackFiltercontenttab(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClickTab, "First collection in  browse page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}

			click(TabletLocators.Filtertype1, "Flter type in  browse page");
			click(TabletLocators.Applyfilter, "Apply Filter is clicked");
			Thread.sleep(3000);

			click(TabletLocators.activeAssetTitle, title + " is from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton)) {
				// click(TabletLocators.playButton,"Play button");
				ReporterLog.pass("play Button", "Play button is Visible");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for " + title);
			}
			click(TabletLocators.favorite, "Favorite in  browse page");
			click(TabletLocators.watchLater, "watch Later in  browse page");

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackOthersalsowatched Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static void contentPlayBackOthersalsowatched(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnmovies, "Movies button");
			// List<WebElement>
			// eleHeader=driver.findElements(By.id("tv.hooq.android:id/recycler_view"));
			// System.out.println(eleHeader.size());
			// List<WebElement>
			// eleSubscription=eleHeader.get(0).findElements(By.id("tv.hooq.android:id/imgPoster"));
			// System.out.println(eleSubscription.size());
			// eleSubscription.get(0).click();
			// List<WebElement>
			// eleHeader=driver.findElements(By.id("tv.hooq.android:id/recycler_view"));
			List<WebElement> eleHeader = driver.findElements(By.id("tv.hooq.android:id/epoxyRecyclerView"));
			System.out.println(eleHeader.size());
			List<WebElement> eleSubscription = eleHeader.get(0)
					.findElements(By.id("tv.hooq.android:id/image_container"));
			System.out.println(eleSubscription.size());
			eleSubscription.get(0).click();
			Thread.sleep(1000);
			for (int i = 1; i <= 4; i++) {
				swipeUpOrBottom(true);
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.youmayalso)) {
				ReporterLog.pass("You May Also Like Section", "You May Also Like Section is visible");
			} else {
				ReporterLog.fail("You May Also Like Section", "You May Also Like Section is not visible");
			}
			/*
			 * if(isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/recycleSimilarTitleList"))) {
			 * MobileElement eleCollection=(MobileElement)
			 * driver.findElement(By.id("tv.hooq.android:id/recycleSimilarTitleList"));
			 * List<MobileElement> eleList1=eleCollection.findElements(By.className(
			 * "android.widget.RelativeLayout")); if(eleList1.size()>0) {
			 * System.out.println(eleList1.size()); List<MobileElement>
			 * eleMovie=eleList1.get(0).findElements(By.className("android.widget.ImageView"
			 * )); if(eleMovie.size()>0) { eleMovie.get(0).click(); } } }
			 * Thread.sleep(2000); if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)){
			 * click(TabletLocators.playButton1,"Play button"); } else {
			 * Reporter.fail("PlayButton", "Play button is not displayed for"); }
			 * MePageHelper.verifyVideoPlayback();
			 */
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackOthersalsowatchedTablet Developed By :- Raja
	 * Reddy Date :- 23-May-2019
	 */

	public static void contentPlayBackOthersalsowatchedTablet(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClickTab, "First collection in  browse page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.activeAssetTitle, title + " is from selected collection");
			Thread.sleep(10000);
			for (int i = 1; i <= 5; i++) {
				swipeUpOrBottom(true);
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Othersalsowatched)) {
				ReporterLog.pass("Others also Watched Section", "Others also Watched Section is visible");

			} else {

				ReporterLog.fail("Others also Watched Section", "Others also Watched Section is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/relatedListView"))) {
				MobileElement eleCollection = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/relatedListView"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.RelativeLayout"));
				if (eleList.size() > 0) {
					System.out.println(eleList.size());
					List<MobileElement> eleMovie = eleList.get(0)
							.findElements(By.className("android.widget.ImageView"));
					String strType = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetType")).getText();
					System.out.println(strType);
					String strTitle = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetTitle")).getText();
					System.out.println(strTitle);
					if (eleMovie.size() > 0) {
						eleMovie.get(0).click();
					}
				}
			}
			Thread.sleep(5000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButtonTab)) {
				click(TabletLocators.playButtonTab, "Play button");
			} else {
				ReporterLog.fail("play Button Tab", "Play button is not displayed for " + title);
			}
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackContinuewatching1 Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static void contentPlayBackContinuewatching1(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.youwerewatching)) {
				ReporterLog.pass("Continue Watching Section", "Continue Watching Section is displayed");
			} else {
				ReporterLog.fail("Continue Watching Section", "Continue Watching Section is not displayed");
			}
			Thread.sleep(3000);

			/*
			 * if(isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/collectionContainer"))) {
			 * MobileElement eleCollection=(MobileElement)
			 * driver.findElement(By.id("tv.hooq.android:id/collectionContainer"));
			 * List<MobileElement>
			 * eleList=eleCollection.findElements(By.className("android.widget.LinearLayout"
			 * )); if(eleList.size()>0) { System.out.println(eleList.size());
			 * eleList.get(0).click(); } }
			 */

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cwplay)) {
				click(TabletLocators.cwplay, "CW Play");
			}
			Thread.sleep(5000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Continuewatchinglapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void Continuewatchinglapse(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.youwerewatching)) {
				ReporterLog.pass("Continue Watching Section", "Continue Watching Section is displayed");
			} else {
				ReporterLog.fail("Continue Watching Section", "Continue Watching Section is not displayed");
			}
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cwplay)) {
				click(TabletLocators.cwplay, "CW Play");
			}
			Thread.sleep(5000);
			verifyPaymentlapse();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackQuickLink Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackQuickLink(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.youwerewatching)) {
				ReporterLog.pass("Continue Watching Section", "Continue Watching Section is displayed");
			} else {
				ReporterLog.fail("Continue Watching Section", "Continue Watching Section is not displayed");
			}
			Thread.sleep(7000);
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/collectionList"))) {
				MobileElement eleCollection = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/collectionList"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 1) {
					System.out.println(eleList.size());
					eleList.get(1).click();
				}
			}
			Thread.sleep(5000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackYouwerewatchingtvod Developed By :- Raja
	 * Reddy Date :- 23-May-2019
	 */

	public static void contentPlayBackYouwerewatchingtvod(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.youwerewatching)) {
				ReporterLog.pass("You Were Watching Section", "You Were Watching Section is displayed");
			} else {
				ReporterLog.fail("You Were Watching Section", "You Were Watching Section is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/collectionList"))) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.YouwwTVOD)) {
					ReporterLog.pass("You Were Watching Section TVOD", "You Were Watching Section TVOD is displayed");
				} else {
					ReporterLog.fail("You Were Watching Section TVOD",
							"You Were Watching Section TVOD is not displayed");
				}
				MobileElement eleCollection = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/collectionList"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					System.out.println(eleList.size());
					eleList.get(0).click();
				}
			}
			Thread.sleep(5000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackContinuewatchingtvod Developed By :- Raja
	 * Reddy Date :- 23-May-2019
	 */

	public static void contentPlayBackContinuewatchingtvod(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.continuewatching)) {
				ReporterLog.pass("Continue Watching Section", "Continue Watching Section is displayed");
			} else {
				ReporterLog.fail("Continue Watching Section", "Continue Watching Section is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/collectionList"))) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.CWTVOD)) {
					ReporterLog.pass("Continue Watching Section TVOD", "Continue Watching Section TVOD is displayed");
				} else {
					ReporterLog.fail("Continue Watching Section TVOD",
							"Continue Watching Section TVOD is not displayed");
				}
				// MobileElement eleCollection=(MobileElement)
				// driver.findElement(By.id("tv.hooq.android:id/collectionList"));
				// List<MobileElement>
				// eleList=eleCollection.findElements(By.className("android.widget.LinearLayout"));
				// if(eleList.size()>0)
				// {
				// System.out.println(eleList.size());
				// eleList.get(0).click();
				// }
			}
			click(TabletLocators.continuewatching, "Continue watching section");
			Thread.sleep(5000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackContinuewatching Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static void contentPlayBackContinuewatching(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.continuewatching)) {
				ReporterLog.pass("Continue Watching Section", "Continue Watching Section is displayed");
			} else {
				ReporterLog.fail("Continue Watching Section", "Continue Watching Section is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.continuewatchindicator)) {
				ReporterLog.pass("continue watch indicator", "continue watch indicator is displayed");
			} else {
				ReporterLog.fail("continue watch indicator", "continue watch indicator is not displayed");
			}
			click(TabletLocators.continuewatchposter, "Continue watching section");

			/*
			 * if(isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/highlightViewpager"))) {
			 * MobileElement eleCollection=(MobileElement)
			 * driver.findElement(By.id("tv.hooq.android:id/highlightViewpager"));
			 * List<MobileElement> eleList=eleCollection.findElements(By.className(
			 * "android.widget.RelativeLayout")); if(eleList.size()>0) {
			 * System.out.println(eleList.size()); eleList.get(0).click(); } }
			 */
			Thread.sleep(5000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackmob Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackmob(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.activehooqClick, "First collection in  browse page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.activeAssetTitle, title + " is from selected collection");
			click(TabletLocators.FindSeasonEpisod, "Find Episode");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/mSeasonRecyclerListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/seasonTitle"));
			eleEpisode.get(0).click();
			List<MobileElement> eleList = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			if (eleList.size() > 0) {
				eleList.get(0).click();

			}
			{
				// MePageHelper.verifyVideoPlayback();
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton)) {
					click(TabletLocators.playButton, "Play button");
				} else {
					ReporterLog.fail("PlayButton", "Play button is not displayed for " + title);
				}
				verifyVideoPlayback();
				// return true;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackpremium Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackpremium(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			// click(TabletLocators.searchmov, "Search Content");
			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			Thread.sleep(15000);
			verifyPaymentlapse();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackpremiumfree Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void contentPlayBackpremiumfree(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			// click(TabletLocators.searchmov, "Search Content");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButton1)) {
				click(TabletLocators.playButton1, "Play button");
			} else {
				ReporterLog.fail("PlayButton", "Play button is not displayed for ");
			}
			Thread.sleep(5000);
			// MePageHelper.verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadQuality Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadQuality(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(2000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");

			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");
			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			click(TabletLocators.downloaddone, "Download option");
			Thread.sleep(700000);
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.mesect, "Me option");
			click(TabletLocators.Downloadoption, "Downlaod option");
			Thread.sleep(3000);
			click(TabletLocators.downloadmplay, "Downloaded content");
			Thread.sleep(20000);
			// BrowsePageHelper.fnVerifyBrightCovePlayer();
			// MePageHelper.verifyVideoPlayback();
			driver.navigate().back();
			Thread.sleep(5000);
			click(TabletLocators.editButton1, " Edit Option");
			click(TabletLocators.editdel, " Del Option");
			// MobileElement eleMobile1=(MobileElement)
			// driver.findElement(By.id("tv.hooq.android:id/listView"));
			// List<MobileElement>
			// eleEpisode1=eleMobile1.findElements(By.className("android.widget.RelativeLayout"));
			// eleEpisode1.get(0).click();
			// click(TabletLocators.downloadmplay, "Downloaded content");
			click(TabletLocators.removeButton, " Remove Option");
			click(TabletLocators.deleteButton1, " Delelting the Downloaded Video");
			Thread.sleep(2000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadQualityPremium Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void VerifyDownloadQualityPremium(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			// click(TabletLocators.txtSuggestedText1,"Suggested Text");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(2000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");
			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");

			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadQualityPre Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadQualityPre(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			// click(TabletLocators.txtSuggestedText1,"Suggested Text");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(2000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(10000);
			// click(TabletLocators.Downloadvideo,"Clicked on Download option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");

			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");

			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			click(TabletLocators.downloaddone, "Download option");
			Thread.sleep(9000);
			driver.navigate().back();
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.mesect, "Me option");
			click(TabletLocators.Downloadoption, "Downlaod option");
			Thread.sleep(3000);
			click(TabletLocators.editButton, " Edit Option");
			click(TabletLocators.downloadmplay, "Downloaded content");
			click(TabletLocators.removeButton, " Remove Option");
			click(TabletLocators.deleteButton1, " Delelting the Downloaded Video");
			Thread.sleep(2000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadQualityFree Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadQualityFree(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			// click(TabletLocators.txtSuggestedText1,"Suggested Text");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(2000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");

			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");

			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			click(TabletLocators.downloaddone, "Download low option");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadFreePremium Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadFreePremium(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			// click(TabletLocators.txtSuggestedText1,"Suggested Text");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(2000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadbutton)) {
				ReporterLog.pass("Download option", "Download option is visible");
			} else {
				ReporterLog.fail("Download option", "Download option is not visible");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadLapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadLapse(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			// click(TabletLocators.txtSuggestedText1,"Suggested Text");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(2000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(15000);
			String strSource = driver.getPageSource();
			String strText = "SUBSCRIBE";
			if (strSource.contains("SUBSCRIBE") && strSource.contains(strText)) {
				ReporterLog.pass("Subscribe header", "Subscribe header is displayed successfully");
			} else {
				ReporterLog.fail("Subscribe header", "Subscribe header is not displayed successfully");
			}
			if (strSource.contains("Promotion") && strSource.contains(strText)) {
				ReporterLog.pass("Promotion Text", "Promotion Text is displayed successfully");
			} else {
				ReporterLog.fail("Promotion Text", "Promotion Text is not displayed successfully");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadActive Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadActive(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			// click(TabletLocators.txtSuggestedText,"Suggested Text");
			Thread.sleep(3000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(20000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");
			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");
			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			// click(TabletLocators.downloadsavepref,"Download Preference option");
			click(TabletLocators.downloaddone, "Download low option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreferencedone)) {
				ReporterLog.pass("Download loading option", "Download loading option is visible");
			} else {
				ReporterLog.fail("Download loading option", "Download loading option is not visible");
			}
			driver.navigate().back();
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.mesect, "Me option");
			click(TabletLocators.Downloadoption, "Downlaod option");
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadmeload)) {
				ReporterLog.pass("Download loading option in Me Section",
						"Download loading option in Me Section is visible");
			} else {
				ReporterLog.fail("Download loading option in Me Section",
						"Download loading option in Me Section is not visible");
			}
			click(TabletLocators.downloadmeload, "Downlaod option to Pause");
			Thread.sleep(4000);
			click(TabletLocators.downloadmeload, "Downlaod option to Start Downloading Aagin");
			Thread.sleep(99000);
			click(TabletLocators.downloadmplay, "Playing the Downloaded content");
			Thread.sleep(6000);
			verifyVideoPlayback();
			click(TabletLocators.editButton, " Edit Option");
			click(TabletLocators.downloadmplay, "Downloaded content");
			click(TabletLocators.removeButton, " Remove Option");
			click(TabletLocators.deleteButton, " Delelting the Downloaded Video");
			Thread.sleep(3000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadQualityTab Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadQualityTab(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			click(TabletLocators.txtSuggestedText, "Suggested Text");
			Thread.sleep(3000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(20000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");
			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");
			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			click(TabletLocators.downloadsavepref, "Download Preference option");
			click(TabletLocators.downloaddone, "Download low option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreferencedone)) {
				ReporterLog.pass("Download loading option", "Download loading option is visible");
			} else {
				ReporterLog.fail("Download loading option", "Download loading option is not visible");
			}
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.mesect, "Me option");
			click(TabletLocators.Downloadoption, "Downlaod option");
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadmeload)) {
				ReporterLog.pass("Download loading option in Me Section",
						"Download loading option in Me Section is visible");
			} else {
				ReporterLog.fail("Download loading option in Me Section",
						"Download loading option in Me Section is not visible");
			}
			click(TabletLocators.downloadmeload, "Downlaod option to Pause");
			Thread.sleep(4000);
			click(TabletLocators.downloadmeload, "Downlaod option to Start Downloading Again");
			Thread.sleep(90000);
			click(TabletLocators.downloadmplay, "Playing the Downloaded content");
			Thread.sleep(6000);
			verifyVideoPlayback();
			click(TabletLocators.editButton, " Edit Option");
			click(TabletLocators.downloadmplay, "Downloaded content");
			click(TabletLocators.removeButton, " Remove Option");
			click(TabletLocators.deleteButton, " Delelting the Downloaded Video");
			Thread.sleep(3000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifyDownloadFavorite Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void VerifyDownloadFavorite(String description, String strEpisodeName) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, description, "Enter Movie Details");
			click(TabletLocators.searchmov, "Search Content");
			Thread.sleep(5000);
			click(TabletLocators.addwatchlater, "Added to watch later");
			click(TabletLocators.addfavorite, "Added to Favorite");
			Thread.sleep(2000);
			driver.navigate().back();
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.mesect, " Me option");
			click(TabletLocators.addwatchlater, " Watch later option");
			Thread.sleep(5000);
			click(TabletLocators.downloadmplay, "Playing the Downloaded content");
			Thread.sleep(4000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(20000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");
			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");
			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			click(TabletLocators.downloadsavepref, "Download Preference option");
			click(TabletLocators.downloaddone, "Download low option");
			Thread.sleep(60000);
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.Downloadoption, "Downlaod option");
			Thread.sleep(3000);
			click(TabletLocators.downloadmplay, " Downloaded content");
			Thread.sleep(6000);
			click(TabletLocators.downloadmplay1, " Downloaded content play");
			verifyVideoPlayback();
			click(TabletLocators.editButton, " Edit Option");
			click(TabletLocators.downloadmplay, "Downloaded content");
			click(TabletLocators.removeButton, " Remove Option");
			click(TabletLocators.conformlogout1, "Conform option");
			Thread.sleep(3000);
			driver.navigate().back();
			click(TabletLocators.addfav, "Favorite option");
			Thread.sleep(4000);
			click(TabletLocators.downloadmplay, "Downloaded content");
			Thread.sleep(4000);
			click(TabletLocators.Downloadvideo, "Download option");
			Thread.sleep(20000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download quality option", "Download quality option is visible");
			} else {
				ReporterLog.fail("Download quality option", "Download quality option is not visible");
			}
			click(TabletLocators.downloadlow, "Download low option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
				ReporterLog.pass("Download preference option", "Download preference option is visible");
			} else {
				ReporterLog.fail("Download preference option", "Download preference option is not visible");
			}
			click(TabletLocators.downloadsavepref, "Download Preference option");
			click(TabletLocators.downloaddone, "Download low option");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreferencedone)) {
				ReporterLog.pass("Download loading option", "Download loading option is visible");
			} else {
				ReporterLog.fail("Download loading option", "Download loading option is not visible");
			}
			driver.navigate().back();
			click(TabletLocators.mesect, "Me option");
			click(TabletLocators.Downloadoption, "Downlaod option");
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadmeload)) {
				ReporterLog.pass("Download loading option in Me Section",
						"Download loading option in Me Section is visible");
			} else {
				ReporterLog.fail("Download loading option in Me Section",
						"Download loading option in Me Section is not visible");
			}
			click(TabletLocators.downloadmeload, "Downlaod option to Pause");
			Thread.sleep(4000);
			click(TabletLocators.downloadmeload, "Downlaod option to Play");
			Thread.sleep(60000);
			click(TabletLocators.downloadmplay, "Playing the Downloaded content");
			Thread.sleep(6000);
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousBrowValidaton Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void AnonymousBrowValidaton() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(15000);
			clickOnTab("Browse");
			click(TabletLocators.firstCollection, "First collection");
			Thread.sleep(2500);
			click(TabletLocators.firstShowFromCollection, "First show from collection");
			click(TabletLocators.playButton1, "Play button is clicked");
			Thread.sleep(2500); // time to allow player to load
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");
			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);
			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousBrowValidatonFilter Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void AnonymousBrowValidatonFilter() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(2500);
			clickOnTab("Browse");
			click(TabletLocators.firstCollection, "First collection");
			Thread.sleep(2500);
			click(TabletLocators.Filtericon, "Filter icon in  browse page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is not Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is not Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Applyfilter)) {
				ReporterLog.pass("Apply Filter", "Apply Filter is Visible");
			} else {
				ReporterLog.fail("Apply Filter", "Apply Filter is not visible");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- SupportValidation Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void SupportValidation() {
		try {
			click(TabletLocators.Support, "Support option is clicked");
			click(TabletLocators.aboutus, "About Us option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutus1)) {
				ReporterLog.pass("About Us", "About Us Option is visible");
			} else {
				ReporterLog.fail("About Us", "About Us Option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("About Us Back Button", "About Us Back Button is displayed");
			} else {
				ReporterLog.fail("About Us Back Button", "About Us Back Button is not displayed");
			}
			/*
			 * String strSource=driver.getPageSource(); String strText="ABOUT US";
			 * if(strSource.contains("ABOUT US")) { Reporter.pass("About us context",
			 * "About us context is displayed"); } else { Reporter.fail("About us context",
			 * "About us context is not displayed successfully"); } String
			 * strText1="Just press Play."; if(strSource.contains("Just press Play.") &&
			 * strSource.contains(strText1)) { Reporter.pass("About us desc",
			 * "About us desc is displayed successfully"); } else {
			 * Reporter.fail("About us desc",
			 * "About us desc is not displayed successfully"); }
			 */

			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// FAQ
			click(TabletLocators.faq, "FAQ option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.faq)) {
				ReporterLog.pass("FAQ", "FAQ Option is visible");
			} else {
				ReporterLog.fail("FAQ", "FAQ is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("FAQ Back Button", "FAQ Back Button is displayed");
			} else {
				ReporterLog.fail("FAQ Back Button", "FAQ Back Button is not displayed");
			}
			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// Terms of Use

			click(TabletLocators.tos, "Terms of Use option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.tos1)) {
				ReporterLog.pass("Terms of Use", "Terms of Use is visible");
			} else {
				ReporterLog.fail("Terms of Use", "Terms of Use is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("Terms of Use Back Button", "Terms of Use Back Button is displayed");
			} else {
				ReporterLog.fail("Terms of Use Back Button", "Terms of Use Back Button is not displayed");
			}

			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// Privacy Policy
			click(TabletLocators.Privacypolicy, "Privacy policy option is clicked");
			Thread.sleep(13000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Privacypolicy1)) {
				ReporterLog.pass("Privacy policy", "Privacy policy is visible");
			} else {
				ReporterLog.fail("Privacy policy", "Privacy policy is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("Privacy policy Back Button", "Privacy policy Back Button is displayed");
			} else {
				ReporterLog.fail("Privacy policy Back Button", "Privacy policy Back Button is not displayed");
			}
			click(TabletLocators.aboutusback1, "Back Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contactus)) {
				ReporterLog.pass("Contact us", "Contact us button is visible");

			} else {
				ReporterLog.fail("Contact Us", "Contact Us is not visible");
			}
			click(TabletLocators.opensrc, "open source Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Opensrclicense1)) {
				ReporterLog.pass("Open Source License", "Open Source License button is visible");

			} else {
				ReporterLog.fail("Open Source License", "Open Source License is not visible");
			}
			click(TabletLocators.aboutusback1, "Back Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Appv)) {
				ReporterLog.pass("App Version", "App Version button is visible");

			} else {
				ReporterLog.fail("App Version", "App Version button is not visible");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- SupportValidation1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void SupportValidation1() {
		try {
			click(TabletLocators.Support, "Support option is clicked");
			click(TabletLocators.aboutus, "About Us option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutus1)) {
				ReporterLog.pass("About Us", "About Us Option is visible");
			} else {
				ReporterLog.fail("About Us", "About Us Option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("About Us Back Button", "About Us Back Button is displayed");
			} else {
				ReporterLog.fail("About Us Back Button", "About Us Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			// System.out.println(strSource);
			String strText = "So whether you want to watch the latest Bollywood blockbuster,";
			if (strSource.contains("ABOUT US") && strSource.contains(strText)) {
				ReporterLog.pass("About Us Page", "About Us Page is displayed successfully");

			} else {
				ReporterLog.fail("About Us Page", "About Us Page is not displayed successfully");
			}
			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// FAQ
			click(TabletLocators.faq, "FAQ option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.faq)) {
				ReporterLog.pass("FAQ", "FAQ Option is visible");
			} else {
				ReporterLog.fail("FAQ", "FAQ is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("FAQ Back Button", "FAQ Back Button is displayed");
			} else {
				ReporterLog.fail("FAQ Back Button", "FAQ Back Button is not displayed");
			}
			String strSourceFAQ = driver.getPageSource();
			if (strSourceFAQ.contains("HOOQ Digital Pte Ltd")) {
				ReporterLog.pass("FAQ Page", "FAQ Page is displayed successfully");
			} else {
				ReporterLog.fail("FAQ Page", "FAQ Page is not displayed successfully");
			}
			click(TabletLocators.aboutusback1, "Back Button is clicked");
			// Terms of Use

			click(TabletLocators.tos, "Terms of Use option is clicked");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.tos)) {
				ReporterLog.pass("Terms of Use", "Terms of Use is visible");
			} else {
				ReporterLog.fail("Terms of Use", "Terms of Use is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback1)) {
				ReporterLog.pass("Terms of Use Back Button", "Terms of Use Back Button is displayed");
			} else {
				ReporterLog.fail("Terms of Use Back Button", "Terms of Use Back Button is not displayed");
			}
			String strSourceTos = driver.getPageSource();
			System.out.println(strSourceTos);
			String strTextTos = "GENERAL TERMS AND CONDITIONS FOR HOOQ (“GENERAL TERMS”)";
			if (strSourceTos.toLowerCase().contains(strTextTos.toLowerCase())) {
				ReporterLog.pass("Terms of Use Page", "Terms of UsePage is displayed successfully");
			} else {
				ReporterLog.fail("Terms of Use Page", "Terms of UsePage is not displayed successfully");
			}
			click(TabletLocators.aboutusback, "Back Button is clicked");
			// Privacy Policy
			click(TabletLocators.Privacypolicy, "Privacy policy option is clicked");
			Thread.sleep(13000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Privacypolicy)) {
				ReporterLog.pass("Privacy policy", "Privacy policy is visible");
			} else {
				ReporterLog.fail("Privacy policy", "Privacy policy is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.aboutusback)) {
				ReporterLog.pass("Privacy policy Back Button", "Privacy policy Back Button is displayed");
			} else {
				ReporterLog.fail("Privacy policy Back Button", "Privacy policy Back Button is not displayed");
			}
			String strSourcepp = driver.getPageSource();
			String strTextpp = "Respecting your privacy and protecting your personal data";
			if (strSourcepp.toLowerCase().contains(strTextpp.toLowerCase())) {
				ReporterLog.pass("Privacy policy Page", "Privacy policy Page is displayed successfully");
			} else {
				ReporterLog.fail("Privacy policy Page", "Privacy policy Page is not displayed successfully");
			}
			click(TabletLocators.aboutusback, "Back Button is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.contactus)) {
				ReporterLog.pass("Contact us", "Contact us button is visible");

			} else {
				ReporterLog.fail("Contact Us", "Contact Us is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Opensrclicense)) {
				ReporterLog.pass("Open Source License", "Open Source License button is visible");

			} else {
				ReporterLog.fail("Open Source License", "Open Source License is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Appv)) {
				ReporterLog.pass("App Version", "App Version button is visible");

			} else {
				ReporterLog.fail("App Version", "App Version button is not visible");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- LapsedContentPlayBackTablet Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void LapsedContentPlayBackTablet(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.lapsedhooqClickTab, "First collection in  browse page");
			waitForInVisibilityOfElement(TabletLocators.genreListView, "Browse page content");
			click(TabletLocators.lapsedAssetTitleTab, "first episode/movie from selected collection");
			Thread.sleep(2000);
			click(TabletLocators.playButtonTab, "Play button");
			Thread.sleep(5000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedpaywallBtn)) {
				ReporterLog.pass("Get Started page", "Subscribe button is displayed");
			} else {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.videoView)) {
					ReporterLog.fail("Get Started", "Video is playing for lapsed user");

				} else {
					ReporterLog.fail("Get Started", "Get started button displayed");
				}
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedClose)) {
				ReporterLog.pass("Get Started page", "Close button is visible");
			} else {
				ReporterLog.fail("Get Started page", "Close button is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedImage)) {
				ReporterLog.pass("Get Started page", "Get started image is displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedDescription)) {
				String des = getText(TabletLocators.anonDescription, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + des);
			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			// click on GetStarted button
			click(TabletLocators.lapsedpaywallBtn, "Get Started button");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- contentPlayBackTablet Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void contentPlayBackTablet(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			// click on browse button
			click(TabletLocators.btnBrowse, "Browse button");
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			Thread.sleep(2000);
			click(TabletLocators.activehooqClickTab, "First collection in  browse page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.activeAssetTitle, title + " is from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButtonTab)) {
				click(TabletLocators.playButtonTab, "Play button");
			} else {
				ReporterLog.fail("Playbutton", "Play button is not displayed for " + title);
			}
			verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousBrowValidatonTablet Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void AnonymousBrowValidatonTablet() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(2500);
			clickOnTab("Browse");
			Thread.sleep(2500);
			click(TabletLocators.firstCollectiont, "First collection");
			Thread.sleep(2500);
			click(TabletLocators.firstShowFromCollection, "First show from collection");
			Thread.sleep(500);
			click(TabletLocators.playButtonTab, "Play button is clicked");
			Thread.sleep(2500); // time to allow player to load
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");
			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonImage)) {
				ReporterLog.pass("Get Started Image", "Get started image is displayed");
			} else {
				ReporterLog.fail("Get Started Image", "Get started image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription)) {
				String description = getText(TabletLocators.anonDescription, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.anonpaywallBtn, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- moviestab Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void moviestab(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnMovies, "Movies Tab");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.headerMovies)) {
				ReporterLog.pass("Movies Section", "Movies Section is displayed successfully ");
				/*
				 * String s1=getText(TabletLocators.txtFirstColl,
				 * "First Collection in Movies Section"); System.out.println(s1);
				 */
				MobileElement eleMobile = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/epoxyRecyclerView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/tvTitle"));
				String s2 = eleEpisode.get(0).getText();
				System.out.println(s2);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.linkseeAll)) {
					ReporterLog.pass("Verify See All Link", "See All Link Displayed successfully");
					List<MobileElement> eleList = eleMobile.findElements(By.id("tv.hooq.android:id/txt_see_all"));
					if (eleList.size() > 0) {

						eleList.get(0).click();
						String s3 = getText(TabletLocators.SeeAllfirstCollHeader, "See All First Collection Header");
						if (s2.equalsIgnoreCase(s3)) {
							ReporterLog.pass("Verify Collection", "See All Collection Displayed Sucessfully as" + s3);
							eleEpisode.get(0).click();
							ActiveuserplayMovie1();

						} else {
							ReporterLog.fail("Verify Collection", "See All Collection is not displayed Sucessfully as");

						}

					}
				} else {
					ReporterLog.fail("Verify See All Link", "See All link is not displayed");
				}

			} else {
				ReporterLog.fail("Movies Section", "Movies Section is not displayed successfully ");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- tvshowsTab Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void tvshowsTab(String description) {
		try {
			ReporterLog.info("HOOQ Android", description);
			click(TabletLocators.btnTVShows, "TV Shows Tab");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.headerTVShows)) {
				ReporterLog.pass("TV Shows Section", "TV Shows Section is displayed successfully ");
				/*
				 * String s1=getText(TabletLocators.txtFirstColl,
				 * "First Collection in Movies Section"); System.out.println(s1);
				 */
				MobileElement eleMobile = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/epoxyRecyclerView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/tvTitle"));
				String s2 = eleEpisode.get(0).getText();
				System.out.println(s2);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.linkseeAll)) {
					ReporterLog.pass("Verify See All Link", "See All Link Displayed successfully");
					List<MobileElement> eleList = eleMobile.findElements(By.id("tv.hooq.android:id/txt_see_all"));
					if (eleList.size() > 0) {

						eleList.get(0).click();
						String s3 = getText(TabletLocators.SeeAllfirstCollHeader, "See All First Collection Header");
						if (s2.equalsIgnoreCase(s3)) {
							ReporterLog.pass("Verify Collection", "See All Collection Displayed Sucessfully as" + s3);
							eleEpisode.get(0).click();
							for (int i = 1; i <= 1; i++) {
								swipeUpOrBottom(true);
							}
							MobileElement eleMobile2 = (MobileElement) driver
									.findElement(By.id("tv.hooq.android:id/recycleSeasonList"));
							List<MobileElement> eleEpisode2 = eleMobile2
									.findElements(By.className("android.widget.RelativeLayout"));
							eleEpisode2.get(0).click();
							List<MobileElement> eleList1 = eleMobile2
									.findElements(By.id("tv.hooq.android:id/play_icon"));
							System.out.println(eleList1.size());
							if (eleList1.size() > 0) {
								eleList1.get(0).click();
								Thread.sleep(4000);
								verifyVideoPlayback();
								driver.navigate().back();
							}

						} else {
							ReporterLog.fail("Verify Collection", "See All Collection is not displayed Sucessfully as");

						}

					}
				} else {
					ReporterLog.fail("Verify See All Link", "See All link is not displayed");
				}
			} else {
				ReporterLog.fail("TV Shows Section", "TV Shows Section is not displayed successfully ");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLogin Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static String  dataDisplayed;

	public static void testLogin(String email, String region, String envi) {
		try {

			if (strUserID == null) {
				strUserID = email;
			} else {
				if (strUserID.equals(email) == false) {
					blnFirstLogin = false;
				}
			}
			ReporterLog.info("HOOQ Android", "Login into app");
			if (blnFirstLogin == true) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.MeProf)) {
					logOut();
				}
				strUserID = email;

				// already have an account
				click(TabletLocators.Login, "Login option");
				// email id
				click(TabletLocators.emailTab, "Email tab");
				click(TabletLocators.addnewemail1, "Email Textbox");
				Thread.sleep(1000);

				List<WebElement> eleList1 = driver.findElements(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));
				System.out.println(eleList1.size());
				if (eleList1.size() > 0) {
					eleList1.get(0).click();
				}

				click(TabletLocators.addnewemail1, "Email Textbox");
				type(TabletLocators.addnewemail1, email, "Email Address");
				click(TabletLocators.emailDone, "Done button");
				Thread.sleep(5000);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
					// Date at = new Date();
					// System.out.println(sdf.format(at));
					// Login_End_Date_Time=sdf.format(at);
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
					blnFirstLogin = false;

				} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
					//dualLaunc(email);
					click(TabletLocators.Login, "Already have an account?");
					click(TabletLocators.emailTab, "Email tab");
					tapOn("Login");
					click(TabletLocators.Region, "Region List");
					click(TabletLocators.NameOfRegion(region), region);
					click(TabletLocators.Api1, "API");
					click(TabletLocators.NameofAPI(envi), envi);
					click(TabletLocators.email, "email dropdown");
					click(TabletLocators.addnewEmailButton, "+ add new email");
					type(TabletLocators.addnewemail, email, "Email Address");
					click(TabletLocators.emailDone, "Done button");

					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
						ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
						blnFirstLogin = false;
					} else {
						ReporterLog.fail("Verify Login", "Login failed with " + email);
					}
				} else {
					ReporterLog.fail("Verify Login", "Login failed with " + email);
				}
			} else {
				ReporterLog.pass("Verfify Login", "User is already logged in with " + email);
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLogin Developed By :- Jagadish Date :- 19-June-2019
	 */

	public static void testLoginReg(String email, String region, String envi) {
		try {
			// try {Thread.sleep(3000);} catch (InterruptedException e){}
			Thread.sleep(5000);
			
			System.out.println("ActionEngine.driver.findElements(TabletLocators.MeProf).size() > 0: "
			+ActionEngine.driver.findElements(TabletLocators.MeProf).size());
			
			if (ActionEngine.driver.findElements(TabletLocators.MeProf).size() > 0) {
				click(TabletLocators.MeProf, "Me Profile");
				for (int i = 1; i <= 2; i++) {
					swipeUpOrBottom(true);

				}
				String strLoggedinUsr = getText(TabletLocators.loggedInAccount, "Logged in User account");
				if (strLoggedinUsr.equalsIgnoreCase(email)) {
					ReporterLog.pass("Verfify Login", "User is already logged in with " + email);
					driver.navigate().back();
				} else {
					ReporterLog.pass("Verfify Login",
							"User is already logged in with a different id: " + strLoggedinUsr);
					ReporterLog.pass("Verfify Login", "Logging out and login again with" + email);
					logOut();
					testLoginReg(email, ConfigDetails.country, ConfigDetails.environment);
				}
			} else {
				// already have an account
				click(TabletLocators.Login, "Login option");
				// email id
				click(TabletLocators.emailTab, "Email tab");
				click(TabletLocators.addnewemail1, "Email Textbox");
				try {
					Thread.sleep(1000);
					ActionEngine.driver.navigate().back();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

				// To remove the default email popup
				List<WebElement> eleList1 = driver.findElements(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));
				// System.out.println(eleList1.size());
				if (eleList1.size() > 0) {
					eleList1.get(0).click();
				}

				click(TabletLocators.addnewemail1, "Email Textbox");
				ActionEngine.driver.navigate().back();
				type(TabletLocators.addnewemail1, email, "Email Address");
				click(TabletLocators.emailDone, "Done button");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
					// Date at = new Date();
					// System.out.println(sdf.format(at));
					// Login_End_Date_Time=sdf.format(at);
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);

				} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
					click(TabletLocators.lnkpassword, "Enter Password link");
					Thread.sleep(5000);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.pwdPageHeader)) {
						ReporterLog.pass("Verfify Login", "Enter Password page is displayed");
						type(TabletLocators.txtPassword, "123456", "Password");
						Thread.sleep(5000);
						click(TabletLocators.btnPwdOkay, "Okay");
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
							ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
							blnFirstLogin = false;
						}
						else
							throw new Exception();
					}
					else
						throw new Exception();
				}
				else
					throw new Exception();
			}
				
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginmob Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void testLoginmob(String mobile, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.mobile, "Mobile tab");
			click(TabletLocators.mobiledrop, "Mobile drop down");
			Thread.sleep(2000);
			click(TabletLocators.mobalert1, "Mobile country code");
			click(TabletLocators.email1, "email dropdown");
			System.out.println(mobile);
			type(TabletLocators.addnewmobile, mobile, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			click(TabletLocators.mobalert, "Verification Alert");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + mobile);

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(mobile);
				click(TabletLocators.Login, "Already have an account?");
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + mobile);
				else
					ReporterLog.fail("Verify Login", "Login failed with " + mobile);
			} else
				ReporterLog.fail("Verify Login", "Login failed with " + mobile);
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginpre Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void testLoginpre(String email, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			// email id
			click(TabletLocators.emailTab, "Email tab");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api2, "API");
			Thread.sleep(3000);
			click(TabletLocators.preprod, "ENVI");
			click(TabletLocators.email2, "email dropdown");
			type(TabletLocators.addnewemail, email, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			Thread.sleep(7000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(email);
				click(TabletLocators.Login, "Already have an account?");
				click(TabletLocators.emailTab, "Email tab");
				tapOn("Login");
				click(TabletLocators.Region, "Region List");
				click(TabletLocators.NameOfRegion(region), region);
				click(TabletLocators.Api2, "API");
				click(TabletLocators.NameofAPI(envi), envi);
				click(TabletLocators.email2, "email dropdown");
				click(TabletLocators.addnewEmailButton, "+ add new email");
				type(TabletLocators.addnewemail, email, "Email Address");
				click(TabletLocators.emailDone, "Done button");
				// Thread.sleep(3000);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
				else
					ReporterLog.fail("Verify Login", "Login failed with " + email);
			} else
				ReporterLog.fail("Verify Login", "Login failed with " + email);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnGetTVContentName Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String  fnGetTVContentName(int intRow, String strSheetName) throws IOException {
		String strContent = "";
		String spreadsheetId = "1QeeKgl-s3eM1x_PU4vbbKY2_BGksjviN0jp7vCcteUk";
		String rangePassCount = strSheetName + "!D" + intRow + ":D" + intRow;
		strContent = GoogleDriveAPI.fnGetData(spreadsheetId, rangePassCount);
		return strContent;
	}

	/***
	 * Function Name :- fnGetCountrySheet Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String  fnGetCountrySheet(String strCode) {
		String strCountry = "";
		if (strCode.toLowerCase().contains("in")) {
			strCountry = "India";
		} else if (strCode.toLowerCase().contains("id")) {
			strCountry = "Indonesia";
		} else if (strCode.toLowerCase().contains("ph")) {
			strCountry = "Philippines";
		} else if (strCode.toLowerCase().contains("sg")) {
			strCountry = "Singapore";
		} else if (strCode.toLowerCase().contains("th")) {
			strCountry = "Thailand";
		}
		return strCountry;
	}

	/***
	 * Function Name :- fnGetMoviesContentName Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String  fnGetMoviesContentName(int intRow, String strSheetName) throws IOException {
		String strContent = "";
		String spreadsheetId = "1QeeKgl-s3eM1x_PU4vbbKY2_BGksjviN0jp7vCcteUk";
		String rangePassCount = strSheetName + "!C" + intRow + ":C" + intRow;
		strContent = GoogleDriveAPI.fnGetData(spreadsheetId, rangePassCount);
		return strContent;
	}

	/***
	 * Function Name :- fnGetMoviesContentNameHotStar Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static String  fnGetMoviesContentNameHotStar(int intRow, String strSheetName) throws IOException {
		String strContent = "";
		String spreadsheetId = "1QeeKgl-s3eM1x_PU4vbbKY2_BGksjviN0jp7vCcteUk";
		String rangePassCount = strSheetName + "!B" + intRow + ":B" + intRow;
		strContent = GoogleDriveAPI.fnGetData(spreadsheetId, rangePassCount);
		return strContent;
	}

	/***
	 * Function Name :- fnGetTVShowsContentNameHotStar Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */

	public static String  fnGetTVShowsContentNameHotStar(int intRow, String strSheetName) throws IOException {
		String strContent = "";
		String spreadsheetId = "1QeeKgl-s3eM1x_PU4vbbKY2_BGksjviN0jp7vCcteUk";
		String rangePassCount = strSheetName + "!B" + intRow + ":B" + intRow;
		strContent = GoogleDriveAPI.fnGetData(spreadsheetId, rangePassCount);
		return strContent;
	}

	/***
	 * Function Name :- fnGetTVShowsSeasons Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String  fnGetTVShowsSeasons(int intRow, String strSheetName) throws IOException {
		String strContent = "";
		String spreadsheetId = "1QeeKgl-s3eM1x_PU4vbbKY2_BGksjviN0jp7vCcteUk";
		String rangePassCount = strSheetName + "!C" + intRow + ":C" + intRow;
		strContent = GoogleDriveAPI.fnGetData(spreadsheetId, rangePassCount);
		return strContent;
	}

	/***
	 * Function Name :- Top10playback Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public boolean Top10playback(String strContent) {

		boolean blnStatus = true;
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, strContent, "Enter Movie Details");
			Thread.sleep(4000);
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode.size());
			if (eleEpisode.size() > 0) {
				eleEpisode.get(0).click();
				Thread.sleep(4000);
			} else {
				blnStatus = false;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}

		return blnStatus;
	}

	/***
	 * Function Name :- HotStarGetTotalEpisodes Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public int HotStarGetTotalEpisodes() {
		int intSize = 0;
		try {

			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/recycleSeasonList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/container"));
			System.out.println(eleEpisode.size());
			return eleEpisode.size();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}

		return intSize;
	}

	/***
	 * Function Name :- HotstarTVShow Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public boolean HotstarTVShow(int intEpisode) {

		boolean blnStatus = true;
		try {

			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/recycleSeasonList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/container"));
			System.out.println(eleEpisode.size());
			if (eleEpisode.size() > 0) {
				for (int i = 0; i < eleEpisode.size(); i++) {
					WebElement eleEpisodeNo = eleEpisode.get(i).findElement(By.id("tv.hooq.android:id/episode_number"));
					int intEpisodeVerify = Integer.parseInt(eleEpisodeNo.getText());
					if (intEpisodeVerify == intEpisode) {
						// Play the Video
						eleEpisode.get(i).findElement(By.id("tv.hooq.android:id/button_play")).click();
						break;
					}
				}
				Thread.sleep(4000);
			} else {
				blnStatus = false;
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}

		return blnStatus;
	}

	/***
	 * Function Name :- Top10playbackmovie Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void Top10playbackmovie(String strContent) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Clicked of search option");
			click(TabletLocators.btnSearch, "Search button");
			type(TabletLocators.edtSearch, strContent, "Enter Movie Details");
			Thread.sleep(4000);
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode.size());
			eleEpisode.get(0).click();
			Thread.sleep(4000);
			click(TabletLocators.playButton1, "Play button");

			verifyVideoPlayback();
			driver.navigate().back();
			// Thread.sleep(2000);
			driver.navigate().back();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testCCDetail Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void testCCDetail(String mobile, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Enter CC Details");
			// already have an account
			click(TabletLocators.ccdetailnumber, "email dropdown");
			type(TabletLocators.ccdetailnumber, mobile, "Email Address");
			// click(TabletLocators.emailDone,"Done button");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginmob1 Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void testLoginmob1(String mobile, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.mobile, "Mobile tab");
			click(TabletLocators.mobiledrop, "Mobile drop down");
			Thread.sleep(2000);
			click(TabletLocators.mobalert1, "Mobile country code");
			click(TabletLocators.email1, "email dropdown");
			System.out.println(mobile);
			type(TabletLocators.addnewmobile, mobile, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + mobile);

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(mobile);
				click(TabletLocators.Login, "Already have an account?");
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.fail("Verify Login", "Successfully logged in with " + mobile);
				else
					ReporterLog.pass("Verify Login", "Login failed with " + mobile);
			} else
				ReporterLog.pass("Verify Login", "Login failed with " + mobile);
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginmobVerify Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void testLoginmobVerify(String mobile, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.mobile, "Mobile tab");
			click(TabletLocators.mobiledrop, "Mobile drop down");
			Thread.sleep(2000);
			click(TabletLocators.mobalert1, "Mobile country code");
			click(TabletLocators.email, "email dropdown");
			System.out.println(mobile);
			type(TabletLocators.addnewmobile, mobile, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			click(TabletLocators.mobalert, "Verification Alert");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + mobile);

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(mobile);
				click(TabletLocators.Login, "Already have an account?");
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + mobile);
				else
					ReporterLog.fail("Verify Login", "Login failed with " + mobile);
			} else
				ReporterLog.pass("Verify Login", "Login failed with " + mobile);
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginVerify Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void testLoginVerify(String email, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			// email id
			click(TabletLocators.emailTab, "Email tab");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.email, "email dropdown");
			click(TabletLocators.addnewEmailButton, "+ add new email");
			type(TabletLocators.addnewemail, email, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo) == false) {
				ReporterLog.pass("Verfify Login", "Unable to logged in with " + email);
			} else
				ReporterLog.fail("Verify Login", "Login Success with " + email);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLogintvod Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void testLogintvod(String email, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			// email id
			click(TabletLocators.emailTab, "Email tab");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.email, "email dropdown");
			click(TabletLocators.addnewEmailButton, "+ add new email");
			type(TabletLocators.addnewemail, email, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(email);
				click(TabletLocators.Login, "Already have an account?");
				click(TabletLocators.emailTab, "Email tab");
				tapOn("Login");
				click(TabletLocators.Region, "Region List");
				click(TabletLocators.NameOfRegion(region), region);
				click(TabletLocators.Api1, "API");
				click(TabletLocators.NameofAPI(envi), envi);
				click(TabletLocators.email, "email dropdown");
				click(TabletLocators.addnewEmailButton, "+ add new email");
				type(TabletLocators.addnewemail, email, "Email Address");
				click(TabletLocators.emailDone, "Done button");
				// Thread.sleep(3000);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
				else
					ReporterLog.fail("Verify Login", "Login failed with " + email);
			} else
				ReporterLog.fail("Verify Login", "Login failed with " + email);
			// System.out.println(Login_Start_Date_Time);
			// System.out.println(Login_End_Date_Time);
			// st.executeUpdate("INSERT INTO
			// hooqandroid(Login_Start_Date_Time,Login_End_Date_Time) VALUES ('" +
			// Login_Start_Date_Time + "','" + Login_End_Date_Time +"')");
			// st.close();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginmobinvalid Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void testLoginmobinvalid(String mobile, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.mobile, "Mobile tab");
			click(TabletLocators.mobiledrop, "Mobile drop down");
			Thread.sleep(2000);
			click(TabletLocators.mobalert1, "Mobile country code");
			type(TabletLocators.addnewmobile, mobile, "Email Address");
			click(TabletLocators.emailDone, "Done button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo) == false) {
				ReporterLog.pass("Verify Login", "Login failed with " + mobile);

			} else {
				ReporterLog.fail("Verfify Login", "Successfully logged in with " + mobile);
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLogintvod1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void testLogintvod1(String email, String region, String envi) {
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			// email id
			click(TabletLocators.emailTab, "Email tab");
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api2, "API");
			Thread.sleep(3000);
			click(TabletLocators.nightenvi, "ENVI");
			// click(TabletLocators.NameofAPI1(envi),envi);
			click(TabletLocators.email, "email dropdown");
			click(TabletLocators.addnewEmailButton, "+ add new email");
			type(TabletLocators.addnewemail, email, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			Thread.sleep(7000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(email);
				click(TabletLocators.Login, "Already have an account?");
				click(TabletLocators.emailTab, "Email tab");
				tapOn("Login");
				click(TabletLocators.Region, "Region List");
				click(TabletLocators.NameOfRegion(region), region);
				click(TabletLocators.Api2, "API");
				click(TabletLocators.NameofAPI(envi), envi);
				click(TabletLocators.email, "email dropdown");
				click(TabletLocators.addnewEmailButton, "+ add new email");
				type(TabletLocators.addnewemail, email, "Email Address");
				click(TabletLocators.emailDone, "Done button");
				// Thread.sleep(3000);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
				else
					ReporterLog.fail("Verify Login", "Login failed with " + email);
			} else
				ReporterLog.fail("Verify Login", "Login failed with " + email);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- testLoginmobile Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void testLoginmobile(String email, String region, String envi) {
		// boolean blnfound=false;
		try {
			ReporterLog.info("HOOQ Android", "Search Movie / TV Episode");
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			// email id
			click(TabletLocators.emailTab, "Email tab");
			// tapOn("Login");
			// click(TabletLocators.Region,"Region List");
			// click(TabletLocators.NameOfRegion(region),region);
			// click(TabletLocators.Api1,"API");
			// click(TabletLocators.NameofAPI(envi),envi);
			click(TabletLocators.email, "email dropdown");
			click(TabletLocators.addnewEmailButton, "+ add new email");
			type(TabletLocators.addnewemail, email, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo)) {
				ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
				// return true;

			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.iHaveVerifiedBtn)) {
				//dualLaunc(email);
				click(TabletLocators.Login, "Already have an account?");
				click(TabletLocators.emailTab, "Email tab");
				tapOn("Login");
				click(TabletLocators.Region, "Region List");
				click(TabletLocators.NameOfRegion(region), region);
				click(TabletLocators.Api1, "API");
				click(TabletLocators.NameofAPI(envi), envi);
				click(TabletLocators.email, "email dropdown");
				click(TabletLocators.addnewEmailButton, "+ add new email");
				type(TabletLocators.addnewemail, email, "Email Address");
				click(TabletLocators.emailDone, "Done button");
				// Thread.sleep(3000);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
					ReporterLog.pass("Verfify Login", "Successfully logged in with " + email);
				else
					ReporterLog.fail("Verify Login", "Login failed with " + email);
			} else
				ReporterLog.fail("Verify Login", "Login failed with " + email);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- logOut Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void logOut() {
		try {
			click(TabletLocators.MeProf, "Me Profile");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);

			}
			click(TabletLocators.logOut, "ME icon");
			click(TabletLocators.conformlogout1, "Confirm Logout");
			// Thread.sleep(2000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyfailedLogin Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyfailedLogin(String email, String region, String description) {
		try {
			click(TabletLocators.Login, "logInButton");
			click(TabletLocators.email, "emailField");
			click(TabletLocators.addnewEmailButton, "emailId");
			type(TabletLocators.addnewemail, email, "emailField");
			click(TabletLocators.emailDone, "Done");
			Thread.sleep(2000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- voucherEntry Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void voucherEntry(String email, String region, String api, String description, String vocher) {
		try {
			click(TabletLocators.MeProf, "Me Profile");
			click(TabletLocators.Subscrip, "Subscription");
			// click(TabletLocators.vcode(vocher),"vocher code");
			type(TabletLocators.vocherTextBox, vocher, "Vocher Code");
			click(TabletLocators.vocherSubmit, "Vocher submit");
			// return false;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- navigateToSubscription Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void navigateToSubscription(boolean planSelector) {
		// boolean res=false;
		try {
			// Reporter.reportStep("Navigating to Subscription page");
			click(TabletLocators.MeProf, "ME icon");
			Thread.sleep(2000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.Subscrip, "Subscription");
			if (planSelector)
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ManageS)) {
					click(TabletLocators.ManageS, "Manage Subscription");
				} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SubscribeS)) {
					click(TabletLocators.SubscribeS, "Manage Subscription");
				}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}

		// return res;
	}

	/***
	 * Function Name :- navigateToSubscriptionVocherCodeRedemption Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */

	public static void navigateToSubscriptionVocherCodeRedemption() {
		// boolean res=false;
		try {
			// Reporter.reportStep("Navigating to Subscription page");
			click(TabletLocators.MeProf, "Me Profile");
			for (int i = 1; i <= 4; i++) {
				swipeUpOrBottom(true);

			}
			click(TabletLocators.Subscrip, "Subscription");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}

		// return res;
	}

	/***
	 * Function Name :- swipeUpElement Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void swipeUpElement(By by, boolean up) {
		// boolean res=false;
		try {
			MobileElement element = getMobileElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", element.getId());
			if (up) {
				//element.swipe(SwipeElementDirection.UP, 500);
				
				scrollObject.put("direction", "up");
				js.executeScript("mobile: swipe", scrollObject);
				
				ReporterLog.pass("SwipeLeft", "Swipe left action performed");
			} else {
				scrollObject.put("direction", "down");
				js.executeScript("mobile: swipe", scrollObject);
				ReporterLog.pass("SwipeRight", "Swipe right action performed");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- applyVoucher Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void applyVoucher(String code, boolean click) {
		try {
			type(TabletLocators.vocherTextBox, code, "Voucher textbox");
			if (click)
				click(TabletLocators.vocherSubmit, "Submit button");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- scrollTo Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	boolean scrollTo(String value) {
		while (driver.findElements(By.name(value)).size() == 0) {

			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			
			new TouchAction((PerformsTouchActions) getMobileDriver()).
			longPress(LongPressOptions.longPressOptions().
					withPosition(PointOption.point(scrollStart,scrollEnd))).perform();
		
			
			//TouchAction act = new TouchAction((MobileDriver<?>) driver);
			//act.longPress(scrollStart, scrollEnd).release().perform();
		}
		if (driver.findElements(By.name(value)).size() > 0) {
			return true;
		} else
			return false;
	}

	/***
	 * Function Name :- facebookLogin Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void facebookLogin(String email, String password) {
		try {
			click(TabletLocators.Login, "Already have an account?");
			click(TabletLocators.fbLink, "FB link");
			type(TabletLocators.fbUserName, email, "FB UserName");
			type(TabletLocators.fbPassword, password, "FB Password");
			click(TabletLocators.fbLogin, "FB Login");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
				ReporterLog.pass("Verfify FBLogin", "Successfully logged in with FB credentials");
			else
				ReporterLog.fail("Verify FBLogin", "Login failed with fb credentials");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- navigateToMeProfile Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void navigateToMeProfile() {
		// boolean res=false;
		try {
			// Reporter.reportStep("Navigating to Profile page");
			click(TabletLocators.MeProf, "ME icon");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- navigateToMeProfilet Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void navigateToMeProfilet() {
		// boolean res=false;
		try {
			// Reporter.reportStep("Navigating to Profile page");
			click(TabletLocators.MeProft, "ME icon");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- navigateToSubscription Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void navigateToSubscription() {
		// boolean res=false;
		try {
			// Reporter.reportStep("Navigating to Subscription page");
			click(TabletLocators.MeProf, "ME icon");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.Subscrip, "Subscription");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeSubScripBtn)) {
				click(TabletLocators.SubscribeS, "Subscribe Now");
				// click(TabletLocators.activeSubScripBtn, "ManageSubscription");
			} else {
				// click(TabletLocators.SubscribeS, "Subscribe Now");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- navigateToBrowseProfile Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void navigateToBrowseProfile(boolean planSelector) {
		// boolean res=false;
		try {
			// Reporter.reportStep("Navigating to Subscription page");
			click(TabletLocators.btnBrowse, "ME icon");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- InvalidtestLogin Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void InvalidtestLogin(String email, String region, String envi, String description) {
		try {
			// Reporter.reportStep("Login into app");
			// already have an account
			click(TabletLocators.Login, "Already have an account?");
			// email id
			click(TabletLocators.emailTab, "Email tab");
			Thread.sleep(500);
			tapOn("Login");
			click(TabletLocators.Region, "Region List");
			click(TabletLocators.NameOfRegion(region), region);
			click(TabletLocators.Api1, "API");
			click(TabletLocators.NameofAPI(envi), envi);
			click(TabletLocators.email, "email dropdown");
			click(TabletLocators.addnewEmailButton, "+ add new email");
			type(TabletLocators.addnewemail, email, "Email Address");
			click(TabletLocators.emailDone, "Done button");
			Thread.sleep(500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HOOQLogo))
				ReporterLog.fail("Verfify Login", "Successfully logged in with " + email);
			else
				ReporterLog.pass("Verfify Login", "Invalid login credentials entered and account does not exist");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- activateApp Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void activateApp() {
		try {
			/*
			 * String AppPackage = configProps.getProperty("appPackage"); String AppActivity
			 * = configProps.getProperty("appActivity"); System.out.println(AppActivity);
			 * String OSVersion = configProps.getProperty("OSVersion"); String ipkPath =
			 * configProps.getProperty("apkPath"); String tempIPK =
			 * System.getProperty("user.dir")+ipkPath; File ipk = new File(tempIPK);
			 * DesiredCapabilities capabilitiesForAppium = new DesiredCapabilities();
			 * System.out.println("DeviceName is : " + DeviceName);
			 * capabilitiesForAppium.setCapability("deviceName",DeviceName);
			 * capabilitiesForAppium.setCapability("platformVersion",OSVersion);
			 * capabilitiesForAppium.setCapability("platformName","Android");
			 * capabilitiesForAppium.setCapability("newCommandTimeout","120000");
			 * //capabilitiesForAppium.setCapability("autoWebview", "true");
			 * capabilitiesForAppium.setCapability("autoWebviewTimeout","1000");
			 * //capabilitiesForAppium.setCapability("fullReset", true);
			 * capabilitiesForAppium.setCapability("appPackage", AppPackage);
			 * capabilitiesForAppium.setCapability("appActivity", AppActivity);
			 * System.out.println(ipk.getCanonicalPath());
			 * capabilitiesForAppium.setCapability("app", ipk.getCanonicalPath());
			 * AndroidDriver2 = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
			 * capabilitiesForAppium); driver = (AndroidDriver2);
			 * driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 * Reporter.pass("ActivateApp", "Application is launched succesfully");
			 */

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- dualLaunc Developed By :- Raja Reddy Date :- 23-May-2019
	 *//*

	public static void dualLaunc(String email) {
		try {
			// driver.quit();
			Thread.sleep(2000);

			// Reporter.reportStep("Launching Chrome");
			// driver = LaunchChrome();
			// Reporter.reportStep("Navigating to http://yopmail.com");
			//launchUrl("http://yopmail.com");
			// Reporter.reportStep("Activating user credentials");
			activateURLInMobile(email);
			// Reporter.reportStep("Launching app");
			activateApp();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}*/

	/***
	 * Function Name :- LaunchChrome Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	@SuppressWarnings("rawtypes")
	public RemoteWebDriver LaunchChrome() {
		try {
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "my phone");
			capabilities.setCapability(MobileCapabilityType.VERSION, "5.0.1");
			capabilities.setCapability("newCommandTimeout", "120000");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			ReporterLog.pass("Chrome Launch", "Chrome launched succesfully");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			ReporterLog.fail("Chrome Launch", "Failed to launch chrome");
		}
		return (RemoteWebDriver) driver;
	}

	/***
	 * Function Name :- activateURLInMobile Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 *//*

	public static void activateURLInMobile(String email) {
		try {
			String[] mail = email.split("@");
			driver.findElement(By.xpath("//input[@id='login']")).sendKeys(mail[0]);
			click(By.xpath("//input[@type='submit']"), "Submit button");
			Thread.sleep(2000);
			switchToFrameById("ifinbox");
			click(By.xpath("//span[contains(text(),'Your verification code')]"), "First emailid");
			switchToDefaultFrame();
			click(By.xpath("//a[text()='Confirm Email']"), "Confirm Email");
			Thread.sleep(5000);
			driver.quit();

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}*/

	/***
	 * Function Name :- verifyPlanSelector Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyPlanSelector(String plan, boolean autoRenewOption, String footerText) {
		boolean res = false;
		try {
			String[] planSelector = plan.split("\\^");
			// Reporter.reportStep(planSelector[0]);
			if (planSelector.length == 4) {
				bringIntoView(planSelector[0]);
				@SuppressWarnings("unchecked")
				List<MobileElement> elements = ((AndroidDriver<MobileElement>) driver)
						.findElements(TabletLocators.planTitle);
				MobileElement centerElement = determineCenter(elements);
				String title = centerElement.getText();
				if (title.equalsIgnoreCase(planSelector[0])) {
					ReporterLog.pass(planSelector[0], planSelector[0] + " plan is available");
					res = true;
				}

				if (!res) {
					ReporterLog.fail(planSelector[0], planSelector[0] + " is not available");
				} else {
					
					verifyText(TabletLocators.planSubtitle, planSelector[1].trim());
					verifyText(TabletLocators.planDescription, planSelector[2].trim());
					if (driver.findElements(TabletLocators.planPrice(planSelector[3])).size() > 0) {
						ReporterLog.pass(planSelector[3], planSelector[3] + " is available in the selected plan");
					} else {
						ReporterLog.fail(planSelector[3], planSelector[3] + " is not available in the selected plan");
					}

				}
				if (autoRenewOption) {
					if (driver.findElements(TabletLocators.autoRenew).size() > 0) {
						ReporterLog.pass("AutoRenew", "Auto renew option is available");
					} else {
						ReporterLog.fail("AutoRenew", "Auto renew option is not available");
					}

					verifyText(TabletLocators.autoRenewDesc(planSelector[3]), footerText);

				} else {
					if (driver.findElements(TabletLocators.autoRenew(planSelector[3])).size() == 0) {
						ReporterLog.pass("AutoRenew", "Auto renew option is not available for plan:" + planSelector[0]);
					} else {
						ReporterLog.fail("AutoRenew", "Auto renew option is available for plan" + planSelector[0]);
					}
				}

				if (driver.findElements(TabletLocators.SelectPlan).size() > 0) {
					ReporterLog.pass("SelectPlan", "Select plan button is available");
				} else {
					ReporterLog.fail("SelectPlan", "Select plan button is not available");
				}
			} else {
				ReporterLog.fail("Insufficient data", "Data is not supplied");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- bringIntoView Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	@SuppressWarnings("unchecked")
	public static void bringIntoView(String plans) {
		try {
			boolean flag = true;
			String[] days = plans.split("\\s+");
			while (flag) {
				List<MobileElement> elements = ((AndroidDriver<MobileElement>) driver)
						.findElements(TabletLocators.planTitle);
				MobileElement centerElement = determineCenter(elements);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", centerElement.getId());
				if (centerElement.getText().equalsIgnoreCase(plans)) {
					flag = false;
				} else if (Integer.parseInt(days[0]) > Integer.parseInt(centerElement.getText().replace(" Days", ""))) {
					scrollObject.put("direction", "left");
					js.executeScript("mobile: swipe", scrollObject);
					//centerElement.swipe(SwipeElementDirection.LEFT, 500);
				} else {
					//centerElement.swipe(SwipeElementDirection.RIGHT, 500);
					scrollObject.put("direction", "right");
					js.executeScript("mobile: swipe", scrollObject);
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- determineCenter Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static MobileElement determineCenter(List<MobileElement> elements) {
		MobileElement element = null;
		try {
			if (elements.size() == 2) {
				if (Integer.parseInt(elements.get(0).getText().replace(" Days", "")) == 7) {
					element = elements.get(0); // starting point
				} else {
					element = elements.get(1); // ending point
				}
			} else {
				element = elements.get(1);
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}

		return element;
	}

	/***
	 * Function Name :- bringPlanSelector Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void bringPlanSelector(String planSelector, int index) {
		boolean res = false;
		try {
			String title = null;
			for (int i = 0; i < index; i++) {

				title = getText(TabletLocators.planTitle, "Plan title");
				if (title.equalsIgnoreCase(planSelector.trim())) {

					res = true;
					break;
				} else {
					swipeUseElement(TabletLocators.planTitle, true);// true will give left swipe
				}
			}
			if (!res) {
				for (int i = 0; i < index; i++) {
					title = getText(TabletLocators.planTitle, "Plan title");
					if (title.equalsIgnoreCase(planSelector)) {
						res = true;
						break;
					} else {
						swipeUseElement(TabletLocators.planTitle, false);// true will give left swipe
					}
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- swipeUseElement Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void swipeUseElement(By by, boolean left) {
		// boolean res=false;
		try {
			MobileElement element = getMobileElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", element.getId());
			if (left) {
				//element.swipe(SwipeElementDirection.LEFT, 500);
				scrollObject.put("direction", "left");
				js.executeScript("mobile: swipe", scrollObject);
				ReporterLog.pass("SwipeLeft", "Swipe left action performed");
			} else {
				//element.swipe(SwipeElementDirection.RIGHT, 460);
				scrollObject.put("direction", "right");
				js.executeScript("mobile: swipe", scrollObject);
				ReporterLog.pass("SwipeRight", "Swipe right action performed");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- swipeUseElement1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void swipeUseElement1(By by, boolean left) {
		// boolean res=false;
		try {
			MobileElement element = getMobileElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", element.getId());
			if (left) {
				//element.swipe(SwipeElementDirection.LEFT, 500);
				scrollObject.put("direction", "left");
				js.executeScript("mobile: swipe", scrollObject);
				ReporterLog.pass("SwipeLeft", "Swipe left action performed");
			} else {
				//element.swipe(SwipeElementDirection.RIGHT, 460);
				scrollObject.put("direction", "right");
				js.executeScript("mobile: swipe", scrollObject);
				ReporterLog.pass("SwipeRight", "Swipe right action performed");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :-swipeUseElementspot Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void swipeUseElementspot(By by, boolean left) {
		// boolean res=false;
		try {
			MobileElement element = getMobileElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			
			if (left) {
				scrollObject.put("direction", "left");
				scrollObject.put("element", element.getId());
				js.executeScript("mobile: scroll", scrollObject);
				
				//element.swipe(SwipeElementDirection.LEFT, 720);
				ReporterLog.pass("SwipeLeft", "Swipe left action performed");
			} else {
				scrollObject.put("direction", "right");
				scrollObject.put("element", element.getId());
				js.executeScript("mobile: scroll", scrollObject);
				ReporterLog.pass("SwipeRight", "Swipe right action performed");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fillCardDetails Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void fillCardDetails(String cardNumber, String nameOnCard, String expiryDate, String cvv,
			boolean clickMakePayment) {
		// boolean res=false;
		try {
			if (cardNumber.length() > 0)
				type(TabletLocators.cardNumber, cardNumber, "Card Number");

			if (nameOnCard.length() > 0)
				type(TabletLocators.nameOfCard, nameOnCard, "Name on Card");

			if (expiryDate.length() > 0)
				type(TabletLocators.expiryDate, expiryDate, "Expiry date");

			if (cvv.length() > 0)
				type(TabletLocators.cvv, cvv, "CVV");

			if (clickMakePayment) {
				click(TabletLocators.makePayment, "Make Payment");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- watchlistverify Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void watchlistverify() {
		try {
			// DiscoverPageHelper.clickOnTab("Discover");
			// Reporter.reportStep("Add to favorites");
			// String title = DiscoverPageHelper.addFavouriteWatchLater(false,true);
			// driver.navigate().back();
			// click(TabletLocators.meProfile1, "Me");
			Thread.sleep(20000);
			watchlaterContentPlayBack1();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Paymentlapse Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void Paymentlapse() {
		try {
			Thread.sleep(15000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paytmlogo)) {
				ReporterLog.pass("Paytm Logo button", "Paytm Logo button is displayed");
			} else {
				ReporterLog.fail("Paytm Logo button", "Paytm Logo button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaytmDesc)) {
				ReporterLog.pass("Paytm Description", "Paytm Description is displayed");
			} else {
				ReporterLog.fail("Paytm Description", "Paytm Description is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paytmsubscribe)) {
				ReporterLog.pass("Paytm Subscribe option", "Paytm Subscribe option is displayed");
			} else {
				ReporterLog.fail("Paytm Subscribe option", "Paytm Subscribe option is not displayed");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyCarrierBilling Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyCarrierBilling(String carrierBilling, boolean click) {
		// boolean res=false;
		try {
			String[] carrier = carrierBilling.split("\\^");
			if (carrier.length > 1) {
				type(TabletLocators.mobileNumber, carrier[0], "Mobile Number");
				click(TabletLocators.carrierSelector, "Carrier");
				click(TabletLocators.carrierOption(carrier[1]), carrier[1]);
			} else {
				type(TabletLocators.mobileNumber, carrier[0], "Mobile Number");
			}

			if (click) {
				click(TabletLocators.makePayment, "Make Payment");
			}
			((ContextAware) driver).context("NATIVE_APP");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyGlobeAIS Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyGlobeAIS(String carrierBilling, boolean click) {
		// boolean res=false;
		try {
			String[] carrier = carrierBilling.split("\\^");
			type(TabletLocators.globeAISMobileNumber, carrier[0], "Mobile Number");
			if (click) {
				click(TabletLocators.makePayment, "Make Payment");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- MePage Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void MePage(String strColumn) {
		try {
			// click(TabletLocators.meProfile1, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500);
			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");

			Thread.sleep(3000);
			click(TabletLocators.premiumme, "Premium button");
			Thread.sleep(9000);
			click(TabletLocators.premiumbtn, "Explore Rental Movies btn");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}

			click(TabletLocators.rentalsBackbtn, "Rentals Back button is clicked");
			Thread.sleep(2500); // time to allow player to load
			click(TabletLocators.WatchList, "WatchList button is displayed");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}
			click(TabletLocators.WatchLaterBackbtn, "WatchLaterBackbtn Back button is clicked");
			Thread.sleep(1000);
			// click(TabletLocators.FavSection,"Favorite button is displayed");
			// if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)){
			// Reporter.pass("Favorite Title", "FavTitle title is displayed");
			// } else{
			// Reporter.fail("Favorite Title", "Favorite title is not displayed");
			// }
			// click(TabletLocators.FavBackbtn,"FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.settingsSection, "Settings button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SettingsTitle)) {
				ReporterLog.pass("Settings Title", "Settings Title  is displayed");
			} else {
				ReporterLog.fail("Settings Title", " Settings Title is not displayed");
			}
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.setcancel, "cancel");
			Thread.sleep(1000);
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.englishlanguage, "English");
			click(TabletLocators.deleteButton1, "Okay");
			Thread.sleep(5000);
			click(TabletLocators.meProfile1, "Me");
			click(TabletLocators.settings, "settings");
			// click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.Default, "Default");
			click(TabletLocators.deleteButton1, "Okay");
			Thread.sleep(2000);
			click(TabletLocators.meProfile1, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.Downloadquality, "Downloadquality");
			click(TabletLocators.low, "Low");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.Downloadquality, "Downloadquality");
			click(TabletLocators.medium, "Medium");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.Downloadquality, "Downloadquality");
			click(TabletLocators.high, "High");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.settingsdatausage)) {
				ReporterLog.pass("Mobile Data Usage", "Mobile Data Usage is visible");

			} else {
				ReporterLog.fail("Mobile Data Usage", "Mobile Data Usage is not visible");
			}
			click(TabletLocators.settingsdatausage, "mobile data usage");
			click(TabletLocators.playbackpopup, "playback quality");
			click(TabletLocators.low, "Low");
			click(TabletLocators.aboutusback1, "back");
			click(TabletLocators.SetBackbtn1, "Settings Back button is clicked");
			Thread.sleep(1500);
			click(TabletLocators.meProfile1, "Me");
			/*
			 * click(TabletLocators.Subscrip,"Subscription");
			 * if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.Substitle)) {
			 * Reporter.pass("Subscription sub Title",
			 * "Subscription sub Title is displayed"); } else {
			 * Reporter.fail("Subscription sub Title",
			 * " Subscription sub Title is not displayed"); }
			 */
			List<WebElement> eleHeader = driver.findElements(By.id("tv.hooq.android:id/discover_feed_list"));
			System.out.println(eleHeader.size());
			List<WebElement> eleSubscription = eleHeader.get(1)
					.findElements(By.id("tv.hooq.android:id/profileBtnSubscription"));
			System.out.println(eleSubscription.size());
			eleSubscription.get(0).click();
			/*
			 * MobileElement eleCollection=(MobileElement)
			 * driver.findElement(By.id("tv.hooq.android:id/discover_feed_list"));
			 * List<MobileElement> eleList1=eleCollection.findElements(By.className(
			 * "android.widget.RelativeLayout")); if(eleList1.size()>0) {
			 * System.out.println(eleList1.size()); List<MobileElement>
			 * eleMovie=eleList1.get(0).findElements(By.className(
			 * "android.widget.RelativeLayout")); if(eleMovie.size()>0) {
			 * eleMovie.get(5).click(); }
			 */
			driver.navigate().back();
			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}

			click(TabletLocators.setBackbtn, "settings Backbtn Back button is clicked");

			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1400);
			click(TabletLocators.SupportSection, "Support button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SupportSectionheader)) {
				ReporterLog.pass("SupportSection Title", "SupportSection  is displayed");
			} else {
				ReporterLog.fail("SupportSection", " SupportSection is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "settings Backbtn Back button is clicked");
			driver.navigate().back();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyCashCard Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyCashCard(String cashCard, boolean click) {
		// boolean res = false;
		try {

			click(TabletLocators.cashCard, "Cash card");
			click(TabletLocators.cashCardOption(cashCard), cashCard);
			// selectByOptionText(TabletLocators.cashCard, cashCard, "Cash Card");
			if (click) {
				click(TabletLocators.makePayment, "Make Payment");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyNetBanking Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyNetBanking(String netBanking, boolean click) {
		// boolean res=false;
		try {
			click(TabletLocators.netBanking, "Net banking");
			click(TabletLocators.netBankingOption(netBanking), netBanking);
			if (click) {
				click(TabletLocators.makePayment, "Make Payment");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPayTm Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void verifyPayTm(String paytm, boolean click) {
		// boolean res=false;
		try {

			ReporterLog.pass("PayTm", "In payTm block");
			// need to write code for payTM

			Thread.sleep(1000);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- subscriptionPageCheck Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void subscriptionPageCheck(String Description) {
		// Reporter.reportStep(Description);
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedSubScripText)) {
				ReporterLog.pass("SubscriptionPage", "Subscription text is displayed");
			} else {
				ReporterLog.fail("SubscriptionPage", "Subscription text is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedSubScripBtn)) {
				ReporterLog.pass("SubscriptionPage", "Subscribe Now button is displayed");
			} else {
				ReporterLog.fail("SubscriptionPage", "Subscribe Now button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedVoucherCodeBox)) {
				ReporterLog.pass("SubscriptionPage", "Voucher code box is displayed");
			} else {
				ReporterLog.fail("SubscriptionPage", "Voucher code box is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedRedeemBtn)) {
				ReporterLog.pass("SubscriptionPage", "Submit Button is displayed");
			} else {
				ReporterLog.fail("SubscriptionPage", "Submit Button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedbtnUp)) {
				ReporterLog.pass("SubscriptionPage", "back button is displayed");
			} else {
				ReporterLog.fail("SubscriptionPage", "back button is not displayed");
			}

			click(TabletLocators.lapsedSubScripBtn, "Subscribe button");
			Thread.sleep(25000);
			verifyPaymentlapse();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonSubScripCheck Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void AnonSubScripCheck(String description) {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonemptyStateIcon)) {
				ReporterLog.pass("AnonymouseUserSubPage", "Empty state icon is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "Empty state icon is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonstateDescrip)) {
				ReporterLog.pass("AnonymouseUserSubPage", "Anonymouse User content description message is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage",
						"Anonymouse User content description message is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonStartBtn)) {
				ReporterLog.pass("AnonymouseUserSubPage", "<b>Get started</b> button is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "<b>Get started</b> button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonbtnUp)) {
				ReporterLog.pass("AnonymouseUserSubPage", "Back button is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "Back button is not displayed");
			}

			click(TabletLocators.anonbtnUp, "Back button");

			Thread.sleep(500);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonMeProf1)) {
				ReporterLog.pass("ProfilePage", "Navigated to Profile page");
				for (int i = 1; i <= 3; i++) {
					swipeUpOrBottom(true);
				}
				click(TabletLocators.Subscrip, "Subscription button");

			} else {
				ReporterLog.fail("ProfilePage", "Unable to navigate to Profile page");
			}

			click(TabletLocators.anonStartBtn, "Get Started button");
			Thread.sleep(500);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUp)) {
				ReporterLog.pass("Sign Up", "Sign Up page is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Sign Up page is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUpTxt)) {
				ReporterLog.pass("Sign Up", "Sign up with your mobile number content is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Sign up with your mobile number content is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonInputLayout)) {
				ReporterLog.pass("Sign Up", "Mobile number input field is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Mobile number input field is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUpDoneBtn)) {
				ReporterLog.pass("Sign Up", "Done is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Done is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonTos)) {
				ReporterLog.pass("Sign Up", "Terms & Conditions, Privacy policy content is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Terms & Conditions, Privacy policy content is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUpLogin)) {
				ReporterLog.pass("Sign Up", "Already a user? Login is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Already a user? Login is not displayed");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveSubScripCheck Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void ActiveSubScripCheck(String description) {
		// Reporter.reportStep(description);
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeSubScripText)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Subscription text is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Subscription text is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeDaysLeft)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Days left is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Days left is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeSubScripBtn)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Subscribe Now is button displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Subscribe Now is button not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeVoucherCodeBox)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Voucher input field is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Voucher input field not is displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeRedeemBtn)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Submit button is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Submit button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activebtnUp)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Back button is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Back button is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveSubScripCheck1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void ActiveSubScripCheck1(String description) {
		// Reporter.reportStep(description);
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeSubScripText2)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Subscription text is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Subscription text is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeDaysLeft)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Days left is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Days left is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeVoucherCodeBox)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Voucher input field is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Voucher input field not is displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeRedeemBtn)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Submit button is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Submit button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activebtnUp)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Back button is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Back button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonVCR1)) {
				ReporterLog.pass("ActiveUserSubscriptionPage", "Voucher Code Redemption title is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Voucher Code Redemption description is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonVCRDesc1)) {
				ReporterLog.pass("AnonVCRDesc", "AnonVCRDesc is displayed");
			} else {
				ReporterLog.fail("ActiveUserSubscriptionPage", "Voucher Code Redemption description is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cancelsubs)) {
				ReporterLog.pass("Cancel subscription", "Cancel subscription is displayed");
			} else {
				ReporterLog.fail("ActiveUser Cancel subscription", "Cancel subscription is not displayed");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyVideoPlaybackTest Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyVideoPlaybackTest() {

		// Reporter.reportStep("VideoPlayBack_and_Verification");
		try {
			Thread.sleep(9000); // time to allow player to load
			advancedTap();
			String strText = driver.getPageSource();
			System.out.println(strText);
			String beforePlay = enablePlayerControl(false, false, true, false, "");
			System.out.println("Before Play " + beforePlay);
			if (beforePlay == null) {
				ReporterLog.fail("Player", "Player is taking much time to load");
			} else {
				ReporterLog.pass("Playback", "Video streaming started and play time is :" + beforePlay);
				Thread.sleep(20000);
				ReporterLog.pass("Waiting to video to play", "Explict wait of 20 seconds is applied to video to play");
				String afterPlay = enablePlayerControl(false, false, true, false, "");
				System.out.println("After Play " + afterPlay);
				if (afterPlay == null) {
					ReporterLog.fail("Player", "Player is taking much time to load");
				}
				// validation code
				if (beforePlay.equals(afterPlay)) {
					ReporterLog.fail("Playback", "Video is not playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				} else {
					ReporterLog.pass("Playback", "Video is playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				}
				enablePlayerControl(false, true, false, false, "");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyVideoPlayback Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyVideoPlayback() {
		// Reporter.reportStep("VideoPlayBack_and_Verification");
		try {
			Thread.sleep(9000); // time to allow player to load
			String beforePlay = enablePlayerControl(false, false, true, false, "");
			System.out.println("Before Play " + beforePlay);
			if (beforePlay == null) {
				ReporterLog.fail("Player", "Player is taking much time to load");
			} else {
				ReporterLog.pass("Playback", "Video streaming started and play time is :" + beforePlay);
				Thread.sleep(40000);
				ReporterLog.pass("Waiting to video to play", "Explict wait of 40 seconds is applied to video to play");
				String afterPlay = enablePlayerControl(false, false, true, false, "");
				System.out.println("After Play " + afterPlay);
				if (afterPlay == null) {
					ReporterLog.fail("Player", "Player is taking much time to load");
				}
				// validation code
				if (beforePlay.equals(afterPlay)) {
					ReporterLog.fail("Playback", "Video is not playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				} else {
					ReporterLog.pass("Playback", "Video is playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				}
				enablePlayerControl(false, true, false, false, "");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyVideoPlaybacktrailer Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void verifyVideoPlaybacktrailer() {
		// Reporter.reportStep("VideoPlayBack_and_Verification");
		try {
			Thread.sleep(9000); // time to allow player to load
			String beforePlay = enablePlayerControl(false, false, true, false, "");
			System.out.println("Before Play " + beforePlay);
			if (beforePlay == null) {
				ReporterLog.fail("Player", "Player is taking much time to load");
			} else {
				ReporterLog.pass("Playback", "Video streaming started and play time is :" + beforePlay);
				Thread.sleep(10000);
				ReporterLog.pass("Waiting to video to play", "Explict wait of 40 seconds is applied to video to play");
				String afterPlay = enablePlayerControl(false, false, true, false, "");
				System.out.println("After Play " + afterPlay);
				if (afterPlay == null) {
					ReporterLog.fail("Player", "Player is taking much time to load");
				}
				// validation code
				if (beforePlay.equals(afterPlay)) {
					ReporterLog.fail("Playback", "Video is not playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				} else {
					ReporterLog.pass("Playback", "Video is playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				}
				enablePlayerControl(false, true, false, false, "");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyVideoPlayback1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyVideoPlayback1() {

		// Reporter.reportStep("VideoPlayBack_and_Verification");
		try {
			Thread.sleep(9000); // time to allow player to load
			String beforePlay = enablePlayerControl1(true, false, true, false, "");
			System.out.println("Before Play " + beforePlay);

			if (beforePlay == null) {
				ReporterLog.fail("Player", "Player is taking much time to load");
			} else {
				ReporterLog.pass("Playback", "Video streaming started and play time is :" + beforePlay);
				Thread.sleep(40000);
				ReporterLog.pass("Waiting to video to play", "Explict wait of 40 seconds is applied to video to play");
				String afterPlay = enablePlayerControl1(false, false, true, false, "");
				System.out.println("After Play " + afterPlay);
				if (afterPlay == null) {
					ReporterLog.fail("Player", "Player is taking much time to load");
				}
				// validation code
				if (beforePlay.equals(afterPlay)) {
					ReporterLog.fail("Playback", "Video is not playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				} else {
					ReporterLog.pass("Playback", "Video is playing beforeplaytime is:" + beforePlay
							+ " and after allowing to buffer playtime is" + afterPlay);
				}
				enablePlayerControl(false, true, false, false, "");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPlayback Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyPlayback() {

		try {
			Thread.sleep(8000); // time to allow player to load
			String beforePlay = enablePlayerControlCont(false, true, false, false, "");
			System.out.println(beforePlay);
			boolean blnPlayer = true;
			while (blnPlayer) {
				String PlayTimer = enablePlayerControlCont(false, true, false, false, "");
				System.out.println(PlayTimer);
				if (PlayTimer.contains("06:51") || PlayTimer.contains("06:52") || PlayTimer.contains("06:53")
						|| PlayTimer.contains("06:54") || PlayTimer.contains("06:55") || PlayTimer.contains("06:56")
						|| PlayTimer.contains("06:57") || PlayTimer.contains("06:58") || PlayTimer.contains("06:59")
						|| PlayTimer.contains("07:00")) {
					blnPlayer = false;

					driver.findElement(TabletLocators.activeClose).click();
					driver.navigate().back();
					driver.navigate().back();
					driver.navigate().back();
				}
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- enablePlayerControl Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String enablePlayerControl(Boolean clickOnSubtitle, Boolean clickOnClose, Boolean returnPlayerTime,
			Boolean clickOnSlider, String sliderPercentage) {

		boolean flag = true;
		int count = 0;
		String playerTime = null;
		while (flag && count < 10) {
			count++;

			try {
				Thread.sleep(100);
				advancedTap();

				if (returnPlayerTime) {
					playerTime = driver.findElement(TabletLocators.activePlayTime).getText();

				}
				if (clickOnSubtitle) {
					driver.findElement(TabletLocators.playersub).click();
				}

				if (clickOnClose) {
					driver.findElement(TabletLocators.activeClose).click();
				}
				break;
			} catch (Exception e) {
				flag = true;
			}
		}
		if (flag && count < 10) {
			if (returnPlayerTime) {
				ReporterLog.pass("PlayerTime", "Current Player time is " + playerTime);
			} else if (clickOnSubtitle) {
				ReporterLog.pass("Player", "Clicked on subtitle button");
			} else if (clickOnClose) {
				ReporterLog.pass("Player", "Clicked on close button");
			}
		} else {
			ReporterLog.fail("PlayerTime", "Player is taking much time to respond");
		}
		// String beforePlay1 = enablePlayerControl(false, false, true, false, "");
		// click(TabletLocators.playersub, "Subtitle option");
		// Thread.sleep(5000);
		// click(TabletLocators.subtitleeng, "Subtitle english selected");
		// click(TabletLocators.playerclose, "Subtitle close option");
		return playerTime;
	}

	/***
	 * Function Name :- enablePlayerControl1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String  enablePlayerControl1(Boolean clickOnSubtitle, Boolean clickOnClose, Boolean returnPlayerTime,
			Boolean clickOnSlider, String sliderPercentage) {

		boolean flag = true;
		int count = 0;
		String playerTime = null;
		while (flag && count < 10) {
			count++;

			try {
				Thread.sleep(100);
				advancedTap();

				if (returnPlayerTime) {
					playerTime = driver.findElement(TabletLocators.activePlayTime).getText();

				}
				advancedTap();
				if (clickOnSubtitle) {
					advancedTap();
					driver.findElement(TabletLocators.playersub).click();
					click(TabletLocators.subtitleeng, "Subtitle english selected");
					click(TabletLocators.playerclose, "Subtitle close option");
					driver.navigate().back();
				}

				if (clickOnClose) {
					advancedTap();
					driver.findElement(TabletLocators.activeClose).click();
				}
				break;
			} catch (Exception e) {
				flag = true;
			}
		}
		if (flag && count < 10) {
			if (returnPlayerTime) {
				ReporterLog.pass("PlayerTime", "Current Player time is " + playerTime);
			} else if (clickOnSubtitle) {
				ReporterLog.pass("Player", "Clicked on subtitle button");
			} else if (clickOnClose) {
				ReporterLog.pass("Player", "Clicked on close button");
			}
		} else {
			ReporterLog.fail("PlayerTime", "Player is taking much time to respond");
		}
		// String beforePlay1 = enablePlayerControl(false, false, true, false, "");
		// click(TabletLocators.playersub, "Subtitle option");
		// Thread.sleep(5000);
		// click(TabletLocators.subtitleeng, "Subtitle english selected");
		// click(TabletLocators.playerclose, "Subtitle close option");
		return playerTime;
	}

	/***
	 * Function Name :- enablePlayerControlCont Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String enablePlayerControlCont(Boolean clickOnPause, Boolean returnPlayerTime, Boolean clickOnClose,
			Boolean clickOnSlider, String sliderPercentage) {

		boolean flag = true;
		int count = 0;
		String playerTime = null;
		while (flag && count < 10) {
			count++;

			try {
				Thread.sleep(1000);
				advancedTap();
				if (returnPlayerTime) {
					playerTime = driver.findElement(TabletLocators.activePlayTime).getText();
					// System.out.println(playerTime);

				}
				if (clickOnClose) {
					driver.findElement(TabletLocators.activeClose).click();
				}
				break;
			} catch (Exception e) {
				flag = true;
			}
		}
		if (flag && count < 10) {
			if (returnPlayerTime) {
				ReporterLog.pass("PlayerTime", "Current Player time is " + playerTime);
			} else if (clickOnPause) {
				ReporterLog.pass("Player", "Clicked on Pause/Play button");
			} else if (clickOnClose) {
				ReporterLog.pass("Player", "Clicked on close button");
			}
		} else {
			ReporterLog.fail("PlayerTime", "Player is taking much time to respond");
		}

		return playerTime;
	}

	/***
	 * Function Name :- advancedTap Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void advancedTap() {
		try {
			Dimension size = driver.manage().window().getSize();
			int endy = (int) (size.height * 0.20);
			int startx = size.width / 2;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, Object> tapObject = new HashMap<String, Object>();
			tapObject.put("x", startx); // in pixels from left
			tapObject.put("y", endy); // in pixels from top
			tapObject.put("duration", 0.0);
			tapObject.put("touchCount", 1.0);
			tapObject.put("tapCount", 3.0); // double tap
			js.executeScript("mobile: tap", tapObject);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DownloadscontentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void DownloadscontentPlayBack(String description) {
		try {
			// Reporter.reportStep(description);
			Thread.sleep(1500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsBackbtn)) {
				ReporterLog.pass("DownloadsBackbtn ", "Downloads back button  is displayed");
			} else {
				ReporterLog.fail("Downloads back button", "Downloads back button is not displayed");
			}
			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Downloads EDIT button", "Downloads edit button  is displayed");
				} else {
					ReporterLog.fail("Downloads EDIT button", "Downloads edit button is not displayed");
				}
				click(TabletLocators.firstShowInDownloadSection, "First show/movie in download section");
				verifyVideoPlayback();
			} else {
				ReporterLog.pass("Downloads", "No videos to play in download section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- favoritesContentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void favoritesContentPlayBack(String description) {
		try {// Reporter.reportStep(description);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavBackbtn)) {
				ReporterLog.pass("Favorite ", "Favorite back button  is displayed");
			} else {
				ReporterLog.fail("Favorite back button", "Favorite back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Favorite EDIT button", "Favorite edit button  is displayed");
				} else {
					ReporterLog.fail("Favorite EDIT button", "Favorite edit button is not displayed");
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Favimg)) {
					ReporterLog.pass("Favorite image button", "Favorite image button  is displayed");
					click(TabletLocators.Favimg, " Favorite image button");
				} else {
					ReporterLog.fail("Favorite image button", "Favorite image button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "First collection in  Favorite");
				verifyVideoPlayback();
			} else {
				ReporterLog.pass("Favorites", "No videos to play in favorite section");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- favoritesContentPlayBack1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void favoritesContentPlayBack1() {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavBackbtn)) {
				ReporterLog.pass("Favorite ", "Favorite back button  is displayed");
			} else {
				ReporterLog.fail("Favorite back button", "Favorite back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Favorite EDIT button", "Favorite edit button  is displayed");
				} else {
					ReporterLog.fail("Favorite EDIT button", "Favorite edit button is not displayed");
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Favimg)) {
					ReporterLog.pass("Favorite image button", "Favorite image button  is displayed");
					click(TabletLocators.Favimg, " Favorite image button");
				} else {
					ReporterLog.fail("Favorite image button", "Favorite image button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "First collection in  Favorite");
				verifyVideoPlayback();
			} else {
				ReporterLog.pass("Favorites", "No videos to play in favorite section");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- favContentPlayBackLapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void favContentPlayBackLapse(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavBackbtn)) {
				ReporterLog.pass("Favorite ", "Favorite back button  is displayed");
			} else {
				ReporterLog.fail("Favorite back button", "Favorite back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Favorite EDIT button", "Favorite edit button  is displayed");
				} else {
					ReporterLog.fail("Favorite EDIT button", "Favorite edit button is not displayed");
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Favimg)) {
					ReporterLog.pass("Favorite image button", "Favorite image button  is displayed");
					click(TabletLocators.Favimg, " Favorite image button");
				} else {
					ReporterLog.fail("Favorite image button", "Favorite image button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "First collection in  Favorite");
				Thread.sleep(20000);
				verifyPaymentlapse();
			} else {
				ReporterLog.pass("Favorites", "No videos to play in favorite section");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- wthlaterContentPlayBackLapse Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void wthlaterContentPlayBackLapse(String description) {
		try {
			// Reporter.reportStep(description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WLSection)) {
				ReporterLog.pass("Watch later Title", "Watch later title  is displayed");
			} else {
				ReporterLog.fail("Watch later", "Watch later is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterBackbtn)) {
				ReporterLog.pass("Watch Later ", "Watch Later back button  is displayed");
			} else {
				ReporterLog.fail("Watch Later back button", "Watch Later back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Watch Later EDIT button", "Watch Later edit button  is displayed");
				} else {
					ReporterLog.fail("Watch Later EDIT button", "Watch Later edit button is not displayed");
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Favimg)) {
					ReporterLog.pass("Watch Later image button", "Watch Later image button  is displayed");
					click(TabletLocators.Favimg, " Favorite image button");
				} else {
					ReporterLog.fail("Watch Later image button", "Watch Later image button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "First collection in  Favorite");
				Thread.sleep(20000);
				verifyPaymentlapse();
			} else {
				ReporterLog.pass("Watch Later", "No videos to play in Watch Later section");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- watchlaterContentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void watchlaterContentPlayBack(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.watchlist, "Watch list button");

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Watch list EDIT button", "Watch list edit button  is displayed");
				} else {
					ReporterLog.fail("Watch list EDIT button", "Watch list edit button is not displayed");
				}
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Favimg)) {
					ReporterLog.pass("Watch list image button", "Watch list image button  is displayed");
					click(TabletLocators.Favimg, " Watch later button");
				} else {
					ReporterLog.fail("Watch list image button", "Watch list image button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "First collection in Watch list");
				verifyVideoPlayback();
			} else {
				ReporterLog.pass("Watch list", "No videos to play in Watch list section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- watchlaterContentPlayBack1 Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */

	public static void watchlaterContentPlayBack1() {
		try {
			click(TabletLocators.watchlist2, "Watch list button");

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Watch list EDIT button", "Watch list edit button  is displayed");
				} else {
					ReporterLog.fail("Watch list EDIT button", "Watch list edit button is not displayed");
				}
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Favimg)) {
					ReporterLog.pass("Watch list image button", "Watch list image button  is displayed");
					click(TabletLocators.Favimg, " Watch list button");
				} else {
					ReporterLog.fail("Watch later image button", "Watch later image button is not displayed");
				}
				click(TabletLocators.playButton1, "First collection in Watch later");
				Thread.sleep(20000);
				fnVerifyBrightCovePlayer();
				// MePageHelper.verifyVideoPlayback();
			} else {
				ReporterLog.pass("Watch later", "No videos to play in Watch later section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historyContentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void historyContentPlayBack(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}
				List<WebElement> element = driver.findElements(TabletLocators.firstShowFromCollection);
				if (element.size() > 0) {
					element.get(0).click();
					ReporterLog.pass("History", "Clicked on first collection");
					click(TabletLocators.playButtonv, "Play button");
				}
				Thread.sleep(3000);
				verifyVideoPlayback();

			} else {
				ReporterLog.pass("History", "No videos to play in history section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historysection Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void historysection(String description) {
		try {
			// Reporter.reportStep(description);
			Thread.sleep(2000);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}
				// List<WebElement> element =
				// driver.findElements(TabletLocators.firstShowFromCollection);
				// if(eleEpisode.size()>0){
				// element.get(0).click();
				// Reporter.pass("History", "Clicked on first collection");
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				System.out.println(eleEpisode.size());
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "Play button");
			}
			Thread.sleep(30000);
			fnVerifyBrightCovePlayer();
			driver.navigate().back();
			// } else {
			// Reporter.pass("History", "No videos to play in history section");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historysectiondownload Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void historysectiondownload(String description) {
		try {
			// Reporter.reportStep(description);
			Thread.sleep(2000);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}
				List<WebElement> element = driver.findElements(TabletLocators.firstShowFromCollection);
				if (element.size() > 0) {
					element.get(0).click();
					ReporterLog.pass("History", "Clicked on first collection");
					click(TabletLocators.Downloadvideo, "Download option");
					Thread.sleep(10000);
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
						ReporterLog.pass("Download quality option", "Download quality option is visible");

					} else {
						ReporterLog.fail("Download quality option", "Download quality option is not visible");
					}
					click(TabletLocators.downloadlow, "Download low option");
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadpreference)) {
						ReporterLog.pass("Download preference option", "Download preference option is visible");

					} else {
						ReporterLog.fail("Download preference option", "Download preference option is not visible");
					}
				}
			} else {
				ReporterLog.pass("History", "No videos to play in history section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historysectionlapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void historysectionlapse(String description) {
		try {
			// Reporter.reportStep(description);
			Thread.sleep(2000);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				System.out.println(eleEpisode.size());
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "Play button");
			}
			Thread.sleep(3000);
			verifyPaymentlapse();

			// } else {
			// Reporter.pass("History", "No videos to play in history section");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historyContentPlayBackTab Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void historyContentPlayBackTab(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}
				List<WebElement> element = driver.findElements(TabletLocators.firstShowFromCollection);
				if (element.size() > 0) {
					element.get(0).click();
					ReporterLog.pass("History", "Clicked on first collection");
					click(TabletLocators.playButtonTab, "Play button");
				}
				Thread.sleep(3000);
				verifyVideoPlayback();

			} else {
				ReporterLog.pass("History", "No videos to play in history section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historyContentPlayBackTVOD Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void historyContentPlayBackTVOD(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}

				List<WebElement> element = driver.findElements(TabletLocators.firstShowFromCollection);
				if (element.size() > 0) {
					element.get(0).click();
					ReporterLog.pass("History", "Clicked on first collection");
					click(TabletLocators.playButton, "Play button");
				}
				verifyVideoPlayback();
				driver.navigate().back();
				click(TabletLocators.cancelBtn, "Edit button");
				click(TabletLocators.historyimage, "Clicked on History content button");
				click(TabletLocators.historyremove, "Clicked on History remove button");
				click(TabletLocators.historydelete, "Clicked on History delete button");

			} else {
				ReporterLog.pass("History", "No videos to play in history section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- historyContentPlayBackTVODtab Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void historyContentPlayBackTVODtab(String description) {
		try {
			// Reporter.reportStep(description);
			Rentonboardingtab(description);
			driver.navigate().back();
			driver.navigate().back();
			click(TabletLocators.meProfile, "Me");
			// navigateToMeProfile();
			Thread.sleep(3000);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryBackbtn)) {
				ReporterLog.pass("HistoryBackbtn ", "History back button  is displayed");
			} else {
				ReporterLog.fail("History back button", "History back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("History EDIT button", "History edit button  is displayed");
				} else {
					ReporterLog.fail("History EDIT button", "History edit button is not displayed");
				}

				List<WebElement> element = driver.findElements(TabletLocators.firstShowFromCollection);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentflag)) {
					ReporterLog.pass("Rent flag ", "Rent flag is displayed");
				} else {
					ReporterLog.fail("Rent flag", "Rent flag is not displayed");
				}
				if (element.size() > 0) {
					element.get(0).click();
					ReporterLog.pass("History", "Clicked on first collection");
					click(TabletLocators.playButtonTab, "Play button");
				}
				verifyVideoPlayback();
				driver.navigate().back();
				click(TabletLocators.cancelBtn, "Edit button");
				click(TabletLocators.historyimage, "Clicked on History content button");
				click(TabletLocators.historyremove, "Clicked on History remove button");
				click(TabletLocators.historydelete, "Clicked on History delete button");

			} else {
				ReporterLog.pass("History", "No videos to play in history section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- watchLatercontentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void watchLatercontentPlayBack(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.WatchLater, "WatchLater button is displayed");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterBackbtn)) {
				ReporterLog.pass("Watch Later back button", "Watch Later back button  is displayed");
			} else {
				ReporterLog.fail("Watch Later back button", "Watch Later back button is not displayed");
			}

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Watch Later EDIT button", "Watch Later edit button  is displayed");
				} else {
					ReporterLog.fail("Watch Later EDIT button", "Watch Later edit button is not displayed");
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterimg)) {
					ReporterLog.pass("Watch Later image button", "Watch Later image button  is displayed");
					click(TabletLocators.WatchLaterimg, " Watch Later image button");
				} else {
					ReporterLog.fail("Watch Later image button", "Watch Later image button is not displayed");
				}
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playButtonv)) {
					ReporterLog.pass("WatchLater",
							"WatchLater content content play button is displayed and is clicked");
					click(TabletLocators.playButtonv, "First collection in  WatchLater");
					Thread.sleep(50000);
				} else {
					ReporterLog.fail("DiscPlayBtn",
							"WatchLater content play button is not displayed and is not clicked");
				}
				verifyVideoPlayback();
			} else {
				ReporterLog.pass("WatchLater", "No videos are available in watch later section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- findSeasonEpisodeContentPlayBack Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void findSeasonEpisodeContentPlayBack(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.WatchLater, "WatchLater button is displayed");

			isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle);
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterBackbtn);
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection);

			isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterimg);
			if (findEpisode()) {

				click(TabletLocators.findEpisodeBtn, "Find Episode");
				List<WebElement> playButtons = driver.findElements(TabletLocators.episodeList);
				if (playButtons.size() > 0) {
					playButtons.get(3).click();
					ReporterLog.pass("SecondEpisode", "Clicked play button on second episode");
				} else {
					ReporterLog.fail("EpisodeList", "Episode list is empty");
				}
				verifyVideoPlayback();
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- findEpisode Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static boolean findEpisode() {
		boolean blnStatus = true;
		try {
			List<WebElement> shows = driver.findElements(TabletLocators.findShows);
			if (shows.size() > 0) {
				shows.get(0).click();
				ReporterLog.pass("TVShow", "Clicked on a show");
			} else {
				ReporterLog.fail("TVShow", "Shows are not available in this section");
			}
			blnStatus = true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			blnStatus = false;
		}
		return blnStatus;
	}

	/***
	 * Function Name :- VerifyShows Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void VerifyShows(String Description) {
		try {
			String str = "TOP 50 TV SHOWS";

			for (int i = 0; i <= 30; i++) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				if (driver.findElements(By.name(str)).size() != 0) {
					System.out.println("TOP 50 TV SHOWS has been found and now clicking on It.");
					driver.findElement(By.name(str)).click();
					// String str1 = "Ramany Vs Ramany";
					while (driver.findElements(By.name("TVSHOW")).size() != 0) {
						for (int k = 0; k <= 50; k++) {
							swipeUpOrBottom(true);
							List<WebElement> allTitlesOnfirstScreen = driver
									.findElements(By.className("android.widget.RelativeLayout"));
							System.out.println("Titles are = " + allTitlesOnfirstScreen.get(i).getAttribute("index"));
							String Values = allTitlesOnfirstScreen.get(i).getAttribute("text");
							System.out.println(Values);
							swipeUpOrBottom(true);
						}
					}
					break;
				} else {
					swipeUpOrBottom(true);
				}
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousMeValidaton Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousMeValidaton() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500); // time to allow player to load
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Downloads", "Browse button is displayed");
			} else {
				ReporterLog.fail("Downloads", "Browse button is not displayed");
			}
			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");
			Thread.sleep(1500);
			click(TabletLocators.WatchLater, "WatchLater button is displayed");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("WatchLater", "WatchLater Browse button is displayed");
			} else {
				ReporterLog.fail("WatchLater", "WatchLater Browse button is not displayed");
			}
			click(TabletLocators.WatchLaterBackbtn, "WatchLaterBackbtn Back button is clicked");
			Thread.sleep(1000);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Favorites", "Favorites Browse button is displayed");
			} else {
				ReporterLog.fail("Favorites", "Favorites Browse button is not displayed");
			}

			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("History", "History Browse button is displayed");
			} else {
				ReporterLog.fail("History", "History Browse button is not displayed");
			}
			driver.navigate().back(); // close keyboard
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymousaudiolan Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymousaudiolan() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.Audiolan, "Audio language");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AudiolanTitle)) {
				ReporterLog.pass("Audio language Title", "Audio language Title is displayed");
			} else {
				ReporterLog.fail("Audio language Title", " Audio language Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AudiolanTitlecancel)) {
				ReporterLog.pass("Audio language Title Cancel", "Audio language Title Cancel is displayed");
			} else {
				ReporterLog.fail("Audio language Title Cancel", " Audio language Title Cancel is not displayed");
			}
			click(TabletLocators.AudiolanTitlecancel, "Audio language cancel");

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymousapplanguage Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymousapplanguage() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.Appdisplay, "App Display language");
			click(TabletLocators.Appdisplaybahasa, "Display language Bahasa");
			Thread.sleep(2000);

			click(TabletLocators.Appokay, "App restart okay");
			Thread.sleep(5000);
			click(TabletLocators.meProfile, "Me");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Bahasalang)) {
				ReporterLog.pass("App language Title", "App language Title is displayed");
			} else {
				ReporterLog.fail("App language Title", " App language Title is not displayed");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymousapplanguagethai Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymousapplanguagethai() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.Appdisplay, "App Display language");
			click(TabletLocators.Appdisplaythai, "Display language Bahasa");
			Thread.sleep(2000);
			click(TabletLocators.Appokay, "App restart okay");
			Thread.sleep(5000);
			click(TabletLocators.meProfile, "Me");
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousdownQuality Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousdownQuality() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.Downloadqual, "Display Download quality");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadqual1)) {
				ReporterLog.pass("Download Quality Title", "Download Quality Title is displayed");
			} else {
				ReporterLog.fail("Download Quality Title", " Download Quality Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadlow1)) {
				ReporterLog.pass("Download Quality Title low", "Download Quality Title low is displayed");
			} else {
				ReporterLog.fail("Download Quality Title low", " Download Quality Title low is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadmed)) {
				ReporterLog.pass("Download Quality Title med", "Download Quality Title med is displayed");
			} else {
				ReporterLog.fail("App language Title bahasa", " App language Title bahasa is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadhigh)) {
				ReporterLog.pass("Download Quality Title high", "Download Quality Title high is displayed");
			} else {
				ReporterLog.fail("Download Quality Title high", " Download Quality Title high is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousplayQuality Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousplayQuality() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.playbackquality, "playback quality");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playbackqual)) {
				ReporterLog.pass("Download Quality Title", "Download Quality Title is displayed");
			} else {
				ReporterLog.fail("Download Quality Title", " Download Quality Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playbackauto)) {
				ReporterLog.pass("Download Quality Title Auto", "Download Quality Title Auto is displayed");
			} else {
				ReporterLog.fail("Download Quality Title Auto", " Download Quality Title Auto is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadlow1)) {
				ReporterLog.pass("Download Quality Title low", "Download Quality Title low is displayed");
			} else {
				ReporterLog.fail("Download Quality Title low", " Download Quality Title low is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadmed)) {
				ReporterLog.pass("Download Quality Title med", "Download Quality Title med is displayed");
			} else {
				ReporterLog.fail("App language Title bahasa", " App language Title bahasa is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.downloadhigh)) {
				ReporterLog.pass("Download Quality Title high", "Download Quality Title high is displayed");
			} else {
				ReporterLog.fail("Download Quality Title high", " Download Quality Title high is not displayed");
			}

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymoussubtitle Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymoussubtitle() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.subtitle, "subtitle language");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subtitlelan)) {
				ReporterLog.pass("Sub Title Language", "Sub Title Language is displayed");
			} else {
				ReporterLog.fail("Sub Title Language", " Sub Title Language is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subtitlelan1)) {
				ReporterLog.pass("Sub Title Language", "Sub Title Language is displayed");
			} else {
				ReporterLog.fail("Sub Title Language", " Sub Title Language is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subtitlecancel)) {
				ReporterLog.pass("Sub Title Cancel", "Sub Title Cancel is displayed");
			} else {
				ReporterLog.fail("Sub Title Cancel", " Sub Title Cancel is not displayed");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymoussupport Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymoussupport() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(9000);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			SupportValidation();
			Thread.sleep(2000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymousappmobdata Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void Anonymousappmobdata() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.mobdatausage, "Mobile data usage");
			Thread.sleep(2000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.mobdatausage)) {
				ReporterLog.pass("Download Quality Title", "Download Quality Title is displayed");
			} else {
				ReporterLog.fail("Download Quality Title", " Download Quality Title is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymoustransactionHis Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymoustransactionHis() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anontransaction, "Transaction page");
			Thread.sleep(2000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.transactionhistoryheader)) {
				ReporterLog.pass("Transaction history header", "Download Quality Title is displayed");
			} else {
				ReporterLog.fail("Transaction history header", " Transaction history header is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.transactionhistoryimage)) {
				ReporterLog.pass("Transaction history Empty image", "Transaction history Empty image is displayed");
			} else {
				ReporterLog.fail("Transaction history Empty image",
						" Transaction history Empty image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.transactionhistorydesc)) {
				ReporterLog.pass("Transaction history Empty desc", "Transaction history Empty desc is displayed");
			} else {
				ReporterLog.fail("Transaction history Empty desc", " Transaction history Empty desc is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymousapplanguageeng Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymousapplanguageeng() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(3500);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Anonsettings, "Settings page");
			click(TabletLocators.Appdisplay, "App Display language");
			click(TabletLocators.Appdisplayeng, "Display language English");
			Thread.sleep(2000);
			click(TabletLocators.Appokay, "App restart okay");
			Thread.sleep(5000);
			click(TabletLocators.meProfile, "Me");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.englang)) {
				ReporterLog.pass("App language Title", "App language Title is displayed");
			} else {
				ReporterLog.fail("App language Title", " App language Title is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- TVODMeValidaton Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void TVODMeValidaton() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500);
			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");
			Thread.sleep(3000);
			click(TabletLocators.Rentalsme, "Rentals button");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/genreList"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetType"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetTitle"))
								.getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/duration")).getText();
						String strDes2 = eleList.get(0).findElement(By.id("tv.hooq.android:id/summary")).getText();

						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strDes2);
						eleList.get(0).findElement(By.id("tv.hooq.android:id/btnPlay")).click();
						Thread.sleep(1500);
						verifyVideoPlayback();
					}
				}
			}

			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");
			Thread.sleep(2500); // time to allow player to load
			click(TabletLocators.WatchLater, "WatchLater button is displayed");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}
			click(TabletLocators.WatchLaterBackbtn, "WatchLaterBackbtn Back button is clicked");
			Thread.sleep(1000);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.settingsSection, "Settings button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SettingsTitle)) {
				ReporterLog.pass("Settings Title", "Settings Title  is displayed");
			} else {
				ReporterLog.fail("Settings Title", " Settings Title is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "Settings Back button is clicked");

			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}

			click(TabletLocators.Subscrip, "Subscription");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ManageS)) {
				click(TabletLocators.ManageS, "Manage Subscription");
			} else if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SubscribeS)) {
				click(TabletLocators.SubscribeS, "Manage Subscription");
			}
			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(1400);
			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/listView"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					// System.out.println(eleList.size());
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/header1"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/header2")).getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/desc1")).getText();
						String strDes2 = eleList.get(0).findElement(By.id("tv.hooq.android:id/desc2")).getText();
						String strAmount = eleList.get(0).findElement(By.id("tv.hooq.android:id/amount")).getText();

						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strDes2);
						System.out.println(strAmount);
					}
					String strTrLabel = eleList.get(0).findElements(By.id("tv.hooq.android:id/transaction_date_label"))
							.get(0).getText();
					String strTrNO = eleList.get(0).findElements(By.id("tv.hooq.android:id/transaction_id_label"))
							.get(0).getText();
					String strTrDate = eleList.get(0).findElements(By.id("tv.hooq.android:id/transaction_date")).get(0)
							.getText();
					String strTrID = eleList.get(0).findElements(By.id("tv.hooq.android:id/transaction_id")).get(0)
							.getText();
					System.out.println(strTrLabel);
					System.out.println(strTrNO);
					System.out.println(strTrDate);
					System.out.println(strTrID);
				}
			}

			click(TabletLocators.setBackbtn, "settings Backbtn Back button is clicked");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1400);
			click(TabletLocators.SupportSection, "Support button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SupportSection)) {
				ReporterLog.pass("SupportSection Title", "SupportSection  is displayed");
			} else {
				ReporterLog.fail("SupportSection", " SupportSection is not displayed");
			}
			click(TabletLocators.setBackbtn, "settings Backbtn Back button is clicked");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.logOut, "ME icon");
			click(TabletLocators.conformlogout, "ME");
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveTransHistory Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveTransHistory() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);

			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/listView"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					// System.out.println(eleList.size());
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/header1"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/header2")).getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/desc1")).getText();
						String strAmount = eleList.get(0).findElement(By.id("tv.hooq.android:id/amount")).getText();

						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strAmount);
					}
				}
				driver.navigate().back();
				for (int i = 1; i <= 2; i++) {
					swipeUpOrBottom(true);
				}
				click(TabletLocators.logOut, "Me Logout");
				click(TabletLocators.conformlogout1, "Confirm Logout");

			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveTransHistory_sanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveTransHistory_sanity() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);

			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/listView"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					// System.out.println(eleList.size());
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/header1"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/header2")).getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/desc1")).getText();
						String strAmount = eleList.get(0).findElement(By.id("tv.hooq.android:id/amount")).getText();

						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strAmount);
					}
				}
				driver.navigate().back();
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveuserRentalslapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveuserRentalslapse() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Rentalsme, "Rentals button");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/genreList"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetType"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetTitle"))
								.getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/duration")).getText();

						String strDes2 = eleList.get(0).findElement(By.id("tv.hooq.android:id/summary")).getText();
						// String
						// strTvod=eleList.get(0).findElement(By.id("tv.hooq.android:id/btnTvod")).getText();
						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strDes2);
						// System.out.println(strTvod);
						// eleList.get(0).findElement(By.id("tv.hooq.android:id/btnPlay")).click();
						// Thread.sleep(1500);
						// MePageHelper.verifyVideoPlayback();
					}
				}
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveuserRentals Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveuserRentals() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.Rentalsme, "Rentals button");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/genreList"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetType"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetTitle"))
								.getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/duration")).getText();

						String strDes2 = eleList.get(0).findElement(By.id("tv.hooq.android:id/summary")).getText();
						// String
						// strTvod=eleList.get(0).findElement(By.id("tv.hooq.android:id/btnTvod")).getText();
						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strDes2);
						// System.out.println(strTvod);
						eleList.get(0).findElement(By.id("tv.hooq.android:id/btnPlay")).click();
						Thread.sleep(1500);
						verifyVideoPlayback();
					}
				}
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveuserRentals_Sanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void ActiveuserRentals_Sanity() {
		try {
			click(TabletLocators.meProfile1, "Me");
			Thread.sleep(2500);
			click(TabletLocators.premiumme, "Premium button");
			// click(TabletLocators.exploremovie,"Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/genreList"))) {
				MobileElement eleCollection = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
				List<MobileElement> eleList = eleCollection.findElements(By.className("android.widget.LinearLayout"));
				if (eleList.size() > 0) {
					List<MobileElement> eleMovieDetails = eleList.get(0)
							.findElements(By.className("android.widget.RelativeLayout"));
					if (eleMovieDetails.size() > 0) {

						String strMoveRental = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetType"))
								.getText();
						System.out.println(strMoveRental);
						String strMovieName = eleList.get(0).findElement(By.id("tv.hooq.android:id/assetTitle"))
								.getText();
						String strDes1 = eleList.get(0).findElement(By.id("tv.hooq.android:id/duration")).getText();

						String strDes2 = eleList.get(0).findElement(By.id("tv.hooq.android:id/summary")).getText();
						// String
						// strTvod=eleList.get(0).findElement(By.id("tv.hooq.android:id/btnTvod")).getText();
						System.out.println(strMovieName);
						System.out.println(strDes1);
						System.out.println(strDes2);
						// System.out.println(strTvod);
						eleList.get(0).findElement(By.id("tv.hooq.android:id/btnPlay")).click();
						Thread.sleep(20000);
						fnVerifyBrightCovePlayer();
						driver.navigate().back();
						// MePageHelper.verifyVideoPlayback();
					}
				}
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- MePageValidaton Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void MePageValidaton() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500);
			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");
			Thread.sleep(3000);
			click(TabletLocators.Rentalsme, "Rentals button");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}
			click(TabletLocators.rentalsBackbtn, "Rentals Back button is clicked");
			Thread.sleep(2500); // time to allow player to load
			click(TabletLocators.WatchList, "WatchList button is displayed");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}
			click(TabletLocators.WatchLaterBackbtn, "WatchLaterBackbtn Back button is clicked");
			Thread.sleep(1000);
			/*
			 * click(TabletLocators.FavSection,"Favorite button is displayed");
			 * if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)){
			 * Reporter.pass("Favorite Title", "FavTitle title  is displayed"); } else{
			 * Reporter.fail("Favorite Title", "Favorite title is not displayed"); }
			 * click(TabletLocators.FavBackbtn,"FavBackbtn Back button is clicked");
			 */
			Thread.sleep(1400);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.settingsSection, "Settings button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SettingsTitle)) {
				ReporterLog.pass("Settings Title", "Settings Title  is displayed");
			} else {
				ReporterLog.fail("Settings Title", " Settings Title is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "Settings Back button is clicked");

			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1500);
			click(TabletLocators.Subscrip, "Subscription");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Substitle)) {
				ReporterLog.pass("Subscription sub Title", "Subscription sub Title is displayed");
			} else {
				ReporterLog.fail("Subscription sub Title", " Subscription sub Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SubmitTitle)) {
				ReporterLog.pass("Submit option", "Submit option is displayed");
			} else {
				ReporterLog.fail("Submit option", " Submit option is not displayed");
			}
			driver.navigate().back();
			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}
			click(TabletLocators.setBackbtn, "settings Backbtn Back button is clicked");

			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1400);
			click(TabletLocators.SupportSection, "Support button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SupportSectionheader)) {
				ReporterLog.pass("SupportSection Title", "SupportSection  is displayed");
			} else {
				ReporterLog.fail("SupportSection", " SupportSection is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "settings Backbtn Back button is clicked");
			Thread.sleep(1400);
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.logOut, "ME icon");
			click(TabletLocators.conformlogout1, "Logout");

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- MePageSanity Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void MePageSanity() {
		try {
			click(TabletLocators.meProfile1, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500);
			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");
			Thread.sleep(3000);
			click(TabletLocators.premiumme, "Premium button");
			Thread.sleep(9000);
			click(TabletLocators.premiumbtn, "Explore Rental Movies btn");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}
			click(TabletLocators.rentalsBackbtn, "Rentals Back button is clicked");
			Thread.sleep(2500); // time to allow player to load
			click(TabletLocators.WatchList, "WatchList button is displayed");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}
			click(TabletLocators.WatchLaterBackbtn, "WatchLaterBackbtn Back button is clicked");
			Thread.sleep(1000);
			// click(TabletLocators.FavSection,"Favorite button is displayed");
			// if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)){
			// Reporter.pass("Favorite Title", "FavTitle title is displayed");
			// } else{
			// Reporter.fail("Favorite Title", "Favorite title is not displayed");
			// }
			// click(TabletLocators.FavBackbtn,"FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.settingsSection, "Settings button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SettingsTitle)) {
				ReporterLog.pass("Settings Title", "Settings Title  is displayed");
			} else {
				ReporterLog.fail("Settings Title", " Settings Title is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "Settings Back button is clicked");

			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1500);
			/*
			 * click(TabletLocators.Subscrip,"Subscription");
			 * if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.Substitle)) {
			 * Reporter.pass("Subscription sub Title",
			 * "Subscription sub Title is displayed"); } else {
			 * Reporter.fail("Subscription sub Title",
			 * " Subscription sub Title is not displayed"); }
			 */
			List<WebElement> eleHeader = driver.findElements(By.id("tv.hooq.android:id/discover_feed_list"));
			System.out.println(eleHeader.size());
			List<WebElement> eleSubscription = eleHeader.get(1)
					.findElements(By.id("tv.hooq.android:id/profileBtnSubscription"));
			System.out.println(eleSubscription.size());
			eleSubscription.get(0).click();

			/*
			 * MobileElement eleCollection=(MobileElement)
			 * driver.findElement(By.id("tv.hooq.android:id/discover_feed_list"));
			 * List<MobileElement> eleList1=eleCollection.findElements(By.className(
			 * "android.widget.RelativeLayout")); if(eleList1.size()>0) {
			 * System.out.println(eleList1.size()); List<MobileElement>
			 * eleMovie=eleList1.get(0).findElements(By.className(
			 * "android.widget.RelativeLayout")); if(eleMovie.size()>0) {
			 * eleMovie.get(5).click(); }
			 */
			driver.navigate().back();
			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}
			click(TabletLocators.setBackbtn, "settings Backbtn Back button is clicked");

			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1400);
			click(TabletLocators.SupportSection, "Support button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SupportSectionheader)) {
				ReporterLog.pass("SupportSection Title", "SupportSection  is displayed");
			} else {
				ReporterLog.fail("SupportSection", " SupportSection is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "settings Backbtn Back button is clicked");
			driver.navigate().back();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- MePageValidatonuser Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void MePageValidatonuser() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500);
			click(TabletLocators.DownloadsBackbtn, "Back button is clicked");
			Thread.sleep(3000);
			click(TabletLocators.Rentalsme, "Rentals button");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsticketsdesc)) {
				ReporterLog.pass("Rentals tickets desc", "Rentals tickets desc Title is displayed");
			} else {
				ReporterLog.fail("Rentals tickets desc", " Rentals tickets desc Title is not displayed");
			}
			click(TabletLocators.rentalsBackbtn, "Rentals Back button is clicked");
			Thread.sleep(2500); // time to allow player to load
			click(TabletLocators.WatchLater, "WatchLater button is displayed");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}
			click(TabletLocators.WatchLaterBackbtn, "WatchLaterBackbtn Back button is clicked");
			Thread.sleep(1000);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			click(TabletLocators.FavBackbtn, "FavBackbtn Back button is clicked");
			Thread.sleep(1400);
			click(TabletLocators.settingsSection, "Settings button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SettingsTitle)) {
				ReporterLog.pass("Settings Title", "Settings Title  is displayed");
			} else {
				ReporterLog.fail("Settings Title", " Settings Title is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "Settings Back button is clicked");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(1500);
			click(TabletLocators.Subscrip, "Subscription");
			Thread.sleep(2500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Substitle1)) {
				ReporterLog.pass("Subscription sub Title", "Subscription sub Title is displayed");
			} else {
				ReporterLog.fail("Subscription sub Title", " Subscription sub Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SubmitTitle)) {
				ReporterLog.pass("Submit option", "Submit option is displayed");
			} else {
				ReporterLog.fail("Submit option", " Submit option is not displayed");
			}
			driver.navigate().back();
			Thread.sleep(1400);
			click(TabletLocators.TransactionSection, "Transaction History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.TransactionTitle)) {
				ReporterLog.pass("Transaction Title", "Transaction Title  is displayed");
			} else {
				ReporterLog.fail("Transaction Title", " Transaction Title is not displayed");
			}
			click(TabletLocators.setBackbtn, "settings Backbtn Back button is clicked");
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.SupportSection, "Support button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.SupportSectionheader)) {
				ReporterLog.pass("SupportSection Title", "SupportSection  is displayed");
			} else {
				ReporterLog.fail("SupportSection", " SupportSection is not displayed");
			}
			click(TabletLocators.SetBackbtn1, "settings Backbtn Back button is clicked");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			Thread.sleep(2000);
			click(TabletLocators.logOut, "ME icon");
			click(TabletLocators.conformlogout1, "Logout");

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Linktv Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void Linktv() {
		try {
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.linktv, "Link Tv button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Linktvheader)) {
				ReporterLog.pass("LinkTv header", "LinkTv header is displayed");
			} else {
				ReporterLog.fail("LinkTv header", " LinkTv header is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Linktvtext)) {
				ReporterLog.pass("LinkTv Text", "LinkTv Text is displayed");
			} else {
				ReporterLog.fail("LinkTv Text", " LinkTv Text is not displayed");
			}
			click(TabletLocators.link, "Link option");
			Thread.sleep(1000);
			click(TabletLocators.linkscan, "Scan QR Code option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Linktvscantext)) {
				ReporterLog.pass("LinkTv Scan QR Text", "LinkTv Scan QR Text is displayed");
			} else {
				ReporterLog.fail("LinkTv Scan QR Text", " LinkTv Scan QR Text is not displayed");
			}
			Thread.sleep(2000);
			click(TabletLocators.linktvcode, "Link Tv code");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.link)) {
				ReporterLog.pass("LinkTv TV Code Button", "LinkTv TV Code Button is displayed");
			} else {
				ReporterLog.fail("LinkTv TV Code Button", "LinkTv TV Code Button is not displayed");
			}
			click(TabletLocators.linktvbckbtn, "Link Tv back button");
			click(TabletLocators.linktvbckbtn, "Link Tv back button");
			Thread.sleep(2000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.logOut, "ME icon");
			click(TabletLocators.conformlogout1, "Conform option");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Linktvsanity Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void Linktvsanity() {
		try {
			// click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.linktv, "Link Tv button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Linktvheader)) {
				ReporterLog.pass("LinkTv header", "LinkTv header is displayed");
			} else {
				ReporterLog.fail("LinkTv header", " LinkTv header is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Linktvtext)) {
				ReporterLog.pass("LinkTv Text", "LinkTv Text is displayed");
			} else {
				ReporterLog.fail("LinkTv Text", " LinkTv Text is not displayed");
			}
			click(TabletLocators.link, "Link option");
			Thread.sleep(1000);
			click(TabletLocators.linkscan, "Scan QR Code option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Linktvscantext)) {
				ReporterLog.pass("LinkTv Scan QR Text", "LinkTv Scan QR Text is displayed");
			} else {
				ReporterLog.fail("LinkTv Scan QR Text", " LinkTv Scan QR Text is not displayed");
			}
			Thread.sleep(2000);
			click(TabletLocators.linktvcode, "Link Tv code");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.link)) {
				ReporterLog.pass("LinkTv TV Code Button", "LinkTv TV Code Button is displayed");
			} else {
				ReporterLog.fail("LinkTv TV Code Button", "LinkTv TV Code Button is not displayed");
			}
			click(TabletLocators.linktvbckbtn, "Link Tv back button");
			click(TabletLocators.linktvbckbtn, "Link Tv back button");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Favoritesanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	/*
	 * public static void Favoritesanity() { clickOnTab("Discover");
	 * //Reporter.reportStep("Add to favorites"); String title =
	 * addFavouriteWatchLater(true,false); driver.navigate().back();
	 * click(TabletLocators.meProfile, "Me"); favoritesContentPlayBack1(); return
	 * true; }
	 *//***
		 * Function Name :- watchlistsanity Developed By :- Raja Reddy Date :-
		 * 23-May-2019
		 */
	public static void watchlistsanity() {
		try {
			clickOnTab("Discover");
			// Reporter.reportStep("Add to favorites");
			// String title = addFavouriteWatchLater(false,true);
			driver.navigate().back();
			click(TabletLocators.meProfile1, "Me");
			Thread.sleep(20000);
			watchlaterContentPlayBack1();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- addFavouriteWatchLater Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static String  addFavouriteWatchLater(boolean favourite, boolean watchLater) {
		try {
			@SuppressWarnings("unused")
			boolean flag = true;
			@SuppressWarnings("unused")
			int count = 0;
			Thread.sleep(1000);
			// navigateToShow();
			if (favourite) {
				click(TabletLocators.favorite, "Favorite");
			}
			if (watchLater) {
				click(TabletLocators.WatchList, "Watch List");
			}
			return getText(TabletLocators.videoTitle, "Video title");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return getText(TabletLocators.videoTitle, "Video title");
	}

	/***
	 * Function Name :- TVODMeValidatonrentalssection Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void TVODMeValidatonrentalssection() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(9000);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			Thread.sleep(3000);
			click(TabletLocators.Rentalsme, "Rentals button");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals Me Title", "Rentals Me Title is displayed");
			} else {
				ReporterLog.fail("Rentals Me Title", " Rentals Me Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsmetitle)) {
				ReporterLog.pass("Rentals title header", "Rentals title header is displayed");
			} else {
				ReporterLog.fail("Rentals title header", " Rentals title header is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsemptyicon)) {
				ReporterLog.pass("Rentals image ", "Rentals image is displayed");
			} else {
				ReporterLog.fail("Rentals image", " Rentals image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentalsemptydesc)) {
				ReporterLog.pass("Rentals empty description ", "Rentals empty description is displayed");
			} else {
				ReporterLog.fail("Rentals empty description", " Rentals empty description is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- SettingsMePageValidation Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void SettingsMePageValidation() {
		try {
			click(TabletLocators.settings, "settings button");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.appdisplaylanguage)) {
				ReporterLog.pass("App Display Language Title", "App Display Language is displayed");
			} else {
				ReporterLog.fail("App Display Language Title", " App Display Language Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.audiolanguage)) {
				ReporterLog.pass("Audio Language Title", "Audio Language Title is displayed");
			} else {
				ReporterLog.fail("Audio Language Title", " Audio Language Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subtitlelang)) {
				ReporterLog.pass("Subtitle Language Title", "Subtitle Language Title is displayed");
			} else {
				ReporterLog.fail("Subtitle Language Title", " Subtitle Language Title is not displayed");
			}
			click(TabletLocators.subtitlelang, "Subtitle language button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subtitlelang)) {
				ReporterLog.pass("Subtitle Language Title", "Subtitle Language Title is displayed");
			} else {
				ReporterLog.fail("Subtitle Language Title", " Subtitle Language Title is not displayed");
			}
			click(TabletLocators.subtitleeng, "Subtitle language English is selected");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.playback)) {
				ReporterLog.pass("Playback Quality Title", "Playback Quality Title is displayed");
			} else {
				ReporterLog.fail("Playback Quality Title", " Playback Quality Title is not displayed");
			}
			click(TabletLocators.playback, "Playback quality is selected");
			click(TabletLocators.playbackauto, "Playback quality is selected");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Downloadquality)) {
				ReporterLog.pass("Download Quality Title", "Download Quality Title is displayed");
			} else {
				ReporterLog.fail("Download Quality Title", " Download Quality Title is not displayed");
			}
			click(TabletLocators.Downloadquality, "Download quality is selected");

			click(TabletLocators.DownloadqualityMed, "Download quality Medium is selected");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.mobiledata)) {
				ReporterLog.pass("mobile data Title", "mobile data Title is displayed");
			} else {
				ReporterLog.fail("mobile data Title", " mobile data Title is not displayed");
			}

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- VerifywatchnowSection Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void VerifywatchnowSection() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Watchnowtitle)) {
				ReporterLog.pass("Watch Now Section", "Watch Now Section is displayed");
			} else {
				ReporterLog.fail("Watch Now Section", " Watch Now Section is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.watchnowbutton)) {
				ReporterLog.pass("watch now button", "watch now button is displayed");
			} else {
				ReporterLog.fail("watch now button", " watch now button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.watchnowfav)) {
				ReporterLog.pass("watch now favorite button", "watch now favorite button is displayed");
			} else {
				ReporterLog.fail("watch now favorite button", "watch now favorite button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.watchnowtitle)) {
				ReporterLog.pass("watch now title", "watch now title is displayed");
			} else {
				ReporterLog.fail("watch now title", "watch now title is not displayed");
			}
			click(TabletLocators.watchnowbutton, "Watchnow button");
			Thread.sleep(9000);
			verifyVideoPlayback();
			Thread.sleep(3000);
			click(TabletLocators.favoriteswatchnow, "Favorite option is clicked");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.favoriteswatchnowfavorited)) {
				ReporterLog.pass("watchnow favorited", "watchnow favorited is displayed");
			} else {
				ReporterLog.fail("watchnow favorited", "watchnow favorited is not displayed");
			}
			MobileElement eleBanner = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/pager_indicator_container"));
			List<MobileElement> eleList = eleBanner.findElements(By.className("android.widget.ImageView"));
			System.out.println(eleList.size());
			for (int i = 0; i < 5; i++) {
				eleList.get(i).click();
				String strTitle = driver.findElement(By.id("tv.hooq.android:id/spotLightTitle")).getText();
				System.out.println(strTitle);
				Thread.sleep(3000);
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidationREC Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void verifyPaymentValidationREC() {
		try {
			verifyPaymentlapse();
			Thread.sleep(6000);
			// Paytm
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("logo_paytm")) {
				ReporterLog.pass("Paytm Logo ", "Paytm Logo is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Logo", "Paytm Logo is not displayed successfully");
			}

			if (strSource4.contains("HOOQ SPECIAL OFFER")) {
				ReporterLog.pass("30-day FREE Trial Paytm ", "30-day FREE Trial Paytm is displayed successfully");
			} else {
				ReporterLog.fail("30-day FREE Trial Paytm", "30-day FREE Trial Paytm is not displayed successfully");
			}

			if (strSource4.contains("Subscribe now")) {
				ReporterLog.pass("Start your free trial now ", "Start your free trial now is displayed successfully");
			} else {
				ReporterLog.fail("Start your free trial now",
						"Start your free trial now is not displayed successfully");
			}
			click(TabletLocators.startsubscribe, "Start your Trial Button is clicked");
			Thread.sleep(25000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaytmMob)) {
				ReporterLog.pass("Enter Mobile number option", "Enter Mobile number option is visible");

			} else {
				ReporterLog.fail("Enter Mobile number option", "Enter Mobile number is not visible");
			}
			click(TabletLocators.paytmback, "Payment Back Button is clicked");
			Thread.sleep(5000);
			click(TabletLocators.CCrec, "Credit card Rec");

			if (strSource4.contains("logo_credit")) {
				ReporterLog.pass("Credit card Logo ", "Credit card Logo is displayed successfully");
			} else {
				ReporterLog.fail("Credit card Logo", "Credit card Logo is not displayed successfully");
			}
			if (strSource4.contains("HOOQ SPECIAL OFFER")) {
				ReporterLog.pass("30-day FREE Trial CC ", "30-day FREE Trial CC is displayed successfully");
			} else {
				ReporterLog.fail("30-day FREE Trial CC", "30-day FREE Trial CC is not displayed successfully");
			}

			if (strSource4.contains("Subscribe now")) {
				ReporterLog.pass("Start your free trial now ", "Start your free trial now is displayed successfully");
			} else {
				ReporterLog.fail("Start your free trial now",
						"Start your free trial now is not displayed successfully");
			}
			click(TabletLocators.startsubscribe, "Start your Trial Button is clicked");
			Thread.sleep(15000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.CCRecon)) {
				ReporterLog.pass("Credit card Rec ON", "Credit card Rec ON option is visible");
			} else {
				ReporterLog.fail("Credit card Rec ON", "Credit card Rec ON is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardno)) {
				ReporterLog.pass("Credit card Number", "Credit card Number option is visible");
			} else {
				ReporterLog.fail("Credit card Number", "Credit card Number is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardname)) {
				ReporterLog.pass("Credit card Name", "Credit card Name option is visible");

			} else {
				ReporterLog.fail("Credit card Name", "Credit card Name is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardexpiry)) {
				ReporterLog.pass("Credit card expiry", "Credit card expiry is visible");
			} else {
				ReporterLog.fail("Credit card expiry", "Credit card expiry is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardcvv)) {
				ReporterLog.pass("Credit card cvv", "Credit card cvv is visible");
			} else {
				ReporterLog.fail("Credit card cvv", "Credit card cvv is not visible");
			}
			driver.navigate().back();
			Thread.sleep(5000);

			// Phone Bill
			click(TabletLocators.Phonebil, "Phone bill");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebil)) {
				ReporterLog.pass("Phone bill logo", "Phone bill logo is visible");
			} else {
				ReporterLog.fail("Phone bill logo", "Phone bill logo is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebil)) {
				ReporterLog.pass("Phone bill logo", "Phone bill logo is visible");
			} else {
				ReporterLog.fail("Phone bill logo", "Phone bill logo is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillpre)) {
				ReporterLog.pass("Phone bill prepaid text", "Phone bill prepaid text is visible");
			} else {
				ReporterLog.fail("Phone bill prepaid text", "Phone bill prepaid text is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillsubs)) {
				ReporterLog.pass("Phone bill subscription", "Phone bill subscription text is visible");
			} else {
				ReporterLog.fail("Phone bill subscription", "Phone bill subscription text is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonesubs)) {
				ReporterLog.pass("Phone bill subscribe now", "Phone bill subscribe now is visible");
			} else {
				ReporterLog.fail("Phone bill subscribe now", "Phone bill subscribe now is not visible");
			}

			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonepayment)) {
				ReporterLog.pass("Phone bill payment Header", "Phone bill payment Header is visible");
			} else {
				ReporterLog.fail("Phone bill payment Header", "Phone bill payment Header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillmonth)) {
				click(TabletLocators.phonebillmonth, "Phone bill button");
			} else {
				ReporterLog.fail("Phone bill", "Phone bill is not displayed for ");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillautooff)) {
				ReporterLog.pass("Phone bill autooff", "Phone bill autooff is visible");
			} else {
				ReporterLog.fail("Phone bill autooff", "Phone bill autooff is not displayed for ");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillprice)) {
				ReporterLog.pass("Phone bill price", "Phone bill price is visible");
			} else {
				ReporterLog.fail("Phone bill price", "Phone bill price is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillmeth)) {
				ReporterLog.pass("Phone bill method", "Phone bill method is visible");
			} else {
				ReporterLog.fail("Phone bill method", "Phone bill method is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonemob)) {
				ReporterLog.pass("Phone bill mobile", "Phone bill mobile is visible");
			} else {
				ReporterLog.fail("Phone bill mobile", "Phone bill mobile is not displayed for ");
			}
			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}

			// Debit Card

			click(TabletLocators.subsdebit, "Debit card Option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsdebit)) {
				ReporterLog.pass("Debit card option", "Debit card option is visible");
			} else {
				ReporterLog.fail("Debit card option", "Debit card option is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillpre)) {
				ReporterLog.pass("Debit card prepaid text", "Debit card prepaid text is visible");

			} else {
				ReporterLog.fail("Debit card prepaid text", "Debit card prepaid text is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillsubs)) {
				ReporterLog.pass("Debit card subscription", "Debit card subscription text is visible");

			} else {
				ReporterLog.fail("Debit card subscription", "Debit card subscription text is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonesubs)) {
				ReporterLog.pass("Debit card subscribe now", "Debit card subscribe now is visible");

			} else {
				ReporterLog.fail("Debit card subscribe now", "Debit card subscribe now is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DebitINR)) {
				ReporterLog.pass("Debit card INR", "Debit card INR is visible");

			} else {
				ReporterLog.fail("Debit card INR", "Debit card INR is not visible");
			}

			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthsubs)) {
				ReporterLog.pass("Debit card 1 month subscription", "Debit card 1 month subscription is visible");

			} else {
				ReporterLog.fail("Debit card 1 month subscription", "Debit card 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthinr)) {
				ReporterLog.pass("Debit card 1 month subscription", "Debit card 1 month subscription is visible");

			} else {
				ReporterLog.fail("Debit card 1 month subscription", "Debit card 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthyear)) {
				ReporterLog.pass("Debit card 1 year subscription", "Debit card 1 year subscription is visible");

			} else {
				ReporterLog.fail("Debit card 1 year subscription", "Debit card 1 year subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debityearinr)) {
				ReporterLog.pass("Debit card 1 year inr", "Debit card 1 year inr is visible");

			} else {
				ReporterLog.fail("Debit card 1 year inr", "Debit card 1 year inr is not visible");
			}

			click(TabletLocators.Debitmonthsubs, "1 Month Subscription option");
			Thread.sleep(3000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardno)) {
				ReporterLog.pass("Debit card Number", "Debit card Number option is visible");

			} else {
				ReporterLog.fail("Debit card Number", "Debit card Number is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardname)) {
				ReporterLog.pass("Debit card Name", "Debit card Name option is visible");

			} else {
				ReporterLog.fail("Debit card Name", "Debit card Name is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardexpiry)) {
				ReporterLog.pass("Debit card expiry", "Debit card expiry is visible");

			} else {
				ReporterLog.fail("Debit card expiry", "Debit card expiry is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardcvv)) {
				ReporterLog.pass("Debit card cvv", "Debit card cvv is visible");

			} else {
				ReporterLog.fail("Debit card cvv", "Debit card cvv is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillautooff)) {
				ReporterLog.pass("Debit card autooff", "Debit card autooff is visible");
			} else {
				ReporterLog.fail("Debit card  autooff", "Debit card autooff is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitcancel)) {
				ReporterLog.pass("Debit card cancel", "Debit card cancel is visible");
			} else {
				ReporterLog.fail("Debit card cancel", "Debit card cancel is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitpayment)) {
				ReporterLog.pass("Debit card make payment", "Debit card make payment is visible");
			} else {
				ReporterLog.fail("Debit card make payment", "Debit card make payment is not displayed for ");
			}

			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}

			// Net banking

			click(TabletLocators.netbanking, "Net banking Option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.netbanking)) {
				ReporterLog.pass("Net banking option", "Net banking option is visible");
			} else {
				ReporterLog.fail("Net banking option", "Net banking option is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillpre)) {
				ReporterLog.pass("Net banking prepaid text", "Net banking prepaid text is visible");

			} else {
				ReporterLog.fail("Net banking prepaid text", "Net banking prepaid text is not visible");
			}

			// click(TabletLocators.netbanking,"Net banking Option");
			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthsubs)) {
				ReporterLog.pass("Net banking 1 month subscription", "Net banking 1 month subscription is visible");

			} else {
				ReporterLog.fail("Net banking 1 month subscription", "Net banking 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthinr)) {
				ReporterLog.pass("Net banking 1 month subscription", "Net banking 1 month subscription is visible");

			} else {
				ReporterLog.fail("Net banking 1 month subscription", "Net banking 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthyear)) {
				ReporterLog.pass("Net banking 1 year subscription", "Net banking 1 year subscription is visible");

			} else {
				ReporterLog.fail("Net banking 1 year subscription", "Net banking 1 year subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debityearinr)) {
				ReporterLog.pass("Net banking 1 year inr", "Net banking 1 year inr is visible");

			} else {
				ReporterLog.fail("Net banking 1 year inr", "Net banking 1 year inr is not visible");
			}

			click(TabletLocators.Debitmonthsubs, "1 Month Subscription option");
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.netbanktext)) {
				ReporterLog.pass("Net banking ", "Net banking text is visible");

			} else {
				ReporterLog.fail("Net banking text", "Net banking text is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.selectbank)) {
				ReporterLog.pass("select bank text ", "select bank text is visible");

			} else {
				ReporterLog.fail("select bank text", "select bank text is not visible");
			}

			click(TabletLocators.netbankingselect, "Netbanking select option");
			Thread.sleep(1000);
			driver.navigate().back();
			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :-verifyPaymentValidation_SG Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void verifyPaymentValidation_SG() {
		try {
			Thread.sleep(6000);
			click(TabletLocators.CCrec, "Credit card Rec");

			// Credit Card REC
			String strSource6 = driver.getPageSource();
			if (strSource6.contains("logo_credit")) {
				ReporterLog.pass("Credit card Logo ", "Credit card Logo is displayed successfully");
			} else {
				ReporterLog.fail("Credit card Logo", "Credit card Logo is not displayed successfully");
			}
			String strSource7 = driver.getPageSource();
			if (strSource7.contains("30-day FREE Trial")) {
				ReporterLog.pass("30-day FREE Trial CC ", "30-day FREE Trial CC is displayed successfully");
			} else {
				ReporterLog.fail("30-day FREE Trial CC", "30-day FREE Trial CC is not displayed successfully");
			}

			String strSource9 = driver.getPageSource();
			if (strSource9.contains("Start your free trial now")) {
				ReporterLog.pass("Start your free trial now ", "Start your free trial now is displayed successfully");
			} else {
				ReporterLog.fail("Start your free trial now",
						"Start your free trial now is not displayed successfully");
			}
			click(TabletLocators.subscribeSG, "Start your Trial Button is clicked");
			Thread.sleep(15000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.CCRecon)) {
				ReporterLog.pass("Credit card Rec ON", "Credit card Rec ON option is visible");
			} else {
				ReporterLog.fail("Credit card Rec ON", "Credit card Rec ON is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardno)) {
				ReporterLog.pass("Credit card Number", "Credit card Number option is visible");
			} else {
				ReporterLog.fail("Credit card Number", "Credit card Number is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardname)) {
				ReporterLog.pass("Credit card Name", "Credit card Name option is visible");

			} else {
				ReporterLog.fail("Credit card Name", "Credit card Name is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardexpiry)) {
				ReporterLog.pass("Credit card expiry", "Credit card expiry is visible");
			} else {
				ReporterLog.fail("Credit card expiry", "Credit card expiry is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardcvv)) {
				ReporterLog.pass("Credit card cvv", "Credit card cvv is visible");
			} else {
				ReporterLog.fail("Credit card cvv", "Credit card cvv is not visible");
			}
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}

			// MOL Payment Method
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscribeMOL)) {
				ReporterLog.pass("MOL Option", "MOL Option is visible");
			} else {
				ReporterLog.fail("MOL Option", "MOL Option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.molprepaid)) {
				ReporterLog.pass("MOL prepaid", "MOL prepaid is visible");
			} else {
				ReporterLog.fail("MOL prepaid", "MOL prepaid is not visible");
			}

			click(TabletLocators.subscribeMOL, "MOL Button is clicked");
			Thread.sleep(2000);
			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			Thread.sleep(2000);
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.mol1month)) {
				ReporterLog.pass("MOL 1 month", "MOL 1 month is visible");
			} else {
				ReporterLog.fail("MOL 1 month", "MOL 1 month is not visible");
			}

			click(TabletLocators.mol1month, "MOL 1 Month");
			Thread.sleep(12000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.molsubs)) {
				ReporterLog.pass("MOL 1 month", "MOL 1 month is visible");
			} else {
				ReporterLog.fail("MOL 1 month", "MOL 1 month is not visible");
			}

			click(TabletLocators.molsubs, "MOL Sign in");
			Thread.sleep(2000);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidationActive Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void verifyPaymentValidationActive() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscriptionheader)) {
				ReporterLog.pass("subscription header ", "subscription header is visible");
			} else {
				ReporterLog.fail("subscription header", "subscription header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("subscription header payment back button",
						"subscription header payment back button is displayed");
			} else {
				ReporterLog.fail("subscription header payment back button",
						"subscription header payment back button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrec)) {
				ReporterLog.pass("subscription renews text", "subscription renews text is displayed");
			} else {
				ReporterLog.fail("subscription renews text", "subscription renews text is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecautorenew)) {
				ReporterLog.pass("subscription auto renew", "subscription auto renew is displayed");
			} else {
				ReporterLog.fail("subscription auto renew", "subscription auto renew is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecautorenew1)) {
				ReporterLog.pass("subscription auto renew ON", "subscription auto renew ON is displayed");
			} else {
				ReporterLog.fail("subscription auto renew ON", "subscription auto renew ON is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecsubmit)) {
				ReporterLog.pass("subscription submit", "subscription submit is displayed");
			} else {
				ReporterLog.fail("subscription submit", "subscription submit is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscancel)) {
				ReporterLog.pass("subscription cancel subs", "subscription cancel subs is displayed");
			} else {
				ReporterLog.fail("subscription cancel subs", "subscription cancel subs is not displayed");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidationsanity Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void verifyPaymentValidationsanity() {

		try {
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscriptionheader)) {
				ReporterLog.pass("subscription header ", "subscription header is visible");
			} else {
				ReporterLog.fail("subscription header", "subscription header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("subscription header payment back button",
						"subscription header payment back button is displayed");
			} else {
				ReporterLog.fail("subscription header payment back button",
						"subscription header payment back button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrec)) {
				ReporterLog.pass("subscription renews text", "subscription renews text is displayed");
			} else {
				ReporterLog.fail("subscription renews text", "subscription renews text is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecautorenew)) {
				ReporterLog.pass("subscription auto renew", "subscription auto renew is displayed");
			} else {
				ReporterLog.fail("subscription auto renew", "subscription auto renew is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecautorenew1)) {
				ReporterLog.pass("subscription auto renew ON", "subscription auto renew ON is displayed");
			} else {
				ReporterLog.fail("subscription auto renew ON", "subscription auto renew ON is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecsubmit)) {
				ReporterLog.pass("subscription submit", "subscription submit is displayed");
			} else {
				ReporterLog.fail("subscription submit", "subscription submit is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscancel)) {
				ReporterLog.pass("subscription cancel subs", "subscription cancel subs is displayed");
			} else {
				ReporterLog.fail("subscription cancel subs", "subscription cancel subs is not displayed");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyspotlightlapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void verifyspotlightlapse() {
		// click(TabletLocators.startsubscribe,"Subscribe Now button");
		swipeUseElementspot(TabletLocators.spotlight, true);
	}

	/***
	 * Function Name :- verifyPaymentValidationLpase Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void verifyPaymentValidationLpase() {
		try {
			click(TabletLocators.MeProf, "ME icon");
			Thread.sleep(2000);
			click(TabletLocators.Subscrip, "Subscription");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscriptionheader)) {
				ReporterLog.pass("subscription header ", "subscription header is visible");
			} else {
				ReporterLog.fail("subscription header", "subscription header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("subscription header payment back button",
						"subscription header payment back button is displayed");
			} else {
				ReporterLog.fail("subscription header payment back button",
						"subscription header payment back button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsesubs)) {
				ReporterLog.pass("Lapse subscription message", "Lapse subscription message is displayed");
			} else {
				ReporterLog.fail("Lapse subscription message", "Lapse subscription message is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsrecsubmit)) {
				ReporterLog.pass("subscription submit", "subscription submit is displayed");
			} else {
				ReporterLog.fail("subscription submit", "subscription submit is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.vouchercode)) {
				ReporterLog.pass("Voucher code option", "Voucher code option is displayed");
			} else {
				ReporterLog.fail("Voucher code option", "Voucher code option is not displayed");
			}
			click(TabletLocators.startsubscribe, "Subscribe Now button");
			Thread.sleep(6000);

			// Paytm
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("logo_paytm")) {
				ReporterLog.pass("Paytm Logo ", "Paytm Logo is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Logo", "Paytm Logo is not displayed successfully");
			}

			if (strSource4.contains("HOOQ SPECIAL OFFER")) {
				ReporterLog.pass("30-day FREE Trial Paytm ", "30-day FREE Trial Paytm is displayed successfully");
			} else {
				ReporterLog.fail("30-day FREE Trial Paytm", "30-day FREE Trial Paytm is not displayed successfully");
			}

			if (strSource4.contains("Subscribe now")) {
				ReporterLog.pass("Start your free trial now ", "Start your free trial now is displayed successfully");
			} else {
				ReporterLog.fail("Start your free trial now",
						"Start your free trial now is not displayed successfully");
			}
			click(TabletLocators.startsubscribe, "Start your Trial Button is clicked");
			Thread.sleep(25000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaytmMob)) {
				ReporterLog.pass("Enter Mobile number option", "Enter Mobile number option is visible");

			} else {
				ReporterLog.fail("Enter Mobile number option", "Enter Mobile number is not visible");
			}
			click(TabletLocators.paytmback, "Payment Back Button is clicked");
			Thread.sleep(5000);
			click(TabletLocators.CCrec, "Credit card Rec");

			// Credit Card REC
			if (strSource4.contains("logo_credit")) {
				ReporterLog.pass("Credit card Logo ", "Credit card Logo is displayed successfully");
			} else {
				ReporterLog.fail("Credit card Logo", "Credit card Logo is not displayed successfully");
			}
			if (strSource4.contains("HOOQ SPECIAL OFFER")) {
				ReporterLog.pass("30-day FREE Trial CC ", "30-day FREE Trial CC is displayed successfully");
			} else {
				ReporterLog.fail("30-day FREE Trial CC", "30-day FREE Trial CC is not displayed successfully");
			}

			if (strSource4.contains("Subscribe now")) {
				ReporterLog.pass("Start your free trial now ", "Start your free trial now is displayed successfully");
			} else {
				ReporterLog.fail("Start your free trial now",
						"Start your free trial now is not displayed successfully");
			}
			click(TabletLocators.startsubscribe, "Start your Trial Button is clicked");
			Thread.sleep(15000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.CCRecon)) {
				ReporterLog.pass("Credit card Rec ON", "Credit card Rec ON option is visible");
			} else {
				ReporterLog.fail("Credit card Rec ON", "Credit card Rec ON is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardno)) {
				ReporterLog.pass("Credit card Number", "Credit card Number option is visible");
			} else {
				ReporterLog.fail("Credit card Number", "Credit card Number is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardname)) {
				ReporterLog.pass("Credit card Name", "Credit card Name option is visible");

			} else {
				ReporterLog.fail("Credit card Name", "Credit card Name is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardexpiry)) {
				ReporterLog.pass("Credit card expiry", "Credit card expiry is visible");
			} else {
				ReporterLog.fail("Credit card expiry", "Credit card expiry is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardcvv)) {
				ReporterLog.pass("Credit card cvv", "Credit card cvv is visible");
			} else {
				ReporterLog.fail("Credit card cvv", "Credit card cvv is not visible");
			}
			driver.navigate().back();
			Thread.sleep(5000);

			// Phone Bill
			click(TabletLocators.Phonebil, "Phone bill");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebil)) {
				ReporterLog.pass("Phone bill logo", "Phone bill logo is visible");
			} else {
				ReporterLog.fail("Phone bill logo", "Phone bill logo is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebil)) {
				ReporterLog.pass("Phone bill logo", "Phone bill logo is visible");
			} else {
				ReporterLog.fail("Phone bill logo", "Phone bill logo is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillpre)) {
				ReporterLog.pass("Phone bill prepaid text", "Phone bill prepaid text is visible");
			} else {
				ReporterLog.fail("Phone bill prepaid text", "Phone bill prepaid text is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillsubs)) {
				ReporterLog.pass("Phone bill subscription", "Phone bill subscription text is visible");
			} else {
				ReporterLog.fail("Phone bill subscription", "Phone bill subscription text is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonesubs)) {
				ReporterLog.pass("Phone bill subscribe now", "Phone bill subscribe now is visible");
			} else {
				ReporterLog.fail("Phone bill subscribe now", "Phone bill subscribe now is not visible");
			}

			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonepayment)) {
				ReporterLog.pass("Phone bill payment Header", "Phone bill payment Header is visible");
			} else {
				ReporterLog.fail("Phone bill payment Header", "Phone bill payment Header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillmonth)) {
				click(TabletLocators.phonebillmonth, "Phone bill button");
			} else {
				ReporterLog.fail("Phone bill", "Phone bill is not displayed for ");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillautooff)) {
				ReporterLog.pass("Phone bill autooff", "Phone bill autooff is visible");
			} else {
				ReporterLog.fail("Phone bill autooff", "Phone bill autooff is not displayed for ");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillprice)) {
				ReporterLog.pass("Phone bill price", "Phone bill price is visible");
			} else {
				ReporterLog.fail("Phone bill price", "Phone bill price is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillmeth)) {
				ReporterLog.pass("Phone bill method", "Phone bill method is visible");
			} else {
				ReporterLog.fail("Phone bill method", "Phone bill method is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonemob)) {
				ReporterLog.pass("Phone bill mobile", "Phone bill mobile is visible");
			} else {
				ReporterLog.fail("Phone bill mobile", "Phone bill mobile is not displayed for ");
			}
			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}

			// Debit Card

			click(TabletLocators.subsdebit, "Debit card Option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subsdebit)) {
				ReporterLog.pass("Debit card option", "Debit card option is visible");
			} else {
				ReporterLog.fail("Debit card option", "Debit card option is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillpre)) {
				ReporterLog.pass("Debit card prepaid text", "Debit card prepaid text is visible");

			} else {
				ReporterLog.fail("Debit card prepaid text", "Debit card prepaid text is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillsubs)) {
				ReporterLog.pass("Debit card subscription", "Debit card subscription text is visible");

			} else {
				ReporterLog.fail("Debit card subscription", "Debit card subscription text is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonesubs)) {
				ReporterLog.pass("Debit card subscribe now", "Debit card subscribe now is visible");

			} else {
				ReporterLog.fail("Debit card subscribe now", "Debit card subscribe now is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DebitINR)) {
				ReporterLog.pass("Debit card INR", "Debit card INR is visible");

			} else {
				ReporterLog.fail("Debit card INR", "Debit card INR is not visible");
			}

			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthsubs)) {
				ReporterLog.pass("Debit card 1 month subscription", "Debit card 1 month subscription is visible");

			} else {
				ReporterLog.fail("Debit card 1 month subscription", "Debit card 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthinr)) {
				ReporterLog.pass("Debit card 1 month subscription", "Debit card 1 month subscription is visible");

			} else {
				ReporterLog.fail("Debit card 1 month subscription", "Debit card 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthyear)) {
				ReporterLog.pass("Debit card 1 year subscription", "Debit card 1 year subscription is visible");

			} else {
				ReporterLog.fail("Debit card 1 year subscription", "Debit card 1 year subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debityearinr)) {
				ReporterLog.pass("Debit card 1 year inr", "Debit card 1 year inr is visible");

			} else {
				ReporterLog.fail("Debit card 1 year inr", "Debit card 1 year inr is not visible");
			}

			click(TabletLocators.Debitmonthsubs, "1 Month Subscription option");
			Thread.sleep(3000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardno)) {
				ReporterLog.pass("Debit card Number", "Debit card Number option is visible");

			} else {
				ReporterLog.fail("Debit card Number", "Debit card Number is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardname)) {
				ReporterLog.pass("Debit card Name", "Debit card Name option is visible");

			} else {
				ReporterLog.fail("Debit card Name", "Debit card Name is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardexpiry)) {
				ReporterLog.pass("Debit card expiry", "Debit card expiry is visible");

			} else {
				ReporterLog.fail("Debit card expiry", "Debit card expiry is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Cardcvv)) {
				ReporterLog.pass("Debit card cvv", "Debit card cvv is visible");

			} else {
				ReporterLog.fail("Debit card cvv", "Debit card cvv is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phonebillautooff)) {
				ReporterLog.pass("Debit card autooff", "Debit card autooff is visible");
			} else {
				ReporterLog.fail("Debit card  autooff", "Debit card autooff is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitcancel)) {
				ReporterLog.pass("Debit card cancel", "Debit card cancel is visible");
			} else {
				ReporterLog.fail("Debit card cancel", "Debit card cancel is not displayed for ");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitpayment)) {
				ReporterLog.pass("Debit card make payment", "Debit card make payment is visible");
			} else {
				ReporterLog.fail("Debit card make payment", "Debit card make payment is not displayed for ");
			}

			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}

			// Net banking

			click(TabletLocators.netbanking, "Net banking Option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.netbanking)) {
				ReporterLog.pass("Net banking option", "Net banking option is visible");
			} else {
				ReporterLog.fail("Net banking option", "Net banking option is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Phonebillpre)) {
				ReporterLog.pass("Net banking prepaid text", "Net banking prepaid text is visible");

			} else {
				ReporterLog.fail("Net banking prepaid text", "Net banking prepaid text is not visible");
			}

			// click(TabletLocators.netbanking,"Net banking Option");
			click(TabletLocators.Phonesubs, "Subscribe Now Option");
			click(TabletLocators.Phonesubcont, "Subscribe Now Continue");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthsubs)) {
				ReporterLog.pass("Net banking 1 month subscription", "Net banking 1 month subscription is visible");

			} else {
				ReporterLog.fail("Net banking 1 month subscription", "Net banking 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthinr)) {
				ReporterLog.pass("Net banking 1 month subscription", "Net banking 1 month subscription is visible");

			} else {
				ReporterLog.fail("Net banking 1 month subscription", "Net banking 1 month subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debitmonthyear)) {
				ReporterLog.pass("Net banking 1 year subscription", "Net banking 1 year subscription is visible");

			} else {
				ReporterLog.fail("Net banking 1 year subscription", "Net banking 1 year subscription is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Debityearinr)) {
				ReporterLog.pass("Net banking 1 year inr", "Net banking 1 year inr is visible");

			} else {
				ReporterLog.fail("Net banking 1 year inr", "Net banking 1 year inr is not visible");
			}

			click(TabletLocators.Debitmonthsubs, "1 Month Subscription option");
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.netbanktext)) {
				ReporterLog.pass("Net banking ", "Net banking text is visible");

			} else {
				ReporterLog.fail("Net banking text", "Net banking text is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.selectbank)) {
				ReporterLog.pass("select bank text ", "select bank text is visible");

			} else {
				ReporterLog.fail("select bank text", "select bank text is not visible");
			}

			click(TabletLocators.netbankingselect, "Netbanking select option");
			Thread.sleep(1000);
			driver.navigate().back();
			driver.navigate().back();
			driver.navigate().back();
			Thread.sleep(5000);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnClickSelectPlan Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void fnClickSelectPlan(String strPlanValue, boolean blnAutoRenew) {
		// boolean blnStatus=false;
		try {
			boolean blnSwipe = true;
			while (blnSwipe) {
				MobileElement eleCont = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/container"));
				List<MobileElement> eleList = eleCont.findElements(By.className("android.widget.LinearLayout"));
				System.out.println(eleList.size());
				for (int i = 0; i < eleList.size(); i++) {
					String strPlanValueVerify = eleList.get(i).findElement(By.id("tv.hooq.android:id/planTitle"))
							.getText();
					System.out.println(strPlanValueVerify);
					if (strPlanValueVerify.equals(strPlanValue)) {
						if (strPlanValue.endsWith("7 Days") == false) {
							if (blnAutoRenew) {
								eleList.get(i).findElement(By.id("tv.hooq.android:id/autoRenew")).click();
							}
						}
						eleList.get(i).findElement(By.id("tv.hooq.android:id/selectPlan")).click();
						// return true;
					}
				}
				swipeUseElement(TabletLocators.planTitle, true);
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation30DaysAutoRenewOn Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation30DaysAutoRenewOn() {
		try {
			swipeUseElement(TabletLocators.planTitle, true);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Autorenewon)) {
				ReporterLog.pass("AutoRenew On", "AutoRenew On is displayed");
			} else {
				ReporterLog.fail("AutoRenew On", "AutoRenew On is not displayed");
			}
			Thread.sleep(1000);
			fnClickSelectPlan("30 Days", false);
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc30)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc button is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod199", "PaymentMethod199 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod199", "PaymentMethod69 button is not displayed");
			}

			// Paytm
			click(TabletLocators.Paytm1, "Payment Back Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(6000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Credit card
			click(TabletLocators.creditcard, "Credit Card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			if (strSource.contains("Expiry Date")) {
				ReporterLog.pass("Expiry Date Option ", "Expiry Date Option is displayed successfully");
			} else {
				ReporterLog.fail("Expiry Date Option", "Expiry Date Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation30DaysAutoRenewOff Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation30DaysAutoRenewOff() {
		try {
			click(TabletLocators.Autorenewoff, "Auto Renew Off for 30 days");
			Thread.sleep(1000);
			fnClickSelectPlan("30 Days", true);
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc30)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc button is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod199", "PaymentMethod199 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod199", "PaymentMethod199 button is not displayed");
			}
			// carrier billing
			click(TabletLocators.Carrierbilling, "Click on Carrier Billing");
			Thread.sleep(1400);
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			String strSource = driver.getPageSource();
			if (strSource.contains("MOBILE BILL PAYMENT")) {
				ReporterLog.pass("Carrier Billing", "Carrier Billing displayed successfully");
			} else {
				ReporterLog.fail("Carrier Billing", "Carrier Billing is not displayed successfully");
			}
			String strText1 = "The cost will be charged to your mobile phone bill.";
			if (strSource.contains("The cost will be charged to your mobile phone bill.")
					&& strSource.contains(strText1)) {
				ReporterLog.pass("Carrier Billing desc", "Carrier Billing desc is displayed successfully");
			} else {
				ReporterLog.fail("Carrier Billing desc", "Carrier Billing desc is not displayed successfully");
			}
			String strText2 = "Mobile Number";
			if (strSource.contains("Mobile Number") && strSource.contains(strText2)) {
				ReporterLog.pass("Carrier Billing Mobile Number",
						"Carrier Billing Mobile Number is displayed successfully");
			} else {
				ReporterLog.fail("Carrier Billing Mobile Number",
						"Carrier Billing Mobile Number is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");

			Thread.sleep(2000);
			// Debit card
			click(TabletLocators.Debitcarrier30, "Debit card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("carrierbillmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource1 = driver.getPageSource();
			String strText4 = "DEBIT CARD DETAILS";
			if (strSource1.contains("DEBIT CARD DETAILS") && strSource1.contains(strText4)) {
				ReporterLog.pass("DEBIT CARD ", "Debit Card Details is displayed successfully");
			} else {
				ReporterLog.fail("DEBIT CARD DETAILS", "DEBIT CARD DETAILS is not displayed successfully");
			}

			if (strSource1.contains("Card Number")) {
				ReporterLog.pass("Debit Card Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number", "Debit Card Number is not displayed successfully");
			}

			if (strSource1.contains("Name on Card")) {
				ReporterLog.pass("Debit Card Number Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number Number", "Debit Card Number is not displayed successfully");
			}

			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// cash card
			click(TabletLocators.Cashcard30, "Cash card Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource2 = driver.getPageSource();
			if (strSource2.contains("CASH CARD PAYMENT")) {
				ReporterLog.pass("Cash Card Payment ", "Cash Card Payment is displayed successfully");
			} else {
				ReporterLog.fail("Cash Card Payment", "Cash Card Payment is not displayed successfully");
			}
			if (strSource2.contains("Please select your cash card")) {
				ReporterLog.pass("Select your cash card", "Select your cash card is displayed successfully");
			} else {
				ReporterLog.fail("Select your cash card", "Select your cash card is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// NetBanking
			click(TabletLocators.Netbanking30, "Net Banking option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource3 = driver.getPageSource();
			if (strSource3.contains("NET BANKING PAYMENT")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			if (strSource3.contains("Select your bank")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Paytm
			click(TabletLocators.Paytm30, "Payment Back Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(5000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(7000);
			swipeUseElement1(TabletLocators.planTitle30, true);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation90DaysAutoRenewOn Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation90DaysAutoRenewOn() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Autorenewon)) {
				ReporterLog.pass("AutoRenew On for 90 Days", "AutoRenew On for 90 Days is displayed");
			} else {
				ReporterLog.fail("AutoRenew On for 90 Days", "AutoRenew On for 90 Days is not displayed");
			}
			Thread.sleep(2000);
			fnClickSelectPlan("90 Days", false);
			Thread.sleep(2500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc90)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc button is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod490", "PaymentMethod490 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod199", "PaymentMethod69 button is not displayed");
			}
			// Paytm
			click(TabletLocators.Paytm2, "PayTM Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(6000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Credit card
			click(TabletLocators.creditcard1, "Credit Card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			if (strSource.contains("Expiry Date")) {
				ReporterLog.pass("Expiry Date Option ", "Expiry Date Option is displayed successfully");
			} else {
				ReporterLog.fail("Expiry Date Option", "Expiry Date Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation90DaysAutoRenewOff Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation90DaysAutoRenewOff() {
		try {
			click(TabletLocators.Autorenewoff, "Auto Renew Off for 90 days");
			Thread.sleep(1000);
			fnClickSelectPlan("90 Days", true);
			// click(TabletLocators.selectplan,"Select plan 30 days");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc90)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc button is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod490", "PaymentMethod490 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod490", "PaymentMethod490 button is not displayed");
			}
			Thread.sleep(2000);
			// Debit card
			click(TabletLocators.Debitcarrier90, "Debit card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("carrierbillmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource1 = driver.getPageSource();
			String strText4 = "DEBIT CARD DETAILS";
			if (strSource1.contains("DEBIT CARD DETAILS") && strSource1.contains(strText4)) {
				ReporterLog.pass("DEBIT CARD ", "Debit Card Details is displayed successfully");
			} else {
				ReporterLog.fail("DEBIT CARD DETAILS", "DEBIT CARD DETAILS is not displayed successfully");
			}

			if (strSource1.contains("Card Number")) {
				ReporterLog.pass("Debit Card Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number", "Debit Card Number is not displayed successfully");
			}

			if (strSource1.contains("Name on Card")) {
				ReporterLog.pass("Debit Card Number Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number Number", "Debit Card Number is not displayed successfully");
			}

			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// cash card
			click(TabletLocators.Cashcard90, "Cash card Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource2 = driver.getPageSource();
			if (strSource2.contains("CASH CARD PAYMENT")) {
				ReporterLog.pass("Cash Card Payment ", "Cash Card Payment is displayed successfully");
			} else {
				ReporterLog.fail("Cash Card Payment", "Cash Card Payment is not displayed successfully");
			}
			if (strSource2.contains("Please select your cash card")) {
				ReporterLog.pass("Select your cash card", "Select your cash card is displayed successfully");
			} else {
				ReporterLog.fail("Select your cash card", "Select your cash card is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// NetBanking
			click(TabletLocators.Netbanking90, "Net Banking option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource3 = driver.getPageSource();
			if (strSource3.contains("NET BANKING PAYMENT")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			if (strSource3.contains("Select your bank")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Paytm
			click(TabletLocators.Paytm90, "Payment Back Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(5000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(7000);
			swipeUseElement1(TabletLocators.planTitle90, true);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation180DaysAutoRenewOn Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation180DaysAutoRenewOn() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Autorenewon)) {
				ReporterLog.pass("AutoRenew On for 180 Days", "AutoRenew On for 180 Days is displayed");
			} else {
				ReporterLog.fail("AutoRenew On for 180 Days", "AutoRenew On for 180 Days is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Bill180desc)) {
				ReporterLog.pass("Bill desc for 180 Days", "Bill desc for 180 Days is displayed");
			} else {
				ReporterLog.fail("Bill desc for 180 Days", "Bill desc for 180 Days is not displayed");
			}
			Thread.sleep(2000);
			fnClickSelectPlan("180 Days", false);
			Thread.sleep(2500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc180)) {
				ReporterLog.pass("PaymentMethoddesc for 180 Plan", "PaymentMethoddesc for 180 Plan is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc for 180 Plan", "PaymentMethoddesc for 180 Plan is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod900", "PaymentMethod900 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod900", "PaymentMethod900 button is not displayed");
			}
			// Paytm
			click(TabletLocators.Paytm3, "PayTM Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(6000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Credit card
			click(TabletLocators.creditcard2, "Credit Card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			if (strSource.contains("Expiry Date")) {
				ReporterLog.pass("Expiry Date Option ", "Expiry Date Option is displayed successfully");
			} else {
				ReporterLog.fail("Expiry Date Option", "Expiry Date Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation180DaysAutoRenewOff Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation180DaysAutoRenewOff() {
		try {
			click(TabletLocators.Autorenewoff, "Auto Renew Off for 180 days");
			Thread.sleep(1000);
			fnClickSelectPlan("180 Days", true);
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc180)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc button is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod900", "PaymentMethod490 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod900", "PaymentMethod490 button is not displayed");
			}
			Thread.sleep(2000);
			// Debit card
			click(TabletLocators.Debitcarrier180, "Debit card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("carrierbillmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource1 = driver.getPageSource();
			String strText4 = "DEBIT CARD DETAILS";
			if (strSource1.contains("DEBIT CARD DETAILS") && strSource1.contains(strText4)) {
				ReporterLog.pass("DEBIT CARD ", "Debit Card Details is displayed successfully");
			} else {
				ReporterLog.fail("DEBIT CARD DETAILS", "DEBIT CARD DETAILS is not displayed successfully");
			}

			if (strSource1.contains("Card Number")) {
				ReporterLog.pass("Debit Card Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number", "Debit Card Number is not displayed successfully");
			}

			if (strSource1.contains("Name on Card")) {
				ReporterLog.pass("Debit Card Number Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number Number", "Debit Card Number is not displayed successfully");
			}

			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// cash card
			click(TabletLocators.Cashcard180, "Cash card Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource2 = driver.getPageSource();
			if (strSource2.contains("CASH CARD PAYMENT")) {
				ReporterLog.pass("Cash Card Payment ", "Cash Card Payment is displayed successfully");
			} else {
				ReporterLog.fail("Cash Card Payment", "Cash Card Payment is not displayed successfully");
			}
			if (strSource2.contains("Please select your cash card")) {
				ReporterLog.pass("Select your cash card", "Select your cash card is displayed successfully");
			} else {
				ReporterLog.fail("Select your cash card", "Select your cash card is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// NetBanking
			click(TabletLocators.Netbanking180, "Net Banking option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource3 = driver.getPageSource();
			if (strSource3.contains("NET BANKING PAYMENT")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			if (strSource3.contains("Select your bank")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Paytm
			click(TabletLocators.Paytm180, "PayTM Option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(5000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(7000);
			swipeUseElement1(TabletLocators.planTitle180, true);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation360DaysAutoRenewOn Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation360DaysAutoRenewOn() {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Autorenewon)) {
				ReporterLog.pass("AutoRenew On for 180 Days", "AutoRenew On for 180 Days is displayed");
			} else {
				ReporterLog.fail("AutoRenew On for 180 Days", "AutoRenew On for 180 Days is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Bill360desc)) {
				ReporterLog.pass("Bill desc for 360 Days", "Bill desc for 360 Days is displayed");
			} else {
				ReporterLog.fail("Bill desc for 360 Days", "Bill desc for 360 Days is not displayed");
			}
			Thread.sleep(2000);
			fnClickSelectPlan("360 Days", false);
			Thread.sleep(2500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc360)) {
				ReporterLog.pass("PaymentMethoddesc for 360 Plan", "PaymentMethoddesc for 360 Plan is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc for 360 Plan", "PaymentMethoddesc for 360 Plan is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR2)) {
				ReporterLog.pass("PaymentMethod1700", "PaymentMethod1700 is displayed");
			} else {
				ReporterLog.fail("PaymentMethod1700", "PaymentMethod1700 is not displayed");
			}
			// Paytm
			click(TabletLocators.Paytm4, "PayTM Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(6000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Credit card
			click(TabletLocators.creditcard3, "Credit Card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			if (strSource.contains("Expiry Date")) {
				ReporterLog.pass("Expiry Date Option ", "Expiry Date Option is displayed successfully");
			} else {
				ReporterLog.fail("Expiry Date Option", "Expiry Date Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidation360DaysAutoRenewOff Developed By :-
	 * Raja Reddy Date :- 23-May-2019
	 */
	public static void verifyPaymentValidation360DaysAutoRenewOff() {
		try {
			click(TabletLocators.Autorenewoff, "Auto Renew Off for 360 days");
			Thread.sleep(1000);
			fnClickSelectPlan("360 Days", true);
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc360)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR2)) {
				ReporterLog.pass("PaymentMethod1700", "PaymentMethod1700  is displayed");
			} else {
				ReporterLog.fail("PaymentMethod1700", "PaymentMethod1700 is not displayed");
			}
			Thread.sleep(2000);
			// Debit card
			click(TabletLocators.Debitcarrier360, "Debit card option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("carrierbillmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource1 = driver.getPageSource();
			String strText4 = "DEBIT CARD DETAILS";
			if (strSource1.contains("DEBIT CARD DETAILS") && strSource1.contains(strText4)) {
				ReporterLog.pass("DEBIT CARD ", "Debit Card Details is displayed successfully");
			} else {
				ReporterLog.fail("DEBIT CARD DETAILS", "DEBIT CARD DETAILS is not displayed successfully");
			}

			if (strSource1.contains("Card Number")) {
				ReporterLog.pass("Debit Card Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number", "Debit Card Number is not displayed successfully");
			}

			if (strSource1.contains("Name on Card")) {
				ReporterLog.pass("Debit Card Number Number  ", "Debit Card Number is displayed successfully");
			} else {
				ReporterLog.fail("Debit Card Number Number", "Debit Card Number is not displayed successfully");
			}

			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// cash card
			click(TabletLocators.Cashcard360, "Cash card Button is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource2 = driver.getPageSource();
			if (strSource2.contains("CASH CARD PAYMENT")) {
				ReporterLog.pass("Cash Card Payment ", "Cash Card Payment is displayed successfully");
			} else {
				ReporterLog.fail("Cash Card Payment", "Cash Card Payment is not displayed successfully");
			}
			if (strSource2.contains("Please select your cash card")) {
				ReporterLog.pass("Select your cash card", "Select your cash card is displayed successfully");
			} else {
				ReporterLog.fail("Select your cash card", "Select your cash card is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");

			// NetBanking
			click(TabletLocators.Netbanking360, "Net Banking option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource3 = driver.getPageSource();
			if (strSource3.contains("NET BANKING PAYMENT")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			if (strSource3.contains("Select your bank")) {
				ReporterLog.pass("Net Banking Option ", "Net Banking Option is displayed successfully");
			} else {
				ReporterLog.fail("Net Banking Option", "Net Banking Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
			// Paytm
			click(TabletLocators.Paytm360, "PayTM Option is clicked");
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(10000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			Thread.sleep(5000);
			String strSource4 = driver.getPageSource();
			if (strSource4.contains("Enter Mobile Number")) {
				ReporterLog.pass("Paytm Mobile Number Option ", "Paytm Mobile Number Option is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Mobile Number Option",
						"Paytm Mobile Number Option is not displayed successfully");
			}
			click(TabletLocators.paymentback, "Payment Back Button is clicked");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonSubScripCheckTablet Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonSubScripCheckTablet(String description) {
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonemptyStateIcon)) {
				ReporterLog.pass("AnonymouseUserSubPage", "Empty state icon is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "Empty state icon is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anAnonUser)) {
				ReporterLog.pass("AnonymouseUserSubPage", "<b>HOWDY STRANGER!</b> content is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "<b>HOWDY STRANGER!</b> content is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonstateDescrip)) {
				ReporterLog.pass("AnonymouseUserSubPage", "Anonymouse User content description message is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage",
						"Anonymouse User content description message is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonStartBtn)) {
				ReporterLog.pass("AnonymouseUserSubPage", "<b>Get started</b> button is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "<b>Get started</b> button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonbtnUp)) {
				ReporterLog.pass("AnonymouseUserSubPage", "Back button is displayed");
			} else {
				ReporterLog.fail("AnonymouseUserSubPage", "Back button is not displayed");
			}

			click(TabletLocators.anonbtnUp, "Back button");

			Thread.sleep(500);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonMeProf1)) {
				ReporterLog.pass("ProfilePage", "Navigated to Profile page");
				Thread.sleep(300);
				click(TabletLocators.Subscrip, "Subscription button");

			} else {
				ReporterLog.fail("ProfilePage", "Unable to navigate to Profile page");
			}

			Thread.sleep(500);
			click(TabletLocators.anonStartBtn, "Get Started button");
			Thread.sleep(500);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUp)) {
				ReporterLog.pass("Sign Up", "Sign Up page is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Sign Up page is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUpTxt)) {
				ReporterLog.pass("Sign Up", "Sign up with your mobile number content is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Sign up with your mobile number content is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonInputLayout)) {
				ReporterLog.pass("Sign Up", "Mobile number input field is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Mobile number input field is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUpDoneBtn)) {
				ReporterLog.pass("Sign Up", "Done is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Done is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonTos)) {
				ReporterLog.pass("Sign Up", "Terms & Conditions, Privacy policy content is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Terms & Conditions, Privacy policy content is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.AnonSignUpLoginTab)) {
				ReporterLog.pass("Sign Up", "Already a user? Login is displayed");
			} else {
				ReporterLog.fail("Sign Up", "Already a user? Login is not displayed");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- findSeasonEpisodeContentPlayBacktab Developed By :- Raja
	 * Reddy Date :- 23-May-2019
	 */
	public static void findSeasonEpisodeContentPlayBacktab(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.WatchLater, "WatchLater button is displayed");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watch Later Title", "Watch Later title  is displayed");
			} else {
				ReporterLog.fail("Watch Later Title", "Watch Later title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterBackbtn)) {
				ReporterLog.pass("Watch Later back button", "Watch Later back button  is displayed");
			} else {
				ReporterLog.fail("Watch Later back button", "Watch Later back button is not displayed");
			}
			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Watch Later EDIT button", "Watch Later edit button  is displayed");
				} else {
					ReporterLog.fail("Watch Later EDIT button", "Watch Later edit button is not displayed");
				}

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterimg)) {
					ReporterLog.pass("Watch Later image button", "Watch Later image button  is displayed");
				} else {
					ReporterLog.fail("Watch Later image button", "Watch Later image button is not displayed");
				}

				if (findEpisode()) {
					click(TabletLocators.findEpisodeBtn, "Find Episode");
					List<WebElement> playButtons = driver.findElements(TabletLocators.episodeList);
					if (playButtons.size() > 0) {
						playButtons.get(3).click();
						ReporterLog.pass("SecondEpisode", "Clicked play button on second episode");
					} else {
						click(TabletLocators.playButtonep, "play video");
					}

					verifyVideoPlayback();
				}

			} else {
				ReporterLog.pass("WatchLater", "No videos are available in watch later section");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- activelanguageset Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void activelanguageset(String description) {
		try {
			click(TabletLocators.Settings, "Language settings");
			Thread.sleep(2500);
			click(TabletLocators.Applang, "App Language");
			Thread.sleep(2500);
			click(TabletLocators.Applanguagethai, "App Display Thai Language");
			driver.navigate().back(); // close keyboard
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- findSeasonEpisodeContentPlayBacksearch Developed By :- Raja
	 * Reddy Date :- 23-May-2019
	 */
	public static void findSeasonEpisodeContentPlayBacksearch(String description) {
		try {
			if (findEpisode()) {
				// Reporter.reportStep("Search Movie / TV Episode");
				click(TabletLocators.btnSearch, "Search button");
				type(TabletLocators.edtSearch, description, "Enter Movie Details");
				click(TabletLocators.txtSuggestedText, "Suggested Text");
				click(TabletLocators.FindSeasonEpisod, "Find Episode");
				MobileElement eleMobile = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/mSeasonRecyclerListView"));
				List<MobileElement> eleList = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				if (eleList.size() > 0) {
					MobileElement eleEpisode = eleList.get(0);
					MobileElement eleFrameLayout = eleEpisode.findElement(By.id("tv.hooq.android:id/idFramePlay"));
					MobileElement elePlayButton = eleFrameLayout.findElement(By.id("tv.hooq.android:id/play"));
					elePlayButton.click();
					// blnfound=true;
				}
				verifyVideoPlayback();
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentlapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void verifyPaymentlapse() {
		try {
			Thread.sleep(15000);
			String strSource = driver.getPageSource();
			if (strSource.contains("Promotion")) {
				ReporterLog.pass("Paytm Billing", "Paytm Billing displayed successfully");
			} else {
				ReporterLog.fail("Paytm Billing", "Paytm Billing is not displayed successfully");
			}
			String strText1 = "Promotion";
			if (strSource.contains("Promotion") && strSource.contains(strText1)) {
				ReporterLog.pass("Paytm Billing desc", "Paytm Billing desc is displayed successfully");
			} else {
				ReporterLog.fail("Paytm Billing desc", "Paytm Billing desc is not displayed successfully");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyPaymentValidationTablet Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void verifyPaymentValidationTablet() {
		try {
			click(TabletLocators.selectplan, "Select plan 7 days");
			Thread.sleep(1500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentTitle)) {
				ReporterLog.pass("PaymentMethodTitle", "PaymentMethodTitle button is displayed");
			} else {
				ReporterLog.fail("PaymentMethodTitle", "PaymentMethodTitle button is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmtddesc)) {
				ReporterLog.pass("PaymentMethoddesc", "PaymentMethoddesc button is displayed");
			} else {
				ReporterLog.fail("PaymentMethoddesc", "PaymentMethoddesc button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.PaymentINR)) {
				ReporterLog.pass("PaymentMethod69", "PaymentMethod69 button is displayed");
			} else {
				ReporterLog.fail("PaymentMethod69", "PaymentMethod69 button is not displayed");
			}

			click(TabletLocators.CarrierRadiobtn, "Click on Carrier Billing");
			Thread.sleep(1400);
			click(TabletLocators.Makepaymentbtn, "Make payment");
			Thread.sleep(3500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Carrierbillmthd)) {
				ReporterLog.pass("carrierbillmethod", "carrier billing payment method is displayed");
			} else {
				ReporterLog.fail("carrierbillmethod", "carrier billing payment method is not displayed");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyactiveuserTVODColl Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void verifyactiveuserTVODColl() {
		try {
			// Reporter.reportStep("Premium+ Page");
			click(TabletLocators.tabPremium, "Premium Tab");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.exploremovie)) {
				click(TabletLocators.exploremovie, "Explore Rental Movies");
				Thread.sleep(2000);
			}
			click(TabletLocators.firstCollection1, "First collection");
			Thread.sleep(2000);
			MobileElement elemobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/browseTitlesListView"));
			List<MobileElement> eleEpisode = elemobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(3000);
			// MePageHelper.verifyVideoPlayback();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- verifyactiveuserPremiumTab Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void verifyactiveuserPremiumTab() {
		try {
			// Reporter.reportStep("Premium+ Page");
			click(TabletLocators.tabPremium, "Premium Tab");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			Thread.sleep(2000);
			click(TabletLocators.firstCollection1, "First collection");
			Thread.sleep(2000);
			MobileElement elemobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/browseTitlesListView"));
			List<MobileElement> eleEpisode = elemobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(0).click();
			Thread.sleep(3000);
			click(TabletLocators.rentPlay1, "Rent video playing");
			Thread.sleep(3000);
			verifyVideoPlayback();
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- watchlaterContentPlayBackFree Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void watchlaterContentPlayBackFree(String description) {
		try {
			// Reporter.reportStep(description);
			click(TabletLocators.WatchList, "Watch list button");

			if (!isNumOf_Elements_Greater_Than_Zero(TabletLocators.browseBtnInDownLoadSection)) {
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cancelBtn)) {
					ReporterLog.pass("Watch list EDIT button", "Watch list edit button  is displayed");
				} else {
					ReporterLog.fail("Watch list EDIT button", "Watch list edit button is not displayed");
				}
				MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/listView"));
				List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
				eleEpisode.get(0).click();
				click(TabletLocators.playButton1, "First collection in Watch list");
				// MePageHelper.verifyVideoPlayback();
			} else {
				ReporterLog.pass("Watch list", "No videos to play in Watch list section");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Rentonboarding Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Rentonboarding(String description) {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.latestmvtext)) {
				ReporterLog.pass("Latest movie text available", "Latest movie available text is displayed");
			} else {
				ReporterLog.fail("Latest movie text available", "Latest movie available text is not displayed");
			}

			MobileElement eleDesc = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/dialogContentLayout"));
			List<MobileElement> eleListDesc = eleDesc.findElements(By.className("android.widget.TextView"));
			String strText = eleListDesc.get(0).getText();
			System.out.println(strText);
			if (strText.length() > 10) {
				// System.out.println("Description is present");
				ReporterLog.pass("ticketdesc Title", "ticketdesc title  is displayed");
			} else {
				// System.out.println("Description is not present");
				ReporterLog.fail("ticketdesc Title", "ticketdesc title is not displayed");
			}
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			// waitForInVisibilityOfElement(TabletLocators.genreList, "Rent page content");
			// click(TabletLocators.activehooqClick,"First collection in Rent page");
			// String title = getText(TabletLocators.activeAssetTitle, "title");
			// waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content"
			// activeAssetTitle);
			// click(TabletLocators.genreListView1, title+" is from selected collection");

			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.lapsedhooqClick, "First collection in  Rent page");
			String title = getText(TabletLocators.lapsedAssetTitle, "title");
			waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content");
			click(TabletLocators.lapsedAssetTitle, "first episode/movie from selected collection");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Trailer)) {
				click(TabletLocators.Trailer, "Trailer");
			} else {
				ReporterLog.fail("Trailer Button", "Trailer Button is not displayed for" + title);
			}
			// Thread.sleep(2000);
			// MePageHelper.verifyVideoPlayback();

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentoptn)) {
				click(TabletLocators.rentoptn, "Rent option");
			} else {
				ReporterLog.fail("Rent option", "Rent option is not displayed for " + title);
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpurchasedes)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttc)) {
				ReporterLog.pass("rent TC Title", "rent TC Title is displayed");
			} else {
				ReporterLog.fail("rent TC Title", "rent TC Title is not displayed");
			}
			click(TabletLocators.tktpurchase, "ticket purchase");
			click(TabletLocators.ticketconform, "ticket conform");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ticketredeem)) {
				ReporterLog.pass("ticket redeemed Title", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticket redeemed Title", "ticket redeemed Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ticketredeeminfo)) {
				ReporterLog.pass("ticketredeeminfo ", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticketredeeminfo", "ticket redeemed info is not displayed");
			}
			click(TabletLocators.watchnow, "watch now option");
			verifyVideoPlayback();
			// return true;
			// driver.navigate().back(); //close keyboard
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Premiumplusonboarding Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Premiumplusonboarding(String description) {
		try {
			click(TabletLocators.tabPremium, "Premium page");
			Thread.sleep(1000);
			/*
			 * if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.explorebtn)) {
			 * click(TabletLocators.explorebtn,"Explore Rental Movies btn");
			 * Thread.sleep(1000); }
			 */
			MobileElement eleDesc = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/dialogContentLayout"));
			List<MobileElement> eleListDesc = eleDesc.findElements(By.className("android.widget.TextView"));
			String strText = eleListDesc.get(0).getText();
			System.out.println(strText);
			if (strText.length() > 10) {
				// System.out.println("Description is present");
				ReporterLog.pass("ticketdesc Title", "ticketdesc title  is displayed");
			} else {
				// System.out.println("Description is not present");
				ReporterLog.fail("ticketdesc Title", "ticketdesc title is not displayed");
			}
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("Premium+ Title", "Premium+ title  is displayed");
			} else {
				ReporterLog.fail("Premium+ Title", "Premium+ title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}

			waitForInVisibilityOfElement(TabletLocators.genreList1, "Premium+ page content");
			click(TabletLocators.lapsedhooqClick, "First collection in  premium+ page");
			/*
			 * waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content");
			 * String title = getText(TabletLocators.lapsedAssetTitle, "title");
			 * click(TabletLocators.
			 * lapsedAssetTitle,"first episode/movie from selected collection");
			 */
			MobileElement elemobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/browseTitlesListView"));
			List<MobileElement> eleEpisode = elemobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(4).click();
			String title = getText(TabletLocators.lapsedAssetTitle, "title");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Trailer)) {
				click(TabletLocators.Trailer, "Trailer");
				Thread.sleep(10000);
				driver.navigate().back();
			} else {
				ReporterLog.fail("Trailer Button", "Trailer Button is not displayed for" + title);
			}
			// Thread.sleep(2000);
			// MePageHelper.verifyVideoPlayback();

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentoptn)) {
				click(TabletLocators.rentoptn, "Rent option");
			} else {
				ReporterLog.fail("Rent option", "Rent option is not displayed for " + title);
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpurchasedes)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttc)) {
				ReporterLog.pass("rent TC Title", "rent TC Title is displayed");
			} else {
				ReporterLog.fail("rent TC Title", "rent TC Title is not displayed");
			}
			// click(TabletLocators.tktpurchase,"ticket purchase");
			click(TabletLocators.ticketconfirm, "Confirm Option");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ticketredeem)) {
				ReporterLog.pass("ticket redeemed Title", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticket redeemed Title", "ticket redeemed Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ticketredeeminfo)) {
				ReporterLog.pass("ticketredeeminfo ", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticketredeeminfo", "ticket redeemed info is not displayed");
			}
			click(TabletLocators.watchnow, "watch now option");
			verifyVideoPlayback();
			// return true;
			// driver.navigate().back(); //close keyboard

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Rent_onboarding_Lapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Rent_onboarding_Lapse(String description) {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.latestmvtext)) {
				ReporterLog.pass("Latest movie text available", "Latest movie available text is displayed");
			} else {
				ReporterLog.fail("Latest movie text available", "Latest movie available text is not displayed");
			}

			MobileElement eleDesc = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/dialogContentLayout"));
			List<MobileElement> eleListDesc = eleDesc.findElements(By.className("android.widget.TextView"));
			String strText = eleListDesc.get(0).getText();
			System.out.println(strText);
			if (strText.length() > 10) {
				ReporterLog.pass("ticketdesc Title", "ticketdesc title  is displayed");
			} else {
				ReporterLog.fail("ticketdesc Title", "ticketdesc title is not displayed");
			}
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.lapsedhooqClick, "First collection in  Rent page");
			String title = getText(TabletLocators.lapsedAssetTitle, "title");
			waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content");
			click(TabletLocators.lapsedAssetTitle, "first episode/movie from selected collection");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttvod)) {
				// click(TabletLocators.rentoptn,"Rent option");
				ReporterLog.pass("ticket TVOD", "ticket TVOD  is displayed");
			} else {
				ReporterLog.fail("Rent TVOD", "Rent option is not displayed for " + title);
			}

			click(TabletLocators.tktpurchase1, "ticket purchase");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsedesc)) {
				ReporterLog.pass("ticket redeemed Title", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticket redeemed Title", "ticket redeemed Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapsesubs)) {
				ReporterLog.pass("ticket info ", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticketredeeminfo", "ticket redeemed info is not displayed");
			}
			click(TabletLocators.lapsesubs, "lapse subscribe");
			Thread.sleep(10000);
			verifyPaymentlapse();

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Rentonboardingtab Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Rentonboardingtab(String description) {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.latestmvtext)) {
				ReporterLog.pass("Latest movie text available", "Latest movie available text is displayed");
			} else {
				ReporterLog.fail("Latest movie text available", "Latest movie available text is not displayed");
			}

			MobileElement eleDesc = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/msg2_container"));
			List<MobileElement> eleListDesc = eleDesc.findElements(By.className("android.widget.TextView"));
			String strText = eleListDesc.get(0).getText();
			System.out.println(strText);
			if (strText.length() > 10) {
				ReporterLog.pass("ticketdesc Title", "ticketdesc title  is displayed");
			} else {
				ReporterLog.fail("ticketdesc Title", "ticketdesc title is not displayed");
			}

			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}
			for (int i = 1; i <= 3; i++) {
				swipeUpOrBottom(true);
			}
			waitForInVisibilityOfElement(TabletLocators.genreList, "Rent page content");
			click(TabletLocators.activehooqClick, "First collection in  Rent page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.activeAssetTitle, title + " is from selected collection");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Trailer1)) {
				click(TabletLocators.Trailer1, "Trailer");
			} else {
				ReporterLog.fail("Trailer Button", "Trailer Button is not displayed for " + title);
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentoptntab)) {
				click(TabletLocators.rentoptntab, "Rent option");
			} else {
				ReporterLog.fail("Rent option", "Rent option is not displayed for " + title);
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpurchasedes)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttc)) {
				ReporterLog.pass("rent TC Title", "rent TC Title is displayed");
			} else {
				ReporterLog.fail("rent TC Title", "rent TC Title is not displayed");
			}
			click(TabletLocators.tktpurchase, "ticket purchase");
			click(TabletLocators.ticketconform, "ticket conform");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ticketredeem)) {
				ReporterLog.pass("ticket redeemed Title", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticket redeemed Title", "ticket redeemed Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ticketredeeminfo)) {
				ReporterLog.pass("ticketredeeminfo ", "ticket redeemed title  is displayed");
			} else {
				ReporterLog.fail("ticketredeeminfo", "ticket redeemed info is not displayed");
			}
			click(TabletLocators.watchnow, "watch now option");
			verifyVideoPlayback();
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- RentonboardingCC Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void RentonboardingCC(String description) {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.latestmvtext)) {
				ReporterLog.pass("Latest movie text available", "Latest movie available text is displayed");
			} else {
				ReporterLog.fail("Latest movie text available", "Latest movie available text is not displayed");
			}
			// click(TabletLocators.exploremovie,"Explore button");
			MobileElement eleDesc = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/dialogContentLayout"));
			List<MobileElement> eleListDesc = eleDesc.findElements(By.className("android.widget.TextView"));
			String strText = eleListDesc.get(0).getText();
			System.out.println(strText);
			if (strText.length() > 10) {
				ReporterLog.pass("ticketdesc Title", "ticketdesc title  is displayed");
			} else {
				ReporterLog.fail("ticketdesc Title", "ticketdesc title is not displayed");
			}
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}
			waitForInVisibilityOfElement(TabletLocators.genreList, "Browse page content");
			click(TabletLocators.lapsedhooqClick, "First collection in  Rent page");
			waitForInVisibilityOfElement(TabletLocators.genreListView1, "Browse page content");
			click(TabletLocators.lapsedAssetTitle, "first episode/movie from selected collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonrent)) {
				click(TabletLocators.anonrent, "Rent option");
			} else {
				ReporterLog.fail("Rent option", "Rent option is not displayed for ");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpurchasedes)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.myrentalstitle)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttc)) {
				ReporterLog.pass("rent TC Title", "rent TC Title is displayed");
			} else {
				ReporterLog.fail("rent TC Title", "rent TC Title is not displayed");
			}
			// if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.tvodcc)){
			// Reporter.pass("Rent Country currency ", "Rent Country currency is Visible");
			// } else{
			// Reporter.fail("Rent Country currency", "Rent Country currency is not
			// Visible");
			// }

			click(TabletLocators.tvodConform, "Credit Card purchase");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpayment)) {
				ReporterLog.pass("rent Payment header Title", "rent Payment header Title is displayed");
			} else {
				ReporterLog.fail("rent Payment header Title", "rent Payment header Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ccmovie)) {
				ReporterLog.pass("Purchase Movie Title", "Purchase Movie Title is displayed");
			} else {
				ReporterLog.fail("Purchase Movie Title", "Purchase Movie Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phpprice)) {
				ReporterLog.pass("Purchase Movie PHP Cost", "Purchase Movie PHP Cost is displayed");
			} else {
				ReporterLog.fail("Purchase Movie PHP Cost", "Purchase Movie PHP Cost is not displayed");
			}

			click(TabletLocators.ccradio, "Credit Card purchase option");
			click(TabletLocators.rentmakepayment, "Rent Make Payment");
			Thread.sleep(10000);

			// Credit card
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			if (strSource.contains("Expiry Date")) {
				ReporterLog.pass("Expiry Date Option ", "Expiry Date Option is displayed successfully");
			} else {
				ReporterLog.fail("Expiry Date Option", "Expiry Date Option is not displayed successfully");
			}
			// click(TabletLocators.paymentback,"Payment Back Button is clicked");

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveRent_Sanity1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */

	public static void ActiveRent_Sanity1(String description, String strColumn) {
		try {
			click(TabletLocators.premium, "Premium page");
			Thread.sleep(1000);
			// click(TabletLocators.exploremovie,"Explore Rental Movies");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.premiumheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}

			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/collectionItem"));
			eleEpisode.get(0);
			List<MobileElement> eleList = eleMobile.findElements(By.id("tv.hooq.android:id/rental"));
			if (eleList.size() > 0) {
				eleList.get(0);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
					ReporterLog.pass("Rent collection TVOD Flag",
							"Rent collection TVOD Flag is displayed for First collection");
				} else {
					ReporterLog.fail("Rent collection TVOD Flag",
							"Rent collection TVOD Flag is not displayed First collection");
				}

				eleEpisode.get(1);
				if (eleList.size() > 0) {
					eleList.get(1);
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
						ReporterLog.pass("Rent collection TVOD Flag",
								"Rent collection TVOD Flag is displayed Second collection");
					} else {
						ReporterLog.fail("Rent collection TVOD Flag",
								"Rent collection TVOD Flag is not displayed Second collection");
					}

				}
				click(TabletLocators.firstCollection1, "First collection");
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollheader)) {
					ReporterLog.pass("Rent collection Header available", "Rent collection Header is displayed");
				} else {
					ReporterLog.fail("Rent collection Header available", "Rent collection Header is not displayed");
				}
			}
			Thread.sleep(1000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
				ReporterLog.pass("Rent collection TVOD Flag", "Rent collection TVOD Flag is displayed collection");
			} else {
				ReporterLog.fail("Rent collection TVOD Flag", "Rent collection TVOD Flag is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveRent Developed By :- Raja Reddy Date :- 23-May-2019
	 */

	public static void ActiveRent(String description) {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.latestmvtext)) {
				ReporterLog.pass("Latest movie text available", "Latest movie available text is displayed");
			} else {
				ReporterLog.fail("Latest movie text available", "Latest movie available text is not displayed");
			}

			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}

			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/collectionItem"));
			eleEpisode.get(0);
			List<MobileElement> eleList = eleMobile.findElements(By.id("tv.hooq.android:id/rental"));
			if (eleList.size() > 0) {
				eleList.get(0);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
					ReporterLog.pass("Rent collection TVOD Flag",
							"Rent collection TVOD Flag is displayed for First collection");
				} else {
					ReporterLog.fail("Rent collection TVOD Flag",
							"Rent collection TVOD Flag is not displayed First collection");
				}

				eleEpisode.get(1);
				if (eleList.size() > 0) {
					eleList.get(1);
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
						ReporterLog.pass("Rent collection TVOD Flag",
								"Rent collection TVOD Flag is displayed Second collection");
					} else {
						ReporterLog.fail("Rent collection TVOD Flag",
								"Rent collection TVOD Flag is not displayed Second collection");
					}

				}
				click(TabletLocators.firstCollection1, "First collection");
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollheader)) {
					ReporterLog.pass("Rent collection Header available", "Rent collection Header is displayed");
				} else {
					ReporterLog.fail("Rent collection Header available", "Rent collection Header is not displayed");
				}
			}
			Thread.sleep(1000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
				ReporterLog.pass("Rent collection TVOD Flag", "Rent collection TVOD Flag is displayed collection");
			} else {
				ReporterLog.fail("Rent collection TVOD Flag", "Rent collection TVOD Flag is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveRent_Sanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveRent_Sanity(String description) {
		try {
			click(TabletLocators.premium, "Premium page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.premiumheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}

			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.id("tv.hooq.android:id/collectionItem"));
			eleEpisode.get(0);
			List<MobileElement> eleList = eleMobile.findElements(By.id("tv.hooq.android:id/rental"));
			if (eleList.size() > 0) {
				eleList.get(0);
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
					ReporterLog.pass("Rent collection TVOD Flag",
							"Rent collection TVOD Flag is displayed for First collection");
				} else {
					ReporterLog.fail("Rent collection TVOD Flag",
							"Rent collection TVOD Flag is not displayed First collection");
				}

				eleEpisode.get(1);
				if (eleList.size() > 0) {
					eleList.get(1);
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
						ReporterLog.pass("Rent collection TVOD Flag",
								"Rent collection TVOD Flag is displayed Second collection");
					} else {
						ReporterLog.fail("Rent collection TVOD Flag",
								"Rent collection TVOD Flag is not displayed Second collection");
					}

				}
				click(TabletLocators.firstCollection1, "First collection");
				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollheader)) {
					ReporterLog.pass("Rent collection Header available", "Rent collection Header is displayed");
				} else {
					ReporterLog.fail("Rent collection Header available", "Rent collection Header is not displayed");
				}
			}
			Thread.sleep(1000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
				ReporterLog.pass("Rent collection TVOD Flag", "Rent collection TVOD Flag is displayed collection");
			} else {
				ReporterLog.fail("Rent collection TVOD Flag", "Rent collection TVOD Flag is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- RentonboardingtabCC Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void RentonboardingtabCC(String description) {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.latestmvtext)) {
				ReporterLog.pass("Latest movie text available", "Latest movie available text is displayed");
			} else {
				ReporterLog.fail("Latest movie text available", "Latest movie available text is not displayed");
			}

			MobileElement eleDesc = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/msg2_container"));
			List<MobileElement> eleListDesc = eleDesc.findElements(By.className("android.widget.TextView"));
			String strText = eleListDesc.get(0).getText();
			System.out.println(strText);
			if (strText.length() > 10) {
				ReporterLog.pass("ticketdesc Title", "ticketdesc title  is displayed");
			} else {
				ReporterLog.fail("ticketdesc Title", "ticketdesc title is not displayed");
			}

			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentheader)) {
				ReporterLog.pass("rentheader Title", "rentheader title  is displayed");
			} else {
				ReporterLog.fail("rentheader Title", "rentheader title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketdesc)) {
				ReporterLog.pass("rentticketdesc Title", "rentticketdesc title  is displayed");
			} else {
				ReporterLog.fail("rentticketdesc Title", "rentticketdesc title is not displayed");
			}
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			waitForInVisibilityOfElement(TabletLocators.genreList, "Rent page content");
			click(TabletLocators.activehooqClick, "First collection in  Rent page");
			String title = getText(TabletLocators.activeAssetTitle, "title");
			click(TabletLocators.activeAssetTitle, title + " is from selected collection");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Trailer1)) {
				ReporterLog.pass("Trailer Button", "Trailer Button  is displayed");
			} else {
				ReporterLog.fail("Trailer Button", "Trailer Button is not displayed for " + title);
			}
			// Thread.sleep(2000);
			// MePageHelper.verifyVideoPlayback();

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentoptntab)) {
				click(TabletLocators.rentoptntab, "Rent option");
			} else {
				ReporterLog.fail("Rent option", "Rent option is not displayed for " + title);
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpurchasedes)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpurchasedes)) {
				ReporterLog.pass("rent ticket purchase desc Title", "rent ticket purchase desc Title is displayed");
			} else {
				ReporterLog.fail("rent ticket purchase desc Title", "rent ticket purchase desc Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttc)) {
				ReporterLog.pass("rent TC Title", "rent TC Title is displayed");
			} else {
				ReporterLog.fail("rent TC Title", "rent TC Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.tvodcc)) {
				ReporterLog.pass("Rent Country currency ", "Rent Country currency is Visible");
			} else {
				ReporterLog.fail("Rent Country currency", "Rent Country currency is not Visible");
			}

			click(TabletLocators.tvodcc, "Credit Card purchase");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentpayment)) {
				ReporterLog.pass("rent Payment header Title", "rent Payment header Title is displayed");
			} else {
				ReporterLog.fail("rent Payment header Title", "rent Payment header Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.ccmovie)) {
				ReporterLog.pass("Purchase Movie Title", "Purchase Movie Title is displayed");
			} else {
				ReporterLog.fail("Purchase Movie Title", "Purchase Movie Title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.phpprice)) {
				ReporterLog.pass("Purchase Movie PHP Cost", "Purchase Movie PHP Cost is displayed");
			} else {
				ReporterLog.fail("Purchase Movie PHP Cost", "Purchase Movie PHP Cost is not displayed");
			}

			click(TabletLocators.ccradio, "Credit Card purchase option");
			click(TabletLocators.rentmakepayment, "Rent Make Payment");
			Thread.sleep(10000);

			// Credit card
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Paymentmthd)) {
				ReporterLog.pass("Paymentmethod", " payment method is displayed");
			} else {
				ReporterLog.fail("Paymentmethod", " payment method is displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.paymentback)) {
				ReporterLog.pass("Payment Back Button", "Payment Back Button is displayed");
			} else {
				ReporterLog.fail("Payment Back Button", "Payment Back Button is not displayed");
			}
			String strSource = driver.getPageSource();
			if (strSource.contains("Expiry Date")) {
				ReporterLog.pass("Expiry Date Option ", "Expiry Date Option is displayed successfully");
			} else {
				ReporterLog.fail("Expiry Date Option", "Expiry Date Option is not displayed successfully");
			}
			// click(TabletLocators.paymentback,"Payment Back Button is clicked");

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- RentSubScripCheck Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void RentSubScripCheck(String description) {
		// Reporter.reportStep(description);
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentSubScripText)) {
				ReporterLog.pass("RentSubscriptionPage", "RentSubscriptionPage text is displayed");
			} else {
				ReporterLog.fail("RentSubscriptionPage", "RentSubscriptionPage text is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.subscriptiondays)) {
				ReporterLog.pass("SubscriptionDays", "SubscriptionDays left is displayed");
			} else {
				ReporterLog.fail("SubscriptionDays", "SubscriptionDays left is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentticketsText)) {
				ReporterLog.pass("rentticketsText", "rent ticket Text is displayed");
			} else {
				ReporterLog.fail("rent tickets Text", "rent ticket Text is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.nooftickets)) {
				ReporterLog.pass("no of tickets", "no of tickets is displayed");
			} else {
				ReporterLog.fail("no of tickets", "no of tickets is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Renttabvalidation Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Renttabvalidation() {
		try {
			click(TabletLocators.rentbtn, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			// Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttabtext)) {
				ReporterLog.pass("Rent Tab Text", "Rent Tab Text is displayed");
			} else {
				ReporterLog.fail("Rent Tab Text", "Rent Tab Text is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("rentcollection image", "rentcollection image is displayed");
			} else {
				ReporterLog.fail("rentcollection image", "rentcollection image is not displayed");
			}

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousRentvalidation Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousRentvalidation() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(9000);
			click(TabletLocators.rentbtn, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}
			click(TabletLocators.firstShowFromCollection, "First show from collection");

			click(TabletLocators.rentoptn1, "Rent option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}

			// if(isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonImage)){
			// Reporter.pass("Get Started Image", "Get started image is displayed");
			// }
			// else{
			// Reporter.fail("Get Started Image", "Get started image is not displayed");
			// }
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			driver.navigate().back(); // close keyboard
			driver.navigate().back();
			// Thread.sleep(1500);
			click(TabletLocators.discoverBtn, "Discover page");
			Thread.sleep(500);
			for (int i = 1; i <= 5; i++) {
				swipeUpOrBottom(true);

			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentflag)) {
				ReporterLog.pass("Rent flag image", "Rent flag image is visible");

			} else {
				ReporterLog.fail("Rent flag image", "Rent flag image is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentflag)) {
				ReporterLog.pass("Rent flag image", "Rent flag image is visible");

			} else {
				ReporterLog.fail("Rent flag image", "Rent flag image is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Rentposter)) {
				ReporterLog.pass("Discover rent option", "Discover rent option is visible");

			} else {
				ReporterLog.fail("Discover rent option", "Discover rent option is not visible");
			}
			click(TabletLocators.Rentposter, "Rent option");
			click(TabletLocators.rentoptn1, "Rent option");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousRentvalidation1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousRentvalidation1() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(10000);
			click(TabletLocators.rent, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}
			click(TabletLocators.firstShowFromCollection, "First show from collection");

			click(TabletLocators.anonrent, "Rent option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			driver.navigate().back(); // close keyboard
			// driver.navigate().back();

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousRentfav Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousRentfav() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(9000);
			click(TabletLocators.rent, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}
			click(TabletLocators.firstShowFromCollection, "First show from collection");
			click(TabletLocators.favoriteOption1, "Favorite");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymoustvodwatchlater Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymoustvodwatchlater() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(9000);
			click(TabletLocators.rent, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}
			click(TabletLocators.firstShowFromCollection, "First show from collection");
			click(TabletLocators.watchlater1, "Watch Later");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousRentFilter Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousRentFilter() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(20000);
			click(TabletLocators.rent, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}
			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is not Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is not Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Applyfilter)) {
				ReporterLog.pass("Apply Filter", "Apply Filter is Visible");
			} else {
				ReporterLog.fail("Apply Filter", "Apply Filter is not visible");
			}

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveRentFilter Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveRentFilter() {
		try {
			click(TabletLocators.rent, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}

			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is not Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is not Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Applyfilter)) {
				ReporterLog.pass("Apply Filter", "Apply Filter is Visible");
			} else {
				ReporterLog.fail("Apply Filter", "Apply Filter is not visible");
			}
			click(TabletLocators.Applyfilter, "Filter in  browse page");
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveRentFilter_Sanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveRentFilter_Sanity() {
		try {
			click(TabletLocators.premium, "Premium page");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentsect)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}

			click(TabletLocators.Filtericon, "Filter icon in  browse page");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterheader)) {
				ReporterLog.pass("Filter header", "Filter header is not Visible");
			} else {
				ReporterLog.fail("Filter header", "Filter header is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Filterreset)) {
				ReporterLog.pass("Filter Reset", "Filter Reset is not Visible");
			} else {
				ReporterLog.fail("Filter Reset", "Filter Reset is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Applyfilter)) {
				ReporterLog.pass("Apply Filter", "Apply Filter is Visible");
			} else {
				ReporterLog.fail("Apply Filter", "Apply Filter is not visible");
			}
			click(TabletLocators.Filterclose, "Filter close in  browse page");
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousRentvalidationtab Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void AnonymousRentvalidationtab() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(9000);
			click(TabletLocators.rentbtn, "Rent page");
			click(TabletLocators.exploremovie, "Explore Rental Movies");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentimg)) {
				ReporterLog.pass("Rent Tab image", "Rent Tab image is visible");

			} else {
				ReporterLog.fail("Rent Tab image", "Rent Tab image is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.renttabtext)) {
				ReporterLog.pass("Rent Tab Text", "Rent Tab Text is displayed");
			} else {
				ReporterLog.fail("Rent Tab Text", "Rent Tab Text is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection image", "Rentcollection image is displayed");
			} else {
				ReporterLog.fail("Rentcollection image", "Rentcollection image is not displayed");
			}
			click(TabletLocators.firstCollection, "First collection");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentcollectionimg)) {
				ReporterLog.pass("Rentcollection show image", "Rentcollectionshow image is displayed");
			} else {
				ReporterLog.fail("Rentcollection show image", "Rentcollectionshow image is not displayed");
			}
			click(TabletLocators.firstShowFromCollection, "First show from collection");
			click(TabletLocators.rentoptntab, "Rent option");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonImage)) {
				ReporterLog.pass("Get Started Image", "Get started image is displayed");
			} else {
				ReporterLog.fail("Get Started Image", "Get started image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription)) {
				String description = getText(TabletLocators.anonDescription, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.anonpaywallBtn, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			driver.navigate().back(); // close keyboard
			driver.navigate().back();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- addFavouriteWatchLater3 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static String  addFavouriteWatchLater3(boolean favourite, boolean watchList) {
		try {
			boolean flag = true;
			int count = 0;
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.videoLoader);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			while (flag && count < 4) {
				try {
					// MobileElement favouriteView = (MobileElement)
					// driver.findElement(TabletLocators.videoOptions);
					flag = false;
				} catch (Exception e) {
					swipeUpOrBottom(true);
				}
				count++;
			}
			for (int i = 1; i <= 0; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.discoverPageFirstShowMovie2, "First show/movie in  discover page");
			if (favourite) {
				String text = getText(TabletLocators.favoriteOption1, "Favorite");
				if (!text.equalsIgnoreCase("favorited")) {
					click(TabletLocators.favoriteOption1, "Favorite");
				} else
					ReporterLog.pass("Click", "The video is already in favorite list");
			}
			if (watchList) {
				click(TabletLocators.watchlist, "Watch List");
				Thread.sleep(2000);
			}
			// return ("Video title");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return ("Video title");
	}

	/***
	 * Function Name :- addFavouriteWatchLatertvod Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static String  addFavouriteWatchLatertvod(boolean favourite, boolean watchLater) {
		try {
			boolean flag = true;
			int count = 0;
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.videoLoader);
			while (flag && count < 4) {
				try {
					// MobileElement favouriteView = (MobileElement)
					// driver.findElement(TabletLocators.videoOptions);
					flag = false;
				} catch (Exception e) {
					swipeUpOrBottom(true);
				}
				count++;
			}
			for (int i = 1; i <= 0; i++) {
				swipeUpOrBottom(true);
			}

			// click(TabletLocators.videoOptions, "Video Options");
			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(1).click();
			List<MobileElement> eleList = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			if (eleList.size() > 0) {
				eleList.get(1).click();
			}
			// click(TabletLocators.discoverPageFirstShowMovieTablet,"First show/movie in
			// discover page");
			if (favourite) {
				String text = getText(TabletLocators.favoriteOption1, "Favorite");
				if (!text.equalsIgnoreCase("favorited")) {
					click(TabletLocators.favoriteOption1, "Favorite");
				} else
					ReporterLog.pass("Click", "The video is already in favorite list");
			}
			if (watchLater) {
				click(TabletLocators.watchlist, "Watch List");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return ("Video title");
	}

	/***
	 * Function Name :- addFavouriteWatchLater2 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static String  addFavouriteWatchLater2(boolean favourite, boolean watchLater) {
		try {
			boolean flag = true;
			int count = 0;
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.videoLoader);
			while (flag && count < 4) {
				try {
					// MobileElement favouriteView = (MobileElement)
					// driver.findElement(TabletLocators.videoOptions);
					flag = false;
				} catch (Exception e) {
					swipeUpOrBottom(true);
				}
				count++;
			}
			for (int i = 1; i <= 0; i++) {
				swipeUpOrBottom(true);
			}
			MobileElement eleMobile = (MobileElement) driver.findElement(By.id("tv.hooq.android:id/genreList"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			eleEpisode.get(2).click();
			List<MobileElement> eleList = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			if (eleList.size() > 0) {
				eleList.get(2).click();
			}
			// click(TabletLocators.discoverPageFirstShowMovie1,"First show/movie in
			// discover page");
			// click(TabletLocators.discoverPageFirstShowMovieTablet,"First show/movie in
			// discover page");
			if (favourite) {
				String text = getText(TabletLocators.favoriteOption1, "Favorite");
				if (!text.equalsIgnoreCase("favorited")) {
					click(TabletLocators.favoriteOption1, "Favorite");
				} else
					ReporterLog.pass("Click", "The video is already in favorite list");
			}
			if (watchLater) {
				click(TabletLocators.watchlist, "Watch List");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return ("Video title");
	}

	/***
	 * Function Name :- addFavouriteWatchLateractive Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static String  addFavouriteWatchLateractive(boolean favourite, boolean watchLater) {
		try {
			boolean flag = true;
			int count = 0;
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.videoLoader);
			while (flag && count < 4) {
				try {
					// MobileElement favouriteView = (MobileElement)
					// driver.findElement(TabletLocators.videoOptions);
					flag = false;
				} catch (Exception e) {
					swipeUpOrBottom(true);
				}
				count++;
			}
			for (int i = 1; i <= 0; i++) {
				swipeUpOrBottom(true);
			}
			if (favourite) {
				String text = getText(TabletLocators.favoriteOption1, "Favorite");
				if (!text.equalsIgnoreCase("favorited")) {
					click(TabletLocators.favoriteOption1, "Favorite");
				} else
					ReporterLog.pass("Click", "The video is already in favorite list");
			}
			if (watchLater) {
				String text = getText(TabletLocators.watchlist, "Watch list");
				if (!text.equalsIgnoreCase("Watch")) {
					click(TabletLocators.watchlist, "Watch List");
				} else
					ReporterLog.pass("Click", "The video is already in watch later list");
			}
			// return ("Video title");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return ("Video title");
	}

	/***
	 * Function Name :- clickLikeunlikeactive Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static String  clickLikeunlikeactive(boolean like, boolean unlike) {
		try {
			boolean flag = true;
			int count = 0;
			isNumOf_Elements_Greater_Than_Zero(TabletLocators.videoLoader);
			while (flag && count < 4) {
				try {
					// MobileElement favouriteView = (MobileElement)
					// driver.findElement(TabletLocators.videoOptions);
					flag = false;
				} catch (Exception e) {
					swipeUpOrBottom(true);
				}
				count++;
			}
			for (int i = 1; i <= 0; i++) {
				swipeUpOrBottom(true);
			}
			if (like) {
				String text = getText(TabletLocators.likeOption, "Like");
				if (!text.equalsIgnoreCase("Liked")) {
					ReporterLog.pass("Click", "The video is not liked");
					click(TabletLocators.likeOption, "Like");
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.unlike)) {
						ReporterLog.pass("Like", "Successfully liked the video");
					} else {
						ReporterLog.fail("Like", "Unable to like video");
					}

				} else {
					ReporterLog.fail("Click", "This  video is liked already");
				}
			}
			if (unlike) {
				String text = getText(TabletLocators.unlike, "Unlike");
				if (!text.equalsIgnoreCase("Like")) {
					ReporterLog.pass("Click", "The video is liked already");
					click(TabletLocators.unlike, "Unliked");
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.likeOption)) {
						ReporterLog.pass("unlike", "Successfully unliked the video");
					} else {
						ReporterLog.fail("unlike", "Unable to unlike the video");
					}

				}

				else {
					ReporterLog.fail("Click", "The video is not liked");
				}
			}

			// return ("Video title");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return ("Video title");
	}

	/***
	 * Function Name :- fnGetBuildNo Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static String  fnGetBuildNo() {
		if (ReadTestData.strBuildNo == null) {
			try {
				// Reporter.reportStep("Get HOOQ Build No");
				click(TabletLocators.meProfile1, "Me");
				Thread.sleep(2000);
				for (int i = 1; i <= 1; i++) {
					swipeUpOrBottom(true);
				}
				Thread.sleep(2000);
				click(TabletLocators.Support, "Support option is clicked");
				String strBuildNo = getText(TabletLocators.buildNo, "HOOQ Build No");
				Thread.sleep(2000);
				driver.navigate().back();
				driver.navigate().back();
				Thread.sleep(5000);
				click(TabletLocators.discoverBtn, "Discover page");
				Thread.sleep(5000);
				ReadTestData.strBuildNo = strBuildNo;
			} catch (Exception e) {
				TestUtilities.logReportFailure(e);
				// strBuildNo=LocalDateTime.now().toString();
			}
		}
		return ReadTestData.strBuildNo;
	}

	/***
	 * Function Name :- addFavouriteWatchLaterwatch Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static String  addFavouriteWatchLaterwatch(boolean favourite, boolean watchLater) {
		boolean flag = true;
		int count = 0;

		while (flag && count < 4) {
			try {
				flag = false;
			} catch (Exception e) {

			}
			count++;
		}
		if (favourite) {
			String text = getText(TabletLocators.favoriteswatchnow, "Favorite");
			if (!text.equalsIgnoreCase("favorited")) {
				click(TabletLocators.favoriteswatchnow, "Favorite");
			} else
				ReporterLog.pass("Click", "The video is already in favorite list");
		}
		if (watchLater) {
			click(TabletLocators.watchLater, "Watch Later");
		}
		return getText(TabletLocators.videoTitle, "Video title");
	}

	/***
	 * Function Name :- fnVerifyAppDisplayLanguagebahasa Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void fnVerifyAppDisplayLanguagebahasa(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.setcancel, "cancel");
			Thread.sleep(1000);
			// click(TabletLocators.meProfile, "Me");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyAppDisplayLanguagethai Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void fnVerifyAppDisplayLanguagethai(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.setcancel, "cancel");
			Thread.sleep(1000);
			// click(TabletLocators.meProfile, "Me");
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyAppDisplayLanguageEnglish Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static void fnVerifyAppDisplayLanguageEnglish(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.englishlanguage, "English");
			click(TabletLocators.deleteButton1, "Okay");
			Thread.sleep(5000);
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.appdisplaylanguage, "appdisplaylanguage");
			click(TabletLocators.Default, "Default");
			click(TabletLocators.deleteButton1, "Okay");
			Thread.sleep(2000);
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyAppsubtitlelan Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void fnVerifyAppmobiledatausage(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.settingsdatausage)) {
				ReporterLog.pass("Mobile Data Usage", "Mobile Data Usage is visible");

			} else {
				ReporterLog.fail("Mobile Data Usage", "Mobile Data Usage is not visible");
			}
			click(TabletLocators.settingsdatausage, "mobile data usage");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void fnVerifyAppsubtitlelan(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.settingssubtitle)) {
				ReporterLog.pass("Settings Subtitle Language", "Settings Subtitle Language is visible");

			} else {
				ReporterLog.fail("Settings Subtitle Language", "Settings Subtitle Language is not visible");
			}
			click(TabletLocators.settingssubtitle, "Settings Subtitle Language");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.settingssubtitleheader)) {
				ReporterLog.pass("Settings Subtitle Language Header", "Settings Subtitle Language Header is visible");

			} else {
				ReporterLog.fail("Settings Subtitle Language Header",
						"Settings Subtitle Language Header is not visible");
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyAppDownloadQuality Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void fnVerifyAppDownloadQuality(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.Downloadquality, "Downloadquality");
			click(TabletLocators.low, "Low");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.Downloadquality, "Downloadquality");
			click(TabletLocators.medium, "Medium");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.Downloadquality, "Downloadquality");
			click(TabletLocators.high, "High");
			click(TabletLocators.aboutusback1, "back");

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyPlayBackQuality Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void fnVerifyPlayBackQuality(String strAudioLanguage) {
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.playbackpopup, "playback quality");
			click(TabletLocators.low, "Low");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.playbackpopup, "playback quality");
			click(TabletLocators.medium, "Medium");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.playbackpopup, "playback quality");
			click(TabletLocators.high, "High");
			click(TabletLocators.aboutusback1, "back");
			Thread.sleep(4000);
			click(TabletLocators.settings, "settings");
			click(TabletLocators.playbackpopup, "playback quality");
			click(TabletLocators.auto, "Auto");
			click(TabletLocators.aboutusback1, "back");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnAudioLanguage Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void fnAudioLanguage() {
		try {
			List<String> objString = fnGetAudioLanguage();
			List<String> objAudioLanguageData = new ArrayList<String>();
			objAudioLanguageData.add("English");
			objAudioLanguageData.add("Assamese");
			objAudioLanguageData.add("Bahasa Indonesia");
			objAudioLanguageData.add("Bahasa Malaysia");
			objAudioLanguageData.add("Bengali");
			objAudioLanguageData.add("Bhojpuri");
			objAudioLanguageData.add("Chhattisgarhi");
			objAudioLanguageData.add("Gujarati");
			objAudioLanguageData.add("Japanese");
			objAudioLanguageData.add("Hindi");
			objAudioLanguageData.add("Kannada");
			objAudioLanguageData.add("Konkani");
			objAudioLanguageData.add("Korean");
			objAudioLanguageData.add("Marathi");
			objAudioLanguageData.add("Malayalam");
			objAudioLanguageData.add("Oriya");
			objAudioLanguageData.add("Punjabi");
			objAudioLanguageData.add("Sindhi");
			objAudioLanguageData.add("Tagalog");
			objAudioLanguageData.add("Tamil");
			objAudioLanguageData.add("Telugu");
			objAudioLanguageData.add("Thai");
			objAudioLanguageData.add("Mandarin");
			objAudioLanguageData.add("Cantonese");
			objAudioLanguageData.add("Hokkien");
			System.out.println("************************************************");
			System.out.println("Audio Language");
			for (int i = 0; i < objAudioLanguageData.size(); i++) {
				boolean blnStaus = false;
				for (int j = 0; j < objString.size(); j++) {
					if (objAudioLanguageData.get(i).equals(objString.get(j))) {
						blnStaus = true;
						break;
					}

				}
				if (blnStaus) {
					System.out.println(objAudioLanguageData.get(i) + " is displayed in Audio Language List");
				} else {
					System.out.println(objAudioLanguageData.get(i) + " is not display in Audio Language List");
				}
			}
			System.out.println("************************************************");
			System.out.println("Audio Language Change");
			for (int i = 0; i < objString.size(); i++) {
				boolean blnChange = fnSelectAudioLanguage(objString.get(i));
				if (blnChange) {
					boolean blnStatus = fnVerifyAudioLanguageSettings(objString.get(i));
					if (blnStatus) {
						System.out.println(objString.get(i) + " is changed in Audio Language List");
					} else {
						System.out.println(objString.get(i) + " is not change in Audio Language List");
					}
				}
			}
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- fnVerifyAudioLanguageSettings Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
	public static boolean fnVerifyAudioLanguageSettings(String strAudioLanguage) {

		click(TabletLocators.meProfile, "Me");
		click(TabletLocators.settings, "settings");
		List<WebElement> eleList = driver.findElement(TabletLocators.SettingsBlock)
				.findElements(By.className("android.widget.LinearLayout"));
		String strName = eleList.get(1).findElement(By.className("android.widget.RelativeLayout"))
				.findElement(By.id("android:id/summary")).getText();
		System.out.println(strName);
		if (strName.equals(strAudioLanguage)) {
			click(TabletLocators.aboutusback1, "aboutusback1");
			return true;
		}
		return false;
	}

	/***
	 * Function Name :- fnGetAudioLanguage Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static List<String> fnGetAudioLanguage() {
		List<String> objString = null;
		try {
			click(TabletLocators.meProfile, "Me");
			click(TabletLocators.settings, "settings");
			click(TabletLocators.audiolanguage, "audiolanguage");
			WebElement eleSettings = driver.findElement(By.id("tv.hooq.android:id/select_dialog_listview"));
			List<String> eleLanguage = new ArrayList<String>();
			List<WebElement> eleList = eleSettings.findElements(By.className("android.widget.CheckedTextView"));
			System.out.println(eleList.size());
			for (int i = 0; i < eleList.size(); i++) {
				String strName = eleList.get(i).getText();
				System.out.println(strName);
				eleLanguage.add(strName);
			}
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			List<WebElement> eleList1 = eleSettings.findElements(By.className("android.widget.CheckedTextView"));
			System.out.println(eleList1.size());
			for (int i = 0; i < eleList1.size(); i++) {
				String strName = eleList1.get(i).getText();
				System.out.println(strName);
				eleLanguage.add(strName);
			}
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			List<WebElement> eleList2 = eleSettings.findElements(By.className("android.widget.CheckedTextView"));
			System.out.println(eleList2.size());
			for (int i = 0; i < eleList2.size(); i++) {
				String strName = eleList2.get(i).getText();
				System.out.println(strName);
				eleLanguage.add(strName);
			}
			objString = fnGetListWithoutDuplicate(eleLanguage);
			click(TabletLocators.Cancel, "Cancel");
			click(TabletLocators.aboutusback1, "aboutusback1");
			// return objString;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return objString;
	}

	/***
	 * Function Name :- fnGetListWithoutDuplicate Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static List<String> fnGetListWithoutDuplicate(List<String> eleList) {
		List<String> eleListSort = new ArrayList<String>();

		try {

			if (eleList != null) {
				eleListSort.add(eleList.get(0));
				for (int i = 1; i < eleList.size(); i++) {
					boolean blnFound = false;
					String strData = eleList.get(i);
					for (int j = 0; j < eleListSort.size(); j++) {
						String strNewData = eleListSort.get(j);
						if (strData.equals(strNewData)) {
							blnFound = true;
						}
					}
					if (blnFound == false) {
						eleListSort.add(eleList.get(i));
					}
				}

			}
			// return eleListSort;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return eleListSort;
	}

	/***
	 * Function Name :- fnSelectAudioLanguage Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static boolean fnSelectAudioLanguage(String strAudioLanguage) {
		click(TabletLocators.meProfile, "Me");
		click(TabletLocators.settings, "settings");
		click(TabletLocators.audiolanguage, "audiolanguage");
		WebElement eleSettings = driver.findElement(By.id("tv.hooq.android:id/select_dialog_listview"));
		List<WebElement> eleList = eleSettings.findElements(By.className("android.widget.CheckedTextView"));
		System.out.println(eleList.size());
		for (int i = 0; i < eleList.size(); i++) {
			String strName = eleList.get(i).getText();
			System.out.println(strName);
			if (strAudioLanguage.equals(strName)) {
				eleList.get(i).click();
				click(TabletLocators.aboutusback1, "aboutusback1");
				return true;
			}
		}
		for (int i = 1; i <= 1; i++) {
			swipeUpOrBottom(true);
		}
		List<WebElement> eleList1 = eleSettings.findElements(By.className("android.widget.CheckedTextView"));
		System.out.println(eleList1.size());
		for (int i = 0; i < eleList1.size(); i++) {
			String strName = eleList1.get(i).getText();
			System.out.println(strName);
			if (strAudioLanguage.equals(strName)) {
				eleList.get(i).click();
				click(TabletLocators.aboutusback1, "aboutusback1");
				return true;
			}
		}
		for (int i = 1; i <= 1; i++) {
			swipeUpOrBottom(true);
		}
		List<WebElement> eleList2 = eleSettings.findElements(By.className("android.widget.CheckedTextView"));
		System.out.println(eleList2.size());
		for (int i = 0; i < eleList2.size(); i++) {
			String strName = eleList2.get(i).getText();
			System.out.println(strName);
			if (strAudioLanguage.equals(strName)) {
				eleList.get(i).click();
				click(TabletLocators.aboutusback1, "aboutusback1");
				return true;
			}
		}
		click(TabletLocators.Cancel, "Cancel");
		click(TabletLocators.aboutusback1, "aboutusback1");

		return false;
	}

	/***
	 * Function Name :- addFavouriteWatchLaterTab1 Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static String  addFavouriteWatchLaterTab1(boolean favourite, boolean watchLater) {
		try {
			click(TabletLocators.discoverPageFirstShowMovieTablet, "First show/movie in  discover page");
			if (favourite) {
				String text = getText(TabletLocators.favoriteOptiont, "Favorite");
				if (!text.equalsIgnoreCase("favorited")) {
					click(TabletLocators.favoriteOptiont, "Favorite");
				} else
					ReporterLog.pass("Click", "The video is already in favorite list");
			}
			if (watchLater) {
				click(TabletLocators.watchLater, "Watch Later");
			}
			click(TabletLocators.cancelBtn, "Cancel");
			swipeUpOrBottom(true);
			// return getText(TabletLocators.videoTitle, "Video title");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
		return getText(TabletLocators.videoTitle, "Video title");
	}

	/***
	 * Function Name :- clickOnTab Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void clickOnTab(String tabName) {
		try {
			if (tabName.equalsIgnoreCase("discover")) {
				click(TabletLocators.discoverBtn, "Discover");
			} else if (tabName.equalsIgnoreCase("browse")) {
				click(TabletLocators.browseBtnnew, "Browse");
			} else if (tabName.equalsIgnoreCase("me")) {
				click(TabletLocators.meProfile, "Me");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- clickOnTabtvod Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void clickOnTabtvod(String tabName) {
		try {
			if (tabName.equalsIgnoreCase("discover")) {
				click(TabletLocators.discoverBtn, "Discover");
			} else if (tabName.equalsIgnoreCase("rent")) {
				click(TabletLocators.rentbtn, "Rent");
				click(TabletLocators.exploremovie, "Explore Rental Movies");
				Thread.sleep(8000);

			} else if (tabName.equalsIgnoreCase("me")) {
				click(TabletLocators.meProfile, "Me");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- clickOnTabtvodn Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void clickOnTabtvodn(String tabName) {
		try {
			if (tabName.equalsIgnoreCase("discover")) {
				click(TabletLocators.discoverBtn, "Discover");
			} else if (tabName.equalsIgnoreCase("rent")) {
				click(TabletLocators.rentbtn, "Rent");
				click(TabletLocators.exploremovie, "Explore Rental Movies");
				Thread.sleep(8000);
				for (int i = 1; i <= 1; i++) {
					swipeUpOrBottom(true);
				}
			} else if (tabName.equalsIgnoreCase("me")) {
				click(TabletLocators.meProfile, "Me");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- clickOnTabtvod1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void clickOnTabtvod1(String tabName) {
		try {
			if (tabName.equalsIgnoreCase("discover")) {
				click(TabletLocators.discoverBtn, "Discover");
			} else if (tabName.equalsIgnoreCase("rent")) {
				click(TabletLocators.rentbtn, "Rent");
				// click(TabletLocators.exploremovie,"Explore Rental Movies");
				Thread.sleep(2000);
				for (int i = 1; i <= 1; i++) {
					swipeUpOrBottom(true);
				}
			} else if (tabName.equalsIgnoreCase("me")) {
				click(TabletLocators.meProfile, "Me");
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- removeVideo Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void removeVideo(String titleName) {
		try {
			click(TabletLocators.editButton, "Edit button");
			click(TabletLocators.videoTitle(titleName), "Video title");
			click(TabletLocators.removeButton, "Remove button");
			click(TabletLocators.deleteButton, "Delete button");
			waitForInVisibilityOfElement(TabletLocators.videoTitle(titleName), "Video title");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- checkEmpty Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void checkEmpty() {
		// boolean flag=false;
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateIcon)) {
				ReporterLog.pass("Favorites", "EmptyStateIcon is displayed");
			} else {
				ReporterLog.fail("Favorites", "EmptyStateIcon is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateTitle)) {
				ReporterLog.pass("Favorites", "Empty state title is displayed");
			} else {
				ReporterLog.fail("Favorites", "Empty state title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptySateDesc)) {
				ReporterLog.pass("Favorites", "Empty state desc is displayed");
			} else {
				ReporterLog.fail("Favorites", "Empty state desc is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Favorites", "Browse button is displayed");
			} else {
				ReporterLog.fail("Favorites", "Browse button is not displayed");
			}

			// flag=true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- back Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void back() {
		boolean flag = false;
		try {
			driver.navigate().back();
			Thread.sleep(1000);
			flag = true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		} finally {
			if (!flag) {
				ReporterLog.fail("Back", "Back button action not performed");
			} else {
				ReporterLog.pass("Back", "Back button action performed");
			}
		}
		// return flag;
	}

	/***
	 * Function Name :- waitForFavorites Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void waitForFavorites() {
		boolean flag = true;
		int count = 0;
		try {
			while (flag && count < 5) {
				count++;
				List<WebElement> element = driver.findElements(TabletLocators.favoritePageLoader);
				if (element.size() > 0) {
					break;
				} else
					Thread.sleep(1000);
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonContentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonContentPlayBack() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			click(TabletLocators.anonbtnSearchnew, "Browse button");
			Thread.sleep(2000);
			click(TabletLocators.anonhooqClick, "First collection in  browse page");
			Thread.sleep(2000);
			click(TabletLocators.anonassetTitle, "first episode/movie from selected collection");
			Thread.sleep(2000);
			// play button on selected video page
			click(TabletLocators.playButtonv, "Play button");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonContentDownloadmov Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonContentDownloadmov() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("salt", "1");
			// Download button on selected video page
			click(TabletLocators.downloadbtn, "Download button");
			Thread.sleep(2000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);
			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonContentDownloadTVShow Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonContentDownloadTVShow() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("Big Bang Theory", "1");
			// Download button on selected video page
			click(TabletLocators.downloadbtntv, "Download button");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);
			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonContentDownloadpremium Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void AnonContentDownloadpremium() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("jazbaa", "1");
			// Download button on selected video page
			click(TabletLocators.downloadbtn, "Download button");
			Thread.sleep(2000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- LapseCCSanity Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void LapseCCSanity(String description) {
		try {
			// Reporter.reportStep(description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Lapsetrial)) {
				ReporterLog.pass("Lapse Start Trial option", "Lapse Start Trial option is visible");

			} else {
				ReporterLog.fail("Lapse Start Trial option", "Lapse Start Trial option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapseott)) {
				ReporterLog.pass("Lapse Lets Go option", "Lapse Lets Go option is visible");
			} else {
				ReporterLog.fail("Lapse Lets Go option", "Lapse Lets Go option is not visible");
			}

			click(TabletLocators.lapseott, "Lets Go is clicked");
			Thread.sleep(6000);
			Paymentlapse();
			driver.navigate().back();
			for (int i = 1; i <= 3; i++) {
				swipeUpOrBottom(true);
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapseott1)) {
				ReporterLog.pass("Lapse Avail title option", "Lapse Avail title option is visible");
			} else {
				ReporterLog.fail("Lapse Avail title option", "Lapse Avail title option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapseclaim)) {
				ReporterLog.pass("Lapse Avail claim option", "Lapse Avail claim option is visible");
			} else {
				ReporterLog.fail("Lapse Avail claim option", "Lapse Avail claim option is not visible");
			}
			click(TabletLocators.lapseclaim, " Claim Now ");
			Thread.sleep(8000);
			Paymentlapse();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anondownloadsection Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anondownloadsection() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(4000);
			// Download button on selected video page
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.DownloadSection, "Downloads button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.DownloadsTitle)) {
				ReporterLog.pass("Downloads Title", "Downloads Title  is displayed");
			} else {
				ReporterLog.fail("Downloads Title", " Downloads Title is not displayed");
			}
			Thread.sleep(2500);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Downloads", "Browse button is displayed");
			} else {
				ReporterLog.fail("Downloads", "Browse button is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonFavsection Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonFavsection() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(4000);
			// Download button on selected video page
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Favorites", "Favorites Browse button is displayed");
			} else {
				ReporterLog.fail("Favorites", "Favorites Browse button is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonwatchlatersection Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonwatchlatersection() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(4000);
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.WatchLater, "watch later button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.WatchLaterTitle)) {
				ReporterLog.pass("Watchlater Title", "Watchlater title  is displayed");
			} else {
				ReporterLog.fail("Watchlater Title", "Watchlater title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Watchlater", "Favorites Browse button is displayed");
			} else {
				ReporterLog.fail("Watchlater", "Favorites Browse button is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonLinktv Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void AnonLinktv() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(4000);
			// Download button on selected video page
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			for (int i = 1; i <= 2; i++) {
				swipeUpOrBottom(true);
			}

			click(TabletLocators.FavSection, "Favorite button is displayed");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.FavTitle)) {
				ReporterLog.pass("Favorite Title", "FavTitle title  is displayed");
			} else {
				ReporterLog.fail("Favorite Title", "Favorite title is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("Favorites", "Favorites Browse button is displayed");
			} else {
				ReporterLog.fail("Favorites", "Favorites Browse button is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonHistorysection Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonHistorysection() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(4000);
			// Download button on selected video page
			click(TabletLocators.meProfile, "Me");
			Thread.sleep(2500);
			click(TabletLocators.HistorySection, "History button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.HistoryTitle)) {
				ReporterLog.pass("History Title", "History Title  is displayed");
			} else {
				ReporterLog.fail("History Title", " History Title is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.emptyStateButton)) {
				ReporterLog.pass("History", "History Browse button is displayed");
			} else {
				ReporterLog.fail("History", "History Browse button is not displayed");
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousFavMov Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousFavMov() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("American Hustle", "1");
			// Favorite button on selected video page
			click(TabletLocators.favoriteOption1, "Favorite");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousplayMov Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousplayMov() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("American Hustle", "1");
			// Favorite button on selected video page
			click(TabletLocators.Anonyplaybtn, "Paly button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveuserSearch Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveuserSearch() {
		try {
			SearchActive("Fri", "1");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult1)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult2)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}
			click(TabletLocators.searchresult2, "See All Button");
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveSearch_movie Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveSearch_movie() {
		try {
			SearchActive("Journey To The Center Of The Earth", "1");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Active_Trailer Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Active_Trailer() {
		try {
			SearchActive("American sniper", "1");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode.size());
			eleEpisode.get(0).click();

			Thread.sleep(2000);
			click(TabletLocators.playtrailer, "Trailer button");
			Thread.sleep(20000);
			driver.navigate().back();
			// BrowsePageHelper.fnVerifyBrightCovePlayer();
			// MePageHelper.verifyVideoPlaybacktrailer();
			Thread.sleep(2000);
			driver.navigate().back();
			driver.navigate().back();

			SearchActive("Risen", "1");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}
			MobileElement eleMobile1 = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode1 = eleMobile1.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode1.size());
			eleEpisode1.get(0).click();

			Thread.sleep(2000);
			click(TabletLocators.playtrailer, "Trailer button");
			Thread.sleep(20000);
			driver.navigate().back();
			// BrowsePageHelper.fnVerifyBrightCovePlayer();
			// MePageHelper.verifyVideoPlaybacktrailer();
			Thread.sleep(2000);

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Search_movieContent Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Search_movieContent() {
		try {
			MobileElement eleMobile = (MobileElement) driver
					.findElement(By.id("tv.hooq.android:id/matchedTitlesListView"));
			List<MobileElement> eleEpisode = eleMobile.findElements(By.className("android.widget.RelativeLayout"));
			System.out.println(eleEpisode.size());
			eleEpisode.get(0).click();

			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.content_detail)) {
				ReporterLog.pass("content_detail", "content_detail is displayed");
			} else {
				ReporterLog.fail("content_detail", "content_detail is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.content_poster)) {
				ReporterLog.pass("content_poster", "content_poster is displayed");
			} else {
				ReporterLog.fail("content_poster", "content_poster is not displayed");

				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.content_duration)) {
					ReporterLog.pass("content_duration", "content_duration is displayed");
				} else {
					ReporterLog.fail("content_duration", "content_duration is not displayed");
				}
			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveuserplayMovie Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveuserplayMovie() {
		try {
			// BrowsePageHelper.browseSpecificEpisodeActive("American Hustle","1");
			click(TabletLocators.playButton1, "Play button");
			Thread.sleep(20000);
			fnVerifyBrightCovePlayer();
			driver.navigate().back();
			// MePageHelper.verifyVideoPlayback1();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- ActiveSearch_Tvshow Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveSearch_Tvshow() {
		try {
			SearchActive("Arrow", "1");
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.searchresult)) {
				ReporterLog.pass("Search result match", "Search result match is displayed");
			} else {
				ReporterLog.fail("Search result match", "Search result match is not displayed");
			}

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- LapseuserplayMovie Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void LapseuserplayMovie() {
		try {
			browseSpecificEpisodeActive("American Hustle", "1");
			click(TabletLocators.lapplaybtn, "Play button");
			Thread.sleep(25000);
			verifyPaymentlapse();
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymousplaypremium Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymousplaypremium() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("Jazbaa", "1");
			// Favorite button on selected video page
			click(TabletLocators.Anonyplaybtn, "Paly button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousFavPremium Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousFavPremium() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("Jazbaa", "1");
			// Favorite button on selected video page
			click(TabletLocators.favoriteOption1, "Favorite");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymousFavTVShow Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymousFavTVShow() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("friends", "1");
			// Favorite button on selected video page
			click(TabletLocators.favoriteOption1, "Favorite");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymouswatchlaterMov Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymouswatchlaterMov() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("American Hustle", "1");
			// Watch Later button on selected video page
			click(TabletLocators.watchlater1, "Watch Later");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Get Started Button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonymouswatchlaterTVShow Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonymouswatchlaterTVShow() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("Friends", "1");
			// Watch Later button on selected video page
			click(TabletLocators.watchlater1, "Watch Later");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Get Started Button");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymouswatchlaterpremium Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void Anonymouswatchlaterpremium() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			Thread.sleep(2000);
			browseSpecificEpisodeActive("Jazbaa", "1");
			// Watch Later button on selected video page
			click(TabletLocators.watchlater1, "Watch Later");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription2)) {
				String description = getText(TabletLocators.anonDescription2, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Get Started Button");

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signuppage)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscovercontentPlayBack Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void DiscovercontentPlayBack(String description) {
		// Reporter.reportStep(description);
		try {
			for (int i = 1; i <= 3; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.discoverPageFirstShowMovie1, "First show/movie in  discover page");
			click(TabletLocators.playButton1, "Play button is clicked");
			Thread.sleep(20000);
			verifyPaymentlapse();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscoverSanity Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void DiscoverSanity(String description, String strColumn) {
		// Reporter.reportStep(description);
		try {
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/recycler_view"))) {
				MobileElement eleCollection = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/recycler_view"));
				List<MobileElement> eleList = eleCollection.findElements(By.id("tv.hooq.android:id/main_layout"));
				if (eleList.size() > 0) {
					System.out.println(eleList.size());
					eleList.get(0).click();
					driver.navigate().back();
				} else {
					ReportStatus.blnStatus = false;
				}
				if (eleList.size() > 0) {
					System.out.println(eleList.size());
					eleList.get(1).click();
					driver.navigate().back();
				} else {
					ReportStatus.blnStatus = false;
				}
				if (eleList.size() > 0) {
					System.out.println(eleList.size());
					eleList.get(2).click();
					driver.navigate().back();
				} else {
					ReportStatus.blnStatus = false;
				}
			} else {
				ReportStatus.blnStatus = false;
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.youwerewatching)) {
				ReporterLog.pass("Continue Watching Section", "Continue Watching Section is displayed");
			} else {
				ReporterLog.fail("Continue Watching Section", "Continue Watching Section is not displayed");
			}
			Thread.sleep(3000);
			if (isNumOf_Elements_Greater_Than_Zero(By.id("tv.hooq.android:id/recycler_view"))) {
				MobileElement eleCollection = (MobileElement) driver
						.findElement(By.id("tv.hooq.android:id/recycler_view"));
				List<MobileElement> eleList = eleCollection.findElements(By.id("tv.hooq.android:id/title_container"));
				if (eleList.size() > 0) {
					System.out.println(eleList.size());
					eleList.get(0).click();
					if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.cwcontent)) {
						ReporterLog.pass("CW Rail", "CW Rail is visible");

					} else {
						ReporterLog.fail("CW Rail", "CW Rail is not visible");
					}
					driver.navigate().back();
				}
			}
			for (int i = 1; i <= 3; i++) {
				swipeUpOrBottom(true);
			}

			click(TabletLocators.discoverPageFirstShowMovie2, "First show/movie in  discover page");
			click(TabletLocators.playButton1, "Play button is clicked");
			Thread.sleep(20000);
			fnVerifyBrightCovePlayer();
			driver.navigate().back();
			// MePageHelper.verifyVideoPlayback();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscovercontentLapse Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void DiscovercontentLapse(String description) {
		// Reporter.reportStep(description);
		try {
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Lapsetrial)) {
				ReporterLog.pass("Lapse Start Trial option", "Lapse Start Trial option is visible");

			} else {
				ReporterLog.fail("Lapse Start Trial option", "Lapse Start Trial option is not visible");
			}
			Thread.sleep(2000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Lapsecw)) {
				ReporterLog.pass("Lapse Continue Watching option", "Lapse Continue Watching option is visible");

			} else {
				ReporterLog.fail("Lapse Continue Watching option", "Lapse Continue Watching option is not visible");
			}
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);

			}
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscoverconciergecardLapse Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void DiscoverconciergecardLapse(String description) {
		try {
			// Reporter.reportStep(description);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Lapsetrial)) {
				ReporterLog.pass("Lapse Start Trial option", "Lapse Start Trial option is visible");

			} else {
				ReporterLog.fail("Lapse Start Trial option", "Lapse Start Trial option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapseott)) {
				ReporterLog.pass("Lapse Lets Go option", "Lapse Lets Go option is visible");
			} else {
				ReporterLog.fail("Lapse Lets Go option", "Lapse Lets Go option is not visible");
			}

			click(TabletLocators.lapseott, "Lets Go is clicked");
			Thread.sleep(6000);
			verifyPaymentlapse();
			driver.navigate().back();
			for (int i = 1; i <= 3; i++) {
				swipeUpOrBottom(true);
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapseott1)) {
				ReporterLog.pass("Lapse Avail title option", "Lapse Avail title option is visible");
			} else {
				ReporterLog.fail("Lapse Avail title option", "Lapse Avail title option is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.lapseclaim)) {
				ReporterLog.pass("Lapse Avail claim option", "Lapse Avail claim option is visible");
			} else {
				ReporterLog.fail("Lapse Avail claim option", "Lapse Avail claim option is not visible");
			}
			click(TabletLocators.lapseclaim, " Claim Now ");
			Thread.sleep(8000);
			verifyPaymentlapse();

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscovercontentPlayBackact Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void DiscovercontentPlayBackact(String description) {
		try {
			// Reporter.reportStep(description);
			for (int i = 1; i <= 4; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.discoverPageFirstShowMovie1, "First show/movie in  discover page");
			click(TabletLocators.playButton1, "Play button is clicked");
			Thread.sleep(2000);
			verifyVideoPlayback();
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscovercontentPlayBackact1 Developed By :- Raja Reddy Date
	 * :- 23-May-2019
	 */
	public static void DiscovercontentPlayBackact1(String description) {
		try {
			// Reporter.reportStep(description);
			for (int i = 1; i <= 3; i++) {
				swipeUpOrBottom(true);
			}
			click(TabletLocators.discoverPageFirstShowMovie2, "First show/movie in  discover page");
			click(TabletLocators.playButton1, "Play button is clicked");
			Thread.sleep(20000);
			fnVerifyBrightCovePlayer();
			driver.navigate().back();
			// MePageHelper.verifyVideoPlayback();
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- TVODcontentdiscover Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void TVODcontentdiscover(String description) {
		try {
			click(TabletLocators.discoverBtn, "Discover page");
			// Reporter.reportStep(description);
			for (int i = 1; i <= 1; i++) {
				swipeUpOrBottom(true);
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentflag)) {
				ReporterLog.pass("Rent flag image", "Rent flag image is visible");

			} else {
				ReporterLog.fail("Rent flag image", "Rent flag image is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.rentflag)) {
				ReporterLog.pass("Rent flag image", "Rent flag image is visible");

			} else {
				ReporterLog.fail("Rent flag image", "Rent flag image is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.trailer)) {
				ReporterLog.pass("Trailer option", "Trailer option is visible");

			} else {
				ReporterLog.fail("Trailer option", "Trailer option is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.discrent)) {
				ReporterLog.pass("Discover rent option", "Discover rent option is visible");

			} else {
				ReporterLog.fail("Discover rent option", "Discover rent option is not visible");
			}
			// MePageHelper.verifyVideoPlayback();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- GetStarted Developed By :- Raja Reddy Date :- 23-May-2019
	 */
	public static void GetStarted() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			click(TabletLocators.discoverBtn, "Discover btn");
			Thread.sleep(8000);
			click(TabletLocators.discoverPageFirstShowMovie1, "First show/movie in  discover page");
			click(TabletLocators.playButtonv, "Play button is clicked");
			Thread.sleep(500); // time to allow player to load

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription1)) {
				String description = getText(TabletLocators.anonDescription1, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.getstartednew, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- Anonymoussearch Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void Anonymoussearch() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(15000);
			browseSpecificEpisodeanony("Big Bang Theory", "1");
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- NewtoHooqSignup Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void NewtoHooqSignup() {
		try {
			click(TabletLocators.signup, "New to Hooq Sign up");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Signuptitle)) {
				ReporterLog.pass("Sign up Title", "Sign up Title is visible");
			} else {
				ReporterLog.fail("Sign up Title", "Sign up Title is not visible");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupmobdesc)) {
				ReporterLog.pass("Sign Up mobile description", "Sign Up mobile description is displayed");
			} else {
				ReporterLog.fail("Sign Up mobile description", "Sign Up mobile description is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Mobilecountrycode)) {
				ReporterLog.pass("Mobile Country code", "Mobile Country code is displayed");

			} else {
				ReporterLog.fail("Mobile Country code", "Mobile Country code is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupmobileinput)) {
				ReporterLog.pass("Signup Mobile input", "Signup Mobile input is displayed");

			} else {
				ReporterLog.fail("Signup Mobile input", "Signup Mobile input is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupdone)) {
				ReporterLog.pass("Signup Done Button", "Signup Done Button is displayed");

			} else {
				ReporterLog.fail("Signup Done Button", "Signup Done Button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupskip)) {
				ReporterLog.pass("Signup Skip ", "Signup Skip is displayed");

			} else {
				ReporterLog.fail("Signup Skip", "Signup Skip is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupalreadylogin)) {
				ReporterLog.pass("Signup Already login ", "Signup Already login is displayed");

			} else {
				ReporterLog.fail("Signup Already login", "Signup Already login is not displayed");
			}

			click(TabletLocators.signupskip, "Signup Skip");
			Thread.sleep(1000);
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupconformemail)) {
				ReporterLog.pass("Signup Conform email ", "Signup Conform email is displayed");

			} else {
				ReporterLog.fail("Signup Conform email", "Signup Conform email is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupemailtext)) {
				ReporterLog.pass("Signup Eamil Text ", "Signup Eamil Text is displayed");

			} else {
				ReporterLog.fail("Signup Eamil Text", "Signup Eamil Text is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.starttrial)) {
				ReporterLog.pass("Signup Start Trial option ", "Signup Start Trial option is displayed");

			} else {
				ReporterLog.fail("Signup Start Trial option", "Signup Start Trial option is not displayed");
			}

			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- NewtoHooqSignupTC Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void NewtoHooqSignupTC() {
		try {
			click(TabletLocators.Newtohooq, "New to Hooq Sign up");
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Signuptitle)) {
				ReporterLog.pass("Sign up Title", "Sign up Title is visible");

			} else {
				ReporterLog.fail("Sign up Title", "Sign up Title is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupmobdesc)) {
				ReporterLog.pass("Sign Up mobile description", "Sign Up mobile description is displayed");
			} else {
				ReporterLog.fail("Sign Up mobile description", "Sign Up mobile description is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Mobilecountrycode)) {
				ReporterLog.pass("Mobile Country code", "Mobile Country code is displayed");

			} else {
				ReporterLog.fail("Mobile Country code", "Mobile Country code is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupmobileinput)) {
				ReporterLog.pass("Signup Mobile input", "Signup Mobile input is displayed");

			} else {
				ReporterLog.fail("Signup Mobile input", "Signup Mobile input is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupdone)) {
				ReporterLog.pass("Signup Done Button", "Signup Done Button is displayed");

			} else {
				ReporterLog.fail("Signup Done Button", "Signup Done Button is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupskip)) {
				ReporterLog.pass("Signup Skip ", "Signup Skip is displayed");

			} else {
				ReporterLog.fail("Signup Skip", "Signup Skip is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.signupalreadylogin)) {
				ReporterLog.pass("Signup Already login ", "Signup Already login is displayed");

			} else {
				ReporterLog.fail("Signup Already login", "Signup Already login is not displayed");
			}
			Thread.sleep(2000);
			click(TabletLocators.signupTCPP, "Signup T&C");
			Thread.sleep(10000);
			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- AnonContentPlayBackTablet Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void AnonContentPlayBackTablet() {
		try {
			click(TabletLocators.anonskip, "Skip This Step");
			click(TabletLocators.anonbtnSearch, "Browse button");
			click(TabletLocators.anonhooqtabletClick, "First collection in  browse page");
			click(TabletLocators.anonassetTitle, "first episode/movie from selected collection");
			Thread.sleep(2000);
			// play button on selected video page
			click(TabletLocators.playButtonTab, "Play button");
			Thread.sleep(2000);

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.getStartedClose)) {
				ReporterLog.pass("Get Started page", "Close button is visible");

			} else {
				ReporterLog.fail("Get Started page", "Close button is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonImage)) {
				ReporterLog.pass("Get Started page", "Get started image is displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription)) {
				String description = getText(TabletLocators.anonDescription, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);

			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonpaywallBtn)) {
				ReporterLog.pass("Get Started page", "Get started button displayed");
			} else {
				ReporterLog.fail("Get Started page", "Get started button displayed");
			}
			click(TabletLocators.anonpaywallBtn, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- GetStartedTablet Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void GetStartedTablet() {
		try {
			click(TabletLocators.Sts, "Skip This Step");
			Thread.sleep(2000);
			click(TabletLocators.discoverBtn, "Discover btn");
			Thread.sleep(1000);
			click(TabletLocators.discoverPageFirstShowMovieTablet, "First show/movie in  discover page");
			Thread.sleep(1000);
			click(TabletLocators.playButtonTab, "Play button is clicked");
			Thread.sleep(500); // time to allow player to load

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.Getstr)) {
				ReporterLog.pass("Get Started", "Get Started button is visible");
			} else {
				ReporterLog.fail("Get Started page", "Get Started button is not visible");
			}

			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonImage)) {
				ReporterLog.pass("Get Started Image", "Get started image is displayed");
			} else {
				ReporterLog.fail("Get Started Image", "Get started image is not displayed");
			}
			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.anonDescription)) {
				String description = getText(TabletLocators.anonDescription, "Getstarted description");
				ReporterLog.pass("Get Started page", "Get started <b>description</b> is displayed:" + description);
			} else {
				ReporterLog.fail("Get Started page", "Get started <b>description</b> is not displayed");
			}
			click(TabletLocators.anonpaywallBtn, "Sign Up or Login");
			driver.navigate().back(); // close keyboard
			driver.navigate().back(); // to back from signIn up

			// return true;

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

	/***
	 * Function Name :- DiscovercontentPlayBacktablet Developed By :- Raja Reddy
	 * Date :- 23-May-2019
	 */
//	public static void DiscovercontentPlayBacktablet(String description) {
//		try {
//			// Reporter.reportStep(description);
//			click(TabletLocators.discoverPageFirstShowMovieTablet, "First show/movie in  discover page");
//			click(TabletLocators.playButtonTab, "Play button is clicked");
//			Thread.sleep(5000); // time to allow player to load
//			Dimension size = driver.manage().window().getSize();
//			int endy = (int) (size.height * 0.20);
//			int startx = size.width / 2;
//			boolean flag = true;
//			while (flag) {
//				Thread.sleep(100);
//				try {
//					((AndroidDriver<?>) driver).swipe(startx, endy, startx, endy, 3000);
//					driver.findElement(TabletLocators.activePlayTime);
//				} catch (Exception e) {
//					flag = true;
//				} finally {
//					if (flag)
//						break;
//				}
//			}
//			String beforePlay = null;
//			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activePlayTime)) {
//				beforePlay = getText(TabletLocators.activePlayTime, "PlayTime");
//				ReporterLog.pass("Playback", "Video streaming started and play time is :" + beforePlay);
//			} else {
//				((AndroidDriver<?>) driver).swipe(startx, endy, startx, endy, 3000); // to enable controls
//				beforePlay = getText(TabletLocators.activePlayTime, "PlayTime");
//				ReporterLog.pass("Playback", "Video streaming started and play time is :" + beforePlay);
//			}
//			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeClose)) {
//				ReporterLog.pass("Playback", "Close button is displayed");
//			} else {
//				((AndroidDriver<?>) driver).swipe(startx, endy, startx, endy, 3000); // to enable controls
//				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activeClose))
//					ReporterLog.pass("Playback", "Close button is displayed");
//				else
//					ReporterLog.fail("Playback", "Close button is not displayed");
//			}
//			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activebandWidthIndi)) {
//				ReporterLog.pass("Playback", "Active bandwidth content is displayed");
//			} else {
//				((AndroidDriver<?>) driver).swipe(startx, endy, startx, endy, 3000); // to enable controls
//				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activebandWidthIndi))
//					ReporterLog.pass("Playback", "Active bandwidth content is displayed");
//				else
//					ReporterLog.fail("Playback", "Active bandwidth content is not displayed");
//			}
//
//			Thread.sleep(20000);
//			ReporterLog.pass("Waiting to buffer Video", "Explict wait of 20 seconds is applied to buffer video");
//			((AndroidDriver<?>) driver).swipe(startx, endy, startx, endy, 3000); // to enable video controls
//
//			String afterPlay = null;
//			if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activePlayTime1)) {
//				afterPlay = getText(TabletLocators.activePlayTime, "PlayTime");
//				Thread.sleep(300);
//				ReporterLog.pass("Playback", "Video stream started and play time is :" + afterPlay);
//			} else {
//				((AndroidDriver<?>) driver).swipe(startx, endy, startx, endy, 3000); // to enable controls
//				if (isNumOf_Elements_Greater_Than_Zero(TabletLocators.activePlayTime1)) {
//					afterPlay = getText(TabletLocators.activePlayTime, "PlayTime");
//					ReporterLog.pass("Playback", "Video stream started and play time is :" + afterPlay);
//				} else {
//					ReporterLog.fail("Playback", "Playtime is not displayed");
//				}
//			}
//			// validation code
//			if (beforePlay.equals(afterPlay)) {
//				ReporterLog.fail("Playback", "Video is not playing");
//			} else {
//				ReporterLog.pass("Playback", "Video is playing");
//			}
//			verifyVideoPlayback();
//			// return true;
//
//		} catch (Exception e) {
//			TestUtilities.logReportFailure(e);
//			// blnStatus = false;
//		}
//	}

	/***
	 * Function Name :- ActiveuserplayMovie1 Developed By :- Raja Reddy Date :-
	 * 23-May-2019
	 */
	public static void ActiveuserplayMovie1() {
		try {
			// BrowsePageHelper.browseSpecificEpisodeActive("American Hustle","1");
			click(TabletLocators.playButton1, "Play button");
			Thread.sleep(2000);
			verifyVideoPlayback1();
			// return true;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
			// blnStatus = false;
		}
	}

}

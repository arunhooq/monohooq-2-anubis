package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import androidtv.utils.AndroidTVConstants;

public class ATVSearchPage extends ActionEngine {
	
	public static By search = By.id("tv.hooq.android:id/search");
	public static By typeKeyword = By.id("tv.hooq.android:id/search_editor");
	public static By firstList = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView");
	
	public static By title = By.id("tv.hooq.android:id/title");
	public static By movieInfo = By.id("tv.hooq.android:id/movie_info");
	public static By subtitle = By.id("tv.hooq.android:id/subtitle");
	public static By cast = By.id("tv.hooq.android:id/cast");
	public static By synopsis = By.id("tv.hooq.android:id/synopsis");
	public static By genres = By.id("tv.hooq.android:id/genres");
	
	public static By infoMessage = By.id("tv.hooq.android:id/message");
	
	public void searchSVOD() {
		try {
			click(search, "search");
			type(typeKeyword, AndroidTVConstants.TITLE_SVOD, "keyword");
			click(firstList, "firstList");
			waitTillElementPresent_HardWait_Polling(synopsis, GlobalConstant.WAIT_SHORT_10_SEC);
			
			getText(title, "titleName");
			getText(movieInfo, "movieInfo");
			getText(subtitle, "subtitleInfo");
			getText(cast, "castInfo");
			getText(synopsis, "synopsysInfo");
			getText(genres, "genresInfo");
			
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void searchTVOD() {
		try {
			pressKeyRemote("delete", 1);
			type(typeKeyword, AndroidTVConstants.TITLE_TVOD, "keyword");
			click(firstList, "firstList");
			waitTillElementPresent_HardWait_Polling(synopsis, GlobalConstant.WAIT_SHORT_10_SEC);
			
			getText(title, "titleName");
			getText(movieInfo, "movieInfo");
			getText(subtitle, "subtitleInfo");
			getText(cast, "castInfo");
			getText(synopsis, "synopsysInfo");
			getText(genres, "genresInfo");
			
			pressKeyRemote("back", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void searchNoResult() {
		try {
			pressKeyRemote("delete", 1);
			type(typeKeyword, AndroidTVConstants.TITLE_UNKNOWN, "keyword");
			getText(infoMessage, "infoMessage");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
}
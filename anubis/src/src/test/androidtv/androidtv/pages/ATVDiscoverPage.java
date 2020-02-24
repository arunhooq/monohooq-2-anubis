package androidtv.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import androidtv.utils.AndroidTVConstants;

public class ATVDiscoverPage extends ActionEngine {
	
	public static By toast = By.id("tv.hooq.android:id/toast_message");
	public static By spotlight = By.id("tv.hooq.android:id/info_container");
	public static By quicklinks = By.id("tv.hooq.android:id/title");
	public static By continueWatching = By.id("tv.hooq.android:id/bucket_title");
	public static By spotlightTitle = By.id("tv.hooq.android:id/carousel_title");

	public static String verifyToastMessage(String user, String region) {
		try {
			
			String email = "";
			
			if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_SG))) {
				email = AndroidTVConstants.EMAIL_ACTIVE_SG;
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_SG))) {
				email = AndroidTVConstants.EMAIL_LAPSED_SG;
				
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_ID))) {
				email = AndroidTVConstants.EMAIL_ACTIVE_ID;
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_ID))) {
				email = AndroidTVConstants.EMAIL_LAPSED_ID;
				
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))) {
				email = AndroidTVConstants.EMAIL_ACTIVE_IN;
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_IN))) {
				email = AndroidTVConstants.EMAIL_LAPSED_IN;
				
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_TH))) {
				email = AndroidTVConstants.EMAIL_ACTIVE_TH;
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_TH))) {
				email = AndroidTVConstants.EMAIL_LAPSED_TH;
				
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_ACTIVE)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_PH))) {
				email = AndroidTVConstants.EMAIL_ACTIVE_PH;
			} else if ((user.equalsIgnoreCase(GlobalConstant.USERTYPE_LAPSED)) && (region.equalsIgnoreCase(GlobalConstant.COUNTRY_PH))) {
				email = AndroidTVConstants.EMAIL_LAPSED_PH;
				
			} else {
				throw new Exception("Please assign an email.");
			}
			
			String signedInEmail = getText(toast, "toast");
			String signedInEmailSubstr = signedInEmail.substring(signedInEmail.lastIndexOf(' ') + 1, signedInEmail.length() - 1);
			verifyText(email, signedInEmailSubstr);
			
			ReporterLog.pass("Signed In", "Successfully Signed In with "+signedInEmail.substring(signedInEmail.lastIndexOf(' ') + 1, signedInEmail.length() - 1));

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return user;
	}
	
	public void verifySpotlight() {
		try {
			isElementDisplayed(spotlight);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifySpotlightTitle() {
		try {
			isElementDisplayed(spotlightTitle);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyQuicklinks() {
		try {
			isElementDisplayed(quicklinks);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public void verifyContinueWatching() {
		try {
			isElementDisplayed(continueWatching);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public ATVContentDetailsPage chooseSVODContent() {
		try {
			int randOne = randomNumber(4, 9);
			int randTwo = randomNumber(3, 6);
			pressKeyRemote("down", randOne);
			pressKeyRemote("right", randTwo);
			pressKeyRemote("center", 1);
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new ATVContentDetailsPage();
	}
	
}
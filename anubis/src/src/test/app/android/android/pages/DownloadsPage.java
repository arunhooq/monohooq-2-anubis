package android.pages;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class DownloadsPage extends ActionEngine {

	public static By downloadTitle = By.id("tv.hooq.android:id/toolbarTitle");
	public static By downloadCounttext = By.id("tv.hooq.android:id/esDownloadCounter");
	public static By btnDownloadEdit = By.id("tv.hooq.android:id/btnEdit");
	public static By btnDownloadRemove = By.id("tv.hooq.android:id/tvRemove");
	public static By btnDownloadCancel = By.id("tv.hooq.android:id/btnEdit");
	public static By btnDownloadBack = By.id("tv.hooq.android:id/btnUp");

	public static By txtdownloadMovie = By.id("tv.hooq.android:id/txtDownload");
	public static By imgDownloadICONMovie = By.id("tv.hooq.android:id/downloadIcon");
	public static By dialogBoxDownload = By.id("dialogContentLayout");
	public static By txtDownloadQualityTitle = By.id("tv.hooq.android:id/title");
	public static By txtLowQuality = By.id("quality_low");
	public static By txtMediumQuality = By.id("tv.hooq.android:id/quality_medium");
	public static By txtHighQuality = By.id("tv.hooq.android:id/quality_high");
	public static By checkboxSavePreferences = By.id("tv.hooq.android:id/pref_checkbox");
	public static By btnDownload_dialogueBox = By.id("tv.hooq.android:id/selection_done");
	public static By imgDownloadingProgress = By.id("tv.hooq.android:id/secondaryProgress");
	public static By dialogDownloadInProgress = By.id("tv.hooq.android:id/secondaryProgress");
	public static By txtTitleDownloadInProgress = By.id("tv.hooq.android:id/title");
	public static By btnPause_Resume_DownloadInProgress = By.id("tv.hooq.android:id/okay");
	public static By btnDeleteDownloadInProgress = By.id("tv.hooq.android:id/btnSecond");
	public static By btnConfirmDownloadInProgress = By.id("tv.hooq.android:id/okay");
	public static By txtConfirmDialogue = By.id("tv.hooq.android:id/title");

	public DownloadsPage getDownloadsTitle() {
		try {
			String strDownloadsTitle = getText(downloadTitle, "Downloads title");
			verifyText(strDownloadsTitle, "Downloads");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}


	public MePage clickDownloadsBack() {
		try {
			click(btnDownloadBack, "Downloads Back");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new MePage();
	}

	public DownloadsPage validateDownloadIconPresent() {

		try {
			if (isElementPresentEvenNotVisibleOnScreen(txtdownloadMovie)) {

				ReporterLog.pass("Content details page", "Download button is displayed");
			} else {
				ReporterLog.fail("Content details page", "Download button is not displayed");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();

	}

	public DownloadsPage validateDownloadTitleWhenDownloadStarted() {

		try {
			waitForElementClickable(txtdownloadMovie, "Download button");

			String strDownload = getText(txtdownloadMovie, "download movie button");

			if (strDownload.contentEquals("Downloading") || strDownload.contentEquals("Queued")) {

				ReporterLog.pass("Download ", "downloading started successfully");
			} else {
				ReporterLog.fail("Download ", "downloading didn't started.");
			}

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();

	}

	public DownloadsPage clickDownloadsTitle() {
		try {
			waitForElementClickable(txtdownloadMovie, "Download title on Content detail page");
			click(txtdownloadMovie, "download movie icon text");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage clickDownloadsTitleDownloadStartedorPaused() {
		try {
			waitForElementClickable(btnPause_Resume_DownloadInProgress, "Pause or Resume button");
			click(btnPause_Resume_DownloadInProgress, "download movie icon text");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage validateDownloadDialogueBox() {

		try {
			if (isElementPresentEvenNotVisibleOnScreen(dialogBoxDownload)) {

				ReporterLog.pass("Content details page", "Download button is displayed");
			} else {
				ReporterLog.fail("Content details page", "Download button is not displayed");
				throw new Exception();
			}

			String strDownloadQualityTitle = getText(txtDownloadQualityTitle, "Download Quality Title");

			verifyText(strDownloadQualityTitle, "Download Quality");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage validateVideoQuality() {
		try {
			isElementDisplayed(txtMediumQuality);
			isElementDisplayed(txtLowQuality);
			isElementDisplayed(txtHighQuality);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage UnCheckDownloadPreferences() {
		try {
			String checked = getAttribute(checkboxSavePreferences, "checked");

			if (checked.contains("true")) {
				ReporterLog.info("Download Quality DialogueBox preference",
						"Download dialogue box preference checkbox is checked");
				click(checkboxSavePreferences, "Save preferences Check");

				String afterChecked = getAttribute(checkboxSavePreferences, "checked");

				if (afterChecked.contains("false")) {
					ReporterLog.pass("Save My Preferences ", "Save My Preferences is working fine.");
				} else {
					ReporterLog.fail("Save My Preferences ", "Save My Preferences is not working fine.");
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage CheckDownloadPreferences() {
		try {
			String checked = getAttribute(checkboxSavePreferences, "checked");

			if (checked.contains("false")) {
				ReporterLog.info("Download Quality DialogueBox preference",
						"Download dialogue box preference checkbox is Unchecked");
				click(checkboxSavePreferences, "Save preferences Check");

				String afterChecked = getAttribute(checkboxSavePreferences, "checked");

				if (afterChecked.contains("true")) {
					ReporterLog.pass("Save My Preferences ", "Save My Preferences is working fine.");
				} else {
					ReporterLog.fail("Save My Preferences ", "Save My Preferences is not working fine.");
				}
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage clickDownloadButtonInDialogueBox() {
		try {
			waitForElementClickable(btnDownload_dialogueBox, "download button in dialogue box");
			click(btnDownload_dialogueBox, "Download button at Dialogue box");
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage validateResumeFunctionality() {
		try {
			click(btnPause_Resume_DownloadInProgress, "Pause or Resume Button is Clicked");

			waitForElementClickable(txtdownloadMovie, "Download button");

			String strDownload = getText(txtdownloadMovie, "download movie button");

			if (strDownload.contentEquals("Resume")) {

				ReporterLog.pass("Download ", "downloading Paused successfully");
			} else {
				ReporterLog.fail("Download ", "downloading didn't paused successfully.");
			}

			click(txtdownloadMovie, "Click resume button");

			waitForElementClickable(btnPause_Resume_DownloadInProgress, "Resume button");

			click(btnPause_Resume_DownloadInProgress, "Resume Button is Clicked");

			validateDownloadTitleWhenDownloadStarted();

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

	public DownloadsPage validateDeleteFunctionality() {
		try {
			click(txtdownloadMovie, " Download button");

			waitForElementClickable(btnDeleteDownloadInProgress, "Delete Button");

			click(btnDeleteDownloadInProgress, "Delete Button");

			waitForVisibilityOfElement(btnConfirmDownloadInProgress, "Confirm Button");

			verifyText(txtConfirmDialogue, "Are you sure you want to delete this download?");

			click(btnConfirmDownloadInProgress, "Confirm button");

			waitForVisibilityOfElement(txtdownloadMovie, "Download Button");

			verifyText(txtdownloadMovie, "Download");

		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new DownloadsPage();
	}

}

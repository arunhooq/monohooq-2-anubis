package ios.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;

public class SettingsPage extends ActionEngine {
	
	//Locators
	public static By navBackBtn = By.name("navbar back btn");
	
	
	/***
	 * Function Name :- naviagtetoBackFromSettings Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			click(navBackBtn, "Back From History page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	/***
	 * Function Name :- fnVerifySettingsPageAppDisplayLanguage Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppDisplayLanguage()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppDisplayLanguage Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppDisplayLanguage()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	
	/***
	 * Function Name :- fnVerifySettingsPageAppAudioTrack Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppAudioTrack()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppAudioTrack Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppAudioTrack()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnVerifySettingsPageAppSubTitle Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppSubTitle()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppSubTitle Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppSubTitle()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnVerifySettingsPageAppPlaybackQuality Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppPlaybackQuality()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppPlaybackQuality Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppPlaybackQuality()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnVerifySettingsPageAppDownloadquality Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppDownloadquality()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppDownloadquality Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppDownloadquality()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}
	
	/***
	 * Function Name :- fnVerifySettingsPageAppMobileDataUsage Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppMobileDataUsage()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppMobileDataUsage Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppMobileDataUsage()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}
	
	/***
	 * Function Name :- fnVerifySettingsPageAppParentalControls Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnVerifySettingsPageAppParentalControls()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();

	}
	
	/***
	 * Function Name :- fnChangeSettingsPageAppParentalControls Developed By :- Pankaj Kumar Date :-
	 * 23-May-2019
	 */
	public SettingsPage fnChangeSettingsPageAppParentalControls()  {
		try {
			
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return new SettingsPage();
	}	
}
package ios.tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedController;

public class ParentalControlPageTests extends TestConfiguration{

	//******************************************************************************************
	//Login with Valid User - Active
	//Get R21 Content by API 
	//Search R21 Content then > Content Details
	//Validate Contents using API (Title , audio , ,subtitles ,like)  
	//Set up initial Password - wrong password 
	//Set up correct password
	//Enter Incorrect password for watching
	//Enter Correct Password for watching.
	//Forgot My Password Scenario ( need email integration )
	//Validate movies play-back
	//******************************************************************************************

	@Test(description= "ParentalControl PIN Set Up E2E",priority = 1, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE, "ParentalControlPinSetUpE2E" })
	public void ParentalControlPinSetUpE2E() {
		try {
			ReadTestData.fnAddTestRailScriptID(22881);
			ReadTestData.R21_MOVIES = TestUtilities.getRandomItem(DiscoverFeedController.getSVODR21MovieList());
			searchPage.validateSearch(ReadTestData.R21_MOVIES);
			contentDetailsPage.verifyContentDetailsUsingAPI(ReadTestData.R21_MOVIES)
			.verifyManageSettingPopUp()
			.verifyParentalControlPage();

		}catch(Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(dependsOnMethods = {"ParentalControlPinSetUpE2E"},description= "Play Restricted Content with Correct and Incorrect PIN ",
			priority = 2, enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE, "PlayRestrictedContentWithCorrectAndIncorrectPIN" })
	public void PlayRestrictedContentWithCorrectAndIncorrectPIN() {
		try {

			ReadTestData.fnAddTestRailScriptID(22470);
			ReadTestData.R21_MOVIES = TestUtilities.getRandomItem(DiscoverFeedController.getSVODR21MovieList());
			searchPage.validateSearch(ReadTestData.R21_MOVIES);		
			//TODO -Play button
			contentDetailsPage.verifyWithIncorrectPIN()
			.verifyWithcorrectPIN()
			.verifyVideoPlayingSuccess();

		}catch(Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	@Test(dependsOnMethods = {"ParentalControlPinSetUpE2E"},description= "Reset PIN if Forgot",priority = 3, 
			enabled = true, groups = { GlobalConstant.GROUP_REGRESSION_ACTIVE, "ChangeParentalControlPINIfForgot" })
	public void ChangeParentalControlPINIfForgot() {
		try {

			ReadTestData.fnAddTestRailScriptID(22882);
			ReadTestData.R21_MOVIES = TestUtilities.getRandomItem(DiscoverFeedController.getSVODR21MovieList());
			searchPage.validateSearch(ReadTestData.R21_MOVIES);		
			//TODO --

		}catch(Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
}

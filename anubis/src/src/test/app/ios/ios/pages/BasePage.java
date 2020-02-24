package ios.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;

import ios.utils.IOSConstants;

public class BasePage extends ActionEngine {
	public static String strBuildNo = "";

	public enum DIRECTION {
		DOWN, UP, LEFT, RIGHT;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date dt = null;
	String beforelogin = null;
	String afterLogin = null;
	public static boolean FirstLogin = true;
	public static By meLabel = By.id("icon me");
	public static By logoutButton = By.name("Logout");
	public static By confirm = By.name("Confirm");
	public static By Login = By.name("Login");
	public static By btnGetStarted = By.xpath(".//XCUIElementTypeOther/XCUIElementTypeButton");
	public static By freemiumLogin = By.id("LOG IN");
	
	//Braze page
	public static By btnClose = By.xpath("//*[@name = '"+FileUtilities.strGetLocaleText(IOSConstants.BRAIZE_SIGNUP_TEXT)+"']");
	public static By btnCloseAdvertisement = By.id("Close Advertisement");
	public static By handlePopUp = By.name("Cancel");
	
	public LoginPage getLoginPage() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Login Started");
		try {
			LoginPage.fnHandleBrazePage();
			if (isNumOf_Elements_Greater_Than_Zero(meLabel)) {
				click(meLabel, "Me Icon");

				if (isNumOf_Elements_Greater_Than_Zero(logoutButton)) {
					click(logoutButton, "Logout Button");
					click(confirm, "Confirm");
				}
			}

			if (!ConfigDetails.country.equalsIgnoreCase("IN")) {
				Thread.sleep(10000);
				LoginPage.fnHandleBrazePage();
				if (isNumOf_Elements_Greater_Than_Zero(freemiumLogin)) {
					LoginPage.fnHandleBrazePage();
					click(freemiumLogin, "Login");
					}
			} else
				click(Login, "logInButton");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new LoginPage();
	}

	public static void fnHandleGetStartedButton() {
		try {
		Thread.sleep(10000);
		if (isNumOf_Elements_Greater_Than_Zero(BasePage.btnGetStarted)) {
			click(btnGetStarted, "Get Started Button");
			Thread.sleep(5000);
		}
		
		if(isNumOf_Elements_Greater_Than_Zero(SearchPage.btnCancel)) {
			click(SearchPage.btnCancel, "Cancel Button");
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

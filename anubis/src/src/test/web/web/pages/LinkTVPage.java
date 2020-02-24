package web.pages;

import org.json.JSONObject;
import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import web.data.WebSocketListener;

public class LinkTVPage extends ActionEngine {
	
	public static By tvcodeTextfield = By.cssSelector("input[name='tvcode']");
	public static By linkNowButton = By.cssSelector("button");
	public static By TVLinkedHeading = By.cssSelector(".PromptModalBase__ModalHeader-sc-1atbraf-2");
	public static By TVLinkedDescription = By.cssSelector(".PromptModalBase__ModalBody-sc-1atbraf-3");

	public static By okayButton = By.cssSelector(".Button-sc-1ik3oe9-2");
	public static By toastMessage = By.cssSelector("#e2e-message-popup");

	public static String getValidTVCode() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		String tvCode = "";
		try {
			
			String input = "{\"action\":\"request-key\",\"apiVersion\":\"2.0\",\"device\":{\"deviceId\":\"test-device-id-sg_1551756433_16967\",\"deviceModel\":\"webClient\"}}";
			JSONObject devicePairResponse = WebSocketListener.getResponseFromWebSocket("wss://api-prod.hooq.tv/1.1-alpha/device-pairing", input);
			JSONObject devicePairResponseData = (JSONObject) devicePairResponse.get("data");			
			tvCode = (String) devicePairResponseData.get("key");
			
			if(tvCode.equals(""))
				throw new Exception();
			
			TestUtilities.logReportPass("TVCode generated successfully: "+tvCode);

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return tvCode;
	}
	
	public LinkTVPage enterTVCode(String tvCode) {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			waitForElementClickable(tvcodeTextfield, "tvcodeTextfield");
			openUrl(ConfigDetails.strURL+"/mytv?key="+tvCode);
			waitForElementClickable(linkNowButton, "linkNowButton");
			TestUtilities.logReportPass("Entered LinkTV code successfully");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new LinkTVPage();
	}
	
	public LinkTVPage clickLinkNow() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			click(linkNowButton, "linkNowButton");
			TestUtilities.logReportPass("Clicked on LinkNow button successfully");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new LinkTVPage();
	}
	
	
	public LinkTVPage verifySuccessMessage() {

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			
			waitForVisibilityOfElement(TVLinkedHeading, "TVLinkedHeading");
			verifyText(TVLinkedHeading, ReadTestData.LINK_TV_CONFIRM);
			verifyText(TVLinkedDescription, ReadTestData.LINK_TV_SUCCESS);
			click(okayButton, "okayButton");
			
			TestUtilities.logReportPass("LinkTV success message shown as expected");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new LinkTVPage();
	}
	
	public LinkTVPage verifyFailureMessage() { 

		ReporterLog.info(Thread.currentThread().getStackTrace()[1].getMethodName(), "Start of Workflow");
		try {
			pollForText(toastMessage, ReadTestData.LINK_TV_FAILURE);
			TestUtilities.logReportPass("LinkTV failure message shown as expected");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return new LinkTVPage();
	}

}

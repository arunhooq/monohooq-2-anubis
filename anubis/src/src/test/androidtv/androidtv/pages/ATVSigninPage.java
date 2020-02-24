package androidtv.pages;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.openqa.selenium.By;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;

import androidtv.utils.AndroidTVConstants;
import web.data.WebSocketListener;

public class ATVSigninPage extends ActionEngine {

	public static By pin = By.id("tv.hooq.android:id/pin");

	public void verifyLoginPage() {
		try {
			isElementDisplayed(pin);			
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
	}
	
	public static String signinUsingLinkTV(String user, String region, String env, String pair) {	
		try {
			
			String pinCode = getText(pin, null);
			
			String email = "";
			String country = "";
			String urly = "";
			String pairing = "";
			
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
			
			if (region.equalsIgnoreCase(GlobalConstant.COUNTRY_SG)) {
				country = GlobalConstant.COUNTRY_SG;
			} else if (region.equalsIgnoreCase(GlobalConstant.COUNTRY_ID)) {
				country = GlobalConstant.COUNTRY_ID;
			} else if (region.equalsIgnoreCase(GlobalConstant.COUNTRY_TH)) {
				country = GlobalConstant.COUNTRY_TH;
			} else if (region.equalsIgnoreCase(GlobalConstant.COUNTRY_IN)) {
				country = GlobalConstant.COUNTRY_IN;
			} else if (region.equalsIgnoreCase(GlobalConstant.COUNTRY_PH)) {
				country = GlobalConstant.COUNTRY_PH;
			} else {
				throw new Exception("Please assign a region.");
			}
			
			if (env == AndroidTVConstants.PROD_ENV) {
				urly = AndroidTVConstants.SIGNIN_PROD;
			} else if (env == AndroidTVConstants.STAG_ENV) {
				urly = AndroidTVConstants.SIGNIN_NIGHTLY;
			} else {
				throw new Exception("Please assign an environment.");
			}
			
			String contentType = AndroidTVConstants.CON_TYPE;
			String deviceSignature = AndroidTVConstants.DEVICE_SIGNATURE;
			
			if (pair == AndroidTVConstants.PROD_ENV) {
				pairing = AndroidTVConstants.PAIRING_PROD;
			} else if (pair ==  AndroidTVConstants.STAG_ENV) {
				pairing = AndroidTVConstants.PAIRING_NIGHTLY;
			} else {
				throw new Exception("Please assign pairing environment.");
			}
			
			//Generate User Token
			URL obj = new URL(urly);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", contentType);
			con.setRequestProperty("device-signature", deviceSignature);

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes("{\"email\":\""+email+"\"}");
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			BufferedReader iny = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			
			while ((output = iny.readLine()) != null) {
				response.append(output);
			}
			
			iny.close();
			
			String jsonString = response.toString(); 
			JSONObject jsonObject = new JSONObject(jsonString);
			String token = (String) jsonObject.get("token");
			
			//Device Pairing using WebSocket
			String input = "{\"action\":\"validate-key\",\"key\":\""+pinCode+"\",\"credentials\":{\"email\":\""+email+"\",\"country\":\""+country+"\",\"token\":\""+token+"\"}}";
			WebSocketListener.getResponseFromWebSocket(pairing, input);
			
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return user;
	}
}
package sanctuary.utils;


import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import java.util.Properties;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.ini4j.Ini;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;


import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;



public class ReusableMethods {
	
	
	public static String  getJsonValueUsingPath(String strJson , String strJsonPath) {
        String data = JsonPath.read(strJson, strJsonPath);
        return data ;
    }
	
	public static XmlPath rawToXML(Response r)
	{
			
		String respon=r.asString();
		XmlPath x=new XmlPath(respon);
		return x;
		
	}
		
	public static JSONObject rawtoJsonObject(Response r) throws ParseException
	{
		String res = r.asString();
		JSONParser parser = new JSONParser();	
		JSONObject jsonObject = (JSONObject) parser.parse(res);
		return jsonObject;
	}
	
	
	public static int getRandomNumberInRange(int min, int max) {
		
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();
	}
	
	public static long getTimeStamp()
	{
	   
		 Date date= new Date();	 
		 long time = date.getTime();	 		 
		 return time;
	    
	}
	
    
	public static String fnGetConfigFileName() {
		String strConfigFile = "Default";
		try {
			String strTest = System.getProperty("configfile");
			if (strTest.equals("null") == false) {
				strConfigFile = strTest;
			}
		} catch (Exception e) {
		}
		return strConfigFile;
	}
	
	public static String fnGetCurrentUserDir() {
		String dir = System.getProperty("user.dir");
		dir = dir.replaceAll("\\\\", "/");
		//System.out.println("current dir = " + dir);
		return dir;
	}
	
	public static Properties fnGetPropertiesDetail(String strFileName) {
		Properties prop = new Properties();
		try {
			String dir = fnGetCurrentUserDir();
			strFileName = strFileName.replace(".properties", "");
			String strFilePath = dir + "/" + strFileName + ".properties";
			prop.load(new FileInputStream(strFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	public static void writeToFile(String fileName, String contents, String color) {
		
		if(fileName.equals("NA"))
			fileName = Thread.currentThread().getStackTrace()[2].getMethodName().toString();  			
        
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("./Reports/" + fileName + ".html", true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			if (color != null)
				//bw.write("<font color=\"" + color + "\">" + contents + "</font>");
				bw.write("<font color=\"" + color + "\">" + contents + "</font>");
			else
				bw.write(contents);
			bw.write("<br>");
			bw.newLine();
		} catch (IOException io) {
			Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
					+ io.getStackTrace()[0].getLineNumber() + " And Exception is", io);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException io) {
				Assert.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED, Line No: "
						+ io.getStackTrace()[0].getLineNumber() + " And Exception is", io);
			}
		}

	}
    
	public static String strReadIniFile(String strHeader, String strKeys) throws Exception {
		Ini ini = null;
		try {
			ini = new Ini(new File(System.getProperty("user.dir") + "/security.ini"));

		} catch (Exception e) {
			ReporterLog.warning("strReadIniFile", "Seems 'security.ini' file is not present in the current directory.");
			ReusableMethods.logReportFatal(e);
		}
		return ini.get(strHeader, strKeys);
	}
	
	public static void logReportFailure(Exception e, String... data) {
		String locatorName = "NA";
		String locator = "NA";
		if (data.length > 0) {
			locatorName = data[0];
			if (data[1] != null)
				locator = data[1];
		}

		ReporterLog.fail(Thread.currentThread().getStackTrace()[2].getMethodName(),
				"LocatorName: " + locatorName + "\n" + "Locator: " + locator + "\n" + "MethodName: "
						+ Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + "Line No: "
						+ e.getStackTrace()[0].getLineNumber() + "\n" + "And Exception is: " + e.toString());

	}

	public static void logReportFatal(Exception e, String... data) {
		String locatorName = "NA";
		String locator = "NA";
		if (data.length > 0) {
			locatorName = data[0];
			if (data[1] != null)
				locator = data[1];
		}

		ReporterLog.fatal(Thread.currentThread().getStackTrace()[2].getMethodName(),
				"LocatorName: " + locatorName + "\n" + "Locator: " + locator + "\n" + "MethodName: "
						+ Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + "Line No: "
						+ e.getStackTrace()[0].getLineNumber() + "\n" + "And Exception is: " + e.toString());

	}
	
	//generate HMAC for phantom login
	@SuppressWarnings("unchecked")
	public static String generateHMACforPhantomLogin(String email,String deviceId)
	{
		String hash = null;
	    try 
	    {
		     String secret = ApiConfigDetails.PL_KEY8;
		     
		     JSONObject payLoad = new JSONObject();
		     
		     payLoad.put("contactUserName", email);
		     payLoad.put("deviceId", deviceId);
		     		     
		     String message = payLoad.toString();
		     
		     // Get an algorithm instance
		     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		     
		     // Create secret key
		     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		     
		     // Assign secret key algorithm
		     sha256_HMAC.init(secret_key);
		     
		     // Generate Base64 encoded cipher string
		     hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		     System.out.println(hash);
		  }
		  catch (NoSuchAlgorithmException e) {
			   	 e.printStackTrace();} 
	      catch (InvalidKeyException e) {
			  	 e.printStackTrace();}
		  catch (Exception e){
		    	 e.printStackTrace();}
		
	    	return hash;
			  
		   }
	
	// generate HMAC for Singtelstb 
	@SuppressWarnings("unchecked")
	public static String generateHMACforSingtel(String titleid,String deviceSignature,String accountId)
	{
		String hash = null;
	    try 
	    {
		     String secret = ApiConfigDetails.SSTB_KEY4;
		     		  
		     String message = ""+titleid+":MSSS/PLAYREADY:"+deviceSignature+":"+accountId+"";
		     	    
		     // Get an algorithm instance
		     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		     
		     // Create secret key
		     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		     
		     // Assign secret key algorithm
		     sha256_HMAC.init(secret_key);
		     
		     // Generate Base64 encoded cipher string
		     hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		     System.out.println(hash);
		  }
		  catch (NoSuchAlgorithmException e) {
			   	 e.printStackTrace();} 
	      catch (InvalidKeyException e) {
			  	 e.printStackTrace();}
		  catch (Exception e){
		    	 e.printStackTrace();}
		
	    	return hash;
			  
		   }
	
	
	public static String getPlatformName(String strPlatformName) {
		String platform = "";
		switch (strPlatformName.toLowerCase()) {

		case "web":
			platform = "WEBCLIENT";
			break;
		case "ios":
		case "iphone":
			platform = "IOSMOBILE";
			break;
		case "android":
		case "tablet":
			platform = "ANDROIDMOBILE";
			break;
		case "TV":
			platform = "TV";
			break;
		}
		return platform;
	}

	public static String getUserAccessLevel(String strUserType) {
		String accesslevel = "";
		switch (strUserType.toLowerCase()) {

		case "active":
			accesslevel = "30";
			break;
		case "lapsed":
			accesslevel = "20";
			break;
		case "visitor":
			accesslevel = "10";
			break;
		}
		return accesslevel;
	}
	
	public static String getAPI(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}
	
	public static String decode_JWT(String JWT)
	{
        String[] split_string = JWT.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        String body = new String(base64Url.decode(base64EncodedBody));
        String signature = new String(base64Url.decode(base64EncodedSignature));

		return body;
	}

	public static JSONObject extractJWTBodytoJSON(String JWT) throws ParseException
	{
		JSONObject json = null;
		try
        {
            String body = decode_JWT(JWT);

            // Convert to JSON
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(body);
        }
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return json;
	}
	
	/**
	 * @author mdafsarali
	 * @description This method will be used for getting JSON value using key and it will ignore if no key/value present. 
	 * if value is null then it will return us empty string 
	 * @param childObj
	 * @param key
	 * @return
	 */
	public static Object getJSONValueIfKeyExists(JSONObject childObj, String key) {
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
    
}
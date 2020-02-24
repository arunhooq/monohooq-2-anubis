package com.automation.utilities;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.ini4j.Ini;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ActionEngine;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;

public class FileUtilities {

	/***
	 * Name of Function :- fnGetPropertiesDetail Developed By :- Pankaj Kumar
	 * (Cigniti Technologies) Date :- 15-May-2019 Function Description :- To Get the
	 * Property Details from File Name
	 * 
	 * @param strFileName
	 * @return Properties
	 */
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

	/***
	 * Name of Function :- fnGetCurrentUserDir Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Get the Current
	 * Directory Path
	 * 
	 * @return String
	 */
	public static String fnGetCurrentUserDir() {
		String dir = System.getProperty("user.dir");
		dir = dir.replaceAll("\\\\", "/");
		//System.out.println("current dir = " + dir);
		return dir;
	}

	public static void main(final String[] args) {
		System.out.println("Date Time Folder ");
		System.out.println(GetCurrentDateStamp());

		String strTest = System.getProperty("configfile");
		System.out.println("Config File Selected ==> " + strTest);

	}

	//Get current system time
	public static String GetCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	//Get current system time
	public static String GetCurrentDateStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	/***
	 * Name of Function :- createfolder Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Create a Folder
	 * 
	 * @param Path,folderName
	 * @return boolean
	 */
	public boolean createfolder(String Path, String folderName) {
		boolean blnStatus = false;
		try {
			File newFolder = new File(Path + "/" + folderName);
			blnStatus = newFolder.mkdirs();
			if (blnStatus) {
				System.out.println("Folder was created !");
			} else {
				System.out.println("Unable to create folder");
			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- DeleteFolder Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Delete a Folder
	 * 
	 * @param Path,folderName
	 * @return boolean
	 */
	public boolean DeleteFolder(String Path, String folderName) {
		boolean blnStatus = false;
		try {
			File file = new File(Path + "/" + folderName);
			if (!file.exists()) {
				System.out.println("File Do not Exist");
			} else {
				file.delete();
				System.out.println("Deleted file");
				blnStatus = true;
			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- RenameFolder Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Rename a Folder
	 * 
	 * @param Path,oldfolderName,newfolderName
	 * @return boolean
	 */
	public boolean RenameFolder(String Path, String oldfolderName, String newfolderName) {
		boolean blnStatus = false;
		try {
			File oldfile = new File(Path + "/" + oldfolderName);
			File newfile = new File(Path + "/" + newfolderName);
			if (!oldfile.exists()) {
				System.out.println("File Do not Exist");
			} else {
				oldfile.renameTo(newfile);
				System.out.println("File renamed");
				blnStatus = true;
			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- SearchFolder Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Search a Folder
	 * 
	 * @param Path,folderName
	 * @return boolean
	 */
	public boolean SearchFolder(String Path, String folderName) {
		boolean blnStatus = false;
		;
		try {
			File file = new File(Path + "/" + folderName);
			if (!file.exists()) {
				System.out.println("File Do not Exist");
			} else {
				System.out.println("File Exist : " + file);
				blnStatus = true;
			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- createTextFile Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Create a Text
	 * File
	 * 
	 * @param Path,folderName
	 * @return boolean
	 */
	public boolean createTextFile(String Path, String fileName) {
		boolean blnstatus = false;
		File newFile = new File(Path + "/" + fileName + (".txt"));
		try {
			if (newFile.createNewFile()) {
				System.out.println("File was created !");
				blnstatus = true;
			}
		} catch (IOException e) {
		}
		return blnstatus;
	}

	/***
	 * Name of Function :- DeleteTextFile Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Delete a Text
	 * File
	 * 
	 * @param Path,folderName
	 * @return boolean
	 */
	public boolean DeleteTextFile(String Path, String fileName) {
		boolean blnStatus = false;
		try {
			File newFile = new File(Path + "/" + fileName + (".txt"));
			if (!newFile.exists())
				System.out.println("File Do not Exist");
			else
				newFile.delete();
			System.out.println("Deleted file");
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- RenameTextFile Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Create a Text
	 * File
	 * 
	 * @param Path,oldfileName,newfileName
	 * @return boolean
	 */
	public boolean RenameTextFile(String Path, String oldfileName, String newfileName) {
		boolean blnStatus = false;
		try {
			// String Dirpath = (System.getProperty("user.dir"));
			File oldfile = new File(Path + "/" + oldfileName);
			File newfile = new File(Path + "/" + newfileName);
			if (!oldfile.exists())
				System.out.println("File Do not Exist");
			else
				oldfile.renameTo(newfile);
			System.out.println("File renamed");
			blnStatus = true;
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- CopyFolder Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Create a Text
	 * File
	 * 
	 * @param Path1,Path2,oldfileNamenewfileName
	 * @return boolean
	 */
	public boolean CopyFolder(String Path1, String oldfileName, String Path2, String newfileName) {
		boolean blnStatus = false;
		try {
			File oldfile = new File(Path1 + "/" + oldfileName);
			System.out.println(oldfile);
			File newfile = new File(Path2 + "/" + newfileName);
			System.out.println(newfile);
			try {
				FileUtils.copyDirectory(oldfile, newfile, DirectoryFileFilter.DIRECTORY);
				System.out.println("File Copied Successfully!!");
				blnStatus = true;
			} catch (IOException e) {
			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- writeInToAFile Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Write in a File
	 * 
	 * @param Path,fileName,data
	 * @return boolean
	 */
	public boolean writeInToAFile(String Path, String fileName, String data) {
		boolean blnStatus = false;
		try {
			// String Dirpath = (System.getProperty("user.dir"));
			FileWriter fw = new FileWriter(Path + "/" + fileName + (".txt"));
			fw.write(data);
			fw.close();
			System.out.println("Successfully wrote data in file");
			blnStatus = true;
		} catch (IOException e) {
		}
		return blnStatus;
	}

	/***
	 * Name of Function :- appendStrToFile Developed By :- Pankaj Kumar (Cigniti
	 * Technologies) Date :- 15-May-2019 Function Description :- To Write in a File
	 * 
	 * @param Path,fileName,data
	 * @return boolean
	 */
	public boolean appendStrToFile(String Path, String fileName, String data) {
		boolean blnStatus = false;
		try {
			// String Dirpath = (System.getProperty("user.dir"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path + "/" + fileName + (".txt"), true));
			writer.newLine();
			writer.write(data);
			writer.close();
			System.out.println("Successfully wrote data in file");
		} catch (IOException e) {
		}
		return blnStatus;
	}

	/**
	 * Write to file.
	 *
	 * @param fileName the file name
	 * @param contents the contents
	 * @param color the color
	 */
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

	public static String strReadIniFile(String strHeader, String strKeys){
		Ini ini = null;
		try {
			ini = new Ini(new File(System.getProperty("user.dir") + "/security.ini"));

		} catch (Exception e) {
			ReporterLog.fail("strReadIniFile", "Seems 'security.ini' file is not present in the current directory.");
			TestUtilities.logReportFatal(e);
		}
		return ini.get(strHeader, strKeys);
	}

	public static String strGetLocaleText(String strEnterKey){
		Ini ini = null;
		try {
			ini = new Ini(new File(System.getProperty("user.dir") + "/TestData/Locale_Data.ini"));
		} catch (Exception e) {
			ReporterLog.warning("strReadIniFile", "Seems 'Locale_Data.ini' file is not present in the current directory.");
			TestUtilities.logReportFatal(e);
		}
		return ini.get(ConfigDetails.country.toUpperCase(), strEnterKey);
	}

	public static String strGetRandomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		try {

			final String AB = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();

			for (int i = 0; i < len; i++) {
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			}

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return sb.toString();
	}

	public static String getIPAddress(String strCountry) {

		String ipAddress = "";
		try {
			switch (strCountry.toLowerCase()) {

			case "in":
				ipAddress = "165.231.253.108";
				break;
			case "sg":
				ipAddress = "118.200.236.168";
				break;
			case "ph":
				ipAddress = "14.102.168.0";
				break;
			case "id":
				ipAddress = "14.102.152.0";
				break;
			case "th":
				ipAddress = "14.128.8.0";
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return ipAddress;
	}

	public static String getChannelPartnerID(String strCountry) {

		String channelPartnerID = "";
		try {
			switch (strCountry.toLowerCase()) {

			case "in":
				channelPartnerID = "HOOQIND";
				break;
			case "sg":
				channelPartnerID = "HOOQSGP";
				break;
			case "ph":
				channelPartnerID = "GLOBE";
				break;
			case "id":
				channelPartnerID = "HOOQINDO";
				break;
			case "th":
				channelPartnerID = "AIS";
				break;
			}
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return channelPartnerID;
	}

	public static String getScreenshot(String strName) throws Exception {

		File directory = new File(System.getProperty("user.dir")+"/Reports/ScreenShots");
		if (! directory.exists()){
			directory.mkdir();
		}
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		File SrcFile=((TakesScreenshot)ActionEngine.driver).getScreenshotAs(OutputType.FILE);

		File DestFile=new File(directory+"/"+strName+"_"+dateName+".png");
		FileUtils.copyFile(SrcFile, DestFile);

		return DestFile.getPath();

	}
	
	public static String embedImageToTestNGReport(String imagePath) throws IOException {
		//Compressing image size to 0.25% 
		//FileUtilities.compressImage(imagePath);
		byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		
		String path = "<img src=\"data:image/png;base64, "+  encodedString +"\" width=\"300\" height=\"350\" />";
		Reporter.log(path);
		return encodedString;
	}

	public static String getPackageName(String project) {

		String packages ="";
		if(project.toLowerCase().contentEquals("ios")) {
			packages = "ios.tests";
		}else if(project.toLowerCase().contentEquals("android")) {
			packages = "android.tests";
		}else if(project.toLowerCase().contentEquals("web")) {
			packages = "web.tests";
		}else if(project.toLowerCase().contentEquals("androidtvv1")) {
			packages = "androidtv.tests";
		}else if(project.toLowerCase().contentEquals("androidtvv2")) {
			packages = "androidtv2.tests";
		}
		return packages;
	}

	public static List<String> readExcludeJSON(String project , String country) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("./ExcludeTest/"+project+".json"));

		JSONObject jsonObject = (JSONObject) obj;
		List<String> excludedtestList = new ArrayList<String>();
		JSONArray arr = (JSONArray) jsonObject.get(country);

		for(int i =0; i<arr.size() ;i++) {
			excludedtestList.add(arr.get(i).toString());

		}
		return excludedtestList;
	}

	public static void startRecordingScreen() {

		try{
			if(Boolean.parseBoolean(ConfigDetails.isVideoRecord)) {
				IOSStartScreenRecordingOptions iosOptions = new IOSStartScreenRecordingOptions();
				// The maximum value is 30 minutes.
				iosOptions.withTimeLimit(Duration.ofMinutes(30));
				iosOptions.withFps(10); // default 10
				iosOptions.withVideoQuality(IOSStartScreenRecordingOptions.VideoQuality.LOW);
				iosOptions.withVideoType("libx264");

				AndroidStartScreenRecordingOptions androidOptions = new AndroidStartScreenRecordingOptions();
				androidOptions.withTimeLimit(Duration.ofMinutes(30));
				androidOptions.enableBugReport(); // default 10


				if(ConfigDetails.project.equalsIgnoreCase("android") || ConfigDetails.strPlatformName.contains("android") || ConfigDetails.strPlatformName.contains("tablet")) {
					((AndroidDriver) ActionEngine.driver).startRecordingScreen(androidOptions);
				}else {
					((IOSDriver) ActionEngine.driver).startRecordingScreen(iosOptions);
				}
				System.out.println("Video Recording Started ......");
			}
		}catch(Exception e) {e.printStackTrace();}
	}

	public static String stopRecordingScreen() {
		String base64Video =null;
		try {
			if(Boolean.parseBoolean(ConfigDetails.isVideoRecord)) {

				if(ConfigDetails.project.equalsIgnoreCase("android") || ConfigDetails.strPlatformName.contains("android") || ConfigDetails.strPlatformName.contains("tablet")) {
					base64Video = ((AndroidDriver) ActionEngine.driver).stopRecordingScreen();
				}else {
					base64Video = ((IOSDriver) ActionEngine.driver).stopRecordingScreen();
				}
				System.out.println("Video Recording stopped ......");
			}
		}catch(Exception e) {e.printStackTrace();}
		return base64Video;
	}

	public static void base64toVideoFile(String base64Video ,String FileName) {
		if(Boolean.parseBoolean(ConfigDetails.isVideoRecord)) {
			try {
				File directory = new File(System.getProperty("user.dir")+"/Reports/VideoRecordings");
				if (! directory.exists()){
					directory.mkdir();
				} 
				File videoFile = new File(directory + "/" + FileName+".mp4");
				FileUtils.writeByteArrayToFile(videoFile, Base64.getDecoder().decode(base64Video), false);
				System.out.println("Video Saved successfully ....");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void compressImage(String infilPath) {
		try {
		File input = new File(infilPath);
	    BufferedImage image = ImageIO.read(input);

	    File compressedImageFile = new File(infilPath);
	    OutputStream os = new FileOutputStream(compressedImageFile);

	    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
	    ImageWriter writer = (ImageWriter) writers.next();

	    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	    writer.setOutput(ios);

	    ImageWriteParam param = writer.getDefaultWriteParam();

	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	    param.setCompressionQuality(0.25f);  // Change the quality value you prefer
	    writer.write(null, new IIOImage(image, null, null), param);

	    os.close();
	    ios.close();
	    writer.dispose();
		}catch(Exception e) {e.printStackTrace();}
	}

}

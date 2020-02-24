package com.automation.reports;

import java.io.File;
import java.nio.file.Files;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.FileUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ReportsExtent{
	public static ExtentReports extentReport = null;
	public static ExtentTest testReporter = null;
	public static String extentReportFileName = null;
	
	public static void fnCreateExtentReport(String Project,String country) {
		
		extentReportFileName = Project+"_"+country+"_Automation_Report.html";
		extentReport = new ExtentReports(FileUtilities.fnGetCurrentUserDir() + "/Reports/"+extentReportFileName);
		extentReport.loadConfig(new File("src/test/engine/com/automation/reports/extent_config.xml"));
		
	}

	public static void fnCloseExtentReports() {
		extentReport.flush();
	}

	public static void fnStartTest(String strTestName, String project, String suiteType) {

		testReporter = extentReport.startTest(strTestName,"Description : " + TestUtilities.fnGetTestDescription(project, suiteType).replaceAll("_", " "));
	}

	public static void fnEndTest(String strTestName) {
		
		File fileCheck = new File("./Reports/" + strTestName + ".html");
		
		if(fileCheck.exists())
			//ReporterLog.info("Test Info", TestUtilities.returnFileContentAsString("./Reports/" + strTestName + ".html"));
		
		extentReport.endTest(testReporter);
	}

	public static void fnCopyReportToHistory() {
		try {
			String strName = FileUtilities.GetCurrentTimeStamp();
			System.out.println(strName);
			strName = strName.replaceAll(":", "_").replaceAll("-", "_").replaceAll(" ", "_");
			System.out.println(strName);
			String strHistoryFolderName = FileUtilities.fnGetCurrentUserDir() + "/HistoryOfReports/" + strName;
			System.out.println(strHistoryFolderName);
			File newFolder = new File(strHistoryFolderName);
			String strSourcePath = FileUtilities.fnGetCurrentUserDir() + "/Reports/"+extentReportFileName;
			String strDestination = strHistoryFolderName + extentReportFileName;
			boolean blnStatus = newFolder.mkdirs();
			if (blnStatus) {
				System.out.println("Created Folder'");
				Files.copy(new File(strSourcePath).toPath(), new File(strDestination).toPath());
			} else {
				System.out.println("Folder not created");
			}
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}

	
}

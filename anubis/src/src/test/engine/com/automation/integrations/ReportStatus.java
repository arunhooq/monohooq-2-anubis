package com.automation.integrations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

public class ReportStatus {
	public static boolean blnStatus = true;
	public static String strUserType;
	public static String strCountry;
	public static String strBuild = null;

	public static void fnUpdateDashboard() {
		try {
			// Find the Row
			String spreadsheetId = ReadTestData.SHEET_ID;
			String strSheetName = ReadTestData.strGoogleSheet;

			// Get the Row Number
			int intRowNo = GoogleDriveAPI.fnGetRowNo(ReadTestData.strPlatform, ReadTestData.strDevice, ReadTestData.strScriptName,
					strSheetName, spreadsheetId);
			// Update Current Status
			String strStatus = "";
			boolean blnStatus = ReportStatus.blnStatus;
			if (blnStatus) {
				strStatus = "PASS";
			} else {
				strStatus = "FAIL";
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate localDate = LocalDate.now();
			String strDate = dtf.format(localDate);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String strTime = sdf.format(cal.getTime());
			// Update the Current Status
			String rangeStatus = strSheetName + "!G" + intRowNo + ":G" + intRowNo;
			GoogleDriveAPI.updateContentData(spreadsheetId, rangeStatus, strStatus);
			// Date Wise Update
			String strdateColumn = fnGetColumnName();
			String rangeStatusdate = strSheetName + "!" + strdateColumn + intRowNo + ":" + strdateColumn + intRowNo;
			GoogleDriveAPI.updateContentData(spreadsheetId, rangeStatusdate, strStatus);
			// Update Current Date
			String rangeCurrentDate = strSheetName + "!I" + intRowNo + ":I" + intRowNo;
			GoogleDriveAPI.updateContentData(spreadsheetId, rangeCurrentDate, strDate);
			// Update Current Time
			String rangeCurrentTime = strSheetName + "!J" + intRowNo + ":J" + intRowNo;
			GoogleDriveAPI.updateContentData(spreadsheetId, rangeCurrentTime, strTime);
			// Update Current Build No
			String rangeCurrentBuild = strSheetName + "!H" + intRowNo + ":H" + intRowNo;
			GoogleDriveAPI.updateContentData(spreadsheetId, rangeCurrentBuild, ReadTestData.strBuildNo);
			if (strStatus.equals("PASS")) {
				// Update Pass Count
				String rangePassCount = strSheetName + "!K" + intRowNo + ":K" + intRowNo;
				// Get the current Count of Pass
				String strPassCount = GoogleDriveAPI.fnGetData(spreadsheetId, rangePassCount);
				int CountPass = Integer.parseInt(strPassCount) + 1;
				String strUpdatePassCount = String.valueOf(CountPass);
				GoogleDriveAPI.updateContentData(spreadsheetId, rangePassCount, strUpdatePassCount);
				// Update Last Pass Date
				String rangePassDate = strSheetName + "!L" + intRowNo + ":L" + intRowNo;
				GoogleDriveAPI.updateContentData(spreadsheetId, rangePassDate, strDate);
				// Update Last Pass Time
				String rangePassTime = strSheetName + "!M" + intRowNo + ":M" + intRowNo;
				GoogleDriveAPI.updateContentData(spreadsheetId, rangePassTime, strTime);
				// Update Last Pass Build
				String rangePassBuild = strSheetName + "!N" + intRowNo + ":N" + intRowNo;
				GoogleDriveAPI.updateContentData(spreadsheetId, rangePassBuild, ReadTestData.strBuildNo);
			} else {
				// Update Fail Count
				String rangeFailCount = strSheetName + "!O" + intRowNo + ":O" + intRowNo;
				String strFailCount = GoogleDriveAPI.fnGetData(spreadsheetId, rangeFailCount);
				int CountFail = Integer.parseInt(strFailCount) + 1;
				String strUpdateFailCount = String.valueOf(CountFail);
				GoogleDriveAPI.updateContentData(spreadsheetId, rangeFailCount, strUpdateFailCount);
				// Update Last Fail Date
				String rangeFailDate = strSheetName + "!P" + intRowNo + ":P" + intRowNo;
				GoogleDriveAPI.updateContentData(spreadsheetId, rangeFailDate, strDate);
				// Update Last Fail Time
				String rangeFailTime = strSheetName + "!Q" + intRowNo + ":Q" + intRowNo;
				GoogleDriveAPI.updateContentData(spreadsheetId, rangeFailTime, strTime);
				// Update Last Fail Build
				String rangeFailBuild = strSheetName + "!R" + intRowNo + ":R" + intRowNo;
				GoogleDriveAPI.updateContentData(spreadsheetId, rangeFailBuild, ReadTestData.strBuildNo);
			}
			String strCol = fnGetColumnName();
			String dayBuildstatus = strSheetName + "!" + strCol + intRowNo + ":" + strCol + intRowNo;
			GoogleDriveAPI.updateContentData(spreadsheetId, dayBuildstatus, strStatus);
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	public static String fnGetColumnName() {
		String strColumn = "";
		try {
			int intDate = 0;
			// Get the Date No
			DateFormat dateFormat = new SimpleDateFormat("dd");
			Date date = new Date();
			System.out.println("Current Date ==> " + dateFormat.format(date));
			intDate = Integer.parseInt(dateFormat.format(date));
			if (intDate == 1) {
				strColumn = "S";
			} else if (intDate == 2) {
				strColumn = "T";
			} else if (intDate == 3) {
				strColumn = "U";
			} else if (intDate == 4) {
				strColumn = "V";
			} else if (intDate == 5) {
				strColumn = "W";
			} else if (intDate == 6) {
				strColumn = "X";
			} else if (intDate == 7) {
				strColumn = "Y";
			} else if (intDate == 8) {
				strColumn = "Z";
			} else if (intDate == 9) {
				strColumn = "AA";
			} else if (intDate == 10) {
				strColumn = "AB";
			} else if (intDate == 11) {
				strColumn = "AC";
			} else if (intDate == 12) {
				strColumn = "AD";
			} else if (intDate == 13) {
				strColumn = "AE";
			} else if (intDate == 14) {
				strColumn = "AF";
			} else if (intDate == 15) {
				strColumn = "AG";
			} else if (intDate == 16) {
				strColumn = "AH";
			} else if (intDate == 17) {
				strColumn = "AI";
			} else if (intDate == 18) {
				strColumn = "AJ";
			} else if (intDate == 19) {
				strColumn = "AK";
			} else if (intDate == 20) {
				strColumn = "AL";
			} else if (intDate == 21) {
				strColumn = "AM";
			} else if (intDate == 22) {
				strColumn = "AN";
			} else if (intDate == 23) {
				strColumn = "AO";
			} else if (intDate == 24) {
				strColumn = "AP";
			} else if (intDate == 25) {
				strColumn = "AQ";
			} else if (intDate == 26) {
				strColumn = "AR";
			} else if (intDate == 27) {
				strColumn = "AS";
			} else if (intDate == 28) {
				strColumn = "AT";
			} else if (intDate == 29) {
				strColumn = "AU";
			} else if (intDate == 30) {
				strColumn = "AV";
			} else if (intDate == 31) {
				strColumn = "AW";
			}
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return strColumn;
	}
}

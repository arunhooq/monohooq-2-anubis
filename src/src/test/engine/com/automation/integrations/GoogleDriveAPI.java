package com.automation.integrations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleDriveAPI {

	/** Application name. */
	private static final String APPLICATION_NAME = "Google Drive API";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	// Response Variable Declration
	public static ValueRange response;

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final java.util.Collection<String> SCOPES = DriveScopes.all();
	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		// String strPath=
		// "E:/HOOQ_WEB_NEW/HOOQ_WEB_API_TEST_Sanity/src/main/java/com/web/automation/googledrive/client_secret.json";
		String strPath = System.getProperty("user.dir")
				+ "/src/test/engine/com/automation/integrations/client_secret.json";

		InputStream in = new FileInputStream(strPath);
		// Quickstart.class.getResourceAsStream(strPath);
		// System.out.println(in);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		// System.out.println(
		// "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	/**
	 * Get the Google Drive Services
	 */

	public static Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Create the Google Drive Folder
	 */
	public static String fnCreateFolder() throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName("Invoices2");
		fileMetadata.setMimeType("application/vnd.google-apps.folder");
		Drive service = getDriveService();

		File file = service.files().create(fileMetadata).setFields("id").execute();
		// System.out.println("Folder ID: " + file.getId());
		return file.getId();
	}

	/**
	 * Create the Google Sheet
	 */
	public static String fnCreateGoogleSheet() throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName("Automation Sheet");
		fileMetadata.setMimeType("application/vnd.google-apps.spreadsheet");
		Drive service = getDriveService();

		File file = service.files().create(fileMetadata).setFields("id").execute();
		System.out.println("Folder ID: " + file.getId());
		return file.getId();
	}

	/**
	 * Search the Google Sheet or Folder1
	 */

	public static String fnSearchGoogleFileORFolder(String strName) throws IOException {
		String strFolderID = "";
		Drive service = getDriveService();
		FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
		List<File> files = result.getFiles();
		if (files == null || files.size() == 0) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				if (file.getName().contains(strName)) {
					System.out.printf("%s (%s)\n", file.getName(), file.getId());
					strFolderID = file.getId();
				}
				// System.out.printf("%s (%s)\n", file.getName(), file.getId());
			}
		}

		return strFolderID;
	}

	/**
	 * Get the File Details of Google Folder inside the Folder It will work as the
	 * Search within the folder
	 */
	public static String[] fnGetFileID(String strFolderID, String strFileType) throws IOException {
		String strFileId[] = new String[2];
		String pageToken = null;
		Drive service = getDriveService();
		do {
			FileList result = service.files().list().setQ("'" + strFolderID + "' in parents and trashed=false")
					.setFields("nextPageToken, files(id, name)").setPageToken(pageToken).execute();
			for (File file : result.getFiles()) {
				if (file.getName().toLowerCase().contains(strFileType.toLowerCase())) {
					System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
					strFileId[0] = file.getName();
					strFileId[1] = file.getId();
					return strFileId;
				}
				System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
			}
			pageToken = result.getNextPageToken();
		} while (pageToken != null);

		return null;
	}

	/**
	 * Download the File to the localDrive
	 */
	public static boolean fnDownloadFile(String strID, String strFilePath) throws IOException {
		boolean blnStatus = true;
		try {
			java.io.File file = new java.io.File(strFilePath);
			String strURL = "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=" + strID;
			// System.out.println(strURL);
			org.apache.commons.io.FileUtils.copyURLToFile(new URL(strURL), file);
		} catch (Exception e) {
			blnStatus = false;
		}
		return blnStatus;
	}

	/**
	 * Download the File to the localDrive
	 */
	public static boolean fnDownloadFileToLocal(String strRootFolderName, String strFileType, String strLocalPath) {
		boolean blnDownload = false;
		try {
			System.out.println("****************************************************");
			System.out.println("************* Find Google File Location ************");
			String strFolderID = fnSearchGoogleFileORFolder(strRootFolderName);
			System.out.println("****************************************************");
			System.out.println("Folder ID for Application Build ==> " + strFolderID);
			System.out.println("****************************************************");
			System.out.println("************* Find Google File Name ****************");
			String strFileID[] = fnGetFileID(strFolderID, strFileType);
			System.out.println("*********File Name ==> " + strFileID[0]);
			System.out.println("****************************************************");
			System.out.println("************* Find Google File ID ******************");
			System.out.println("*********File ID ==> " + strFileID[1]);
			System.out.println("****************************************************");
			System.out.println("************* Started Download Google File " + strFileID[1] + " ****************");
			boolean blnDownload1 = fnDownloadFile(strFileID[1], strLocalPath);
			System.out.println("****************************************************");
			System.out.println("************* Download Status Google File " + strFileID[1] + " ******************");
			if (blnDownload1) {
				java.io.File file = new java.io.File(strLocalPath);
				if (file.exists()) {
					System.out.println(strFileID[0] + " Downloaded successfully ");
					blnDownload = true;
				} else {
					System.out.println(strFileID[0] + " not Downloaded");
				}
			} else {
				System.out.println(strFileID[0] + " not Downloaded");
			}
			System.out.println("****************************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnDownload;
	}

	public static boolean fnUploadFile(String strFilePath) {
		boolean blnUpload = true;
		try {
			// String strFilePath="C:/Test/Test.html";
			Drive service = getDriveService();
			File fileMetadata = new File();
			fileMetadata.setName("Report.html");
			java.io.File filePath = new java.io.File("C:/Test/Test.html");
			FileContent mediaContent = new FileContent("text/html", filePath);
			File file = service.files().create(fileMetadata, mediaContent).setFields("id").execute();
			System.out.println("File ID: " + file.getId());

		} catch (Exception e) {
			e.printStackTrace();
			blnUpload = false;
		}
		return blnUpload;
	}

	public static ValueRange getResponse(String SheetName, String RowStart, String RowEnd) throws IOException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		String spreadsheetId = "19ERMqsvngRU5-Genb4xykehKz8KnAsAuaMa5JeMez1Y";
		String range = SheetName + "!" + RowStart + ":" + RowEnd;
		response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		return response;

	}

	public static void setValue(String SheetName, String RowStart, String RowEnd, String strData) throws IOException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		// Prints the names and majors of students in a sample spreadsheet:
		String spreadsheetId = "19ERMqsvngRU5-Genb4xykehKz8KnAsAuaMa5JeMez1Y";
		// String range = RowStart+":"+RowEnd;
		String range = RowStart + ":" + RowStart;
		List<List<Object>> arrData = getData(strData);

		ValueRange oRange = new ValueRange();
		oRange.setRange(range); // I NEED THE NUMBER OF THE LAST ROW
		oRange.setValues(arrData);

		List<ValueRange> oList = new ArrayList<>();
		oList.add(oRange);

		BatchUpdateValuesRequest oRequest = new BatchUpdateValuesRequest();
		oRequest.setValueInputOption("RAW");
		oRequest.setData(oList);

		// BatchUpdateValuesResponse oResp1 =
		service.spreadsheets().values().batchUpdate(spreadsheetId, oRequest).execute();

		// service.spreadsheets().values().update (spreadsheetId, range,) ;
		// return request;

	}

	public static List<List<Object>> getData(String strData) {

		List<Object> data1 = new ArrayList<Object>();
		data1.add(strData);

		List<List<Object>> data = new ArrayList<List<Object>>();
		data.add(data1);

		return data;

	}

	public static void fnGetData() throws IOException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		// String spreadsheetId = "19ERMqsvngRU5-Genb4xykehKz8KnAsAuaMa5JeMez1Y";
		// String range = "Data!A2:G2";
		int intRowNo = 22000;
		String spreadsheetId = "1hzxcNixA9_ku8zWSvH-guyK54pKLWKwZ8CzF3wpV2WY";
		String range = "Content!O" + intRowNo + ":BC" + intRowNo;
		System.out.println(range);

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.

				System.out.printf("%s \n", row.get(0));
				System.out.printf("%s \n", row.get(1));
				System.out.printf("%s \n", row.get(2));
				System.out.printf("%s \n", row.get(3));
				System.out.printf("%s \n", row.get(4));
				System.out.printf("%s \n", row.get(5));
				System.out.printf("%s \n", row.get(6));

			}
		}
	}

	public static void updateContentData(String spreadsheetId, String range, String strData)
			throws IOException, InterruptedException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		List<List<Object>> arrData = getData(strData);
		ValueRange oRange = new ValueRange();
		oRange.setRange(range); // I NEED THE NUMBER OF THE LAST ROW
		oRange.setValues(arrData);
		List<ValueRange> oList = new ArrayList<>();
		oList.add(oRange);
		BatchUpdateValuesRequest oRequest = new BatchUpdateValuesRequest();
		oRequest.setValueInputOption("RAW");
		oRequest.setData(oList);
		// BatchUpdateValuesResponse oResp1 =
		service.spreadsheets().values().batchUpdate(spreadsheetId, oRequest).execute();
		Thread.sleep(2000);
		if (fnGetData(spreadsheetId, range).equals(strData) == false) {
			// BatchUpdateValuesResponse oResp2 =
			service.spreadsheets().values().batchUpdate(spreadsheetId, oRequest).execute();
			System.out.println("2nd Time Update Done");
		}
	}

	public static String fnGetAPITestMobileNo() throws IOException {
		String strMobileNo = "";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		// String spreadsheetId = "19ERMqsvngRU5-Genb4xykehKz8KnAsAuaMa5JeMez1Y";
		// String range = "Data!A2:G2";
		int intRowNo = 2;
		String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = "Configuration!A" + intRowNo + ":A" + intRowNo;
		System.out.println(range);

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				strMobileNo = row.get(0).toString();
				System.out.printf("%s \n", row.get(0));
			}
		}
		return strMobileNo;
	}

	public static String fnUpdateAPITestMobileNo() throws IOException {
		String strMobileNo = "";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		// String spreadsheetId = "19ERMqsvngRU5-Genb4xykehKz8KnAsAuaMa5JeMez1Y";
		// String range = "Data!A2:G2";
		int intRowNo = 2;
		String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = "Configuration!A" + intRowNo + ":BC" + intRowNo;
		System.out.println(range);

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				strMobileNo = row.get(0).toString();
				System.out.printf("%s \n", row.get(0));
			}
		}
		long mob = Long.parseLong(strMobileNo);
		System.out.println(mob);
		long mobUpdated = mob + 1;
		System.out.println(mobUpdated);
		// Build a new authorized API client service.
		service = getSheetsService();
		List<List<Object>> arrData = getData(String.valueOf(mobUpdated));
		ValueRange oRange = new ValueRange();
		oRange.setRange(range); // I NEED THE NUMBER OF THE LAST ROW
		oRange.setValues(arrData);
		List<ValueRange> oList = new ArrayList<>();
		oList.add(oRange);
		BatchUpdateValuesRequest oRequest = new BatchUpdateValuesRequest();
		oRequest.setValueInputOption("RAW");
		oRequest.setData(oList);
		// BatchUpdateValuesResponse oResp1 =
		service.spreadsheets().values().batchUpdate(spreadsheetId, oRequest).execute();
		// service.spreadsheets().values().update (spreadsheetId, range,) ;
		// return request;
		return String.valueOf(mobUpdated);
	}

	public static int fnGetRowNo(String strOSName, String strBrowser, String strScriptName, String strSheetName,
			String spreadsheetId) throws IOException {
		// String strMobileNo="";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		// String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = strSheetName + "!B2:D1553";
		System.out.println(range);
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Device, ScriptName");
			int intRow = 2;
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				String strOS = row.get(0).toString();
				String strDevice = row.get(1).toString();
				String strScrName = row.get(2).toString();
				System.out.println(strDevice);
				System.out.println(strScrName);
				if (strOSName.toLowerCase().equals(strOS.toLowerCase())) {
					if (strDevice.toLowerCase().equals(strBrowser.toLowerCase())) {
						if (strScrName.toLowerCase().equals(strScriptName.toLowerCase())) {
							return intRow;
						}
					}
				}
				System.out.println(intRow);
				intRow = intRow + 1;
			}
		}
		return 0;
	}

	public static int fnGetRowNoContent(String strBrowser, String strScriptName, String strSheetName,
			String spreadsheetId) throws IOException {
		// String strMobileNo="";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		// String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = strSheetName + "!B2:C30000";
		System.out.println(range);
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Device, ScriptName");
			int intRow = 2;
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				String strDevice = row.get(0).toString();
				String strScrName = row.get(1).toString();
				System.out.println(strDevice);
				System.out.println(strScrName);
				if (strDevice.toLowerCase().equals(strBrowser.toLowerCase())) {
					if (strScrName.toLowerCase().equals(strScriptName.toLowerCase())) {
						return intRow;
					}
				}
				System.out.println(intRow);
				intRow = intRow + 1;
			}
		}
		return 0;
	}

	public static void fnUpdateTimeStampStatus(int intRow, String status, String strBuildNo, String strScriptName)
			throws IOException, InterruptedException {
		/*
		 * SlackTest objData=new SlackTest(); objData.strScriptName=strScriptName;
		 * String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk"; String
		 * rangeStatus = "Sanity_API_Test!E"+intRow+":E"+intRow;
		 * updateContentData(spreadsheetId,rangeStatus,status);
		 * objData.strStatus=status; String rangeBuild =
		 * "Sanity_API_Test!H"+intRow+":H"+intRow;
		 * updateContentData(spreadsheetId,rangeBuild,strBuildNo);
		 * objData.strBuildNo=strBuildNo; String range =
		 * "Sanity_API_Test!F"+intRow+":F"+intRow; DateTimeFormatter dtf =
		 * DateTimeFormatter.ofPattern("dd-MMM-yyyy"); LocalDate localDate =
		 * LocalDate.now(); String strDate=dtf.format(localDate);
		 * objData.strBuildDate=strDate; updateContentData(spreadsheetId,range,strDate);
		 * String range1 = "Sanity_API_Test!G"+intRow+":G"+intRow; Calendar cal =
		 * Calendar.getInstance(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("HH:mm:ss"); String strTime=sdf.format(cal.getTime());
		 * objData.strBuildTime=strTime;
		 * updateContentData(spreadsheetId,range1,strTime); String
		 * strCycleNo=fnGetCycleNo(); objData.strCycle=strCycleNo; String range2 =
		 * "Sanity_API_Test!I"+intRow+":I"+intRow;
		 * updateContentData(spreadsheetId,range2,strCycleNo);
		 * //SlackTest.fnUpdateSanityChanel(objData);
		 */ }

	public static String fnGetData(String spreadsheetId, String range) throws IOException {
		String strMobileNo = "";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		System.out.println(range);
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				strMobileNo = row.get(0).toString();
				System.out.printf("%s \n", row.get(0));
			}
		}
		return strMobileNo;
	}

	public static String fnGetCycleNo() throws IOException {
		String strMobileNo = "";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		int intRowNo = 2;
		String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = "Configuration!B" + intRowNo + ":B" + intRowNo;
		System.out.println(range);

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				strMobileNo = row.get(0).toString();
				System.out.printf("%s \n", row.get(0));
			}
		}
		return strMobileNo;
	}

	public static int fnGetBuildIdentifier(String strBuildIdentify) throws IOException {
		// String strMobileNo="";
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = "Build_Execution_Details!F1:F13000";
		System.out.println(range);
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Build identifier");
			int intRow = 1;
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				String strBuildIdentifier = row.get(0).toString();
				System.out.println(strBuildIdentifier);
				if (strBuildIdentifier.toLowerCase().equals(strBuildIdentify.toLowerCase())) {
					return intRow;
				}
				System.out.println(intRow);
				intRow = intRow + 1;
			}
		}
		return 0;
	}

	public static int fnGetRowNoDashBoard(String strBrowser, String strScriptName, String strSheetName,
			String spreadsheetId) throws IOException {
		// Build a new authorized API client service.
		Sheets service = getSheetsService();
		// String spreadsheetId = "1JbJZ2hjDwxDQ079VuvPdZjJrDrGTHR9NIUKdE2RnaWk";
		String range = strSheetName + "!B2:E555";
		System.out.println(range);
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		System.out.println(values.size());
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Device, ScriptName");
			int intRow = 2;
			for (List<?> row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				String strDevice = row.get(0).toString();
				String strScrName = row.get(3).toString();
				System.out.println(strDevice);
				System.out.println(strScrName);
				if (strDevice.toLowerCase().equals(strBrowser.toLowerCase())) {
					if (strScrName.toLowerCase().equals(strScriptName.toLowerCase())) {
						return intRow;
					}
				}
				System.out.println(intRow);
				intRow = intRow + 1;
			}
		}
		return 0;
	}
}

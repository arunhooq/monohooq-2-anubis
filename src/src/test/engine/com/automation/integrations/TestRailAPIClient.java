package com.automation.integrations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.codepine.api.testrail.TestRail;

public class TestRailAPIClient {

	public TestRail testRail;
	private final static String endPoint = "https://hooqtv.testrail.net/index.php?/api/v2/";
	private final static String username = "ali@hooq.tv";
	private final static String password = "@Afsar273";
	private static int testRunId;
	private static String testRunName;
	private static String projectname = ConfigDetails.strTestRailProject;
	private static int projectId;
	private static int suiteId;
	private static String suiteName;
	private static int testcaseId;
	private static String milestoneName;
	private static int milestoneId;
	private static String planName;
	private static int planId;
	private static String entry_id;
	private static String status;

	/**
	 * @author mdafsarali
	 * @description This method is being used to get Basic Authentication
	 * @param user
	 * @param password
	 * @return
	 */

	private static String getAuthorization(String user, String password) {
		try {
			return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
		} catch (IllegalArgumentException e) {
			// Not thrown
		}

		return "";
	}

	/**
	 * @author mdafsarali
	 * @description This method is used for getting ProjectId
	 * @param client
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */
	private static void getProjectId(TestRailAPIClient client) throws MalformedURLException, IOException, APIException {

		JSONArray obj = (JSONArray) client.sendGet("get_projects");
		int pid = 0;
		for (int i = 0; i < obj.size(); i++) {

			if (((JSONObject) obj.get(i)).get("name").toString().equalsIgnoreCase(TestRailAPIClient.projectname))

				pid = Integer.parseInt(((JSONObject) obj.get(i)).get("id").toString());
			setProjectId(pid);
		}
	}

	/**
	 * @author mdafsarali
	 * @description This method is used for getting MileStoneId
	 * @param client
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */
	private static void getMileStoneId(TestRailAPIClient client)
			throws MalformedURLException, IOException, APIException {

		JSONArray milestones = (JSONArray) client.sendGet("get_milestones/" + getProjectId());
		for (int i = 0; i < milestones.size(); i++) {

			if (((JSONObject) milestones.get(i)).get("name").toString()
					.contentEquals(TestUtilities.getMileStoneName())) {
				milestoneId = Integer.parseInt(((JSONObject) milestones.get(i)).get("id").toString());
				setMilestoneId(milestoneId);
			}
		}
	}

	/**
	 * @author mdafsarali
	 * @description This method is used for getting TestPlanID
	 * @param client
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */
	private static void getPlanId(TestRailAPIClient client) throws MalformedURLException, IOException, APIException {
		try {
		int count = 0;
		JSONArray plans = (JSONArray) client.sendGet("get_plans/" + getProjectId());
		for (int i = 0; i < plans.size(); i++) {

			if (((JSONObject) plans.get(i)).get("name").toString().contentEquals(getPlanName())) {
				planId = Integer.parseInt(((JSONObject) plans.get(i)).get("id").toString());
				setPlanId(planId);
				count++;
			}
		}
		if (!(count > 0)) {
			Map<String, Serializable> data = new HashMap<>();
			data.put("name", getPlanName());
			data.put("milestone_id", getMilestoneId());
			JSONObject obj = (JSONObject) client.sendPost("add_plan/" + getProjectId(), data);
			setPlanId(Integer.parseInt(obj.get("id").toString()));
		} else {
			System.out.println("Plan already exists ...");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author mdafsarali
	 * @description This method is used for getting SuiteId
	 * @param client
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */
	private static void getSuiteId(TestRailAPIClient client) throws MalformedURLException, IOException, APIException {
		try {
		JSONArray obj1 = (JSONArray) client.sendGet("get_suites/" + getProjectId());
		int sid = 0;
		for (int i = 0; i < obj1.size(); i++) {

			if (((JSONObject) obj1.get(i)).get("name").toString().equalsIgnoreCase(getSuiteName()))

				sid = Integer.parseInt(((JSONObject) obj1.get(i)).get("id").toString());
			setSuiteId(sid);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author mdafsarali
	 * @description
	 * @param client
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static void updateTestPlanEntry(TestRailAPIClient client) {
		try {
			if (getSuiteId() == 0) {
				throw new Exception("Please Check your TestRail Suite name");
			}
			JSONObject obj = new JSONObject();
			obj.put("suite_id", getSuiteId());
			obj.put("name", getTestRunName());
			obj.put("include_all", true);

			JSONObject obj2 = new JSONObject();
			obj2.put("include_all", true);
			obj2.put("name", getTestRunName());

			JSONArray arr = new JSONArray();
			arr.add(0, obj2);
			obj.put("runs", arr);

			JSONObject planResponse = (JSONObject) client.sendGet("get_plan/" + getPlanId());
			JSONArray entries = (JSONArray) planResponse.get("entries");

			if (entries.size() > 0) {

				boolean flag = false;

				for (int i = 0; i < entries.size(); i++) {

					if (((JSONObject) entries.get(i)).get("name").toString().equalsIgnoreCase(getTestRunName())) {

						flag = true;
					}
				}
				if (!flag) {
					System.out.println("Test Run is not available creating new add_plan_entry .....");
					client.sendPost("add_plan_entry/" + getPlanId(), obj);
				} else {
					System.out.println("Test Run Exists already ......");
				}

			} else {
				client.sendPost("add_plan_entry/" + getPlanId(), obj);
			}
		} catch (Exception e) {
			// Not Failing test even if No Test Suite is present ..will print the error
			// message only .
			e.printStackTrace();
		}
	}

	/**
	 * @author mdafsarali
	 * @description This method is used for getting TestRunId
	 * @param client
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */

	private static void getTestRunId(TestRailAPIClient client) throws MalformedURLException, IOException, APIException {
		try {
		JSONObject planObj = (JSONObject) client.sendGet("get_plan/" + getPlanId());
		JSONArray entries = (JSONArray) planObj.get("entries");
		int runId = 0;
		if (entries.size() > 0) {
			for (int j = 0; j < entries.size(); j++) {

				if (((JSONObject) entries.get(j)).get("name").toString().equalsIgnoreCase(getTestRunName())) {

					JSONArray runsObj = (JSONArray) ((JSONObject) entries.get(j)).get("runs");
					for (int i = 0; i < runsObj.size(); i++) {

						if (((JSONObject) runsObj.get(i)).get("name").toString().contentEquals(getTestRunName())) {
							runId = Integer.parseInt(((JSONObject) runsObj.get(i)).get("id").toString());
							setTestRunId(runId);
							break;
						}
					}
				}
			}

		} else {
			System.out.println("Test Run Not Created for PlanID : " + getPlanId());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author mdafsarali
	 * @description This method is used for Updating Test Results after test run
	 *              completes ,that means will remove all untested test cases
	 * @param client
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */
	public static void updateTestRun(TestRailAPIClient client) throws MalformedURLException, IOException, APIException {
		try {
		List<Integer> testIds = new ArrayList<Integer>();
		List<Integer> addActualTests = new ArrayList<Integer>();

		JSONArray testCases = (JSONArray) client.sendGet("get_cases/" + getProjectId() + "&suite_id=" + getSuiteId());
		for (int i = 0; i < testCases.size(); i++) {
			testIds.add(Integer.parseInt(((JSONObject) testCases.get(i)).get("id").toString()));
		}

		for (Integer a : testIds) {

			JSONArray testcasedetails = (JSONArray) client.sendGet("get_results_for_case/" + getTestRunId() + "/" + a);

			for (int i = 0; i < testcasedetails.size(); i++) {

				String status = ((JSONObject) testcasedetails.get(i)).get("status_id").toString();

				if (status.contentEquals("1") || status.contentEquals("5") || status.contentEquals("2")) {
					addActualTests.add(a);
					break;
				}
			}
		}

		JSONObject planObj = (JSONObject) client.sendGet("get_plan/" + getPlanId());
		JSONArray entries = ((JSONArray) planObj.get("entries"));

		for (int i = 0; i < entries.size(); i++) {
			if (((JSONObject) entries.get(i)).get("name").toString().contentEquals(getTestRunName())) {
				setEntryId(((JSONObject) entries.get(i)).get("id").toString());
				break;
			} else {
				System.out.println("Test Run is not available ...");
			}
		}

		Map<String, Serializable> data = new HashMap<>();
		data.put("include_all", false);
		data.put("case_ids", (Serializable) addActualTests);
		client.sendPost("update_plan_entry/" + getPlanId() + "/" + getEntryId(), data);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/**
	 * Add testcase results to existing test run
	 * 
	 * @author mdafsarali
	 * @param client
	 * @param testCaseId
	 * @param testRunId
	 * @param testStatus
	 * @param error
	 * @throws IOException
	 * @throws APIException
	 */
	public static void addResultForTestCase(TestRailAPIClient client, int testCaseId, int testStatus, String comment) {
		// logger.info("Adding run results for test case");
		setTestcaseId(testCaseId);
		Map<String, Serializable> data = new HashMap<>();
		data.put("status_id", testStatus);
		if (testStatus == 1) {
			data.put("comment", comment + " - PASS");
		} else if (testStatus == 5 || testStatus == 2) {
			data.put("comment", comment + " - FAIL");
		}

		try {
			// Add Test results only when a valid test caseId is available
			if (Boolean.parseBoolean(ConfigDetails.strTestRail)) {
				if(getTestcaseId() != 0 ) {
					
				client.sendPost("add_result_for_case/" + getTestRunId() + "/" + testCaseId, data);
			// Set Test Case Id to 0 so that when No Test Skips it will not update wrong
			// testCase status
				}
			setTestcaseId(0);
				}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send POST
	 *
	 * Issues a POST request (write) against the API and returns the result (as
	 * Object, see below).
	 *
	 * Arguments:
	 *
	 * uri The API method to call including parameters (e.g. add_case/1) data The
	 * data to submit as part of the request (e.g., a map) If adding an attachment,
	 * must be the path to the file
	 *
	 * Returns the parsed JSON response as standard object which can either be an
	 * instance of JSONObject or JSONArray (depending on the API method). In most
	 * cases, this returns a JSONObject instance which is basically the same as
	 * java.util.Map.
	 */
	public Object sendPost(String uri, Object data) throws MalformedURLException, IOException, APIException {
		return this.sendRequest("POST", uri, data);
	}

	private Object sendRequest(String method, String uri, Object data)
			throws MalformedURLException, IOException, APIException {
		URL url = new URL(endPoint + uri);
		// Create the connection object and set the required HTTP method
		// (GET/POST) and headers (content type and basic auth).
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		String auth = getAuthorization(username, password);
		conn.addRequestProperty("Authorization", "Basic " + auth);

		if (method.equals("POST")) {
			conn.setRequestMethod("POST");
			// Add the POST arguments, if any. We just serialize the passed
			// data object (i.e. a dictionary) and then add it to the
			// request body.
			if (data != null) {
				if (uri.startsWith("add_attachment")) // add_attachment API requests
				{
					String boundary = "TestRailAPIAttachmentBoundary"; // Can be any random string
					File uploadFile = new File((String) data);

					conn.setDoOutput(true);
					conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

					OutputStream ostreamBody = conn.getOutputStream();
					BufferedWriter bodyWriter = new BufferedWriter(new OutputStreamWriter(ostreamBody));

					bodyWriter.write("\n\n--" + boundary + "\r\n");
					bodyWriter.write("Content-Disposition: form-data; name=\"attachment\"; filename=\""
							+ uploadFile.getName() + "\"");
					bodyWriter.write("\r\n\r\n");
					bodyWriter.flush();

					// Read file into request
					InputStream istreamFile = new FileInputStream(uploadFile);
					int bytesRead;
					byte[] dataBuffer = new byte[1024];
					while ((bytesRead = istreamFile.read(dataBuffer)) != -1) {
						ostreamBody.write(dataBuffer, 0, bytesRead);
					}

					ostreamBody.flush();

					// end of attachment, add boundary
					bodyWriter.write("\r\n--" + boundary + "--\r\n");
					bodyWriter.flush();

					// Close streams
					istreamFile.close();
					ostreamBody.close();
					bodyWriter.close();
				} else // Not an attachment
				{
					conn.addRequestProperty("Content-Type", "application/json");
					byte[] block = JSONValue.toJSONString(data).getBytes("UTF-8");

					conn.setDoOutput(true);
					OutputStream ostream = conn.getOutputStream();
					ostream.write(block);
					ostream.close();
				}
			}
		} else // GET request
		{
			conn.addRequestProperty("Content-Type", "application/json");
		}

		// Execute the actual web request (if it wasn't already initiated
		// by getOutputStream above) and record any occurred errors (we use
		// the error stream in this case).
		int status = conn.getResponseCode();

		InputStream istream;
		if (status != 200) {
			istream = conn.getErrorStream();
			if (istream == null) {
				throw new APIException(
						"TestRail API return HTTP " + status + " (No additional error message received)");
			}
		} else {
			istream = conn.getInputStream();
		}

		// If 'get_attachment/' returned valid status code, save the file
		if ((istream != null) && (uri.startsWith("get_attachment/"))) {
			FileOutputStream outputStream = new FileOutputStream((String) data);

			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = istream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			istream.close();
			return (String) data;
		}

		// Not an attachment received
		// Read the response body, if any, and deserialize it from JSON.
		String text = "";
		if (istream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(istream, "UTF-8"));

			String line;
			while ((line = reader.readLine()) != null) {
				text += line;
				text += System.getProperty("line.separator");
			}

			reader.close();
		}

		Object result;
		if (!text.equals("")) {
			result = JSONValue.parse(text);
		} else {
			result = new JSONObject();
		}

		// Check for any occurred errors and add additional details to
		// the exception message, if any (e.g. the error message returned
		// by TestRail).
		if (status != 200) {
			String error = "No additional error message received";
			if (result != null && result instanceof JSONObject) {
				JSONObject obj = (JSONObject) result;
				if (obj.containsKey("error")) {
					error = '"' + (String) obj.get("error") + '"';
				}
			}
			throw new APIException("TestRail API returned HTTP " + status + "(" + error + ")");
		}
		return result;
	}

	/**
	 * Send Get
	 *
	 * Issues a GET request (read) against the API and returns the result (as
	 * Object, see below).
	 *
	 * Arguments:
	 *
	 * uri The API method to call including parameters (e.g. get_case/1)
	 *
	 * Returns the parsed JSON response as standard object which can either be an
	 * instance of JSONObject or JSONArray (depending on the API method). In most
	 * cases, this returns a JSONObject instance which is basically the same as
	 * java.util.Map.
	 * 
	 * If 'get_attachment/:attachment_id', returns a String
	 */
	public Object sendGet(String uri, String data) throws MalformedURLException, IOException, APIException {
		return this.sendRequest("GET", uri, data);
	}

	public Object sendGet(String uri) throws MalformedURLException, IOException, APIException {
		return this.sendRequest("GET", uri, null);
	}

	/**
	 * @author mdafsarali
	 * @description This method need to be present in @BeforeSuite or @BeforeTest so
	 *              that we can used other methods of Testrail
	 * @param strTestPlanName
	 * @param strSuiteName
	 * @param strTestRunName
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 */
	public static void setTestRail(String strTestPlanName, String strSuiteName, String strTestRunName)
			throws MalformedURLException, IOException, APIException {
		setPlanName(strTestPlanName); // Plan name
		setSuiteName(strSuiteName); // TestBase Suite Name
		setTestRunName(strTestRunName); // Run Name

		TestRailAPIClient client = new TestRailAPIClient();
		TestRailTestRunStatus.testrailObj = client;
		getProjectId(client);
		getMileStoneId(client);
		getPlanId(client);
		getSuiteId(client);
		updateTestPlanEntry(client);
		getTestRunId(client);
	}

	public static String getEntryId() {
		return entry_id;
	}

	public static void setEntryId(String string) {
		TestRailAPIClient.entry_id = string;
	}

	public TestRail getTestRail() {
		return testRail;
	}

	public void setTestRail(TestRail testRail) {
		this.testRail = testRail;
	}

	public static String getMilestoneName() {
		return milestoneName;
	}

	public static void setMilestoneName(String milestoneName) {
		TestRailAPIClient.milestoneName = milestoneName;
	}

	public static int getMilestoneId() {
		return milestoneId;
	}

	public static void setMilestoneId(int milestoneId) {
		TestRailAPIClient.milestoneId = milestoneId;
	}

	public static String getPlanName() {
		return planName;
	}

	public static void setPlanName(String planName) {
		TestRailAPIClient.planName = planName;
	}

	public static int getPlanId() {
		return planId;
	}

	public static void setPlanId(int planId) {
		TestRailAPIClient.planId = planId;
	}

	public static String getEndpoint() {
		return endPoint;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setProjectname(String projectname) {
		TestRailAPIClient.projectname = projectname;
	}

	public String getProjectname() {
		return projectname;
	}

	public static String getTestRunName() {
		return testRunName;
	}

	public static void setTestRunName(String testRunName) {
		TestRailAPIClient.testRunName = testRunName;
	}

	public static int getTestcaseId() {
		return testcaseId;
	}

	public static void setTestcaseId(int testcaseId) {
		TestRailAPIClient.testcaseId = testcaseId;
	}

	public static int getTestRunId() {
		return testRunId;
	}

	public static void setTestRunId(int testRunId) {
		TestRailAPIClient.testRunId = testRunId;
	}

	public static int getProjectId() {
		return projectId;
	}

	public static void setProjectId(int projectId) {
		TestRailAPIClient.projectId = projectId;
	}

	public static int getSuiteId() {
		return suiteId;
	}

	public static void setSuiteId(int sid) {
		TestRailAPIClient.suiteId = sid;
	}

	public static String getSuiteName() {
		return suiteName;
	}

	public static void setSuiteName(String strSuiteName) {
		TestRailAPIClient.suiteName = strSuiteName;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		TestRailAPIClient.status = status;
	}

	public static void main(String[] args) throws MalformedURLException, IOException, APIException {
		// getMilestones
		// setProjectId(5);
		setPlanName("Test-API7"); // Plan name
		setSuiteName("HOOQ_IOS_Sanity"); // TestBase Suite Name
		setTestRunName("IN-Test-6"); // Run Name

		TestRailAPIClient client = new TestRailAPIClient();
		getProjectId(client);
		getMileStoneId(client);
		getPlanId(client);
		getSuiteId(client);
		updateTestPlanEntry(client); // Create Test Run for a Test Plan/Suite
		getTestRunId(client);
		addResultForTestCase(client, 20394, 1, "Through API");

		updateTestRun(client);
	}

}

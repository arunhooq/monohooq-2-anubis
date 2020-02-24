package com.automation.utilities;

import com.automation.testengine.TestUtilities;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtilities {

	static JSONObject jsonObject;

	public static String getJsonValueUsingPath(String strJson, String strJsonPath) {
		String data = JsonPath.read(strJson, strJsonPath);
		return data;
	}

	public static JSONObject stringToJsonObject(String response_value) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(response_value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject readJSONFile(String strFilePath) {

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Object obj = parser.parse(new FileReader(strFilePath));

			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String getLocalizedString(String strLanguage, String strKey) {

		JSONObject obj = readJSONFile(System.getProperty("user.dir") + "/TestData/localizedStrings.json");

		JSONObject obj2 = (JSONObject) obj.get(strLanguage);
		String value = obj2.get(strKey).toString();

		return value;
	}

	public static JSONObject executePOST(String strBaseURL) throws Exception {
		String response_value = null;
		JSONObject response = new JSONObject();
		try {
			URL url = new URL(strBaseURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			BufferedReader br;

			if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			response_value = br.lines().collect(Collectors.joining());
			response = stringToJsonObject(response_value);

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return response;
	}

	public static String strURLEncode(String strURL) throws Exception {
		String encodedvalue = null;
		try {
			encodedvalue = URLEncoder.encode(strURL, "UTF-8");
		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return encodedvalue;
	}

	public static String strURLDecode(String strURL) throws Exception {
		String decodedValue = null;
		try {

			decodedValue = java.net.URLDecoder.decode(strURL, "UTF-8");

		} catch (Exception e) {
			TestUtilities.logReportFatal(e);
		}
		return decodedValue;
	}

	@SuppressWarnings("unchecked")
	public static void writeToJson(String suitename, Map<Object, Object> results)
			throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		String jsonPath = "src/test/engine/com/automation/reports/ResultCollector.json";
		File file = new File(jsonPath);

		if (!file.exists()) {
			file.createNewFile();
			@SuppressWarnings("resource")
			FileWriter writer = new FileWriter(file);
			writer.append("{}");
			writer.flush();
		}
		Object obj = parser.parse(new FileReader(jsonPath));

		jsonObject = (JSONObject) obj;

		if (suitename.contentEquals("TOTAL_TEST_RUN")) {
			jsonObject.put(suitename, results);
		} else {
			jsonObject.putIfAbsent(suitename, results);
		}

		try (FileWriter out = new FileWriter(jsonPath)) {

			out.write(jsonObject.toJSONString());
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

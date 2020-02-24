package com.automation.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class GmailAPI {
	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String user = "me";
	static Gmail service = null;
	private static Path currentRelativePath = Paths.get("");
	private static String currentDir = currentRelativePath.toAbsolutePath().toString();
	private static final String CREDENTIALS_FILE_PATH = currentDir
			+ "/TestData/credentials.json";
	private static File filePath = new File(CREDENTIALS_FILE_PATH);

	
	
	
	private static Credential authorize() {
		try {

			InputStream in = new FileInputStream(filePath);
			 System.out.println("CREDENTIALS_FILE_PATH: "+CREDENTIALS_FILE_PATH);
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

			return new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
					.setJsonFactory(JSON_FACTORY)
					.setClientSecrets(clientSecrets.getDetails().getClientId().toString(),
							clientSecrets.getDetails().getClientSecret().toString())
					.build()
					.setAccessToken(getAccessToken())
					.setRefreshToken(
							"1//0gBWNVoNk-HdSCgYIARAAGBASNgF-L9IrjnVDHE9XLwgEX0yygNAOrhllgWGDsbzziC8T7gq7frVS9DhlVfXxruqruyd9Uhhs5A");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void getGmailService() {
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorize()).setApplicationName(APPLICATION_NAME)
					.build();
		} catch (Exception e) {
			System.out.println("Expection caught .. ");
			e.printStackTrace();
		}
	}

	
	private static String getAccessToken()
	{
	    try
	    {
	        Map<String,Object> params = new LinkedHashMap<>();
	        params.put("grant_type","refresh_token");
	        params.put("client_id","169289400846-qulkjbfioorri6g8i0mflhgcjgr4j0p6.apps.googleusercontent.com");
	        params.put("client_secret","QWyJYeFbwYdEnGfwhf_NF7it");
	        params.put("refresh_token","1//0gBWNVoNk-HdSCgYIARAAGBASNgF-L9IrjnVDHE9XLwgEX0yygNAOrhllgWGDsbzziC8T7gq7frVS9DhlVfXxruqruyd9Uhhs5A");

	        StringBuilder postData = new StringBuilder();
	        for(Map.Entry<String,Object> param : params.entrySet())
	        {
	            if(postData.length() != 0)
	            {
	                postData.append('&');
	            }
	            postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        URL url = new URL("https://accounts.google.com/o/oauth2/token");
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setDoOutput(true);
	        con.setUseCaches(false);
	        con.setRequestMethod("POST");
	        con.getOutputStream().write(postDataBytes);

	        BufferedReader  reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        StringBuffer buffer = new StringBuffer();
	        for (String line = reader.readLine(); line != null; line = reader.readLine())
	        {
	            buffer.append(line);
	        }

	        JSONObject json = new JSONObject(buffer.toString());
	        String accessToken = json.getString("access_token");
	        return accessToken;
	    }
	    catch (Exception ex)
	    {
	        ex.printStackTrace(); 
	    }
	    return null;
	}
	
	public static String getMessageId(String email) {
		String messageId = null;
		try {

			Gmail.Users.Messages.List request = service.users().messages().list(user).setQ("to: " + email);
			ListMessagesResponse messagesResponse = request.execute();
			request.setPageToken(messagesResponse.getNextPageToken());
			messageId = messagesResponse.getMessages().get(0).getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageId;
	}

	public static String fetchLinkFromEmail(String email, String stringOfLink) {
		List<String> allMatches = new ArrayList<String>();
		getGmailService();
		try {

			Message message = service.users().messages().get(user, GmailAPI.getMessageId(email)).execute();

			String emailBody = StringUtils
					.newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));

			Pattern p = Pattern.compile("https?://" + stringOfLink + "(.*)");
			Matcher m = p.matcher(emailBody);

			while (m.find()) {
				allMatches.add(m.group());
			}

			System.out.println("match: " + allMatches.get(0));
			return allMatches.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static String getRedirectURLforNewUserLogin(String email) {

		List<String> allMatches = new ArrayList<String>();
		getGmailService();
		try {

			Message message = service.users().messages().get(user, GmailAPI.getMessageId(email.toLowerCase()))
					.execute();

			String emailBody = StringUtils
					.newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));

			Pattern p = Pattern.compile("https?://nebula.hooq.tv/verify/email(.*)");
			Matcher m = p.matcher(emailBody);

			while (m.find()) {
				allMatches.add(m.group());
			}

			// System.out.println("match: "+allMatches.get(0));
			return allMatches.get(0).replace(">", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static String getOTPForParentalControl(String email) throws IOException {
		List<String> allMatches = null;
		try {
			getGmailService();
			allMatches = new ArrayList<String>();
			Message message = service.users().messages().get(user, GmailAPI.getMessageId(email)).execute();

			String emailBody = StringUtils
					.newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));

			Pattern p = Pattern.compile("-?\\d+");
			Matcher m = p.matcher(emailBody);

			while (m.find()) {
				allMatches.add(m.group());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allMatches.get(0);
	}

	
	public static void main(String... args) {

		System.out.println(getAccessToken());
	}
}
package com.automation.integrations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.automation.testengine.ConfigDetails;
import com.automation.utilities.GlobalConstant;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackSessionWrapper;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import sanctuary.utils.ReusableMethods;

public class SlackIntegration {


	@SuppressWarnings("unchecked")
	public static Response sendSlackreport(int totalruns,int pass ,int fail,int skip,int passperc ,int failperc) {
		
		Response res = null;
		try
		{	
			// baseUrl and controller details
			RestAssured.baseURI  = "https://hooks.slack.com/services";
			RestAssured.basePath = "T046C7FU3/BT9CVHVDL/sndQ537JdtdJqpKOCt2SAbf5";
						
			// header details
			HashMap<String, Object>  header = new HashMap<>();
			header.put("Content-Type","application/json");
			String color="primary";
			String color1="#1fdb3b";
			if(fail > 0) {
				color ="danger";
				color1="danger";
			}
			
			String attachment ="{\n" + 
					"    \"channel\": \"#automation-pipeline\",\n" + 
					"    \"username\":\"qarobot\",\n" + 
					"    \"icon_emoji\": \":robot_face:\",\n" + 
					"    \"attachments\": [\n" + 
					"        {\n" + 
					"            \"mrkdwn_in\": [\"text\"],\n" + 
					"            \"color\": \""+color1+"\",\n" + 
					"            \"pretext\": \"Test Execution for "+ConfigDetails.project.toUpperCase()+" is Completed !!!\",\n" + 
					"            \"title\": \" "+ConfigDetails.jenkinsBuildName+"\",\n" + 
					"        \n" + 
					"            \"fields\": [\n" + 
					"                {\n" + 
					"                    \"title\": \"Total Test Runs\",\n" + 
					"                    \"value\": \""+totalruns+"\",\n" + 
					"                    \"short\": false\n" + 
					"                },\n" + 
					"                {\n" + 
					"                    \"title\": \"PASS\",\n" + 
					"                    \"value\": \""+pass+"\",\n" + 
					"                    \"short\": true\n" + 
					"                },\n" + 
					"                {\n" + 
					"                    \"title\": \"FAIL\",\n" + 
					"                    \"value\": \""+fail+"\",\n" + 
					"                    \"short\": true\n" + 
					"                },\n" + 
					"                {\n" + 
					"                    \"title\": \"PASS % : \",\n" + 
					"                    \"value\": \""+passperc+"\",\n" + 
					"                    \"short\": true\n" + 
					"                },\n" + 
					"                {\n" + 
					"                    \"title\": \"FAIL % :\",\n" + 
					"                    \"value\": \""+failperc+"\",\n" + 
					"                    \"short\": true\n" + 
					"                }\n" + 
					"            ],\n" + 
					"            \n" + 
					"            \"fallback\": \"Test link button to https://slack.com/\",\n" + 
					"            \"actions\": [\n" + 
					"                {\n" + 
					"                    \"type\": \"button\",\n" + 
					"                    \"name\": \"file_request_123456\",\n" + 
					"                    \"text\": \"Testrail\",\n" + 
					"                    \"url\": \""+getTestrailUrl()+"/\",\n" + 
					"                    \"style\": \""+color+"\"\n" + 
					"                },\n" + 
					"                {\n" + 
					"                    \"type\": \"button\",\n" + 
					"                    \"name\": \"file_request_123456\",\n" + 
					"                    \"text\": \"Jenkins\",\n" + 
					"                    \"url\": \""+getJenkinsURL()+"/\",\n" + 
					"                    \"style\": \""+color+"\"\n" + 
					"                }\n" + 
					"            ]\n" + 
					"        \n" + 
					"        }\n" + 
					"    ]\n" + 
					"}";
		
			JSONParser parse = new JSONParser();
			JSONObject attachments = (JSONObject) parse.parse(attachment);
			
			res=
					given().
					headers(header).
					body(attachments).
					when().
					post().
					then().extract().response();
		 } 
		 catch (Exception e) 
		 {
			ReusableMethods.logReportFailure(e);
		 }
		
		return res;
			
	}
	
	private static String getJenkinsURL() {
		
		String buildnumber= "";
		if(ConfigDetails.jenkinsBuildName.startsWith("Build #")) {
			String parts[] = ConfigDetails.jenkinsBuildName.split(":");
			buildnumber = parts[1].split("-")[0].trim();
		}
		
		String url="";
		switch(ConfigDetails.project.toUpperCase()) {
		
			case GlobalConstant.PROJECT_ANDROID :
			url="https://jenkins-prod.hooqster.com/view/2-Build-Monitors/view/All-Automation-Monitor/job/androidclient-automation/"+buildnumber+"/console";
			break;
			case GlobalConstant.PROJECT_IOS:
				url="https://jenkins-prod.hooqster.com/view/2-Build-Monitors/view/All-Automation-Monitor/job/iosclient-automation/"+buildnumber+"/console";
				break;
			case GlobalConstant.PROJECT_ANDROIDTVV1:
				url="https://jenkins-prod.hooqster.com/view/2-Build-Monitors/view/All-Automation-Monitor/job/androidtv-automation/"+buildnumber+"/console";
				break;
			case GlobalConstant.PROJECT_API:
				url="https://jenkins-prod.hooqster.com/view/2-Build-Monitors/view/All-Automation-Monitor/job/partnerapis-automation/"+buildnumber+"/console";
				break;
			case GlobalConstant.PROJECT_PARTNERAPI:
				url="https://jenkins-prod.hooqster.com/view/2-Build-Monitors/view/All-Automation-Monitor/job/partnerapis-automation/"+buildnumber+"/console";
				break;
			case GlobalConstant.PROJECT_WEB:
				url="https://jenkins-prod.hooqster.com/view/2-Build-Monitors/view/All-Automation-Monitor/job/web-automation/"+buildnumber+"/console";
				break;
		}
		return url;
	}
	
	
	private static String getTestrailUrl() {
		
		return "https://hooqtv.testrail.net/index.php?/plans/view/"+TestRailAPIClient.getPlanId();
		
	}
	public static void main(String[] args) throws IOException {
		
		ConfigDetails.jenkinsBuildName = "Build #:578-env:prod-countries:TH,IN,ID,PH,SG";
		System.out.println(ConfigDetails.project);
		System.out.println(sendSlackreport(1,1,0,3,3,4));
		
	}
	
	
	
}

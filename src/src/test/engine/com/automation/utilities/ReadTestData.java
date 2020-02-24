package com.automation.utilities;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;

import ios.utils.IOSConstants;

public class ReadTestData {
	public static String FREE_USER_ID;
	public static String FREE_USER_NUMBER;
	public static String ACTIVE_USER_ID;
	public static String ACTIVE_USER_NUMBER;
	public static String LAPSED_USER_ID;
	public static String LAPSED_USER_NUMBER;
	public static String PASSWORD;
	public static String SINGTEL_ID;
	public static String SINGTEL_PWD;
	public static String R21_MOVIES;
	public static String R21_TVSHOWS;
	public static String R21_PREMIUM;
	public static String TRAILER_MOVIES;
	public static String TRAILER_TVSHOWS;
	public static String TRAILER_PREMIUM;
	public static String FREE_CONTENT;
	public static String PREMIUM_CONTENT;
	public static String TV_SERIES;
	public static String TVOD_CONTENT;
	public static String SVOD_CONTENT;
	public static String strBrowserName;
	public static String strScriptName;
	public static String strBuildNo = null;
	public static String SHEET_ID;
	public static String SHEET_NAME_REG;
	public static String SHEET_NAME_SANITY;
	public static int TestRailID;
	// Google Sheet Related Data
	public static String strGoogleSheet;
	public static String strPlatform;
	public static String strDevice;
	public static ReadTestData testData;
	public static String NO_SEARCH_RESULT;
	public static String TRAILER_PREFIX;
	public static String LINK_TV_FAILURE;
	public static String LINK_TV_SUCCESS;
	public static String LINK_TV_CONFIRM;
	public static String CARD_NUMBER;
	public static String NAME;
	public static String EXPIRY;
	public static String CVV;	
	public static String LIVETV_CHANNEL;
	public static String UCAST_CHANNEL;
	public static String PAYTV_CHANNEL;

	public static void getTestData(String project){
		try {
	
			FREE_USER_ID = TestUtilities.getJSONValue(project, "FREE_USER_ID");
			FREE_USER_NUMBER = TestUtilities.getJSONValue(project, "FREE_USER_NUMBER");
			ACTIVE_USER_ID = TestUtilities.getJSONValue(project, "ACTIVE_USER_ID");
			ACTIVE_USER_NUMBER = TestUtilities.getJSONValue(project, "ACTIVE_USER_NUMBER");
			LAPSED_USER_ID = TestUtilities.getJSONValue(project, "LAPSED_USER_ID");
			LAPSED_USER_NUMBER = TestUtilities.getJSONValue(project, "LAPSED_USER_NUMBER");
			PASSWORD = TestUtilities.getJSONValue(project, "PASSWORD");
			SINGTEL_ID = TestUtilities.getJSONValue(project, "SINGTEL_ID");
			SINGTEL_PWD = TestUtilities.getJSONValue(project, "SINGTEL_PWD");
			R21_MOVIES = TestUtilities.getJSONValue(project, "R21_MOVIES");
			R21_TVSHOWS = TestUtilities.getJSONValue(project, "R21_TVSHOWS");
			R21_PREMIUM = TestUtilities.getJSONValue(project, "R21_PREMIUM");
			TRAILER_MOVIES = TestUtilities.getJSONValue(project, "TRAILER_MOVIES");
			TRAILER_TVSHOWS = TestUtilities.getJSONValue(project, "TRAILER_TVSHOWS");
			TRAILER_PREMIUM = TestUtilities.getJSONValue(project, "TRAILER_PREMIUM");
			FREE_CONTENT = TestUtilities.getJSONValue(project, "FREE_CONTENT");
			PREMIUM_CONTENT = TestUtilities.getJSONValue(project, "PREMIUM_CONTENT");
			TV_SERIES = TestUtilities.getJSONValue(project, "TV_SERIES");
			TVOD_CONTENT = TestUtilities.getJSONValue(project, "TVOD_CONTENT");
			SVOD_CONTENT = TestUtilities.getJSONValue(project, "SVOD_CONTENT");
			SHEET_ID = TestUtilities.getJSONValue(project, "SHEET_ID");
			SHEET_NAME_REG = TestUtilities.getJSONValue(project, "SHEET_NAME_REG");
			SHEET_NAME_SANITY = TestUtilities.getJSONValue(project, "SHEET_NAME_SANITY");
			NO_SEARCH_RESULT = TestUtilities.getJSONValue(project, "NO_SEARCH_RESULT");
			TRAILER_PREFIX = TestUtilities.getJSONValue(project, "TRAILER_PREFIX");
			LINK_TV_FAILURE = TestUtilities.getJSONValue(project, "LINK_TV_FAILURE");
			LINK_TV_SUCCESS = TestUtilities.getJSONValue(project, "LINK_TV_SUCCESS");
			LINK_TV_CONFIRM = TestUtilities.getJSONValue(project, "LINK_TV_CONFIRM");
			CARD_NUMBER = TestUtilities.getJSONValue(project, "CARD_NUMBER");
			NAME = TestUtilities.getJSONValue(project, "NAME");
			EXPIRY = TestUtilities.getJSONValue(project, "EXPIRY");
			CVV = TestUtilities.getJSONValue(project, "CVV");
			LIVETV_CHANNEL = TestUtilities.getJSONValue(project, "LIVETV_CHANNEL");
			UCAST_CHANNEL = TestUtilities.getJSONValue(project, "UCAST_CHANNEL");
			PAYTV_CHANNEL = TestUtilities.getJSONValue(project, "PAYTV_CHANNEL");
					
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}

	public static int fnGetSheetRowNo(String strCountry) {
		int intRowNo = 0;
		if (strCountry.toLowerCase().contains("in")) {
			intRowNo = 1;
		} else if (strCountry.toLowerCase().contains("ph")) {
			intRowNo = 2;
		} else if (strCountry.toLowerCase().contains("th")) {
			intRowNo = 3;
		} else if (strCountry.toLowerCase().contains("id")) {
			intRowNo = 4;
		} else if (strCountry.toLowerCase().contains("sg")) {
			intRowNo = 5;
		}
		return intRowNo;
	}

	public static void fnAddTestRailScriptID(int intScriptID) {
		try {
			TestRailID = intScriptID;
		} catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
	}
	
	/**
	 * @author mdafsarali
	 * @date 24 sep 2019
	 * @description This method will be useful when we have multiple test groups for
	 *              one test (e.g if one test has multiple groups like
	 *              Sanity_Active,Sanity_Lapsed , Sanity_Visitor ) then if in test
	 *              rail we have multiple tests available for this one test then we
	 *              have to provide multiple test rail id in this method as a string
	 *              then it will return us testrail id based on the Usertype
	 * @param testrailIdForActiveLapsedVisitor ( Sanity_Active (TestRail ID ) ,
	 *                                         Sanity_Lapsed(Test rail ID) ,
	 *                                         Sanity_Visitor (Test Rail ID)
	 * @return testrailId
	 */

	public static int getTestRailID(String testrailIdForActiveLapsedVisitor) {

		String[] list = testrailIdForActiveLapsedVisitor.split(",");
		int testrailid = 0;
		if (ConfigDetails.userType.contentEquals(GlobalConstant.USERTYPE_ACTIVE)) {
			testrailid = Integer.parseInt(list[0]);
			return testrailid;
		} else if (ConfigDetails.userType.contentEquals(GlobalConstant.USERTYPE_LAPSED)) {
			testrailid = Integer.parseInt(list[1]);
			return testrailid;
		} else if (ConfigDetails.userType.contentEquals(GlobalConstant.USERTYPE_VISITOR)) {
			testrailid = Integer.parseInt(list[2]);
			return testrailid;
		}
		return testrailid;
	}
}

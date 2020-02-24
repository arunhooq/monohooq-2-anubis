package com.automation.testengine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.json.simple.parser.ParseException;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.automation.utilities.FileUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.GmailHTMLReport;

public class TestRunner {

	/**
	 * @author mdafsarali
	 * @description you can also run this project with below commands
	 *              {@code   mvn test -DProject="IOS" -DsuiteType='Regression' -DuserTypeList='Active,Lapsed,Visitor' -Dcountries='SG,IN,TH,PH,ID'}
	 * @param args
	 * @throws Exception
	 */
	// mvn test -DProject="IOS" -DsuiteType='Sanity' -DuserTypeList='Visitor' -Dcountries='IN' -DjenkinsBuildName='RemoteBuildNumber'

	static String project;
	static String singleTestName="";

	public static void main(String[] args) {

		try {
			String suiteType = System.getProperty("suiteType") == null ? "Sanity" : System.getProperty("suiteType");
			String userTypeList = System.getProperty("userTypeList") == null ? "Lapsed": System.getProperty("userTypeList");
			String countries = System.getProperty("countries") == null ? "IN" : System.getProperty("countries");
			project = System.getProperty("Project") == null ? "Android" : System.getProperty("Project");
			String testPackageName = FileUtilities.getPackageName(project);
			singleTestName = System.getProperty("singleTestName"); 
			//singleTestName = "YOUR_TEST_NAME"; //If you want to run a single test Uncomment this line and replace your testName
			String[] arr = countries.split(",");
			List<String> countryList = Arrays.asList(arr);
			List<String> userType = Arrays.asList(userTypeList.split(","));

			switch (project.toLowerCase()) {

			case "ios":
			case "android":
			case "web":
			case "androidtvv1":
				runSuite(countryList, userType, suiteType, project, testPackageName);
				break;
			case "androidtvv2":
				runSuite(countryList, userType, suiteType, project, testPackageName);
				break;
			case "api":
			case "sanctuary":
			case "apipartner":
				runXMLSuite(project);
				break;
			default:
				throw new Exception("Project/TestNG XML file Name is Incorrect.,Please Try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Sending Email Report ...Please wait ....");

		String buildNumber = System.getProperty("RunType");
		String emailSubject = buildNumber + "-" + project + "- Automation Report";
		String emailList = System.getProperty("EmailList");
		try {
			if(ConfigDetails.isEmail.equalsIgnoreCase("true")) {
			Thread.sleep(20000);
			GmailHTMLReport.sendEmailWithHTMLReport(emailList, emailSubject,System.getProperty("user.dir") + "/test-output/custom-report.html");
			}else {
				System.out.println("Email Not Sending , As it is Disabled");
			}
			} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author mdafsarali
	 * @param suite
	 * @param packages
	 * @param country
	 * @param userType
	 * @param suiteType
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void createTest(String project, XmlSuite suite, List<XmlPackage> packages, String country,
			String userType, String suiteType) throws FileNotFoundException, IOException, ParseException {

		String testName = country + "_" + userType + "_Tests";
		String groupName = suiteType + "_" + userType;
		Map<String, String> params = new HashMap<String, String>();
		params.put("country", country);
		params.put("userType", userType);

		XmlTest test = new XmlTest(suite);
		test.setName(testName);
		test.setPackages(packages);
		test.setPreserveOrder(true);

		XmlGroups group = new XmlGroups();
		XmlRun run = new XmlRun();
	
		if(!StringUtils.isEmpty(singleTestName) ) {
			run.onInclude(singleTestName);
		}else {
			run.onInclude(groupName);
		}
		

		List<String> list = FileUtilities.readExcludeJSON(project.toLowerCase(),
				country.toLowerCase() + "_" + userType.toLowerCase());

		for (String arr : list) {
			run.onExclude(arr);
		}

		group.setRun(run);
		test.setGroups(group);
		test.setParameters(params);
	}

	/**
	 * @author mdafsarali
	 * @param countryList
	 * @param userType
	 * @param suiteType
	 * @param project
	 * @param testPackageName
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void runSuite(List<String> countryList, List<String> userType, String suiteType, String project,
			String testPackageName) throws FileNotFoundException, IOException, ParseException {

		// New list for the Suites
		List<XmlSuite> suites = new ArrayList<XmlSuite>();

		for (String country : countryList) {

			XmlSuite suite = new XmlSuite();
			suite.addListener("com.automation.reports.EmailableReport");
			if (project.equalsIgnoreCase(GlobalConstant.PROJECT_WEB)
					|| project.equalsIgnoreCase(GlobalConstant.PROJECT_ANDROID)
					|| project.equalsIgnoreCase(GlobalConstant.PROJECT_IOS)) {
				suite.addListener("com.automation.utilities.AnnotationTransformer");
				suite.addListener("com.automation.utilities.BrowserstackSessionStatus");
			}
			suite.addListener("com.vimalselvam.testng.listener.ExtentTestNgFormatter");
			suite.setName("HOOQ Automation - " + suiteType + " Tests - " + country);
			suite.setPreserveOrder(true);

			Map<String, String> params = new HashMap<String, String>();
			params.put("Project", project);
			params.put("SuiteType", suiteType);
			params.put("country", country);

			suite.setParameters(params);

			// ######### test Packages ############################

			List<XmlPackage> packages = new ArrayList<XmlPackage>();

			packages.add(new XmlPackage(testPackageName));

			// ######### Creating Tests ############################

			for (String user : userType) {

				createTest(project, suite, packages, country, user, suiteType);
			}

			// Add suite to the list
			suites.add(suite);
		}

		// Creating the xml
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG tng = new TestNG();
		// tng.setAnnotationTransformer(new MyTransformer());
		tng.setXmlSuites(suites);
		tng.addListener(tla);
		tng.run();
	}

	/**
	 * @author mdafsarali
	 * @description this only for API
	 * @param XMLName
	 */
	public static void runXMLSuite(String XMLName) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(".//" + XMLName + ".xml");
		testng.setTestSuites(suites);
		testng.run();
	}
}
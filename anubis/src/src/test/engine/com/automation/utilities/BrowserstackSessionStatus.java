package com.automation.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class BrowserstackSessionStatus extends TestListenerAdapter{


	@Override
	public void onFinish(ITestContext context) {

		System.out.println("After Test Listener Called ...");
		Iterator<ITestResult> failedTestCases = context.getFailedTests().getAllResults().iterator();
		Iterator<ITestResult> passedTestCases = context.getPassedTests().getAllResults().iterator();
		Iterator<ITestResult> failedConfiguration = context.getFailedConfigurations().getAllResults().iterator();

		List<String> failedTests = new ArrayList<String>();
		
		//Failed Configuration 
		
		while (failedConfiguration.hasNext()) {
			
			ITestResult failedTestCase = failedConfiguration.next();
			ITestNGMethod method = failedTestCase.getMethod();
			failedTests.add(method.getMethodName());
			System.out.println("Test Config Name ======= " + method.getMethodName() + "  Status : Failed");
		}
		
		// For Failed Tests
		while (failedTestCases.hasNext()) {
			
			ITestResult failedTestCase = failedTestCases.next();
			ITestNGMethod method = failedTestCase.getMethod();
			failedTests.add(method.getMethodName());
			System.out.println("Test Name ======= " + method.getMethodName() + "  Status : Failed");
		}
		
		
		if(failedTests.size()>0) {
			//Set the Browserstack session to Failed
			try {
				APIUtils.setBrowserStackSessionStatus(GlobalConstant.BROWSERSTACK_SESSION_ID, "failed");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			
			try {
				APIUtils.setBrowserStackSessionStatus(GlobalConstant.BROWSERSTACK_SESSION_ID, "passed");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// For Passed Tests
		while (passedTestCases.hasNext()) {

			ITestResult passedTestCase = passedTestCases.next();
			ITestNGMethod method = passedTestCase.getMethod();

			System.out.println("Test Name  ======= " + method.getMethodName() + "  Status : Pass");
		}

	}
}

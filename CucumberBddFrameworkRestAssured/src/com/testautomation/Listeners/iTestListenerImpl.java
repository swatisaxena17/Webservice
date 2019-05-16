package com.testautomation.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

public class iTestListenerImpl extends ExtentReportListener implements ITestListener {

	public static ExtentReports extent;
	

	public void onTestStart(ITestResult result) {
		System.out.println("Execution Started ...");
		
	}


	public void onTestSuccess(ITestResult result) {
//		System.out.println("PASS");
		
	}


	public void onTestFailure(ITestResult result) {
//		System.out.println("FAIL");
		
	}


	public void onTestSkipped(ITestResult result) {
//		System.out.println("SKIP");
		
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		System.out.println("FAIL");
		
	}

	
	public void onStart(ITestContext context) {
		System.out.println("Execution Started  ...");
		extent = setUp();
		
	}


	public void onFinish(ITestContext context) {
		System.out.println("Execution completed  ...");
		extent.flush();
		System.out.println("Generated Report ...");
		
	}
	
	

}

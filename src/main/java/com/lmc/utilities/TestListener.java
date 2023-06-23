package com.lmc.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener
{
	private static ExtentManager extentManager;
	private static FileLib fileLib;

	public TestListener() 
	{
		extentManager =new ExtentManager();
		fileLib =new FileLib();
	}
	
	@Override
	public void onStart(ITestContext context) {
		fileLib.deleteOldFies();
		extentManager.logInfo("OnTest is started");
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentManager.extent_CreateTest(result);
		extentManager.extent_TestStarted(result);
	

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentManager.extent_Pass(result);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentManager.extent_Fail(result);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentManager.extent_Skip(result);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentManager.logging_Info("OnTest is ended");
	}
}

package com.lmc.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager 
{
	private static ExtentSparkReporter sparkReporter;
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	private static Logger logger;

	public ExtentManager() 
	{
		logger = LogManager.getLogger(Log.class.getName());
	}

	public void setUpExtentReport() 
	{
		
		sparkReporter = new ExtentSparkReporter("HTMLReport\\HtmlReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("LMC Framework", "Srinivas Addla");
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
	}

	public void extent_CreateTest(ITestResult result)
	{
		extentTest = extentReports.createTest(result.getName());
	}

	public void logging_Info(String enterMessage) 
	{
		extentTest.info(enterMessage);
		logger.info(enterMessage);

	}
	
	public void logInfo(String enterMessage) 
	{
		logger.info(enterMessage);
	}

	public void extent_TestStarted(ITestResult result) 
	{
		extentTest.info("On test started");
		logging_Info(result.getName() + " test is Started");
	}

	public void extent_Pass(ITestResult result) 
	{
		logging_Info("On test Sucesses");
		extentTest.pass(result.getName() + " test is Passed");
	}

	public void extent_AttachScreenShot(ITestResult result) 
	{
		String path;
		try 
		{
			 path = new WebActionDriver().getScreenShot(result.getName());
			 extentTest.info(MediaEntityBuilder.createScreenCaptureFromPath(path, result.getName()).build());
			logging_Info("Failed Test Screenshot is attched");
		} 
		catch (Exception e) 
		{
			logging_Info("Failed Test Screenshot is not attched");
		}
	}

	public void extent_Fail(ITestResult result) 
	{
		extentTest.info(result.getThrowable());
		logger.throwing(result.getThrowable());
		extent_AttachScreenShot(result);
		extentTest.fail(result.getName() + " test is Failed");
		logger.info(result.getName() + " is failed");

	}

	public void extent_Skip(ITestResult result) 
	{
		extentTest.info(result.getThrowable());
		logger.throwing(result.getThrowable());
		extent_AttachScreenShot(result);
		extentTest.skip(result.getName() + " test is Skipped");
		logger.info(result.getName() + " is Skipped");

	}

	public void extent_SystemInfo(String deviceName, String authorName, String enterCategoryName) 
	{
		extentTest.assignDevice(deviceName);
		extentTest.assignAuthor(authorName);
		extentTest.assignCategory(enterCategoryName);
	}

	public void endExtentReport() throws Exception 
	{
		extentReports.flush();
	}	
}

package lmcBase;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.lmc.utilities.ExtentManager;
import com.lmc.utilities.WebActionDriver;

public class BaseLmc 
{
	private static WebActionDriver webactionDriver;
	private static ExtentManager extentManager;

	public BaseLmc() {
		extentManager = new ExtentManager();
		webactionDriver = new WebActionDriver();
	}

	@BeforeSuite(alwaysRun = true)
	public void extentReport() {
		extentManager.setUpExtentReport();

	}

	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void launchBrower(String browserName) {
		webactionDriver.selectBrowser(browserName);
	}
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowerIfClosed(String browserName) {
		if(webactionDriver.checkBroswerSession()==null && webactionDriver.checkBroswerSession()!=null)
		{
			webactionDriver.selectBrowser(browserName);
		} 
		else 
		{
			
		}
	}
	
	@Parameters({"author","category"})
	@AfterTest(alwaysRun = true)
	public void assignAuthorAndCategory(String assignAuthor, String assignCategory) {
		Capabilities capbil = ((RemoteWebDriver) webactionDriver.getDriver()).getCapabilities();
		String device = capbil.getBrowserName() + "_"
				+ capbil.getBrowserVersion().substring(0, capbil.getBrowserVersion().indexOf("."));
		extentManager.extent_SystemInfo(device, assignAuthor, assignCategory);
		System.out.println("From Baselmc class in after test");
	}
	
	@Parameters({"closeBrowserAfterTest"})
	@AfterMethod(alwaysRun = true)
	public void ifBrowserNotClosed(String closeBrowserAfterTest)
	{
		if(Boolean.parseBoolean(closeBrowserAfterTest))
		{
			webactionDriver.getDriver().quit();
		}
	}
	@Parameters({"closeBrowserAfterTest"})
	@AfterClass(alwaysRun = true)
	public void ifBrowserNotClose(String closeBrowserAfterTest)
	{
		if(!Boolean.parseBoolean(closeBrowserAfterTest))
		{
			webactionDriver.getDriver().quit();
		}
	}


	@AfterSuite(alwaysRun = true)
	public void endExtentReport() throws Exception 
	{
		extentManager.endExtentReport();
		webactionDriver.getDriver().quit();
	}
}

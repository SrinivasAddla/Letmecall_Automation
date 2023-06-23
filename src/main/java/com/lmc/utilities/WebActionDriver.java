package com.lmc.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebActionDriver implements ActionDriver
{
	
	protected static WebDriver driver;
	protected static JavascriptExecutor javascriptExecutor;
	protected static ExtentManager extentManager;

	public WebActionDriver() 
	{
		extentManager = new ExtentManager();

	}

	@Override
	public WebDriver getDriver()
	{
		return driver;
	}

	// pass parameter of browser value in baseclass
	@Override
	public void selectBrowser(String browserName)
	{
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);
		} 
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new EdgeDriver(edgeOptions);
		} 
		else if (browserName.equals("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new FirefoxDriver(firefoxOptions);
		} 
		else 
		{
			extentManager.logInfo("given browser name is Mismatched");
		}
		extentManager.logInfo("selected browser is " + browserName);
		//pageLoadTimeOutIs(20);
		//implicityWaitTime(20);
	}

	@Override
	public void ClearBrowserCache()
	{

		Set<Cookie> allCookies = driver.manage().getCookies();
		for (Cookie cookie : allCookies)
		{
		    driver.manage().deleteCookieNamed(cookie.getName());
		}		
	}

	@Override
	public void pageLoadTimeOutIs(int timeInSec) 
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeInSec));
		extentManager.logInfo("Page load Time is " + timeInSec + "sec");

	}

	@Override
	public void implicityWaitTime(int timeInSec) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSec));
		extentManager.logInfo("Implicity wait Time out is " + timeInSec);
	}

	@Override
	public void getURL(String url)
	{
		extentManager.logging_Info("Given URL :" + url);
		driver.get(url);
		driver.manage().window().maximize();
		ClearBrowserCache();
		extentManager.logging_Info("Given URL is Lanuched");

	}

	@Override
	public void getCurrentURL() 
	{
		String currentURL = driver.getCurrentUrl();
		extentManager.logging_Info("Current Window URL :" + currentURL);
	}

	@Override
	public void getTitle() 
	{
		String currentURL = driver.getTitle();
		extentManager.logging_Info("Current Window Title :" + currentURL);
	}

	@Override
	public String getWindowHandle() 
	{
		return driver.getWindowHandle();

	}

	@Override
	public Set<String> getWindowHandles() 
	{
		return driver.getWindowHandles();

	}

	@Override
	public void nagivateToGivenUrl(String url, int timeInSec)
	{
		driver.navigate().to(url);
		pageLoadTimeOutIs(timeInSec);
		implicityWaitTime(timeInSec);
		extentManager.logging_Info("Navigating to provided URL, given URL :" + url);

	}

	@Override
	public void nagivateToNextpage() 
	{
		driver.navigate().forward();
		extentManager.logging_Info("Navigating to next page, next page title :" + driver.getTitle());
	}

	@Override
	public void nagivateToPreviouspage() 
	{
		driver.navigate().back();
		extentManager.logging_Info("Navigating to previous page, next page title :" + driver.getTitle());
	}

	@Override
	public void switchToFrame(By ele)
	{
		extentManager.logging_Info("Switching to given frame Element");
		extentManager.logging_Info("Given element: " + ele);
		WebElement webElement = getElement(ele);
		driver.switchTo().frame(webElement);
	}

	@Override
	public void switchToParentFrame()
	{
		extentManager.logging_Info("Switching to Parent Frame");
		driver.switchTo().parentFrame();
	}

	@Override
	public void switchToDefaultFrame() 
	{
		extentManager.logging_Info("Switching to Default Frame");
		driver.switchTo().defaultContent();
	}

	@Override
	public WebElement getElement(By ele) 
	{
		return driver.findElement(ele);

	}

	@Override
	public String isElementDisplayed(By ele) 
	{
		String flag;
		extentManager.logging_Info("Given element: " + ele);
		if (getElement(ele).isDisplayed()) {
			extentManager.logging_Info(ele+" Element is Displayed in Webpage");
			flag = "true";
		} else {
			extentManager.logging_Info(ele+" Element is not Displayed in Webpage");
			flag = "false";
		}
		return flag;

	}

	@Override
	public String isElementEnabled(By ele) 
	{
		String flag;
		extentManager.logging_Info("Given element: " + ele);
		if (getElement(ele).isEnabled()) 
		{
			extentManager.logging_Info("Given element is Enabled in Webpage");
			flag = "true";
		} else
		{
			extentManager.logging_Info("Given element is not Enabled in Webpage");
			flag = "true";
		}
		return flag;
	}

	@Override
	public String isElementSelected(By ele) 
	{
		String flag;
		extentManager.logging_Info("Given element: " + ele);
		if (getElement(ele).isSelected()) 
		{
			extentManager.logging_Info("Given element is Selected in Webpage");
			flag = "true";
		} else 
		{
			extentManager.logging_Info("Given element is not Selected in Webpage");
			flag = "true";
		}
		return flag;
	}

	@Override
	public void sendKeys(By ele, String text) 
	{
		extentManager.logging_Info("Typing value is: " + text);
		extentManager.logging_Info("Given element: " + ele);
		scrollByVisibilityOfElement(ele);
		getElement(ele).clear();
		getElement(ele).sendKeys(text);

	}

	@Override
	public void clickOnElement(By ele)
	{
		extentManager.logging_Info("Given element: " + ele);
		scrollByVisibilityOfElement(ele);
		extentManager.logging_Info("Waiting for Element to be click, Given Element is " + ele);
		getElement(ele).click();
		extentManager.logging_Info("Clicked on Given Element");

	}
	@Override
	public void actionClick(WebElement ele, By by)
	{
		WebElement ele1 = driver.findElement(by);
		Actions act = new Actions(driver);
		act.click(ele1).build().perform();
	}

	@Override
	public WebDriverWait getInstanceOfWebDriverWait(int timeInSec) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
	}

	@Override
	public void waitForElementToVisable(By ele, int timeInSec) {
		extentManager.logging_Info("Waiting for element to be visible: " + ele);
		getInstanceOfWebDriverWait(timeInSec).until(ExpectedConditions.visibilityOfElementLocated(ele));
	}

	@Override
	public void waitForTitleContains(String titleText, int timeInSec) 
	{
		extentManager.logging_Info("Waiting for title contians: " + titleText);
		getInstanceOfWebDriverWait(timeInSec).until(ExpectedConditions.titleContains(titleText));
	}

	@Override
	public void scrollByVisibilityOfElement(By ele) 
	{
		extentManager.logging_Info("Scroll by given element is " + ele);
		WebElement selectEle = getElement(ele);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", selectEle);

	}

	@Override
	public void scrollByPixelVertically(int pixelValue) {
		extentManager.logging_Info("Scroll by Pixel, Pixel value is " + pixelValue);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(0," + pixelValue + ")");

	}

	@Override
	public void scrollByPixelHorizontal(int pixelValue) {
		extentManager.logging_Info("Scroll by Pixel, Pixel value is " + pixelValue);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(" + pixelValue + ", 0)");

	}

	@Override
	public void scrollByPixelValues(int X, int Y) {
		extentManager.logging_Info("Scroll by Pixel, Pixel value is " + X + " ," + Y);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(" + X + "," + Y + ")");
	}

	@Override
	public Select getInstancesOfSelect(By ele) {
		WebElement selectEle = getElement(ele);
		return new Select(selectEle);
	}

	@Override
	public void selectByVisibleText(By ele, String visibleText) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Given Visible Text value is " + visibleText);
		getInstancesOfSelect(ele).selectByVisibleText(visibleText);
	}

	@Override
	public void selectByValue(By ele, String value) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Given Value is " + value);
		getInstancesOfSelect(ele).selectByValue(value);
	}

	@Override
	public void selectByIndex(By ele, int indexValue) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Given Index Value is " + indexValue);
		getInstancesOfSelect(ele).selectByIndex(indexValue);
	}

	@Override
	public void deSelectByVisibleText(By ele, String visibleText) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Deselecting the value by using Visible Text is " + visibleText);
		getInstancesOfSelect(ele).deselectByVisibleText(visibleText);
	}

	@Override
	public void deSelectByValue(By ele, String value) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Deselecting the value by using Value is " + value);
		getInstancesOfSelect(ele).deselectByValue(value);
	}

	@Override
	public void deSelectByIndex(By ele, int indexValue) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Deselecting the value by using Index is " + indexValue);
		getInstancesOfSelect(ele).deselectByIndex(indexValue);
	}

	@Override
	public void getAllSelectOption(By ele) {
		extentManager.logging_Info("Given element: " + ele);
		List<WebElement> allOption = getInstancesOfSelect(ele).getAllSelectedOptions();
		extentManager.logging_Info("All selected option are printed in below");
		for (WebElement selectedOption : allOption) {
			extentManager.logging_Info("Selected option is " + selectedOption.getText());
		}
	}
	
	@Override
	public void listOfElements(By ele)
	{
		extentManager.logging_Info("Given element: "+ele);
		List<WebElement> allElements = driver.findElements(ele);
		int count = 0;
		for(WebElement ele1:allElements)
		{
			String listItems = ele1.getText();
			System.out.println(listItems);
			count++;
		}
		System.out.println("Total number of items in Restaurant: "+count);
	}

	@Override
	public void deSelectAll(By ele) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Deselecting the all selected option ");
		getAllSelectOption(ele);
		getInstancesOfSelect(ele).deselectAll();
	}

	@Override
	public void getFirstSelected(By ele) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info(
				"Frist Selected option is " + "'" + getInstancesOfSelect(ele).getFirstSelectedOption().getText() + "'");
	}

	@Override
	public Alert InitiatingAlert() {
		return driver.switchTo().alert();
	}

	@Override
	public void getTextFromAlert() {
		extentManager.logging_Info("Getting the text from alert popup");
		String text = InitiatingAlert().getText();
		extentManager.logging_Info("Alert popup message: " + text);
	}

	@Override
	public void acceptAlert() {
		extentManager.logging_Info("click on Ok button in simple Alert");
		InitiatingAlert().accept();
	}

	@Override
	public void dismissAlert() {
		extentManager.logging_Info("click on Dismiss button in simple Alert");
		InitiatingAlert().dismiss();
	}

	@Override
	public void alertSendKeys(String text) {
		extentManager.logging_Info("Alert typing value: " + text);
		InitiatingAlert().sendKeys(text);
	}

	@Override
	public String getScreenShot(String fileName) 
	{
		File destiFile = null;
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		destiFile = new File(System.getProperty("user.dir") + "\\HTMLReport\\" + fileName + "_" + dateName + ".png");
		try 
		{
			FileUtils.copyFile(srcFile, destiFile);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return destiFile.getAbsolutePath();
	}

	@Override
	public String getScreenShot() throws Exception 
	{
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

	}

	@Override
	public void enterTextThroughJS(By ele, String text) 
	{
		extentManager.logging_Info("Typing value is: " + text);
		extentManager.logging_Info("Given element: " + ele);
		WebElement selectEle = getElement(ele);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].value='" + text + "'", selectEle);
		extentManager.logging_Info("Given value is enterd");
	}

	@Override
	public void clickByJS(By ele) {
		extentManager.logging_Info("Given element: " + ele);
		WebElement selectEle = getElement(ele);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", selectEle);
		extentManager.logging_Info("Clicked on given element");
	}

	@Override
	public SessionId checkBroswerSession() {
		SessionId browserSession = ((RemoteWebDriver) driver).getSessionId();
		return browserSession;
	}

	@Override
	public void openNewWindow() {
		getDriver().switchTo().newWindow(WindowType.WINDOW);
		extentManager.logging_Info("Opened new Window");
	}

	@Override
	public void openNewTab() {
		getDriver().switchTo().newWindow(WindowType.TAB);
		extentManager.logging_Info("Opened new Tab");
	}

	@Override
	public String getElementScreenshot(By ele) {
		File destiFile = null;
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File srcFile = getElement(ele).getScreenshotAs(OutputType.FILE);
		destiFile = new File(
				System.getProperty("user.dir") + ".\\Screenshot\\" + "ElementScreenshot_" + dateName + ".png");
		try {
			FileUtils.copyFile(srcFile, destiFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentManager.logging_Info("Taken element Screenshot");
		return destiFile.getAbsolutePath();
	}

	@Override
	public void clickByWebDriverWait(By ele, int timeInSec) {
		extentManager.logging_Info("Given element: " + ele);
		getInstanceOfWebDriverWait(timeInSec).until(ExpectedConditions.elementToBeClickable(ele)).click();
		extentManager.logging_Info("Clicked on Given Element");
	}

	@Override
	public void sendKeysByWebDriverWait(By ele, int timeInSec, String text) {
		extentManager.logging_Info("Given element: " + ele);
		extentManager.logging_Info("Typing value is: " + text);
		getInstanceOfWebDriverWait(timeInSec).until(ExpectedConditions.visibilityOfElementLocated(ele)).sendKeys(text);
		extentManager.logging_Info("Given text is entered");
	}

	@Override
	public Actions getInstanceOfAction() {
		return new Actions(driver);
	}
	
	@Override
	public void selectByList(By by, String value)
	{
		extentManager.logging_Info("given element: "+by);
		List<WebElement> ele = driver.findElements(by);
		for(WebElement ele1:ele)
		{
			if(ele1.getText().contains(value))
			ele1.click();
		}
		
	}
	
	@Override
	public void actionClick(WebElement ele)
	{
		extentManager.logging_Info("given element: "+ele);
		Actions action = new Actions(driver);
		action.click(ele).build().perform();
	}
	
	
	
}

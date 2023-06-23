package com.lmc.customerPages;

import org.openqa.selenium.By;
import com.lmc.utilities.FileLib;
import com.lmc.utilities.WebActionDriver;

public class LoginPage 
{
	WebActionDriver webactionDriver;
	FileLib fileLib;

	public LoginPage()
	{
		webactionDriver = new WebActionDriver();
	}
		//login page
		public static By email = By.xpath("(//input[@id='email'])[1]");
		public static By password = By.xpath("//input[@id='password']");
		//public static By signButton = By.xpath("(//button[@type='submit'])[1]");
		public static By signButton = By.xpath("//button[text()='Sign In']");
		
	public void login() throws Exception
	{
		Thread.sleep(2000);
		webactionDriver.getElement(email).sendKeys("florida_ernser@gmail.com");
		webactionDriver.getElement(password).sendKeys("Password@123");
		webactionDriver.clickByWebDriverWait(signButton, 20);
		Thread.sleep(2000);
	}
}

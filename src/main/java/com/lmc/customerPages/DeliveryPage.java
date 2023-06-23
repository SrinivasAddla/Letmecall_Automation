package com.lmc.customerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.lmc.utilities.FileLib;
import com.lmc.utilities.WebActionDriver;

public class DeliveryPage 
{
	WebActionDriver webactionDriver;
	FileLib fileLib;

	public DeliveryPage()
	{
		webactionDriver = new WebActionDriver();
	}
	
	//delivery address
	
	public static By selectDeliveryLoc = By.xpath("//input[@id='addressLine3']");
	//public static By pickUpLoc = By.xpath("//p[contains(text(),'New delivery Address')]/..//input");
	//public static By selectAddress = By.xpath("//button[text()='SELECT'] | (//button[@type='button'])[last()]");
	public static By pickUpLoc = By.xpath("(//input[@type='text'])[last()]");
	public static By selectAddress = By.xpath("(//div[@role='dialog']//button)[last()]");
	public static By addressLane = By.xpath("//input[@id='addressLine1']");	
	public static By continueToOrderSummary = By.xpath("//button[@type='submit']");
	
	public void deliveryAddress() throws Exception
	{
		webactionDriver.getElement(selectDeliveryLoc).click();
		webactionDriver.clickByWebDriverWait(pickUpLoc, 10);
		webactionDriver.getElement(pickUpLoc).sendKeys("74555 Falcon Lane Palm Desert, CA, USA",Keys.ENTER);
		Thread.sleep(5000);
		webactionDriver.clickByWebDriverWait(selectAddress, 20);
		//webactionDriver.getElement(addressLane).clear();
		Thread.sleep(10000);
		webactionDriver.getElement(addressLane).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
		webactionDriver.getElement(addressLane).sendKeys("Kachiguda, Barkatpura, Hyderabad - 500027");
		webactionDriver.getElement(continueToOrderSummary).click();
	}
}

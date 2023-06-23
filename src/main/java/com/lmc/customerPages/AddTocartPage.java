package com.lmc.customerPages;

import org.openqa.selenium.By;
import com.lmc.utilities.FileLib;
import com.lmc.utilities.WebActionDriver;

public class AddTocartPage 
{
	WebActionDriver webactionDriver;
	FileLib fileLib;

	public AddTocartPage()
	{
		webactionDriver = new WebActionDriver();
	}
	public static By logoVerify = By.xpath("(//button[@type='button']//img)[1]");
	public static By buttonsVerify = By.xpath("(//div[@id='root']//div[2]//div//div)[3]");
	
	//addtocart
	public static By clickOnALL = By.xpath("(//div[@role='tablist']//button)[1]");
	public static By addingItems = By.xpath("(//button[text()='Add to Cart'])[1]");
	public static By addtoOrder = By.xpath("//button[contains(text(),'Add To Order')]");
	//By locator = By.xpath("(//button[text()='"+cart+"'])[1]");
	
	
	
	public void restaurantLanding() throws Exception
	{
		webactionDriver.isElementDisplayed(logoVerify);
		System.out.println("After verifying logo");
		//boolean logoPresent = webactionDriver.getElement(logoVerify).isDisplayed();
		//System.out.println("Element displayed is: "+logoPresent);
		boolean buttonsVerification = webactionDriver.getElement(buttonsVerify).isDisplayed();
		System.out.println("Buttons Element is displayed: "+buttonsVerification);
	}
	
	public void selectItem() throws Exception
	{
		Thread.sleep(2000);
		webactionDriver.getElement(clickOnALL).click();
		webactionDriver.getElement(addingItems).click();
		//webactionDriver.listOfElements(listofItems);
		webactionDriver.clickByWebDriverWait(addtoOrder, 10);
	}
}

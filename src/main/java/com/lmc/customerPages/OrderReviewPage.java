package com.lmc.customerPages;

import org.openqa.selenium.By;
import com.lmc.utilities.FileLib;
import com.lmc.utilities.WebActionDriver;

public class OrderReviewPage 
{
	WebActionDriver webactionDriver;
	FileLib fileLib;

	public OrderReviewPage()
	{
		webactionDriver = new WebActionDriver();
	}
	
	//order review
	//public static By timeSlot = By.xpath("//div[@class='swiper-button-next']");
	public static By timeSelection = By.xpath("//div[@class='swiper-wrapper']//span");
	public static By continueToPayment = By.xpath("(//button[text()='Continue To Payment'])[last()]");
	
	
	public void orderReview()
	{
		//webactionDriver.getElement(timeSlot).click();
		webactionDriver.selectByList(timeSelection, "02:00 AM - 02:30 AM");
		webactionDriver.getElement(continueToPayment).click();
		
	}
}

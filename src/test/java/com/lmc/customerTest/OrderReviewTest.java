package com.lmc.customerTest;

import org.testng.annotations.Test;
import com.lmc.customerPages.AddTocartPage;
import com.lmc.customerPages.CheckoutPage;
import com.lmc.customerPages.DeliveryPage;
import com.lmc.customerPages.LoginPage;
import com.lmc.customerPages.OrderReviewPage;
import com.lmc.utilities.FileLib;
import com.lmc.utilities.WebActionDriver;
import configPropertyFile.Config_Properties;
import lmcBase.BaseLmc;

public class OrderReviewTest extends BaseLmc
{
	private static WebActionDriver webactionDriver;
	private static FileLib fileLib; 
	public static String url;
	
	public AddTocartPage addToCartPage;
	public CheckoutPage checkoutPage;
	public LoginPage loginPage;
	public DeliveryPage deliverPage;
	public OrderReviewPage orderReview;
	
	public OrderReviewTest() 
	{
		addToCartPage = new AddTocartPage();
		checkoutPage = new CheckoutPage();
		loginPage = new LoginPage();
		deliverPage = new DeliveryPage();
		orderReview = new OrderReviewPage();
		webactionDriver = new WebActionDriver();
		fileLib=new FileLib();
	}
	
	@Test
	public void orderReview() throws Throwable
	{
		url = fileLib.getPropertyData(Config_Properties.URL);
		webactionDriver.getURL(url);
		addToCartPage.restaurantLanding();
		addToCartPage.selectItem();
		checkoutPage.checkout();
		loginPage.login();
		deliverPage.deliveryAddress();
		orderReview.orderReview();
		
	}
}

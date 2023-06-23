package com.lmc.customerTest;

import org.testng.annotations.Test;

import com.lmc.customerPages.AddTocartPage;
import com.lmc.customerPages.CheckoutPage;
import com.lmc.customerPages.LoginPage;
import com.lmc.utilities.FileLib;
import com.lmc.utilities.Groups;
import com.lmc.utilities.WebActionDriver;

import configPropertyFile.Config_Properties;
import lmcBase.BaseLmc;

public class LoginTest extends BaseLmc
{
	private static WebActionDriver webactionDriver;
	private static FileLib fileLib; 
	public static String url;
	public AddTocartPage addToCartPage;
	public CheckoutPage checkoutPage;
	public LoginPage loginPage;
	
	public LoginTest() 
	{
		addToCartPage = new AddTocartPage();
		checkoutPage = new CheckoutPage();
		loginPage = new LoginPage();
		
		webactionDriver = new WebActionDriver();
		fileLib=new FileLib();
	}
	
	@Test(groups = Groups.LMCP0)
	public void loginTest() throws Throwable
	{
		
		url = fileLib.getPropertyData(Config_Properties.URL);
		webactionDriver.getURL(url);
		addToCartPage.restaurantLanding();
		addToCartPage.selectItem();
		checkoutPage.checkout();
		loginPage.login();
	}
}

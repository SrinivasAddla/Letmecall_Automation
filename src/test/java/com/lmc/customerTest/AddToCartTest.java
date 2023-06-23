package com.lmc.customerTest;

import org.testng.annotations.Test;
import com.lmc.customerPages.AddTocartPage;
import com.lmc.utilities.FileLib;
import com.lmc.utilities.Groups;
import com.lmc.utilities.WebActionDriver;
import configPropertyFile.Config_Properties;
import lmcBase.BaseLmc;

public class AddToCartTest extends BaseLmc
{
	private static WebActionDriver webactionDriver;
	private static FileLib fileLib; 
	public static String url;
	
	public AddTocartPage addToCartPage;
	
	public AddToCartTest() 
	{
		addToCartPage = new AddTocartPage();
		webactionDriver = new WebActionDriver();
		fileLib=new FileLib();
	}
	
	@Test(groups = Groups.LMCP0)
	public void addToCartTest() throws Throwable
	{
		
		url = fileLib.getPropertyData(Config_Properties.URL);
		webactionDriver.getURL(url);
		addToCartPage.restaurantLanding();
		addToCartPage.selectItem();
		
	}
}

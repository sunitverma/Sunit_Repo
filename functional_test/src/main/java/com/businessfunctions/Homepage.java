package com.businessfunctions;

import com.library.Common;

public class Homepage {
	 
	Common browser;
	//constructor with one argument.
	public Homepage(Common br)
	{
	        browser=br;
	}
	   
	// K - Method - to verify the all accounts    
	public void verifyHomepageAllAccounts()
	{
		browser.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[1]");
    	browser.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[1]");
		System.out.println("Saving account is available");
		browser.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[2]");
		browser.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[2]");
    	System.out.println("Loan account is available");
    	browser.screenshot();
	}
	
	// K - Method - to verify one account
	public void verifyHomepageOneAccounts()
	{
		browser.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[1]");
		browser.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[1]");
		browser.screenshot();
		System.out.println("Loan account is available");
	}
	
	// S - Method - to verify no account
	public void verifyHomepageNoAccounts() throws InterruptedException
	{		
		browser.sleepThread(2000);
		browser.verifyText("xpath", "//*[@content-desc='disclaimerMessage']","Don't see all your accounts?");
		browser.screenshot();
		System.out.println("No accounts are available");
	}

	// S - Method - to verify animation displayed on Landing page
	public void animationOnLanding()
	{

	  try
	  {
	  if (browser.verifyElementPresent("xpath", "//*[@content-desc='spinnerText']")==true)
	  {
	    if (browser.getText("xpath", "//*[@content-desc='spinnerText']").equals("One moment please"))
	    {
	      browser.screenshot();
	      System.out.println("Correct anitmation message displayed - One moment please");
	    }
	    else
	    {
	      browser.screenshot();
	      System.out.println("Animation message on Landing page is not correct");
	    }
	    browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
	    browser.verifyElementPresent("xpath", "//*[@text[starts-with(.,'Good')]]");
	  }
	  else
	  {
	    browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
	    System.out.println("Animation not displayed on Landing page");
	  }
	}
	  catch (Exception e)
	  {
	    System.out.println(e);  
	  }
	}
}

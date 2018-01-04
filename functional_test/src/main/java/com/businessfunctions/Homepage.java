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
    	browser.screenShot();
	}
	
	// K - Method - to verify one account
	public void verifyHomepageOneAccounts()
	{
		browser.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[1]");
		browser.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[1]");
		browser.screenShot();
		System.out.println("Loan account is available");
	}
	
	// S - Method - to verify no account
	public void verifyHomepageNoAccounts() throws InterruptedException
	{		
		browser.sleepThread(2000);
		browser.verifyText("xpath", "//*[@content-desc='disclaimerMessage']","Don't see all your accounts?");
		browser.screenShot();
		System.out.println("No accounts are available");
	}

	// S - Method - to verify animation displayed on Landing page
	public void animationOnLanding()
	{
	  if (browser.getSize("xpath", "//*[@content-desc='spinnerText']")!=0)
	  {
	    if (browser.getText("xpath", "//*[@content-desc='spinnerText']").equals("One moment please"))
	    {
	      browser.screenShot();
	      System.out.println("Correct anitmation message displayed - One moment please");
	    }
	    else
	    {
	      browser.screenShot();
	      System.out.println("Animation message on Landing page is not correct");
	    }
	    browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton' or @content-desc='welcomeName']");
	  }
	  else
	  {
	    browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton' or @content-desc='welcomeName']");
	    System.out.println("Animation not displayed on Landing page");
	  }
	}
}
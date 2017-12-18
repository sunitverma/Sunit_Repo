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
	
	// K - Method - to verify no account
	public void verifyHomepageNoAccounts() throws InterruptedException
	{		
		browser.sleepThread(2000);
		browser.verifyText("xpath", "//*[@content-desc='disclaimerMessage']","Don't see all your accounts?");
		browser.screenshot();
		System.out.println("No accounts are available");
		
	}
}

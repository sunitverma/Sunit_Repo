package com.businessfunctions;

import com.library.Common;
import com.testscripts.RootTest;

public class Homepage extends RootTest {
	 
	 static Login login=new Login(brow);
	 Common browser;
	 
	 //constructor with one argument.
	    public void Home(Common br)
	    {
	        browser=br;
	    }
	   
	// K - Method - to verify the all accounts    
	public void verifyHomepageAllAccounts()
	
	{
		brow.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[1]");
    	brow.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[1]");
		System.out.println("Saving account is available");
    	brow.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[2]");
    	brow.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[2]");
    	System.out.println("Loan account is available");
    	brow.screenshot();
	}
	
	// K - Method - to verify one account
	public void verifyHomepageOneAccounts()
	{
		brow.waitUntilElementPresent("(//*[@content-desc='accountCardType'])[1]");
		brow.verifyElementPresent("xpath", "(//*[@content-desc='accountCardType'])[1]");
		brow.screenshot();
		System.out.println("Loan account is available");
	}
	
	// K - Method - to verify no account
	public void verifyHomepageNoAccounts() throws InterruptedException
	{		
		// Need to verify the Message
		brow.sleepThread(2000);
		brow.verifyText("xpath", "//*[@content-desc='disclaimerMessage']","Don't see all your accounts?");
		brow.screenshot();
		System.out.println("No accounts are available");
	}
	
	// S - Method - to verify feedback function
	public void feedback()
	{
		
		
	}
}

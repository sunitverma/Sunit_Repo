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
	   
	/**
	 * Need to check with chequeing 
	 */
	    
	// K - Method - to verify the all accounts    
	public void verifyHomepageAllAccounts()
	
	{
		brow.waitUntilElementPresent("//*[@text='Savings']");
    	brow.verifyElementPresent("xpath", "//*[@text='Savings']");
		System.out.println("Saving account is available");
    	brow.waitUntilElementPresent("//*[@text='Loan']");
    	brow.verifyElementPresent("xpath", "//*[@text='Loan']");
    	brow.screenshot();
    	System.out.println("Loan account is available");
		//System.out.println("Chequeing account is available");
	}
	
	// K - Method - to verify one account
	public void verifyHomepageOneAccounts()
	{
		brow.waitUntilElementPresent("//*[@class='android.view.ViewGroup' and ./*[@text='Savings']]");
		brow.verifyElementPresent("xpath", "//*[@class='android.view.ViewGroup' and ./*[@text='Savings']]");
		brow.screenshot();
		System.out.println("Loan account is available");
	}
	
	// K - Method - to verify no account
	public void verifyHomepageNoAccounts() throws InterruptedException
	{		
		// Need to verify the Message
		Thread.sleep(10000);
		brow.verifyElementPresent("xpath", "//*[@text=\"Don't see all your accounts?\"]");
		brow.screenshot();
		System.out.println("No accounts are available");
	}
}

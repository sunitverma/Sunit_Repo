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
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='Savings']]")!=0)
        {
            System.out.println("Saving account is available");
        }
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='Loan']]")!=0)
        {
            System.out.println("Loan account is available");
        }
        else
            {
                System.out.println("Neither Savings nor Loan account is available");
            }
        browser.screenShot();
    }
	
	// K - Method - to verify one account
    public void verifyHomepageOneAccounts()
    {
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='Savings']]")!=0)
        {
            System.out.println("Saving account is available");
        }
        else if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='Loan']]")!=0)
        {
            System.out.println("Loan account is available");
        }
        else {
            System.out.println("Neither Savings nor Loan account is available");
        }
        browser.screenShot();
    }
	
	// S - Method - to verify no account
    public void verifyHomepageNoAccounts() throws InterruptedException
    {       
        browser.sleepThread(2000);
        if(browser.getSize("xpath", "//*[@content-desc='alertMessage']")!=0)
        {
            System.out.println("No accounts are available");
        }
        browser.screenShot();
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
	
	//S - Method - to verify transfer button working for saving and Chequing accounts on landing page
    public void transferButtonOnLandingPage(String accounttype)
    {
      browser.click("xpath", "(//*[@content-desc='accountCard' and *[@text='"+ accounttype + "'] and *[@text>0]]//*[@content-desc='Transfer Button'])[1]");
      browser.verifyText("xpath", "//*[@content-desc='TransferHeader']", "Where would you like to transfer to ?");
      browser.verifyElementPresent("xpath", "//*[@content-desc='backButton']");
      browser.click("xpath", "//*[@content-desc='backButton']");
      browser.verifyText("xpath", "//*[@content-desc='welcomeName']", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
      if(browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Loan']]//*[@content-desc='Transfer Button']")!=0)
      {
          System.out.println("The Transfer button is there for Loan account");
      }
      {
          System.out.println("The Transfer button is not there for Loan account and its as expected");
      }
    }
}
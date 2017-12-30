package com.businessfunctions;

import com.library.Common;

public class ViewAccounts {
	
	 Common browser;
	 //constructor with one argument.
	 public ViewAccounts(Common br)
	 {
	    browser=br;
	 }

	// K - Method - to verify 10 transactions on page
	public void viewAccount()
	{
        int count=browser.getCount("xpath","(//*[@contentDescription='accountText'])");
        if(count==10)
        {              
        	System.out.println("Able to get the top 10 transactions");
        }
        else
        {
        	System.out.println("The given account have transactions less than 10");
        }
	}

	// S - Method - to verify animation displayed on Account details  page
    public void animationOnAccountDetails()
    {
      try
      {
      if (browser.verifyElementPresent("xpath", "//*[@content-desc='spinnerText']"))
      {
        if (browser.getText("xpath", "//*[@content-desc='spinnerText']").equals("One moment please"))
        {
          browser.screenshot();
          System.out.println("Correct anitmation message displayed - One moment please");
        }
        else
        {
          browser.screenshot();
          System.out.println("Animation message on Account page is not correct");
        }
        browser.waitUntilElementPresent("//*[@content-desc='backButton']");
        browser.verifyElementPresent("xpath", "//*[@text='My Accounts']");
      }
      }
      catch (Exception e)
      {
        browser.waitUntilElementPresent("//*[@content-desc='backButton']");
        System.out.println("Animation not displayed on Account page");
      }
    }
}
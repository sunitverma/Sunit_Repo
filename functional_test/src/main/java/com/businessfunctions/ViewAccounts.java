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
    
	// S - Method - to verify animation displayed on Account Details page
    public void animationOnAccountDetail()
    {
      if (browser.getSize("xpath", "//*[@content-desc='spinnerText']")!=0)
      {
        browser.screenShot();
        System.out.println("Animation displayed on Account details page");
        browser.waitUntilElementPresent("//*[@text='My Accounts']");
      }
      else
      {
        browser.screenShot();
        System.out.println("Animation not displayed on Landing page");
        browser.waitUntilElementPresent("//*[@text='My Accounts']");
      }
    }
}
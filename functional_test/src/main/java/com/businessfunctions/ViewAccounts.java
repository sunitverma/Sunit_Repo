package com.businessfunctions;

import com.library.Common;

public class ViewAccounts {
	
	 Common browser;
	 //constructor with one argument.
	 public ViewAccounts(Common br)
	 {
	    browser=br;
	 }
	 
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
}
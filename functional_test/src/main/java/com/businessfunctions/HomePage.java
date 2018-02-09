package com.businessfunctions;

import com.library.Common;

public class HomePage {
     
    Common browser;
    
    //constructor with one argument.
    public HomePage(Common br)
    {
        browser=br;
    }
       
    // K - Method - to verify the available accounts    
    public void verifyHomepageAccounts()
    {
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Savings']]") != 0)
        {
            System.out.println("Savings account is available");
        }
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Chequing']]") != 0)
        {
            System.out.println("Chequing account is available");
        }
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Loan']]") != 0)
        {
            System.out.println("Loan account is available");
        }
        else
        {
            System.out.println("Neither Savings or Chequing nor Loan account is available");
        }
    }
    
    // K - Method - to verify no account
    public void verifyHomepageNoAccounts()
    {       
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        
        if(browser.getSize("name", "alertMessage") != 0)
        {
            String msg = browser.getText("name", "alertMessage");
            System.out.println(msg);
            System.out.println("No accounts are available");
        }
    }

    // S - Method - to verify transfer button not available for Loan accounts on landing page
    public void transferButtonNotForLoan(String accounttype)
    {
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='" + accounttype + "']]//*[@content-desc='Transfer Button']")!=0)
        {
            System.out.println("The Transfer button is there for Loan account");
        }
        else
        {
            System.out.println("The Transfer button is not there for Loan account and its as expected");
        }
    }
    
    // S - Method - to verify transfer button working for saving and Chequing accounts on landing page and back button working
    public void transferButtonOnLandingPage(String accounttype)
    {
        browser.click("xpath", "(//*[@content-desc='accountCard' and *[@text='" + accounttype + "']]//*[@content-desc='Transfer Button'])[1]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyText("name", "TransferHeader", "Where would you like to transfer to?");
        browser.verifyElementPresent("name", "backButton");
        browser.click("name", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("name", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
}
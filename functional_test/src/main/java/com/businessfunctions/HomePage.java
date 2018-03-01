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
    public void homePageWithAccounts()
    {
        if ((browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Savings']]") != 0) || (browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Chequing']]") != 0) || (browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='Loan']]") != 0))
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
        }
        else
        {
            System.out.println("Neither Savings or Chequing nor Loan account is available");
        }
    }
    
    // K - Method - to verify no account
    public void homePageWithoutAccounts()
    {       
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        
        if(browser.getSize("accessibilityId", "alertMessage") != 0)
        {
            String msg = browser.getText("accessibilityId", "alertMessage");
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
        browser.verifyText("accessibilityId", "TransferHeader", "Where would you like to transfer to?");
        browser.verifyElementPresent("accessibilityId", "backButton");
        browser.click("accessibilityId", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
}
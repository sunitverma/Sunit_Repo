package com.businessfunctions;

import com.library.Common;

public class ViewAccounts {
    
    Common browser;
    
    //constructor with one argument.
    public ViewAccounts(Common br)
    {
        browser=br;
    }

    // K - Method - to verify 10 transactions on page // update count to 50 --05/02/2018
    public void viewAccount()
    {
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='Savings']]");
        
        browser.waitUntilElementPresent("//*[@content-desc='accountType']");
      
        int count=browser.getCount("name", "merchantText");
        if(count==50)
        {              
            System.out.println("Able to get the top 50 transactions");
        }
        else
        {
            System.out.println("The given account have transactions less than 50");
        }
    }
     
    // S - Method - to verify animation displayed on Account Details page
    public void animationOnAccountDetail()
    {
        browser.click("xpath", "//*[@content-desc='accountCard'][1]");
        browser.screenShot();
        
        if (browser.getSize("name", "spinnerText") != 0)
        {
            System.out.println("Animation displayed on Account details page");
            browser.waitUntilElementPresent("//*[@text='My Accounts']");
        }
        else
        {
            System.out.println("Animation not displayed on Account details page");
            browser.waitUntilElementPresent("//*[@text='My Accounts']");
        }
    }
        
    // S - Method - to verify transfer button working on saving and Chequing account details page and back button working
    public void transferButtonOnAccountDetailsPage(String accounttype)
    {
        browser.click("xpath", "(//*[@content-desc='accountCard' and *[@text='"+ accounttype + "']])[1]");
        browser.waitUntilElementPresent("//*[@content-desc='Transfer Button' and ./preceding-sibling::*[./*[@class='android.view.View']]]");
        
        browser.click("xpath", "//*[@content-desc='Transfer Button' and ./preceding-sibling::*[./*[@class='android.view.View']]]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        
        browser.verifyText("name", "TransferHeader", "Where would you like to transfer to?");
        browser.verifyElementPresent("name", "backButton");
        
        browser.click("name", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("name", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
}
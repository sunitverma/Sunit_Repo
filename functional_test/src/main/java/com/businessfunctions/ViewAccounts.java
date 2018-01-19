package com.businessfunctions;

import com.library.Common;

public class ViewAccounts{
    
    Common browser;
    //constructor with one argument.
    
    public ViewAccounts(Common br)
    {
        browser=br;
    }

    // K - Method - to verify 10 transactions on page
    public void viewAccount()
    {
        int count=browser.getCount("name", "accountText");
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
        if (browser.getSize("name", "spinnerText")!=0)
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
        
    //S - Method - to verify transfer button working on saving and Chequing account details page
    public void transferButtonOnAccountDetailsPage(String accounttype)
    {
        browser.click("xpath", "(//*[@content-desc='accountCard' and *[@text='"+ accounttype +"']])[1]");
        browser.verifyElementPresent("xpath", "//*[@contentDescription='Transfer Button' and ./preceding-sibling::*[./*[@class='android.view.View']]]");
        browser.click("xpath", "//*[@contentDescription='Transfer Button' and ./preceding-sibling::*[./*[@class='android.view.View']]]");
        browser.verifyText("name", "TransferHeader", "Where would you like to transfer to ?");
        browser.verifyElementPresent("name", "backButton");
        browser.click("name", "backButton");
        browser.verifyText("name", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
}
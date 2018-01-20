package com.businessfunctions;

import com.library.Common;

public class TransferPage {

    Common browser;
    //constructor with one argument.
    
    public TransferPage(Common br)
    {
        browser=br;
    }
    
    //K - Method - to verify me to me transfers
    public void meToMeTransfer(String Currency) throws InterruptedException
    {
        if(browser.verifyElementPresent("xpath", "//*[@class='android.widget.ImageView' and ./parent::*[@contentDescription='Next Button Disabled']]"))
        {
            browser.click("xpath", "//*[@contentDescription='accountCard' and ./parent::*[./parent::*[./preceding-sibling::*[@class='android.view.ViewGroup']]] and ./*[@text='Savings']]");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./parent::*[@contentDescription='Next Button Enabled']]");
        }   
        Thread.sleep(5000);
        
        if(browser.verifyElementPresent("name", "Next Button Disabled"))
        {
            browser.getText("name", "MoneyInput");
            browser.sendKeys("name", "MoneyInput", "1");
            browser.click("name", "TransferHeader");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
            browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
            browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])["+ Currency +"])");
            //browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
            browser.click("name", "Next Button Enabled");
            browser.verifyElementPresent("name", "sourceAccountTitle");
            browser.click("xpath", "//*[@text='Cancel']");
            
            if(browser.getSize("name", "cancelModalLogoutButton")!=0)
            {
                browser.click("name", "cancelModalLogoutButton"); 
                browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
            }
        }
    }
     
    //K - Method - to verify me to me transfers on accounts from Account List page
    public void meToMeTransfersAccountListPage(String AccountType,String AccountNumber) throws InterruptedException
    {
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='"+ AccountType +"']]")!=0)
        {
            String Currency =browser.getText("xpath", "//*[@text='JMD' and  ./preceding-sibling::*[@text='435335217'] and ./preceding-sibling::*[@text='Savings']]");
            System.out.println("currency is" +Currency);
            browser.click("xpath", "//*[@text='TRANSFER' and ./parent::*[./preceding-sibling::*[@text='" + AccountNumber + "']]]");
        }
    }
     
    //K - Method - to verify me to me transfers on accounts from Account Summary page
    public void meToMeTransfersAccountSummaryPage(String AccountType,String AccountNumber) throws InterruptedException
    {
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='"+ AccountType +"']]")!=0)
        {
            browser.click("xpath", "//*[@contentDescription='accountCard' and ./*[@text='"+ AccountNumber +"'] and ./*[@text='"+ AccountType +"']]");
            browser.click("xpath", "//*[@contentDescription='Transfer Button' and ./preceding-sibling::*[@class='android.widget.ScrollView']]");
        }
    }
     
    //K - Method - to verify me to you transfers button
    public void meToYouTransferButton() throws InterruptedException
    {
        Thread.sleep(5000);
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    }
    
    //K - Method - to verify me to you transfers
    public void meToYouTransfer(String Currency,String Nickname)
    {
        if(browser.getSize("xpath","//*[@content-desc='Next Button Disabled' and @width>0]")!=0)
        {
            browser.click("xpath", "//*[@contentDescription='accountCard' and ./*[@text='"+ Nickname +"']]");
            browser.click("xpath", "//*[@contentDescription='Next Button Enabled']");
            if(browser.getSize("name", "Next Button Disabled")!=0)
            {
                browser.getText("name", "MoneyInput");
                browser.sendKeys("name", "MoneyInput", "1");
                browser.click("name", "TransferHeader");
                browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
                browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
                browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])["+ Currency +"])");
                //browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
                browser.click("name", "Next Button Enabled");
                browser.verifyElementPresent("name", "sourceAccountTitle");
                browser.click("xpath", "//*[@text='Cancel']");
                
                if(browser.getSize("name", "cancelModalLogoutButton")!=0)
                {
                    browser.click("name", "cancelModalLogoutButton"); 
                    browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
                }
            }
        }
    }
    
    //S - Method - to verify transfer flowpage
    public void transferFlowMeToMe(String accountfromtype, String amount)
    {    
        String accountfromno = browser.getText("xpath", "(//*[@content-desc='accountCard' and *[@text='"+ accountfromtype +"']]//*[@content-desc='accountCardNumber'])[1]");
        String accounttono = browser.getText("xpath", "(//*[@content-desc='accountCard' and *[@accountCardType='Savings' or 'Chequing']]//*[@content-desc='accountCardNumber'][not(@text='"+ accountfromno +"')])[1]");
        String accounttotype = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accounttono + "']]//*[@content-desc='accountCardType']");
        System.out.println(accountfromno + " " + accounttono + " " + accounttotype);
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfromno +"']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String trunck = browser.subString(accounttono, 5, 9);
        browser.click("xpath", "//*[@text='  ••"+ trunck +"']");
        System.out.println("account no are correct for account from and account to");
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.sendKeys("name", "MoneyInput", amount);
        browser.keyboardKeys(66);
        String currency = browser.getText("xpath" , "(//*[@class='android.widget.EditText'])[2]");
        browser.click("name", "Next Button Enabled");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("name", "sourceAccountTitle", "Send from");
        browser.verifyText("name", "destinationAccountTitle", "to");
        browser.verifyText("name", "transferAmountTitle", "for the amount of");
        browser.verifyElementPresent("name", "submitTransferButton");
        browser.verifyText("name", "sourceAccountTypeAndNumber", accountfromtype + "  ••" + browser.subString(accountfromno, 5, 9) );
        browser.verifyText("name", "destinationAccountTypeAndNumber", accounttotype + "  ••" + browser.subString(accounttono, 5, 9) );
        browser.verifyText("name", "transferCurrency", currency);
        browser.verifyText("name", "transferAmount", "$"+amount+"0.00");
        browser.click("name", "submitTransferButton");
        browser.sleepThread(5000);
    }
}
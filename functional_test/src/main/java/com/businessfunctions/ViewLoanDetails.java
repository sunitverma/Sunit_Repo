package com.businessfunctions;

import com.library.Common;

public class ViewLoanDetails {
    
    Common browser;
    //constructor with one argument.
    
    public ViewLoanDetails(Common br)
    {
        browser=br;
    }
    
    //Verify the details on Loan Details page -- S
    public void viewLoanDetails()
    {
        browser.waitUntilElementPresent("(//*[@class='android.widget.TextView'])[58]");
        browser.verifyText("xpath", "//*[@text='My Accounts']", "My Accounts");
        
        browser.verifyText("name", "accountType", browser.getText("xpath", "//*[@text[starts-with(.,'Loan •')]]"));
        browser.verifyText("name", "accountNumber", " 351158298");
        
        browser.verifyText("name", "balanceTypeTitle", "Loan Balance");
        browser.verifyText("name", "balanceAmount","471,610.30");
        browser.verifyText("name", "(//*[@class='android.widget.TextView'])[72]","JMD");
        
        browser.verifyText("name", "Next Payment", "Next Payment");
        browser.verifyText("name", "nextPaymentDueDate" ,"Mar 30, 2017");
        browser.verifyText("name", "installmentAmount" ,"$15,788.26");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[76]","JMD");
            
        browser.verifyText("name", "Loan Period","Loan Period");
        browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][1]","60 months");
        browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][2]","22/Jun/15 - 30/Jun/20");
    }
    
    //Verify the details on paid loan account Details page -- S
    public void zeroLoanBalance()
    {
        browser.waitUntilElementPresent("(//*[@class='android.widget.TextView'])[53]");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[53]" , "My Accounts");
        
        browser.verifyText("name", "accountNumber", " 021035270");
        
        browser.verifyText("name", "balanceTypeTitle", "Loan Balance");
        browser.verifyText("name", "balanceAmount","0.00");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[62]","JMD");
            
        browser.verifyText("name", "Next Payment']", "Next Payment");
        browser.verifyText("name", "nextPaymentDueDate" ,"Dec 32, 1999");
        browser.verifyText("name", "installmentAmount" ,"$0.00");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[66]"," N/A");
            
        browser.verifyText("name", "Loan Period","Loan Period");
        browser.verifyText("xpath", "(//*[@content-desc='loanPeriod'])[1]","0 months");
        browser.verifyText("xpath", "(//*[@content-desc='loanPeriod'])[2]","31/Dec/99 - 32/Dec/99");
            
        browser.click("name", "backButton");
            
        browser.verifyText("name", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
        System.out.println("Account summary page displayed after click on back arrow on loan account details");
    }
}
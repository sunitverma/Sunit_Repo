package com.businessfunctions;

import com.library.Common;

public class ViewLoanDetails {
    
    Common browser;
    
    //constructor with one argument.
    public ViewLoanDetails(Common br)
    {
        browser=br;
    }
    
    // S - Verify the details on Loan Details page
    public void viewLoanDetails(String accountno)
    {
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
      
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountno + "']]");
        browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
        browser.verifyText("xpath", "//*[@text='My Accounts']", "My Accounts");
        
        browser.verifyText("name", "accountType", browser.getText("xpath", "//*[@text[starts-with(.,'Loan •')]]"));
        browser.verifyText("name", "accountNumber", " " + accountno);
        
        browser.verifyText("name", "balanceTypeTitle", "Loan Balance");
        browser.verifyText("name", "balanceAmount", "348,432.95");
        browser.verifyText("name", "accountCurrency", "JMD");
        
        browser.verifyText("name", "Next Payment", "Next Payment");
        browser.verifyText("name", "nextPaymentDueDate" , "Jan 28, 2018");
        browser.verifyText("name", "installmentAmount" , "$68,446.11");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[71]", "JMD");
            
        browser.verifyText("name", "Loan Period", "Loan Period");
        browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][1]", "60 months");
        browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][2]", "20/May/13 - 28/May/18");
    }
    
    // S - Verify the details on paid loan account Details page
    public void zeroLoanBalance(String accountno)
    {
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
      
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountno + "']]");
        browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
        browser.verifyText("xpath", "//*[@text='My Accounts']", "My Accounts");
        
        browser.verifyText("name", "accountNumber", " " + accountno);
        
        browser.verifyText("name", "balanceTypeTitle", "Loan Balance");
        browser.verifyText("name", "balanceAmount", "0.00");
        browser.verifyText("name", "accountCurrency", "JMD");
            
        browser.verifyText("name", "Next Payment", "Next Payment");
        browser.verifyText("name", "nextPaymentDueDate", "Jan 28, 2018");
        browser.verifyText("name", "installmentAmount", "$0.00");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[71]", " N/A");
            
        browser.verifyText("name", "Loan Period", "Loan Period");
        browser.verifyText("xpath", "(//*[@content-desc='loanPeriod'])[1]", "0 months");
        browser.verifyText("xpath", "(//*[@content-desc='loanPeriod'])[2]", "31/Dec/99 - 32/Dec/99");
            
        browser.click("name", "backButton");
            
        browser.verifyText("name", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
        System.out.println("Account summary page displayed after click on back arrow on loan account details");
    }
}
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
    public void viewLoanDetail(String accountno) throws Exception
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountno + "']]");
        browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
        browser.verifyText("xpath", "//*[@text='My Accounts']", "My Accounts");
        
        browser.verifyText("accessibilityId", "accountType", browser.getText("xpath", "//*[@text[starts-with(.,'Loan •')]]"));
        browser.verifyText("accessibilityId", "accountNumber", " " + accountno);
        
        browser.verifyText("accessibilityId", "balanceTypeTitle", "Loan Balance");
        browser.verifyText("accessibilityId", "balanceAmount", "348,432.95");
        browser.verifyText("accessibilityId", "accountCurrency", "JMD");
        
        browser.verifyText("accessibilityId", "Next Payment", "Next Payment");
        browser.verifyText("accessibilityId", "nextPaymentDueDate" , "Jan 28, 2018");
        browser.verifyText("accessibilityId", "installmentAmount" , "$68,446.11");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[71]", "JMD");
            
        browser.verifyText("accessibilityId", "Loan Period", "Loan Period");
        browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][1]", "60 months");
        browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][2]", "20/May/13 - 28/May/18");
    }
    
    // S - Verify the details on paid loan account Details page
    public void zeroLoanBalance(String accountno)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountno + "']]");
        browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
        browser.verifyText("xpath", "//*[@text='My Accounts']", "My Accounts");
        
        browser.verifyText("accessibilityId", "accountNumber", " " + accountno);
        
        browser.verifyText("accessibilityId", "balanceTypeTitle", "Loan Balance");
        browser.verifyText("accessibilityId", "balanceAmount", "0.00");
        browser.verifyText("accessibilityId", "accountCurrency", "JMD");
            
        browser.verifyText("accessibilityId", "Next Payment", "Next Payment");
        browser.verifyText("accessibilityId", "nextPaymentDueDate", "Jan 28, 2018");
        browser.verifyText("accessibilityId", "installmentAmount", "$0.00");
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[71]", " N/A");
            
        browser.verifyText("accessibilityId", "Loan Period", "Loan Period");
        browser.verifyText("xpath", "(//*[@content-desc='loanPeriod'])[1]", "0 months");
        browser.verifyText("xpath", "(//*[@content-desc='loanPeriod'])[2]", "31/Dec/99 - 32/Dec/99");
            
        browser.click("accessibilityId", "backButton");
            
        browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
        System.out.println("Account summary page displayed after click on back arrow on loan account details");
    }
}
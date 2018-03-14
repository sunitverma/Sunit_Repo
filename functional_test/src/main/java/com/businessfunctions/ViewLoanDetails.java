package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class ViewLoanDetails {
    
    Common browser;
    
    //constructor with one argument.
    public ViewLoanDetails(Common br) {
      browser = br;
    }
        
    // S - Verify the details on Loan Details page
    public void viewLoanDetail() {
      
      try {
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]") != 0) {
          String accountNo = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]][1]//*[@content-desc='accountCardNumber']");
          String accountBal = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]][1]//*[@content-desc='accountCardBalanceAmount']");
          String currencySign = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]][1]//*[@content-desc='accountCardBalanceNegative']");
          String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]][1]//*[@content-desc='accountCardBalanceCurrency']");
          
          browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]][1]");
          browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
          
          //Verify the details on Loan account details page
          browser.verifyText("accessibilityId", "accountType", "LOAN");
          browser.verifyText("accessibilityId", "balanceTypeTitle", accountNo);
          browser.verifyText("accessibilityId", "balanceAmount", currencySign + accountBal);
          browser.verifyText("accessibilityId", "accountCurrency", " " + currency);
          
          browser.verifyText("accessibilityId", "Next Payment", "Next Payment");
          browser.verifyElementPresent("accessibilityId", "nextPaymentDueDate");
          browser.verifyElementPresent("accessibilityId", "installmentAmount");
          browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[88]", " JMD");
              
          browser.verifyText("accessibilityId", "Loan Period", "Loan Period");
          browser.verifyElementPresent("xpath", "//*[@content-desc='loanPeriod'][1]");
          browser.verifyElementPresent("xpath", "//*[@content-desc='loanPeriod'][2]");
        }
        else {
          System.out.println("No Loan account is avaliable for this user. Please use another user for automation testing which have Loan account.");
        }
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
    
    // S - Verify the details on paid loan account Details page
    public void zeroLoanBalance(String accountno) {
      
      try {
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
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
}   
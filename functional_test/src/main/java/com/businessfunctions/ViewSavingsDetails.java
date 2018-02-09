package com.businessfunctions;

import com.library.Common;

public class ViewSavingsDetails {
       
    Common browser;
    
    //constructor with one argument.
    public ViewSavingsDetails(Common br)
    {
        browser=br;
    }
    
    // K - Savings account balance breakdown
    public void savingAccountBalanceBreakdown()
    {
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.click("xpath", "(//*[@content-desc='accountCard'])[1]");
      
        String accountType= browser.getText("name", "accountType").toString();
        String accountNumber= browser.getText("name", "accountNumber").toString();
        System.out.printf("Account Type: %s Account Number: %s\n", accountType, accountNumber);
            
        String availableBalance = browser.getText("name", "availableBalance");
        String availableAmount = browser.getText("name", "availableBalanceBalanceAmount");
        System.out.printf("Balance Type: %s , Available Amount: %s\n", availableBalance, availableAmount);
            
        String lienBalance = browser.getText("name", "lienBalance)");
        String lienBalanceAmount = browser.getText("name", "lienBalanceBalanceAmount");
        System.out.printf("Balance Type: %s , Lien Balance Amount: %s\n", lienBalance, lienBalanceAmount);
            
        String unclearedBalance = browser.getText("name", "unclearBalance");
        String unclearedBalanceAmount = browser.getText("name", "unclearBalanceBalanceAmount");
        System.out.printf("Balance Type: %s , Uncleared Balance Amount: %s\n", unclearedBalance, unclearedBalanceAmount);
        
        String balanceType = browser.getText("name", "balanceTypeTitle");
        String balanceAmount = browser.getText("name", "balanceAmount");
        System.out.printf("Balance Type: %s , Balance Amount: %s\n", balanceType, balanceAmount);
        browser.scrollDown("Down", 200, 3500);
    }
}
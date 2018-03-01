package com.businessfunctions;

import com.library.Common;

public class ViewSavingsDetails {
       
    Common browser;
    
    //constructor with one argument.
    public ViewSavingsDetails(Common br)
    {
        browser=br;
    }
    
    // K - Verify the details on Savings Details page
    public void viewSavingsDetail()
    {
        browser.click("xpath", "(//*[@content-desc='accountCard'])[1]");
      
        String accountType= browser.getText("accessibilityId", "accountType").toString();
        String accountNumber= browser.getText("accessibilityId", "accountNumber").toString();
        System.out.printf("Account Type: %s Account Number: %s\n", accountType, accountNumber);
            
        String availableBalance = browser.getText("accessibilityId", "availableBalance");
        String availableAmount = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
        System.out.printf("Balance Type: %s , Available Amount: %s\n", availableBalance, availableAmount);
            
        String lienBalance = browser.getText("accessibilityId", "lienBalance)");
        String lienBalanceAmount = browser.getText("accessibilityId", "lienBalanceBalanceAmount");
        System.out.printf("Balance Type: %s , Lien Balance Amount: %s\n", lienBalance, lienBalanceAmount);
            
        String unclearedBalance = browser.getText("accessibilityId", "unclearBalance");
        String unclearedBalanceAmount = browser.getText("accessibilityId", "unclearBalanceBalanceAmount");
        System.out.printf("Balance Type: %s , Uncleared Balance Amount: %s\n", unclearedBalance, unclearedBalanceAmount);
        
        String balanceType = browser.getText("accessibilityId", "balanceTypeTitle");
        String balanceAmount = browser.getText("accessibilityId", "balanceAmount");
        System.out.printf("Balance Type: %s , Balance Amount: %s\n", balanceType, balanceAmount);
        browser.scrollDown("Down", 200, 3500);
    }
}
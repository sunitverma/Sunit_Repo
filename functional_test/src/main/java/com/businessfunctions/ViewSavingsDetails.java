package com.businessfunctions;

import com.library.Common;

public class ViewSavingsDetails {
       
	 Common browser;
	 //constructor with one argument.
	 public ViewSavingsDetails(Common br)
	 {
	    browser=br;
	 }
	
	    public void savingAccountBalanceBreakdown() throws InterruptedException
        {
            String accountType= browser.getText("xpath", "(//*[@content-desc='accountType'])").toString();
            String accountNumber= browser.getText("xpath", "(//*[@content-desc='accountNumber'])").toString();
            System.out.printf("Account Type: %s Account Number: %s\n",accountType, accountNumber);
            
            String availableBalance = browser.getText("xpath", "(//*[@content-desc='availableBalance'])");
            String availableAmount = browser.getText("xpath", "(//*[@content-desc='availableBalanceBalanceAmount'])");
            System.out.printf("Balance Type: %s Available Amount: %s\n",availableBalance, availableAmount);
            
            String lienBalance = browser.getText("xpath", "(//*[@content-desc='lienBalance'])");
            String lienBalanceAmount = browser.getText("xpath", "(//*[@content-desc='lienBalanceBalanceAmount'])");
            System.out.printf("Balance Type: %s Lien Balance Amount: %s\n",lienBalance, lienBalanceAmount);
            
            String unclearedBalance = browser.getText("xpath", "(//*[@content-desc='unclearBalance'])");
            String unclearedBalanceAmount = browser.getText("xpath", "(//*[@content-desc='unclearBalanceBalanceAmount'])");
            System.out.printf("Balance Type: %s Uncleared Balance Amount: %s\n",unclearedBalance, unclearedBalanceAmount);
            
            String balanceType = browser.getText("xpath", "(//*[@content-desc='balanceTypeTitle'])");
            String balanceAmount = browser.getText("xpath", "(//*[@content-desc='balanceAmount'])");
            System.out.printf("Balance Type: %sBalance Amount: %s",balanceType, balanceAmount);
            browser.scrollDown("Down", 200, 3500);
       }
}
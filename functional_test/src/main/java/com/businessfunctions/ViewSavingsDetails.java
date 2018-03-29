package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class ViewSavingsDetails {
       
    Common browser;
    
    //constructor with one argument.
    public ViewSavingsDetails(Common br) {
      browser = br;
    }

    // K - Verify the details on Savings Details page
    public void viewSavingsDetail() {
      
      try {
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]]") != 0) {
          String accountNo = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardNumber']");
          String accountBal = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardBalanceAmount']");
          String currencySign = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardBalanceNegative']");
          String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardBalanceCurrency']");
          String subAccountNo = browser.subString(accountNo, 3, 7);
          
          browser.click("xpath", "(//*[@content-desc='accountCardType'][@text='SAVINGS'])[1]");
          browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
          
          //Verify the details on Savings account details page
          browser.verifyText("accessibilityId", "accountType", "SAVINGS");
          browser.verifyText("accessibilityId", "balanceTypeTitle", browser.getText("xpath", "(//*[@text[contains(., " + "'" + subAccountNo + "'" + ")]][1])[2]"));
          browser.verifyText("accessibilityId", "balanceAmount", currencySign + accountBal);
          browser.verifyText("accessibilityId", "accountCurrency", " " + currency);
          
          browser.verifyText("accessibilityId", "availableBalance", "Available:");
          browser.verifyElementPresent("accessibilityId", "availableBalanceBalanceAmount");
          browser.verifyText("accessibilityId", "availableBalanceCurrency", " " + currency);
          
          browser.verifyText("accessibilityId", "lienBalance", "Lien:");
          browser.verifyElementPresent("accessibilityId", "lienBalanceBalanceAmount");
          browser.verifyText("accessibilityId", "lienBalanceCurrency", " " + currency);
          
          browser.verifyText("accessibilityId", "unclearBalance", "Uncleared:");
          browser.verifyElementPresent("accessibilityId", "unclearBalanceBalanceAmount");
          browser.verifyText("accessibilityId", "unclearBalanceCurrency", " " + currency);
          
          browser.verifyText("accessibilityId", "transactionHeader", "TRANSACTIONS HISTORY");
          browser.verifyText("accessibilityId", "transactionHeaderCurrency", currency);
          
          browser.scroll("Down", 200, 3500);
        }
        else {
          System.out.println("No Savings account is avaliable for this user. Please use another user for automation testing which have Savings account.");
        }
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
}
package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class ViewSavingsDetails {
       
    public static String accountNo;
    public static String accountBal;
    public static String currencySign;
    public static String currency;
    public static String subAccountNo;
    
	Common browser;
    
    //constructor with one argument.
    public ViewSavingsDetails(Common br) {
    	browser = br;
    }
    
    // K - Verify the details on Savings Details page
    public void viewSavingsDetail() {
    	
    	try {
    		if(browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]]") != 0) {
    			
    			accountNo = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardNumber']");
    			accountBal = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardBalanceAmount']");
    			currencySign = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardBalanceNegative']");
    			currency = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]][1]//*[@content-desc='accountCardBalanceCurrency']");
    			subAccountNo = browser.subString(accountNo, 3, 7);
          
			    browser.click("xpath", "(//*[@content-desc='accountCardType'][@text='SAVINGS'])[1]");
			    browser.waitUntilElementPresent("//*[@content-desc='backButton']");
			          
			    //Verify the details on Savings account details page
			    browser.verifyText("accessibilityId", "accountType", "SAVINGS");
			    browser.verifyText("accessibilityId", "accountNumber", browser.getText("xpath", "//*[@text[contains(., " + "'" + subAccountNo + "'" + ")]]"));
			    browser.verifyText("accessibilityId", "availableBalance", currencySign + accountBal + " " + currency);
			    			    			          
			    browser.verifyText("accessibilityId", "Available: Label", "Available:");
		        browser.verifyElementPresent("accessibilityId", "Available: Value");
		        		          
		        browser.verifyText("accessibilityId", "Lien: Label", "Lien:");
		        browser.verifyElementPresent("accessibilityId", "Lien: Value");
		        		        
		        browser.verifyText("accessibilityId", "Uncleared: Label", "Uncleared:");
		        browser.verifyElementPresent("accessibilityId", "Uncleared: Value");
		        		        
		        browser.verifyText("accessibilityId", "transactionHeader", "TRANSACTIONS (USD)");
		        browser.verifyElementPresent("accessibilityId", "transactionDate");
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

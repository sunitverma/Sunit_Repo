package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class ViewLoanDetails {
    
    public static String accountNo;
    public static String accountBal;
    public static String currencySign;
    public static String currency;
    public static String subAccountNo;
    
    Common browser;
    
    //constructor with one argument.
    public ViewLoanDetails(Common br) {
    	browser = br;
    }
        
    // S - Verify the details on Loan Details page
    public void viewLoanDetail() {
    	
    	try {
    		if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"LOAN\"))") != 0) {
    			
    			accountNo = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')]/..//*[@content-desc='accountCardNumber']");
    			accountBal = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')]/..//*[@content-desc='accountCardBalanceAmount']");
    			currencySign = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')]/..//*[@content-desc='accountCardBalanceNegative']");
    			currency = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')]/..//*[@content-desc='accountCardBalanceCurrency']");
    			subAccountNo = browser.subString(accountNo, 3, 7);
          
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')]/..");
    			browser.waitUntilElementPresent("//*[@content-desc='backButton']");
    			
    			//Verify the details on Loan account details page
    			browser.verifyText("accessibilityId", "accountType", "LOAN");
    			browser.verifyText("accessibilityId", "accountNumber", browser.getText("xpath", "//*[@text[contains(., " + "'" + subAccountNo + "'" + ")]]"));
    			browser.verifyText("accessibilityId", "availableBalance", currencySign + accountBal + " " + currency);
    			          
    			browser.verifyText("accessibilityId", "Next Payment", "Next Payment");
    			browser.verifyElementPresent("accessibilityId", "nextPaymentDueDate");
    			browser.verifyElementPresent("accessibilityId", "installmentAmount");
    			browser.verifyText("xpath", "//*[./preceding-sibling::*[@content-desc='installmentAmount']][1]", "JMD");
              
    			browser.verifyText("accessibilityId", "Loan Period", "Loan Period");
    			browser.verifyElementPresent("xpath", "//*[@content-desc='loanPeriod'][1]");
    			browser.verifyElementPresent("xpath", "//*[@content-desc='loanPeriod'][2]");
    		}
    		else {
    			System.out.println("No Loan account is avaliable for this user. Please use another user for automation testing which have Loan account.");
    		}
    	} catch (NoSuchElementException e) {
    		browser.screenShot();
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
        
    // S - Verify the details on paid loan account Details page
    public void zeroLoanBalance() {
    	
    	try {
    		if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"LOAN\"))") != 0) {
    			
    			accountNo = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][@text='0.00']/..//*[@content-desc='accountCardNumber']");
    			accountBal = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][@text='0.00']/..//*[@content-desc='accountCardBalanceAmount']");
    			currencySign = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][@text='0.00']/..//*[@content-desc='accountCardBalanceNegative']");
    			currency = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][@text='0.00']/..//*[@content-desc='accountCardBalanceCurrency']");
    			subAccountNo = browser.subString(accountNo, 3, 6);
          
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='accountCardBalanceAmount'][@text='0.00']/..");
    			browser.waitUntilElementPresent("//*[@content-desc='backButton']");
    			
    			//Verify the details on Loan account details page
    			browser.verifyText("accessibilityId", "accountType", "LOAN");
    			browser.verifyText("accessibilityId", "accountNumber", browser.getText("xpath", "//*[@text[contains(., " + "'" + subAccountNo + "'" + ")]]"));
    			browser.verifyText("accessibilityId", "availableBalance", currencySign + accountBal+ " " + currency);
    			          
    			browser.verifyText("accessibilityId", "Next Payment", "Next Payment");
    			browser.verifyText("accessibilityId", "nextPaymentDueDate", "Jan 28, 2018");
    			browser.verifyText("accessibilityId", "installmentAmount", "$63,382.03");
    			browser.verifyText("xpath", "//*[./preceding-sibling::*[@content-desc='installmentAmount']][1]", "JMD");
    			
    			browser.verifyText("accessibilityId", "Loan Period", "Loan Period");
    			browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][1]", "90 months");
    			browser.verifyText("xpath", "//*[@content-desc='loanPeriod'][2]", "1/Mar/11 - 28/Sep/18");
        		browser.click("accessibilityId", "backButton");
        		browser.waitUntilElementPresent("//*[@content-desc='logo']");
        		
        		if (browser.verifyElementPresent("accessibilityId", "logo")== true) {
        			System.out.println("Account summary page displayed after click on back arrow on loan account details");
        		}
        		else {
        			System.out.println("Account summary page not displayed after click on back arrow on loan account details");
        		}
    		}
    		else {
    			System.out.println("No Loan account is avaliable for this user. Please use another user for automation testing which have Loan account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
}

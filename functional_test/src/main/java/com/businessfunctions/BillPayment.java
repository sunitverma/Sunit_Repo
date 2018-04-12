package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BillPayment {

    public static String accountNoFrom;
    public static String accountBalanceFrom;
    public static String accountCurrencyTypeFrom;
  
    ExtentReports extent;
    ExtentTest test;
  
    Common browser;
  
    //constructor with one argument.
    public BillPayment(Common br) {
    	browser = br;
    }
    
    // S - Method - To use in billPaymentListPage and billPaymentSummaryPage method
    public void billPaymentEndToEnd(String accounttype, String amount, String accountNoFrom, String accountBalanceFrom, String accountCurrencyTypeFrom) {
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("accessibilityId", "TransferHeader", "Who would you like to pay?");
    	
    	if (browser.getSize("accessibilityId", "Bill Payee Nickname") != 0) {
    		String accountNameTo = browser.getText("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
	        browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
	        browser.click("accessibilityId", "Next Button Enabled");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][1])[2]", "How much would you like to pay?");
	        browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your available balance is " + accountBalanceFrom + " " + accountCurrencyTypeFrom + ".");
	        browser.sendKeys("accessibilityId", "MoneyInput", amount);
	        
	        String moneyInput = browser.getText("accessibilityId", "MoneyInput");
	        
	        browser.click("accessibilityId", "Next Button Enabled");
	        
	        browser.waitUntilElementPresent("//*[@content-desc='billPaymentReviewTitle']");
	        browser.verifyText("accessibilityId", "billPaymentReviewTitle", "Please review and confirm details.");
	        browser.verifyText("accessibilityId", "sendFromLabel", "Send from");
	        browser.verifyText("accessibilityId", "sendFromData", browser.titleCase("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardType']") + " " + accountNoFrom);
	        browser.verifyText("accessibilityId", "toText", "to");
	        browser.verifyText("accessibilityId", "toData", accountNameTo);
	        browser.click("accessibilityId", "scrollToEndButton");
	        browser.verifyText("accessibilityId", "amountLabel", "for the amount of");
	        browser.verifyText("accessibilityId", "amountCurrency", accountCurrencyTypeFrom);
	        browser.verifyText("accessibilityId", "amountData", moneyInput);
	        
	        browser.verifyText("accessibilityId", "disclaimerNote", "Note: Once you select the Submit Payment button, you CANNOT undo this payment.");
	        browser.click("accessibilityId", "submitPaymentButton");
	        
	        browser.waitUntilElementPresent("//*[@content-desc='successImage']");
	        browser.verifyText("accessibilityId", "successText", "Success! \nYour payment is complete.");
	        browser.verifyElementPresent("accessibilityId", "paymentReferenceNumber");
	        browser.verifyText("accessibilityId", "paymentReferenceNumber", browser.getText("xpath", "//*[@text[starts-with(.,'Payment Reference:')]]"));
	        System.out.println("Bill payment is working fine for " + accounttype + " account.");
    	}
    	else {
    		browser.verifyText("accessibilityId", "No Payees Message", "There are no payees to display.\nPlease visit JNCB online to add a new payee.");
            System.out.println("No Payee for this user so please use another user for automation testing which have payee");
    	}
    }

    // S - Method - to verify bill payee flow
    public void billPaymentListPage(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			
    			accountNoFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardNumber']");
    			
    			if (browser.getSize("accessibilityId", "PAY A BILL BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='PAY A BILL BUTTON']]");
    				browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    				browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
		            
    				accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
			        accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
			            
			        browser.click("accessibilityId", "backButton");  
			        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
			        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
			        browser.click("accessibilityId", "PAY A BILL BUTTON");
			        browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			           
			        billPaymentEndToEnd(accounttype, amount, accountNoFrom, accountBalanceFrom, accountCurrencyTypeFrom);
    			}
    			else {
    				browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
	    			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
	            
		            accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
		            accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
		            
		            browser.click("accessibilityId", "backButton");  
		            browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
		            browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
		            browser.click("accessibilityId", "PAY A BILL BUTTON");
		
		            billPaymentEndToEnd(accounttype, amount, accountNoFrom, accountBalanceFrom, accountCurrencyTypeFrom);
    			}
    		}
    		else {
    			System.out.println("No " + accounttype + " is account avaliable for this user. So please use another user for automation testing which have " + accounttype + " account.");
    		}
    	} catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
    	}
    }
       
    // S - Method - to verify bill Payee flow
    public void billPaymentSummaryPage(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			
    			accountNoFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardNumber']");
    			
    			if (browser.getSize("accessibilityId", "PAY A BILL BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='PAY A BILL BUTTON']]");
		            browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
		            browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
		            
		            accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
		            accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
		            
		            browser.click("accessibilityId", "PAY A BILL BUTTON");
		            
		            browser.screenShot();
		            billPaymentEndToEnd(accounttype, amount, accountNoFrom, accountBalanceFrom, accountCurrencyTypeFrom);
    			}
    			else {
    				browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
		            browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
		            
		            accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
		            accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
		            
		            browser.click("accessibilityId", "PAY A BILL BUTTON");
		            browser.screenShot();
		            billPaymentEndToEnd(accounttype, amount, accountNoFrom, accountBalanceFrom, accountCurrencyTypeFrom);
    			}
    		}
    		else {
    			System.out.println("No " + accounttype + " is account avaliable for this user. So please use another user for automation testing which have " + accounttype + " account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
        
    public void backToAccountsButton() {
    	
    	try {
    		browser.verifyElementPresent("xpath", "//*[@text='PAY ANOTHER BILL']");
                
    		browser.click("xpath", "//*[@text='PAY ANOTHER BILL']"); // click on pay another bill
    		browser.waitUntilElementPresent("(//*[@text='Who would you like to pay?'])[2]");
    		System.out.println("Sucessfully verify Back to Accounts button functionality");
    	}
    	catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    public void payAnotherBillButton() {
    	
    	try {
    		browser.verifyElementPresent("xpath", "//*[@text='Back to Accounts']");
        
	        browser.click("xpath", "//*[@text='Back to Accounts']"); // click on back to accounts
	        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
	        browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
	        System.out.println("Successfully verify Pay another bill button functionality");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
}
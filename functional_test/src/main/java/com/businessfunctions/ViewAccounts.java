package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class ViewAccounts {
    
    Common browser;
    
    //constructor with one argument.
    public ViewAccounts(Common br) {
    	browser = br;
    }

    // K - Method - to verify 10 transactions on page // update count to 50 --05/02/2018
    public void viewAccount() {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='SAVINGS']]]") != 0) {
    			
    			browser.click("xpath", "//*[@content-desc='accountCardType'][@text='SAVINGS'][1]");
    			browser.waitUntilElementPresent("//*[@content-desc='accountType']");
    			int count=browser.getSize("accessibilityId", "merchantText");
    			
    			if (count==50) {
    				System.out.println("Able to get the top 50 transactions");
    			}
    			else if (count>50) {
    				System.out.println("The given account have transactions more than 50");
    			}
    			else {
    				System.out.println("The given account have transactions less than 50");
    			}
    		}
    		else {
    			System.out.println("No Savings account is avaliable for this user. Please use another user for automation testing which have Savings account.");
    			}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
         
    // S - Method - to use in animationOnAccountDetail method
    public void spinnerVerification() {
    	
    	if (browser.getSize("accessibilityId", "spinnerText") != 0) {
    		System.out.println("Animation displayed on Account details page");
    		browser.waitUntilElementPresent("//*[@content-desc='accountType']");
    	}
    	else {
    		System.out.println("Animation not displayed on Account details page");
    		browser.waitUntilElementPresent("//*[@content-desc='accountType']");
    	}
    }
        
    // S - Method - to verify animation displayed on Account Details page
    public void animationOnAccountDetail() {
    	
    	try {
    		if(browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    			browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    			browser.click("xpath", "//*[@content-desc='accountCard'][1]");
    			browser.screenShot();
    			spinnerVerification();
    		}
    		else {
    			browser.click("xpath", "//*[@content-desc='accountCard'][1]");
    			browser.screenShot();
    			spinnerVerification();
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - To use in transferButtonOnAccountDetailsPage method
    public void transferButtonFlow(String accounttype) {
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    	browser.waitUntilElementPresent("//*[@content-desc='accountType']");
    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");      
    	browser.verifyText("accessibilityId", "TransferHeader", "To whom would you like to transfer?");
    	browser.verifyElementPresent("accessibilityId", "backButton");
    	browser.screenShot();
    	browser.click("accessibilityId", "backButton");
    	browser.waitUntilElementPresent("//*[@content-desc='logoutButton']");
    	browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
        
    // S - Method - to verify transfer button working on saving and Chequing account details page and back button working
    public void transferButtonOnAccountDetailsPage(String accounttype) {
    	
    	try {
    		browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				browser.screenShot();
    				transferButtonFlow(accounttype);
    				System.out.println("Transfer and back button are working on Account details page for " + accounttype + " account.");
    			}
    			else {
    				browser.screenShot();
    				transferButtonFlow(accounttype);
    				System.out.println("Transfer and back button are working on Account details page for " + accounttype + " account.");
    			}
    		}
    		else {
    			System.out.println("No " + accounttype + " account is avaliable for this user. Please use another user for automation testing which have " + accounttype + " account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - To use in billPaymentButtonOnLandingPage method
    public void billPaymentButtonFlow(String accounttype) {
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    	browser.click("xpath", "//*[@content-desc='PAY A BILL BUTTON']");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("accessibilityId", "TransferHeader", "Who would you like to pay?");
    	browser.verifyElementPresent("accessibilityId", "backButton");
    	browser.click("accessibilityId", "backButton");
    	browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    	browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
    
    // S - Method - To verify pay a bill button working for saving and Chequing account on account details page and back button working
    public void billPaymentButtonOnAccountDetailsPage(String accounttype) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if (browser.getSize("accessibilityId", "PAY A BILL BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='PAY A BILL BUTTON']]");
    				browser.screenShot();
    				billPaymentButtonFlow(accounttype);
    				System.out.println("Bill payment and back button are working on Account details page for " + accounttype + " account.");
    			}
    			else {
    				browser.screenShot();
    				billPaymentButtonFlow(accounttype);
    				System.out.println("Bill payment and back button are working on Account details page for " + accounttype + " account.");
    			}
    		}
    		else {
    			System.out.println("No " + accounttype + " is account avaliable for this user. Please use another user for automation testing which have " + accounttype + " account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
}

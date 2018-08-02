package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class HomePage {
    
    Common browser;
    
    //constructor with one argument.
    public HomePage(Common br) {
    	browser = br;
    }
       
    // K - Method - to verify the available accounts    
    public void homePageWithAccounts() {
    	
    	try {
    		if((browser.getSize("accessibilityId", "accountCard") != 0)) {
    			if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"SAVINGS\"))") != 0) {
    				System.out.println("Savings account is available");
    			}
				if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"CHEQUING\"))") != 0) {
					System.out.println("Chequing account is available");
				}
				if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"LOAN\"))") != 0) {
					System.out.println("Loan account is available");
				}
    		}
    		else {
    			System.out.println("Neither Savings or Chequing nor Loan account is available. Please use another user for automation testing which have all accounts.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - to verify no account
    public void homePageWithoutAccounts() {
    	
    	try {
    		browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
    		
    		if(browser.getSize("accessibilityId", "Snackbar Message") != 0) {
    			String msg = browser.getText("accessibilityId", "Snackbar Message");
    			System.out.println(msg);
    			System.out.println("No accounts are available and error message as expected");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - to verify transfer and pay a bill options not available for Loan accounts on landing page
    public void quickActionsButtonNotForLoan() {
    	
    	try {
    		//if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"LOAN\"))") != 0) {
    		if(browser.scrollForObject("LOAN") == true) {
    			if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='QUICK ACTIONS Button']")!=0) {
    				System.out.println("Quick Actions - Make A Transfer/ Pay A Bill options are available for Loan account.");
    				}
    			else {
    				System.out.println("Quick Actions - Make A Transfer/ Pay A Bill options are NOT available for Loan account as expected.");
    				}
    		}
    		else {
    			System.out.println("No Loan account is avaliable for this user. Please use another user for automation testing which have Loan account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    	browser.screenShot();
    }
    
    // S - Method - to verify transfer button working for saving and Chequing accounts on landing page and back button working
    public void transferButtonOnListPage(String accounttype) {
    	
    	try {
    		if(browser.getSize("androidUIAutomator", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"" + accounttype + "\"))") != 0) {
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='QUICK ACTIONS Button']");
    			browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    	    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    	    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	    	browser.screenShot();
    	    	browser.verifyText("accessibilityId", "TransferHeader", "To whom would you like to transfer?");
    	    	browser.verifyElementPresent("accessibilityId", "backButton");
    	    	browser.click("accessibilityId", "backButton");
    	    	browser.waitUntilElementPresent("//*[@content-desc='logo']");
    	    	System.out.println("Transfer and back button are working on Landing page for " + accounttype + " account.");
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
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='PAY A BILL BUTTON']");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.screenShot();
    	browser.verifyText("accessibilityId", "TransferHeader", "Who would you like to pay?");
    	browser.verifyElementPresent("accessibilityId", "backButton");
    	browser.click("accessibilityId", "backButton");
    	browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    	browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
        
    // S - Method - to verify pay a bill button working for saving and Chequing accounts on landing page and back button working
    public void billPaymentButtonOnLandingPage(String accounttype) {
    	
    	try {
    		if(browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if(browser.getSize("accessibilityId", "PAY A BILL BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='PAY A BILL BUTTON']]");
    				browser.screenShot();
    				billPaymentButtonFlow(accounttype);
    				System.out.println("Bill payment and back button are working on Landing page for " + accounttype + " account.");
    			}
    			else {
    				browser.screenShot();
    				billPaymentButtonFlow(accounttype);
    				System.out.println("Bill payment and back button are working on Landing page for " + accounttype + " account.");
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
    
    // S - Method - to verify back button is working on all pages for Savings or Chequing accounts.
    public void backButton(String accounttype, String paymenttype) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    				if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    					
    					browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    					
    					if (paymenttype =="moneytrf") {
    						browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='MAKE A TRANSFER BUTTON']");
    					}
    					else if (paymenttype =="billpay") {
    						browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='PAY A BILL BUTTON']");
    					}
    					
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[1] | //*[@content-desc='accountCard' and .//*[@text='Savings' or @text='Chequing']][1]");
    					browser.click("accessibilityId", "Next Button Enabled");
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
		              
    					browser.sendKeys("accessibilityId", "MoneyInput", "1");
    					browser.click("accessibilityId", "Next Button Enabled");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle' or @content-desc='billPaymentReviewTitle']");
    					browser.click("accessibilityId", "backButton");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][1])[2]", "How much would you like to transfer? ");
    					browser.click("accessibilityId", "backButton");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.verifyText("accessibilityId", "TransferHeader", "To whom would you like to transfer?");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    					
    					System.out.println("Back buttons are working on all pages for Money Transfer flow of " + accounttype + ".");
    				}
    				else {
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='MAKE A TRANSFER BUTTON']");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.click("xpath", "//*[@content-desc='accountCard' and .//*[@text='Savings' or @text='Chequing']][1]");
    					browser.click("accessibilityId", "Next Button Enabled");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.sendKeys("accessibilityId", "MoneyInput", "1");
    					browser.click("accessibilityId", "Next Button Enabled");
		              
    					browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
    					browser.click("accessibilityId", "backButton");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][1])[2]", "How much would you like to transfer? ");
    					browser.click("accessibilityId", "backButton");
		              
    					browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    					browser.verifyText("accessibilityId", "TransferHeader", "To whom would you like to transfer?");
    					
    					browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    					
    					System.out.println("Back buttons are working on all pages for Money Transfer flow of " + accounttype + ".");
    				}
    			}
    			else {
    				System.out.println("This user have only one account which is " + accounttype + ". So please use another user for automation testing which have more then one savings or chequing account.");
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
 
    // A - Method - to verify Credit Cards details are shown properly on home page 10-5-2018
 	public void homePageCardInfo() {
 		
 		try {
 			if ((browser.getSize("accessibilityId", "creditCard") != 0)) {
 				
 				boolean avlBal;
 				boolean logo;
 				boolean prodName;
 				boolean cardNum;
 				boolean avlText;

 				avlBal = browser.verifyElementPresent("accessibilityId", "availableBalance");
 				if (avlBal) {
 					String available = browser.getText("xpath", "//*[@content-desc='availableBalance']");
 					System.out.println("Available balance shown " + available);
 				}
 			
 				logo = browser.verifyElementPresent("accessibilityId", "logo");
 				if (logo) {
 					System.out.println("logo for card is shown");
 				}
 				
 				prodName = browser.verifyElementPresent("accessibilityId", "productName");
 				if (prodName) {
 					String cardBrand = browser.getText("xpath", "//*[@content-desc='productName']");
 					System.out.println("Brand of card shown " + cardBrand);
 				}
 				
 				cardNum = browser.verifyElementPresent("accessibilityId", "cardNumber");
 				if (cardNum) {
 					String cardNumb = browser.getText("xpath", "//*[@content-desc='cardNumber']");
 					System.out.println("card number is :" + cardNumb);
 				}
 				
 				avlText = browser.verifyElementPresent("xpath", "//*[@content-desc='creditCard' and ./*[@text='Available Balance']][1]");
 				if (avlText) {
 					System.out.println("available balance text shown");
 				}
 			
 				browser.screenShot();
 			}
 		} catch (NoSuchElementException e) {
 			System.out.println("Element Not Found");
 			e.printStackTrace();
 		}
 	}

 	// A - Menu Display on Homepage - A 12-07-28
 	public void  homeMenuShow() {
 		
 		try { 
 			if((browser.getSize("accessibilityId", "leftNavIcon") != 0)) {
 				browser.click("xpath", "//*[@content-desc='leftNavIcon']");
 				browser.waitUntilElementPresent("//*[@content-desc='SectionHeader:Transfers']");
 				browser.verifyText("xpath", "//*[@content-desc='SectionHeader:Transfers']","Transfers");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Between My Accounts']", "Between My Accounts");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:To Other NCB Accounts']", "To Other NCB Accounts");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Add Beneficiaries']", "Add Beneficiaries");

 				browser.verifyText("xpath", "//*[@content-desc='SectionHeader:Bill Pay']", "Bill Pay");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Pay a Bill']", "Pay a Bill");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Add Payees']", "Add Payees");
 			
 				browser.screenShot();
 				browser.click("xpath", "//*[@content-desc='rightNavIcon']");
 				browser.screenShot();
 			}
 			else {
 				System.out.println("No Menu shown");
 			}
 		} catch (NoSuchElementException e) {
 			System.out.println("Element Not Found");
 			e.printStackTrace();
 		}
 	}
 	
 	// A- Profile Icon items Validation 12-07-18
 	public void  homeProfileShow() {
 		
 		try {
 			if((browser.getSize("accessibilityId", "rightNavIcon") != 0)) {
 				browser.click("xpath", "//*[@content-desc='rightNavIcon']");
 				browser.waitUntilElementPresent("//*[@content-desc='SectionHeader:Support']");
 				browser.verifyElementPresent("xpath", "//*[@content-desc='rightNavIcon']");
 				browser.verifyText("xpath", "//*[@content-desc='SectionHeader:Support']","Support");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:FAQ']", "FAQ");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Rate the App']", "Rate the App");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Report a Bug']", "Report a Bug");
 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:Give Us Feedback']", "Give Us Feedback");

 				browser.verifyText("xpath", "//*[@content-desc='MenuItem:LOG OUT']", "LOG OUT");
 				browser.screenShot();
 				browser.click("xpath", "//*[@content-desc='leftNavIcon']");
 				browser.click("xpath", "//*[@content-desc='accountCard'][1]");
 				browser.waitUntilElementPresent("//*[@content-desc='transactionHeader']");
 				
 				browser.verifyElementPresent("xpath", "//*[@content-desc='rightNavIcon']");
 				browser.click("xpath", "//*[@content-desc='rightNavIcon']");
 				browser.waitUntilElementPresent("//*[@content-desc='MenuItem:LOG OUT']");
 				browser.click("xpath", "//*[@content-desc='MenuItem:LOG OUT']");
 				
 				browser.waitUntilElementPresent("//*[@content-desc='LogoutModalAcceptButton']");
 				browser.click("xpath", "//*[@content-desc='LogoutModalAcceptButton']");
 			}
 			else {
 				System.out.println("No Profile Menu shown");
 			}
 		} catch (NoSuchElementException e) {
 			System.out.println("Element Not Found");
 			e.printStackTrace();
 		}
 	}
}

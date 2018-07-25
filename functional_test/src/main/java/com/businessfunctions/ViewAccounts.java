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
    			//int count=browser.getSize("accessibilityId", "merchantText");
    			
    			try {
					browser.count();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
//    			if (count==50) {
//    				System.out.println("Able to get the top 50 transactions");
//    			}
//    			else if (count>50) {
//    				System.out.println("The given account have transactions more than 50");
//    			}
//    			else {
//    				System.out.println("The given account have transactions less than 50");
//    			}
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

	// Aditya - Verify payment due date, Minimum payment due, Statement Balance, Statement Date, Total Credit Limit, Current Balance
	public void viewCardAccount() {
		
		if ((browser.getSize("accessibilityId", "creditCard") != 0)) {
			
			boolean stmBal;
			boolean minBal;
			boolean pmtDueDate;
			boolean StmtDate;
			boolean ttlCreditLimitValue;
			boolean availableBalanceValue;
			boolean currentBal;

			browser.click("xpath", "//*[@content-desc='creditCard']");
			browser.waitUntilElementPresent("//*[@content-desc='ccName']");
			
			try {
				if ((browser.getSize("accessibilityId", "ccName") != 0)) {
					if (browser.getSize("xpath", "//*[@content-desc='balance']") != 0) {
						System.out.println("Balance is shown");
					}
					if (browser.getSize("xpath", "//*[@content-desc='ccLogo']") != 0) {
						System.out.println("Card Logo is shown");
					}
					if (browser.getSize("xpath", "//*[@content-desc='ccNumber']") != 0) {
						System.out.println("Card Number is shown");
					}

					stmBal = browser.verifyElementPresent("accessibilityId", "Statement Balance: Value");
					if (stmBal) {
						String available = browser.getText("xpath", "//*[@content-desc='Statement Balance: Value']");
						browser.verifyText("xpath", "//*[@content-desc='Statement Balance: Label']",
								"Statement Balance:");
						System.out.println("Statement balance shown " + available);
					}

					minBal = browser.verifyElementPresent("accessibilityId", "Minimum Payment Due: Value");
					if (minBal) {
						String minBalVal = browser.getText("accessibilityId", "Minimum Payment Due: Value");
						browser.verifyText("xpath", "//*[@content-desc='Minimum Payment Due: Label']",
								"Minimum Payment Due:");
						System.out.println("Minimum Payment Due for card is shown " + minBalVal);
					}

					pmtDueDate = browser.verifyElementPresent("accessibilityId", "Payment Due Date: Value");
					if (pmtDueDate) {
						String minDueDtValue = browser.getText("accessibilityId", "Payment Due Date: Value");
						browser.verifyText("xpath", "//*[@content-desc='Payment Due Date: Label']",
								"Payment Due Date:");
						System.out.println("Payment Due Date is shown " + minDueDtValue);
					}

					browser.click("xpath", "//*[@content-desc='MORE DETAILS Button']");
					browser.waitUntilElementPresent("//*[@content-desc='Statement Date: Value']");
					StmtDate = browser.verifyElementPresent("accessibilityId", "Statement Date: Value");
					if (StmtDate) {
						String statmentDate = browser.getText("accessibilityId", "Statement Date: Value");
						browser.verifyText("xpath", "//*[@content-desc='Statement Date: Label']", "Statement Date:");
						System.out.println("Statement Date is shown " + statmentDate);
					}

					ttlCreditLimitValue = browser.verifyElementPresent("accessibilityId", "Total Credit Limit: Value");
					if (ttlCreditLimitValue) {
						String cardBrand = browser.getText("xpath", "//*[@content-desc='Total Credit Limit: Value']");
						browser.verifyText("xpath", "//*[@content-desc='Total Credit Limit: Label']",
								"Total Credit Limit:");
						System.out.println("Total credit card Limit " + cardBrand);
						// System.out.println(cardBrand);
					}

					availableBalanceValue = browser.verifyElementPresent("accessibilityId", "Available Balance: Value");
					if (availableBalanceValue) {
						String availableBalanceValueb = browser.getText("xpath",
								"//*[@content-desc='Available Balance: Value']");
						browser.verifyText("xpath", "//*[@content-desc='Available Balance: Label']",
								"Available Balance:");
						System.out.println("Available Balance is :" + availableBalanceValueb);

					}
					currentBal = browser.verifyElementPresent("accessibilityId", "Current Balance: Value");
					if (currentBal) {
						String currentBala = browser.getText("xpath", "//*[@content-desc='Current Balance: Value']");
						browser.verifyText("xpath", "//*[@content-desc='Current Balance: Label']", "Current Balance:");
						System.out.println("Current Balance is :" + currentBala);

					}
					browser.screenShot();
					browser.click("xpath", "//*[@content-desc='backButton']");
					browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
					if (browser.getSize("xpath", "//*[@content-desc='welcomeName']") != 0) {
						System.out.println("Back button press navigates user to accounts listing page");
					}
				} else {
					System.out.println("Back button press functionality failed");
				}

			} catch (NoSuchElementException e) {
				System.out.println("Element Not Found");
				e.printStackTrace();
			}
		}
	}
}

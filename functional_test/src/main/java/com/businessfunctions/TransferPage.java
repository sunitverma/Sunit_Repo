package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class TransferPage {

    public static String accountTypeFrom;
    public static String accountNoFrom;
    public static String accountBalanceFrom;
    public static String accountCurrencyTypeFrom;
    public static String accountTypeTo;
    public static String accountNoTo;
    
    Common browser;
    
    //constructor with one argument.
    public TransferPage(Common br) {
    	browser = br;
    }
    
    // K - Method - To use in meToMeTransfersAccountListPage and meToMeTransfersAccountSummaryPage methods for cancel button on amount page and validation
    public void meToMeTransferCancelButtonOnAmountPage() {
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("accessibilityId", "TransferHeader", "To whom would you like to transfer?");
    	browser.verifyElementPresent("accessibilityId", "backButton");
    	browser.click("xpath", "//*[@text='MY ACCOUNTS']");
    	browser.click("xpath", "//*[@content-desc='accountCard' and .//*[@text='Savings' or @text='Chequing']][1]");
    	browser.click("accessibilityId", "Next Button Enabled");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
      
    	// To verify cancel button functionality on amount page
    	browser.click("accessibilityId", "Cancel Button");
    	
    	browser.verifyText("accessibilityId", "cancelModalFirstTextField", "Cancel Transaction");
    	browser.verifyText("accessibilityId", "cancelModalSecondTextField", "Are you sure you want to cancel this transaction?");
    	browser.verifyElementPresent("accessibilityId", "cancelModalCancelButton");
    	browser.verifyElementPresent("accessibilityId", "cancelModalLogoutButton");
    	browser.click("accessibilityId", "cancelModalCancelButton"); 
    	
    	browser.click("xpath", "//*[@text='Cancel']");
    	browser.click("accessibilityId", "cancelModalLogoutButton");
    	browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    	browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
    
    // K - Method - To use in meToMeTransfersAccountListPage and meToMeTransfersAccountSummaryPage methods for cancel button on review page and validation
    public void meToMeTransferCancelButtonOnReviewPage(String accounttype, String amount) {
    	
    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.click("xpath", "//*[@text='MY ACCOUNTS']");
    	browser.click("xpath", "//*[@content-desc='accountCard' and .//*[@text='Savings' or @text='Chequing']][1]");
    	browser.click("accessibilityId", "Next Button Enabled");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][1])[2]", "How much would you like to transfer? ");
    	browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your available balance is " + accountBalanceFrom + " " + accountCurrencyTypeFrom + ".");
    	browser.sendKeys("accessibilityId", "MoneyInput", amount);
    	
    	browser.click("accessibilityId", "Next Button Enabled");
    	browser.waitUntilElementPresent("//*[@content-desc='ReviewTitle']");
      
    	browser.verifyText("accessibilityId", "sendFromLabel", "Send from");
    	browser.verifyText("accessibilityId", "sendFromData", browser.titleCase("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardType']") + " " + accountNoFrom);
    	browser.verifyText("accessibilityId", "toText", "to");
    	//browser.verifyText("accessibilityId", "", browser.titleCase("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardType']")+ "  ••" + browser.subString(accountNoTo, 3, 7));
    	browser.click("accessibilityId", "scrollToEndButton");
    	browser.verifyText("accessibilityId", "amountLabel", "for the amount of");
    	browser.verifyText("accessibilityId", "amountCurrency", accountCurrencyTypeFrom);
    	//browser.verifyText("accessibilityId", "amountData", moneyInput); // need to change 
    	browser.verifyText("accessibilityId", "disclaimerNote", "Note: Once you select the Submit Transfer button, you CANNOT undo this transfer.");
    	browser.verifyElementPresent("accessibilityId", "submitTransferButton");
      
    	// To verify cancel button functionality on review page
    	browser.click("accessibilityId", "Cancel Button");
      
    	browser.verifyText("accessibilityId", "cancelModalFirstTextField", "Cancel Transaction");
    	browser.verifyText("accessibilityId", "cancelModalSecondTextField", "Are you sure you want to cancel this transaction?");
    	browser.verifyElementPresent("accessibilityId", "cancelModalCancelButton");
    	browser.verifyElementPresent("accessibilityId", "cancelModalLogoutButton");
    	browser.click("accessibilityId", "cancelModalCancelButton"); 
      
    	browser.click("accessibilityId", "Cancel Button");
    	browser.click("accessibilityId", "cancelModalLogoutButton");
    	browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    	browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
    
    // S - Method - To verify MAKE A TRANSFER button working for saving and Chequing account on account details page and back button working
    public void meToMeTransfersAccountListPage(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    				
    				accountTypeFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardType']");
    				accountNoFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardNumber']");
    				accountTypeTo = browser.getText("xpath", "(//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING'] and .//*[@content-desc='accountCardNumber'][not(@text[contains(.,'" + accountNoFrom + "')])]]//*[@content-desc='accountCardType'])[1]");
    				accountNoTo = browser.getText("xpath", "(//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING'] and .//*[@content-desc='accountCardNumber'][not(@text[contains(.,'" + accountNoFrom + "')])]]//*[@content-desc='accountCardNumber'])[1]");
    				
    				if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    					browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    					browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
              
    					accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    					accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    					
    					browser.click("accessibilityId", "backButton");  
    					browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    					browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    					
    					browser.screenShot();
    					meToMeTransferCancelButtonOnAmountPage();
    					meToMeTransferCancelButtonOnReviewPage(accounttype, amount);
    					System.out.println("Back buttons are working and validation on all pages are OK for transfer flow on Account Listing page for " + accounttype + " account.");
    				}
    				else {
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    					browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    					
    					accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    					accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    					
    					browser.click("accessibilityId", "backButton");  
    					browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    					browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    					
    					browser.screenShot();
    					meToMeTransferCancelButtonOnAmountPage();
    					meToMeTransferCancelButtonOnReviewPage(accounttype, amount);
    					System.out.println("Back buttons are working and validation on all pages are OK for transfer flow on Account Listing page for " + accounttype + " account.");
    				}
    			}
    			else {
    				System.out.println("This user have only one account which is " + accounttype + ". So please use another user for automation testing which have more then one savings or chequing account.");
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
    
    // K - Method - to verify me to me transfers on accounts from Account Summary page
    public void meToMeTransfersAccountSummaryPage(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    				
    				accountTypeFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardType']");
    				accountNoFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardNumber']");
    				accountTypeTo = browser.getText("xpath", "(//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING'] and .//*[@content-desc='accountCardNumber'][not(@text[contains(.,'" + accountNoFrom + "')])]]//*[@content-desc='accountCardType'])[1]");
    				accountNoTo = browser.getText("xpath", "(//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING'] and .//*[@content-desc='accountCardNumber'][not(@text[contains(.,'" + accountNoFrom + "')])]]//*[@content-desc='accountCardNumber'])[1]");
            
    				if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    					browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    					browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    					accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    					accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    					browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    					
    					browser.screenShot();
    					meToMeTransferCancelButtonOnAmountPage();
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");
    					meToMeTransferCancelButtonOnReviewPage(accounttype, amount);
    					System.out.println("Back buttons are working and validation on all pages are OK for transfer flow on Account Summary page for " + accounttype + " account.");
    				}
    				else {
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    					browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    					accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    					accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    					browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    					
    					browser.screenShot();
    					meToMeTransferCancelButtonOnAmountPage();
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");
    					meToMeTransferCancelButtonOnReviewPage(accounttype, amount);
    					System.out.println("Back buttons are working and validation on all pages are OK for transfer flow on Account Summary page for " + accounttype + " account.");
    				}
    			}
    			else {
    				System.out.println("This user have only one account which is " + accounttype + ". So please use another user for automation testing which have more then one savings or chequing account.");
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
    
    // K - Method - to verify me to you transfers button
    public void meToYouTransferButton() {
    	
    	try {
    		//Thread.sleep(5000);
    		browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
        
    // K - Method - to verify me to you transfers
    public void meToYouTransfer(String currency, String nickname) {
    	
    	try {
    		if(browser.getSize("xpath","//*[@content-desc='Next Button Disabled' and @width>0]")!=0) {
    			browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
    			//browser.click("xpath", "//*[@content-desc='accountCard' and ./*[@text='"+ Nickname +"']]");
    			browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
    			browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    			browser.verifyElementPresent("accessibilityId", "TransferHeader");
    			browser.getText("accessibilityId", "MoneyInput");
    			browser.sendKeys("accessibilityId", "MoneyInput", "1");
    			browser.click("accessibilityId", "TransferHeader");
    			browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
    			browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
    			browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])[" + currency + "])");
    			//browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
    			browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
    			browser.verifyElementPresent("accessibilityId", "sourceAccountTitle");
    			browser.click("xpath", "//*[@text='Cancel']");
          
    			if(browser.getSize("accessibilityId", "cancelModalLogoutButton")!=0) {
    				browser.click("accessibilityId", "cancelModalLogoutButton");
    				browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
    			}
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - To use in transferFlowMeToMe to verify successfully transfer
    public void meToMeTransfer(String amount) {
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.click("xpath", "//*[@text='MY ACCOUNTS']");
    	browser.click("xpath", "//*[@content-desc='accountCard' and .//*[@text='Savings' or @text='Chequing']][1]");
    	browser.click("accessibilityId", "Next Button Enabled");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.sendKeys("accessibilityId", "MoneyInput", amount);
    	browser.click("accessibilityId", "Next Button Enabled");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='ReviewTitle']");
    	
    	browser.click("accessibilityId", "scrollToEndButton");
    	//browser.verifyText("accessibilityId", "amountData", moneyInput); // need to change 
    	browser.verifyElementPresent("accessibilityId", "submitTransferButton");
    	browser.click("accessibilityId", "submitTransferButton");
    	browser.waitUntilElementPresent("");
    	browser.verifyText("accessibilityId", "submitTransferButton", "success message");
    }

    // S - Method - to verify Me to Me transfer flow page
    public void transferFlowMeToMe(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
    			if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    				if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    					
    					browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    					browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    					
    					browser.screenShot();
    					meToMeTransfer(amount);
    					System.out.println("Money Transfer is working on Account Listing page for " + accounttype + " account.");
    				}
    				else {
    					browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    					browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    					
    					browser.screenShot();
    					meToMeTransfer(amount);
    					System.out.println("Money Transfer is working on Account Listing page for " + accounttype + " account.");
    				}
    			}
    			else {
    				System.out.println("This user have only one account which is " + accounttype + ". So please use another user for automation testing which have more then one savings or chequing account.");
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
        
    // K - Method - to verify Me to You transfer flow page
    public void transferFlowMeToYou(String accountfromtype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    			
    			accountTypeFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accountfromtype + "']]][1]//*[@content-desc='accountCardType']");
    			accountNoFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accountfromtype + "']]][1]//*[@content-desc='accountCardNumber']");
    			accountTypeTo = browser.getText("xpath", "(//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING'] and .//*[@content-desc='accountCardNumber'][not(@text[contains(.,'" + accountNoFrom + "')])]]//*[@content-desc='accountCardType'])[1]");
    			accountNoTo = browser.getText("xpath", "(//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING'] and .//*[@content-desc='accountCardNumber'][not(@text[contains(.,'" + accountNoFrom + "')])]]//*[@content-desc='accountCardNumber'])[1]");
    			
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accountfromtype + "']]][1]");
    				browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    				
    				accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    				accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    				
    				browser.click("accessibilityId", "backButton");  
    				browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    				browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accountfromtype + "']]][1]//*[@content-desc='moreButton']");  
    				browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    			}
    		}
    		
  browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
  browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
  browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
  browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])");
  browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
  browser.verifyElementPresent("accessibilityId", "TransferHeader");
  browser.sendKeys("accessibilityId", "MoneyInput", amount);
  browser.keyboardKey(66);
 
  browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
  
  //Transfer Review Page
  browser.waitUntilElementPresent("//*[@content-desc='sendFromLabel']");
  browser.verifyText("accessibilityId", "sendFromLabel", "Send from");
  browser.verifyText("accessibilityId", "toText", "to");
  browser.verifyText("accessibilityId", "amountLabel", "for the amount of");
  browser.verifyText("accessibilityId", "amountData", "$" + amount + "0.00");
  browser.click("accessibilityId","scrollToEndButton");
  browser.verifyElementPresent("accessibilityId", "disclaimerNote");
  browser.click("accessibilityId", "submitPaymentButton");
  browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
  browser.verifyElementPresent("accessibilityId", "transferSuccessImage");
  browser.verifyText("accessibilityId", "transferSuccessText", "Success! \nYour transfer is complete.");
  browser.click("accessibilityId", "backToAccountsButton");
  browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
  browser.verifyText("xpath", "//*[@text='" + accountNoFrom + "']", accountNoFrom);
  browser.click("accessibilityId", "submitTransferButton");
  browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
  browser.verifyElementPresent("accessibilityId", "transferSuccessImage");
  browser.verifyText("accessibilityId", "transferSuccessText", "Success! \nYour transfer is complete.");
  browser.click("accessibilityId", "backToAccountsButton");
  browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
  browser.verifyText("xpath", "//*[@text='" + accountNoFrom + "']", accountNoTo);
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
      /*try {
        String accountFromNo = browser.getText("xpath", "(//*[@content-desc='accountCard' and *[@text='" + accountfromtype + "']]//*[@content-desc='accountCardNumber'])[1]");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountFromNo + "']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
        browser.sendKeys("accessibilityId", "MoneyInput", amount);
        browser.keyboardKey(66);
       
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("accessibilityId", "sourceAccountTitle", "Send from");
        browser.verifyText("accessibilityId", "destinationAccountTitle", "to");
        browser.verifyText("accessibilityId", "transferAmountTitle", "for the amount of");
        browser.verifyElementPresent("accessibilityId", "submitTransferButton");
        browser.verifyText("accessibilityId", "sourceAccountTypeAndNumber", accountfromtype + "  ••" + browser.subString(accountFromNo, 5, 9) );
        int size=browser.getCharCount("xpath", "//*[@content-desc='destinationAccountTypeAndNumber']");
        
        if (size == 17) {
          System.out.println("Nickname length is 17, as per requirement");
        }
        else
        {
          System.out.println("Nickname length is not equals to 17 characters");
          System.out.println("The length is " + (size-3));
        }
        
        browser.verifyText("accessibilityId", "transferAmount", "$" + amount + "0.00");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
        browser.verifyElementPresent("accessibilityId", "transferSuccessImage");
        browser.verifyText("accessibilityId", "transferSuccessText", "Success! \nYour transfer is complete.");
        browser.click("accessibilityId", "backToAccountsButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("xpath", "//*[@text='" + accountFromNo + "']", accountFromNo);
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }*/
    
    // K - Method - Transfer Me to You after entering OTP and PIN and click on submit
    public void addBeneficiaryUsingOtpSubmit() {
    	
    	try {
    		browser.click("xpath", "save button"); //click on save button
    		browser.verifyElementPresent("xpath", "Cancel button"); //Verify cancel button
    		browser.verifyElementPresent("xpath", "Submit button"); //Verify submit button
    		browser.sendKeys("xpath", "PIN + TOKEN", "PIN + TOKEN"); //Enter pin and token
    		browser.click("xpath", "submit button"); //click on submit button
    		browser.verifyElementPresent("xpath", "Element in list of beneficiaries page");
    		browser.verifyElementPresent("xpath", "toast message"); //verify the toast message
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - to verify add beneficiary functionality
    public void addBeneficiaryToAccount(String accounttype) {
    	
    	try {
    		if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    			browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='MAKE A TRANSFER BUTTON']");
    			browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    			browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    			browser.waitUntilElementPresent("//*[@content-desc='addButton']");
    		}
    		else {
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
    			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='MAKE A TRANSFER BUTTON']");
    			browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    			browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    			browser.waitUntilElementPresent("//*[@content-desc='addButton']");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
      
    // S - Method - to verify text fields validation on blank page for save button and close button functionality
    public void blankBeneficiaryPagePersonal(String duplicatenickname) {
    	
    	try {
    		browser.click("accessibilityId", "addButton");
    		browser.waitUntilElementPresent("//*[@text='Add New Beneficiary']");
    		browser.click("accessibilityId", "Retail");
    		
    		browser.click("accessibilityId", "firstname");
    		browser.keyboardKey(66);
    		
    		browser.click("accessibilityId", "lastname");
    		browser.keyboardKey(66);
    		
    		browser.click("accessibilityId", "accountnumber");
    		browser.keyboardKey(66);
    		
    		browser.scroll("Down", 200, 400);
    		
    		browser.click("accessibilityId", "nickname");
    		browser.keyboardKey(66);
    		
    		//browser.scroll("Down", 200, 400);
    		
    		//Verify error message for blank fields
    		browser.verifyText("accessibilityId", "firstname-error", "Please enter a first name");
    		browser.verifyText("accessibilityId", "lastname-error", "Please enter a last name");
    		browser.verifyText("accessibilityId", "accountnumber-error", "Please enter a 9 digit account number");
    		browser.verifyText("accessibilityId", "nickname-error", "Please enter a nickname");
    		browser.screenShot();
    		
    		//Verify error message for duplicate nick accessibilityId
    		browser.sendKeys("accessibilityId", "nickname", duplicatenickname); //update nickname value with existing nickname
    		browser.keyboardKey(66);
    		
    		browser.click("accessibilityId", "accountnumber");
    		browser.keyboardKey(66);
        
	        browser.verifyText("accessibilityId", "nickname-error", "The nickname is already in use please try another");
	        browser.screenShot();
	        
	        //Verify the field length for text fields
	        browser.sendKeys("accessibilityId", "firstname", "RFirst Name Pneumonoultramicroscopicsilicovolcanoconiosis");
	        browser.keyboardKey(66);
	        
	        int fNameChar = browser.getCharCount("accessibilityId", "firstname");
	        if (fNameChar == 40) {
	          System.out.println("First Name is 40 character");
	        }
	        else {
	          System.out.println("First Name is " + fNameChar +" character");
	        }
	        
	        browser.sendKeys("accessibilityId", "lastname", "RLast Name Pneumonoultramicroscopicsilicovolcanoconiosis");
	        browser.keyboardKey(66);
	        
	        int lNameChar = browser.getCharCount("accessibilityId", "lastname");
	        if (lNameChar == 40) {
	        	System.out.println("Last Name is 40 character");
	        }
	        else {
	        	System.out.println("Last Name is " + lNameChar + " character");
	        }
	        
	        browser.sendKeys("accessibilityId", "accountnumber", "1234567890");
	        browser.keyboardKey(66);
	        
	        int aNumberCount = browser.getCharCount("accessibilityId", "accountnumber");
	        if (aNumberCount == 9) {
	        	System.out.println("Account Number is 9 digit");
	        }
	        else {
	        	System.out.println("Account Number is " + aNumberCount + " digit");
	        }
	         
	        browser.sendKeys("accessibilityId", "nickname","RNick Name Pneumonoultramicroscopicsilicovolcanoconiosis");        
	        browser.keyboardKey(66);
	        
	        int nNameChar = browser.getCharCount("accessibilityId", "nickname");
	        if (nNameChar == 17) {
	        	System.out.println("Nick Name is 17 character");
	        }
	        else {
	        	System.out.println("Nick Name is " + nNameChar +" character");
	        }
	        
	        browser.screenShot();
	        
	        browser.click("accessibilityId", "closeButton");
	        browser.waitUntilElementPresent("//*[@content-desc='logoutModalFirstTextField']");
	        browser.click("accessibilityId", "logoutModalLogoutButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        System.out.println("blank beneficiary page for Personal tested");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
        
    // S - Method - to verify elements available on Personal add beneficiary page/pop-up message and cancel button functionality
    public void cancelBeneficiaryPersonal() {
    	
    	try {
    		browser.click("accessibilityId", "addButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
	        browser.click("accessibilityId", "Retail");
	        
	        browser.verifyElementPresent("accessibilityId", "firstname");
	        browser.verifyElementPresent("accessibilityId", "lastname");
	        browser.verifyElementPresent("accessibilityId", "accountnumber");
	        browser.verifyElementPresent("accessibilityId", "nickname");
	        
	        browser.scroll("Down", 200, 500);
	        
	        browser.verifyElementPresent("accessibilityId", "cancelBeneficiary");
	        browser.verifyElementPresent("accessibilityId", "saveBeneficiary");
	        
	        browser.screenShot();
	        
	        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
	        browser.verifyText("accessibilityId", "logoutModalFirstTextField", "Cancel adding new beneficiary");
	        browser.verifyText("accessibilityId", "logoutModalSecondTextField", "Are you sure you want to cancel adding a new beneficiary?");
	        
	        browser.verifyElementPresent("accessibilityId", "logoutModalCancelButton");
	        browser.verifyElementPresent("accessibilityId", "logoutModalLogoutButton");
	        
	        browser.screenShot();
	        
	        browser.click("accessibilityId", "logoutModalCancelButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
	        browser.click("accessibilityId", "logoutModalLogoutButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        System.out.println("cancel button on beneficiary page for Personal tested");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
        
    // S - Method - to verify add beneficiary Personal page
    public void beneficiaryPersonal(String nickname) {
    	
    	try {
    		browser.click("accessibilityId", "addButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
	        browser.click("accessibilityId", "Retail");
	        
	        String fName = "Kartosfirstname";
	        String lName = "Kartoslastname";
	        String aNumber = "304510080";
	                       
	        browser.sendKeys("accessibilityId", "firstname", fName);
	        browser.keyboardKey(66);
	        
	        browser.sendKeys ("accessibilityId", "lastname", lName);
	        browser.keyboardKey(66);
	        
	        browser.sendKeys ("accessibilityId", "accountnumber", aNumber);
	        browser.keyboardKey(66);
	        
	        browser.sendKeys ("accessibilityId", "nickname", nickname);
	        browser.keyboardKey(66);
	        
	        String aNumberChange = browser.subString(aNumber, 5, 9); 
	        String aNumberChange1 = "  ••" + aNumberChange;
	        
	        browser.scroll("Down", 200, 400);
	        
	        browser.verifyElementPresent("xpath", "//*[@content-desc='saveBeneficiary']");
	        browser.click("xpath", "//*[@content-desc='saveBeneficiary']");
	        //browser.sleepThread(1000);
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        
	        browser.scroll("Down", 200, 400);
	        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ nickname +"']]", nickname);
	        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='" + "  ••" + aNumberChange + "']]", aNumberChange1);
	        //browser.sleepThread(2000);
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - to verify text fields validation on blank page for save button and close button functionality
    public void blankBeneficiaryPageBusiness() {
    	
    	try {
    		browser.click("accessibilityId", "addButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
	        browser.click("accessibilityId", "Corporate");
	        
	        browser.click("accessibilityId", "companyname");
	        browser.keyboardKey(66);
	        
	        browser.click("accessibilityId", "accountname");
	        browser.keyboardKey(66);
	        
	        browser.click("accessibilityId", "nickname");
	        browser.keyboardKey(66);
	        
	        browser.scroll("Down", 200, 400);
	        
	        //Verify error message for blank fields
	        browser.verifyText("accessibilityId", "companyname-error", "Please enter a business name");
	        browser.verifyText("accessibilityId", "accountname-name-error", "Please enter a 9 digit account number");
	        browser.verifyText("accessibilityId", "nickname-error", "Please enter a nickname");
	        
	        browser.screenShot();
	        
	        //Verify error message for duplicate nick accessibilityId
	        browser.sendKeys("accessibilityId", "nickname","Auto1");
	        browser.keyboardKey(66);
	        
	        browser.click("accessibilityId", "accountnumber");
	        browser.keyboardKey(66);
	        
	        browser.verifyText("accessibilityId", "nickname-error", "The nickname is already in use please try another");
	        browser.screenShot();
	        
	        //Verify the field length for text fields
	        browser.sendKeys("accessibilityId", "companyname", "CName Pneumonoultramicroscopicsilicovolcanoconiosis Pneumonoultramicroscopicsilicovolcanoconiosis");
	        
	        int CName = browser.getCharCount("accessibilityId", "companyname");
	        if (CName == 80) {
	        	System.out.println("Company accessibilityId is 80 character");
	        }
	        else {
	        	System.out.println("Company accessibilityId is " + CName +" character");
	        }
	        
	        browser.sendKeys("accessibilityId", "accountnumber", "1234567890");
	        browser.keyboardKey(66);
	        
	        int aNumberCount = browser.getCharCount("accessibilityId", "accountnumber");
	        if (aNumberCount == 9) {
	        	System.out.println("Account Number is 9 digit");
	        }
	        else {
	        	System.out.println("Account Number is " + aNumberCount + " digit");
	        }
                  
	        browser.sendKeys("accessibilityId", "nickname","RNick Name Pneumonoultramicroscopicsilicovolcanoconiosis");        
	        browser.keyboardKey(66);
	        
	        int nNameChar = browser.getCharCount("accessibilityId", "nickname");
	        if (nNameChar == 17) {
	        	System.out.println("Nick accessibilityId is 17 character");
	        }
	        else {
	        	System.out.println("Nick accessibilityId is " + nNameChar + " character");
	        }
	                 
	        browser.click("accessibilityId", "closeButton");
	        browser.waitUntilElementPresent("//*[@content-desc='logoutModalFirstTextField']");
	        browser.click("accessibilityId", "logoutModalLogoutButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        System.out.println("blank beneficiary page for Corporate tested");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - to verify elements available on Corporate add beneficiary page/pop-up message and cancel button functionality
    public void cancelBeneficiaryBusiness() {
    	
    	try {
    		browser.click("accessibilityId", "addButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
	        browser.click("accessibilityId", "Corporate;");
	        
	        browser.verifyElementPresent("accessibilityId", "companyname");
	        browser.verifyElementPresent("accessibilityId", "accountname");
	        browser.verifyElementPresent("accessibilityId", "nickname");
	        
	        browser.scroll("Down", 200, 500);
	        
	        browser.verifyElementPresent("accessibilityId", "cancelBeneficiary");
	        browser.verifyElementPresent("accessibilityId", "saveBeneficiary");
	        browser.screenShot();
	        
	        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
	        browser.verifyText("accessibilityId", "logoutModalFirstTextField", "Cancel adding new beneficiary");
	        browser.verifyText("accessibilityId", "logoutModalSecondTextField", "Are you sure you want to cancel adding a new beneficiary?");
	        browser.verifyElementPresent("accessibilityId", "logoutModalCancelButton");
	        browser.verifyElementPresent("accessibilityId", "logoutModalLogoutButton");
	        browser.screenShot();
	        
	        browser.click("accessibilityId", "logoutModalCancelButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
	        browser.click("accessibilityId", "logoutModalLogoutButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        System.out.println("cancle button on beneficiary page for Corporate tested");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - to verify add beneficiary Corporate page
    public void beneficiaryBusiness(String nickname) {
    	
    	try {
    		browser.click("accessibilityId", "addButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
	        browser.click("accessibilityId", "corporate");
	
	        String cName = "Kartoscompanyname";
	        String aNumber = "304510080";
	                
	        browser.sendKeys("accessibilityId", "companyname", cName);
	        browser.keyboardKey(66);
	        browser.sendKeys ("accessibilityId", "accountname", aNumber);
	        browser.keyboardKey(66);
	        browser.sendKeys ("accessibilityId", "nickname", nickname);
	        browser.keyboardKey(66);
	        
	        String aNumberChange = browser.subString(aNumber, 5, 9); 
	        String aNumberChange1 = "  ••" + aNumberChange;
	        
	        browser.scroll("Down", 200, 400);
	        browser.verifyElementPresent("xpath", "//*[@content-desc='saveBeneficiary']");
	        browser.click("xpath", "//*[@content-desc='saveBeneficiary']");

	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.scroll("Down", 200, 400);
	        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='" + nickname + "']]", nickname);
	        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='" + "  ••" + aNumberChange + "']]", aNumberChange1);
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - To use in balanceAndExceedMeToMe Methods
    public void balanceAndExceed(String accounttype) {
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
    	browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    	
    	String accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    	String accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    	
    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
                  
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.click("xpath", "//*[@text='MY ACCOUNTS']");
    	browser.click("xpath", "//*[@content-desc='accountCard' and .//*[@text='Savings' or @text='Chequing']][1]");
    	browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[1]");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your available balance is " + accountBalanceFrom + " " + accountCurrencyTypeFrom + ".");
    	
    	browser.sendKeys("accessibilityId", "MoneyInput", accountBalanceFrom + 1);
    	browser.keyboardKey(66);
    	
    	//browser.verifyText("accessibilityId", "Snackbar Message", "Amount exceeds available balance. Please reduce the amount.");
    }
    
    // S - Method - Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount 
    public void balanceAndExceedMeToMe(String accounttype) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				balanceAndExceed(accounttype);
    				System.out.println("Verified the available balance and exceed the total amount for Money Transfer for " + accounttype + " account.");
    			}
    			else {
    				balanceAndExceed(accounttype);
    				System.out.println("Verified the available balance and exceed the total amount for Money Transfer for " + accounttype + " account.");
    			}
    		}
    		else {
    			System.out.println("This user have only one account which is " + accounttype + ". So please use another user for automation testing which have more then one savings or chequing account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - To use in restictionsMeToMe Methods
    public void restictionMToM(String accountfrom, String accountto, String accountFromLastFour) {
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text[contains(.,'" + accountFromLastFour + "')]]]]");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='MAKE A TRANSFER BUTTON']");
    	
    	String accountBalanceFrom = browser.getText("accessibilityId", "availableBalanceBalanceAmount");
    	String accountCurrencyTypeFrom = browser.getText("accessibilityId", "availableBalanceCurrency");
    	
    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.click("xpath", "//*[@text='MY ACCOUNTS']");
    	
    	String accountToLastFour = browser.subString(accountto, 5, 9);
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '•••" + accountToLastFour + "')]]");
    	
    	browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[1]");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your available balance is " + accountBalanceFrom + " " + accountCurrencyTypeFrom + ".");
    	
    	browser.sendKeys("accessibilityId", "MoneyInput", accountBalanceFrom + 1);
    	browser.keyboardKey(66);
    	
    	browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[3]");
    	browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
    	browser.click("accessibilityId", "submitTransferButton");
    	
    	browser.waitUntilElementPresent("//*[@text='Unable to Process']");
    	browser.verifyText("accessibilityId", "errorModalFirstTextField", "Unable to Process");
    	browser.verifyText("accessibilityId", "errorModalSecondTextField", "Sorry, we are not able to process a transfer for this account. Please contact Customer Care at 1-888-622-3478.");
    	browser.screenShot();
    	browser.verifyElementPresent("errorModalButton", "Back to accounts");
    	browser.click("errorModalButton", "Back to accounts");
    	browser.waitUntilElementPresent("//*[@content-desc='welcomeName'");
    }
    
    // S - Method - Error handling for Me to Me Transfers: Verify the error message for restricted accounts
    public void restictionsMeToMe(String accountfrom, String accountto) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    			String accountFromLastFour = browser.subString(accountfrom, 5, 9);
    			browser.screenShot();
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				restictionMToM(accountfrom, accountto, accountFromLastFour);
    				System.out.println("Verified the transfer restictions flow and message for Me to Me flow.");
    			}
    			else {
    				restictionMToM(accountfrom, accountto, accountFromLastFour);
    				System.out.println("Verified the transfer restictions flow and message for Me to Me flow.");
    			}
    		}
    		else {
    			System.out.println("This user have only one savings or chequing account. So please use another user for automation testing which have more then one savings or chequing accounts.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // S - Method - Error handling for Me to Me Transfers: Verify the oops message
    public void oopsMeToMe(String accountfrom, String accountto) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				//balanceAndExceed(accountfrom, accountto);
    				System.out.println("Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
    			}
    			else {
    				//balanceAndExceed(accounttype);
    				System.out.println("Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
    			}
    		}
    		else {
    			//System.out.println("This user have only one account which is " + accounttype + ". So please use another user for automation testing which have more then one savings or chequing account.");
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
        
    // S - Method - Error handling for Me to You Transfers: Verify the available balance and exceed the total amount 
    public void balanceAndExceedMeToYou(String accountfrom, String accountto) {
    	
    	try {
    		
    		String balance = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@content-desc='accountCardBalanceAmount']");
    		String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@content-desc='accountCardBalanceCurrency']");
	        
    		browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
	        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
	        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + accountto + "')]][1]");
	        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[2]");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your current balance is $" + balance + " "+ currency +".");
	        browser.screenShot();
	        browser.sendKeys("accessibilityId", "MoneyInput", "100000");
	        browser.keyboardKey(66);
	        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
	        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
	        browser.click("accessibilityId", "submitTransferButton");
	        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
	        browser.verifyText("accessibilityId", "alertMessage", "Your account has " + balance + " " + currency + ". Please click here and enter a smaller amount in order to complete the transfer."); //update the message as per new D4 screenshot
	        browser.screenShot();
	        browser.click("accessibilityId", "backButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.verifyElementPresent("accessibilityId", "TransferHeader");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
         
    // S - Method - Error handling for Me to You Transfers: Verify the error message for restricted accounts
    public void restictionsMeToYou(String accountfrom, String accountto) {
    	
    	try {
    		browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
	        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
	        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + accountto + "')]][1]");
	        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[2]");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.sendKeys("accessibilityId", "MoneyInput", "10");
	        browser.keyboardKey(66);
	        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
	        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
	        browser.click("accessibilityId", "submitTransferButton");
	        browser.waitUntilElementPresent("//*[@text='Unable to Process']");
	        browser.verifyText("accessibilityId", "errorModalFirstTextField", "Unable to Process");
	        browser.verifyText("accessibilityId", "errorModalSecondTextField", "Sorry, we are not able to process a transfer for this account. Please contact Customer Care at 1-888-622-3478.");
	        browser.screenShot();
	        browser.verifyElementPresent("errorModalButton", "Back to accounts");
	        browser.click("errorModalButton", "Back to accounts");
	        browser.waitUntilElementPresent("//*[@content-desc='welcomeName'");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }

    // S - Method - Error handling for Me to You Transfers: Verify the oops message
    public void oopsMeToYou(String accountfrom, String accountto) {
    	
    	try {
    		browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
	        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
	        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + accountto + "')]][1]");
	        browser.click("accessibilityId", "Next Button Enabled");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.sendKeys("accessibilityId", "MoneyInput", "10");
	        browser.keyboardKey(66);
	        browser.click("accessibilityId", "Next Button Enabled");
	        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
	        browser.click("accessibilityId", "submitTransferButton");
	        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
	        browser.verifyText("accessibilityId", "alertMessage", "Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
	        browser.screenShot();
	        browser.click("accessibilityId", "backButton");
	        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
	        browser.verifyElementPresent("accessibilityId", "TransferHeader");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - Transfer Me to You after entering OTP and PIN and click on submit
    public void addBeneficiaryUsingOtpCancel() {
    	
    	try {
    		browser.click("xpath", "save button"); //click on save button
	        browser.verifyElementPresent("xpath", "Cancel button"); //Verify cancel button
	        browser.verifyElementPresent("xpath", "Submit button"); //Verify submit button
	        browser.sendKeys("xpath", "PIN + TOKEN", "PIN + TOKEN"); //Enter pin and token
	        browser.click("xpath", "cancel button"); //click on submit button
	        browser.verifyElementPresent("xpath", "Element in list of beneficiaries page");
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - To use in verifyLimitMeToMe method
    public void LimitsMeToMe(String accounttype, String amount) {
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']"); 
    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.click("xpath", "//*[@text='MY ACCOUNTS']");
    	browser.click("xpath", "(//*[@content-desc='accountCard'][1])[2]");
    	browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[1]");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.sendKeys("accessibilityId", "MoneyInput", amount);
    	browser.keyboardKey(66);
    	browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[3]");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
    	browser.click("accessibilityId", "submitTransferButton");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
    	
    	browser.verifyText("accessibilityId", "alertMessage", "We're sorry, this transfer exceeds the limit. Please click here and enter a smaller amount in order to complete the transfer.");
    }
    
    //K - Method - to verify Limits while transferring money for me to me
    public void verifyLimitMeToMe(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				LimitsMeToMe(accounttype, amount);
    				System.out.println("Succesully verify the alert message for Me to Me limt exceed");
    			}
    			else {
    				LimitsMeToMe(accounttype, amount);
    				System.out.println("Succesully verify the alert message for Me to Me limt exceed");
    			}
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
    
    // K - Method - To use in verifyLimitMeToYou method
    public void LimitsMeToYou(String accounttype, String amount) {
    	
    	browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']"); 
    	browser.click("accessibilityId", "MAKE A TRANSFER BUTTON");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    	browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
    	browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[2]");
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.sendKeys("accessibilityId", "MoneyInput", amount);
    	browser.keyboardKey(66);
    	browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[3]");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
    	browser.click("accessibilityId", "submitTransferButton");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
    	String msg = browser.getText("accessibilityId", "alertMessage");
    	System.out.println(msg );
    	
    	browser.verifyText("accessibilityId", "alertMessage", "We're sorry, this transfer exceeds the limit. Please click here and enter a smaller amount in order to complete the transfer.");
    }
    
    //K - Method - to verify Limits while transferring money for me to you
    public void verifyLimitMeToYou(String accounttype, String amount) {
    	
    	try {
    		if (browser.getSize("xpath", "//*[@content-desc='accountCard' and .//*[@text='SAVINGS' or @text='CHEQUING']]") >= 2) {
    			if (browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
    				browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
    				LimitsMeToYou(accounttype, amount);
    				System.out.println("Succesully verify the alert message for limt exceed");
    			}
    			else {
    				LimitsMeToYou(accounttype, amount);
    				System.out.println("Succesully verify the alert message for limt exceed");
    			}
    		}
    	} catch (NoSuchElementException e) {
    		System.out.println("Element Not Found");
    		e.printStackTrace();
    	}
    }
}
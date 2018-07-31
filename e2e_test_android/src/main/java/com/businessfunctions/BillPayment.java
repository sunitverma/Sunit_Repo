package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;

public class BillPayment {

    public static String accountNoFrom;
    public static String accountBalanceFrom;
    public static String accountCurrencyTypeFrom;
  
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
	        //browser.verifyText("accessibilityId", "sendFromData", browser.titleCase("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardType']") + " " + accountNoFrom);
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
    
    // A - Method - To use in billPaymentListPage and billPaymentSummaryPage method error
    public void billPaymentEndToEndErr(String accounttype, String amount, String accountNoFrom, String accountBalanceFrom, String accountCurrencyTypeFrom) {
    	
    	try {
    	
    	browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
    	browser.verifyText("accessibilityId", "TransferHeader", "Who would you like to pay?");
      
    	if (browser.getSize("accessibilityId", "Bill Payee Nickname") != 0) {
    		
    		browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
    		browser.click("accessibilityId", "Next Button Enabled");
    		browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
             
    		browser.sendKeys("accessibilityId", "MoneyInput", amount);
        
	        String moneyInput = browser.getText("accessibilityId", "MoneyInput");
	        browser.keyboardKey();     
	        browser.click("accessibilityId", "Next Button Enabled");
	        
	        browser.waitUntilElementPresent("//*[@content-desc='ReviewTitle']");
	        browser.verifyText("accessibilityId", "ReviewTitle", "Please review and confirm details");
	       
	        browser.verifyText("accessibilityId", "toText", "to");
	       
	        browser.verifyText("accessibilityId", "amountLabel", "for the amount of");
	        browser.verifyText("accessibilityId", "amountCurrency", accountCurrencyTypeFrom);
	        browser.verifyText("accessibilityId", "amountData", moneyInput);
	        
	        browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton']");
	        browser.click("accessibilityId", "scrollToEndButton");
	        
	        browser.verifyText("accessibilityId", "disclaimerNote", "Note: Once you select the Submit Payment button, you CANNOT undo this payment.");
	        browser.click("accessibilityId", "submitPaymentButton");
	        
	        browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
	        browser.verifyText("accessibilityId", "Snackbar Message", "Unable to process. You may have exceeded your payment limit or there was an error submitting your request.");
	        System.out.println("Err msg is working fine for " + accounttype + " account.");
	        browser.click("accessibilityId", "backButton");
	        browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
	        browser.click("accessibilityId", "Next Button Enabled");
	        browser.click("accessibilityId", "scrollToEndButton");
	        browser.waitUntilElementPresent("//*[@content-desc='disclaimerNote']");
	        browser.verifyText("accessibilityId", "disclaimerNote", "Note: Once you select the Submit Payment button, you CANNOT undo this payment.");
	        
	        if (browser.getSize("accessibilityId", "disclaimerNote") != 0) {
	        	System.out.println("Error message is not shown after coming back from prvious page, so test passed");
	        }
	        else {
	        	System.out.println("Error message is shown, so test not  passed");
	        }
    	}
    	else {
    		browser.verifyText("accessibilityId", "No Payees Message", "There are no payees to display.\nPlease visit JNCB online to add a new payee.");
    		System.out.println("No Payee for this user so please use another user for automation testing which have payee");
    	}
    	} catch (NoSuchElementException e) {
            System.out.println("Element Not Found");
            e.printStackTrace();
    	}
    }
        
    // A - error msg on summary page when payment limit exceeds
    public void billPaymentSummaryPageErr(String accounttype, String amount) {
        
        try {
        	if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
        		
        		accountNoFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardNumber']");
        		accountBalanceFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardBalanceAmount']");
        		accountCurrencyTypeFrom = browser.getText("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='accountCardBalanceCurrency']");
        		
        		if (browser.getSize("accessibilityId", "PAY A BILL BUTTON") != 0) {
        			browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='PAY A BILL BUTTON']]");
        			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
        			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
        			browser.click("accessibilityId", "PAY A BILL BUTTON");
	              
        			browser.screenShot();
        			billPaymentEndToEndErr(accounttype, amount, accountNoFrom, accountBalanceFrom, accountCurrencyTypeFrom);
        		}
        		else {
        			browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]");
        			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
        			browser.click("xpath", "//*[@text='submitPaymentButton']");
              
        			browser.screenShot();
        			billPaymentEndToEndErr(accounttype, amount, accountNoFrom, accountBalanceFrom, accountCurrencyTypeFrom);
        		}
        	}
        	else {
        		System.out.println("No " + accounttype + " is account available for this user. So please use another user for automation testing which have " + accounttype + " account.");
        	}
        } catch (NoSuchElementException e) {
        	System.out.println("Element Not Found");
        	e.printStackTrace();
        }
    }
    
	// A- Add a Payee functionality automated navigation to Add payee  page 07-06-2018
	public void PayeePagenav() {
		
		try {
			browser.click("xpath", "//*[@content-desc='accountCard'][1]");
			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			browser.click("xpath", "//*[@content-desc='PAY A BILL BUTTON']");
			browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
			browser.click("accessibilityId", "addNewPayeeButton");
			browser.screenShot();
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	// A - Add Payee functionality automated 07-06-2018
	public void addPayee() {
		
		try {
			browser.click("className", "android.widget.EditText");
			browser.waitUntilElementPresent("//*[@text='Ag Chem Plant Agcp']");
			browser.click("xpath", "//*[@text='Ag Chem Plant Agcp']");
			browser.keyboardKey();
			browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
			browser.click("accessibilityId", "Next Button Enabled");
			browser.waitUntilElementPresent("//*[@content-desc='selectedMerchantNameLabel']");
			browser.verifyText("xpath", "//*[@content-desc='selectedMerchantNameLabel']", "Merchant Name: AG CHEM PLANT");
			browser.verifyText("xpath", "//*[@content-desc='selectedCategoryNameLabel']", "Category: Other Bills");
			browser.sendKeys("xpath", "//*[@content-desc='accountNumber']", "2326805");
			browser.sendKeys("xpath", "//*[@content-desc='nickName']", "ni" + System.currentTimeMillis());
			browser.click("xpath", "//*[@content-desc='selectedMerchantHint']");
			browser.waitUntilElementPresent("//*[@content-desc='Save Button']");
			browser.click("xpath", "//*[@content-desc='Save Button']");
			browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
			browser.verifyText("xpath", "//*[@content-desc='Snackbar Message']", "Success! New bill payee added.");
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}

	// A - Add Payee cancel modal test
	public void addPayeeCancel() {
		
		try {
			browser.click("accessibilityId", "addNewPayeeButton");
			browser.click("xpath", "//*[@content-desc='closeButton']");
			browser.waitUntilElementPresent("//*[@content-desc='CancelAddPayeeModalHeader']");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalHeader']"), "Cancel add new payee");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalBody']"), "Are you sure you want to cancel adding a new payee?");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalReturnButton']"), "Cancel");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalAcceptButton']"), "Yes");
			browser.click("xpath", "//*[@content-desc='CancelAddPayeeModalReturnButton']");
			browser.waitUntilElementPresent("//*[@content-desc='Bill Payee Card UnSelected']");
			
			if (browser.getSize("accessibilityId", "Bill Payee Card UnSelected") != 0) {
				System.out.println("Cancel button press works, as user remains on Add Payee page");
			}
			else {
				System.out.println("Cancel button press failed");
			}
			
			browser.click("xpath", "//*[@content-desc='closeButton']");
			browser.waitUntilElementPresent("//*[@content-desc='CancelAddPayeeModalHeader']");
			browser.click("xpath", "//*[@content-desc='CancelAddPayeeModalAcceptButton']");
			browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
			
			if (browser.getSize("accessibilityId", "TransferHeader") != 0) {
				System.out.println("Yes button press works, as user Navigates to payment page");
			}
			else {
				System.out.println("Yes button press for Modal failed");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	// A - Add Payee functionality automated 07-06-2018
	public void ErrHandlingaddPayee() {
		
		try {	
			browser.click("xpath", "//*[@content-desc='categoryCard'and ./*[@text='Cable']]");
	
			browser.click("className", "android.widget.TextView");
			browser.waitUntilElementPresent("//*[@text='Flow Flow']");
			browser.click("xpath", "//*[@text='Flow Flow']");
			browser.keyboardKey();
			browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
			browser.click("accessibilityId", "Next Button Enabled");
			browser.waitUntilElementPresent("//*[@content-desc='selectedMerchantNameLabel']");
			
			browser.sendKeys("xpath", "//*[@content-desc='accountNumber']", "00000");
			browser.sendKeys("xpath", "//*[@content-desc='nickName']", "ni" + System.currentTimeMillis());
			
			browser.waitUntilElementPresent("//*[@content-desc='Save Button']");
			browser.click("xpath", "//*[@content-desc='Save Button']");
			browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
			browser.verifyText("xpath", "//*[@content-desc='Snackbar Message']", "Invalid Account Number");
			
			browser.clearTextField("xpath", "//*[@content-desc='accountNumber']");
			browser.sendKeys("xpath", "//*[@content-desc='accountNumber']", "12345678");
			browser.keyboardKey();
			browser.clearTextField("xpath", "//*[@content-desc='nickName']");
			browser.sendKeys("xpath", "//*[@content-desc='nickName']", "Jps Bill");
			
			browser.click("xpath", "//*[@text[starts-with(.,'12345678')]]");
			
			if (browser.getSize("xpath", "//*[@text='This nickname is already in use, please try another']")!=0){
				System.out.println("Error Message for already existing nickname shown");
			}
			else {
				System.out.println("Error Message for already existing nickname is not shown nickname ");
			}
							
			browser.clearTextField("xpath", "//*[@content-desc='nickName']");
			browser.sendKeys("xpath", "//*[@content-desc='nickName']", "adityasharmaeleva");
				
			browser.click("xpath", "//*[@text[starts-with(.,'12345678')]]");
			browser.verifyText("xpath", "//*[@content-desc='nickName']", "adityasharmaeleva");
				
			browser.click("xpath", "//*[@content-desc='Cancel Button']");
			browser.waitUntilElementPresent("//*[@content-desc='CancelAddPayeeModalHeader']");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalHeader']"), "Cancel add new payee");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalBody']"), "Are you sure you want to cancel adding a new payee?");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalReturnButton']"), "Cancel");
			browser.verifyText("xpath", ("//*[@content-desc='CancelAddPayeeModalAcceptButton']"), "Yes");
			browser.click("xpath", "//*[@content-desc='CancelAddPayeeModalReturnButton']");
			browser.waitUntilElementPresent("//*[@content-desc='nickName']");
				
			browser.click("xpath", "//*[@content-desc='Cancel Button']");
			browser.waitUntilElementPresent("//*[@content-desc='CancelAddPayeeModalHeader']");
			browser.click("xpath", "//*[@content-desc='CancelAddPayeeModalAcceptButton']");
			browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
				
			if (browser.getSize("accessibilityId", "TransferHeader") != 0) {
				System.out.println("Yes button press works, as user Navigates to payment page");
			}
			else {
				System.out.println("Yes button press for Modal failed");
			}
		} catch (NoSuchElementException e) {
				System.out.println("Element Not Found");
				e.printStackTrace();
			}
			
	}
	
	//	A - PayBills from Credit cards automated 14-06-2018 // payBillsCreditCardCancelModal();
	public void payBillsCreditCard() {
		
		try {	
			String CreditaccountNoFrom;
		    String CreditaccountBalanceFrom;
		    String CreditPayee;
		    browser.click("xpath", "//*[@content-desc='creditCard']");
			browser.waitUntilElementPresent("//*[@content-desc='ccName']");
			
			CreditaccountNoFrom = browser.getText("xpath", "//*[@content-desc='cardNumber']");
			CreditaccountBalanceFrom  = browser.getText("xpath", "//*[@content-desc='availableBalance']");
			
			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			browser.click("accessibilityId", "PAY A BILL BUTTON");

			browser.waitUntilElementPresent("//*[@content-desc='Bill Payee Nickname']");
			
			if (browser.getSize("accessibilityId", "Bill Payee Nickname") != 0) {
				CreditPayee = browser.getText("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
		        
				browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
		        browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
		        browser.click("accessibilityId", "Next Button Enabled");
		        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");

		        browser.waitUntilElementPresent("//*[@content-desc='MoneyInput']");
		        browser.sendKeys("accessibilityId", "MoneyInput", "50000");
		        browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
		        browser.click("accessibilityId", "Next Button Enabled");
		        
		        browser.waitUntilElementPresent("//*[@content-desc='ReviewTitle']");
		        browser.verifyText("accessibilityId", "ReviewTitle", "Please review and confirm details");
		        browser.verifyText("accessibilityId", "sendFromLabel", "Send from");
		 
		        String fullname = browser.getText("xpath", "//*[@content-desc='sendFromData']");
		        int startPt = (fullname.length()- 4);
		        String cmptxt = browser.subString(fullname,startPt,fullname.length()); 

		        int in =(CreditaccountNoFrom.length() - 4); 
		        String cardNum = browser.subString(CreditaccountNoFrom, in, CreditaccountNoFrom.length());
		        
		        if (cardNum.equals(cmptxt)) {
		        	System.out.println("Account Number is correctly shown");
		        }
		        else {
		        	System.out.println("Account Number is not correctly shown");
		        }
		        
		        browser.verifyText("accessibilityId", "toText", "to");
		        browser.verifyText("accessibilityId", "toData", CreditPayee);
		       
		        browser.click("accessibilityId", "scrollToEndButton");
		        browser.verifyText("accessibilityId", "amountLabel", "for the amount of");
		        browser.verifyText("accessibilityId", "amountData", "$500.00");
		        
		        browser.verifyText("accessibilityId", "disclaimerNote", "Note: Once you select the Submit Payment button, you CANNOT undo this payment.");
		        browser.click("accessibilityId", "submitPaymentButton");
		        browser.waitUntilElementPresent("//*[@content-desc='successMessage']");
		        
		        browser.verifyText("accessibilityId", "successMessage", "Success! \nYour bill payment is complete.");
		        browser.verifyElementPresent("accessibilityId", "refernceNumber");
		        browser.verifyText("accessibilityId", "refernceNumber", browser.getText("xpath", "//*[@text[starts-with(.,'Reference #:')]]"));
		        
		        System.out.println("Bill payment is working fine for Credit Cards.");
		        browser.click("accessibilityId", "PAY ANOTHER BILL Button");
		        System.out.println("Verifying Pay another Bill functionality");
		        
		        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
		        browser.verifyText("accessibilityId", "TransferHeader", "Who would you like to pay?");
		        System.out.println("Verifying Back to Accounts button functionality");
		        
		        browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
		        browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
		        browser.click("accessibilityId", "Next Button Enabled");
		        
		        browser.waitUntilElementPresent("//*[@content-desc='MoneyInput']");
		        browser.sendKeys("accessibilityId", "MoneyInput", "50000");
		        browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
		        browser.click("accessibilityId", "Next Button Enabled");
		        browser.waitUntilElementPresent("//*[@content-desc='disclaimerNote']");
		        browser.click("accessibilityId", "scrollToEndButton");
		        browser.waitUntilElementPresent("//*[@content-desc='submitPaymentButton']");
		        browser.click("accessibilityId", "submitPaymentButton");
		        browser.waitUntilElementPresent("//*[@content-desc='Back to Accounts Button']");
		        browser.click("accessibilityId", "Back to Accounts Button");
		        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
		        browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	//	A - Credit Card payment cancel modal test  18-06-2018 
	public void payBillsCreditCardCancelModal() {
		
		try {
			System.out.println("Validating cancel Payment modal functionality on money input page");
			browser.click("xpath", "//*[@content-desc='creditCard']");
			browser.waitUntilElementPresent("//*[@content-desc='ccName']");
			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			browser.click("accessibilityId", "PAY A BILL BUTTON");
			browser.waitUntilElementPresent("//*[@content-desc='Bill Payee Nickname']");
		
			if (browser.getSize("accessibilityId", "Bill Payee Nickname") != 0) {
				browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
			    browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
			    browser.click("accessibilityId", "Next Button Enabled");
			    browser.waitUntilElementPresent("//*[@content-desc='Cancel Button']");
			    
			    browser.click("accessibilityId", "Cancel Button");

			    browser.waitUntilElementPresent("//*[@content-desc='CancelPaymentModalHeader']");
			    browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalHeader']"), "Cancel Payment");
			    browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalBody']"), "Are you sure you want to cancel this payment?");
			    browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalReturnButton']"), "No");
			    browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalAcceptButton']"), "Yes");
			    browser.click("xpath", "//*[@content-desc='CancelPaymentModalReturnButton']");
			    browser.waitUntilElementPresent("//*[@content-desc='Cancel Button']");
			    
			    String a =browser.getText("xpath", "//*[@text[starts-with(.,'Your available balance is')]]");
			    browser.verifyElementPresent("xpath", "//*[@content-desc='MoneyInput']");
			    browser.click("accessibilityId", "Cancel Button");
			    browser.waitUntilElementPresent("//*[@content-desc='CancelPaymentModalHeader']");
			    browser.click("accessibilityId", "CancelPaymentModalAcceptButton");
			    browser.waitUntilElementPresent("//*[@content-desc='tabBarLogo']");
	               
			    boolean accpPress1;
			    accpPress1 = browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
	        	if(accpPress1) {
	        		System.out.println("Cancel Modal for Yes works fine on input payment Page");
	        	}
	        	else {
	        		System.out.println("Cancel Modal for Yes not working on input payment Page");
	        	}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	//	A - Credit Card payment cancel modal test  18-06-2018 
	public void payBillsCreditCardCancelModalreviewPage() {
		try {
			System.out.println("Validating cancel Payment modal functionality on Payment Review page");
			browser.click("xpath", "//*[@content-desc='creditCard']");
			browser.waitUntilElementPresent("//*[@content-desc='ccName']");
			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			browser.click("accessibilityId", "PAY A BILL BUTTON");
			browser.waitUntilElementPresent("//*[@content-desc='Bill Payee Nickname']");
			
			if (browser.getSize("accessibilityId", "Bill Payee Nickname") != 0) {
				browser.getText("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
				browser.click("xpath", "(//*[@content-desc='Bill Payee Nickname'])[2]");
				browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
				browser.click("accessibilityId", "Next Button Enabled");
				browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
				
				browser.waitUntilElementPresent("//*[@content-desc='MoneyInput']");
				browser.sendKeys("accessibilityId", "MoneyInput", "50000");
				browser.waitUntilElementPresent("//*[@content-desc='Next Button Enabled']");
				browser.click("accessibilityId", "Next Button Enabled");
				browser.waitUntilElementPresent("//*[@content-desc='disclaimerNote']");
				browser.click("accessibilityId", "Cancel Button");
				
				browser.waitUntilElementPresent("//*[@content-desc='CancelPaymentModalHeader']");
				browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalHeader']"), "Cancel Payment");
				browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalBody']"), "Are you sure you want to cancel this payment?");
				browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalReturnButton']"), "No");
				browser.verifyText("xpath", ("//*[@content-desc='CancelPaymentModalAcceptButton']"), "Yes");
				browser.click("xpath", "//*[@content-desc='CancelPaymentModalReturnButton']");
				browser.waitUntilElementPresent("//*[@content-desc='Cancel Button']");
				
				boolean onPaymentpage;
				onPaymentpage = browser.verifyText("xpath", "//*[@content-desc='ReviewTitle']", "Please review and confirm details");
				if(onPaymentpage) {
					System.out.println("Cancel Modal for No works fine on review Page");
				}
				else {
					System.out.println("Cancel Modal for No not working on review Page");
				}
				        
				browser.click("accessibilityId", "Cancel Button");
				browser.waitUntilElementPresent("//*[@content-desc='CancelPaymentModalHeader']");
				browser.click("accessibilityId", "CancelPaymentModalAcceptButton");
				browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
				
				boolean accpPress;
				accpPress = browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
				if(accpPress) {
					System.out.println("Cancel Modal for Yes works fine on review Page");
				}
				else {
					System.out.println("Cancel Modal for Yes not working on review Page");
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	// A Error message on selecting Restricted payee from credit card pay a bill 
	public void creditCardRestrictedPay() {
		
		try {
			browser.waitUntilElementPresent("//*[@content-desc='creditCard'][1]");
			browser.click("xpath", "//*[@content-desc='creditCard'][1]");
			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			browser.click("xpath", "//*[@content-desc='PAY A BILL BUTTON']");
			browser.waitUntilElementPresent("//*[@text='Add a New Payee']");
			browser.click("xpath","//*[@text='Adincbcrunion']");
			browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
			browser.verifyText("xpath", "//*[@content-desc='Snackbar Message']", "Payments to this merchant, using a Credit Card is not allowed. Please select a different source account.");
			browser.click("xpath", "//*[@text='BACK']"); 
			browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
			browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
			
			browser.click("xpath", "//*[@content-desc='creditCard'][1]");
			browser.waitUntilElementPresent("//*[@content-desc='PAY A BILL BUTTON']");
			browser.click("xpath", "//*[@content-desc='PAY A BILL BUTTON']");
			browser.waitUntilElementPresent("//*[@text='Add a New Payee']");
			browser.click("xpath","//*[@text='Adincbcrunion']");
			browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
			
			browser.click("xpath", "//*[@text='1st Guardsmann']");
			browser.waitUntilElementPresent("//*[@text='NEXT']");
			browser.verifyElementPresent("xpath", "//*[@text='NEXT']");
			browser.click("xpath", "//*[@content-desc='backButton']");
			
			browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
			browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
}

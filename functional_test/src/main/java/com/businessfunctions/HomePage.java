package com.businessfunctions;

import com.library.Common;
import org.openqa.selenium.NoSuchElementException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class HomePage {
    
    ExtentReports extent;
    ExtentTest test;
    
    Common browser;
    
    //constructor with one argument.
    public HomePage(Common br) {
      browser = br;
    }
       
    // K - Method - to verify the available accounts    
    public void homePageWithAccounts() {
      
      try {
        if((browser.getSize("accessibilityId", "accountCard") != 0)) {
          
          if(browser.getSize("xpath", "//*[@content-desc='accountCardType'][@text='SAVINGS']") != 0) {
            System.out.println("Savings account is available");
          }
          
          if(browser.getSize("xpath", "//*[@content-desc='accountCardType'][@text='CHEQUING']") != 0) {
            System.out.println("Chequing account is available");
          }
          
          if(browser.getSize("xpath", "//*[@content-desc='accountCardType'][@text='LOAN']") != 0) {
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
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        
        if(browser.getSize("accessibilityId", "alertMessage") != 0) {
          String msg = browser.getText("accessibilityId", "alertMessage");
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
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]") != 0) {
          if (browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='LOAN']]]//*[@content-desc='moreButton']")!=0) {
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

    // S - Method - To use in transferButtonOnLandingPage method
    public void transferButtonFlow(String accounttype) {
      
      browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]][1]//*[@content-desc='moreButton']");  
      browser.click("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]//*[@content-desc='MAKE A TRANSFER BUTTON']");
      browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
      browser.screenShot();
      browser.verifyText("accessibilityId", "TransferHeader", "Where would you like to transfer to?");
      browser.verifyElementPresent("accessibilityId", "backButton");
      browser.click("accessibilityId", "backButton");
      browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
      browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    }
    
    // S - Method - to verify transfer button working for saving and Chequing accounts on landing page and back button working
    public void transferButtonOnLandingPage(String accounttype) {
      
      try {
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and ./*[./*[@text='" + accounttype + "']]]")!= 0) {
          if(browser.getSize("accessibilityId", "MAKE A TRANSFER BUTTON") != 0) {
            browser.click("xpath", "//*[@content-desc='moreButton' and ./following-sibling::*[@content-desc='MAKE A TRANSFER BUTTON']]");
            browser.screenShot();
            transferButtonFlow(accounttype);
            System.out.println("Transfer and back button are working on Landing page for " + accounttype + " account.");
          }
          else {
            browser.screenShot();
            transferButtonFlow(accounttype);
            System.out.println("Transfer and back button are working on Landing page for " + accounttype + " account.");
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
}
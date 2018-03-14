package com.businessfunctions;

import com.library.Common;

import org.openqa.selenium.NoSuchElementException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Login {
    
    ExtentReports extent;
    ExtentTest test;
    
    Common browser;
    
    //constructor with one argument.
    public Login(Common br) {
      browser = br;
    }
        
    // S - Method - Login to NCB Mobile Application
    public void loginToApp(String username, String password) {
      
      try {
        browser.waitUntilElementPresent("//*[@content-desc='tabBarLogo']");
        
        browser.sendKeys("accessibilityId", "usernameInput", username);
        browser.keyboardKey(66);
        
        browser.sendKeys("accessibilityId", "passwordInput", password);
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "loginButtonText");
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
    
    // M - Method - Expected - Verifying the Login button while login with Empty Credentials
    public void emptyLoginExpeceted() {
      
      try {
        boolean buttonValue = browser.isButtonEnabled("accessibilityId", "loginButtonText");
        
        if (buttonValue == true) {
          test.pass("Login button is disabled - Please enter the credentials"); //.pass(driver.getTitle() +" contain "+"QA manual");
          
          //System.out.println("Login button is disabled - Please enter the credentials");
        }
        else {
          test.log(Status.FAIL, "Button is Enabled" );
        }
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
     
    // M - Method - Expected - Login with invalid user accessibilityId  
    public void invalidUserExpected() {
      
      try {
        String msg = "";
        browser.verifyText("accessibilityId", "alertMessage", msg);
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
        
    // M - Method - Expected - Login with invalid password
    public void invalidPasswordExpected() {
      
      try {
        String msg = "You will be locked out after 2 more password attempts. To unlock your account, you will need to call Customer Care at 1-888-622-3478.";
        browser.verifyText("accessibilityId", "alertMessage", msg);
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
       
    // S - Method - Retry 3 times  
    public void retryPassword(String username, String password) {
      
      try {
        //update the expected error message
        String msg1 = "You will be locked out after 2 more password attempts. To unlock your account, you will need to call Customer Care at 1-888-622-3478.";
        String msg2 = "You will be locked out after 1 more password attempts. To unlock your account, you will need to call Customer Care at 1-888-622-3478.";
        String msg3 = "You have been locked out. To reactivate your account, please call Customer Care at 1-888-622-3478.";
        
        browser.waitUntilElementPresent("//*[@content-desc='Username']");
        browser.sendKeys("xpath", "//*[@content-desc='Username']", username);
        browser.keyboardKey(66);
        
        browser.sendKeys("xpath", "//*[@content-desc='Password']", password);
        browser.keyboardKey(66);
        
        //First error message
        browser.click("accessibilityId", "loginButtonText");
        browser.screenShot();
        
        String errormsg1 = browser.getText("accessibilityId", "alertMessage");
        if (errormsg1.equals(msg1)) {
          System.out.println("Expected first message is correct");
        }
        else {
          System.out.println("Expected first message is wrong");
        }
        
        //Second error message
        browser.click("accessibilityId", "loginButtonText");
        browser.screenShot();
        
        String errormsg2 = browser.getText("accessibilityId", "alertMessage");
        if (errormsg2.equals(msg2)) {
          System.out.println("Expected second message is correct");
        }
        else {
          System.out.println("Expected second message is wrong");
        }
        
        //Third error message
        browser.click("accessibilityId", "loginButtonText");
        browser.screenShot();
        
        String errormsg3 = browser.getText("accessibilityId", "alertMessage");
        if (errormsg3.equals(msg3)) {
          System.out.println("Expected third message is correct");
        }
        else {
          System.out.println("Expected third message is wrong");
        }
        
        //if message is wrong the test case will fail
        browser.compareTexts(errormsg1, msg1);
        browser.compareTexts(errormsg2, msg2);
        browser.compareTexts(errormsg3, msg3);
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }  
    }

    // S - Method - Expected - Login with invalid password
    public void logOutButtonFunction() {
      
      try {
        browser.verifyElementPresent("accessibilityId", "logoutButton");
        
        browser.verifyText("accessibilityId", "logoutButton", "Log out");
        
        browser.click("accessibilityId", "logoutButton");
        
        browser.verifyText("accessibilityId", "logoutModalFirstTextField", "Leaving already?");
        browser.verifyText("accessibilityId", "logoutModalSecondTextField", "Are you sure you want to log out?");
        
        browser.verifyElementPresent("accessibilityId", "logoutModalCancelButton");
        browser.verifyElementPresent("accessibilityId", "logoutModalLogoutButton");
        browser.screenShot();
        
        browser.click("accessibilityId", "logoutModalCancelButton");
        browser.verifyText("accessibilityId", "welcomeName", browser.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
        browser.screenShot();
        System.out.println("Cancel button working fine on Log out pop up");
        
        browser.click("accessibilityId", "logoutButton");
        
        browser.click("accessibilityId", "logoutModalLogoutButton");
        browser.verifyElementPresent("accessibilityId", "logo");
        browser.screenShot();
        System.out.println("Log out button working fine on Log out pop up");
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }

    // S - Method - Verifying the Feed back button functionality
    public void feedBackButtonFunction() {
      
      try {
        browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[8]");
        browser.click("xpath", "(//*[@class='android.view.View'])[8]");
        
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[1]", "Email us your Feedback");
        browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[10]");
        
        browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[2]", "WhatsApp us your Feedback");
        browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[13]");
        browser.screenShot();
        browser.click("xpath", "(//*[@class='android.view.View'])[10]");
        
        browser.verifyText("id", "from_account_name", "kratosbuild@gmail.com");
        browser.verifyText("id", "to", "<mobileappfeedback@jncb.com>, ");
        browser.verifyText("id", "subject", "Mobile App Feedback");
        browser.sendKeys("xpath", "//*[@text='Compose email']", "Feed back for NCB App via Automation Script");
        browser.click("id", "send");
        browser.screenShot();
        
        browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[8]");
        browser.click("xpath", "(//*[@class='android.view.View'])[8]");
       
        browser.click("xpath", "(//*[@class='android.widget.TextView'])[2]");
        browser.verifyElementPresent("id", "entry");
        
        browser.sendKeys("id", "entry", "Feed back for NCB App via Automation Script");
        browser.click("id", "send");
        browser.screenShot();
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }

    // K - Method - Verifying Terms and Conditions
    public void termsAndConditionsExists(String username, String password) {
      
      try {
        if(browser.getSize("accessibilityId", "welcomeName") != 0) {
          System.out.println("The user has already accepted Terms and Conditions");
        }
        else if(browser.getSize("accessibilityId", "scrollToEndButton") != 0) {
          browser.click("accessibilityId", "scrollToEndButton");
          browser.verifyElementPresent("accessibilityId", "declineButton");
          browser.click("accessibilityId", "declineButton");
          
          if(browser.verifyElementPresent("accessibilityId", "usernameInput")) {
            browser.waitUntilElementPresent("//*[@content-desc='usernameInput']");
            browser.sendKeys("accessibilityId", "usernameInput", username);
            browser.keyboardKey(66);
            
            browser.sendKeys("accessibilityId", "passwordInput", password);
            browser.keyboardKey(66);
            
            browser.click("accessibilityId", "loginButtonText");
            
            browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton']");
            browser.screenShot();
            
            browser.click("accessibilityId", "scrollToEndButton");
            
            browser.verifyElementPresent("accessibilityId", "acceptButton");
            browser.screenShot();
            
            browser.click("accessibilityId", "acceptButton");
            
            System.out.println("The user accept Terms and Conditions");
            browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
          }
        }
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
 
    // K - Method - Accept Terms and Conditions
    public void acceptTermAndConditions() {
      
      try {
        if(browser.getSize("accessibilityId", "welcomeName") != 0) {
          //Nothing to print
          browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        }
        else if(browser.getSize("accessibilityId", "scrollToEndButton") != 0) {
          browser.click("accessibilityId", "scrollToEndButton");
          browser.click("accessibilityId", "acceptButton");
          browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        }
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }

    // S - Method - to verify animation displayed on Landing page
    public void animationOnLandingPage() {
      
      try {
        browser.screenShot();
        
        if (browser.getSize("accessibilityId", "spinnerText") != 0) {
          System.out.println("Animation displayed on Landing page");
          browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton' or @content-desc='welcomeName']");
        }
        else {
          System.out.println("Animation not displayed on Landing page");
          browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton' or @content-desc='welcomeName']");
        }
      } catch (NoSuchElementException e) {
        System.out.println("Element Not Found");
        e.printStackTrace();
      }
    }
}
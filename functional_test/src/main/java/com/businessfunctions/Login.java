package com.businessfunctions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.library.Common;

import static org.testng.Assert.assertTrue;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.Reporter;

public class Login {
	
	Common browser;
  
	ExtentReports extent;
    ExtentTest test;
	
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
	public boolean emptyLoginExpeceted() {
		
		try {
			boolean buttonValue = browser.isButtonEnabled("accessibilityId", "loginButtonText");
      
			if (buttonValue == false) {
				System.out.println("Login button is disabled - Please enter the credentials");
				Reporter.log ("Login button is disabled - Please enter the credentials");
				//test.log(Status.INFO, "Login button is disabled - Please enter the credentials");
				//test.pass("Login button is disabled - Please enter the credentials");
				Assert.assertTrue(true);
				return true;
			}
			else {
				System.out.println("Button is Enabled");
				Reporter.log ("Button is Enabled");
				//test.fail("Button is Enabled");
				assertTrue(false);
				return true;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			test.log(Status.INFO, ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
		return false;
	}
	
	// M - Method - Expected - Login with invalid user accessibilityId  
	public void invalidUserExpected() {
		
		try {
			browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
			browser.verifyText("accessibilityId", "Snackbar Message", "Invalid username or password.");
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	// M - Method - Expected - Login with invalid password
	public void invalidPasswordExpected() {
		
		try {
			browser.waitUntilElementPresent("//*[@content-desc='Snackbar Message']");
			String msg = "Invalid username or password. You will be locked out after 2 more failed login attempts.";
			browser.verifyText("accessibilityId", "Snackbar Message", msg);
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	     
	// S - Method - Retry 3 times  
	public void retryPassword(String username, String password) {
		
		try {
			//update the expected error message
		    String msg1 = "Invalid username or password. You will be locked out after 2 more failed login attempts.";
		    String msg2 = "Invalid username or password. You will be locked out after 1 more failed login attempts.";
		    String msg3 = "You have been locked out. To reactivate your account, please call customer care.";
		      
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
			
			browser.verifyText("accessibilityId", "logoutButton", "Log Out");
			
			browser.click("accessibilityId", "logoutButton");
			
			browser.verifyText("accessibilityId", "logoutModalFirstTextField", "Leaving already?");
			browser.verifyText("accessibilityId", "logoutModalSecondTextField", "Are you sure you want to log out?");
			browser.verifyText("accessibilityId", "logoutModalCancelButton", "Cancel");
			browser.verifyText("accessibilityId", "logoutModalLogoutButton", "Log out");        
			
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
			browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'][1])[31]");
			browser.click("xpath", "(//*[@class='android.view.View'][1])[31]");
			
			browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[1]", "Email us your Feedback");
			browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'][1])[12]");
			
			browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[2]", "WhatsApp us your Feedback");
			browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'][1])[13]");
			browser.screenShot();
			browser.click("xpath", "(//*[@class='android.view.View'][1])[12]");
			
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
					
					System.out.println("The user accepts Terms and Conditions");
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
			browser.waitUntilElementPresent("//*[@content-desc='scrollToEndButton'] | //*[@content-desc='welcomeName']");
			if(browser.getSize("accessibilityId", "welcomeName") != 0) {
				//Nothing to print
				browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
			}
			else if(browser.getSize("accessibilityId", "scrollToEndButton") != 0) {
				browser.click("accessibilityId", "scrollToEndButton");
				browser.waitUntilElementPresent("//*[@content-desc='acceptButton']");
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
	
	// A - Method - Login to NCB Mobile Application without RSA key
	public void RSAExpected() {
		
		try {
			String msg1 = "For your added security, a RSA token is required to use the NCB mobile app. Please get a token online to login.";
			browser.waitUntilElementPresent("//*[@content-desc= 'cancelModalSecondTextField']");
			String errormsg1 = browser.getText("accessibilityId", "cancelModalSecondTextField");
		  
			if (errormsg1.equals(msg1)) {
				System.out.println("Expected RSA message is correct");
			}
			else {
				System.out.println("Expected RSA message is wrong");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
	
	// A - method to click and validate cancel button on RSA modal
	public void rsaCancel() {
		
		try {
			browser.click("accessibilityId", "cancelModalCancelButton");
  	  	
			int usr =	browser.getCharCount("xpath", "//*[@content-desc='usernameInput']");
			if(usr == 0) {
				System.out.println("Username field is empty, after user clicked on Cancel button on RSA dialogue");
			}
			else {
				System.out.println("Username field is not empty, after user clicked on Cancel button on RSA dialogue");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
			
	//A - Methond - RIB navigation validation in RSA modal
	public void getToken() {
		
		try {
			browser.click("accessibilityId", "cancelModalLogoutButton");
			browser.verifyText("xpath", "//*[@id='url_bar']", browser.getText("xpath", "//*[contains(text(),'retail.ncbelink.com')]"));
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			e.printStackTrace();
		}
	}
}

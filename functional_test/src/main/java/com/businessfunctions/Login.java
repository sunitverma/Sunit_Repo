package com.businessfunctions;

import com.library.Common;

public class Login
{
	
    Common browser;
    //constructor with one argument.
    public Login(Common br)
    {
        browser=br;
    }
    
    // S - Method - Login to NCB Mobile Application
    public void loginToapp(String username,String password) throws InterruptedException
    {
    	browser.waitUntilElementPresent("//*[@content-desc='logoImage']");
        browser.click("xpath","//*[@content-desc='loginText']");
    	
    	browser.waitUntilElementPresent("//*[@content-desc='Username']");
        browser.sendKeys("xpath","//*[@content-desc='Username']",username);
        browser.keyboardKeys(66);
        
        browser.sendKeys("xpath","//*[@content-desc='Password']", password);
        browser.sleepThread(1000);
        browser.keyboardKeys(66);
        
        browser.click("xpath","//*[@content-desc='loginButtonText']");
                      				   
        browser.sleepThread(2000);
        browser.screenshot();
    }
    
    // M - Method - Expected - Verifying the Login button while login with Empty Credentials
    public void emptyLoginExpeceted() throws InterruptedException
    {
    	boolean buttonValue = browser.isButtonEnabled("xpath","//*[@content-desc='loginButtonText']");
    	
    	if(buttonValue == true)
	 	{
	 		System.out.println("Login button is disabled - Please enter the credentials");
	 	}
    	
	 	browser.sleepThread(2000);
	 	browser.screenshot();
    }
  
    // M - Method - Expected - Login with invalid user name  
    public void InvalidUserExpected()
    {
    	browser.verifyText("xpath", "//*[@class='android.widget.TextView'][4]", "Invalid username");
    	browser.screenshot();
    }
    
    // M - Method - Expected - Login with invalid password
    public void InvalidPasswordExpected()
    {
    	browser.verifyText("xpath", "//*[@class='android.widget.TextView'][5]", "Incorrect password");
    	browser.screenshot();
    }
    
    // S - Method - Retry 3 times  
    public void RetryPassword(String username, String password)
    {
    	//update the expected error message
    	String msg1="You will be locked out after 2 more password attempts. To unlock your account, you will need to call Customer Care.";
    	String msg2="You will be locked out after 1 more password attempt. To unlock your account, you will need to call Customer Care.";
    	String msg3="You have been locked out. To reactivate your account, please call Customer Care at 1-888-622-3478.";
    	
    	browser.waitUntilElementPresent("//*[@content-desc='Username']");
    	browser.sendKeys("xpath","//*[@content-desc='Username']",username);
    	browser.sleepThread(1000);
    	browser.keyboardKeys(66);
    	
    	browser.sendKeys("xpath","//*[@content-desc='Password']", password);
    	browser.sleepThread(1000);
    	browser.keyboardKeys(66);
    	
    	//First error message
    	browser.click("xpath","//*[@content-desc='loginButtonText']");
        browser.sleepThread(1000);
        String errormsg1= browser.getText("xpath","//*[@content-desc='alertMessage']");
        browser.screenshot();
        
        if (errormsg1 == msg1)
        {
        	System.out.println("Expected first message is correct");
         }
        else
        {
        	System.out.println("Expected first message is wrong");
        }
        
        //Second error message
    	browser.click("xpath","//*[@content-desc='loginButtonText']");
        browser.sleepThread(1000);
        String errormsg2= browser.getText("xpath","//*[@content-desc='alertMessage']");
        browser.screenshot();
        
        if (errormsg2==msg2)
        {
        	System.out.println("Expected second message is correct");
         }
        else
        {
        	System.out.println("Expected second message is wrong");
        }
        
        //Third error message
    	browser.click("xpath","//*[@content-desc='loginButtonText']");
        browser.sleepThread(1000);
        String errormsg3= browser.getText("xpath","//*[@content-desc='alertMessage']");
        browser.screenshot();
        
        if (errormsg3==msg3)
        {
        	System.out.println("Expected third message is correct");
         }
        else
        {
        	System.out.println("Expected third message is wrong");
        }
        
        String message1 = errormsg1;
        String message2= errormsg2;
        String message3= errormsg3;
        
        //if message is wrong the test case will fail
        browser.comparetexts(message1,msg1);
        browser.comparetexts(message2,msg2);
        browser.comparetexts(message3,msg3);
    }

    // S - Method - Expected - Login with invalid password
    public void LogOutButtonFunction()
    {
    	browser.verifyElementPresent("xpath", "//*[@content-desc='logoutButton']");
    	browser.verifyText("xpath", "//*[@content-desc='logoutButton']", "Log out");
    	
    	browser.click("xpath", "//*[@content-desc='logoutButton']");
    	
    	browser.verifyText("xpath", "//*[@content-desc='logoutModalFirstTextField']", "Leaving already?");
    	browser.verifyText("xpath", "//*[@content-desc='logoutModalSecondTextField']", "Are you sure you want to log out?");
    	
    	browser.verifyElementPresent("xpath", "//*[@content-desc='logoutModalCancelButton']");
    	browser.verifyElementPresent("xpath", "//*[@content-desc='logoutModalLogoutButton']");
    	browser.screenshot();
    	
    	browser.click("xpath", "//*[@content-desc='logoutModalCancelButton']");
    	browser.verifyText("xpath", "//*[@content-desc='welcomeName']", browser.getText("xpath", "//*[@text[starts-with(.,'Welcome')]]"));
    	browser.screenshot();
    	System.out.println("Cancel button working fine on Log out pop up");
    	
    	browser.click("xpath", "//*[@content-desc='logoutButton']");
    	
    	browser.click("xpath", "//*[@content-desc='logoutModalLogoutButton']");
    	browser.verifyText("xpath", "//*[@content-desc='loginTitle']", "Log in");
    	browser.screenshot();
    	System.out.println("Log out button working fine on Log out pop up");
    }
}
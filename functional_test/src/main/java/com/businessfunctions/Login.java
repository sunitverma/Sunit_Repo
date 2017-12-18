package com.businessfunctions;

import com.library.Common;

public class Login {
	
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
                      				   
        browser.sleepThread(10000);
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
    public void invalidUserExpected()
    {
    	browser.verifyText("xpath", "//*[@class='android.widget.TextView'][4]", "Invalid username");
    	browser.screenshot();
    }
    
    // M - Method - Expected - Login with invalid password
    public void invalidPasswordExpected()
    {
    	browser.verifyText("xpath", "//*[@class='android.widget.TextView'][5]", "Incorrect password");
    	browser.screenshot();
    }
    
    // S - Method - Retry 3 times  
    public void retryPassword(String username, String password)
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
    public void logOutButtonFunction()
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
    
    //K - Method - Terms and Conditions
    public void termsAndConditions() throws Exception
    {
    	browser.click("xpath", "(//*[@content-desc='scrollToEndButton'])");
    	browser.verifyElementPresent("xpath", "(//*[@content-desc='declineButton'])");
    	browser.click("xpath", "(//*[@content-desc='declineButton'])");
    	if(browser.verifyElementPresent("xpath", "(//*[@content-desc='Username'])"))
    	{
    		browser.waitUntilElementPresent("//*[@content-desc='Username']");
    	    browser.sendKeys("xpath","(//*[@content-desc='Username'])","DOUGLASCA");
    	    browser.keyboardKeys(66);
    	    browser.sendKeys("xpath","(//*[@content-desc='Password'])", "Password1##");
    	    browser.sleepThread(1000);
    	    browser.keyboardKeys(66);
    	    browser.click("xpath","(//*[@content-desc='loginButtonText'])");
    	    browser.sleepThread(10000);
    	    browser.click("xpath", "(//*[@content-desc='scrollToEndButton'])");
    	    browser.verifyElementPresent("xpath", "(//*[@content-desc='acceptButton'])");
    	    browser.click("xpath", "(//*[@content-desc='acceptButton'])");
    	}
    }
    
    // S - Method - Verifying the Feed back button functionality
    public void feedBackButtonFunction()
    {
    	browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[7]");
    	browser.click("xpath", "(//*[@class='android.view.View'])[7]");
    	
    	browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[1]", "Email us your Feedback");
    	browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[10]");
    	
    	browser.verifyText("xpath", "(//*[@class='android.widget.TextView'])[2]", "WhatsApp us your Feedback");
    	browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[13]");
    	browser.screenshot();
    	
    	browser.click("xpath", "(//*[@class='android.view.View'])[10]");
    	browser.verifyText("id", "from_account_name", "kratosbuild@gmail.com");
    	
    	browser.sendKeys("id", "body", "Feed back for NCB App");
    	browser.click("id", "send");
    	browser.screenshot();
    	
    	browser.verifyElementPresent("xpath", "(//*[@class='android.view.View'])[7]");
    	browser.click("xpath", "(//*[@class='android.view.View'])[7]");
    	
    	browser.click("xpath", "(//*[@class='android.view.View'])[13]");
    	browser.verifyElementPresent("id", "entry");
    	
    	browser.sendKeys("id", "entry", "Feed back for NCB App");
    	browser.click("id", "send");
    	browser.screenshot();
    }
}
package com.testscripts;

import com.businessfunctions.Homepage;
import com.businessfunctions.Login;
import java.net.MalformedURLException;
import org.testng.annotations.Test;

public class Testcases extends RootTest
    {
    static Login login=new Login(brow);
    static Homepage homepage = new Homepage();
    
    //===================================================
    // Created by Sunit Verma and Murali(Date: 13/11/2017)
    //===================================================
    
    //================Sprint 1: 2 test cases ============
    //================Sprint 1 Start=====================
    
    // Test case_01 with Empty Login Credentials username -- M
    @Test
    public static void LoginWithEmptyData() throws Exception
    {
    	System.out.println("Running Testcase 01 - Login with Empty Testdata");
        brow.setUp();
        login.loginToapp("","");
        login.emptyLoginExpeceted();
        brow.QuitObject();
        System.out.println("Testcase 01 - Successfully Completed");
    }
    
    // Testcase_02 with valid Credentials -- M
    @Test
    public static void LoginWithValidTestdata() throws Exception
    {
    	System.out.println("Running Testcase 02 - Login with Valid Credentials");
        brow.setUp();
        login.loginToapp("pollyanna","Password1##");
        brow.waitUntilElementPresent("//*[@class='android.widget.TextView'][1]");
        brow.verifyText("xpath","//*[@class='android.widget.TextView'][1]","Welcome, Janel");
        brow.screenshot();
        brow.QuitObject();
        System.out.println("Testcase 02 - Successfully Completed");
    }     
    
    // ======================= Sprint 1 End =============================

    // ======================= Sprint 2 Start =============================
    //Sprint 2: Verifying the home page after with different account users
    //Sprint 2: Verifying the Login with Invalid Credentials
    //Sprint 2: 5 test cases
          
    // Test case_03 Login with Invalid User name -- S
    @Test
    public static void LoginWithInvalidUsername() throws Exception
    {
    	System.out.println("Running Testcase 03 - Login with Invalid Username");
        brow.setUp();
        login.loginToapp("pollyannaa","Password1##");
        login.InvalidUserExpected();
        brow.QuitObject();
        System.out.println("Testcase 03 - Successfully Completed");
    }
    
    // Test case_04 Login with Invalid Password	-- S
    @Test
    public static void LoginWithInvalidPassword() throws Exception
    {
    	System.out.println("Running Testcase 04 - Login with Invalid Password");
    	brow.resetcount("pollyanna");
    	brow.setUp();
        login.loginToapp("pollyanna","Password1###");
        login.InvalidPasswordExpected();
        brow.QuitObject();
        System.out.println("Testcase 04 - Successfully Completed");
    }
    
    // Test case_05 Login with 3 Password retry -- S
    @Test
    public static void PasswordRetry() throws Exception
    {
    	System.out.println("Running Testcase 05 - Login with Invalid Password with retry 3 times");
    	brow.resetcount("TOMMYS");
    	brow.setUp();
        login.RetryPassword("TOMMYS","Password1###");
        brow.QuitObject();
        System.out.println("Testcase 05 - Successfully Completed");
    }
    
 // Test case_06 Verify Home page after Login user having all the 3 Accounts
    @Test
    public static void verifyHomepageAllAccounts() throws MalformedURLException, InterruptedException 
    {
    	System.out.println("Running Testcase - Verifying Login page - User having Saving, Chequing and Loan Accounts");
    	brow.setUp();
    	login.loginToapp("RHONEGAN", "Password1##");
    	homepage.verifyHomepageAllAccounts();
    	brow.QuitObject();
    }
    
    
    // Test case_07 Verify Home page after Login user having only one account
    @Test
    public static void verifyHomepageOneAccount() throws MalformedURLException, InterruptedException 
    {
    	System.out.println("Running Testcase - Verifying Login page - User having One Account");
    	brow.setUp();
    	login.loginToapp("DOUGLASCA", "Password1##");
    	homepage.verifyHomepageOneAccounts();
    	brow.QuitObject();
    }
    
    
    // Test case_08 Verify Home page after Login user having only No accounts
    @Test
    public static void verifyHomepageNoAccounts() throws MalformedURLException, InterruptedException 
    {
    	System.out.println("Running Testcase - Verifying Login page - User having No Accounts");
    	brow.setUp();
    	login.loginToapp("PINNIRAM", "Password1##");
    	homepage.verifyHomepageNoAccounts();
    	brow.QuitObject();
    }
    
  // ======================= Sprint 2 End =============================
 
}
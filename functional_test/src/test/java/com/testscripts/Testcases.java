package com.testscripts;

import com.businessfunctions.*;

import java.net.MalformedURLException;
import org.testng.annotations.Test;

public class Testcases extends RootTest
    {
    static Login login=new Login(brow);
    static Homepage homepage = new Homepage();
    static ViewAccounts viewaccountspage = new ViewAccounts();
    static ViewLoanDetails viewloandetails = new ViewLoanDetails();
    static ViewSavingsDetails viewSavingsDetailspage = new ViewSavingsDetails();
    
    //===================================================
    // Created by Sunit Verma and Murali(Date: 13/11/2017)
    //===================================================
    
    //================Sprint 1: 2 test cases ============================
    //================Sprint 1 Start=====================================
    
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
    
    //=============== Sprint 1 End ======================================
    //================Sprint 2: 6 test cases ============================
    //================Sprint 2 Start=====================================
          
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
    
    // Test case_06 Verify Home page after Login user having all the 3 Accounts -- K
    @Test
    public static void verifyHomepageAllAccounts() throws MalformedURLException, InterruptedException 
    {
    	System.out.println("Running Testcase 06 - Verifying Login page - User having Saving, Chequing and Loan Accounts");
    	brow.setUp();
    	login.loginToapp("RHONEGAN", "Password1##");
    	homepage.verifyHomepageAllAccounts();
    	brow.QuitObject();
    	System.out.println("Testcase 06 - Successfully Completed");
    }
        
    // Test case_07 Verify Home page after Login user having only one account -- K
    @Test
    public static void verifyHomepageOneAccount() throws MalformedURLException, InterruptedException 
    {
    	System.out.println("Running Testcase 07 - Verifying Login page - User having One Account");
    	brow.setUp();
    	login.loginToapp("DOUGLASCA", "Password1##");
    	homepage.verifyHomepageOneAccounts();
    	brow.QuitObject();
    	System.out.println("Testcase 07 - Successfully Completed");
    }
        
    // Test case_08 Verify Home page after Login user having only No accounts -- K
    @Test
    public static void verifyHomepageNoAccounts() throws MalformedURLException, InterruptedException 
    {
    	System.out.println("Running Testcase 08 - Verifying Login page - User having No Accounts");
    	brow.setUp();
    	login.loginToapp("PINNIRAM", "Password1##");
    	homepage.verifyHomepageNoAccounts();
    	brow.QuitObject();
    	System.out.println("Testcase 08 - Successfully Completed");
    }
    
    //=============== Sprint 2 End ======================================
    //================Sprint 3: 1 test case =============================
    //================Sprint 3 Start=====================================
    
    // Test case_09 Show top 10 transaction -- K
    @Test
    public static void ShowTenTransaction() throws Exception
    {
        System.out.println("Running Testcase 09 - Show Ten Transaction");
        brow.setUp();
        login.loginToapp("douglasca","Password1##");
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "//*[@content-desc='accountCard']");
        viewaccountspage.viewAccount();
        brow.QuitObject();
        System.out.println("Testcase 09 - Successfully Completed");
    }

    //=============== Sprint 3 End ======================================
    //================Sprint 4: 3 test cases ============================
    //================Sprint 4 Start=====================================

    // Test case_10 Show loan balance details -- S
    @Test
    public static void withLoanBalanceDetails() throws Exception
    {
        System.out.println("Running Testcase 10 - View Loan Balance Details of Customer");
        brow.setUp();
        login.loginToapp("YANDISUD","Password1##");
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@text='Loan'])[2]");
        viewloandetails.viewLoanDetails();
        brow.screenshot();
        brow.QuitObject();
        System.out.println("Testcase 10 - Successfully Completed");
    }

    // Test case_11 Show Zero balance loan details -- S    
    @Test
    public static void zeroLoanBalanceDetails() throws Exception
    {
        System.out.println("Running Testcase 11 - View Zero Loan Balance Details of Customer");
        brow.setUp();
        login.loginToapp("YANDISUD","Password1##");
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@text='Loan'])[1]");
        viewloandetails.zeroLoanBalance();
        brow.QuitObject();
        System.out.println("Testcase 11 - Successfully Completed");
    }

    // Test case_12 Verify Savings account balance breakdown -- K
    @Test
    public void savingsBalanceBreakdown() throws Exception
    {
                System.out.println("Running Testcase 12 - Show Savings account balance breakdown");
        brow.setUp();
        brow.waitUntilElementPresent("//*[@content-desc='loginText']");
        System.out.println("login");
        login.loginToapp("douglasca","Password1##");
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@content-desc='accountCard'])");
        viewSavingsDetailspage.savinsAccountBalanceBreakdown();
        brow.QuitObject();
        System.out.println("Testcase 12 - Successfully Completed");
    }

    //=============== Sprint 4 End ======================================
    //================Sprint 5:  test cases =============================
    //================Sprint 5 Start=====================================
    
}
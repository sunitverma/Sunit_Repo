package com.testscripts;

import com.businessfunctions.*;

import java.net.MalformedURLException;
import org.apache.maven.shared.utils.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Testcases extends RootTest {
    
    static Login login=new Login(brow);
    static Homepage homepage = new Homepage(brow);
    static ViewAccounts viewaccountspage = new ViewAccounts(brow);
    static ViewLoanDetails viewloandetails = new ViewLoanDetails(brow);
    static ViewSavingsDetails viewSavingsDetailspage = new ViewSavingsDetails(brow);
    
    //===================================================
    // Created by Sunit Verma and Murali(Date: 13/11/2017)
    //===================================================

    // Open and Close App
    
    @BeforeSuite
    public static void OpenApp() throws Exception
    {
        brow.setUp();
        FileUtils.deleteDirectory("target/surefire-reports/screenshots");
    }
    
    @AfterSuite
    public static void CloseApp() throws Exception
    {
        brow.quitObject();
    }  

    //================Sprint 1: 2 test cases ============================
    //================Sprint 1 Start=====================================

    // Test case_01 with Empty Login Credentials username -- M
    @Test
    public static void LoginWithEmptyData() throws Exception
    {
        System.out.println("Running Testcase 01 - Login with Empty Testdata");
        brow.reset();
        login.loginToapp("","");
        login.emptyLoginExpeceted();
        System.out.println("Testcase 01 - Successfully Completed");
    }
    
    // Testcase_02 with valid Credentials -- M
    @Test
    public static void LoginWithValidTestdata() throws Exception
    {
        System.out.println("Running Testcase 02 - Login with Valid Credentials");
        brow.reset();
        login.loginToapp("pollyanna","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@class='android.widget.TextView'][1]");
        brow.verifyText("xpath","//*[@class='android.widget.TextView'][1]","Good" + "Janel");
        brow.screenshot();
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
        brow.reset();
        login.loginToapp("pollyannaa","Password1##");
        login.invalidUserExpected();
        System.out.println("Testcase 03 - Successfully Completed");
    }
    
    // Test case_04 Login with Invalid Password -- S
    @Test
    public static void LoginWithInvalidPassword() throws Exception
    {
        System.out.println("Running Testcase 04 - Login with Invalid Password");
        brow.resetcount("pollyanna");
        brow.reset();
        login.loginToapp("pollyanna","Password1###");
        login.invalidPasswordExpected();
        System.out.println("Testcase 04 - Successfully Completed");
    }
    
    // Test case_05 Login with 3 Password retry -- S
    @Test
    public static void PasswordRetry() throws Exception
    {
        System.out.println("Running Testcase 05 - Login with Invalid Password with retry 3 times");
        brow.resetcount("TOMMYS");
        brow.reset();
        brow.click("xpath", "//*[@content-desc='loginText']");
        login.retryPassword("TOMMYS","Password1###");
        System.out.println("Testcase 05 - Successfully Completed");
    }
    
    // Test case_06 Verify Home page after Login user having all the 3 Accounts -- K
    @Test
    public static void verifyHomepageAllAccounts() throws MalformedURLException, InterruptedException 
    {
        System.out.println("Running Testcase 06 - Verifying Login page - User having Saving, Chequing and Loan Accounts");
        brow.reset();
        login.loginToapp("RHONEGAN", "Password1##");
        login.acceptTermAndConditions();
        homepage.verifyHomepageAllAccounts();
        System.out.println("Testcase 06 - Successfully Completed");
    }
        
    // Test case_07 Verify Home page after Login user having only one account -- K
    @Test
    public static void verifyHomepageOneAccount() throws MalformedURLException, InterruptedException 
    {
        System.out.println("Running Testcase 07 - Verifying Login page - User having One Account");
        brow.reset();
        login.loginToapp("DOUGLASCA", "Password1##");
        login.acceptTermAndConditions();
        homepage.verifyHomepageOneAccounts();
        System.out.println("Testcase 07 - Successfully Completed");
    }
        
    // Test case_08 Verify Home page after Login user having only No accounts -- K
    @Test
    public static void verifyHomepageNoAccounts() throws MalformedURLException, InterruptedException 
    {
        System.out.println("Running Testcase 08 - Verifying Login page - User having No Accounts");
        brow.reset();
        login.loginToapp("PINNIRAM", "Password1##");
        login.acceptTermAndConditions();
        homepage.verifyHomepageNoAccounts();
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
        brow.reset();
        login.loginToapp("douglasca","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "//*[@content-desc='accountCard']");
        viewaccountspage.viewAccount();
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
        brow.reset();
        login.loginToapp("YANDISUD","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@text='Loan'])[2]");
        viewloandetails.viewLoanDetails();
        brow.screenshot();
        System.out.println("Testcase 10 - Successfully Completed");
    }

    // Test case_11 Show Zero balance loan details -- S    
    @Test
    public static void zeroLoanBalanceDetails() throws Exception
    {
        System.out.println("Running Testcase 11 - View Zero Loan Balance Details of Customer");
        brow.reset();
        login.loginToapp("YANDISUD","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@text='Loan'])[1]");
        viewloandetails.zeroLoanBalance();
        System.out.println("Testcase 11 - Successfully Completed");
    }

    // Test case_12 Verify Savings account balance breakdown -- K
    @Test
    public void savingsBalanceBreakdown() throws Exception
    {
        System.out.println("Running Testcase 12 - Show Savings account balance breakdown");
        brow.reset();
        brow.waitUntilElementPresent("//*[@content-desc='loginText']");
        login.loginToapp("yandisud","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@content-desc='accountCard'][3])");
        viewSavingsDetailspage.savingAccountBalanceBreakdown();
        System.out.println("Testcase 12 - Successfully Completed");
    }

    //=============== Sprint 4 End ======================================
    //================Sprint 5: 3 test cases ============================
    //================Sprint 5 Start=====================================
    
    // Test case_13 Verify Log out button functionality -- S
    @Test
    public void LogOutButton() throws Exception
    {
        System.out.println("Running Testcase 13 - Verify Log out button functionality");
        brow.reset();
        brow.waitUntilElementPresent("//*[@content-desc='loginText']");
        login.loginToapp("YANDISUD","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        login.logOutButtonFunction();
        System.out.println("Testcase 13 - Successfully Completed");
    }

    // Test case_14 Verify Feed back button functionality -- S
    @Test
    public void FeedBackButton() throws Exception
    {
        System.out.println("Running Testcase 14 - Verify Feed Back button functionality");
        brow.reset();
        brow.waitUntilElementPresent("//*[@content-desc='loginText']");
        brow.click("xpath","//*[@content-desc='loginText']");
        login.feedBackButtonFunction();
        System.out.println("Testcase 14 - Successfully Completed");
    }    
    
    // Test case_15 Verify the Terms and Conditions page -- K
    @Test
    public void termsAndConditions() throws Exception
    {
        System.out.println("Running Testcase 15 - Verify Terms and Conditions functionality");
        brow.reset();
        login.loginToapp("DOUGLASCA","Password1##");
        login.termsAndConditionsExists("douglasca", "Password1##");
        System.out.println("Testcase 15 - Successfully Completed");
    }

    //=============== Sprint 5 End ======================================
    //================Sprint 6: 1 test case =============================
    //================Sprint 6 Start=====================================
    
    // Test case_16 Verify the Animations on different pages -- S
    @Test
    public void animation() throws Exception
    {
      System.out.println("Running Testcase 16 - Verify Anitmation on Loading and Account details page");
      brow.reset();
      login.loginToapp("POLLYANNA","Password1##");
      login.acceptTermAndConditions();
      homepage.animationOnLanding();
      brow.click("xpath", "//*[@content-desc='accountCard'][1]");
      viewaccountspage.animationOnAccountDetails();
      System.out.println("Testcase 16 - Successfully Completed");
    }
    
    //=============== Sprint 6 End ======================================
    //================Sprint 7:  test cases =============================
    //================Sprint 7 Start=====================================    
}
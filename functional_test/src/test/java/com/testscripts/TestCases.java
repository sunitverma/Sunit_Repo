package com.testscripts;

import com.businessfunctions.*;

import java.net.MalformedURLException;
import org.apache.maven.shared.utils.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCases extends RootTest {
	
	static Login login=new Login(brow);
    static HomePage homepage = new HomePage(brow);
    static ViewAccounts viewaccounts = new ViewAccounts(brow);
    static ViewLoanDetails viewloandetails = new ViewLoanDetails(brow);
    static ViewSavingsDetails viewsavingdetails = new ViewSavingsDetails(brow);
    static TransferPage transferpage=new TransferPage(brow);
    
    //===================================================
    // Created by Sunit Verma and Murali(Date: 13/11/2017)
    //===================================================
    
    // Open and Close App
    
    @BeforeSuite
    public static void openApp() throws Exception
    {
    	brow.setUp();
        FileUtils.deleteDirectory("target/surefire-reports/screenShots");
    }
    
    @AfterSuite
    public static void closeApp() throws Exception
    {
    	brow.quitObject();
    }
    
    //================Sprint 1: 2 test cases ============================
    //================Sprint 1 Start=====================================
    
    // Test case_01 with Empty Login Credentials username -- M
    @Test
    public static void loginWithEmptyData() throws Exception
    {
    	System.out.println("Running Testcase 01 - Login with Empty Testdata");
        brow.reset();
        login.loginToapp("","");
        login.emptyLoginExpeceted();
        System.out.println("Testcase 01 - Successfully Completed");
    }
    
    // Testcase_02 with valid Credentials -- M
    @Test
    public static void loginWithValidTestdata() throws Exception
    {
    	System.out.println("Running Testcase 02 - Login with Valid Credentials");
    	brow.reset();
    	login.loginToapp("pollyanna","Password1##");
    	login.acceptTermAndConditions();
    	brow.waitUntilElementPresent("//*[@class='android.widget.TextView'][1]");
        brow.verifyText("xpath", "//*[@class='android.widget.TextView'][1]","Log Out");
        brow.screenShot();
        System.out.println("Testcase 02 - Successfully Completed");
    }
    
    //=============== Sprint 1 End ======================================
    //================Sprint 2: 6 test cases ============================
    //================Sprint 2 Start=====================================
          
    // Test case_03 Login with Invalid User name -- S
    @Test
    public static void loginWithInvalidUsername() throws Exception
    {
    	System.out.println("Running Testcase 03 - Login with Invalid Username");
        brow.reset();
        login.loginToapp("pollyannaa","Password1##");
        login.invalidUserExpected();
        System.out.println("Testcase 03 - Successfully Completed");
    }
    
    // Test case_04 Login with Invalid Password	-- S
    @Test
    public static void loginWithInvalidPassword() throws Exception
    {
    	System.out.println("Running Testcase 04 - Login with Invalid Password");
        brow.resetCount("pollyanna");
        brow.reset();
        login.loginToapp("pollyanna","Password1###");
        login.invalidPasswordExpected();
        System.out.println("Testcase 04 - Successfully Completed");
    }
    
    // Test case_05 Login with 3 Password retry -- S
    @Test
    public static void passwordRetry() throws Exception
    {
    	System.out.println("Running Testcase 05 - Login with Invalid Password with retry 3 times");
        brow.resetCount("TOMMYS");
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
        login.loginToapp("YANDISUD", "Password1##");
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
        login.loginToapp("YANDISUD", "Password1##");
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
    public static void showTenTransaction() throws Exception
    {
        System.out.println("Running Testcase 09 - Show Ten Transaction");
        brow.reset();
        login.loginToapp("douglasca","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "//*[@content-desc='accountCard']");
        viewaccounts.viewAccount();
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
        brow.click("xpath", "//*[@class='android.view.View' and *[@text='Loan']][1]");
        viewloandetails.viewLoanDetails();
        brow.screenShot();
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
        login.loginToapp("douglasca","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        brow.click("xpath", "(//*[@content-desc='accountCard'])");
        viewsavingdetails.savingAccountBalanceBreakdown();
        System.out.println("Testcase 12 - Successfully Completed");
    }

    //=============== Sprint 4 End ======================================
    //================Sprint 5: 3 test cases =============================
    //================Sprint 5 Start=====================================
    
    // Test case_13 Verify Log out button functionality -- S
    @Test
    public void logOutButton() throws Exception
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
    public void feedBackButton() throws Exception
    {
    	System.out.println("Running Testcase 14 - Verify Feed Back button functionality");
    	brow.reset();
    	brow.waitUntilElementPresent("//*[@content-desc='loginText']");
    	brow.click("xpath", "//*[@content-desc='loginText']");
    	login.feedBackButtonFunction();
        System.out.println("Testcase 14 - Successfully Completed");
    }    
    
    // Test case_15 Verify the Terms and Conditions page -- K
    @Test
    public void termsAndConditions() throws Exception
    {
    	System.out.println("Running Testcase 15 - Verify Terms and Conditions functionality");
        brow.reset();
        login.loginToapp("douglasca","Password1##");
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
    	brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
    	brow.click("xpath", "//*[@content-desc='accountCard'][1]");
    	viewaccounts.animationOnAccountDetail();
    	System.out.println("Testcase 16 - Successfully Completed");
    }
    
    //=============== Sprint 6 End ======================================
    //================Sprint 7:  test cases =============================
    //================Sprint 7 Start=====================================
 
    // Test case_17 Verify the Navigation for Transfers on Card and Summary Page (FE) -- S
    @Test
    public void transfers() throws Exception
    {
    	System.out.println("Running Testcase 17 - Verify Transfers button functionality");
    	brow.reset();
    	login.loginToapp("pollyanna","Password1##");
    	login.acceptTermAndConditions();
    	brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
      	homepage.transferButtonOnLandingPage("Savings");
      	homepage.transferButtonOnLandingPage("Chequing");
      	viewaccounts.transferButtonOnAccountDetailsPage("Savings");
      	viewaccounts.transferButtonOnAccountDetailsPage("Chequing");
      	System.out.println("Testcase 17 - Successfully Completed");
    }
    
    //Test case_18 Verify Me to Me Transfer from Savings Account List Page -- K
    @Test
    public void meToMeTransferfromSavingsListPage() throws Exception
    {
    	System.out.println("Running Testcase 18 - Verify Me to Me Transfer from Savings Account List Page");
        brow.reset();
        login.loginToapp("YANDISUD","Password1##");
        //homepage.animationOnLanding();
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.meToMeTransfersAccountListPage("Savings","435335217");
        // Enter the Index of the currency in the currency dropdown
        transferpage.meToMeTransfer("2");
        System.out.println("Testcase 18 - Successfully Completed");
    }
    
    //Test case_19 Verify Me to Me Transfer from Savings Account summary page -- K
    @Test
    public void meToMeTransferfromSavingsSummaryPage() throws Exception
    {
    	System.out.println("Running Testcase 19 - Verify Me to Me Transfer from Savings Account Summary Page");
        brow.reset();
        login.loginToapp("YANDISUD","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.meToMeTransfersAccountSummaryPage("Savings","435335217");
        // Enter the Index of the currency in the currency dropdown
        transferpage.meToMeTransfer("2");
        System.out.println("Testcase 19 - Successfully Completed");
    }
    
    //Test case_20 Verify Me to Me Transfer from Chequings Account List page -- K
    @Test
    public void meToMeTransferfromChequingsListPage() throws Exception
    {
    	System.out.println("Running Testcase 20 - Verify Me to Me Transfer from Chequing Account List Page");
        brow.reset();
        login.loginToapp("POLLYANNA","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.meToMeTransfersAccountListPage("Chequing","061162232");
        // Enter the Index of the currency in the currency dropdown
        transferpage.meToMeTransfer("2");
        System.out.println("Testcase 20 - Successfully Completed");
    }
    
    //Test case_21 Verify Me to Me Transfer from Chequings Account summary page -- K
    @Test
    public void meToMeTransferfromChequingsSummaryPage() throws Exception
    {
    	System.out.println("Running Testcase 21 - Verify Me to Me Transfer from Chequing Account Summary Page");
        brow.reset();
        login.loginToapp("POLLYANNA","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.meToMeTransfersAccountSummaryPage("Chequing","061162232");
        // Enter the Index of the currency in the currency dropdown
        transferpage.meToMeTransfer("2");
        System.out.println("Testcase 21 - Successfully Completed");
    }
    
    //Test case_22 Verify Me to You Transfer --K
    @Test
    public void meToYouTransfer() throws Exception
    {
    	System.out.println("Running Testcase 22 - Verify Me to You Transfer");
        brow.reset();
        login.loginToapp("YANDISUD","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.meToMeTransfersAccountListPage("Savings","435335217");
        transferpage.meToYouTransferButton();
        // Enter the Index of the currency in the currency dropdown
        transferpage.meToYouTransfer("2","Andres");
        System.out.println("Testcase 22 - Successfully Completed");
    }

    //Test case_23 Verify Me to Me Transfer -- S
    @Test
    public void meToMetransfer() throws Exception
    {
        System.out.println("Running Testcase 23 - Verify Me to Me Transfer, Transfer Edit and Confirmation page");
        brow.reset();
        login.loginToapp("stanigar","Password1##");
        login.acceptTermAndConditions();
        transferpage.transferFlowMeToMe("Savings", "1");
        brow.sleepThread(1000);
        System.out.println("Testcase 23 - Successfully Completed");
    }
    
    //Test case_24 Add Retail beneficiary -- S
    @Test
    public void addBeneficiaryRetail() throws Exception
    {
        System.out.println("Running Testcase 24 - Add Retail beneficiary to account");
        brow.reset();
        login.loginToapp("stonepj","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.addBeneficiaryToAccount("Savings");
        //transferpage.blankbeneficiaryPageRetail();
        transferpage.cancelBeneficiaryRetail();
        transferpage.beneficiaryRetail();
        System.out.println("Testcase 24 - Successfully Completed");
    }
    
    //Test case_25 Add Corporate beneficiary -- S
    @Test
    public void addBeneficiaryCorporate() throws Exception
    {
        System.out.println("Running Testcase 25 - Add Corporate beneficiary to account");
        brow.reset();
        login.loginToapp("stonepj","Password1##");
        login.acceptTermAndConditions();
        brow.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        transferpage.addBeneficiaryToAccount("Chequing");
        //transferpage.blankbeneficiaryPageCorporate();
        transferpage.cancelBeneficiaryCorporate();
        transferpage.beneficiaryCorporate();
        System.out.println("Testcase 25 - Successfully Completed");
    }

    //Test case_26 Verify Me to You Transfer -- S
    @Test
    public void reviewMeToYoutransfer() throws Exception
    {
        System.out.println("Running Testcase 24 - Verify Me to You Transfer, Transfer Edit and Confirmation page");
        brow.reset();
        login.loginToapp("darkelor","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        transferpage.transferFlowMeToYou("Savings", "1");
        brow.sleepThread(1000);
        System.out.println("Testcase 26 - Successfully Completed");
    }
    
    //Test case_27 Add Beneficiary Submit after entering OTP
    @Test
    public void addBeneficiarySubmit() throws Exception
    {
        System.out.println("Running Testcase 25 - Verify Adding beneficiary by entering PIN + OTP and click on submit");
        brow.reset();
        login.loginToapp("yandisud","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        //add beneficiary
        transferpage.transferUsingOTPSubmit();
        brow.sleepThread(1000);
        System.out.println("Testcase 27 - Successfully Completed");
    }
    
    //Test case_28 Add Beneficiary Cancel after entering OTP
    @Test
    public void addBeneficiaryCancel() throws Exception
    {
        System.out.println("Running Testcase 28 - Verify Adding beneficiary by entering PIN + OTP and click on submit");
        brow.reset();
        login.loginToapp("yandisud","Password1##");
        homepage.animationOnLanding();
        login.acceptTermAndConditions();
        //add beneficiary
        transferpage.transferUsingOTPCancel();
        brow.sleepThread(1000);
        System.out.println("Testcase 28 - Successfully Completed");
    }
}
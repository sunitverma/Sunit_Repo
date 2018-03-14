package com.testscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.businessfunctions.*;
import com.utils.ExtentReports.ExtentManager;
import org.apache.maven.shared.utils.io.FileUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCases extends RootTest {
  
    static Login login = new Login(brow);
    static HomePage homepage = new HomePage(brow);
    static ViewAccounts viewaccounts = new ViewAccounts(brow);
    static ViewLoanDetails viewloandetails = new ViewLoanDetails(brow);
    static ViewSavingsDetails viewsavingdetails = new ViewSavingsDetails(brow);
    static TransferPage transferpage = new TransferPage(brow);
    static BillPayment billpayment = new BillPayment(brow);
    
    ExtentReports extent;
    ExtentTest test;
    
    //===================================================
    // Created by Sunit Verma and Murali(Date: 13/11/2017)
    //===================================================
    
    // Open and Close App
    
    @BeforeSuite
    public void openApp() throws Exception {
      extent = ExtentManager.GetExtent();
      brow.setUp();
      FileUtils.deleteDirectory("target/surefire-reports/screenShots");
    }
    
    @AfterSuite
    public void closeApp() {
      extent.flush();
      brow.quitObject();
    }
    
    @AfterMethod
    public void getResult(ITestResult result) {
      if(result.getStatus() == ITestResult.FAILURE) {
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
        test.fail(result.getThrowable());
      }
      else if(result.getStatus() == ITestResult.SUCCESS) {
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
      }
      else {
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
        test.skip(result.getThrowable());
      }
    }
       
    //================Sprint 1: 2 test cases ============================
    //================Sprint 1 Start=====================================
    
    // Test case_01 with Empty Login Credentials username -- M
    @Test
    public void loginWithEmptyData() {
      test = extent.createTest("Test case_01", "Empty Login Credentials username");
      //System.out.println("Running Testcase 01 - Login with Empty Testdata");
      brow.reset();
      login.loginToApp("", "");
      login.emptyLoginExpeceted();
      brow.screenShot();
      test.pass("Testcase 01 - Successfully Completed");
      //System.out.println("Testcase 01 - Successfully Completed");
    }
        
    // Testcase_02 with valid Credentials -- M
    @Test
    public void loginWithValidTestdata() {
      test = extent.createTest("Test case_02", "Login with Valid Credentials");
      //System.out.println("Running Testcase 02 - Login with Valid Credentials");
      brow.reset();
      login.loginToApp("pollyanna", "Password1##");
      login.acceptTermAndConditions();
      brow.verifyText("accessibilityId", "welcomeName", brow.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
      brow.screenShot();
      //System.out.println("Testcase 02 - Successfully Completed");
    }
        
    //=============== Sprint 1 End ======================================
    //================Sprint 2: 6 test cases ============================
    //================Sprint 2 Start=====================================
          
    // Test case_03 Login with Invalid User accessibilityId -- S
    @Test
    public void loginWithInvalidUsername() {
      System.out.println("Running Testcase 03 - Login with Invalid Username");
      brow.reset();
      login.loginToApp("pollyannaa", "Password1##");
      login.invalidUserExpected();
      brow.screenShot();
      System.out.println("Testcase 03 - Successfully Completed");
    }
       
    // Test case_04 Login with Invalid Password	-- S
    @Test
    public void loginWithInvalidPassword() {
      System.out.println("Running Testcase 04 - Login with Invalid Password");
      brow.reset();
      login.loginToApp("pollyanna", "Password1###");
      login.invalidPasswordExpected();
      brow.screenShot();
      System.out.println("Testcase 04 - Successfully Completed");
    }
    
    // Test case_05 Login with 3 Password retry -- S
    @Test
    public void passwordRetry() throws Exception {
      System.out.println("Running Testcase 05 - Login with Invalid Password with retry 3 times");
      brow.resetUser("stanigar");
      brow.reset();
      brow.click("accessibilityId", "login");
      login.retryPassword("stanigar", "Password1###");
      brow.resetUser("stanigar");
      System.out.println("Testcase 05 - Successfully Completed");
    }
        
    // Test case_06 Verify Home page after Login user having all the 3 Accounts -- K
    @Test
    public void verifyHomepageAllAccounts() {
      System.out.println("Running Testcase 06 - Verifying Login page - User having Saving, Chequing and Loan Accounts");
      brow.reset();
      login.loginToApp("darkelor", "Password1##");
      login.acceptTermAndConditions();
      homepage.homePageWithAccounts();
      brow.screenShot();
      System.out.println("Testcase 06 - Successfully Completed");
    }
        
    // Test case_07 Verify Home page after Login user having only one account -- K
    @Test
    public void verifyHomepageOneAccount() {
      System.out.println("Running Testcase 07 - Verifying Login page - User having One Account");
      brow.reset();
      login.loginToApp("walnutkitten", "Password1##");
      login.acceptTermAndConditions();
      homepage.homePageWithAccounts();
      brow.screenShot();
      System.out.println("Testcase 07 - Successfully Completed");
      Reporter.log("Testcase 07 - Successfully Completed");
    }
            
    // Test case_08 Verify Home page after Login user having only No accounts -- K
    @Test
    public void verifyHomepageNoAccounts() {
      System.out.println("Running Testcase 08 - Verifying Login page - User having No Accounts");
      brow.reset();
      login.loginToApp("pinniram", "Password1##");
      homepage.homePageWithoutAccounts();
      brow.screenShot();
      System.out.println("Testcase 08 - Successfully Completed");
    }
        
    //=============== Sprint 2 End ======================================
    //================Sprint 3: 1 test case =============================
    //================Sprint 3 Start=====================================
    
    // Test case_09 Show top 50 transactions -- K
    // change from 10 to 50 transactions as update to show 50 -- 05/02/2018 
    @Test
    public void showFiftyTransaction() {
      System.out.println("Running Testcase 09 - Show Fifty Transactions");
      brow.reset();
      login.loginToApp("douglasca", "Password1##");
      login.acceptTermAndConditions();
      viewaccounts.viewAccount();
      brow.screenShot();
      System.out.println("Testcase 09 - Successfully Completed");
    }

    //=============== Sprint 3 End ======================================
    //================Sprint 4: 3 test cases ============================
    //================Sprint 4 Start=====================================

    // Test case_10 Show loan balance details -- S
    @Test
    public void withLoanBalanceDetails() {
      System.out.println("Running Testcase 10 - View Loan Balance Details of Customer");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      viewloandetails.viewLoanDetail();
      brow.screenShot();
      System.out.println("Testcase 10 - Successfully Completed");
    }
    
    // Test case_11 Show Zero balance loan details -- S
    @Test
    public void zeroLoanBalanceDetails() {
      System.out.println("Running Testcase 11 - View Zero Loan Balance Details of Customer");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      viewloandetails.zeroLoanBalance("431108844");
      brow.screenShot();
      System.out.println("Testcase 11 - Successfully Completed");
    }

    // Test case_12 Show Saving balance details -- K
    @Test
    public void withSavingsBalanceDetails() {
      System.out.println("Running Testcase 12 - Show Savings account balance breakdown");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      viewsavingdetails.viewSavingsDetail();
      brow.screenShot();
      System.out.println("Testcase 12 - Successfully Completed");
    }
   
    //=============== Sprint 4 End ======================================
    //================Sprint 5: 3 test cases =============================
    //================Sprint 5 Start=====================================
    
    // Test case_13 Verify Log out button functionality -- S
    @Test
    public void logOutButton() {
      System.out.println("Running Testcase 13 - Verify Log out button functionality");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      login.logOutButtonFunction();
      System.out.println("Testcase 13 - Successfully Completed");
    }

    // Test case_14 Verify Feed back button functionality -- S
    @Test
    public void feedBackButton() {
      System.out.println("Running Testcase 14 - Verify Feed Back button functionality");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      login.feedBackButtonFunction();
      System.out.println("Testcase 14 - Successfully Completed");
    }
    
    // Test case_15 Verify the Terms and Conditions page -- K
    @Test
    public void termsAndConditions() {
      System.out.println("Running Testcase 15 - Verify Terms and Conditions functionality");
      brow.reset();
      login.loginToApp("douglasca", "Password1##");
      login.termsAndConditionsExists("douglasca", "Password1##");
      System.out.println("Testcase 15 - Successfully Completed"); 
    }

    //=============== Sprint 5 End ======================================
    //================Sprint 6: 1 test case =============================
    //================Sprint 6 Start=====================================
    
    // Test case_16 Verify the Animations on different pages -- S
    @Test
    public void animation() {
      System.out.println("Running Testcase 16 - Verify Anitmation on Loading and Account details page");
      brow.reset();
      login.loginToApp("pollyanna", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      viewaccounts.animationOnAccountDetail();
      System.out.println("Testcase 16 - Successfully Completed");
    }
    
    //=============== Sprint 6 End ======================================
    //================Sprint 7:  test cases =============================
    //================Sprint 7 Start=====================================
 
    // Test case_17 Verify the Navigation for Transfers on Card and Summary Page (FE) -- S
    @Test
    public void transfers() {
      System.out.println("Running Testcase 17 - Verify Transfers button functionality");
      brow.reset();
      login.loginToApp("michele", "Password1##");
      login.acceptTermAndConditions();
      homepage.quickActionsButtonNotForLoan();
      homepage.transferButtonOnLandingPage("SAVINGS");
      viewaccounts.transferButtonOnAccountDetailsPage("SAVINGS");
      homepage.transferButtonOnLandingPage("CHEQUING");
      viewaccounts.transferButtonOnAccountDetailsPage("CHEQUING");
      System.out.println("Testcase 17 - Successsfully Completed");
    }
    
    //Test case_18 Verify Me to Me Transfer from Savings Account List Page -- K
    @Test
    public void meToMeTransferFromSavingsListPage() {
      System.out.println("Running Testcase 18 - Verify Me to Me Transfer from Savings Account List Page");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountListPage("SAVINGS", "10");
      //transferpage.meToMeTransfer("2");
      System.out.println("Testcase 18 - Successfully Completed");
    }
       
    //Test case_19 Verify Me to Me Transfer from Savings Account summary page -- K
    @Test
    public void meToMeTransferFromSavingsSummaryPage() {
      System.out.println("Running Testcase 19 - Verify Me to Me Transfer from Savings Account Summary Page");
      brow.reset();
      login.loginToApp("YANDISUD", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountSummaryPage("Savings", "435335217");
      //transferpage.meToMeTransfer("2");
      System.out.println("Testcase 19 - Successfully Completed");
    }
     
    //Test case_20 Verify Me to Me Transfer from Chequings Account List page -- K
    @Test
    public void meToMeTransferFromChequingsListPage() {
      System.out.println("Running Testcase 20 - Verify Me to Me Transfer from Chequing Account List Page");
      brow.reset();
      login.loginToApp("POLLYANNA", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountListPage("Chequing", "061162232");
      //transferpage.meToMeTransfer("2");
      System.out.println("Testcase 20 - Successfully Completed");
    }

    //Test case_21 Verify Me to Me Transfer from Chequings Account summary page -- K
    @Test
    public void meToMeTransferFromChequingsSummaryPage() {
      System.out.println("Running Testcase 21 - Verify Me to Me Transfer from Chequing Account Summary Page");
      brow.reset();
      login.loginToApp("POLLYANNA", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountSummaryPage("Chequing", "061162232");
      //transferpage.meToMeTransfer("2");
      System.out.println("Testcase 21 - Successfully Completed");
    }
    
    //Test case_22 Verify Me to You Transfer -- K
    @Test
    public void meToYouTransfer() {
      System.out.println("Running Testcase 22 - Verify Me to You Transfer");
      brow.reset();
      login.loginToApp("YANDISUD", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountListPage("Savings", "435335217");
      transferpage.meToYouTransferButton();
      transferpage.meToYouTransfer("2", "Andres");    // Enter the Index of the currency in the currency dropdown
      System.out.println("Testcase 22 - Successfully Completed");
    }
    
    //Test case_23 Verify Me to Me Transfer -- S
    @Test
    public void meToMeTransfer() {
      System.out.println("Running Testcase 23 - Verify Me to Me Transfer, Transfer Edit and Confirmation page");
      brow.reset();
      login.loginToApp("stanigar", "Password1##");
      login.acceptTermAndConditions();
      transferpage.transferFlowMeToMe("Savings", "1");
      brow.sleepThread(1000);
      System.out.println("Testcase 23 - Successfully Completed");
    }

    //Test case_24 Add Personal beneficiary -- S
    @Test
    public void addBeneficiaryPersonal() {
      System.out.println("Running Testcase 24 - Add Personal beneficiary to account");
      brow.reset();
      login.loginToApp("darkelor", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryToAccount("Savings");
      transferpage.blankBeneficiaryPageBusiness();
      transferpage.cancelBeneficiaryPersonal();
      //transferpage.beneficiaryPersonal();
      System.out.println("Testcase 24 - Successfully Completed");
    }
    
    //Test case_25 Add Business beneficiary -- S
    @Test
    public void addBeneficiaryBusiness() {
      System.out.println("Running Testcase 25 - Add Corporate beneficiary to account");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryToAccount("Chequing");
      transferpage.blankBeneficiaryPageBusiness();
      transferpage.cancelBeneficiaryBusiness();
      //transferpage.beneficiaryBusiness();
      System.out.println("Testcase 25 - Successfully Completed");
    }

    //Test case_26 Verify Me to You Transfer -- S
    @Test
    public void reviewMeToYouTransfer() {
      System.out.println("Running Testcase 26 - Verify Me to You Transfer, Transfer Edit and Confirmation page");
      brow.reset();
      login.loginToApp("darkelor", "Password1##");
      login.acceptTermAndConditions();
      transferpage.transferFlowMeToYou("Savings", "1");
      brow.sleepThread(1000);
      System.out.println("Testcase 26 - Successfully Completed");
    }
    
    //Test case_27 Add Beneficiary Submit after entering OTP -- K
    @Test
    public void addBeneficiarySubmit() {
      System.out.println("Running Testcase 27 - Verify Adding beneficiary by entering PIN + OTP and click on submit");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryUsingOtpCancel();    //add beneficiary
      brow.sleepThread(1000);
      brow.screenShot();
      System.out.println("Testcase 27 - Successfully Completed");
    }
    
    //Test case_28 Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount -- S
    @Test
    public void errorHandlingMeToMeBalanceAndExceed() {
      System.out.println("Running Testcase 28 - Verify the available balance and exceed the total amount: Me to Me");
      brow.reset();
      login.loginToApp("stanigar", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      transferpage.balanceAndExceedMeToMe("304523913", "356425529");
      System.out.println("Testcase 28 - Successfully Completed");
    }
    
    //Test case_29 Error handling for Me to Me Transfers: Verify the transfer freeze on dormant/restricted accounts -- S
    @Test
    public void errorHandlingMeToMeRestriction() {
      System.out.println("Running Testcase 29 - Verify the transfer freeze on dormant/restricted accounts: Me to Me");
      brow.reset();
      login.loginToApp("tommys", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      transferpage.restictionsMeToMe("756201891", "351182717");
      System.out.println("Testcase 29 - Successfully Completed");
    }
    
    //Test case_30 Error handling for Me to Me Transfers: Verify the oops message -- S
    @Test
    public void errorHandlingMeToMeOopsMessage() {
      System.out.println("Running Testcase 30 - Verify the oops message: Me to Me");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      transferpage.oopsMeToMe("435335217", "614078707");
      System.out.println("Testcase 30 - Successfully Completed");
    }
        
    //Test case_31 Error handling for Me to You Transfers: Verify the available balance and exceed the total amount -- S
    @Test
    public void errorHandlingMeToYouBalanceAndExceed() {
      System.out.println("Running Testcase 31 - Verify the available balance and exceed the total amount: Me to You");
      brow.reset();
      login.loginToApp("stanigar", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      transferpage.balanceAndExceedMeToYou("304523913", "4450");
      System.out.println("Testcase 31 - Successfully Completed");
    }
    
    //Test case_32 Error handling for Me to You Transfers: Verify the transfer freeze on dormant/restricted accounts -- S
    @Test
    public void errorHandlingMeToYouRestriction() {
      System.out.println("Running Testcase 32 - Verify the transfer freeze on dormant/restricted accounts: Me to You");
      brow.reset();
      login.loginToApp("tommys", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      transferpage.restictionsMeToMe("756201891", "4450");
      System.out.println("Testcase 32 - Successfully Completed");
    }
     
    //Test case_33 Error handling for Me to You Transfers: Verify the oops message -- S
    @Test
    public void errorHandlingMeToYouOopsMessage() {
      System.out.println("Running Testcase 33 - Verify the oops message: Me to You");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      transferpage.oopsMeToMe("435335217", "4450");
      System.out.println("Testcase 33 - Successfully Completed");
    }

    //Test case_34 Add Beneficiary Cancel after entering OTP -- K
    @Test
    public void addBeneficiaryUsingOtpCancel() {
      System.out.println("Running Testcase 34 - Verify Adding beneficiary by entering PIN + OTP and click on cancel");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryUsingOtpCancel();    //add beneficiary
      brow.sleepThread(1000);
      brow.screenShot();
      System.out.println("Testcase 34 - Successfully Completed");
    }
    
    //Test case_35 Verifying the Limits while me to me transfer -- K
    @Test
    public void verifyTransferLimitsMetoMe() {
      System.out.println("Running Testcase 35 - Verify the limits while transferring the amount");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.verifyLimitMeToMe("Savings", "354162075","80000");
      brow.screenShot();
      System.out.println("Testcase 35 - Successfully Completed");
    }
 
    //Test case_36 Verifying the Limits while me to you transfer -- K
    @Test
    public void verifyTransferLimitsMetoYou() {
      System.out.println("Running Testcase 36 - Verify the limits while transferring the amount");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.verifyLimitMeToYou("Savings", "354162075", "800");
      brow.screenShot();
      System.out.println("Testcase 36 - Successfully Completed");
    }

    // Test case_37 Verify the Navigation for Bill Payment on Landing and Summary Page -- S
    @Test
    public void Payments() {
      test = extent.createTest("Test case_37", "Verify the Navigation for Bill Payment on Landing and Summary Page");
      brow.reset();
      login.loginToApp("michele", "Password1##");
      login.acceptTermAndConditions();
      homepage.quickActionsButtonNotForLoan();
      homepage.billPaymentButtonOnLandingPage("SAVINGS");
      viewaccounts.billPaymentButtonOnAccountDetailsPage("SAVINGS");
      homepage.billPaymentButtonOnLandingPage("CHEQUING");
      viewaccounts.billPaymentButtonOnAccountDetailsPage("CHEQUING");
      test.pass("Testcase 37 - Successsfully Completed");
    }
    
    // Test case_38 Verify Bill Payment from Saving Account List page -- S
    @Test
    public void billPaymentFromSavingsListPage() {
      test = extent.createTest("Test case_38", "Verify Bill Payment from Saving Account List page");
      brow.reset();
      login.loginToApp("michele", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentListPage("SAVINGS", "1");
      test.pass("Testcase 38 - Successfully Completed");
    }
    
    // Test case_39 Verify Bill Payment from Saving Account Summary page -- S
    @Test
    public void billPaymentFromSavingsSummaryPage() {
      test = extent.createTest("Test case_39", "Verify Bill Payment from Saving Account Summary page");
      brow.reset();
      login.loginToApp("michele", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentSummaryPage("SAVINGS", "1");
      test.pass("Testcase 39 - Successfully Completed");
    }
    
    // Test case_40 Verify Bill Payment from Chequings Account List page -- S
    @Test
    public void billPaymentFromChequingsListPage() {
      test = extent.createTest("Test case_40", "Verify Bill Payment from Chequings Account List page");
      brow.reset();
      login.loginToApp("michele", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentListPage("CHEQUING", "1");
      test.pass("Testcase 40 - Successfully Completed");
    }
    
    // Test case_41 Verify Bill Payment from Chequings Account Summary page -- S
    @Test
    public void billPaymentFromChequingsSummaryPage() {
      test = extent.createTest("Test case_41", "Verify Bill Payment from Chequings Account Summary page");
      brow.reset();
      login.loginToApp("michele", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentSummaryPage("CHEQUING", "1");
      test.pass("Testcase 41 - Successfully Completed");
    }
    
    // Test case_42 Verify no Bill Payee -- S
    @Test
    public void noBillPayee() {
      test = extent.createTest("Test case_42", "Verify Bill Payment from Chequings Account Summary page");
      brow.reset();
      login.loginToApp("stanigar", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentSummaryPage("SAVINGS", "1");
      test.pass("Testcase 42 - Successfully Completed");
    }
}
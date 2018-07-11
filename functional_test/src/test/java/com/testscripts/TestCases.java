package com.testscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.businessfunctions.*;
import com.utils.ExtentReports.ExtentManager;
import com.utils.ExtentReports.Retry;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCases extends RootTest {
  
    static Login login = new Login(brow);
    static HomePage homepage = new HomePage(brow);
    static ViewAccounts viewaccounts = new ViewAccounts(brow);
    static ViewLoanDetails viewloandetails  = new ViewLoanDetails(brow);
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
    public void closeApp() throws Exception {
    	//extent.flush();
    	brow.quitObject();
    	//Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
    }
    
    @AfterTest
    public void testend() throws Exception {
        extent.flush();
    }
    
    /*@AfterMethod
    public void getResult(ITestResult result) throws Exception {
      	if(result.getStatus() == ITestResult.FAILURE) {
      		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
      		test.addScreencastFromPath("screenshot.png");
      		test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("//target//surefire-reports//screenshots//screenshot.png").build());
      		test.fail(result.getThrowable());
      	
//      		Exception exception = new NullPointerException();
//      		test.fail(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
      	}
      	else if(result.getStatus() == ITestResult.SUCCESS) {
      		test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
      	}
      	else {
      		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
      		test.skip(result.getThrowable());
      	}
    }*/
       
    //================Sprint 1: 2 test cases ============================
    //================Sprint 1 Start=====================================
    
    // Test case_01 with Empty Login Credentials username -- M
    @Test (retryAnalyzer=Retry.class)
    public void loginWithEmptyData() {
    	test = extent.createTest("Testcase 01", "Login with Empty Testdata");
    	test.assignAuthor("Murali");
    	System.out.println("Running Testcase 01 - Login with Empty Testdata");
    	login.loginToApp("", "");
    	login.emptyLoginExpeceted();
    	brow.screenShot();
    	System.out.println("Testcase 01 - Successfully Completed");
    	Reporter.log ("Testcase 01 - Successfully Completed");
    }
        
    // Testcase_02 with valid Credentials -- M
    @Test //(retryAnalyzer=Retry.class)
    public void loginWithValidTestData() {
    	try {
    	//test = extent.createTest("Testcase 02", "Login with Valid Credentials");
    	System.out.println("Running Testcase 02 - Login with Valid Credentials");
    	brow.reset();
    	login.loginToApp("yandisud", "Password1##");
    	login.acceptTermAndConditions();
    	brow.verifyText("accessibilityId", "welcomeName", brow.getText("xpath", "//*[@text[starts-with(.,'Good')]]"));
    	brow.screenShot();
    	System.out.println("Testcase 02 - Successfully Completed");
    	} catch (NoSuchElementException e) {
			System.out.println("Element Not Found");
			//test.log(Status.INFO, ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
    }
        
    //=============== Sprint 1 End ======================================
    //================Sprint 2: 6 test cases ============================
    //================Sprint 2 Start=====================================
          
    // Test case_03 Login with Invalid User name -- S
    @Test (retryAnalyzer=Retry.class)
    public void loginWithInvalidUsername() {
    	System.out.println("Running Testcase 03 - Login with Invalid Username");
    	brow.reset();
    	login.loginToApp("yandisudd", "Password1##");
    	login.invalidUserExpected();
    	brow.screenShot();
    	System.out.println("Testcase 03 - Successfully Completed");
    }
       
    // Test case_04 Login with Invalid Password	-- S
    @Test (retryAnalyzer=Retry.class)
    public void loginWithInvalidPassword() {
    	System.out.println("Running Testcase 04 - Login with Invalid Password");
    	brow.reset();
    	login.loginToApp("yandisud", "Password1###");
    	login.invalidPasswordExpected();
    	brow.screenShot();
    	System.out.println("Testcase 04 - Successfully Completed");
    }
    
    // Test case_05 Retry 3 times login with Invalid Password -- S
    @Test
    public void passwordRetry() throws Exception {
    	System.out.println("Running Testcase 05 - Retry 3 times login with Invalid Password");
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
      System.out.println("Running Testcase 06 - Verifying Home page - User having Saving, Chequing and Loan Accounts");
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
      System.out.println("Running Testcase 07 - Verifying Home page - User having one Account");
      brow.reset();
      login.loginToApp("darkelor", "Password1##");
      login.acceptTermAndConditions();
      homepage.homePageWithAccounts();
      brow.screenShot();
      System.out.println("Testcase 07 - Successfully Completed");
    }
            
    // Test case_08 Verify Home page after Login user having no accounts -- K
    @Test
    public void verifyHomepageNoAccounts() {
      System.out.println("Running Testcase 08 - Verifying Home page - User having No Accounts");
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
      login.loginToApp("yandisud", "Password1##");
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
      System.out.println("Running Testcase 12 - View Savings Balance Details of Customer");
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
    
    // Test case_15 Verify the Terms and Conditions functionality -- K
    @Test
    public void termsAndConditions() {
      System.out.println("Running Testcase 15 - Verify the Terms and Conditions functionality");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.termsAndConditionsExists("yandisud", "Password1##");
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
      login.loginToApp("yandisud", "Password1##");
      login.animationOnLandingPage();
      login.acceptTermAndConditions();
      viewaccounts.animationOnAccountDetail();
      System.out.println("Testcase 16 - Successfully Completed");
    }
    
    //=============== Sprint 6 End ======================================
    //================Sprint 7:  test cases =============================
    //================Sprint 7 Start=====================================
 
    // Test case_17 Verify Transfers button is available on different pages -- S
    @Test
    public void transfers() {
      System.out.println("Running Testcase 17 - Verify Transfers button is available on different pages");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      homepage.quickActionsButtonNotForLoan();
      homepage.transferButtonOnListPage("SAVINGS");
      viewaccounts.transferButtonOnAccountDetailsPage("SAVINGS");
      homepage.transferButtonOnListPage("CHEQUING");
      viewaccounts.transferButtonOnAccountDetailsPage("CHEQUING");
      System.out.println("Testcase 17 - Successsfully Completed");
    }
    
    //Test case_18 Text and Elements validation for Me to Me Transfer flow for Savings Account List Page -- K
    @Test
    public void meToMeTransferFromSavingsListPage() {
      System.out.println("Running Testcase 18 - Validate text and elements for Me to Me Transfer from Savings Account List Page");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountListPage("SAVINGS", "10");
      System.out.println("Testcase 18 - Successfully Completed");
    }
       
    //Test case_19 Text and Elements validation for Me to Me Transfer flow for Savings Account Summary Page -- K
    @Test
    public void meToMeTransferFromSavingsSummaryPage() {
      System.out.println("Running Testcase 19 - Validate text and elements for Me to Me Transfer from Savings Account Summary Page");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountSummaryPage("SAVINGS", "10");
      System.out.println("Testcase 19 - Successfully Completed");
    }
     
    //Test case_20 Text and Elements validation for Me to Me Transfer flow for Chequing Account List Page -- K
    @Test
    public void meToMeTransferFromChequingsListPage() {
      System.out.println("Running Testcase 20 - Validate text and elements for Me to Me Transfer from Chequing Account List Page");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountListPage("CHEQUING", "10");
      System.out.println("Testcase 20 - Successfully Completed");
    }

    //Test case_21 Text and Elements validation for Me to Me Transfer flow for Chequing Account Summary Page -- K
    @Test
    public void meToMeTransferFromChequingsSummaryPage() {
      System.out.println("Running Testcase 21 - Validate text and elements for Me to Me Transfer from Chequing Account Summary Page");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToMeTransfersAccountSummaryPage("CHEQUING", "10");
      System.out.println("Testcase 21 - Successfully Completed");
    }

    //Test case_22 Verify end to end flow for Me to Me Transfer -- S
    @Test
    public void endToEndMeToMeTransfer() {
      System.out.println("Running Testcase 22 - Verify end to end flow for Me to Me Transfer");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.transferFlowMeToMe("SAVINGS", "200");
      System.out.println("Testcase 22 - Successfully Completed");
    }
    
    //Test case_23 Text and Elements validation for Me to You Transfer flow for Savings Account List Page -- K
    @Test
    public void meToYouTransfer() {
      System.out.println("Running Testcase 23 - Validate text and elements for Me to You Transfer from Savings Account List Page");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.meToYouTransfers("SAVINGS", "30");
      System.out.println("Testcase 23 - Successfully Completed");
    }
    
    //Test case_24 Verify end to end flow for Me to You Transfer -- S
    @Test
    public void endToEndMeToYouTransfer() {
      System.out.println("Running Testcase 24 - Verify end to end flow for Me to You Transfer");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.transferFlowMeToYou("SAVINGS", "40000");
      System.out.println("Testcase 24 - Successfully Completed");
    }

    //Test case_25 Add Personal beneficiary to account -- S
    @Test
    public void addBeneficiaryPersonal() {
      System.out.println("Running Testcase 25 - Add Personal beneficiary to account");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryToAccount("SAVINGS");
      transferpage.blankBeneficiaryPagePersonal("Jody");
      transferpage.cancelBeneficiaryPersonal();
      //transferpage.beneficiaryPersonal("");
      System.out.println("Testcase 25 - Successfully Completed");
    }
    
    //Test case_26 Add Business beneficiary to account -- S
    @Test
    public void addBeneficiaryBusiness() {
      System.out.println("Running Testcase 26 - Add Corporate beneficiary to account");
      brow.reset();
      login.loginToApp("stonepj", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryToAccount("CHEQUING");
      transferpage.blankBeneficiaryPageBusiness();
      transferpage.cancelBeneficiaryBusiness();
      transferpage.beneficiaryBusiness("");
      System.out.println("Testcase 26 - Successfully Completed");
    }

    //Test case_27 Add Beneficiary Submit after entering OTP -- K
    @Test
    public void addBeneficiaryWithOTP() {
      System.out.println("Running Testcase 27 - Verify Adding beneficiary by entering PIN + OTP and click on submit");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryUsingOtpCancel();    //add beneficiary
      brow.sleepThread(1000);
      brow.screenShot();
      System.out.println("Testcase 27 - Successfully Completed");
    }
    
    //Test case_28 Error handling for Me to Me Transfers: Verify the error message if try to transfer money more then account balance -- S
    @Test
    public void errorHandlingMeToMeBalanceAndExceed() {
      System.out.println("Running Testcase 28 - Verify the error message if try to transfer money more then account balance: Me to Me");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.balanceAndExceedMeToMe("SAVINGS");
//      Assert.assertTrue(brow.verifyText("accessibilityId", "Snackbar Message", "1Amount exceeds available balance. Please reduce the amount."));
//      test.log(Status.INFO, "This step shows usage of log(status, details)");
      System.out.println("Testcase 28 - Successfully Completed");
    }
    
    //Test case_29 Error handling for Me to Me Transfers: Verify the error message for transfer money to dormant/restricted accounts -- S
    @Test
    public void errorHandlingMeToMeRestriction() {
      System.out.println("Running Testcase 29 - Verify the error message for transfer money to dormant/restricted accounts: Me to Me");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.restictionsMeToMe("756201891", "351182717");
      System.out.println("Testcase 29 - Successfully Completed");
    }
    
    //Test case_30 Error handling for Me to Me Transfers: Verify the oops error message -- S
    @Test
    public void errorHandlingMeToMeOopsMessage() {
      System.out.println("Running Testcase 30 - Verify the oops error message: Me to Me");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.oopsMeToMe("435335217", "614078707");
      System.out.println("Testcase 30 - Successfully Completed");
    }
        
    //Test case_31 Error handling for Me to You Transfers: Verify the error message if try to transfer money more then account balance -- S
    @Test
    public void errorHandlingMeToYouBalanceAndExceed() {
      System.out.println("Running Testcase 31 - Verify the error message if try to transfer money more then account balance: Me to You");
      brow.reset();
      login.loginToApp("stanigar", "Password1##");
      login.acceptTermAndConditions();
      transferpage.balanceAndExceedMeToYou("304523913", "4450");
      System.out.println("Testcase 31 - Successfully Completed");
    }
    
    //Test case_32 Error handling for Me to Me Transfers: Verify the error message for transfer money to dormant/restricted accounts -- S
    @Test
    public void errorHandlingMeToYouRestriction() {
      System.out.println("Running Testcase 32 - Verify the error message for transfer money to dormant/restricted accounts: Me to You");
      brow.reset();
      login.loginToApp("tommys", "Password1##");
      login.acceptTermAndConditions();
      transferpage.restictionsMeToYou("756201891", "4450");
      System.out.println("Testcase 32 - Successfully Completed");
    }
     
    //Test case_33 Error handling for Me to Me Transfers: Verify the oops error message -- S
    @Test
    public void errorHandlingMeToYouOopsMessage() {
      System.out.println("Running Testcase 33 - Verify the oops error message: Me to You");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.oopsMeToYou("435335217", "4450");
      System.out.println("Testcase 33 - Successfully Completed");
    }

    //Test case_34 Add Beneficiary Cancel after entering OTP -- K
    @Test
    public void addBeneficiaryUsingOtpCancel() {
      System.out.println("Running Testcase 34 - Verify Adding beneficiary by entering PIN + OTP and click on cancel");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.addBeneficiaryUsingOtpCancel();
      brow.screenShot();
      System.out.println("Testcase 34 - Successfully Completed");
    }
    
    //Test case_35 Verifying the Limits while me to me transfer -- K
    @Test
    public void verifyTransferLimitsMetoMe() {
      System.out.println("Running Testcase 35 - Verify the limits while transferring the amount for Me to Me");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.verifyLimitMeToMe("SAVINGS", "5000000");
      brow.screenShot();
      System.out.println("Testcase 35 - Successfully Completed");
    }
 
    //Test case_36 Verifying the Limits while me to you transfer -- K
    @Test
    public void verifyTransferLimitsMetoYou() {
      System.out.println("Running Testcase 36 - Verify the limits while transferring the amount for Me to You");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      transferpage.verifyLimitMeToYou("SAVINGS", "5000000");
      brow.screenShot();
      System.out.println("Testcase 36 - Successfully Completed");
    }

    // Test case_37 Verify the Navigation for Bill Payment on Landing and Summary Page -- S
    @Test
    public void Payments() {
      System.out.println("Testcase 37 - Verify the Navigation for Bill Payment on Landing and Summary Page");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      homepage.quickActionsButtonNotForLoan();
      homepage.billPaymentButtonOnLandingPage("SAVINGS");
      viewaccounts.billPaymentButtonOnAccountDetailsPage("SAVINGS");
      homepage.billPaymentButtonOnLandingPage("CHEQUING");
      viewaccounts.billPaymentButtonOnAccountDetailsPage("CHEQUING");
      System.out.println("Testcase 37 - Successsfully Completed");
    }
    
    // Test case_38 Verify Bill Payment from Saving Account List page and Back to Accounts button functionality -- S
    @Test
    public void billPaymentFromSavingsListPage() {
      System.out.println("Testcase 38 - Verify Bill Payment from Saving Account List page");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentListPage("SAVINGS", "1");
      billpayment.backToAccountsButton();
      System.out.println("Testcase 38 - Successsfully Completed");
    }
    
    // Test case_39 Verify Bill Payment from Saving Account Summary page and Pay Another Bill button functionality -- S
    @Test
    public void billPaymentFromSavingsSummaryPage() {
      System.out.println("Testcase 39 - Verify Bill Payment from Saving Account Summary page");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentSummaryPage("SAVINGS", "1");
      billpayment.payAnotherBillButton();
      System.out.println("Testcase 39 - Successsfully Completed");
    }
    
    // Test case_40 Verify Bill Payment from Chequings Account List page -- S
    @Test
    public void billPaymentFromChequingsListPage() {
      System.out.println("Testcase 40 - Verify Bill Payment from Chequings Account List page");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentListPage("CHEQUING", "1");
      System.out.println("Testcase 40 - Successsfully Completed");
    }
    
    // Test case_41 Verify Bill Payment from Chequings Account Summary page -- S
    @Test
    public void billPaymentFromChequingsSummaryPage() {
      System.out.println("Testcase 41 - Verify Bill Payment from Chequings Account Summary page");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentSummaryPage("CHEQUING", "1");
      System.out.println("Testcase 41 - Successsfully Completed");
    }
    
    // Test case_42 Verify no Bill Payee -- S
    @Test
    public void noBillPayee() {
      System.out.println("Testcase 42 - Verify no bill payee message");
      brow.reset();
      login.loginToApp("stanigar", "Password1##");
      login.acceptTermAndConditions();
      billpayment.billPaymentSummaryPage("SAVINGS", "1");
      System.out.println("Testcase 42 - Successsfully Completed");
    }
    
    // Test case_43 Verify Cancel button functionality on all pages -- S
    @Test
    public void backButtons() {
      System.out.println("Testcase 43 - Verify Cancel button functionality on all pages");
      brow.reset();
      login.loginToApp("yandisud", "Password1##");
      login.acceptTermAndConditions();
      homepage.backButton("SAVINGS", "billpay"); //billpay for 'PAY A BILL' and moneytrf for 'MAKE A TRANSFER'
      System.out.println("Testcase 43 - Successsfully Completed");
    }

    // Test case_44 Verify userWithoutRSA if user clicks cancel button -- A
    @Test
    public static void loginWithoutRSA()
    {
    	System.out.println("Running Testcase 44 - Login without RSA");
        brow.reset();
        login.loginToApp("Rhonegan", "Password1##");
        login.RSAExpected();
        brow.screenShot();
        login.rsaCancel();
        brow.screenShot();
        System.out.println("Testcase 44 - Successfully Completed");
    } 
    
    // Test case_45 Login without RSA key get button -- A      
    @Test
    public static void loginWithoutRSAgettoken()
    {
        System.out.println("Running Testcase 44 - Token required link verification");
        brow.reset();
        login.loginToApp("Rhonegan", "Password1##");
        login.RSAExpected();
        login.getToken();
        brow.screenShot();
        System.out.println("Testcase 45 for token - Successfully Completed");
    }
      
    // Test case_46 Verify Bill Payment from Saving Account Summary page Err Msg -- A
    @Test
    public void billPaymentFromSavingsSummaryPageErmsg() {
    	System.out.println("Running Testcase 47 - Verify Bill Payment from Saving Account Error msg on Summary page");
    	brow.reset();
    	login.loginToApp("darkelor", "Password1##");
    	login.acceptTermAndConditions();
    	billpayment.billPaymentSummaryPageErr("SAVINGS", "200001");
    	System.out.println("Testcase 46 - Successfully Completed");
    }
    
    // Test case_47 Verify Bill Payment from Chequing Account Summary page Err Msg -- A
    @Test
    public void billPaymentFromChequingSummaryPageErmsg() {
    	System.out.println("Running Testcase 47 - Verify Bill Payment from Chequing Account Error msg on Summary page");
    	brow.reset();
    	login.loginToApp("darkelor", "Password1##");
    	login.acceptTermAndConditions();
    	billpayment.billPaymentSummaryPageErr("CHEQUING", "200001");
    	System.out.println("Testcase 47 - Successfully Completed");
    }
    
    //Test case_48 Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount -- A
    @Test
    public void dailyLmtExceedmsgMetoMetransferChequing() {
    	System.out.println("Running Testcase 48 - Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount");
    	brow.reset();
    	login.loginToApp("darkelor", "Password1##");
    	login.acceptTermAndConditions();
    	transferpage.meTomeErr("CHEQUING", "200001");
    	System.out.println("Testcase 48 - Successfully Completed");
    }
   
    //Test case_49 Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount -- A
    @Test
    public void dailyLmtExceedmsgMetoMetransferSavings() {
    	System.out.println("Running Testcase 49 - Verify the available balance and exceed the total amount: Me to Me");
    	brow.reset();
    	login.loginToApp("darkelor", "Password1##");
    	login.acceptTermAndConditions();
    	transferpage.meTomeErr("SAVINGS", "200001");
    	System.out.println("Testcase 49 - Successfully Completed");
    }
    
    //Test case_50 Error handling for Me to you Transfers: Verify the available balance and exceed the total amount -- A
    @Test
    public void meToyouErrSavings() {
    	System.out.println("Running Testcase 50 - Verify exceed limit msg error for saving : Me to You");
    	brow.reset();
    	login.loginToApp("darkelor", "Password1##");
    	login.acceptTermAndConditions();
    	transferpage.meToyouErr("SAVINGS", "500001");
    	System.out.println("Testcase 50 - Successfully Completed");
    }
    
  //Test case_51 Error handling for Me to you Transfers: Verify the available balance and exceed the total amount -- A
    @Test
    public void meToyouErrChequing() {
    	System.out.println("Running Testcase 51 - Verify exceed limit msg error for Chequing : Me to You");
    	brow.reset();
    	login.loginToApp("darkelor", "Password1##");
    	login.acceptTermAndConditions();
    	transferpage.meToyouErr("CHEQUING", "500001");
    	System.out.println("Testcase 51 - Successfully Completed");
    }
    
    // test case_52 Credit Card details on home page -- A 10-5-2018
 	@Test
 	public void creditCardonHome() {
 		System.out.println("Running Testcase 52 - Verify Credit Card details on home page");
 		brow.reset();
 		login.loginToApp("darkelor", "Password1##");
 		login.acceptTermAndConditions();
 		homepage.homePageCardInfo();
 		System.out.println("Testcase 52 - Credit card details test Successfully Completed");
 	}
}
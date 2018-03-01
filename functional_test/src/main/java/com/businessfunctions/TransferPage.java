package com.businessfunctions;

import com.library.Common;

public class TransferPage {

    Common browser;
    
    //constructor with one argument.
    public TransferPage(Common br)
    {
        browser=br;
    }
    
    // K - Method - to verify me to me transfers
    public void meToMeTransfer(String currency)
    {
        if(browser.verifyElementPresent("xpath", "//*[@class='android.widget.ImageView' and ./parent::*[@content-desc='Next Button Disabled']]"))
        {
            browser.click("xpath", "//*[@content-desc='accountCard' and ./parent::*[./parent::*[./preceding-sibling::*[@class='android.view.ViewGroup']]] and ./*[@text='Savings']]");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./parent::*[@content-desc='Next Button Enabled']]");
        }   
        //Thread.sleep(5000);
        
        if(browser.verifyElementPresent("accessibilityId", "Next Button Disabled"))
        {
            browser.getText("accessibilityId", "MoneyInput");
            browser.sendKeys("accessibilityId", "MoneyInput", "1");
            browser.click("accessibilityId", "TransferHeader");
            
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
            browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
            browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])[" + currency + "])");
            
            //browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
            browser.click("accessibilityId", "Next Button Enabled");
            browser.verifyElementPresent("accessibilityId", "sourceAccountTitle");
            browser.click("xpath", "//*[@text='Cancel']");
            
            if(browser.getSize("accessibilityId", "cancelModalLogoutButton")!=0)
            {
                browser.click("accessibilityId", "cancelModalLogoutButton"); 
                browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
                browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
            }
        }
    }
     
    // K - Method - to verify me to me transfers on accounts from Account List page
    public void meToMeTransfersAccountListPage(String accounttype, String accountnumber)
    {
        if(browser.getSize("xpath", "//*[@content-desc='accountCard' and *[@text='" + accounttype + "']]")!=0)
        {
            String currency = browser.getText("xpath", "//*[@text='JMD' and  ./preceding-sibling::*[@text='" + accountnumber + "'] and ./preceding-sibling::*[@text='Savings']]");
            System.out.println("currency is " + currency);
            browser.click("xpath", "//*[@text='TRANSFER' and ./parent::*[./preceding-sibling::*[@text='" + accountnumber + "']]]");
        }
    }
     
    // K - Method - to verify me to me transfers on accounts from Account Summary page
    public void meToMeTransfersAccountSummaryPage(String accounttype, String accountnumber)
    {
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='"+ accounttype +"']]")!=0)
        {
            browser.click("xpath", "//*[@content-desc='accountCard' and ./*[@text='" + accountnumber + "'] and ./*[@text='" + accounttype + "']]");
            browser.click("xpath", "//*[@content-desc='Transfer Button' and ./preceding-sibling::*[@class='android.widget.ScrollView']]");
        }
    }
     
    // K - Method - to verify me to you transfers button
    public void meToYouTransferButton()
    {
        //Thread.sleep(5000);
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    }
    
    // K - Method - to verify me to you transfers
    public void meToYouTransfer(String currency, String nickname)
    {
        if(browser.getSize("xpath","//*[@content-desc='Next Button Disabled' and @width>0]")!=0)
        {
            browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
            //browser.click("xpath", "//*[@content-desc='accountCard' and ./*[@text='"+ Nickname +"']]");
            browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
            browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
            browser.verifyElementPresent("accessibilityId", "TransferHeader");
            browser.getText("accessibilityId", "MoneyInput");
            browser.sendKeys("accessibilityId", "MoneyInput", "1");
            browser.click("accessibilityId", "TransferHeader");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
            browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
            browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])[" + currency + "])");
            //browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
            browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
            browser.verifyElementPresent("accessibilityId", "sourceAccountTitle");
            browser.click("xpath", "//*[@text='Cancel']");
            if(browser.getSize("accessibilityId", "cancelModalLogoutButton")!=0)
            {
                browser.click("accessibilityId", "cancelModalLogoutButton"); 
                browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
            }
        }
    }
    
    // S - Method - to verify transfer flow page
    public void transferFlowMeToMe(String accountfromtype, String amount)
    {    
        String accountFromNo = browser.getText("xpath", "(//*[@content-desc='accountCard']//*[@content-desc='accountCardType'][@text='" + accountfromtype + "']/../*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')])[1]");
        String accountToNo = browser.getText("xpath", "(//*[@content-desc='accountCard']//*[@content-desc='accountCardType'][@text='Savings' and 'Chequing']/..//*[@content-desc='accountCardNumber'][not(@text='356425529')])[1]");//"+ accountfromno +"
        String accountToType = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountFromNo + "']]//*[@content-desc='accountCardType']");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountToNo +"']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String trunck = browser.subString(accountToNo, 5, 9);
        browser.click("xpath", "//*[@text='  ••" + trunck + "']");
        System.out.println("account no are correct for account from and account to");
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
        browser.sendKeys("accessibilityId", "MoneyInput", amount);
        browser.keyboardKey(66);
        String currency = browser.getText("xpath" , "(//*[@class='android.widget.EditText'])[4]");
        browser.click("accessibilityId", "Next Button Enabled");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("accessibilityId", "sourceAccountTitle", "Send from");
        browser.verifyText("accessibilityId", "destinationAccountTitle", "to");
        browser.verifyText("accessibilityId", "transferAmountTitle", "for the amount of");
        browser.verifyElementPresent("accessibilityId", "submitTransferButton");
        browser.verifyText("accessibilityId", "sourceAccountTypeAndNumber", accountfromtype + "  ••" + browser.subString(accountFromNo, 5, 9) );
        browser.verifyText("accessibilityId", "destinationAccountTypeAndNumber", accountToType + "  ••" + browser.subString(accountToNo, 5, 9) );
        browser.verifyText("accessibilityId", "transferCurrency", currency);
        browser.verifyText("accessibilityId", "transferAmount", " $" + amount + "0.00");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
        browser.verifyElementPresent("accessibilityId", "transferSuccessImage");
        browser.verifyText("accessibilityId", "transferSuccessText", "Success! \nYour transfer is complete.");
        browser.click("accessibilityId", "backToAccountsButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("xpath", "//*[@text='" + accountFromNo + "']", accountToNo);
    }
    
    // K - Method - to verify Me to You transfer flow page
    public void transferFlowMeToYou(String accountfromtype, String amount)
    {
        String accountFromNo = browser.getText("xpath", "(//*[@content-desc='accountCard' and *[@text='" + accountfromtype + "']]//*[@content-desc='accountCardNumber'])[1]");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountFromNo + "']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
        browser.sendKeys("accessibilityId", "MoneyInput", amount);
        browser.keyboardKey(66);
       
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("accessibilityId", "sourceAccountTitle", "Send from");
        browser.verifyText("accessibilityId", "destinationAccountTitle", "to");
        browser.verifyText("accessibilityId", "transferAmountTitle", "for the amount of");
        browser.verifyElementPresent("accessibilityId", "submitTransferButton");
        browser.verifyText("accessibilityId", "sourceAccountTypeAndNumber", accountfromtype + "  ••" + browser.subString(accountFromNo, 5, 9) );
        int size=browser.getCharCount("xpath", "//*[@content-desc='destinationAccountTypeAndNumber']");
        if (size == 17)
        {
            System.out.println("Nickname length is 17, as per requirement");
            
        }
        else
        {
            System.out.println("Nickname length is not equals to 17 characters");
            System.out.println("The length is " + (size-3));
        }
        
        browser.verifyText("accessibilityId", "transferAmount", "$" + amount + "0.00");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
        browser.verifyElementPresent("accessibilityId", "transferSuccessImage");
        browser.verifyText("accessibilityId", "transferSuccessText", "Success! \nYour transfer is complete.");
        browser.click("accessibilityId", "backToAccountsButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("xpath", "//*[@text='" + accountFromNo + "']", accountFromNo);
    }
    
    // K - Method - Transfer Me to You after entering OTP and PIN and click on submit
    public void addBeneficiaryUsingOtpSubmit()
    {
        browser.click("xpath", "save button"); //click on save button
        browser.verifyElementPresent("xpath", "Cancel button"); //Verify cancel button
        browser.verifyElementPresent("xpath", "Submit button"); //Verify submit button
        browser.sendKeys("xpath", "PIN + TOKEN", "PIN + TOKEN"); //Enter pin and token
        browser.click("xpath", "submit button"); //click on submit button
        browser.verifyElementPresent("xpath", "Element in list of beneficiaries page");
        browser.verifyElementPresent("xpath", "toast message"); //verify the toast message
    }
    
    // K - Method - Transfer Me to You after entering OTP and PIN and click on submit
    public void addBeneficiaryUsingOtpCancel()
    {
        browser.click("xpath", "save button"); //click on save button
        browser.verifyElementPresent("xpath", "Cancel button"); //Verify cancel button
        browser.verifyElementPresent("xpath", "Submit button"); //Verify submit button
        browser.sendKeys("xpath", "PIN + TOKEN", "PIN + TOKEN"); //Enter pin and token
        browser.click("xpath", "cancel button"); //click on submit button
        browser.verifyElementPresent("xpath", "Element in list of beneficiaries page"); 
    }
    
    // S - Method - to verify add beneficiary functionality
    public void addBeneficiaryToAccount(String accounttype)
    {
        //browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.click("xpath", "(//*[@content-desc='accountCard' and *[@text='" + accounttype + "']]//*[@content-desc='Transfer Button'])[1]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.waitUntilElementPresent("//*[@content-desc='addButton']");
    }
      
    // S - Method - to verify text fields validation on blank page for save button and close button functionality
    public void blankBeneficiaryPagePersonal()
    {
        browser.click("accessibilityId", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("accessibilityId", "Retail");
        
        browser.click("accessibilityId", "firstname");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "lastname");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "accountnumber");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "nickname");
        browser.keyboardKey(66);
        
        browser.scrollDown("Down", 200, 400);
        
        //Verify error message for blank fields
        browser.verifyText("accessibilityId", "firstname-error", "Please enter a first name");
        browser.verifyText("accessibilityId", "lastname-error", "Please enter a last name");
        browser.verifyText("accessibilityId", "accountnumber-error", "Please enter a 9 digit account number");
        browser.verifyText("accessibilityId", "nickname-error", "Please enter a nickname");
        browser.screenShot();

        //Verify error message for duplicate nick accessibilityId
        browser.sendKeys("accessibilityId", "nickname","Allen");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "accountnumber");
        browser.keyboardKey(66);
        
        browser.verifyText("accessibilityId", "nickname-error", "The nickname is already in use please try another");
        browser.screenShot();
        
        //Verify the field length for text fields
        browser.sendKeys("accessibilityId", "firstname", "RFirst Name Pneumonoultramicroscopicsilicovolcanoconiosis");
        browser.keyboardKey(66);
        
        int fNameChar = browser.getCharCount("accessibilityId", "firstname");
        if (fNameChar == 40)
        {
            System.out.println("First Name is 40 character");
        }
        else
        {
            System.out.println("First Name is " + fNameChar +" character");
        }
        
        browser.sendKeys("accessibilityId", "lastname", "RLast Name Pneumonoultramicroscopicsilicovolcanoconiosis");
        browser.keyboardKey(66);
        
        int lNameChar = browser.getCharCount("accessibilityId", "lastname");
        if (lNameChar == 40)
        {
            System.out.println("Last Name is 40 character");
        }
        else
        {
            System.out.println("Last Name is " + lNameChar + " character");
        }
        
        browser.sendKeys("accessibilityId", "accountnumber", "1234567890");
        browser.keyboardKey(66);
        
        int aNumberCount = browser.getCharCount("accessibilityId", "accountnumber");
        if (aNumberCount == 9)
        {
            System.out.println("Account Number is 9 digit");
        }
        else
        {
            System.out.println("Account Number is " + aNumberCount + " digit");
        }
          
        browser.sendKeys("accessibilityId", "nickname","RNick Name Pneumonoultramicroscopicsilicovolcanoconiosis");        
        browser.keyboardKey(66);
        
        int nNameChar = browser.getCharCount("accessibilityId", "nickname");
        if (nNameChar == 17)
        {
            System.out.println("Nick Name is 17 character");
        }
        else
        {
            System.out.println("Nick Name is " + nNameChar +" character");
        }
        
        browser.screenShot();
  
        browser.click("accessibilityId", "closeButton");
        browser.waitUntilElementPresent("//*[@content-desc='logoutModalFirstTextField']");
        browser.click("accessibilityId", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("blank beneficiary page for Personal tested");
    }
    
    // S - Method - to verify elements available on Personal add beneficiary page/pop-up message and cancel button functionality
    public void cancelBeneficiaryPersonal()
    {
        browser.click("accessibilityId", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("accessibilityId", "Retail");
        
        browser.verifyElementPresent("accessibilityId", "firstname");
        browser.verifyElementPresent("accessibilityId", "lastname");
        browser.verifyElementPresent("accessibilityId", "accountnumber");
        browser.verifyElementPresent("accessibilityId", "nickname");
        
        browser.scrollDown("Down", 200, 500);
        
        browser.verifyElementPresent("accessibilityId", "cancelBeneficiary");
        browser.verifyElementPresent("accessibilityId", "saveBeneficiary");
        
        browser.screenShot();
        
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.verifyText("accessibilityId", "logoutModalFirstTextField", "Cancel adding new beneficiary");
        browser.verifyText("accessibilityId", "logoutModalSecondTextField", "Are you sure you want to cancel adding a new beneficiary?");
        
        browser.verifyElementPresent("accessibilityId", "logoutModalCancelButton");
        browser.verifyElementPresent("accessibilityId", "logoutModalLogoutButton");
        
        browser.screenShot();
        
        browser.click("accessibilityId", "logoutModalCancelButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.click("accessibilityId", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("cancel button on beneficiary page for Personal tested");
    }
    
    // S - Method - to verify add beneficiary Personal page
    public void beneficiaryPersonal(String nickname)
    {
        browser.click("accessibilityId", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("accessibilityId", "Retail");
        
        String fName = "Kartosfirstname";
        String lName = "Kartoslastname";
        String aNumber = "304510080";
                       
        browser.sendKeys("accessibilityId", "firstname", fName);
        browser.keyboardKey(66);
        
        browser.sendKeys ("accessibilityId", "lastname", lName);
        browser.keyboardKey(66);
        
        browser.sendKeys ("accessibilityId", "accountnumber", aNumber);
        browser.keyboardKey(66);
        
        browser.sendKeys ("accessibilityId", "nickname", nickname);
        browser.keyboardKey(66);
        
        String aNumberChange = browser.subString(aNumber, 5, 9); 
        String aNumberChange1 = "  ••" + aNumberChange;
        
        browser.scrollDown("Down", 200, 400);
        
        browser.verifyElementPresent("xpath", "//*[@content-desc='saveBeneficiary']");
        browser.click("xpath", "//*[@content-desc='saveBeneficiary']");
        //browser.sleepThread(1000);
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        
        browser.scrollDown("Down", 200, 400);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ nickname +"']]", nickname);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='" + "  ••" + aNumberChange + "']]", aNumberChange1);
        //browser.sleepThread(2000);
    }
    
    // S - Method - to verify text fields validation on blank page for save button and close button functionality
    public void blankBeneficiaryPageBusiness()
    {
        browser.click("accessibilityId", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("accessibilityId", "Corporate");
        
        browser.click("accessibilityId", "companyname");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "accountname");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "nickname");
        browser.keyboardKey(66);
        
        browser.scrollDown("Down", 200, 400);
        
        //Verify error message for blank fields
        browser.verifyText("accessibilityId", "companyname-error", "Please enter a business name");
        browser.verifyText("accessibilityId", "accountname-name-error", "Please enter a 9 digit account number");
        browser.verifyText("accessibilityId", "nickname-error", "Please enter a nickname");
        
        browser.screenShot();
        
        //Verify error message for duplicate nick accessibilityId
        browser.sendKeys("accessibilityId", "nickname","Auto1");
        browser.keyboardKey(66);
        
        browser.click("accessibilityId", "accountnumber");
        browser.keyboardKey(66);
        
        browser.verifyText("accessibilityId", "nickname-error", "The nickname is already in use please try another");
        browser.screenShot();
        
        //Verify the field length for text fields
        browser.sendKeys("accessibilityId", "companyname", "CName Pneumonoultramicroscopicsilicovolcanoconiosis Pneumonoultramicroscopicsilicovolcanoconiosis");
        
        int CName = browser.getCharCount("accessibilityId", "companyname");
        if (CName == 80)
        {
          System.out.println("Company accessibilityId is 80 character");
        }
        else
        {
          System.out.println("Company accessibilityId is " + CName +" character");
        }
        
        browser.sendKeys("accessibilityId", "accountnumber", "1234567890");
        browser.keyboardKey(66);
        
        int aNumberCount = browser.getCharCount("accessibilityId", "accountnumber");
        if (aNumberCount == 9)
        {
            System.out.println("Account Number is 9 digit");
        }
        else
        {
            System.out.println("Account Number is " + aNumberCount + " digit");
        }
          
        browser.sendKeys("accessibilityId", "nickname","RNick Name Pneumonoultramicroscopicsilicovolcanoconiosis");        
        browser.keyboardKey(66);
        
        int nNameChar = browser.getCharCount("accessibilityId", "nickname");
        if (nNameChar == 17)
        {
            System.out.println("Nick accessibilityId is 17 character");
        }
        else
        {
            System.out.println("Nick accessibilityId is " + nNameChar + " character");
        }
          
        browser.click("accessibilityId", "closeButton");
        browser.waitUntilElementPresent("//*[@content-desc='logoutModalFirstTextField']");
        browser.click("accessibilityId", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("blank beneficiary page for Corporate tested");
    }

    // S - Method - to verify elements available on Corporate add beneficiary page/pop-up message and cancel button functionality
    public void cancelBeneficiaryBusiness()
    {
        browser.click("accessibilityId", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("accessibilityId", "Corporate;");
        
        browser.verifyElementPresent("accessibilityId", "companyname");
        browser.verifyElementPresent("accessibilityId", "accountname");
        browser.verifyElementPresent("accessibilityId", "nickname");
        
        browser.scrollDown("Down", 200, 500);
        
        browser.verifyElementPresent("accessibilityId", "cancelBeneficiary");
        browser.verifyElementPresent("accessibilityId", "saveBeneficiary");
        browser.screenShot();
        
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.verifyText("accessibilityId", "logoutModalFirstTextField", "Cancel adding new beneficiary");
        browser.verifyText("accessibilityId", "logoutModalSecondTextField", "Are you sure you want to cancel adding a new beneficiary?");
        browser.verifyElementPresent("accessibilityId", "logoutModalCancelButton");
        browser.verifyElementPresent("accessibilityId", "logoutModalLogoutButton");
        browser.screenShot();
        
        browser.click("accessibilityId", "logoutModalCancelButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.click("accessibilityId", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("cancle button on beneficiary page for Corporate tested");
    }
    
    // S - Method - to verify add beneficiary Corporate page
    public void beneficiaryBusiness(String nickname)
    {
        browser.click("accessibilityId", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("accessibilityId", "corporate");

        String cName = "Kartoscompanyname";
        String aNumber = "304510080";
                
        browser.sendKeys("accessibilityId", "companyname", cName);
        browser.keyboardKey(66);
        browser.sendKeys ("accessibilityId", "accountname", aNumber);
        browser.keyboardKey(66);
        browser.sendKeys ("accessibilityId", "nickname", nickname);
        browser.keyboardKey(66);
        
        String aNumberChange = browser.subString(aNumber, 5, 9); 
        String aNumberChange1 = "  ••" + aNumberChange;
        
        browser.scrollDown("Down", 200, 400);
        browser.verifyElementPresent("xpath", "//*[@content-desc='saveBeneficiary']");
        browser.click("xpath", "//*[@content-desc='saveBeneficiary']");
        //browser.sleepThread(1000);
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.scrollDown("Down", 200, 400);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='" + nickname + "']]", nickname);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='" + "  ••" + aNumberChange + "']]", aNumberChange1);
        //browser.sleepThread(2000);
    }
    
    // S - Method - Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount 
    public void balanceAndExceedMeToMe(String accountfrom, String accountto)
    {
        String balance = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@content-desc='accountCardBalanceAmount']");
        String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@content-desc='accountCardBalanceCurrency']");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String lastFourDigit = browser.subString(accountto, 5, 9);
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + lastFourDigit + "')]]");
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyText("xpath", "//*[@content-desc='TransferHeader']", "Your current balance is $" + balance + " " + currency + ".");
        browser.screenShot();
        browser.sendKeys("accessibilityId", "MoneyInput", "100000");
        browser.keyboardKey(66);
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("accessibilityId", "alertMessage", "Your account has " + balance + " " + currency + ". Please click here and enter a smaller amount in order to complete the transfer."); //update the message as per new D4 screenshot
        browser.screenShot();
        browser.click("accessibilityId", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
    }
    
    // S - Method - Error handling for Me to Me Transfers: Verify the error message for restricted accounts
    public void restictionsMeToMe(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String lastFourDigit = browser.subString(accountto, 5, 9);
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + lastFourDigit + "')]]");
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("accessibilityId", "MoneyInput", "10");
        browser.keyboardKey(66);
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@text='Unable to Process']");
        browser.verifyText("accessibilityId", "errorModalFirstTextField", "Unable to Process");
        browser.verifyText("accessibilityId", "errorModalSecondTextField", "Sorry, we are not able to process a transfer for this account. Please contact Customer Care at 1-888-622-3478.");
        browser.screenShot();
        browser.verifyElementPresent("errorModalButton", "Back to accounts");
        browser.click("errorModalButton", "Back to accounts");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName'");
    }
    
    // S - Method - Error handling for Me to Me Transfers: Verify the oops message
    public void oopsMeToMe(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String lastFourDigit = browser.subString(accountto, 5, 9);
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + lastFourDigit + "')]]");
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("accessibilityId", "MoneyInput", "10");
        browser.keyboardKey(66);
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("accessibilityId", "alertMessage", "Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
        browser.screenShot();
        browser.click("accessibilityId", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
    }
    
    // S - Method - Error handling for Me to You Transfers: Verify the available balance and exceed the total amount 
    public void balanceAndExceedMeToYou(String accountfrom, String accountto)
    {
        String balance = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@content-desc='accountCardBalanceAmount']");
        String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@content-desc='accountCardBalanceCurrency']");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + accountto + "')]][1]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your current balance is $" + balance + " "+ currency +".");
        browser.screenShot();
        browser.sendKeys("accessibilityId", "MoneyInput", "100000");
        browser.keyboardKey(66);
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("accessibilityId", "alertMessage", "Your account has " + balance + " " + currency + ". Please click here and enter a smaller amount in order to complete the transfer."); //update the message as per new D4 screenshot
        browser.screenShot();
        browser.click("accessibilityId", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
    }
    
    // S - Method - Error handling for Me to You Transfers: Verify the error message for restricted accounts
    public void restictionsMeToYou(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + accountto + "')]][1]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("accessibilityId", "MoneyInput", "10");
        browser.keyboardKey(66);
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@text='Unable to Process']");
        browser.verifyText("accessibilityId", "errorModalFirstTextField", "Unable to Process");
        browser.verifyText("accessibilityId", "errorModalSecondTextField", "Sorry, we are not able to process a transfer for this account. Please contact Customer Care at 1-888-622-3478.");
        browser.screenShot();
        browser.verifyElementPresent("errorModalButton", "Back to accounts");
        browser.click("errorModalButton", "Back to accounts");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName'");
    }
    
    // S - Method - Error handling for Me to You Transfers: Verify the oops message
    public void oopsMeToYou(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='" + accountfrom + "']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••" + accountto + "')]][1]");
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("accessibilityId", "MoneyInput", "10");
        browser.keyboardKey(66);
        browser.click("accessibilityId", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("accessibilityId", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("accessibilityId", "alertMessage", "Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
        browser.screenShot();
        browser.click("accessibilityId", "backButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
    }
    
    //M - Method - to verify Limits while transferring money
    public void verifyLimitMeToMe(String accountfromtype,String accountfromno,String amount)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfromno +"']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        browser.click("xpath", "(//*[@content-desc='accountCard'][1])[2]");
        browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[1]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
        browser.sendKeys("accessibilityId", "MoneyInput", amount);
        browser.keyboardKey(66);
        browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[3]");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("accessibilityId", "transferAmount", "$"+amount+"0.00");
        browser.click("accessibilityId", "submitTransferButton");
        
        if(browser.getSize("accessibilityId", "alertMessage")!=0)//Error message if limit exceeded
        {
            browser.verifyText("accessibilityId", "alertMessage", "");
            System.out.println("We're sorry, this transfer exceeds the limit. Please click here and enter a smaller amount in order to complete the transfer.");
        }
    }

    //M - Method - to verify Limits while transferring money
    public void verifyLimitMeToYou(String accountfromtype,String accountfromno,String amount)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfromno +"']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
        browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("accessibilityId", "TransferHeader");
        browser.sendKeys("accessibilityId", "MoneyInput", amount);
        browser.keyboardKey(66);
        browser.verifyElementPresent("xpath", "(//*[@contentDescription='Next Button Enabled'])[3]");
        browser.click("xpath", "(//*[@contentDescription='Next Button Enabled'])[3]");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("accessibilityId", "transferAmount", "$"+amount+"0.00");
        browser.click("accessibilityId", "submitTransferButton");
        if(browser.getSize("accessibilityId", "alertMessage")!=0)//Error message if limit exceeded
        {
            browser.verifyText("accessibilityId", "alertMessage", "");
            System.out.println("We're sorry, this transfer exceeds the limit. Please click here and enter a smaller amount in order to complete the transfer.");
        }
    }
}
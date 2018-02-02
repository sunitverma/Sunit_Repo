package com.businessfunctions;

import com.library.Common;

public class TransferPage {

    Common browser;
    //constructor with one argument.
    
    public TransferPage(Common br)
    {
        browser=br;
    }
    
    //K - Method - to verify me to me transfers
    public void meToMeTransfer(String Currency) throws InterruptedException
    {
        if(browser.verifyElementPresent("xpath", "//*[@class='android.widget.ImageView' and ./parent::*[@content-desc='Next Button Disabled']]"))
        {
            browser.click("xpath", "//*[@content-desc='accountCard' and ./parent::*[./parent::*[./preceding-sibling::*[@class='android.view.ViewGroup']]] and ./*[@text='Savings']]");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./parent::*[@content-desc='Next Button Enabled']]");
        }   
        Thread.sleep(5000);
        
        if(browser.verifyElementPresent("name", "Next Button Disabled"))
        {
            browser.getText("name", "MoneyInput");
            browser.sendKeys("name", "MoneyInput", "1");
            browser.click("name", "TransferHeader");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
            browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
            browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])["+ Currency +"])");
            //browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
            browser.click("name", "Next Button Enabled");
            browser.verifyElementPresent("name", "sourceAccountTitle");
            browser.click("xpath", "//*[@text='Cancel']");
            
            if(browser.getSize("name", "cancelModalLogoutButton")!=0)
            {
                browser.click("name", "cancelModalLogoutButton"); 
                browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
            }
        }
    }
     
    //K - Method - to verify me to me transfers on accounts from Account List page
    public void meToMeTransfersAccountListPage(String AccountType,String AccountNumber) throws InterruptedException
    {
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='"+ AccountType +"']]")!=0)
        {
            String Currency =browser.getText("xpath", "//*[@text='JMD' and  ./preceding-sibling::*[@text='435335217'] and ./preceding-sibling::*[@text='Savings']]");
            System.out.println("currency is" +Currency);
            browser.click("xpath", "//*[@text='TRANSFER' and ./parent::*[./preceding-sibling::*[@text='" + AccountNumber + "']]]");
        }
    }
     
    //K - Method - to verify me to me transfers on accounts from Account Summary page
    public void meToMeTransfersAccountSummaryPage(String AccountType,String AccountNumber) throws InterruptedException
    {
        if(browser.getSize("xpath", "//*[@class='android.view.ViewGroup' and *[@text='"+ AccountType +"']]")!=0)
        {
            browser.click("xpath", "//*[@content-desc='accountCard' and ./*[@text='"+ AccountNumber +"'] and ./*[@text='"+ AccountType +"']]");
            browser.click("xpath", "//*[@content-desc='Transfer Button' and ./preceding-sibling::*[@class='android.widget.ScrollView']]");
        }
    }
     
    //K - Method - to verify me to you transfers button
    public void meToYouTransferButton() throws InterruptedException
    {
        Thread.sleep(5000);
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
    }
    
    //K - Method - to verify me to you transfers
    public void meToYouTransfer(String Currency,String Nickname)
    {
        if(browser.getSize("xpath","//*[@content-desc='Next Button Disabled' and @width>0]")!=0)
        {
            browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
            //browser.click("xpath", "//*[@content-desc='accountCard' and ./*[@text='"+ Nickname +"']]");
            browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
            browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
            browser.verifyElementPresent("name", "TransferHeader");
            browser.getText("name", "MoneyInput");
            browser.sendKeys("name", "MoneyInput", "1");
            browser.click("name", "TransferHeader");
            browser.click("xpath", "//*[@class='android.widget.ImageView' and ./preceding-sibling::*[@class='android.view.ViewGroup']]");
            browser.click("xpath", "//*[@class='android.widget.EditText' and ./preceding-sibling::*[@class='android.widget.TextView']]");
            browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])["+ Currency +"])");
            //browser.click("xpath", "((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*[@class='android.view.ViewGroup'])/*[@text='"+Currency+"'])");
            browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
            browser.verifyElementPresent("name", "sourceAccountTitle");
            browser.click("xpath", "//*[@text='Cancel']");
            if(browser.getSize("name", "cancelModalLogoutButton")!=0)
            {
                browser.click("name", "cancelModalLogoutButton"); 
                browser.verifyElementPresent("xpath", "//*[@content-desc='welcomeName']");
            }
        }
    }
    
    //S - Method - to verify transfer flowpage
    public void transferFlowMeToMe(String accountfromtype, String amount)
    {    
        String accountfromno = browser.getText("xpath", "(//*[@content-desc='accountCard']//*[@content-desc='accountCardType'][@text='" + accountfromtype + "']/..//*[@content-desc='accountCardBalanceAmount'][not(@text='0.00')])[1]");
        String accounttono = browser.getText("xpath", "(//*[@content-desc='accountCard']//*[@content-desc='accountCardType'][@text='Savings' and 'Chequing']/..//*[@content-desc='accountCardNumber'][not(@text='356425529')])[1]");//"+ accountfromno +"
        String accounttotype = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accounttono + "']]//*[@content-desc='accountCardType']");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfromno +"']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String trunck = browser.subString(accounttono, 5, 9);
        browser.click("xpath", "//*[@text='  ••"+ trunck +"']");
        System.out.println("account no are correct for account from and account to");
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.sendKeys("name", "MoneyInput", amount);
        browser.keyboardKeys(66);
        String currency = browser.getText("xpath" , "(//*[@class='android.widget.EditText'])[4]");
        browser.click("name", "Next Button Enabled");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("name", "sourceAccountTitle", "Send from");
        browser.verifyText("name", "destinationAccountTitle", "to");
        browser.verifyText("name", "transferAmountTitle", "for the amount of");
        browser.verifyElementPresent("name", "submitTransferButton");
        browser.verifyText("name", "sourceAccountTypeAndNumber", accountfromtype + "  ••" + browser.subString(accountfromno, 5, 9) );
        browser.verifyText("name", "destinationAccountTypeAndNumber", accounttotype + "  ••" + browser.subString(accounttono, 5, 9) );
        browser.verifyText("name", "transferCurrency", currency);
        browser.verifyText("name", "transferAmount", " $"+amount+"0.00");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
        browser.verifyElementPresent("name", "transferSuccessImage");
        browser.verifyText("name", "transferSuccessText", "Success! \nYour transfer is complete.");
        browser.click("name", "backToAccountsButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("xpath", "//*[@text='"+accountfromno+"']", accountfromno);
    }
    
    //K - Method - to verify Me to You transfer flowpage
    public void transferFlowMeToYou(String accountfromtype,String amount) throws InterruptedException
    {
        String accountfromno = browser.getText("xpath", "(//*[@content-desc='accountCard' and *[@text='"+ accountfromtype +"']]//*[@content-desc='accountCardNumber'])[1]");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfromno +"']]//*[@content-desc='Transfer Button']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "(//*[@content-desc='accountCard'][1])[3]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.sendKeys("name", "MoneyInput", amount);
        browser.keyboardKeys(66);
       
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        
        //Transfer Review Page
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.verifyText("name", "sourceAccountTitle", "Send from");
        browser.verifyText("name", "destinationAccountTitle", "to");
        browser.verifyText("name", "transferAmountTitle", "for the amount of");
        browser.verifyElementPresent("name", "submitTransferButton");
        browser.verifyText("name", "sourceAccountTypeAndNumber", accountfromtype + "  ••" + browser.subString(accountfromno, 5, 9) );
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
        
        browser.verifyText("name", "transferAmount", "$"+amount+"0.00");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='transferSuccessImage']");
        browser.verifyElementPresent("name", "transferSuccessImage");
        browser.verifyText("name", "transferSuccessText", "Success! \nYour transfer is complete.");
        browser.click("name", "backToAccountsButton");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName']");
        browser.verifyText("xpath", "//*[@text='"+accountfromno+"']", accountfromno);
    }
    
    //K - Method - Transfer Me to You after entering OTP and PIN and click on submit
    public void transferUsingOTPSubmit()
    {
        browser.click("xpath", "save button"); //click on save button
        browser.verifyElementPresent("xpath", "Cancel button"); //Verify cancel button
        browser.verifyElementPresent("xpath", "Submit button"); //Verify submit button
        browser.sendKeys("xpath", "PIN + TOKEN", "PIN + TOKEN"); //Enter pin and token
        browser.click("xpath", "submit button"); //click on submit button
        browser.verifyElementPresent("xpath", "Element in list of beneficiaries page");
        browser.verifyElementPresent("xpath", "toast message"); //verify the toast message
    }
    
    //K - Method - Transfer Me to You after entering OTP and PIN and click on submit
    public void transferUsingOTPCancel()
    {
        browser.click("xpath", "save button"); //click on save button
        browser.verifyElementPresent("xpath", "Cancel button"); //Verify cancel button
        browser.verifyElementPresent("xpath", "Submit button"); //Verify submit button
        browser.sendKeys("xpath", "PIN + TOKEN", "PIN + TOKEN"); //Enter pin and token
        browser.click("xpath", "cancel button"); //click on submit button
        browser.verifyElementPresent("xpath", "Element in list of beneficiaries page"); 
    }
    
    //S - Method - to verify add beneficiary functionality
    public void addBeneficiaryToAccount(String accounttype)
    {
        browser.click("xpath", "(//*[@content-desc='accountCard' and *[@text='"+ accounttype + "']]//*[@content-desc='Transfer Button'])[1]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.waitUntilElementPresent("//*[@content-desc='addButton']");
    }
      
    //S - Method - to verify text fields validation on blank page for save button and close button functionality
    public void blankbeneficiaryPageRetail()
    {
        browser.click("name", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("name", "Retail");
        
        browser.click("name", "saveBeneficiary");
        browser.verifyText("name", "firstname", "first name missing");
        browser.verifyText("name", "lastname", "last name missing");
        browser.verifyText("name", "accountnumber", "account number missing");
        browser.verifyText("name", "nickname", "nick name missing");
        browser.click("name", "closeButton");
        browser.waitUntilElementPresent("//*[@content-desc='logoutModalFirstTextField']");
        browser.click("name", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("blank beneficiary page for Retail tested");
    }
    
    //S - Method - to verify elements available on Retail add beneficiary page/pop-up message and cancel button functionality
    public void cancelBeneficiaryRetail() throws Exception
    {
        browser.click("name", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("name", "Retail");
        
        browser.verifyElementPresent("name", "firstname");
        browser.verifyElementPresent("name", "lastname");
        browser.verifyElementPresent("name", "accountnumber");
        browser.verifyElementPresent("name", "nickname");
        browser.verifyElementPresent("name", "cancelBeneficiary");
        browser.verifyElementPresent("name", "saveBeneficiary");
        
        //browser.scrollDown("Down", 200, 500);
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.verifyText("name", "logoutModalFirstTextField", "Cancel adding new beneficiary");
        browser.verifyText("name", "logoutModalSecondTextField", "Are you sure you want to cancel adding a new beneficiary?");
        browser.verifyElementPresent("name", "logoutModalCancelButton");
        browser.verifyElementPresent("name", "logoutModalLogoutButton");
        browser.click("name", "logoutModalCancelButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.click("name", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("cancel button on beneficiary page for Retail tested");
    }
    
    //S - Method - to verify add beneficiary Retail page
    public void beneficiaryRetail() throws Exception
    {
        browser.click("name", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("name", "Retail");
        
        String fname = "Kartosfirstname";
        String lname = "Kartoslastname";
        String anumber = "304510080";
        String nname = "Kartosnickname1";        
        
        /*browser.sendKeys("name", "first name", "RFirst Name Pneumonoultramicroscopicsilicovolcanoconiosis");
        int fNameChar = browser.getCharCount("name", "first name");
        if (fNameChar==40)
        {
            System.out.println("First Name is 40 character");
        }
        else
        {
            System.out.println("First Name is " + fNameChar +" character");
        }
          
        browser.sendKeys("name", "last name", "RLast Name Pneumonoultramicroscopicsilicovolcanoconiosis");
        int lNameChar = browser.getCharCount("name", "last name");
        if (lNameChar==40)
        {
            System.out.println("Last Name is 40 character");
        }
        else
        {
            System.out.println("Last Name is " + lNameChar +" character");
        }
        
        browser.sendKeys("name", "account number", "1234567890");
        String aNumber = browser.getText("name", "account number");
        int aNumbercount = browser.getCharCount("name", "account number");
        if (aNumbercount==9)
        {
            System.out.println("Account Number is 9 digit");
        }
        else
        {
            System.out.println("Account Number is " + aNumbercount +" digit");
        }
          
        browser.sendKeys("name", "nickname","RNick Name Pneumonoultramicroscopicsilicovolcanoconiosis");        
        String nName = browser.getText("name", "nickname");
        int nNameChar = browser.getCharCount("name", "nickname");
        if (nNameChar==17)
        {
            System.out.println("Nick Name is 17 character");
        }
        else
        {
            System.out.println("Nick Name is " + nNameChar +" character");
        }
        */
        
        browser.sendKeys("name", "firstname", fname);
        browser.keyboardKeys(66);
        browser.sendKeys ("name", "lastname", lname);
        browser.keyboardKeys(66);
        browser.sendKeys ("name", "accountnumber", anumber);
        browser.keyboardKeys(66);
        browser.sendKeys ("name", "nickname", nname);
        browser.keyboardKeys(66);
        
        String aNumberChange = browser.subString(anumber, 5, 9); 
        String aNumberChange1 = "  ••" + aNumberChange;
        
        browser.scrollDown("Down", 200, 400);
        browser.verifyElementPresent("xpath", "//*[@content-desc='saveBeneficiary']");
        browser.click("xpath", "//*[@content-desc='saveBeneficiary']");
        browser.sleepThread(1000);
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.scrollDown("Down", 200, 400);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ nname +"']]", nname);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ "  ••"+ aNumberChange +"']]", aNumberChange1);
        browser.sleepThread(2000);
    }
    
    //S - Method - to verify text fields validation on blank page for save button and close button functionality
    public void blankbeneficiaryPageCorporate()
    {
        browser.click("name", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("name", "Corporate");
        
        browser.click("name", "saveBeneficiary");
        browser.verifyText("name", "companyname", "name missing");
        browser.verifyText("name", "accountname", "account number missing");
        browser.verifyText("name", "nickname", "nick name missing");
        browser.click("name", "closeButton");
        browser.waitUntilElementPresent("//*[@content-desc='logoutModalFirstTextField']");
        browser.click("name", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("blank beneficiary page for Corporate tested");
    }

    //S - Method - to verify elements available on Corporate add beneficiary page/pop-up message and cancel button functionality
    public void cancelBeneficiaryCorporate() throws Exception
    {
        browser.click("name", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("name", "Corporate;");
        
        browser.verifyElementPresent("name", "companyname");
        browser.verifyElementPresent("name", "accountname");
        browser.verifyElementPresent("name", "nickname");
        browser.verifyElementPresent("name", "cancelBeneficiary");
        browser.verifyElementPresent("name", "saveBeneficiary");
        
        //browser.scrollDown("Down", 200, 500);
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.verifyText("name", "logoutModalFirstTextField", "Cancel adding new beneficiary");
        browser.verifyText("name", "logoutModalSecondTextField", "Are you sure you want to cancel adding a new beneficiary?");
        browser.verifyElementPresent("name", "logoutModalCancelButton");
        browser.verifyElementPresent("name", "logoutModalLogoutButton");
        browser.click("name", "logoutModalCancelButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.click("xpath", "//*[@content-desc='cancelBeneficiary']");
        browser.click("name", "logoutModalLogoutButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        System.out.println("cancle button on beneficiary page for Corporate tested");
    }
    
    //S - Method - to verify add beneficiary Corporate page
    public void beneficiaryCorporate() throws Exception
    {
        browser.click("name", "addButton");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][@text='Add New Beneficiary']");
        browser.click("name", "Corporate");

        String cname = "Kartoscompanyname";
        String anumber = "304510080";
        String nname = "Kartoscomnickname";
        
        /*browser.sendKeys("name", "company name", "CName Pneumonoultramicroscopicsilicovolcanoconiosis Pneumonoultramicroscopicsilicovolcanoconiosis");
        int CName = browser.getCharCount("name", "company name");
        if (CName==80)
        {
          System.out.println("Company Name is 80 character");
        }
        else
        {
          System.out.println("Company Name is " + CName +" character");
        }
        
        browser.sendKeys("name", "account number", "1234567890");
        String aNumber = browser.getText("name", "account number");
        int aNumbercount = browser.getCharCount("name", "account number");
        if (aNumbercount==9)
        {
          System.out.println("Account Number is 9 digit");
        }
        else
        {
          System.out.println("Account Number is " + aNumbercount +" digit");
        }
        
        browser.sendKeys("name", "nickname","CNick Name Pneumonoultramicroscopicsilicovolcanoconiosis");
        String nName = browser.getText("name", "nickname");
        int nNameChar = browser.getCharCount("name", "nickname");
        if (nNameChar==17)
        {
          System.out.println("Nick Name is 17 character");
        }
        else
        {
          System.out.println("Nick Name is " + nNameChar +" character");
        }
        */
        
        browser.sendKeys("name", "companyname", cname);
        browser.keyboardKeys(66);
        browser.sendKeys ("name", "accountname", anumber);
        browser.keyboardKeys(66);
        browser.sendKeys ("name", "nickname", nname);
        browser.keyboardKeys(66);
        
        String aNumberChange = browser.subString(anumber, 5, 9); 
        String aNumberChange1 = "  ••" + aNumberChange;
        
        browser.scrollDown("Down", 200, 400);
        browser.verifyElementPresent("xpath", "//*[@content-desc='saveBeneficiary']");
        browser.click("xpath", "//*[@content-desc='saveBeneficiary']");
        browser.sleepThread(1000);
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.scrollDown("Down", 200, 400);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ nname +"']]", nname);
        browser.verifyText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ "  ••"+ aNumberChange +"']]", aNumberChange1);
        browser.sleepThread(2000);
    }
    
    //S - Method - Error handling for Me to Me Transfers: Verify the available balance and exceed the total amount 
    public void balanceAndExceedMetoMe(String accountfrom, String accountto)
    {
        String balance = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@content-desc='accountCardBalanceAmount']");
        String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@content-desc='accountCardBalanceCurrency']");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String lastfourdigit = browser.subString(accountto, 5, 9);
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••"+ lastfourdigit +"')]]");
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyText("xpath", "//*[@content-desc='TransferHeader']", "Your current balance is $"+ balance +" "+currency+".");
        browser.screenShot();
        browser.sendKeys("name", "MoneyInput", "100000");
        browser.keyboardKeys(66);
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("name", "alertMessage", "Your account has "+ balance +" "+ currency + ". Please enter a smaller amount in order to complete the transfer.");
        browser.screenShot();
        //browser.click("partialLinkText", "enter a smaller amount");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.click("xpath", "//*[@text='Cancel']");
        browser.click("name", "cancelModalLogoutButton");
    }
    
    //S - Method - Error handling for Me to Me Transfers: Verify the error message for restricted accounts
    public void restictionsMetoMe(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String lastfourdigit = browser.subString(accountto, 5, 9);
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••"+ lastfourdigit +"')]]");
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("name", "MoneyInput", "10");
        browser.keyboardKeys(66);
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@text='Unable to Process']");
        browser.verifyText("name", "errorModalFirstTextField", "Unable to Process");
        browser.verifyText("name", "errorModalSecondTextField", "Sorry, we are not able to process a transfer for this account. Please contact Customer Care at 1-888-622-3478.");
        browser.screenShot();
        browser.verifyElementPresent("errorModalButton", "Back to accounts");
        browser.click("errorModalButton", "Back to accounts");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName'");
    }
    
    //S - Method - Error handling for Me to Me Transfers: Verify the oops message
    public void oopsMetoMe(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='MY ACCOUNTS']");
        String lastfourdigit = browser.subString(accountto, 5, 9);
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••"+ lastfourdigit +"')]]");
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("name", "MoneyInput", "10");
        browser.keyboardKeys(66);
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("name", "alertMessage", "Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
        browser.screenShot();
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.click("xpath", "//*[@text='Cancel']");
        browser.click("name", "cancelModalLogoutButton");
    }
    
    //S - Method - Error handling for Me to You Transfers: Verify the available balance and exceed the total amount 
    public void balanceAndExceedMetoYou(String accountfrom, String accountto)
    {
        String balance = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@content-desc='accountCardBalanceAmount']");
        String currency = browser.getText("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@content-desc='accountCardBalanceCurrency']");
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••"+ accountto +"')]][1]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyText("xpath", "(//*[@content-desc='TransferHeader'][2])[2]", "Your current balance is $"+ balance +" "+currency+".");
        browser.screenShot();
        browser.sendKeys("name", "MoneyInput", "100000");
        browser.keyboardKeys(66);
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("name", "alertMessage", "Your account has "+ balance +" "+ currency + ". Please enter a smaller amount in order to complete the transfer.");
        //browser.click("partialLinkText", "enter a smaller amount");
        browser.screenShot();
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.click("xpath", "//*[@text='Cancel']");
        browser.click("name", "cancelModalLogoutButton");
    }
    
    //S - Method - Error handling for Me to You Transfers: Verify the error message for restricted accounts
    public void restictionsMetoYou(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••"+ accountto +"')]][1]");
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'][1])[2]");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("name", "MoneyInput", "10");
        browser.keyboardKeys(66);
        browser.click("xpath", "(//*[@content-desc='Next Button Enabled'])[3]");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@text='Unable to Process']");
        browser.verifyText("name", "errorModalFirstTextField", "Unable to Process");
        browser.verifyText("name", "errorModalSecondTextField", "Sorry, we are not able to process a transfer for this account. Please contact Customer Care at 1-888-622-3478.");
        browser.screenShot();
        browser.verifyElementPresent("errorModalButton", "Back to accounts");
        browser.click("errorModalButton", "Back to accounts");
        browser.waitUntilElementPresent("//*[@content-desc='welcomeName'");
    }
    
    //S - Method - Error handling for Me to You Transfers: Verify the oops message
    public void oopsMetoYou(String accountfrom, String accountto)
    {
        browser.click("xpath", "//*[@content-desc='accountCard' and *[@text='"+ accountfrom +"']]//*[@text='TRANSFER']");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader'][1]");
        browser.click("xpath", "//*[@text='NCB BENEFICIARIES']");
        browser.click("xpath", "//*[@content-desc='accountCard' and ./*[contains(@text, '  ••"+ accountto +"')]][1]");
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.sendKeys("name", "MoneyInput", "10");
        browser.keyboardKeys(66);
        browser.click("name", "Next Button Enabled");
        browser.waitUntilElementPresent("//*[@content-desc='sourceAccountTitle']");
        browser.click("name", "submitTransferButton");
        browser.waitUntilElementPresent("//*[@content-desc='alertMessage']");
        browser.verifyText("name", "alertMessage", "Oops! We have encountered an error. We apologize for the inconvenience while we fix it.");
        browser.screenShot();
        browser.waitUntilElementPresent("//*[@content-desc='TransferHeader']");
        browser.verifyElementPresent("name", "TransferHeader");
        browser.click("xpath", "//*[@text='Cancel']");
        browser.click("name", "cancelModalLogoutButton"); 
    }
}
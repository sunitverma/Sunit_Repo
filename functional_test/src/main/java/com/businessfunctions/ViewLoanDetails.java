package com.businessfunctions;

import com.testscripts.RootTest;

public class ViewLoanDetails extends RootTest{
	
	static Login login=new Login(brow);
	
	//Verify the details on Loan Details page
	public void viewLoanDetails()
	{
		brow.waitUntilElementPresent("(//*[@class='android.widget.TextView'])[71]");
		brow.verifyText("xpath","(//*[@class='android.widget.TextView'])[71]" , "My Accounts");
		
		brow.verifyText("xpath", "//*[@content-desc='accountNumber']", " 351158298");
		
		brow.verifyText("xpath", "//*[@content-desc='accountCardBalanceTitle']", "Loan Balance");
		brow.verifyText("xpath", "//*[@content-desc='balanceAmount']","421,945.82");
		brow.verifyText("xpath","(//*[@class='android.widget.TextView'])[62]","JMD");
		
		brow.verifyText("xpath","//*[@content-desc='Next Payment']", "Next Payment");
		brow.verifyText("xpath","//*[@content-desc='nextPaymentDueDate']" ,"Oct 30, 2017");
		brow.verifyText("xpath","//*[@content-desc='installmentAmount']" ,"$15,788.26");
		brow.verifyText("xpath","(//*[@class='android.widget.TextView'])[66]","JMD");
		
		brow.verifyText("xpath","//*[@content-desc='Loan Period']","Loan Period");
		brow.verifyText("xpath","(//*[@content-desc='loanPeriod'])[1]","60 months");
		brow.verifyText("xpath","(//*[@content-desc='loanPeriod'])[2]","22/Jun/15 - 30/Jun/20");
	}
	
	//Verify the details on paid loan account Details page
	public void zeroLoanBalance()
	{
		brow.waitUntilElementPresent("(//*[@class='android.widget.TextView'])[71]");
		brow.verifyText("xpath","(//*[@class='android.widget.TextView'])[71]" , "My Accounts");
		
		brow.verifyText("xpath", "//*[@content-desc='accountNumber']", " 021035270");
		
		brow.verifyText("xpath", "//*[@content-desc='balanceTypeTitle']", "Loan Balance");
		brow.verifyText("xpath", "//*[@content-desc='balanceAmount']","0.00");
		brow.verifyText("xpath","(//*[@class='android.widget.TextView'])[62]","JMD");
		
		brow.verifyText("xpath","//*[@content-desc='Next Payment']", "Next Payment");
		brow.verifyText("xpath","//*[@content-desc='nextPaymentDueDate']" ,"Dec 32, 1999");
		brow.verifyText("xpath","//*[@content-desc='installmentAmount']" ,"$0.00");
		brow.verifyText("xpath","(//*[@class='android.widget.TextView'])[66]"," N/A");
		
		brow.verifyText("xpath","//*[@content-desc='Loan Period']","Loan Period");
		brow.verifyText("xpath","(//*[@content-desc='loanPeriod'])[1]","0 months");
		brow.verifyText("xpath","(//*[@content-desc='loanPeriod'])[2]","31/Dec/99 - 32/Dec/99");
		
		brow.click("xpath", "//*[@content-desc='backButton']");
		
		brow.verifyText("xpath","//*[@content-desc='welcomeName']", "Welcome, Yolando");
		System.out.println("Account summary page displayed after click on back arrow on loan account details");
	}
}
package com.businessfunctions;

import com.testscripts.RootTest;

public class ViewSavingsDetails extends RootTest {
       
	static Login login=new Login(brow);
	public void savinsAccountBalanceBreakdown() throws InterruptedException
		{
			String accountType= brow.getText("xpath", "(//*[@content-desc='accountType'])").toString();
			String accountNumber= brow.getText("xpath", "(//*[@content-desc='accountNumber'])").toString();
			System.out.printf("Account Type: %s Account Number: %s\n",accountType, accountNumber);
			
			String availableBalance = brow.getText("xpath", "(//*[@content-desc='availableBalance'])");
			String availableAmount = brow.getText("xpath", "(//*[@content-desc='availableBalanceBalanceAmount'])");
			System.out.printf("Balance Type: %s Available Amount: %s\n",availableBalance, availableAmount);
			
			String lienBalance = brow.getText("xpath", "(//*[@content-desc='lienBalance'])");
			String lienBalanceAmount = brow.getText("xpath", "(//*[@content-desc='lienBalanceBalanceAmount'])");
			System.out.printf("Balance Type: %s Lien Balance Amount: %s\n",lienBalance, lienBalanceAmount);
			
			String unclearedBalance = brow.getText("xpath", "(//*[@content-desc='unclearBalance'])");
			String unclearedBalanceAmount = brow.getText("xpath", "(//*[@content-desc='unclearBalanceBalanceAmount'])");
			System.out.printf("Balance Type: %s Uncleared Balance Amount: %s\n",unclearedBalance, unclearedBalanceAmount);
			
			String balanceType = brow.getText("xpath", "(//*[@content-desc='balanceTypeTitle'])");
			String balanceAmount = brow.getText("xpath", "(//*[@content-desc='balanceAmount'])");
			System.out.printf("Balance Type: %sBalance Amount: %s",balanceType, balanceAmount);
			
			brow.scrollDown();
   }
}

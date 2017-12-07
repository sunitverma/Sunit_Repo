package com.businessfunctions;

import com.testscripts.RootTest;

public class ViewAccounts extends RootTest{
	
	static Login login=new Login(brow);
	public void viewAccount()
	{
		//brow.getCount();
		int count=brow.getCount("xpath","(//*[@contentDescription='accountText'])");
		if(count==10)
		System.out.println("Able to get the top 10 transactions");
		
	}

}
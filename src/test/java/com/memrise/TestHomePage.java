package com.memrise;

import org.testng.annotations.Test;
import com.memrise.core.Base;
import static com.memrise.core.GlobalConstants.*;

import static com.memrise.pages.LoginPage.*;
import static com.memrise.pages.HomePage.*;

public class TestHomePage extends Base{
	
	@Test
	public void testVerifyLearningPreferncesTab()throws Exception{
		String userName = userNameForPaymentsAccount1;
		String password = passwordForPaymentsAccount1;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			VerifyLearningPrefernces(driver , userName);
		}else{
			System.out.println("Account doesnot exist");
		}
	}
	@Test
	public void testVerifyStaticTextsAndLinksInHomepage() throws Exception{
		verifyStaticTextInHomePage(driver);
	}
	@Test
	public void testVerifyAppMenu() throws Exception{
		String userName = userNameForPaymentsAccount;
		String password = passwordForPaymentsAccount;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			verifyAppMenu(driver);
		}else{
			System.out.println("Account doesnot exist");
		}

	}
}

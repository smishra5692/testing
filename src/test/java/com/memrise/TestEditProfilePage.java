package com.memrise;

import org.testng.annotations.Test;
import com.memrise.core.Base;
import static com.memrise.core.GlobalConstants.*;

import static com.memrise.pages.LoginPage.*;
import static com.memrise.pages.MemberListingPage.*;

public class TestEditProfilePage extends Base{
	@Test
	public void testEditProfilePage() throws Exception{
		String userName = UserName2;
		String emailId  = Email2;
		String password = Password2;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			verifyProfilePage(driver, userName);
			verifyEditprofilepage(driver, userName, emailId);
			goToSettingsPage(driver);
			verifyAllLinksInSettingsPage(driver);
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
			verifyProfilePage(driver, userName);
			verifyEditprofilepage(driver, userName, emailId);
			goToSettingsPage(driver);
			verifyAllLinksInSettingsPage(driver);
		}
	}
	@Test
	public void testVerifyEmailButton() throws Exception{
		// this test case can not implemented because there is no link to verify email
	}
}

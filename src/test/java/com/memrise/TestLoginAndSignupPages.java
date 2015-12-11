package com.memrise;

import org.testng.annotations.Test;
import com.memrise.core.Base;
import static com.memrise.core.GlobalConstants.*;

import static com.memrise.pages.LoginPage.*;

public class TestLoginAndSignupPages extends Base{
	@Test
	public void testVerifyLoginFunctionality() throws Exception{
		String userName = UserName;
		String password = Password;
		verifyStartingPage(driver);
		verifyStartingPage(driver);
		clickOnLogin(driver);
		if(verifyLoginFunctionality(driver, userName, password)){
			System.out.println("Account exist");
		}
		else
			System.out.println("Account doesnot exist");
	}
	@Test
	public void testVerifyLoginWithFaceBookFunctionalityForFirstTime() throws Exception{
		String userName= userNameForSignupAccount;
		
	//	String password= faceBookPassword1;
		verifyStartingPage(driver);
		clickOnSignup(driver);
		clickOnSignupWithFacebook(driver);
		verifyFaceBookPage(driver);
		verifyFacebookUser(driver, userName);// in progress
	}
	@Test
	public void testVerifyLoginWithFaceBookFunctionality() throws Exception{
		String userName= UserName2;
		String email = Email2 ;
		String password = facebookPassword2 ;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		clickOnLoginWithFacebook(driver);
		verifyFacebookPage(driver, email, password);
		verifyFacebookUser(driver, userName); 
	}
	@Test
	public void testVerifyFacebookFunctionality() throws Exception{
		String userName = userNameForSignup ;
		String password = "password123";
		clickOnSignup(driver);
		clickOnSignupWithFacebook(driver);
		verifyFacebookFunctionality(driver, userName, password);
	}
	@Test
	public void testVerifyLoginWithGooglePlusFunctionality() throws Exception{
		verifyStartingPage(driver);
		clickOnLogin(driver);
		clickOnLoginWithGooglePlus(driver);
		verifiedGooglePlusUser(driver);
	}
	@Test
	public void testVerifyLoginGooglePlusFunctionalityForFirstTime() throws Exception{
		verifyStartingPage(driver);
		clickOnSignup(driver);
		clickOnSignupWithGooglePlus(driver);
	}
	@Test
	public void testVerifySignUpFunctionality() throws Exception{
		String password = passwordForSignup;
		String userName1 = userNameForSignupAccount;
		String emailId = userNameForSignup;
		verifyStartingPage(driver);
		clickOnSignup(driver);
		verifySignUpFunctionality(driver,userName1,emailId,password);
	}
	@Test
	public void testValidatingSignUpPage() throws Exception{
		verifyStartingPage(driver);
		clickOnSignup(driver);
		verifyingValidationsOfFieldsInSignUpPage(driver);
	}
	@Test
	public void testValidatingLoginPage() throws Exception{
		verifyStartingPage(driver);
		clickOnLogin(driver);
		verifyingValidationsOfFieldsInLoginPage(driver);
	}
	@Test
	public void testVerifyForgotPasswordFunctionality() throws Exception{
		String userName = forgotPasswordMail;
		String password = newPassword;
		String gmailPassword = gmailPasswordForForgotPasswordmail;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		DeleteAllEmailsBeforeResettingPassword(userName,password);
		clickOnForgotPasswordLinkInLoginPage(driver);
		verifyForgotPasswordFunctionality(driver,userName);
		gotoMailAndCheckLinkForResettingPWD(driver,userName,gmailPassword);// in progress
		//verifyingResetPasswordPage(driver,password);//in progress
		verifyLoginFunctionality(driver, userName, password);
	}
	@Test
	public void testVerifyChangePasswordFunctionality() throws Exception{
		String userName = forgotPasswordMail;
		String password = newPassword;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		verifyLoginFunctionality(driver, userName, password);
		goToProfilePage(driver);
		goToEditProfilePage(driver);
		verifyPasswordChange(driver, newPassword);
		clickSaveForEditProfile(driver);
	}
	@Test
	public void testVerifyLogOutWithFaceBookFunctionality() throws Exception{
		String userName= userNameForSignupAccount;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		clickOnLoginWithFacebook(driver);
		verifyFacebookUser(driver, userName); 
		verifyLogoutFunctionality(driver);
	}
	@Test
	public void testVerifyLogOutWithGooglePlusFunctionality() throws Exception{
		verifyStartingPage(driver);
		clickOnLogin(driver);
		clickOnLoginWithGooglePlus(driver);
		verifiedGooglePlusUser(driver);
		verifyLogoutFunctionality(driver);
	}
	@Test
	public void testVerifyLogOutFunctionality() throws Exception{
		String userName = userNameForSignup;
		String password = passwordForSignup;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		if(verifyLoginFunctionality(driver, userName, password)){
			System.out.println("Account exist");
			verifyLogoutFunctionality(driver);
		}
		else
			System.out.println("Account doesnot exist");
	}
}

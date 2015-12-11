package com.memrise.pages;

import static com.memrise.core.GlobalConstants.*;
import static com.memrise.core.Action.*;
import static com.memrise.pages.MemberListingPage.*;
import static com.memrise.pages.HomePage.*;
import java.util.List;
import io.appium.java_client.AppiumDriver;
import static com.memrise.core.Helper.*;
import static org.testng.Assert.*;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public static void verifyStartingPage(AppiumDriver driver) throws Exception{	
		waitTill(7000);
		swipeScreenRight(driver,2);
		assertTrue(verifyElementPresent(driver,"id==com.memrise.android.memrisecompanion:id/screen_slide_button_sign_up"),"signup button is not available");
		assertTrue(verifyElementPresent(driver,"id==com.memrise.android.memrisecompanion:id/screen_slide_button_login"),"login button is not present");
	}
	
	public static void clickOnLogin(AppiumDriver driver) throws Exception{
		waitTill(1000);
		assertTrue(verifyElementPresent(driver, "name==Login"),"login button is not present");
		click(driver, "name==Login");
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_email"),"UserName textbox is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_password"),"Password text is not present");
	}
	
	public static boolean verifyLoginFunctionality(AppiumDriver driver, String userName, String password ) throws Exception{
		waitTill(4000);
		click(driver, "id=="+id+"edit_text_email");
		sendKeys(driver, "id=="+id+"edit_text_email", userName);
		driver.hideKeyboard();
		click(driver, "id=="+id+"edit_text_password");
		sendKeys(driver, "id=="+id+"edit_text_password", password);
		driver.hideKeyboard();
		waitTill(2000);
		click(driver, "name==Login");
		if(verifyElementPresent(driver, "name==Error")){
			String errorMessage = getWebElement(driver, "id==android:id/message").getText();
			if(errorMessage.equals("Sorry, there was an error with the request!")){
				int count =1;
				while(count<=2){
				      click(driver, "id==android:id/button2");
				      waitTill(4000);
				      getWebElement(driver,  "id=="+id+"edit_text_email").clear();
				      sendKeys(driver, "id=="+id+"edit_text_email", userName);
				      getWebElement(driver, "id=="+id+"edit_text_password").clear();
				      sendKeys(driver, "id=="+id+"edit_text_password", password);
				      click(driver, "name==Login");
				      waitTill(4000);
				      if(!verifyElementPresent(driver, "name==Error")){
				    	  return true ;
				      }
				      count ++;
				}
			return true ;
			}else 
			return false ;
			
		}else
			return true ;
	}
	
	public static void verifyLogoutFunctionality(AppiumDriver driver) throws Exception{
		waitTill(4000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		click(driver, "name==Sign Out");
	}
	
	public static void clickOnLoginWithFacebook(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "name==Sign in with Facebook"),"Login with facebook button is not present");
		click(driver, "name==Sign in with Facebook");
	}
	
	public static void clickOnSignupWithFacebook(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "name==Sign up with Facebook"),"Signup with facebook button is not present");
		click(driver, "name==Sign up with Facebook");
	}
	
	public static void verifyFacebookPage(AppiumDriver driver, String email, String password) throws Exception{
		waitTill(3000);
		assertTrue(verifyElementPresent(driver, "id==com.facebook.katana:id/login_fb_logo"), "Not in the facebook page");
		getWebElement(driver, "id==com.facebook.katana:id/login_username").clear();
		sendKeys(driver, "id==com.facebook.katana:id/login_username", email);
		getWebElement(driver, "id==com.facebook.katana:id/login_password").clear();
		sendKeys(driver, "id==com.facebook.katana:id/login_password", password);
		click(driver, "id==com.facebook.katana:id/login_login");
		waitTill(3000);
	}
	public static void verifyFacebookUser(AppiumDriver driver, String userName) throws Exception{
		verifyDashBoardPage(driver);
		verifyProfilePage(driver, userName);
	}
	
	public static void loginWithFacebook(AppiumDriver driver, String userName, String password) throws Exception{
		// in progress
	}
	
	public static void clickOnLoginWithGooglePlus(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "name==Sign in with Google +"),"login with google plus button is not present");
		click(driver, "name==Sign in with Google +");
		waitTill(1000);
		if(verifyElementPresent(driver, "id==android:id/list")){
			click(driver, "name==surekhau6@gmail.com");
			waitTill(3000);
		}
	}
	
	public static void clickOnSignupWithGooglePlus(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "name==Sign up with Google +"), "Sign up with google plus button is not there");
		click(driver, "name==Sign up with Google +");
	}
	
	public static void deactivateAccount(AppiumDriver driver, String password) throws Exception{
		// in progress
	}
	
	public static void verifiedGooglePlusUser(AppiumDriver driver) throws Exception{
	/*	waitTill(3000);
		assertTrue(verifyElementPresent(driver, "name==Add account"), "Add account option is not present");
		click(driver, "name==Add account");
		waitTill(4000);
		assertTrue(verifyElementPresent(driver, "class==android.widget.EditText"),"Email field is not present");
		sendKeys(driver, "class==android.widget.EditText", userNameForGoogleLogin);
		click(driver, "xpath==//*[@content-desc='NEXT']");
		waitTill(2000);
		assertTrue(verifyElementPresent(driver, "xpath==//*[@content-desc='Password']"), "Password text isnot present");
		sendKeys(driver, "class==android.widget.EditText", Password);
		click(driver, "xpath==//*[@content-desc='NEXT']");
		click(driver, "xpath==//*[@content-desc='ACCEPT']");
		waitTill(5000);
		click(driver, "id==com.google.android.gms:id/accept_button");*/
		verifyDashBoardPage(driver);
		verifyProfilePage(driver, "surekha_u0a");
	}
	public static void clickOnSignup(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_sign_up"),"GetStarted button is not present");
		click(driver, "id=="+id+"screen_slide_button_sign_up");
	}
	
	public static boolean verifySignUpFunctionality(AppiumDriver driver, String userName, String email, String password) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_username"),"textbox for user name is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_email"),"text box for email is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_password"),"text box for password is not present");
		sendKeys(driver, "id=="+id+"edit_text_username", userName);
		driver.hideKeyboard();
		sendKeys(driver, "id=="+id+"edit_text_email", email);
		driver.hideKeyboard();
		sendKeys(driver, "id=="+id+"edit_text_password", password);
		click(driver,"id=="+id+"button_sign_up_in");
		waitTill(2000);
		if(verifyElementPresent(driver, "name==Error")){
			String errorMessage = getWebElement(driver, "id==android:id/message").getText();
			if(errorMessage.equals("Sorry, there was an error with the request!")){
				int count =1;
				while(count<=2){
				      click(driver, "id==android:id/button2");
				      waitTill(4000);
				      getWebElement(driver,  "id=="+id+"edit_text_email").clear();
				      sendKeys(driver, "id=="+id+"edit_text_email", userName);
				      getWebElement(driver, "id=="+id+"edit_text_password").clear();
				      sendKeys(driver, "id=="+id+"edit_text_password", password);
				      click(driver, "name==Login");
				      waitTill(4000);
				      if(!verifyElementPresent(driver, "name==Error")){
				    	  return true ;
				      }
				      count ++;
				}
			return true ;
			}else 
			return false ;
			
		}else
			return true ;
	}
	
	public static void goingbackToSignupPage(AppiumDriver driver) throws Exception{
		String message = getWebElement(driver, "id==android:id/message").getText();
		assertTrue(message.contains("Error"), "Error message is not displayed");
		click(driver, "ud==android:id/buttonPanel");
		driver.navigate().back();
		waitTill(4000);
	}
	
	public static void verifyingValidationsOfFieldsInSignUpPage(AppiumDriver driver) throws Exception{

    	//with invalid password, username, email
    	waitTill(2000);
		sendKeys(driver, "id=="+id+"edit_text_username", "test@Memrise");
		sendKeys(driver, "id=="+id+"edit_text_email", "sss123.com");
		sendKeys(driver, "id=="+id+"edit_text_password", "123");
		click(driver, "id=="+id+"button_sign_up_in");
		waitTill(1000);
		String errorMessageForPSWD = getWebElement(driver, "id=="+id+"text_password_error_message").getText();
		String errorMessageForUsername = getWebElement(driver, "id=="+id+"text_username_error_message").getText();
		String errorMessageForMail = getWebElement(driver, "id=="+id+"text_email_error_message").getText();
		assertTrue(errorMessageForPSWD.equals("Sorry, your password appears to be less than 6 characters!"), "Error message for password is not same");
		assertTrue(errorMessageForUsername.equals("This value may contain only letters, numbers and the following characters [./-/_]."), "Error message for Username is not same");
		assertTrue(errorMessageForMail.equals("Sorry, your email appears to be invalid!"), "Error message for Mail is not same");
		
		//with duplicate username
		waitTill(2000);
		getWebElement(driver, "id=="+id+"edit_text_username").clear();
		sendKeys(driver, "id=="+id+"edit_text_username", "test_Memrise");
		getWebElement(driver, "id=="+id+"edit_text_email").clear();
		sendKeys(driver, "id=="+id+"edit_text_email", "surekha.memrise@gmail.com");
		getWebElement(driver, "id=="+id+"edit_text_password").clear();
		sendKeys(driver, "id=="+id+"edit_text_password", "123456790");
		click(driver, "id=="+id+"button_sign_up_in");
		waitTill(1000);
		String errorMessage = getWebElement(driver, "id==android:id/message").getText();
		System.out.println(errorMessage);
		assertTrue(errorMessage.equals("Unfortunately, your username or e-mail is already in use, please try another."), "errormessageforduplicate username is changed");
		click(driver, "name==OK");
		//with duplicate emailid
		waitTill(2000);
		getWebElement(driver, "id=="+id+"edit_text_username").clear();
		sendKeys(driver, "id=="+id+"edit_text_username", "test_Mem");
		getWebElement(driver, "id=="+id+"edit_text_email").clear();
		sendKeys(driver, "id=="+id+"edit_text_email", "memrise@sunfra.com");
		getWebElement(driver, "id=="+id+"edit_text_password").clear();
		sendKeys(driver, "id=="+id+"edit_text_password", "123456752");
		click(driver, "id=="+id+"button_sign_up_in");
		waitTill(1000);
		String message = getWebElement(driver, "id==android:id/message").getText();
		System.out.println(message);
		assertTrue(message.equals("Unfortunately, your username or e-mail is already in use, please try another."), "errormessageforduplicate username is changed");
		click(driver, "name==OK");
		
	}
	
	public static void verifyingValidationsOfFieldsInLoginPage(AppiumDriver driver) throws Exception{
		
		//invalid user name,password
		sendKeys(driver, "id=="+id+"edit_text_email", "Memrise123");
		sendKeys(driver, "id=="+id+"edit_text_password", "123456");
		click(driver, "id=="+id+"button_sign_up_in");
		waitTill(3000);
		String errorMessage = getWebElement(driver, "id==android:id/message").getText();
		assertTrue(errorMessage.equals("Incorrect username or password!"), "error message for invalid username and password is changed");
	}
	
	public static void clickOnForgotPasswordLinkInLoginPage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_view_forgotten_password"),"forgotten password link is not present in login page");
		click(driver,"id=="+id+"text_view_forgotten_password");
	}
	
	public static void verifyForgotPasswordFunctionality(AppiumDriver driver, String userName) throws Exception{
		assertTrue(verifyElementPresent(driver, "class==android.widget.GridView"),"pop up message is not displayed");
		click(driver, "name==Memrise");
		click(driver, "name==Just once");
		waitTill(4000);
		swipeScreenRight(driver,1);
		sendKeys(driver, "class==android.widget.EditText", userName);
		click(driver, "class==android.widget.Button");
		waitTill(3000);
		click(driver, "name==Memrise");
		click(driver, "name==Just once");
		waitTill(4000);
		swipeScreenRight(driver, 1);
		assertTrue(verifyElementPresent(driver, "name==Password Reset Initialized - Memrise"), "popup for successfull password reset is not displyed");
		click(driver, "class==android.widget.ImageButton");
		waitTill(3000);
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_view_forgotten_password"), "Not in the login page");
		click(driver, "id=="+id+"text_view_forgotten_password");
		click(driver, "name==Internet");
		click(driver, "name==Just once");
	}
	
	public static void gotoMailAndCheckLinkForResettingPWD(AppiumDriver driver, String userName, String password) throws Exception{
		String url = null;
		String emailSubject="Password reset on www.memrise.com";
		url = getLinkWithContentInMail(emailSubject, "password/reset", userName, password);
		System.out.println("Password reset URL:"+url);
		if(url!=null){
			System.out.println(url);
			getWebElement(driver, "id==com.android.browser:id/url").clear();
			sendKeys(driver, "id==com.android.browser:id/url", url);
			click(driver, "id==com.android.browser:id/search");
			waitTill(3000);
		/*	driver.get(url);
			Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			AppiumDriver driver2 = new AndroidDriver();*/
			//assertTrue("Link not directed to reset password page", verifyElementPresent(driver, By.cssSelector(headingInResetPasswordPage)));
		}else{
			gotoMailAndCheckLinkForResettingPWD(driver, userName, password);	
		}
	}
	
	public static void verifyingResetPasswordPage(AppiumDriver driver, String password) throws Exception{
		
	}
	
	public static void goToLeaderBoardPage(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(1).click();
	}
	public static void goToProfilePage(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(2).click();
	}
	public static void goToSettingsPage(AppiumDriver driver) throws Exception{
		waitTill(4000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		click(driver, "name==Settings");
	}
	public static void DeleteAllEmailsBeforeResettingPassword(String MailID, String Password) throws Exception{
		deleteAllEmail(MailID, Password);
	}
	public static void goToEditProfilePage(AppiumDriver driver) throws Exception{
		waitTill(1000);
		click(driver, "id=="+id+"image_profile_picture");
	}
	public static void verifyPasswordChange(AppiumDriver driver, String newPassword) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_old_password"), "Old passwordfield is not present");
		sendKeys(driver, "id=="+id+"edit_text_old_password", newPassword);
		swipe(driver, 1);
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_new_password"), "New passwordfield is not present");
		sendKeys(driver, "id=="+id+"edit_text_new_password", gmailPasswordForForgotPasswordmail);
	}
	public static void clickSaveForEditProfile(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "xpath==//*[@content-desc='Save']"),"save option is not present in editprofile page");
		click(driver, "xpath==//*[@content-desc='Save']");
		waitTill(3000);
	}
	public static void VerifyLeaderboardPage(AppiumDriver driver) throws Exception{
		waitTill(1000);
		goToLeaderBoardPage(driver);
		String LeaderBoardMessage = getWebElement(driver, "id=="+id+"text_leaderboard_no_friends").getText();
		assertTrue(LeaderBoardMessage.equals("Win points, complete levels, and battle your friends on leaderboards!"), "Message in leaderboard is changed");
		driver.navigate().back();
	}
	public static void verifyFacebookFunctionality(AppiumDriver driver, String userName, String password) throws Exception{
		// with invalid password
		waitTill(3000);
		getWebElement(driver, "id==com.facebook.katana:id/login_username").clear();
		sendKeys(driver, "id==com.facebook.katana:id/login_username", userName);
		driver.hideKeyboard();
		getWebElement(driver, "id==com.facebook.katana:id/login_password").clear();
		sendKeys(driver, "id==com.facebook.katana:id/login_password", password);
		click(driver, "id==com.facebook.katana:id/login_login");
		waitTill(3000);
		assertTrue(verifyElementPresent(driver, "id==com.facebook.katana:id/alertTitle"), "Error message is not present");
		String title = getWebElement(driver, "id==com.facebook.katana:id/alertTitle").getText();
		String message = getWebElement(driver, "id==com.facebook.katana:id/message").getText();
		assertTrue(title.equals("Incorrect Password"), "Error message title is changed");
		assertTrue(message.equals("The password you entered is incorrect. Please try again."), "Error message is changed");
		click(driver, "id==com.facebook.katana:id/button1");
	}
	public static void verifyFaceBookPage(AppiumDriver driver) throws Exception{
		
	}
}

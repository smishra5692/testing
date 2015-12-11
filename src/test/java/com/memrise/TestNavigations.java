package com.memrise;

import org.testng.annotations.Test;
import com.memrise.core.Base;
import static com.memrise.core.GlobalConstants.*;

import static com.memrise.pages.LoginPage.*;
import static com.memrise.pages.MemberListingPage.*;
import static com.memrise.pages.HomePage.*;
import static com.memrise.pages.LearningPage.*;
import static com.memrise.pages.NavigationPage.*;
public class TestNavigations extends Base{
	@Test
	public void testVerifyTopNavigationMenu() throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			verifyDashBoardPage(driver);
			VerifyLeaderboardPage(driver);
			verifyProfilePage(driver, userName);
			verifyEditprofilepage(driver, userName, emailId);
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
			verifyDashBoardPage(driver);
			VerifyLeaderboardPage(driver);
			verifyProfilePage(driver, userName);
			verifyEditprofilepage(driver, userName, emailId);
		}
	}
	@Test
	public void testVerifyCheckFeaturesOfNonPremiumUsersandPremiumUsers() throws Exception{	
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			if(verifyUserIsPremium(driver)){
				goToSettingsPage(driver);
				verifyPremiumUserInSettingsPage(driver);
			}else{
				goToSettingsPage(driver);
				verifyNonPremiumUserInSettingsPage(driver);
			}
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
			if(verifyUserIsPremium(driver)){
				goToSettingsPage(driver);
				verifyPremiumUserInSettingsPage(driver);
			}else{
				goToSettingsPage(driver);
				verifyNonPremiumUserInSettingsPage(driver);
			}
		}
	}
	@Test
	public void testVerifyCoursesPage() throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		clickOnLogin(driver);
		boolean AccountExists=verifyLoginFunctionality(driver, userName, password);
		if(AccountExists){
			clickOnCourse(driver);
			verifyCoursesPage(driver);
		}else{
		goingbackToSignupPage(driver);
		clickOnSignup(driver);
		verifySignUpFunctionality(driver, userName, emailId, password);
		clickOnCourse(driver);
		verifyCoursesPage(driver);
		}
	}
	@Test
	public void testVerifyCreateCoursePage() throws Exception{
		//There is no option to create a course
	}
	@Test
	public void testVerifyProfilePage() throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			verifyProfilePage(driver, userName);
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
			verifyProfilePage(driver, userName);
		}
	}
	@Test
	public void testVerifySettingsPage() throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			goToSettingsPage(driver);
			verifyAllLinksInSettingsPage(driver);
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
			goToSettingsPage(driver);
			verifyAllLinksInSettingsPage(driver);
		}
	}
	@Test
	public void testVerifyCheckAccessingEditProfileFromProfilePage() throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			verifyProfilePage(driver, userName);
			verifyEditprofilepage(driver, userName, emailId);
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifyProfilePage(driver, userName);
			verifyEditprofilepage(driver, userName, emailId);
		}
	}
	@Test
	public void testVerifyCheckLogoInCoursesPage() throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		clickOnLogin(driver);
		boolean AccountExists=verifyLoginFunctionality(driver, userName, password);
		if(AccountExists){
			clickOnCourse(driver);
			verifyCoursesPage(driver);
			goingtoDashBoardfromCoursePage(driver);
		}else{
		goingbackToSignupPage(driver);
		clickOnSignup(driver);
		verifySignUpFunctionality(driver, userName, emailId, password);
		clickOnCourse(driver);
		verifyCoursesPage(driver);
		goingtoDashBoardfromCoursePage(driver);
		}
	}
	@Test
	public void testVerifyFunctionalityOfLeaderBoardButtonInDashBoard() throws Exception{
		String userName = UserNameForCoursesReview1;
		String password = PasswordsForCoursesReview1;
		clickOnLogin(driver);
		boolean AccountExists=verifyLoginFunctionality(driver, userName, password);
		if(AccountExists){
			VerifyLeaderboardPage(driver);
		}else{
			System.out.println("Account doesn't exist");
		}
	}
	@Test
	public void testVerifyCourseCards() throws Exception{
		String userName = UserNameForCoursesReview1;
		String password = PasswordsForCoursesReview1;
		clickOnLogin(driver);
		boolean AccountExists=verifyLoginFunctionality(driver, userName, password);
		if(AccountExists){
			verifyCourseCard(driver);
		}else{
			System.out.println("Account doesnot exist");
		}
	}
}

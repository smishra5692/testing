package com.memrise;

import org.testng.annotations.Test;
import com.memrise.core.Base;
import static com.memrise.core.GlobalConstants.*;
import static com.memrise.pages.LoginPage.*;
import static com.memrise.pages.LearningPage.*;
public class TestLearningSessionPage extends Base{
	@Test

	public void testResumeCourse()throws Exception{
		
	}
	@Test
	public void testVerifyMCTestWithCorrectAnswer()throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		verifyStartingPage(driver);
		clickOnLogin(driver);
		boolean AccountExists=verifyLoginFunctionality(driver, userName, password);
		if(AccountExists){
			clickOnCourse(driver);
		    selectEnglishCourse(driver);
		    searchACourse(driver, "Vocabulary for IELTS");
	        CheckPoints=true;
		    StartTheCourseAndVerifyMCPointsWithCorrectAnswers(driver, "Vocabulary for IELTS");
		    CheckPoints=false;	
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
			clickOnCourse(driver);
		    selectEnglishCourse(driver);
		    searchACourse(driver, "Vocabulary for IELTS");
	        CheckPoints=true;
		    StartTheCourseAndVerifyMCPointsWithCorrectAnswers(driver, "Vocabulary for IELTS");
		    CheckPoints=false;
		}
	}
}

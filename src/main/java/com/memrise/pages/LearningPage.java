package com.memrise.pages;

import static com.memrise.core.GlobalConstants.*;
import static com.memrise.core.Action.*;
import static com.memrise.pages.LoginPage.*;

import java.util.ArrayList;
import java.util.List;
import io.appium.java_client.AppiumDriver;
import static org.testng.Assert.*;
import org.openqa.selenium.WebElement;
public class LearningPage {
	 static ArrayList<String> Translations = new ArrayList<String>(); 
	 static ArrayList<String> Words = new ArrayList<String>();
	public static void clickOnCourse(AppiumDriver driver) throws Exception{
		waitTill(4000);
		List<WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		int index = element.size() - 1 ;
		element.get(index).click();
	}
	public static void selectEnglishCourse(AppiumDriver driver) throws Exception{
		waitTill(4000);
		assertTrue(verifyElementPresent(driver, "name==I want to learn…"),"not in the course selection page");
		click(driver, "name==English");
		waitTill(1000);
		click(driver,"id=="+id+"button_browse_more_courses");
	}
	public static void searchACourse(AppiumDriver driver, String CourseName) throws Exception{
		waitTill(4000);
		click(driver, "id=="+id+"search_button");
		waitTill(3000);
		sendKeys(driver, "id=="+id+"search_src_text", CourseName);
		driver.hideKeyboard();
		List<WebElement> element = getWebElements(driver, "id=="+id+"text_course_title");
		List<WebElement> element1 = getWebElements(driver, "id=="+id+"button_start_learning");
		int index=0;
		for(WebElement courseTitle : element){
			String title = courseTitle.getText();
			if(title.equals(CourseName)){
				element1.get(index).click();
			}
			index++;
		}
	}
	public static void selectRandomLevel(AppiumDriver driver) throws Exception{
		
	}
	public static void copyTheLevelWords(AppiumDriver driver) throws Exception{
		
	}
	public static void clickOnContinue(AppiumDriver driver) throws Exception{
		
	}
	public static void StartTheCourseAndVerifyMCPointsWithCorrectAnswers(AppiumDriver driver, String courseName) throws Exception{
		 for(int i=0;;i++){
			  boolean PageDetected=false;
			  System.out.println("StartTheCourse : "+i);
			  if(verifyElementPresent(driver, "id=="+id+"tooltip_title")){
				  String popupTitle = getWebElement(driver, "id=="+id+"tooltip_title").getText();
				  assertTrue(popupTitle.equals("This is what you're learning"),"popup Title is changed");
				  click(driver,"id=="+id+"dismiss_button");
			  }
			  /*if(verifyElementPresent(driver, By.cssSelector("#forest-banner>p"))){
				  PageDetected=true;
				  verifySessionCompletePage(driver,CourseName);
				  break;
			  }*/
			  if(!PageDetected){
				  if(checkLearningWordIsPresent(driver)){
				      PageDetected=true;
				      rememberTheWordsForReview(driver);
				   }
			  }
			  if(!PageDetected){
				  if(checkWordChoosingIsPresent(driver)){
					  PageDetected=true;
					/*  List<WebElement> element = driver.findElementsByXPath("//*[@class='android.widget.TextView'] and [@resource-id='']");
					  String text = element.get(0).getText();
					  System.out.println(text);*/
					  selectTheAppropriateOption(driver);
					}
			  }
			  if(!PageDetected){
				  if(checkWordTypingIsPresent(driver)){
					  PageDetected=true;
					  typeAppropriateAnswer(driver);
					}
			  }
			  if(!PageDetected){
				  if(checkWordsArrangementIsPresent(driver)){
					  PageDetected=true;
					  arrangeTheWordsInOrder(driver);
					}
			  }
		 }
	}
	public static boolean checkLearningWordIsPresent(AppiumDriver driver) throws Exception{
		boolean methodStatus=false;
		if(!methodStatus){
			 if(verifyElementPresent(driver, "id=="+id+"text_header_column_a") &&
				verifyElementPresent(driver, "id=="+id+"text_header_column_b") )
				 methodStatus = true ;
		}
		return methodStatus ;
	}
	public static boolean checkWordChoosingIsPresent(AppiumDriver driver) throws Exception{
		boolean methodStatus=false;
		if(!methodStatus){
			if(verifyElementPresent(driver, "id=="+id+"text_header_column_a")){
				if(verifyElementPresent(driver, "id=="+id+"text_header_instruction") &&
					verifyElementPresent(driver, "xpath==//*[@class='android.widget.TextView'] and [@resource-id='']")){
					methodStatus = true ;
				}
			}
			
		}
		return methodStatus ;
	}
	public static boolean checkWordTypingIsPresent(AppiumDriver driver) throws Exception{
		boolean methodStatus=false;
		if(!methodStatus){
			if(verifyElementPresent(driver, "id=="+id+"text_header_column_a")){
				if(verifyElementPresent(driver, "id=="+id+"text_header_instruction") &&
					verifyElementPresent(driver, "")){
					methodStatus = true ;
				}
			}
		}
		return methodStatus ;
	}
	public static boolean checkWordsArrangementIsPresent(AppiumDriver driver) throws Exception{
		boolean methodStatus=false;
		if(!methodStatus){
			if(verifyElementPresent(driver, "id=="+id+"text_header_column_a")){
				if(verifyElementPresent(driver, "id=="+id+"text_header_instruction") &&
					verifyElementPresent(driver, "id=="+id+"text_tapping")){
					methodStatus = true ;
				}
			}
		}
		return methodStatus ;
	}
	public static void rememberTheWordsForReview(AppiumDriver driver) throws Exception{
		 String UnKnownLngWrd="";
		 String KnownLngWrd="";
		 UnKnownLngWrd = getWebElement(driver, "id=="+id+"text_header_column_a").getText(); 
		 KnownLngWrd = getWebElement(driver, "id=="+id+"text_header_column_b").getText();
		 boolean IsWordAlreadyPresent=false;
	      for (int i=0;i<Translations.size();i++){
		     if (Translations.get(i).equals(UnKnownLngWrd)){
		    	IsWordAlreadyPresent=true;
		      }
		   }
	 if (!IsWordAlreadyPresent) {
		  Translations.add(UnKnownLngWrd);
		  Words.add(KnownLngWrd);		  
	   }    
	}
	public static void selectTheAppropriateOption(AppiumDriver driver) throws Exception{
		 int InitialUserPoints=-1;
		 int UserPointsAfterAnswering=-1;
		 ArrayList<String> Options = new ArrayList<String>(); 
		 String Question= getWebElement(driver, "id=="+id+"text_header_column_a").getText();
		 Question=Question.trim();
		 List<WebElement> OptionsList=getWebElements(driver, "xpath==//*[@class='android.widget.TextView'] and [@resource-id='']");
		 for(int i=0;i<OptionsList.size();i++){
			 Options.add(OptionsList.get(i).getText());
		 }
		 System.out.println("Question: "+Question+" /**/");
	     for (int i = 0; i < Options.size(); i++) {
			    String value = Options.get(i);
			    System.out.println("Options : "+i+"-> " + value);
			}
		   boolean AnswerKnown=false;
		   String AnswerForQuestionIs="";
		   for (int i=0;i<Translations.size();i++){
		     if (Translations.get(i).equals(Question)){
		    	 AnswerKnown=true;
		    	 System.out.println("Question found at Translations"+i+": "+Translations.get(i));
		    	 AnswerForQuestionIs=Words.get(i);
		    	 System.out.println("Answer is "+i+": "+Words.get(i));
		     }
		   }
		   if(!AnswerKnown){
			   for (int i=0;i<Words.size();i++){
				     if (Words.get(i).equals(Question)){
				    	 System.out.println("Question found at Words"+i+": "+Words.get(i));
				    	 AnswerForQuestionIs=Translations.get(i);
				    	 System.out.println("Answer is "+i+": "+Translations.get(i));
				     }
				   }
		   }
		   int OptionNo=-1;
		   for (int i=0;i<Options.size();i++){
			     if (Options.get(i).equals(AnswerForQuestionIs)){
			    	 System.out.println("Option found at "+i+": "+Options.get(i));
			    	 OptionNo=i;
			    	 break;
			     }
			   }
		   String Option = OptionsList.get(OptionNo).getText();
		   System.out.println("-----Final Option "+Option);
		   if(OptionNo>=0){
			   click(driver, "name=="+Option);
	       }
		   String Points="";	   
		   if(Points.equals("")){
			   waitTill(300);
			   Points=""; 
			   UserPointsAfterAnswering=Integer.parseInt(Points);
		   }else{
			   UserPointsAfterAnswering=Integer.parseInt(Points);
		   }
			 System.out.println("Initial: "+InitialUserPoints+" After : "+UserPointsAfterAnswering);
	}
	public static void typeAppropriateAnswer(AppiumDriver driver) throws Exception{
		
	}
	public static void arrangeTheWordsInOrder(AppiumDriver driver) throws Exception{
		
	}
	public static void verifyLearningSessionStartPageElements(AppiumDriver driver, String courseName) throws Exception{
		String title = getWebElement(driver,"id=="+id+"session_toolbar_title").getText();
		assertTrue(title.equals(courseName),"Course name is not equal in learning session page");
		assertTrue(verifyElementPresent(driver, "id=="+id+"cancel_session_button"),"Exit link is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_plant"), "progress plant is not present");
	}
	public static void LearnCourse(AppiumDriver driver)throws Exception{
		String userName = UserNameForCourses;
		String emailId  = EmailForCourses;
		String password = PasswordsForCourses;
		String CourseNametosearch="Advanced English (C1, C2)";
		clickOnLogin(driver);
		boolean login = verifyLoginFunctionality(driver, userName, password);
		if(login){
			clickOnCourse(driver);
		    selectEnglishCourse(driver);
		    searchACourse(driver, CourseNametosearch);
		}else{
			goingbackToSignupPage(driver);
			clickOnSignup(driver);
			verifySignUpFunctionality(driver, userName, emailId, password);
		}
	}
}

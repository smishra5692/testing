package com.memrise.pages;

import static com.memrise.core.Action.*;
import static com.memrise.core.GlobalConstants.*;
import static org.testng.Assert.*;
import java.util.List;

import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;

public class NavigationPage {
	
	public static boolean verifyUserIsPremium(AppiumDriver driver) throws Exception{
		if(verifyElementPresent(driver, "id=="+id+"memrise_premium")){
			return false ;
		}else{
			return true ;
		}
	}
	public static void verifyCoursesPage(AppiumDriver driver) throws Exception{
		waitTill(3000);
		List<WebElement> element = getWebElements(driver, "class==android.widget.TextView");
		String title = element.get(0).getText();
		log(title);
		assertTrue(title.contains("I want to learn…"), "Course page title is changed");
		assertTrue(verifyElementPresent(driver, "id=="+id+"search_courses"), "Search option is not present");
		assertTrue(verifyElementPresent(driver, "name==LANGUAGES"), "Languages option is not present");
		assertTrue(verifyElementPresent(driver, "name==OTHER TOPICS"), "Other topics option is not present");
	}
	public static void goingtoDashBoardfromCoursePage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"search_courses"), "Not in the courses page");
		driver.navigate().back();
		waitTill(2000);
		assertTrue(verifyElementPresent(driver, "class==android.support.v7.app.ActionBar$Tab"), "Not in the dashboard page");
	}
	public static void verifyCourseCard(AppiumDriver driver) throws Exception{
		List<WebElement> element = getWebElements(driver, "id=="+id+"text_course_title");
		if(element.size()==0){
			System.out.println("No coursecard is present in the dashboard");
		}else{
			int index = getRandomIntwithinRange(0, element.size());
			String selectedCourseName = element.get(index).getText();
			element.get(index).click();
			waitTill(3000);
			String CourseName = getWebElement(driver, "id=="+id+"toolbar_title").getText();
			assertTrue(selectedCourseName.equals(CourseName), "Not in the selected course page");
		}
	}
	public static void verifyPremiumUserInSettingsPage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_your_account"), "Account option is not there in settings page");
		String AccountText = getWebElement(driver, "id=="+id+"text_your_account").getText();
		assertTrue(AccountText.equals("Your account: Premium (unsubscribe)"), "Account is not a premium account");
	}
	public static void verifyNonPremiumUserInSettingsPage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_your_account"), "Account option is not there in settings page");
		String AccountText = getWebElement(driver, "id=="+id+"text_your_account").getText();
		assertTrue(AccountText.equals("Your account: Free (subscribe)"), "Account is not a non premium account");
	}
}

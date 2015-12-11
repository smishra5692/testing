package com.memrise.pages;

import static com.memrise.core.Action.*;
import static com.memrise.core.GlobalConstants.*;
import static com.memrise.pages.MemberListingPage.*;
import static org.testng.Assert.*;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import static com.memrise.pages.LoginPage.*;
public class HomePage {
	public static void VerifyLearningPrefernces(AppiumDriver driver, String userName) throws Exception{
		goToSettingsPage(driver);
		verifyLearningAndSoundSetting(driver);
	}
	public static void verifyDashBoardPage(AppiumDriver driver) throws Exception{
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
	//	element.get(0).click();
		assertTrue(element.get(0).isDisplayed(), "Home button is not present in dashboard");
		assertTrue(element.get(1).isDisplayed(), "LeaderBoard option is not present in dashboard");
		assertTrue(element.get(2).isDisplayed(), "Profile option is not present in dashboard");
		assertTrue(element.get(3).isDisplayed(), "App menu is not present in dashboard");
		int size = element.size();
		assertTrue(element.get(size-1).isDisplayed(), "Add course option is not present in dashboard");
		assertTrue(verifyElementPresent(driver, "id=="+id+"words_learned"), "Number of words learnt option is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"words_to_review"), "Number of words available to review option is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"points"), "Number of points earned option is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"memrise_premium"), "Premium option is not present in dashboard page");
	}
	public static void verifyStaticTextInHomePage(AppiumDriver driver) throws Exception{
		verifyFirstHomePage(driver);
		swipeScreenRight(driver,1);
		verifySecondHomePage(driver);
		swipeScreenRight(driver,1);
		verifyThirdHomePage(driver);
	}
	public static void verifyFirstHomePage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_image_memrise_logo"), "Memrise logo is not present in 1st homepage");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_image_memrise_text_logo"), "Memrise text is missing from 1st homepage");
		String staticText  = getWebElement(driver, "id=="+id+"screen_slide_text_walkthrough").getText();
		assertTrue(staticText.equals("The ultimate vocabulary memorisation tool"), "Static text in 1st homepage is changed");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_sign_up"), "Sign up button is not present in 1st homepage");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_login"), "Login button is not present in 1st homepage");
	}
	public static void verifySecondHomePage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_image_memrise_logo"), "Memrise logo is not present in 2nd homepage");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_image_memrise_text_logo"), "Memrise text is missing from 2nd homepage");
		String staticText  = getWebElement(driver, "id=="+id+"screen_slide_text_walkthrough").getText();
		assertTrue(staticText.equals("Scientifically optimised memory training games"), "Static text in 2nd homepage is changed");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_sign_up"), "Sign up button is not present in 2nd homepage");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_login"), "Login button is not present in 2nd homepage");
	}
	public static void verifyThirdHomePage(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_image_memrise_logo"), "Memrise logo is not present in 3rd homepage");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_image_memrise_text_logo"), "Memrise text is missing from 3rd homepage");
		String staticText  = getWebElement(driver, "id=="+id+"screen_slide_text_walkthrough").getText();
		assertTrue(staticText.equals("Super-charge your memory."), "Static text in 3rd homepage is changed");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_sign_up"), "Sign up button is not present in 3rd homepage");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_login"), "Login button is not present in 3rd homepage");
	}
	public static void verifyAppMenu(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		assertTrue(verifyElementPresent(driver, "name==About Memrise"), "About memrise link is not present");
		assertTrue(verifyElementPresent(driver, "name==Memrise Science"), "Memrise science link is not present");
		assertTrue(verifyElementPresent(driver, "name==Help"), "Help link is not present");
		assertTrue(verifyElementPresent(driver, "name==Settings"), "Settings link is not present");
		assertTrue(verifyElementPresent(driver, "name==Sign Out"), "Sign out link is not present");
		verifyAboutMemriseLink(driver);
		verifyMemriseScienceLink(driver);
		verifyHelpLink(driver);
		verifySettingsLink(driver);
		verifySignOutLink(driver);
	}
	public static void verifyAboutMemriseLink(AppiumDriver driver) throws Exception{
		click(driver, "name==About Memrise");
		assertTrue(verifyElementPresent(driver, "id=="+id+"facebook_like_button"), "Facebook link is not present about memrise page");
		assertTrue(verifyElementPresent(driver, "id=="+id+"twitter_follow_button"), "Twitter link is not present in about Memrise page");
		assertTrue(getWebElement(driver, "id=="+id+"text_about_memrise_title").getText().equals("ABOUT MEMRISE"),"Not in the about memrise page");
		driver.navigate().back();
	}
	public static void verifyMemriseScienceLink(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		click(driver, "name==Memrise Science");
		assertTrue(getWebElement(driver, "name==Memrise Science").getText().equals("Memrise Science"), "Not in the Memrise Science page");
		driver.navigate().back();
	}
	public static void verifyHelpLink(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		click(driver, "name==Help");
		assertTrue(verifyElementPresent(driver, "name==Contact us"), "Contact us link is not present in help page");
		assertTrue(verifyElementPresent(driver, "name==Feedback Forum"), "Feedback forum link is not present in help page");
		driver.navigate().back();
	}
	public static void verifySettingsLink(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		click(driver, "name==Settings");
		assertTrue(getWebElement(driver, "name==Learning and Sound Settings").getText().equals("Learning and Sound Settings"), "Not in settings page");
		driver.navigate().back();
	}
	public static void verifySignOutLink(AppiumDriver driver) throws Exception{
		waitTill(2000);
		List <WebElement> element = getWebElements(driver, "class==android.widget.ImageView");
		element.get(3).click();
		click(driver, "name==Sign Out");
		assertTrue(verifyElementPresent(driver, "id=="+id+"screen_slide_button_sign_up"), "Not in the starting page");

	}
}

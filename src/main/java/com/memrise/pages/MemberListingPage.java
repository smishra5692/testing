package com.memrise.pages;

import static com.memrise.core.GlobalConstants.*;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import static com.memrise.core.Action.*;
import static com.memrise.pages.LoginPage.*;
import static org.testng.Assert.*;
public class MemberListingPage {

	public static void verifyProfilePage(AppiumDriver driver, String userName) throws Exception{
		waitTill(3000);
		goToProfilePage(driver);
		String profileName = getWebElement(driver, "id=="+id+"text_username").getText();
		assertTrue(profileName.equals(userName),"username is not same in profile page");
		assertTrue(verifyElementPresent(driver, "id=="+id+"words_learned"), "Words learnt is not present");
		//assertTrue(verifyElementPresent(driver, "id=="+id+"words"), "Words learnt is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"points"), "Points earned is not present");
	}
/*	
 * This method is not required due to the update version of app
 * public static void verifyProfileSettingsPage(AppiumDriver driver, String userName) throws Exception{
		click(driver,"name==PROFILE & SETTINGS");
		String profileName  = getWebElement(driver, "id=="+id+"text_username").getText();
		assertTrue(profileName.equals(userName),"username is not same in profile settings page");
		assertTrue(verifyElementPresent(driver, "id=="+id+"button_edit_profile"),"edit profile button is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_learning_settings"),"learning and sound setting link is not present");
		//swipeScreendown(driver, 2);
	//	String facebookLink = getWebElement(driver, "id=="+id+"text_connect_to_facebook").getText();
		swipe(driver, 2);
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_your_account"),"premium link is not pesent");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_connect_to_facebook"),"facebook link is not present");
		verifyAllLinksInProfileSettingsPage(driver);
	}*/
	public static void verifyEditprofilepage(AppiumDriver driver, String userName, String Email) throws Exception{
		click(driver, "id=="+id+"image_profile_picture");
		waitTill(2000);
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_username"), "TextField for username is not present");
		String name = getWebElement(driver, "id=="+id+"edit_text_username").getText();
		assertTrue(name.equals(userName), "Username is changed in editprofile page");
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_email"), "TextField for email is not present");
		String mail = getWebElement(driver, "id=="+id+"edit_text_email").getText();
		assertTrue(mail.equals(Email), "Email is changed in editprofile page");
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_old_password"), "TextField for oldpassword is not present");
		swipe(driver,5);
		assertTrue(verifyElementPresent(driver, "id=="+id+"edit_text_new_password"), "TextField for new password is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"spinner_age"), "Age selection is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"spinner_gender"), "Gender selection is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"spinner_timezone"), "Timezone selection is not present");
		driver.navigate().back();
		waitTill(1000);
	}
	public static void verifyAllLinksInSettingsPage(AppiumDriver driver) throws Exception{	
		verifyAccountSettings(driver);
		verifyFacebookLink(driver);
		verifyLearningAndSoundSetting(driver);
	}
	public static void verifyLearningAndSoundSetting(AppiumDriver driver) throws Exception{
		waitTill(1000);
		verifyTestType(driver);
		verifyLearningSetting(driver);
		swipe(driver, 5);
		verifySoundSetting(driver);
	}
	public static void verifyTestType(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_tapping_test"),"Tapping test type is not present");
		String status = getWebElement(driver, "id=="+id+"text_on_off_tapping").getText();
		if(status.equals("ON")){
			click(driver,"id=="+id+"switch_tapping_settings");
			String checkedStatus = getWebElement(driver, "id=="+id+"text_on_off_tapping").getText();
			assertTrue(checkedStatus.equals("OFF"),"tapping test value not changed from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_tapping_settings");
			String checkedStatus = getWebElement(driver, "id=="+id+"text_on_off_tapping").getText();
			assertTrue(checkedStatus.equals("ON"),"tapping test value not changed from OFF to ON");
		}
	}
	public static void verifyLearningSetting(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_item_per_learning_session"),"Items per learning is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_item_per_review_session"),"Items per reviewing is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"daily_reminder_text"),"Daily reminder option is not present");
		assertTrue(verifyElementPresent(driver, "id=="+id+"auto_detect_text"),"Auto detect option is not present");
		List<WebElement> option = getWebElements(driver, "id==android:id/text1");
		option.get(0).click();
		List<WebElement> element = getWebElements(driver, "class==android.widget.TextView");
		for(WebElement learningOptions:element){
			String text = learningOptions.getText();
			learningOptions.click();
			//String selectedOption = option.get(0).getText();
			String selectedOption = getWebElement(driver, "id==android:id/text1").getText();
			assertTrue(text.equals(selectedOption),"respective option is not selected for learning");
			option.get(0).click();
		}
	//	click(driver,"id=="+id+"text_item_per_learning_session");
		element.get(0).click();
		option.get(1).click();
		List<WebElement> element1 = getWebElements(driver, "class==android.widget.TextView");
		for(WebElement reviewOption : element1){
			String text = reviewOption.getText();
			reviewOption.click();
			String selectedOption = option.get(1).getText();
			assertTrue(text.equals(selectedOption),"respective option is not selected for reviewing");
			option.get(1).click();
		}
		//click(driver,"id=="+id+"text_item_per_review_session");
		element1.get(0).click();
		String reminderStatus = getWebElement(driver, "id=="+id+"text_on_off_daily_reminder").getText();
		if(reminderStatus.equals("ON")){
			click(driver,"id=="+id+"switch_daily_reminder");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_daily_reminder").getText();
			assertTrue(checkStatus.equals("OFF"),"Daily reminder is not change from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_daily_reminder");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_daily_reminder").getText();
			assertTrue(checkStatus.equals("ON"),"Daily reminder is not change from OFF to ON");
		}
		String autodetectStatus = getWebElement(driver, "id=="+id+"text_on_off_auto_detect").getText();
		if(autodetectStatus.equals("ON")){
			click(driver, "id=="+id+"switch_auto_detect");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_auto_detect").getText();
			assertTrue(checkStatus.equals("OFF"),"Auto detect is not change from ON to OFF");
		}else{
			click(driver, "id=="+id+"switch_auto_detect");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_auto_detect").getText();
			assertTrue(checkStatus.equals("ON"),"Auto detect is not change from OFF to ON");
		}
	}
	public static void verifySoundSetting(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_audio"),"Audio option is not present in Sound Setting");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_autoplay_audio"),"Auto play audio option is not present in Sound Setting");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_sound_effects"),"Sound effects option is not present in Sound Setting");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_audio_tests"),"Audio tests option is not present in Sound Setting");
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_vibration"),"Vibration option is not present in Sound Setting");
		String audioStatus = getWebElement(driver, "id=="+id+"text_on_off_audio").getText();
		if(audioStatus.equals("ON")){
			click(driver,"id=="+id+"switch_audio_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_audio").getText();
			assertTrue(checkStatus.equals("OFF"),"Audio status is not changed from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_audio_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_audio").getText();
			assertTrue(checkStatus.equals("ON"),"Audio status is not changed from OFF to ON");
		}
		String autoplayAudioStatus = getWebElement(driver, "id=="+id+"text_on_off_autoplay_audio").getText();
		if(autoplayAudioStatus.equals("ON")){
			click(driver,"id=="+id+"switch_autoplay_audio_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_autoplay_audio").getText();
			assertTrue(checkStatus.equals("OFF"),"Autoplay Audio status is not changed from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_autoplay_audio_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_autoplay_audio").getText();
			assertTrue(checkStatus.equals("ON"),"Autoplay Audio status is not changed from OFF to ON");
		}
		String soundEffectsStatus = getWebElement(driver, "id=="+id+"text_on_off_sound_effects").getText();
		if(soundEffectsStatus.equals("ON")){
			click(driver,"id=="+id+"switch_sound_effects_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_sound_effects").getText();
			assertTrue(checkStatus.equals("OFF"),"Sound Effects status is not changed from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_sound_effects_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_sound_effects").getText();
			assertTrue(checkStatus.equals("ON"),"Sound Effects status is not changed from OFF to ON");
		}
		String audioTestStatus = getWebElement(driver, "id=="+id+"text_on_off_audio_tests").getText();
		if(audioTestStatus.equals("ON")){
			click(driver,"id=="+id+"switch_audio_tests_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_audio_tests").getText();
			assertTrue(checkStatus.equals("OFF"),"Audio Test status is not changed from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_audio_tests_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_audio_tests").getText();
			assertTrue(checkStatus.equals("ON"),"Audio Test status is not changed from OFF to ON");
		}
		String vibrationStatus = getWebElement(driver, "id=="+id+"text_on_off_vibrations").getText();
		if(vibrationStatus.equals("ON")){
			click(driver,"id=="+id+"switch_vibration_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_vibrations").getText();
			assertTrue(checkStatus.equals("OFF"),"Vibration status is not changed from ON to OFF");
		}else{
			click(driver,"id=="+id+"switch_vibration_settings");
			String checkStatus = getWebElement(driver, "id=="+id+"text_on_off_vibrations").getText();
			assertTrue(checkStatus.equals("ON"),"Vibration status is not changed from OFF to ON");
		}
	}
	public static void verifyAccountSettings(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_your_account"),"Account option is not present");
		String accountStatus = getWebElement(driver, "id=="+id+"text_your_account").getText();
		if(accountStatus.contains("Premium")){
			log("Account is premium");
			click(driver,"id=="+id+"text_your_account");
			assertTrue(verifyElementPresent(driver, "class==android.widget.LinearLayout"),"popup message is not displayed");
			click(driver, "id=="+id+"text_offline_mode_negative");
		}else{
			log("Account is not premium");
		}
	}
	public static void verifyFacebookLink(AppiumDriver driver) throws Exception{
		assertTrue(verifyElementPresent(driver, "id=="+id+"text_connect_to_facebook"),"facebook link is not present");
	}
	public static void verifyPractice(AppiumDriver driver) throws Exception{
		swipeScreenUp(driver, 1);
	}
}

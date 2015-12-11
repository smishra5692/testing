package com.memrise.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
public class Action {
	public static void log(String message){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.print("--->"+sdf.format(cal.getTime())+" ");
		System.out.println(message);
	}
	/**
	 *  @Author :SANJU
	 */
	public static void waitTill(long ar) throws InterruptedException{
		Thread.sleep(ar);
	}
	/**
	 * @Author :SANJU
	 */
	public static void swipeScreendown(AppiumDriver driver, int repeat) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
	    Dimension screenSize = driver.manage().window().getSize();
	    Double screenWidth = Double.valueOf(String.valueOf(screenSize.getWidth())) / 2;
	    Double screenHeight = Double.valueOf(String.valueOf(screenSize.getHeight())) / 2;
	    swipeObject.put("startX", (screenWidth));
	    swipeObject.put("startY", screenHeight + 100);
	    swipeObject.put("endX", (screenWidth));
	    swipeObject.put("endY", (screenHeight));
	    swipeObject.put("duration", 1.0);
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    for(int i=0; i<repeat; i++){
		    js.executeScript("mobile: swipe", swipeObject);
		    waitTill(1000);
		 }	
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	/**
	 *  @Author :SANJU
	 */
	public static void swipe(AppiumDriver driver, int repeat){
		for(int i=1; i<=repeat; i++){
			driver.swipe(75, 275, 75, 50, (int).8);
		}
	}
	/**
	 *  @Author :SANJU
	 */
	public static void tapScreen(AppiumDriver driver,Double x,Double y) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("x", x); // pixels from left
		swipeObject.put("y", y); // pixels from top
		js.executeScript("mobile: tap", swipeObject);
		Thread.sleep(3000);
	}
	/**
	 *  @Author :SANJU
	 */
	public static List<WebElement> getWebElements(AppiumDriver driver, String identifier) throws Exception{
		String[] locator = identifier.split("==");
		List<WebElement> ele = null;
		try{
			if(locator[0].equalsIgnoreCase("class")){
				ele = driver.findElementsByClassName(locator[1]);
			} else if(locator[0].equalsIgnoreCase("xpath")){
				ele = driver.findElementsByXPath(locator[1]);
			} else if(locator[0].equalsIgnoreCase("name")){
				ele = driver.findElementsByName(locator[1]);
			} else if(locator[0].equalsIgnoreCase("id")){
				ele = driver.findElementsById(locator[1]);
			} else if(locator[0].equalsIgnoreCase("cssSlelector")){
				ele = driver.findElementsByCssSelector(locator[1]);
			} else if(locator[0].equalsIgnoreCase("linkText")){
				ele = driver.findElementsByLinkText(locator[1]);
			} else if(locator[0].equalsIgnoreCase("tagName")){
				ele = driver.findElementsByTagName(locator[1]);
			} 				
		}catch(Exception e){
			log("Error: Element not found or Selection type - Not matched");
			throw e;
		}
		return ele;
	}
	/**
	 *  @Author :SANJU
	 */
	public static void doubleTap(AppiumDriver driver,String identifier,int ar,Double tapCount) throws Exception{
		List<WebElement> ele = getWebElements(driver, identifier);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		Dimension screenSize = ele.get(ar).getSize();
		Double screenWidth = Double.valueOf(String.valueOf(screenSize.getWidth()));
		Double screenHeight = Double.valueOf(String.valueOf(screenSize.getHeight()));
		swipeObject.put("x", screenWidth+1); // pixels from left
		swipeObject.put("y", screenHeight+1); // pixels from top
		swipeObject.put("tapCount", tapCount);
		js.executeScript("mobile: tap", swipeObject);
		waitTill(3000);
	}
	/**
	 *  @Author :SANJU
	 */
	public static WebElement getWebElement(AppiumDriver driver,String identifier) {
		String[] locator = identifier.split("==");
		WebElement ele = null;
		try{
			if(locator[0].equalsIgnoreCase("class")){
				ele = driver.findElementByClassName(locator[1]);
			} else if(locator[0].equalsIgnoreCase("xpath")){
				ele = driver.findElementByXPath(locator[1]);
			} else if(locator[0].equalsIgnoreCase("name")){
				ele = driver.findElementByName(locator[1]);
			} else if(locator[0].equalsIgnoreCase("id")){
				ele = driver.findElementById(locator[1]);
			} else if(locator[0].equalsIgnoreCase("cssSelelector")){
				ele = driver.findElementByCssSelector(locator[1]);
			} else if(locator[0].equalsIgnoreCase("linkText")){
				ele = driver.findElementByLinkText(locator[1]);
			} else if(locator[0].equalsIgnoreCase("tagName")){
				ele = driver.findElementByTagName(locator[1]);
			} 				
		}catch(Exception e){
			log("Error: Element not found or Selection type - Not matched");	
		}
		return ele;
	}

	/**
	 *  @Author :SANJU
	 */
	 public static int getRandomIntwithinRange(int low, int high){
			Random random = new Random();
		    int randomNum = random.nextInt((high - low) + 1) + low;
		    return randomNum;
		}
	 	/**
		 *  @Author :SANJU
		 */
	 public static boolean verifyElementPresent(AppiumDriver driver,String identifier){
			Boolean present = false;
			try{
				WebElement ele = getWebElement(driver, identifier);
				present = ele.isDisplayed();
			}catch(Exception e){
				present = false;
			}
			return present;
		}
	 	/**
		 *  @Author :SANJU
		 */
	 public static void sendKeys(AppiumDriver driver,String identifier,String input) throws Exception{
			try{
				WebElement ele = getWebElement(driver, identifier);
				ele.sendKeys(input);
//				driver.hideKeyboard();
			}catch(Exception e){
				throw e;
			}
		}
	 	/**
		 *  @Author :SANJU
		 */
		public static void sendKeys(AppiumDriver driver,String identifier,Keys key) throws Exception{
			try{
				WebElement ele = getWebElement(driver, identifier);
				ele.sendKeys(key);
				driver.hideKeyboard();
			}catch(Exception e){
				throw e;
			}
		}
		
		/**
		 * @Author :SANJU
		 */
		public static void click(AppiumDriver driver, String identifier) throws Exception{
			String[] locator = identifier.split("==");
			try{
				if(locator[0].equalsIgnoreCase("class")){
					driver.findElementByClassName(locator[1]).click();
				} else if(locator[0].equalsIgnoreCase("xpath")){
					driver.findElementByXPath(locator[1]).click();
				} else if(locator[0].equalsIgnoreCase("name")){
					driver.findElementByName(locator[1]).click();
				} else if(locator[0].equalsIgnoreCase("id")){
					driver.findElementById(locator[1]).click();
				} else if(locator[0].equalsIgnoreCase("cssSlelector")){
					driver.findElementByCssSelector(locator[1]).click();
				} else if(locator[0].equalsIgnoreCase("linkText")){
					driver.findElementByLinkText(locator[1]).click();
				} else if(locator[0].equalsIgnoreCase("tagName")){
					driver.findElementByTagName(locator[1]).click();
				} 				
			}catch(Exception e){
				log("Error: Element not found or Selection type - Not matched");	
			}
		}
		/**
		 *  @Author :SANJU
		 */
		public static boolean findElementBy(AppiumDriver driver, String identifier) throws Exception{
			String[] locator = identifier.split("==");
			boolean elementDisplayed = true;
			try{
				if(locator[0].equalsIgnoreCase("class")){
					elementDisplayed = driver.findElementByClassName(locator[1]).isDisplayed();
				} else if(locator[0].equalsIgnoreCase("xpath")){
					elementDisplayed = driver.findElementByXPath(locator[1]).isDisplayed();
				} else if(locator[0].equalsIgnoreCase("name")){
					elementDisplayed = driver.findElementByName(locator[1]).isDisplayed();
				} else if(locator[0].equalsIgnoreCase("id")){
					elementDisplayed = driver.findElementById(locator[1]).isDisplayed();
				} else if(locator[0].equalsIgnoreCase("cssSlelector")){
					elementDisplayed = driver.findElementByCssSelector(locator[1]).isDisplayed();
				} else if(locator[0].equalsIgnoreCase("linkText")){
					elementDisplayed = driver.findElementByLinkText(locator[1]).isDisplayed();
				} else if(locator[0].equalsIgnoreCase("tagName")){
					elementDisplayed = driver.findElementByTagName(locator[1]).isDisplayed();
				} 				
			}catch(Exception e){
				log("Error: Element not found or Selection type - Not matched");	
			}
			return elementDisplayed;
		}
		/**
		 *  @Author :SANJU
		 */
//		public static void scrollToElement(AppiumDriver driver, String identifier){
//			WebElement element = getWebElement(driver, identifier);
//			HashMap<String, String> arguments = new HashMap<String, String>();
//			arguments.put("element", element.getId());
//			(JavascriptExecutor)driver.executeScript("mobile: scrollTo", arguments);
//		}
		public static void swipeScreenRight(AppiumDriver driver, int repeat) throws InterruptedException{
			driver.context("NATIVE_APP");
		    Dimension size = driver.manage().window().getSize();
		    int endX = (int)(size.width * 0.09);
		    int startX = (int)(size.width * 0.80);
		    int startY = size.height/2;
		    for(int i=1;i<=repeat;i++){
		    driver.swipe(startX, startY, endX, startY, 3000);
		    waitTill(3000);
		    }
		}
		public static void swipeScreenUp(AppiumDriver driver, int repeat) throws InterruptedException{
			driver.context("NATIVE_APP");
		    Dimension size = driver.manage().window().getSize();
		//    int y = (int)(size.height);
		    int startY= (int)(size.height * 0.28);
		    int endY = (int)(size.height * 0.80);
		    int startX = size.width/2;
		    for(int i=1;i<=repeat;i++){
		    driver.swipe(startX, startY, startX, endY, 3000);
		    waitTill(3000);
		    }
		}
}

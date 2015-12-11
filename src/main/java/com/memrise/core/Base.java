package com.memrise.core;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import static com.memrise.core.GlobalConstants.ApkFileNameAndPath;

public class Base {

public AppiumDriver driver;
	
	@BeforeMethod
	public void setUp() throws Exception{
		File app = new File(ApkFileNameAndPath);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName", "appium");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName","Android");
	    capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("appPackage", "com.memrise.android.memrisecompanion");
	    capabilities.setCapability("appActivity", "com.memrise.android.memrisecompanion.ui.activity.LauncherActivity");
	    driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown() throws Exception{
		driver.quit();
	}
}

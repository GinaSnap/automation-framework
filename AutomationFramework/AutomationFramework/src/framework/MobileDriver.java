package framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class MobileDriver extends AbstractDriver {
	
	public static AppiumDriver<MobileElement> instance;
	
	public static void init()
	{
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("automationName", "XCUITest");
		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformVersion", "11.2");
		caps.setCapability("deviceName", "iPhone Simulator");
//		caps.setCapability("xcodeOrgId", "RDH5T872HA");
//		caps.setCapability("xcodeSigningId", "iPhone Developer");
//		caps.setCapability("bundleId","com.snapkitchen.a-SnapKitchen");
		caps.setCapability("app", "/Users/GMitchell/Library/Developer/Xcode/DerivedData/SnapKitchen-cyadazvcvfvecxbxhinebnhduluu/Build/Products/Debug-iphonesimulator/Snap Kitchen.app");
		
		//caps.setCapability("udid", "ENUL6303030010"); //Give Device ID of your mobile phone
		//caps.setCapability("appPackage", "com.android.vending");
		//caps.setCapability("appActivity", "com.google.android.finsky.activities.MainActivity");
		//caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
				//mobile_instance = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
				instance = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
				instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void quit()
	{
		instance.quit();
	}
	
	public static void navigate(String url)
	{
		instance.get(url);
	}


}

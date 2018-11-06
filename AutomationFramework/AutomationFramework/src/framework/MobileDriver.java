package framework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileDriver extends AbstractDriver {
	
	public static AppiumDriver<MobileElement> instance;
	public final static Logger LOGGER = Logger.getLogger("TestLogger");
	
	public static void init()
	{
		initializeLogger();
		
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("automationName", "XCUITest");
		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformVersion", "12.0");
		caps.setCapability("deviceName", "iPhone SE");
		caps.setCapability("app", "/Users/GMitchell/Library/Developer/Xcode/DerivedData/SnapKitchen-afugxgdjzpapsoaulupvxhadeojz/Build/Products/Debug-iphonesimulator/Snap Kitchen.app");
		
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

	/**
	 * 
	 */
	private static void initializeLogger()
	{
		LOGGER.setLevel(Level.INFO);

        FileHandler fileTxt;
		try {
			fileTxt = new FileHandler("MobileTest.log");
	        SimpleFormatter formatterTxt = new SimpleFormatter();
	        fileTxt.setFormatter(formatterTxt);
	        LOGGER.addHandler(fileTxt);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

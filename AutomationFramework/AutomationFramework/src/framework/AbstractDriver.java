package framework;

public abstract class AbstractDriver {
	
//	public static WebDriver instance;
//	public static AppiumDriver<MobileElement> mobile_instance;
//	
//	//public static final String RootURL = "http://www.snapkitchen.com";
//	public static final String RootURL = "http://www-stage.snapkitchen.com";
	
//	public static void init()
//	{
//		
//		System.setProperty("webdriver.gecko.driver", "/Users/GMitchell/AutomationFramework/Browsers/geckodriver");
//		instance=new FirefoxDriver();
//		instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//	}
//	
//	public static void mobile_init()
//	{
//		//Set the Desired Capabilities
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("automationName", "XCUITest");
//		caps.setCapability("platformName", "iOS");
//		caps.setCapability("platformVersion", "11.2");
//		caps.setCapability("deviceName", "iPhone Simulator");
////		caps.setCapability("xcodeOrgId", "RDH5T872HA");
////		caps.setCapability("xcodeSigningId", "iPhone Developer");
////		caps.setCapability("bundleId","com.snapkitchen.a-SnapKitchen");
//		caps.setCapability("app", "/Users/GMitchell/Library/Developer/Xcode/DerivedData/SnapKitchen-cyadazvcvfvecxbxhinebnhduluu/Build/Products/Debug-iphonesimulator/Snap Kitchen.app");
//		
//		//caps.setCapability("udid", "ENUL6303030010"); //Give Device ID of your mobile phone
//		//caps.setCapability("appPackage", "com.android.vending");
//		//caps.setCapability("appActivity", "com.google.android.finsky.activities.MainActivity");
//		//caps.setCapability("noReset", "true");
//		
//		//Instantiate Appium Driver
//		try {
//				//mobile_instance = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
//				mobile_instance = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
//				mobile_instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			
//		} catch (MalformedURLException e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
	
//	public abstract void init();
	
//	public static void quit()
//	{
//		instance.quit();
//	}
	
//	public abstract void quit();
	
//	public static void mobile_quit() 
//	{
//		mobile_instance.quit();
//	}
	
//	public abstract void navigate(String url);
	
//	public static void navigate(String url)
//	{
//		instance.get(url);
//	}
	
	public static void pause(long millis)
	{
		try 
		{
			Thread.sleep(millis);
		}
		catch (InterruptedException e)
		{
			//
		}
	}
	
}

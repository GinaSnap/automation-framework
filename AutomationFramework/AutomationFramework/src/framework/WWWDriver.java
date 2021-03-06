package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WWWDriver extends AbstractDriver {
	
	public static WebDriver instance;
	//public static final String RootURL = "http://www.snapkitchen.com";
	public static final String RootURL = "http://www-stage.snapkitchen.com";
	//public static final String RootURL = "http://www-stage.snapkitchen.com/?code=qa_oneline";
	public static final String SUCCESS = "Success";
	
	public static void init()
	{
		
//		System.setProperty("webdriver.gecko.driver", "/Users/GMitchell/AutomationFramework/Browsers/geckodriver");
//		instance=new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", "/Users/GMitchell/AutomationFramework/Browsers/chromedriver");
		instance=new ChromeDriver();

		instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

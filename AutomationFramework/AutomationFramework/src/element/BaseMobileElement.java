package element;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.FindMethod;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class BaseMobileElement {
	private String id;
	private FindMethod findMethod;
	
	public BaseMobileElement(String id)
	{
		this.id = id;
		this.findMethod = FindMethod.ACCESS_ID;
	}
	
	public BaseMobileElement(FindMethod findMethod, String id)
	{
		this.id = id;
		this.findMethod = findMethod;
	}
	
	public MobileElement getMobileElement()
	{
		switch (findMethod)
		{
		case ACCESS_ID:
			return MobileDriver.instance.findElementByAccessibilityId(id);
		case XPATH:
			return MobileDriver.instance.findElementByXPath(id);
		default:
			return MobileDriver.instance.findElementByAccessibilityId(id);
		}
		
	}
		
	public MobileElement getMobileElement(boolean throwException)
	{
		try
		{
			return getMobileElement();
		}
		catch (Exception e)
		{
			if (throwException)
			{
				throw e;
			}
		}
		return null;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void click()
	{
		scroll("d");
		getMobileElement().click();
	}
	
	public void setWebValue(String text)
	{
		MobileElement textElement = getMobileElement();
		textElement.clear();
		textElement.sendKeys(text);
	}
	
	public String getText()
	{
		return getMobileElement().getText();
	}
	
	public boolean exists()
	{
		try {
			scroll("d");
			return getMobileElement().isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isEnabled()
	{
		try {
			return getMobileElement().isEnabled();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
		
	/**
	 * Wait for up to 60 seconds for an element to be visible.
	 * The driver will automatically wait 10 seconds for an element.  
	 * Use this method for individual cases where a longer wait is needed.
	 */
	public void waitUntilClickable()
	{
		WebDriverWait wait = new WebDriverWait(MobileDriver.instance, 60);
		wait.until(ExpectedConditions.elementToBeClickable(getMobileElement()));
	}
	
	/**
	 * Scroll in in the requested direction.
	 * @param direction Enter d (down), u (up), l (left), r (right).
	 */
	public void scroll(String direction)
	{
		if (!getMobileElement().isDisplayed())
		{			
			try {
	            JavascriptExecutor js = (JavascriptExecutor) MobileDriver.instance;
	            HashMap<String, String> swipeObject = new HashMap<String, String>();
	            if (direction.equals("d")) {
	                swipeObject.put("direction", "down");
	            } else if (direction.equals("u")) {
	                swipeObject.put("direction", "up");
	            } else if (direction.equals("l")) {
	                swipeObject.put("direction", "left");
	            } else if (direction.equals("r")) {
	                swipeObject.put("direction", "right");
	            }
	            //swipeObject.put("element", ((RemoteWebElement) getMobileElement()).getId());
	            js.executeScript("mobile: scroll", swipeObject);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		}
	}
}

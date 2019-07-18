package element;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
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
			return MobileDriver.instance.findElement(By.xpath(id));
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
		//displayElement();
		if (!getMobileElement().isDisplayed())
		{
			scrollToElement();
		}
		getMobileElement().click();
	}
	
	public void clickOld()
	{
		boolean scrollDown=false;
		boolean scrollLeft=true;
		boolean scrollRight=true;
		boolean scrollUp=false;
		
		while (!(isClicked()) && (!scrollDown || !scrollUp || !scrollLeft || !scrollRight))
		{
			if (!scrollDown)
			{
				scroll("d");
				scrollDown=true;
				continue;
			}
			if (!scrollUp)
			{
				scroll("u");
				scrollUp=true;
				continue;
			}
			if (!scrollLeft)
			{
				scroll("l");
				scrollLeft=true;
				continue;
			}
			if (!scrollRight)
			{
				scroll("r");
				scrollRight=true;
				continue;
			}
		}
	}
	
	private boolean isClicked()
	{
		try
		{
			getMobileElement().click();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		return true;
	}
	
	private boolean displayElementNew()
	{
		boolean scrollDown=false;
		boolean scrollLeft=true;
		boolean scrollRight=true;
		boolean scrollUp=false;
		while (!(getMobileElement().isDisplayed()) && (!scrollDown || !scrollUp || !scrollLeft || !scrollRight))
		{
			if (!scrollDown)
			{
				System.out.println("Scrolling down.");
				swipeToDirection(getMobileElement(), "d");
				scrollDown=true;
				continue;
			}
			if (!scrollUp)
			{
				System.out.println("Scrolling up.");
				swipeToDirection(getMobileElement(), "u");
				scrollUp=true;
				continue;
			}
			if (!scrollLeft)
			{
				System.out.println("Scrolling left.");
				swipeToDirection(getMobileElement(), "l");
				scrollLeft=true;
				continue;
			}
			if (!scrollRight)
			{
				System.out.println("Scrolling right.");
				swipeToDirection(getMobileElement(), "r");
				scrollRight=true;
				continue;
			}
		}
		return getMobileElement().isDisplayed();
	}
	
	private boolean displayElement()
	{
		boolean scrollDown=false;
		boolean scrollLeft=true;
		boolean scrollRight=true;
		boolean scrollUp=false;
		while (!(getMobileElement().isDisplayed()) && (!scrollDown || !scrollUp || !scrollLeft || !scrollRight))
		{
			if (!scrollDown)
			{
				scroll("d");
				scrollDown=true;
				continue;
			}
			if (!scrollUp)
			{
				scroll("u");
				scrollUp=true;
				continue;
			}
			if (!scrollLeft)
			{
				scroll("l");
				scrollLeft=true;
				continue;
			}
			if (!scrollRight)
			{
				scroll("r");
				scrollRight=true;
				continue;
			}
		}
		return getMobileElement().isDisplayed();
	}
	
	private boolean scrollToElement()
	{
		HashMap<String, String> swipeObject = new HashMap<String, String>();
		swipeObject.put("name", getId());
		MobileDriver.instance.executeScript("mobile: scroll", swipeObject);
		return true;
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
	
	/**
	 * Return true if the element exists anywhere on the screen.
	 * @return 
	 */
	public boolean exists()
	{
		//Search for the element anywhere on the screen and scroll to it.
		try {
			scrollToElement();
		}
		catch (WebDriverException e) {
			//We get this if we used anything other than the accessibility id to find the element.
			//Ignore for now.
		}
		
		//If the element was already on the screen, or if we were able to scroll to it, then return true.
		try {
				return getMobileElement().isEnabled() || getMobileElement().isDisplayed();
			}
				catch (NoSuchElementException e) {
					return false;
			}
				catch (WebDriverException e) {
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
	
	public boolean isVisible()
	{
		try {
			return getMobileElement().getAttribute("visible").equals("true");
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
	
	// iOS scroll by object
    public boolean swipeToDirection(MobileElement el, String direction) {
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
            swipeObject.put("element", el.getId());
            js.executeScript("mobile:swipe", swipeObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollToDirection(MobileElement el, String direction) {
        // The main difference from swipe call with the same argument is that scroll will try to move
        // the current viewport exactly to the next/previous page (the term "page" means the content,
        // which fits into a single device screen)
        try {
            JavascriptExecutor js = (JavascriptExecutor) MobileDriver.instance;
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            if (direction.equals("d")) {
                scrollObject.put("direction", "down");
            } else if (direction.equals("u")) {
                scrollObject.put("direction", "up");
            } else if (direction.equals("l")) {
                scrollObject.put("direction", "left");
            } else if (direction.equals("r")) {
                scrollObject.put("direction", "right");
            }
            scrollObject.put("element", el.getId());
            scrollObject.put("toVisible", "true"); // optional but needed sometimes
            js.executeScript("mobile:scroll", scrollObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

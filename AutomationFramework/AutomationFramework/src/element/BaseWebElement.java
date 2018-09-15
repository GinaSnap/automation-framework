package element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.FindMethod;
import framework.WWWDriver;

public class BaseWebElement {
	private String id;
	private FindMethod findMethod;
	
	public BaseWebElement(String id)
	{
		this.id = id;
		findMethod = FindMethod.ID;
	}
	
	public BaseWebElement(FindMethod findMethod, String id)
	{
		this.id=id;
		this.findMethod = findMethod;
	}
	
	
	public WebElement getWebElement()
	{
		return WWWDriver.instance.findElement(getBy());
	}
	
	/**
	 * This will allow you to ignore any exceptions thrown
	 * When trying to find a particular web element.  This is useful if the
	 * existence of this element does not affect the functioning of the test.
	 * @param throwException Enter false if you wish to ignore the exception.
	 * @return
	 */
	public WebElement getWebElement(boolean throwException)
	{
		try
		{
			return getWebElement();
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
	
	/**
	 * Return the By that findElement will use to locate the element
	 * based upon the Find Method used to define the element on the page.
	 * @return
	 */
	private By getBy()
	{
		switch (findMethod)
		{
		case ID:
			return By.id(getId());
		case CLASSNAME:
			return By.className(getId());
		case NAME:
			return By.name(getId());
		case LINKTEXT:
			return By.linkText(getId());
		case XPATH:
			return By.xpath(getId());
		default:
			return By.id(getId());
		}
	}
	
	/**
	 * Return the id value that defines the element in the page.  
	 * This may be the class name attribute, the name attribute, or the xpath.
	 * @return
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * If I need to explain this one, then give up and go home.
	 */
	public void click()
	{
		getWebElement().click();
	}
	
	/**
	 * Clear the current value in the element, and enter the text provided.
	 * @param text Enter the text you wish to enter into the text field.
	 */
	public void setWebValue(String text)
	{
		getWebElement().clear();
		getWebElement().sendKeys(text);
	}
	
	/**
	 * Select a value from a drop down list.
	 * @param text Enter the "value" attribute of the option you wish to select.
	 */
	public void selectValue(String text)
	{
		
		//Display the elements
		getWebElement().click();
		
		//Find the desired Element
		List<WebElement> listOptionElements = getWebElement().findElements(By.tagName("option"));
		for (WebElement optionElement : listOptionElements)
		{
			if (optionElement.getAttribute("value").equals(text))
			{
				optionElement.click();
			}
		}
		
	}
	
	/**
	 * I need to determine when we use getText() and when we use getValue()
	 * ie for which element types on the web.
	 * @return
	 */
	public String getText()
	{
		return getWebElement().getText();
	}
	
	/**
	 * Return the value of a text input
	 * @return Returns the value attribute of the element.
	 */
	public String getValue()
	{
		return getWebElement().getAttribute("value");
	}
	
	/**
	 * Return true if the given element is visible on the screen.
	 */
	public boolean exists()
	{
		long startTimeMilli = System.currentTimeMillis();
		long endTimeMilli = System.currentTimeMillis();
		
		while ((endTimeMilli - startTimeMilli) < 5000)
		{
			try 
			{
				return getWebElement().isDisplayed();
			}
			catch (NoSuchElementException e) 
			{
				return false;
			}
			catch (StaleElementReferenceException e)
			{
				//If this happens it just means that the element changed and we need to re-attach.
			}
		}
		
		//The only time I should return true is if isDisplayedReturns true.
		return false;
	}
	
	/**
	 * Return true if the given element is enabled such that I can interact (click, enter text).
	 */
	public boolean isEnabled()
	{
		try {
			return getWebElement().isEnabled();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	/**
	 * Wait up to 10 seconds until an element is accessible.
	 * Note that the driver will always wait up to 5 seconds for an element 
	 * to be accessible.  Use this only if additional time is needed for processing.
	 */
	public void waitUntilClickable()
	{
		WebDriverWait wait = new WebDriverWait(WWWDriver.instance, 10);
		wait.until(ExpectedConditions.elementToBeClickable(getWebElement()));
	}
	
	/**
	 * The buttons on the login screen change text while 
	 * processing the login information.  This function will wait
	 * for verification to be complete.
	 * 
	 * @param waitForProcessing Enter true if we should wait for the button
	 * text to change back to the original value.
	 */
	public void click(boolean waitForProcessing)
	{
		String textOriginal = getWebElement().getText();
		long timeInMillis = System.currentTimeMillis();
		click();
		if (waitForProcessing)
		{
			try {
				while (!getWebElement().getText().equals(textOriginal) && ((System.currentTimeMillis() - timeInMillis) < 5000));
				{
					Thread.sleep(500);
				} 
			}
			catch (Exception e)
			{
				return;
			}
		}
	}
}

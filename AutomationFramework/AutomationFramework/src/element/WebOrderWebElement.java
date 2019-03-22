package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.FindMethod;

public class WebOrderWebElement extends BaseWebElement {

	public WebOrderWebElement(FindMethod findMethod, String id) {
		super(findMethod, id);
	}
	
	/**
	 * Returns the month value for the order scheduled date.
	 * @return
	 */
	public String getMonth()
	{
		try
		{
			return getWebElement().findElement(By.className("calendar-page-title")).getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Returns the day value for the order scheduled date.
	 * @return
	 */
	public String getDay()
	{
		try
		{
			return getWebElement().findElement(By.className("calendar-page-date")).getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Returns the year value for the order scheduled date.
	 * @return
	 */
	public String getYear()
	{
		try
		{
			return getWebElement().findElement(By.className("calendar-page-year")).getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Returns the fulfillment value (pickup from <store> OR delivery to <something>)
	 * @return
	 */
	public String getOrderFulfillment()
	{
		try
		{
			return getWebElement().findElement(By.className("order-title")).getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Returns the order number
	 * @return
	 */
	public String getOrderNumber()
	{
		try
		{
			return getWebElement().findElement(By.className("order-number")).getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Clicks the view button to load the order detail.
	 * @return Returns true if we were able to find a click the view button.
	 */
	public boolean clickView()
	{
		String xPath = "//button[text()='view']";
		
		try
		{
			WebElement viewElement = getWebElement().findElement(By.xpath(xPath));
			viewElement.click();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	
}

package element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.FindMethod;
import framework.WWWDriver;

public class WebFullfillmentDetailsElement extends BaseWebElement {

	public WebFullfillmentDetailsElement(FindMethod findMethod, String id) {
		super(findMethod, id);
	}
	
	public WebFullfillmentDetailsElement(String id) {
		super(id);
	}
	
	public String setPickupDate()
	{
		return "";
	}
	
	public String getPickupDate()
	{
		return "";
	}
	
	public String setPickupTime()
	{
		return "";
	}
	
	public String getPickupTime()
	{
		return "";
	}

	public String clickSave()
	{
		WWWDriver.pause(5000);
		//String xPath = "//div[contains(@class,'component-select-date-time-modal')]/div[@class='component-modal']/div[@class='component-modal--scrollable']/div[@class='component-modal--inner']/div[@class='sts__content']/button[@class='save-button']";
		String xPath = "//*[@id='scrollable']/div/div/button";
		try {
			WebElement saveElement = getWebElement().findElement(By.xpath(xPath));
			Actions actions = new Actions(WWWDriver.instance);
			actions.moveToElement(saveElement);
			saveElement.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find save button under fulfillment details.";
		}
		
		return "Success";
	}
	
}

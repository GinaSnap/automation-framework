package element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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
		try {
			WebElement saveElement = WWWDriver.instance.findElement(By.xpath("//button[text()='Save']"));
			WebDriverWait wait = new WebDriverWait(WWWDriver.instance, 10);
			wait.until(ExpectedConditions.elementToBeClickable(saveElement));
			saveElement.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find save button under fulfillment details.";
		}
		
		return "Success";
	}
	
}

package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.FindMethod;
import framework.WWWDriver;

public class WebMealPlanCardElement extends BaseWebElement {

	public WebMealPlanCardElement(FindMethod findMethod, String id) {
		super(findMethod, id);
	}

	public String clickOrderNow()
	{
		try
		{
			String xPath = "//div[contains(@class,'card-balance')]/div/div/a";
			WebElement buttonElement = WWWDriver.instance.findElement(By.xpath(xPath));
			buttonElement.click();
		}
		catch(Exception e)
		{
			return "Could not find Order Element on Meal Plan Card.";
		}
		return "Success";
	}
	
	public boolean exists()
	{
		try
		{
			return getWebElement().isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
}

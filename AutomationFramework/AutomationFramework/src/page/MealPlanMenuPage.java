package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import framework.FindMethod;

public class MealPlanMenuPage extends BasePage {
	
	private BaseWebElement checkoutButton = new BaseWebElement(FindMethod.CLASSNAME, "component-live-pricing-button");
	
	public String clickCheckout()
	{
		try
		{
			checkoutButton.click();
		}
		catch (NoSuchElementException e)
		{
			return "Checkout button was not displayed.";
		}
		return "Success";
	}

}

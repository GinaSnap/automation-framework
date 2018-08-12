package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import framework.FindMethod;

public class FulfillmentPage extends BasePage {
	
	private BaseWebElement closeX = new BaseWebElement(FindMethod.CLASSNAME, "component-modal--close");
	private BaseWebElement continueToCheckout = new BaseWebElement(FindMethod.XPATH, "//button[text()='continue to checkout']");

	public String closeWindow()
	{
		try
		{
			closeX.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the close button in the upper left corner.";
		}
		return "Success";
	}
	
	public String clickContinueToCheckout()
	{
		try
		{
			continueToCheckout.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Continue to Checkout button.";
		}
		return "Success";
	}
}

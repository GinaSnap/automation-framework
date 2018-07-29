package page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import framework.FindMethod;

public class CheckoutPage extends BasePage {
	
	private final BaseWebElement placeOrder = new BaseWebElement(FindMethod.CLASSNAME, "place-order-button");
	
	public String placeOrder()
	{
		String status = "Success";
		try
		{
			placeOrder.waitUntilClickable();
			placeOrder.click();
			waitForPageLoadingIndicator();
		}
		catch (NoSuchElementException e)
		{
			status="Place Order Button does not exist.";
		}
		
		return status;
	}

}

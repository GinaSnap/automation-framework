package page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import element.BaseWebElement;
import framework.FindMethod;

public class CheckoutPage extends BasePage {
	
	private final BaseWebElement placeOrder = new BaseWebElement(FindMethod.CLASSNAME, "place-order-button");
	private final BaseWebElement orderNumText = new BaseWebElement(FindMethod.XPATH,"//div[@class='order-status-page order-page']/h1");
	
	public String placeOrder()
	{
		String status = "Success";
		try
		{
			placeOrder.waitUntilClickable();
			placeOrder.click();
			waitForOrderToComplete();
		}
		catch (NoSuchElementException e)
		{
			status="Place Order Button does not exist.";
		}
		
		return status;
	}
	
	private boolean waitForOrderToComplete()
	{
		try
		{
			orderNumText.waitUntilTextContains("Order #");
			return true;
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}
	
	public String getOrderNumber()
	{
		try
		{
			return orderNumText.getText();
		}
		catch (NoSuchElementException e)
		{
			return "";
		}
	}

}

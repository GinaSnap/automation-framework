package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import element.MobileProductCardWebElement;
import framework.FindMethod;

public class BasketPage extends BasePage {

	public final BaseMobileElement delivery = new BaseMobileElement("DELIVERY");
	public final BaseMobileElement pickUp = new BaseMobileElement("PICKUP");
	public final BaseMobileElement goToCheckOut = new BaseMobileElement("GO TO CHECKOUT");
	
	/**
	 * Go to the Checkout Screen from the Shopping Cart page.
	 * @return 
	 * Returns "Success" if the user is able to click on the Checkout Link.
	 * Returns an error string if not.
	 */
	public String goToCheckOut()
	{
		String status="Success";
		try
		{
			goToCheckOut.click();
		}
		catch (NoSuchElementException e)
		{
			status="Go To Checkout Link is not displayed.";
			return status;
		}
		return status;
	}
	
	public boolean menuItemExists(String expectedProductName)
	{
		boolean status = false;
		try
		{
			MobileProductCardWebElement basketElement = new MobileProductCardWebElement(FindMethod.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[5]");
			if (basketElement.getProductName().endsWith(expectedProductName))
			{
				status=true;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return status;
	}


}

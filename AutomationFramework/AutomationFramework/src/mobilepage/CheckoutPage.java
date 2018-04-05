package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class CheckoutPage extends BasePage {
	
	private final int SNAP_LOCATION = 1;
	private final int FULFILLMENT_LOCATION = 2;
	private final int FULFILLMENT_TIME = 3;
	private final int PAYMENT = 4;
	private final int REPLACEMENT = 5;
	
	public final BaseMobileElement header = new BaseMobileElement("Header");
	public final BaseMobileElement close = new BaseMobileElement("Close");
	public final BaseMobileElement placeOrder = new BaseMobileElement("PLACE ORDER");
	
	private MobileElement getCheckoutTable()
	{
		return MobileDriver.instance.findElementByXPath("//XCUIElementTypeTable");
	}
	
	/**
	 * Click Place Order from the Checkout Screen.
	 * @return
	 * Returns "Success" if the user was able to click the link.
	 * Returns an error String if not.
	 */
	public String placeOrder()
	{
		String status="Success";
		try {
			placeOrder.click();
		}
		catch (NoSuchElementException e)
		{
			status="Place Order Button was not displayed.";
			return status;
		}
		
		//For each session the user is prompted whether they would like to receive notifications from SnapKitchen.
		try
		{
			OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
			orderSubmissionPage.allowNotifications();
		}
		catch (Exception e)
		{
			//I'm not sure if this is a must.  For now, if it's there we'll deal with it, and just ignore if not.
		}
		return status;
		
	}

}

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
	
	private final BaseMobileElement paymentMethods = new BaseMobileElement("payment");
	private final BaseMobileElement addNewCard = new BaseMobileElement("Add New Card…");
	private final BaseMobileElement card_Number = new BaseMobileElement("card number");
	private final BaseMobileElement card_ExpireDate = new BaseMobileElement("expiration date");
	private final BaseMobileElement card_CVC = new BaseMobileElement("CVC");
	private final BaseMobileElement card_Cancel = new BaseMobileElement("Cancel");
	private final BaseMobileElement card_Done = new BaseMobileElement("Done");
	
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

		return status;
		
	}
	
	/**
	 * Click Payments from the Checkout Page.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Payments.
	 * Return error string if not.
	 */
	public String goToPayments()
	{
		String status="Success";
		
		try {
			paymentMethods.click();
		}
		catch (NoSuchElementException e)
		{
			status="Payments button is not displayed.";
			return status;
		}
		
		return status;
	}
	
	public String addPaymentMethod(String cardNumber, String date, String cvc)
	{
		goToPayments();
		try 
		{
			addNewCard.waitUntilClickable();
			addNewCard.click();
			card_Number.setWebValue(cardNumber);
			card_ExpireDate.setWebValue(date);
			card_CVC.setWebValue(cvc);
			card_Done.click();
		}
		catch (NoSuchElementException e)
		{
			return "Error";
		}
	
		return "Success";
	}

}

package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import framework.FindMethod;

public class BasketPage extends BasePage {
	
	private final BaseWebElement deliveryTab = new BaseWebElement(FindMethod.CLASSNAME, "order-type--delivery");
	private final BaseWebElement pickupTab = new BaseWebElement(FindMethod.CLASSNAME, "order-type--pickup");
	private final BaseWebElement fulfillmentDetails = new BaseWebElement(FindMethod.CLASSNAME, "fulfillment-details");
	private final BaseWebElement emptyBasket = new BaseWebElement(FindMethod.CLASSNAME, "empty-basket-icon");
	private final BaseWebElement goToCheckout = new BaseWebElement(FindMethod.CLASSNAME, "proceed-to-checkout-button");
	
	public String getFulfillmentDetails()
	{
		//fulfillment-details-content
			//header-title
			//header-address
		return "";
	}
	
	public String clickDeliveryTab()
	{
		return "";
	}
	
	public String clickPickupTab()
	{
		return "";
	}
	
	public String emptyShoppingBasket()
	{
		String status="Success";
		try
		{
			emptyBasket.click();
		}
		catch (NoSuchElementException e)
		{
			return "Empty Shopping Basket was not displayed.";
		}
		return status;
	}
	
	public String goToCheckout()
	{
		String status = "Success";
		try
		{
			goToCheckout.click();
			waitForPageLoadingIndicator();
		}
		catch (NoSuchElementException e)
		{
			status="Checkout button was not displayed.";
		}
		return status;
	}
	
	public int getNumItemsInCart()
	{
		return -1;
	}
	
	public boolean itemExists(String productName)
	{
		return false;
	}

	
	//Basket items
	//component-basket-item-lists 
		//basket-item-list-section
			//basket-items-section-title  "Available today"
		//component-list-basket-items 
			//component-basket-item 
				//basket-item-photo  get alt attribute for help text
				//product-info-container
					//product-info
						//product-name
						//product-variant-info
					//product-purchase-info
						//select element 'quantity'
						//product purchase price
	

}

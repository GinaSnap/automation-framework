package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import element.MobileProductCardWebElement;
import framework.FindMethod;

public class BasketPage extends BasePage {

	private final BaseMobileElement delivery = new BaseMobileElement("DELIVERY");
	private final BaseMobileElement pickUp = new BaseMobileElement("PICKUP");
	private final BaseMobileElement goToCheckOut = new BaseMobileElement("GO TO CHECKOUT");
	private final BaseMobileElement emptyBasketMessage = new BaseMobileElement("the easiest way to shop healthy");
	private final BaseMobileElement shopMenu = new BaseMobileElement("SHOP MENU");
	private final BaseMobileElement addPromoCode = new BaseMobileElement("add promo code");
	private final BaseMobileElement emptyBasket = new BaseMobileElement("btn empty basket");
	private final BaseMobileElement emptyBasketPrompt = new BaseMobileElement("empty basket?");
	private final BaseMobileElement emptyBasketYes = new BaseMobileElement("yes");
	private final BaseMobileElement emptyBasketCancel = new BaseMobileElement("cancel");
	
	
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

	/**
	 * Returns True if the 'the easiest way to shop healthy' title
	 * and the shop menu button exists.
	 * @return
	 */
	public boolean isBasketEmpty()
	{
		//TODO:  This isn't a good enough test.
		return shopMenu.exists();
	}
	
	public boolean shopMenuButtonExists()
	{
		return shopMenu.exists();
	}
	
	public String shopMenu()
	{
		try
		{
			shopMenu.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Shop Menu Button.";
		}
		return "Success";
	}
	
	public String emptyBasket()
	{
		try
		{
			emptyBasket.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Empty Basket Button.";
		}
		return "Success";
	}
	
	public boolean emptyBasketPromptExists()
	{
		return emptyBasketPrompt.exists();
	}
	
	public String emptyBasketPrompt_ClickYes()
	{
		try
		{
			emptyBasketYes.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Yes button on empty basket confirmation.";
		}
		return "Success";
	}

}

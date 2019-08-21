package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;

public abstract class AbstractMenuPage {
	
	public BaseMobileElement sampleMenuText = new BaseMobileElement("sample menu");
	public BaseMobileElement signInToOrder = new BaseMobileElement("sign in to order");
	public BaseMobileElement letsGetStarted = new BaseMobileElement("GetStarted");
	public BaseMobileElement close = new BaseMobileElement("GetStarted");
	public BaseMobileElement shoppingBasket = new BaseMobileElement("icn basket white");

	public AbstractMenuPage() {
	}
	
	public abstract boolean isLoaded(boolean isLoggedIn);
	
	
	public String letsGetStarted()
	{
		try 
		{
			letsGetStarted.waitUntilClickable();
			letsGetStarted.click();
		}
		catch (NoSuchElementException e)
		{
			return "Let's Get Started Dialog was not displayed.";
		}
		return "Success";
	}
	
	public String goToShoppingBasket()
	{
		try
		{
			shoppingBasket.waitUntilClickable();
			shoppingBasket.click();
		}
		catch (NoSuchElementException e)
		{
			return "Shopping Basket Icon was not displayed.";
		}
		return "Success";
	}
	
	

}

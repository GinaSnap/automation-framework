package page;

import org.openqa.selenium.NoSuchElementException;

import common.PlanType;
import element.BaseMobileElement;
import mobilepage.BasePage;

public class ShippingMenuPage extends BasePage {
	
	private BaseMobileElement sampleMenuText = new BaseMobileElement("sample menu");
	private BaseMobileElement signInToOrder = new BaseMobileElement("sign in to order");
	private BaseMobileElement buildYourOwn = new BaseMobileElement("build");
	private BaseMobileElement balance = new BaseMobileElement("balance");
	private BaseMobileElement letsGetStarted = new BaseMobileElement("GetStarted");
	private BaseMobileElement close = new BaseMobileElement("GetStarted");
	private BaseMobileElement shoppingBasket = new BaseMobileElement("icn basket white");

	public ShippingMenuPage() {
	}
	
	public boolean isLoaded()
	{
		try
		{
			sampleMenuText.waitUntilClickable();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		return sampleMenuText.exists();
	}
	
	public String selectMealPlan(PlanType planType)
	{
		
		try 
		{
			switch (planType) {
			case BALANCE:
				balance.click();
				break;
			case BUILD_YOUR_OWN:
				buildYourOwn.click();
				break;
			default:
				break;
			}
		}
		catch (NoSuchElementException e)
		{
			return "Meal Plan to select was not displayed.";
		}
		return "Success";
	}
	
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

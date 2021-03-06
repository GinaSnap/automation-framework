package page;

import org.openqa.selenium.NoSuchElementException;

import common.PlanType;
import element.BaseMobileElement;
import mobilepage.BasePage;

public class ShippingMenuPage extends AbstractMenuPage {
	
	private BaseMobileElement buildYourOwn = new BaseMobileElement("build");
	private BaseMobileElement balance = new BaseMobileElement("balance");
	private BaseMobileElement findAPlanMade = new BaseMobileElement("find a plan made");
	private BaseMobileElement justForYou = new BaseMobileElement("just for you");
	private BaseMobileElement newMenu = new BaseMobileElement("new menu");

	

	public ShippingMenuPage() {
	}
	
	/**
	 * The Shipping Menu is loaded if
	 * 		The sample menu and 'sign in to order' is at the top
	 * 		Find a plan made just for you banner is displayed
	 * 		Balance Meal Plan card is displayed.
	 */
	public boolean isLoaded(boolean isLoggedIn)
	{
		boolean testIsLoaded;
		if (isLoggedIn) {
			testIsLoaded = !sampleMenuText.exists() && findAPlanMade.exists() && justForYou.exists() && balance.exists();
		} else {
			testIsLoaded = sampleMenuText.exists() && findAPlanMade.exists() && justForYou.exists() && balance.exists();

		}
		if (testIsLoaded) {
			return true;
		}
		return false;
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

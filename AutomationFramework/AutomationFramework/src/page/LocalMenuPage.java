package page;

import org.openqa.selenium.NoSuchElementException;

import common.PlanType;
import element.BaseMobileElement;

public class LocalMenuPage extends AbstractMenuPage {
	
	private BaseMobileElement buildYourOwn = new BaseMobileElement("build");
	private BaseMobileElement balance = new BaseMobileElement("balance");
	private BaseMobileElement findAPlanMade = new BaseMobileElement("find a plan made");
	private BaseMobileElement justForYou = new BaseMobileElement("just for you");
	private BaseMobileElement bestSellers = new BaseMobileElement("best sellers");
	private BaseMobileElement newMenu = new BaseMobileElement("new menu");
	

	public LocalMenuPage() {
	}
	
	/**
	 * The Local Menu is loaded if
	 * 		The sample menu and 'sign in to order' is at the top
	 * 		Find a plan made just for you banner is displayed
	 * 		Balance Meal Plan card is displayed.
	 */
	public boolean isLoaded(boolean isLoggedIn)
	{
		boolean testIsLoaded;
		
		if (isLoggedIn) {
			testIsLoaded = findAPlanMade.exists() && justForYou.exists() && bestSellers.exists() && newMenu.exists() && !sampleMenuText.exists();
		}
		else {
			testIsLoaded = sampleMenuText.exists() && findAPlanMade.exists() && justForYou.exists() && bestSellers.exists() && newMenu.exists();
		}
		if (testIsLoaded) {
			return true;
		}
		return false;
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

package mobilepage;

import element.BaseMobileElement;
import framework.AbstractDriver;
import framework.FindMethod;

/**
 * This class contains methods and variables that are common to all pages.
 * @author GMitchell
 *
 */
public class BasePage {

	//Bottom navigation is common to all pages.
	public BaseMobileElement navMenu = new BaseMobileElement("Menu");
	public BaseMobileElement navMealPlan = new BaseMobileElement("Meal Plan");
	public BaseMobileElement navBasket = new BaseMobileElement("Basket");
	public BaseMobileElement navStoreLocator = new BaseMobileElement("Store Locator");
	public BaseMobileElement navAccount = new BaseMobileElement("Account");
	
	/**
	 * Load the Main Menu Screen.
	 */
	public void goToMenu()
	{
		navMenu.click();
	}
	
	/**
	 * Load the Main Meal Plan Screen if not a meal plan subscriber.
	 * Load the Manage Meal Plan Screen if already a subscriber.
	 */
	public void goToMealPlan()
	{
		navMealPlan.click();
	}
	
	/**
	 * Load the Shopping Cart.
	 */
	public void goToBasket()
	{
		navBasket.click();
	}
	
	/**
	 * Go to the Store Locator Screen.
	 */
	public void goToStoreLocator()
	{
		navStoreLocator.click();
	}
	
	/**
	 * Load the Main Account Menu.
	 */
	public void goToAccount()
	{
		AbstractDriver.pause(3000);
		navAccount.click();
	}
	
}

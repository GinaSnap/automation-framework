package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.AbstractDriver;

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
	public String goToMealPlan()
	{
		try
		{
			navMealPlan.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Meal Plan button in lower navigation.";
		}
		return "Success";
	}
	
	/**
	 * Click on the Shopping Basket icon.
	 */
	public String goToBasket()
	{
		String status = "Success";
		try
		{
			navBasket.click();
		}
		catch (Exception e)
		{
			status = "Unable to find the shopping basket icon.";
			return status;
		}
		return status;
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
	public String goToAccount()
	{
		AbstractDriver.pause(3000);
		try
		{
			navAccount.click();
		}
		catch (NoSuchElementException e)
		{
			return "Coult not find the Account Menu Option in the lower navigation.";
		}
		return "Success";
	}
	
	public void allowNotifications()
	{
		try
		{
			OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
			orderSubmissionPage.allowNotifications();
		}
		catch (Exception e)
		{
			//I'm not sure if this is a must.  For now, if it's there we'll deal with it, and just ignore if not.
		}
	}
	
	public void doNotAllowNotifications()
	{
		try
		{
			OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
			orderSubmissionPage.doNotAllowNotifications();
		}
		catch (Exception e)
		{
			//I'm not sure if this is a must.  For now, if it's there we'll deal with it, and just ignore if not.
		}
	}
	
}

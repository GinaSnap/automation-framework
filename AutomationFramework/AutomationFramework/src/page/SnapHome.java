package page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class SnapHome extends BasePage {
	
	//Top Left Navigation
	public BaseWebElement snapLogo = new BaseWebElement(FindMethod.CLASSNAME, "snap-logo");
	
	//Top Right Navigation
	public BaseWebElement navMenu = new BaseWebElement(FindMethod.CLASSNAME, "nav-item-menu");
	public BaseWebElement navMealPlans = new BaseWebElement(FindMethod.CLASSNAME, "nav-item-meal-plans");
	public BaseWebElement navLocations = new BaseWebElement(FindMethod.CLASSNAME, "nav-item-locations");
	public BaseWebElement navMore = new BaseWebElement(FindMethod.CLASSNAME, "more-link");
	public BaseWebElement navSearch = new BaseWebElement(FindMethod.CLASSNAME, "link--search");
	public BaseWebElement navAccount = new BaseWebElement(FindMethod.CLASSNAME, "link--account");
	public BaseWebElement navCart = new BaseWebElement(FindMethod.CLASSNAME, "link--basket");
	
	//Account Sub Menu
	private BaseWebElement accountOrders = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_orders");
	
	//Buttons
	public BaseWebElement btnMealPlans = new BaseWebElement(FindMethod.XPATH, "//a[contains(@href,'meal-plans')]");
	public BaseWebElement btnMenu = new BaseWebElement(FindMethod.XPATH,"//a[contains(@href,'menu')]");
	
	//
	public BaseWebElement signUp = new BaseWebElement(FindMethod.LINKTEXT, "Sign Up");
	

	public void goTo() {
		WWWDriver.navigate(WWWDriver.RootURL);
	}
	
	/**
	 * Click the Snap Kitchen Logo
	 */
	public void clickSnapLogo()
	{
		snapLogo.click();
	}
	
	/**
	 * Access the menu using the top navigation item
	 * @return Return true if we found the menu and were able to click.
	 */
	public boolean goToTopNavMenu()
	{
		try
		{
			navMenu.click();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		catch (ElementClickInterceptedException e)
		{
			navMenu.waitUntilClickable();
			navMenu.click();
		}
		return true;
	}
	
	/**
	 * Access the menu using the top navigation item
	 */
	public void goToTopNavMealPlans()
	{
		navMealPlans.click();
	}
	
	/**
	 * Access Locations from Top navigation item
	 */
	public void goToLocations()
	{
		navLocations.click();
	}
	
	/**
	 * Display More menu
	 */
	public void clickMore()
	{
		navMore.click();
	}
	
	/**
	 * Search using the supplied searchText
	 * @param searchText A string value to be searched for.
	 */
	public void search(String searchText)
	{
		//Later
	}
	
	/**
	 * Navigate to either the login page (not logged in) or Account Profile page
	 */
	public void goToLogin()
	{
		navAccount.click();
		if (signUp.exists())
		{
			signUp.click();
		}
	}
	
	/**
	 * Navigate to the Shopping Cart
	 */
	public String goToShoppingCart()
	{
		String status = "Success";
		try {
			navCart.click();
			waitForPageLoadingIndicator();
		}
		catch (NoSuchElementException e)
		{
			status="Shopping cart element was not displayed.";
		}
		return status;
	}
	
	/**
	 * Return the number of items in the shopping cart
	 */
	public int getNumShoppingCartItems()
	{
		return -1;
	}
	
	/**
	 * Navigate to the Menu from the Middle Navigation Button
	 */
	public void goToMenuPageNav()
	{
		btnMenu.click();
	}
	
	public void goToMealPlansPageNav()
	{
		btnMealPlans.click();
	}
	
	public String snapHomePageCheck()
	{
		//Later
		return "";
	}
	
	public void goToOrders()
	{
		navAccount.click();
		accountOrders.waitUntilClickable();
		accountOrders.click();
	}
	
}

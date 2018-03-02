package mobilepage;

import element.BaseMobileElement;

/**
 * This class defines elements and methods for the Main Account Page
 * @author GMitchell
 *
 */
public class AccountHome extends BasePage {
	
	public BaseMobileElement menuManageMealPlan = new BaseMobileElement("manage meal plan");
	public BaseMobileElement menuOrders = new BaseMobileElement("orders");
	public BaseMobileElement menuPromoCode = new BaseMobileElement("add promo code");
	public BaseMobileElement menuProfile = new BaseMobileElement("profile");
	public BaseMobileElement menuCustomerCare = new BaseMobileElement("customer care");
	public BaseMobileElement menuGeneralInfo = new BaseMobileElement("general info");
	public BaseMobileElement menuLogOut = new BaseMobileElement("log out");
	
	/**
	 * Access the Manage Meal Plan Screen, or, Meal Plan Setup if not 
	 * currently a subscriber.
	 */
	public void goToManageMealPlan()
	{
		menuManageMealPlan.click();
	}
	
	/**
	 * Access Current and Past Orders.
	 */
	public void goToOrders()
	{
		menuOrders.click();
	}
	
	/**
	 * Not Sure Yet.
	 */
	public void goToPromoCode()
	{
		
	}
	
	/**
	 * Navigate to Account Details for User Currently
	 * logged in.
	 */
	public void goToProfile()
	{
		menuProfile.click();
	}
	
	/**
	 * Load screen which allows customer to enter a message 
	 * or call customer care (for MP subscribers).
	 */
	public void goToCustomerCare()
	{
		menuCustomerCare.click();
	}
	
	/**
	 * Switch to Snap Care Site (not managed by our team)
	 */
	public void goToSnapCare()
	{
		menuGeneralInfo.click();
	}
	
	/**
	 * Logout of the Application.  
	 * User will be returned to the Home Page.
	 */
	public void logout()
	{
		menuLogOut.click();
	}

}

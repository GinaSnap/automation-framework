package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;

/**
 * This class defines elements and methods for the Main Account Page
 * @author GMitchell
 *
 */
public class AccountHome extends BasePage {
	
	private BaseMobileElement menuManageMealPlan = new BaseMobileElement("manage meal plan");
	private BaseMobileElement menuOrders = new BaseMobileElement("orders");
	private BaseMobileElement menuEditPayments = new BaseMobileElement("payments");
	private BaseMobileElement menuPromoCode = new BaseMobileElement("promo");
	private BaseMobileElement menuProfile = new BaseMobileElement("profile");
	private BaseMobileElement menuCustomerCare = new BaseMobileElement("customer care");
	private BaseMobileElement menuGeneralInfo = new BaseMobileElement("general info");
	private BaseMobileElement menuLogOut = new BaseMobileElement("sign out");
	
	/**
	 * Access the Manage Meal Plan Screen, or, Meal Plan Setup if not 
	 * currently a subscriber.
	 */
	public void goToManageMealPlan()
	{
		menuManageMealPlan.click();
	}
	
	/**
	 * Click Orders from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Orders.
	 * Return error string if not.
	 */
	public String goToOrders()
	{
		String status="Success";
		if (goToAccount().equals("Success"))
		{
			try {
				menuOrders.click();
			}
			catch (NoSuchElementException e)
			{
				status="Orders button is not displayed.";
				return status;
			}
		}
		return status;
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
	public String goToProfile()
	{
		try
		{
			menuProfile.click();
		}
		catch (NoSuchElementException e)
		{
			return "Profile Menu Element was not found.";
		}
		return "Success";
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

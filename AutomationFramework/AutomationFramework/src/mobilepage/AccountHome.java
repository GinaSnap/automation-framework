package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.MobileDriver;

/**
 * This class defines elements and methods for the Main Account Page
 * @author GMitchell
 *
 */
public class AccountHome extends BasePage {
	
	private BaseMobileElement menuManageMealPlan = new BaseMobileElement("manage meal plan");
	private BaseMobileElement menuOrders = new BaseMobileElement("orders");
	private BaseMobileElement menuPayments = new BaseMobileElement("payments");
	private BaseMobileElement menuPromo = new BaseMobileElement("promo");
	private BaseMobileElement menuProfile = new BaseMobileElement("profile");
	private BaseMobileElement menuCustomerCare = new BaseMobileElement("customer care");
	private BaseMobileElement menuGeneralInfo = new BaseMobileElement("general info");
	private BaseMobileElement menuLifestyleSuggestion = new BaseMobileElement("lifestyle suggestion");
	private BaseMobileElement menuLogOut = new BaseMobileElement("sign out");
	
	private final BaseMobileElement addNewCard = new BaseMobileElement("Add New Card…");
	private final BaseMobileElement card_Number = new BaseMobileElement("card number");
	private final BaseMobileElement card_ExpireDate = new BaseMobileElement("expiration date");
	private final BaseMobileElement card_CVC = new BaseMobileElement("CVC");
	private final BaseMobileElement card_Cancel = new BaseMobileElement("Cancel");
	private final BaseMobileElement card_Done = new BaseMobileElement("Done");
	
	//Prompt letting user know the payment for their meal plan is being updated.
	private BaseMobileElement updateMealPlanPrompt = new BaseMobileElement("update meal plan");
	private BaseMobileElement ok = new BaseMobileElement("ok");
	
	/**
	 * Click Manage Meal Plan from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Manage Meal Plan.
	 * Return error string if not.
	 */
	public String goToManageMealPlan()
	{
		try
		{
			menuManageMealPlan.click();
		}
		catch (NoSuchElementException e)
		{
			return "Manage Meal Plan Menu Element was not found.";
		}
		return "Success";
	}
	
	/**
	 * Returns true if the Manage Meal Plan Menu item exists.
	 */
	public boolean manageMealPlanExists()
	{
		
		return menuManageMealPlan.exists();
		
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
	 * Returns true if the Orders Menu item exists.
	 */
	public boolean ordersExists()
	{
		return menuOrders.exists();
	}
	
	/**
	 * Click Payments from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Payments.
	 * Return error string if not.
	 */
	public String goToPayments()
	{
		String status="Success";
		if (goToAccount().equals("Success"))
		{
			try {
				menuPayments.click();
			}
			catch (NoSuchElementException e)
			{
				status="Payments button is not displayed.";
				return status;
			}
		}
		return status;
	}
	
	/**
	 * Returns true if the Payments Menu Item exists.
	 */
	public boolean paymentsExists()
	{
		return menuPayments.exists();
	}
	
	public String addPaymentMethod(String cardNumber, String date, String cvc)
	{
		goToPayments();
		try 
		{
			addNewCard.waitUntilClickable();
			addNewCard.click();
			card_Number.setWebValue(cardNumber);
			card_ExpireDate.setWebValue(date);
			card_CVC.setWebValue(cvc);
			card_Done.click();
		}
		catch (NoSuchElementException e)
		{
			return "Error";
		}
	
		return "Success";
	}
	
	/**
	 * Click Promo from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Promo.
	 * Return error string if not.
	 */
	public String goToPromo()
	{
		String status="Success";
		if (goToAccount().equals("Success"))
		{
			try {
				menuPromo.click();
			}
			catch (NoSuchElementException e)
			{
				status="Promo button is not displayed.";
				return status;
			}
		}
		return status;
	}
	
	/**
	 * Returns true if the Promo Menu item exists.
	 */
	public boolean promoExists()
	{
		return menuPromo.exists();
	}
	
	/**
	 * Click Profile from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Profile.
	 * Return error string if not.
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
	 * Returns true if the Profile Menu item exists.
	 */
	public boolean profileExists()
	{
		return menuProfile.exists();
	}
	
	/**
	 * Click Customer Care from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Customer Care.
	 * Return error string if not.
	 */
	public String goToCustomerCare()
	{
		try
		{
			menuCustomerCare.click();
		}
		catch (NoSuchElementException e)
		{
			return "Customer Care Menu Element was not found.";
		}
		return "Success";
	}
	
	/**
	 * Returns true if the Customer Care Menu item exists.
	 */
	public boolean customerCareExists()
	{
		return menuCustomerCare.exists();
	}
	
	/**
	 * Click General Info from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click General Info.
	 * Return error string if not.
	 */
	public String goToGeneralInfo()
	{
		try
		{
			menuGeneralInfo.click();
		}
		catch (NoSuchElementException e)
		{
			return "General Info Menu Element was not found.";
		}
		return "Success";
	}
	
	/**
	 * Returns true if the General Info Menu item exists.
	 */
	public boolean generalInfoExists()
	{
		return menuGeneralInfo.exists();
	}
	
	/**
	 * Returns true if the Sign Out link exists.
	 */
	public boolean signOutExists()
	{
		try
		{
			return menuLogOut.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	/**
	 * Logout of the Application.  
	 * User will be returned to the Home Page.
	 */
	public void logout()
	{
		menuLogOut.click();
	}
	
	/**
	 * Click Lifestyle Suggestions from the Account Menu.
	 * @return
	 * Return "Success" if the user is able to access the account menu and click Lifestyle Suggestions.
	 * Return error string if not.
	 */
	public String goToLifestyleSuggestions()
	{
		String status="Success";
		if (goToAccount().equals("Success"))
		{
			try {
				menuLifestyleSuggestion.click();
			}
			catch (NoSuchElementException e)
			{
				status="Lifestyle Suggestion button is not displayed.";
				return status;
			}
		}
		return status;
	}
	
	/**
	 * Returns true if the Lifestyle Suggestions Menu item exists.
	 */
	public boolean lifestyleSuggestionsExists()
	{
		return menuLifestyleSuggestion.exists();
	}

	public String closeMealPlanPaymentUpdatedPrompt()
	{
		try
		{
			if (updateMealPlanPrompt.exists())
			{
				ok.click();
				return "Success";
			}
			else
			{
				return "Meal Plan Update Prompt was not displayed.";
			}
		}
		catch (NoSuchElementException e)
		{
			return "Meal Plan Payment Update prompt did not exist.";
		}
	}
}

package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import framework.FindMethod;

public class MealPlanConfirmationPage extends BasePage {
	
	private BaseWebElement manageSubscription = new BaseWebElement(FindMethod.XPATH, "//button[text()='Manage Subscription']");
	
	public String clickManageSubscription()
	{
		try
		{
			manageSubscription.click();
		}
		catch (NoSuchElementException e)
		{
			return "Manage Subscription button was not displayed.";
		}
		return "Success";
	}

}

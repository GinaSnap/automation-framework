package mobilepage;

import java.util.NoSuchElementException;

import element.BaseMobileElement;

public class MealPlanningMenu extends BasePage {
	
	private final BaseMobileElement checkout = new BaseMobileElement("Next");
	
	public String checkOut()
	{
		try
		{
			checkout.click();
		}
		catch (NoSuchElementException e)
		{
			return "Cannot find Checkout Button";
		}
		return "Success";
	}

}

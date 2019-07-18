package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import mobilepage.BasePage;

public class MealPlanLifestylePage extends BasePage {
	
	private BaseMobileElement startBuildingSubscription = new BaseMobileElement("start");
	
	public String startBuildingSubscription()
	{
		try
		{
			startBuildingSubscription.click();
		}
		catch (NoSuchElementException e)
		{
			return "Start Building Subscription Button was not found.";
		}
		return "Success";
	}

}

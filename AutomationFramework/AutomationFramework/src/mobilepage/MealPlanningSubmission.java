package mobilepage;

import element.BaseMobileElement;

public class MealPlanningSubmission extends BasePage {
	
	private final BaseMobileElement manageSubscription = new BaseMobileElement("MANAGE PLAN");
	
	public boolean setupSuccess()
	{
		return manageSubscription.exists();
	}

}

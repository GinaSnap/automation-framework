package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseWebElement;
import element.WebFullfillmentDetailsElement;
import framework.FindMethod;

public class MealPlanCheckoutPage extends BasePage {
	
	private WebFullfillmentDetailsElement fulfillmentDetails = new WebFullfillmentDetailsElement(FindMethod.CLASSNAME, "component-step-date-time-form");
	private BaseWebElement startSubscripton = new BaseWebElement(FindMethod.CLASSNAME, "checkout-button__cta");
	
	public String enterFulfillmentDetails()
	{
		//For now just clicking save and using the defaults.
		return fulfillmentDetails.clickSave();
	}
	
	public String startSubscription()
	{
		try
		{
			startSubscripton.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Start Subscription button.";
		}
		return "Success";
	}
}

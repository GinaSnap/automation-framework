package page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;

import common.CreditCardType;
import element.BaseWebElement;
import element.WebFullfillmentDetailsElement;
import framework.FindMethod;
import framework.WWWDriver;

public class MealPlanCheckoutPage extends BasePage {
	
	private WebFullfillmentDetailsElement fulfillmentDetails = new WebFullfillmentDetailsElement(FindMethod.CLASSNAME, "component-step-date-time-multi-select");
	private BaseWebElement startSubscripton = new BaseWebElement(FindMethod.CLASSNAME, "checkout-button__cta");
	private BaseWebElement addNewCard = new BaseWebElement(FindMethod.CLASSNAME, "add-card");
	private BaseWebElement card_cardNumber = new BaseWebElement(FindMethod.ID, "cardNumber");
	private BaseWebElement card_expirationDate = new BaseWebElement(FindMethod.ID, "expiration");
	private BaseWebElement card_cvcNumber = new BaseWebElement(FindMethod.ID,"cvc");
	private BaseWebElement card_saveBtn = new BaseWebElement(FindMethod.CLASSNAME, "button--alt");
	
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
		catch (ElementClickInterceptedException e)
		{
			//This means that the credit card window hasn't quite closed yet.
			//We'll wait a second and try again.
			startSubscripton.waitUntilClickable();
			startSubscripton.click();
		}
		return "Success";
	}
	
	public String addNewCreditCard(CreditCardType cardType)
	{
		WWWDriver.pause(3000); //TODO:  Remove this
		try
		{
			addNewCard.click();
		}
		catch (NoSuchElementException e)
		{
			return "Success";
		}
		
		try
		{
			card_cardNumber.setWebValue(cardType.getCardNumber());
			card_expirationDate.setWebValue(cardType.getExpiration());
			card_cvcNumber.setWebValue(cardType.getCVC());
		}
		catch (NoSuchElementException e)
		{
			return "Credit card fields were not displayed.";
		}
		try
		{
			card_saveBtn.click();
		}
		catch(NoSuchElementException e)
		{
			return "Save Button was not visible.";
		}
		
		if(!waitForCardVerification(10000))
		{
			return "Credit Card verification did not complete.";
		}
		
		if (!waitForCreditCardClose(10000))
		{
			return "Add Credit Card screen did not close.";
		}
		
		return "Success";
	}
	
	private boolean waitForCardVerification(long timeout)
	{
		long startTimeMili = System.currentTimeMillis();
		long currentTimeMili = System.currentTimeMillis();
		String buttonCaption = card_saveBtn.getText();
		
		while (((currentTimeMili - startTimeMili) < timeout) && buttonCaption.contains("verify"))
		{
			currentTimeMili = System.currentTimeMillis();
			buttonCaption = card_saveBtn.getText();
		}
		
		return !buttonCaption.contains("verify");
	}
	
	private boolean waitForCreditCardClose(long timeout)
	{
		long startTimeMili = System.currentTimeMillis();
		long currentTimeMili = System.currentTimeMillis();
		while (((currentTimeMili - startTimeMili) < timeout) && card_cardNumber.exists())
		{
			currentTimeMili = System.currentTimeMillis();
		}
		
		return !card_cardNumber.exists();
	}
}

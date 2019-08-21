package mobilepage;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.FindMethod;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class MealPlanningOptions extends BasePage {
	
	private final BaseMobileElement selectFulfillmentDate = new BaseMobileElement("choose a start date");
	private final BaseMobileElement save = new BaseMobileElement("SAVE");
	private final BaseMobileElement next = new BaseMobileElement("NEXT");
	private final BaseMobileElement editPayment = new BaseMobileElement("payment");
	private final BaseMobileElement addNewCard = new BaseMobileElement("Add New Card…");
	private final BaseMobileElement card_Number = new BaseMobileElement("card number");
	private final BaseMobileElement card_ExpireDate = new BaseMobileElement("expiration date");
	private final BaseMobileElement card_CVC = new BaseMobileElement("CVC");
	private final BaseMobileElement card_Cancel = new BaseMobileElement("Cancel");
	private final BaseMobileElement card_Done = new BaseMobileElement("Done");
	private final BaseMobileElement startSubscription = new BaseMobileElement("START PLAN");

	public String selectFulfillementDate(int fulfillmentDay)
	{
		try {
			selectFulfillmentDate.click();
		} catch (NoSuchElementException e) {
			return "Could Not Find Select Fulfillment Date Element";
		}
		
		try {
			BaseMobileElement firstFulfillmentDay = new BaseMobileElement(FindMethod.XPATH, String.format("//XCUIElementTypeButton[@name='%d']",fulfillmentDay));
			firstFulfillmentDay.click();
		} catch (NoSuchElementException e) {
			return "The fulfillent day requested was not found on the calendar element.";
		}
		
		try {
			save.click();
		} catch (NoSuchElementException e) {
			return "The save button did not exist.";
		}
		
		try {
			next.click();
		} catch (NoSuchElementException e) {
			return "The next button did not exist.";
		}
		
		return "Success";
	}
	
	public boolean saveButtonExists()
	{
		try
		{
			return save.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String addCreditCard(String cardNumber, String date, String cvc)
	{
		try {
			editPayment.waitUntilClickable();
			editPayment.click();
			addNewCard.click();
		} catch (NoSuchElementException e) {
			return "Error navigating to the Add Credit Card screen.";
		}
		
		try {
			card_Number.setWebValue(cardNumber);
			card_ExpireDate.setWebValue(date);
			card_CVC.setWebValue(cvc);
			card_Done.click();
		} catch (NoSuchElementException e) {
			return "Error Entering Credit card information.";
		}
	
		return "Success";
	}
	
	public String startSubscription()
	{
		try {
			startSubscription.click();
		} catch (NoSuchElementException e) {
			return "Could not locate the Start Subscription button on the Checkout screen.";
		}
		
		return "Success";
	}
}

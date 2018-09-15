package page;

import org.openqa.selenium.NoSuchElementException;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.Size;
import element.BaseWebElement;
import element.WebDayPartElement;
import framework.FindMethod;

public class MealPlanOptionsPage extends BasePage {
	
	private BaseWebElement calories1200 = new BaseWebElement(FindMethod.XPATH, "//div[text()='1200 cal']");
	private BaseWebElement calories1500 = new BaseWebElement(FindMethod.XPATH, "//div[text()='1500 cal']");
	private BaseWebElement calories1800 = new BaseWebElement(FindMethod.XPATH, "//div[text()='1800 cal']");
	private BaseWebElement calories2000 = new BaseWebElement(FindMethod.XPATH, "//div[text()='2000 cal']");

	//This is just not good but I need to keep moving.
	private BaseWebElement threeDays = new BaseWebElement(FindMethod.XPATH, "//div[contains(text(),'3')]");
	private BaseWebElement fiveDays = new BaseWebElement(FindMethod.XPATH, "//div[contains(text(),'5')]");
	private BaseWebElement sevenDays = new BaseWebElement(FindMethod.XPATH, "//div[contains(text(),'7')]");
	
	private WebDayPartElement breakfast = new WebDayPartElement("BREAKFAST");
	private WebDayPartElement lunch = new WebDayPartElement("LUNCH");
	private WebDayPartElement dinner = new WebDayPartElement("DINNER");
	private WebDayPartElement amSnack = new WebDayPartElement("AM SNACK");
	private WebDayPartElement pmSnack = new WebDayPartElement("PM SNACK");
	private WebDayPartElement drinks = new WebDayPartElement("DRINKS");
	
	private BaseWebElement pickup = new BaseWebElement(FindMethod.XPATH, "//div[text()='pickup']");
	private BaseWebElement delivery = new BaseWebElement(FindMethod.XPATH, "//div[text()='delivery']");
	
	private BaseWebElement next = new BaseWebElement(FindMethod.XPATH, "//button[contains(text(),'View Menu')]");

	public String selectCalories(Size size)
	{
		try
		{
			switch (size) {
			case CALORIES_1200:
				calories1200.click();
				break;
			case CALORIES_1500:
				calories1500.click();
				break;
			case CALORIES_1800:
				calories1800.click();
				break;
			default:
				return "Size option specified is invalid.";
			}
		}
		catch (NoSuchElementException e)
		{
			return "Chosen Calories Button was not displayed.";
		}
		return "Success";
	}
	
	public String selectDays(DaysPerWeek days)
	{
		try
		{
			switch (days) {
			case THREE_DAYS:
				threeDays.click();
				break;
			case FIVE_DAYS:
				fiveDays.click();
				break;
			case SEVEN_DAYS:
				sevenDays.click();
				break;
			default:
				return "Specified days option was invalid.";
			}
		}
		catch (NoSuchElementException e)
		{
			return "Chosen Days button does not exist.";
		}
		return "Success";
	}
	
	public String selectDayParts(DayParts dayParts)
	{
		switch (dayParts) {
		case BREAKFAST:
			return breakfast.selectElement();
		case LUNCH:
			return lunch.selectElement();
		case DINNER:
			return dinner.selectElement();
		case AM_SNACK:
			return amSnack.selectElement();
		case PM_SNACK:
			return pmSnack.selectElement();
		case DRINKS:
			return drinks.selectElement();
		default:
			return "Day Part Specified is invalid.";
		}
	}
	
	public void selectFulfillment(Fulfillment fulfillment)
	{
		switch (fulfillment) {
		case PICKUP:
			pickup.click();
			break;
		case DELIVERY:
			delivery.click();
			break;
		default:
			break;
		}
	}
	
	public boolean viewMenuExists()
	{
		try
		{
			return next.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String clickNext()
	{
		try
		{
			next.click();
		}
		catch (NoSuchElementException e)
		{
			return "View Menu button is not displayed.";
		}
		return "Success";
	}

}

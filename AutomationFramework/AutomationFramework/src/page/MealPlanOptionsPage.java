package page;

import org.openqa.selenium.NoSuchElementException;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.Size;
import element.BaseWebElement;
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
	
	private BaseWebElement breakfast = new BaseWebElement(FindMethod.XPATH, "//div[text()='Breakfast']");
	private BaseWebElement lunch = new BaseWebElement(FindMethod.XPATH, "//div[text()='Lunch']");
	private BaseWebElement dinner = new BaseWebElement(FindMethod.XPATH, "//div[text()='Dinner']");
	private BaseWebElement amSnack = new BaseWebElement(FindMethod.XPATH, "//div[text()='AM Snack']");
	private BaseWebElement pmSnack = new BaseWebElement(FindMethod.XPATH, "//div[text()='PM Snack']");
	private BaseWebElement drinks = new BaseWebElement(FindMethod.XPATH, "//div[text()='Drinks']");
	
	private BaseWebElement pickup = new BaseWebElement(FindMethod.XPATH, "//div[text()='pickup']");
	private BaseWebElement delivery = new BaseWebElement(FindMethod.XPATH, "//div[text()='delivery']");
	
	private BaseWebElement next = new BaseWebElement(FindMethod.XPATH, "//button[contains(text(),'Next')]");

	public void selectCalories(Size size)
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
			break;
		}
	}
	
	public void selectDays(DaysPerWeek days)
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
			break;
		}
	}
	
	public void selectDayParts(DayParts dayParts)
	{
		switch (dayParts) {
		case BREAKFAST:
			breakfast.click();
			break;
		case LUNCH:
			lunch.click();
			break;
		case DINNER:
			dinner.click();
			break;
		case AM_SNACK:
			amSnack.click();
			break;
		case PM_SNACK:
			pmSnack.click();
			break;
		case DRINKS:
			drinks.click();
			break;
		default:
			break;
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

package page;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import element.BaseWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class MealPlanMenuPage extends BasePage {
	
	private BaseWebElement checkoutButton = new BaseWebElement(FindMethod.CLASSNAME, "component-live-pricing-button");
	
	//Add Day Parts
	private BaseWebElement addBreakfast = new BaseWebElement(FindMethod.CLASSNAME, "day-part-icon-breakfast");
	private BaseWebElement addAMSnack = new BaseWebElement(FindMethod.CLASSNAME, "day-part-icon-snack1");
	private BaseWebElement addLunch = new BaseWebElement(FindMethod.CLASSNAME, "day-part-icon-lunch");
	private BaseWebElement addPMSnack = new BaseWebElement(FindMethod.CLASSNAME, "day-part-icon-snack2");
	private BaseWebElement addDinner = new BaseWebElement(FindMethod.CLASSNAME, "day-part-icon-dinner");
	private BaseWebElement addDrinks = new BaseWebElement(FindMethod.CLASSNAME, "day-part-icon-drink");
	
	//Edit Day Part
	private BaseWebElement searchIcon = new BaseWebElement(FindMethod.CLASSNAME, "header-icon");
	private BaseWebElement searchTextBox = new BaseWebElement(FindMethod.CLASSNAME, "search-page-input");
	private BaseWebElement saveFloating = new BaseWebElement(FindMethod.CLASSNAME, "component-floating-save-button");
	
	//Day Navigation
	private BaseWebElement continueNextDay = new BaseWebElement(FindMethod.CLASSNAME, "meal-plan-day-progress-button");
	

	public String clickCheckout()
	{
		try
		{
			checkoutButton.click();
		}
		catch (NoSuchElementException e)
		{
			return "Checkout button was not displayed.";
		}
		return "Success";
	}
	
	public boolean addDayPartExists(DayParts dayPart)
	{
		switch (dayPart) {
		case BREAKFAST:
			return addBreakfast.exists();
		case AM_SNACK:
			return addAMSnack.exists();
		case LUNCH:
			return addLunch.exists();
		case PM_SNACK:
			return addPMSnack.exists();
		case DINNER:
			return addDinner.exists();
		case DRINKS:
			return addDrinks.exists();

		default:
			return false;
		}
	}
	
	public boolean clickAddDayPart(DayParts dayPart)
	{
		switch (dayPart) {
		case BREAKFAST:
			return addBreakfast.click();
		case AM_SNACK:
			return addAMSnack.click();
		case LUNCH:
			return addLunch.click();
		case PM_SNACK:
			return addPMSnack.click();
		case DINNER:
			return addDinner.click();
		case DRINKS:
			return addDrinks.click();

		default:
			return false;
		}
	}
	
	public String addDayPart(DayParts dayPart, String productName)
	{
		WWWDriver.pause(1000);  //TODO:  Wait
		if (!clickAddDayPart(dayPart))
		{
			return "Could not click the Add Day Part Icon.";
		}
		
		WWWDriver.pause(1000);  //TODO:  Wait
		if (!searchMeals(productName))
		{
			return "Could not search for product from the Add Day Part Screen.";
		}
		
		if (!addProduct(productName))
		{
			return "Could not click to add product from the search screen.";
		}
		
		if (!saveFloating.click())
		{
			return "Could not find/click save button.";
		}
		
		WWWDriver.pause(1000);  //TODO:  Wait
		return "Success";
	}
	
	public boolean searchMeals(String productName)
	{
		try
		{
			searchIcon.click();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		
		try
		{
			searchTextBox.setWebValue(productName);
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		
		return true;
		
	}
	
	public boolean addProduct(String productName)
	{
		List<WebElement> productElements = getProductList();
			for (WebElement productElement : productElements)
			{
				WebElement nameElement = productElement.findElement(By.className("productName"));
				if (nameElement.getText().equals(productName))
				{
					WebElement addElement = productElement.findElement(By.className("day-part-editor__meal-action"));
					addElement.click();
				}
			}
			
			return true;
	}
	
	private List<WebElement> getProductList()
	{
		String xPath = "//div[contains(@class, 'meal-option')]";
		
		List<WebElement> productElements = new ArrayList<WebElement>();
				
		try
		{
			productElements = WWWDriver.instance.findElements(By.xpath(xPath));
		}
		catch (Exception e)
		{
			//
		}
		return productElements;

	}
	
	public boolean planNextDay()
	{
		return continueNextDay.click();
	}
	
	public boolean planSpecificDay(int day)
	{
		List<WebElement> dayElements = getDayElements();
		try
		{
			dayElements.get(day-1).click();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		return true;
	}
	
	private List<WebElement> getDayElements()
	{
		List<WebElement> dayElements = new ArrayList<WebElement>();
		
		try
		{
			dayElements = WWWDriver.instance.findElements(By.className("day-option"));
		}
		catch (Exception e)
		{
			//Just return the empty list.
		}
		return dayElements;
	}
	
	public boolean buildYourOwnMenu(DaysPerWeek daysPerWeek)
	{
		int numDays = 0;
		switch (daysPerWeek) {
		case THREE_DAYS:
			numDays=3;
			break;
		case FIVE_DAYS:
			numDays=5;
			break;
		case SEVEN_DAYS:
			numDays=7;
		default:
			break;
		}
		
		for (int i=1;i<=numDays;i++)
		{	
			addDayPart(DayParts.BREAKFAST, "ab&j oatmeal");
			addDayPart(DayParts.AM_SNACK, "beanitos black bean");
			addDayPart(DayParts.LUNCH, "bbq chicken plate");
			addDayPart(DayParts.PM_SNACK, "chomps - original beef");
			addDayPart(DayParts.DINNER, "naked salmon");
			addDayPart(DayParts.DRINKS, "blue bottle cold brew coffee");
			if (i<numDays)
			{
				planNextDay();
			}
		}
		return true;
	}

}


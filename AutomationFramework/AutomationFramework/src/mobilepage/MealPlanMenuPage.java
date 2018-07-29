package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import element.BaseMobileElement;
import framework.FindMethod;

public class MealPlanMenuPage extends BasePage {
	
	private BaseMobileElement returnToOptions = new BaseMobileElement("OPTIONS");
	private BaseMobileElement dayOne = new BaseMobileElement("DAY 1");
	private BaseMobileElement dayTwo = new BaseMobileElement("DAY 2");
	private BaseMobileElement dayThree = new BaseMobileElement("DAY 3");
	private BaseMobileElement dayFour = new BaseMobileElement("DAY 4");
	private BaseMobileElement dayFive = new BaseMobileElement("DAY 5");
	private BaseMobileElement daySix = new BaseMobileElement("DAY 6");
	private BaseMobileElement daySeven = new BaseMobileElement("DAY 7");
	
	private BaseMobileElement dayPartBreakfast = new BaseMobileElement("BREAKFAST");
	private BaseMobileElement dayPartAMSnack = new BaseMobileElement("AM SNACK");
	private BaseMobileElement dayPartLunch = new BaseMobileElement("LUNCH");
	private BaseMobileElement dayPartPMSnack = new BaseMobileElement("PM SNACK");
	private BaseMobileElement dayPartDinner = new BaseMobileElement("DINNER");
	private BaseMobileElement dayPartDrinks = new BaseMobileElement("DRINKS");
	
	private BaseMobileElement addBreakfast = new BaseMobileElement("ADD BREAKFAST");
	private BaseMobileElement addAMSnack = new BaseMobileElement("ADD AM SNACK");
	private BaseMobileElement addLunch = new BaseMobileElement("ADD LUNCH");
	private BaseMobileElement addPMSnack = new BaseMobileElement("ADD PM SNACK");
	private BaseMobileElement addDinner = new BaseMobileElement("ADD DINNER");
	private BaseMobileElement addDrinks = new BaseMobileElement("ADD DRINKS");
	
	private BaseMobileElement planDay2 = new BaseMobileElement("PLAN DAY 2");
	private BaseMobileElement planDay3 = new BaseMobileElement("PLAN DAY 3");
	private BaseMobileElement planDay4 = new BaseMobileElement("PLAN DAY 4");
	private BaseMobileElement planDay5 = new BaseMobileElement("PLAN DAY 5");
	private BaseMobileElement planDay6 = new BaseMobileElement("PLAN DAY 6");
	private BaseMobileElement planDay7 = new BaseMobileElement("PLAN DAY 7");

	
	/**
	 * From the Day View, return true if the given day part exists on the screen.
	 * I am looking for the day part header "BREAKFAST", "LUNCH", etc.
	 * If the day part isn't initially on the screen, it will scroll down and look for it again.
	 * @param dayPart
	 * @return
	 */
	public boolean dayPartExists(DayParts dayPart)
	{
		
		try {
			switch (dayPart)
			{
				case BREAKFAST:
					return dayPartBreakfast.exists();
				case AM_SNACK:
					return dayPartAMSnack.exists();
				case LUNCH:
					return dayPartLunch.exists();
				case PM_SNACK:
					return dayPartPMSnack.exists();
				case DINNER:
					return dayPartDinner.exists();
				case DRINKS:
					return dayPartDrinks.exists();
				default:
					return false;
					
			}
		} catch (NoSuchElementException e) {
			return false;
		}	
	}
	
	/**
	 * From the Day View, return true if the add day part option for the given day part exists.
	 * I am looking for the link text "ADD BREAKFAST", "ADD LUNCH", etc.
	 * If the add day part link isn't initially on the screen, it will scroll down and look for it again.
	 * @param dayPart
	 * @return
	 */
	public boolean addDayPartExists(DayParts dayPart)
	{
		
		try {
			switch (dayPart)
			{
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
		} catch (NoSuchElementException e) {
			return false;
		}	
	}
	
	public boolean dayOneExists()
	{
		try
		{
			return dayOne.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean dayTwoExists()
	{
		try
		{
			return dayTwo.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean dayThreeExists()
	{
		try
		{
			return dayThree.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean dayFourExists()
	{
		try
		{
			return dayFour.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean dayFiveExists()
	{
		try
		{
			return dayFive.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean daySixExists()
	{
		try
		{
			return daySix.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean daySevenExists()
	{
		try
		{
			return daySeven.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String goToDayOne()
	{
		try
		{
			dayOne.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 1 Link.";
		}
		
		return "Success";
	}
	
	public String goToDayTwo()
	{
		try
		{
			dayTwo.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 2 Link.";
		}
		
		return "Success";
	}
	
	public String goToDayThree()
	{
		try
		{
			dayThree.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 3 Link.";
		}
		
		return "Success";
	}
	
	public String goToDayFour()
	{
		try
		{
			dayFour.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 4 Link.";
		}
		
		return "Success";
	}
	
	public String goToDayFive()
	{
		try
		{
			dayFive.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 5 Link.";
		}
		
		return "Success";
	}
	
	public String goToDaySix()
	{
		try
		{
			daySix.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 6 Link.";
		}
		
		return "Success";
	}
	
	public String goToDaySeven()
	{
		try
		{
			daySeven.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Day 7 Link.";
		}
		
		return "Success";
	}
	
	public String planDay2()
	{
		try
		{
			planDay2.scrollUntilVisible(5);
			planDay2.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Plan Day 2 Link.";
		}
		
		return "Success";
	}
	
	public String planDay3()
	{
		try
		{
			planDay3.scrollUntilVisible(5);
			planDay3.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Plan Day 3 Link.";
		}
		
		return "Success";
	}
	
	public String planDay4()
	{
		try
		{
			planDay4.scrollUntilVisible(5);
			planDay4.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Plan Day 4 Link.";
		}
		
		return "Success";
	}
	
	public String planDay5()
	{
		try
		{
			planDay5.scrollUntilVisible(5);
			planDay5.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Plan Day 5 Link.";
		}
		
		return "Success";
	}
	
	public String planDay6()
	{
		try
		{
			planDay6.scrollUntilVisible(5);
			planDay6.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Plan Day 6.";
		}
		
		return "Success";
	}
	
	public String planDay7()
	{
		try
		{
			planDay7.scrollUntilVisible(5);
			planDay7.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Plan Day 7 Link.";
		}
		
		return "Success";
	}
	
	public String returnMealPlanOptions()
	{
		try
		{
			returnToOptions.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the return to options link on the Main Menu Page.";
		}
		return "Success";
	}
	
	public String editDayPart(DayParts dayPart)
	{
		String xPath;
		BaseMobileElement editElement;
		try
		{
			switch (dayPart) {
			case BREAKFAST:
				xPath = "//XCUIElementTypeStaticText[@name='BREAKFAST']/preceding-sibling::XCUIElementTypeButton[@name='EDIT'])";
				editElement = new BaseMobileElement(FindMethod.XPATH,xPath);
				editElement.click();
				break;
				
			case AM_SNACK:
				xPath = "//XCUIElementTypeStaticText[@name='AM_SNACK']/preceding-sibling::XCUIElementTypeButton[@name='EDIT'])";
				editElement = new BaseMobileElement(FindMethod.XPATH,xPath);
				editElement.click();
				break;
				
			case LUNCH:
				xPath = "//XCUIElementTypeStaticText[@name='LUNCH']/preceding-sibling::XCUIElementTypeButton[@name='EDIT'])";
				editElement = new BaseMobileElement(FindMethod.XPATH,xPath);
				editElement.click();
				break;
				
			case PM_SNACK:
				xPath = "//XCUIElementTypeStaticText[@name='PM_SNACK']/preceding-sibling::XCUIElementTypeButton[@name='EDIT'])";
				editElement = new BaseMobileElement(FindMethod.XPATH,xPath);
				editElement.click();
				break;
				
			case DINNER:
				xPath = "//XCUIElementTypeStaticText[@name='DINNER']/preceding-sibling::XCUIElementTypeButton[@name='EDIT'])";
				editElement = new BaseMobileElement(FindMethod.XPATH,xPath);
				editElement.click();
				break;
				
			case DRINKS:
				xPath = "//XCUIElementTypeStaticText[@name='DRINKS']/preceding-sibling::XCUIElementTypeButton[@name='EDIT'])";
				editElement = new BaseMobileElement(FindMethod.XPATH,xPath);
				editElement.click();
				break;

			default:
				break;
			}
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Edit Element for the given Day Part: " + dayPart;
		}
		return "Success";
	}

}

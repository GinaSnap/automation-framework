package mobilepage;

import java.util.Calendar;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.PlanType;
import common.MealPlanOptions.Size;
import common.UserType;
import element.BaseMobileElement;
import framework.FindMethod;
import framework.WWWDriver;

public class MealPlanningPage extends BasePage {
	
	
	private final BaseMobileElement calories1200 = new BaseMobileElement("1200 CAL");
	private final BaseMobileElement calories1500 = new BaseMobileElement("1500 CAL");
	private final BaseMobileElement calories1800 = new BaseMobileElement("1800 CAL");
	private final BaseMobileElement calories2000 = new BaseMobileElement("2000 CAL");

	private final BaseMobileElement small = new BaseMobileElement("SMALL");
	private final BaseMobileElement medium = new BaseMobileElement("MEDIUM");
	private final BaseMobileElement large = new BaseMobileElement("LARGE");
	
	private final BaseMobileElement threeDays = new BaseMobileElement("3 DAYS");
	private final BaseMobileElement fiveDays = new BaseMobileElement("5 DAYS");
	private final BaseMobileElement sevenDays = new BaseMobileElement("7 DAYS");
	
	private final BaseMobileElement breakfast = new BaseMobileElement("BREAKFAST");
	private final BaseMobileElement lunch = new BaseMobileElement("LUNCH");
	private final BaseMobileElement dinner = new BaseMobileElement("DINNER");
	private final BaseMobileElement amSnack = new BaseMobileElement("AM SNACK");
	private final BaseMobileElement pmSnack = new BaseMobileElement("PM SNACK");
	private final BaseMobileElement drinks = new BaseMobileElement("DRINKS");

	
	//The accessibility id contains the store name, which is different per user, so need to use xPath find.
	private final BaseMobileElement pickup = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeButton[contains(@name,'PICKUP')]");
	private final BaseMobileElement delivery = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeButton[contains(@name,'DELIVERY')]");
	
	private final BaseMobileElement next = new BaseMobileElement("NEXT");


	
	public String select1200Calories()
	{
		try
		{
			calories1200.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find 1200 Calorie Button";
		}
		return "Success";
	}
	
	public boolean exists1200Calories()
	{
		try
		{
			return calories1200.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}

	public String select1500Calories()
	{
		try
		{
			calories1500.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find 1500 Calorie Button";
		}
		return "Success";
	}
	
	public boolean exists1500Calories()
	{
		try
		{
			return calories1500.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String select1800Calories()
	{
		try
		{
			calories1800.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find 1800 Calorie Button";
		}
		return "Success";
	}
	
	public boolean exists1800Calories()
	{
		try
		{
			return calories1800.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String selectSmallOption()
	{
		try
		{
			small.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Small Option on Whole 30.";
		}
		return "Success";
	}
	
	public boolean existsSmallOption()
	{
		try
		{
			return small.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String selectMediumOption()
	{
		try
		{
			medium.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Medium Option on Whole 30.";
		}
		return "Success";
	}
	
	public boolean existsMediumOption()
	{
		try
		{
			return medium.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String selectLargeOption()
	{
		try
		{
			large.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Large Option on Whole 30.";
		}
		return "Success";
	}
	
	public boolean existsLargeOption()
	{
		try
		{
			return large.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	
	public String select3Days()
	{
		try
		{
			threeDays.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Three Day Button";
		}
		return "Success";
	}
	
	public boolean exists3Days()
	{
		try
		{
			return threeDays.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String select5Days()
	{
		try
		{
			fiveDays.click();
		}
		catch (WebDriverException e)
		{
			return "Could not find Five Day Button";
		}
		return "Success";
	}
	
	public boolean exists5Days()
	{
		try
		{
			return fiveDays.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String select7Days()
	{
		try
		{
			sevenDays.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Seven Day Button";
		}
		return "Success";
	}
	
	public boolean exists7Days()
	{
		try
		{
			return sevenDays.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String selectPickup()
	{
		try
		{
			pickup.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Pickup Button";
		}
		return "Success";
	}
	
	public boolean existsPickup()
	{
		try
		{
			return pickup.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String selectDelivery()
	{
		try
		{
			delivery.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Delivery Button";
		}
		return "Success";
	}
	
	public boolean existsDelivery()
	{
		try
		{
			return delivery.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String selectNext()
	{
		try
		{
			next.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Next Button.";
		}
		return "Success";
	}
	
	/**
	 * From the Meal Plan Options Page, return True if the Next Button Exists.
	 * For testing that user is not able to continue to the menu until they have 
	 * made a selection for all meal plan options.
	 * @return
	 */
	public boolean nextButtonExists()
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
	
	public String createMealPlan(UserType user, PlanType planType, Size size, DaysPerWeek daysPerWeek, Fulfillment type, DayParts...dayParts)
	{
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, 4);
		int firstFulfillmentDay = today.get(Calendar.DAY_OF_MONTH);
		
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanningMenu mealPlanningMenu = new MealPlanningMenu();
		MealPlanningOptions mealPlanningOptions = new MealPlanningOptions();
		SnapHome snapHome = new SnapHome();
		
		String status = lowerNav.goToMealPlan();
		if (!status.equals("Success"))
		{
			return status;
		}
		
		status = snapHome.scrollMealPlanIntro();
		if (!status.equals("Success"))
		{
			return status;
		}
		
		status = mealPlanMainPage.selectPlanType(planType);
		if (!status.equals("Success"))
		{
			return status;
		}
		
		try
		{
			if (!planType.equals(PlanType.WHOLE30))
			{
				selectSize(size);
			}
			selectDaysPerWeek(daysPerWeek);
			for (int i=0;i<dayParts.length;i++)
			{
				toggleDayPart(dayParts[i]);
			}
			selectFulfillmentType(type);
			next.click();
		}
		catch (Exception e)
		{
			return "Could not select plan calories, days, and fulfillment type.";
		}
		
		try
		{
			mealPlanningMenu.checkOut();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the checkout button.";
		}
		
		
		if (mealPlanningOptions.selectFulfillementDate(firstFulfillmentDay).equals("Success"))
		{	
			mealPlanningOptions.clickSave();
		}
		else
		{
			return "Error Fulfillment Days";
		}
		
		selectNext();
		
		if (!mealPlanningOptions.addCreditCard("4000000000000077", "0321", "333").equals("Success"))
		{
			return "Error with credit card.";
		}
		
		mealPlanningOptions.startSubscription();
		
		allowNotifications();
		
		MealPlanningSubmission mealPlanningSubmission = new MealPlanningSubmission();
		if (!mealPlanningSubmission.setupSuccess())
		{
			return "Error on Submission.";
		}
		return "Success";
	}
	
	/**
	 * Helper Method to select the size based upon the user's input.
	 * @param size
	 */
	public void selectSize(Size size)
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
			
		case CALORIES_2000:
			calories2000.click();
			break;
			
		case SMALL:
			small.click();
			break;
			
		case MEDIUM:
			medium.click();
			break;
				
		case LARGE:
			large.click();
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * Helper Method to select the number of days per week based upon the user's input.
	 * @param size
	 */
	private void selectDaysPerWeek(DaysPerWeek days)
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
	
	public String toggleDayPart(DayParts dayPart)
	{
		BaseMobileElement dayPartElement = getDayPartElement(dayPart);
		try 
		{
			dayPartElement.click();
		} 
		catch (NoSuchElementException e) 
		{
			return "Could not find daypart element.";
		}
		return "Success";
	}
	
	/**
	 * 
	 */
	public boolean isDayPartSelected(DayParts dayPart)
	{
		return getDayPartElement(dayPart).getMobileElement().getAttribute("value") != null;
	}
	
	public BaseMobileElement getDayPartElement(DayParts dayPart)
	{
		switch (dayPart) {
		case BREAKFAST:
			return breakfast;
			
		case LUNCH:
			return lunch;
			
		case DINNER:
			return dinner;
			
		case AM_SNACK:
			return amSnack;
			
		case PM_SNACK:
			return pmSnack;
			
		case DRINKS:
			return drinks;
			
		default:
			return breakfast;
		}
	}
	
	/**
	 * Helper Method to select the fulfillment type based upon the user's input.
	 * @param size
	 */
	private void selectFulfillmentType(Fulfillment type)
	{
		switch (type) {
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
	
	public String createThreeDayMealPlanOLD(UserType user)
	{
		Calendar today = Calendar.getInstance();
		int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
		int firstFulfillmentDay = dayOfMonth + 4;
		
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanningMenu mealPlanningMenu = new MealPlanningMenu();
		MealPlanningOptions mealPlanningOptions = new MealPlanningOptions();
		
		try
		{
			lowerNav.goToMealPlan();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Meal Plan Link in the Lower Navigation.";
		}
		
		try 
		{
			mealPlanMainPage.selectHighProtein();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find High Protein Card on Meal Planning Main Page.";
		}
		
		try
		{
			calories1200.click();
			threeDays.click();
			pickup.click();
			next.click();
		}
		catch (Exception e)
		{
			return "Could not select plan calories, days, and fulfillment type.";
		}
		
		try
		{
			mealPlanningMenu.checkOut();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the checkout button.";
		}
		
		
		if (mealPlanningOptions.selectFulfillementDate(firstFulfillmentDay).equals("Success"))
		{	
			mealPlanningOptions.clickSave();
		}
		else
		{
			return "Error Fulfillment Days";
		}
		
		selectNext();
		
		if (!mealPlanningOptions.addCreditCard("4000000000000077", "0321", "333").equals("Success"))
		{
			return "Error with credit card.";
		}
		
		mealPlanningOptions.startSubscription();
		
		allowNotifications();
		
		MealPlanningSubmission mealPlanningSubmission = new MealPlanningSubmission();
		if (!mealPlanningSubmission.setupSuccess())
		{
			return "Error on Submission.";
		}
		return "Success";
	}
	
	public void waitForScreenToRefresh()
	{
		WWWDriver.pause(1000);
		
	}
}

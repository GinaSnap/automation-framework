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
	private final BaseMobileElement sixMeals = new BaseMobileElement("6 MEALS");
	private final BaseMobileElement twelveMeals = new BaseMobileElement("12 MEALS");

	private final BaseMobileElement threeDays = new BaseMobileElement("3 DAYS");
	private final BaseMobileElement fiveDays = new BaseMobileElement("5 DAYS");
	private final BaseMobileElement sevenDays = new BaseMobileElement("7 DAYS");
	
	private final BaseMobileElement breakfast = new BaseMobileElement("BREAKFAST");
	private final BaseMobileElement lunch = new BaseMobileElement("LUNCH");
	private final BaseMobileElement dinner = new BaseMobileElement("DINNER");
	private final BaseMobileElement amSnack = new BaseMobileElement("AM SNACK");
	private final BaseMobileElement pmSnack = new BaseMobileElement("PM SNACK");
	private final BaseMobileElement drinks = new BaseMobileElement("DRINKS");
	
	private final BaseMobileElement pickup = new BaseMobileElement("editPickupButton");
	private final BaseMobileElement delivery = new BaseMobileElement("editDeliveryButton");
	private final BaseMobileElement shipping = new BaseMobileElement("editShippingButton");
	private final BaseMobileElement moreOptions = new BaseMobileElement("MORE OPTIONS");
	private final BaseMobileElement shippingOnlyText = new BaseMobileElement("This plan is currently only available for direct shipping via FedEx.");
	private final BaseMobileElement localOnlyText = new BaseMobileElement("This plan is currently only available for local pickup or delivery.");
	
	private final BaseMobileElement enterAddressNewUser = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeTextField[@value='enter new shipping address']");
	private final BaseMobileElement enterAddressExistingUser = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeTextField[@value='street number & name']");

	private final BaseMobileElement twoFourZeroHoneycombCir = new BaseMobileElement("240 Honeycomb Cir");
	private final BaseMobileElement saveShippingAddress = new BaseMobileElement("save");
	private final BaseMobileElement confirmShippingAddress = new BaseMobileElement("CONFIRM");

	private final BaseMobileElement startBuildingPlan = new BaseMobileElement("start");
	private final BaseMobileElement viewAndCustomize = new BaseMobileElement("start");


	public String selectMealPlanSizeOption(Size size)
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
				
			case SIX_MEALS:
				sixMeals.click();
				break;
				
			case TWELVE_MEALS:
				twelveMeals.click();
				break;

			default:
				break;
			}
		}
		catch (NoSuchElementException e)
		{
			return "Meal Plan Size Chosen was not displayed.";
		}
		return "Success";
	}
	
	public boolean mealPlanSizeOptionExists(Size size)
	{
		switch (size) {
		case CALORIES_1200:
			return calories1200.exists();
			
		case CALORIES_1500:
			return calories1500.exists();
			
		case CALORIES_1800:
			return calories1800.exists();
			
		case SIX_MEALS:
			return sixMeals.exists();
			
		case TWELVE_MEALS:
			return twelveMeals.exists();

		default:
			return false;
		}
	}
	
	public String selectDaysPerWeek(DaysPerWeek daysPerWeek)
	{
		try
		{
			switch (daysPerWeek) {
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
		catch (NoSuchElementException e)
		{
			return "Could not find Days Per Week Option Specified.";
		}
		return "Success";
	}
	
	public boolean daysPerWeekOptionExists(DaysPerWeek daysPerWeek)
	{
		switch (daysPerWeek) {
		case THREE_DAYS:
			return threeDays.exists();
		case FIVE_DAYS:
			return fiveDays.exists();
		case SEVEN_DAYS:
			return sevenDays.exists();
		default:
			return false;
		}
	}
	
	public String selectFulfillmentOption(Fulfillment fulfillment)
	{
		clickMoreOptions();
		
		try
		{
			switch (fulfillment) {
			case SHIPPING:
				shipping.click();
				break;
			case DELIVERY:
				delivery.click();
				break;
			case PICKUP:
				pickup.click();
				break;
			default:
				break;
			}
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Fulfillment Option Chosen.";
		}
		return "Success";
	}
	
	public String enterShippingAddress(String address) {
		boolean foundAddressField = true;
		if (twoFourZeroHoneycombCir.exists()) {
			twoFourZeroHoneycombCir.click();
		}
		else {
			//Enter the address
			try {
				enterAddressNewUser.setWebValue(address);
			}
			catch (Exception e) {
				foundAddressField=false;
			}
			
			if (!foundAddressField) {
				try {
					enterAddressExistingUser.setWebValue(address);
				}
				catch (Exception e) {
					return "Could not find the shipping address field.";
				}
			}
			
			//Click on the matching item from the list.
			twoFourZeroHoneycombCir.click();
			saveShippingAddress.click();
			
			//Confirm whatever they send back at this point
			confirmShippingAddress.waitUntilClickable();
			confirmShippingAddress.click();
		}
		
		return "Success";
	}
	
	public boolean fulfillmentOptionExists(Fulfillment fulfillment)
	{
		switch (fulfillment) {
		case SHIPPING:
			return shipping.exists();
		case DELIVERY:
			return delivery.exists();
		case PICKUP:
			return pickup.exists();

		default:
			return false;
		}
	}
	
	public String selectViewMenu()
	{
		try
		{
			viewAndCustomize.click();
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
	public boolean viewMenuExists()
	{
		try
		{
			return viewAndCustomize.exists();
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
		
		status = mealPlanMainPage.selectPlanType(planType);
		if (!status.equals("Success"))
		{
			return status;
		}
		
		startBuildingPlan();
		
		selectFulfillmentOption(type);
		
		if (type.equals(Fulfillment.SHIPPING)) {
			enterShippingAddress("240 Honeycomb Circle");
			
			selectSize(size);
		}
		else {
			if (!planType.equals(PlanType.WHOLE30))
			{
				selectSize(size);
			}
			selectDaysPerWeek(daysPerWeek);
			for (int i=0;i<dayParts.length;i++)
			{
				toggleDayPart(dayParts[i]);
			}
		}
		viewAndCustomize.click();

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
		
		selectViewMenu();
		
		if (!mealPlanningOptions.addCreditCard("4000000000000077", "0321", "333").equals("Success"))
		{
			return "Error with credit card.";
		}
		
		mealPlanningOptions.startSubscription();
				
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
			
		case SIX_MEALS:
			sixMeals.click();
			break;
			
		case TWELVE_MEALS:
			twelveMeals.click();
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
			viewAndCustomize.click();
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
		
		selectViewMenu();
		
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
	
	public boolean startBuildingPlan() {
		startBuildingPlan.click();
		
		return true;
	}
	
	public boolean shippingOnlyTextDisplayed() {
		return shippingOnlyText.exists();
	}
	
	public boolean localOnlyTextDisplayed() {
		return localOnlyText.exists();
	}
	
	public boolean moreOptionsDisplayed() {
		return moreOptions.exists();
	}
	
	public String clickMoreOptions() {
		try {
			moreOptions.click();
		}
		catch (NoSuchElementException e) {
			return "Could not find the More Options button.";
		}
		return "Success";
	}
}

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
	
	private final BaseMobileElement selectPickup = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeStaticText[contains(@name,'PICKUP')]");
	private final BaseMobileElement editPickup = new BaseMobileElement("editPickupButton");

	private final BaseMobileElement selectDelivery = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeStaticText[contains(@name,'DELIVERY')]");
	private final BaseMobileElement editDelivery = new BaseMobileElement("editDeliveryButton");

	private final BaseMobileElement selectShipping = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeStaticText[contains(@name,'SHIPPING')]");
	private final BaseMobileElement editShipping = new BaseMobileElement("editShippingButton");

	
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
	
	private final BaseMobileElement addressValidationServiceDown = new BaseMobileElement("Address validation service is currently down");


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
		try
		{
			switch (fulfillment) {
			case SHIPPING:
				selectShipping.click();
				break;
			case DELIVERY:
				selectDelivery.click();
				break;
			case PICKUP:
				selectPickup.click();
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
	
	public String editFulfillmentOption(Fulfillment fulfillment)
	{
		
		try
		{
			switch (fulfillment) {
			case SHIPPING:
				editShipping.click();
				break;
			case DELIVERY:
				editDelivery.click();
				break;
			case PICKUP:
				editPickup.click();
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
			try {
				twoFourZeroHoneycombCir.click();
			}
			catch (NoSuchElementException e) {
				return "Could not find expected matching address.";
			}
			
			try {
				saveShippingAddress.click();
			} catch (NoSuchElementException e) {
				return "Could not find Save Shipping address button.";
			}
			
			//Confirm whatever they send back at this point
			try {
				confirmShippingAddress.waitUntilClickable();
				confirmShippingAddress.click();
			} catch (NoSuchElementException e) {
				if (addressValidationServiceDown.exists()) {
					return "Address Validation Service is down.";
				}
				else {
					return "Could not find Confirm Address button.";
				}
			}
		}
		
		return "Success";
	}
	
	public boolean fulfillmentOptionExists(Fulfillment fulfillment)
	{
		switch (fulfillment) {
		case SHIPPING:
			return selectShipping.exists();
		case DELIVERY:
			return selectDelivery.exists();
		case PICKUP:
			return selectPickup.exists();

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
			return viewAndCustomize.isVisible();
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
		
		status = selectFulfillmentOption(type);
		if (!status.equals("Success")) {
			return String.format("Could not find the [%s] fulfillment option.", type.toString());
		}
		
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
		
		status = mealPlanningOptions.selectFulfillementDate(firstFulfillmentDay);
		if (!status.equals("Success"))
		{	
			return status;
		}
			
		status = mealPlanningOptions.addCreditCard("4000000000000077", "0321", "333");
		if (!status.equals("Success"))
		{
			return status;
		}
		
		status = mealPlanningOptions.startSubscription();
		if (!status.equals("Success"))
		{
			return status;
		}
				
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

}

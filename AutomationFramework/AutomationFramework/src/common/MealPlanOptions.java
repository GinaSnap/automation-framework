package common;

public class MealPlanOptions {
	
	public enum PlanType
	{
		HIGH_PROTEIN,
		LOW_CARB,
		BALANCE,
		WHOLE30,
		PALEO,
		CAMP_GLADIATOR,
		BUILD_YOUR_OWN,
		VEGAN,
		KETO,
		UNDEFINED;
	}
	
	public enum Size
	{
		CALORIES_1200,
		CALORIES_1500,
		CALORIES_1800,
		CALORIES_2000,
		SMALL,
		MEDIUM,
		LARGE,
		SIX_MEALS,
		TWELVE_MEALS
	}
	
	public enum DaysPerWeek
	{
		THREE_DAYS,
		FIVE_DAYS,
		SEVEN_DAYS
	}
	
	public enum Fulfillment
	{
		PICKUP,
		DELIVERY,
		SHIPPING
	}
	
	public enum DayParts
	{
		BREAKFAST,
		LUNCH,
		DINNER,
		AM_SNACK,
		PM_SNACK,
		DRINKS
	}

}

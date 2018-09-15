package test;

import java.util.NoSuchElementException;

import common.PlanType;
import element.BaseWebElement;
import element.WebMealPlanCardElement;
import framework.FindMethod;
import page.BasePage;

public class MealPlanPage extends BasePage {
	private BaseWebElement viewPlansElement = new BaseWebElement(FindMethod.XPATH,"//button[contains(text(),'View plans')]");
	private BaseWebElement manageMealPlan = new BaseWebElement(FindMethod.CLASSNAME, "manage-plan-button");
	private WebMealPlanCardElement highProteinMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME, "card-high-protein");
	private WebMealPlanCardElement lowCarbMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-low-carb");
	private WebMealPlanCardElement balanceMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-balance");
	private WebMealPlanCardElement whole30MealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-whole30");
	private WebMealPlanCardElement paleoMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-paleo");
	private WebMealPlanCardElement campGladMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-camp-gladiator");

	
	public String clickViewPlans()
	{
		try
		{
			viewPlansElement.click();
		}
		catch (NoSuchElementException e)
		{
			return "View Plans button does not exist on the meal plans page.";
		}
		return "Success";
	}

	public String clickManageMealPlan()
	{
		try
		{
			manageMealPlan.click();
		}
		catch (NoSuchElementException e)
		{
			return "Manage Meal Plan button did not exist.";
		}
		return "Success";
	}
	
	public boolean manageMealPlanExists()
	{
		try
		{
			return manageMealPlan.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String orderMealPlan(PlanType type)
	{
		switch (type) {
		case HIGH_PROTEIN:
			return highProteinMealPlan.clickOrderNow();
		case LOW_CARB:
			return lowCarbMealPlan.clickOrderNow();
		case BALANCE:
			return balanceMealPlan.clickOrderNow();
		case WHOLE30:
			return whole30MealPlan.clickOrderNow();
		case PALEO:
			return paleoMealPlan.clickOrderNow();
		case CAMP_GLADIATOR:
			return campGladMealPlan.clickOrderNow();
		default:
			return "Plan Type selected is invalid.";
		}
	}
}

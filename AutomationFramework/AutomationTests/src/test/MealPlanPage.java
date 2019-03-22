package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.PlanType;
import element.BaseWebElement;
import element.WebMealPlanCardElement;
import framework.FindMethod;
import framework.WWWDriver;
import page.BasePage;
import page.MealPlanMenuPage;

public class MealPlanPage extends BasePage {
	private BaseWebElement viewPlansElement = new BaseWebElement(FindMethod.XPATH,"//button[contains(text(),'View plans')]");
	private BaseWebElement manageMealPlan = new BaseWebElement(FindMethod.CLASSNAME, "manage-plan-button");
	private WebMealPlanCardElement highProteinMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME, "card-high-protein");
	private WebMealPlanCardElement lowCarbMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-low-carb");
	private WebMealPlanCardElement balanceMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-balance");
	private WebMealPlanCardElement whole30MealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-whole30");
	private WebMealPlanCardElement paleoMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-paleo");
	//private WebMealPlanCardElement campGladMealPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME,"card-camp-gladiator");
	private WebMealPlanCardElement buildYourOwnPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME, "card-build_your_own");
	private WebMealPlanCardElement vegetarianPlan = new WebMealPlanCardElement(FindMethod.CLASSNAME, "card-vegetarian");
	
	private List<WebMealPlanCardElement> mealPlanOrder_Stage = new ArrayList<WebMealPlanCardElement>();
	
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
		scrollToMealPlan(type);
		WWWDriver.pause(5000);
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
		case BUILD_YOUR_OWN:
			return buildYourOwnPlan.clickOrderNow();
		case VEGETARIAN:
			return vegetarianPlan.clickOrderNow();
		default:
			return "Plan Type selected is invalid.";
		}
	}
	
	private void setMealPlanOrder()
	{
		mealPlanOrder_Stage.add(buildYourOwnPlan);
		mealPlanOrder_Stage.add(lowCarbMealPlan);
		mealPlanOrder_Stage.add(balanceMealPlan);
		mealPlanOrder_Stage.add(vegetarianPlan);
		mealPlanOrder_Stage.add(highProteinMealPlan);
		mealPlanOrder_Stage.add(whole30MealPlan);
		mealPlanOrder_Stage.add(paleoMealPlan);
	}
	
	public boolean scrollToMealPlan(PlanType type)
	{
		setMealPlanOrder();
		
		WebMealPlanCardElement mealPlanCard = null;
		switch (type) {
		case HIGH_PROTEIN:
			mealPlanCard = highProteinMealPlan;
			break;
		case LOW_CARB:
			mealPlanCard = lowCarbMealPlan;
			break;
		case BALANCE:
			mealPlanCard = balanceMealPlan;
			break;
		case WHOLE30:
			mealPlanCard = whole30MealPlan;
			break;
		case PALEO:
			mealPlanCard = paleoMealPlan;
			break;
		case BUILD_YOUR_OWN:
			mealPlanCard = buildYourOwnPlan;
			break;
		case VEGETARIAN:
			mealPlanCard = vegetarianPlan;
			break;
		default:
			break;
		}
		
		for (WebMealPlanCardElement card : mealPlanOrder_Stage)
		{
			card.click();
			WWWDriver.pause(2000);
			if (mealPlanCard.exists())
				return true;
		}
		
		return false;
	}
}

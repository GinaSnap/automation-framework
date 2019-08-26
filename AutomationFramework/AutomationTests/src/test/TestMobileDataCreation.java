package test;

import org.junit.Test;

import common.UserType;
import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.PlanType;
import common.MealPlanOptions.Size;
import mobilepage.MealPlanningPage;
import mobilepage.SnapHome;

public class TestMobileDataCreation extends MobileTestCase {
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateLocalPlan_HighProtein()
	{
		createLocalPlan(PlanType.HIGH_PROTEIN);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateLocalPlan_LowCarb()
	{
		createLocalPlan(PlanType.LOW_CARB);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateLocalPlan_Keto()
	{
		createLocalPlan(PlanType.KETO);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateLocalPlan_Paleo()
	{
		createLocalPlan(PlanType.PALEO);
	}
	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateLocalPlan_Balance()
//	{
//		createLocalPlan(PlanType.BALANCE);
//	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateLocalPlan_Whole30()
	{
		createLocalPlan(PlanType.WHOLE30);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateShippingPlan_HighProtein()
	{
		createShippingPlan(PlanType.HIGH_PROTEIN);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateShippingPlan_LowCarb()
	{
		createShippingPlan(PlanType.LOW_CARB);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateShippingPlan_Keto()
	{
		createShippingPlan(PlanType.KETO);
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateShippingPlan_Paleo()
	{
		createShippingPlan(PlanType.PALEO);
	}
	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateLocalPlan_Balance()
//	{
//		createLocalPlan(PlanType.BALANCE);
//	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateShippingPlan_Whole30()
	{
		createShippingPlan(PlanType.WHOLE30);
	}
	
	private void createLocalPlan(PlanType type) {
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com", DefaultAustinZip);
		step(String.format("Setting up %s Meal Plan for %s with email %s", type.toString(), newUser.getUsername(), newUser.getEmail()));
		
		SnapHome snapHome = new SnapHome();
		step("Create a new account via Meal Plan Page.");
		String status = snapHome.createAccountViaMealPlanning_LocalUser(newUser);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		status = mealPlanningPage.createMealPlan(newUser, type, Size.CALORIES_1500, DaysPerWeek.FIVE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
	}
	
	private void createShippingPlan(PlanType type) {
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com", DefaultAustinZip);
		step(String.format("Setting up %s Meal Plan for %s with email %s", type.toString(), newUser.getUsername(), newUser.getEmail()));
		
		SnapHome snapHome = new SnapHome();
		step("Create a new account via Meal Plan Page.");
		String status = snapHome.createAccountViaMealPlanning_LocalUser(newUser);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		status = mealPlanningPage.createMealPlan(newUser, type, Size.SIX_MEALS, DaysPerWeek.FIVE_DAYS, Fulfillment.SHIPPING, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
	}
}

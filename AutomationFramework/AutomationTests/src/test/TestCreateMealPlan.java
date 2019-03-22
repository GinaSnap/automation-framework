package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.UserType;
import common.Location;
import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.PlanType;
import common.MealPlanOptions.Size;
import mobilepage.MealPlanningPage;
import mobilepage.SnapHome;

public class TestCreateMealPlan extends MobileTestCase {
	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateNewMealPlan_HighProtein_1200_3Day()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up High Protein Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.HIGH_PROTEIN, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateNewMealPlan_LowCarb_1200_3Day()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Low Carb Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.LOW_CARB, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateNewMealPlan_Balance_1200_3Day()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Balance Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.BALANCE, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateNewMealPlan_PALEO_1200_3Day()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Paleo Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.PALEO, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateNewMealPlan_Whole30_1500_5Day()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Whole30 Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.WHOLE30, Size.SMALL, DaysPerWeek.SEVEN_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER, DayParts.DRINKS));
//		
//	}
//	
//	/**
//	 *   Create New User by using the Meal Planning Onboarding Screens.
//	 *   Create New Meal Plan
//	 *   3 Day, 1200 Cal, Pickup
//	 *   Verify No errors on creating subscription.
//	 */
//	@Test
//	public void testCreateNewMealPlan_CG_2000_3Day()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Camp Gladiator Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.CAMP_GLADIATOR, Size.CALORIES_2000, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
	
//	@Test
//	public void testCreateNewMealPlan_HP()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Whole30 Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaLogin(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.HIGH_PROTEIN, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	@Test
//	public void testCreateNewMealPlan_LC()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Whole30 Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaLogin(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan Low Carb, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.LOW_CARB, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	@Test
//	public void testCreateNewMealPlan_B()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Whole30 Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaLogin(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.BALANCE, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	@Test
//	public void testCreateNewMealPlan_P()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Whole30 Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaLogin(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.PALEO, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}
//	
//	@Test
//	public void testCreateNewMealPlan_W30()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("Setting up Whole30 Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaLogin(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.WHOLE30, Size.CALORIES_1500, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
//		
//	}

	
	@Test
	public void testCreateNewMealPlan()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		System.out.printf("User: %s Email: %s", newUser.getUsername(), newUser.getEmail());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaLogin(newUser, Location.HOUSTON));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		assertEquals("Verify:  Create Meal Plan.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.HIGH_PROTEIN, Size.CALORIES_1500, DaysPerWeek.SEVEN_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
		
	}

}

package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.MailUtilities;
import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Size;
import common.PlanType;
import common.UserType;
import page.FulfillmentPage;
import page.LoginPage;
import page.MealPlanCheckoutPage;
import page.MealPlanConfirmationPage;
import page.MealPlanMenuPage;
import page.MealPlanOptionsPage;
import page.SnapHome;

/**
 * Tests for the new user sign up and existing user login screens.
 * @author GMitchell
 *
 */
public class TestWebCreateMealPlan extends BaseTestCase {

	/**
	 * Create a new user and new meal HIGH PROTEIN meal plan.
	 */
	@Test
	public void testCreateMealPlan_FivePercent_Tier () {
		createMealPlan(PlanType.LOW_CARB, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
	}
	
	/**
	 * Create a new user and new meal LOW CARB meal plan.
	 */
	@Test
	public void testCreateMealPlan_TenPercent_Tier () {
		createMealPlan(PlanType.BALANCE, Size.CALORIES_1500, DaysPerWeek.FIVE_DAYS, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
	}
	
	/**
	 * Create a new user and new meal BALANCE meal plan.
	 */
	@Test
	public void testCreateMealPlan_Balance () {
		createMealPlan(PlanType.BALANCE, Size.CALORIES_1800, DaysPerWeek.THREE_DAYS, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
	}
	
	/**
	 * Create a new user and new meal PALEO meal plan.
	 */
	@Test
	public void testCreateMealPlan_Paleo () {
		createMealPlan(PlanType.PALEO, Size.CALORIES_1200, DaysPerWeek.SEVEN_DAYS, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
	}
	
	/**
	 * Create a new user and new meal WHOLE30 meal plan.
	 */
	@Test
	public void testCreateMealPlan_Whole30 () {
		createMealPlan(PlanType.WHOLE30, Size.CALORIES_1500, DaysPerWeek.THREE_DAYS, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
	}
	
	/**
	 * Create a new user and new meal CAMP GLAD meal plan.
	 */
	@Test
	public void testCreateMealPlan_CampGladiator () {
		createMealPlan(PlanType.CAMP_GLADIATOR, Size.CALORIES_1800, DaysPerWeek.SEVEN_DAYS, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
	}
	
	/**
	 * Helper Method for creating a meal plan with various options.
	 * @param planType Enter the plan type.
	 * @param size Enter size.  For Whole30 this value will not be used.
	 * @param daysPerWeek Enter 3 or 7 Days Per Week.  I'm having trouble with 5 Days.
	 * @param dayParts Enter all day parts that should be included (Break, lunch, dinner, am snack, pm snack, drinks)
	 */
	private void createMealPlan(PlanType planType, Size size, DaysPerWeek daysPerWeek, DayParts...dayParts)
	{
		String uniqueString = util.getUniqueString(7);
		String uniquePhone = getUniquePhone();
		UserType newUser = new UserType(uniquePhone, DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.printf("New user created:  %s with email %s.", uniquePhone, uniqueString + "@snapkitchen.com");
		
		LoginPage loginPage = new LoginPage();
		assertEquals("Step:  Create new user and loyalty account.", "Success", loginPage.createAccount(newUser));
		
		SnapHome snapHome = new SnapHome();
		assertEquals("Step:  Click Meal Plans from top navigation.", "Success", snapHome.goToTopNavMealPlans());
		
		MealPlanPage mealPlanPage = new MealPlanPage();
		assertEquals("Step:  Click View Plans on the Meal Plans Page.", "Success", mealPlanPage.clickViewPlans());
		assertEquals("Step:  Click Order Now on the chosen meal plan card.","Success",mealPlanPage.orderMealPlan(planType));
		
		MealPlanOptionsPage optionsPage = new MealPlanOptionsPage();
		assertEquals("Step:  Select Calories.", "Success", optionsPage.selectCalories(size));
		assertEquals("Step:  Select Days Per Week.", "Success", optionsPage.selectDays(daysPerWeek));  //Only 3 Days and 7 Days will work for now.
		for (int i=0;i<dayParts.length;i++)
		{
			assertEquals("Step:  Select Breakfast.", "Success", optionsPage.selectDayParts(dayParts[i]));
		}
		//optionsPage.selectFulfillment(Fulfillment.PICKUP);
		
		assertEquals("Step:  Click Next Button.", "Success", optionsPage.clickNext());
		
		MealPlanMenuPage menuPage = new MealPlanMenuPage();
		assertEquals("Step:  Click Checkout.", "Success", menuPage.clickCheckout());
		
		FulfillmentPage fulfillmentPage = new FulfillmentPage();
		assertEquals("Step:  Select Pickup or Delivery.", "Success", fulfillmentPage.clickContinueToCheckout());
		
		MealPlanCheckoutPage checkoutPage = new MealPlanCheckoutPage();
		assertEquals("Step:  Enter Fulfillment Details.", "Success", checkoutPage.enterFulfillmentDetails());
		assertEquals("Step:  Add Credit Card if one does not exist.", "Success", checkoutPage.addNewCreditCard(validVisa1));
		assertEquals("Step:  Click Start Subscription.", "Success", checkoutPage.startSubscription());
		
		MealPlanConfirmationPage confirmationPage = new MealPlanConfirmationPage();
		assertEquals("Step:  Click Manage Subscription.","Success", confirmationPage.clickManageSubscription());
		
		//checkout-card__title will say "Saving your subscription changes..." until processing it complete
	}
	

	/**
	 * Email Test
	 */
	@Test
	public void testTestEmail() {
		MailUtilities mailUtilities = new MailUtilities();
		String msgOriginal = mailUtilities.getMessage("let us do the work");
		String msgNoHTML = msgOriginal.replaceAll("\\<.*?\\>", "");
		System.out.printf("MESSAGE:  %s", msgNoHTML);
		
		mailUtilities.deleteMessage("let us do the work");
	}
	
}

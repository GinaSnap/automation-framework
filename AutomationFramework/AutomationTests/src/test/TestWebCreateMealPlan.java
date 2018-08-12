package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Size;
import common.MailUtilities;
import common.PlanType;
import page.FulfillmentPage;
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
	 * Create a new user with a unique phone number.  Access the profile page and verify that all data is correct.
	 */
	@Test
	public void testCreateMealPlan() {
		
//		String uniqueString = util.getUniqueString(7);
//		String uniquePhone = getUniquePhone();
//		UserType newUser = new UserType(uniquePhone, DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.printf("New user created:  %s with email %s.", uniquePhone, uniqueString + "@snapkitchen.com");
//		
//		LoginPage loginPage = new LoginPage();
//		loginPage.createAccount(newUser);
		
		login(UserNoMealPlan);
		
		SnapHome snapHome = new SnapHome();
		assertEquals("Step:  Click Meal Plans from top navigation.", "Success", snapHome.goToTopNavMealPlans());
		
		MealPlanPage mealPlanPage = new MealPlanPage();
		mealPlanPage.clickViewPlans();
		mealPlanPage.orderMealPlan(PlanType.BALANCE);
		
		MealPlanOptionsPage optionsPage = new MealPlanOptionsPage();
		optionsPage.selectCalories(Size.CALORIES_1200);
		optionsPage.selectDays(DaysPerWeek.THREE_DAYS);  //Only 3 Days and 7 Days will work for now.
		optionsPage.selectDayParts(DayParts.BREAKFAST);
		optionsPage.selectDayParts(DayParts.LUNCH);
		optionsPage.selectDayParts(DayParts.DINNER);
		//optionsPage.selectFulfillment(Fulfillment.PICKUP);
		
		assertEquals("Step:  Click Next Button.", "Success", optionsPage.clickNext());
		
		MealPlanMenuPage menuPage = new MealPlanMenuPage();
		assertEquals("Step:  Click Checkout.", "Success", menuPage.clickCheckout());
		
		FulfillmentPage fulfillmentPage = new FulfillmentPage();
		//assertEquals("Step:  Select Pickup or Delivery.", "Success", fulfillmentPage.clickContinueToCheckout());
		
		MealPlanCheckoutPage checkoutPage = new MealPlanCheckoutPage();
		assertEquals("Step:  Enter Fulfillment Details.", "Success", checkoutPage.enterFulfillmentDetails());
		assertEquals("Step:  Click Start Subscription.", "Success", checkoutPage.startSubscription());
		
		MealPlanConfirmationPage confirmationPage = new MealPlanConfirmationPage();
		assertEquals("Step:  Click Manage Subscription.","Success", confirmationPage.clickManageSubscription());
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

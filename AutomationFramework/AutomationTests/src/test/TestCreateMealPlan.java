package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.PlanType;
import common.MealPlanOptions.Size;
import common.UserType;
import mobilepage.MealPlanMainPage;
import mobilepage.MealPlanningPage;
import mobilepage.SnapHome;
import page.MealPlanLifestylePage;

public class TestCreateMealPlan extends MobileTestCase {
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateNewMealPlan_Shipping_6Meals()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com", DefaultNationalSW);
		step(String.format("Setting up Shipping Plan for %s with email %s", newUser.getUsername(), newUser.getEmail()));
		
		SnapHome snapHome = new SnapHome();
		step("Create Account via Meal Plan Page.");
		String status = snapHome.createAccountViaMealPlanning_ShippingUser(newUser);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		status = mealPlanningPage.createMealPlan(newUser, PlanType.LOW_CARB, Size.SIX_MEALS, DaysPerWeek.THREE_DAYS, Fulfillment.SHIPPING, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateNewMealPlan_Local_MultiSize()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com", DefaultAustinZip);
		step(String.format("Setting up High Protein Meal Plan for %s with email %s", newUser.getUsername(), newUser.getEmail()));
		
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
		status = mealPlanningPage.createMealPlan(newUser, PlanType.HIGH_PROTEIN, Size.CALORIES_1500, DaysPerWeek.FIVE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
	}
	
	@Test
	public void testVerifyMealPlanOptions_Shipping()
	{		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanLifestylePage mealPlanLifestylePage = new MealPlanLifestylePage();
		
		step("Login to the app with an existing account.");
		String status=login(ShippingCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Click On Meal Plans");
		status = mealPlanningPage.goToMealPlan();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click on the Low Carb Meal Plan");
		status = mealPlanMainPage.selectLowCarb();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click Build Subscription");
		status = mealPlanLifestylePage.startBuildingSubscription();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest("Success");
		}
		
		step("Click Shipping Fulfillment Type");
		status = mealPlanningPage.selectFulfillmentOption(Fulfillment.SHIPPING);
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Select Address from List");
		status = mealPlanningPage.enterShippingAddress("240 Honeycomb Circle");
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Verify that 6 Meal & 12 Meal Size Options exist.");
		if (mealPlanningPage.mealPlanSizeOptionExists(Size.SIX_MEALS) && mealPlanningPage.mealPlanSizeOptionExists(Size.TWELVE_MEALS))
		{
			passTest("6 and 12 Meal Plan Sizes Exist");
		}
		else
		{
			failTest("6 and 12 Meal Plan Options are not displayed.");
		}
		
		step("Verify that 1200, 1500, 1800 Size options are not displayed.");
		if (mealPlanningPage.mealPlanSizeOptionExists(Size.CALORIES_1200) || mealPlanningPage.mealPlanSizeOptionExists(Size.CALORIES_1500) || mealPlanningPage.mealPlanSizeOptionExists(Size.CALORIES_1800) )
		{
			failTest("1200, 1500, and 1800 Size options are displayed.");
		}
		else
		{
			passTest("1200, 1500, and 1800 Size Options were not displayed.");
		}
		
		step("Verify that View Menu is not displayed after selecting only Fulfillment.");
		if (mealPlanningPage.viewMenuExists())
		{
			failTestAndContinue("View Menu is displayed before required options are selected.");
		}
		else
		{
			passTest("View menu is not displayed when only Fulfillment Option is chosen.");
		}
		
		step("Select 6 Meals");
		status = mealPlanningPage.selectMealPlanSizeOption(Size.SIX_MEALS);
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Verify that View Menu is displayed after selecting Fulfillment and Size.");
		if (mealPlanningPage.viewMenuExists())
		{
			passTest("View Menu is displayed after required options are selected.");
		}
		else
		{
			failTest("View Menu is not displayed after required options are selected.");
		}
		
	}
	
	@Test
	public void testVerifyVeganShippingOnly()
	{		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanLifestylePage mealPlanLifestylePage = new MealPlanLifestylePage();
		
		step("Login to the app with an existing account.");
		String status=login(ShippingCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Click On Meal Plans");
		status = mealPlanningPage.goToMealPlan();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click on the Vegan Meal Plan");
		status = mealPlanMainPage.selectPlanType(PlanType.VEGAN);
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click Build Subscription");
		status = mealPlanLifestylePage.startBuildingSubscription();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest("Success");
		}
		
		step("Verify that Delivery Option does not exist on a shipping only plan.");
		if (!mealPlanningPage.fulfillmentOptionExists(Fulfillment.DELIVERY)) {
			passTest("Success");
		}
		else {
			failTest("Delivery Option was listed.");
		}
		
		step("Verify that Pickup Option does not exist on a shipping only plan.");
		if (!mealPlanningPage.fulfillmentOptionExists(Fulfillment.PICKUP)) {
			passTest("Success");
		}
		else {
			failTest("Pickup Option was listed.");
		}
		
		step("Verify that Shipping Option is listed on a Shipping Only Meal Plan.");
		if (mealPlanningPage.fulfillmentOptionExists(Fulfillment.SHIPPING)) {
			passTest("Success");
		}
		else {
			failTest("Shipping Option was not listed.");
		}
		
		step("Verify that the 'shipping only' text exists under the Fulfillment Option.");
		if (mealPlanningPage.shippingOnlyTextDisplayed()) {
			passTest("Success");
		}
		else {
			failTest("Text was not displayed.");
		}
	}
	
	@Test
	public void testVerifyKetoLocalOnly()
	{		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanLifestylePage mealPlanLifestylePage = new MealPlanLifestylePage();
		
		step("Login to the app with an existing account.");
		String status=login(LocalCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Click On Meal Plans");
		status = mealPlanningPage.goToMealPlan();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click on the Keto Meal Plan");
		status = mealPlanMainPage.selectPlanType(PlanType.KETO);
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click Build Subscription");
		status = mealPlanLifestylePage.startBuildingSubscription();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest("Success");
		}
		
		step("Verify that Delivery Option exists on a Local Only Plan.");
		if (mealPlanningPage.fulfillmentOptionExists(Fulfillment.DELIVERY)) {
			passTest("Success");
		}
		else {
			failTest("Delivery Option was not listed.");
		}
		
		step("Verify that Pickup Option exists on a Local Only Plan.");
		if (mealPlanningPage.fulfillmentOptionExists(Fulfillment.PICKUP)) {
			passTest("Success");
		}
		else {
			failTest("Pickup Option was not listed.");
		}
		
		step("Verify that Shipping Option is not listed on a Local Only Meal Plan.");
		if (!mealPlanningPage.fulfillmentOptionExists(Fulfillment.SHIPPING)) {
			passTest("Success");
		}
		else {
			failTest("Shipping Option was listed.");
		}
		
		step("Verify that the 'local only' text exists under the Fulfillment Option.");
		if (mealPlanningPage.localOnlyTextDisplayed()) {
			passTest("Success");
		}
		else {
			failTest("Text was not displayed.");
		}
	}
	
	@Test
	public void testVerifyFulfillmentOptsShippingCustomer()
	{		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanLifestylePage mealPlanLifestylePage = new MealPlanLifestylePage();
		
		step("Login to the app with an existing account.");
		String status=login(ShippingCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Click On Meal Plans");
		status = mealPlanningPage.goToMealPlan();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click on the High Protein Meal Plan");
		status = mealPlanMainPage.selectPlanType(PlanType.HIGH_PROTEIN);
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Click Build Subscription");
		status = mealPlanLifestylePage.startBuildingSubscription();
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest("Success");
		}
		
		step("Verify that Delivery Option is not displayed by default for shipping customers.");
		if (!mealPlanningPage.fulfillmentOptionExists(Fulfillment.DELIVERY)) {
			passTest("Success");
		}
		else {
			failTest("Delivery Option was displayed.");
		}
		
		step("Verify that Pickup Option is not displayed by default for shipping customers.");
		if (!mealPlanningPage.fulfillmentOptionExists(Fulfillment.PICKUP)) {
			passTest("Success");
		}
		else {
			failTest("Pickup Option was displayed.");
		}
		
		step("Verify that Shipping Option is displayed by default for shipping customers.");
		if (mealPlanningPage.fulfillmentOptionExists(Fulfillment.SHIPPING)) {
			passTest("Success");
		}
		else {
			failTest("Shipping Option was not displayed.");
		}

	}

}

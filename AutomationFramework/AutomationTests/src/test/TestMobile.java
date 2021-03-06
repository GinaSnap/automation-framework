package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.MealPlanOptions.DayParts;
import common.MealPlanOptions.DaysPerWeek;
import common.MealPlanOptions.Fulfillment;
import common.MealPlanOptions.PlanType;
import common.MealPlanOptions.Size;
import common.MenuType;
import common.UserType;
import mobilepage.AccountHome;
import mobilepage.BasketPage;
import mobilepage.CheckoutPage;
import mobilepage.LowerNavPage;
import mobilepage.MainMenuPage;
import mobilepage.MainMenuPage.MenuFormat;
import mobilepage.MealPlanMainPage;
import mobilepage.MealPlanMenuPage;
import mobilepage.MealPlanningPage;
import mobilepage.OrdersPage;
import mobilepage.SnapHome;

public class TestMobile extends MobileTestCase {
	
	/**
	 * Create an order with an existing user account, and then cancel.
	 */
	@Test
	public void testCreateNewOrder()
	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
//		
		SnapHome snapHome = new SnapHome();
//		step("Step:  Create new user account."); 
//		if (snapHome.createAccountViaLogin(newUser)) {
//			passTest("Complete");
//		} else {
//			failTest("Could not create a new user account.");
//		}
//		
		AccountHome accountHome = new AccountHome();
//		step("Step:  Add new Payment Method from the Profile Page.");
//		String status = accountHome.addPaymentMethod("5555555555554444", "0424", "444");
//		if (status.equals("Success")) {
//			passTest("Complete");
//		} else {
//			failTest(status);
//		}
//		
//		step("Step:  User is able to access the Orders Screen under Accounts.");
//		status = accountHome.goToOrders();
//		if (status.equals("Success")) {
//			passTest("Complete");
//		} else {
//			failTest(status);
//		}
//		
		OrdersPage ordersPage = new OrdersPage();	
//		step("Verify:  Zero orders should be listed with a new account.");
//		int numOrders = ordersPage.getNumberOfOrders();
//		if (numOrders == 0) {
//			passTest("Complete");
//		} else {
//			failTestAndContinue("Order information was listed on the orders page for a new customer.");
//		}
		
		step("Step:  Login as a local customer.");
		String status = login(LocalCustomer);
		returnTestStatus(status);

		MainMenuPage mainMenuPage = new MainMenuPage();
		step("Step:  Switch to the category menu if you are not there already.");
		status = mainMenuPage.switchToMenu(MenuFormat.CATEGORY);
		returnTestStatus(status);
		
		step("Step:  User is able to access the breakfast menu.");
		status = mainMenuPage.clickMenu(MenuType.BREAKFAST);
		returnTestStatus(status);
		
		String productName = mainMenuPage.getFirstMenuItemProductName();
		step("Step:  Gather name of first product in the list.");
		if (!productName.startsWith("ERROR")){
			passTest("Complete");
		} else {
			failTest("Could not gather a product to add to the basket.");
		}
		
		step("Step:  User is able to add first breakfast menu item to the shopping cart."); 
		status = mainMenuPage.addFirstAvailableItemToCart();
		returnTestStatus(status);
		
		step("Step:  User is able to access the shopping cart.");
		status =  mainMenuPage.goToBasket();
		returnTestStatus(status);
				
		BasketPage basketPage = new BasketPage();
		step("Verify: The product we added exists in the shopping basket.");
		if (basketPage.menuItemExists(productName)) {
			passTest("Complete");
		} else {
			failTest("Could not locate our item in the shopping basket.");
		}
		
		step("Step:  User is able to access the checkout screen.");
		status = basketPage.goToCheckOut();
		returnTestStatus(status);
		
		CheckoutPage checkoutPage = new CheckoutPage();
		step("Step:  Add new Payment Method."); 
		status = checkoutPage.addPaymentMethod("4000 0000 0000 0077", "03/21", "586");
		returnTestStatus(status);
		
		step("Step:  User is able to place order from the checkout screen."); 
		status = checkoutPage.placeOrder();
		returnTestStatus(status);
		
//		OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
//		step("Step:  User is able to cancel the order from the Order Details Screen.");
//		status = orderSubmissionPage.cancelOrder();
//		if (status.equals("Success")) {
//			passTest("Complete");
//		} else {
//			failTest(status);
//		}
//				
//		step("Step:  User is able to access the Orders Screen under Accounts."); 
//		status = accountHome.goToOrders();
//		if (status.equals("Success")) {
//			passTest("Complete");
//		} else {
//			failTest(status);
//		}
//		
//		step("Step:  User is able to access Upcoming orders.");
//		status = ordersPage.goToUpcomingOrders();
//		if (status.equals("Success")) {
//			passTest("Complete");
//		} else {
//			failTest(status);
//		}
//		
//		step("Verify:  One Order should be listed.");
//		int numOrders = ordersPage.getNumberOfOrders();
//		if (numOrders == 1) {
//			passTest("Complete");
//		} else {
//			failTestAndContinue("Order was still listed as active.");
//		}
		
	}
	
	/**
	 * Add items to the shopping basket, empty, and verify.
	 */
	@Test
	public void testEmptyShoppingBasket()
	{
		step("Step:  Login as a local customer.");
		String status = login(LocalCustomer);
		returnTestStatus(status);
		
		BasketPage basketPage = new BasketPage();
		MainMenuPage mainMenuPage = new MainMenuPage();
		
		step("Step:  Click on the shopping basket icon");
		status = mainMenuPage.goToBasket();
		returnTestStatus(status);

		step("Verify:  Shopping Basket should initially be empty.");
		if (basketPage.isBasketEmpty()) {
			passTest("Complete");
		} else {
			failTestAndContinue("Shopping basket was not empty.");
		}
		
		step("Verify:  Shop Menu button is available on an empty shopping basket.");
		if (basketPage.shopMenuButtonExists()) {
			passTest("Complete");
		} else {
			failTest("Could not locate the Shop Menu Button.");
		}
		
		assertEquals("Step:  Click Shop Menu button.", "Success", basketPage.shopMenu());
		
		
		assertEquals("Step:  User is able to access the breakfast menu.", "Success", mainMenuPage.clickMenu(MenuType.BREAKFAST));
		
		String productName = mainMenuPage.getFirstMenuItemProductName();
		assertFalse("Step:  Product Name is retrieved successfully.", productName.startsWith("ERROR"));
		
		assertEquals("Step:  User is able to add first breakfast menu item to the shopping cart.", "Success", mainMenuPage.addFirstAvailableItemToCart());
		assertEquals("Step:  User is able to access the shopping cart.", "Success", mainMenuPage.goToBasket());
				
		assertTrue("Verify: The product we added exists in the shopping basket.", basketPage.menuItemExists(productName));
		
		assertEquals("Step:  Click Empty Basket.", "Success", basketPage.emptyBasket());
		assertTrue("Verify:  Empty Basket Confirmation is displayed.", basketPage.emptyBasketPromptExists());
		assertEquals("Step:  Click Yes at Empty Basket Confirmation.", "Success", basketPage.emptyBasketPrompt_ClickYes());
		
		assertTrue("Verify:  Shopping Basket is empty after using the Empty Basket function.", basketPage.isBasketEmpty());
	}
	
	
//	/**
//	 *   Create Meal Plan Smoke Test
//	 *   Test id : SQ-4
//	 */
//	@Test
//	public void testCreateNewMealPlan_SmokeTest()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.println(newUser.getUsername());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		Calendar today = Calendar.getInstance();
//		int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
//		int firstFulfillmentDay = dayOfMonth + 4;
//		
//		LowerNavPage lowerNav = new LowerNavPage();
//		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
//		MealPlanningMenu mealPlanningMenu = new MealPlanningMenu();
//		MealPlanningOptions mealPlanningOptions = new MealPlanningOptions();
//		
//		lowerNav.goToMealPlan();
//		
//		mealPlanMainPage.selectPlanType(PlanType.LOW_CARB);
//		
//		mealPlanningPage.select1200Calories();
//		mealPlanningPage.select5Days();
//		
//		DayParts[] dayParts = {DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER};
//		mealPlanningPage.selectDayParts(dayParts);
//		mealPlanningPage.selectPickup();
//		mealPlanningPage.selectNext();
//		mealPlanningMenu.checkOut();
//		
//		if (mealPlanningOptions.selectFulfillementDate(firstFulfillmentDay).equals("Success"))
//		{	
//			mealPlanningOptions.clickSave();
//		}
//		
//		mealPlanningPage.selectNext();
//		
//		if (!mealPlanningOptions.addCreditCard("4000000000000077", "0321", "333").equals("Success"))
//		
//		
//		mealPlanningOptions.startSubscription();
//		
//		mealPlanningPage.allowNotifications();
//		
//		MealPlanningSubmission mealPlanningSubmission = new MealPlanningSubmission();
//		assertTrue("Verify that the meal plan was setup successfully.", mealPlanningSubmission.setupSuccess());
//	}
	
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 1:  Verify that all meal plan cards are displayed.
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyAllPlansExist()
	{
		LowerNavPage lowerNav = new LowerNavPage();
		SnapHome snapHome = new SnapHome();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		
		assertEquals("Verify that user login was successful.", "Success", login(ShippingCustomer));
		
		assertEquals("Step:  Click on Meal Plan Link in lower navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Complete MP Onboarding.", "Success", snapHome.completeMealPlanOnboarding());
	
		assertTrue("Verify that High Protein Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.HIGH_PROTEIN));	
		assertEquals("Step:  Click Low Carb.","Success",mealPlanMainPage.selectLowCarb());
		assertTrue("Verify that Low Carb Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.LOW_CARB));
		assertEquals("Step:  Click Balance.","Success",mealPlanMainPage.selectBalance());
		assertTrue("Verify that Balance Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.BALANCE));
		assertEquals("Step:  Click Whole 30.","Success",mealPlanMainPage.selectWhole30());
		assertTrue("Verify that Whole30 Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.WHOLE30));
		assertEquals("Step:  Click Paleo.","Success",mealPlanMainPage.selectPaleo());
		assertTrue("Verify that Paleo Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.PALEO));

	}
	
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 2:  Verify Plan Options for High Protein.
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyPlanOptions_HighProtein()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertEquals("Step:  Create new user via Meal Planning Screens.", "Success", snapHome.createAccountViaMealPlanning_LocalUser(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select High Protein.", "Success", mealPlanMainPage.selectPlanType(PlanType.HIGH_PROTEIN));
		
		assertFalse("Verify:  View Menu button does not exist when meal plan options page is loaded.", mealPlanningPage.viewMenuExists());
		
		assertTrue("Verify:  1200 Calories Exists.", mealPlanningPage.mealPlanSizeOptionExists(Size.CALORIES_1200));
		assertTrue("Verify:  1500 Calories Exists.", mealPlanningPage.mealPlanSizeOptionExists(Size.CALORIES_1500));
		assertTrue("Verify:  1800 Calories Exists.", mealPlanningPage.mealPlanSizeOptionExists(Size.CALORIES_1800));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.selectMealPlanSizeOption(Size.CALORIES_1200));
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size is selected.", mealPlanningPage.viewMenuExists());
		
		assertTrue("Verify:  3 Days Exists.", mealPlanningPage.daysPerWeekOptionExists(DaysPerWeek.THREE_DAYS));
		assertTrue("Verify:  5 Days Exists.", mealPlanningPage.daysPerWeekOptionExists(DaysPerWeek.FIVE_DAYS));
		assertTrue("Verify:  7 Days Exists.", mealPlanningPage.daysPerWeekOptionExists(DaysPerWeek.SEVEN_DAYS));
		
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.THREE_DAYS));
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size and Days are selected.", mealPlanningPage.viewMenuExists());

				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Select Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Select Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Select AM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertEquals("Step:  Select PM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertEquals("Step:  Select Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));

		
		assertTrue("Verify:  Breakfast Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.BREAKFAST));
		assertTrue("Verify:  Lunch Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.LUNCH));
		assertTrue("Verify:  Dinner Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.DINNER));
		assertTrue("Verify:  AM Snack Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.AM_SNACK));
		assertTrue("Verify:  PM Snack Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.PM_SNACK));
		assertTrue("Verify:  Drinks Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.DRINKS));

		
		assertEquals("Step:  Deselect Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Deselect Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Deselect Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Deselect AM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertEquals("Step:  Deselect PM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertEquals("Step:  Deselect Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));

		assertFalse("Verify:  Breakfast Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.BREAKFAST));
		assertFalse("Verify:  Lunch Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.LUNCH));
		assertFalse("Verify:  Dinner Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.DINNER));
		assertFalse("Verify:  AM Snack Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.AM_SNACK));
		assertFalse("Verify:  PM Snack Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.PM_SNACK));
		assertFalse("Verify:  Drinks Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.DRINKS));
		
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size, Days and DayParts are selected.", mealPlanningPage.viewMenuExists());
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectFulfillmentOption(Fulfillment.PICKUP));
		assertTrue("Verify:  View Menu button exists when all Meal Plan Options are chosen.", mealPlanningPage.viewMenuExists());

		assertEquals("Verify:  User can select Delivery.", "Success", mealPlanningPage.selectFulfillmentOption(Fulfillment.DELIVERY));
		
	}
 
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 2:  Verify Plan Options for Whole30.
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyPlanOptions_Whole30()
	{
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		
		step("Step:  Login as a local customer.");
		String status = login(LocalCustomer);
		returnTestStatus(status);
		
		step("Step:  Click Meal Plans from Lower Navigation.");
		status = lowerNav.goToMealPlan();
		returnTestStatus(status);
		
		step("Step:  Select Whole30.");
		status = mealPlanMainPage.selectPlanType(PlanType.WHOLE30);
		returnTestStatus(status);
		
		step("Step:  Click Start Building Plan.");
		if (mealPlanningPage.startBuildingPlan()) {
			passTest("Complete");
		} else {
			failTest("Could not find the Start Building Subscription button.");
		}
		
		step("Verify:  View Menu button does not exist when meal plan options page is loaded.");
		if (!mealPlanningPage.viewMenuExists()){
			passTest("Complete");
		} else {
			failTest("View Menu button was not displayed.");
		}
		
		step("Verify:  User can select Pickup."); 
		status = mealPlanningPage.selectFulfillmentOption(Fulfillment.PICKUP);
		returnTestStatus(status);
		
		step("Verify:  View Menu button does not exist when only Fulfillment Options are chosen.");
		if (!mealPlanningPage.viewMenuExists()) {
			passTest("Complete");
		} else {
			failTest("View Menu was displayed.");
		}
		
		step("Verify:  3 Days Exists.");
		if (mealPlanningPage.daysPerWeekOptionExists(DaysPerWeek.THREE_DAYS)) {
			passTest("Complete");
		} else {
			failTest("3 Day Days Per Week Option did not exist.");
		}
		
		step("Verify:  5 Days Exists.");
		if (mealPlanningPage.daysPerWeekOptionExists(DaysPerWeek.FIVE_DAYS)) {
			passTest("Complete");
		} else {
			failTest("5 Day Days Per Week Option did not exist.");
		}
		
		step("Verify:  7 Days Exists.");
		if (mealPlanningPage.daysPerWeekOptionExists(DaysPerWeek.SEVEN_DAYS)) {
			passTest("Complete");
		} else {
			failTest("7 Day Days Per Week Option did not exist.");
		}
				
		step("Step:  Select 3 Days.");
		status = mealPlanningPage.selectDaysPerWeek(DaysPerWeek.THREE_DAYS);
		returnTestStatus(status);
		
		step("Verify:  View Menu button does not exist when only Fulfillment and Days Per Week are selected.");
		if (!mealPlanningPage.viewMenuExists()) {
			passTest("Complete");
		} else {
			failTest("View Menu button was displayed.");
		}
		
		step("Step:  Select Breakfast Day Part.");
		status = mealPlanningPage.toggleDayPart(DayParts.BREAKFAST);
		returnTestStatus(status);
		
		step("Step:  Select Lunch Day Part.");
		status = mealPlanningPage.toggleDayPart(DayParts.LUNCH);
		returnTestStatus(status);
		
		step("Step:  Select Dinner Day Part.");
		status = mealPlanningPage.toggleDayPart(DayParts.DINNER);
		returnTestStatus(status);
		
		step("Step:  Select Drinks Day Part.");
		status = mealPlanningPage.toggleDayPart(DayParts.DRINKS);
		returnTestStatus(status);
		
		step("Verify: All day parts are selected.");
		if (		mealPlanningPage.isDayPartSelected(DayParts.BREAKFAST) &&
				mealPlanningPage.isDayPartSelected(DayParts.LUNCH) &&
				mealPlanningPage.isDayPartSelected(DayParts.DINNER) &&
				mealPlanningPage.isDayPartSelected(DayParts.DRINKS)) {
			passTest("Complete");
		}
		else {
			failTestAndContinue("Not All Day Parts were selected.");
		}
		
		step("Verify:  AM Snack does not exist.");
		status = mealPlanningPage.toggleDayPart(DayParts.AM_SNACK);
		if (status.equals("Could not find daypart element.")) {
			passTest("Complete");
		}

		step("Verify:  PM Snack does not exist.");
		status = mealPlanningPage.toggleDayPart(DayParts.PM_SNACK);
		if (status.equals("Could not find daypart element.")) {
			passTest("Complete");
		}

		step("Step:  Deselect Breakfast Day Part.");
		status =  mealPlanningPage.toggleDayPart(DayParts.BREAKFAST);
		returnTestStatus(status);
		
		step("Step:  Deselect Lunch Day Part.");
		status =  mealPlanningPage.toggleDayPart(DayParts.LUNCH);
		returnTestStatus(status);
		
		step("Step:  Deselect Dinner Day Part.");
		status =  mealPlanningPage.toggleDayPart(DayParts.DINNER);
		returnTestStatus(status);
		
		step("Step:  Deselect Drinks Day Part.");
		status =  mealPlanningPage.toggleDayPart(DayParts.DRINKS);
		returnTestStatus(status);
		
		step("Verify: All day parts are selected.");
		if (		!mealPlanningPage.isDayPartSelected(DayParts.BREAKFAST) &&
				!mealPlanningPage.isDayPartSelected(DayParts.LUNCH) &&
				!mealPlanningPage.isDayPartSelected(DayParts.DINNER) &&
				!mealPlanningPage.isDayPartSelected(DayParts.DRINKS)) {
			passTest("Complete");
		}
		else {
			failTestAndContinue("Not All Day Parts were DE-selected.");
		}
		
		step("Step:  Select Breakfast Day Part.");
		status =  mealPlanningPage.toggleDayPart(DayParts.BREAKFAST);
		returnTestStatus(status);
		
		step("Verify:  View Menu button exists when only Fulfillments, Days Per Week, and Day Parts are selected.");
		if (mealPlanningPage.viewMenuExists()) {
			passTest("Complete");
		} else {
			failTest("View Menu was not listed even though all required options were selected.");
		}
		
	}
	
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 2:  Verify Days Per Week Option Recognized
	 *   Navigate through Meal Plan Days using Day Links in upper Navigation.
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyPlanDaysRecognized()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertEquals("Step:  Create new user via Meal Planning Screens.", "Success", snapHome.createAccountViaMealPlanning_LocalUser(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.selectMealPlanSizeOption(Size.CALORIES_1200));
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.THREE_DAYS));
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Select Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Select Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Select AM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertEquals("Step:  Select PM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertEquals("Step:  Select Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectFulfillmentOption(Fulfillment.PICKUP));
		
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectViewMenu());
		
		//Three Day Meal Plan Tests
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayTwo());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayThree());
		assertNotEquals("Verify:  Day 4 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFour());
		assertNotEquals("Verify:  Day 5 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFive());
		assertNotEquals("Verify:  Day 6 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySix());
		assertNotEquals("Verify:  Day 7 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySeven());
		
		//Five Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 5 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.FIVE_DAYS));
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectViewMenu());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayTwo());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayThree());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFour());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFive());
		assertNotEquals("Verify:  Day 6 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySix());
		assertNotEquals("Verify:  Day 7 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySeven());
		
		//Seven Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 7 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.SEVEN_DAYS));
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectViewMenu());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayTwo());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayThree());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFour());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFive());
		assertEquals("Verify:  Day 6 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySix());
		assertEquals("Verify:  Day 7 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySeven());

	}
	
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 2:  Verify Days Per Week Option Recognized
	 *   Navigate Through Plan Days using Lower Navigation
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyLowerNavigationOnPlanDays()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertEquals("Step:  Create new user via Meal Planning Screens.", "Success", snapHome.createAccountViaMealPlanning_LocalUser(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.selectMealPlanSizeOption(Size.CALORIES_1200));
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.THREE_DAYS));
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Select Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Select Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Select AM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertEquals("Step:  Select PM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertEquals("Step:  Select Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectFulfillmentOption(Fulfillment.PICKUP));
		
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectViewMenu());
		
		//Three Day Meal Plan Tests
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay2());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay3());
		assertNotEquals("Verify:  Day 4 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.planDay4());
		
		
		//Five Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 5 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.FIVE_DAYS));
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectViewMenu());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay2());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay3());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay4());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay5());
		assertNotEquals("Verify:  Day 6 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.planDay6());
		
		//Seven Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 7 Days.", "Success", mealPlanningPage.selectDaysPerWeek(DaysPerWeek.SEVEN_DAYS));
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectViewMenu());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay2());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay3());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay4());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay5());
		assertEquals("Verify:  Day 6 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay6());
		assertEquals("Verify:  Day 7 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay7());

	}
	
//	/**
//	 *   Create Meal Plan Smoke Test
//	 *   Step 2:  Verify Days Per Week Option Recognized
//	 *   Test id : SQ-4
//	 */
//	@Test
//	public void testVerifyPlanDayPartsRecognized()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.println(newUser.getUsername());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		LowerNavPage lowerNav = new LowerNavPage();
//		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
//		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
//		
//		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
//		
//		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
//		
//		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
//		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
//				
//		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
//		
//		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
//		
//		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
//		
//		//Only Breakfast DayPart exists
//		assertEquals("Step:  Load Day 1.", "Success", mealPlanMenuPage.goToDayOne());
//		assertTrue("Verify:  Breakfast Day Part Exists.", mealPlanMenuPage.dayPartExists(DayParts.BREAKFAST));
//		assertFalse("Verify:  AM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.AM_SNACK));
//		assertFalse("Verify:  Lunch Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.LUNCH));
//		assertFalse("Verify:  PM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.PM_SNACK));
//		assertFalse("Verify:  Dinner Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DINNER));
//		assertFalse("Verify:  Drinks Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DRINKS));
//		
//		assertEquals("Step:  Load Day 2.", "Success",  mealPlanMenuPage.goToDayTwo());
//		assertTrue("Verify:  Breakfast Day Part Exists.", mealPlanMenuPage.dayPartExists(DayParts.BREAKFAST));
//		assertFalse("Verify:  AM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.AM_SNACK));
//		assertFalse("Verify:  Lunch Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.LUNCH));
//		assertFalse("Verify:  PM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.PM_SNACK));
//		assertFalse("Verify:  Dinner Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DINNER));
//		assertFalse("Verify:  Drinks Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DRINKS));
//		
//		assertEquals("Step: Load Day 3.", "Success",  mealPlanMenuPage.goToDayThree());
//		assertTrue("Verify:  Breakfast Day Part Exists.", mealPlanMenuPage.dayPartExists(DayParts.BREAKFAST));
//		assertFalse("Verify:  AM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.AM_SNACK));
//		assertFalse("Verify:  Lunch Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.LUNCH));
//		assertFalse("Verify:  PM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.PM_SNACK));
//		assertFalse("Verify:  Dinner Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DINNER));
//		assertFalse("Verify:  Drinks Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DRINKS));
//
//	}
	
//	/**
//	 *   Create Meal Plan Smoke Test
//	 *   Step 5:  Edit Day Part and Modify Quantity
//	 *   Test id : SQ-4
//	 */
//	@Test
//	public void testEditDayPartAndModifyQuantity()
//	{
//		String uniqueString = util.getUniqueString(7);
//		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
//		System.out.println(newUser.getUsername());
//		
//		SnapHome snapHome = new SnapHome();
//		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
//		
//		MealPlanningPage mealPlanningPage = new MealPlanningPage();
//		LowerNavPage lowerNav = new LowerNavPage();
//		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
//		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
//		
//		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
//		
//		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
//		
//		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
//		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
//				
//		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
//		
//		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
//		
//		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
//		
//		assertEquals("Step:  Click Edit on the Breakfast Day Part.", "Success", mealPlanMenuPage.editDayPart(DayParts.BREAKFAST));
//		
//	}
 
}

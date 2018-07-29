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
import mobilepage.MealPlanMainPage;
import mobilepage.MealPlanMenuPage;
import mobilepage.MealPlanningPage;
import mobilepage.OrderSubmissionPage;
import mobilepage.OrdersPage;
import mobilepage.ProfileHome;
import mobilepage.SnapHome;

public class TestMobile extends MobileTestCase {
		
	/**
	 * Login as an existing user and verify profile data.
	 */
	@Test
	public void testLogin()
	{
		login(StagingUser);
		
		AccountHome accountHome = new AccountHome();
		accountHome.goToAccount();
		accountHome.goToProfile();
		
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", StagingUser.getFirstName(), profileHome.firstName.getText());
		assertEquals("Verify that the last name value is correct.", StagingUser.getLastName(), profileHome.lastName.getText());
		assertEquals("Verify that the email address value is correct.", StagingUser.getEmail(), profileHome.email.getText());
		assertEquals("Verify that the phone number value is correct.", StagingUser.getUsername(), profileHome.phoneNumber.getText());
		
	}
	
	/**
	 * Create a new user account by clicking Login from the Main Screen.
	 * Verify data on the profile screen.
	 */
	@Test
	public void testCreateAccount()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Verify that new account was created successfully.", snapHome.createAccountViaLogin(newUser));
	
		
		AccountHome accountHome = new AccountHome();
		accountHome.goToAccount();
		accountHome.goToProfile();
		
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", newUser.getFirstName(), profileHome.firstName.getText());
		assertEquals("Verify that the last name value is correct.", newUser.getLastName(), profileHome.lastName.getText());
		assertEquals("Verify that the email address value is correct.", newUser.getEmail(), profileHome.email.getText());
		assertEquals("Verify that the phone number value is correct.", newUser.getUsername(), profileHome.phoneNumber.getText());
		
	}
	
	/**
	 * Create an order with an existing user account, and then cancel.
	 */
	@Test
	public void testCreateNewOrderAndCancel()
	{
		assertEquals("Step:  User is able to login with the default Staging User Account.", "Success", login(StagingUser));
		
		MainMenuPage mainMenuPage = new MainMenuPage();
		assertEquals("Step:  User is able to access the breakfast menu.", "Success", mainMenuPage.clickMenu(MenuType.BREAKFAST));
		
		String productName = mainMenuPage.getFirstMenuItemProductName();
		assertFalse("Step:  Product Name is retrieved successfully.", productName.startsWith("ERROR"));
		
		assertEquals("Step:  User is able to add first breakfast menu item to the shopping cart.", "Success", mainMenuPage.addFirstItemToCart());
		assertEquals("Step:  User is able to access the shopping cart.", "Success", mainMenuPage.goToBasket());
				
		BasketPage basketPage = new BasketPage();
		assertTrue("Verify: The product we added exists in the shopping basket.", basketPage.menuItemExists(productName));
		
		assertEquals("Step:  User is able to access the checkout screen.","Success", basketPage.goToCheckOut());
		
		CheckoutPage checkoutPage = new CheckoutPage();
		assertEquals("Step:  User is able to place order from the checkout screen.", "Success", checkoutPage.placeOrder());
		
		OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
		assertEquals("Step:  User is able to cancel the order from the Order Details Screen.", "Success", orderSubmissionPage.cancelOrder());
				
		AccountHome accountHome = new AccountHome();
		assertEquals("Step:  User is able to access the Orders Screen under Accounts.", "Success", accountHome.goToOrders());
		
		OrdersPage ordersPage = new OrdersPage();
		assertEquals("Step:  User is able to access Upcoming orders.", "Success", ordersPage.goToUpcomingOrders());
		assertEquals("Verify:  Zero orders should be listed.", 0, ordersPage.getNumberOfOrders());
		
	}
	
	@Test
	/**
	 * This test verifies that all menu items exist on the main menu screen.
	 */
	public void testMainMenuCheck()
	{
		assertEquals("Verify that user login was successful.", "Success", login(StagingUser));
		
		MainMenuPage mainMenu = new MainMenuPage();
		assertTrue("Verify:  Breakfast Menu Exists.", mainMenu.menuExists(MenuType.BREAKFAST));
		assertTrue("Verify:  Lunch and Dinner Menu Exists.", mainMenu.menuExists(MenuType.LUNCH_AND_DINNER));
		assertTrue("Verify:  Salads Menu Exists.", mainMenu.menuExists(MenuType.SALADS));
		assertTrue("Verify:  Soups Menu Exists.", mainMenu.menuExists(MenuType.SOUPS));
		assertTrue("Verify:  Small Bites Menu Exists.", mainMenu.menuExists(MenuType.SMALL_BITES));
		assertTrue("Verify:  Sides Menu Exists.", mainMenu.menuExists(MenuType.SIDES));
		assertTrue("Verify:  Snacks Menu Exists.", mainMenu.menuExists(MenuType.SNACKS));
		assertTrue("Verify:  Drinks Menu Exists.", mainMenu.menuExists(MenuType.DRINKS));
		assertTrue("Verify:  Sweets Menu Exists.", mainMenu.menuExists(MenuType.SWEETS));

	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateNewMealPlan_3Day_1200_Pickup()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.HIGH_PROTEIN, Size.CALORIES_1200, DaysPerWeek.THREE_DAYS, Fulfillment.PICKUP, DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
		
	}
	
	/**
	 *   Create New User by using the Meal Planning Onboarding Screens.
	 *   Create New Meal Plan
	 *   3 Day, 1200 Cal, Pickup
	 *   Verify No errors on creating subscription.
	 */
	@Test
	public void testCreateNewMealPlan_5Day_1800_Pickup()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		assertEquals("Verify:  Create Meal Plan High Protein, 1200, 3 Days, Pickup.", "Success", mealPlanningPage.createMealPlan(newUser, PlanType.HIGH_PROTEIN, Size.CALORIES_1800, DaysPerWeek.FIVE_DAYS, Fulfillment.PICKUP,DayParts.BREAKFAST, DayParts.LUNCH, DayParts.DINNER));
		
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
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		SnapHome snapHome = new SnapHome();
		
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		assertEquals("Step:  Click on Meal Plan Link in lower navigation.", "Success", lowerNav.goToMealPlan());
	
		assertTrue("Verify that High Protein Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.HIGH_PROTEIN));	
		assertEquals("Step:  Click Low Carb.","Success",mealPlanMainPage.selectLowCarb());
		assertTrue("Verify that Low Carb Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.LOW_CARB));
		assertEquals("Step:  Click Balance.","Success",mealPlanMainPage.selectBalance());
		assertTrue("Verify that Balance Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.BALANCE));
		assertEquals("Step:  Click Whole 30.","Success",mealPlanMainPage.selectWhole30());
		assertTrue("Verify that Whole30 Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.WHOLE30));
		assertEquals("Step:  Click Paleo.","Success",mealPlanMainPage.selectPaleo());
		assertTrue("Verify that Paleo Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.PALEO));
		assertEquals("Step:  Click Camp Gladiator.","Success",mealPlanMainPage.selectCampGladiator());
		assertTrue("Verify that Camp Gladiator Exists.", mealPlanMainPage.mealPlanCardExists(PlanType.CAMP_GLADIATOR));

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
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select High Protein.", "Success", mealPlanMainPage.selectPlanType(PlanType.HIGH_PROTEIN));
		
		assertFalse("Verify:  View Menu button does not exist when meal plan options page is loaded.", mealPlanningPage.nextButtonExists());
		
		assertTrue("Verify:  1200 Calories Exists.", mealPlanningPage.exists1200Calories());
		assertTrue("Verify:  1500 Calories Exists.", mealPlanningPage.exists1500Calories());
		assertTrue("Verify:  1800 Calories Exists.", mealPlanningPage.exists1800Calories());
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size is selected.", mealPlanningPage.nextButtonExists());
	
		assertTrue("Verify:  3 Days Exists.", mealPlanningPage.exists3Days());
		assertTrue("Verify:  5 Days Exists.", mealPlanningPage.exists5Days());
		assertTrue("Verify:  7 Days Exists.", mealPlanningPage.exists7Days());
		
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size and Days are selected.", mealPlanningPage.nextButtonExists());

				
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
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size, Days and DayParts are selected.", mealPlanningPage.nextButtonExists());
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
		assertTrue("Verify:  View Menu button exists when all Meal Plan Options are chosen.", mealPlanningPage.nextButtonExists());

		assertEquals("Verify:  User can select Delivery.", "Success", mealPlanningPage.selectDelivery());
		
	}
 
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 2:  Verify Plan Options for Whole30.
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyPlanOptions_Whole30()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select High Protein.", "Success", mealPlanMainPage.selectPlanType(PlanType.WHOLE30));
		
		assertFalse("Verify:  View Menu button does not exist when meal plan options page is loaded.", mealPlanningPage.nextButtonExists());
		
		assertTrue("Verify:  Small Option Exists.", mealPlanningPage.existsSmallOption());
		assertTrue("Verify:  Medium Option Exists.", mealPlanningPage.existsMediumOption());
		assertTrue("Verify:  Large Option Exists.", mealPlanningPage.existsLargeOption());
		
		assertEquals("Step:  Select Small Meal Plan.", "Success", mealPlanningPage.selectSmallOption());
		
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size is selected.", mealPlanningPage.nextButtonExists());
	
		assertTrue("Verify:  3 Days Exists.", mealPlanningPage.exists3Days());
		assertTrue("Verify:  5 Days Exists.", mealPlanningPage.exists5Days());
		assertTrue("Verify:  7 Days Exists.", mealPlanningPage.exists7Days());
		
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
		
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size and Days are selected.", mealPlanningPage.nextButtonExists());
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Select Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Select Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Select Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));
		
		assertTrue("Verify:  Breakfast Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.BREAKFAST));
		assertTrue("Verify:  Lunch Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.LUNCH));
		assertTrue("Verify:  Dinner Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.DINNER));
		assertNotEquals("Verify:  AM Snack does not exist.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertNotEquals("Verify:  PM Snack does not exist.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertTrue("Verify:  Drinks Day Part is selected.", mealPlanningPage.isDayPartSelected(DayParts.DRINKS));
		
		assertEquals("Step:  Deselect Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Deselect Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Deselect Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Deselect Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));

		assertFalse("Verify:  Breakfast Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.BREAKFAST));
		assertFalse("Verify:  Lunch Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.LUNCH));
		assertFalse("Verify:  Dinner Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.DINNER));
		assertFalse("Verify:  Drinks Day Part can be deselected.", mealPlanningPage.isDayPartSelected(DayParts.DRINKS));
		
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertFalse("Verify:  View Menu button does not exist when only Meal Plan Size, Days and DayParts are selected.", mealPlanningPage.nextButtonExists());
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
		assertTrue("Verify:  View Menu button exists when all Meal Plan Options are chosen.", mealPlanningPage.nextButtonExists());

		assertEquals("Verify:  User can select Delivery.", "Success", mealPlanningPage.selectDelivery());
		
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
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Select Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Select Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Select AM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertEquals("Step:  Select PM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertEquals("Step:  Select Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
		
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
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
		assertEquals("Step:  Select 5 Days.", "Success", mealPlanningPage.select5Days());
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayTwo());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayThree());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFour());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.goToDayFive());
		assertNotEquals("Verify:  Day 6 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySix());
		assertNotEquals("Verify:  Day 7 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.goToDaySeven());
		
		//Seven Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 7 Days.", "Success", mealPlanningPage.select7Days());
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
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
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		assertEquals("Step:  Select Lunch Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.LUNCH));
		assertEquals("Step:  Select Dinner Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DINNER));
		assertEquals("Step:  Select AM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.AM_SNACK));
		assertEquals("Step:  Select PM Snack Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.PM_SNACK));
		assertEquals("Step:  Select Drinks Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.DRINKS));
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
		
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
		//Three Day Meal Plan Tests
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay2());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay3());
		assertNotEquals("Verify:  Day 4 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.planDay4());
		
		
		//Five Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 5 Days.", "Success", mealPlanningPage.select5Days());
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay2());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay3());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay4());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay5());
		assertNotEquals("Verify:  Day 6 does not exist for a three day meal plan.", "Success",  mealPlanMenuPage.planDay6());
		
		//Seven Day Meal Plan Tests
		assertEquals("Step:  Return to Meal Plan Options.", "Success", mealPlanMenuPage.returnMealPlanOptions());
		assertEquals("Step:  Select 7 Days.", "Success", mealPlanningPage.select7Days());
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
		assertEquals("Verify:  Day 1 exists for a three day meal plan.", "Success", mealPlanMenuPage.goToDayOne());
		assertEquals("Verify:  Day 2 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay2());
		assertEquals("Verify:  Day 3 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay3());
		assertEquals("Verify:  Day 4 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay4());
		assertEquals("Verify:  Day 5 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay5());
		assertEquals("Verify:  Day 6 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay6());
		assertEquals("Verify:  Day 7 exists for a three day meal plan.", "Success",  mealPlanMenuPage.planDay7());

	}
	
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 2:  Verify Days Per Week Option Recognized
	 *   Test id : SQ-4
	 */
	@Test
	public void testVerifyPlanDayPartsRecognized()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
		
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
		//Only Breakfast DayPart exists
		assertEquals("Step:  Load Day 1.", "Success", mealPlanMenuPage.goToDayOne());
		assertTrue("Verify:  Breakfast Day Part Exists.", mealPlanMenuPage.dayPartExists(DayParts.BREAKFAST));
		assertFalse("Verify:  AM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.AM_SNACK));
		assertFalse("Verify:  Lunch Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.LUNCH));
		assertFalse("Verify:  PM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.PM_SNACK));
		assertFalse("Verify:  Dinner Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DINNER));
		assertFalse("Verify:  Drinks Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DRINKS));
		
		assertEquals("Step:  Load Day 2.", "Success",  mealPlanMenuPage.goToDayTwo());
		assertTrue("Verify:  Breakfast Day Part Exists.", mealPlanMenuPage.dayPartExists(DayParts.BREAKFAST));
		assertFalse("Verify:  AM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.AM_SNACK));
		assertFalse("Verify:  Lunch Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.LUNCH));
		assertFalse("Verify:  PM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.PM_SNACK));
		assertFalse("Verify:  Dinner Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DINNER));
		assertFalse("Verify:  Drinks Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DRINKS));
		
		assertEquals("Step: Load Day 3.", "Success",  mealPlanMenuPage.goToDayThree());
		assertTrue("Verify:  Breakfast Day Part Exists.", mealPlanMenuPage.dayPartExists(DayParts.BREAKFAST));
		assertFalse("Verify:  AM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.AM_SNACK));
		assertFalse("Verify:  Lunch Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.LUNCH));
		assertFalse("Verify:  PM Snack Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.PM_SNACK));
		assertFalse("Verify:  Dinner Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DINNER));
		assertFalse("Verify:  Drinks Day Part does not exist.", mealPlanMenuPage.dayPartExists(DayParts.DRINKS));

	}
	
	/**
	 *   Create Meal Plan Smoke Test
	 *   Step 5:  Edit Day Part and Modify Quantity
	 *   Test id : SQ-4
	 */
	@Test
	public void testEditDayPartAndModifyQuantity()
	{
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.println(newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Step:  Create new user via Meal Planning Screens.", snapHome.createAccountViaMealPlanning(newUser));
		
		MealPlanningPage mealPlanningPage = new MealPlanningPage();
		LowerNavPage lowerNav = new LowerNavPage();
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		MealPlanMenuPage mealPlanMenuPage = new MealPlanMenuPage();
		
		assertEquals("Step:  Click Meal Plans from Lower Navigation.", "Success", lowerNav.goToMealPlan());
		
		assertEquals("Step:  Select Low Carb.", "Success", mealPlanMainPage.selectPlanType(PlanType.LOW_CARB));
		
		assertEquals("Step:  Select 1200 Calories.", "Success", mealPlanningPage.select1200Calories());
		assertEquals("Step:  Select 3 Days.", "Success", mealPlanningPage.select3Days());
				
		assertEquals("Step:  Select Breakfast Day Part.", "Success", mealPlanningPage.toggleDayPart(DayParts.BREAKFAST));
		
		assertEquals("Verify:  User can select Pickup.", "Success", mealPlanningPage.selectPickup());
		
		assertEquals("Step: Select Next to View the Menu.", "Success", mealPlanningPage.selectNext());
		
		assertEquals("Step:  Click Edit on the Breakfast Day Part.", "Success", mealPlanMenuPage.editDayPart(DayParts.BREAKFAST));
		
	}
 
}

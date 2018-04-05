package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.MenuType;
import common.UserType;
import mobilepage.AccountHome;
import mobilepage.BasketPage;
import mobilepage.CheckoutPage;
import mobilepage.MainMenuPage;
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
		login(ThreeDayMealPlan);
		
		AccountHome accountHome = new AccountHome();
		accountHome.goToAccount();
		accountHome.goToProfile();
		
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", ThreeDayMealPlan.getFirstName(), profileHome.firstName.getText());
		assertEquals("Verify that the last name value is correct.", ThreeDayMealPlan.getLastName(), profileHome.lastName.getText());
		assertEquals("Verify that the email address value is correct.", ThreeDayMealPlan.getEmail(), profileHome.email.getText());
		assertEquals("Verify that the phone number value is correct.", ThreeDayMealPlan.getUsername(), profileHome.phoneNumber.getText());
		
	}
	
	/**
	 * Create a new user account, and verify data on the profile screen.
	 */
	@Test
	public void testCreateAccount()
	{
		//MobileDriver.LOGGER.info("Hi");
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Verify that new account was created successfully.", snapHome.createAccount(newUser));
	
		
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
		assertEquals("Step:  User is able to login with ThreeDayMealPlan user account.", "Success", login(ThreeDayMealPlan));
		
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
		assertEquals("Verify that user login was successful.", "", login(ThreeDayMealPlan));
		
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
	
 
}

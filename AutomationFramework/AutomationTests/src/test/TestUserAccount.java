package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.MenuType;
import common.UserType;
import page.BaseMenu;
import page.BasketPage;
import page.CheckoutPage;
import page.LoginPage;
import page.LoginPage.AccountMenu;
import page.MenuPopup;
import page.OrderSubmissionPage;
import page.OrdersPage;
import page.ProfileHome;
import page.SnapHome;

/**
 * Tests for Creating User Accounts and Verifying Data on the User Profile
 * @author GMitchell
 *
 */
public class TestUserAccount extends BaseTestCase {
	
	/**
	 * Login with an existing staging user.  Access the profile page and verify that all data is correct.
	 */
	@Test
	public void testLoginWithExistingAccount() {
		
		assertEquals("Step:  Login to staging.", "Success", login(StagingUser));
		
		LoginPage loginPage = new LoginPage();
		assertTrue("Step:  Click on Account Profile Page.", loginPage.goToAccountProfile());
		
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", StagingUser.getFirstName(), profileHome.firstName.getValue());
		assertEquals("Verify that the last name value is correct.", StagingUser.getLastName(), profileHome.lastName.getValue());
		assertEquals("Verify that the email value is correct.", StagingUser.getEmail(), profileHome.email.getValue());
		assertEquals("Verify that the phone number value is correct.", StagingUser.getUsername(), profileHome.phone.getValue());
		
	}
	
	/**
	 * Create a new user with a unique phone number.  Access the profile page and verify that all data is correct.
	 */
	@Test
	public void testCreateAccount() {
		
		ProfileHome profileHome = new ProfileHome();
		
		String uniqueString = util.getUniqueString(7);
		String uniquePhone = getUniquePhone();
		UserType newUser = new UserType(uniquePhone, DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		System.out.printf("New user created:  %s", uniquePhone);
		
		LoginPage loginPage = new LoginPage();
		loginPage.createAccount(newUser);
		
		assertEquals("Verify:  Account Popup Menu is correct.","Success", loginPage.verifyAccountPopupMenu());
		
		assertEquals("Verify:  Account Left Navigation Menu is correct.", "Success", loginPage.verifyAccountLeftNavigation());
		
		//Verify Manage Meal Plan default screen.
		assertTrue("Step:  Click on Manage Meal Plan Account Menu Option.", loginPage.goToAccountOption(AccountMenu.MANAGE_MEAL_PLAN));
		assertTrue("Verify:  Manage Meal Plan screen prompts user to subscribe to a new plan.", profileHome.verifyDefaultManageMealPlanScreen());
		
		//Verify the Default Orders screen.
		assertTrue("Step:  Click on the Orders account menu option.", loginPage.goToAccountOption(AccountMenu.ORDERS));
		assertTrue("Verify:  Orders screen prompts user to view our menu.", profileHome.verifyDefaultOrdersScreen());
		assertTrue("Verify:  Past Orders tab exists.", profileHome.pastOrdersExists());
		assertTrue("Verify:  Upcoming Orders tab exists.", profileHome.upcomingOrdersExists());
		assertTrue("Verify:  Upcoming Orders tab is active by default.", profileHome.isUpcomingOrdersActive());
		assertFalse("Verify:  Past Orders tab is not active by default.", profileHome.isPastOrdersActive());
		
		//Verify the Default Payments screen.
		assertTrue("Step:  Click on Payments Menu Option.", loginPage.goToAccountOption(AccountMenu.PAYMENTS));
		assertEquals("Verify:  Status of Payment methods is correct.", "You do not have saved credit cards.", profileHome.getPaymentStatusMessage());
		assertTrue("Verify:  Add new Payment Method button exists.", profileHome.addNewCardExists());
		
		//Verify Default Favorites Screen.
		assertTrue("Step:  Click on the Favorites Menu Option.", loginPage.goToAccountOption(AccountMenu.FAVORITES));
		assertTrue("Verify:  No Favorites text exists.", profileHome.getNoFavoritesMessage().contains("Oh no, you have not favorited any items yet."));
		assertTrue("Verify:  View Our Menu button exists.", profileHome.viewOurMenuExists());
		
		//Verify Default Customer Care Screen.
		assertTrue("Step:  Click on the Customer Care Menu Option.", loginPage.goToAccountOption(AccountMenu.CUSTOMER_CARE));
		//TODO:  Update customer care tests.
		//assertTrue("Verify:  Customer Care Message is correct.", profileHome.getCustomerCareMessage().contains(CUSTOMER_CARE_MSG));
		//assertEquals("Step:  Send customer care comment.", "Success", profileHome.sendCustomerCareComment("QA Smoke Test"));
		
		//assertTrue("Verify:  Customer Care Message Sent Confirmation Exists.", profileHome.getCustomerCareMessage().contains(CUSTOMER_CARE_MSG_CONF));
		//assertTrue("Verify:  Customer Care Send Another Message Link Exists.", profileHome.sendAnotherMsgLinkExists());
		
		// Verify that Account Profile was saved successfully.
		assertTrue("Step:  Click on the Profile Option.", loginPage.goToAccountOption(AccountMenu.PROFILE));		
		assertEquals("Verify that the first name value is correct.", newUser.getFirstName(), profileHome.firstName.getValue());
		assertEquals("Verify that the last name value is correct.", newUser.getLastName(), profileHome.lastName.getValue());
		assertEquals("Verify that the email value is correct.", newUser.getEmail(), profileHome.email.getValue());
		assertEquals("Verify that the phone number value is correct.", newUser.getUsername(), profileHome.phone.getValue());
	}
	
	/**
	 * Login with an existing user and create a new order.  Verify Order details.
	 */
	@Test
	public void testCreateNewOrder()
	{
		SnapHome snapHome = new SnapHome();
		OrdersPage ordersPage = new OrdersPage();
		
		login(StagingUser);
		
		snapHome.goToOrders();
		int numScheduledStart = ordersPage.getNumScheduledOrders();
		int numActiveStart = ordersPage.getNumActiveOrders();
		int numPastStart = ordersPage.getNumPastOrders();
		
		assertNotEquals("Step:  Get Start number of Scheduled Orders.", -1, numScheduledStart);
		assertNotEquals("Step:  Get Start number of Past Orders.", -1, numPastStart);
		assertNotEquals("Step:  Get Start number of Active Orders.", -1, numActiveStart);

		
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//Make sure that we are set for delivery to 9330 United Drive, so that we are using Snap HQ
//		BaseMenu baseMenu = new BaseMenu();
//		baseMenu.changeDeliveryAddress("9330 United Dr, Austin, TX 78758");
		
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Breakfast Menu exists.", menuPopup.breakfast.exists());
		
		assertTrue("Test Step:  Click on Breakfast Menu.", menuPopup.goToMenu(MenuType.BREAKFAST));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Breakfast Menu.", "breakfast", menu.getCategoryName());
		
		String productName = menu.addFirstAvailableItemToCart();
		assertFalse("Test Step:  Click to add the first item in the list to the shopping cart.", productName.isEmpty());
		
		//Go to the shopping cart
		assertEquals("Step:  Navigate to the shopping cart.", "Success", goToShoppingCart());
		
		//Go to check out
		BasketPage basketPage = new BasketPage();
		assertEquals("Step:  Go To Checkout.", "Success", basketPage.goToCheckout());
		
		//Place Order
		CheckoutPage checkoutPage = new CheckoutPage();
		assertEquals("Step:  Place Order and verify success.", "Success", checkoutPage.placeOrder());
		
		//Get Order number for later steps
		String orderNum = checkoutPage.getOrderNumber();
		assertFalse("Verify:  Order Number was returned.", orderNum.equals(""));
		
		//Verify Order exists
		snapHome.goToOrders();
		int numScheduledAfterOrder = ordersPage.getNumScheduledOrders();
		int numActiveAfterOrder = ordersPage.getNumActiveOrders();
		int numPastAfterOrder = ordersPage.getNumPastOrders();
		
		assertNotEquals("Step:  Get Start number of Scheduled Orders.", -1, numScheduledAfterOrder);
		assertNotEquals("Step:  Get Start number of Past Orders.", -1, numPastAfterOrder);
		assertNotEquals("Step:  Get Start number of Active Orders.", -1, numActiveAfterOrder);
		//assertEquals("Verify:  Number of scheduled orders is the same.", numScheduledAfterOrder, numScheduledStart);
		assertEquals("Verify:  Number of past orders is the same.", numPastAfterOrder, numPastStart);
		assertEquals("Verify:  Number of active orders is 1.", numActiveStart + 1, numActiveAfterOrder);

		//Load Order
		assertEquals("Step:  Go to Orders page and view.","Success",ordersPage.viewActiveOrder(orderNum));
		
		//Cancel order
		OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
		assertEquals("Step:  Cancel Order.", "Success", orderSubmissionPage.cancelOrder());
		
		//Verify order cancelled
		snapHome.goToOrders();
		int numScheduledAfterCancel = ordersPage.getNumScheduledOrders();
		int numPastAfterCancel = ordersPage.getNumPastOrders();
		int numActiveAfterCancel = ordersPage.getNumActiveOrders();

		
		assertNotEquals("Step:  Get Start number of Scheduled Orders.", numScheduledStart, numScheduledAfterCancel);
		assertNotEquals("Step:  Get Start number of Past Orders.", numPastStart+1, numPastAfterCancel);
		assertEquals("Step:  Get Start number of Active Orders.", numActiveStart, numActiveAfterCancel);
	}
	
}

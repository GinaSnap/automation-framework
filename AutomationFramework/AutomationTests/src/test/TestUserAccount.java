package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.MenuType;
import common.UserType;
import page.BaseMenu;
import page.BasketPage;
import page.CheckoutPage;
import page.LoginPage;
import page.MenuPopup;
import page.OrderSubmissionPage;
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
		
		login(StagingUser);
		
		LoginPage loginPage = new LoginPage();
		loginPage.goToAccountProfile();
		
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
		
		String uniqueString = util.getUniqueString(7);
		String uniquePhone = getUniquePhone();
		UserType newUser = new UserType(uniquePhone, DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		System.out.printf("New user created:  %s", uniquePhone);
		
		LoginPage loginPage = new LoginPage();
		loginPage.createAccount(newUser);
		
		loginPage.goToAccountProfile();
		
		ProfileHome profileHome = new ProfileHome();
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
		login(StagingUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//Make sure that we are set for delivery to 9330 United Drive, so that we are using Snap HQ
		BaseMenu baseMenu = new BaseMenu();
		baseMenu.changeDeliveryAddress("9330 United Dr, Austin, TX 78758");
		
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Breakfast Menu exists.", menuPopup.breakfast.exists());
		
		assertTrue("Test Step:  Click on Breakfast Menu.", menuPopup.goToMenu(MenuType.BREAKFAST));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Breakfast Menu.", "breakfast", menu.getCategoryName());
		
		String productName = menu.addFirstItemToCart();
		assertFalse("Test Step:  Click to add the first item in the list to the shopping cart.", productName.isEmpty());
		
		//Go to the shopping cart
		assertEquals("Step:  Navigate to the shopping cart.", "Success", goToShoppingCart());
		
		//Go to check out
		BasketPage basketPage = new BasketPage();
		assertEquals("Step:  Go To Checkout.", "Success", basketPage.goToCheckout());
		
		//Place Order
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.placeOrder();
		
		//Verify Order exists
		//snapHome.goToOrders();
		
		//Cancel order
		OrderSubmissionPage orderSubmissionPage = new OrderSubmissionPage();
		orderSubmissionPage.cancelOrder();
		
		//Verify order cancelled
		snapHome.goToOrders();
	}
	
}

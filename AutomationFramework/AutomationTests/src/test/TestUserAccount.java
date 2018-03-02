package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.MenuType;
import common.UserType;
import page.BaseMenu;
import page.LoginPage;
import page.MenuPopup;
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
		
		login(ThreeDayMealPlan);
		
		LoginPage loginPage = new LoginPage();
		loginPage.goToAccountProfile();
		
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", ThreeDayMealPlan.getFirstName(), profileHome.firstName.getValue());
		assertEquals("Verify that the last name value is correct.", ThreeDayMealPlan.getLastName(), profileHome.lastName.getValue());
		assertEquals("Verify that the email value is correct.", ThreeDayMealPlan.getEmail(), profileHome.email.getValue());
		assertEquals("Verify that the phone number value is correct.", ThreeDayMealPlan.getUsername(), profileHome.phone.getValue());
		
	}
	
	/**
	 * Create a new user with a unique phone number.  Access the profile page and verify that all data is correct.
	 */
	@Test
	public void testCreateAccount() {
		
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		
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
		login(ThreeDayMealPlan);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Breakfast Menu exists.", menuPopup.breakfast.exists());
		
		assertTrue("Test Step:  Click on Breakfast Menu.", menuPopup.goToMenu(MenuType.BREAKFAST));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Breakfast Menu.", "breakfast", menu.getCategoryName());
		
		String productName = menu.addFirstItemToCart();
		assertFalse("Test Step:  Click to add the first item in the list to the shopping cart.", productName.isEmpty());
	}
	
}

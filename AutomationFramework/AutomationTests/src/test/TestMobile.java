package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.UserType;
import mobilepage.AccountHome;
import mobilepage.ProfileHome;
import mobilepage.SnapHome;
import test.MobileTestCase;

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
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), "qqqqqqqq", "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com");
		
		SnapHome snapHome = new SnapHome();
		snapHome.createAccount(newUser);
		
		AccountHome accountHome = new AccountHome();
		accountHome.goToAccount();
		accountHome.goToProfile();
		
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", newUser.getFirstName(), profileHome.firstName.getText());
		assertEquals("Verify that the last name value is correct.", newUser.getLastName(), profileHome.lastName.getText());
		assertEquals("Verify that the email address value is correct.", newUser.getEmail(), profileHome.email.getText());
		assertEquals("Verify that the phone number value is correct.", newUser.getUsername(), profileHome.phoneNumber.getText());
		
	}
	
 
}

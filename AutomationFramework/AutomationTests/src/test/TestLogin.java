package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mobilepage.AccountHome;
import mobilepage.ProfileHome;
import mobilepage.SnapHome;

public class TestLogin extends MobileTestCase {
	
	/**
	 * Login as an existing user and verify profile data.
	 */
	@Test
	public void testLogin()
	{
		step("testLogin");
		step("Login with existing user account.");
		String status = login(StagingUser);
		if (status.equals("Success"))
		{
			passTest(status);
		}
		else
		{
			failTest(status);
		}
		
		step("Verify All Account Menu Items Exist.");
		AccountHome accountHome = new AccountHome();
		assertEquals("Step:  Click the Account Menu In the Lower Navigation.", "Success", accountHome.goToAccount());
		assertTrue("Verify:  Manage Meal Plan Menu Item Exists.", accountHome.manageMealPlanExists());
		assertTrue("Verify:  Orders Menu Item Exists.", accountHome.ordersExists());
		assertTrue("Verify:  Payments Menu Item Exists.", accountHome.paymentsExists());
		assertTrue("Verify:  Promo Menu Item Exists.", accountHome.promoExists());
		assertTrue("Verify:  Profile Menu Item Exists.", accountHome.profileExists());
		assertTrue("Verify:  Customer Care Menu Item Exists.", accountHome.customerCareExists());
		assertTrue("Verify:  General Info Menu Item Exists.", accountHome.generalInfoExists());
		
		assertEquals("Step:  Click the Profile Menu Item.", "Success", accountHome.goToProfile());
		ProfileHome profileHome = new ProfileHome();
		assertEquals("Verify that the first name value is correct.", StagingUser.getFirstName(), profileHome.firstName.getText());
		assertEquals("Verify that the last name value is correct.", StagingUser.getLastName(), profileHome.lastName.getText());
		assertEquals("Verify that the email address value is correct.", StagingUser.getEmail(), profileHome.email.getText());
		assertEquals("Verify that the phone number value is correct.", StagingUser.getUsername(), profileHome.phoneNumber.getText());
		
	}
	
	/**
	 * Login with an invalid password and verify error message.
	 */
	@Test
	public void testLoginInvalidPassword()
	{
		step("testLogin");
		step("Login with existing user account.");
		String status = login("5126085335", "11111111");
		if (status.equals("Success"))
		{
			failTest("Incorrect password was not recognized.");
		}
		else
		{
			if (status.equals("User entered an incorrect password."))
			{
				passTest("User received the correct password error.");
			}
			else
			{
				failTest("User did not receive the correct password error.");
			}
		}
		
	}
	
	/**
	 * Login with an invalid password.
	 * Click Try again, and enter the correct password.
	 */
	@Test
	public void testInvalidPassword_TryAgain()
	{
		step("testLogin");
		step("Login with existing user account.");
		String status = login(StagingUser.getUsername(), "11111111");
		if (status.equals("Success"))
		{
			failTest("Incorrect password was not recognized.");
		}
		else
		{
			if (status.equals("User entered an incorrect password."))
			{
				passTest("User received the correct password error.");
			}
			else
			{
				failTest("User did not receive the correct password error.");
			}
		}
		
		SnapHome snapHome = new SnapHome();
		if (!snapHome.passwordError_TryAgain())
		{
			failTest("User was not able to click the 'Try Again' button on the password error screen.");
		}
		
		status = snapHome.enterPassword(StagingUser.getPassword());
		if (status.equals("Success")) {
			passTest(status);
		}
		else {
			failTest(status);
		}
		
	}
	
	/**
	 * Login with an invalid password and verify error message.
	 */
	@Test
	public void testResetPassword()
	{
		SnapHome snapHome = new SnapHome();
		step("testLogin");
		step("Login with existing user account.");
		String status = snapHome.resetPassword("0095550137", "qqqqqqqq");
		if (status.equals("Success"))
		{
			passTest("Incorrect password was not recognized.");
		}
		else
		{
			failTest(status);
		}
		
	}

}

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
		
		step("Click on the Account Icon in Lower Navigation.");
		status = accountHome.goToAccount();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		step("Verify: All Menu Items exist.");
		StringBuilder statusSb = new StringBuilder();
		if (!accountHome.manageMealPlanExists()) {
			statusSb.append("Manage Meal Plan");
		}
		if (!accountHome.ordersExists()) {
			statusSb.append("|Orders");
		}
		if (!accountHome.paymentsExists()) {
			statusSb.append("|Payments");
		}
		if (!accountHome.promoExists()) {
			statusSb.append("|Promo");
		}
		if (!accountHome.profileExists()) {
			statusSb.append("|Profile");
		}
		if (!accountHome.customerCareExists()) {
			statusSb.append("|Customer Care");
		}
		if (!accountHome.generalInfoExists()) {
			statusSb.append("|General Info");
		}
		if (statusSb.length() > 0) {
			failTestAndContinue(String.format("Following Items were missing from the Account Menu: %s", statusSb.toString()));
		} else {
			passTest("Complete");
		}
		
		step("Click on the Profile Menu Item.");
		status = accountHome.goToProfile();
		if (status.equals("Success")) {
			passTest("Complete");
		} else {
			failTest(status);
		}
		
		ProfileHome profileHome = new ProfileHome();
		step("Verify all information on the profile page.");
		
		if (StagingUser.getFirstName().equals(profileHome.firstName.getText())){
			passTest("Complete");
		} else {
			failTestAndContinue("First Name Value was not correct.");
		}
		if (StagingUser.getLastName().equals(profileHome.lastName.getText())){
			passTest("Complete");
		} else {
			failTestAndContinue("Last Name Value was not correct.");
		}
		if (StagingUser.getEmail().equals(profileHome.email.getText())){
			passTest("Complete");
		} else {
			failTestAndContinue("Email Value was not correct.");
		}
		if (StagingUser.getUsername().equals(profileHome.phoneNumber.getText())){
			passTest("Complete");
		} else {
			failTestAndContinue("Phone Value was not correct.");
		}
		
	}
	
	/**
	 * Login with an invalid password and verify error message.
	 */
	@Test
	public void testLoginInvalidPassword()
	{
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
		step("Login with existing user account.  Reset Password.");
		String status = snapHome.resetPassword("0095550137", "qqqqqqqq");
		if (status.equals("Success"))
		{
			passTest("Complete");
		}
		else
		{
			failTest(status);
		}
		
		//Need to logout and Login with the new password to really test this.
		
	}

}

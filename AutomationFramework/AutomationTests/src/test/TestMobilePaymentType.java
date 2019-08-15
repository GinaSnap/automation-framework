package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.Location;
import common.UserType;
import mobilepage.AccountHome;
import mobilepage.EditPaymentsPage;
import mobilepage.SnapHome;

public class TestMobilePaymentType extends MobileTestCase {
	
	/**
	 * Create a new user account by clicking Login from the Main Screen.
	 * Verify data on the profile screen.
	 */
	@Test
	public void testEditPaymentMethod_SmokeTest()
	{
		String uniquePhone = getUniquePhone();
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(uniquePhone, DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		
		System.out.println("New User: " + uniquePhone);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Verify that new account was created successfully.", snapHome.createAccountViaLogin(newUser));
	
		//assertEquals("Step:  Login as an existing user.", "Success", login(StagingUser));
		
		AccountHome accountHome = new AccountHome();
		assertEquals("Step:  Go to Account screen.", "Success", accountHome.goToAccount());
		assertEquals("Step:  Go to Edit Payments.", "Success", accountHome.goToPayments());
		
		EditPaymentsPage editPaymentsPage = new EditPaymentsPage();
		assertEquals("Step:  Add Visa Ending in 0077.", "Success", editPaymentsPage.addCreditCardFromAccount(visa0077));
		
		//assertEquals("Verify Notification:  Meal Plan using new Visa 0077.", "Success", accountHome.closeMealPlanPaymentUpdatedPrompt());
		
		assertEquals("Step:  Go to Edit Payments after adding Visa 0077.", "Success", accountHome.goToPayments());

		assertEquals("Step:  Add Mastercard Ending in 4444.", "Success", editPaymentsPage.addCreditCardFromAccount(mastercard4444));
		
		//assertEquals("Verify Notification:  Meal Plan using new Mastercard 4444.", "Success", accountHome.closeMealPlanPaymentUpdatedPrompt());
		
		assertEquals("Step:  Go to Edit Payments after saving Mastercard 4444.", "Success", accountHome.goToPayments());
		
		assertTrue("Verify:  Visa 0077 Exists.", editPaymentsPage.creditCardExists(visa0077));
		assertTrue("Verify:  Mastercard 4444 Exists.", editPaymentsPage.creditCardExists(mastercard4444));

		//Should only be able to delete the 0077 card since it is not default.
		assertEquals("Step:  Delete Card.", "Success", editPaymentsPage.deleteCard(visa0077));
		
		assertFalse("Verify:  Credit Card No Longer Exists.", editPaymentsPage.creditCardExists(visa0077));
		assertTrue("Verify:  Mastercard 4444 Still Exists.", editPaymentsPage.creditCardExists(mastercard4444));

		
	}

}

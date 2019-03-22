package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import common.CustomerType;
import common.Location;
import common.PlanType;
import common.UserType;
import mobilepage.AccountHome;
import mobilepage.ProfileHome;
import mobilepage.SnapHome;
import page.RequestZipcodePage;
import page.ShippingMenuPage;

public class TestMobileAccount extends MobileTestCase {
	
	/**
	 * Scroll through new user onboarding and click Get Started.
	 * Enter Shipping Zip Code
	 */
	@Test
	public void testEnterShippingZipCodeFromOnboarding()
	{
		step("testEnterShippingZipCodeFromOnboarding");
		
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		HashMap<String, CustomerType> zipCodeMap = getZipCodeMap();
		
		Iterator<Entry<String, CustomerType>> it = zipCodeMap.entrySet().iterator();
		while (it.hasNext())
		{
			relaunch();
			Map.Entry<String, CustomerType> pair = (Map.Entry<String, CustomerType>)it.next();
			
			step("Testing Zip Code " + pair.getKey());
			
			step("Scroll through new user onboarding.");
			String status=snapHome.completeNewUserOnboarding();
			if (!status.equals("Success"))
			{
				failTestAndContinue(status);
				continue;
			}
			else
			{
				passTest(status);
			}
			
			step("Enter zip code.");
			status=zipCodePage.enterZipCode(pair.getKey());
			if (!status.equals("Success"))
			{
				failTestAndContinue(status);
				continue;
			}
			else
			{
				passTest(status);
			}
			
			step("Verify that correct menu was loaded.");
			isCorrectMenuLoaded(pair.getValue());
		}
		
	}

	public void isCorrectMenuLoaded(CustomerType customerType) {
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();
		
		switch (customerType) {
		case LOCAL:
			break;
			
		case NATIONAL:
			if (shippingMenuPage.isLoaded())
			{
				passTest("Shipping Menu was loaded.");
			}
			else
			{
				failTestAndContinue("Shipping Menu was not loaded.");
			}
			break;
			
		case OUT_OF_RANGE:
			if (zipCodePage.isZipUnavailable())
			{	
				zipCodePage.willDo.click();
				passTest("Out Of Range Message was Loaded.");
			}
			else
			{
				failTestAndContinue("Out Of Range Message was not loaded.");
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * Create user account with zipcodes from different regions.
	 * Verify user is taken to the right menu.
	 */
	@Test
	public void testCreateAccountViaLoginZipCodeTests()
	{
		String uniqueString;
		String status;
		SnapHome snapHome = new SnapHome();
		AccountHome accountHome = new AccountHome();

		HashMap<String,CustomerType> zipCodeMap = getZipCodeMap();
		
		Iterator<Entry<String,CustomerType>> iterator = zipCodeMap.entrySet().iterator();
		
		while (iterator.hasNext())
		{
			relaunch();
			Map.Entry<String, CustomerType> pair = (Map.Entry<String, CustomerType>)iterator.next();
			step("Testing Zipcode " + pair.getKey());

			uniqueString = util.getUniqueString(7);
			UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com",pair.getKey());
			step("Username: " + newUser.getUsername());
			
			if (!snapHome.createAccountViaLogin(newUser, Location.AUSTIN))
			{
				failTestAndContinue("Error when creating user account.");
			}
			else
			{
				passTest("Create account completed successfully.");
				
				step("Verify that correct menu is loaded based upon zipcode.");
				isCorrectMenuLoaded(pair.getValue());
				
				step("Verify that all account information is correct.");
				accountHome.goToAccount();
				accountHome.goToProfile();
				
				ProfileHome profileHome = new ProfileHome();
				if (newUser.getFirstName().equals(profileHome.firstName.getText()))
				{
					passTest("First Name value is correct.");
				}
				else
				{
					failTestAndContinue("First Name Value is not correct.");
				}
				if (newUser.getLastName().equals(profileHome.lastName.getText()))
				{
					passTest("Last Name value is correct.");
				}
				else
				{
					failTestAndContinue("Last Name Value is incorrect.");
				}
				if (newUser.getEmail().equals(profileHome.email.getText()))
				{
					passTest("Email value is correct.");
				}
				else
				{
					failTestAndContinue("Email Value is not correct.");
				}
//				if (newUser.getZipCode().equals(profileHome.zipCode.getText()))
//				{
//					passTest("Zip Code value is correct.");
//				}
//				else 
//				{
//					failTestAndContinue("Zip Code Value is not correct.");
//				}
				if (newUser.getUsername().equals(profileHome.phoneNumber.getText()))
				{
					passTest("Phone value is correct.");
				}
				else
				{
					failTestAndContinue("Phone Value is not correct.");
				}
					
			}
		}
		
	}
	
	/**
	 * Scroll through new user onboarding and click Get Started.
	 * Verify that clicking on a meal plan card will prompt to login
	 */
	@Test
	public void testCreateAccountViaShippingMenu_MealPlan()
	{
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();

		String uniqueString = util.getUniqueString(7);
		
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		step(newUser.getUsername());
		
		step("Scroll through new user onboarding.");
		String status = snapHome.completeNewUserOnboarding();
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Enter valid zip code.");
		status=zipCodePage.enterZipCode("77777");
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		status=shippingMenuPage.selectMealPlan(PlanType.BALANCE);
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Click on Lets Get Started and verify login screen displayed.");
		status = shippingMenuPage.letsGetStarted();
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Create new Account.");
		if (snapHome.createAccount(newUser, Location.AUSTIN))
		{
			passTest("Success");
		}
		else
		{
			failTest("Error when creating new account.");
		}
	}
	
	/**
	 * Scroll through new user onboarding and click Get Started.
	 * Verify that clicking on the shopping basket will prompt to login
	 */
	@Test
	public void testCreateAccountViaShippingMenu_ClickShoppingBasket()
	{
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		step(newUser.getUsername());
		
		step("Scroll through new user onboarding.");
		String status = snapHome.completeNewUserOnboarding();
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Enter valid zip code.");
		status=zipCodePage.enterZipCode("77777");
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Click on Shopping Basket and verify user is prompted to login.");
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();
		status = shippingMenuPage.goToShoppingBasket();
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Click on Lets Get Started and verify login screen displayed.");
		status = shippingMenuPage.letsGetStarted();
		if (!status.equals("Success"))
		{
			failTest(status);
		}
		else
		{
			passTest(status);
		}
		
		step("Create new Account.");
		if (snapHome.createAccount(newUser, Location.AUSTIN))
		{
			passTest("Success");
		}
		else
		{
			failTest("Error when creating new account.");
		}
	}
		
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
	 * Create a new user account by clicking Login from the Main Screen.
	 * Verify data on the profile screen.
	 */
	@Test
	public void testCreateAccount()
	{
		
		step("testCreateAccount");
		
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com","78758");
		step("Creating User Account: "  + newUser.getUsername());
		
		SnapHome snapHome = new SnapHome();
		if (snapHome.createAccountViaLogin(newUser, Location.AUSTIN))
		{
			passTest("User Account was created successfully.");
		}
		else
		{
			failTest("Errors when creating user account.");
		}
	
		
		AccountHome accountHome = new AccountHome();
		accountHome.goToAccount();
		accountHome.goToProfile();
		
		ProfileHome profileHome = new ProfileHome();
		if (newUser.getFirstName().equals(profileHome.firstName.getText()))
		{
			passTest("First Name Value is correct.");
		}
		{
			failTestAndContinue("First Name Value did not match what was entered.");
		}
		if (newUser.getLastName().equals(profileHome.lastName.getText()))
		{
			passTest("Last Name Value is correct.");
		}
		{
			failTestAndContinue("Last Name Value did not match what was entered.");
		}
		if (newUser.getEmail().equals(profileHome.email.getText()))
		{
			passTest("Email Value is correct.");
		}
		{
			failTestAndContinue("Email did not match what was entered.");
		}
		if (newUser.getUsername().equals(profileHome.phoneNumber.getText()))
		{
			passTest("PhoneNumber Value is correct.");
		}
		{
			failTestAndContinue("Phone Number did not match what was entered.");
		}
		
	}

}

package test;

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
import mobilepage.MainMenuPage;
import mobilepage.ProfileHome;
import mobilepage.SnapHome;
import page.LocalMenuPage;
import page.RequestZipcodePage;
import page.ShippingMenuPage;

public class TestMobileAccount extends MobileTestCase {
	
	/**
	 * Scroll through new user onboarding and click Get Started.
	 * Enter Zipcodes from Local area in SW, Local Area in Philly, Shipping in SW, Shipping in Philly
	 * and out of range.  Verify correct menu is loaded in each case.
	 * Add new zips in /Users/GMitchell/git/automation-framework/Zip_National.txt
	 */
	@Test
	public void testEnterShippingZipCodeFromOnboarding()
	{		
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		HashMap<String, CustomerType> zipCodeMap = getZipCodeMap(false);
		
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
			isCorrectMenuLoaded(pair.getValue(), false);
		}
		
	}
	
	/**
	 * Create user account by clicking Login on the New User Onboarding.
	 * Enter various zip codes.
	 * Verify user is taken to the right menu.
	 * Verify user data is saved successfully.
	 */
	@Test
	public void testCreateAccountViaLoginZipCodeTests()
	{
		String uniqueString;
		String status;
		SnapHome snapHome = new SnapHome();
		AccountHome accountHome = new AccountHome();

		HashMap<String,CustomerType> zipCodeMap = getZipCodeMap(false);
		
		Iterator<Entry<String,CustomerType>> iterator = zipCodeMap.entrySet().iterator();
		
		while (iterator.hasNext())
		{
			relaunch();
			Map.Entry<String, CustomerType> pair = (Map.Entry<String, CustomerType>)iterator.next();
			step("Testing Zipcode " + pair.getKey());

			uniqueString = util.getUniqueString(7);
			UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com",pair.getKey());
			step("Username: " + newUser.getUsername());
			
			if (!snapHome.createAccountViaLogin(newUser))
			{
				failTestAndContinue("Error when creating user account.");
			}
			else
			{
				passTest("Create account completed successfully.");
				
				step("Verify that correct menu is loaded based upon zipcode.");
				isCorrectMenuLoaded(pair.getValue(), true);
				
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
	public void testCreateAccountViaShippingMenu_SWK()
	{
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();

		String uniqueString = util.getUniqueString(7);
		
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@swk.com",DefaultNationalSW);
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
		status=zipCodePage.enterZipCode(DefaultNationalSW);
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
		if (snapHome.createAccount(newUser))
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
	 * Enter a Shipping zip code.
	 * Click on shopping basket and verify user is prompted to login.
	 * Verify that the customer is defaulted to the Shipping Fulfillment.
	 * Should put other verifications in later.
	 */
	@Test
	public void testCreateShippingOnlyCustomerViaMenu_SWK()
	{
		createNewShippingCustomer(DefaultNationalSW);
	}
	
	/**
	 * Scroll through new user onboarding and click Get Started.
	 * Enter a Shipping zip code.
	 * Click on shopping basket and verify user is prompted to login.
	 * Verify that the customer is defaulted to the Shipping Fulfillment.
	 * Should put other verifications in later.
	 */
	@Test
	public void testCreateShippingOnlyCustomerViaMenu_NEZ()
	{
		createNewShippingCustomer(DefaultNationalNE);
	}
	
	/**
	 * Create a new local customer from the Austin Area.
	 * Verify that the customer is defaulted to Pickup at Clarksville
	 * Should put other verifications in later.
	 */
	@Test
	public void testCreateLocalCustomerViaMenu_Austin()
	{
		createNewLocalCustomer(DefaultAustinZip, DefaultAustinStore);
	}
	
	/**
	 * Create a new local customer from the Dallas Area.
	 * Verify that the customer is defaulted to Pickup at Uptown
	 * Should put other verifications in later.
	 */
	@Test
	public void testCreateLocalCustomerViaMenu_Dallas()
	{
		createNewLocalCustomer(DefaultDallasZip, DefaultDallasStore);
	}
	
	/**
	 * Create a new local customer from the Houston Area.
	 * Verify that the customer is defaulted to Pickup at Kirby
	 * Should put other verifications in later.
	 */
	@Test
	public void testCreateLocalCustomerViaMenu_Houston()
	{
		createNewLocalCustomer(DefaultHoustonZip, DefaultHoustonStore);
	}
	
	/**
	 * Create a new local customer from the Dallas Area.
	 * Verify that the customer is defaulted to Pickup at Uptown
	 * Should put other verifications in later.
	 */
	@Test
	public void testCreateLocalCustomerViaMenu_Philly()
	{
		createNewLocalCustomer(DefaultPhillyZip, DefaultPhillyStore);
	}
	
	
	/**
	 * This is a helper function to test different Shipping Only Zipcodes
	 * @param zipCode String with a 5 digit zip code
	 */
	public void createNewShippingCustomer(String zipCode)
	{
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com",zipCode);
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
		status=zipCodePage.enterZipCode(zipCode);
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
		if (snapHome.createAccount(newUser))
		{
			passTest("Success");
		}
		else
		{
			failTest("Error when creating new account.");
		}
		
		step("Verify that the shipping menu is displayed.");
		MainMenuPage mainMenu = new MainMenuPage();
		if (mainMenu.isShipping())
		{
			passTest("Success");
		}
		else
		{
			failTest("User is not defaulted to shipping");
		}
	}
	
	/**
	 * This is a helper function for creating a local customer with different zip codes.
	 * @param zipCode String with a 5 digit zip code.
	 */
	public void createNewLocalCustomer(String zipCode, String defaultStore)
	{
		SnapHome snapHome = new SnapHome();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		String uniqueString = util.getUniqueString(7);
		UserType newUser = new UserType(getUniquePhone(), DEFAULT_PWD, "SnapFN" + uniqueString, "SnapLN" + uniqueString, uniqueString + "@snapkitchen.com",zipCode);
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
		status=zipCodePage.enterZipCode(zipCode);
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
		if (snapHome.createAccount(newUser))
		{
			passTest("Success");
		}
		else
		{
			failTest("Error when creating new account.");
		}
		
		step("Verify that the user is defaulted to Pickup.");
		MainMenuPage mainMenu = new MainMenuPage();
		if (mainMenu.isPickup(defaultStore))
		{
			passTest("Success");
		}
		else
		{
			failTest("User is not defaulted to Pickup.");
		}
	}
	
	/**
	 * This is a helper function to determine if the correct menu has been loaded based upon the zipcode entered.
	 * @param customerType
	 */
	public void isCorrectMenuLoaded(CustomerType customerType, boolean isLoggedIn) {
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();
		LocalMenuPage localMenuPage = new LocalMenuPage();
		
		switch (customerType) {
		case LOCAL:
			if (localMenuPage.isLoaded(isLoggedIn)) {
				passTest("Local Menu was loaded.");
			} else {
				failTestAndContinue("Local Menu was not loaded.");
			}
			break;
			
		case NATIONAL:
			if (shippingMenuPage.isLoaded(isLoggedIn)) {
				passTest("Shipping Menu was loaded.");
			} else {
				failTestAndContinue("Shipping Menu was not loaded.");
			}
			break;
			
		case OUT_OF_RANGE:
			if (zipCodePage.isLoaded()) {	
				passTest("Out Of Range Message was Loaded.");
			} else {
				failTestAndContinue("Out Of Range Message was not loaded.");
			}
			break;

		default:
			break;
		}
	}

}

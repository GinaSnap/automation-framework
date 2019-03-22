package page;

import org.openqa.selenium.NoSuchElementException;

import common.UserType;
import element.BaseWebElement;
import framework.AbstractDriver;
import framework.FindMethod;
import framework.WWWDriver;

public class LoginPage extends BasePage {
	
	//Welcome Back Login Screen
	private BaseWebElement wb_login = new BaseWebElement(FindMethod.CLASSNAME, "snap-input--text");
	private BaseWebElement wb_password = new BaseWebElement(FindMethod.CLASSNAME, "snap-input--password");
	private BaseWebElement sb_signin = new BaseWebElement(FindMethod.XPATH,"//button[@type='submit']");
	private BaseWebElement sb_signUp = new BaseWebElement(FindMethod.LINKTEXT,"Sign Up");
	
	//New User Sign Up Screen
	private BaseWebElement closeX = new BaseWebElement(FindMethod.CLASSNAME, "component-modal--close");
	private BaseWebElement help = new BaseWebElement(FindMethod.CLASSNAME, "help-action");
	private BaseWebElement signUp = new BaseWebElement(FindMethod.LINKTEXT, "Sign Up");
	private BaseWebElement phoneNumber = new BaseWebElement(FindMethod.ID, "phoneField");
	private BaseWebElement nextBtn = new BaseWebElement(FindMethod.CLASSNAME, "button--alt");
	private BaseWebElement smsCode = new BaseWebElement(FindMethod.CLASSNAME, "snap-input--number");
	private BaseWebElement password = new BaseWebElement(FindMethod.NAME, "password");
	
	//User Profile Screen
	private BaseWebElement completeMyProfile = new BaseWebElement(FindMethod.CLASSNAME, "continue-signup-link");
	private BaseWebElement firstName = new BaseWebElement(FindMethod.NAME, "first_name");
	private BaseWebElement lastName = new BaseWebElement(FindMethod.NAME, "last_name");
	private BaseWebElement email = new BaseWebElement(FindMethod.NAME, "email");
	private BaseWebElement city = new BaseWebElement(FindMethod.NAME, "city");
	private BaseWebElement rememberMe = new BaseWebElement(FindMethod.CLASSNAME, "snap-faux-checkbox");
	private BaseWebElement showPassword = new BaseWebElement(FindMethod.CLASSNAME, "snap-input-icon--password");
	private BaseWebElement forgotPassword = new BaseWebElement(FindMethod.CLASSNAME, "forgot-password-link");
	
	//Account Menu
	private BaseWebElement accountMenu = new BaseWebElement(FindMethod.CLASSNAME, "logged-in-link-icon");
	
	//Account Popup Sub Menu
	private BaseWebElement account_ManageMealPlan = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_manage_meal_plan");
	private BaseWebElement account_Orders = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_orders");
	private BaseWebElement account_Payments = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_payments");
	private BaseWebElement account_Favorites = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_favorites");
	private BaseWebElement account_Profile = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_profile");
	private BaseWebElement account_CustomerCare = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_customer-care");
	private BaseWebElement account_ReferAFriend = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_refer-a-friend");
	private BaseWebElement account_SignOut = new BaseWebElement(FindMethod.CLASSNAME, "account-link--signout");
	
	//Account Left Nav Menu
	private BaseWebElement leftNav_ManageMealPlan = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_manage_meal_plan");
	private BaseWebElement leftNav_Orders = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_orders");
	private BaseWebElement leftNav_Payments = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_payments");
	private BaseWebElement leftNav_Favorites = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_favorites");
	private BaseWebElement leftNav_Profile = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_profile");
	private BaseWebElement leftNav_CustomerCare = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_customer-care");
	private BaseWebElement leftNav_SignOut = new BaseWebElement(FindMethod.CLASSNAME, "account-navbar-link--account_sign-out");
	
	public enum AccountMenu
	{
		MANAGE_MEAL_PLAN,
		ORDERS,
		PAYMENTS,
		FAVORITES,
		PROFILE,
		CUSTOMER_CARE,
		SIGN_OUT
	}

	
	/**
	 * Navigate to the new user sign in screen.
	 */
	public boolean goToSignUp()
	{
		navigate();
		
		//For some reason, it thinks that it clicks on this element before it ever reaches the page.
		//I really have no choice but to pause for a minute.
		AbstractDriver.pause(3000);
		return signUp.click();
	}
	
	/**
	 * Navigate to the Login Screen.
	 * This will allow an existing user to sign in.
	 */
	public void goToLogin()
	{
		navigate();
	}
	
	public void login(String phone, String pwd)
	{
		goToSignUp();
		phoneNumber.setWebValue(phone);
		nextBtn.click(true);
		
		password.setWebValue(pwd);
		nextBtn.click(true);

	}
	
	public void login_CurrentUser(String phone, String pwd)
	{
		goToLogin();
		wb_login.setWebValue(phone);
		wb_password.setWebValue(pwd);
		wb_login.click();
	}
	
	/**
	 * Create a new account with the given phone number and password.
	 * @param user Enter a user of type UserType with all required values set.
	 */
	public String createAccount(UserType user){
		
		goToSignUp();
				
		String status = enterPhoneNumber(user.getUsername());
		if (!status.equals("Success"))
		{
			return status;
		}
		
		status = enterSMSCode("222222");
		if (!status.equals("Success"))
		{
			return status;
		}
		
		try
		{
			completeMyProfile.click();
		}
		catch (NoSuchElementException e)
		{
			return "Complete my Profile button was not displayed.";
		}
		
		try
		{
			firstName.setWebValue(user.getFirstName());
			lastName.setWebValue(user.getLastName());
			city.selectValue("Austin");
			email.setWebValue(user.getEmail());
			password.setWebValue(user.getPassword());
		}
		catch (NoSuchElementException e)
		{
			return "At least one field on the profile page was not displayed.";
		}
		
		try
		{
			nextBtn.click();
		}
		catch (NoSuchElementException e)
		{
			return "Next Button was not displayed.";
		}
		
		if (!waitForAccountScreenClose(10000))
		{
			return "Account Screen did not close after submitting user data.";
		}
		
		return "Success";
		
	}
	
	/**
	 * This method is the first step in the create account process.
	 * It assumes that the Phone Number screen is loaded.
	 * Use this if you want to specifically test this screen.
	 * Otherwise, use the createAccount method.
	 * @param desiredPhone Provide the phone number to enter.
	 * @return Returns false if any error messages are received.
	 * Otherwise return true.
	 */
	public String enterPhoneNumber(String desiredPhone)
	{
		try
		{
			phoneNumber.setWebValue(desiredPhone);
		}
		catch (NoSuchElementException e)
		{
			return "Phone Number field was not displayed.";
		}
		
		try
		{
			nextBtn.click();
		}
		catch (NoSuchElementException e)
		{
			return "Next Button was not displayed on the Enter Phone Screen.";
		}
		
		if (this.hasError())
		{
			return "Error received after entering phone number.";
		}
		return "Success";
	}
	
	/**
	 * This method is used as a part of the create account process.
	 * It assumes that the SMS Code Entry Screen is loaded.
	 * Use this if you want to specifically test this screen.
	 * Otherwise use the createAccount method.
	 * @param code  Provide the SMS Code to enter.
	 * @return Returns false if any error messages were received 
	 * after submitting.  Returns true otherwise.
	 */
	public String enterSMSCode(String code)
	{
		try
		{
			smsCode.setWebValue(code);
		}
		catch (NoSuchElementException e)
		{
			return "SMS Code field was not displayed.";
		}
		
		try
		{
			nextBtn.click();
		}
		catch (NoSuchElementException e)
		{
			return "Next Button was not displayed.";
		}
		
		if (this.hasError())
		{
			return "Error received after submitting SMS Code.";
		}
		return "Success";
	}
	
	/**
	 * Click on the Account Icon and select Profile.
	 * @return Method will return false if the user is not logged in
	 * or errors are returned.  Otherwise it will return true.
	 */
	public boolean goToAccountOption(AccountMenu accountMenuOption)
	{
		WWWDriver.pause(5000); //TODO:  Remove This Wait.
		
		if (!goToAccountMenu())
		{
			return false;
		}
		
		switch (accountMenuOption) {
		case MANAGE_MEAL_PLAN:
			return account_ManageMealPlan.click();
			
		case ORDERS:
			return account_Orders.click();

		case PAYMENTS:
			return account_Payments.click();
			
		case FAVORITES:
			return account_Favorites.click();
			
		case PROFILE:
			return account_Profile.click();
			
		case CUSTOMER_CARE:
			return account_CustomerCare.click();
			
		default:
			return false;
		}
	}
	
	/**
	 * Click on the Account Icon and select Profile.
	 * @return Method will return false if the user is not logged in
	 * or errors are returned.  Otherwise it will return true.
	 */
	public boolean goToAccountProfile()
	{
		WWWDriver.pause(5000); //TODO:  Remove This Wait.
		goToAccountMenu();
		account_Profile.click();
		
		return true;
	}
	
	public boolean goToAccountMenu()
	{
		return accountMenu.click();
	}
	
	private boolean waitForAccountScreenClose(long timeout)
	{
		long startTimeMili = System.currentTimeMillis();
		long currentTimeMili = System.currentTimeMillis();
		while (((currentTimeMili - startTimeMili) < timeout) && firstName.exists())
		{
			currentTimeMili = System.currentTimeMillis();
		}
		
		return !firstName.exists();
	}
	
	/**
	 * This function will verify that all menu items exist in the Account Popup Menu.
	 * @return Return a string specifying which menu items do not exist, or "Success"
	 */
	public String verifyAccountPopupMenu()
	{
		StringBuilder sb = new StringBuilder();
		
		goToAccountMenu(); //Open Account Menu
		if (!account_ManageMealPlan.exists())
			sb.append("Manage Meal Plan;");
		if (!account_Orders.exists())
			sb.append("Orders;");
		if (!account_Payments.exists())
			sb.append("Payments;");
		if (!account_Favorites.exists())
			sb.append("Favorites;");
		if (!account_Profile.exists())
			sb.append("Profile;");
		if (!account_CustomerCare.exists())
			sb.append("Customer Care;");
		
		goToAccountMenu(); //Close Account Menu
		
		if (sb.length() > 0)
			return sb.toString();
		else
			return "Success";
	}
	
	/**
	 * This function will verify that all menu items exist in the Account Left Navigation Menu
	 * @return Return a string specifying which menu items do not exist, or "Success"
	 */
	public String verifyAccountLeftNavigation()
	{
		StringBuilder sb = new StringBuilder();
		goToAccountProfile();
		if (!leftNav_ManageMealPlan.exists())
			sb.append("Manage Meal Plan;");
		if (!leftNav_Orders.exists())
			sb.append("Orders;");
		if (!leftNav_Payments.exists())
			sb.append("Payments;");
		if (!leftNav_Favorites.exists())
			sb.append("Favorites;");
		if (!leftNav_Profile.exists())
			sb.append("Profile;");
		if (!leftNav_CustomerCare.exists())
			sb.append("Customer Care;");
		
		if (sb.length() > 0)
			return sb.toString();
		else
			return "Success";
	}
	
	@Override
	public String getURI()
	{
		return "/account";
	}
	
}

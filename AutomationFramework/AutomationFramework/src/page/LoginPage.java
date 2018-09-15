package page;

import org.openqa.selenium.NoSuchElementException;

import common.UserType;
import element.BaseWebElement;
import framework.AbstractDriver;
import framework.FindMethod;
import framework.WWWDriver;

public class LoginPage extends BasePage {
	
	//Welcome Back Login Screen
	public BaseWebElement wb_login = new BaseWebElement(FindMethod.CLASSNAME, "snap-input--text");
	public BaseWebElement wb_password = new BaseWebElement(FindMethod.CLASSNAME, "snap-input--password");
	public BaseWebElement sb_signin = new BaseWebElement(FindMethod.XPATH,"//button[@type='submit']");
	public BaseWebElement sb_signUp = new BaseWebElement(FindMethod.LINKTEXT,"Sign Up");
	
	//New User Sign Up Screen
	public BaseWebElement closeX = new BaseWebElement(FindMethod.CLASSNAME, "component-modal--close");
	public BaseWebElement help = new BaseWebElement(FindMethod.CLASSNAME, "help-action");
	public BaseWebElement signUp = new BaseWebElement(FindMethod.LINKTEXT, "Sign Up");
	public BaseWebElement phoneNumber = new BaseWebElement(FindMethod.ID, "phoneField");
	public BaseWebElement nextBtn = new BaseWebElement(FindMethod.CLASSNAME, "button--alt");
	public BaseWebElement smsCode = new BaseWebElement(FindMethod.CLASSNAME, "snap-input--number");
	public BaseWebElement password = new BaseWebElement(FindMethod.NAME, "password");
	
	//User Profile Screen
	public BaseWebElement completeMyProfile = new BaseWebElement(FindMethod.CLASSNAME, "continue-signup-link");
	public BaseWebElement firstName = new BaseWebElement(FindMethod.NAME, "first_name");
	public BaseWebElement lastName = new BaseWebElement(FindMethod.NAME, "last_name");
	public BaseWebElement email = new BaseWebElement(FindMethod.NAME, "email");
	public BaseWebElement city = new BaseWebElement(FindMethod.NAME, "city");
	public BaseWebElement rememberMe = new BaseWebElement(FindMethod.CLASSNAME, "snap-faux-checkbox");
	public BaseWebElement showPassword = new BaseWebElement(FindMethod.CLASSNAME, "snap-input-icon--password");
	public BaseWebElement forgotPassword = new BaseWebElement(FindMethod.CLASSNAME, "forgot-password-link");
	
	//Account Menu
	public BaseWebElement accountMenu = new BaseWebElement(FindMethod.CLASSNAME, "logged-in-link-icon");
	
	//Account SubMenu
	public BaseWebElement account_ManageMealPlan = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_manage_meal_plan");
	public BaseWebElement account_Orders = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_orders");
	public BaseWebElement account_Favorites = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_favorites");
	public BaseWebElement account_Profile = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_profile");
	public BaseWebElement account_CustomerCare = new BaseWebElement(FindMethod.CLASSNAME, "account-link--account_customer-care");
	public BaseWebElement account_SignOut = new BaseWebElement(FindMethod.CLASSNAME, "account-link--signout");
	
	/**
	 * Navigate to the new user sign in screen.
	 */
	public void goToSignUp()
	{
		navigate();
		
		//For some reason, it thinks that it clicks on this element before it ever reaches the page.
		//I really have no choice but to pause for a minute.
		AbstractDriver.pause(3000);
		signUp.click();
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
	public boolean goToAccountProfile()
	{
		WWWDriver.pause(5000); //TODO:  Remove This Wait.
		goToAccountMenu();
		account_Profile.click();
		
		return true;
	}
	
	public void goToAccountMenu()
	{
		accountMenu.click();
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
	
	@Override
	public String getURI()
	{
		return "/account";
	}
	
}

package mobilepage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;

import common.UserType;
import element.BaseMobileElement;
import framework.FindMethod;

public class SnapHome {
	
	public BaseMobileElement startLogin = new BaseMobileElement("Log In");
	public BaseMobileElement phoneNumber = new BaseMobileElement("Phone number");
	public BaseMobileElement next = new BaseMobileElement("next");
	public BaseMobileElement sixDigitCode = new BaseMobileElement("Verification code");
	public BaseMobileElement password = new BaseMobileElement("password");
	public BaseMobileElement submitLogin = new BaseMobileElement("log in");
	public BaseMobileElement thanksGotIt = new BaseMobileElement("Next");
	public BaseMobileElement alreadyHaveAccount = new BaseMobileElement("Scan");
	public BaseMobileElement passwordAlert = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeAlert");
	public BaseMobileElement pwd_resetPassword = new BaseMobileElement("reset password");
	public BaseMobileElement pwd_tryAgain = new BaseMobileElement("try again");
	
	public String login(String username, String pwd)
	{
		String status = "Success";
		try
		{
			startLogin.click();	
		}
		catch (NoSuchElementException e)
		{
			status="Login Link did not exist on the start page.";
			return status;
		}
			
		try
		{
			phoneNumber.setWebValue(username);
			next.click();
		}
		catch (NoSuchElementException e)
		{
			status="User was not prompted for a phone number.";
			return status;
		}
		
		try
		{
			password.setWebValue(pwd);
		}
		catch (NoSuchElementException e)
		{
			status="User was not prompted for a password.";
			return status;
		}
		try
		{
			if (submitLogin.isEnabled())
			{	
				submitLogin.click();
			}
			else
			{
				status = "The submit button was not clickable.";
				return status;
			}
		}
		catch (NoSuchElementException e)
		{
			status="Submit Button did not exist.";
			return status;
		}
		
		if (passwordAlert.exists())
		{
			status="User entered an incorrect password.";
			return status;
		}
		return status;
	}
	
	public boolean createAccountViaLogin(UserType user)
	{
		startLogin.click();
		return createAccount(user);
	}
	
	public boolean createAccountViaMealPlanning(UserType user)
	{
		IntroPage introPage = new IntroPage();
		introPage.clickNext();
		introPage.clickNext();
		introPage.clickNext();
		introPage.mealPlanButtonExists();
		introPage.menuButtonExists();
		introPage.shopMealPlans();
		introPage.clickNextMealPlanIntro();
		introPage.clickNextMealPlanIntro();
		introPage.getStarted();
		
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		mealPlanMainPage.selectHighProtein();
		mealPlanMainPage.letsGetStarted();
		
		return createAccount(user);
	}
	
	public String scrollMealPlanIntro()
	{
		try
		{
			IntroPage introPage = new IntroPage();
			introPage.clickNextMealPlanIntro();
			introPage.clickNextMealPlanIntro();
			introPage.getStarted();
		}
		catch (NoSuchElementException e)
		{
			return "Could not navigate through Meal Plan Intro.";
		}
		return "Success";
	}
	
	/**
	 * Create a new account with a unique phone number.
	 * 
	 * Returns true if the process completed.  I do not check the user account details.
	 * @throws  
	 */
	public boolean createAccount(UserType user)
	{
		phoneNumber.setWebValue(user.getUsername());
		
		//If next is not enabled it means we didn't enter a valid phone number.
		if (next.isEnabled())
			next.click();
		else
		{
			//"User did not enter a valid phone number.");
		}
		
		//If the password field exists, then it means the user already exists and we cannot continue.
		if (password.exists())
		{
			//throw new CreateAccountException("User did not enter a unique phone number.");
		}
		
		if (sixDigitCode.isEnabled())
		{
			sixDigitCode.setWebValue("222222"); //don't hardcode this
		}
		else
		{
			//throw new CreateAccountException("User was not prompted to enter a six digit code.");
		}
		
		//If Next isn't enabled, then again it didn't recognize our 6 digit code, or it wasn't entered properly
		if (next.isEnabled()) 
		{
			next.click();
		}
		else
		{
			//throw new CreateAccountException("User did not enter a valid six digit code.");
		}
		
		//We expect to be prompted to create a snap funds profile
		if (thanksGotIt.isEnabled())
		{
			thanksGotIt.click();
		}
		else
		{
			//throw new CreateAccountException("User was not prompted to create a new profile.");
		}
		
		//Now we switch to the create profile screen to complete the process
		CreateProfile createProfile = new CreateProfile();
		if (createProfile.firstName.exists())
		{
			createProfile.firstName.setWebValue(user.getFirstName());
			createProfile.lastName.setWebValue(user.getLastName());
			createProfile.email.setWebValue(user.getEmail());
			createProfile.selectCity();
			createProfile.password.setWebValue(user.getPassword());
		
			createProfile.done.click();
		}
		else
		{
			//throw new CreateAccountException("Create Profile Screen did not load successfully.");
		}
		
		return true;
	}

}

	
	
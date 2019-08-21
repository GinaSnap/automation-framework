package mobilepage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

import common.Location;
import common.PlanType;
import common.UserType;
import element.BaseMobileElement;
import element.NotificationWebElement;
import framework.FindMethod;
import page.LocalMenuPage;
import page.RequestZipcodePage;
import page.ShippingMenuPage;

public class SnapHome {
	
	private BaseMobileElement startLogin = new BaseMobileElement("LogIn");
	private BaseMobileElement phoneNumber = new BaseMobileElement("Phone number");
	private BaseMobileElement next = new BaseMobileElement("next");
	private BaseMobileElement sixDigitCode = new BaseMobileElement("Verification code");
	private BaseMobileElement password = new BaseMobileElement("Password");
	private BaseMobileElement submitLogin = new BaseMobileElement("log in");
	private BaseMobileElement submit = new BaseMobileElement("submit");
	private BaseMobileElement thanksGotIt = new BaseMobileElement("Next");
	private BaseMobileElement alreadyHaveAccount = new BaseMobileElement("Scan");
	private BaseMobileElement passwordError = new BaseMobileElement("password error");
	private BaseMobileElement resetPassword = new BaseMobileElement("reset password");
	private BaseMobileElement pwd_tryAgain = new BaseMobileElement("try again");
	private NotificationWebElement pushNotificationsPrompt = new NotificationWebElement("don’t miss out");
	private BaseMobileElement notNow = new BaseMobileElement("Not Now");
	private BaseMobileElement dontAllow = new BaseMobileElement("Don't Allow");
	private BaseMobileElement savePassword = new BaseMobileElement("Save Password");
	
	public String login(String username, String pwd)
	{
		String status = "Success";
		try
		{
			startLogin.click();	
		}
		catch (WebDriverException e)
		{
			status="Login Link did not exist on the start page.";
			return status;
		}
		
		savedPasswordNotNow();
			
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
		
		savedPasswordNotNow();
		
		status=enterPassword(pwd);
		
		return status;
	}
	
	public String enterPassword(String pwd)
	{
		String status = "Success";
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
		
		try
		{
			passwordError.waitUntilClickable();
			if (passwordError.exists())
			{
				status="User entered an incorrect password.";
				return status;
			}
		}
		catch (Exception e) {
			
		}
		
		savedPasswordNotNow();
		closePushNotificationPrompt();
		return status;
	}
	
	public String resetPassword(String username, String newPassword)
	{
		try
		{
			startLogin.click();	
		}
		catch (WebDriverException e)
		{
			return "Login Link did not exist on the start page.";
		}
		
		savedPasswordNotNow();
			
		try
		{
			phoneNumber.setWebValue(username);
			next.click();
		}
		catch (NoSuchElementException e)
		{
			return "User was not prompted for a phone number.";
		}
		
		savedPasswordNotNow();
		
		try {
			resetPassword.click();
		}
		catch (NoSuchElementException e) {
			return "Reset Password link was not displayed.";
		}
		
		try {
			sixDigitCode.setWebValue("222222");
			next.click();
		}
		catch (NoSuchElementException e) {
			return "User was not prompted for a six digit code.";
		}
		
		try {
			password.setWebValue("qqqqqqqq");
			submit.click();
		}
		catch (NoSuchElementException e) {
			return "User was not prompted for a new password.";
		}
		
		updatePasswordDontAllow();
		
		return "Success";
	}
	
	public void closePushNotificationPrompt()
	{
		try
		{
			pushNotificationsPrompt.waitUntilClickable();
			pushNotificationsPrompt.maybeLater();
		}
		catch (TimeoutException e)
		{
			return;
		}
	}
	
	public boolean createAccountViaLogin(UserType user)
	{
		startLogin.click();
		return createAccount(user);
	}
	
	public String createAccountViaMealPlanning_ShippingUser(UserType user)
	{
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		String nuOnboardingStatus = completeNewUserOnboarding();
		if (!nuOnboardingStatus.equals("Success"))
		{
			return nuOnboardingStatus;
		}
		
		String status = zipCodePage.enterZipCode(user.getZipCode());
		if (!status.equals("Success")) {
			return "Was not able to enter zip code at the end of user onboarding.";
		}
		
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();
		status = shippingMenuPage.selectMealPlan(PlanType.BALANCE);
		if (!status.equals("Success")) {
			return "Could not find the specified meal plan.";
		}
		
		status = shippingMenuPage.letsGetStarted();
		if (!status.equals("Success")) {
			return "User was not prompted to create an account.";
		}
		
		if (!createAccount(user)) {
			return "Was not able to create a new user account.";
		}
		
		return "Success";
	}
	
	public String createAccountViaMealPlanning_LocalUser(UserType user)
	{
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		
		String nuOnboardingStatus = completeNewUserOnboarding();
		if (!nuOnboardingStatus.equals("Success"))
		{
			return nuOnboardingStatus;
		}
		
		String status = zipCodePage.enterZipCode(user.getZipCode());
		if (!status.equals("Success")) {
			return "Was not able to enter zip code at the end of user onboarding.";
		}
		
		LowerNavPage lowerNav = new LowerNavPage();
		status = lowerNav.goToMealPlan();
		if (!status.equals("Success")) {
			return "Could not find the meal plan link in the lower navigation.";
		}
		
		MealPlanMainPage mealPlanMainPage = new MealPlanMainPage();
		status = mealPlanMainPage.selectLowCarb();
		if (!status.equals("Success")) {
			return "Could not find the Low Carb Meal Plan Card.";
		}
		
		status = mealPlanMainPage.letsGetStarted();
		if (!status.equals("Success")) {
			return "User was not prompted to create an account.";
		}
		
		if (!createAccount(user)) {
			return "Was not able to create a new user account.";
		}
				
		return "Success";
	}
	
	public String completeNewUserOnboarding() {
		
		IntroPage introPage = new IntroPage();
		RequestZipcodePage zipCodePage = new RequestZipcodePage();
		ShippingMenuPage shippingMenuPage = new ShippingMenuPage();
		
		if (!(introPage.clickNext() == "Success"))
		{
			return "Next button did not exist on the first onboarding screen.";
		}
		if (!(introPage.clickNext() == "Success"))
		{
			return "Next button did not exist on the second onboarding screen.";
		}
		if (!(introPage.clickNext() == "Success"))
		{
			return "Next button did not exist on the third onboarding screen.";
		}
		
		if (introPage.getStartedButtonExists())
		{
			introPage.getStarted();
		}
		else
		{
			return "Get Started button did not exist at the end of new user onboarding.";
		}
		
		return "Success";
	}

	public String completeMealPlanOnboarding() {
		IntroPage introPage = new IntroPage();

		if (!(introPage.clickNextMealPlanIntro().equals("Success")))
		{
			return "Next button did not exist on first page of subscription onboarding.";
		}
		if (!(introPage.clickNextMealPlanIntro().equals("Success")))
		{
			return "Next button did not exist on second page of subscription onboarding.";
		}
		if (!introPage.getStarted())
		{
			return "Get started button was not displayed at the end of meal plan onboarding.";
		}
		return "Success";
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
		savedPasswordNotNow();
		
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
		
		enterSixDigitCode();
		
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
			createProfile.zipCode.setWebValue(user.getZipCode());
			//createProfile.password.setWebValue(user.getPassword());
		
			createProfile.done.click();
		}
		else
		{
			return false;
		}
		
		closePushNotificationPrompt();
		
		return true;
	}

	/**
	 * Enter the Six Digit Code either when creating a new account OR to reset your password.
	 */
	public void enterSixDigitCode() {
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
	}
	
	public boolean savedPasswordNotNow()
	{
		try
		{
			notNow.waitUntilClickable();
			notNow.click();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean updatePasswordDontAllow()
	{
		try
		{
			savePassword.waitUntilClickable();
			savePassword.click();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean passwordErrorExists()
	{
		return passwordError.exists();
	}
	
	public boolean passwordError_TryAgain()
	{
		pwd_tryAgain.waitUntilClickable();
		pwd_tryAgain.click();
		
		return true;
	}

}

	
	
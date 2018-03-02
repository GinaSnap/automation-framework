package mobilepage;

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
	public BaseMobileElement thanksGotIt = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeButton[@name='Scan'][1]");
	public BaseMobileElement alreadyHaveAccount = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeButton[@name='Scan'][2]");
	
	public void login(String username, String pwd)
	{
		startLogin.click();		
		phoneNumber.setWebValue(username);
		next.click();
		
		password.setWebValue(pwd);
		submitLogin.click();
	}
	
	/**
	 * Create a new account with a unique phone number.
	 * This method spans multiple pages, but SnapHome seemed 
	 * like the most logical place to keep it.
	 * 
	 * Returns true if the process completed.  I do not check the user account details.
	 */
	public boolean createAccount(UserType user)
	{
		startLogin.click();
		phoneNumber.setWebValue(user.getUsername());
		next.click();
		
		//error checking needed here.
		
		sixDigitCode.setWebValue("222222"); //don't hardcode this
		next.click();
		
		//error checking needed here.
		
		thanksGotIt.click();
		
		//Error checking needed here.
		//Now we switch to the create profile screen to complete the process
		
		CreateProfile createProfile = new CreateProfile();
		createProfile.firstName.setWebValue(user.getFirstName());
		createProfile.lastName.setWebValue(user.getLastName());
		createProfile.email.setWebValue(user.getEmail());
		createProfile.selectCity();
		//createProfile.password.setWebValue(user.getPassword());
		
		createProfile.done.click();
		
		return true;
	}

}

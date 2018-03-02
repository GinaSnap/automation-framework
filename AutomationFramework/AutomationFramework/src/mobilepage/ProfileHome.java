package mobilepage;

import element.BaseMobileElement;

/**
 * This Class defines the behavior and elements on the Profile Page.
 * Access by Clicking on Account and Selecting Profile.
 * @author GMitchell
 *
 */
public class ProfileHome extends BasePage {
	
	//Main Account Page
	public BaseMobileElement btnGoBack = new BaseMobileElement("your account");
	public BaseMobileElement btnSave = new BaseMobileElement("save");
	public BaseMobileElement firstName = new BaseMobileElement("First name");
	public BaseMobileElement lastName = new BaseMobileElement("Last name");
	public BaseMobileElement email = new BaseMobileElement("Email address");
	public BaseMobileElement phoneNumber = new BaseMobileElement("Password");  //I'm not sure why this is labeled as Password.
	public BaseMobileElement btnNewPassword = new BaseMobileElement("NewPassword");
	
	/**
	 * Click the Back Arrow in the top Navigation.
	 */
	public void goBack()
	{
		btnGoBack.click();
	}
	
	/**
	 * Save Profile Changes
	 */
	public void save()
	{
		btnSave.click();
	}
	
	/**
	 * Update Password - not developed
	 */
	public void updatePassword(String password)
	{
		
		
	}
	
	

}

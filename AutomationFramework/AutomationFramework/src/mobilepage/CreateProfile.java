package mobilepage;

import element.BaseMobileElement;
import framework.FindMethod;

/**
 * This class defines the methods and elements on the create new profile screen.
 * 
 * @author GMitchell
 *
 */
public class CreateProfile extends BasePage {
	
	//IDs requested.  Using XPath for now.
	public BaseMobileElement firstName = new BaseMobileElement("FirstName");
	public BaseMobileElement lastName = new BaseMobileElement("LastName");
	public BaseMobileElement email = new BaseMobileElement("Email");
	public BaseMobileElement password = new BaseMobileElement("Password");
	public BaseMobileElement city = new BaseMobileElement("City");
	public BaseMobileElement goBack = new BaseMobileElement("Close");
	public BaseMobileElement done = new BaseMobileElement("done");
	public BaseMobileElement viewPassword = new BaseMobileElement("icn masked");
	
	//These elements are displayed when we click the city button
	public BaseMobileElement austin = new BaseMobileElement("Austin");
	public BaseMobileElement chicago = new BaseMobileElement("Chicago");
	public BaseMobileElement dallas = new BaseMobileElement("Dallas/Fort Worth");
	public BaseMobileElement houston = new BaseMobileElement("Houston");
	public BaseMobileElement philly = new BaseMobileElement("Philadelphia");
	public BaseMobileElement close = new BaseMobileElement("close");
	
	/**
	 * Click done to save new Profile
	 */
	public void clickDone()
	{
		done.click();
	}
	
	/**
	 * Closes the new Profile Screen
	 */
	public void goBack()
	{
		goBack.click();
	}
	
	/**
	 * Click to make the password visible.
	 */
	public void viewPassword()
	{
		viewPassword.click();
	}
	
	/**
	 * Select a city from the dropdown list.
	 */
	public void selectCity()
	{
		city.click();
		austin.click();
	}
	

}


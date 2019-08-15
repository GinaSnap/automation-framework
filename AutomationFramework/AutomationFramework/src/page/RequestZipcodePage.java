package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.FindMethod;
import mobilepage.BasePage;

public class RequestZipcodePage extends BasePage {
	
	public BaseMobileElement zipCode = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeOther[@name=\"Page1\"]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
	public BaseMobileElement OK = new BaseMobileElement("OK");
	public BaseMobileElement signIn = new BaseMobileElement("ResetPassword");
	
	public BaseMobileElement outsideShippingZone = new BaseMobileElement("Sorry, your address is outside of our shipping zone");
	public BaseMobileElement enterEmail = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeTextField[@value='enter your email']");
	public BaseMobileElement notifyMe = new BaseMobileElement("NOTIFY ME");
	public BaseMobileElement viewSampleMenu = new BaseMobileElement("VIEW SAMPLE MENU");
	
	public RequestZipcodePage() {
		
	}
	
	/**
	 * Enter ZipCode and click OK.
	 * @param zipCodeText
	 * @return
	 */
	public String enterZipCode(String zipCodeText)
	{
		try 
		{
			zipCode.setWebValue(zipCodeText);
		} 
		catch (NoSuchElementException e)
		{
			return "ZipCode field was not displayed.";
		}
		
		try
		{
		 OK.click();
		}
		catch (NoSuchElementException e)
		{
			return "OK Field was not displayed.";
		}
		return "Success";
	}
	
	/**
	 * Click the Sign in Button at the bottom of the screen
	 * @return
	 */
	public String clickSignIn()
	{
		try
		{
			signIn.click();
		}
		catch (NoSuchElementException e)
		{
			return "Sign In Button was not displayed.";
		}
		return "Success";
	}
	
	/**
	 * I may add more to this, but for now, just making sure that the one main field is displayed.
	 * @return
	 */
	public boolean isLoaded()
	{
		if (outsideShippingZone.exists() && enterEmail.exists() && notifyMe.exists() && viewSampleMenu.exists()) {
			return true;
		}
		return false;
	}
	
	

}
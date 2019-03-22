package page;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.FindMethod;
import mobilepage.BasePage;

public class RequestZipcodePage extends BasePage {
	
	public BaseMobileElement zipCode = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeOther[@name=\"Page1\"]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
	public BaseMobileElement OK = new BaseMobileElement("OK");
	public BaseMobileElement signIn = new BaseMobileElement("ResetPassword");
	
	public BaseMobileElement unavailable = new BaseMobileElement("unavailable");
	public BaseMobileElement willDo = new BaseMobileElement("will do!");
	
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
		return zipCode.exists();
	}
	
	public boolean isZipUnavailable()
	{
		try
		{
			unavailable.waitUntilClickable();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		return unavailable.exists();
	}
	
	public void clickWillDo()
	{
		willDo.waitUntilClickable();
		willDo.click();
	}
	

}
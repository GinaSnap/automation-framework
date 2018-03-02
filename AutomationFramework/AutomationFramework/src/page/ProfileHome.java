package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.UserType;
import element.BaseWebElement;
import framework.FindMethod;
import framework.WWWDriver;
import page.BasePage;

public class ProfileHome extends BasePage {
	public BaseWebElement welcomeMessage = new BaseWebElement(FindMethod.CLASSNAME, "welcome-message-contents");
	public BaseWebElement pointsMessage = new BaseWebElement(FindMethod.CLASSNAME, "sub-message");
	public BaseWebElement referButton = new BaseWebElement(FindMethod.CLASSNAME, "refer-button-wrapper");
	public BaseWebElement firstName = new BaseWebElement(FindMethod.NAME, "firstName");
	public BaseWebElement lastName = new BaseWebElement(FindMethod.NAME, "lastName");
	public BaseWebElement email = new BaseWebElement(FindMethod.NAME, "email");
	public BaseWebElement phone = new BaseWebElement(FindMethod.NAME, "phone");
	public BaseWebElement showPasswordToggle = new BaseWebElement(FindMethod.CLASSNAME, "snap-input-icon--password");
	
	private WebElement getProfileSaveButton()
	{
		List<WebElement> listSubmitButtons = WWWDriver.instance.findElements(By.xpath("//button[@type='submit']"));
		return listSubmitButtons.get(0);
	}
	
	private WebElement getPasswordSaveButton()
	{
		List<WebElement> listSubmitButtons = WWWDriver.instance.findElements(By.xpath("//button[@type='submit']"));
		return listSubmitButtons.get(0);
	}
	
	/**
	 * To be Developed
	 * @param updateUser
	 */
	public void updateProfile(UserType updateUser)
	{
		
	}
	
	/**
	 * To be Developed
	 * @param updateUser
	 */
	public void updatePassword(UserType updateUser)
	{
		
	}
	
	/**
	 * To Be Developed
	 */
	public void showPassword()
	{
		
	}
	
	/**
	 * To Be Developed
	 */
	public void hidePassword()
	{
		
	}
}

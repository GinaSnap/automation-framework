package page;

import org.openqa.selenium.By;

import element.BaseWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class WebPopupConfirmation extends BaseWebElement {

	public WebPopupConfirmation(FindMethod findMethod, String id) {
		super(findMethod, id);
	}
	
	BaseWebElement confirmPopup = new BaseWebElement(FindMethod.CLASSNAME, "component-action--primary");
	BaseWebElement cancelPopup = new BaseWebElement(FindMethod.CLASSNAME, "component-action--cancel");
	
	public boolean exists()
	{
		try
		{
			return getWebElement().isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
				
	}
	
	public String getMessage()
	{
		return getWebElement().findElement(By.className("message")).getText();
	}
	
	public String getTitle()
	{
		return getWebElement().findElement(By.className("title")).getText();
	}
	
	public String confirmPopup()
	{
		confirmPopup.waitUntilClickable();
		confirmPopup.click();
		return "Success";
	}
	
	public String cancelPopup()
	{
		try
		{
			cancelPopup.waitUntilClickable();
			cancelPopup.click();
			return "Success";
		}
		catch (Exception e)
		{
			return "Could not find cancel button.";
		}
	}

}

package element;

import org.openqa.selenium.NoSuchElementException;

public class NotificationWebElement extends BaseMobileElement {
	
	BaseMobileElement allowNotifications = new BaseMobileElement("ALLOW NOTIFICATIONS");
	BaseMobileElement maybeLater = new BaseMobileElement("MAYBE LATER");
	BaseMobileElement dontAllow = new BaseMobileElement("Don't Allow");
	BaseMobileElement allow = new BaseMobileElement("Allow");

	public NotificationWebElement(String id) {
		super(id);
	}
	
	public String turnOnPushNotifications()
	{
		try
		{
			allowNotifications.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Allow Notifications section.";
		}
		
		try
		{
			allow.click();
		}
		catch (NoSuchElementException e)
		{
			return "iPhone Settings Popup was not displayed.";
		}
		return "Success";
	}
	
	public String turnOffPushNotifications()
	{
		try
		{
			allowNotifications.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Allow Notifications section.";
		}
		try
		{
			dontAllow.click();
		}
		catch (NoSuchElementException e)
		{
			return "iPhone Settings Popup was not displayed.";
		}
		return "Success";
	}
	
	public String maybeLater()
	{
		try
		{
			maybeLater.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Maybe Later link.";
		}
		return "Success";
	}

}

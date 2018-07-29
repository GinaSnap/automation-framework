package page;

import org.openqa.selenium.WebElement;

import element.BaseWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public abstract class BasePage {

	public void navigate()
	{
		WWWDriver.navigate(WWWDriver.RootURL + getURI());
	}
	public boolean isLoaded()
	{
		return WWWDriver.instance.getCurrentUrl().endsWith(getURI());
	}
	
	public String getURI()
	{
		return "";
	}
	
	public boolean hasError()
	{
		BaseWebElement errorElement = new BaseWebElement(FindMethod.CLASSNAME, "form_errors");
		return errorElement.exists();
	}
	
	public String getErrorMessage()
	{
		BaseWebElement errorElement = new BaseWebElement(FindMethod.CLASSNAME, "form_errors");
		return errorElement.getText();
	}
	
	public boolean hasPopup()
	{
		WebPopupConfirmation popup = new WebPopupConfirmation(FindMethod.CLASSNAME, "component-alert");
		return popup.exists();
	}
	
	public String getPopupTitle()
	{
		WebPopupConfirmation popup = new WebPopupConfirmation(FindMethod.CLASSNAME, "component-alert");
		return popup.getTitle();
	}
	
	public String getPopupMessage()
	{
		WebPopupConfirmation popup = new WebPopupConfirmation(FindMethod.CLASSNAME, "component-alert");
		return popup.getMessage();
	}
	
	public void confirmPopup()
	{
		WebPopupConfirmation popup = new WebPopupConfirmation(FindMethod.CLASSNAME, "component-alert");
		popup.confirmPopup();
		waitForPageLoadingIndicator();
	}
	
	public void cancelPopup()
	{
		WebPopupConfirmation popup = new WebPopupConfirmation(FindMethod.CLASSNAME, "component-alert");
		popup.cancelPopup();
	}
	
	public String waitForPageLoadingIndicator()
	{
		//I'm not really sure what I'm waiting for yet.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		return "Success";
	}

}

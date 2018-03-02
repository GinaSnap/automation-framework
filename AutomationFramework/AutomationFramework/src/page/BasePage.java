package page;

import element.BaseWebElement;
import framework.AbstractDriver;
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

}

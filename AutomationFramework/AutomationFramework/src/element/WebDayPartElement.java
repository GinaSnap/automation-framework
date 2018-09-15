package element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import framework.WWWDriver;

public class WebDayPartElement extends BaseWebElement {
	
	String id;

	public WebDayPartElement(String id) {
		super(id);
		this.id=id;
	}
	
	public String selectElement()
	{
		try
		{
			if (!isDayPartSelected())
			{
				getDayPartElement().click();
			}
		}
		catch (NoSuchElementException e)
		{
			return "Day Part Element was not visible.";
		}
		return "Success";
	}
	
	public String deselectElement()
	{
		try
		{
			if (isDayPartSelected())
			{
				getDayPartElement().click();
			}
		}
		catch (NoSuchElementException e)
		{
			return "Day Part Element was not visible.";
		}
		return "Success";
	}
	
	private WebElement getDayPartElement()
	{
		List<WebElement> buttonList = WWWDriver.instance.findElements(By.className("component-pill"));
		for (WebElement dayPart : buttonList) {
			if (dayPart.getText().contains(id))
			{
				return dayPart;
			}
		}
		return null;
	}
	
	private boolean isDayPartSelected()
	{
		return !getDayPartElement().getAttribute("class").contains("inactive");
	}

}

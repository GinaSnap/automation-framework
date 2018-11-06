package mobilepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class OrdersPage extends BasePage {
	
	private final BaseMobileElement upcomingOrders = new BaseMobileElement("UPCOMING");
	private final BaseMobileElement pastOrders = new BaseMobileElement("PAST");
	private final BaseMobileElement close = new BaseMobileElement("Close");
	
	/**
	 * Click on the Past Orders link on the Main Orders page.
	 * @return
	 * Return "Success" if user is able to click on the link.
	 * Return an error string otherwise.
	 */
	public String goToPastOrders()
	{
		String status="Success";
		try
		{
			pastOrders.click();
		}
		catch (NoSuchElementException e)
		{
			status="Past Orders link is not displayed.";
		}
		return status;
	}
	
	/**
	 * Click Upcoming orders tab from the main orders page.
	 * @return
	 * Return "Success" if the user is able to click on the link.
	 * Return an error string otherwise.
	 */
	public String goToUpcomingOrders()
	{
		String status="Success";
		try
		{
			upcomingOrders.click();
		}
		catch (NoSuchElementException e)
		{
			status="Upcoming Orders link is not displayed.";
		}
		return status;
	}
	
	
	public boolean orderExists(String productName)
	{
		List<MobileElement> ordersList = getOrderElements();
		for (MobileElement order : ordersList)
		{
			
		}
		
		return true;
	}
	
	/**
	 * From either the Past or Upcoming Orders screen, return the number of orders listed.
	 * @return
	 * Integer value containing the total number of orders listed.
	 */
	public int getNumberOfOrders()
	{
		List<MobileElement> orderElements = getOrderElements();
		return orderElements.size();
	}
	
	/**
	 * Returns the parent element which contains a list of Order "cells".
	 * @return
	 * Returns a web element pointing to the root table element.
	 */
	private MobileElement getOrderTable()
	{
		try
		{
			return MobileDriver.instance.findElementByXPath("//XCUIElementTypeTable");
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * Returns a list of Elements representing each order listed on the page.
	 * @return  Returns an ArrayList of Elements.
	 */
	private List<MobileElement> getOrderElements()
	{
		List<MobileElement> ordersList = new ArrayList<MobileElement>();
		ordersList = getOrderTable().findElementsByXPath("//XCUIElementTypeCell[XCUIElementTypeImage]");
		return ordersList;
	}
	
	/**
	 * Click the close button at the top of the orders screen.
	 * @return Returns "Success" if we were able to find and click the close button.
	 */
	public String closeOrdersScreen()
	{
		try
		{
			close.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find close button on the orders screen.";
		}
		return "Success";
	}

}

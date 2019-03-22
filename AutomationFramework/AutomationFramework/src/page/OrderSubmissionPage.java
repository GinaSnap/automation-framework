package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import framework.WWWDriver;

public class OrderSubmissionPage extends BasePage {
	
	public String getOrderNumber()
	{
		//Requested ID from dev  WEB-1479
		return "";
	}
	
	public String getStatusText()
	{
		//We're getting your order ready....
		//Requested ID from dev WEB-1479
		return "";
	}
	
	public String getETA()
	{
		//Requested ID from dev  WEB-1479
		return "";
	}

	/**
	 * Returns a list of toolbar elements on the order details page.
	 * Currently:  Order Details, Get Help, Cancel Order
	 * @return List of WebElements for each Toolbar item on the Order Details Page.
	 * Have requested an id on each element, but for now...
	 *  index 0 - Directions To Store
	 *  index 1 - Order Details
	 *  index 2 - Get Help
	 *  index 3 - View Store Details
	 *  index 4 - call for curbside pickup
	 *  index 5 - cancel order
	 */
	private List<WebElement> getOrderToolbarList()
	{
		List<WebElement> toolbarList = new ArrayList<WebElement>();
		try {
			toolbarList = WWWDriver.instance.findElements(By.xpath("//ul[@class='order-toolbar-list']/li"));
		}
		catch (Exception e) {
			
		}
		return toolbarList;
	}
	
	/**
	 * Click on the Order Details Link.
	 */
	public String goToOrderDetails()
	{
		String status = "Success";
		try {
		getOrderToolbarList().get(0).click();
		}
		catch (NoSuchElementException e)
		{
			return "The order details button did not exist.";
		}
		return status;
	}
	
	/**
	 * Click on the Help Link.
	 */
	public String goToHelp()
	{
		String status = "Success";
		try {
		getOrderToolbarList().get(1).click();
		}
		catch (NoSuchElementException e)
		{
			return "The help button did not exist.";
		}
		return status;
	}
	
	/**
	 * Click on the Cancel Order Link.
	 */
	public String cancelOrder()
	{
		String status = "Success";
		try {
		getOrderToolbarList().get(2).click();
		}
		catch (NoSuchElementException e)
		{
			return "The cancel order button did not exist.";
		}
		confirmPopup();
		return status;
	}

}

package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;
import framework.FindMethod;

public class OrderSubmissionPage extends BasePage {
	
	private final BaseMobileElement close = new BaseMobileElement("Close");
	private final BaseMobileElement header = new BaseMobileElement("Header");
	private final BaseMobileElement directions = new BaseMobileElement("DIRECTIONS");
	private final BaseMobileElement order = new BaseMobileElement("ORDER");
	private final BaseMobileElement help = new BaseMobileElement("HELP");
	private final BaseMobileElement more = new BaseMobileElement("MORE");
	private final BaseMobileElement viewStoreDetails = new BaseMobileElement("view store details");
	private final BaseMobileElement callForCurbsidePickup = new BaseMobileElement("call for curbside pickup");
	private final BaseMobileElement cancelOrder = new BaseMobileElement("cancel order");
	private final BaseMobileElement notificationSettings = new BaseMobileElement("notification settings");
	private final BaseMobileElement closeMoreOptions = new BaseMobileElement("close");
	private final BaseMobileElement alertPopup = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeAlert");
	private final BaseMobileElement allow = new BaseMobileElement("Allow");
	private final BaseMobileElement dontAllow = new BaseMobileElement("Don’t Allow");
	private final BaseMobileElement cancel_neverMind = new BaseMobileElement("never mind");
	private final BaseMobileElement cancel_confirmCancel = new BaseMobileElement(FindMethod.XPATH,"//XCUIElementTypeButton[@name=\"cancel order\"]");
	
	public void closeScreen()
	{
		close.click();
	}
	
	public String getOrderNumber()
	{
		return header.getText();
	}
	
	public void clickDirections()
	{
		directions.click();
	}
	
	public void goToOrderDetails()
	{
		order.click();
	}
	
	public void clickHelp()
	{
		help.click();
	}
	
	public void clickMore()
	{
		more.click();
	}
	
	public void viewStoreDetails()
	{
		clickMore();
		viewStoreDetails.click();
	}
	
	public void callForCurbsidePickup()
	{
		clickMore();
		callForCurbsidePickup.click();
	}
	
	/**
	 * From the orders page, click More.... and then Cancel Order.
	 * Click Yes on the Confirmation Popup.
	 * @return
	 * Returns "Success" if the user is able to click Cancel.
	 * Returns an Error String with a description if not.
	 */
	public String cancelOrder()
	{
		String status="Success";
		try
		{
			clickMore();
		}
		catch (NoSuchElementException e)
		{
			status="More Actions button was not displayed.";
			return status;
		}
		
		try
		{
			cancelOrder.click();
		}
		catch (NoSuchElementException e)
		{
			status="Cancel Order button was not displayed.";
			return status;
		}
		
		status=confirmCancelOrder();
		
		return status;
	}
	
	public void viewNotificationSettings()
	{
		clickMore();
		notificationSettings.click();
	}
	
	public void allowNotifications()
	{
		try
		{
			alertPopup.waitUntilClickable();
			allow.click();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void dontAllowNotifications()
	{
		try
		{
			alertPopup.waitUntilClickable();
			dontAllow.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**
	 * Click Cancel on the Confirm Cancellation Popup
	 * @return
	 * Returns "Success" if the user was able to click Cancel on the popup.
	 * Returns an Error String if not.
	 */
	public String confirmCancelOrder()
	{
		String status="Success";
		try
		{
			cancel_confirmCancel.click();
		}
		catch (Exception e)
		{
			status="Cancel button was not displayed.";
			return status;
		}
		return status;
	}
	
	public void neverMindCancelOrder()
	{
		cancel_neverMind.click();
	}
}

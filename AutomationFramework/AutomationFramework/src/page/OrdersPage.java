package page;

import java.util.List;

import element.BaseWebElement;
import element.WebOrderListWebElement;
import element.WebOrderWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class OrdersPage extends BasePage {
	
	private BaseWebElement noOrdersSection = new BaseWebElement(FindMethod.CLASSNAME, "no-orders");
	private BaseWebElement viewOurMenuBtn = new BaseWebElement(FindMethod.XPATH, "//a[text()='view our menu']");
	private BaseWebElement pastOrders = new BaseWebElement(FindMethod.XPATH, "//div[@title='Past orders']");
	private BaseWebElement upcomingOrders = new BaseWebElement(FindMethod.XPATH, "//div[@title='Upcoming orders']");
	private WebOrderListWebElement ordersListActive = new WebOrderListWebElement(FindMethod.CLASSNAME, "orders-list--active");
	private WebOrderListWebElement ordersListScheduled = new WebOrderListWebElement(FindMethod.CLASSNAME, "orders-list--scheduled");
	private WebOrderListWebElement ordersListPast = new WebOrderListWebElement(FindMethod.CLASSNAME, "orders-list--inactive");

	
	public boolean goToPastOrders()
	{
		pastOrders.click();
		return waitUntilSelected(pastOrders,5000);
	}
	
	public boolean goToUpcomingOrders()
	{
		upcomingOrders.click();
		return waitUntilSelected(pastOrders,5000);
	}
	
	public int getNumPastOrders()
	{
		goToPastOrders();
		WWWDriver.pause(1000); //TODO:  Pause
		return ordersListPast.getNumOrders();
	}
	
	public int getNumScheduledOrders()
	{
		goToUpcomingOrders();
		WWWDriver.pause(1000);  //TODO:  Pause
		return ordersListScheduled.getNumOrders();
	}
	
	public int getNumActiveOrders()
	{
		goToUpcomingOrders();
		WWWDriver.pause(1000);  //TODO:  Pause
		return ordersListActive.getNumOrders();
	}
	
	public String viewActiveOrder(String orderNum)
	{
		goToUpcomingOrders();
		List<WebOrderWebElement> listOrders = ordersListActive.getOrderList();
		for (WebOrderWebElement order : listOrders)
		{
			if (order.getOrderNumber().equals(orderNum))
			{
				order.clickView();
				return "Success";
			}
		}
		return String.format("Could not find the given order [%s].", orderNum);
	}
	
	public boolean waitUntilSelected(BaseWebElement element, long timeoutMillis)
	{
		long startTime = System.currentTimeMillis();
		
		while (!isSelected(element) && (System.currentTimeMillis() < (startTime + timeoutMillis)))
		{
			WWWDriver.pause(1000);
		}
		return isSelected(element);
	}
	
	public boolean isSelected(BaseWebElement element)
	{
		return !element.getWebElement().getAttribute("class").contains("inactive");
	}

}

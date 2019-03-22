package element;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.FindMethod;

public class WebOrderListWebElement extends BaseWebElement {

	public WebOrderListWebElement(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public WebOrderListWebElement(FindMethod findMethod, String id) {
		super(findMethod, id);
		// TODO Auto-generated constructor stub
	}
	
	public int getNumOrders()
	{
		try
		{
			return getOrderList().size();
		}
		catch (Exception e)
		{
			return -1;
		}
	}
	
	public List<WebOrderWebElement> getOrderList()
	{
		List<WebOrderWebElement> orderList = new ArrayList<WebOrderWebElement>();
		
		List<WebElement> elementList = getWebElement().findElements(By.className("component-order-list-item"));
		for (int i=0;i<elementList.size();i++)
		{
			WebOrderWebElement orderElement = new WebOrderWebElement(FindMethod.XPATH, elementList.get(i).getAttribute("xpath"));
			orderList.add(orderElement);
		}
		return orderList;
	}

}

package element;

import java.util.ArrayList;
import java.util.List;

import framework.FindMethod;
import io.appium.java_client.MobileElement;

public class MobileOrderElement extends BaseMobileElement {
	
	public MobileOrderElement(String id) {
		super(id);
	}
	
	public MobileOrderElement(FindMethod findMethod, String id)
	{
		super(findMethod, id);
	}
	
	private final int MONTH = 0;
	private final int DAY = 2;
	private final int YEAR = 1;
	private final int FULFILLMENT = 3;
	private final int PRODUCT = 4;
	private final int STATUS = 5;

	public String getOrderMonth()
	{
		return getOrderStaticTextElements().get(MONTH).getAttribute("name");
	}
	
	public String getOrderDay()
	{
		return getOrderStaticTextElements().get(DAY).getAttribute("name");
	}
	
	public String getOrderYear()
	{
		return getOrderStaticTextElements().get(YEAR).getAttribute("name");
	}
	
	public String getOrderFulfillmentType()
	{
		return getOrderStaticTextElements().get(FULFILLMENT).getAttribute("name");
	}
	
	public String getOrderProductName()
	{
		return getOrderStaticTextElements().get(PRODUCT).getAttribute("name");
	}
	
	public String getOrderStatus()
	{
		return getOrderStaticTextElements().get(STATUS).getAttribute("name");
	}
	
	private List<MobileElement> getOrderStaticTextElements()
	{
		List<MobileElement> orderElements = new ArrayList<MobileElement>();
		orderElements = getMobileElement().findElementsByXPath("//XCUIElementTypeStaticText");
		return orderElements;
	}
}

package element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import framework.FindMethod;

public class BasketOrderWebElement extends BaseWebElement {

	public BasketOrderWebElement(FindMethod findMethod, String id) {
		super(findMethod, id);
		// TODO Auto-generated constructor stub
	}
	
	//Basket items
		//component-basket-item-lists 
			//basket-item-list-section
				//basket-items-section-title  "Available today"
			//component-list-basket-items 
				//component-basket-item ***
					//basket-item-photo  get alt attribute for help text
					//product-info-container
						//product-info
							//product-name
							//product-variant-info
						//product-purchase-info
							//select element 'quantity'
							//product purchase price
	/**
	 * Returns the product name of the basket item.
	 * @return String value containing the product name.
	 */
	public String getProductName()
	{
		String productName="";
		try {
			productName = getWebElement().findElement(By.xpath("//product-name")).getText();
		}
		catch (Exception e) {
			
		}
		return productName;
	}
	
	/**
	 * Returns the entire String listed for size and calories
	 * @return String in the format 'size | 100 calories'
	 */
	public String getProductSizeAndCalories()
	{
		String productName="";
		try {
			productName = getWebElement().findElement(By.xpath("//product-name")).getText();
		}
		catch (Exception e) {
			
		}
		return productName;
	}
	
	/**
	 * (Not Developed) Click the Remove link in the shopping basket.
	 * @return 
	 * Returns 'Success' if the user was able to click the link.
	 * Returns an error string otherwise.
	 */
	public String clickRemove()
	{
		return "TBD";
	}
	
	/**
	 * Return the number of items ordered.
	 * @return Integer value that is selected in the Quanitity Dropdown list.
	 */
	public int getQuantityOrdered()
	{
		String numOrdered="";
		try {
			numOrdered = getWebElement().findElement(By.className("quantity")).getText();
		}
		catch (Exception e) {
			
		}
		return Integer.parseInt(numOrdered);
	}
	
	/**
	 * (Not Developed) Select the number to order from the Quantity Drop Down List.
	 * @param numItems Enter the number of items to order.
	 * @return
	 * Return "Success" if user was able to select the number of items.
	 * Return Error String otherwise.
	 */
	public String selectQuantity(int numItems)
	{
		return "TBD";
	}
	
	/**
	 * Return the price of the basket item.
	 * @return String value with the price of the item.
	 */
	public String getPrice()
	{
		try
		{
			return getWebElement().findElement(By.className("product-purchase-price")).getText();
		}
		catch (NoSuchElementException e)
		{
			return "Error:  blahblahblahblah";
		}
	}

}

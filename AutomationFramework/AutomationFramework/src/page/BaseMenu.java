package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import element.BaseWebElement;
import element.ProductCardWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class BaseMenu extends BasePage {
	
	public BaseWebElement categorySelector = new BaseWebElement(FindMethod.CLASSNAME,"categorySelector");
	
	/**
	 * Return the menu name.
	 * @return String value containing the menu name.
	 */
	public String getCategoryName()
	{
		return categorySelector.getText();
	}
	
	/**
	 * For the purposes of a smoke test, add the first item in the menu to the cart.
	 * @return Returns the product name that was added for verification purposes.
	 */
	public String addFirstItemToCart()
	{
		WWWDriver.pause(3000); //TODO : figure out how to get rid of this.
		List<WebElement> productList = getProductList();
		try
		{
			if (productList.size() > 0)
			{
				String xPath = String.format("//div[@data-reactid='%s']", productList.get(0).getAttribute("data-reactid"));
				ProductCardWebElement productCard = new ProductCardWebElement(FindMethod.XPATH, xPath);
				productCard.addToCart();
				return productCard.getProductName();
			}
			else
			{
				return "";
			}
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	private List<WebElement> getProductList()
	{
		List<WebElement> productList = new ArrayList<WebElement>();
		try
		{
			productList = WWWDriver.instance.findElements(By.className("product-card"));
		}
		catch (Exception e)
		{
			return productList;  //Just return the empty list.
		}
		return productList;
	}

}

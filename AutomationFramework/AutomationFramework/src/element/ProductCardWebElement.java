package element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.FindMethod;
import framework.WWWDriver;

public class ProductCardWebElement extends BaseWebElement {

	public ProductCardWebElement(FindMethod findMethod, String id) {
		super(findMethod, id);
	}
	
	public ProductCardWebElement(String id) {
		super(id);
	}
	
	/**
	 * Return the name of the product listed on the screen.
	 * @return String value containing the product name.
	 */
	public String getProductName()
	{
		try {
			WebElement productName = getWebElement().findElement(By.className("productName"));
			return productName.getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Return the number of calories for the given product.
	 * @return String value with the number of calories.
	 */
	public String getCalories()
	{
		try {
			WebElement productCal = getWebElement().findElement(By.className("calorieRange"));
			return productCal.getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Return the price range of the given product.
	 * @return String value with the price range value.
	 */
	public String getPriceRange()
	{
		try {
			WebElement productPrice = getWebElement().findElement(By.className("priceRange"));
			return productPrice.getText();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Add Item to cart and return the name of the item.
	 * @return
	 */
	public boolean addToCart()
	{
		try {
			//Click the Add To Cart Button
			WebElement addToCartButton = getWebElement().findElement(By.className("add-to-basket-button"));
			addToCartButton.click();
			
			selectSize();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Select Small or Medium for the given product. (For now, let's just select small)
	 * This assumes that the add to cart button has already been 
	 * clicked.  It should only be called by addToCart().
	 * @return Return true if we were able to select the size indicated.
	 */
	private boolean selectSize()
	{
		try {
			WebElement optionsShown = getWebElement().findElement(By.className("product-card-options"));
			String optionsShownClass = optionsShown.getAttribute("class");
			if (optionsShownClass.contains("showing--true"))
			{
				List<WebElement> sizeList = optionsShown.findElements(By.className("component-add-to-basket-button"));
				if (sizeList.size() > 0)
				{
					sizeList.get(0).click();
					WebElement optionsClose = optionsShown.findElement(By.className("card-options-close-icon"));
					optionsClose.click();
					return true; // We were able to click the option we wanted, and close.
				}
				else
				{
					return false; // No size options were available but should have been.
				}
			}
			else
			{
				return false; // This product does not have size variants.
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Click on the product to load the product detail page.
	 * Returns true if the product element was found, and click was successful.
	 */
	public boolean loadProductDetail()
	{
		try {
			WebElement productElement = getWebElement().findElement(By.className("product-card-content"));
			productElement.click();
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

}

package element;

import java.util.List;

import framework.FindMethod;
import io.appium.java_client.MobileElement;

public class MobileProductCardWebElement extends BaseMobileElement {
	
	public MobileProductCardWebElement(String id) {
		super(id);
	}
	
	public MobileProductCardWebElement(FindMethod findMethod, String id)
	{
		super(findMethod, id);
	}
	
	private final int PRODUCT_NAME = 0;
	private final int CALORIES = 1;
	private final int QUANTITY = 1;
	private final int PRICE = 3;
	private final int IMAGE = 0;
	
	
	/**
	 * Return the name of the product listed on the screen.
	 * @return String value containing the product name.
	 */
	public String getProductName()
	{
		try {
			List<MobileElement> listStaticTextElements = getMobileElement().findElementsByXPath("//XCUIElementTypeStaticText");
			return listStaticTextElements.get(PRODUCT_NAME).getAttribute("name");
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
			List<MobileElement> listStaticTextElements = getMobileElement().findElementsByXPath("/XCUIElementTypeStaticText");
			return listStaticTextElements.get(CALORIES).getAttribute("name");
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Return the quantity of items ordered.
	 * @return String value with the quantity ordered.
	 */
	public String getQuantityOrdered()
	{
		try {
			List<MobileElement> listStaticTextElements = getMobileElement().findElementsByXPath("/XCUIElementTypeButton");
			return listStaticTextElements.get(QUANTITY).getAttribute("name");
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	/**
	 * Return the price of the given product.
	 * @return String value with the price value.
	 */
	public String getPrice()
	{
		try {
			List<MobileElement> listStaticTextElements = getMobileElement().findElementsByXPath("/XCUIElementTypeStaticText");
			return listStaticTextElements.get(PRICE).getAttribute("name");
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
		MobileElement addToCartButton = null;
		try {
			//Click the Add To Cart Button
			addToCartButton = getMobileElement().findElementByAccessibilityId("Add to basket");
			addToCartButton.click();
			
			//selectSize();
		}
		catch (Exception e)
		{
			addToCartButton.click();
		}
		return true;
	}
	
//	/**
//	 * Select Small or Medium for the given product. (For now, let's just select small)
//	 * This assumes that the add to cart button has already been 
//	 * clicked.  It should only be called by addToCart().
//	 * @return Return true if we were able to select the size indicated.
//	 */
//	private boolean selectSize()
//	{
//		try {
//			WebElement optionsShown = getWebElement().findElement(By.className("product-card-options"));
//			String optionsShownClass = optionsShown.getAttribute("class");
//			if (optionsShownClass.contains("showing--true"))
//			{
//				List<WebElement> sizeList = optionsShown.findElements(By.className("component-add-to-basket-button"));
//				if (sizeList.size() > 0)
//				{
//					sizeList.get(0).click();
//					WebElement optionsClose = optionsShown.findElement(By.className("card-options-close-icon"));
//					optionsClose.click();
//					return true; // We were able to click the option we wanted, and close.
//				}
//				else
//				{
//					return false; // No size options were available but should have been.
//				}
//			}
//			else
//			{
//				return false; // This product does not have size variants.
//			}
//		}
//		catch (Exception e)
//		{
//			return false;
//		}
//	}
//	
	/**
	 * Click on the product to load the product detail page.
	 * Returns true if the product element was found, and click was successful.
	 */
	public boolean loadProductDetail()
	{
		try {
			List<MobileElement> listImageElements = getMobileElement().findElementsByXPath("/XCUIElementTypeImage");
			listImageElements.get(IMAGE).click();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Returns true if item is out of stock.
	 */
	public boolean isOutOfStock()
	{
		try
		{
			MobileElement preOrderElement = getMobileElement().findElementByAccessibilityId("sold out today");
			return preOrderElement.isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}

}

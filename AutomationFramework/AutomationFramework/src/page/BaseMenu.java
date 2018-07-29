package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import common.MenuType;
import element.BaseWebElement;
import element.ProductCardWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class BaseMenu extends BasePage {
	
	private BaseWebElement categorySelector = new BaseWebElement(FindMethod.CLASSNAME,"categorySelector");
	private BaseWebElement deliverySelector = new BaseWebElement(FindMethod.CLASSNAME, "order-type--delivery");
	private BaseWebElement pickupSelector = new BaseWebElement(FindMethod.CLASSNAME, "order-type--pickup");
	private BaseWebElement enterNewAddress = new BaseWebElement(FindMethod.CLASSNAME, "delivery-address-list-button");
	private BaseWebElement addressTypeResidential = new BaseWebElement(FindMethod.XPATH, "//input[@value='residential']");  //need id
	private BaseWebElement addressTypeBusiness = new BaseWebElement(FindMethod.XPATH, "//input[@value='business']");  //need id
	
	//enter new address
	private BaseWebElement street = new BaseWebElement(FindMethod.NAME, "street");
	private BaseWebElement aptSuite = new BaseWebElement(FindMethod.NAME, "aptSuiteNumber");
	private BaseWebElement city = new BaseWebElement(FindMethod.NAME, "city");
	private BaseWebElement state = new BaseWebElement(FindMethod.NAME, "state");
	private BaseWebElement zipcode = new BaseWebElement(FindMethod.NAME, "zipcode");
	private BaseWebElement submit = new BaseWebElement(FindMethod.XPATH, "//button[@type='submit']");  //need id
	private BaseWebElement businessName = new BaseWebElement(FindMethod.NAME, "businessName");

	
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
				WebElement linkElement = productList.get(0).findElement(By.tagName("a"));
				String hrefTag = linkElement.getAttribute("href");
				String hrefSplit[] = hrefTag.split("/");
				String xPath = String.format("//a[contains(@href,'%s')]", hrefSplit[hrefSplit.length-1]);
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
	
	/**
	 * Select Delivery from the main menu page.
	 * @return
	 * Return "Success" if user was able to access the link.
	 * Return error string otherwise.
	 */
	public String selectDelivery()
	{
		try
		{
			List<WebElement> listDeliveryElements = WWWDriver.instance.findElements(By.className("order-type--delivery"));
			for (WebElement deliveryElement : listDeliveryElements)
			{
				if (deliveryElement.isDisplayed())
				{
					deliveryElement.click();
					break;
				}
			}
		}
		catch (NoSuchElementException e)
		{
			return "Delivery Selector is not displayed.";
		}
		return "Success";
	}
	
	/**
	 * Is delivery currently selected for order fulfillment?
	 * @return
	 * True if delivery is selected, and false otherwise.
	 */
	public boolean isDeliverySelected()
	{
		//the class name contains this specific string if it has been selected by the user.
		return deliverySelector.getWebElement().getAttribute("class").contains("order-mode--preferred");
	}
	
	/**
	 * Is pickup currently selected for order fulfillment?
	 * @return
	 * True if pickup is selected, and false otherwise.
	 */
	public boolean isPickupSelected()
	{
		//the class name contains this specific string if it has been selected by the user.
		return pickupSelector.getWebElement().getAttribute("class").contains("order-mode--preferred");
	}
	
	/**
	 * Select Pickup from the main menu page.
	 * @return
	 * Return "Success" if user was able to access the link.
	 * Return error string otherwise.
	 */
	public String selectPickup()
	{
		try
		{
			WebElement pickupEditIcon = deliverySelector.getWebElement().findElement(By.className("edit-icon"));
			pickupEditIcon.click();
		}
		catch (NoSuchElementException e)
		{
			return "Pickup Selector is not displayed.";
		}
		return "Success";
	}
	
	/**
	 * Change the current delivery address.
	 * @param newAddress Enter a string value with the new address.
	 * @return
	 * Return success if the user was able to complete the process.
	 * Return error string if not.
	 */
	public String changeDeliveryAddress(String newAddress)
	{
		//If delivery is not selected, then select it.
		if (!isDeliverySelected())
		{
			selectDelivery();
		}
		
		//Click on it again to change the address
		selectDelivery();
		
		//Confirm popup when it asks if you want to change the address
		WebPopupConfirmation webPopupConfirmation = new WebPopupConfirmation(FindMethod.CLASSNAME, "component-alert");
		
		//If the address exists in the list, select it.
		//If not enter a new one.
		if (!deliveryAddressExists(newAddress, true))
		{
			enterNewDeliveryAddress(newAddress);
		}
		
		return "Success";
	}
	
	/**
	 * Return a list of webelements for the addresses listed on the Recent Addresses Page.
	 * @return
	 * Array List of WebElements.
	 */
	private List<WebElement> getListAddresses()
	{
		List<WebElement> listAddresses = new ArrayList<WebElement>();
		try
		{
			listAddresses = WWWDriver.instance.findElements(By.className("address-list-row"));
		}
		catch (Exception e)
		{
			//Just return the empty list.
		}
		return listAddresses;
	}
	
	/**
	 * Does the given address already exist in our list of recent addresses?
	 * Format Should be:  9330 United Dr, Austin, TX 78758
	 * @param address String value containing the address in the above format.
	 * @param selectIfFound Enter true if the value should also be selected.
	 * @return
	 * Return true if the address exists.
	 */
	public boolean deliveryAddressExists(String address, boolean selectIfFound)
	{
		boolean isFound = false;
		List<WebElement> listAddresses = getListAddresses();
		WebElement addressTextElement = null;
		for (WebElement currentAddress : listAddresses)
		{
			try 
			{
				addressTextElement = currentAddress.findElement(By.className("address"));
				if (addressTextElement.getText().equals(address))
				{
					isFound=true;
					if (selectIfFound)
					{
						WebElement addressRadioElement = currentAddress.findElement(By.className("address-list-icon"));
						addressRadioElement.click();
					}
					break;
				}
			}
			catch (NoSuchElementException e)
			{
				//Just return false.
			}
			catch (NullPointerException e)
			{
				//Same.
			}
		}
		return isFound;
	}
	
	/**
	 * From the Recent Addresses Screen, enter a new delivery address
	 * Format Should be:  9330 United Dr, Austin, TX 78758
	 * @param address String value containing the new address
	 * @return
	 * Return "Success" if the user is able to complete the process.
	 * Return error string if not.
	 */
	public String enterNewDeliveryAddress(String address)
	{
		try
		{
			enterNewAddress.click();
		
			//Select residential or business
			addressTypeResidential.click();
			
			//0 street
			//1 city
			//2 state and zip
			String[] addressArray = address.split(",");
			street.setWebValue(addressArray[0]);
			city.setWebValue(addressArray[1]);
			state.selectValue("Texas");  //for now
			zipcode.setWebValue("78619"); //for now
			
			submit.click();
		}
		catch (NoSuchElementException e)
		{
			//dunno
		}
		return "Success";
	}
	
	/**
	 * Blah
	 * @return Blah
	 */
	public String checkProductImages()
	{
		WWWDriver.pause(3000); //TODO : figure out how to get rid of this.
		List<WebElement> productList = getProductList();
		try
		{
			for (WebElement product : productList)
			{
				String xPath = String.format("//div[@data-reactid='%s']", product.getAttribute("data-reactid"));
				ProductCardWebElement productCard = new ProductCardWebElement(FindMethod.XPATH, xPath);
				if (productCard.isPictureVisible())
				{
					System.out.println("Image exists for: " + productCard.getProductName());
				}
				else
				{
					System.out.println("Image is missing for " + productCard.getProductName());
				}
			}
		}
		catch (Exception e)
		{
			return "Error";
		}
		return "Success";
	}
	

}

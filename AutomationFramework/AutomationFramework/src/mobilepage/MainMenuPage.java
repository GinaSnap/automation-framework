package mobilepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import common.MenuType;
import element.BaseMobileElement;
import element.MobileProductCardWebElement;
import framework.FindMethod;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class MainMenuPage extends BasePage {
	
	public enum MenuFormat {
		CATEGORY,
		LIST
	}
	
	public final BaseMobileElement breakfastMenu = new BaseMobileElement("breakfast");
	public final BaseMobileElement lunchDinnerMenu = new BaseMobileElement("lunch & dinner");
	public final BaseMobileElement saladSoupMenu = new BaseMobileElement("salads & soups");
	public final BaseMobileElement componentsMenu = new BaseMobileElement("components");
	public final BaseMobileElement smallBitesMenu = new BaseMobileElement("small bites");
	public final BaseMobileElement snacksMenu = new BaseMobileElement("snacks");
	public final BaseMobileElement drinksMenu = new BaseMobileElement("drinks");
	public final BaseMobileElement sweetsMenu = new BaseMobileElement("sweets");
	public final BaseMobileElement pantryMenu = new BaseMobileElement("pantry");
	public final BaseMobileElement actionsBtn = new BaseMobileElement("Actions");
	public final BaseMobileElement actionsSearchMenu = new BaseMobileElement("search menu");  //name
	public final BaseMobileElement actionsNewMenuItems = new BaseMobileElement("new menu items");  //name
	public final BaseMobileElement actionsFavorites = new BaseMobileElement("favorites"); //name
	public final BaseMobileElement actionsClose = new BaseMobileElement("close");
	public final BaseMobileElement noThanks = new BaseMobileElement("NO THANKS");
	
	//Fulfillment Types
	private final BaseMobileElement pickupFulfillment = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeStaticText[contains(@name,'PICKUP FROM')]");
	private final BaseMobileElement deliveryFulfillment = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeStaticText[contains(@name,'DELIVERY TO')]");
	private final BaseMobileElement shippingFulfillment = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeStaticText[contains(@name,'SHIPPING TO')]");
	private final BaseMobileElement changeFulfillment = new BaseMobileElement("icn_dropdown_dark_6");
	
	//Navigation
	private final BaseMobileElement toggleMenu_Category = new BaseMobileElement("btn menu categories");
	private final BaseMobileElement toggleMenu_List = new BaseMobileElement("btn menu list");
	
	/**
	 * Access the Breakfast Main Menu.
	 */
	public String clickMenu(MenuType menuType)
	{
		goToMenu();
		String status = "Success";
		try
		{
			switch (menuType) {
			case BREAKFAST:
				breakfastMenu.click();
				break;
			case LUNCH_AND_DINNER:
				lunchDinnerMenu.click();
				break;
			case SALADS:
				saladSoupMenu.click();
				break;
			case SMALL_BITES:
				smallBitesMenu.click();
				break;
			case SIDES:
				componentsMenu.click();
				break;
			case SNACKS:
				snacksMenu.click();
				break;
			case DRINKS:
				drinksMenu.click();
				break;
			case SWEETS:
				sweetsMenu.click();
				break;
			case PANTRY:
				pantryMenu.click();
				break;
			default:
				break;
			}
		}
		catch (NoSuchElementException e)
		{
			status=String.format("Link for %s Menu did not exist.", menuType.name());
			return status;
		}
		return status;
	}
	
	/**
	 * Does the given menu exist on the main menu page?
	 * @param MenuType Breakfast, Lunch & Dinner, etc.
	 * @return Returns true if the menu exists on the screen.
	 */
	public boolean menuExists(MenuType menuType)
	{
		switch (menuType) {
		case BREAKFAST:
			return breakfastMenu.exists();
		case LUNCH_AND_DINNER:
			return lunchDinnerMenu.exists();
		case SALADS:
			return saladSoupMenu.exists();
		case SMALL_BITES:
			return smallBitesMenu.exists();
		case SIDES:
			return componentsMenu.exists();
		case SNACKS:
			return snacksMenu.exists();
		case DRINKS:
			return drinksMenu.exists();
		case SWEETS:
			return sweetsMenu.exists();
		case PANTRY:
			return pantryMenu.exists();
		default:
			return false;
		}
	}
	
	public void clickActionsMenu()
	{
		actionsBtn.click();
	}
	
	public void loadSearchPage()
	{
		clickActionsMenu();
		actionsSearchMenu.click();
	}
	
	public void loadFavorites()
	{
		clickActionsMenu();
		actionsFavorites.click();
	}
	
	public void loadNewMenuItems()
	{
		clickActionsMenu();
		actionsNewMenuItems.click();
	}
	
	public List<MobileElement> getMenuItems()
	{
		return MobileDriver.instance.findElementsByXPath("//XCUIElementTypeCollectionView/XCUIElementTypeCell");
	}
	
	/**
	 * For a given menu (breakfast, lunch&dinner...) return a MobileElement pointing to the first menu item.
	 * @return MobileProductCardWebElement pointing to the first menu item.  Return null if element is not found.
	 */
	private MobileProductCardWebElement getMenuItem(int index)
	{
		String xPath = String.format("//XCUIElementTypeCollectionView/XCUIElementTypeCell[%d]", index);
		try
		{
			MobileProductCardWebElement menuElement = new MobileProductCardWebElement(FindMethod.XPATH, xPath);
			return menuElement;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public String addFirstAvailableItemToCart()
	{
		boolean found=false;
		int index = 1;
		
		MobileProductCardWebElement menuElement = null;
		try
		{
			menuElement = getMenuItem(index);
			while ((!found) && (menuElement != null))
			{
				if (!menuElement.isOutOfStock())
				{
					found=true;
				}
				else
				{
					index++;
					menuElement = getMenuItem(index);
				}
			}
		}
		catch (Exception e)
		{
			return "Could not locate a menu item that was in stock.";
		}
		
		try
		{
			menuElement.addToCart();
		}
		catch (NoSuchElementException e)
		{
			return "Could not locate Add To Cart button.";
		}
		
		try
		{
			noThanks.click();
		}
		catch (NoSuchElementException e)
		{
			//Do nothing.  It is only there under certain circumstances.
		}
		return "Success";
	}
	
	/**
	 * For a given menu (breakfast, lunch&dinner...) return the name of the first menu item.
	 * @return String containing the product name.
	 */
	public String getFirstMenuItemProductName()
	{
		MobileProductCardWebElement firstMenuElement = null;
		try
		{
			firstMenuElement = getMenuItem(0);
		}
		catch (Exception e)
		{
			return "ERROR:  Could not locate first menu item.";
		}
		
		try
		{
			return firstMenuElement.getProductName();
		}
		catch (Exception e)
		{
			return "ERROR:  Could not locate product name of first menu element.";
		}
	}
	
	public boolean isPickup(String defaultStore)
	{
		String xPath = String.format("//XCUIElementTypeStaticText[contains(@name,'PICKUP FROM %s')]",defaultStore);
		BaseMobileElement pickupFulfillment = new BaseMobileElement(FindMethod.XPATH, xPath);
		return pickupFulfillment.exists();
	}
	
	public boolean isDelivery()
	{
		return deliveryFulfillment.exists();
	}
	
	public boolean isShipping()
	{
		return shippingFulfillment.exists();
	}
	
	public String switchToMenu(MenuFormat format) {
		
		switch (format) {
		case CATEGORY:
			if (!isCategoryMenu()) {
				switchToCategoryMenu();
			}
			break;
			
		case LIST:
			if (!isListMenu()) {
				switchToListMenu();
			}
			break;

		default:
			break;
		}
		return "Success";
	}
	
	public boolean isCategoryMenu() {
		return toggleMenu_List.exists();
	}
	
	public boolean isListMenu() {
		return toggleMenu_Category.exists();
	}
	
	private void switchToCategoryMenu() {
		toggleMenu_Category.click();
	}
	
	private void switchToListMenu() {
		toggleMenu_List.click();
	}
}

package mobilepage;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import common.MenuType;
import element.BaseMobileElement;
import element.MobileProductCardWebElement;
import framework.FindMethod;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class MainMenuPage extends BasePage {
	
	public final BaseMobileElement breakfastMenu = new BaseMobileElement("BREAKFAST");
	public final BaseMobileElement lunchDinnerMenu = new BaseMobileElement("LUNCH & DINNER");
	public final BaseMobileElement saladMenu = new BaseMobileElement("SALADS");
	public final BaseMobileElement soupMenu = new BaseMobileElement("SOUPS");
	public final BaseMobileElement smallBitesMenu = new BaseMobileElement("SMALL BITES");
	public final BaseMobileElement sidesMenu = new BaseMobileElement("SIDES");
	public final BaseMobileElement snacksMenu = new BaseMobileElement("SNACKS");
	public final BaseMobileElement juicesBlendsMenu = new BaseMobileElement("JUICES & BLENDS");
	public final BaseMobileElement drinksMenu = new BaseMobileElement("DRINKS");
	public final BaseMobileElement sweetsMenu = new BaseMobileElement("SWEETS");
	public final BaseMobileElement actionsBtn = new BaseMobileElement("Actions");
	public final BaseMobileElement actionsSearchMenu = new BaseMobileElement("search menu");  //name
	public final BaseMobileElement actionsNewMenuItems = new BaseMobileElement("new menu items");  //name
	public final BaseMobileElement actionsFavorites = new BaseMobileElement("favorites"); //name
	public final BaseMobileElement actionsClose = new BaseMobileElement("close");
	
	/**
	 * Access the Breakfast Main Menu.
	 */
	public String clickMenu(MenuType menuType)
	{
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
				saladMenu.click();
				break;
			case SOUPS:
				soupMenu.click();
				break;
			case SMALL_BITES:
				smallBitesMenu.click();
				break;
			case SIDES:
				sidesMenu.click();
				break;
			case SNACKS:
				snacksMenu.click();
				break;
			case JUICES:
				juicesBlendsMenu.click();
				break;
			case DRINKS:
				drinksMenu.click();
				break;
			case SWEETS:
				sweetsMenu.click();
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
			return saladMenu.exists();
		case SOUPS:
			return soupMenu.exists();
		case SMALL_BITES:
			return smallBitesMenu.exists();
		case SIDES:
			return sidesMenu.exists();
		case SNACKS:
			return snacksMenu.exists();
		case JUICES:
			return juicesBlendsMenu.exists();
		case DRINKS:
			return drinksMenu.exists();
		case SWEETS:
			return sweetsMenu.exists();
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
	private MobileProductCardWebElement getFirstMenuItem()
	{
		try
		{
			MobileProductCardWebElement menuElement = new MobileProductCardWebElement(FindMethod.XPATH, "//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]");
			return menuElement;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public String addFirstItemToCart()
	{
		String status="Success";
		MobileProductCardWebElement firstMenuElement = null;
		try
		{
			firstMenuElement = getFirstMenuItem();
		}
		catch (Exception e)
		{
			status="Could not locate first menu item.";
			return status;
		}
		try
		{
			firstMenuElement.addToCart();
		}
		catch (Exception e)
		{
			status="Could not locate Add To Cart button.";
			return status;
		}
		return status;
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
			firstMenuElement = getFirstMenuItem();
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
			

}

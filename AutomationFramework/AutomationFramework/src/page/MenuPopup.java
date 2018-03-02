package page;

import org.openqa.selenium.NoSuchElementException;

import common.MenuType;
import element.BaseWebElement;
import framework.FindMethod;

public class MenuPopup extends BasePage {
	
	public BaseWebElement breakfast = new BaseWebElement(FindMethod.CLASSNAME,"category-image-breakfast");
	public BaseWebElement lunchAndDinner = new BaseWebElement(FindMethod.CLASSNAME, "category-image-lunch_dinner");
	public BaseWebElement salads = new BaseWebElement(FindMethod.CLASSNAME, "category-image-salads");
	public BaseWebElement soups = new BaseWebElement(FindMethod.CLASSNAME, "category-image-soups");
	public BaseWebElement smallBites = new BaseWebElement(FindMethod.CLASSNAME, "category-image-small_bites");
	public BaseWebElement sides = new BaseWebElement(FindMethod.CLASSNAME, "category-image-sides");
	public BaseWebElement snacks = new BaseWebElement(FindMethod.CLASSNAME, "category-image-snacks");
	public BaseWebElement juices = new BaseWebElement(FindMethod.CLASSNAME, "category-image-juices_blends");
	public BaseWebElement drinks = new BaseWebElement(FindMethod.CLASSNAME, "category-image-drinks");
	public BaseWebElement sweets = new BaseWebElement(FindMethod.CLASSNAME, "category-image-sweets");
	public BaseWebElement newMenuItems = new BaseWebElement(FindMethod.CLASSNAME, "new-item-button");
	
	/**
	 * Go To Breakfast Menu
	 * @return Returns true if menu element is found and click is successful.
	 */
	public boolean goToMenu(MenuType menuType)
	{
		try {
			switch (menuType)
			{
				case BREAKFAST:
					breakfast.click();
					break;
				case LUNCH_AND_DINNER:
					breakfast.click();
					break;
				case SALADS:
					salads.click();
					break;
				case SOUPS:
					soups.click();
					break;
				case SMALL_BITES:
					smallBites.click();
					break;
				case SNACKS:
					snacks.click();
					break;
				case JUICES:
					juices.click();
					break;
				case DRINKS:
					drinks.click();
					break;
				case SWEETS:
					sweets.click();
					break;
			}
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		return true;
	}
	
}

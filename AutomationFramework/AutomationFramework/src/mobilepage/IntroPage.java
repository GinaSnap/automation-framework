package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import element.BaseMobileElement;

public class IntroPage extends BasePage {
	
	private BaseMobileElement nextButton = new BaseMobileElement("Next");
	private BaseMobileElement shopMealPlans = new BaseMobileElement("MealPlans");
	private BaseMobileElement shopMenu = new BaseMobileElement("Menu");
	private BaseMobileElement nextMealPlanIntro = new BaseMobileElement("next");
	private BaseMobileElement getStarted = new BaseMobileElement("GET STARTED");
	private BaseMobileElement OK = new BaseMobileElement("OK");

	
	/**
	 * Click the Next Button on any of the intro pages.
	 * @return
	 */
	public String clickNext()
	{
		String status="Success";
		try
		{
			nextButton.waitUntilClickable();
			nextButton.click();
		}
		catch (Exception e)
		{
			status="Could not find Next Button";
		}
		return status;
	}
	
	/**
	 * Does the Shop Menu button exist on the final page of the intro?
	 * @return
	 */
	public boolean menuButtonExists()
	{
		try
		{
			return shopMenu.exists();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Does the Shop Meal Plans Button exist on the final page of the intro?
	 * @return
	 */
	public boolean mealPlanButtonExists()
	{
		try
		{
			return shopMealPlans.exists();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public String shopMealPlans()
	{
		try
		{
			shopMealPlans.click();
		}
		catch (NoSuchElementException e)
		{
			return "Shop Meal Plans Button does not exist.";
		}
		return "Success";
	}
	
	public String shopMenu()
	{
		try
		{
			shopMenu.click();
		}
		catch (NoSuchElementException e)
		{
			return "Shop Menu Button does not exist.";
		}
		return "Success";
	}
	
	/**
	 * Click Next Arrow on the Meal Plan Intro Pages.
	 * Use this until accessibility ids are put on the Meal Plan Button.
	 * @return
	 */
	public String clickNextMealPlanIntro()
	{
		try
		{
			nextMealPlanIntro.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Next Arrow."; 
		}
		return "Success";
	}
	
	/**
	 * Does Get Started Button Exist on the Last Page of Meal Plan Intro?
	 * @return
	 */
	public boolean getStartedButtonExists()
	{
		try
		{
			return getStarted.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	/**
	 * Does OK Button Exist on the Last Page of Meal Plan Intro?
	 * @return
	 */
	public boolean okButtonExists()
	{
		try
		{
			return OK.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	/**
	 * Click either the Get Started button or the OK button 
	 * that is displayed at the end of the meal plan intro
	 * @return
	 */
	public boolean getStarted()
	{
		boolean found=false;
		
		if (getStartedButtonExists())
		{
			getStarted.click();
			found=true;
		}
		if (okButtonExists())
		{
			OK.click();
			found=true;
		}
		return found;
	}

}

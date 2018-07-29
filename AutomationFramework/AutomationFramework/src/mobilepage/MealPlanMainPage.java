package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import common.MealPlanOptions.PlanType;
import element.BaseMobileElement;

public class MealPlanMainPage extends BasePage {
	
	private final BaseMobileElement highProteinCard = new BaseMobileElement("HIGH");
	private final BaseMobileElement lowCarbCard = new BaseMobileElement("LOW");
	private final BaseMobileElement balanceCard = new BaseMobileElement("BALANCE");
	private final BaseMobileElement paleoCard = new BaseMobileElement("PALEO");
	private final BaseMobileElement whole30Card = new BaseMobileElement("WHOLE30");
	private final BaseMobileElement campGladCard = new BaseMobileElement("CAMP ");
	private final BaseMobileElement letsGetStarted = new BaseMobileElement("GetStarted");
	private final BaseMobileElement close = new BaseMobileElement("Close");
	
	/**
	 * Helper Method to select the meal plan based upon the user's input.
	 * @param PlanType Enter the plan type desired.
	 * @return "Success" or a description of the error encountered.
	 */
	public String selectPlanType(PlanType planType)
	{
		try
		{
			switch (planType) {
			case HIGH_PROTEIN:
				highProteinCard.click();
				break;
				
			case LOW_CARB:
				lowCarbCard.click();
				lowCarbCard.click();
				break;
				
			case BALANCE:
				lowCarbCard.click();
				balanceCard.click();
				balanceCard.click();
				break;
				
			case WHOLE30:
				lowCarbCard.click();
				balanceCard.click();
				whole30Card.click();
				whole30Card.click();
				break;
				
			case PALEO:
				lowCarbCard.click();
				balanceCard.click();
				whole30Card.click();
				paleoCard.click();
				paleoCard.click();
				break;
				
			case CAMP_GLADIATOR:
				lowCarbCard.click();
				balanceCard.click();
				whole30Card.click();
				paleoCard.click();
				campGladCard.click();
				campGladCard.click();
				break;
				
			default:
				return "User did not specify an appropriate Meal Plan Type.";
			}
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the meal plan card specified.";
		}
		return "Success";
	}
	
	public boolean mealPlanCardExists(PlanType planType)
	{
		switch (planType) {
		case HIGH_PROTEIN:
			return highProteinCard.exists();
			
		case LOW_CARB:
			return lowCarbCard.exists();
			
		case BALANCE:
			return balanceCard.exists();
			
		case PALEO:
			return paleoCard.exists();
			
		case WHOLE30:
			return whole30Card.exists();
			
		case CAMP_GLADIATOR:
			return campGladCard.exists();

		default:
			return false;
		}
	}
	
	

	
	public String selectHighProtein()
	{
		try
		{
			highProteinCard.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the High Protein Card.";
		}
		return "Success";
	}
	
	public String selectLowCarb()
	{
		try
		{
			lowCarbCard.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Low Carb Card.";
		}
		return "Success";
	}
	
	public String selectBalance()
	{
		try
		{
			balanceCard.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Balance Card.";
		}
		return "Success";
	}
	
	public String selectWhole30()
	{
		try
		{
			whole30Card.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Whole30 Card.";
		}
		return "Success";
	}
	
	public String selectPaleo()
	{
		try
		{
			paleoCard.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Paleo Card.";
		}
		return "Success";
	}
	
	public String selectCampGladiator()
	{
		try
		{
			campGladCard.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Camp Gladiator Card.";
		}
		return "Success";
	}
	
	public String letsGetStarted()
	{
		try
		{
			letsGetStarted.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Let's Get Started Button.";
		}
		return "Success";
	}
	
	public String cancel()
	{
		try
		{
			close.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Cancel Button.";
		}
		return "Success";
	}

}

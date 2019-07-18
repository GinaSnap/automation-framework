package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import common.MealPlanOptions.PlanType;
import element.BaseMobileElement;

public class MealPlanMainPage extends BasePage {
	
	private final BaseMobileElement highProteinCard = new BaseMobileElement("high protein");
	private final BaseMobileElement lowCarbCard = new BaseMobileElement("low carb");
	private final BaseMobileElement balanceCard = new BaseMobileElement("balance");
	private final BaseMobileElement paleoCard = new BaseMobileElement("paleo");
	private final BaseMobileElement whole30Card = new BaseMobileElement("whole30");
	private final BaseMobileElement buildYourOwn = new BaseMobileElement("build your own");
	private final BaseMobileElement ketoFriendly = new BaseMobileElement("keto-");
	private final BaseMobileElement vegan = new BaseMobileElement("vegan");
	private final BaseMobileElement letsGetStarted = new BaseMobileElement("GetStarted");
	private final BaseMobileElement quizGetStarted = new BaseMobileElement("GET STARTED");
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
				break;
				
			case BALANCE:
				balanceCard.click();
				break;
				
			case WHOLE30:
				whole30Card.click();
				break;
				
			case PALEO:
				paleoCard.click();
				break;
				
			case BUILD_YOUR_OWN:
				buildYourOwn.click();
				
			case VEGAN:
				vegan.click();
				break;
				
			case KETO:
				ketoFriendly.click();
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
	
	public String selectKeto()
	{
		try
		{
			ketoFriendly.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find the Keto Friendly Card.";
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
	
	public String startQuiz() {
		try {
			quizGetStarted.click();
		}
		catch (NoSuchElementException e) {
			return "Could not find Quiz Get Started Button.";
		}
		return "Success";
	}

}

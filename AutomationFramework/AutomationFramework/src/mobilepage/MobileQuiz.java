package mobilepage;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import common.Gender;
import common.PlanType;
import element.BaseMobileElement;
import framework.FindMethod;
import framework.MobileDriver;
import io.appium.java_client.MobileElement;

public class MobileQuiz extends BasePage {
	
	private final BaseMobileElement firstNameField = new BaseMobileElement(FindMethod.XPATH, "//XCUIElementTypeTextField");  //It's the only text field on the page.
	private final BaseMobileElement letsDoThis = new BaseMobileElement("LET’S DO THIS!");
	private final BaseMobileElement continueButton = new BaseMobileElement("CONTINUE");
	private final BaseMobileElement backButton = new BaseMobileElement("Back");
	private final BaseMobileElement closeButton = new BaseMobileElement("Close");
	private final BaseMobileElement genderMale = new BaseMobileElement("male");
	private final BaseMobileElement genderFemale = new BaseMobileElement("female");
	private final BaseMobileElement genderNonBinary = new BaseMobileElement("non-binary");
	private final BaseMobileElement submitButton = new BaseMobileElement("SUBMIT");
	
	//Lifestyle Plans Recommended
	private final BaseMobileElement lowCarbPlan = new BaseMobileElement("low carb lifestyle");
	private final BaseMobileElement highProteinPlan = new BaseMobileElement("high protein lifestyle");
	private final BaseMobileElement ketoFriendlyPlan = new BaseMobileElement("keto-friendly lifestyle");
	private final BaseMobileElement whole30Plan = new BaseMobileElement("whole30 lifestyle");
	private final BaseMobileElement paleoPlan = new BaseMobileElement("paleo lifestyle");
	private final BaseMobileElement balancePlan = new BaseMobileElement("balance lifestyle");
	private final BaseMobileElement vegetarianPlan = new BaseMobileElement("vegetarian lifestyle");
	private final BaseMobileElement veganPlan = new BaseMobileElement("vegan lifestyle");

	public String startQuiz() {
		AccountHome accountHome = new AccountHome();
		return accountHome.goToLifestyleSuggestions();
	}
	/**
	 * function:  enterFirstNameAndContinue
	 * @param name The name of the user taking the quiz.
	 * @return "Success" as long as the text field and continue button were visible/accessible.
	 */
	public String enterFirstNameAndContinue(String name) {
					
		try {
			firstNameField.setWebValue(name);
			letsDoThis.click();
		}
		catch (NoSuchElementException e) {
			return "Could not find the first name text field.";
		}
		return "Success";

	}
	
	/**
	 * function:  greetingIsDisplayed
	 * @param name The name of the user that should be displayed.
	 * @return Returns true if the greeting is displayed.
	 */
	public boolean greetingIsDisplayed(String name) {
		try {
			MobileElement greetingElement = MobileDriver.instance.findElementByAccessibilityId(String.format("nice to meet you, %s!", name));
			return greetingElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * function:  questionIsDisplayed
	 * @param name The text of the question that should be displayed.
	 * @return Returns true if displayed.
	 */
	public boolean questionIsDisplayed(String question) {
		try {
			MobileDriver.pause(1000);
			MobileElement questionElement = MobileDriver.instance.findElementByXPath(String.format("//XCUIElementTypeStaticText[contains(@name,\"%s\")]",question));
			return questionElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * function:  selectAnswer
	 * @param name The text of the answer to be selected
	 * @return Returns true if the question was found and selected.
	 */
	public String selectAnswer(String answer) {
		if (clickAnswerElement(answer)) {
			return "Success";
		}
		else {
			scroll("d"); //One scroll should display the rest of the answers.
			if (clickAnswerElement(answer)) {
				return "Success";
			}
		}
		return "Could not find the answer in the list after scrolling.";
	}
	
	private boolean clickAnswerElement(String answer) {
		try {
			MobileElement answerElement = MobileDriver.instance.findElementByXPath(String.format("//XCUIElementTypeStaticText[contains(@name,\"%s\")]",answer));
			if (!answerElement.isDisplayed()) {
				scrollToElement(answerElement);
			}
			answerElement.click();
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public String selectAnswerTest(String answer) {
		try {
			BaseMobileElement answerElement = new BaseMobileElement(answer);
			//MobileElement answerElement = MobileDriver.instance.findElementByXPath(String.format("//XCUIElementTypeStaticText[contains(@name,\"%s\")]",answer));
//			if (!answerElement.isDisplayed()) {
//				scrollToElement(answerElement);
//			}
			answerElement.clickTest();
			return "Success";
		}
		catch (NoSuchElementException e) {
			return "Could not find answer in the list.";
		}
		catch (ElementNotVisibleException e) {
			MobileElement answerElement = MobileDriver.instance.findElementByXPath(String.format("//XCUIElementTypeStaticText[contains(@name,\"%s\")]",answer));
			if (!answerElement.isDisplayed()) {
				scrollToElement(answerElement);
			}
			answerElement.click();
			return "Success";
		}
	}
	
	private boolean displayElement(MobileElement element)
	{
		boolean scrollDown=false;
		boolean scrollLeft=true;
		boolean scrollRight=true;
		boolean scrollUp=false;
		while (!(element.isDisplayed()) && (!scrollDown || !scrollUp || !scrollLeft || !scrollRight))
		{
			if (!scrollDown)
			{
				scroll("d");
				scrollDown=true;
				continue;
			}
			if (!scrollUp)
			{
				scroll("u");
				scrollUp=true;
				continue;
			}
			if (!scrollLeft)
			{
				scroll("l");
				scrollLeft=true;
				continue;
			}
			if (!scrollRight)
			{
				scroll("r");
				scrollRight=true;
				continue;
			}
		}
		return element.isDisplayed();
	}
	
	/**
	 * Scroll in in the requested direction.
	 * @param direction Enter d (down), u (up), l (left), r (right).
	 */
	public void scroll(String direction)
	{
			try {
	            JavascriptExecutor js = (JavascriptExecutor) MobileDriver.instance;
	            HashMap<String, String> swipeObject = new HashMap<String, String>();
	            if (direction.equals("d")) {
	                swipeObject.put("direction", "down");
	            } else if (direction.equals("u")) {
	                swipeObject.put("direction", "up");
	            } else if (direction.equals("l")) {
	                swipeObject.put("direction", "left");
	            } else if (direction.equals("r")) {
	                swipeObject.put("direction", "right");
	            }
	            //swipeObject.put("element", ((RemoteWebElement) getMobileElement()).getId());
	            js.executeScript("mobile: scroll", swipeObject);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	private boolean scrollToElement(MobileElement element)
	{
		HashMap<String, String> swipeObject = new HashMap<String, String>();
		swipeObject.put("name", element.getAttribute("name"));
		MobileDriver.instance.executeScript("mobile: scroll", swipeObject);
		return true;
	}
	
	/**
	 * function:  goalsGreetingIsDisplayed
	 * @param name The name of the user that should be displayed.
	 * @return Returns true if the greeting is displayed.
	 */
	public boolean goalsGreetingIsDisplayed(String name) {
		try {
			String greetingXPath = String.format("//XCUIElementTypeStaticText[contains(@name,\"awesome %s! let's talk about your goals.\")]",name);
			MobileElement greetingElement = MobileDriver.instance.findElement(By.xpath(greetingXPath));
			return greetingElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * function:  continueToNextPage
	 * Shared continue button to move user to the next page of the quiz.
	 * @return "Success" if the Continue button is displayed and clickable.
	 */
	public String continueToNextPage() {
		try {
			continueButton.click();
			return "Success";
		}
		catch (NoSuchElementException e) {
			return "Could not find the continue button.";
		}
	}
	
	/**
	 * function:  backToPreviousPage
	 * Shared back button to move user to the previous page of the quiz.
	 * @return "Success" if the Back button is displayed and clickable.
	 */
	public String backToPrevioustPage() {
		try {
			backButton.click();
			return "Success";
		}
		catch (NoSuchElementException e) {
			return "Could not find the back button.";
		}
	}
	
	/**
	 * function:  exitQuiz
	 * Shared button to exit the quiz.
	 * @return "Success" if the Close button is displayed and clickable.
	 */
	public String exitQuiz() {
		try {
			closeButton.click();
			return "Success";
		}
		catch (NoSuchElementException e) {
			return "Could not find the close button.";
		}
	}
	
	/**
	 * function selectGender
	 * Select the gender on the "personalized recommendation" page of the quiz.
	 * @param Enter the gender that you wish to select
	 * @return "Success" if the gender was found and clickable.
	 */
	public String selectGender(Gender gender) {
		try {
			switch (gender) {
			case MALE:
				genderMale.click();
				break;
			case FEMALE:
				genderFemale.click();
				break;
			case NON_BINARY:
				genderNonBinary.click();
				break;
			default:
				break;
			}
		}
		catch (NoSuchElementException e) {
			return String.format("Could not find [%s] in the list.", gender);
		}
		return "Success";
	}
	
	/**
	 * function enterHeight
	 * Enter the user's height on the "personalized recommendation" page of the Quiz
	 * @param feet String value indicating 'feet' portion of height.
	 * @param inches String value indicating 'inches' portion of height.
	 * @return
	 */
	public String enterHeight(String feet, String inches) {
		String feetXPath = "//XCUIElementTypeStaticText[@name='feet']/preceding-sibling::XCUIElementTypeTextField";
		String inchesXPath = "//XCUIElementTypeStaticText[@name='feet']/following-sibling::XCUIElementTypeTextField";
		try {
			MobileElement feetElement = MobileDriver.instance.findElement(By.xpath(feetXPath));
			MobileElement inchesElement = MobileDriver.instance.findElement(By.xpath(inchesXPath));
			
			feetElement.sendKeys(feet);
			inchesElement.sendKeys(inches);
		}
		catch (NoSuchElementException e) {
			return "Could not find the feet and inches text fields.";
		}
		return "Success";

	}
	
	/**
	 * function enterWeight
	 * Enter the user's weight on the "personalized recommendation" page of the Quiz
	 * @param weight String value indicating weight in lbs
	 * @return
	 */
	public String enterWeight(String weight) {
		String weightXPath = "//XCUIElementTypeStaticText[@name='lbs']/preceding-sibling::XCUIElementTypeTextField";
		try {
			MobileElement weightElement = MobileDriver.instance.findElement(By.xpath(weightXPath));
			
			weightElement.sendKeys(weight);
		}
		catch (NoSuchElementException e) {
			return "Could not find the weight text field.";
		}
		return "Success";

	}
	
	/**
	 * function enterDateOfBirth
	 * Enter the user's date of birth on the "personalized recommendation" page of the Quiz
	 * @param date String value of the user's birthdate in format mm/yyyy.
	 * @return
	 */
	public String enterDateOfBirth(String dateOfBirth) {
		String dateOfBirthXPath = "//XCUIElementTypeStaticText[@name='date of birth']/following-sibling::XCUIElementTypeTextField";
		try {
			MobileElement dobElement = MobileDriver.instance.findElement(By.xpath(dateOfBirthXPath));
			
			dobElement.sendKeys(dateOfBirth);
			//I have to tab out of the field, or it doesn't recognize that a value was entered, and continue is not enabled.
		}
		catch (NoSuchElementException e) {
			return "Could not find the date of birth text field.";
		}
		return "Success";

	}
	
	/**
	 * function:  activityGreetingIsDisplayed
	 * @return Returns true if the greeting is displayed.
	 */
	public boolean activityGreetingIsDisplayed() {
		try {
			MobileElement greetingElement = MobileDriver.instance.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,\"great goals! let's dig into your activity and diet\")]"));
			return greetingElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * function:  typicalDayGreetingIsDisplayed
	 * @return Returns true if the greeting is displayed.
	 */
	public boolean typicalDayGreetingIsDisplayed() {
		try {
			String greetingXPath = "//XCUIElementTypeStaticText[contains(@name,'almost done. finally, tell us about your typical day')]";
			MobileElement greetingElement = MobileDriver.instance.findElement(By.xpath(greetingXPath));
			return greetingElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * function:  finalCommentsPageIsDisplayed
	 * @return Returns true if the greeting is displayed.
	 */
	public boolean finalCommentsPageIsDisplayed() {
		try {
			String greetingXPath = "//XCUIElementTypeStaticText[contains(@name,'is there anything else you would like to share?')]";
			MobileElement greetingElement = MobileDriver.instance.findElement(By.xpath(greetingXPath));
			return greetingElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String enterFinalComments(String text) {
		try {
			MobileElement finalCommentsTextBox = MobileDriver.instance.findElement(By.xpath("//XCUIElementTypeTextView"));
			finalCommentsTextBox.sendKeys(text);
		}
		catch (NoSuchElementException e) {
			return "Could not find Comment Text Box.";
		}
		return "Success";
	}
	
	//share your zipcode so that we can provide you a personalized recommendation with items available in your area.
	/**
	 * function:  zipCodePromptIsDisplayed
	 * @return Returns true if the greeting is displayed.
	 */
	public boolean zipCodePromptIsDisplayed() {
		try {
			MobileElement greetingElement = MobileDriver.instance.findElementByAccessibilityId("share your zipcode so that we can provide you a personalized recommendation with items available in your area.");
			return greetingElement.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String enterZipCode(String zipCode) {
		try {
			MobileElement finalCommentsTextBox = MobileDriver.instance.findElement(By.xpath("//XCUIElementTypeTextField"));
			finalCommentsTextBox.clear();
			finalCommentsTextBox.sendKeys(zipCode);
		}
		catch (NoSuchElementException e) {
			return "Could not find ZipCode Text Box.";
		}
		return "Success";
	}
	
	/**
	 * function:  submit
	 * Click submit to complete the quiz.
	 * @return "Success" if the submit button is displayed and clickable.
	 */
	public String submitQuiz() {
		try {
			submitButton.click();
			return "Success";
		}
		catch (NoSuchElementException e) {
			return "Could not find the submit button.";
		}
	}
	
	/**
	 * function greetingExists
	 * @param name String value containing the customer's first name
	 * @return True if the greeting exists in the format expected.
	 */
	public boolean greetingExists(String name) {
		String greeting = String.format("way to go, %s. based on what you told us, we recommend:", name);
		BaseMobileElement greetingElement = new BaseMobileElement(greeting);
		
		return greetingElement.exists();
	}
	
	public boolean isRecommendedLifestyle(PlanType planType) {
		String mealPlanText = "";
		switch (planType) {
		case LOW_CARB:
			mealPlanText = "low carb";
			break;
			
		case HIGH_PROTEIN:
			mealPlanText = "high protein";
			break;
			
		case KETO_FRIENDLY:
			mealPlanText = "keto-friendly";
			break;
			
		case WHOLE30:
			mealPlanText = "whole30";
			break;
			
		case PALEO:
			mealPlanText = "paleo";
			break;
			
		case BALANCE:
			mealPlanText = "balance";
			break;
			
		case VEGETARIAN:
			mealPlanText = "vegetarian";
			break;
			
		case VEGAN:
			mealPlanText = "vegan";
			break;

		default:
			mealPlanText = "";
			break;
		}
		
		MobileElement ele = MobileDriver.instance.findElementByXPath(String.format("//XCUIElementTypeStaticText[contains(@name,'%s lifestyle')]",mealPlanText));
		return ele.isDisplayed();
		
	}
}

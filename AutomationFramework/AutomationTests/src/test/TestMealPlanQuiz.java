package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import common.Gender;
import common.PlanType;
import common.QuizUtilities;
import framework.MobileDriver;
import mobilepage.MobileQuiz;

public class TestMealPlanQuiz extends MobileTestCase {
	
	MobileQuiz mobileQuiz = new MobileQuiz();
	QuizUtilities quizUtilities = new QuizUtilities();
	
	private enum RelationshipFood {
	    BALANCED_DIET ("I have a balanced diet I'm here to learn how to do that better or stick with it"),
	    YO_YO_ER ("I am a yo-yo-er I am all or nothing - eating healthy or face first in pizza & ice cream"),
	    CANT_STICK_TO_A_PLAN ("can't stick to a plan I tend to fall off track after starting new habits"),
	    EMOTIONAL_EATER ("I'm an emotional eater I use food for comfort during times of stress"),
	    NONE ("none of these fit me");

	    private final String name;       

	    private RelationshipFood(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}

	private enum ActivityLevel {
	    SEDENTARY ("Sedentary little or no excercise"),
	    LIGHTLY_ACTIVE ("Lightly active exercise or sports 1-3 times/week"),
	    MODERATELY_ACTIVE ("Moderately active exercise or sports 3-5 times/week"),
	    VERY_ACTIVE ("Very active hard exercise or sports 6-7 times/week"),
	    EXTREMELY_ACTIVE ("Extremely active very hard exercise or sports 6-7 times/week or a physical job");

	    private final String name;       

	    private ActivityLevel(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}
	
	private enum Goals {
	    MAINTAIN_HEALTH ("maintain my health"),
	    WEIGHT_LOSS ("weight loss"),
	    ATHLETIC_PERFORMANCE ("improve my athletic performance"),
	    MANAGE_HEALTH_ISSUE ("manage a health issue"),
	    REALISTIC_PLAN ("find a realistic plan I can stick to"),
	    IMPROVE_DIGESTION ("improve digestion"),
	    MORE_ENERGY ("more energy"),
	    DECREASE_INFLAMMATION ("decrease inflammation");

	    private final String name;       

	    private Goals(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}
	
	private enum Roadblocks {
	    TOO_BUSY ("too busy"),
	    NOT_SURE_HOW ("not sure how to eat healthy"),
	    LIMITED_BUDGET ("limited budget"),
	    PORTION_CONTROL ("portion control"),
	    NOT_SEEING_RESULTS ("not seeing results"),
	    STRUGGLE_WITH_BALANCE ("struggle to find balance"),
	    FREQUENT_TRAVEL ("frequent travel"),
	    PLANNING_FAMILY ("planning around my family"),
	    DONT_PLAN_AHEAD ("I don't plan ahead I want structure, but I don't know where to start");

	    private final String name;       

	    private Roadblocks(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}
	
	private enum HealthIssues {
	    DIGESTIVE_ISSUES ("digestive issues"),
	    AUTOIMMUNE_COND ("autoimmune condition"),
	    HIGH_BLOOD_PRESSURE ("high blood pressure"),
	    THYROID_COND ("thyroid condition"),
	    DIABETES ("diabetes,pre-diabetes or blood sugar issues"),
	    HIGH_CHOLESTEROL ("high cholesterol"),
	    OTHER ("other");

	    private final String name;       

	    private HealthIssues(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
	}
	
	@Test
	/**
	 * This test ensures that we support different types and lengths of names.
	 */
	public void testQuizNiceToMeetYou()
	{	
		List<String> listNames = new ArrayList<String>();
		listNames.add("Gina");
		listNames.add("áÁéÉíÍóÓúüñ¿¡");
		listNames.add("Gi-Na");
		listNames.add("1234567890");
		step("testQuizNiceToMeetYou");
		
		step("Login to the app with an existing account.");
		String status=login(LocalCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Navigate to Quiz.");
		MobileQuiz mobileQuiz = new MobileQuiz();
		status = mobileQuiz.startQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		for (String name : listNames) {
			step("Enter First Name.");
			
			status = mobileQuiz.enterFirstNameAndContinue(name);
			if (status.equals("Success")) {
				passTest("Complete");
			}
			else {
				failTest(status);
			}
			
			step("Verify:  Greeting screen is shown.");
			if (mobileQuiz.greetingIsDisplayed(name)) {
				passTest("Complete");
			}
			else {
				failTestAndContinue("The greeting was either not displayed, or formatted differently.");
			}
					
			step("Go Back to the first screen.");
			status = mobileQuiz.backToPrevioustPage();
			if (status.equals("Success")) {
				passTest("Complete");
			}
			else {
				failTest("Back button was either not displayed or not enabled.");
			}
		}
		
	}
	
	//These tests verify that the user is shown the right calorie level based upon their biometric data.
	//BMR (kcal / day) = 10 * weight (kg) + 6.25 * height (cm) – 5 * age (y) + s (kcal / day),
	//where s is +5 for males and -161 for females.
	//Activity Factor
	//1.2 for Sedentary
	//1.375 Lightly Active
	//1.55 Moderately Active
	//1.725 Very Active
	//1.9 Extra Active
	
	/**
	 * This is very unlikely, but we will make sure we are processing each range correctly.
	 * 100 lb, 5 ft tall 65 year old woman.
	 * Sedentary
	 * 
	 * (10 * 100) + (6.25 * 60) - (5*65) + (-161)
	 * 1000 + 375 - 325 - 161 = 889
	 * 889 * 1.2 = 1066.8
	 */
	@Test 
	public void testRangeLessThan1099() {
		completeQuizCalorieGoals(Gender.FEMALE, "5", "0", "100", "01/1954", ActivityLevel.SEDENTARY);
		//1200 Lifestyle Plan
	}
	
	/**
	 * I'm using the same biometric data but increasing her activity level to Lightly Active
	 * and that should put us at the next range.
	 * 100 lb, 5 ft tall 65 year old woman.
	 * Lightly Active
	 * 
	 * (10 * 100) + (6.25 * 60) - (5*65) + (-161)
	 * 1000 + 375 - 225 - 161 = 889
	 * 889 * 1.375 = 1222.375
	 */
	@Test 
	public void testRange1100To1299() {
		completeQuizCalorieGoals(Gender.FEMALE, "5", "0", "100", "01/1954", ActivityLevel.LIGHTLY_ACTIVE);
		//Verify 1200 Lifestyle Plan
	}
	
	/**
	 * Change the above person to a man and verify that this change alone puts them at the next level
	 * 100 lb, 5 ft tall 65 year old man.
	 * Lightly Active
	 * 
	 * (10 * 100) + (6.25 * 60) - (5*65) + (+5)
	 * 1000 + 375 - 225 + 5 = 1155
	 * 1155 * 1.375 = 1588.125
	 */
	@Test 
	public void testVerifyGender_Male() {
		completeQuizCalorieGoals(Gender.MALE, "5", "0", "100", "01/1954", ActivityLevel.LIGHTLY_ACTIVE);
		//Verify 1500 Lifestyle Plan
	}
	
	/**
	 * Change the above person to non binary.  It should have the same processing as the male.
	 * 100 lb, 5 ft tall 65 year old non binary.
	 * Lightly Active
	 * 
	 * (10 * 100) + (6.25 * 60) - (5*65) + (+5)
	 * 1000 + 375 - 225 + 5 = 1155
	 * 1155 * 1.375 = 1588.125
	 */
	@Test 
	public void testVerifyGender_NonBinary() {
		completeQuizCalorieGoals(Gender.NON_BINARY, "5", "0", "100", "01/1954", ActivityLevel.LIGHTLY_ACTIVE);
		//Verify 1500 Lifestyle Plan
	}
	
	/**
	 * I'm using the same biometric data but increasing her activity level to Moderately Active.
	 * 100 lb, 5 ft tall 65 year old woman.
	 * Moderately Active
	 * 
	 * (10 * 100) + (6.25 * 60) - (5*65) + (-161)
	 * 1000 + 375 - 225 - 161 = 889
	 * 889 * 1.55 = 1377.95
	 */
	@Test 
	public void testRange1300To1599() {
		completeQuizCalorieGoals(Gender.FEMALE, "5", "0", "100", "01/1954", ActivityLevel.MODERATELY_ACTIVE);
	}
	
	/**
	 * Now, increase to Very Active.  Increase weight by 1 lb to put us to the next range level
	 * 100 lb, 5 ft tall 65 year old woman.
	 * Moderately Active
	 * 
	 * (10 * 101) + (6.25 * 60) - (5*65) + (-161)
	 * 1010 + 375 - 225 - 161 = 999
	 * 999 * 1.725 = 1723.275
	 */
	@Test 
	public void testRange1600To1899() {
		completeQuizCalorieGoals(Gender.FEMALE, "5", "0", "101", "01/1954", ActivityLevel.VERY_ACTIVE);
	}
	
	/**
	 * Now, increase to Extremely Active.
	 * 102 lb, 5 ft tall 65 year old woman.
	 * Extremely Active
	 * 
	 * (10 * 102) + (6.25 * 60) - (5*65) + (-161)
	 * 1020 + 375 - 225 - 161 = 1009
	 * 1009 * 1.9 = 1917.1
	 */
	@Test 
	public void testRangeGreaterThan1899() {
		completeQuizCalorieGoals(Gender.FEMALE, "5", "0", "102", "01/1954", ActivityLevel.EXTREMELY_ACTIVE);
	}
	
	/**
	 * Trying to see what would happen if the customer entered invalid data bec they
	 * didn't want to answer these questions.  Which will happen.
	 * 0 lb, 0 ft tall 119 year old woman.
	 * Sedentary
	 * Leads to a negative result.
	 * 
	 * (10 * 0) + (6.25 * 0) - (5*119) + (-161)
	 * 0 + 0 - 595 - 161 = -756
	 * -756 * 1.9 = -1436.40
	 */
	@Test 
	public void testInvalidData() {
		completeQuizCalorieGoals(Gender.FEMALE, "0", "0", "0", "01/1900", ActivityLevel.EXTREMELY_ACTIVE);
	}
	
	//These tests cover the max and min limits on Biometric Data
	/**
	 * Enter max value in the feet field
	 * 250 lb, 9 ft tall 24 year old woman.
	 * Moderately Active
	 * 
	 * (10 * 250) + (6.25 * 108) - (5*24) + (+5)
	 * 2500 + 675 - 120 + 5 = 3060
	 * 3060 * 1.55 = 4743
	 */
	@Test
	public void testMaxFeet() {
		completeQuizCalorieGoals(Gender.MALE, "9", "0", "250", "01/1995", ActivityLevel.MODERATELY_ACTIVE);
		//1800 Plan
	}
	
	/**
	 * Enter max value in the inches field
	 * 250 lb, 0 ft 99 inches tall 24 year old woman.
	 * Moderately Active
	 * 
	 * (10 * 250) + (6.25 * 99) - (5*24) + (+5)
	 * 2500 + 618.75 - 120 + 5 = 3003.75
	 * 3003.75 * 1.55 = 4655.8125
	 */
	@Test
	public void testMaxInches() {
		completeQuizCalorieGoals(Gender.MALE, "0", "99", "250", "01/1995", ActivityLevel.MODERATELY_ACTIVE);
		//1800 Plan
	}
	
	/**
	 * Enter max value in the weight field
	 * 999 lb, 5 ft 6 inches tall 24 year old man.
	 * Moderately Active
	 * 
	 * (10 * 999) + (6.25 * 66) - (5*24) + (+5)
	 * 9990 + 412.5 - 120 + 5 = 10287.5
	 * 10287.5 * 1.55 = 15945.625
	 */
	@Test
	public void testMaxWeight() {
		completeQuizCalorieGoals(Gender.MALE, "5", "6", "999", "01/1995", ActivityLevel.MODERATELY_ACTIVE);
		//1800 Calorie Goal
	}
	
	/**
	 * Enter the min value in the year field of the date of birth
	 * 250 lb, 5 ft 6 inches tall 24 year old man.
	 * Moderately Active
	 * 
	 * (10 * 250) + (6.25 * 66) - (5*1019) + (+5)
	 * 2500 + 412.5 - 5095 + 5 = -2177.5
	 * -2177.5 * 1.55 = -3375.125
	 */
	@Test
	public void testMinYear() {
		completeQuizCalorieGoals(Gender.MALE, "5", "6", "250", "01/1000", ActivityLevel.MODERATELY_ACTIVE);
		//1200 Meal Plan
	}
	
	/**
	 * Enter the max value in the year field of the date of birth
	 * 250 lb, 5 ft 6 inches tall - year old man.
	 * Moderately Active
	 * 
	 * (10 * 250) + (6.25 * 66) + (5*(-981)) + (+5)
	 * 2500 + 412.5 - 4905 + 5 = -1987.5
	 * -1987.5 * 1.55 = -3080.625
	 */
	@Test 
	public void testMaxYear() {
		completeQuizCalorieGoals(Gender.MALE, "5", "6", "250", "01/3000", ActivityLevel.MODERATELY_ACTIVE);
		//1200 Meal Plan
	}
	
	//These tests cover which blog entries should be visible based upon roadblock inputs
	/**
	 * testRoadblock_TooBusy
	 * Goal = maintain my health
	 * Relationship = I have a balanced Diet
	 * Roadblock = Too Busy
	 * Articles
	 * 1.  How Snap Kitchen Saves You Time
	 * The goal and relationship item that we chose doesn't lead to a blog article.  
	 * So, the other two should be general articles.
	 */
	@Test
	public void testRoadblock_TooBusy() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.TOO_BUSY);
	}
	
	/**
	 * testRoadblock_NotSure
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Not Sure how to eat healthy
	 * None of these articles are attached to a blog entry.
	 * Verify only that three are returned.
	 */
	@Test
	public void testRoadblock_NotSure() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.NOT_SURE_HOW);
	}
	
	/**
	 * testRoadblock_LimitedBudget
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Limited Budget
	 * None of these articles are attached to a blog entry.
	 * Verify only that three are returned.
	 */
	@Test
	public void testRoadblock_LimitedBudget() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.LIMITED_BUDGET);
	}
	
	/**
	 * testRoadblock_PortionControl
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Portion Control
	 * None of these articles are attached to a blog entry.
	 * Verify only that three are returned.
	 */
	@Test
	public void testRoadblock_PortionControl() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.PORTION_CONTROL);
	}
	
	/**
	 * testRoadblock_NotSeeingResults
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Not Seeing Results
	 * None of these articles are attached to a blog entry.
	 * Verify only that three are returned.
	 */
	@Test
	public void testRoadblock_NotSeeingResults() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.NOT_SEEING_RESULTS);
	}
	
	/**
	 * testRoadblock_StruggleBalance
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Struggle With Balance
	 * 1.  How to Stop Yo-Yo Dieting & Find a Balance....
	 * Other two should be general articles.
	 */
	@Test
	public void testRoadblock_StruggleBalance() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.STRUGGLE_WITH_BALANCE);
	}
	
	/**
	 * testRoadblock_FrequentTravel
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Frequent Travel
	 * None of these articles are attached to a blog entry.
	 * Verify only that three are returned.
	 */
	@Test
	public void testRoadblock_FrequentTravel() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.FREQUENT_TRAVEL);
	}
	
	/**
	 * testRoadblock_PlanningFamily
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = Planning Around My Family
	 * None of these articles are attached to a blog entry.
	 * Verify only that three are returned.
	 */
	@Test
	public void testRoadblock_PlanningFamily() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.PLANNING_FAMILY);
	}
	
	/**
	 * testRoadblock_DontPlanAhead
	 * Goal = maintain my health
	 * Relationship = I have a balanced diet
	 * Roadblock = I don't plan ahead
	 * 1.  Let Us Help You Plan
	 * 2.  General Articles for the final two.
	 */
	@Test
	public void testRoadblock_DontPlanAhead() {
		completeQuizRoadblocks("Gina", "78758", Roadblocks.DONT_PLAN_AHEAD);
	}
	
	//This set of tests cover blog articles that should be present based upon specific health issues.
	/**
	 * testManageHealthIssue_Digestive
	 * Goal = manage a health issue -> digestive issues
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  6 tips to improve digestion.
	 * 3.  General Article.
	 * Make sure that the first two exist, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_Disgestive() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.DIGESTIVE_ISSUES);
	}
	
	/**
	 * testManageHealthIssue_AutoImmune
	 * Goal = manage a health issue -> autoimmune condition
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_AutoImmune() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.AUTOIMMUNE_COND);
	}
	
	/**
	 * testManageHealthIssue_HighBloodPressure
	 * Goal = manage a health issue -> high blood pressure
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  The Real Deal.  Sodium.
	 * 3.  General Article.
	 * Make sure that the first two exist, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_HighBloodPressure() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.HIGH_BLOOD_PRESSURE);
	}
	
	/**
	 * testManageHealthIssue_Thyroid
	 * Goal = manage a health issue -> thyroid condition
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_Thyroid() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.THYROID_COND);
	}
	
	/**
	 * testManageHealthIssue_Diabetes
	 * Goal = manage a health issue -> diabetes
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_Diabetes() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.DIABETES);
	}
	
	/**
	 * testManageHealthIssue_HighCholesterol
	 * Goal = manage a health issue -> high cholesterol
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  How to Lower Your Cholesterol
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_HighCholesterol() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.HIGH_CHOLESTEROL);
	}
	
	/**
	 * testManageHealthIssue_Other
	 * Goal = manage a health issue -> other
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testManageHealthIssue_Other() {
		completeQuizManageHealthIssue("Gina", "78758", HealthIssues.OTHER);
	}
	
	//This set of tests cover the rest of the goals.
	/**
	 * testGoals_MaintainHealth
	 * Goal = Maintain Health
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testGoals_MaintainHealth() {
		completeQuizGoals("Gina", "78758", Goals.MAINTAIN_HEALTH);
	}
	
	/**
	 * testGoals_WeightLoss
	 * Goal = Weight Loss
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testGoals_WeightLoss() {
		completeQuizGoals("Gina", "78758", Goals.WEIGHT_LOSS);
	}
	
	/**
	 * testGoals_AthleticPerformance
	 * Goal = Improve Athletic Performance
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  General Article
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testGoals_AthleticPerformance() {
		completeQuizGoals("Gina", "78758", Goals.ATHLETIC_PERFORMANCE);
	}
	
	/**
	 * testGoals_ImproveDigestion
	 * Goal = Improve Digestion
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  6 tips to improve digestion
	 * 3.  General Article.
	 * Make sure that the first two exist, and that there are 3 total.
	 */
	@Test
	public void testGoals_ImproveDigestion() {
		completeQuizGoals("Gina", "78758", Goals.IMPROVE_DIGESTION);
	}
	
	/**
	 * testGoals_RealisticPlan
	 * Goal = Find a Realistic Plan I can Stick with
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  How to Stop Yo Yo Dieting
	 * 3.  General Article.
	 * Make sure that the first two exist, and that there are 3 total.
	 */
	@Test
	public void testGoals_RealisticPlan() {
		completeQuizGoals("Gina", "78758", Goals.REALISTIC_PLAN);
	}
	
	/**
	 * testGoals_MoreEnergy
	 * Goal = More Energy
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  None
	 * 3.  General Article.
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testGoals_MoreEnergy() {
		completeQuizGoals("Gina", "78758", Goals.MORE_ENERGY);
	}
	
	/**
	 * testGoals_DecreaseInflammation
	 * Goal = Decrease Inflammation
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  Inflammation 101
	 * 3.  General Article.
	 * Make sure that the first two exist, and that there are 3 total.
	 */
	@Test
	public void testGoals_DecreaseInflammation() {
		completeQuizGoals("Gina", "78758", Goals.DECREASE_INFLAMMATION);
	}
	
	/**
	 * testRelationship_I have a balanced diet
	 * Goal = Maintain my Health
	 * Relationship = I have a balanced diet
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  None
	 * 3.  From Relationship:  None
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testRelationship_BalancedDiet() {
		completeQuizRelationship("Gina", "78758", RelationshipFood.BALANCED_DIET);
	}
	
	/**
	 * testRelationship_I am a yo yo er
	 * Goal = Maintain my Health
	 * Relationship = I am a yo-yo-er
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  None
	 * 3.  From Relationship:  How to stop yo-yo dieting
	 * Make sure that the first two exist, and that there are 3 total.
	 */
	@Test
	public void testRelationship_YoYoEr() {
		completeQuizRelationship("Gina", "78758", RelationshipFood.YO_YO_ER);
	}
	
	//I am going to skip the rest of the relationship with food answers and just enter "none".
	//None of the other answers have articles attached.
	
	/**
	 * testRelationship_NoneFitMe
	 * Goal = Maintain my Health
	 * Relationship = None of these fit me
	 * Roadblock = too busy
	 * 1.  From Roadblock : How Snap Kitchen Saves you Time.
	 * 2.  From Goal:  None
	 * 3.  From Relationship:  None
	 * Make sure that the first exists, and that there are 3 total.
	 */
	@Test
	public void testRelationship_None() {
		completeQuizRelationship("Gina", "78758", RelationshipFood.NONE);
	}
	
	//Zip Code tests
	//We should show a local meal plan for an in proximity zip code, and otherwise shipping
	@Test
	public void testLocalZipCode() {
		completeQuizBasic("Gina", "78758");
	}
	
	@Test
	public void testShippingOnlyZipCode() {
		completeQuizBasic("Gina", "20024");
	}
	
	@Test
	public void testOutOfRangeZipCode() {
		completeQuizBasic("Gina", "62959");
	}

	private boolean isQuestionOptional(String question) {
		List<String> optionalQuestions = new ArrayList<String>();
		optionalQuestions.add("don't lose your progress or your final results");
		optionalQuestions.add("what health issues are you currently dealing with?");
		optionalQuestions.add("which lifestyle do you follow?");

		if (optionalQuestions.contains(question)) {
			return true;
		}
		return false;
	}
	
	private void continueStep() {
		MobileQuiz mobileQuiz = new MobileQuiz();
		String status;
		
		step("Continue to the next question.");
		status = mobileQuiz.continueToNextPage();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest("Continue button was not displayed or not enabled.");
		}
	}
	
	private void loginStep() {
		step("Login to the app with an existing account.");
		String status=login(LocalCustomer);
				
		if (status.equals("Success")) {
			passTest("Login complete.");
			}
		else {
			failTest(status);
		}
	}
	
	private void navigateStep() {
		step("Navigate to Quiz.");
		String status = mobileQuiz.startQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
	}
	
	private void enterNameStep(String name) {
		step("Enter the user's name and continue");		
		String status = mobileQuiz.enterFirstNameAndContinue(name);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
	}
	
	private void enterEmailStep(String questionText, String email) {
		step(String.format("Verify that question [%s] is displayed.", questionText));
		if (mobileQuiz.questionIsDisplayed(questionText)) {
			passTest("Complete");
		}
		else {
			if (isQuestionOptional(questionText)) {
				failTestAndContinue("Optional Question was not displayed.");
			}
			else {
				failTest("Expected question was not displayed");
			}
		}
	}
	
	private void processRankQuestion(String questionText, String expectedQuestion, String expectedAnswer) {
		String status="";
		step(String.format("Verify that question [%s] is displayed.", questionText));
		if (mobileQuiz.questionIsDisplayed(questionText)) {
			passTest("Complete");
		}
		else {
			failTest("Expected question was not displayed");
		}
		
		//If we want to answer a specific question, we wait until we are presented with that question
		if (questionText.equals(expectedQuestion)) {
			step(String.format("Select answer [%s].", expectedAnswer));
			status = mobileQuiz.selectAnswer(expectedAnswer);
		}
		else {
			//Otherwise, just pick the first item from the list.
			List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
			
			step(String.format("Select answer [%s].", answerList.get(0)));
			status = mobileQuiz.selectAnswer(answerList.get(0));
			if (status.equals("Success")) {
				passTest("Complete");
			}
			else {
				failTest(status);
			}
		}
		continueStep();
	}
	
	private void processMultiSelectQuestion(String questionText, String expectedQuestion, String expectedAnswer) {
		String status="";
		step(String.format("Verify that question [%s] is displayed.", questionText));
		if (mobileQuiz.questionIsDisplayed(questionText)) {
			passTest("Complete");
			
			//If this is the question we wanted, then we supply the answer we wanted
			if (questionText.equals(expectedQuestion)) {
				step(String.format("Select answer [%s].", expectedAnswer));
				status = mobileQuiz.selectAnswer(expectedAnswer);
			}
			else {
				//Otherwise, just pick the first item from the list.
				List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
				
				step(String.format("Select answer [%s].", answerList.get(0)));
				status = mobileQuiz.selectAnswer(answerList.get(0));
				if (status.equals("Success")) {
					passTest("Complete");
				}
				else {
					failTest(status);
				}
			}
			continueStep();
			}
			else {
				if (isQuestionOptional(questionText)) {
					failTestAndContinue("Optional Question was not displayed.");
				}
				else {
					failTest("Expected question was not displayed");
				}
			}
	}
	
	private void processSingleSelectQuestion(String questionText, String expectedQuestion, String expectedAnswer) {
		String status="";
		step(String.format("Verify that question [%s] is displayed.", questionText));
		if (mobileQuiz.questionIsDisplayed(questionText)) {
			passTest("Complete");
			
			List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
			
			step(String.format("Select answer [%s].", answerList.get(0)));
			status = mobileQuiz.selectAnswer(answerList.get(0));
			if (status.equals("Success")) {
				passTest("Complete");
			}
			else {
				failTest(status);
			}
			
			continueStep();
		}
		else {
			if (isQuestionOptional(questionText)) {
				failTestAndContinue("Optional Question was not displayed.");
			}
			else {
				failTest("Expected question was not displayed");
			}
		}
	}
	
	private void processBiometricQuestion(Gender gender, String feet, String inches, String weight, String dateOfBirth) {
		String status = "";
		step("Enter Gender, Height, Weight, and Birthdate");
		status = mobileQuiz.selectGender(gender);
		if (status.equals("Success")) {
			passTest("Gender Complete.");
		}
		else {
			failTest(status);
		}
		
		status = mobileQuiz.enterHeight(feet, inches);
		if (status.equals("Success")) {
			passTest("Height Complete.");
		}
		else {
			failTest(status);
		}
		
		status = mobileQuiz.enterWeight(weight);
		if (status.equals("Success")) {
			passTest("Weight Complete.");
		}
		else {
			failTest(status);
		}
		
		status = mobileQuiz.enterDateOfBirth(dateOfBirth);
		if (status.equals("Success")) {
			passTest("Date Of Birth Complete.");
		}
		else {
			failTest(status);
		}
		
		continueStep(); //The first click is to click out of the date of birth field so that it's value is recognized.
		continueStep(); //And the second is to continue.
	}
	
	private void processNotesQuestion(String notes) {
		String status = "";
		step("Verify that Final Comments Page is displayed.");
		if (mobileQuiz.finalCommentsPageIsDisplayed()) {
			passTest("Complete");
		}
		else {
			failTest("Final Comments Page is not displayed.");
		}
				
		step("Enter Final Comments into the text field provided.");
		status = mobileQuiz.enterFinalComments(notes);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		continueStep();
	}
	
	private void processZipcodeQuestion(String zipCode) {
		String status="";
		step("Verify that user is prompted for their zip code.");
		if (mobileQuiz.zipCodePromptIsDisplayed()) {
			passTest("Complete");
		}
		else {
			failTest("Zip Code Prompt is not displayed.");
		}
				
		step("Enter Zip Code into the text field provided.");
		status = mobileQuiz.enterZipCode(zipCode);
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
		
		step("Click Submit to Complete the Quiz");
		status = mobileQuiz.submitQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}
	}
	
	public void completeQuizBasic(String name, String zipCode) {
		QuizUtilities quizUtilities = new QuizUtilities();
		MobileQuiz mobileQuiz = new MobileQuiz();
		String status;
		
		//Access Quiz
		step("Login to the app with an existing account.");
		status=login(LocalCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Navigate to Quiz.");
		status = mobileQuiz.startQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					step("Enter the user's name and continue");		
					status = mobileQuiz.enterFirstNameAndContinue(name);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
					}
					else {
						failTest("Expected question was not displayed");
					}
					
					List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
					
					step(String.format("Select answer [%s].", answerList.get(0)));
					status = mobileQuiz.selectAnswer(answerList.get(0));
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						//Answer Question
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						
						step(String.format("Select answer [%s].", answerList.get(0)));
						status = mobileQuiz.selectAnswer(answerList.get(0));
						if (status.equals("Success")) {
							passTest("Complete");
						}
						else {
							failTest(status);
						}
						
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						//Answer Question
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						
						step(String.format("Select answer [%s].", answerList.get(0)));
						status = mobileQuiz.selectAnswer(answerList.get(0));
						if (status.equals("Success")) {
							passTest("Complete");
						}
						else {
							failTest(status);
						}
						
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
					
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					step("Enter Gender, Height, Weight, and Birthdate");
					status = mobileQuiz.selectGender(Gender.MALE);
					if (status.equals("Success")) {
						passTest("Gender Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterHeight("5", "5");
					if (status.equals("Success")) {
						passTest("Height Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterWeight("130");
					if (status.equals("Success")) {
						passTest("Weight Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterDateOfBirth("11/1990");
					if (status.equals("Success")) {
						passTest("Date Of Birth Complete.");
					}
					else {
						failTest(status);
					}
					
					continueStep(); //The first click is to click out of the date of birth field so that it's value is recognized.
					continueStep(); //And the second is to continue.
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					step("Verify that Final Comments Page is displayed.");
					if (mobileQuiz.finalCommentsPageIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Final Comments Page is not displayed.");
					}
							
					step("Enter Final Comments into the text field provided.");
					status = mobileQuiz.enterFinalComments("Test");
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					step("Verify that user is prompted for their zip code.");
					if (mobileQuiz.zipCodePromptIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Zip Code Prompt is not displayed.");
					}
							
					step("Enter Zip Code into the text field provided.");
					status = mobileQuiz.enterZipCode(zipCode);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					step("Click Submit to Complete the Quiz");
					status = mobileQuiz.submitQuiz();
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					
				}
				else {
					
				}
			}
		}
	}
	
	public void completeQuizRoadblocks(String name, String zipCode, Roadblocks roadblock) {
		QuizUtilities quizUtilities = new QuizUtilities();
		MobileQuiz mobileQuiz = new MobileQuiz();
		String status;
		
		//Access Quiz
		step("Login to the app with an existing account.");
		status=login(LocalCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Navigate to Quiz.");
		status = mobileQuiz.startQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					step("Enter the user's name and continue");		
					status = mobileQuiz.enterFirstNameAndContinue(name);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
					}
					else {
						failTest("Expected question was not displayed");
					}
					
					List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
					
					step(String.format("Select answer [%s].", answerList.get(0)));
					status = mobileQuiz.selectAnswer(answerList.get(0));
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						//Answer Question
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						
						step(String.format("Select answer [%s].", answerList.get(0)));
						status = mobileQuiz.selectAnswer(answerList.get(0));
						if (status.equals("Success")) {
							passTest("Complete");
						}
						else {
							failTest(status);
						}
						
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						//If this is the question we wanted, then we supply the roadblock that we wanted
						if (questionText.equals("what is your biggest roadblock when it comes to hitting your goals?")) {
							step(String.format("Select answer [%s].", roadblock.toString()));
							//status = mobileQuiz.selectAnswer(answerList.get(0));
							status = mobileQuiz.selectAnswer(roadblock.toString());
						}
						else {
							//Otherwise, just pick the first item from the list.
							
							step(String.format("Select answer [%s].", answerList.get(0)));
							status = mobileQuiz.selectAnswer(answerList.get(0));
							if (status.equals("Success")) {
								passTest("Complete");
							}
							else {
								failTest(status);
							}
						}
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
					
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					step("Enter Gender, Height, Weight, and Birthdate");
					status = mobileQuiz.selectGender(Gender.FEMALE);
					if (status.equals("Success")) {
						passTest("Gender Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterHeight("5", "5");
					if (status.equals("Success")) {
						passTest("Height Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterWeight("120");
					if (status.equals("Success")) {
						passTest("Weight Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterDateOfBirth("11/1990");
					if (status.equals("Success")) {
						passTest("Date Of Birth Complete.");
					}
					else {
						failTest(status);
					}
					
					continueStep(); //The first click is to click out of the date of birth field so that it's value is recognized.
					continueStep(); //And the second is to continue.
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					step("Verify that Final Comments Page is displayed.");
					if (mobileQuiz.finalCommentsPageIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Final Comments Page is not displayed.");
					}
							
					step("Enter Final Comments into the text field provided.");
					status = mobileQuiz.enterFinalComments("Test");
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					step("Verify that user is prompted for their zip code.");
					if (mobileQuiz.zipCodePromptIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Zip Code Prompt is not displayed.");
					}
							
					step("Enter Zip Code into the text field provided.");
					status = mobileQuiz.enterZipCode(zipCode);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					step("Click Submit to Complete the Quiz");
					status = mobileQuiz.submitQuiz();
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					
				}
				else {
					
				}
			}
		}
	}
	
	public void completeQuizBiometric(String name, String zipCode, Gender gender, String feet, String inches, String weight, String dateOfBirth) {
		QuizUtilities quizUtilities = new QuizUtilities();
		MobileQuiz mobileQuiz = new MobileQuiz();
		String status;
		
		//Access Quiz
		step("Login to the app with an existing account.");
		status=login(LocalCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Navigate to Quiz.");
		status = mobileQuiz.startQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					step("Enter the user's name and continue");		
					status = mobileQuiz.enterFirstNameAndContinue(name);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
					}
					else {
						failTest("Expected question was not displayed");
					}
					
					List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
					
					step(String.format("Select answer [%s].", answerList.get(0)));
					status = mobileQuiz.selectAnswer(answerList.get(0));
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						//Answer Question
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						
						step(String.format("Select answer [%s].", answerList.get(0)));
						status = mobileQuiz.selectAnswer(answerList.get(0));
						if (status.equals("Success")) {
							passTest("Complete");
						}
						else {
							failTest(status);
						}
						
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						//Answer Question
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						
						step(String.format("Select answer [%s].", answerList.get(0)));
						status = mobileQuiz.selectAnswer(answerList.get(0));
						if (status.equals("Success")) {
							passTest("Complete");
						}
						else {
							failTest(status);
						}
						
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
					
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					step("Enter Gender, Height, Weight, and Birthdate");
					status = mobileQuiz.selectGender(gender);
					if (status.equals("Success")) {
						passTest("Gender Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterHeight(feet, inches);
					if (status.equals("Success")) {
						passTest("Height Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterWeight(weight);
					if (status.equals("Success")) {
						passTest("Weight Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterDateOfBirth(dateOfBirth);
					if (status.equals("Success")) {
						passTest("Date Of Birth Complete.");
					}
					else {
						failTest(status);
					}
					
					continueStep(); //The first click is to click out of the date of birth field so that it's value is recognized.
					continueStep(); //And the second is to continue.
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					step("Verify that Final Comments Page is displayed.");
					if (mobileQuiz.finalCommentsPageIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Final Comments Page is not displayed.");
					}
							
					step("Enter Final Comments into the text field provided.");
					status = mobileQuiz.enterFinalComments("Test");
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					step("Verify that user is prompted for their zip code.");
					if (mobileQuiz.zipCodePromptIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Zip Code Prompt is not displayed.");
					}
							
					step("Enter Zip Code into the text field provided.");
					status = mobileQuiz.enterZipCode(zipCode);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					step("Click Submit to Complete the Quiz");
					status = mobileQuiz.submitQuiz();
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					
				}
				else {
					
				}
			}
		}
	}

	/**
	 * This function will verify that basic elements exist (which should be included on all quiz results)
	 * @param name
	 */
	private void verifyResultsBasic(String name, PlanType planType) {
		
		String status = "";
		step("Verify:  Greeting exists at the top of the screen.");
		if (mobileQuiz.greetingExists(name)) {
			passTest("Success");
		}
		else {
			failTestAndContinue("Greeting did not exist at the top of the page in the format expected.");
		}
		
		step(String.format("Verify:  Plan Type Recommended is [%s].",planType.toString()));
		if (mobileQuiz.isRecommendedLifestyle(PlanType.WHOLE30)) {
			passTest("Success");
		}
		else {
			failTestAndContinue("Could not find Whole30 Lifestyle Text.");
		}
	}
	
	public void completeQuizManageHealthIssue(String name, String zipCode, HealthIssues healthIssue) {
		QuizUtilities quizUtilities = new QuizUtilities();
		MobileQuiz mobileQuiz = new MobileQuiz();
		String status;
		
		//Access Quiz
		step("Login to the app with an existing account.");
		status=login(LocalCustomer);
		
		if (status.equals("Success"))
		{
			passTest("Login complete.");
		}
		else
		{
			failTest(status);
		}
		
		step("Navigate to Quiz.");
		status = mobileQuiz.startQuiz();
		if (status.equals("Success")) {
			passTest("Complete");
		}
		else {
			failTest(status);
		}

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					enterNameStep(name);
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					enterEmailStep(questionText, "");
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					processRankQuestion(questionText, "tell us about your goals, so that we can help you reach them!", "manage a health issue");
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					processMultiSelectQuestion(questionText, "what health issues are you currently dealing with?", healthIssue.toString());
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					step(String.format("Verify that question [%s] is displayed.", questionText));
					if (mobileQuiz.questionIsDisplayed(questionText)) {
						passTest("Complete");
						
						List<String> answerList = quizUtilities.getAnswersByQuestion(questionText);
						
						step(String.format("Select answer [%s].", answerList.get(0)));
						status = mobileQuiz.selectAnswer(answerList.get(0));
						if (status.equals("Success")) {
							passTest("Complete");
						}
						else {
							failTest(status);
						}
						
						continueStep();
					}
					else {
						if (isQuestionOptional(questionText)) {
							failTestAndContinue("Optional Question was not displayed.");
						}
						else {
							failTest("Expected question was not displayed");
						}
					}
					
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					step("Enter Gender, Height, Weight, and Birthdate");
					status = mobileQuiz.selectGender(Gender.FEMALE);
					if (status.equals("Success")) {
						passTest("Gender Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterHeight("5", "5");
					if (status.equals("Success")) {
						passTest("Height Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterWeight("120");
					if (status.equals("Success")) {
						passTest("Weight Complete.");
					}
					else {
						failTest(status);
					}
					
					status = mobileQuiz.enterDateOfBirth("11/1990");
					if (status.equals("Success")) {
						passTest("Date Of Birth Complete.");
					}
					else {
						failTest(status);
					}
					
					continueStep(); //The first click is to click out of the date of birth field so that it's value is recognized.
					continueStep(); //And the second is to continue.
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					step("Verify that Final Comments Page is displayed.");
					if (mobileQuiz.finalCommentsPageIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Final Comments Page is not displayed.");
					}
							
					step("Enter Final Comments into the text field provided.");
					status = mobileQuiz.enterFinalComments("Test");
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					continueStep();
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					step("Verify that user is prompted for their zip code.");
					if (mobileQuiz.zipCodePromptIsDisplayed()) {
						passTest("Complete");
					}
					else {
						failTest("Zip Code Prompt is not displayed.");
					}
							
					step("Enter Zip Code into the text field provided.");
					status = mobileQuiz.enterZipCode(zipCode);
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
					
					step("Click Submit to Complete the Quiz");
					status = mobileQuiz.submitQuiz();
					if (status.equals("Success")) {
						passTest("Complete");
					}
					else {
						failTest(status);
					}
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					
				}
				else {
					
				}
			}
		}
	}
	
	public void completeQuizGoals(String name, String zipCode, Goals goal) {
		
		loginStep();
		
		navigateStep();

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					enterNameStep(name);
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					enterEmailStep(questionText, "");  //This isn't being used right now.
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					processRankQuestion(questionText, "tell us about your goals, so that we can help you reach them!", goal.toString());
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					processMultiSelectQuestion(questionText, "", "");				
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					processSingleSelectQuestion(questionText, "", "");
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					processBiometricQuestion(Gender.FEMALE, "5", "5", "120", "09/1990");
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					processNotesQuestion("Test");
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					processZipcodeQuestion("78758");
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					
				}
				else {
					
				}
			}
		}
	}

	public void completeQuizRelationship(String name, String zipCode, RelationshipFood relationship) {
		
		loginStep();
		
		navigateStep();

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					enterNameStep(name);
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					enterEmailStep(questionText, "");  //This isn't being used right now.
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					processRankQuestion(questionText, "", "");
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					processMultiSelectQuestion(questionText, "", "");				
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					processSingleSelectQuestion(questionText, "what is your relationship with food", relationship.toString());
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					processBiometricQuestion(Gender.FEMALE, "5", "5", "120", "09/1990");
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					processNotesQuestion("Test");
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					processZipcodeQuestion("78758");
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					MobileDriver.pause(5000);
				}
				else {
					
				}
			}
		}
		verifyResultsBasic(name, PlanType.WHOLE30);
	}
	
	public void completeQuizCalorieGoals(Gender gender, String feet, String inches, String weight, String dateOfBirth, ActivityLevel activityLevel) {
		
		loginStep();
		
		navigateStep();

		List<String> quizSections = quizUtilities.getQuizSections();
		
		for (String section : quizSections) {
			step("Testing Section:  " + section);
			Map<String,String> questionsBySection = quizUtilities.getQuizQuestions(section);
			
			Iterator questionIt = questionsBySection.entrySet().iterator();
			while (questionIt.hasNext()) {
				Map.Entry<String, String> questionEntry = (Map.Entry<String, String>)questionIt.next();
				String questionText = questionEntry.getKey();
				String questionType = questionEntry.getValue();
				
				//NAME:  First page of the quiz requesting the user's first name
				if (questionType.equals("name")) {
					enterNameStep("Tester");
				}
				//EMAIL:  If the user is not logged in, request their email.
				else if (questionType.equals("email")) {
					enterEmailStep(questionText, "");  //This isn't being used right now.
				}
				//INFO:  These are just the transitional screens in between each quiz section.
				else if (questionType.equals("info")) {
					//This is just information for the user.  Just continue.
					continueStep();
				}
				//RANK:  Allows the user to rank up to 5 items.
				else if (questionType.equals("rank")) {
					processRankQuestion(questionText, "", "");
				}
				//MULTI-SELECT:  Allows user to select one or all answers in the list
				else if (questionType.equals("multi_select")) {
					processMultiSelectQuestion(questionText, "", "");				
				}
				//SINGLE-SELECT:  User has to select one from all options
				else if (questionType.equals("single_select")) {
					processSingleSelectQuestion(questionText, "what's your activity level", activityLevel.toString());
				}
				//BIOMETRIC:  User enters their gender, height, weight, and date of birth
				else if (questionType.equals("biometric")) {
					processBiometricQuestion(gender, feet, inches, weight, dateOfBirth);
				}
				//NOTES:  Final question to ask if the user has anything else to share.
				else if (questionType.equals("notes")) {
					processNotesQuestion("Test");
				}
				//ZIP:  Follow up question to allow us to provide menu items they can order.
				else if (questionType.equals("zip")) {
					processZipcodeQuestion("78758");
				}
				//LOADING:  After the quiz, this screen is present while results are processing
				else if (questionType.equals("loading")) {
					
				}
				else {
					
				}
			}
		}
	}
}

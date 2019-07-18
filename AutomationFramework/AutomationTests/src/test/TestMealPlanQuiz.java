package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import common.Gender;
import common.QuizUtilities;
import mobilepage.MobileQuiz;

public class TestMealPlanQuiz extends MobileTestCase {
	
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
	
	@Test
	public void testMaxFeet() {
		quizBasic("Talls", "78758", Gender.FEMALE, "9", "5", "100", "11/1990");
	}
	
	@Test
	public void testMinFeet() {
		quizBasic("Talls", "78758", Gender.FEMALE, "0", "5", "100", "11/1990");
	}
	
	@Test
	public void testMaxInches() {
		quizBasic("Talls", "78758", Gender.FEMALE, "5", "99", "100", "11/1990");
	}
	
	@Test
	public void testMinInches() {
		quizBasic("Talls", "78758", Gender.FEMALE, "5", "0", "100", "11/1990");
	}
	
	@Test
	public void testAllInches() {
		quizBasic("ShortPeopleGot", "78758", Gender.FEMALE, "0", "60", "100", "11/1990");
	}
	
	@Test
	public void testMaxWeight() {
		quizBasic("AteTooLittle", "78758", Gender.FEMALE, "5	", "7", "999", "11/1990");
	}
	
	@Test
	public void testMinWeight() {
		quizBasic("AteTooMuch", "78758", Gender.FEMALE, "5", "7", "0", "11/1990");
	}
	
	@Test
	public void testMinYear() {
		quizBasic("TooOld", "78758", Gender.FEMALE, "5", "7", "1200", "11/1000");
	}
	
	@Test 
	public void testMaxYear() {
		quizBasic("TooYoung", "78758", Gender.FEMALE, "5", "7", "100", "11/3000");
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
	
	public void quizBasic(String name, String zipCode, Gender gender, String feet, String inches, String weight, String dateOfBirth) {
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

}

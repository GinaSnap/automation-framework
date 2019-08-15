package test;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import common.CreditCardType;
import common.CustomerType;
import common.UserType;
import framework.MobileDriver;
import mobilepage.AccountHome;
import mobilepage.SnapHome;

public class MobileTestCase extends AbstractTestCase {
	
	public CreditCardType visa4242 = new CreditCardType("4242424242424242", "0424", "333", "Visa Ending In 4242");
	public CreditCardType visa0077 = new CreditCardType("4000000000000077", "0321", "333", "Visa Ending In 0077");
	public CreditCardType visa0341 = new CreditCardType("4000000000000341", "0321", "333", "Visa Ending In 0341");
	public CreditCardType mastercard4444 = new CreditCardType("5555 5555 5555 4444", "0424", "333", "MasterCard Ending In 4444");
	public String DefaultLocalZip = "78758";
	public String DefaultNationalSW = "73552";
	public String DefaultNationalNE = "11368";
	public String DefaultAustinZip = "78619";
	public String DefaultAustinStore = "Clarksville";
	public String DefaultDallasZip = "75044";
	public String DefaultDallasStore = "Uptown";
	public String DefaultHoustonZip = "77016";
	public String DefaultHoustonStore = "Kirby";
	public String DefaultPhillyZip = "19026";
	public String DefaultPhillyStore = "Old City";

	
	@Rule public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {
		MobileDriver.init();
		MobileDriver.pause(5000); //This is to give the app time to load.
		
		logger.setLevel(Level.INFO);
		logger.setUseParentHandlers(false);
	    //ConsoleHandler handler = new ConsoleHandler();
	    //logger.addHandler(handler);
		Handler h = new FileHandler("//Users/GMitchell/git/automation-framework/LogFiles/TestOutput.log");
		h.setFormatter(new SimpleFormatter() {
	          private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

	          @Override
	          public synchronized String format(LogRecord lr) {
	              return String.format(format,
	                      new Date(lr.getMillis()),
	                      lr.getLevel().getLocalizedName(),
	                      lr.getMessage()
	              );
	          }
	      });
//		Formatter f = new SimpleFormatter();
//		h.setFormatter(f);
//		h.setLevel(Level.ALL);
		logger.setLevel(Level.ALL);
		logger.addHandler(h);
		
		step(String.format("START Testing Method [%s]", name.getMethodName()));
	}

	@After
	public void tearDown() throws Exception {
		step(String.format("FINISH Testing Method [%s]", name.getMethodName()));

		MobileDriver.quit();
	}
	
	/**
	 * If no user is specified, we will login with the default user.  
	 * For now, that is a standard three day meal plan subscriber.
	 */
	public String login()
	{
		return login(StagingUser);
	}
	
	/**
	 * Login with a specific user type.  
	 * These users are predefined and always exist in the staging environment.
	 */
	public String login(UserType user)
	{
		return login(user.getUsername(), user.getPassword());
	}
	
	/**
	 * I am putting this function here to make it easily accessible for testcases.
	 * Always make updates to the code in SnapHome.
	 */
	public String login(String username, String password)
	{
		SnapHome snapHome = new SnapHome();
		return snapHome.login(username, password);
	}
	
	/**
	 * I am putting this logout function here to make it easily accessible for all testcases.
	 * The code should still be owned by AccountHome so that we have one version.
	 */
	public void logout()
	{
		AccountHome accountHome = new AccountHome();
		accountHome.logout();
	}
	
	public void relaunch()
	{
		MobileDriver.closeApp();
		MobileDriver.launchApp();
	}
	
	public HashMap<String, CustomerType> getZipCodeMap(boolean isSanityTest)
	{
		HashMap<String, CustomerType> zipCodeMap = new HashMap<String,CustomerType>();
		if (isSanityTest) {
			zipCodeMap.put("73552", CustomerType.NATIONAL);   //Maybe just add one from each region?
		}
		else {
			String file = "/Users/GMitchell/git/automation-framework/Zip_National.txt";
			try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {
	            String line;
	            CustomerType customerType;
	            while ((line = br.readLine()) != null) {
	            		String[] zipCodeData = line.split(",");
	            		if (zipCodeData[1].equals("NATIONAL")) {
	            			customerType = CustomerType.NATIONAL;
	            		} else if (zipCodeData[1].equals("LOCAL")) {
	            			customerType = CustomerType.LOCAL;
	            		} else {
	            			customerType = CustomerType.OUT_OF_RANGE;
	            		}
	         
	                zipCodeMap.put(zipCodeData[0], customerType);
	            }
	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }	
		}
		
		return zipCodeMap;
	}
	
	public void step(String msg)
	{
		logger.info(String.format("Step:  %s", msg));
	}
	
	public void passTest(String msg)
	{
		logger.info(String.format("Pass:  %s", msg));
	}
	
	public void failTest(String msg)
	{
		logger.severe(String.format("FAIL:  %s", msg));
		fail(msg);
	}
	
	public void failTestAndContinue(String msg)
	{
		logger.warning(String.format("FAIL:  %s", msg));
	}
	
	public enum QuestionType{
		STATIC_TEXT,
		SINGLE_SELECT,
		MULTIPLE_SELECT,
		RANK,
		TEXT_BOX
	}
	
	public Map<String, QuestionType> getQuestionMap(){
		Map<String, QuestionType> questionMap = new LinkedHashMap<String, QuestionType>();
		questionMap.put("what's your first name", QuestionType.TEXT_BOX);
		questionMap.put("nice to meet you", QuestionType.STATIC_TEXT);
		questionMap.put("when it comes to health, which best describes you", QuestionType.SINGLE_SELECT);
		questionMap.put("let's talk about your goals.", QuestionType.STATIC_TEXT);
		questionMap.put("tell us about your goals, so that we can help you reach them", QuestionType.RANK);

		return questionMap;
		
	}
	
	public Map<String, String> getBasicInfoQuestions()
	{
		Map<String,String> basicInfoQuestions = new LinkedHashMap<String,String>();
		basicInfoQuestions.put("when it comes to health, which best describes you?", "I want a new routine - let's come up with a plan together");
		return basicInfoQuestions;
		
	}
	
	public Map<String, List<String>> getBasicInfoQuestions2()
	{
		List<String> whenItComesToHealth = new ArrayList<String>();
		whenItComesToHealth.add("I know exactly what I need to do");
		whenItComesToHealth.add("I think I know what works for me, but I love to learn");
		whenItComesToHealth.add("I want a new routine - let's come up with a plan together");
		
		Map<String,List<String>> basicInfoQuestions = new LinkedHashMap<String,List<String>>();
		basicInfoQuestions.put("when it comes to health, which best describes you?", whenItComesToHealth);
		return basicInfoQuestions;
		
	}
	
	public Map<String, String> getGeneralQuestions(boolean includeFollowUp){
		
		Map<String,String> generalQuestions = new LinkedHashMap<String,String>();
		if (includeFollowUp) {
			generalQuestions.put("tell us about your goals, so that we can help you reach them!", "manage a health issue");
			generalQuestions.put("what health issues are you currently dealing with?", "digestive issues");
		}
		else {
			generalQuestions.put("tell us about your goals, so that we can help you reach them!", "maintain my health");
		}
		generalQuestions.put("what is your relationship with food?", "I have a balanced diet");
		generalQuestions.put("what is your biggest roadblock when it comes to hitting your goals?", "too busy");
		return generalQuestions;
	}
	
	public Map<String, List<String>> getGeneralQuestions2(boolean includeFollowUp){
		
		List<String> tellUsAboutYourGoals = new ArrayList<String>();
		tellUsAboutYourGoals.add("maintain my health");
		tellUsAboutYourGoals.add("weight loss");
		tellUsAboutYourGoals.add("improve my athletic performance");
		tellUsAboutYourGoals.add("manage a health issue");
		tellUsAboutYourGoals.add("find a realistic plan I can stick to");
		tellUsAboutYourGoals.add("improve my digestion");
		tellUsAboutYourGoals.add("more energy");
		tellUsAboutYourGoals.add("decrease inflammation");
		
		List<String> whatHealthIssues = new ArrayList<String>();
		whatHealthIssues.add("digestive issues");
		whatHealthIssues.add("autoimmune condition");
		whatHealthIssues.add("high blood pressure");
		whatHealthIssues.add("thyroid condition");
		whatHealthIssues.add("diabetes, pre-diabetes or blood sugar issues");
		whatHealthIssues.add("high cholesterol");
		whatHealthIssues.add("other");

		
		Map<String,List<String>> generalQuestions = new LinkedHashMap<String,List<String>>();
		if (includeFollowUp) {
			generalQuestions.put("tell us about your goals, so that we can help you reach them!", tellUsAboutYourGoals);
			generalQuestions.put("what health issues are you currently dealing with?", whatHealthIssues);
		}
		else {
			generalQuestions.put("tell us about your goals, so that we can help you reach them!", tellUsAboutYourGoals);
		}
		//generalQuestions.put("what is your relationship with food?", "I have a balanced diet");
		//generalQuestions.put("what is your biggest roadblock when it comes to hitting your goals?", "too busy");
		return generalQuestions;
	}
	
	public Map<String, String> getActivityQuestions(boolean includeFollowUp){
		
		Map<String,String> activityQuestions = new LinkedHashMap<String,String>();
		activityQuestions.put("what's your activity level?", "Sedentary");
		if (includeFollowUp) {
			activityQuestions.put("do you currently follow a particular dietary lifestyle or way of eating?", "sure do");
			activityQuestions.put("which lifestyle do you follow?", "paleo");
		}
		else {
			activityQuestions.put("do you currently follow a particular dietary lifestyle or way of eating?", "nothing specific right now");
		}
		activityQuestions.put("do you avoid any of the following items in your diet?", "none");
		return activityQuestions;
		
	}
	
	public Map<String, String> getTypicalDayQuestions(){
		
		Map<String,String> typicalDayQuestions = new LinkedHashMap<String,String>();
		typicalDayQuestions.put("do you plan all your meals for the week?", "yes, I'm a planner!");
		typicalDayQuestions.put("when it comes to time...", "I don't have any");
		typicalDayQuestions.put("tell us about your stress level", "stress? what's stress?");
		typicalDayQuestions.put("do you enjoy cooking?", "yes - just call me chef!");
		typicalDayQuestions.put("when it comes to spending on food and groceries...", "health is wealth - quality is priority");
		typicalDayQuestions.put("what type of eater are you?", "1 big meal a day");
		return typicalDayQuestions;
		
	}

}

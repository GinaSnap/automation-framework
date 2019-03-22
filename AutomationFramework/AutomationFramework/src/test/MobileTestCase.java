package test;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.Before;

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
	
	@Before
	public void setUp() throws Exception {
		MobileDriver.init();
		MobileDriver.pause(5000); //This is to give the app time to load.
		
		logger.setLevel(Level.INFO);
		Handler h = new FileHandler("//users/GMitchell/git/automation-framework/LogFiles/TestOutput.log");
		Formatter f = new SimpleFormatter();
		h.setFormatter(f);
		h.setLevel(Level.ALL);
		logger.setLevel(Level.ALL);
		logger.addHandler(h);
		
	}

	@After
	public void tearDown() throws Exception {
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
	
	public HashMap<String, CustomerType> getZipCodeMap()
	{
		HashMap<String, CustomerType> zipCodeMap = new HashMap<String,CustomerType>();
		zipCodeMap.put("77777", CustomerType.NATIONAL);   //Local or National
		zipCodeMap.put("78758", CustomerType.LOCAL);   //Austin TX Arboretum
		zipCodeMap.put("78619", CustomerType.LOCAL);   //Austin Outskirts - Driftwood
		zipCodeMap.put("76092", CustomerType.LOCAL);   //Dallas Southlake Area
		zipCodeMap.put("75065", CustomerType.LOCAL);   //Dallas Outskirts - Denton TX
		zipCodeMap.put("77094", CustomerType.LOCAL);   //Houtson TX
		zipCodeMap.put("77545", CustomerType.LOCAL);   //Houston Outskirts - Fresno
		zipCodeMap.put("19103", CustomerType.LOCAL);   //Philly
		zipCodeMap.put("19073", CustomerType.LOCAL);   //Philly Outskirts - Newtown Square
		zipCodeMap.put("78006", CustomerType.NATIONAL);   //San Antonio, TX
		zipCodeMap.put("88101", CustomerType.NATIONAL);   //Clovis, NM
		zipCodeMap.put("73008", CustomerType.NATIONAL);   //Oklahoma City, OK
		zipCodeMap.put("67052", CustomerType.NATIONAL);   //Wichita, KS
		zipCodeMap.put("71101", CustomerType.NATIONAL);   //Shreveport, LA
		zipCodeMap.put("30301", CustomerType.OUT_OF_RANGE); //Atlanta, GA
		
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

}

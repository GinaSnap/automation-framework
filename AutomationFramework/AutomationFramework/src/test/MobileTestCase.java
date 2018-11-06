package test;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.Before;

import common.CreditCardType;
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
		Handler h = new FileHandler("%h/TestOutput_%g.log", 1000, 4);
		Formatter f = new SimpleFormatter();
		h.setFormatter(f);
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

}

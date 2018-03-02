package test;

import org.junit.After;
import org.junit.Before;

import common.UserType;
import framework.AbstractDriver;
import framework.MobileDriver;
import mobilepage.AccountHome;
import mobilepage.SnapHome;

public class MobileTestCase extends AbstractTestCase {
	
	@Before
	public void setUp() throws Exception {
		MobileDriver.init();
		MobileDriver.pause(5000); //This is to give the app time to load.
	}

	@After
	public void tearDown() throws Exception {
		MobileDriver.quit();
	}
	
	/**
	 * If no user is specified, we will login with the default user.  
	 * For now, that is a standard three day meal plan subscriber.
	 */
	public void login()
	{
		login(ThreeDayMealPlan);
	}
	
	/**
	 * Login with a specific user type.  
	 * These users are predefined and always exist in the staging environment.
	 */
	public void login(UserType user)
	{
		login(user.getUsername(), user.getPassword());
	}
	
	/**
	 * I am putting this function here to make it easily accessible for testcases.
	 * Always make updates to the code in SnapHome.
	 */
	public void login(String username, String password)
	{
		SnapHome snapHome = new SnapHome();
		snapHome.login(username, password);
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

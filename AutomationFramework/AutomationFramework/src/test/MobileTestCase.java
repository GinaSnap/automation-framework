package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;

import common.UserType;
import framework.MobileDriver;
import mobilepage.AccountHome;
import mobilepage.LowerNavPage;
import mobilepage.MealPlanMainPage;
import mobilepage.MealPlanningSubmission;
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

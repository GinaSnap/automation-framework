package test;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import common.UserType;
import common.Utilities;
import framework.AbstractDriver;
import framework.WWWDriver;
import page.LoginPage;

public class BaseTestCase extends AbstractTestCase {
	
	@Before
	public void setUp() throws Exception {
		WWWDriver.init();
	}

	@After
	public void tearDown() throws Exception {
		WWWDriver.quit();
	}
	
	public void login()
	{
		login(ThreeDayMealPlan);
	}
	
	public void login(UserType user)
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(user.getUsername(), user.getPassword());
	}
	
	public void login(String phone, String pwd)
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(phone, pwd);

	}
	
}


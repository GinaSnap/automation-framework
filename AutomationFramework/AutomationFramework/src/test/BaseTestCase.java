package test;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import common.UserType;
import common.Utilities;
import framework.AbstractDriver;
import framework.WWWDriver;
import page.LoginPage;
import page.SnapHome;

public class BaseTestCase extends AbstractTestCase {
	
	@Before
	public void setUp() throws Exception {
		WWWDriver.init();
	}

	@After
	public void tearDown() throws Exception {
		WWWDriver.quit();
	}
	
	public String login()
	{
		login(StagingUser);
		return "";
	}
	
	public String login(UserType user)
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(user.getUsername(), user.getPassword());
		return "";
	}
	
	public String login(String phone, String pwd)
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(phone, pwd);
		return "";
	}
	
	public String goToShoppingCart()
	{
		SnapHome snapHome = new SnapHome();
		return snapHome.goToShoppingCart();
	}
	
}


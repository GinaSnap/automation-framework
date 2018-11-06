package test;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import common.CreditCardType;
import common.UserType;
import common.Utilities;
import framework.AbstractDriver;
import framework.WWWDriver;
import page.LoginPage;
import page.SnapHome;

public class BaseTestCase extends AbstractTestCase {
	
	public CreditCardType visa4242 = new CreditCardType("4242424242424242", "04/2024", "333", "Visa Ending In 4242");
	public CreditCardType visa0077 = new CreditCardType("4000000000000077", "04/2024", "333", "Visa Ending In 0077");
	public CreditCardType visa0341 = new CreditCardType("4000000000000341", "04/2024", "333", "Visa Ending In 0341");
	public CreditCardType mastercard4444 = new CreditCardType("5555 5555 5555 4444", "04/2024", "333", "MasterCard Ending In 4444");
	
	@Before
	public void setUp() throws Exception {
		WWWDriver.init();
		
		logger.setLevel(Level.INFO);
		Handler h = new FileHandler("%h/TestOutput_%g.log", 1000, 4);
		Formatter f = new SimpleFormatter();
		h.setFormatter(f);
		logger.addHandler(h);
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


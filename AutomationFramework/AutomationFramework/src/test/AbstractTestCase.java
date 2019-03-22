package test;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;

import common.CreditCardType;
import common.UserType;
import common.Utilities;

public abstract class AbstractTestCase {
	
	static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public final long DEFAULT_TIMEOUT=5000;
	public final String DEFAULT_PWD = "qqqqqqqq";
	public UserType StagingUser = new UserType("(512) 608-5335", "qqqqqqqq", "Gina", "Mitchell", "gina.l.mitchell@gmail.com","78758");
	public UserType UserNoMealPlan = new UserType("(007) 555-0170","qqqqqqqq","Test","0075550170","0075550170@snapkitchen.com","78758");
	public UserType ProdUser7 = new UserType("(512) 694-6161", "snapkit1", "QA", "TestUser7", "testuser7@snapkitchen.com","78758");
	public UserType defaultUser = ProdUser7;
	
	Utilities util = new Utilities();

	
	/**
	 * This will build a unique phone number that staging will recognize as a test number.
	 * Allowed Values: ###-555-01## where # is any number.
	 * In this case, "222222" will be accepted as the secret code.
	 * Returned format is:  (###) 555-01##
	 * @return
	 */
	public String getUniquePhone()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("(");
		sb.append(util.getUniqueString(3));
		sb.append(") ");
		sb.append("555-");
		sb.append("01");
		sb.append(util.getUniqueString(2));
		
		return sb.toString();
	}
	
	@Before
	public abstract void setUp() throws Exception;

	@After
	public abstract void tearDown() throws Exception;
	
	public abstract String login();
	
	public abstract String login(UserType userType);
	
	public abstract String login(String username, String password);

}

package test;

import org.junit.After;
import org.junit.Before;

import common.UserType;
import common.Utilities;

public abstract class AbstractTestCase {
	
	public final long DEFAULT_TIMEOUT=5000;
	public final String DEFAULT_PWD = "snapkit1";
	public UserType ThreeDayMealPlan = new UserType("(111) 555-0103", "snapkit1", "ThreeDay", "MealPlan", "test3@snapkitchen.com");
	public UserType FiveDayMealPlan = new UserType("(111) 555-0105", "snapkit1", "","","");
	public UserType SevenDayMealPlan = new UserType("(111) 555-0107", "snapkit1","","","");
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
	
	public abstract void login();
	
	public abstract void login(UserType userType);
	
	public abstract void login(String username, String password);

}

package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCreateMealPlan.class, 
	TestLogin.class, TestMobile.class, 
	TestMobileAccount.class,
	TestMobile.class,
	TestMobilePaymentType.class
	})
public class TestMobileTestSuite {
	static {
        new TestLogin();
    }

}

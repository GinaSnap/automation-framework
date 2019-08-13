package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public class TestMobileTestRunner {

	public static void main(String[] args) {
		org.junit.runner.Result result = JUnitCore.runClasses(TestMobileTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());

	}

}

package stlsecodingchallengetemplate;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
  public static void main(String[] args) {

    // run tests and count passing/failing
    Result result = JUnitCore.runClasses(BankServiceTest.class);
    int testsRun = result.getRunCount();
    int testsFailed = result.getFailureCount();

    // optional: print failures
    for (Failure failure: result.getFailures()) {
      System.out.println(failure.toString());
    }

    // REQUIRED
    // these lines are required in order for our system to assign a grade based on the unit tests
    System.out.println("__JUNIT__ TESTS RUN COUNT: " + testsRun);
    System.out.println("__JUNIT__ TESTS FAILURE COUNT: " + testsFailed);
    System.out.println("__JUNIT__ ALL TESTS PASSING: " + result.wasSuccessful());

  }
}

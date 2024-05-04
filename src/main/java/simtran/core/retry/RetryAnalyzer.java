package simtran.core.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static simtran.core.config.ConfigManager.config;

/**
 * This class implements the `IRetryAnalyzer` interface from TestNG to provide retry functionality for failed tests.
 *
 * @author simtran
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int counter = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (counter < config().retry()) {
                counter++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}

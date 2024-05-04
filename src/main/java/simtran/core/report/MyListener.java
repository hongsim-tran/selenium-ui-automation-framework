package simtran.core.report;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import simtran.core.utils.MyLogger;
import simtran.core.wdm.DriverManager;

/**
 * This class implements the `ITestListener` and `TestLifecycleListener` interfaces from TestNG to provide custom behavior for test execution events
 *
 * @author simtran
 */
public class MyListener implements ITestListener, TestLifecycleListener {

    @Override
    public void onStart(ITestContext context) {
        MyLogger.debug("Start the test: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        MyLogger.debug("Finish the test: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        MyLogger.debug("Start the test case: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        MyLogger.debug("TEST CASE - " + result.getName() + " is PASSED!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Todo
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Todo
    }

    @Override
    public void onTestFailure(ITestResult result) {
        MyLogger.error("TEST CASE - " + result.getName() + " is FAILED!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        MyLogger.error("TEST CASE - " + result.getName() + " is SKIPPED!");
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (DriverManager.getDriver() != null) {
                AllureManager.getBrowserInfo();
                AllureManager.saveScreenshotPNG();
            }
            AllureManager.saveLogMessage(result.getStatusDetails().toString());
        }
    }
}

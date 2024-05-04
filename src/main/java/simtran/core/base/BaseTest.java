package simtran.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import simtran.core.report.AllureManager;
import simtran.core.report.MyListener;
import simtran.core.utils.MyLogger;
import simtran.core.wdm.DriverManager;

import java.time.Duration;

import static simtran.core.config.ConfigManager.config;

/**
 * This base class provides common functionality and setup for Selenium tests.
 * It's intended to be extended by specific test classes to promote code reuse and maintainability.
 *
 * @author simtran
 */
@Listeners(MyListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"target"})
    protected void beforeSuite(ITestContext testContext, String target) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), (config().logLevel()));
        AllureManager.setAllureEnvironment(target);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"target", "browser"})
    protected void setup(String target, String browser) {
        MyLogger.debug("Start driver...!");
        driver = new DriverManager().createDriverInstance(browser);
        DriverManager.setDriver(driver, target);
        MyLogger.debug("Open the web page at: " + config(target).baseUrl());
        DriverManager.getDriver().get(config(target).baseUrl());

        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        MyLogger.debug("Stop driver...!");
        DriverManager.quit();
    }

    public void wait(int timeInSecond) {
        try {
            Thread.sleep(Duration.ofSeconds(timeInSecond));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package simtran.evershop.tests.cucumber.stepDefinitions;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import simtran.core.utils.MyLogger;
import simtran.core.wdm.DriverManager;

import java.time.Duration;

import static simtran.core.config.ConfigManager.config;

public class StepSetup {
    public static String target;
    public static String browser;
    public static WebDriver driver;
    public static SoftAssert softAssert;
    public static Faker faker;

    public static void setTarget(){
        target = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("target");
    }

    public static void setBrowser(){
        browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    }

    public static void stepSetup(){
        MyLogger.debug("Start driver...!");
        driver = new DriverManager().createDriverInstance(browser);
        DriverManager.setDriver(driver, StepSetup.target);
        softAssert = new SoftAssert();
        faker = new Faker();
    }

    protected void wait(int timeInSecond) {
        try {
            MyLogger.debug("Waiting for " + timeInSecond + " seconds");
            Thread.sleep(Duration.ofSeconds(timeInSecond));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

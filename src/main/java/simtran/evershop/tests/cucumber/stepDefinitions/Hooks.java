package simtran.evershop.tests.cucumber.stepDefinitions;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import simtran.core.report.AllureManager;
import simtran.core.utils.DBConnection;
import simtran.core.wdm.DriverManager;

import java.io.File;
import java.io.IOException;

import static simtran.core.config.ConfigManager.config;

public class Hooks {

    @BeforeAll
    public static void beforeAll(){
        StepSetup.setTarget();
        StepSetup.setBrowser();
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), (config().logLevel()));
        AllureManager.setAllureEnvironment(StepSetup.target);
    }

    @Before ()
    public void beforeScenario(){
        StepSetup.stepSetup();
        DBConnection.getConnection(StepSetup.target);
    }

    @After
    public void afterScenario(){
        DriverManager.quit();
        DBConnection.closeConnection();
    }

    @AfterStep
    public void addScreenshotWhenFailure(Scenario scenario){
        if (scenario.isFailed()){
            File sourcePath = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
                scenario.attach(fileContent, "image/png", "screenshot");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package simtran.evershop.tests.cucumber.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import simtran.core.utils.DBConnection;
import simtran.core.wdm.DriverManager;

import java.io.File;
import java.io.IOException;

public class Hooks {

    @Before ()
    public void beforeScenario(){
        StepSetup.target = "local";
        StepSetup.browser = "chrome";
        StepSetup.stepSetup();
        DBConnection.getConnection(StepSetup.target);
    }

    @After
    public void afterScenario(){
        StepSetup.softAssert.assertAll();
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

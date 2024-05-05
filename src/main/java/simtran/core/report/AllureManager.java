package simtran.core.report;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import simtran.core.wdm.DriverManager;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static simtran.core.config.ConfigManager.config;

/**
 * This class provides utility methods for collecting test data and artifacts for inclusion in the Allure report
 *
 * @author simtran
 */
public class AllureManager {

    /**
     * Sets environment information for Allure reports.
     *
     * @param target target environment
     */
    public static void setAllureEnvironment(String target) {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", System.getProperty("os.name"))
                        .put("Test Environment", target)
                        .put("Headless mode", config(target).headless().toString())
                        .put("URL", config(target).baseUrl())
                        .build());
    }

    @Attachment(value = "Log message", type = "text/plain")
    public static String saveLogMessage(String message) {
        return message;
    }

    @Attachment(value = "Browser info", type = "text/plain")
    public static String getBrowserInfo() {
        return DriverManager.getBrowserInfo();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
package simtran.core.wdm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static simtran.core.config.ConfigManager.config;

/**
 * This enum provides a factory pattern for creating WebDriver instances with common browser configurations.
 * It supports Chrome, Firefox, Edge, and Safari browsers.
 *
 * @author simtran
 */
public enum BrowserFactory {
    /**
     * Represents the Chrome browser.
     */
    CHROME {
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();

            options.addArguments(BrowserArguments.START_MAXIMIZED);
            options.addArguments(BrowserArguments.DISABLE_NOTIFICATIONS);
            options.addArguments(BrowserArguments.DISABLE_POPUP_BLOCKING);

            if (config().headless())
                options.addArguments(BrowserArguments.CHROME_HEADLESS);
            return options;
        }

        @Override
        public WebDriver initDriver() {
            return new ChromeDriver(getOptions());
        }
    },

    /**
     * Represents the Firefox browser.
     */
    FIREFOX {
        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();

            if (config().headless())
                options.addArguments(BrowserArguments.HEADLESS);
            return options;
        }

        @Override
        public WebDriver initDriver() {
            FirefoxDriver driver = new FirefoxDriver(getOptions());
            driver.manage().window().maximize();
            return driver;
        }
    },

    /**
     * Represents the Microsoft Edge browser.
     */
    EDGE {
        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();

            options.addArguments(BrowserArguments.START_MAXIMIZED);

            if (config().headless())
                options.addArguments(BrowserArguments.HEADLESS);
            return options;
        }

        @Override
        public WebDriver initDriver() {
            return new EdgeDriver(getOptions());
        }
    },

    /**
     * Represents the Safari browser.
     */
    SAFARI {
        @Override
        public SafariOptions getOptions() {
            SafariOptions options = new SafariOptions();
            options.setAutomaticInspection(false);

            return options;
        }

        @Override
        public WebDriver initDriver() {
            if (config().headless())
                throw new IllegalStateException("Headless not supported for Safari!");

            return new SafariDriver(getOptions());
        }
    };

    /**
     * Method to retrieve browser-specific options for WebDriver initialization.
     *
     * @return An instance of the appropriate `AbstractDriverOptions` subclass configured for the specific browser.
     */
    public abstract AbstractDriverOptions<?> getOptions();

    /**
     * Method to initialize a WebDriver instance with the browser-specific options.
     *
     * @return A new WebDriver instance configured for the chosen browser.
     */
    public abstract WebDriver initDriver();
}

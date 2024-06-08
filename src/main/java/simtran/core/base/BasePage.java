package simtran.core.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import simtran.core.utils.MyLogger;
import simtran.core.wdm.DriverManager;
import simtran.evershop.pages.Page;

import java.time.Duration;
import java.util.List;

/**
 * This class provides the keywords to perform actions on web UI elements by passing the UI locator variable of type {@link By}
 * or UI element variable of type {@Link WebElement}
 *
 * @author simtran
 */
public class BasePage extends Page {

    protected void wait(int timeInSecond) {
        try {
            MyLogger.debug("Waiting for " + timeInSecond + " seconds");
            Thread.sleep(Duration.ofSeconds(timeInSecond));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> WebElement findElement(T element) {
        if (element.getClass().getName().contains("By")) {
            return DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else return (WebElement) element;
    }

    protected <T> List<WebElement> findElements(T element) {
        if (element.getClass().getName().contains("By")) {
            return DriverManager.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) element));

        } else return List.of((WebElement) element);
    }

    protected <T> WebElement findChildElement(T element, T parentElement) {
        if (element.getClass().getName().contains("By")) {
            if (parentElement.getClass().getName().contains("By"))
                return findElement(parentElement).findElement((By) element);
            else return ((WebElement) parentElement).findElement((By) element);
        } else return (WebElement) element;
    }

    protected <T> List<WebElement> findChildrenElements(T element, T parentElement) {
        if (element.getClass().getName().contains("By")) {
            if (parentElement.getClass().getName().contains("By"))
                return findElement(parentElement).findElements((By) element);
            else return ((WebElement) parentElement).findElements((By) element);
        } else return List.of((WebElement) element);
    }

    protected <T> boolean isVisible(T element) {
        if (element.getClass().getName().contains("By")) {
            try {
                return DriverManager.getDriver().findElement((By) element).isDisplayed();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                MyLogger.debug(String.format("Element: %s not found", element));
                return false;
            }
        } else {
            try {
                return ((WebElement) element).isDisplayed();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                MyLogger.debug(String.format("Element: %s not found", element));
                return false;
            }
        }
    }

    protected <T> boolean isSelected(T element) {
        if (element.getClass().getName().contains("By")) {
            try {
                return DriverManager.getDriver().findElement((By) element).isSelected();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                MyLogger.debug(String.format("Element: %s not found", element));
                return false;
            }
        } else {
            try {
                return ((WebElement) element).isSelected();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                MyLogger.debug(String.format("Element: %s not found", element));
                return false;
            }
        }
    }

    protected <T> void click(T element) {
        WebElement el;
        if (element.getClass().getName().contains("By")) {
            el = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable((By) element));
        } else {
            el = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable((WebElement) element));
        }
        try {
            MyLogger.debug("Click the element: " + element);
            el.click();
        } catch (ElementClickInterceptedException e) {
            MyLogger.debug(String.format("Click the element: %s by JS", element));
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
        }
    }

    protected <T> void enterText(T element, String text) {
        WebElement el;
        MyLogger.debug(String.format("Enter text: %s into the text field: %s", text, element));
        if (element.getClass().getName().contains("By")) {
            el = DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else el = DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element));

        el.clear();
        el.sendKeys(text);
    }

    protected <T> void enterKey(T element, Keys key) {
        WebElement el;
        MyLogger.debug(String.format("Press the key: %s", key));
        if (element.getClass().getName().contains("By")) {
            el = DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else el = DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element));

        el.sendKeys(key);
    }

    protected <T> void selectDropdown(T element, String dropdownOption) {
        WebElement el;
        if (element.getClass().getName().contains("By")) {
            el = DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else el = DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element));

        Select select = new Select(el);
        MyLogger.debug(String.format("Select the option: %s for the dropdown: %s ", dropdownOption, element));
        select.selectByVisibleText(dropdownOption);
    }

    protected <T> void selectDropdown(T element, int dropdownOption) {
        WebElement el;
        if (element.getClass().getName().contains("By")) {
            el = DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else el = DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element));

        Select select = new Select(el);
        MyLogger.debug(String.format("Select the option: %s for the dropdown: %s ", dropdownOption, element));
        select.selectByIndex(dropdownOption);
    }

    protected void refresh() {
        MyLogger.debug("Refresh the page...");
        DriverManager.getDriver().navigate().refresh();
    }

    protected void openUrl(String url) {
        MyLogger.debug("Open the url " + url);
        DriverManager.getDriver().get(url);
    }

    protected String getUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    protected <T> String getText(T element) {
        if (element.getClass().getName().contains("By")) {
            return DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element)).getText();
        } else return DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element)).getText();
    }

    protected <T> String getAttribute(T element, String attributeName) {
        if (element.getClass().getName().contains("By")) {
            return DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element)).getAttribute(attributeName);
        } else
            return DriverManager.getWait().until(ExpectedConditions.visibilityOf((WebElement) element)).getAttribute(attributeName);
    }

    protected <T> String getValue(T element) {
        return getAttribute(element, "value");
    }

    protected String getAlertText() {
        return DriverManager.getWait().until(ExpectedConditions.alertIsPresent()).getText();
    }

    protected void acceptAlert() {
        MyLogger.debug("Accept the alert");
        DriverManager.getWait().until(ExpectedConditions.alertIsPresent()).accept();
    }

    protected void dismissAlert() {
        MyLogger.debug("Dismiss the alert");
        DriverManager.getWait().until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    protected void inputAlert(String text) {
        MyLogger.debug(String.format("Enter text: %s into the alert", text));
        DriverManager.getWait().until(ExpectedConditions.alertIsPresent()).sendKeys(text);
    }

    protected WebDriver switchToIframe(int index) {
        MyLogger.debug("Switch to iframe number: " + index);
        return DriverManager.getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    protected WebDriver switchToIframe(String nameOrId) {
        MyLogger.debug("Switch to iframe: " + nameOrId);
        return DriverManager.getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
    }

    protected <T> WebDriver switchToIframe(T frameElement) {
        MyLogger.debug("Switch to iframe: " + frameElement);
        if (frameElement.getClass().getName().contains("By")) {
            return DriverManager.getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((By) frameElement));
        } else
            return DriverManager.getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((WebElement) frameElement));
    }

    protected WebDriver switchToDefaultWindow() {
        MyLogger.debug("Switch to the default window");
        return DriverManager.getDriver().switchTo().defaultContent();
    }

    protected int getNoOfIframes() {
        return DriverManager.getDriver().findElements(By.tagName("iframe")).size();
    }
}

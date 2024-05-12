package simtran.alfiotest.pages.prelogin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import simtran.alfiotest.pages.Page;
import simtran.alfiotest.pages.loggedin.events.EventsPage;
import simtran.core.base.BasePage;

public class LoginPage extends BasePage {

    //**************** Page's locators ****************
    private final By pageTitle = By.cssSelector(".page-header h2");
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By cancelButton = By.xpath("//button[contains(text(), 'Cancel')]");
    private final By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private final By captchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");
    private final By captchaCheckbox = By.id("recaptcha-anchor");
    private final By errorMessage = By.cssSelector(".alert.alert-danger");

    //**************** Page's attributes ****************
    public String getPageTitle() {
        return getText(pageTitle);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    //**************** Page's actions ****************
    @Step("Input username into the username field")
    public LoginPage inputUsername(String username) {
        enterText(usernameInput, username);
        return this;
    }

    @Step("Input password into the password field")
    public LoginPage inputPassword(String password) {
        enterText(passwordInput, password);
        return this;
    }

    @Step("Check the captcha checkbox if it's available")
    public LoginPage clickCaptchaCheckbox() {
        if (isVisible(captchaFrame)) {
            switchToIframe(captchaFrame);
            click(captchaCheckbox);
            switchToDefaultWindow();
        }
        return this;
    }

    @Step("Click the Cancel button")
    public LoginPage clickCancelButton() {
        click(cancelButton);
        return this;
    }

    @Step("Click the Login button")
    public EventsPage clickLoginButton() {
        click(loginButton);
        return Page.eventsPage();
    }
}

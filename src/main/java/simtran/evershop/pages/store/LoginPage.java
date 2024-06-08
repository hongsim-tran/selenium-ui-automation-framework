package simtran.evershop.pages.store;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import simtran.evershop.pages.Page;

public class LoginPage extends Navigation {

    //**************** Page's locators ****************
    private final By txbEmail = By.cssSelector("input[placeholder='Email']");
    private final By txbPassword = By.cssSelector("input[placeholder='Password']");
    private final By btnSignIn = By.cssSelector("button[type='submit']");
    private final By lnkCreateAnAccount = By.xpath("//a[normalize-space()='Create an account']");
    private final By lnkForgotPassword = By.xpath("//a[normalize-space()='Forgot your password?']");
    private final By txtEmailError = By.xpath("//input[@name='email']/ancestor::div[contains(@class,'field-wrapper')]/following-sibling::div[contains(@class,'field-error')]");
    private final By txtPasswordError = By.xpath("//input[@name='password']/ancestor::div[contains(@class,'field-wrapper')]/following-sibling::div[contains(@class,'field-error')]");
    private final By txtCredentialError = By.xpath("//div[contains(@class,'text-critical')]");

    //**************** Page's attributes ****************
    public String getEmailErrorMessage() {
        return getText(txtEmailError);
    }

    public String getPasswordErrorMessage() {
        return getText(txtPasswordError);
    }

    public String getCredentialErrorMessage() {
        return getText(txtCredentialError);
    }

    //**************** Page's actions ****************
    @Step
    public LoginPage inputEmail(String email) {
        enterText(txbEmail, email);
        return this;
    }

    @Step
    public LoginPage inputPassword(String password) {
        enterText(txbPassword, password);
        return this;
    }

    @Step
    public Homepage clickSignInButton() {
        click(btnSignIn);
        return Page.homepage();
    }

    @Step
    public void clickCreateAnAccountLink() {
        // Todo
        click(lnkCreateAnAccount);
    }

    @Step
    public void clickForgotPasswordLink() {
        // Todo
        click(lnkForgotPassword);
    }
}

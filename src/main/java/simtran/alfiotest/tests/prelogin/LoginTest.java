package simtran.alfiotest.tests.prelogin;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import simtran.alfiotest.pages.Page;
import simtran.core.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test(description = "Login page's title", groups = {"local", "prod"})
    public void verifyLoginPageTitle() {
        Allure.step("Verify whether or not the Page title is correct");
        Assert.assertEquals(Page.loginPage().getPageTitle(), "Authentication requested");
    }

    @Test(description = "Login - Invalid credential", groups = {"local"})
    public void loginWithInvalidCredential() {
        Page.loginPage()
                .inputUsername("invalid username")
                .inputPassword("invalid password")
                .clickCaptchaCheckbox()
                .clickLoginButton();

        Allure.step("Verify whether the error message shown correctly");
        Assert.assertEquals(Page.loginPage().getErrorMessage(), "Unknown user or wrong password. Please try again.");
    }

    @Test(description = "Login - Valid credential", groups = {"local"})
    public void loginWithValidCredential() {
        Page.loginPage()
                .inputUsername("admin")
                .inputPassword("abcd")
                .clickCaptchaCheckbox()
                .clickLoginButton();

        Allure.step("Verify if the Events page is opened successfully");
        Assert.assertEquals(Page.eventsPage().getPageTitle(), "Events");
    }

    @Test (description = "Login - Ignore captcha checkbox", groups = {"prod"})
    public void loginWithoutCheckingCaptchaCheckbox(){
        Page.loginPage()
                .inputUsername("username")
                .inputPassword("password")
                .clickLoginButton();

        Allure.step("Verify if error message shows when un-checking captcha checkbox");
        Assert.assertEquals(Page.loginPage().getErrorMessage(), "Recaptcha check failed. Are you a human?");
    }
}

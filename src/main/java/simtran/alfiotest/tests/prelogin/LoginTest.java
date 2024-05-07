package simtran.alfiotest.tests.prelogin;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import simtran.alfiotest.pages.Page;
import simtran.core.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test(description = "Login page's title")
    public void verifyLoginPageTitle() {
        Allure.step("Verify whether or not the Page title is correct");
        Assert.assertEquals(Page.loginPage().getPageTitle(), "Authentication requested");
    }

    @Test(description = "Login - Invalid credential", enabled = false)
    public void loginWithInvalidCredential() {
        Page.loginPage()
                .inputUsername("invalid username")
                .inputPassword("invalid password")
                .clickCaptchaCheckbox()
                .clickLoginButton();

        Allure.step("Verify whether the error message shown correctly");
        Assert.assertEquals(Page.loginPage().getErrorMessage(), "Unknown user or wrong password. Please try again.");
    }

    @Test(description = "Login - Valid credential", enabled = false)
    public void loginWithValidCredential() {
        Page.loginPage()
                .inputUsername("admin")
                .inputPassword("abcd")
                .clickCaptchaCheckbox()
                .clickLoginButton();

        Allure.step("Verify if the Events page is opened successfully");
        Assert.assertEquals(Page.eventsPage().getPageTitle(), "Events");
    }
}

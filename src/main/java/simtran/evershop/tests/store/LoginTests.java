package simtran.evershop.tests.store;

import org.testng.Assert;
import org.testng.annotations.Test;
import simtran.core.base.BaseTest;
import simtran.evershop.pages.Page;

import static simtran.core.config.ConfigManager.config;

public class LoginTests extends BaseTest {

    @Test(description = "Login - Verify logging in with valid credential")
    public void verifyLoginUsingValidCredentials() {
        Page
                .openStoreUrl(target)
                .clickProfileMenu()
                .inputEmail(config(target).username())
                .inputPassword(config(target).password())
                .clickSignInButton();
        Assert.assertEquals(Page.homepage().getHeader(), "Discount 20% For All Orders Over $2000");
    }

    @Test(description = "Login - Verify logging in with invalid credentials")
    public void verifyLoginUsingInvalidCredentials() {
        Page
                .openStoreUrl(target)
                .clickProfileMenu()
                .clickSignInButton();
        softAssert.assertEquals(Page.loginPage().getEmailErrorMessage(), "This field can not be empty");
        softAssert.assertEquals(Page.loginPage().getPasswordErrorMessage(), "This field can not be empty");

        Page
                .loginPage()
                .inputEmail(faker.text().text(10))
                .inputPassword(faker.text().text(10))
                .clickSignInButton();
        softAssert.assertEquals(Page.loginPage().getEmailErrorMessage(), "Invalid email");

        Page
                .loginPage()
                .inputEmail(faker.internet().emailAddress())
                .inputPassword(faker.text().text(10))
                .clickSignInButton();
        softAssert.assertEquals(Page.loginPage().getCredentialErrorMessage(), "Invalid email or password");
        softAssert.assertAll();
    }
}

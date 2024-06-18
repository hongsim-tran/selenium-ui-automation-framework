package simtran.evershop.tests.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import simtran.evershop.pages.Page;

public class LoginStep extends StepSetup {

    @Given("User is on Login page")
    public void userIsOnLoginPage() {
        Page.openStoreUrl(target)
                .clickProfileMenu();
    }

    @When("User enter username {string} and password {string}")
    public void userEnterUsernameAndPassword(String username, String password) {
        Page.loginPage()
                .inputEmail(username)
                .inputPassword(password)
                .clickSignInButton();
    }

    @Then("Homepage is displayed")
    public void homepageIsDisplayed() {
        Assert.assertEquals(Page.homepage().getHeader(), "Discount 20% For All Orders Over $2000");
    }

    @When("User login without entering username and password")
    public void userLoginWithoutEnteringUsernameAndPassword() {
        Page.loginPage()
                .clickSignInButton();
    }

    @Then("Error messages for email and password fields are displayed")
    public void errorMessagesForEmailAndPasswordFieldsAreDisplayed() {
        softAssert.assertEquals(Page.loginPage().getEmailErrorMessage(), "This field can not be empty");
        softAssert.assertEquals(Page.loginPage().getPasswordErrorMessage(), "This field can not be empty");
        softAssert.assertAll();
    }

    @Then("Error message {string} for email field is displayed")
    public void errorMessageForEmailFieldIsDisplayed(String errorMessage) {
        Assert.assertEquals(Page.loginPage().getEmailErrorMessage(), errorMessage);
    }

    @Then("Error message {string} is displayed")
    public void errorMessageIsDisplayed(String errorMessage) {
        Assert.assertEquals(Page.loginPage().getCredentialErrorMessage(), errorMessage);
    }
}

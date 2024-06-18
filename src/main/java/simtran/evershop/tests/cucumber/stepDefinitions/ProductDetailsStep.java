package simtran.evershop.tests.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import simtran.evershop.pages.Page;

import java.util.List;

public class ProductDetailsStep extends StepSetup {
    List<String> productNames;
    List<String> productPrices;

    @When("^User clicks on product number (.+) on Homepage$")
    public void userClicksOnProductNumberIndexOnHomepage(int index) {
        productNames = Page.homepage().getProductNames();
        productPrices = Page.homepage().getProductPrices();
        Page.homepage().clickProductNameLink(index);
    }

    @Then("^Product details page number (.+) is displayed$")
    public void productDetailsPageNumberIndexIsDisplayed(int index) {
        softAssert.assertTrue(productNames.get(index).toLowerCase().contains(Page.productDetailsPage().getPagePath().toLowerCase()));
        softAssert.assertTrue(productNames.get(index).toLowerCase().contains(Page.productDetailsPage().getProductName().toLowerCase()));
        softAssert.assertTrue(productPrices.get(index).equalsIgnoreCase(Page.productDetailsPage().getProductPrice()));
    }

    @When("^User clicks on product number (.+) on the category page$")
    public void userClicksOnProductNumberIndexOnTheCategoryPage(int index) {
        productNames = Page.categoryPage().getProductNames();
        productPrices = Page.categoryPage().getProductPrices();
        Page.categoryPage().clickProductName(index);
    }
}

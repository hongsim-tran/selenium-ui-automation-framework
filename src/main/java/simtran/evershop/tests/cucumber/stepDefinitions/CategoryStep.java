package simtran.evershop.tests.cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import simtran.core.utils.CurrencyUtils;
import simtran.evershop.pages.Page;

import java.util.Collections;
import java.util.List;

import static simtran.core.config.ConfigManager.config;
import static simtran.core.config.ConfigManager.envConfig;

public class CategoryStep extends StepSetup {
    List<String> productNames;
    List<String> productPrices;
    List<Double> productPricesDouble;

    @Given("User is on Homepage")
    public void userIsOnHomepage() {
        Page.openStoreUrl(target);
    }

    @When("User clicks on category {string} button")
    public void userClicksOnCategoryButton(String category) {
        Page.homepage().clickCategoryButton(category);
    }

    @Then("User is on category {string} page")
    public void userIsOnCategoryPage(String category) {
        Assert.assertTrue(category.toLowerCase().contains(Page.categoryPage().getPagePath().toLowerCase()));
    }

    @Then("Product count displays correctly")
    public void productCountDisplaysCorrectly() {
        int productCount = Page.categoryPage().getProductCount();
        if (productCount == 0)
            Assert.assertTrue(Page.categoryPage().getNoProductsText().equalsIgnoreCase("There is no product to display"));
        else Assert.assertEquals(Page.categoryPage().getProductNames().size(), productCount);
    }


    @And("User sorts products by name in asc order")
    public void userSortsProductsByNameInAscOrder() {
        productNames = Page.categoryPage().getProductNames();
        Page
                .categoryPage()
                .selectSortByDropdown("Name")
                .clickSortArrow(false);
        wait(envConfig(target).shortTimeout());
    }

    @Then("Products are sorted by name in asc order")
    public void productsAreSortedByNameInAscOrder() {
        List<String> sortedProductsAsc = Page.categoryPage().getProductNames();
        Collections.sort(productNames);
        softAssert.assertEquals(sortedProductsAsc, productNames);
        softAssert.assertAll();
    }

    @When("User sorts products by name in desc order")
    public void userSortsProductsByNameInDescOrder() {
        Page.categoryPage().clickSortArrow(true);
        wait(envConfig(target).shortTimeout());
    }

    @Then("Products are sorted by name in desc order")
    public void productsAreSortedByNameInDescOrder() {
        List<String> sortedProductsDesc = Page.categoryPage().getProductNames();
        productNames.sort(Collections.reverseOrder());
        softAssert.assertEquals(productNames, sortedProductsDesc);
        softAssert.assertAll();
    }

    @When("User sorts products by price in asc order")
    public void userSortsProductsByPriceInAscOrder() {
        productPrices = Page.categoryPage().getProductPrices();
        Page
                .categoryPage()
                .selectSortByDropdown("Price")
                .clickSortArrow(false);
        wait(envConfig(target).shortTimeout());
    }

    @Then("Products are sorted by price in asc order")
    public void productsAreSortedByPriceInAscOrder() {
        List<Double> sortedPricesAsc = CurrencyUtils.convertCurrencyStringToDouble(Page.categoryPage().getProductPrices());
        productPricesDouble = CurrencyUtils.convertCurrencyStringToDouble(productPrices);
        Collections.sort(productPricesDouble);
        softAssert.assertEquals(productPricesDouble, sortedPricesAsc);
        softAssert.assertAll();
    }

    @When("User sorts products by price in desc order")
    public void userSortsProductsByPriceInDescOrder() {
        Page.categoryPage().clickSortArrow(true);
        wait(envConfig(target).shortTimeout());
    }

    @Then("Products are sorted by price in desc order")
    public void productsAreSortedByPriceInDescOrder() {
        List<Double> sortedPricesDesc = CurrencyUtils.convertCurrencyStringToDouble(Page.categoryPage().getProductPrices());
        productPricesDouble.sort(Collections.reverseOrder());
        softAssert.assertEquals(sortedPricesDesc, productPricesDouble);
        softAssert.assertAll();
    }
}

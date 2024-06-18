package simtran.evershop.tests.testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import simtran.core.base.BaseTest;
import simtran.core.utils.CurrencyUtils;
import simtran.evershop.pages.Page;

import java.util.Collections;
import java.util.List;

import static simtran.core.config.ConfigManager.config;
import static simtran.core.config.ConfigManager.envConfig;

public class CategoryTests extends BaseTest {

    @DataProvider
    public Object[][] category() {
        return excelReader.getTableArray("Category");
    }

    @Test(description = "Category - Verify opening categories from Homepage", dataProvider = "category")
    public void verifyOpeningCategoriesFromHomepage(String category) {
        Page
                .openStoreUrl(target)
                .clickCategoryButton(category);
        Assert.assertTrue(category.toLowerCase().contains(Page.categoryPage().getPagePath().toLowerCase()));
    }

    @Test(description = "Category - Verify product count", dataProvider = "category")
    public void verifyProductCount(String category) {
        int productCount = Page
                .openStoreUrl(target)
                .clickCategoryButton(category)
                .getProductCount();

        if (productCount == 0)
            Assert.assertTrue(Page.categoryPage().getNoProductsText().equalsIgnoreCase("There is no product to display"));
        else Assert.assertEquals(Page.categoryPage().getProductNames().size(), productCount);
    }

    @Test(description = "Category - Verify sorting products by name")
    public void verifySortingByName() {
        Page.openStoreUrl(target).clickCategoryButton("Shop women");
        List<String> productNames = Page.categoryPage().getProductNames();

        Page
                .categoryPage()
                .selectSortByDropdown("Name")
                .clickSortArrow(false);
        wait(envConfig(target).shortTimeout());
        List<String> sortedProductsAsc = Page.categoryPage().getProductNames();
        Collections.sort(productNames);
        softAssert.assertEquals(sortedProductsAsc, productNames);

        Page.categoryPage().clickSortArrow(true);
        wait(envConfig(target).shortTimeout());
        List<String> sortedProductsDesc = Page.categoryPage().getProductNames();
        productNames.sort(Collections.reverseOrder());
        softAssert.assertEquals(productNames, sortedProductsDesc);

        softAssert.assertAll();
    }

    @Test(description = "Category - Verify sorting products by price")
    public void verifySortingByPrice() {
        Page.openStoreUrl(target).clickCategoryButton("Shop women");
        List<String> productPrices = Page.categoryPage().getProductPrices();

        Page
                .categoryPage()
                .selectSortByDropdown("Price")
                .clickSortArrow(false);
        wait(envConfig(target).shortTimeout());
        List<Double> sortedPricesAsc = CurrencyUtils.convertCurrencyStringToDouble(Page.categoryPage().getProductPrices());
        List<Double> productPricesDouble = CurrencyUtils.convertCurrencyStringToDouble(productPrices);
        Collections.sort(productPricesDouble);
        softAssert.assertEquals(productPricesDouble, sortedPricesAsc);

        Page.categoryPage().clickSortArrow(true);
        wait(envConfig(target).shortTimeout());
        List<Double> sortedPricesDesc = CurrencyUtils.convertCurrencyStringToDouble(Page.categoryPage().getProductPrices());
        productPricesDouble.sort(Collections.reverseOrder());
        softAssert.assertEquals(sortedPricesDesc, productPricesDouble);

        softAssert.assertAll();
    }
}

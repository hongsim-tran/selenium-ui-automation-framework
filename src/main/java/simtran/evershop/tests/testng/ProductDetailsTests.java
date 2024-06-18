package simtran.evershop.tests.testng;

import org.testng.annotations.Test;
import simtran.core.base.BaseTest;
import simtran.evershop.pages.Page;

import java.util.List;

public class ProductDetailsTests extends BaseTest {

    @Test(description = "Product Details - Verify opening product details page from Homepage")
    public void verifyOpeningProductDetailsPageFromHomepage() {
        Page.openStoreUrl(target);
        List<String> productNames = Page.homepage().getProductNames();
        List<String> productPrices = Page.homepage().getProductPrices();
        for (int i = 0; i < 4; i++) {
            Page.homepage().clickProductNameLink(i);
            softAssert.assertTrue(productNames.get(i).toLowerCase().contains(Page.productDetailsPage().getPagePath().toLowerCase()));
            softAssert.assertTrue(productNames.get(i).toLowerCase().contains(Page.productDetailsPage().getProductName().toLowerCase()));
            softAssert.assertTrue(productPrices.get(i).equalsIgnoreCase(Page.productDetailsPage().getProductPrice()));
            Page.productDetailsPage().clickLogoLink();
        }
        softAssert.assertAll();
    }

    @Test(description = "Product Details - Verify opening product details page from Category page")
    public void verifyOpeningProductDetailsPageFromCategoryPage() {
        Page.openStoreUrl(target).clickCategoryButton("Shop men");
        int productCount = Page.categoryPage().getProductCount();
        for (int i = 0; i < productCount; i++) {
            String productName = Page.categoryPage().getProductName(i);
            String productPrice = Page.categoryPage().getProductPrice(i);
            Page.categoryPage().clickProductName(i);
            softAssert.assertTrue(productName.toLowerCase().contains(Page.productDetailsPage().getPagePath().toLowerCase()));
            softAssert.assertTrue(productName.toLowerCase().contains(Page.productDetailsPage().getProductName().toLowerCase()));
            softAssert.assertEquals(productPrice, Page.productDetailsPage().getProductPrice());
            Page.productDetailsPage().clickLogoLink().clickCategoryButton("Shop men");
        }
        softAssert.assertAll();
    }
}

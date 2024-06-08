package simtran.evershop.pages.store;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.core.utils.MyLogger;
import simtran.evershop.pages.Page;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends Navigation {

    //**************** Page's locators ****************
    private final By ddlSortBy = By.cssSelector("[class*='product-sorting-inner'] select");
    private final By imgSortArrow = By.cssSelector("[class*='sort-direction'] a svg");
    private final By lnkProductThumbnailList = By.cssSelector(".product-thumbnail-listing a");
    private final By lnkProductNameList = By.cssSelector("[class*='product-name'] a");
    private final By txtProductPriceList = By.cssSelector(".product-price-listing span");
    private final By txtProductCount = By.cssSelector("[class*='product-count']");
    private final By txtNoProducts = By.cssSelector(".product-list");

    //**************** Page's attributes ****************
    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement elmProduct : findElements(lnkProductNameList)) {
            productNames.add(getText(elmProduct));
        }
        return productNames;
    }

    public String getProductName(int index) {
        return getText(findElements(lnkProductNameList).get(index));
    }

    public List<String> getProductPrices() {
        List<String> productPrices = new ArrayList<>();
        for (WebElement elmPrice : findElements(txtProductPriceList)) {
            productPrices.add(getText(elmPrice));
        }
        return productPrices;
    }

    public String getProductPrice(int index) {
        return getText(findElements(txtProductPriceList).get(index));
    }

    public int getProductCount() {
        return Integer.parseInt(getText(txtProductCount).split(" ")[0]);
    }

    public String getNoProductsText() {
        return getText(txtNoProducts);
    }

    //**************** Page's actions ****************
    @Step
    public CategoryPage selectSortByDropdown(String option) {
        selectDropdown(ddlSortBy, option);
        return this;
    }

    @Step
    public CategoryPage clickSortArrow(boolean isUp) {
        if (isUp) {
            if (getAttribute(imgSortArrow, "class").contains("arrow-down"))
                click(imgSortArrow);
        } else {
            if (getAttribute(imgSortArrow, "class").contains("arrow-up"))
                click(imgSortArrow);
        }
        return this;
    }

    @Step
    public ProductDetailsPage clickProductThumbnail(int index) {
        click(findElements(lnkProductThumbnailList).get(index));
        return Page.productDetailsPage();
    }

    @Step
    public ProductDetailsPage clickProductName(int index) {
        click(findElements(lnkProductNameList).get(index));
        return Page.productDetailsPage();
    }

    @Step
    public ProductDetailsPage clickProductName(String productName) {
        boolean isClicked = false;
        for (WebElement elmProduct : findElements(lnkProductNameList)) {
            if (getText(elmProduct).equalsIgnoreCase(productName)) {
                click(elmProduct);
                isClicked = true;
            }
        }
        if (!isClicked)
            MyLogger.info(String.format("The product %s not found.", productName));
        return Page.productDetailsPage();
    }
}

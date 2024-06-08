package simtran.evershop.pages.store;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.core.utils.MyLogger;
import simtran.evershop.pages.Page;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends Navigation {

    //**************** Page's locators ****************
    private final By txtHeader = By.cssSelector(".h1");
    private final By btnCategoryList = By.xpath("//a[@class='button primary']");
    private final By lnkProductThumbnailList = By.cssSelector(".product-thumbnail-listing a");
    private final By lnkProductNameList = By.cssSelector(".product-name a");
    private final By txtProductPriceList = By.cssSelector(".sale-price");

    //**************** Page's attributes ****************
    public String getHeader() {
        return getText(txtHeader);
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement elmProductName : findElements(lnkProductNameList)) {
            productNames.add(getText(elmProductName));
        }
        return productNames;
    }

    public String getProductName(int index) {
        return getText(findElements(lnkProductNameList).get(index));
    }

    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();
        for (WebElement elmPrice : findElements(txtProductPriceList))
            prices.add(getText(elmPrice));
        return prices;
    }

    public String getProductPrice(int index) {
        return getText(findElements(txtProductPriceList).get(index));
    }

    //**************** Page's actions ****************
    @Step
    public CategoryPage clickCategoryButton(int index) {
        click(findElements(btnCategoryList).get(index));
        return Page.categoryPage();
    }

    @Step
    public CategoryPage clickCategoryButton(String categoryName) {
        boolean clicked = false;
        for (WebElement elmCategory : findElements(btnCategoryList)) {
            if (getText(elmCategory).equalsIgnoreCase(categoryName)) {
                click(elmCategory);
                clicked = true;
                break;
            }
        }
        if (!clicked)
            MyLogger.info(String.format("The category %s not found.", categoryName));
        return Page.categoryPage();
    }

    @Step
    public ProductDetailsPage clickProductThumbnailLink(int index) {
        click(findElements(lnkProductThumbnailList).get(index));
        return Page.productDetailsPage();
    }

    @Step
    public ProductDetailsPage clickProductNameLink(int index) {
        click(findElements(lnkProductNameList).get(index));
        return Page.productDetailsPage();
    }

    @Step
    public ProductDetailsPage clickProductNameLink(String productName) {
        boolean clicked = false;
        for (WebElement elmProduct : findElements(lnkProductNameList)) {
            if (getText(elmProduct).equalsIgnoreCase(productName)) {
                click(elmProduct);
                clicked = true;
            }
        }
        if (!clicked)
            MyLogger.info(String.format("The product name %s not found.", productName));
        return Page.productDetailsPage();
    }
}

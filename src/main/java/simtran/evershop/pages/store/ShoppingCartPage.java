package simtran.evershop.pages.store;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.core.utils.MyLogger;
import simtran.evershop.pages.Page;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends Navigation {

    //**************** Page's locators ****************
    private final By pnlCartItemList = By.cssSelector(".cart-tem-info");
    private final By lnkProductNameList = By.xpath("//a[contains(@class,'name')]");
    private final By txtProductVariantChildren = By.xpath(".//*[contains(@class,'cart-item-variant-options')]//span[@class='attribute-name']/following-sibling::span");
    private final By lnkRemoveList = By.xpath("//*[@class='cart-tem-info']//span[text()='Remove']");
    private final By txtPriceList = By.cssSelector(".sale-price");
    private final By txbCoupon = By.xpath("//input[@name='coupon']");
    private final By btnApply = By.xpath("//button/span[text()='Apply']");
    private final By txtSubTotalPrice = By.xpath("//*[@class='summary']//div[normalize-space()='Sub total']/following-sibling::div");
    private final By txtTotalPrice = By.xpath("//*[contains(@class,'summary-row')]//*[@class='grand-total-value']");
    private final By btnCheckout = By.xpath("//*[contains(@class,'shopping-cart-checkout-btn')]/a");
    private final By txtEmptyCart = By.xpath("//span[normalize-space()='Your cart is empty!']");

    //**************** Page's attributes ****************
    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement elmProductName : findElements(lnkProductNameList)) {
            productNames.add(getText(elmProductName));
        }
        return productNames;
    }

    public String getProductName(int index) {
        List<WebElement> elmProductNames = findElements(lnkProductNameList);
        return getText(elmProductNames.get(index));
    }

    public int getNumberOfVariants(int index){
        List<WebElement> elmCartItems = findElements(pnlCartItemList);
        try {
            List<WebElement> elmVariants = findChildrenElements(txtProductVariantChildren, elmCartItems.get(index));
            return elmVariants.size();
        } catch (Exception e){
            return 0;
        }
    }

    public List<String> getVariants(int index) {
        List<String> variants = new ArrayList<>();
        List<WebElement> elmCartItems = findElements(pnlCartItemList);
        for (WebElement elmVariant : findChildrenElements(txtProductVariantChildren, elmCartItems.get(index))) {
            variants.add(getText(elmVariant));
        }
        return variants;
    }

    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();
        for (WebElement elmPrice : findElements(txtPriceList)) {
            prices.add(getText(elmPrice));
        }
        return prices;
    }

    public String getProductPrice(int index) {
        return getText(findElements(txtPriceList).get(index));
    }

    public int getNumberOfProducts(){
        if (isVisible(pnlCartItemList))
            return findElements(pnlCartItemList).size();
        else return 0;
    }

    public String getSubTotalPrice() {
        return getText(txtSubTotalPrice);
    }

    public String getTotalPrice() {
        return getText(txtTotalPrice);
    }

    public boolean isEmpty(){
        return isVisible(txtEmptyCart);
    }

    //**************** Page's actions ****************
    @Step
    public ProductDetailsPage clickProductName(int index) {
        click(findElements(lnkProductNameList).get(index));
        return Page.productDetailsPage();
    }

    @Step
    public ProductDetailsPage clickProductName(String productName) {
        boolean isClicked = false;
        for (WebElement elmProductName : findElements(lnkProductNameList)) {
            if (getText(elmProductName).contains(productName)) {
                click(elmProductName);
                isClicked = true;
            }
        }
        if (!isClicked)
            MyLogger.info(String.format("The product %s not found", productName));
        return Page.productDetailsPage();
    }

    @Step
    public ShoppingCartPage clickRemoveLink(int index) {
        click(findElements(lnkRemoveList).get(index));
        return this;
    }

    @Step
    public ShoppingCartPage inputCoupon(String couponValue) {
        enterText(txbCoupon, couponValue);
        return this;
    }

    @Step
    public ShoppingCartPage clickApplyButton() {
        click(btnApply);
        return this;
    }

    @Step
    public void clickCheckoutButton() {
        // Todo
        // Update return value later
        click(btnCheckout);
    }
}

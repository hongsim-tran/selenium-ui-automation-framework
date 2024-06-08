package simtran.evershop.pages.store;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.core.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends Navigation {

    //**************** Page's locators ****************
    private final By txtProductName = By.cssSelector("h1[class='product-single-name']");
    private final By txtProductPrice = By.cssSelector("h4[class='product-single-price']");
    private final By txtSku = By.cssSelector("[class*='product-single-sku']");
    private final By txtSelectedVariantList = By.cssSelector(".list-disc.list-inside li span");
    private final By txbQty = By.cssSelector("input[placeholder='Qty']");
    private final By txtQtyErrorMessage = By.cssSelector(".add-to-cart .text-critical");
    private final By btnAddToCart = By.xpath("//button[@type='button']/span[contains(.,'ADD TO CART')]");
    private final By btnVariantBlockList = By.cssSelector("ul[class*='variant-option-list']");
    private final By btnVariantOptionList = By.xpath(".//li");
    private final By txtVariantErrorMessage = By.cssSelector(".variant-validate.error");
    private final By txtDescription = By.cssSelector(".product-description");

    //**************** Page's attributes ****************
    public String getProductName() {
        return getText(txtProductName);
    }

    public String getProductPrice() {
        return getText(txtProductPrice);
    }

    public String getSku() {
        return getText(txtSku).split(":")[1].trim();
    }

    public List<String> getSelectedVariantsText() {
        List<String> selectVariants = new ArrayList<>();
        for (WebElement elmVariant : findElements(txtSelectedVariantList)) {
            selectVariants.add(getText(elmVariant));
        }
        return selectVariants;
    }

    public boolean isQtyErrorMessageVisible(){
        return isVisible(txtQtyErrorMessage);
    }

    public int getNumberOfVariants() {
        if (isVisible(btnVariantBlockList))
            return findElements(btnVariantBlockList).size();
        else return 0;
    }

    public String getSelectedVariantButton(int index){
        WebElement elmVariantBlock = findElements(btnVariantBlockList).get(index);
        for (WebElement elmVariant: findChildrenElements(btnVariantOptionList, elmVariantBlock)){
            if (getAttribute(elmVariant, "class").equalsIgnoreCase("selected")) {
                return getText(elmVariant);
            }
        }
        return null;
    }

    public List<String> getSelectedVariantButtons() {
        List<String> selectVariants = new ArrayList<>();
        for (WebElement elmVariantBlock : findElements(btnVariantBlockList)) {
            for (WebElement elmVariant : findChildrenElements(btnVariantOptionList, elmVariantBlock)) {
                if (getAttribute(elmVariant, "class").equalsIgnoreCase("selected")) {
                    selectVariants.add(getText(elmVariant));
                }
            }
        }
        return selectVariants;
    }

    public String getVariantErrorMessage() {
        return getText(txtVariantErrorMessage);
    }

    public String getDescription() {
        return getText(txtDescription);
    }

    //**************** Page's actions ****************
    @Step
    public ProductDetailsPage inputQty(String qty) {
        refresh();
        enterText(txbQty, qty);
        return this;
    }

    @Step
    public ProductDetailsPage clickAddToCartButton() {
        click(btnAddToCart);
        return this;
    }

    @Step
    public ProductDetailsPage clickVariantButton(int index, String variant) {
        List<WebElement> elmVariants = findChildrenElements(btnVariantOptionList, findElements(btnVariantBlockList).get(index));
        boolean isClicked = false;
        for (WebElement elmVariant : elmVariants) {
            if (getText(elmVariant).equalsIgnoreCase(variant)) {
                if (!getAttribute(elmVariant, "class").equalsIgnoreCase("un-available")) {
                    click(elmVariant);
                    isClicked = true;
                    break;
                } else MyLogger.info(String.format("The variant button %s is unable to be clicked", variant));
            }
        }
        if (!isClicked)
            MyLogger.info(String.format("The variant %s not found", variant));
        return this;
    }

    @Step
    public ProductDetailsPage clickRandomVariantButton(int index) {
        List<WebElement> elmVariants = findChildrenElements(btnVariantOptionList, findElements(btnVariantBlockList).get(index));
        boolean isClicked = false;
        while (!isClicked) {
            int i = new Faker().number().numberBetween(0, elmVariants.size());
            if (!getAttribute(elmVariants.get(i), "class").equalsIgnoreCase("un-available")) {
                click(elmVariants.get(i));
                isClicked = true;
            }
        }
        return this;
    }
}

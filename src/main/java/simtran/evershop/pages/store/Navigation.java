package simtran.evershop.pages.store;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import simtran.core.base.BasePage;
import simtran.evershop.pages.Page;

public class Navigation extends BasePage {

    //**************** Page's locators ****************
    private final By lnkLogo = By.cssSelector("a[class='logo-icon']");
    private final By mnuSearch = By.cssSelector("a[class='search-icon']");
    private final By mnuShoppingCart = By.cssSelector("a[class='mini-cart-icon']");
    private final By mnuProfile = By.xpath("//div/a[@href='/account/login']");
    private final By txtPagePath = By.xpath("(//a[text()='Home']/ancestor::span/following-sibling::span)[last()]");

    //**************** Page's attributes ****************
    public String getPagePath() {
        return getText(txtPagePath);
    }

    //**************** Page's actions ****************
    @Step
    public Homepage clickLogoLink() {
        click(lnkLogo);
        return Page.homepage();
    }

    @Step
    public void clickSearchMenu() {
        // Todo
        click(mnuSearch);
    }

    @Step
    public ShoppingCartPage clickShoppingCartMenu() {
        click(mnuShoppingCart);
        return Page.shoppingCartPage();
    }

    @Step
    public LoginPage clickProfileMenu() {
        click(mnuProfile);
        return Page.loginPage();
    }
}

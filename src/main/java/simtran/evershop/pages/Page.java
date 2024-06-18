package simtran.evershop.pages;

import simtran.core.base.BasePage;
import simtran.core.utils.MyLogger;
import simtran.core.wdm.DriverManager;
import simtran.evershop.pages.store.*;

import static simtran.core.config.ConfigManager.config;
import static simtran.core.config.ConfigManager.envConfig;

public class Page {

    private static <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            MyLogger.error(e.getMessage());
        }
        return null;
    }

    //**************** Store's pages ****************
    public static Homepage openStoreUrl(String target) {
        MyLogger.debug("Open the store base page at " + envConfig(target).baseUrl());
        DriverManager.getDriver().get(envConfig(target).baseUrl());
        return Page.homepage();
    }

    public static Homepage homepage() {
        return getInstance(Homepage.class);
    }

    public static LoginPage loginPage() {
        return getInstance(LoginPage.class);
    }

    public static Navigation navigation() {
        return getInstance(Navigation.class);
    }

    public static CategoryPage categoryPage() {
        return getInstance(CategoryPage.class);
    }

    public static ProductDetailsPage productDetailsPage() {
        return getInstance(ProductDetailsPage.class);
    }

    public static ShoppingCartPage shoppingCartPage() {
        return getInstance(ShoppingCartPage.class);
    }
}

package simtran.evershop.tests.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import simtran.core.base.BaseTest;
import simtran.core.utils.CurrencyUtils;
import simtran.core.utils.DBConnection;
import simtran.core.utils.MyLogger;
import simtran.evershop.datafactory.CouponDataFactory;
import simtran.evershop.dbqueries.Queries;
import simtran.evershop.models.NewCouponModel;
import simtran.evershop.pages.Page;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static simtran.core.config.ConfigManager.config;
import static simtran.core.config.ConfigManager.envConfig;

public class ShoppingCartTests extends BaseTest {

    private int getProductQty(String sku) throws SQLException {
        int qty = 0;
        DBConnection.getConnection(target);
        var rs = DBConnection.executeQuery(Queries.selectProductQtyWithSku(sku));
        while (true){
            assert rs != null;
            if (!rs.next()) break;
            qty = rs.getInt("qty");
        }
        DBConnection.closeConnection();
        MyLogger.debug(String.format("Quantity value of the product sku %s is %d", sku, qty));
        return qty;
    }

    private void deleteAllCoupons(){
        DBConnection.getConnection(target);
        DBConnection.executeUpdate(Queries.deleteAllCoupons());
        DBConnection.closeConnection();
    }

    private void insertNewCoupon(String couponCode, String description, double discountAmount, String discountType){
        DBConnection.getConnection(target);
        DBConnection.executeUpdate(Queries.insertCoupon(couponCode, description, discountAmount, discountType));
        DBConnection.closeConnection();
    }

    @Test(description = "Shopping Cart - Verify adding multiple products to shopping cart")
    public void verifyAddingMultipleProductsToCart() throws SQLException {
        Page.openStoreUrl(target);
        int numOfAddedProducts = faker.number().numberBetween(1, 10);
        List<HashMap<String, String>> addedProductAttributes = new ArrayList<>();

        MyLogger.debug(String.format("Start adding %s products to shopping cart", numOfAddedProducts));
        for (int i = 0; i < numOfAddedProducts; i++){
            MyLogger.debug(String.format("Add product number %s to shopping cart", i + 1));
            HashMap<String, String> productAttribute = new HashMap<>();
            int productIndex = faker.number().numberBetween(0, 4);
            productAttribute.put("product-name", Page.homepage().getProductName(productIndex));
            productAttribute.put("product-price", Page.homepage().getProductPrice(productIndex));

            Page.homepage().clickProductNameLink(productIndex);
            if (Page.productDetailsPage().getNumberOfVariants() > 0){
                for (int j = 0; j < Page.productDetailsPage().getNumberOfVariants(); j++){
                    Page.productDetailsPage().clickRandomVariantButton(j);
                    wait(envConfig(target).shortTimeout());
                    productAttribute.put(String.format("variant-%s", j), Page.productDetailsPage().getSelectedVariantButton(j).toLowerCase());
                }
            }
            String sku = Page.productDetailsPage().getSku();
            int qty = faker.number().numberBetween(1, getProductQty(sku));
            boolean isAdded = false;
            while (true){
                Page.productDetailsPage().inputQty(String.valueOf(qty)).clickAddToCartButton();
                wait(envConfig(target).shortTimeout());
                if (Page.productDetailsPage().isQtyErrorMessageVisible()){
                    if (qty == 1)
                        break;
                    else qty = faker.number().numberBetween(1, qty);
                } else{
                    isAdded = true;
                    break;
                }
            }
            if (isAdded && !addedProductAttributes.contains(productAttribute))
                addedProductAttributes.add(productAttribute);
            Page.productDetailsPage().clickLogoLink();
        }
        Page.homepage().clickShoppingCartMenu();

        List<HashMap<String, String>> cartProductAttributes = new ArrayList<>();
        if (Page.shoppingCartPage().getNumberOfProducts() > 0){
            for (int i = 0; i < Page.shoppingCartPage().getNumberOfProducts(); i++){
                HashMap<String, String> cartProductAttribute = new HashMap<>();
                cartProductAttribute.put("product-name", Page.shoppingCartPage().getProductName(i));
                cartProductAttribute.put("product-price", Page.shoppingCartPage().getProductPrice(i));
                int j = 0;
                for (String variant : Page.shoppingCartPage().getVariants(i)){
                    cartProductAttribute.put(String.format("variant-%s", j), variant.toLowerCase());
                    j++;
                }
                cartProductAttributes.add(cartProductAttribute);
            }
        }

        for (HashMap<String, String> product: addedProductAttributes){
            softAssert.assertTrue(cartProductAttributes.contains(product));
        }
        for (HashMap<String, String> product: cartProductAttributes){
            softAssert.assertTrue(addedProductAttributes.contains(product));
        }
        softAssert.assertAll();
    }

    @Test(description = "Shopping Cart - Verify remove a product from shopping cart")
    public void verifyRemovingProductFromCart() throws SQLException {
        Page.openStoreUrl(target);
        int numOfAddedProducts = faker.number().numberBetween(1, 10);
        List<Map<String, String>> addedProductAttributes = new CopyOnWriteArrayList<>();

        MyLogger.debug(String.format("Start adding %s products to shopping cart", numOfAddedProducts));
        for (int i = 0; i < numOfAddedProducts; i++){
            MyLogger.debug(String.format("Add product number %s to shopping cart", i + 1));
            Map<String, String> productAttribute = new ConcurrentHashMap<>();
            int productIndex = faker.number().numberBetween(0, 4);
            productAttribute.put("product-name", Page.homepage().getProductName(productIndex));
            productAttribute.put("product-price", Page.homepage().getProductPrice(productIndex));

            Page.homepage().clickProductNameLink(productIndex);
            if (Page.productDetailsPage().getNumberOfVariants() > 0){
                for (int j = 0; j < Page.productDetailsPage().getNumberOfVariants(); j++){
                    Page.productDetailsPage().clickRandomVariantButton(j);
                    wait(envConfig(target).shortTimeout());
                    productAttribute.put(String.format("variant-%s", j), Page.productDetailsPage().getSelectedVariantButton(j).toLowerCase());
                }
            }
            String sku = Page.productDetailsPage().getSku();
            int qty = faker.number().numberBetween(1, getProductQty(sku));
            boolean isAdded = false;
            while (true){
                Page.productDetailsPage().inputQty(String.valueOf(qty)).clickAddToCartButton();
                wait(envConfig(target).shortTimeout());
                if (Page.productDetailsPage().isQtyErrorMessageVisible()){
                    if (qty == 1)
                        break;
                    else qty = faker.number().numberBetween(1, qty);
                } else{
                    isAdded = true;
                    break;
                }
            }
            if (isAdded && !addedProductAttributes.contains(productAttribute))
                addedProductAttributes.add(productAttribute);
            Page.productDetailsPage().clickLogoLink();
        }

        Page.homepage().clickShoppingCartMenu();
        wait(envConfig(target).shortTimeout());
        HashMap<String, String> firstProductAttribute = new HashMap<>();
        firstProductAttribute.put("product-name", Page.shoppingCartPage().getProductName(0));
        firstProductAttribute.put("product-price", Page.shoppingCartPage().getProductPrice(0));
        if (Page.shoppingCartPage().getNumberOfVariants(0) > 0){
            for (int j = 0; j < Page.shoppingCartPage().getNumberOfVariants(0); j++){
                firstProductAttribute.put(String.format("variant-%s", j), Page.shoppingCartPage().getVariants(0).get(j).toLowerCase());
            }
        }

        int numOfShoppingCartProducts = Page.shoppingCartPage().getNumberOfProducts();

        addedProductAttributes.removeIf(product -> product.equals(firstProductAttribute));
        Page.shoppingCartPage().clickRemoveLink(0);
        wait(envConfig(target).shortTimeout());


        List<Map<String, String>> cartProductAttributes = new CopyOnWriteArrayList<>();
        if (numOfShoppingCartProducts == 1)
            softAssert.assertTrue(Page.shoppingCartPage().isEmpty());
        else {
            for (int i = 0; i < Page.shoppingCartPage().getNumberOfProducts(); i++){
                Map<String, String> cartProductAttribute = new ConcurrentHashMap<>();
                cartProductAttribute.put("product-name", Page.shoppingCartPage().getProductName(i));
                cartProductAttribute.put("product-price", Page.shoppingCartPage().getProductPrice(i));
                if (Page.shoppingCartPage().getNumberOfVariants(i) > 0){
                    for (int j = 0; j < Page.shoppingCartPage().getNumberOfVariants(i); j++){
                        cartProductAttribute.put(String.format("variant-%s", j), Page.shoppingCartPage().getVariants(i).get(j).toLowerCase());
                    }
                }
                cartProductAttributes.add(cartProductAttribute);
            }

           for (Map<String, String> addedProduct: addedProductAttributes){
               cartProductAttributes.removeIf(cartProduct -> cartProduct.equals(addedProduct));
               addedProductAttributes.remove(addedProduct);
           }
           softAssert.assertTrue(addedProductAttributes.isEmpty());
           softAssert.assertTrue(cartProductAttributes.isEmpty());
        }
        softAssert.assertAll();
    }

    @Test(description = "Shopping Cart - Verify subtotal and total calculation")
    public void verifySubtotalTotalValue() throws SQLException {
        Page.openStoreUrl(target);
        int numOfAddedProducts = faker.number().numberBetween(1, 10);
        List<Map<String, Double>> addedProductAttributes = new ArrayList<>();

        MyLogger.debug(String.format("Start adding %s products to shopping cart", numOfAddedProducts));
        for (int i = 0; i < numOfAddedProducts; i++){
            MyLogger.debug(String.format("Add product number %s to shopping cart", i + 1));
            Map<String, Double> productAttribute = new HashMap<>();
            int productIndex = faker.number().numberBetween(0, 4);
            productAttribute.put("product-price", CurrencyUtils.convertCurrencyStringToDouble(Page.homepage().getProductPrice(productIndex)));

            Page.homepage().clickProductNameLink(productIndex);
            if (Page.productDetailsPage().getNumberOfVariants() > 0){
                for (int j = 0; j < Page.productDetailsPage().getNumberOfVariants(); j++){
                    Page.productDetailsPage().clickRandomVariantButton(j);
                    wait(envConfig(target).shortTimeout());
                }
            }
            String sku = Page.productDetailsPage().getSku();
            int qty = faker.number().numberBetween(1, getProductQty(sku));
            boolean isAdded = false;
            while (true){
                Page.productDetailsPage().inputQty(String.valueOf(qty)).clickAddToCartButton();
                wait(envConfig(target).shortTimeout());
                if (Page.productDetailsPage().isQtyErrorMessageVisible()){
                    if (qty == 1)
                        break;
                    else qty = faker.number().numberBetween(1, qty);
                } else{
                    isAdded = true;
                    productAttribute.put("product-qty", (double) qty);
                    break;
                }
            }
            if (isAdded){
                addedProductAttributes.add(productAttribute);
            }
            Page.productDetailsPage().clickLogoLink();
        }

        Double expectedTotal = 0.0;
        for (Map<String, Double> productAttribute : addedProductAttributes){
            expectedTotal = expectedTotal + productAttribute.get("product-price") * productAttribute.get("product-qty");
        }

        Page.homepage().clickShoppingCartMenu();
        Assert.assertEquals(expectedTotal, CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice()));
    }

    @Test(description = "Shopping Cart - Verify applying a coupon")
    public void verifyApplyCoupon() throws SQLException {
        // Prepare test data from database
        deleteAllCoupons();
        NewCouponModel newCouponFixedDiscount = CouponDataFactory.generateValidCouponData(NewCouponModel.DiscountType.FIXED_DISCOUNT_ENTIRE_ORDER);
        NewCouponModel newCouponPercentageDiscount = CouponDataFactory.generateValidCouponData(NewCouponModel.DiscountType.PERCENTAGE_DISCOUNT_ENTIRE_ORDER);
        insertNewCoupon(newCouponFixedDiscount.getCouponCode(), newCouponFixedDiscount.getDescription(), newCouponFixedDiscount.getDiscountAmount(), newCouponFixedDiscount.getDiscountType());
        insertNewCoupon(newCouponPercentageDiscount.getCouponCode(), newCouponPercentageDiscount.getDescription(), newCouponPercentageDiscount.getDiscountAmount(), newCouponPercentageDiscount.getDiscountType());

        Page.openStoreUrl(target);
        int numOfAddedProducts = faker.number().numberBetween(1, 10);
        MyLogger.debug(String.format("Start adding %s products to shopping cart", numOfAddedProducts));
        for (int i = 0; i < numOfAddedProducts; i++){
            MyLogger.debug(String.format("Add product number %s to shopping cart", i + 1));
            int productIndex = faker.number().numberBetween(0, 4);
            Page.homepage().clickProductNameLink(productIndex);
            if (Page.productDetailsPage().getNumberOfVariants() > 0){
                for (int j = 0; j < Page.productDetailsPage().getNumberOfVariants(); j++){
                    Page.productDetailsPage().clickRandomVariantButton(j);
                    wait(envConfig(target).shortTimeout());
                }
            }
            String sku = Page.productDetailsPage().getSku();
            int qty = faker.number().numberBetween(1, getProductQty(sku));
            while (true){
                Page.productDetailsPage().inputQty(String.valueOf(qty)).clickAddToCartButton();
                wait(envConfig(target).shortTimeout());
                if (Page.productDetailsPage().isQtyErrorMessageVisible()){
                    if (qty == 1)
                        break;
                    else qty = faker.number().numberBetween(1, qty);
                } else{
                    break;
                }
            }

            Page.productDetailsPage().clickLogoLink();
        }

        // Apply a coupon which is fixed discount to entire order
        Page
                .homepage()
                .clickShoppingCartMenu()
                .inputCoupon(newCouponFixedDiscount.getCouponCode())
                .clickApplyButton();
        wait(envConfig(target).shortTimeout());

        double expectedTotal = (CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice()) - newCouponFixedDiscount.getDiscountAmount());
        softAssert.assertEquals(CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getTotalPrice()), expectedTotal);

        // Apply a coupon which is percentage discount to entire order
        Page
                .shoppingCartPage()
                .inputCoupon(newCouponPercentageDiscount.getCouponCode())
                .clickApplyButton();
        wait(envConfig(target).shortTimeout());

        expectedTotal = Math.round(((CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice())
                - CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice()) * newCouponPercentageDiscount.getDiscountAmount() * 0.01) * 100) / 100);
        softAssert.assertEquals(CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getTotalPrice()), expectedTotal);
        softAssert.assertAll();
    }
}

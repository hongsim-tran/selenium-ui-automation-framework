package simtran.evershop.tests.cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import simtran.core.utils.CurrencyUtils;
import simtran.core.utils.DBConnection;
import simtran.core.utils.MyLogger;
import simtran.evershop.datafactory.CouponDataFactory;
import simtran.evershop.dbqueries.Queries;
import simtran.evershop.models.NewCouponModel;
import simtran.evershop.pages.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static simtran.core.config.ConfigManager.config;

public class ShoppingCartStep extends StepSetup {
    List<ConcurrentHashMap<String, String>> addedProducts;
    ConcurrentHashMap<String, String> firstProduct;
    int numOfShoppingCartProducts;
    NewCouponModel newCoupon;

    private int getProductQty(String sku) throws SQLException {
        int qty = 0;
        var rs = DBConnection.executeQuery(Queries.selectProductQtyWithSku(sku));
        while (true){
            assert rs != null;
            if (!rs.next()) break;
            qty = rs.getInt("qty");
        }
        MyLogger.debug(String.format("Quantity value of the product sku %s is %d", sku, qty));
        return qty;
    }

    @When("^User adds (.+) products to the shopping cart$")
    public void userAddsProductsToTheShoppingCart(int numberOfProducts) throws SQLException {
        addedProducts = new CopyOnWriteArrayList<>();
        for (int i = 0; i < numberOfProducts; i++){
            MyLogger.debug(String.format("Add product number %s to shopping cart", i + 1));
            ConcurrentHashMap<String, String> product = new ConcurrentHashMap<>();
            int productIndex = faker.number().numberBetween(0, 4);
            product.put("product-name", Page.homepage().getProductName(productIndex));
            product.put("product-price", Page.homepage().getProductPrice(productIndex));

            Page.homepage().clickProductNameLink(productIndex);
            if (Page.productDetailsPage().getNumberOfVariants() > 0){
                for (int j = 0; j < Page.productDetailsPage().getNumberOfVariants(); j++){
                    Page.productDetailsPage().clickRandomVariantButton(j);
                    wait(config(target).shortTimeout());
                    product.put(String.format("variant-%s", j), Page.productDetailsPage().getSelectedVariantButton(j).toLowerCase());
                }
            }
            String sku = Page.productDetailsPage().getSku();
            int qty = faker.number().numberBetween(1, getProductQty(sku));
            boolean isAdded = false;
            while (true){
                Page.productDetailsPage().inputQty(String.valueOf(qty)).clickAddToCartButton();
                wait(config(target).shortTimeout());
                if (Page.productDetailsPage().isQtyErrorMessageVisible()){
                    if (qty == 1)
                        break;
                    else qty = faker.number().numberBetween(1, qty);
                } else{
                    isAdded = true;
                    break;
                }
            }
            if (isAdded && !addedProducts.contains(product)){
                product.put("qty", String.valueOf(qty));
                addedProducts.add(product);
            }
            Page.productDetailsPage().clickLogoLink();
        }
        Page.homepage().clickShoppingCartMenu();
    }

    @Then("The added products are present in the shopping cart")
    public void theAddedProductsArePresentInTheShoppingCart() {
        for(ConcurrentHashMap<String, String> product: addedProducts){
            product.remove("qty");
        }
        List<ConcurrentHashMap<String, String>> cartProducts = new CopyOnWriteArrayList<>();
        if (Page.shoppingCartPage().getNumberOfProducts() > 0){
            for (int i = 0; i < Page.shoppingCartPage().getNumberOfProducts(); i++){
                ConcurrentHashMap<String, String> cartProduct = new ConcurrentHashMap<>();
                cartProduct.put("product-name", Page.shoppingCartPage().getProductName(i));
                cartProduct.put("product-price", Page.shoppingCartPage().getProductPrice(i));
                int j = 0;
                for (String variant : Page.shoppingCartPage().getVariants(i)){
                    cartProduct.put(String.format("variant-%s", j), variant.toLowerCase());
                    j++;
                }
                cartProducts.add(cartProduct);
            }
        }
        for (ConcurrentHashMap<String, String> product: addedProducts){
            softAssert.assertTrue(cartProducts.contains(product));
        }
        for (ConcurrentHashMap<String, String> product: cartProducts){
            softAssert.assertTrue(addedProducts.contains(product));
        }
    }

    @And("User removes a product from shopping cart")
    public void userRemovesAProductFromShoppingCart() {
        firstProduct = new ConcurrentHashMap<>();
        firstProduct.put("product-name", Page.shoppingCartPage().getProductName(0));
        firstProduct.put("product-price", Page.shoppingCartPage().getProductPrice(0));
        if (Page.shoppingCartPage().getNumberOfVariants(0) > 0){
            for (int j = 0; j < Page.shoppingCartPage().getNumberOfVariants(0); j++){
                firstProduct.put(String.format("variant-%s", j), Page.shoppingCartPage().getVariants(0).get(j).toLowerCase());
            }
        }

        numOfShoppingCartProducts = Page.shoppingCartPage().getNumberOfProducts();

        addedProducts.removeIf(product -> product.equals(firstProduct));
        Page.shoppingCartPage().clickRemoveLink(0);
        wait(config(target).shortTimeout());
    }

    @Then("The removed product is no longer present in the Shopping Cart")
    public void theRemovedProductIsNoLongerPresentInTheShoppingCart() {
        for(ConcurrentHashMap<String, String> product: addedProducts){
            product.remove("qty");
        }
        List<ConcurrentHashMap<String, String>> cartProducts = new CopyOnWriteArrayList<>();
        if (numOfShoppingCartProducts == 1)
            softAssert.assertTrue(Page.shoppingCartPage().isEmpty());
        else {
            for (int i = 0; i < Page.shoppingCartPage().getNumberOfProducts(); i++){
                ConcurrentHashMap<String, String> cartProduct = new ConcurrentHashMap<>();
                cartProduct.put("product-name", Page.shoppingCartPage().getProductName(i));
                cartProduct.put("product-price", Page.shoppingCartPage().getProductPrice(i));
                if (Page.shoppingCartPage().getNumberOfVariants(i) > 0){
                    for (int j = 0; j < Page.shoppingCartPage().getNumberOfVariants(i); j++){
                        cartProduct.put(String.format("variant-%s", j), Page.shoppingCartPage().getVariants(i).get(j).toLowerCase());
                    }
                }
                cartProducts.add(cartProduct);
            }

            for (ConcurrentHashMap<String, String> addedProduct: addedProducts){
                cartProducts.removeIf(cartProduct -> cartProduct.equals(addedProduct));
                addedProducts.remove(addedProduct);
            }
            softAssert.assertTrue(addedProducts.isEmpty());
            softAssert.assertTrue(cartProducts.isEmpty());
        }
    }

    @Then("The subtotal displayed in the shopping cart matches the sum of individual product prices")
    public void theSubtotalDisplayedInTheShoppingCartMatchesTheSumOfIndividualProductPrices() {
        Double expectedTotal = 0.0;
        int i = 1;
        for (ConcurrentHashMap<String, String> product : addedProducts){
            expectedTotal = expectedTotal + CurrencyUtils.convertCurrencyStringToDouble(product.get("product-price")) * Double.parseDouble(product.get("qty"));
        }

        Assert.assertEquals(expectedTotal, CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice()));
    }

    @And("^User enters a valid coupon code (.+)$")
    public void userEntersAValidCouponCode(String discountType) {
        DBConnection.executeUpdate(Queries.deleteAllCoupons());
        newCoupon = CouponDataFactory.generateValidCouponData(NewCouponModel.DiscountType.FIXED_DISCOUNT_ENTIRE_ORDER);
        DBConnection.executeUpdate(Queries.insertCoupon(newCoupon.getCouponCode(), newCoupon.getDescription(), newCoupon.getDiscountAmount(), discountType));

        Page
                .shoppingCartPage()
                .inputCoupon(newCoupon.getCouponCode())
                .clickApplyButton();
        wait(config(target).shortTimeout());
    }

    @Then("^The total price displayed in the shopping cart reflects the discounted amount after applying the coupon (.+)$")
    public void theTotalPriceDisplayedInTheShoppingCartReflectsTheDiscountedAmountAfterApplyingTheCoupon(String discountType) {
        double expectedTotal;
        if (discountType.equals("fixed_discount_to_entire_order")){
            expectedTotal = (CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice()) - newCoupon.getDiscountAmount());
            softAssert.assertEquals(CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getTotalPrice()), expectedTotal);
        } else if (discountType.equals("percentage_discount_to_entire_order")){
            expectedTotal = Math.round(((CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice())
                    - CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getSubTotalPrice()) * newCoupon.getDiscountAmount() * 0.01) * 100) / 100);
            softAssert.assertEquals(CurrencyUtils.convertCurrencyStringToDouble(Page.shoppingCartPage().getTotalPrice()), expectedTotal);
        }
    }
}

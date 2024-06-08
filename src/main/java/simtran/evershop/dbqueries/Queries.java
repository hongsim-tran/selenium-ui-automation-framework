package simtran.evershop.dbqueries;

public class Queries {

    public static String selectProductQtyWithSku (String sku){
        return String.format("select qty from product_inventory as pi, product as p\n" +
                "where pi.product_inventory_product_id  = p.product_id \n" +
                "and p.sku = '%s'", sku);

    }

    public static String deleteAllCoupons(){
        return "delete from coupon";
    }

    public static String insertCoupon(String couponCode, String description, double discountAmount, String discountType){
        return String.format("insert into coupon (coupon, description, discount_amount, discount_type, condition, user_condition)\n" +
                "values ('%s', '%s', %s, '%s', '{\"order_qty\": \"\", \"order_total\": \"\"}', '{\"emails\": \"\", \"groups\": [\"\"], \"purchased\": \"\"}')",
                couponCode, description, discountAmount, discountType);
    }
}

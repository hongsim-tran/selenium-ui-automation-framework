package simtran.evershop.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewCouponModel {

    String couponCode;
    String description;
    Double discountAmount;
    String discountType;
    int targetProduct;

    public enum DiscountType{
        FIXED_DISCOUNT_ENTIRE_ORDER("fixed_discount_to_entire_order"),
        PERCENTAGE_DISCOUNT_ENTIRE_ORDER("percentage_discount_to_entire_order"),
//        FIXED_DISCOUNT_SPECIFIC_PRODUCTS("fixed_discount_to_specific_products"),
//        PERCENTAGE_DISCOUNT_SPECIFIC_PRODUCTS("percentage_discount_to_specific_products"),
//        BUY_X_GET_Y("buy_x_get_y")
        ;

        private final String type;

        DiscountType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
}

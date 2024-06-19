package simtran.evershop.datafactory;

import net.datafaker.Faker;
import simtran.core.utils.RandomGenerator;
import simtran.evershop.models.NewCouponModel;

public class CouponDataFactory {
    static Faker faker = new Faker();

    public static NewCouponModel generateValidCouponData(){
        NewCouponModel newCoupon = new NewCouponModel();
        newCoupon.setCouponCode(faker.text().text(8));
        newCoupon.setDescription(faker.lorem().paragraph(5));
        newCoupon.setDiscountAmount(faker.number().randomDouble(2, 1, 99));
        return newCoupon;
    }
}

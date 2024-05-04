package simtran.alfiotest.datafactory;

import net.datafaker.Faker;
import simtran.alfiotest.models.EventModel;
import simtran.core.utils.DateTimeUtils;
import simtran.core.utils.RandomGenerator;

public class EventDataFactory {

    private static final String dateFormatPattern = "yyyy-MM-dd HH:mm";
    static Faker faker = new Faker();

    public static EventModel createValidEventData() {
        EventModel event = new EventModel();
        String startDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfterNow(), dateFormatPattern);
        String endDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfter(startDateTime, dateFormatPattern), dateFormatPattern);

        event.setName(faker.show().play());
        event.setFormat(RandomGenerator.getRandomValueOfEnum(EventModel.Format.class).toString());
        event.setLocation(faker.address().fullAddress());
        event.setDateTime(startDateTime + " / " + endDateTime);
        event.setStartDate(startDateTime);
        event.setEndDate(endDateTime);
        event.setDescription(faker.lorem().paragraph(5));
        event.setEventUrl(faker.internet().slug());
        event.setWebsiteLink(faker.internet().url());
        event.setTermConditionUrl(faker.internet().url());
        event.setPolicyUrl(faker.internet().url());
        event.setIsFeeRequested(RandomGenerator.getRandomBoolean());
        event.setMaxTickets(faker.number().numberBetween(1, 10));
        event.setRegularPrice(faker.number().randomDouble(2, 1, 999999));
        event.setCurrency(faker.money().currencyCode());
        event.setTax(faker.number().randomDouble(1, 0, 100));
        event.setTaxIncluded(RandomGenerator.getRandomBoolean());
        event.setOnsitePayment(RandomGenerator.getRandomBoolean());
        event.setOfflinePayment(RandomGenerator.getRandomBoolean());
        event.setPaypal(RandomGenerator.getRandomBoolean());
        event.setSaferPay(RandomGenerator.getRandomBoolean());

        return event;
    }
}

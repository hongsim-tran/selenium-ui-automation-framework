package simtran.alfiotest.datafactory;

import net.datafaker.Faker;
import simtran.alfiotest.models.EventCategoryModel;
import simtran.core.utils.DateTimeUtils;
import simtran.core.utils.RandomGenerator;

public class EventCategoryDataFactory {
    private static final String dateFormatPattern = "yyyy-MM-dd HH:mm";
    static Faker faker = new Faker();

    public static EventCategoryModel createValidEventCategoryData() {
        EventCategoryModel eventCategory = new EventCategoryModel();
        String startDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfterNow(), dateFormatPattern);
        String endDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfter(startDateTime, dateFormatPattern), dateFormatPattern);

        String startValidDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfterNow(), dateFormatPattern);
        String endValidDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfter(startDateTime, dateFormatPattern), dateFormatPattern);

        String startCheckInDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfterNow(), dateFormatPattern);
        String endCheckInDateTime = DateTimeUtils.toString(DateTimeUtils.randomDateTimeAfter(startDateTime, dateFormatPattern), dateFormatPattern);

        eventCategory.setName(faker.industrySegments().industry());
        eventCategory.setDescription(faker.lorem().paragraph(5));
        eventCategory.setVisibility(RandomGenerator.getRandomValueOfEnum(EventCategoryModel.Visibility.class).toString());
        if (eventCategory.getVisibility().equals("Hidden"))
            eventCategory.setStrategy(EventCategoryModel.Strategy.FIXED.toString());
        else
            eventCategory.setStrategy(RandomGenerator.getRandomValueOfEnum(EventCategoryModel.Strategy.class).toString());
        eventCategory.setNumberOfTickets(faker.number().numberBetween(0, 9999));
        eventCategory.setDateTime(startDateTime + " / " + endDateTime);
        eventCategory.setStartDateTime(startDateTime);
        eventCategory.setEndDateTime(endDateTime);
        eventCategory.setPrice(faker.number().randomDouble(2, 1, 999999));
        eventCategory.setCode(faker.idNumber().valid());
        eventCategory.setValidity(RandomGenerator.getRandomValueOfEnum(EventCategoryModel.Validity.class).toString());
        eventCategory.setValidFromDate(startValidDateTime);
        eventCategory.setValidTodDate(endValidDateTime);
        eventCategory.setCheckIn(RandomGenerator.getRandomValueOfEnum(EventCategoryModel.CheckIn.class).toString());
        eventCategory.setCheckInFromDate(startCheckInDateTime);
        eventCategory.setCheckInToDate(endCheckInDateTime);
        eventCategory.setBadgeColor(RandomGenerator.getRandomValueOfEnum(EventCategoryModel.BadgeColor.class).toString());

        return eventCategory;
    }

    public static EventCategoryModel createInvalidEventCategoryData() {
        EventCategoryModel eventCategory = new EventCategoryModel();

        // Todo

        return eventCategory;
    }
}

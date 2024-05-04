package simtran.alfiotest.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventModel {

    private String name;
    private String organizer;
    private String format;
    private String location;
    private String dateTime;
    private String startDate;
    private String endDate;
    private String description;
    private String eventUrl;
    private String websiteLink;
    private String termConditionUrl;
    private String policyUrl;
    private Boolean isFeeRequested;
    private int maxTickets;
    private double regularPrice;
    private String currency;
    private double tax;
    private Boolean taxIncluded;
    private Boolean onsitePayment;
    private Boolean offlinePayment;
    private Boolean paypal;
    private Boolean saferPay;

    public enum Format {
        INPERSON("in person"),
        ONLINE("online"),
        HYBRID("hybrid (in person + online)");

        private final String format;

        Format(String format) {
            this.format = format;
        }

        @Override
        public String toString() {
            return format;
        }
    }
}

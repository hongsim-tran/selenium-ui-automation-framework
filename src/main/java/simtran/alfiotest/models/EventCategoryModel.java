package simtran.alfiotest.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventCategoryModel {

    private String name;
    private String description;
    private String visibility;
    private String strategy;
    private int numberOfTickets;
    private String dateTime;
    private String startDateTime;
    private String endDateTime;
    private double price;
    private String code;
    private String validity;
    private String validFromDate;
    private String validTodDate;
    private String checkIn;
    private String checkInFromDate;
    private String checkInToDate;
    private String badgeColor;

    public enum Visibility {
        PUBLIC("Public"),
        HIDDEN("Hidden");

        private final String visibility;

        Visibility(final String visibility) {
            this.visibility = visibility;
        }

        @Override
        public String toString() {
            return visibility;
        }
    }

    public enum Strategy {
        DYNAMIC("Grow dynamically"),
        FIXED("Fixed number of tickets");

        private final String strategy;

        Strategy(final String strategy) {
            this.strategy = strategy;
        }

        @Override
        public String toString() {
            return strategy;
        }
    }

    public enum Validity {
        ALL("For the entire event"),
        CUSTOM("Custom");

        private final String validity;

        Validity(final String validity) {
            this.validity = validity;
        }

        @Override
        public String toString() {
            return validity;
        }
    }

    public enum CheckIn {
        ANYTIME("At any time"),
        CUSTOM("Custom");

        private final String checkIn;

        CheckIn(final String checkIn) {
            this.checkIn = checkIn;
        }

        @Override
        public String toString() {
            return checkIn;
        }
    }

    public enum BadgeColor {
        BLUE("blue"),
        GRAY("gray"),
        GREEN("green"),
        RED("red"),
        YELLOW("yellow"),
        CYAN("cyan"),
        WHITE("white"),
        BLACK("black");

        private final String badgeColor;

        BadgeColor(final String badgeColor) {
            this.badgeColor = badgeColor;
        }

        @Override
        public String toString() {
            return badgeColor;
        }
    }

}


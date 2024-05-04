package simtran.core.enums;

public enum Months {
    Jan("Jan"),
    Feb("Feb"),
    Mar("Mar"),
    Apr("Apr"),
    May("May"),
    Jun("Jun"),
    Jul("Jul"),
    Aug("Aug"),
    Sep("Sep"),
    Oct("Oct"),
    Nov("Nov"),
    Dec("Dec");

    private final String month;

    Months(final String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return month;
    }
}

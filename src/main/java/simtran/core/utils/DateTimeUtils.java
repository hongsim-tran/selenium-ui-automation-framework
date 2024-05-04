package simtran.core.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class generates a random date time with specific conditions, using the LocalDateTime class
 *
 * @author simtran
 */
public class DateTimeUtils {

    private static LocalDateTime toLocalDateTime(String dateStr, String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        try {
            return LocalDateTime.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            MyLogger.error(String.format("The date-time: %s or the format pattern: %s is incorrect", dateStr, formatPattern));
            return null;
        }
    }

    private static LocalDateTime randomDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime != null && endDateTime != null) {
            if (endDateTime.isAfter(startDateTime)) {
                long startEpoch = startDateTime.toEpochSecond(ZoneOffset.of(String.valueOf(OffsetDateTime.now().getOffset())));
                long endEpoch = endDateTime.toEpochSecond(ZoneOffset.of(String.valueOf(OffsetDateTime.now().getOffset())));
                long randomDayTime = ThreadLocalRandom
                        .current()
                        .nextLong(startEpoch, endEpoch + 1);
                return LocalDateTime.ofEpochSecond(randomDayTime, 0, ZoneOffset.of(String.valueOf(OffsetDateTime.now().getOffset())));

            } else {
                MyLogger.error(String.format("The end date-time: %s is not after the start date-time: %s", endDateTime, startDateTime));
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Format a date time object as a string in a specified format
     * @param localDateTime
     * @param formatPattern
     * @return a date time as a string
     */
    public static String toString(LocalDateTime localDateTime, String formatPattern) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
            return localDateTime.format(dateTimeFormatter);
        } catch (Exception e) {
            MyLogger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Generate a random LocalDateTime object within 2 specified date times in String type
     * @param startDateTime
     * @param endDateTime
     * @param formatPattern specify the format pattern of the inputted date times
     * @return a LocalDateTime within startDateTime and endDateTime
     */
    public static LocalDateTime randomDateTimeBetween(String startDateTime, String endDateTime, String formatPattern) {
        LocalDateTime startDate = toLocalDateTime(startDateTime, formatPattern);
        LocalDateTime endDate = toLocalDateTime(endDateTime, formatPattern);

        return randomDateTimeBetween(startDate, endDate);
    }

    /**
     * Generate a random LocalDateTime object before a specified date time in String type
     * @param endDateTime
     * @param formatPattern specify the format pattern of the inputted date time
     * @return a LocalDateTime before endDateTime
     */
    public static LocalDateTime randomDateTimeBefore(String endDateTime, String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        LocalTime localTime = LocalTime.of(0, 0, 0);
        String startDateTime = LocalDateTime.of(localDate, localTime).format(dateTimeFormatter);
        return randomDateTimeBetween(startDateTime, endDateTime, formatPattern);
    }

    /**
     * Generate a random LocalDateTime object after a specified date time in String type
     * @param startDateTime
     * @param formatPattern specify the format pattern of the inputted date time
     * @return a LocalDateTime after startDateTime
     */
    public static LocalDateTime randomDateTimeAfter(String startDateTime, String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        LocalDate localDate = LocalDate.of(2025, 1, 1);
        LocalTime localTime = LocalTime.of(0, 0, 0);
        String endDateTime = LocalDateTime.of(localDate, localTime).format(dateTimeFormatter);
        return randomDateTimeBetween(startDateTime, endDateTime, formatPattern);
    }

    /**
     * Generate a random LocalDateTime object before the current time
     * @return a LocalDateTime
     */
    public static LocalDateTime randomDateTimeBeforeNow() {
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        LocalTime localTime = LocalTime.of(0, 0, 0);
        LocalDateTime startDateTime = LocalDateTime.of(localDate, localTime);
        return randomDateTimeBetween(startDateTime, LocalDateTime.now());
    }

    /**
     * Generate a random LocalDateTime object within a specified date time in String type and current time
     * @param startDateTime
     * @param formatPattern specify the format pattern of the inputted date times
     * @return a LocalDateTime within startDateTime and current time
     */
    public static LocalDateTime randomDateTimeBeforeNow(String startDateTime, String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        String endDateTime = LocalDateTime.now().format(dateTimeFormatter);
        return randomDateTimeBetween(startDateTime, endDateTime, formatPattern);
    }

    /**
     * Generate a random LocalDateTime object after the current time
     * @return a LocalDateTime
     */
    public static LocalDateTime randomDateTimeAfterNow() {
        LocalDate localDate = LocalDate.of(2025, 1, 1);
        LocalTime localTime = LocalTime.of(0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(localDate, localTime);
        return randomDateTimeBetween(LocalDateTime.now(), endDateTime);
    }


    /**
     * Generate a random LocalDateTime object within current time and a specified date time in String type
     * @param endDateTime
     * @param formatPattern specify the format pattern of the inputted date times
     * @return a LocalDateTime within current time and endDateTime
     */
    public static LocalDateTime randomDateTimeAfterNow(String endDateTime, String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        String startDateTime = LocalDateTime.now().format(dateTimeFormatter);
        return randomDateTimeBetween(startDateTime, endDateTime, formatPattern);
    }
}

package simtran.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides utility methods for converting currency strings to double values.
 *
 * @author simtran
 */
public class CurrencyUtils {

    /**
     * Converts a single string representing a currency value to a double.
     *
     * @param currencyString The string representation of the currency value.
     * @return The converted double value representing the currency amount.
     */
    public static double convertCurrencyStringToDouble(String currencyString) {
        String numericString = currencyString.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericString);
    }

    /**
     * Converts a list of strings representing currency values to a list of corresponding double values.
     *
     * @param currencyStrings A list of strings representing currency values.
     * @return A new list containing the converted double values corresponding to the input strings.
     */
    public static List<Double> convertCurrencyStringToDouble(List<String> currencyStrings) {
        List<Double> currencyDoubles = new ArrayList<>();
        for (String currencyString : currencyStrings) {
            String numericString = currencyString.replaceAll("[^\\d.]", "");
            currencyDoubles.add(Double.parseDouble(numericString));
        }
        return currencyDoubles;
    }
}

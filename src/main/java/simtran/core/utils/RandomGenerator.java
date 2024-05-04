package simtran.core.utils;

import simtran.core.exceptions.ProvidedClassNotAnEnum;

import java.util.Random;

/**
 * This class provides utility methods for generating random values.
 *
 * @author simtran
 */
public class RandomGenerator {

    /**
     * Generates a random value from the given enum type.
     *
     * @param e The class object representing the enum type.
     * @param <T> The type of the enum elements (must be an Enum).
     * @return A random element from the specified enum.
     * @throws IllegalArgumentException If the provided class is not an Enum type.
     */
    public static <T extends Enum<T>> T getRandomValueOfEnum(Class<T> e) {
        if (!e.isEnum()) {
            throw new ProvidedClassNotAnEnum(e.getName());
        }
        final Random random = new Random();
        T[] values = e.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    /**
     * Generates a random boolean value (true or false).
     *
     * @return A random boolean value.
     */
    public static Boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}

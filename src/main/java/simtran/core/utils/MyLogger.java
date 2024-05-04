package simtran.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides a convenient wrapper for logging messages using a logger instance.
 * It offers methods for various logging levels like debug, info, warn, error, and fatal.
 *
 * @author simtran
 */
public class MyLogger {
    private static final Logger log = LogManager.getLogger(MyLogger.class);

    public static void debug(String message) {
        log.debug(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }
}

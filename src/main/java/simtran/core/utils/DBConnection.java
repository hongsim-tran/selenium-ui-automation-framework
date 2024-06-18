package simtran.core.utils;

import java.sql.*;

import static simtran.core.config.ConfigManager.config;
import static simtran.core.config.ConfigManager.envConfig;

/**
 * This class provides methods for connecting to a database, executing queries, and closing the connection.
 *
 * @author simtran
 */
public class DBConnection {

    private static Connection connection;
    private static Statement statement;

    /**
     * Get a connection to database
     *
     * @param target target environment
     */
    public static void getConnection(String target) {
        try {
            connection = DriverManager.getConnection(envConfig(target).databaseUrl(), envConfig(target).databaseUsername(), envConfig(target).databasePassword());
            statement = connection.createStatement();
        } catch (SQLException e) {
            MyLogger.error(new RuntimeException(e).toString());
        }
    }

    /**
     * Execute an SQL query and return results
     *
     * @param query sql query
     * @return ResultSet
     */
    public static ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            MyLogger.error(new RuntimeException(e).toString());
            return null;
        }
    }

    /**
     * Execute an sql query
     *
     * @param query sql query
     */
    public static void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            MyLogger.error(new RuntimeException(e).toString());
        }
    }

    /**
     * Close a connection to database
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            MyLogger.error(new RuntimeException(e).toString());
        }
    }
}

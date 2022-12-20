package net.golbarg.kankor.kankor.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBController {
    public static final String BASE_PATH = new File("").getAbsolutePath();
    public static final String DB_PATH = new File(BASE_PATH + "/assets/db/kankor_db").getAbsolutePath();

    public static String userName = "golbarg_kankor";
    public static String password = "G0l8@rG110";
    public static String databaseName = "maktabdb";

    private static final String DB_PARAMS = "TRACE_LEVEL_FILE=0;TRACE_LEVEL_SYSTEM_OUT=0;CACHE_SIZE=131072;AUTO_SERVER=TRUE;";
    public static final String DB_CONNECTION_STR = "jdbc:h2:" + DB_PATH + ";" + DB_PARAMS;

    public static boolean checkDriver() {
        try {
            Class.forName("org.h2.Driver");
            return true;
        } catch(ClassNotFoundException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not find H2 Drivers");
            return false;
        }
    }

    public static Connection getLocalConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION_STR, userName, password + " " + password);
            return connection;
        } catch (SQLException ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not create connection");
            displaySQLErrors(ex);
            return null;
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Connection connection = getLocalConnection();

            if (connection != null) {
                return connection.prepareStatement(query).executeQuery();
            }
        } catch (SQLException e) {
            displaySQLErrors(e);
        }
        return null;
    }


    public static void displaySQLErrors(SQLException e) {
        System.err.println("SQLException: " + e.getMessage());
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Vendor Error: " + e.getErrorCode());
        e.printStackTrace();
    }
}

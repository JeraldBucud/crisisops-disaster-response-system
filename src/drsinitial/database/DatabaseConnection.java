package drsinitial.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides JDBC connections to the CrisisOps MySQL database.
 */
public class DatabaseConnection {

    private static final String URL = getSetting(
            "CRISISOPS_DB_URL",
            "crisisops.db.url",
            "jdbc:mysql://localhost:3306/drs_enhanced?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");

    private static final String USERNAME = getSetting(
            "CRISISOPS_DB_USERNAME",
            "crisisops.db.username",
            "root");

    private static final String PASSWORD = getSetting(
            "CRISISOPS_DB_PASSWORD",
            "crisisops.db.password",
            "");

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new SQLException(
                    "MySQL JDBC driver not found. Add mysql-connector-j to project libraries.",
                    exception);
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException exception) {
            System.err.println("Database connection failed: " + exception.getMessage());
            return false;
        }
    }

    private static String getSetting(String environmentName,
            String propertyName,
            String defaultValue) {

        String value = System.getenv(environmentName);

        if (value == null || value.isBlank()) {
            value = System.getProperty(propertyName);
        }

        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        return value;
    }
}

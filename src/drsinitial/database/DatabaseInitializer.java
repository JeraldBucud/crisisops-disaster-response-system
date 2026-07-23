package drsinitial.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates the CrisisOps database tables programmatically when the server starts.
 */
public class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    public static void initializeDatabase() {
        createTables();
        seedDefaultData();
    }

    private static void createTables() {
        String[] sqlStatements = new String[] {
            "CREATE TABLE IF NOT EXISTS users ("
                    + "user_id VARCHAR(20) PRIMARY KEY,"
                    + "full_name VARCHAR(100) NOT NULL,"
                    + "email VARCHAR(100) NOT NULL,"
                    + "username VARCHAR(50) NOT NULL UNIQUE,"
                    + "password_hash VARCHAR(255) NOT NULL,"
                    + "role VARCHAR(50) NOT NULL,"
                    + "account_status VARCHAR(20) NOT NULL,"
                    + "created_time DATETIME NOT NULL)",

            "CREATE TABLE IF NOT EXISTS disaster_reports ("
                    + "report_id VARCHAR(20) PRIMARY KEY,"
                    + "reporter_name VARCHAR(100) NOT NULL,"
                    + "disaster_type VARCHAR(50) NOT NULL,"
                    + "location VARCHAR(150) NOT NULL,"
                    + "description TEXT NOT NULL,"
                    + "initial_severity VARCHAR(30) NOT NULL,"
                    + "report_status VARCHAR(30) NOT NULL,"
                    + "reported_time DATETIME NOT NULL)",

            "CREATE TABLE IF NOT EXISTS incidents ("
                    + "incident_id VARCHAR(20) PRIMARY KEY,"
                    + "report_id VARCHAR(20) NOT NULL,"
                    + "location VARCHAR(150) NOT NULL,"
                    + "status VARCHAR(50) NOT NULL,"
                    + "severity VARCHAR(50) NOT NULL,"
                    + "priority VARCHAR(50),"
                    + "created_time DATETIME NOT NULL)",

            "CREATE TABLE IF NOT EXISTS emergency_resources ("
                    + "resource_id VARCHAR(20) PRIMARY KEY,"
                    + "resource_name VARCHAR(100) NOT NULL,"
                    + "type VARCHAR(50) NOT NULL,"
                    + "quantity INT NOT NULL,"
                    + "available_quantity INT NOT NULL,"
                    + "resource_status VARCHAR(30) NOT NULL)",

            "CREATE TABLE IF NOT EXISTS response_agencies ("
                    + "agency_id VARCHAR(20) PRIMARY KEY,"
                    + "agency_name VARCHAR(100) NOT NULL,"
                    + "agency_type VARCHAR(50) NOT NULL,"
                    + "contact_number VARCHAR(50) NOT NULL)",

            "CREATE TABLE IF NOT EXISTS emergency_responses ("
                    + "response_id VARCHAR(20) PRIMARY KEY,"
                    + "incident_id VARCHAR(20) NOT NULL,"
                    + "agency_id VARCHAR(20) NOT NULL,"
                    + "resource_id VARCHAR(20) NOT NULL,"
                    + "dispatch_status VARCHAR(50) NOT NULL,"
                    + "dispatch_notes TEXT,"
                    + "dispatch_time DATETIME NOT NULL)",

            "CREATE TABLE IF NOT EXISTS evacuation_shelters ("
                    + "shelter_id VARCHAR(20) PRIMARY KEY,"
                    + "shelter_name VARCHAR(100) NOT NULL,"
                    + "location VARCHAR(150) NOT NULL,"
                    + "total_capacity INT NOT NULL,"
                    + "current_occupants INT NOT NULL,"
                    + "available_spaces INT NOT NULL,"
                    + "shelter_status VARCHAR(30) NOT NULL,"
                    + "last_updated DATETIME NOT NULL)",

            "CREATE TABLE IF NOT EXISTS public_alerts ("
                    + "alert_id VARCHAR(20) PRIMARY KEY,"
                    + "incident_id VARCHAR(20),"
                    + "alert_type VARCHAR(50) NOT NULL,"
                    + "affected_area VARCHAR(150) NOT NULL,"
                    + "severity_level VARCHAR(30) NOT NULL,"
                    + "alert_message TEXT NOT NULL,"
                    + "created_by VARCHAR(100) NOT NULL,"
                    + "created_time DATETIME NOT NULL,"
                    + "alert_status VARCHAR(30) NOT NULL)",

            "CREATE TABLE IF NOT EXISTS audit_logs ("
                    + "audit_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "action_type VARCHAR(100) NOT NULL,"
                    + "action_details TEXT NOT NULL,"
                    + "action_time DATETIME NOT NULL)"
        };

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
            for (String sql : sqlStatements) {
                statement.execute(sql);
            }
            System.out.println("CrisisOps database tables checked/created.");
        } catch (SQLException exception) {
            System.err.println("Database initialization failed: " + exception.getMessage());
        }
    }

    private static void seedDefaultData() {
        UserDAOHelper.seedUsers();
    }

    private static class UserDAOHelper {
        static void seedUsers() {
            try {
                drsinitial.dao.UserDAO userDAO = new drsinitial.dao.UserDAO();
                if (!userDAO.usernameExists("admin")) {
                    userDAO.createUser("USR001", "System Administrator", "admin@crisisops.local",
                            "admin", "admin123", "SYSTEM_ADMINISTRATOR", "ACTIVE");
                }
                if (!userDAO.usernameExists("ecc")) {
                    userDAO.createUser("USR002", "Emergency Control Centre", "ecc@crisisops.local",
                            "ecc", "ecc123", "EMERGENCY_CONTROL_CENTRE", "ACTIVE");
                }
                if (!userDAO.usernameExists("public")) {
                    userDAO.createUser("USR003", "Public User", "public@crisisops.local",
                            "public", "public123", "PUBLIC_USER", "ACTIVE");
                }
            } catch (Exception exception) {
                System.err.println("Default user seed failed: " + exception.getMessage());
            }
        }
    }
}

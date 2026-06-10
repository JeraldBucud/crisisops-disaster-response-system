package drsinitial.dao;

import drsinitial.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Saves audit log records for timestamping and non-repudiation evidence.
 */
public class AuditLogDAO {

    public void saveAuditLog(String username, String actionType, String actionDetails) {
        String sql = "INSERT INTO audit_logs(username, action_type, action_details, action_time) VALUES (?, ?, ?, NOW())";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username == null ? "system" : username);
            statement.setString(2, actionType);
            statement.setString(3, actionDetails);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Audit log save failed: " + exception.getMessage());
        }
    }
}

package drsinitial.dao;

import drsinitial.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles public alert database operations.
 */
public class PublicAlertDAO {

    public boolean saveAlert(String alertId,
            String incidentId,
            String alertType,
            String affectedArea,
            String severityLevel,
            String alertMessage,
            String createdBy,
            String alertStatus) throws SQLException {

        String sql = "INSERT INTO public_alerts(alert_id, incident_id, "
                + "alert_type, affected_area, severity_level, alert_message, "
                + "created_by, created_time, alert_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, alertId);
            statement.setString(2, incidentId);
            statement.setString(3, alertType);
            statement.setString(4, affectedArea);
            statement.setString(5, severityLevel);
            statement.setString(6, alertMessage);
            statement.setString(7, createdBy);
            statement.setString(8, alertStatus);

            return statement.executeUpdate() > 0;
        }
    }

    public List<Map<String, String>> getPublishedAlerts()
            throws SQLException {

        List<Map<String, String>> alerts = new ArrayList<>();

        String sql = "SELECT alert_id, incident_id, alert_type, "
                + "affected_area, severity_level, alert_message, created_by, "
                + "created_time, alert_status "
                + "FROM public_alerts "
                + "WHERE alert_status = 'PUBLISHED' "
                + "ORDER BY created_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                alerts.add(mapAlert(resultSet));
            }
        }

        return alerts;
    }

    public List<Map<String, String>> getAllAlerts() throws SQLException {
        List<Map<String, String>> alerts = new ArrayList<>();

        String sql = "SELECT alert_id, incident_id, alert_type, "
                + "affected_area, severity_level, alert_message, created_by, "
                + "created_time, alert_status "
                + "FROM public_alerts "
                + "ORDER BY created_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                alerts.add(mapAlert(resultSet));
            }
        }

        return alerts;
    }

    public boolean updateAlertStatus(String alertId, String alertStatus)
            throws SQLException {

        String sql = "UPDATE public_alerts SET alert_status = ? "
                + "WHERE alert_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, alertStatus);
            statement.setString(2, alertId);

            return statement.executeUpdate() > 0;
        }
    }

    public String generateNextAlertId() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM public_alerts";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            int next = 1;

            if (resultSet.next()) {
                next = resultSet.getInt("total") + 1;
            }

            return String.format("ALT%03d", next);
        }
    }

    private Map<String, String> mapAlert(ResultSet resultSet)
            throws SQLException {

        Map<String, String> alert = new HashMap<>();
        alert.put("alertId", resultSet.getString("alert_id"));
        alert.put("incidentId", resultSet.getString("incident_id"));
        alert.put("alertType", resultSet.getString("alert_type"));
        alert.put("affectedArea", resultSet.getString("affected_area"));
        alert.put("severityLevel", resultSet.getString("severity_level"));
        alert.put("alertMessage", resultSet.getString("alert_message"));
        alert.put("createdBy", resultSet.getString("created_by"));
        alert.put("createdTime",
                String.valueOf(resultSet.getTimestamp("created_time")));
        alert.put("alertStatus", resultSet.getString("alert_status"));

        return alert;
    }
}
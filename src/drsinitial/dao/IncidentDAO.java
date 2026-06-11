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
 * Handles incident database queries.
 */
public class IncidentDAO {

    public List<Map<String, String>> getAllIncidents() throws SQLException {
        List<Map<String, String>> incidents = new ArrayList<>();

        String sql = "SELECT incident_id, report_id, affected_people, "
                + "affected_area, severity, priority, status, created_time "
                + "FROM incidents ORDER BY created_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                incidents.add(mapIncident(resultSet));
            }
        }

        return incidents;
    }

    public List<Map<String, String>> searchIncidents(
            Map<String, String> criteria) throws SQLException {

        List<Map<String, String>> incidents = new ArrayList<>();

        String sql = "SELECT incident_id, report_id, affected_people, "
                + "affected_area, severity, priority, status, created_time "
                + "FROM incidents ORDER BY created_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> incident = mapIncident(resultSet);

                if (matchesCriteria(incident, criteria)) {
                    incidents.add(incident);
                }
            }
        }

        return incidents;
    }

    public Map<String, String> findIncidentById(String incidentId)
            throws SQLException {

        String sql = "SELECT incident_id, report_id, affected_people, "
                + "affected_area, severity, priority, status, created_time "
                + "FROM incidents WHERE incident_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, incidentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapIncident(resultSet);
                }
            }
        }

        return null;
    }

    public String generateNextIncidentId() throws SQLException {
        String sql = "SELECT incident_id FROM incidents "
                + "ORDER BY CAST(SUBSTRING(incident_id, 4) AS UNSIGNED) DESC "
                + "LIMIT 1";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                String currentId = resultSet.getString("incident_id");
                int number = Integer.parseInt(currentId.substring(3));
                return String.format("INC%03d", number + 1);
            }
        }

        return "INC001";
    }

    public boolean saveIncident(String incidentId,
            String reportId,
            int affectedPeople,
            String affectedArea,
            String severity,
            String priority,
            String status) throws SQLException {

        String sql = "INSERT INTO incidents "
                + "(incident_id, report_id, affected_people, affected_area, "
                + "severity, priority, status, created_time) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, incidentId);
            statement.setString(2, reportId);
            statement.setInt(3, affectedPeople);
            statement.setString(4, affectedArea);
            statement.setString(5, severity);
            statement.setString(6, priority);
            statement.setString(7, status);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateIncidentPriority(String incidentId,
            String severity,
            String priority) throws SQLException {

        String sql = "UPDATE incidents SET severity = ?, priority = ? "
                + "WHERE incident_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, severity);
            statement.setString(2, priority);
            statement.setString(3, incidentId);

            return statement.executeUpdate() > 0;
        }
    }

    private boolean matchesCriteria(Map<String, String> incident,
            Map<String, String> criteria) {

        if (criteria == null || criteria.isEmpty()) {
            return true;
        }

        String keyword = criteria.getOrDefault("keyword", "").toLowerCase();
        String severity = criteria.getOrDefault("severity", "");
        String priority = criteria.getOrDefault("priority", "");
        String status = criteria.getOrDefault("status", "");

        boolean matchesKeyword = keyword.isEmpty()
                || incident.get("incidentId").toLowerCase().contains(keyword)
                || incident.get("affectedArea").toLowerCase().contains(keyword)
                || incident.get("reportId").toLowerCase().contains(keyword);

        boolean matchesSeverity = severity.isEmpty()
                || severity.equalsIgnoreCase(incident.get("severity"));

        boolean matchesPriority = priority.isEmpty()
                || priority.equalsIgnoreCase(incident.get("priority"));

        boolean matchesStatus = status.isEmpty()
                || status.equalsIgnoreCase(incident.get("status"));

        return matchesKeyword
                && matchesSeverity
                && matchesPriority
                && matchesStatus;
    }

    private Map<String, String> mapIncident(ResultSet resultSet)
            throws SQLException {

        Map<String, String> incident = new HashMap<>();
        incident.put("incidentId", resultSet.getString("incident_id"));
        incident.put("reportId", resultSet.getString("report_id"));
        incident.put("affectedPeople",
                String.valueOf(resultSet.getInt("affected_people")));
        incident.put("affectedArea", resultSet.getString("affected_area"));
        incident.put("severity", resultSet.getString("severity"));
        incident.put("priority", resultSet.getString("priority"));
        incident.put("status", resultSet.getString("status"));
        incident.put("createdTime",
                String.valueOf(resultSet.getTimestamp("created_time")));

        return incident;
    }
}
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
 * Handles emergency resource database queries.
 */
public class EmergencyResourceDAO {

    public List<Map<String, String>> getAllResources() throws SQLException {
        List<Map<String, String>> resources = new ArrayList<>();

        String sql = "SELECT resource_id, resource_name, type, quantity, "
                + "available_quantity, resource_status "
                + "FROM emergency_resources ORDER BY resource_id";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> resource = new HashMap<>();
                resource.put("resourceId",
                        resultSet.getString("resource_id"));
                resource.put("resourceName",
                        resultSet.getString("resource_name"));
                resource.put("resourceType",
                        resultSet.getString("type"));
                resource.put("totalQuantity",
                        String.valueOf(resultSet.getInt("quantity")));
                resource.put("availableQuantity",
                        String.valueOf(resultSet.getInt(
                                "available_quantity")));
                resource.put("assignedQuantity", "0");
                resource.put("unavailableQuantity", "0");
                resource.put("maintenanceQuantity", "0");
                resource.put("resourceStatus",
                        resultSet.getString("resource_status"));

                resources.add(resource);
            }
        }

        return resources;
    }

    public boolean updateResourceAvailability(String resourceId,
            String action) throws SQLException {

        String sql = buildUpdateSql(action);

        if (sql.isEmpty()) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, resourceId);

            return statement.executeUpdate() > 0;
        }
    }

    private String buildUpdateSql(String action) {
        if ("RELEASE_ASSIGNED".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET available_quantity = available_quantity + 1 "
                    + "WHERE resource_id = ?";
        }

        if ("MARK_UNAVAILABLE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET available_quantity = available_quantity - 1, "
                    + "resource_status = 'UNAVAILABLE' "
                    + "WHERE resource_id = ? AND available_quantity > 0";
        }

        if ("MARK_MAINTENANCE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET available_quantity = available_quantity - 1, "
                    + "resource_status = 'UNDER_MAINTENANCE' "
                    + "WHERE resource_id = ? AND available_quantity > 0";
        }

        if ("RESTORE_UNAVAILABLE".equals(action)
                || "RESTORE_MAINTENANCE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET available_quantity = available_quantity + 1, "
                    + "resource_status = 'AVAILABLE' "
                    + "WHERE resource_id = ?";
        }

        return "";
    }
}
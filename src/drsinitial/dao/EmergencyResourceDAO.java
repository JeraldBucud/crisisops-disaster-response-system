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
                + "available_quantity, assigned_quantity, unavailable_quantity, "
                + "maintenance_quantity, resource_status "
                + "FROM emergency_resources ORDER BY resource_id";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

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
                        String.valueOf(resultSet.getInt("available_quantity")));
                resource.put("assignedQuantity",
                        String.valueOf(resultSet.getInt("assigned_quantity")));
                resource.put("unavailableQuantity",
                        String.valueOf(resultSet.getInt("unavailable_quantity")));
                resource.put("maintenanceQuantity",
                        String.valueOf(resultSet.getInt("maintenance_quantity")));
                resource.put("resourceStatus",
                        resultSet.getString("resource_status"));

                resources.add(resource);
            }
        }

        return resources;
    }

    /**
     * Loads all response agencies from the database.
     *
     * @return list of response agency records
     * @throws SQLException if database access fails
     */
    public List<Map<String, String>> getAllResponseAgencies()
            throws SQLException {

        List<Map<String, String>> agencies = new ArrayList<>();

        String sql = "SELECT agency_id, agency_name, agency_type, contact_number "
                + "FROM response_agencies ORDER BY agency_id";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> agency = new HashMap<>();

                agency.put("agencyId", resultSet.getString("agency_id"));
                agency.put("agencyName", resultSet.getString("agency_name"));
                agency.put("agencyType", resultSet.getString("agency_type"));
                agency.put("contactNumber", resultSet.getString("contact_number"));
                agency.put("availabilityStatus", "AVAILABLE");

                agencies.add(agency);
            }
        }

        return agencies;
    }

    public boolean updateResourceAvailability(String resourceId,
            String action) throws SQLException {

        String sql = buildUpdateSql(action);

        if (sql.isEmpty()) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement
                = connection.prepareStatement(sql)) {

            statement.setString(1, resourceId);

            return statement.executeUpdate() > 0;
        }
    }

    private String buildUpdateSql(String action) {
        if ("RELEASE_ASSIGNED".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET assigned_quantity = assigned_quantity - 1, "
                    + "available_quantity = available_quantity + 1, "
                    + "resource_status = 'AVAILABLE' "
                    + "WHERE resource_id = ? AND assigned_quantity > 0";
        }

        if ("MARK_UNAVAILABLE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET available_quantity = available_quantity - 1, "
                    + "unavailable_quantity = unavailable_quantity + 1, "
                    + "resource_status = 'UNAVAILABLE' "
                    + "WHERE resource_id = ? AND available_quantity > 0";
        }

        if ("MARK_MAINTENANCE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET available_quantity = available_quantity - 1, "
                    + "maintenance_quantity = maintenance_quantity + 1, "
                    + "resource_status = 'UNDER_MAINTENANCE' "
                    + "WHERE resource_id = ? AND available_quantity > 0";
        }

        if ("RESTORE_UNAVAILABLE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET unavailable_quantity = unavailable_quantity - 1, "
                    + "available_quantity = available_quantity + 1, "
                    + "resource_status = 'AVAILABLE' "
                    + "WHERE resource_id = ? AND unavailable_quantity > 0";
        }

        if ("RESTORE_MAINTENANCE".equals(action)) {
            return "UPDATE emergency_resources "
                    + "SET maintenance_quantity = maintenance_quantity - 1, "
                    + "available_quantity = available_quantity + 1, "
                    + "resource_status = 'AVAILABLE' "
                    + "WHERE resource_id = ? AND maintenance_quantity > 0";
        }

        return "";
    }
}

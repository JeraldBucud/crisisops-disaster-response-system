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
 * Handles emergency response and dispatch database operations.
 */
public class DispatchDAO {

    public boolean saveDispatch(String responseId,
            String incidentId,
            String agencyId,
            String resourceId,
            String dispatchStatus,
            String dispatchNotes) throws SQLException {

        String sql = "INSERT INTO emergency_responses(response_id, "
                + "incident_id, agency_id, resource_id, dispatch_status, "
                + "dispatch_notes, dispatch_time) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, responseId);
            statement.setString(2, incidentId);
            statement.setString(3, agencyId);
            statement.setString(4, resourceId);
            statement.setString(5, dispatchStatus);
            statement.setString(6, dispatchNotes);

            return statement.executeUpdate() > 0;
        }
    }

    public List<Map<String, String>> getAllDispatchRecords()
            throws SQLException {

        List<Map<String, String>> records = new ArrayList<>();

        String sql = "SELECT er.response_id, er.incident_id, er.agency_id, "
                + "er.resource_id, er.dispatch_status, er.dispatch_notes, "
                + "er.dispatch_time, "
                + "ra.agency_name, ra.agency_type, ra.contact_number, "
                + "res.resource_name, res.type, res.quantity, "
                + "res.available_quantity, res.resource_status "
                + "FROM emergency_responses er "
                + "LEFT JOIN response_agencies ra "
                + "ON er.agency_id = ra.agency_id "
                + "LEFT JOIN emergency_resources res "
                + "ON er.resource_id = res.resource_id "
                + "ORDER BY er.dispatch_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> record = new HashMap<>();

                record.put("responseId",
                        resultSet.getString("response_id"));
                record.put("incidentId",
                        resultSet.getString("incident_id"));
                record.put("agencyId",
                        resultSet.getString("agency_id"));
                record.put("resourceId",
                        resultSet.getString("resource_id"));
                record.put("responseStatus",
                        resultSet.getString("dispatch_status"));
                record.put("dispatchNotes",
                        resultSet.getString("dispatch_notes"));
                record.put("dispatchDateTime",
                        String.valueOf(resultSet.getTimestamp(
                                "dispatch_time")));

                record.put("agencyName",
                        resultSet.getString("agency_name"));
                record.put("agencyType",
                        resultSet.getString("agency_type"));
                record.put("availabilityStatus", "AVAILABLE");

                record.put("resourceName",
                        resultSet.getString("resource_name"));
                record.put("resourceType",
                        resultSet.getString("type"));
                record.put("totalQuantity",
                        String.valueOf(resultSet.getInt("quantity")));
                record.put("availableQuantity",
                        String.valueOf(resultSet.getInt(
                                "available_quantity")));
                record.put("assignedQuantity", "0");
                record.put("unavailableQuantity", "0");
                record.put("maintenanceQuantity", "0");

                records.add(record);
            }
        }

        return records;
    }

    public String generateNextResponseId() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM emergency_responses";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            int next = 1;

            if (resultSet.next()) {
                next = resultSet.getInt("total") + 1;
            }

            return String.format("ER%03d", next);
        }
    }
}
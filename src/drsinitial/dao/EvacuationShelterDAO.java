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
 * Handles evacuation shelter database operations.
 */
public class EvacuationShelterDAO {

    public boolean saveShelter(String shelterId,
            String shelterName,
            String location,
            int totalCapacity,
            int currentOccupants,
            String shelterStatus) throws SQLException {

        int availableSpaces = totalCapacity - currentOccupants;

        String sql = "INSERT INTO evacuation_shelters(shelter_id, "
                + "shelter_name, location, total_capacity, "
                + "current_occupants, available_spaces, shelter_status, "
                + "last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, shelterId);
            statement.setString(2, shelterName);
            statement.setString(3, location);
            statement.setInt(4, totalCapacity);
            statement.setInt(5, currentOccupants);
            statement.setInt(6, availableSpaces);
            statement.setString(7, shelterStatus);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateShelter(String shelterId,
            String shelterName,
            String location,
            int totalCapacity,
            int currentOccupants,
            String shelterStatus) throws SQLException {

        int availableSpaces = totalCapacity - currentOccupants;

        String sql = "UPDATE evacuation_shelters SET shelter_name = ?, "
                + "location = ?, total_capacity = ?, current_occupants = ?, "
                + "available_spaces = ?, shelter_status = ?, "
                + "last_updated = NOW() WHERE shelter_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, shelterName);
            statement.setString(2, location);
            statement.setInt(3, totalCapacity);
            statement.setInt(4, currentOccupants);
            statement.setInt(5, availableSpaces);
            statement.setString(6, shelterStatus);
            statement.setString(7, shelterId);

            return statement.executeUpdate() > 0;
        }
    }

    public List<Map<String, String>> getAllShelters() throws SQLException {
        List<Map<String, String>> shelters = new ArrayList<>();

        String sql = "SELECT shelter_id, shelter_name, location, "
                + "total_capacity, current_occupants, available_spaces, "
                + "shelter_status, last_updated "
                + "FROM evacuation_shelters ORDER BY shelter_id";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> shelter = new HashMap<>();
                shelter.put("shelterId",
                        resultSet.getString("shelter_id"));
                shelter.put("shelterName",
                        resultSet.getString("shelter_name"));
                shelter.put("location",
                        resultSet.getString("location"));
                shelter.put("totalCapacity",
                        String.valueOf(resultSet.getInt(
                                "total_capacity")));
                shelter.put("currentOccupants",
                        String.valueOf(resultSet.getInt(
                                "current_occupants")));
                shelter.put("availableSpaces",
                        String.valueOf(resultSet.getInt(
                                "available_spaces")));
                shelter.put("shelterStatus",
                        resultSet.getString("shelter_status"));
                shelter.put("lastUpdated",
                        String.valueOf(resultSet.getTimestamp(
                                "last_updated")));

                shelters.add(shelter);
            }
        }

        return shelters;
    }

    public String generateNextShelterId() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM evacuation_shelters";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            int next = 1;

            if (resultSet.next()) {
                next = resultSet.getInt("total") + 1;
            }

            return String.format("SH%03d", next);
        }
    }
}
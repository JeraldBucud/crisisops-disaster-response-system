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
 * Handles disaster report database operations.
 */
public class DisasterReportDAO {

    public boolean saveReport(String reportId,
            String reporterName,
            String disasterType,
            String location,
            String description,
            String initialSeverity) throws SQLException {

        String sql = "INSERT INTO disaster_reports(report_id, reporter_name, "
                + "disaster_type, location, description, initial_severity, "
                + "report_status, reported_time) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'REPORTED', NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, reportId);
            statement.setString(2, reporterName);
            statement.setString(3, disasterType);
            statement.setString(4, location);
            statement.setString(5, description);
            statement.setString(6, initialSeverity);

            return statement.executeUpdate() > 0;
        }
    }

    public List<Map<String, String>> getAllReports() throws SQLException {
        List<Map<String, String>> reports = new ArrayList<>();

        String sql = "SELECT report_id, reporter_name, disaster_type, "
                + "location, description, initial_severity, report_status, "
                + "reported_time "
                + "FROM disaster_reports ORDER BY reported_time DESC";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> report = new HashMap<>();
                report.put("reportId", resultSet.getString("report_id"));
                report.put("reporterName",
                        resultSet.getString("reporter_name"));
                report.put("disasterType",
                        resultSet.getString("disaster_type"));
                report.put("location", resultSet.getString("location"));
                report.put("description",
                        resultSet.getString("description"));
                report.put("initialSeverity",
                        resultSet.getString("initial_severity"));
                report.put("reportStatus",
                        resultSet.getString("report_status"));
                report.put("dateTime",
                        String.valueOf(resultSet.getTimestamp(
                                "reported_time")));

                reports.add(report);
            }
        }

        return reports;
    }

    public String generateNextReportId() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM disaster_reports";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            int next = 1;

            if (resultSet.next()) {
                next = resultSet.getInt("total") + 1;
            }

            return String.format("R%03d", next);
        }
    }
}
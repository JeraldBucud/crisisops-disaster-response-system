package drsinitial.dao;

import drsinitial.database.DatabaseConnection;
import drsinitial.security.EncryptionService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles database operations for system users and login.
 */
public class UserDAO {

    private final EncryptionService encryptionService = new EncryptionService();

    public boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT username FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public boolean createUser(String userId,
            String fullName,
            String email,
            String username,
            String password,
            String role,
            String accountStatus) throws SQLException {

        String sql = "INSERT INTO users(user_id, full_name, email, "
                + "username, password_hash, role, account_status, "
                + "created_time) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, userId);
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, role);
            statement.setString(7, accountStatus);

            return statement.executeUpdate() > 0;
        }
    }

    public Map<String, String> authenticate(String username,
            String password) throws SQLException {

        String sql = "SELECT user_id, full_name, username, password_hash, "
                + "role, account_status FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }

                String storedPassword = resultSet.getString("password_hash");
                String status = resultSet.getString("account_status");

                if (!"ACTIVE".equals(status)
                        || !encryptionService.matches(password,
                                storedPassword)) {
                    return null;
                }

                Map<String, String> userData = new HashMap<>();
                userData.put("userId", resultSet.getString("user_id"));
                userData.put("fullName", resultSet.getString("full_name"));
                userData.put("username", resultSet.getString("username"));
                userData.put("role", resultSet.getString("role"));
                userData.put("accountStatus", status);

                return userData;
            }
        }
    }

    public List<Map<String, String>> getAllUsers() throws SQLException {
        List<Map<String, String>> users = new ArrayList<>();

        String sql = "SELECT user_id, full_name, email, username, role, "
                + "account_status FROM users ORDER BY user_id";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("userId", resultSet.getString("user_id"));
                user.put("fullName", resultSet.getString("full_name"));
                user.put("email", resultSet.getString("email"));
                user.put("username", resultSet.getString("username"));
                user.put("role", resultSet.getString("role"));
                user.put("accountStatus",
                        resultSet.getString("account_status"));

                users.add(user);
            }
        }

        return users;
    }

    public String generateNextUserId() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            int next = 1;

            if (resultSet.next()) {
                next = resultSet.getInt("total") + 1;
            }

            return String.format("USR%03d", next);
        }
    }
}
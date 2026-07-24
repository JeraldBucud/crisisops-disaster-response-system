package drsinitial.controller;

import drsinitial.client.BackendClient;
import drsinitial.client.ClientResponse;
import drsinitial.model.enums.UserRole;
import drsinitial.session.UserSession;
import drsinitial.util.BrandingService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controls the CrisisOps login screen.
 *
 * This controller sends login details to the backend server through
 * BackendClient. It does not connect directly to MySQL.
 *
 * @author Jerald Christopher Bucud
 */
public class LoginController {

    private final BackendClient backendClient = new BackendClient();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginStatusLabel;

    /**
     * Handles login button action.
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter username and password.");
            return;
        }

        ClientResponse response = backendClient.login(username, password);

        if (!response.isSuccess()) {
            showError(response.getMessage());
            return;
        }

        if (!response.hasData()) {
            showError("Login failed. No user data was returned.");
            return;
        }

        String accountStatus = response.getDataValue("accountStatus");

        if (accountStatus.equalsIgnoreCase("INACTIVE")) {
            showError("This account is inactive.");
            return;
        }

        String roleValue = response.getDataValue("role");
        UserRole userRole = convertToUserRole(roleValue);

        if (userRole == null) {
            showError("Login failed. Invalid user role returned.");
            return;
        }

        String fullName = response.getDataValue("fullName");

        if (fullName.isEmpty()) {
            fullName = username;
        }

        UserSession.login(username, fullName, userRole);
        openDashboard();
    }

    /**
     * Handles public registration button action.
     */
    @FXML
    private void handleOpenRegistration() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/drsinitial/view/PublicUserRegistrationView.fxml"));

            Scene scene = new Scene(root);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setTitle("CrisisOps | Public User Registration");
            stage.setScene(scene);
            stage.show();

        } catch (IOException exception) {
            showError("Unable to open registration screen.");
        }
    }

    /**
     * Converts a backend role value to a UserRole enum value.
     *
     * @param roleValue role value returned by backend
     * @return matching UserRole, or null if invalid
     */
    private UserRole convertToUserRole(String roleValue) {
        if (roleValue == null || roleValue.trim().isEmpty()) {
            return null;
        }

        String normalizedRole = roleValue.trim()
                .toUpperCase()
                .replace(" ", "_");

        try {
            return UserRole.valueOf(normalizedRole);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    /**
     * Opens the main dashboard screen.
     */
    private void openDashboard() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/drsinitial/view/MainDashboard.fxml"));

            BrandingService.applyCrisisOpsBranding(root);
            Scene scene = new Scene(root);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setTitle("CrisisOps | Emergency Operations Dashboard");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        } catch (IOException exception) {
            showError("Unable to open dashboard.");
        }
    }

    /**
     * Shows an error message on the login screen.
     *
     * @param message message to display
     */
    private void showError(String message) {
        loginStatusLabel.setText(message);
    }
}

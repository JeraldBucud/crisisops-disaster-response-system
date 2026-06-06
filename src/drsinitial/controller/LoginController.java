package drsinitial.controller;

import drsinitial.model.enums.UserRole;
import drsinitial.session.UserSession;
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
 * Controls the login screen for DRS-Enhanced.
 *
 * This controller validates temporary frontend login accounts
 * and opens the main dashboard after successful login.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class LoginController {

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

        if (authenticateUser(username, password)) {
            openDashboard();
            return;
        }

        showError("Invalid username or password.");
    }

    /**
     * Handles public registration button action.
     *
     * The registration screen will be created in the next task.
     */
    @FXML
    private void handleOpenRegistration() {
        showError("Public user registration screen will be added next.");
    }

    /**
     * Authenticates temporary frontend test accounts.
     *
     * These accounts will later be replaced by MySQL login.
     *
     * @param username entered username
     * @param password entered password
     * @return true if login details are valid
     */
    private boolean authenticateUser(String username, String password) {
        String normalizedUsername = username.toLowerCase();

        if (normalizedUsername.equals("admin")
                && password.equals("admin123")) {
            UserSession.login(
                    "admin",
                    "System Administrator",
                    UserRole.SYSTEM_ADMINISTRATOR);
            return true;
        }

        if (normalizedUsername.equals("ecc")
                && password.equals("ecc123")) {
            UserSession.login(
                    "ecc",
                    "Emergency Control Centre",
                    UserRole.EMERGENCY_CONTROL_CENTRE);
            return true;
        }

        if (normalizedUsername.equals("public")
                && password.equals("public123")) {
            UserSession.login(
                    "public",
                    "Public User",
                    UserRole.PUBLIC_USER);
            return true;
        }

        return false;
    }

    /**
     * Opens the main dashboard screen.
     */
    private void openDashboard() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/drsinitial/view/MainDashboard.fxml"));

            Scene scene = new Scene(root);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setTitle("DRS-Enhanced Disaster Response System");
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
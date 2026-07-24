package drsinitial.controller;

import drsinitial.client.BackendClient;
import drsinitial.client.ClientResponse;
import drsinitial.model.User;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controls the CrisisOps public user registration screen.
 *
 * This controller validates public user registration input and sends
 * the registration request to the backend server through BackendClient.
 * It does not connect directly to MySQL.
 *
 * @author Jerald Christopher Bucud
 */
public class PublicUserRegistrationController {

    private final BackendClient backendClient = new BackendClient();

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label registrationStatusLabel;

    @FXML
    private TextField emailField;

    /**
     * Handles public user registration.
     */
    @FXML
    private void handleRegister() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String contactNumber = contactNumberField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!isValidInput(fullName, email, contactNumber, username,
                password, confirmPassword)) {
            return;
        }

        User publicUser = new User(
                "",
                fullName,
                email,
                username,
                "PUBLIC_USER",
                "ACTIVE");

        ClientResponse response = backendClient.registerPublicUser(
                publicUser,
                password);

        if (!response.isSuccess()) {
            showError(response.getMessage());
            return;
        }

        showSuccess("Registration successful. You can now sign in.");

        PauseTransition pause = new PauseTransition(Duration.seconds(1.2));
        pause.setOnFinished(event -> openLoginScreen());
        pause.play();
    }

    /**
     * Handles back to login button action.
     */
    @FXML
    private void handleBackToLogin() {
        openLoginScreen();
    }

    /**
     * Validates registration input.
     *
     * @param fullName full name
     * @param email email address
     * @param contactNumber contact number
     * @param username username
     * @param password password
     * @param confirmPassword confirm password
     * @return true if input is valid
     */
    private boolean isValidInput(String fullName,
            String email,
            String contactNumber,
            String username,
            String password,
            String confirmPassword) {

        if (fullName.isEmpty()
                || email.isEmpty()
                || contactNumber.isEmpty()
                || username.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {

            showError("Please complete all fields.");
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            showError("Please enter a valid email address.");
            return false;
        }

        if (!contactNumber.matches("[0-9+ ]{7,15}")) {
            showError("Please enter a valid contact number.");
            return false;
        }

        if (username.length() < 4) {
            showError("Username must be at least 4 characters.");
            return false;
        }

        if (password.length() < 6) {
            showError("Password must be at least 6 characters.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return false;
        }

        return true;
    }

    /**
     * Opens the login screen.
     */
    private void openLoginScreen() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/drsinitial/view/LoginView.fxml"));

            Scene scene = new Scene(root);

            Stage stage = (Stage) fullNameField.getScene().getWindow();
            stage.setTitle("CrisisOps | Secure Login");
            stage.setScene(scene);
            stage.show();

        } catch (IOException exception) {
            showError("Unable to return to the sign-in screen.");
        }
    }

    /**
     * Shows an error message.
     *
     * @param message message to display
     */
    private void showError(String message) {
        registrationStatusLabel.setStyle("-fx-text-fill: #dc2626;");
        registrationStatusLabel.setText(message);
    }

    /**
     * Shows a success message.
     *
     * @param message message to display
     */
    private void showSuccess(String message) {
        registrationStatusLabel.setStyle("-fx-text-fill: #16a34a;");
        registrationStatusLabel.setText(message);
    }
}

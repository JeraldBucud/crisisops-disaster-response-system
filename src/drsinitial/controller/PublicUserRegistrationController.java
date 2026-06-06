package drsinitial.controller;

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
 * Controls the public user registration screen.
 *
 * This controller validates public user registration input. The account is
 * treated as PUBLIC_USER only.
 *
 * Backend database storage will be connected later.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class PublicUserRegistrationController {

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
        

        if (fullName.isEmpty()
                || email.isEmpty()
                || contactNumber.isEmpty()
                || username.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {

            showError("Please complete all fields.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            showError("Please enter a valid email address.");
            return;
        }

        if (!contactNumber.matches("[0-9+ ]{7,15}")) {
            showError("Please enter a valid contact number.");
            return;
        }

        if (username.length() < 4) {
            showError("Username must be at least 4 characters.");
            return;
        }

        if (password.length() < 6) {
            showError("Password must be at least 6 characters.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        showSuccess("Registration successful. Returning to login...");

        PauseTransition pause = new PauseTransition(Duration.seconds(1.2));
        pause.setOnFinished(event -> openLoginScreen());
        pause.play();
    }

    /**
     * Returns to the login screen.
     */
    @FXML
    private void handleBackToLogin() {
        openLoginScreen();
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
            stage.setTitle("DRS-Enhanced Login");
            stage.setScene(scene);
            stage.show();

        } catch (IOException exception) {
            showError("Unable to return to login screen.");
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

package drsinitial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the CrisisOps emergency operations application.
 *
 * This class loads the login screen first. After successful login,
 * the user is directed to the main dashboard.
 *
 * @author Jerald Christopher Bucud
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application window.
     *
     * @param stage primary JavaFX application stage
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/drsinitial/view/LoginView.fxml"));

        Scene scene = new Scene(loader.load());

        stage.titleProperty().addListener((observable, oldTitle, newTitle) -> {
            if (newTitle == null) {
                return;
            }

            String brandedTitle = newTitle
                    .replace("DRS-Enhanced Disaster Response System",
                            "CrisisOps Emergency Operations System")
                    .replace("DRS-Enhanced", "CrisisOps")
                    .replace("Disaster Response System",
                            "Emergency Operations System");

            if (!brandedTitle.equals(newTitle)) {
                stage.setTitle(brandedTitle);
            }
        });

        stage.setTitle("CrisisOps | Secure Login");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Main entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

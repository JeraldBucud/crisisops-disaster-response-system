package drsinitial;

import drsinitial.util.BrandingService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        Parent loginRoot = loader.load();
        BrandingService.applyCrisisOpsBranding(loginRoot);
        Scene scene = new Scene(loginRoot);

        stage.titleProperty().addListener((observable, oldTitle, newTitle) -> {
            if (newTitle == null) {
                return;
            }

            String brandedTitle = newTitle
                    .replace("DRS-Enhanced Disaster Response System", "CrisisOps")
                    .replace("DRS-Enhanced", "CrisisOps")
                    .replace("Disaster Response System",
                            "Emergency Operations and Disaster Response");

            if (!brandedTitle.equals(newTitle)) {
                stage.setTitle(brandedTitle);
            }
        });

        stage.sceneProperty().addListener((observable, oldScene, newScene) ->
                applyBranding(newScene));

        stage.setTitle("CrisisOps | Secure Login");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

        applyBranding(scene);
    }

    /**
     * Applies branding immediately and again on the next JavaFX pulse. This
     * ensures views loaded during navigation are also rebranded after their
     * controllers have completed initialisation.
     *
     * @param scene scene to rebrand
     */
    private void applyBranding(Scene scene) {
        if (scene == null || scene.getRoot() == null) {
            return;
        }

        BrandingService.applyCrisisOpsBranding(scene.getRoot());
        Platform.runLater(() -> {
            if (scene.getRoot() != null) {
                BrandingService.applyCrisisOpsBranding(scene.getRoot());
            }
        });
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

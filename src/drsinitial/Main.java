package drsinitial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the DRS-Initial Disaster Response System application.
 *
 * This class loads the main dashboard FXML file, creates the
 * JavaFX scene, and displays the main application window.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application window.
     *
     * This method loads the main dashboard view from the FXML file,
     * creates the scene, sets the application title, and shows the stage.
     *
     * @param stage primary JavaFX application stage
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/drsinitial/view/MainDashboard.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setTitle("DRS-Initial Disaster Response System");
        stage.setScene(scene);
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
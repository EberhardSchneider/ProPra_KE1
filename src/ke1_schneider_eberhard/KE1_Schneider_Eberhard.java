package ke1_schneider_eberhard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the main class. It creates the main window (primaryStage) and loads
 * the FXML for the Menus and the ScrollPane which will hold the FileCanvas on
 * which eventually is drawn.
 *
 * @author Eberhard Schneider
 */
public class KE1_Schneider_Eberhard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // to maximize the main window, we maximize the the stage
        primaryStage.setMaximized(true);

        // we connect a new scene with the fxml class Main_Window.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Scene.fxml"));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane, 300, 250);
        // and get the controller from the new FXML scene, so we can transmit
        // the primaryStage to it.
        // (the Main_SceneController needs the primaryStage in order to
        //  open the FileChooser dialog)
        Main_SceneController controller = loader.getController();
        controller.setStage(primaryStage);

        // set title for stage and show our scene on it
        primaryStage.setTitle("Generators");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author eberh_000
 */
public class KE1_Schneider_Eberhard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // um das Hauptwindow maximiert auszugeben, maximieren wir die Stage
        primaryStage.setMaximized(true);

        // we connect a new scene with the fxml class Main_Window.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Scene.fxml"));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane, 300, 250);
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

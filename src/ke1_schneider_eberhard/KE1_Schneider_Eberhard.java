/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author eberh_000
 */
public class KE1_Schneider_Eberhard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // to present the main window maximized, we maximize the stage
        primaryStage.setMaximized(true);

        // we connect a new scene with the fxml class Main_Window.fxml
        Parent root = FXMLLoader.load(getClass().getResource("Main_Scene.fxml"));
        Scene scene = new Scene(root, 300, 250);

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eberh_000
 */
public class Main_SceneController {

    @FXML
    ScrollPane canvasPane;

    @FXML
    Label statusBar;

    @FXML
    MenuItem menu_save;
    
    // wir sichern eine Referenz auf das Hauptfenster
    Stage primaryStage;
    
    // da die Referent aus der Main-Klasse heraus gesetzt werden muss
    // brauchen wir diese Methode, um sie zu setzen
    public void setStage(Stage stage ) {
        this.primaryStage = stage;
    }

    @FXML
    public void menuSaveImage() {
        statusBar.setText("Save Image");
        
         // neuen FileChooser erstellen
        FileChooser fc = new FileChooser();
        
        // Extensionen filtern
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("png files (*.png", "*.png");
        fc.getExtensionFilters().add( filter );
        // Zeige den Save Dialog
        File file = fc.showSaveDialog(primaryStage);
        FileCanvas canvas = (FileCanvas)canvasPane.getContent();
        canvas.saveContent( file );
    }

    @FXML
    void menuSimpleGenerator() {

        // set status bar text
        statusBar.setText("Simple Generator");
        
        

        // load new Scene for the Simple Generator Dialog
        GeneratorPane generatorPane = new GeneratorPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Generator_Scene.fxml"));
            generatorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        // give the Generator Dialog the ScrollPane in which it has to 
        // place the new Canvas Element
        generatorPane.setCanvasPane( canvasPane );

        // set new stage i.e. new window for the dialog
        Stage dialogStage = new Stage();
        Scene dialogScene = new Scene(generatorPane);
        dialogStage.setScene(dialogScene);

        dialogStage.show();

    }

    @FXML
    public void initialize() {

    }

}

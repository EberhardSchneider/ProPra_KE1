/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import java.io.File;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;


/**
 * Diese Klasse erweitert Canvas um Funktionen zum Laden/Speichern des
 * Inhalts
 * @author eberh_000
 */
public class FileCanvas extends Canvas {
    
    private int width;
    private int height;
    
    // wir benötigen einen Zeiger auf die Stage, um den File Dialog öffnen
    // zu können
    private Stage primaryStage;
    
    // Der Constructor setzt die Werte für width und height
    public FileCanvas( int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setStage( Stage stage ) {
        this.primaryStage = stage;
    }
    
    /**
     * Speichert den Inhalt der Zeichenfläche in eine Datei, die vom Benutzer
     * durch einen FileChooser ausgewählt wird.
     */
    
    public void saveContent() {
        
        // neuen FileChooser erstellen
        FileChooser fc = new FileChooser();
        
        // Extensionen filtern
        ExtensionFilter filter = new ExtensionFilter("png files (*.png", "*.png");
        fc.getExtensionFilters().add( filter );
        // Zeige den Save Dialog
        File file = fc.showSaveDialog(primaryStage);
        
        // wir werden die snapshot() Methode benutzen,
        // dafür benötigen zuerst ein writableImage
        WritableImage image = new WritableImage( width, height );
        
        
    }
    
    
    
}

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
    
    
    // Der Constructor setzt die Werte für width und height
    public FileCanvas( int width, int height) {
        this.width = width;
        this.height = height;
    }

    

    /**
     * Speichert den Inhalt der Zeichenfläche in eine Datei, die vom Benutzer
     * durch einen FileChooser ausgewählt wird.
     */
    
    public void saveContent( File file ) {
        
       
        
        // wir werden die snapshot() Methode benutzen,
        // dafür benötigen zuerst ein writableImage
        WritableImage image = new WritableImage( width, height );
        
        
    }
    
    
    
}

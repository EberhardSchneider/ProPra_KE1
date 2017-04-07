/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Diese Klasse erweitert Canvas um Funktionen zum Laden/Speichern des Inhalts
 *
 * @author eberh_000
 */
public class FileCanvas extends Canvas {

    private int width;
    private int height;

    // Der Constructor setzt die Werte für width und height
    public FileCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.setWidth((double) width);
        this.setHeight((double) height);
    }

    /**
     * Speichert den Inhalt der Zeichenfläche in eine Datei, die vom Benutzer
     * durch einen FileChooser ausgewählt wird.
     */
    public void saveContent(File file) {

        // wir werden die snapshot() Methode benutzen,
        // dafür benötigen zuerst ein writableImage
        WritableImage image = new WritableImage(width, height);
        this.snapshot(null, image);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);

        try {
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            System.out.println("Bild konnte nicht gespeichert werden:");
            e.printStackTrace(System.err);
        }

    }

}

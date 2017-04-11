/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * GridPane, das den Generator Dialog beinhaltet und eine Methode generate() zur
 * Verfügung stellt, die eine Zeichenfläche erstellt, bemalt und eine Referenz
 * darauf zurückgibt
 *
 * @author Eberhard Schneider
 */
// wenn es mehrere Generatoren gibt, könnte dies als Modell eines
// Interfaces / einer abstrakten Klasse dienen
public class GeneratorPane extends GridPane {

    /**
     * gibt eine Referenz auf einen FileCanvas zurück, auf den ein blauer Kreis
     * mit Radius radius gezeichnet wurde
     *
     * @param radius Radius des Kreises
     * @param width Breite der zu erzeugenden Zeichenfläche
     * @param height Höhe der zu erzeugenden Zeichenfläche
     * @return bemalte Zeichenfläche
     */
    public FileCanvas generate(double radius, int width, int height) {

        FileCanvas canvas = new FileCanvas(width, height);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillOval(140, 130, radius, radius);

        return canvas;
    }

}

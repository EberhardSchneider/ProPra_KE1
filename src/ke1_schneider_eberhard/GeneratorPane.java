/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author eberh_000
 */
// this could be an interface/abstract, when in the end
// there are parameters
public class GeneratorPane extends GridPane {

    // GeneratorPane speichert eine Referenz auf das Elternelement des
    // zu generierenden Canvas, hier das ScrollPane des Hauptfensters
    ScrollPane canvasPane;

    public void setCanvasPane(ScrollPane pane) {
        this.canvasPane = pane;
    }

    public void generate(double radius, int width, int height) {
        if (canvasPane == null) {
            System.out.println("GeneratorPane: canvas is not set, can't draw");
            return;
        }

        FileCanvas canvas = new FileCanvas(width, height);
        canvasPane.setContent(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillOval(140, 130, radius, radius);
    }

}

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
import javafx.scene.shape.Circle;

/**
 *
 * @author eberh_000
 */
// this could be an interface/abstract, when in the end
// there are parameters
public class GeneratorPane extends GridPane {

    // every generator needs something to paint on
    ScrollPane canvasPane;

    // constructor is not used, but if it's used in the future
    // it should get the canvas
    public void setCanvasPane(ScrollPane canvasPane) {
        this.canvasPane = canvasPane;
    }

    public ScrollPane getCanvasPane() {
        return this.canvasPane;
    }

    public void draw(int radius) {
        if (canvasPane == null) {
            System.out.println("GeneratorPane: canvas is not set, can't draw");
            return;
        }
        Canvas canvas = (Canvas)canvasPane.getContent();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillOval(140, 130, radius, radius);
    }

}

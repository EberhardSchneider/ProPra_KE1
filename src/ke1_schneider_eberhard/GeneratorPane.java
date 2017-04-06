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
    Canvas canvas;

    // constructor is not used, but if it's used in the future
    // it should get the canvas
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void draw(int radius) {
        if (canvas == null) {
            System.out.println("GeneratorPane: canvas is not set, can't draw");
            return;
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillOval(140, 130, radius, radius);
    }

}

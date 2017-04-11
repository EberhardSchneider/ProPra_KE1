
package ke1_schneider_eberhard;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * 
 * GeneratorPane is a GridPane which holds the generator dialog. 
 * The method generate() creates a FileCanvas, draws on it and returns
 * a reference to it.
 *
 * @author Eberhard Schneider
 */

// when there are multiple Generators, there should be an interface
// Generator with the generate() method.

public class GeneratorPane extends GridPane {

    /**
     * returns a FileCanvas with width and height from the parameters
     * on which is drawn a blue circle with radius radius
     *
     * @param radius circle radius
     * @param width of created canvas
     * @param height of created canvas
     * @return FileCanvas with a blue circle
     */
    public FileCanvas generate(double radius, int width, int height) {
        
        // clamp values in 'acceptable' range
        width = (width < 1) ? 1 : width;
        height = (height < 1 ) ? 1: height;
        width = (width > FileCanvas.MAX_WIDTH ) ? FileCanvas.MAX_WIDTH : width;
        height = (height > FileCanvas.MAX_HEIGHT ) ? FileCanvas.MAX_HEIGHT : height;
        
        // create new canvas
        FileCanvas canvas = new FileCanvas(width, height);
        

        // create GraphicsContext, so we can draw on the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE); // set color
        // and draw circle in the middle
        double xCoord = width / 2;
        double yCoord = height / 2;
        
        gc.fillOval( xCoord-radius, yCoord-radius, 2 * radius, 2 * radius); 

        return canvas;
    }

}

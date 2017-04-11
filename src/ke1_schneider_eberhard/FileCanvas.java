package ke1_schneider_eberhard;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * This class gives the Canvas class the possibility to store its content.
 * ( TODO: implement loadContent(), not necessary in the moment )
 *
 * @author Eberhard Schneider
 */
public class FileCanvas extends Canvas {
    
    static final int MAX_WIDTH = 16000;
    static final int MAX_HEIGHT = 9000;
    

    final private int width;  // width
    final private int height; // and height of canvas

    /**
     * Constructor just sets height and width.
     * @param width of canvas
     * @param height of canvas
     */
    public FileCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.setWidth((double) width);
        this.setHeight((double) height);
    }

    /**
     * Stores the content of the Canvas in a '.png'-File.
     * 
     * @param file The file in which the content is stored.
     * @return true if file is stored succesfully,
     *         false otherwise
     */
    public boolean saveContent(File file) {

        // we use the Canvas.snapshot() method,
        // so we need a writableImage
        WritableImage image = new WritableImage(width, height);
        
        // we take the snapshot and transform the result to a Rendered Image
        this.snapshot(null, image);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);

        // and we write the Image to file
        try {
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            System.out.println("FileCanvas: Image couldn't be stored.");
            e.printStackTrace(System.err);
            return false;
        }
        
        return true;    // Image stored succesfully
    }

}

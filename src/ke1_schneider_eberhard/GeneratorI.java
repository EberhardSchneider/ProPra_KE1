package ke1_schneider_eberhard;

import javafx.scene.Scene;

/**
 * interface for all future generators
 *
 * @author Eberhard Schneider
 */
public interface GeneratorI {

    // this methods creates a canvas, generates a drawing then returns
    // a reference on it
    public FileCanvas generate();

    // returns the dialog, which sets the parameter for the generate method,
    // as a Scene
    public Scene getDialogScene();

}

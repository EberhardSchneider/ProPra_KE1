package ke1_schneider_eberhard;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class for the Main Window. here is the main organization and
 * and mandages menu functionality
 *
 * @author Eberhard Schneider
 */
public class Main_SceneController {

    //the ScrollPane which wraps the FileCanvas, on which is painted
    @FXML
    ScrollPane canvasWrapper;

    // the status bar
    @FXML
    Label statusBar;

    // the MenuItem 'Save Image'
    // we need this to be able to enable it, as soon as a canvas is created
    @FXML
    MenuItem menu_save;

    // stores wether SimpleGenerator is open or not
    // to be shure it is not opened twice
    private boolean isGeneratorOpen = false;

    // we store a reference on the main window (primary Stage)
    // we need this to open the FileChooser Dialog
    Stage primaryStage;

    // setter method for the primaryStage reference
    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    // the slider which changes the radius of the circle
    private Slider radiusSlider;

    /**
     * is called when MenuItem File->Save Image is chosen. stores the content of
     * the canvas in a .png File, which is selected by the user through a
     * FileChooser dialog
     *
     * TODO: implement possibility to store jpg and gif
     */
    @FXML
    public void menuSaveImage() {
        // the store method is in the FileCanvas class. every generator can
        // use it.

        // set status
        statusBar.setText("Bild wird gespeichert.");

        // first we get the canvas
        FileCanvas canvas;
        try {
            canvas = (FileCanvas) canvasWrapper.getContent();
        } catch (NullPointerException e) {
            canvas = null; // if a canvas is not yet created
        }

        // oops.. no canvas there, so we return
        if (canvas == null) {
            return;
        }

        // create new FileChooser dialog
        FileChooser fc = new FileChooser();

        // only allow the extension 'png'
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("png Files", "*.png");
        fc.getExtensionFilters().add(filter);

        // show the dialog
        File file = fc.showSaveDialog(primaryStage);

        // first we check if a file was chosen, if not, we do nothing
        if (file != null) {
            // call saveContent() method of FileCanvas to store canvas in file
            canvas.saveContent(file);
        }

        // set status
        statusBar.setText("Bild gespeichert.");
    }

    /**
     * called when File->Quit is chosen. closes the app.
     */
    @FXML
    void menuExit() {
        System.exit(0);
    }

    /**
     * called, when Generators->Simple Generator is chosen creates the
     * SimpleGenerator dialog.
     */
    @FXML
    void menuSimpleGenerator() {

        // if a Generator dialog is already open, we return
        if (isGeneratorOpen) {
            return;
        }

        // now we open one, so we set the variable to true
        isGeneratorOpen = true;

        // set status
        statusBar.setText("Simple Generator ready.");

        // we build a new Stage (Window) for the dialog
        Stage dialogStage = new Stage();

        // as soon as the dialog is closed, we have to reset
        // the isGeneratorOpen-variable
        // we do this by a listener
        dialogStage.setOnCloseRequest(event -> {
            this.isGeneratorOpen = false;
        });

        IGenerator simpleGenerator = new SimpleGenerator(statusBar, canvasWrapper, menu_save);
        // we put the generatorPane on our stage
        Scene dialogScene = simpleGenerator.getDialogScene();
        dialogStage.setScene(dialogScene);

        // and show the stage
        dialogStage.show();

    }

    @FXML
    public void initialize() {

    }

}

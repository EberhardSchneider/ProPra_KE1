package ke1_schneider_eberhard;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class for the Main Window
 * here is the main organization and the implementation of 
 * the menu functionality
 *
 * @author Eberhard Schneider
 */
public class Main_SceneController {

    //the ScrollPane which wraps the FileCanvas, which is yet to be created
    @FXML
    ScrollPane canvasPane;

    // the status bar
    @FXML
    Label statusBar;

    // the MenuItem 'Save Image'
    // we need this to be able to enable it, as soon as a canvas is created
    @FXML
    MenuItem menu_save;

    // stores if SimpleGenerator is open or not
    // to be shure it is not opened twice
    private boolean isSimpleGeneratorDialogOpen = false;


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
     * is called when MenuItem File->Save Image is chosen
     * stores the content of the canvas in a .png File, which is selected
     * by the user through a FileChooser dialog
     *
     * TODO: implement possibility to store jpg and gif
     */
    @FXML
    public void menuSaveImage() {

        // set status
        statusBar.setText("Bild wird gespeichert."); 
        
        // first we get the canvas
        FileCanvas canvas;
        try {
            canvas = (FileCanvas) canvasPane.getContent();
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
            // call saveContent() method of FileCanvas
            canvas.saveContent(file);
        }

        // set status
        statusBar.setText("Bild gespeichert.");
    }

    /**
     * called when File->Quit is chosen.
     *
     * closes the app.
     */
    @FXML
    void menuExit() {
        System.exit(0);
    }

    /**
     * called, when Generators->Simple Generator is chosen
     *
     * creates the SimpleGenerator dialog
     */
    @FXML
    void menuSimpleGenerator() {

        // if a SimpleGenerator dialog is already open, we return
        if (isSimpleGeneratorDialogOpen) {
            return;
        }

        // now we open one, so we set the variable to true
        isSimpleGeneratorDialogOpen = true;

        // set status
        statusBar.setText("Simple Generator ready.");

  
        // GeneratorPane is a GridPane, which contains the Generator Dialog
        // and the generate() method, which returns a canvas and a grpahic on it
        
        GeneratorPane generatorPane = new GeneratorPane();

        // we build the Generator Dialog here, and not in FXML
        // this is much simpler, but for modularity's sake it should be
        // changed, when there are more Generators
        // 1) by a getDialog() method of the GeneratorPane
        // or 2) build everything in FXML
        
        // Slider to change the radius of the circle
        radiusSlider = new Slider();
        radiusSlider.setMin(5);
        radiusSlider.setMax(300);
        radiusSlider.setMajorTickUnit(5);
        radiusSlider.setValue(100);
      
        // and the label for the Slider
        Label labelSlider = new Label("Radius");

        // two TextFields to enter width and height
        Label labelHeight = new Label("Height");
        TextField textfieldHeight = new TextField();
        Label labelWidth = new Label("Width");
        TextField textfieldWidth = new TextField();

        // set standard
        textfieldHeight.setText("400");
        textfieldWidth.setText("600");

        // the generate Button
        Button button = new Button();
        button.setText("Generate");
        
        // is called when the 'Generate' button is clicked
        // reads parameters from the dialog and calls
        // the generate() method of the generatorPane
        button.setOnAction(event -> {

             // set status
             statusBar.setText("Calculating.");
             
            // get width and height
            int width;
            int height;
            
            // contents of textfields are parsed
            // if no integer values are found there, we set
            // a status message and return 
            try {
                String widthString = textfieldWidth.getText();
                String heightString = textfieldHeight.getText();
                width = Integer.parseInt(widthString);
                height = Integer.parseInt(heightString);
            } catch (NumberFormatException e) {
                statusBar.setText("Bitte Integer Werte eingeben.");
                return;
            }
            
            // get the radius from the slider
            double circleRadius = radiusSlider.valueProperty().get();
            
            // now we clamp the values width and height to the right ranges
            width = (width < 1) ? 1 : width;
            height = (height < 1 ) ? 1: height;
            width = (width > FileCanvas.MAX_WIDTH ) ? FileCanvas.MAX_WIDTH : width;
            height = (height > FileCanvas.MAX_HEIGHT ) ? FileCanvas.MAX_HEIGHT : height;

            // we generate the Canvas and show it in our scrollPane
            FileCanvas generatedCanvas = generatorPane.generate(circleRadius, width, height);
            canvasPane.setContent(generatedCanvas);
            
            // now we can enable the 'Save Image' menu
            menu_save.setDisable( false );
        });
        
        // now we build the dialog by adding all elements

        generatorPane.setPrefSize(400, 200);

        generatorPane.getChildren().add(radiusSlider);
        generatorPane.getChildren().add(labelSlider);
        generatorPane.getChildren().add(button);
        generatorPane.getChildren().add(labelHeight);
        generatorPane.getChildren().add(labelWidth);
        generatorPane.getChildren().add(textfieldHeight);
        generatorPane.getChildren().add(textfieldWidth);

        // and keep everything ordered
        GridPane.setRowIndex(radiusSlider, 0);
        GridPane.setRowIndex(labelSlider, 0);
        GridPane.setColumnIndex(radiusSlider, 1);
        GridPane.setRowIndex(labelHeight, 1);
        GridPane.setRowIndex(textfieldHeight, 1);
        GridPane.setRowIndex(textfieldWidth, 2);
        GridPane.setRowIndex(labelWidth, 2);
        GridPane.setColumnIndex(textfieldHeight, 1);
        GridPane.setColumnIndex(textfieldWidth, 1);

        GridPane.setRowIndex(button, 3);
        GridPane.setColumnIndex(button, 1);

        generatorPane.setHgap(30.0);
        generatorPane.setVgap(30.0);

        // we build a new Stage / Window for the dialog
        Stage dialogStage = new Stage();

        // as soon as the dialog is closed, we have to reset
        // the isSimpleGeneratorDialogOpen-variable
        // we do this by a listener
        dialogStage.setOnCloseRequest(event -> {
            this.isSimpleGeneratorDialogOpen = false;
        });

        // we put the generatorPane on our Stage
        Scene dialogScene = new Scene(generatorPane);
        dialogStage.setScene(dialogScene);

        // and show it
        dialogStage.show();

    }

    @FXML
    public void initialize() {

    }

}

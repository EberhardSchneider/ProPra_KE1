package ke1_schneider_eberhard;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * this class is our first generator it just draws a blue circle in the middle
 * of the canvas radius is adjustable by a slider
 *
 * @author Eberhard Schneider
 */
public class SimpleGenerator implements GeneratorI {

    // reference to the main window status bar to put messages there
    Label statusBar;

    // reference to the main window scroll pane to put the canvas in it
    ScrollPane canvasPane;

    // now the parameters for the generate method
    // we will set them in Listeners in the dialog.
    // width and height of the created canvas
    int width = 600;
    int height = 400;

    // radius of the circle
    int circleRadius = 100;

    // the constructor sets the references to the main window status bar and the
    // scroll pane in which the painted canvas is shown
    public SimpleGenerator(Label statusBar, ScrollPane canvasPane) {
        this.statusBar = statusBar;
        this.canvasPane = canvasPane;
    }

    /**
     * builds the dialog
     *
     * @return Scene which contains the dialog
     */
    @Override
    public Scene getDialogScene() {
        // this gridpane will contain everything we need
        GridPane generatorPane = new GridPane();

        // Slider to change the radius of the circle
        Slider radiusSlider = new Slider();
        radiusSlider.setMin(5);
        radiusSlider.setMax(300);
        radiusSlider.setMajorTickUnit(5);
        radiusSlider.setValue(100);

        // set the change Listener for the radius
        radiusSlider.valueProperty().addListener(event -> {
            this.circleRadius = (int) radiusSlider.valueProperty().get();
        });

        // and the label for the Slider
        Label labelSlider = new Label("Radius");

        // two TextFields to enter width and height
        Label labelHeight = new Label("Height");
        TextField textfieldHeight = new TextField();
        Label labelWidth = new Label("Width");
        TextField textfieldWidth = new TextField();

        // set the change Listeners for width and height
        textfieldHeight.textProperty().addListener(event -> {
            try {
                String heightString = textfieldHeight.textProperty().get();
                this.height = Integer.parseInt(heightString);
            } catch (NumberFormatException e) {
                // if there is no integer, we leave everything as it is
            }
        });
        textfieldWidth.textProperty().addListener(event -> {
            try {
                String widthString = textfieldWidth.textProperty().get();
                this.width = Integer.parseInt(widthString);
            } catch (NumberFormatException e) {
                // if there is no integer, we leave everything as it is
            }
        });

        // set standard values
        textfieldHeight.setText("400");
        textfieldWidth.setText("600");

        // the generate Button
        Button button = new Button();
        button.setText("Generate");

        // is called when the 'Generate' button is clicked
        // calls the generate() method and puts the
        // resulting canvas in the main window
        button.setOnAction(event -> {

            FileCanvas generatedCanvas = this.generate();
            canvasPane.setContent(generatedCanvas);

            // TODO: enable Save Image (listen to a variable?)
        });

        // now we build the dialog by adding all elements to the gridpane
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

        // we build the scene and give it back
        Scene dialogScene = new Scene(generatorPane);

        return dialogScene;
    }

    /**
     * generates a new canvas on which is drawn a blue circle
     *
     * @return FileCanvas with a blue circle
     */
    @Override
    public FileCanvas generate() {

        // clamp values in 'acceptable' range
        width = (width < 1) ? 1 : width;
        height = (height < 1) ? 1 : height;
        width = (width > FileCanvas.MAX_WIDTH) ? FileCanvas.MAX_WIDTH : width;
        height = (height > FileCanvas.MAX_HEIGHT) ? FileCanvas.MAX_HEIGHT : height;

        // create new canvas
        FileCanvas canvas = new FileCanvas(width, height);

        // create GraphicsContext, so we can draw on the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE); // set color

        // and draw circle in the middle
        double xCoord = width / 2;
        double yCoord = height / 2;

        gc.fillOval(xCoord - circleRadius, yCoord - circleRadius, 2 * circleRadius, 2 * circleRadius);

        return canvas;
    }
}

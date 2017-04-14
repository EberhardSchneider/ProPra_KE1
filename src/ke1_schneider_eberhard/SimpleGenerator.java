package ke1_schneider_eberhard;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * this class is our first generator it just draws a blue circle in the middle
 * of the canvas radius is adjustable by a slider
 *
 * @author Eberhard Schneider
 */
public class SimpleGenerator implements IGenerator {

    // reference to the main window status bar to put messages there
    Label statusBar;

    // reference to the main window scroll pane to put the canvas in it
    ScrollPane canvasWrapper;

    // reference to the MenuItem Save Image, because we want to enable it
    // when a canvas is created ( is there a better way???)
    MenuItem menuSave;
    // now the parameters for the generate method
    // we will set them in Listeners in the dialog.
    // width and height of the created canvas
    int width = 600;
    int height = 400;

    // radius of the circle
    int circleRadius = 100;

    // the constructor sets the references to the main window status bar and the
    // scroll pane in which the painted canvas is shown
    public SimpleGenerator(Label statusBar, ScrollPane canvasPane, MenuItem menuSave) {
        this.statusBar = statusBar;
        this.canvasWrapper = canvasPane;
        this.menuSave = menuSave;
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

        // Title
        Label title = new Label("Simple Generator");
        title.setStyle("-fx-font: 25px Mondana");

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

            statusBar.setText("Calculating image.");

            FileCanvas generatedCanvas = this.generate();
            canvasWrapper.setContent(generatedCanvas);

            String info = "Image ( " + Integer.toString(width) + "px x "
                    + Integer.toString(height) + "px ) created.";
            statusBar.setText(info);
            // enable Save Image in Drop Down Menu
            menuSave.setDisable(false);
        });

        // now we build the dialog by adding all elements to the gridpane
        generatorPane.setPrefSize(300, 400);

        generatorPane.add(title, 0, 0, 3, 1);
        generatorPane.add(radiusSlider, 1, 1);
        generatorPane.add(labelSlider, 0, 1);
        generatorPane.add(button, 1, 4);
        generatorPane.add(labelHeight, 0, 2);
        generatorPane.add(labelWidth, 0, 3);
        generatorPane.add(textfieldHeight, 1, 2);
        generatorPane.add(textfieldWidth, 1, 3);

        // we give the colums the right size
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPrefWidth(150);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPrefWidth(200);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPrefWidth(150);
        generatorPane.getColumnConstraints().addAll(column1, column2, column3);
        // and put a little space between the lines
        generatorPane.setVgap(30);

        generatorPane.setPadding(new Insets(20, 20, 20, 20));

        // we build the scene and return it
        Scene dialogScene = new Scene(generatorPane);

        return dialogScene;
    }

    /**
     * generates a new canvas on which is drawn a blue circle Parameters are
     * class attributs and changed by EventListeners
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

        // set color for background and draw it
        // (to show the user the size of the canvas)
        gc.setFill(Color.web("#EEEEFF"));
        gc.fillRect(0, 0, width, height);

        // set color for the circle
        gc.setFill(Color.BLUE); // set color
        // and draw circle in the middle
        double xCoord = width / 2;
        double yCoord = height / 2;

        gc.fillOval(xCoord - circleRadius, yCoord - circleRadius, 2 * circleRadius, 2 * circleRadius);

        return canvas;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * FXML Controller class
 *
 * @author eberh_000
 */
public class Main_SceneController {

    // Referenz auf das ScrollPane, das die Zeichenfläche enthalten wird
    @FXML
    ScrollPane canvasPane;

    // Referenz auf die Statusanzeige
    @FXML
    Label statusBar;

    // Referenz auf den Menüpunkt: File -> Save
    @FXML
    MenuItem menu_save;

    // speichert, ob der SimpleGenerator Dialog schon geöffnet wurde
    private boolean isSimpleGeneratorDialogOpen = false;

    // der Radius des zu zeichnenden Kreises
    private double circleRadius;

    // wir sichern eine Referenz auf das Hauptfenster, um später
    // den FileChooser Dialog öffnen zu können
    Stage primaryStage;

    // da die Referenz aus der Main-Klasse heraus gesetzt werden muss
    // brauchen wir diese Methode, um sie zu setzen
    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * wird aufgerufen, wenn der Menüpunkt File->Save ausgewählt wird und
     * speichert den Inhalt der Zeichenfläche in einer .png Datei, die durch
     * einen FileChooser vom Benutzer ausgewählt wird.
     *
     */
    @FXML
    public void menuSaveImage() {

        // Status aktualisieren
        statusBar.setText("Bild wird gespeichert."); // die Zeichenfläche holen (sie ist content des ScrollPanes canvasPane)
        FileCanvas canvas;
        try {
            canvas = (FileCanvas) canvasPane.getContent();
        } catch (NullPointerException e) {
            canvas = null; // wenn noch keine Zeichenfläche erzeugt wurde
        }

        // falls keine Zeichenfläche vorhanden ist, wurde noch kein Bild generiert
        // in diesem Falle abbrechen
        if (canvas == null) {
            return;
        }

        // neuen FileChooser erstellen
        FileChooser fc = new FileChooser();

        // Extensionen filtern
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fc.getExtensionFilters().add(filter);

        // Zeige den Save Dialog
        File file = fc.showSaveDialog(primaryStage);

        // Falls ein Speicherort ausgewählt wurde
        if (file != null) {
            // die save-Methode der Zeichenfläche ausführen
            canvas.saveContent(file);
        }

        // Status aktualisieren
        statusBar.setText("Bild gespeichert.");
    }

    /**
     * Wird aufgerufen, sobald File->Exit ausgewählt wird
     *
     * schließt die Anwendung
     */
    @FXML
    void menuExit() {
        System.exit(0);
    }

    /**
     * wird aufgerufen, sobald Generator->Simple Generator ausgewählt wied
     *
     * erstellt den Simple Generator Dialog
     */
    @FXML
    void menuSimpleGenerator() {

        // wenn schon ein Simple Generator Dialog geöffnet ist, brechen wir
        // sofort ab
        if (isSimpleGeneratorDialogOpen) {
            return;
        }

        // da aber jetzt einer geöffnet wird, speichern wir das
        isSimpleGeneratorDialogOpen = true;

        // Status aktualisieren
        statusBar.setText("Simple Generator");

        // GeneratorPane ist ein GridPane, das den Generator Dialog enthält
        // und die Methode generate() zur Verfügung stellt, um eine Zeichenfläche
        // und eine Grafik auf dieser zu erstellen
        GeneratorPane generatorPane = new GeneratorPane();

        // der Generator Dialog wird hier gebaut und nicht mit FXML,
        // da ich keine Lösung gefunden habe,
        // FXML Loader
        // Slider to change the radius of the circle
        Slider slider = new Slider();
        slider.setMin(5);
        slider.setMax(300);
        slider.setMajorTickUnit(5);
        slider.setValue(100);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.circleRadius = (double) newValue;
        });

        // and the label for the Slider
        Label labelSlider = new Label("Radius");

        // two TextFields to enter width and height
        Label labelHeight = new Label("Height");
        TextField textfieldHeight = new TextField();
        Label labelWidth = new Label("Width");
        TextField textfieldWidth = new TextField();

        // Standardwerte setzen
        textfieldHeight.setText("400");
        textfieldWidth.setText("600");

        // the generate Button
        Button button = new Button();
        button.setText("Generate");
        button.setOnAction(event -> {

            // get width and height
            int width;
            int height;
            // der Inhalt der TextFields für Höhe und Breit wird in die
            // entsprechenden Variablen übergeben, falls keine Integers
            // dort zu finden sind, brechen wir ab.
            try {
                String widthString = textfieldWidth.getText();
                String heightString = textfieldHeight.getText();
                width = Integer.parseInt(widthString);
                height = Integer.parseInt(heightString);
            } catch (NumberFormatException e) {
                statusBar.setText("Bitte Integer Werte eingeben.");
                return;
            }
            FileCanvas generatedCanvas = generatorPane.generate(circleRadius, width, height);
            canvasPane.setContent(generatedCanvas);
        });

        generatorPane.setPrefSize(400, 200);

        generatorPane.getChildren().add(slider);
        generatorPane.getChildren().add(labelSlider);
        generatorPane.getChildren().add(button);
        generatorPane.getChildren().add(labelHeight);
        generatorPane.getChildren().add(labelWidth);
        generatorPane.getChildren().add(textfieldHeight);
        generatorPane.getChildren().add(textfieldWidth);

        GridPane.setRowIndex(slider, 0);
        GridPane.setRowIndex(labelSlider, 0);
        GridPane.setColumnIndex(slider, 1);
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

        // neue Stage, d.h. neues Fenster für den Generator Dialog
        Stage dialogStage = new Stage();

        // sobald der Dialog wieder geschlossen wird, setzen wir die Variable
        // isSimpleGeneratorDialogOpen wieder auf false
        dialogStage.setOnCloseRequest(event -> {
            this.isSimpleGeneratorDialogOpen = false;
        });

        Scene dialogScene = new Scene(generatorPane);
        dialogStage.setScene(dialogScene);

        dialogStage.show();

    }

    @FXML
    public void initialize() {

    }

}

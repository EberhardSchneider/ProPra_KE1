/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke1_schneider_eberhard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

/**
 * FXML Controller class
 *
 * @author eberh_000
 */
public class Generator_SceneController implements Initializable {

    public GeneratorPane generatorPane;
    public Slider sliderRadius;

    private int circleRadius = 50;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // create EventListener for the Slider
        sliderRadius.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.circleRadius = (int) Math.floor((double) newValue);
        });

    }

    public void btnGeneratePressed(ActionEvent event) {
        
        generatorPane.draw(this.circleRadius);
    }

}

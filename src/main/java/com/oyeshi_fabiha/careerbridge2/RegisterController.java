package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;

public class RegisterController {

    @FXML
    private Button mybtn;

    @FXML
    private void handleOpacity(){

        mybtn.setOpacity(0.6d);

    }
}

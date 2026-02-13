package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentLoginController {

    @FXML private TextField studentIdField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleStudentLogin() {
        String id = studentIdField.getText();
        String pass = passwordField.getText();

        if (id.equals("oyeshi") && pass.equals("12345678")) {
            System.out.println("Log-in successful");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Wrong credentials");
            alert.show();
        }
    }

    public void handleNewRegister(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Scene scene = new Scene(loader.load());
            Stage currStage=(Stage) passwordField.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
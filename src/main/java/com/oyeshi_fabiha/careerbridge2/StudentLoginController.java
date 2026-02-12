package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class StudentLoginController {

    @FXML private TextField studentIdField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleStudentLogin() {
        String id = studentIdField.getText();
        String pass = passwordField.getText();

        // Placeholder for real database validation
        if (!id.isEmpty() && !pass.isEmpty()) {
            System.out.println("Student Login Attempt: " + id);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Fields cannot be empty!");
            alert.show();
        }
    }
}
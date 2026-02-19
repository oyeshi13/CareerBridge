/*package com.oyeshi_fabiha.careerbridge2;

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
} /*package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentLoginController {

    @FXML
    private TextField studentidField; // This must match the fx:id in Scene Builder

    @FXML
    private PasswordField passwordField; // This must match the fx:id in Scene Builder

    @FXML
    private void handleStudentLogin() {
        String idInput = studentidField.getText().trim();
        String passInput = passwordField.getText().trim();

        if (idInput.isEmpty() || passInput.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Login Error", "Please fill in all fields.");
            return;
        }

        // Checks index 2 and 3 in students.txt to match your registration order
        if (FileHandler.verifyLogin(idInput, passInput)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Email or Password.");
        }
    }

    @FXML
    public void handleNewRegister(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Scene scene = new Scene(loader.load());
            Stage currStage = (Stage) passwordField.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} */package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentLoginController {

    @FXML
    public TextField studentidField; // Changed to public for better injection visibility

    @FXML
    public PasswordField passwordField; // Changed to public for better injection visibility

    @FXML
    private void handleStudentLogin() {
        // Safe check to see if injection worked
        System.out.println("Button clicked!"); // Proof the button works
        if (studentidField == null) {
            System.out.println("studentidField is NULL - check FXML/Controller link!");
            return;
        }
        if (studentidField == null || passwordField == null) {
            System.out.println("CRITICAL ERROR: UI fields are NULL. Check fx:id in Scene Builder.");
            return;
        }

        String idInput = studentidField.getText().trim();
        String passInput = passwordField.getText().trim();

        if (idInput.isEmpty() || passInput.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Login Error", "Please fill in all fields.");
            return;
        }

        // Verify using FileHandler
        if (FileHandler.verifyLogin(idInput, passInput)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Email or Password.");
        }
    }

    @FXML
    public void handleNewRegister(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Scene scene = new Scene(loader.load());
            // Use the field to get the current window
            Stage currStage = (Stage) studentidField.getScene().getWindow();
            currStage.setScene(scene);
            currStage.setTitle("Register - Career Bridge");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
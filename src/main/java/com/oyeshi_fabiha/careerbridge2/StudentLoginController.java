package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentLoginController {

    @FXML
    public TextField studentidField;

    @FXML
    public PasswordField passwordField;

    @FXML
    private void handleStudentLogin(ActionEvent event) {
        if (studentidField == null || passwordField == null) {
            System.out.println("CRITICAL ERROR: UI fields are NULL. Check fx:id in FXML.");
            return;
        }

        String idInput = studentidField.getText().trim();
        String passInput = passwordField.getText().trim();

        if (idInput.isEmpty() || passInput.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Login Error", "Please fill in all fields.");
            return;
        }

        // Using your existing FileHandler for student verification
        if (FileHandler.verifyLogin(idInput, passInput)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");
            // Switch to student dashboard/home here if needed
            // switchScene(event, "student-home.fxml", "Student Dashboard");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid ID or Password.");
        }
    }

    public void handleNewRegister(ActionEvent event) {
        // Redirects to the student registration page
        switchScene(event, "register.fxml", "Student Register - Career Bridge");
    }

    private void switchScene(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currStage.setScene(new Scene(root));
            currStage.setTitle(title);
            currStage.show();
        } catch (IOException e) {
            System.err.println("Error loading " + fxmlFile);
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
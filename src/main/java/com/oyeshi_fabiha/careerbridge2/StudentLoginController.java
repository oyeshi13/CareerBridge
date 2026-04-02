package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentLoginController {

    @FXML private TextField studentidField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleStudentLogin(ActionEvent event) {
        String email = studentidField.getText().trim();
        String pass  = passwordField.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Info", "Please enter email and password.");
            return;
        }

        if (FileHandler.verifyLogin(email, pass)) {
            String name = FileHandler.getStudentName(email);
            Session.get().login(email, name, Session.Role.STUDENT);
            SceneHelper.switchTo(event, "student-home.fxml", "Student Dashboard");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password.");
        }
    }

    @FXML
    public void handleNewRegister(ActionEvent event) {
        SceneHelper.switchTo(event, "register.fxml", "Student Registration");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneHelper.switchTo(event, "entry.fxml", "Welcome");
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
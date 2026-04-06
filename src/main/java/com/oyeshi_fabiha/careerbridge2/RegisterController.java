package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    // Student fields
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private PasswordField passField;
    @FXML private TextField deptField;
    @FXML private TextField batchField;

    // Alumni fields
    @FXML private TextField alumniNameField;
    @FXML private TextField alumniEmailField;
    @FXML private TextField yearField;
    @FXML private TextField majorField;
    @FXML private TextField companyField;
    @FXML private TextField positionField;
    @FXML private TextField linkedinField;
    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;

    @FXML
    private void handleRegister(ActionEvent event) {
        String name  = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String pass  = passField.getText().trim();
        String dept  = deptField.getText().trim();
        String batch = batchField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Info", "Name, Email and Password are required.");
            return;
        }

        FileHandler.saveStudent(email + "|" + pass + "|" + name + "|" + phone + "|" + dept + "|" + batch);
        showAlert(Alert.AlertType.INFORMATION, "Registered!", "Welcome to CareerBridge, " + name + "!");
        clearStudentFields();
    }

    @FXML
    private void handleAdminRegister(ActionEvent event) {
        String name     = alumniNameField.getText().trim();
        String email    = alumniEmailField.getText().trim();
        String year     = yearField.getText().trim();
        String major    = majorField.getText().trim();
        String company  = companyField.getText().trim();
        String position = positionField.getText().trim();

        String user     = regUsernameField.getText().trim();
        String pass     = regPasswordField.getText().trim();

        if (name.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Info", "Name, Username and Password are required.");
            return;
        }

        FileHandler.saveAlumni(user + "|" + pass + "|" + name + "|" + email + "|" +
                year + "|" + major + "|" + company + "|" + position );
        showAlert(Alert.AlertType.INFORMATION, "Registered!", "Alumni account created for " + name + ".");
        clearAlumniFields();
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        SceneHelper.switchTo(event, "student-login.fxml", "Student Login");
    }

    @FXML
    private void handleBackToAdminLogin(ActionEvent event) {
        SceneHelper.switchTo(event, "admin-login.fxml", "Alumni Login");
    }

    private void clearStudentFields() {
        nameField.clear(); phoneField.clear(); emailField.clear();
        passField.clear(); deptField.clear(); batchField.clear();
    }

    private void clearAlumniFields() {
        alumniNameField.clear(); alumniEmailField.clear(); yearField.clear();
        majorField.clear(); companyField.clear(); positionField.clear();
        regUsernameField.clear(); regPasswordField.clear();

    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert a = new Alert(type);
        a.setTitle(title); a.setHeaderText(null); a.setContentText(msg);
        a.showAndWait();
    }
}
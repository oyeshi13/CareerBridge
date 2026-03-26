/*package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegisterController {

    @FXML
    private Button mybtn;


    @FXML
    private void handleOpacity(){

        mybtn.setOpacity(0.6d);

    }

}
*/ package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private PasswordField passField;
    @FXML private TextField deptField;
    @FXML private TextField batchField;

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String pass = passField.getText();
        String dept = deptField.getText();
        String batch = batchField.getText();

        // Check if required info is missing
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Info", "Please fill in Name, Email, and Password.");
            return;
        }

        // Prepare data string: Email|Password|Name|Phone|Dept|Batch
        String data = email + "|" + pass + "|" + name + "|" + phone + "|" + dept + "|" + batch;

        // Save to file using FileHandler
        FileHandler.saveStudent(data);

        showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Welcome to Career Bridge, " + name + "!");

        // Clear fields after saving
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        passField.clear();
        deptField.clear();
        batchField.clear();
    }
    @FXML private TextField alumniNameField; // Unique name for Alumni
    @FXML private TextField alumniEmailField; // Unique name for Alumni
    @FXML private TextField yearField;
    @FXML private TextField majorField;
    @FXML private TextField companyField;
    @FXML private TextField positionField;
    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;

    // --- NEW DEDICATED ALUMNI FUNCTION ---
    @FXML
    private void handleAdminRegister() {
        // 1. Collect Alumni-specific data
        String name = alumniNameField.getText();
        String email = alumniEmailField.getText();
        String year = yearField.getText();
        String major = majorField.getText();
        String company = companyField.getText();
        String position = positionField.getText();
        String user = regUsernameField.getText();
        String pass = regPasswordField.getText();

        // 2. Format: Username|Password|Name|Email|Year|Major|Company|Position
        String data = user + "|" + pass + "|" + name + "|" + email + "|" +
                year + "|" + major + "|" + company + "|" + position;

        // 3. Save to the alumni file
        FileHandler.saveAlumni(data);

        // 4. Show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alumni Registered");
        alert.setHeaderText(null);
        alert.setContentText("Account created successfully for: " + name);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToAdminLogin(ActionEvent event) {
        // This allows the "Back to Login" button on the reg page to actually work
        switchScene(event, "admin-login.fxml", "Admin Login");
    }
    @FXML
    public void handleBackToLogin(ActionEvent event) {
        // Redirects back to student login from registration
        switchScene(event, "student-login.fxml", "admin Login - Career Bridge");
    }
    @FXML
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
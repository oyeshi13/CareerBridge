package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminLoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField yearField;
    @FXML private TextField majorField;
    @FXML private TextField companyField;
    @FXML private TextField positionField;
    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;
    
    @FXML
    private void handleAdminLogin(ActionEvent event) { // Add parameter
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if ("admin".equals(user) && "1234".equals(pass)) {
            switchScene(event, "home.fxml", "Home");
        } else {
            showAlert("Invalid Credentials");
        }
    }

    private void showAlert(String invalidCredentials) {
    }

    @FXML
    public void handleBackToAdminLogin(ActionEvent event) { // Add parameter
        switchScene(event, "admin-login.fxml", "Admin Login");
    }

    public void handleAdminRegister(ActionEvent event) {
        // This takes the user from the Login page to the Registration page
        switchScene(event, "alumni-registration.fxml", "Register - Career Bridge");
    }

    // Updated helper method
    private void switchScene(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // This gets the stage from the button that triggered the event
            Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currStage.setScene(new Scene(root));
            currStage.setTitle(title);
            currStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
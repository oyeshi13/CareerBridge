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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
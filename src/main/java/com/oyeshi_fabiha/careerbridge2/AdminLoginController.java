package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleAdminLogin(ActionEvent event) {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Info", "Please enter username and password.");
            return;
        }

        if (user.equals("admin") && pass.equals("1234")) {
            Session.get().login("admin", "Admin", Session.Role.ADMIN);
            SceneHelper.switchTo(event, "admin-dashboard.fxml", "Admin Dashboard");
        } else if (FileHandler.verifyAlumniLogin(user, pass)) {
            String name = FileHandler.getAlumniName(user);
            Session.get().login(user, name, Session.Role.ALUMNI);
            SceneHelper.switchTo(event, "alumni-home.fxml", "Alumni Dashboard");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    @FXML
    public void handleAdminRegister(ActionEvent event) {
        SceneHelper.switchTo(event, "alumni-registration.fxml", "Alumni Registration");
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
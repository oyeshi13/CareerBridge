package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML private RadioButton alumniRadio;
    @FXML private RadioButton studentRadio;

    @FXML
    private void handleRoleSelection(ActionEvent event) {
        if (alumniRadio.isSelected()) {
            openWindow(event, "admin-login.fxml", "Alumni Login");
        } else if (studentRadio.isSelected()) {
            openWindow(event, "student-login.fxml", "Student Login");
        }
    }

    private void openWindow(ActionEvent event, String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(title + " — CareerBridge");
            stage.setResizable(false);
            stage.setScene(scene);

            Stage current = (Stage) alumniRadio.getScene().getWindow();
            current.close();

            stage.setOnCloseRequest(e -> {
                javafx.application.Platform.exit();
                System.exit(0);
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
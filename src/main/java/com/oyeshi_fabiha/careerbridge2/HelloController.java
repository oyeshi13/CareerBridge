package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    public Label welcomeText;
    @FXML private RadioButton alumniRadio;
    @FXML private RadioButton studentRadio;
    @FXML
    private void handleRoleSelection() {
        if (alumniRadio.isSelected()) {
            openNewWindow("admin-login.fxml", "Admin Login");
        } else if (studentRadio.isSelected()) {
            openNewWindow("student-login.fxml", "Student Login");
        }
    }

    // A reusable method to close the current window and open a new one
    private void openNewWindow(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            newStage.setTitle(title + " - CareerBridge");
            newStage.setScene(scene);

            // --- NEW LINE TO FIX YOUR PROBLEM ---
            // This ensures that when the user clicks 'X' on this window, the whole app stops.
            newStage.setOnCloseRequest(e -> {
                javafx.application.Platform.exit();
                System.exit(0);
            });

            Stage currentStage = (Stage) studentRadio.getScene().getWindow();
            currentStage.close();

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }
}
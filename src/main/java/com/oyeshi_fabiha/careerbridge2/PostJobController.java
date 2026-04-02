package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PostJobController {

    @FXML private TextField titleField;
    @FXML private TextField companyField;
    @FXML private TextField locationField;
    @FXML private TextField salaryField;

    @FXML
    private void handlePostJob(ActionEvent event) {
        String title    = titleField.getText().trim();
        String company  = companyField.getText().trim();
        String location = locationField.getText().trim();
        String salary   = salaryField.getText().trim();
        String postedBy = Session.get().getUsername();

        if (title.isEmpty() || company.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Info", "Job Title and Company are required.");
            return;
        }

        FileHandler.saveJob(title + "|" + company + "|" + location + "|" + salary + "|" + postedBy);
        showAlert(Alert.AlertType.INFORMATION, "Job Posted!", "Your job listing has been published.");
        clearFields();
    }

    private void clearFields() {
        titleField.clear();
        companyField.clear();
        locationField.clear();
        salaryField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
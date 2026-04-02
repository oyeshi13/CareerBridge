package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML private Label alumniCountLabel;
    @FXML private Label jobCountLabel;
    @FXML private Label storyCountLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alumniCountLabel.setText(String.valueOf(FileHandler.getAllAlumni().size()));
        jobCountLabel.setText(String.valueOf(FileHandler.getAllJobs().size()));
        storyCountLabel.setText(String.valueOf(FileHandler.getAllStories().size()));
    }
}
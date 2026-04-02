package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminOverviewController implements Initializable {

    @FXML private Label alumniCount;
    @FXML private Label studentCount;
    @FXML private Label jobCount;
    @FXML private Label storyCount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alumniCount.setText(String.valueOf(FileHandler.getAllAlumni().size()));
        studentCount.setText(String.valueOf(countLines("students.txt")));
        jobCount.setText(String.valueOf(FileHandler.getAllJobs().size()));
        storyCount.setText(String.valueOf(FileHandler.getAllStories().size()));
    }

    private int countLines(String filename) {
        File f = new File(filename);
        if (!f.exists()) return 0;
        int count = 0;
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                if (!sc.nextLine().trim().isEmpty()) count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }
}
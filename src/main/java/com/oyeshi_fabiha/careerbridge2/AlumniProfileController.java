package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AlumniProfileController implements Initializable {

    @FXML private Label profileName;
    @FXML private Label profileRole;
    @FXML private Label profileEmail;
    @FXML private Label profileMajor;
    @FXML private Label profileYear;
    @FXML private Label profileCompany;
    @FXML private Label profilePosition;
    @FXML private Label avatarInitials;
    @FXML private Circle avatarCircle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = Session.get().getUsername();
        List<FileHandler.Alumni> all = FileHandler.getAllAlumni();

        for (FileHandler.Alumni a : all) {
            if (a.username.equals(username)) {
                profileName.setText(a.name);
                profileRole.setText(a.position + (a.company.isEmpty() ? "" : " @ " + a.company));
                profileEmail.setText(a.email.isEmpty() ? "Not provided" : a.email);
                profileMajor.setText(a.major.isEmpty() ? "—" : a.major);
                profileYear.setText(a.year.isEmpty() ? "—" : "Class of " + a.year);
                profileCompany.setText(a.company.isEmpty() ? "—" : a.company);
                profilePosition.setText(a.position.isEmpty() ? "—" : a.position);

                String initials = getInitials(a.name);
                avatarInitials.setText(initials);
                return;
            }
        }

        profileName.setText(Session.get().getDisplayName());
        avatarInitials.setText(getInitials(Session.get().getDisplayName()));
    }

    private String getInitials(String name) {
        String[] parts = name.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(2, parts.length); i++) {
            if (!parts[i].isEmpty()) sb.append(parts[i].charAt(0));
        }
        return sb.toString().toUpperCase();
    }
}
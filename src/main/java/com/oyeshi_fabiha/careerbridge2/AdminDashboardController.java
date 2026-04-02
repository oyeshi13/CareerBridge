package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private VBox contentArea;
    @FXML private Button btnOverview;
    @FXML private Button btnAlumni;
    @FXML private Button btnStudents;

    private Button activeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Admin Panel");
        loadPage("admin-overview.fxml");
        setActive(btnOverview);
    }

    @FXML private void showOverview(ActionEvent e) { loadPage("admin-overview.fxml"); setActive(btnOverview); }
    @FXML private void showAlumni(ActionEvent e)   { loadPage("find_mentors.fxml");  setActive(btnAlumni); }

    @FXML
    private void handleLogout(ActionEvent e) {
        Session.get().logout();
        SceneHelper.switchTo(e, "entry.fxml", "Welcome");
    }

    private void loadPage(String fxml) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource(fxml));
            contentArea.getChildren().setAll(p);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setActive(Button btn) {
        String inactive = "-fx-background-color: transparent; -fx-text-fill: #BDC3C7; -fx-cursor: hand; -fx-font-size: 14px; -fx-alignment: BASELINE_LEFT;";
        String active   = "-fx-background-color: #3D566E; -fx-text-fill: white; -fx-cursor: hand; -fx-font-size: 14px; -fx-alignment: BASELINE_LEFT; -fx-background-radius: 8;";
        if (activeBtn != null) activeBtn.setStyle(inactive);
        btn.setStyle(active);
        activeBtn = btn;
    }
}
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

public class AlumniHomeController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private VBox contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnPostJob;
    @FXML private Button btnStories;
    @FXML private Button btnMessages;
    @FXML private Button btnProfile;

    private Button activeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome, " + Session.get().getDisplayName() + "!");
        loadPage("dashboard-alumni.fxml");
        setActive(btnDashboard);
    }

    @FXML private void showDashboard(ActionEvent e)  { loadPage("dashboard-alumni.fxml"); setActive(btnDashboard); }
    @FXML private void showPostJob(ActionEvent e)    { loadPage("job_board.fxml");          setActive(btnPostJob); }
    @FXML private void showStories(ActionEvent e)    { loadPage("stories.fxml");           setActive(btnStories); }
    @FXML private void showMessages(ActionEvent e)   { loadPage("messaging.fxml");         setActive(btnMessages); }
    @FXML private void showProfile(ActionEvent e)    { loadPage("alumni-profile.fxml");    setActive(btnProfile); }

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
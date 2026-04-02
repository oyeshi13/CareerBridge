package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private VBox contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnMentors;
    @FXML private Button btnJobs;
    @FXML private Button btnStories;
    @FXML private Button btnMessages;
    @FXML private TextField topSearchField;

    private Button activeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String name = Session.get().getDisplayName();
        welcomeLabel.setText("Welcome, " + name + "!");
        loadPage("dashboard-student.fxml");
        setActive(btnDashboard);
    }

    @FXML private void showDashboard(ActionEvent e) { loadPage("dashboard-student.fxml"); setActive(btnDashboard); }
    @FXML private void showMentors(ActionEvent e)   { loadPage("find_mentors.fxml");      setActive(btnMentors); }
    @FXML private void showJobs(ActionEvent e)      { loadPage("job_board.fxml");          setActive(btnJobs); }
    @FXML private void showStories(ActionEvent e)   { loadPage("stories.fxml");            setActive(btnStories); }
    @FXML private void showMessages(ActionEvent e)  { loadPage("messaging.fxml");          setActive(btnMessages); }

    @FXML
    private void handleSearch(ActionEvent e) {
        String query = topSearchField.getText().trim();
        if (query.isEmpty()) return;
        loadPageWithSearch("find_mentors.fxml", query);
        setActive(btnMentors);
    }

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

    private void loadPageWithSearch(String fxml, String query) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent p = loader.load();
            Object ctrl = loader.getController();
            if (ctrl instanceof FindMentorsController fmc) {
                fmc.search(query);
            }
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
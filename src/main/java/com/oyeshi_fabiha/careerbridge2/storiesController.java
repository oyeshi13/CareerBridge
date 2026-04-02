package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class storiesController implements Initializable {

    @FXML private VBox storiesContainer;
    @FXML private TextField nameInput;
    @FXML private TextArea storyInput;
    @FXML private Button submitBtn;
    @FXML private VBox postPanel;

    private static final String[] CARD_COLORS = {
            "#6C63FF", "#E74C3C", "#27AE60", "#F39C12", "#2980B9", "#8E44AD"
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.Role role = Session.get().getRole();
        boolean canPost = (role == Session.Role.ALUMNI || role == Session.Role.ADMIN);

        if (postPanel != null) postPanel.setVisible(canPost);
        if (postPanel != null) postPanel.setManaged(canPost);

        if (canPost && nameInput != null) {
            nameInput.setText(Session.get().getDisplayName());
        }

        loadStories();
    }

    private void loadStories() {
        storiesContainer.getChildren().clear();
        List<String[]> stories = FileHandler.getAllStories();
        if (stories.isEmpty()) {
            Label empty = new Label("No stories yet. Alumni — be the first to share your journey!");
            empty.setStyle("-fx-text-fill: #7F8C8D; -fx-font-size: 14px;");
            storiesContainer.getChildren().add(empty);
            return;
        }
        for (int i = 0; i < stories.size(); i++) {
            storiesContainer.getChildren().add(buildStoryCard(stories.get(i), i));
        }
    }

    private VBox buildStoryCard(String[] story, int idx) {
        String accent = CARD_COLORS[idx % CARD_COLORS.length];
        VBox card = new VBox(8);
        card.setPadding(new Insets(16));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; " +
                "-fx-border-color: " + accent + "; -fx-border-width: 0 0 0 4; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.06), 8, 0, 0, 3);");

        Label author = new Label("✨ " + story[0]);
        author.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: " + accent + ";");

        Label text = new Label(story[1]);
        text.setStyle("-fx-text-fill: #4A4A4A; -fx-font-size: 13px;");
        text.setWrapText(true);

        card.getChildren().addAll(author, text);
        return card;
    }

    @FXML
    private void handleAddStory() {
        String name  = nameInput.getText().trim();
        String story = storyInput.getText().trim();

        if (name.isEmpty() || story.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Missing Info");
            a.setHeaderText(null);
            a.setContentText("Please fill in your name and story.");
            a.showAndWait();
            return;
        }

        FileHandler.saveStory(name, story);
        storyInput.clear();
        loadStories();

        Alert ok = new Alert(Alert.AlertType.INFORMATION);
        ok.setTitle("Story Posted!");
        ok.setHeaderText(null);
        ok.setContentText("Your story has been shared with the community!");
        ok.showAndWait();
    }
}
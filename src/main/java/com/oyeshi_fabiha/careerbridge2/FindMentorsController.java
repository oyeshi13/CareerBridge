package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindMentorsController implements Initializable {

    @FXML private TextField searchField;
    @FXML private FlowPane mentorGrid;
    @FXML private Label resultCountLabel;

    private static final String[] AVATAR_COLORS = {
            "#6C63FF", "#E74C3C", "#27AE60", "#F39C12",
            "#2980B9", "#8E44AD", "#16A085", "#D35400"
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAlumni(FileHandler.getAllAlumni());
        searchField.textProperty().addListener((obs, old, val) -> search(val));
    }

    public void search(String query) {
        searchField.setText(query);
        List<FileHandler.Alumni> results = query == null || query.isBlank()
                ? FileHandler.getAllAlumni()
                : FileHandler.searchAlumni(query);
        loadAlumni(results);
    }

    private void loadAlumni(List<FileHandler.Alumni> list) {
        mentorGrid.getChildren().clear();
        int count = list.size();
        resultCountLabel.setText(count + " mentor" + (count == 1 ? "" : "s") + " found");

        if (list.isEmpty()) {
            Label empty = new Label("No mentors found. Try a different search.");
            empty.setStyle("-fx-text-fill: #7F8C8D; -fx-font-size: 14px;");
            mentorGrid.getChildren().add(empty);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            mentorGrid.getChildren().add(buildCard(list.get(i), i));
        }
    }

    private VBox buildCard(FileHandler.Alumni a, int idx) {
        String color = AVATAR_COLORS[idx % AVATAR_COLORS.length];
        String initials = getInitials(a.name);

        VBox card = new VBox(12);
        card.setPrefWidth(230);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 16; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 12, 0, 0, 4);");

        StackPane avatar = new StackPane();
        Circle circle = new Circle(36);
        circle.setFill(Color.web(color));
        Label initLabel = new Label(initials);
        initLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        avatar.getChildren().addAll(circle, initLabel);
        avatar.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(a.name);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #2C3E50;");
        nameLabel.setWrapText(true);

        String posText = a.position.isEmpty() ? "" : a.position;
        String compText = a.company.isEmpty() ? "" : "@ " + a.company;
        Label roleLabel = new Label(posText + ((!posText.isEmpty() && !compText.isEmpty()) ? " " : "") + compText);
        roleLabel.setStyle("-fx-text-fill: #7F8C8D; -fx-font-size: 12px;");
        roleLabel.setWrapText(true);

        HBox tags = new HBox(6);
        tags.setAlignment(Pos.CENTER_LEFT);
        if (!a.major.isEmpty()) tags.getChildren().add(buildTag(a.major, "#EBF5FB", "#2980B9"));
        if (!a.year.isEmpty())  tags.getChildren().add(buildTag("Class of " + a.year, "#EAFAF1", "#27AE60"));

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #ECF0F1;");

        VBox contactBox = new VBox(4);
        if (!a.email.isEmpty()) {
            Label emailLabel = new Label("✉  " + a.email);
            emailLabel.setStyle("-fx-text-fill: #5D6D7E; -fx-font-size: 11px;");
            emailLabel.setWrapText(true);
            contactBox.getChildren().add(emailLabel);
        }

        Button connectBtn = new Button("Connect");
        connectBtn.setMaxWidth(Double.MAX_VALUE);
        connectBtn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; " +
                "-fx-background-radius: 20; -fx-font-weight: bold; -fx-cursor: hand;");
        connectBtn.setOnAction(e -> showContactDialog(a));

        card.getChildren().addAll(avatar, nameLabel, roleLabel, tags, sep, contactBox, connectBtn);
        return card;
    }

    private Label buildTag(String text, String bg, String fg) {
        Label tag = new Label(text);
        tag.setStyle("-fx-background-color: " + bg + "; -fx-text-fill: " + fg + "; " +
                "-fx-padding: 2 8 2 8; -fx-background-radius: 10; -fx-font-size: 11px;");
        return tag;
    }

    private void showContactDialog(FileHandler.Alumni a) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Contact " + a.name);
        dialog.setHeaderText(a.name + " — " + a.position + " at " + a.company);
        String content = "📧 Email:    " + (a.email.isEmpty() ? "Not provided" : a.email) + "\n" +
                "🎓 Major:    " + (a.major.isEmpty() ? "N/A" : a.major) + "\n" +
                "📅 Year:     " + (a.year.isEmpty()  ? "N/A" : "Class of " + a.year);
        dialog.setContentText(content);
        dialog.showAndWait();
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
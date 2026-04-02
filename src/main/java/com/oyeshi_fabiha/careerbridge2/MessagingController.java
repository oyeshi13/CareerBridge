package com.oyeshi_fabiha.careerbridge2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;

public class MessagingController implements Initializable {

    @FXML private VBox contactsList;
    @FXML private VBox chatBox;
    @FXML private TextField messageInput;
    @FXML private Button sendBtn;
    @FXML private Label chatHeader;
    @FXML private VBox newContactPane;
    @FXML private TextField newContactField;
    @FXML private VBox chatArea;
    @FXML private ScrollPane chatScroll;

    private String currentContact = null;
    private final String me = Session.get().getUsername();

    private static final String[] COLORS = {
            "#6C63FF", "#E74C3C", "#27AE60", "#F39C12", "#2980B9", "#8E44AD", "#16A085"
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chatBox.setVisible(false);
        newContactPane.setVisible(false);
        loadContacts();
        sendBtn.setOnAction(e -> sendMessage());
        messageInput.setOnAction(e -> sendMessage());
    }

    private void loadContacts() {
        contactsList.getChildren().clear();
        List<String> contacts = FileHandler.getContacts(me);

        // Add all alumni as possible contacts for students, and all students for alumni
        if (Session.get().getRole() == Session.Role.STUDENT) {
            for (FileHandler.Alumni a : FileHandler.getAllAlumni()) {
                if (!contacts.contains(a.username)) contacts.add(a.username);
            }
        } else if (Session.get().getRole() == Session.Role.ALUMNI) {
            for (String line : FileHandler.readLines("students.txt")) {
                String[] p = line.split("\\|");
                if (p.length >= 1) {
                    String id = p[0].trim();
                    if (!contacts.contains(id)) contacts.add(id);
                }
            }
        }

        if (contacts.isEmpty()) {
            Label empty = new Label("No contacts yet.");
            empty.setStyle("-fx-text-fill: #718096; -fx-padding: 12;");
            contactsList.getChildren().add(empty);
            return;
        }

        int idx = 0;
        for (String contact : contacts) {
            contactsList.getChildren().add(buildContactRow(contact, idx++));
        }
    }

    private HBox buildContactRow(String contactId, int idx) {
        String color = COLORS[idx % COLORS.length];
        String displayName = resolveDisplayName(contactId);
        String initials = getInitials(displayName);

        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10, 14, 10, 14));
        row.setStyle("-fx-cursor: hand; -fx-background-color: " +
                (contactId.equals(currentContact) ? "#ede9fe" : "transparent") + "; -fx-background-radius: 10;");

        StackPane avatar = new StackPane();
        Circle circle = new Circle(20);
        circle.setFill(Color.web(color));
        Label initLabel = new Label(initials);
        initLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;");
        avatar.getChildren().addAll(circle, initLabel);

        VBox info = new VBox(2);
        Label nameLabel = new Label(displayName);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2d3748; -fx-font-size: 13px;");
        Label idLabel = new Label("@" + contactId);
        idLabel.setStyle("-fx-text-fill: #718096; -fx-font-size: 11px;");
        info.getChildren().addAll(nameLabel, idLabel);

        row.getChildren().addAll(avatar, info);
        row.setOnMouseClicked(e -> openChat(contactId, color));
        row.setOnMouseEntered(e -> {
            if (!contactId.equals(currentContact))
                row.setStyle("-fx-cursor: hand; -fx-background-color: #f7f7ff; -fx-background-radius: 10;");
        });
        row.setOnMouseExited(e -> {
            if (!contactId.equals(currentContact))
                row.setStyle("-fx-cursor: hand; -fx-background-color: transparent; -fx-background-radius: 10;");
        });

        return row;
    }

    private void openChat(String contactId, String accentColor) {
        currentContact = contactId;
        String displayName = resolveDisplayName(contactId);
        chatHeader.setText("💬  " + displayName);
        chatBox.setVisible(true);
        newContactPane.setVisible(false);
        loadMessages(accentColor);
        loadContacts(); // refresh to highlight selected
    }

    private void loadMessages(String accentColor) {
        chatArea.getChildren().clear();
        List<FileHandler.Message> messages = FileHandler.getConversation(me, currentContact);

        if (messages.isEmpty()) {
            Label empty = new Label("No messages yet. Say hello! 👋");
            empty.setStyle("-fx-text-fill: #718096; -fx-font-size: 13px;");
            HBox wrap = new HBox(empty);
            wrap.setAlignment(Pos.CENTER);
            wrap.setPadding(new Insets(20));
            chatArea.getChildren().add(wrap);
        }

        for (FileHandler.Message msg : messages) {
            boolean isMine = msg.sender.equalsIgnoreCase(me);
            chatArea.getChildren().add(buildBubble(msg, isMine, accentColor));
        }


        chatScroll.layout();
        chatScroll.setVvalue(1.0);
    }

    private VBox buildBubble(FileHandler.Message msg, boolean isMine, String accentColor) {
        Label text = new Label(msg.text);
        text.setWrapText(true);
        text.setMaxWidth(320);
        text.setStyle("-fx-text-fill: " + (isMine ? "white" : "#2d3748") + "; -fx-font-size: 13px;");

        Label time = new Label(msg.timestamp);
        time.setStyle("-fx-text-fill: " + (isMine ? "rgba(255,255,255,0.65)" : "#a0aec0") + "; -fx-font-size: 10px;");

        VBox bubble = new VBox(4, text, time);
        bubble.setPadding(new Insets(10, 14, 10, 14));
        bubble.setStyle("-fx-background-color: " + (isMine ? accentColor : "#f0f4f8") + "; " +
                "-fx-background-radius: " + (isMine ? "16 16 4 16" : "16 16 16 4") + ";");
        bubble.setMaxWidth(340);

        HBox wrapper = new HBox(bubble);
        wrapper.setPadding(new Insets(3, 12, 3, 12));
        wrapper.setAlignment(isMine ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);


        VBox outer = new VBox(wrapper);
        return outer;
    }

    private void sendMessage() {
        if (currentContact == null) return;
        String text = messageInput.getText().trim();
        if (text.isEmpty()) return;

        FileHandler.saveMessage(me, currentContact, text);
        messageInput.clear();

        // Reload messages
        int idx = 0;
        for (FileHandler.Alumni a : FileHandler.getAllAlumni()) {
            if (a.username.equals(currentContact)) break;
            idx++;
        }
        String color = COLORS[idx % COLORS.length];
        loadMessages(color);
    }

    @FXML
    private void handleNewChat() {
        chatBox.setVisible(false);
        newContactPane.setVisible(true);
    }

    private String resolveDisplayName(String id) {

        for (FileHandler.Alumni a : FileHandler.getAllAlumni()) {
            if (a.username.equalsIgnoreCase(id)) return a.name.isEmpty() ? id : a.name;
        }

        String name = FileHandler.getStudentName(id);
        return name.equals("Student") ? id : name;
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
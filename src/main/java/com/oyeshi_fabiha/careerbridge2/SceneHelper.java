package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHelper {

    public static void switchTo(ActionEvent event, String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title + " — CareerBridge");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchTo(Stage stage, String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(fxml));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle(title + " — CareerBridge");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
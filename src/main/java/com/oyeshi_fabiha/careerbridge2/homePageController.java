package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.io.IOException;

public class homePageController {
    @FXML
    VBox changer;
    @FXML private Button dashboard;
    @FXML private Button mentors;
    @FXML private Button stories;
    @FXML
    public void findMentor(ActionEvent actionEvent) {
        dashboard.setStyle("-fx-background-color: transparent; -fx-text-fill: #BDC3C7; -fx-cursor: hand;");
        mentors.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-cursor: hand;");
        stories.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-cursor: hand;");

        try{
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("find_mentors.fxml"));
            Parent page = loader2.load();
            //Stage currStage = (Stage) changer.getScene().getWindow();
            changer.getChildren().setAll(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
@FXML
    public void findStories(ActionEvent event){
        dashboard.setStyle("-fx-background-color: transparent; -fx-text-fill: #BDC3C7; -fx-cursor: hand;");
        stories.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-cursor: hand;");
        mentors.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-cursor: hand;");


        try{
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("stories.fxml"));
            Parent page = loader3.load();
            changer.getChildren().setAll(page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

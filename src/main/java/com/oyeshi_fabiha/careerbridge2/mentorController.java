package com.oyeshi_fabiha.careerbridge2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class mentorController {
    @FXML
    VBox changer;
    @FXML
    public void findMentor(ActionEvent actionEvent) {
        try{
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("find_mentors.fxml"));
            Parent page = loader2.load();
            //Stage currStage = (Stage) changer.getScene().getWindow();
            changer.getChildren().setAll(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

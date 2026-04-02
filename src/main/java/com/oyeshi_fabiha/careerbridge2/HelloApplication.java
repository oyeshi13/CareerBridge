package com.oyeshi_fabiha.careerbridge2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("entry.fxml"));
        Scene scene = new Scene(loader.load(), 650, 460);
        stage.setTitle("CareerBridge");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
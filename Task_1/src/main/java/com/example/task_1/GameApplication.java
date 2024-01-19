package com.example.task_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameApplication extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameApplication.primaryStage = primaryStage;
        loadHomeScreen();
        primaryStage.show();
    }

    public static void loadHomeScreen() {
        try {
            Parent root = FXMLLoader.load(GameApplication.class.getResource("HomeScreen.fxml"));
            Image icon = new Image("file:src/GuessTheNumber.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setTitle("Number Guessing Game - Home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadGameScreen() {
        try {
            Parent root = FXMLLoader.load(GameApplication.class.getResource("game.fxml"));
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setTitle("Number Guessing Game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

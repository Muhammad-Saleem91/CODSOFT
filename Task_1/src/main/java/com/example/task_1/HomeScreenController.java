package com.example.task_1;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class HomeScreenController {

    @FXML
    private StackPane stackPane;

    public void initialize() {
        // Remove background-related code
    }

    @FXML
    private void handlePlayButton() {
        // Load the game screen
        GameApplication.loadGameScreen();
    }
}

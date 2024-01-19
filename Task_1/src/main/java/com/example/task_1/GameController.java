package com.example.task_1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;
import java.util.Random;

public class GameController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField guessTextField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button guessButton;

    private int randomNo;
    private int maxAttempts = 10;
    private int attempts = 0;
    private int roundsPlayed = 0;
    private int roundsWon = 0;
    private int totalAttempts = 0;

    public void initialize() {
        startNewRound();
    }

    private void startNewRound() {
        Random random = new Random();
        randomNo = random.nextInt(1, 100);
        welcomeLabel.setText("Welcome to the Number Guessing Game!");
        feedbackLabel.setText("");
        guessButton.setDisable(false);
        attempts = 0;
    }

    @FXML
    private void handleGuessButton() {
        int guessedNo;

        try {
            guessedNo = Integer.parseInt(guessTextField.getText());
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number!");
            return;
        }

        attempts++;

        if (guessedNo == randomNo) {
            handleCorrectGuess();
        } else if (guessedNo > randomNo) {
            feedbackLabel.setText("Too high! Try again.");
            resetTextField();
        } else {
            feedbackLabel.setText("Too low! Try again.");
            resetTextField();
        }

        if (attempts == maxAttempts) {
            handleGameEnd();
        }
    }

    private void handleCorrectGuess() {
        feedbackLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
        totalAttempts += attempts;
        roundsPlayed++;
        roundsWon++;

        // Show dialog box for user feedback
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.setContentText("You guessed the correct number!\nDo you want to play again?");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(null) == yesButton) {
            startNewRound();
        } else {
            showScoreCard();
        }
    }

    private void handleGameEnd() {
        totalAttempts += attempts;
        roundsPlayed++;

        // Show game over dialog
        Alert gameOverAlert = new Alert(AlertType.CONFIRMATION);
        gameOverAlert.setTitle("Game Over");

        gameOverAlert.setHeaderText("Game Over. You couldn't guess the number after max attempts.");
        gameOverAlert.setContentText("Correct Number: " + randomNo);

        // Add event handler for the OK button
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        gameOverAlert.getButtonTypes().setAll(okButton);

        // Show the alert and wait for user interaction
        Optional<ButtonType> result = gameOverAlert.showAndWait();

        // If the OK button is pressed, run the showScoreCard method
        if (result.isPresent() && result.get() == okButton) {
            showScoreCard();
        }
    }


    private void resetTextField() {
        guessTextField.clear();
        guessTextField.requestFocus();
    }

    private void resetGame() {
        startNewRound();
    }

    private void showScoreCard() {
        Dialog<String> scoreCardDialog = new Dialog<>();
        scoreCardDialog.setTitle("Score Card");
        scoreCardDialog.setHeaderText(null);

        ButtonType newGameButton = new ButtonType("New Game", ButtonBar.ButtonData.YES);
        ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.NO);
        scoreCardDialog.getDialogPane().getButtonTypes().setAll(newGameButton, exitButton);

        TextArea scoreTextArea = new TextArea("Rounds Played: " + this.roundsPlayed +
                "\nRounds Won: " + this.roundsWon +
                "\nTotal Attempts: " + this.totalAttempts);
        scoreTextArea.setEditable(false);

        scoreCardDialog.getDialogPane().setContent(scoreTextArea);

        scoreCardDialog.setResultConverter(buttonType -> {
            if (buttonType == newGameButton) {
                resetGame();
            } else {
                exitGame();
            }
            return null;
        });

        scoreCardDialog.showAndWait();
    }

    private void exitGame() {
        // Show exit message and close the application
        Alert exitAlert = new Alert(AlertType.INFORMATION);
        exitAlert.setTitle("Thanks for Playing");
        exitAlert.setHeaderText(null);
        exitAlert.setContentText("Thanks for playing the game!");
        exitAlert.showAndWait();

        System.exit(0);
    }

    @FXML
    private void handleEnterKey() {
        handleGuessButton();
    }

    @FXML
    private void handleNewGameButton() {
        resetGame();
    }
}

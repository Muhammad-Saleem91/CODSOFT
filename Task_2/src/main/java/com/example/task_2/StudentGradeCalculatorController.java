package com.example.task_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class StudentGradeCalculatorController {

    @FXML
    private TextField nameField, idField, departmentField, dsaField, coalField, discreteField, mvcField, ppField, maxMarksField;

    @FXML
    private Label totalMarksLabel, percentageLabel, gradeLabel;

    @FXML
    private void calculateGrade() {
        try {
            // Extracting marks and max marks from text fields
            float dsaMarks = Float.parseFloat(dsaField.getText());
            float coalMarks = Float.parseFloat(coalField.getText());
            float discreteMarks = Float.parseFloat(discreteField.getText());
            float mvcMarks = Float.parseFloat(mvcField.getText());
            float ppMarks = Float.parseFloat(ppField.getText());

            // Extracting max marks for each subject
            float maxMarks = Float.parseFloat(maxMarksField.getText());

            // Validating max marks
            if (maxMarks <= 0) {
                throw new NumberFormatException("Max Marks should be greater than 0");
            }

            // Calculating total marks
            float totalMarks = dsaMarks + coalMarks + discreteMarks + mvcMarks + ppMarks;

            // Calculating average percentage
            float averagePercentage = (totalMarks / (maxMarks * 5)) * 100;

            // Validating average percentage
            if (averagePercentage > 100) {
                throw new NumberFormatException("You have entered invalid marks!!");
            }

            // Assigning grade based on average percentage
            String grade;
            if (averagePercentage >= 90) {
                grade = "A-ONE";
            } else if (averagePercentage >= 80) {
                grade = "A";
            } else if (averagePercentage >= 70) {
                grade = "B";
            } else if (averagePercentage >= 60) {
                grade = "C";
            } else if (averagePercentage >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            // Show result in a dialog box
            showResultDialog(nameField.getText(), idField.getText(), departmentField.getText(), totalMarks, averagePercentage, grade);

        } catch (NumberFormatException e) {
            // Handle the case where a user enters invalid input (non-numeric values)
            clearFields();
            // Display an error message or take appropriate action
            showErrorDialog("Invalid Input", "Please enter valid numeric values for marks.");
        }
    }

    @FXML
    private void clearFields() {
        // Clearing input fields and result labels
        nameField.clear();
        idField.clear();
        departmentField.clear();
        dsaField.clear();
        coalField.clear();
        discreteField.clear();
        mvcField.clear();
        ppField.clear();
        maxMarksField.clear();
        totalMarksLabel.setText("Total Marks:");
        percentageLabel.setText("Percentage:");
        gradeLabel.setText("Grade:");
    }

    private void showResultDialog(String name, String id, String department, float totalMarks, float percentage, String grade) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText("Student Marksheet ");
        alert.setContentText(
                "Name: " + name + "\n" +
                        "ID: " + id + "\n" +
                        "Department: " + department + "\n" +
                        "Total Marks: " + totalMarks + "\n" +
                        "Percentage: " + String.format("%.2f", percentage) + "%" + "\n" +
                        "Grade: " + grade
        );

        alert.showAndWait();
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
